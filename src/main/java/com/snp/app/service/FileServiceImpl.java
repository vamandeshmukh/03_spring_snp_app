package com.snp.app.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.snp.app.dtos.Files;
import com.snp.app.exception.RecordNotFoundException;
import com.snp.app.repository.FilesRepository;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FilesRepository filesRepository;

	@Override
	public String uploadFile(MultipartFile photo) throws IOException {
		Files file = new Files();
		file.setFileName("Sample.PNG");
		file.setContentType("image/jpg");
		file.setImage(new Binary(BsonBinarySubType.BINARY, photo.getBytes()));
		file = filesRepository.insert(file);
		return file.get_id().toString();
	}

	@Override
	public byte[] findFileById(String photoId) {
		Files file = filesRepository.findBy_id(photoId);
		if (file == null) {
			throw new RecordNotFoundException();
		}
		byte[] byteArray = file.getImage().getData();
		return byteArray;
	}
}
