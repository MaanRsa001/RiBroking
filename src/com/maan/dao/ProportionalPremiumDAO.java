package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;

public interface ProportionalPremiumDAO {
	
	boolean premiumInsertMethod(FaculPremiumBean beanObj, String countryId);

	boolean contractDetails(FaculPremiumBean formObj, String country);

	boolean getPremiumDetails(FaculPremiumBean formObj,String transactionNo, String countryId);

	List<FaculPremiumBean> getPremiumedList(FaculPremiumBean beanObj, String type);

	boolean premiumEdit(FaculPremiumBean formObj, String countryId);

	boolean getPreList(FaculPremiumBean frm);

	boolean premiumUpdateMethod(FaculPremiumBean beanObj);

	List<Map<String,Object>> mdInstallmentDates(String contNo,String layerNo);
	
	List getMandDInstallments(String contNo,String layerNo);
	
	String GetInstalmentAmount(final String contractNo,final String getAmount);
	
	String getRPPremiumOC(final String contractNo,final String layerNo);
	
	List getSPRetroList(String contNo,String productId,String layerNo);
	
	List getRetroContracts(FaculPremiumBean beanObj);
	
	String getSumOfShareSign(FaculPremiumBean beanObj);

	List<Map<String, Object>> getClaimNosDropDown(String contNo);

	boolean getCashLossUpdateValidation(String contractNo,String transactionNo, String claimNo,String cashLOossUpdateOc,String excRate);
	
	public String getMovementReportMaxDate(String branchCode);
	
	List<FaculPremiumBean> getAllocatedList(FaculPremiumBean beanObj);
	
	List getBrokerAndCedingName(FaculPremiumBean beanObj);

	List<Map<String,Object>> getaccountList(FaculPremiumBean bean);

	List<Map<String, Object>> currencyList(FaculPremiumBean bean);

	void bankAddress(FaculPremiumBean bean);

	String GetPreviousPremium(FaculPremiumBean bean);

	String GetContractPremium(FaculPremiumBean bean);

	List<FaculPremiumBean> getCassLossCredit(FaculPremiumBean bean, String claimPayNo);

	void InsertCashLossCredit(FaculPremiumBean bean);

	String getDepartmentNo(FaculPremiumBean bean);


	List<Map<String,Object>> getConstantPeriodDropDown(String categoryId, String contNo,FaculPremiumBean bean);

	List<Map<String,Object>> SlideCommission(FaculPremiumBean bean,String countryId);

	String getCurrencyShortName(FaculPremiumBean bean);

	void GetFieldValues(FaculPremiumBean bean);

	void PremiumContractDetails(FaculPremiumBean bean, String countryId);

	String getSlideValue(FaculPremiumBean bean, double ans);

	void insertSlideCommission(FaculPremiumBean bean);

	//void PremSlideEditFieldValue(FaculPremiumBean bean, String type);

	void addFieldValue(FaculPremiumBean bean);

	List<FaculPremiumBean> getPremiumReserved(FaculPremiumBean bean, String prTransNo, String countryId);

	List<Map<String, Object>> getOSBList(FaculPremiumBean bean);

	int getCountCleanCUT(FaculPremiumBean bean);

	int getCountAccountPeriod(FaculPremiumBean bean);

	void PremiumGetFieldValues(FaculPremiumBean bean);

	String getPremiumValue(FaculPremiumBean bean, String profitCommType,String profitRto, String profitComm);

	void GetLossFieldValues(FaculPremiumBean bean);

	String getLossValue(FaculPremiumBean bean, double parseDouble,
			String premium);

	void insertSection(FaculPremiumBean bean);

	int getSectionCount(FaculPremiumBean bean);

	int getDepositReleaseCount(FaculPremiumBean bean);

	List<Object> getAllocatedCassLossCredit(FaculPremiumBean bean);

	List<Object> getAllocatedTransList(FaculPremiumBean bean);

	double getReverseCassLossCredit(FaculPremiumBean bean);

	void InsertReverseCashLossCredit(FaculPremiumBean bean);

	void cashLossmailTrigger(FaculPremiumBean bean);

	List<Map<String, Object>> getRipremiumList(FaculPremiumBean bean);

	void updateRiStatus(FaculPremiumBean bean);
}
