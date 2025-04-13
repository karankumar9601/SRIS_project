package com.SRIS.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SRIS.Model.ImageForm;

@Repository
public interface ImageFormCRUD extends JpaRepository<ImageForm, Integer> {

	@Override
	public <S extends ImageForm> S save(S entity);

	@Override
	public List<ImageForm> findAll();

	@Override
	public void deleteById(Integer id);
}
