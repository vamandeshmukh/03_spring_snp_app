package com.snp.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.snp.app.dtos.Files;

public interface FilesRepository extends MongoRepository<Files, String> {
	
	Files findBy_id(String id); 
}
