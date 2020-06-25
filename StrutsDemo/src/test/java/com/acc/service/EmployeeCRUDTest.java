package com.acc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.acc.form.EmployeeForm;
import com.acc.form.UserLoginForm;

public class EmployeeCRUDTest {
	
	DashBoardService service;
	
	@Test
	public void checkAddUserWithCorrectData() {
		service=new DashBoardService();
		EmployeeForm form=new EmployeeForm();
		form.setAddress("H.No 123");
		form.setDept("HR");
		form.setEmployeeId(3);
		form.setEmail("abc");
		form.setName("Atul");
		form.setPhone("9899");
		assertEquals(Integer.valueOf(service.saveEmployee(form)),Integer.valueOf("16"));
	}
	
 

}
