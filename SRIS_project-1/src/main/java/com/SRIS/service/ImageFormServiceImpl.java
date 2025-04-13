package com.SRIS.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SRIS.DAO.ImageFormCRUD;
import com.SRIS.Model.ImageForm;

import jakarta.transaction.Transactional;

@Service
public class ImageFormServiceImpl implements ImageFormService {

	private ImageFormCRUD imageFormCRUD;

	@Autowired
	public void setImageFormCRUD(ImageFormCRUD imageFormCRUD) {
		this.imageFormCRUD = imageFormCRUD;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public ImageForm addImage(ImageForm imageForm, MultipartFile multipartFile) throws Exception {

		ImageForm save = null;
		try {
			save = imageFormCRUD.save(imageForm);
			if (save != null) {

				String projectRoot = System.getProperty("user.dir");
				if (projectRoot == null) {
					throw new RuntimeException("Project root directory is null");
				}
				String uploadDir = projectRoot + File.separator + "src" + File.separator + "main" + File.separator
						+ "resources" + File.separator + "static" + File.separator + "UploadImage";
				String path=uploadDir+File.separator+multipartFile.getOriginalFilename();
				
				System.out.println("Saving File at: "+path);
				FileOutputStream fos = new FileOutputStream(path);
				byte[] bytes = multipartFile.getBytes();
				fos.write(bytes);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("File uploaded Failed",e);
		}

		return save;
	}

	@Override
	public List<ImageForm> readAllImage() {

		return imageFormCRUD.findAll();
	}

	@Override
	public void deleteImage(int id) {
       imageFormCRUD.deleteById(id);
	}

}
