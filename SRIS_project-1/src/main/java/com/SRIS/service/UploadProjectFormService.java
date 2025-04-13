package com.SRIS.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.SRIS.Model.UploadProjectForm;

public interface UploadProjectFormService {

	public UploadProjectForm addProject(MultipartFile multipartFile, UploadProjectForm uploadProjectForm) throws Exception;

	public List<UploadProjectForm> readAllProject();

	public void deleteProject(int id);

}
