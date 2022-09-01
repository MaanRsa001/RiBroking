package com.maan.service;

import java.util.List;
import java.util.Map;
import com.maan.bean.FaculPremiumBean;
import com.maan.dao.XolPremiumDAO;
import com.maan.dao.impl.XolPremiumDAOImpl;

public class XolPremiumService {
		
		private static final XolPremiumDAO DAO=new XolPremiumDAOImpl();
		
		public boolean InsertPremium(final FaculPremiumBean beanObj)
		{
		  return DAO.premiumInsertMethod(beanObj);
		}
		public boolean ContractDetails(final FaculPremiumBean formObj,final String country){
			 return DAO.contractDetails(formObj,country);	}

		public static boolean GetPremiumDetails(final FaculPremiumBean formObj,final String transactionNo,final String countryId) {	     
			return DAO.getPremiumDetails(formObj,transactionNo,countryId);
		}
		public List<FaculPremiumBean> getPremiumedList(final FaculPremiumBean beanObj, String type) {
			return DAO.getPremiumedList(beanObj,type);
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
		public List<Map<String,Object>> MDinstallmentDates(final String contNo,final String layerNo, String sourceId, String productId){
			return DAO.mdInstallmentDates(contNo,layerNo,sourceId,productId);
		}
		public String GetInstalmentAmount(final String contractNo,final String layerNo,final String getAmount){
			return DAO.GetInstalmentAmount(contractNo,layerNo, getAmount);
		}	
		public List getMandDInstallments(String contNo, String layerNo, String productId){
			return DAO.getMandDInstallments(contNo, layerNo,productId);
		}	
		public String  getRPPremiumOC(String contNo, String layerNo, String productId){
			return DAO.getRPPremiumOC(contNo, layerNo,productId);
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
		public String GetAdjPremium(FaculPremiumBean bean) {
			return DAO.GetAdjPremium( bean);
		}
		public String getDepartmentId(FaculPremiumBean bean) {
			return DAO.getDepartmentId(bean);
		}
		
}
