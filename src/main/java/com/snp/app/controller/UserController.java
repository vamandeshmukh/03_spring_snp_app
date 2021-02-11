package com.snp.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snp.app.dtos.Users;
import com.snp.app.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, Object>> authenticate(@RequestBody Users userDto) throws AuthenticationException {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
		Map<String, Object> responsePayload = userService.authenticateUser(userDto);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responsePayload);
	}

	@PostMapping("/register")
	public String saveUser(@RequestBody Users user) throws IOException {
		return userService.registerNewUser(user);
	}

	@GetMapping
	public List<Users> listUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/{id}")
	public Users findById(@PathVariable String id) {
		return userService.findUserById(id);
	}

	@PostMapping("/finduserbyemail")
	public List<Users> findByEmail(@RequestBody Users userDto) {
		return userService.findUserByEmail(userDto.getEmail());
	}

	@PostMapping("/updateuserphotoId")
	public String updateUserPhoto(@RequestBody Users userDto) {
		return userService.updateUserPhotoId(userDto);
	}

	@PutMapping("/{id}")
	public String updateUser(@PathVariable String id, @RequestBody Users userDto) {
		return userService.updateUser(id, userDto);
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}
}