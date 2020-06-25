package com.acc.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.acc.form.UserLoginForm;

public class UserLoginServiceTest {

	UserLoginService service;
	
	@Test
	public void checkUserLoginDetailsSuccess() {
		service=new UserLoginService();
		UserLoginForm form=new UserLoginForm();
		form.setPassword("deepak123");
		form.setUserName("deepak");
		assertTrue(service.checkUserLoginDetails(form));
	}
	
	@Test
	public void checkUserLoginDetailsInvalidUserName() {
		service=new UserLoginService();
		UserLoginForm form=new UserLoginForm();
		form.setPassword("deepak123");
		form.setUserName("dev");
		assertFalse(service.checkUserLoginDetails(form));
	}
	
	@Test
	public void checkUserLoginDetailsInvalidPassword() {
		service=new UserLoginService();
		UserLoginForm form=new UserLoginForm();
		form.setPassword("password");
		form.setUserName("deepak");
		assertFalse(service.checkUserLoginDetails(form));
	}
}
