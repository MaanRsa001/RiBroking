package com.maan.dao;

import java.util.List;
import java.util.Map;
import com.maan.bean.FaculPremiumBean;

public interface XolPremiumDAO {
		
		boolean premiumInsertMethod(FaculPremiumBean beanObj);

		boolean contractDetails(FaculPremiumBean formObj, String country);

		boolean getPremiumDetails(FaculPremiumBean formObj,String transactionNo, String countryId);

		List<FaculPremiumBean> getPremiumedList(FaculPremiumBean beanObj, String type);

		boolean premiumEdit(FaculPremiumBean formObj, String countryId);

		boolean getPreList(FaculPremiumBean frm);

		boolean premiumUpdateMethod(FaculPremiumBean beanObj);

		List<Map<String,Object>> mdInstallmentDates(String contNo,String layerNo, String sourceId, String productId);
		
		List getMandDInstallments(String contNo,String layerNo, String productId);
		
		String GetInstalmentAmount(final String contractNo,final String layerNo,final String getAmount);
		
		String getRPPremiumOC(final String contractNo,final String layerNo, String productId);
		
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

		String GetAdjPremium(FaculPremiumBean bean);

		String getDepartmentId(FaculPremiumBean bean);

		void getVatInfo(FaculPremiumBean bean);

		List<Map<String, Object>> getRipremiumList(FaculPremiumBean bean);

		void updateRiStatus(FaculPremiumBean bean);

	}

