package com.SRIS.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SRIS.DAO.UploadProjectFormCRUD;
import com.SRIS.Model.UploadProjectForm;

import jakarta.transaction.Transactional;

@Service
public class UploadProjectFormServiceImpl implements UploadProjectFormService {

	private UploadProjectFormCRUD uploadProjectFormCRUD;

	@Autowired
	public void setUploadProjectFormCRUD(UploadProjectFormCRUD uploadProjectFormCRUD) {
		this.uploadProjectFormCRUD = uploadProjectFormCRUD;
	}
    
	@Transactional(rollbackOn = Exception.class)
	@Override
	public UploadProjectForm addProject(MultipartFile multipartFile, UploadProjectForm uploadProjectForm) throws Exception {
		
		UploadProjectForm save=null;
		try {
			save = uploadProjectFormCRUD.save(uploadProjectForm);
			if (save!=null) {
				String projectRoot = System.getProperty("user.dir");
				if (projectRoot==null) {
					throw new RuntimeException("Project Root Directory is null");
				}
				String uploadDir = projectRoot + File.separator + "src" + File.separator + "main" + File.separator
						+ "resources" + File.separator + "static" + File.separator + "slider";
				String path=uploadDir+File.separator+multipartFile.getOriginalFilename();
				
				System.out.println("Saving File at: "+path);
				FileOutputStream fos = new FileOutputStream(path);
				byte[] bytes = multipartFile.getBytes();
				fos.write(bytes);
			}
			
		} catch (Exception e) {
			throw new Exception("Project Uploaded Failed",e);
		}

		return save;
	}

	@Override
	public List<UploadProjectForm> readAllProject() {

		return uploadProjectFormCRUD.findAll();
	}

	@Override
	public void deleteProject(int id) {
		uploadProjectFormCRUD.deleteById(id);

	}

}
