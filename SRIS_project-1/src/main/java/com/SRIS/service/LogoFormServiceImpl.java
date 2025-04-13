package com.SRIS.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SRIS.DAO.LogoFormCRUD;
import com.SRIS.Model.LogoForm;

@Service
public class LogoFormServiceImpl implements LogoFormService {
	
	private LogoFormCRUD logoFormCRUD;
	
	
    @Autowired
	public void setLogoFormCRUD(LogoFormCRUD logoFormCRUD) {
		this.logoFormCRUD = logoFormCRUD;
	}

	@Override
	public LogoForm addLogo(MultipartFile multipartFile, LogoForm logoForm) throws Exception {
		
		LogoForm save=null;
		
		try {
			save = logoFormCRUD.save(logoForm);
			if (save!=null) {
				String projectRoot = System.getProperty("user.dir");
				if (projectRoot==null) {
					throw new RuntimeException("Project Root directory is null");
				}
				
				 String uploadDir = projectRoot + File.separator +"src" + File.separator + "main" + 
	                        File.separator + "resources" + File.separator + "static" + File.separator+ "Logo";
				 String path=uploadDir+File.separator+multipartFile.getOriginalFilename();
				 System.out.println("Saving at File:"+path);
				 
				 FileOutputStream fos = new FileOutputStream(path);
				 byte[] bytes = multipartFile.getBytes();
				 fos.write(bytes);
				 
			}
		} catch (Exception e) {
			throw new Exception("Logo uploaded Failed",e);
		}
		
		return save;
	}

	@Override
	public List<LogoForm> readLogo() {
	
		return logoFormCRUD.findAll();
	}

	@Override
	public void deleteLogo(int id) {
		logoFormCRUD.deleteById(id);
	}

}
