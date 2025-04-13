package com.SRIS.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SRIS.Model.LogoForm;

@Repository
public interface LogoFormCRUD extends JpaRepository<LogoForm, Integer> {

}
