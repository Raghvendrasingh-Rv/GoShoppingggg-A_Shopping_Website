package com.example.backend.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUpload {
	
	public String UploadImage(String path, MultipartFile file) {
		String fullPath;
		String originalFileName = file.getOriginalFilename();
		String randomImageName = UUID.randomUUID().toString();
		String randomImageNameWithExtention = randomImageName.concat(originalFileName.substring(originalFileName.lastIndexOf(".")));
		fullPath = path+File.separator+randomImageNameWithExtention;
		
		File folderFile = new File(path);
		
		if(!folderFile.exists()) {
			folderFile.mkdirs();
		}
		
		try {
			Files.copy(file.getInputStream(), Paths.get(fullPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return randomImageNameWithExtention;
	}

}
