package com.acc.service;

import java.util.List;

import com.acc.dao.DbUtils;
import com.acc.form.EmployeeForm;

public class DashBoardService {

	public boolean deleteEmployee(String empId) {
		return DbUtils.deleteEmployeeDetails(empId);
	}
	public boolean updateEmployee(EmployeeForm updatedEmpForm) {
		return DbUtils.updateEmployee(updatedEmpForm);
	}
	
	public List<EmployeeForm> getAllEmployees() {
		return DbUtils.getALLEmployee();
	}
	
	public List<String> getAllDept() {
		return DbUtils.getAllDept();
	}
	
	
	public int saveEmployee(EmployeeForm addEmpForm) {
		return DbUtils.saveEmployee(addEmpForm);
	}
}
