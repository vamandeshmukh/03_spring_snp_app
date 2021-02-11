package com.snp.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.snp.app.dtos.Posts;

public interface PostsRepository extends MongoRepository<Posts, String> {

	Posts findBy_id(String id);

	List<Posts> findByUserId(String userId);
}
