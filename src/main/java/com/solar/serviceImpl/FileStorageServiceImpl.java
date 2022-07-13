package com.solar.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.solar.exception.FileStorageException;
import com.solar.exception.MyFileNotFoundException;
import com.solar.property.FileStorageProperties;
import com.solar.service.FileStorageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService{

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file,String fileName) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(originalFileName.contains("..")) {
        	  throw new FileStorageException("Sorry! Filename contains invalid path sequence " + originalFileName);
         }
        if(originalFileName.isEmpty() || originalFileName.equals("")) {
      	  return "";
       }
        	 
        String fileExtension = "";
        try {
        	fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        } catch(Exception e) {
        	e.printStackTrace();
        	fileExtension = "";
        }
        
         fileName=originalFileName;

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            return fileName.split("\\.").length==1?"":fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
    
    public static String ImagePath(String fileName) {
    	String fileDownloadUri="";
    	if(fileName==null || fileName.equals("")) {
    		return fileDownloadUri;
    	}
    	fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
    	return fileDownloadUri;
    }
   
}