package com.snp.app.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.snp.app.config.JwtTokenUtil;
import com.snp.app.dtos.Files;
import com.snp.app.dtos.Users;
import com.snp.app.exception.RecordNotFoundException;
import com.snp.app.repository.FilesRepository;
import com.snp.app.repository.UsersRepository;
import com.snp.app.utility.Utility;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private FilesRepository fileRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public Map<String, Object> authenticateUser(Users userToLogin) throws AuthenticationException {
		final Users user = userRepository.findUserByEmail(userToLogin.getEmail());
		Map<String, Object> tokenResponse = new HashMap<String, Object>();

		if (user != null && user.getIsActive() == false) {
			tokenResponse.put("message", "You account has been blocked.");
			return tokenResponse;
		}

		final String token = jwtTokenUtil.generateToken(user);
		tokenResponse.put("_id", user.get_id());
		tokenResponse.put("firstName", user.getFirstName());
		tokenResponse.put("lastName", user.getLastName());
		tokenResponse.put("dob", user.getDob().toString());
		tokenResponse.put("phone", user.getPhone());
		tokenResponse.put("email", user.getEmail());
		tokenResponse.put("isActive", user.getIsActive());
		tokenResponse.put("isAdmin", user.getIsAdmin());
		tokenResponse.put("gender", user.getGender());
		tokenResponse.put("city", user.getCity());
		tokenResponse.put("state", user.getState());
		tokenResponse.put("country", user.getCountry());
		tokenResponse.put("pincode", user.getPincode());
		tokenResponse.put("photoId", user.getPhotoId());
		tokenResponse.put("profession", user.getProfession());
		tokenResponse.put("token", token);
		return tokenResponse;
	}

	@Override
	public String registerNewUser(Users userToRegister) {
		File file = null;
		byte[] data = null;

		try {
			file = ResourceUtils.getFile("classpath:config/user-male.png");
			data = java.nio.file.Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			String responseMsg = "{ \"status\": 200, \"message\": \"User cannot registered.\"}";
			return responseMsg;
		}

		Files userIcon = new Files();
		userIcon.setFileName("Sample.PNG");
		userIcon.setContentType("image/jpg");
		userIcon.setImage(new Binary(BsonBinarySubType.BINARY, data));
		userIcon = fileRepository.insert(userIcon);

		userToRegister.setPassword(bcryptEncoder.encode(userToRegister.getPassword()));
		userToRegister.setPhotoId(userIcon.get_id());
		Users userRegistered = userRepository.save(userToRegister);
		userRegistered.setId(userRegistered.get_id());
		userRepository.save(userRegistered);
		String responseMsg = "{ \"status\": 200, \"message\": \"User registered successfully\"}";
		return responseMsg;
	}

	@Override
	public List<Users> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public String updateUser(String userId, Users userToUpdate) {
		Users existingUser = findUserById(userId);
		if (userToUpdate.getPassword() != null) {
			userToUpdate.setPassword(bcryptEncoder.encode(userToUpdate.getPassword()));
		}

		if (existingUser != null) {
			Users finalUser = null;
			try {
				finalUser = Utility.mergeObjects(existingUser, userToUpdate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			mongoTemplate.save(finalUser);
		}

		String responseMsg = "{ \"status\": 200, \"message\": \"User updated successfully\"}";
		return responseMsg;
	}

	@Override
	public String deleteUser(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(userId));
		mongoTemplate.findAndRemove(query, Users.class);
		String responseMsg = "{ \"status\": 200, \"message\": \"User deleted successfully\"}";
		return responseMsg;
	}

	@Override
	public Users findUserById(String userId) {
		Users user = userRepository.findBy_id(userId);
		if (user == null) {
			throw new RecordNotFoundException();
		}
		return user;
	}

	@Override
	public List<Users> findUserByEmail(String email) {
		Users user = userRepository.findUserByEmail(email);
		List<Users> userList = new ArrayList<Users>();
		if (user == null) {
			return userList;
		}

		userList.add(user);
		return userList;
	}

	@Override
	public String updateUserPhotoId(Users userToUpdate) {
		Users existingUser = findUserById(userToUpdate.getId());
		existingUser.setPhotoId(userToUpdate.getPhotoId());
		userRepository.save(existingUser);
		String responseMsg = "{ \"status\": 200, \"message\": \"User photoId updated successfully\"}";
		return responseMsg;
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}
}
