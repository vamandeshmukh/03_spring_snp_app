package com.snp.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uploadFile(MultipartFile photo) throws IOException;

	byte[] findFileById(String photoId);
}
