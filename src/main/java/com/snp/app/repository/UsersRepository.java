package com.snp.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.snp.app.dtos.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {

	Users findUserByEmail(String email);

	Users findBy_id(String id);
}