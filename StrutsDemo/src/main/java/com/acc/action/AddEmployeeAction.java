package com.acc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.acc.dao.DbUtils;
import com.acc.form.EmployeeForm;
import com.acc.service.DashBoardService;

public class AddEmployeeAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployeeForm addEmpForm = (EmployeeForm) form;
		DashBoardService dashBoardService =new DashBoardService();
		
		ActionRedirect redirect=new ActionRedirect(mapping.findForward("success"));
		
		int savedIdOfEmployee=dashBoardService.saveEmployee(addEmpForm) ;
		System.out.println("savedIdOfEmployee "+savedIdOfEmployee);
		
			
		request.setAttribute("list",dashBoardService.getAllEmployees());
			
		addEmpForm.setEmployeeId(savedIdOfEmployee);
		
		request.setAttribute("savedId", savedIdOfEmployee);
		
		return redirect;
		
		
		 
	}

}
