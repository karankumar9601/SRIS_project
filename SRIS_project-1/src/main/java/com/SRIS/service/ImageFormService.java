package com.SRIS.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.SRIS.Model.ImageForm;

public interface ImageFormService {
	
	public ImageForm addImage(ImageForm imageForm,MultipartFile multipartFile) throws Exception;
	
	public List<ImageForm> readAllImage();
	
	public void deleteImage(int id);

}
