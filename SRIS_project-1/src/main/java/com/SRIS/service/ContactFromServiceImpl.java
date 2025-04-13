package com.SRIS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SRIS.DAO.ContactFronCRUD;
import com.SRIS.Model.ContactDto;

@Service
public class ContactFromServiceImpl implements ContactFromService {

	private ContactFronCRUD contactFronCRUD;

	@Autowired
	public void setContactFronCRUD(ContactFronCRUD contactFronCRUD) {
		this.contactFronCRUD = contactFronCRUD;
	}

	@Override
	public ContactDto saveContactFromService(ContactDto contactDto) {

		return contactFronCRUD.save(contactDto);

	}

	@Override
	public List<ContactDto> readContactService() {

		return contactFronCRUD.findAll();
	}

	@Override
	public void deleteContactService(int id) {
		contactFronCRUD.deleteById(id);

	}

}
