package com.snp.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snp.app.dtos.Friends;
import com.snp.app.exception.RecordNotFoundException;
import com.snp.app.repository.FriendsRepository;
import com.snp.app.utility.Utility;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendsRepository friendRepository;

	@Override
	public String createNewRequest(Friends friendDto) {
		friendRepository.save(friendDto);
		String responseMsg = "{ \"status\": 200, \"message\": \"Friend request created successfully\"}";
		return responseMsg;
	}

	@Override
	public List<Map<String, String>> findAllFriends() {
		List<Map<String, String>> finalList = new ArrayList<Map<String, String>>();
		List<Friends> friends = friendRepository.findAll();
		Map<String, String> friendsMap = null;
		for (Friends frd : friends) {
			friendsMap = new HashMap<String, String>();
			friendsMap.put("id", frd.get_id());
			friendsMap.put("userId", frd.getUserId());
			friendsMap.put("friendId", frd.getFriendId());
			friendsMap.put("status", frd.getStatus());
			finalList.add(friendsMap);
		}
		return finalList;
	}

	@Override
	public Map<String, String> findFriendById(String id) {
		Friends friend = friendRepository.findBy_id(id);
		if (friend == null) {
			throw new RecordNotFoundException();
		}
		Map<String, String> friendsMap = new HashMap<String, String>();
		friendsMap.put("id", friend.get_id());
		friendsMap.put("userId", friend.getUserId());
		friendsMap.put("friendId", friend.getFriendId());
		friendsMap.put("status", friend.getStatus());
		return friendsMap;
	}

	@Override
	public Map<String, String> findFriendByUserId(String userId) {
		Friends friend = friendRepository.findByUserId(userId);
		if (friend == null) {
			throw new RecordNotFoundException();
		}
		Map<String, String> friendsMap = new HashMap<String, String>();
		friendsMap.put("id", friend.get_id());
		friendsMap.put("userId", friend.getUserId());
		friendsMap.put("friendId", friend.getFriendId());
		friendsMap.put("status", friend.getStatus());
		return friendsMap;
	}

	@Override
	public Map<String, String> findFriendByFriendId(String friendId) {
		Friends friend = friendRepository.findByFriendId(friendId);
		if (friend == null) {
			throw new RecordNotFoundException();
		}
		Map<String, String> friendsMap = new HashMap<String, String>();
		friendsMap.put("id", friend.get_id());
		friendsMap.put("userId", friend.getUserId());
		friendsMap.put("friendId", friend.getFriendId());
		friendsMap.put("status", friend.getStatus());
		return friendsMap;
	}

	@Override
	public String updateFriend(String id, Friends friendToUpdate) {
		Friends existingFriend = friendRepository.findBy_id(id);
		if (existingFriend == null) {
			throw new RecordNotFoundException();
		} else {
			Friends finalFriend = null;
			try {
				finalFriend = Utility.mergeObjects(existingFriend, friendToUpdate);
			} catch (Exception e) {
				e.printStackTrace();
				String responseMsg = "{ \"status\": 200, \"message\": \"Friend request cannot updated\"}";
				return responseMsg;
			}
			friendRepository.save(finalFriend);
		}

		String responseMsg = "{ \"status\": 200, \"message\": \"Friend request updated successfully\"}";
		return responseMsg;
	}

}
