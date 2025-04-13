package com.SRIS.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SRIS.Model.ContactDto;

@Repository
public interface ContactFronCRUD extends JpaRepository<ContactDto, Integer> {

	@Override
	public <S extends ContactDto> S save(S entity);

	@Override
	public List<ContactDto> findAll();
	
	@Override
	public void deleteById(Integer id) ;
}
