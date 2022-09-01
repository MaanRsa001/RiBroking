package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;
import com.maan.dao.StatisticsDAO;
import com.maan.dao.impl.StatisticsDAOImpl;

public class StatisticsService {
	StatisticsDAO dao =new  StatisticsDAOImpl();
	public void SlideScenario(FaculPremiumBean bean) {
		dao.SlideScenario(bean);
	}
	public List<Map<String, Object>> getColumnHeaderlist(FaculPremiumBean bean) {
		return dao.getColumnHeaderlist( bean);
	}
	public List<Map<String, Object>> getRenewalStatisticsList(FaculPremiumBean bean, String type) {
		
		return dao.getRenewalStatisticsList( bean,type);
	}
	public List<Map<String, Object>> getRenewalCalStatisticsList(FaculPremiumBean bean, String type) { 

		return dao.getRenewalCalStatisticsList(bean, type);
	}

}
