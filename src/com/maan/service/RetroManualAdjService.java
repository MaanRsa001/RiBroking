package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;
import com.maan.dao.RetroManualAdjDAO;
import com.maan.dao.impl.RetroManualAdjDAOImpl;

public class RetroManualAdjService {
	private static final RetroManualAdjDAO dao =  new RetroManualAdjDAOImpl();

public List<Map<String, Object>> getRetroManualAdjlist(FaculPremiumBean bean) {
	return dao.getRetroManualAdjlist(bean);
}

public void getRetroDetails(FaculPremiumBean bean) {
	dao.getRetroDetails(bean);
	
}

public void InsertPremium(FaculPremiumBean bean) {
	dao.InsertPremium(bean);
	
}

public boolean PremiumEdit(FaculPremiumBean bean, String countryId) {
	return dao.PremiumEdit(bean,countryId);
	
}

public boolean GetPremiumDetails(FaculPremiumBean bean, String transactionNo,String countryId) {
	return dao.GetPremiumDetails(bean,transactionNo,countryId);
}

}
         