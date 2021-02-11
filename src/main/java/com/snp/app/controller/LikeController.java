package com.snp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snp.app.dtos.Likes;
import com.snp.app.service.LikeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/likes")
public class LikeController {

	@Autowired
	private LikeService likeService;

	@PostMapping("/createlike")
	public String createNewLike(@RequestBody Likes likeDto) {
		return likeService.createNewLike(likeDto);
	}

	@GetMapping
	public List<Likes> listLikes() {
		return likeService.findAllLikes();
	}

	@GetMapping("/{id}")
	public Likes findById(@PathVariable String id) {
		return likeService.findLikeById(id);
	}
}
