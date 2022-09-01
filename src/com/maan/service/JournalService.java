package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.HomeBean;
import com.maan.bean.JournalBean;
import com.maan.dao.JournalDAO;
import com.maan.dao.impl.JournalDAOImpl;

public class JournalService {
JournalDAO dao=new JournalDAOImpl();
public List<Map<String, Object>> getOpenPeriodList(String branchCode) {
	return dao.getOpenPeriodList(branchCode);
}
public List<List<Map<String, Object>>> getJournalViews(JournalBean bean, String branchCode) {
	return dao.getJournalViews(bean,branchCode);
	
}
public List<Map<String, Object>> getSpcCurrencyList(JournalBean bean, String branchCode) {
	return dao.getSpcCurrencyList(bean,branchCode);
}
public int insertActiveOpenPeriod(String startDate, String endDate,String branchCode, String status, String journalname, String loginId) {
	return dao.insertActiveOpenPeriod( startDate,  endDate, branchCode,  status,  journalname,loginId);
	
}
public int insertInActiveOpenPeriod(String startDate, String endDate,String branchCode, String status, String journalname, String loginId) {
	return dao.insertInActiveOpenPeriod( startDate,  endDate, branchCode,  status,  journalname,loginId);
	
}
public int getQuaterEndValidation(String sampledate, String branchCode) {
	return dao.getQuaterEndValidation(sampledate,branchCode);
}
public int insertRetroProcess(String startDate, String endDate, String type, String branchCode) {
	return dao.insertRetroProcess(startDate, endDate,type,branchCode);
}
public String getShortname(String branchCode) {
	return dao.getShortname(branchCode);
}
/*public boolean getUserDetails(String branchCode, String userId) {
	return dao.getUserDetails(branchCode,userId);
}*/
public boolean getUserDetails(JournalBean bean) {
	return dao.getUserDetails(bean);
}
public void ActivateInActivateLoginUsers(JournalBean bean, String status) {
	dao.ActivateInActivateLoginUsers(bean,status);
	
}
public boolean getCountOpenPeriod(JournalBean bean) {
	return dao.getCountOpenPeriod( bean);
}
public void insertManualJV(JournalBean bean) {
	dao.insertManualJV(bean);	
}
public List<JournalBean> getLedgerEntryList(JournalBean bean) {
	return dao.getLedgerEntryList(bean);
}
public void getEditLedgerDetails(JournalBean bean) {
	dao.getEditLedgerDetails( bean);
	
}

public List<Map<String, Object>> getjounalList(JournalBean bean) {
	return dao.getjounalList(bean);
}
public void getViewLedgerDetails(JournalBean bean) {
	dao.getViewLedgerDetails(bean);
}
public String getForExDiffName(String branchCode) {
	return dao.getForExDiffName(branchCode);
}
public List<Map<String, Object>> getStartDateList(String branchCode) {
	return dao.getStartDateList(branchCode);
}
public List<Map<String, Object>> getEndDateList(String branchCode) {
	return dao.getEndDateList( branchCode);
}
public String getStartDateStatus(JournalBean bean) {
	return dao.getStartDateStatus(bean);
}
public String getEndDateStatus(JournalBean bean) {
	return dao.getEndDateStatus(bean);
}
}
