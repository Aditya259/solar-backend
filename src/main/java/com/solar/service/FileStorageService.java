package com.solar.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface FileStorageService {
	String storeFile(MultipartFile file,String fileName);
	Resource loadFileAsResource(String fileName) ;
}
