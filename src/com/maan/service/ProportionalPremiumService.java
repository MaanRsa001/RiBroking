package com.maan.service;

import java.util.List;
import java.util.Map;
import com.maan.bean.FaculPremiumBean;
import com.maan.dao.ProportionalPremiumDAO;
import com.maan.dao.impl.ProportionalPremiumDaoImpl;

public class ProportionalPremiumService {
	
private static final ProportionalPremiumDAO DAO=new ProportionalPremiumDaoImpl();
	
	public boolean InsertPremium(final FaculPremiumBean beanObj, String countryId)
	{
	  return DAO.premiumInsertMethod(beanObj,countryId);
	}
	public boolean ContractDetails(final FaculPremiumBean formObj,final String country){
		 return DAO.contractDetails(formObj,country);	}

	public static boolean GetPremiumDetails(final FaculPremiumBean formObj,final String transactionNo,final String countryId) {	     
		return DAO.getPremiumDetails(formObj,transactionNo,countryId);
	}
	public List<FaculPremiumBean> getPremiumedList(final FaculPremiumBean beanObj, String type) {
		return DAO.getPremiumedList(beanObj, type);
	}
	public boolean PremiumEdit(final FaculPremiumBean formObj,final String countryId) {
		 return DAO.premiumEdit(formObj,countryId);
	}
	public static boolean GetPreList(final FaculPremiumBean frm) {
		 return DAO.getPreList(frm);
	}
	public boolean UpdatePremium(final FaculPremiumBean beanObj) {		
  	     return  DAO.premiumUpdateMethod(beanObj);
	}
	public List<Map<String,Object>> MDinstallmentDates(final String contNo,final String layerNo){
		return DAO.mdInstallmentDates(contNo,layerNo);
	}
	public String GetInstalmentAmount(final String contractNo,final String getAmount){
		return DAO.GetInstalmentAmount(contractNo, getAmount);
	}	
	public List getMandDInstallments(String contNo, String layerNo){
		return DAO.getMandDInstallments(contNo, layerNo);
	}	
	public String  getRPPremiumOC(String contNo, String layerNo){
		return DAO.getRPPremiumOC(contNo, layerNo);
	}	
	public List getSPRetroList(String contNo,String productId,String layerNo) {
		return DAO.getSPRetroList(contNo,productId, layerNo);
	}
	public List getRetroContracts(FaculPremiumBean beanObj) {		
		return DAO.getRetroContracts(beanObj);
	}
	public String getSumOfShareSign(FaculPremiumBean beanObj){		
		return DAO.getSumOfShareSign(beanObj);
	}
	public List<Map<String,Object>> getClaimNosDropDown(String contNo){		
		return DAO.getClaimNosDropDown(contNo);
	}
	public boolean getCashLossUpdateValidation(String contractNo,String transactionNo,String claimNo,String cashLOossUpdateOc,String excRate){
		return DAO.getCashLossUpdateValidation(contractNo,transactionNo,claimNo,cashLOossUpdateOc,excRate);
	}
	public String getMovementReportMaxDate(String branchCode){
		return DAO.getMovementReportMaxDate(branchCode);
	}
	public List<FaculPremiumBean> getAllocatedList(final FaculPremiumBean beanObj) {
		return DAO.getAllocatedList(beanObj);
	}
	public List getBrokerAndCedingName(FaculPremiumBean beanObj) {		
		return DAO.getBrokerAndCedingName(beanObj);
	}
	public List<Map<String,Object>> getaccountList(FaculPremiumBean bean) {
		return DAO.getaccountList(bean);
	}
	public List<Map<String, Object>> currencyList(FaculPremiumBean bean) {
		return DAO.currencyList(bean);
	}
	public void bankAddress(FaculPremiumBean bean) {
		DAO.bankAddress(bean);
	}
	public String GetPreviousPremium(FaculPremiumBean bean) {
		return DAO.GetPreviousPremium(bean);
	}
	public String GetContractPremium(FaculPremiumBean bean) {
		return DAO.GetContractPremium(bean);
	}
	public List<FaculPremiumBean> getCassLossCredit(FaculPremiumBean bean, String claimPayNo) {
		return DAO.getCassLossCredit(bean,claimPayNo);
	}
	public void InsertCashLossCredit(FaculPremiumBean bean) {
		DAO.InsertCashLossCredit(bean);
		
	}
	public String getDepartmentNo(FaculPremiumBean bean) {
		return DAO.getDepartmentNo(bean);
	}

