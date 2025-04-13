package com.SRIS.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.SRIS.Model.LogoForm;

public interface LogoFormService {

	public LogoForm addLogo(MultipartFile multipartFile, LogoForm logoForm) throws Exception;

	public List<LogoForm> readLogo();

	public void deleteLogo(int id);

}
