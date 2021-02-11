package com.snp.app.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.snp.app.service.FileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/uploadfile")
	public ResponseEntity<HashMap<String, String>> uploadPhoto(@RequestParam("picture") MultipartFile picture)
			throws IOException {
		String responseMsg = fileService.uploadFile(picture);
		HashMap<String, String> map = new HashMap<>();
		map.put("uploadId", responseMsg);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(map);
	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getPhoto(@PathVariable String id) {
		byte[] imageFile = fileService.findFileById(id);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageFile);
	}
}
