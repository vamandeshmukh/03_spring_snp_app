package com.snp.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.snp.app.dtos.Friends;

public interface FriendsRepository extends MongoRepository<Friends, String> {

	Friends findBy_id(String id);

	Friends findByUserId(String userId);

	Friends findByFriendId(String friendId);
}
