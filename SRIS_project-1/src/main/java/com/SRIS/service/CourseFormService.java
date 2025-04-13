package com.SRIS.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.SRIS.Model.CourseForm;

import jakarta.servlet.http.HttpServletRequest;

public interface CourseFormService {
	
	public CourseForm addCourse(CourseForm courseForm,MultipartFile multipartFile) throws Exception;
	
	public List<CourseForm>readAllCourse();
	
	public void deleteCourse(int id);

}
