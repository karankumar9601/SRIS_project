package com.SRIS.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SRIS.Model.AboutUsForm;

@Repository
public interface AboutUsFormCRUD extends JpaRepository<AboutUsForm, Integer> {

}