    public List<Map<String,Object>> getConstantPeriodDropDown(String categoryId, String contNo ,FaculPremiumBean bean) {
		return DAO.getConstantPeriodDropDown(categoryId,contNo, bean);
    }

	public List<Map<String,Object>> SlideCommission(FaculPremiumBean bean,String countryId) {
		return DAO.SlideCommission(bean,countryId);
	}

	public String getCurrencyShortName(FaculPremiumBean bean) {
		return DAO.getCurrencyShortName(bean);
	}

	public void GetFieldValues(FaculPremiumBean bean) {
		DAO.GetFieldValues(bean);
	}

	public void PremiumContractDetails(FaculPremiumBean bean, String countryId) {
		DAO.PremiumContractDetails(bean,countryId);
	}

	public String getSlideValue(FaculPremiumBean bean, double ans) {
		return DAO.getSlideValue(bean,ans);
	}

	public void insertSlideCommission(FaculPremiumBean bean) {
		DAO.insertSlideCommission(bean);
	}

	/*public void PremSlideEditFieldValue(FaculPremiumBean bean, String type) {
		DAO.PremSlideEditFieldValue(bean,type);
	}*/
	public void addFieldValue(FaculPremiumBean bean){DAO.addFieldValue(bean);}
	public List<FaculPremiumBean> getPremiumReserved(FaculPremiumBean bean, String prTransNo, String countryId) {
		return DAO.getPremiumReserved(bean,prTransNo,countryId);
	}
	public List<Map<String, Object>> getOSBList(FaculPremiumBean bean) {
		return DAO.getOSBList( bean);
	}
	public int getCountCleanCUT(FaculPremiumBean bean) {
		return DAO.getCountCleanCUT(bean);
	}
	public int getCountAccountPeriod(FaculPremiumBean bean) {
		return DAO.getCountAccountPeriod(bean);
	}
	public void PremiumGetFieldValues(FaculPremiumBean bean) {
		 DAO.PremiumGetFieldValues(bean);
		
	}
	public String getPremiumValue(FaculPremiumBean bean, String profitCommType,String profitRto, String profitComm) {
		return DAO.getPremiumValue(bean,profitCommType,profitRto,profitComm);
	}
	public void GetLossFieldValues(FaculPremiumBean bean) {
		DAO.GetLossFieldValues( bean);
		
	}
	public String getLossValue(FaculPremiumBean bean, double parseDouble,
			String premium) {
		return DAO.getLossValue( bean,  parseDouble, premium);
	}
	public void insertSection(FaculPremiumBean bean) {
		DAO.insertSection( bean);
	}
	public int getSectionCount(FaculPremiumBean bean) {
		return DAO.getSectionCount(bean);
	}
	public int getDepositReleaseCount(FaculPremiumBean bean) {
		return DAO.getDepositReleaseCount(bean);
	}
	public List<Object> getAllocatedCassLossCredit(FaculPremiumBean bean) {
		return DAO.getAllocatedCassLossCredit(bean);
	}
	public List<Object> getAllocatedTransList(FaculPremiumBean bean) {
		return DAO.getAllocatedTransList(bean);
	}
	public double getReverseCassLossCredit(FaculPremiumBean bean) {
		return DAO.getReverseCassLossCredit(bean);
	}
	public void InsertReverseCashLossCredit(FaculPremiumBean bean) {
		DAO.InsertReverseCashLossCredit(bean);
	}
	public void cashLossmailTrigger(FaculPremiumBean bean) {
		DAO.cashLossmailTrigger(bean);
	}
	
}
