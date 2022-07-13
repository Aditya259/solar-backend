package com.solar.payload;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ImagePath {

	 public static String imagePath(String fileName) {
	    	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
	    	return fileDownloadUri;
	    }
}
