package com.acc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.acc.dao.DbUtils;
import com.acc.form.EmployeeForm;
import com.acc.form.HelloWorldForm;

public class AddEmployeeAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployeeForm addEmpForm = (EmployeeForm) form;

		int savedIdOfEmployee=DbUtils.saveEmployee(addEmpForm) ;
		System.out.println("savedIdOfEmployee "+savedIdOfEmployee);
		if(savedIdOfEmployee==-1) {
			
			addEmpForm.setEmployeeId(savedIdOfEmployee);
			request.setAttribute("savedId", savedIdOfEmployee);
			return mapping.findForward("success");
		
		}
		else return mapping.findForward("failure"); 
	}

}
