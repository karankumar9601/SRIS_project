package com.SRIS.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SRIS.DAO.InstructorFormCRUD;
import com.SRIS.Model.InstructorForm;

import jakarta.transaction.Transactional;

@Service
public class InstructorFormServiceImpl implements InstructorFormService {
	
	private InstructorFormCRUD instructorFormCRUD;
	
	
    @Autowired
	public void setInstructorFormCRUD(InstructorFormCRUD instructorFormCRUD) {
		this.instructorFormCRUD = instructorFormCRUD;
	}

    @Transactional(rollbackOn = Exception.class)
	@Override
	public InstructorForm addInstructorDetails(MultipartFile multipartFile, InstructorForm instructorForm)
			throws Exception {
    	InstructorForm save=null;
    	try {
			save = instructorFormCRUD.save(instructorForm);
			if (save!=null) {
				String projectRoot = System.getProperty("user.dir");
				if (projectRoot==null) {
					throw new RuntimeException("Project Root directory is null");
				}
				 String uploadDir = projectRoot + File.separator +"src" + File.separator + "main" + 
	                        File.separator + "resources" + File.separator + "static" + File.separator+ "instructors";
				 String path=uploadDir+File.separator+multipartFile.getOriginalFilename();
				 FileOutputStream fos = new FileOutputStream(path);
				 byte[] bytes = multipartFile.getBytes();
				 fos.write(bytes);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Instructor Images Failed");
		}
		
		return save;
	}

	@Override
	public List<InstructorForm> readAllInstructors() {
		
		return instructorFormCRUD.findAll();
	}

	@Override
	public void deleteInstructorsDetails(int id) {
		instructorFormCRUD.deleteById(id);

	}

}
