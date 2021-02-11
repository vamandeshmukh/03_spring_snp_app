package com.snp.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snp.app.dtos.Friends;
import com.snp.app.service.FriendService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/friends")
public class FriendController {

	@Autowired
	private FriendService friendService;

	@PostMapping("/createrequest")
	public String createNewRequest(@RequestBody Friends friendDto) {
		return friendService.createNewRequest(friendDto);
	}

	@GetMapping
	public ResponseEntity<List<Map<String, String>>> listFriends() {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(friendService.findAllFriends());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, String>> findById(@PathVariable String id) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(friendService.findFriendById(id));
	}

	@PostMapping("/findfriendbyuserid")
	public ResponseEntity<Map<String, String>> findFriendByUserId(@RequestBody Friends friendDto) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(friendService.findFriendByUserId(friendDto.getUserId()));
	}

	@PostMapping("/findfriendbyfriendid")
	public ResponseEntity<Map<String, String>> findFriendByFriendId(@RequestBody Friends friendDto) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(friendService.findFriendByFriendId(friendDto.getFriendId()));
	}
	
	@PutMapping("/{id}")
	public String updateFriend(@PathVariable String id, @RequestBody Friends friendDto) {
		return friendService.updateFriend(id, friendDto);
	}
}
