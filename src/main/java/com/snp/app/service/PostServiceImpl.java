package com.snp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.snp.app.dtos.Posts;
import com.snp.app.exception.RecordNotFoundException;
import com.snp.app.repository.PostsRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostsRepository postRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String createNewPost(Posts postToCreate) {
		postRepository.save(postToCreate);
		String responseMsg = "{ \"status\": 200, \"message\": \"Post created successfully\"}";
		return responseMsg;
	}

	@Override
	public List<Posts> findAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Posts findPostById(String postId) {
		Posts post = postRepository.findBy_id(postId);
		if (post == null) {
			throw new RecordNotFoundException();
		}
		return post;
	}

	@Override
	public List<Posts> findPostByUserId(Map<String, String> userId) {
		return postRepository.findByUserId(userId.get("id"));
	}

	@Override
	public String updatePost(String postId, Posts postToUpdate) {
		Posts existingPost = findPostById(postId);
		if (existingPost != null) {
			existingPost.setLikes(postToUpdate.getLikes());
			existingPost.setIsActive(postToUpdate.getIsActive());
			System.out.println(existingPost.toString());
			postRepository.save(existingPost);
		} else {
			throw new RecordNotFoundException();
		}

		String responseMsg = "{ \"status\": 200, \"message\": \"Post updated successfully\"}";
		return responseMsg;
	}

	@Override
	public String updateManyPost(Map<String, String> postDto) {
		Query query = new Query(Criteria.where("userId").is(postDto.get("userId")));
		Update update = new Update();
		update.set("userPhotoId", postDto.get("photoId"));
		mongoTemplate.updateMulti(query, update, Posts.class);
		String responseMsg = "{ \"status\": 200, \"message\": \"Post User Photo updated successfully\"}";
		return responseMsg;
	}

}
