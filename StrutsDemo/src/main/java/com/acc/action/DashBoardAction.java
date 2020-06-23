package com.acc.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.acc.dao.DbUtils;
import com.acc.form.HelloWorldForm;


public class DashBoardAction extends Action {

@Override
public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    HelloWorldForm hwForm = (HelloWorldForm) form;
	request.setAttribute("list",DbUtils.getALLEmployee());
	hwForm.setMessage("Employee List is");
    return mapping.findForward("success");
    }
}