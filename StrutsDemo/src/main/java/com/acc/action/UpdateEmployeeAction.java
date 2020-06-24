package com.acc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.acc.dao.DbUtils;
import com.acc.form.EmployeeForm;

public class UpdateEmployeeAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployeeForm updateEmpForm = (EmployeeForm) form;
		int employeeId=updateEmpForm.getEmployeeId();
		EmployeeForm employee =DbUtils.getEmployee(employeeId) ;
		System.out.println(employee.getName());
		request.setAttribute("employee", employee);
		return mapping.findForward("success");
		 
	}

}
