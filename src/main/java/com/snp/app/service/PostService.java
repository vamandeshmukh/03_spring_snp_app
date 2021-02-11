package com.snp.app.service;

import java.util.List;
import java.util.Map;

import com.snp.app.dtos.Posts;

public interface PostService {

	String createNewPost(Posts postToCreate);

	List<Posts> findAllPosts();

	Posts findPostById(String postId);

	List<Posts> findPostByUserId(Map<String,String> userId);

	String updatePost(String postId, Posts postToUpdate);

	String updateManyPost(Map<String, String> postDto);

}
