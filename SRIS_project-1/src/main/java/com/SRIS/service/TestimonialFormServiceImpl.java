package com.SRIS.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SRIS.DAO.TestimonialFormCRUD;
import com.SRIS.Model.TestimonialForm;

import jakarta.transaction.Transactional;


@Service
public class TestimonialFormServiceImpl implements TestimonialFormService {
	
	private TestimonialFormCRUD testimonialFormCRUD;
	
	
    @Autowired
	public void setTestimonialFormCRUD(TestimonialFormCRUD testimonialFormCRUD) {
		this.testimonialFormCRUD = testimonialFormCRUD;
	}


      @Transactional(rollbackOn = Exception.class)
	@Override
	public TestimonialForm addTestimonial(TestimonialForm testimonialForm, MultipartFile multipartFile)
			throws Exception {
		TestimonialForm save=null;
		
		try {
			save = testimonialFormCRUD.save(testimonialForm);
			
			if (save!=null) {
				String projectRoot = System.getProperty("user.dir");
				if (projectRoot==null) {
					throw new RuntimeException("Project Root Directory is null");
				}
				
				  String uploadDir = projectRoot + File.separator +"src" + File.separator + "main" + 
	                        File.separator + "resources" + File.separator + "static" + File.separator+ "TestimonialImg";
				  
				  String path=uploadDir+File.separator + multipartFile.getOriginalFilename();
				  System.out.println("Saving File at:" +path);
				  byte[] bytes = multipartFile.getBytes();
				  FileOutputStream fos = new FileOutputStream(path);
				  fos.write(bytes);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("File upload failed",e);
		}
		
		
		return save;
	}


	@Override
	public List<TestimonialForm> readAllTestimonial() {
		// TODO Auto-generated method stub
		return testimonialFormCRUD.findAll();
	}


	@Override
	public void deleteTestimonial(int id) {
		testimonialFormCRUD.deleteById(id);
		
	}

}
