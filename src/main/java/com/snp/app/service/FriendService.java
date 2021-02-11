package com.snp.app.service;

import java.util.List;
import java.util.Map;

import com.snp.app.dtos.Friends;

public interface FriendService {

	String createNewRequest(Friends friendDto);

	List<Map<String, String>> findAllFriends();

	Map<String, String> findFriendById(String id);

	Map<String, String> findFriendByUserId(String userId);

	Map<String, String> findFriendByFriendId(String friendId);

	String updateFriend(String id, Friends friendDto);

}
