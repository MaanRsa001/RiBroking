package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;

public interface StatisticsDAO {

	void SlideScenario(FaculPremiumBean bean);

	List<Map<String, Object>> getColumnHeaderlist(FaculPremiumBean bean);

	List<Map<String, Object>> getRenewalStatisticsList(FaculPremiumBean bean, String type);

	List<Map<String, Object>> getRenewalCalStatisticsList( FaculPremiumBean bean, String type);

}
