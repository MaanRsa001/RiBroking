package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.JournalBean;

public interface JournalDAO {

	List<Map<String, Object>> getOpenPeriodList(String branchCode);

	List<List<Map<String, Object>>> getJournalViews(JournalBean bean, String branchCode);

	List<Map<String, Object>> getSpcCurrencyList(JournalBean bean, String branchCode);

	int insertActiveOpenPeriod(String startDate, String endDate,
			String branchCode, String status, String journalname, String loginId);

	int insertInActiveOpenPeriod(String startDate, String endDate,
			String branchCode, String status, String journalname, String loginId);

	int getQuaterEndValidation(String sampledate, String branchCode);

	int insertRetroProcess(String startDate, String endDate, String type, String branchCode);

	String getShortname(String branchCode);

	/*boolean getUserDetails(String branchCode, String userId);*/

	boolean getUserDetails(JournalBean bean);

	void ActivateInActivateLoginUsers(JournalBean bean, String status);

	boolean getCountOpenPeriod(JournalBean bean);

	void insertManualJV(JournalBean bean);

	List<JournalBean> getLedgerEntryList(JournalBean bean);

	void getEditLedgerDetails(JournalBean bean);

	List<Map<String, Object>> getjounalList(JournalBean bean);

	void getViewLedgerDetails(JournalBean bean);

	String getForExDiffName(String branchCode);
	
	List<Map<String, Object>> getStartDateList(String branchCode);

	List<Map<String, Object>> getEndDateList(String branchCode);

	String getStartDateStatus(JournalBean bean);

	String getEndDateStatus(JournalBean bean);

}
