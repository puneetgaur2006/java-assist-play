package com.acc.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.acc.dao.DbUtils;
import com.acc.form.EmployeeForm;
import com.acc.service.DashBoardService;

public class DashBoardAction extends Action {

@Override
public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	EmployeeForm hwForm = (EmployeeForm) form;
	DashBoardService dashBoardService=new DashBoardService();
	
	request.setAttribute("list",dashBoardService.getAllEmployees());
	
	request.setAttribute("deptlist",dashBoardService.getAllDept());

    return mapping.findForward("success");

    }
}