package com.snp.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snp.app.dtos.Likes;
import com.snp.app.exception.RecordNotFoundException;
import com.snp.app.repository.LikesRepository;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikesRepository likeRepository;

	@Override
	public String createNewLike(Likes likeDto) {
		likeRepository.save(likeDto);
		String responseMsg = "{ \"status\": 200, \"message\": \"Like entry inserted successfully\"}";
		return responseMsg;
	}

	@Override
	public List<Likes> findAllLikes() {
		return likeRepository.findAll();
	}

	@Override
	public Likes findLikeById(String likeId) {
		Likes like = likeRepository.findBy_id(likeId);
		if (like == null) {
			throw new RecordNotFoundException();
		}
		return like;
	}

}
