package com.SRIS.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SRIS.Model.TestimonialForm;

@Repository
public interface TestimonialFormCRUD  extends JpaRepository<TestimonialForm, Integer>{
	
	@Override
	public <S extends TestimonialForm> S save(S entity); 

}
