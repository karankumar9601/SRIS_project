package com.SRIS.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SRIS.DAO.CourseFormCRUD;
import com.SRIS.Model.CourseForm;

import jakarta.transaction.Transactional;

@Service
public class CourseFormServiceImpl implements CourseFormService {
	private CourseFormCRUD courseFormCRUD;
	

   @Autowired
	public void setCourseFormCRUD(CourseFormCRUD courseFormCRUD) {
		this.courseFormCRUD = courseFormCRUD;
	}


   @Transactional(rollbackOn = Exception.class)
	@Override
	public CourseForm addCourse(CourseForm courseForm, MultipartFile multipartFile) throws Exception {
		//Dao layer
		CourseForm savedCourse=null;
		try {
			  // Save course data to the database
            savedCourse = courseFormCRUD.save(courseForm);

            if (savedCourse != null) {
                // ✅ Get project root directory
                String projectRoot = System.getProperty("user.dir"); 
                if (projectRoot == null) {
                    throw new RuntimeException("Project root directory is null!");
                }

                // ✅ Set upload directory
                String uploadDir = projectRoot + File.separator +"src" + File.separator + "main" + 
                        File.separator + "resources" + File.separator + "static" + File.separator+ "MyCourse";

                // ✅ Ensure directory exists
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    boolean created = directory.mkdirs();
                    if (!created) {
                        throw new Exception("Failed to create upload directory!");
                    }
                }

                // ✅ Define file path
                String filePath = uploadDir + File.separator + multipartFile.getOriginalFilename();
                System.out.println("Saving file at: " + filePath);

                // ✅ Save file
                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    fos.write(multipartFile.getBytes());
                }

//                // ✅ Save image name in the database
//                savedCourse.setImage(multipartFile.getOriginalFilename());
//                courseFormCRUD.save(savedCourse);
            }
        } catch (Exception e) {
            throw new Exception("File upload failed", e);
        }

        return savedCourse;
	}


	@Override
	public List<CourseForm> readAllCourse() {
		    
		return courseFormCRUD.findAll();
	}


	@Override
	public void deleteCourse(int id) {
		courseFormCRUD.deleteById(id);
		
	}

}
