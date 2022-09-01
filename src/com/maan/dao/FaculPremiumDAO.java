package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.FaculPremiumBean;

public interface FaculPremiumDAO {
	
	boolean premiumInsertMethod(FaculPremiumBean beanObj);

	boolean contractDetails(FaculPremiumBean formObj, String country);

	boolean getPremiumDetails(FaculPremiumBean formObj,String transactionNo, String countryId);

	List<FaculPremiumBean> getPremiumedList(FaculPremiumBean beanObj);

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

	String GetPreviousPremium(FaculPremiumBean bean);

	String GetContractPremium(FaculPremiumBean bean);

	List<Map<String, Object>> currencyList(FaculPremiumBean bean);

	List<Map<String, Object>> productIdList(FaculPremiumBean bean);

	void PremiumContractDetails(FaculPremiumBean bean, String countryId);

	List<Map<String, Object>> bonusdetails(FaculPremiumBean bean, String countryId);

	void GetFieldValues(FaculPremiumBean bean);

	String getBonusValue(FaculPremiumBean bean, double parseDouble);

	void addFieldValue(FaculPremiumBean bean);

	void PremBonusEditFieldValue(FaculPremiumBean bean, String string);

	String getDepartmentId(FaculPremiumBean bean);

	List<FaculPremiumBean> getPremiumTempList(FaculPremiumBean bean);

}
