package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;

public interface AuthendicationDAO {

	List<FaculPremiumBean> AuthenticationList(FaculPremiumBean bean);

	String AuthenticationChanges(FaculPremiumBean bean);

}
