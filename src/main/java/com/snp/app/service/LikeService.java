package com.snp.app.service;

import java.util.List;

import com.snp.app.dtos.Likes;

public interface LikeService {

	String createNewLike(Likes likeDto);

	List<Likes> findAllLikes();

	Likes findLikeById(String likeId);

}
