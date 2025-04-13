package com.SRIS.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.SRIS.Model.InstructorForm;

public interface InstructorFormService {

	public InstructorForm addInstructorDetails(MultipartFile multipartFile, InstructorForm instructorForm) throws Exception;

	public List<InstructorForm> readAllInstructors();

	public void deleteInstructorsDetails(int id);

}
