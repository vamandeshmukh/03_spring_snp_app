package com.snp.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.snp.app.dtos.Likes;

public interface LikesRepository extends MongoRepository<Likes, String> {

	Likes findBy_id(String id);

}
