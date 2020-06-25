package com.acc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.acc.form.EmployeeForm;
import com.acc.service.DashBoardService;

public class UpdateEmployeeAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployeeForm updateEmpForm = (EmployeeForm) form;
		request.getSession().setAttribute("updateRecord", new DashBoardService().updateEmployee(updateEmpForm));
		return mapping.findForward("success");
		 
	}

}
