package com.SRIS.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SRIS.Model.UploadProjectForm;

@Repository
public interface UploadProjectFormCRUD extends JpaRepository<UploadProjectForm, Integer> {
	
	

}
