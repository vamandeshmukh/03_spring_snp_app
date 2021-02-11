package com.snp.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snp.app.dtos.Posts;
import com.snp.app.service.PostService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/createpost")
	public String createPost(@RequestBody Posts postDto) {
		return postService.createNewPost(postDto);
	}

	@GetMapping
	public List<Posts> listPosts() {
		return postService.findAllPosts();
	}

	@GetMapping("/{id}")
	public Posts findById(@PathVariable String id) {
		return postService.findPostById(id);
	}

	@PostMapping("/findpostbyuserid")
	public List<Posts> findByUserId(@RequestBody Map<String, String> postDto) {
		return postService.findPostByUserId(postDto);
	}

	@PostMapping("/updatemanyposts")
	public String updateBulkPosts(@RequestBody Map<String, String> postDto) {
		return postService.updateManyPost(postDto);
	}

	@PutMapping("/{id}")
	public String updatePost(@PathVariable String id, @RequestBody Map<String, Object> postDto) {
		id = postDto.get("_id").toString();
		Posts postToUpdate = new Posts();
		postToUpdate.setLikes((int) postDto.get("likes"));
		postToUpdate.setIsActive((boolean) postDto.get("isActive"));
		return postService.updatePost(id, postToUpdate);
	}
}
