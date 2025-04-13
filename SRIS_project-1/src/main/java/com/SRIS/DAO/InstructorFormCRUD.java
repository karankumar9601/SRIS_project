package com.SRIS.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SRIS.Model.InstructorForm;

@Repository
public interface InstructorFormCRUD extends JpaRepository<InstructorForm, Integer> {

}
