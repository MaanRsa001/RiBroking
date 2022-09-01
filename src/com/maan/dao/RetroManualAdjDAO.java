package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;

public interface RetroManualAdjDAO {

	List<Map<String, Object>> getRetroManualAdjlist(FaculPremiumBean bean);

	void getRetroDetails(FaculPremiumBean bean);

	void InsertPremium(FaculPremiumBean bean);

	boolean PremiumEdit(FaculPremiumBean bean, String countryId);

	boolean GetPremiumDetails(FaculPremiumBean bean, String transactionNo,String countryId);
			

}
