package com.SRIS.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SRIS.Model.CourseForm;

@Repository
public interface CourseFormCRUD extends JpaRepository<CourseForm, Integer>{
	@Override
	public <S extends CourseForm> S save(S entity); 
	@Override
	public List<CourseForm> findAll();
	
	@Override
	public void deleteById(Integer id);
}
