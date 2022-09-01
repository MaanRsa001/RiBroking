package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;
import com.maan.bean.PremiumBean;

public interface PremiumDAO {

	List<PremiumBean> PremiumList(String branchCode, PremiumBean bean, String tableType);

	String getOpenPeriod(PremiumBean bean);

	List<Map<String, Object>> productIdList(PremiumBean bean);

	List<PremiumBean> contractidetifierlist(PremiumBean bean);

	void copyDatatoDeleteTable(PremiumBean bean);

}
