package com.evantagesoft.service.file;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.evantagesoft.vo.response.Response;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javassist.bytecode.stackmap.BasicBlock.Catch;


@Service
public class FileService  {
	
	private static final Logger logger = LoggerFactory.getLogger(FileService.class);
	
    private Path fileStorageLocation;    
   
    public FileService() {
        this.fileStorageLocation = Paths.get("/home/ec2-user/DATA/")
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
	

    public Object storeFile(MultipartFile file) {
    	
    	try {
    	    Files.createDirectories(this.fileStorageLocation);
    	    }
    	    catch(Exception ex){
    	    	throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
    	    }
    	
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String newFileName=""+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+""+fileName;
        
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            Map<String, String> map = new HashMap<>();
            map.put("orignalName",fileName);
            map.put("createdName", newFileName);
            
            return map;
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

}
