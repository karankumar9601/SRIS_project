package com.SRIS.service;

public interface AdminCredentialService {
	
	public String checkAdminCredential(String oldusername,String oldpassword);
	public String updateAdminCredential(String newusername,String newpassword,String oldusername);
	

}
