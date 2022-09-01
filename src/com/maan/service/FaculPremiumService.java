package com.maan.service;

import java.util.List;
import java.util.Map;
import com.maan.bean.FaculPremiumBean;
import com.maan.dao.FaculPremiumDAO;
import com.maan.dao.impl.FaculPremiumDAOImpl;

public class FaculPremiumService 
{
	
	private static final FaculPremiumDAO DAO=new FaculPremiumDAOImpl();
	
	public boolean InsertPremium(final FaculPremiumBean beanObj)
	{
	  return DAO.premiumInsertMethod(beanObj);
	}
	public boolean ContractDetails(final FaculPremiumBean formObj,final String country){
		 return DAO.contractDetails(formObj,country);	}

	public static boolean GetPremiumDetails(final FaculPremiumBean formObj,final String transactionNo,final String countryId) {	     
		return DAO.getPremiumDetails(formObj,transactionNo,countryId);
	}
	public List<FaculPremiumBean> getPremiumedList(final FaculPremiumBean beanObj) {
		return DAO.getPremiumedList(beanObj);
	}
	public boolean PremiumEdit(final FaculPremiumBean formObj, final String countryId) {
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
	public String GetPreviousPremium(FaculPremiumBean bean) {
		return DAO.GetPreviousPremium(bean);
	}
	public String GetContractPremium(FaculPremiumBean bean) {
		return DAO.GetContractPremium(bean);
	}
	public List<Map<String, Object>> currencyList(FaculPremiumBean bean) {
		return DAO.currencyList(bean);
	}
	public List<Map<String, Object>> productIdList(FaculPremiumBean bean) {
		return DAO.productIdList(bean);
	}
	public void PremiumContractDetails(FaculPremiumBean bean, String countryId) {
		DAO.PremiumContractDetails( bean,  countryId);
	}
	public List<Map<String, Object>> bonusdetails(FaculPremiumBean bean, String countryId) {
		return DAO. bonusdetails( bean,  countryId);
	}
	public void GetFieldValues(FaculPremiumBean bean) {
		DAO.GetFieldValues( bean);
	}
	public String getBonusValue(FaculPremiumBean bean, double parseDouble) {
		return DAO.getBonusValue(bean,parseDouble);
	}
	public void addFieldValue(FaculPremiumBean bean) {
		DAO.addFieldValue( bean);
		
	}
	public void PremBonusEditFieldValue(FaculPremiumBean bean, String string) {
		DAO.PremBonusEditFieldValue(bean,string);
		
	}
	public String getDepartmentId(FaculPremiumBean bean) {
		return DAO.getDepartmentId(bean);
	}
	public List<FaculPremiumBean> getPremiumTempList(FaculPremiumBean bean) {
		return DAO.getPremiumTempList(bean);
	}

}
