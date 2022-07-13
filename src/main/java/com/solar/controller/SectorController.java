package com.solar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.solar.model.TestImage;
import com.solar.repository.SectorRepository;
import com.solar.repository.TestImageRepository;
import com.solar.serviceImpl.FileStorageServiceImpl;
import com.solar.utils.PropertyValues;
import com.solar.voObject.ResponseVo;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SectorController{

	@Autowired
	TestImageRepository testImageRepository; 
	
	@Autowired
	PropertyValues propertyValues;
	
	@Autowired
	SectorRepository sectorRepository;
	
	@Autowired
	FileStorageServiceImpl fileStorageService;
	
	@GetMapping("/sectors")
	public ResponseVo sectors() {
		ResponseVo response = new ResponseVo();
		try {
			response.setData(sectorRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
			response.setStatus(true);
			return response;
		}catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}
	
	@PostMapping("/addImg")
	public ResponseVo  addImg(
			@RequestParam("img") MultipartFile img,
			@RequestParam("name") String name) throws JsonMappingException, JsonProcessingException {
		ResponseVo response = new ResponseVo();
		try {
			String fileName = img==null?"": fileStorageService.storeFile(img,propertyValues.getUploadAttachment());
				TestImage testImage=new TestImage();
				testImage.setName(name);
				testImage.setImg(fileName);
				testImageRepository.save(testImage);
				response.setStatus(true);
				response.setMessage(propertyValues.getSuccessMessage());
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(false);
				response.setError(e.getMessage());
				return response;
			}
	}
}