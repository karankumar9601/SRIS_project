package com.SRIS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SRIS.DAO.AdminDao;
import com.SRIS.Model.Admin;

@Service
public class AdminCredentialServiceImpl implements AdminCredentialService {

	private AdminDao adminDao;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public String checkAdminCredential(String oldusername, String oldpassword) {

		Optional<Admin> byUsername = adminDao.findByUsername(oldusername); // jo old user name ham denge
		if (byUsername.isPresent()) {

			Admin admin = byUsername.get(); // yha se database ka dono ko get kar lenge
			boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());// dono password ko match karenge
			if (matches) {
				return "SUCCESS";
			} else {
               return "Wrong Old Credentials";
			}

		} else {
			return "Wrong Old Credentials";
		}
	}

	@Override
	public String updateAdminCredential(String newusername, String newpassword, String oldusername) {
		int updateCredential = adminDao.updateCredential(newusername, passwordEncoder.encode(newpassword), oldusername);
		if (updateCredential==1) {
			return "Credentials Updated Successfuly";
		} else {
			return "Failed to Updated";

		}
	}

}
