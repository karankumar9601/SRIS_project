package com.SRIS.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.SRIS.Model.AboutUsForm;

public interface AboutUsFormService {

	public AboutUsForm addAboutUs(MultipartFile multipartFile, AboutUsForm aboutUsForm) throws Exception;

	public List<AboutUsForm> readallAboutUs();

	public void deleteAllAboutUs(int id);

}
