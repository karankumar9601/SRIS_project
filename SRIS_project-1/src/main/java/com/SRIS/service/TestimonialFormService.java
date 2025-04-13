package com.SRIS.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.SRIS.Model.TestimonialForm;

public interface TestimonialFormService {

	public TestimonialForm addTestimonial(TestimonialForm testimonialForm, MultipartFile multipartFile)
			throws Exception;

	public List<TestimonialForm> readAllTestimonial();
	
	public void deleteTestimonial(int id);

}
