package com.acc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.acc.service.DashBoardService;

public class DeleteEmployeeAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String empId=(String)request.getParameter("empId");
	    Object o = new DashBoardService().deleteEmployee(empId);
	   request.getSession().setAttribute("deleteRecord", o);
	    return mapping.findForward("success");
	    }
}
