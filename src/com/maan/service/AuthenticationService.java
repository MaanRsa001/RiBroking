package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;
import com.maan.dao.AuthendicationDAO;
import com.maan.dao.impl.AuthendicationDAOImp;



public class AuthenticationService {
AuthendicationDAO dao = new AuthendicationDAOImp();

public List<FaculPremiumBean> AuthenticationList(FaculPremiumBean bean) {
	return dao.AuthenticationList(bean);
}

public String AuthenticationChanges(FaculPremiumBean bean) {
	return dao.AuthenticationChanges(bean);
	
}
}
