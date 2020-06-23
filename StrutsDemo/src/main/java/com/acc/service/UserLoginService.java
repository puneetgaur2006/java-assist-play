package com.acc.service;

import com.acc.dao.DbUtils;
import com.acc.form.UserLoginForm;

public class UserLoginService {

	public boolean checkUserLoginDetails(UserLoginForm form) {
		return DbUtils.getuserDetails(form);
	}
}
