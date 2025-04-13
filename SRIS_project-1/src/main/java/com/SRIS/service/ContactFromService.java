package com.SRIS.service;

import java.util.List;

import com.SRIS.Model.ContactDto;

public interface ContactFromService {

	public ContactDto saveContactFromService(ContactDto contactDto);

	public List<ContactDto> readContactService();
	
	public void deleteContactService(int id);

}
