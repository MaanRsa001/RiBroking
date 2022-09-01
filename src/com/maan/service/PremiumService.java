package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;
import com.maan.bean.PremiumBean;
import com.maan.dao.ClaimDAO;
import com.maan.dao.PremiumDAO;
import com.maan.dao.impl.ClaimDAOImpl;
import com.maan.dao.impl.PremiumDaoImpl;



public class PremiumService {
	
	private static final PremiumDAO dao=new PremiumDaoImpl();
	public List<PremiumBean> PremiumList(String branchCode,PremiumBean bean, String tableType) {
		return dao.PremiumList(branchCode,bean,tableType);
	}
	public String getOpenPeriod(PremiumBean bean) {
		return dao.getOpenPeriod(bean);		
	}
	public List<Map<String, Object>> productIdList(PremiumBean bean) {
		return dao.productIdList(bean);
	}
	public List<PremiumBean> contractidetifierlist(PremiumBean bean) {
		return dao.contractidetifierlist(bean);
	}
	public void copyDatatoDeleteTable(PremiumBean bean) {
		dao.copyDatatoDeleteTable(bean);
	}
	
}
