package com.SRIS.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SRIS.DAO.AboutUsFormCRUD;
import com.SRIS.Model.AboutUsForm;

import jakarta.transaction.Transactional;

@Service
public class AboutUsFormServiceImpl implements AboutUsFormService {
	
	private AboutUsFormCRUD aboutUsFormCRUD;
	
	
    @Autowired
	public void setAboutUsFormCRUD(AboutUsFormCRUD aboutUsFormCRUD) {
		this.aboutUsFormCRUD = aboutUsFormCRUD;
	}
    
    @Transactional(rollbackOn = Exception.class)
	@Override
	public AboutUsForm addAboutUs(MultipartFile multipartFile, AboutUsForm aboutUsForm) throws Exception {
    	AboutUsForm save =null;
		
		try {
			save = aboutUsFormCRUD.save(aboutUsForm);
			//System.out.println(save);
			if (save!=null) {
				String projectRoot = System.getProperty("user.dir");
				if (projectRoot==null) {
					throw new RuntimeException("Project Root directory is null");
				}
			    String uploadDir = projectRoot + File.separator +"src" + File.separator + "main" + 
                        File.separator + "resources" + File.separator + "static" + File.separator+ "aboutUs";
			    
			    String path=uploadDir+File.separator+multipartFile.getOriginalFilename();	
			    FileOutputStream fos = new FileOutputStream(path);
			    byte[] bytes = multipartFile.getBytes();
			    fos.write(bytes);
			    }
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("AboutUs Upload Failed");
		}
		return save;
	}

	@Override
	public List<AboutUsForm> readallAboutUs() {
		// TODO Auto-generated method stub
		return aboutUsFormCRUD.findAll();
	}

	@Override
	public void deleteAllAboutUs(int id) {
		aboutUsFormCRUD.deleteById(id);

	}

}
