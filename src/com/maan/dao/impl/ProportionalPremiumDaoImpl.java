package com.maan.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import com.maan.bean.FaculPremiumBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.CommonDAO;
import com.maan.dao.ProportionalPremiumDAO;
import com.maan.dao.ApiCaller.ApiForProportionalPremium;

public class ProportionalPremiumDaoImpl extends MyJdbcTemplate implements ProportionalPremiumDAO {
	DropDownControllor dropDownController=new DropDownControllor();
	ApiForProportionalPremium premiumapi = new ApiForProportionalPremium();
	private static final Logger LOGGER = LogUtil.getLogger(ProportionalPremiumDaoImpl.class);
	public boolean premiumInsertMethod(final FaculPremiumBean beanObj, String countryId){
		boolean saveFlag = false;
		try {
			premiumapi.premiumInsertMethod(beanObj,countryId);
			saveFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saveFlag;
	}
	public boolean contractDetails(final FaculPremiumBean bean,final String countryId){
	 boolean saveFlag=false;
	 	Map<String, Object> relist = premiumapi.contractDetails(bean);
		if(relist!=null) {
			bean.setContNo(relist.get("ContNo") == null ? "" : relist.get("ContNo").toString());
			bean.setUwYear(relist.get("UwYear") == null ? "" : relist.get("UwYear").toString());
			bean.setCedingCo(relist.get("CedingCo") == null ? "" : relist.get("CedingCo").toString());
			bean.setBroker(relist.get("Broker") == null ? "" : relist.get("Broker").toString());
			bean.setTreatyName_type(relist.get("TreatyNameType") == null ? "" : relist.get("TreatyNameType").toString());
			bean.setInsDate(relist.get("InsDate") == null ? "" : relist.get("InsDate").toString());
			bean.setExpDate(relist.get("ExpDate") == null ? "" : relist.get("ExpDate").toString());
			bean.setEpioc(relist.get("Epioc") == null ? "" : relist.get("Epioc").toString());
			bean.setOverRider_view(relist.get("OverRiderView") == null ? "" : relist.get("OverRiderView").toString());
			bean.setBrokerage_view(relist.get("BrokerageView") == null ? "" : relist.get("BrokerageView").toString());
			bean.setCommission_view(relist.get("CommissionView") == null ? "" : relist.get("CommissionView").toString());
			bean.setTax_view(relist.get("TaxView") == null ? "" : relist.get("TaxView").toString());
			bean.setShareSigned(relist.get("ShareSigned") == null ? "" : relist.get("ShareSigned").toString());
			bean.setOtherCostView(relist.get("OtherCostView") == null ? "" : relist.get("OtherCostView").toString());
			bean.setTreatyType(relist.get("TreatyType") == null ? "" : relist.get("TreatyType").toString());
			bean.setBusinessType(relist.get("BusinessType") == null ? "" : relist.get("BusinessType").toString());
			bean.setBaseCurrencyId(relist.get("BaseCurrencyId") == null ? "" : relist.get("BaseCurrencyId").toString());
			bean.setPremium_Reserve_view(relist.get("PremiumReserveView") == null ? "" : relist.get("PremiumReserveView").toString());
			bean.setLoss_reserve_view(relist.get("LossReserveView") == null ? "" : relist.get("LossReserveView").toString());
			bean.setRdsExchageRate(relist.get("RdsExchageRate") == null ? "" : relist.get("RdsExchageRate").toString());
			bean.setPremiumReserve(relist.get("PremiumReserve") == null ? "" : relist.get("PremiumReserve").toString());
	//		bean.setBranchCode(relist.get("BranchCode") == null ? "" : relist.get("BranchCode").toString());
			bean.setAmendId(relist.get("AmendId") == null ? "" : relist.get("AmendId").toString());
	//		bean.setProductId(relist.get("ProductId") == null ? "" : relist.get("ProductId").toString());
			bean.setProfit_Center(relist.get("ProfitCenter") == null ? "" : relist.get("ProfitCenter").toString());
			bean.setSubProfit_center(relist.get("SubProfitCenter") == null ? "" : relist.get("SubProfitCenter").toString());
			bean.setProposal_No(relist.get("ProposalNo") == null ? "" : relist.get("ProposalNo").toString());
			bean.setLayerNo(relist.get("LayerNo") == null ? "" : relist.get("LayerNo").toString());
			bean.setMonth(relist.get("Month") == null ? "" : relist.get("Month").toString());
			bean.setBaseCurrencyName(relist.get("BaseCurrencyName") == null ? "" : relist.get("BaseCurrencyName").toString());
			bean.setPolicyBranch(relist.get("PolicyBranch") == null ? "" : relist.get("PolicyBranch").toString());
			bean.setAddress(relist.get("Address") == null ? "" : relist.get("Address").toString());
			bean.setDepartmentId(relist.get("DepartmentId") == null ? "" : relist.get("DepartmentId").toString());
			bean.setPredepartment(relist.get("PreDepartment") == null ? "" : relist.get("PreDepartment").toString());
			bean.setConsubProfitId(relist.get("ConsubProfitId") == null ? "" : relist.get("ConsubProfitId").toString());
			bean.setAcceptenceDate(relist.get("AcceptenceDate") == null ? "" : relist.get("AcceptenceDate").toString());
			bean.setProfitCommYN(relist.get("ProfitCommYN") == null ? "" : relist.get("ProfitCommYN").toString());
			bean.setCommissionSurb_view(relist.get("CommissionSurbView") == null ? "" : relist.get("CommissionSurbView").toString());
			bean.setOurAssessmentOfOrginal(relist.get("OurAssessmentOfOrginal") == null ? "" : relist.get("OurAssessmentOfOrginal").toString());
			bean.setPremiumQuota_view(relist.get("PremiumQuotaView") == null ? "" : relist.get("PremiumQuotaView").toString());
			bean.setPremiumsurp_view(relist.get("PremiumsurpView") == null ? "" : relist.get("PremiumsurpView").toString());
			bean.setXl_cost_view(relist.get("XlCostView") == null ? "" : relist.get("XlCostView").toString());
			bean.setCurrencyName(relist.get("CurrencyName") == null ? "" : relist.get("CurrencyName").toString());
			bean.setSettlement_status(relist.get("SettlementStatus") == null ? "" : relist.get("SettlementStatus").toString());
			bean.setSum_of_paid_premium(relist.get("SumofPaidPremium") == null ? "" : relist.get("SumofPaidPremium").toString());
			
			if(relist!=null && relist.size()>0) {
				saveFlag = true;
		}
	}
	return saveFlag;
}
public boolean getPremiumDetails(final FaculPremiumBean bean,final String TransactionNo,final String countryId)  {
	 boolean saveFlag=false;
	 
	 List<Map<String,Object>> relist= premiumapi.getPremiumDetails(bean,TransactionNo,countryId);
	if(relist!=null) {
		for(int i=0;i<relist.size();i++) {
		Map<String, Object> list = (Map<String,Object>)relist.get(i);
		bean.setContNo(list.get("ContNo") == null ? "" : list.get("ContNo").toString());
		bean.setTransactionNo(list.get("TransactionNo") == null ? "" : list.get("TransactionNo").toString());
		bean.setTransaction(list.get("Transaction") == null ? "" : list.get("Transaction").toString());
		bean.setBrokerage(list.get("Brokerage") == null ? "" : list.get("Brokerage").toString());
		bean.setTax(list.get("Tax") == null ? "" : list.get("Tax").toString());
		bean.setPremiumQuotaShare(list.get("PremiumQuotaShare") == null ? "" : list.get("PremiumQuotaShare").toString());
		bean.setCommissionQuotaShare(list.get("CommissionQuotaShare") == null ? "" : list.get("CommissionQuotaShare").toString());
		bean.setPremiumSurplus(list.get("PremiumSurplus") == null ? "" : list.get("PremiumSurplus").toString());
		bean.setCommissionSurplus(list.get("CommissionSurplus") == null ? "" : list.get("CommissionSurplus").toString());
		bean.setPremiumportifolioIn(list.get("PremiumportifolioIn") == null ? "" : list.get("PremiumportifolioIn").toString());
		bean.setCliamPortfolioin(list.get("CliamPortfolioin") == null ? "" : list.get("CliamPortfolioin").toString());
		bean.setPremiumportifolioout(list.get("Premiumportifolioout") == null ? "" : list.get("Premiumportifolioout").toString());
		bean.setLossReserveReleased(list.get("LossReserveReleased") == null ? "" : list.get("LossReserveReleased").toString());
		bean.setPremiumReserve_QuotaShare(list.get("PremiumReserveQuotaShare") == null ? "" : list.get("PremiumReserveQuotaShare").toString());
		bean.setCashLoss_Credit(list.get("CashLossCredit") == null ? "" : list.get("CashLossCredit").toString());
		bean.setLoss_ReserveRetained(list.get("LossReserveRetained") == null ? "" : list.get("LossReserveRetained").toString());
		bean.setProfit_Commission(list.get("ProfitCommission") == null ? "" : list.get("ProfitCommission").toString());
		bean.setCash_LossPaid(list.get("CashLossPaid") == null ? "" : list.get("CashLossPaid").toString());
		bean.setNetDue(list.get("NetDue") == null ? "" : list.get("NetDue").toString());
		bean.setReceipt_no(list.get("Receiptno") == null ? "" : list.get("Receiptno").toString());
		bean.setClaims_paid(list.get("ClaimsPaid") == null ? "" : list.get("ClaimsPaid").toString());
		bean.setInception_Date(list.get("InceptionDate") == null ? "" : list.get("InceptionDate").toString());
		bean.setXl_Cost(list.get("XlCost") == null ? "" : list.get("XlCost").toString());
		bean.setCliam_portfolio_out(list.get("CliamPortfolioOut") == null ? "" : list.get("CliamPortfolioOut").toString());
		bean.setPremium_Reserve_Released(list.get("PremiumReserveReleased") == null ? "" : list.get("PremiumReserveReleased").toString());
		bean.setAccount_Period(list.get("AccountPeriod") == null ? "" : list.get("AccountPeriod").toString());
		bean.setAccount_Period_year(list.get("AccountPeriodYear") == null ? "" : list.get("AccountPeriodYear").toString());
		bean.setCurrencyId(list.get("CurrencyId") == null ? "" : list.get("CurrencyId").toString());
		bean.setCurrency(list.get("Currency") == null ? "" : list.get("Currency").toString());
		bean.setBrokerage_usd(list.get("BrokerageUsd") == null ? "" : list.get("BrokerageUsd").toString());
		bean.setOtherCost(list.get("OtherCost") == null ? "" : list.get("OtherCost").toString());
		bean.setTax_usd(list.get("TaxUsd") == null ? "" : list.get("TaxUsd").toString());
		bean.setPremium_reserve_quota_share_usd(list.get("PremiumQuotaShareUsd") == null ? "" : list.get("PremiumQuotaShareUsd").toString());
		bean.setCommsissionQuotaShare_usd(list.get("CommsissionQuotaShareUsd") == null ? "" : list.get("CommsissionQuotaShareUsd").toString());
		bean.setPremium_surplus_usd(list.get("PremiumSurplusUsd") == null ? "" : list.get("PremiumSurplusUsd").toString());
		bean.setComission_surplus_usd(list.get("ComissionSurplusUsd") == null ? "" : list.get("ComissionSurplusUsd").toString());
		bean.setPremium_portfolio_in_usd(list.get("PremiumPortfolioInUsd") == null ? "" : list.get("PremiumPortfolioInUsd").toString());
		bean.setCliam_portfolio_usd(list.get("CliamPortfolioUsd") == null ? "" : list.get("CliamPortfolioUsd").toString());
		bean.setPremium_PortfolioOut_usd(list.get("PremiumPortfolioOutUsd") == null ? "" : list.get("PremiumPortfolioOutUsd").toString());
		bean.setLoss_Reserve_released_usd(list.get("LossReserveReleasedUsd") == null ? "" : list.get("LossReserveReleasedUsd").toString());
		bean.setPremium_reserve_quota_share_usd(list.get("PremiumReserveQuotaShareUsd") == null ? "" : list.get("PremiumReserveQuotaShareUsd").toString());
		bean.setCash_loss_credit_usd(list.get("CashLossCreditUsd") == null ? "" : list.get("CashLossCreditUsd").toString());
		bean.setLoss_reserve_retained_usd(list.get("LossReserveRetainedUsd") == null ? "" : list.get("LossReserveRetainedUsd").toString());
		bean.setProfit_commission_usd(list.get("ProfitCommissionUsd") == null ? "" : list.get("ProfitCommissionUsd").toString());
		bean.setCash_loss_paid_usd(list.get("CashLossPaidUsd") == null ? "" : list.get("CashLossPaidUsd").toString());
		bean.setClams_paid_usd(list.get("ClamsPaidUsd") == null ? "" : list.get("ClamsPaidUsd").toString());
		bean.setXl_cost_usd(list.get("XlCostUsd") == null ? "" : list.get("XlCostUsd").toString());
		bean.setCliam_portfolio_out_usd(list.get("CliamPortfolioOutUsd") == null ? "" : list.get("CliamPortfolioOutUsd").toString());
		bean.setPremium_Reserve_Released_usd(list.get("PremiumReserveReleasedUsd") == null ? "" : list.get("PremiumReserveReleasedUsd").toString());
		bean.setNet_due_usd(list.get("NetDueUsd") == null ? "" : list.get("NetDueUsd").toString());
		bean.setOtherCostUSD(list.get("OtherCostUSD") == null ? "" : list.get("OtherCostUSD").toString());
		bean.setCedentRef(list.get("CedentRef") == null ? "" : list.get("CedentRef").toString());
		bean.setRemarks(list.get("Remarks") == null ? "" : list.get("Remarks").toString());
		bean.setTotalCredit(list.get("TotalCredit") == null ? "" : list.get("TotalCredit").toString());
		bean.setTotalCreditDC(list.get("TotalCreditDC") == null ? "" : list.get("TotalCreditDC").toString());
		bean.setTotalDebit(list.get("TotalDebit") == null ? "" : list.get("TotalDebit").toString());
		bean.setTotalDebitDC(list.get("TotalDebitDC") == null ? "" : list.get("TotalDebitDC").toString());
		bean.setInterest(list.get("Interest") == null ? "" : list.get("Interest").toString());
		bean.setInterestDC(list.get("InterestDC") == null ? "" : list.get("InterestDC").toString());
		bean.setOsClaimsLossUpdateOC(list.get("OsClaimsLossUpdateOC") == null ? "" : list.get("OsClaimsLossUpdateOC").toString());
		bean.setOsClaimsLossUpdateDC(list.get("OsClaimsLossUpdateDC") == null ? "" : list.get("OsClaimsLossUpdateDC").toString());
		bean.setOverrider(list.get("Overrider") == null ? "" : list.get("Overrider").toString());
		bean.setOverriderUSD(list.get("OverriderUSD") == null ? "" : list.get("OverriderUSD").toString());
		bean.setAmendmentDate(list.get("AmendmentDate") == null ? "" : list.get("AmendmentDate").toString());
		bean.setWithHoldingTaxOC(list.get("WithHoldingTaxOC") == null ? "" : list.get("WithHoldingTaxOC").toString());
		bean.setWithHoldingTaxDC(list.get("WithHoldingTaxDC") == null ? "" : list.get("WithHoldingTaxDC").toString());
		bean.setDueDate(list.get("DueDate") == null ? "" : list.get("DueDate").toString());
		bean.setCreditsign(list.get("Creditsign") == null ? "" : list.get("Creditsign").toString());
		bean.setRi_cession(list.get("RiCession") == null ? "" : list.get("RiCession").toString());
		bean.setTaxDedectSource(list.get("TaxDedectSource") == null ? "" : list.get("TaxDedectSource").toString());
		bean.setTaxDedectSourceDc(list.get("TaxDedectSourceDc") == null ? "" : list.get("TaxDedectSourceDc").toString());
		bean.setVatPremium(list.get("VatPremium")==null?"":list.get("VatPremium").toString());
	    bean.setVatPremiumDc(list.get("VatPremiumDc")==null?"":list.get("VatPremiumDc").toString());
	    bean.setBrokerageVat(list.get("BrokerageVat")==null?"":list.get("BrokerageVat").toString());
	    bean.setBrokerageVatDc(list.get("BrokerageVatDc")==null?"":list.get("BrokerageVatDc").toString());
	    bean.setDocumentType(list.get("DocumentType")==null?"":list.get("DocumentType").toString());
		bean.setLossParticipation(list.get("LossParticipation") == null ? "" : list.get("LossParticipation").toString());
		bean.setLossParticipationDC(list.get("LossParticipationDC") == null ? "" : list.get("LossParticipationDC").toString());
		bean.setSlideScaleCom(list.get("SlideScaleCom") == null ? "" : list.get("SlideScaleCom").toString());
		bean.setSlideScaleComDC(list.get("SlideScaleComDC") == null ? "" : list.get("SlideScaleComDC").toString());
		bean.setSubProfitId(list.get("SubProfitId") == null ? "" : list.get("SubProfitId").toString());
		bean.setExchRate(list.get("ExchRate") == null ? "" : list.get("ExchRate").toString());
		bean.setStatementDate(list.get("StatementDate") == null ? "" : list.get("StatementDate").toString());
		bean.setPremiumClass(list.get("PremiumClass") == null ? "" : list.get("PremiumClass").toString());
		bean.setPremiumSubClass(list.get("PremiumSubClass") == null ? "" : list.get("PremiumSubClass").toString());
		bean.setOsbYN(list.get("OsbYN") == null ? "" : list.get("OsbYN").toString());
		bean.setSectionName(list.get("SectionName") == null ? "" : list.get("SectionName").toString());
		bean.setAccDate(list.get("AccDate") == null ? "" : list.get("AccDate").toString());
		bean.setSum_of_paid_premium(list.get("SumOfPaidPremium") == null ? "" : list.get("SumOfPaidPremium").toString());
		bean.setCurrencyName(list.get("CurrencyName") == null ? "" : list.get("CurrencyName").toString());
	}
	}
	if(relist!=null && relist.size()>0) {
		saveFlag = true;
	}
	return saveFlag;
}
public List<FaculPremiumBean> getPremiumedList(final FaculPremiumBean beanObj, String type){
	List<FaculPremiumBean> premiumlist = new ArrayList<FaculPremiumBean>();
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	list = premiumapi.getPremiumedList(beanObj, type);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> PremiumedList = (Map<String, Object>) list.get(i);
			FaculPremiumBean tempBean = new FaculPremiumBean();
			tempBean.setTransactionNo(PremiumedList.get("TransactionNo") == null ? "": PremiumedList.get("TransactionNo").toString());
			tempBean.setAccount_Period(PremiumedList.get("AccountPeriod") == null ? "": PremiumedList.get("AccountPeriod").toString());
			tempBean.setAccountPeriodDate(PremiumedList.get("AccountPeriodDate") == null ? "": PremiumedList.get("AccountPeriodDate").toString());
			tempBean.setStatementDate(PremiumedList.get("StatementDate") == null ? "": PremiumedList.get("StatementDate").toString());
			tempBean.setTransDate(PremiumedList.get("TransDate") == null ? "": PremiumedList.get("TransDate").toString());
			tempBean.setInception_Date(PremiumedList.get("InceptionDate") == null ? "": PremiumedList.get("InceptionDate").toString());
			tempBean.setContNo(PremiumedList.get("ContNo") == null ? "": PremiumedList.get("ContNo").toString());
			tempBean.setCeding_Company_Name(PremiumedList.get("CedingCompanyName") == null ? "": PremiumedList.get("CedingCompanyName").toString());
			tempBean.setBroker(PremiumedList.get("Broker") == null ? "": PremiumedList.get("Broker").toString());
			tempBean.setRequestNo(PremiumedList.get("RequestNo") == null ? "": PremiumedList.get("RequestNo").toString());
			tempBean.setProposal_No(PremiumedList.get("ProposalNo") == null ? "": PremiumedList.get("ProposalNo").toString());
			tempBean.setLayerno(PremiumedList.get("Layerno") == null ? "": PremiumedList.get("Layerno").toString());
			tempBean.setTransDropDownVal(PremiumedList.get("TransDropDownVal") == null ? "": PremiumedList.get("TransDropDownVal").toString());
			tempBean.setEndtYN(PremiumedList.get("EndtYN") == null ? "": PremiumedList.get("EndtYN").toString());
			tempBean.setProductId(PremiumedList.get("ProductId") == null ? "": PremiumedList.get("ProductId").toString());
			tempBean.setMovementYN(PremiumedList.get("MovementYN") == null ? "": PremiumedList.get("MovementYN").toString());
			tempBean.setTransOpenperiodStatus(PremiumedList.get("TransOpenperiodStatus") == null ? "": PremiumedList.get("TransOpenperiodStatus").toString());
			tempBean.setAllocatedYN(PremiumedList.get("AllocatedYN") == null ? "": PremiumedList.get("AllocatedYN").toString());
			tempBean.setDeleteStatus(PremiumedList.get("DeleteStatus") == null ? "": PremiumedList.get("DeleteStatus").toString());
			tempBean.setSectionNo(PremiumedList.get("SectionNo") == null ? "": PremiumedList.get("SectionNo").toString());
			premiumlist.add(tempBean);
		}
		return premiumlist;
}
public boolean premiumEdit(final FaculPremiumBean bean,final String countryId) {
	boolean savFlg = false;
			Map<String,Object> resMap = premiumapi.premiumEdit(bean,countryId);
			if(resMap!=null) {
			
			
			bean.setBrokerage(resMap.get("Brokerage")==null?"":resMap.get("Brokerage").toString());
			bean.setTax(resMap.get("Tax")==null?"":resMap.get("Tax").toString());
			bean.setPremiumQuotaShare(resMap.get("PremiumQuotaShare")==null?"":resMap.get("PremiumQuotaShare").toString());
			bean.setCommissionQuotaShare(resMap.get("CommissionQuotaShare")==null?"":resMap.get("CommissionQuotaShare").toString());
			bean.setPremiumSurplus(resMap.get("PremiumSurplus")==null?"":resMap.get("PremiumSurplus").toString());
			bean.setCommissionSurplus(resMap.get("CommissionSurplus")==null?"":resMap.get("CommissionSurplus").toString());
			bean.setPremiumportifolioIn(resMap.get("PremiumportifolioIn")==null?"":resMap.get("PremiumportifolioIn").toString());
			bean.setCliamPortfolioin(resMap.get("CliamPortfolioin")==null?"":resMap.get("CliamPortfolioin").toString());
			bean.setPremiumportifolioout(resMap.get("Premiumportifolioout")==null?"":resMap.get("Premiumportifolioout").toString());
			bean.setLossReserveReleased(resMap.get("LossReserveReleased")==null?"":resMap.get("LossReserveReleased").toString());
			bean.setPremiumReserve_QuotaShare(resMap.get("PremiumReserveQuotaShare")==null?"":resMap.get("PremiumReserveQuotaShare").toString());
			bean.setCashLoss_Credit(resMap.get("CashLossCredit")==null?"":resMap.get("CashLossCredit").toString());
			bean.setLoss_ReserveRetained(resMap.get("LossReserveRetained")==null?"":resMap.get("LossReserveRetained").toString());
			bean.setProfit_Commission(resMap.get("ProfitCommission")==null?"":resMap.get("ProfitCommission").toString());
			bean.setCash_LossPaid(resMap.get("CashLossPaid")==null?"":resMap.get("CashLossPaid").toString());
			
			bean.setNetDue(resMap.get("NetDue")==null?"":resMap.get("NetDue").toString());
			
			bean.setClaims_paid(resMap.get("Claimspaid")==null?"":resMap.get("Claimspaid").toString());
			//bean.setSettlement_status(resMap.getString("SETTLEMENT_STATUS"));
		    bean.setMd_premium(resMap.get("Mdpremium")==null?"":resMap.get("Mdpremium").toString());
		    bean.setAdjustment_premium(resMap.get("Adjustmentpremium")==null?"":resMap.get("Adjustmentpremium").toString());
		    bean.setRecuirement_premium(resMap.get("Recuirementpremium")==null?"":resMap.get("Recuirementpremium").toString());
		    bean.setCommission(resMap.get("Commission")==null?"":resMap.get("Commission").toString());
		   
		    bean.setXl_Cost(resMap.get("XlCost")==null?"":resMap.get("XlCost").toString());
		    bean.setCliam_portfolio_out(resMap.get("Cliamportfolioout")==null?"":resMap.get("Cliamportfolioout").toString());
		    bean.setPremium_Reserve_Released(resMap.get("PremiumReserveReleased")==null?"":resMap.get("PremiumReserveReleased").toString());
		    bean.setOtherCost(resMap.get("OtherCost")==null?"":resMap.get("OtherCost").toString());
		    bean.setCedentRef(resMap.get("CedentRef")==null?"":resMap.get("CedentRef").toString());
			
			bean.setInterest(resMap.get("Interest")==null?"":resMap.get("Interest").toString());
			bean.setOsClaimsLossUpdateOC(resMap.get("OsClaimsLossUpdateOC")==null?"":resMap.get("OsClaimsLossUpdateOC").toString());
			bean.setOverrider(resMap.get("Overrider")==null?"":resMap.get("Overrider").toString());
			bean.setOverriderUSD(resMap.get("OverriderUSD")==null?"":resMap.get("OverriderUSD").toString());
			
            bean.setWithHoldingTaxOC(resMap.get("WithHoldingTaxOC")==null?"":resMap.get("WithHoldingTaxOC").toString());
            bean.setWithHoldingTaxDC(resMap.get("WithHoldingTaxDC")==null?"":resMap.get("WithHoldingTaxDC").toString());
            
            bean.setTaxDedectSource(resMap.get("TaxDedectSource")==null?"":resMap.get("TaxDedectSource").toString());
			bean.setTaxDedectSourceDc(resMap.get("TaxDedectSourceDc")==null?"":resMap.get("TaxDedectSourceDc").toString());
			bean.setVatPremium(resMap.get("VatPremium")==null?"":resMap.get("VatPremium").toString());
            bean.setVatPremiumDc(resMap.get("VatPremiumDc")==null?"":resMap.get("VatPremiumDc").toString());
            bean.setBrokerageVat(resMap.get("BrokerageVat")==null?"":resMap.get("BrokerageVat").toString());
            bean.setBrokerageVatDc(resMap.get("BrokerageVatDc")==null?"":resMap.get("BrokerageVatDc").toString());
            
			bean.setLossParticipation(resMap.get("LossParticipation")==null?"":resMap.get("LossParticipation").toString());
			bean.setLossParticipationDC(resMap.get("LossParticipationDC")==null?"":resMap.get("LossParticipationDC").toString());
			bean.setSlideScaleCom(resMap.get("SlideScaleCom")==null?"":resMap.get("SlideScaleCom").toString());
			bean.setSlideScaleComDC(resMap.get("SlideScaleComDC")==null?"":resMap.get("SlideScaleComDC").toString());
			bean.setSubProfitId(resMap.get("SubProfitId")==null?"":resMap.get("SubProfitId").toString());
			bean.setPrAllocatedAmount(resMap.get("PrAllocatedAmount")==null?"":resMap.get("PrAllocatedAmount").toString());
			bean.setLrAllocatedAmount(resMap.get("LrAllocatedAmount")==null?"":resMap.get("LrAllocatedAmount").toString());
			
			
			 if(!"transEdit".equalsIgnoreCase(bean.getMode())) {
				bean.setTransaction(resMap.get("Transaction")==null?"":resMap.get("Transaction").toString());
				bean.setAccount_Period(resMap.get("AccountPeriod")==null?"":resMap.get("AccountPeriod").toString());
				bean.setAccount_Period_year(resMap.get("AccountPeriodyear")==null?"":resMap.get("AccountPeriodyear").toString());
				bean.setCurrencyId(resMap.get("CurrencyId")==null?"":resMap.get("CurrencyId").toString());
				bean.setCurrency(resMap.get("Currency")==null?"":resMap.get("Currency").toString());
				bean.setExchRate(resMap.get("ExchRate")==null?"":resMap.get("ExchRate").toString());
				bean.setStatus(resMap.get("Status")==null?"":resMap.get("Status").toString());
				bean.setEnteringMode(resMap.get("EnteringMode")==null?"":resMap.get("EnteringMode").toString());
				bean.setReceipt_no(resMap.get("Receiptno")==null?"":resMap.get("Receiptno").toString());
				bean.setInstlmentNo(resMap.get("InstlmentNo")==null?"":resMap.get("InstlmentNo").toString());
				bean.setInception_Date(resMap.get("InceptionDate")==null?"":resMap.get("InceptionDate").toString());
				bean.setRemarks(resMap.get("Remarks")==null?"":resMap.get("Remarks").toString());
				bean.setAmendmentDate(resMap.get("AmendmentDate")==null?"":resMap.get("AmendmentDate").toString());
				bean.setRi_cession(resMap.get("Ricession")==null?"":resMap.get("Ricession").toString());
				bean.setDocumentType(resMap.get("DocumentType")==null?"":resMap.get("DocumentType").toString());
				bean.setOsbYN(resMap.get("OsbYN")==null?"":resMap.get("OsbYN").toString());
				bean.setSectionName(resMap.get("SectionName")==null?"":resMap.get("SectionName").toString());;
//				bean.setSectionType("2");
				bean.setAccountPeriodDate(resMap.get("AccountPeriodDate")==null?"":resMap.get("AccountPeriodDate").toString());
				bean.setPredepartment(resMap.get("Predepartment")==null?"":resMap.get("Predepartment").toString());
				bean.setStatementDate(resMap.get("StatementDate")==null?"":resMap.get("StatementDate").toString());
				bean.setMipremium1(resMap.get("M1OC")==null?"":resMap.get("M1OC").toString());
				bean.setMipremium2(resMap.get("M2OC")==null?"":resMap.get("M2OC").toString());
				bean.setMipremium3(resMap.get("M3OC")==null?"":resMap.get("M3OC").toString());
			 }
			
		}
		if(resMap!=null && resMap.size()>0)
			savFlg = true;
		return savFlg;
	}

	public boolean getPreList(final FaculPremiumBean bean) {
		boolean saveFlag=false;
		Map<String, Object> list = (Map<String, Object>) premiumapi.getPreList(bean);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				bean.setUwYear(list.get("UwYear") == null ? "" : list.get("UwYear").toString());
				bean.setContNo(list.get("ContNo") == null ? "" : list.get("ContNo").toString());
				bean.setCeding_Company_Name(list.get("CedingCompanyName") == null ? "" : list.get("CedingCompanyName").toString());
				bean.setBrokername(list.get("BrokerName") == null ? "" : list.get("BrokerName").toString());
				bean.setDepartment_Name(list.get("DepartmentName") == null ? "" : list.get("DepartmentName").toString());
				bean.setLayerno(list.get("Layerno") == null ? "" : list.get("Layerno").toString());
				bean.setProductId(list.get("ProductId") == null ? "" : list.get("ProductId").toString());
				bean.setProposal_No(list.get("ProposalNo") == null ? "" : list.get("ProposalNo").toString());
				bean.setCeaseStatus(list.get("CeaseStatus") == null ? "" : list.get("CeaseStatus").toString());
			
				if(list!=null && list.size()>0)
					saveFlag = true;
				}
			}
		return saveFlag;
	}
	public boolean premiumUpdateMethod(final FaculPremiumBean beanObj){
		boolean saveFlag = false;
		try {
			premiumapi.premiumUpdateMethod(beanObj);
			saveFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveFlag;
	}

	public List<Map<String,Object>> mdInstallmentDates(final String contNo,final String layerNo) {
		LOGGER.info("PremiumDAOImpl mdInstallmentDates || Enter");
		String query="";
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	    try{
		    Object[] args=new Object[6];
		    args[0]=contNo;
		    args[1]=layerNo;
		    args[2]=contNo;
		    args[3]=layerNo;
		    args[4]=contNo;
		    args[5]=layerNo;
		    query=getQuery(DBConstants.PREMIUM_SELECT_MDINSTALMENTLIST);
		    LOGGER.info("Select mdInstallmentDates Query=>"+query);
		    list=this.mytemplate.queryForList(query, args);
		    Map<String,Object> tempMap1 = new HashMap<String, Object>();
		    tempMap1.put("KEY1","EP");
		    tempMap1.put("VALUE","Endorsement Premium");
		    list.add(tempMap1);
			}catch (Exception exe) {
				LOGGER.debug("Exception "+exe);
		}
	    LOGGER.info("PremiumDAOImpl mdInstallmentDates || Exit");
		   return list;
	}
	public List getMandDInstallments(String contNo, String layerNo)
			{
		LOGGER.info("PremiumDAOImpl getMandDInstallments || Enter");
		List list=null;
		try{
			String query=this.getQuery(DBConstants.PREMIUM_SELECT_MDINSTALLMENTS);
			 LOGGER.info("Select mdInstallmentDates Query=>"+query);
			 LOGGER.info("Contract No=>"+contNo);
			 LOGGER.info("Layer No=>"+layerNo);
			 list=this.mytemplate.queryForList(query, new Object[]{contNo,layerNo});
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("PremiumDAOImpl getMandDInstallments || Exit List Size"+list.size());
		return list;
	}

	public String GetInstalmentAmount(String contractNo, String getAmount)
			{
		LOGGER.info("PremiumDAOImpl GetInstalmentAmount || Enter");
		String  string=null;
		try{
			String query=this.getQuery(DBConstants.PREMIUM_SELECT_MNDPREMIUMOC);
			 final String[] Instalmentno=getAmount.split("_");
			 LOGGER.info("Select mdInstallmentDates Query=>"+query);
			 LOGGER.info("Contract No=>"+contractNo);
			 LOGGER.info("Inst No=>"+Instalmentno[0]);
			 string=(String)this.mytemplate.queryForObject(query, new Object[]{contractNo,Instalmentno[0]},String.class);
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("PremiumDAOImpl GetInstalmentAmount || Exit Ins Amt=>"+string);
		return string;
	}

	public String getRPPremiumOC(String contractNo, String layerNo)
			{
		LOGGER.info("PremiumDAOImpl getRPPremiumOC || Enter");
		String  string=null;
		try{
			String query=this.getQuery(DBConstants.PREMIUM_SELECT_RPPREMIUMOC);
			 LOGGER.info("Select RP Premium Query=>"+query);
			 LOGGER.info("Contract No=>"+contractNo);
			 LOGGER.info("Layer No=>"+layerNo);
			 string=(String)this.mytemplate.queryForObject(query, new Object[]{contractNo,layerNo},String.class);
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("PremiumDAOImpl getRPPremiumOC || Exit RP Amt=>"+string);
		return string;
	}

	public List<Map<String, Object>> getSPRetroList(String contNo,String productId, String layerNo){
		return premiumapi.getSPRetroList(contNo);
	}
	public List<Map<String, Object>> getRetroContracts(FaculPremiumBean beanObj) {
		return premiumapi.getRetroContracts(beanObj);
	}
	public String getSumOfShareSign(FaculPremiumBean beanObj)
			{
		LOGGER.info("PremiumDAOImpl getSumOfShareSign || Enter");
		String query="";
		String  string="0";
		String noOfRetroCess="";
		Object args[]=null;
		try{
				query=this.getQuery(DBConstants.PREMIUM_SELECT_GETNORETROCESS);
				LOGGER.info("Query=>"+query);
				args=new Object[1];
				args[0]=beanObj.getRetroContractNo();
				LOGGER.info("Args[0]==>"+args[0]);
				noOfRetroCess=(String)this.mytemplate.queryForObject(query, args,String.class);
				LOGGER.info("Result=>"+noOfRetroCess);
				query=this.getQuery(DBConstants.PREMIUM_SELECT_GETSUMOFSHARESIGN);
				LOGGER.info("Query=>"+query);
				args=new Object[2];
				args[0]=beanObj.getRetroContractNo();
				args[1]=Integer.parseInt(noOfRetroCess)-1;
				LOGGER.info("Args[0]==>"+args[0]+"\nArgs[1]==>"+args[1]);
				string=(String)this.mytemplate.queryForObject(query, args,String.class);
				LOGGER.info("Result=>"+string);
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("PremiumDAOImpl getSumOfShareSign || Exit Sum of Retro SS Amt=>"+string);
		return string;
	}

	public List<Map<String, Object>> getClaimNosDropDown(String contNo){ 
	    return premiumapi.getClaimNosDropDown(contNo);
	}
	
	public boolean getCashLossUpdateValidation(String contractNo,String transactionNo, String claimNo,String cashLOossUpdateOc,String excRate) {
		LOGGER.info("PremiumDAOImpl getCashLossUpdateValidation() || Enter Contract No=>"+contractNo+"transactionNo=>"+transactionNo+"claimNo=>"+claimNo+"cashLOossUpdateOc=>"+cashLOossUpdateOc+"excRate=>"+excRate);
		boolean cashlossFlag=false;
		transactionNo=StringUtils.isBlank(transactionNo)?"9999999999":transactionNo;
	 	List<Map<String,String>> claimNos=new ArrayList<Map<String,String>>();
		try{
		 	String query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFPAIDCLAIM);
		    LOGGER.info("Select Query=>"+query);
		    LOGGER.info("Args[0]=>"+contractNo+"Args[1]=>"+claimNo);
		    double sumClaimsPaid=(Double)this.mytemplate.queryForObject(query, new String[]{contractNo,claimNo},Double.class);
		    LOGGER.info("Select Result=>"+sumClaimsPaid);
		    query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFCASHLOSSUPDATE);
		    LOGGER.info("Select Query=>"+query);
		    LOGGER.info("Args[0]=>"+contractNo+"Args[1]=>"+transactionNo+"Args[2]=>"+claimNo+"Args[3]=>"+transactionNo);
		    double sumLossUpdatesPaid=(Double)this.mytemplate.queryForObject(query, new String[]{contractNo,transactionNo,claimNo,transactionNo},Double.class);
		    double totalLossUpdates=sumLossUpdatesPaid+(Double.parseDouble(cashLOossUpdateOc)/Double.parseDouble(excRate));
		    LOGGER.info("Select Result=>"+totalLossUpdates);
		    if(totalLossUpdates>sumClaimsPaid)
		    	cashlossFlag=true;
		}catch(Exception e){
			LOGGER.debug("Exception "+e);
		}
	    LOGGER.info("PremiumDAOImpl getCashLossUpdateValidation() || Exit cashlossFlag=>"+cashlossFlag);
	    return cashlossFlag;
	}

	public String getMovementReportMaxDate(String branchCode) {
		LOGGER.info("PremiumDAOImpl getCashLossUpdateValidation() || Enter");
		String maxDate = "";
		LOGGER.info("PremiumDAOImpl getMovementReportMaxDate || Enter");
		String query = this.getQuery(DBConstants.PREMIUM_MOV_REP_MAX_DATE);
		LOGGER.info("Select Query=>"+query);
		LOGGER.info("Obj[]=>"+branchCode);
		try {
			maxDate = (String) this.mytemplate.queryForObject(query,new Object[]{branchCode},
					String.class);
		} catch (Exception e) {
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("PremiumDAOImpl getMovementReportMaxDate() || Exit maxDate"+maxDate);
		return maxDate;
	}
	public List<FaculPremiumBean> getAllocatedList(final FaculPremiumBean beanObj){
		List<FaculPremiumBean> allocateList = new ArrayList<FaculPremiumBean>();
		List<Map<String,Object>> list =premiumapi.getAllocatedList(beanObj);
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
				FaculPremiumBean tempBean = new FaculPremiumBean();
				tempBean.setSerial_no(tempMap.get("SerialNo")==null?"":tempMap.get("SerialNo").toString());
				tempBean.setAllocateddate(tempMap.get("AllocatedDate")==null?"":tempMap.get("AllocatedDate").toString());
				tempBean.setProductname(tempMap.get("ProductName")==null?"":tempMap.get("ProductName").toString());
				tempBean.setType(tempMap.get("Type")==null?"":tempMap.get("Type").toString());
				tempBean.setPayamount(tempMap.get("PayAmount")==null?"":tempMap.get("PayAmount").toString());
				tempBean.setCurrencyValue(tempMap.get("CurrencyValue")==null?"":tempMap.get("CurrencyValue").toString());
				tempBean.setAlloccurrencyid(tempMap.get("AlloccurrencyId")==null?"":tempMap.get("AlloccurrencyId").toString());
				tempBean.setAllocateType(tempMap.get("AllocateType")==null?"":tempMap.get("AllocateType").toString());
				tempBean.setStatus(tempMap.get("Status")==null?"":tempMap.get("Status").toString());
				tempBean.setSettlementType(tempMap.get("SettlementType")==null?"":tempMap.get("SettlementType").toString());
				tempBean.setPay_rec_no(tempMap.get("PayRecNo")==null?"":tempMap.get("PayRecNo").toString());
				allocateList.add(tempBean);
			}
		}
		return allocateList;
	}
	public List<Map<String,Object>> getBrokerAndCedingName(FaculPremiumBean beanObj) {
		return premiumapi.getBrokerAndCedingName(beanObj);
	}
	public List<Map<String,Object>> getaccountList(FaculPremiumBean bean) {
		LOGGER.info("PremiumDAOImpl getaccountList() || Enter");
		List<Map<String,Object>> list=null;
		try{
			String query="";
			Object args[]=null;
			args=new Object[2];
			args[0]=bean.getCurrencyId();
			args[1]=bean.getBranchCode();
			query=this.getQuery(DBConstants.ACCOUNT_LIST);
			list=this.mytemplate.queryForList(query, args);
			if (list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
					bean.setCurrencyName(tempMap.get("SHORT_NAME")==null?"":tempMap.get("SHORT_NAME").toString());
				}
			}
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("PremiumDAOImpl getaccountList() || Exit List Size"+list.size());
		return list;
	}

	public List<Map<String, Object>> currencyList(FaculPremiumBean bean) {
		return premiumapi.currencyList(bean);
	}
	public void bankAddress(FaculPremiumBean bean) {
	LOGGER.info("PremiumDAOImpl bankAddress() || Enter");
	List<Map<String,Object>> list=null;
		try{
			String query="";
			Object args[]=null;
			args=new Object[2];
			args[0]=bean.getBranchCode();
			args[1]=bean.getBancAccountNo();
			query=this.getQuery(DBConstants.BANK_ADDRESS_LIST);
			list=this.mytemplate.queryForList(query, args);
			if (list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
					bean.setBankAddress(tempMap.get("BANK_ADDRESS")==null?"":tempMap.get("BANK_ADDRESS").toString());
					bean.setBancAccountNo(tempMap.get("BANK_AC_NO")==null?"":tempMap.get("BANK_AC_NO").toString());
				}
			}
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("PremiumDAOImpl bankAddress() || Exit List Size"+list.size());
	}
	public String GetPreviousPremium(FaculPremiumBean bean) {
		String premium="";
		premium=premiumapi.GetPreviousPremium(bean.getContNo());
		return premium;
	}

	public String GetContractPremium(FaculPremiumBean bean) {
		String premium="";
		premium = premiumapi.GetContractPremium(bean.getContNo(),bean.getDepartmentId(),bean.getBranchCode());
		return premium;
	}
	public List<FaculPremiumBean> getCassLossCredit(FaculPremiumBean bean, String claimPayNo) {
		List<FaculPremiumBean> cashLossList = new ArrayList<FaculPremiumBean>();
		List<Map<String,Object>> list = premiumapi.getCassLossCredit(bean, claimPayNo);
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
		    Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
		    FaculPremiumBean tempBean = new FaculPremiumBean();
			tempBean.setContNo(tempMap.get("ContNo")==null?"":tempMap.get("ContNo").toString());
			tempBean.setSerial_no(tempMap.get("SerialNo")==null?"":tempMap.get("SerialNo").toString());
			tempBean.setPaidDate(tempMap.get("PaidDate")==null?"":tempMap.get("PaidDate").toString());
			tempBean.setClaimNumber(tempMap.get("ClaimNumber")==null?"":tempMap.get("ClaimNumber").toString());
			tempBean.setClaimPaymentNo(tempMap.get("ClaimPaymentNo")==null?"":tempMap.get("ClaimPaymentNo").toString());
			tempBean.setPayamount(tempMap.get("PayAmount")==null?"":tempMap.get("PayAmount").toString());
			tempBean.setExcessRatePercent(tempMap.get("ExcessRatePercent")==null?"":tempMap.get("ExcessRatePercent").toString());
			tempBean.setCurrencyValue(tempMap.get("CurrencyValue")==null?"":tempMap.get("CurrencyValue").toString());
			tempBean.setCurrencyId(tempMap.get("CurrencyId")==null?"":tempMap.get("CurrencyId").toString());
			tempBean.setCurrencyValueName(tempMap.get("CurrencyValueName")==null?"":tempMap.get("CurrencyValueName").toString());
			tempBean.setCurrencyIdName(tempMap.get("CurrencyIdName")==null?"":tempMap.get("CurrencyIdName").toString());
			tempBean.setStatus(tempMap.get("Status")==null?"":tempMap.get("Status").toString());
			tempBean.setCreditAmountCLC(tempMap.get("CreditAmountCLC")==null?"":tempMap.get("CreditAmountCLC").toString());
			tempBean.setCreditAmountCLD(tempMap.get("CreditAmountCLD")==null?"":tempMap.get("CreditAmountCLD").toString());
			tempBean.setCLCsettlementRate(tempMap.get("CLCsettlementRate")==null?"":tempMap.get("CLCsettlementRate").toString());
			tempBean.setCheck(tempMap.get("Check")==null?"":tempMap.get("Check").toString());
			tempBean.setCreditAmountCLCTemp(tempMap.get("CreditAmountCLCTemp")==null?"":tempMap.get("CreditAmountCLCTemp").toString());
			tempBean.setCreditAmountCLDTemp(tempMap.get("CreditAmountCLDTemp")==null?"":tempMap.get("CreditAmountCLDTemp").toString());
			tempBean.setCLCsettlementRateTemp(tempMap.get("CLCsettlementRateTemp")==null?"":tempMap.get("CLCsettlementRateTemp").toString());
			cashLossList.add(tempBean);
			}
		}
		return cashLossList;
	}
	public void InsertCashLossCredit(FaculPremiumBean bean) {
		try{
		
			LOGGER.info("InsertCashLossCredit || Enter");
			if(StringUtils.isNotBlank(bean.getClaimPaymentNo())){
			String[] ClaimPayNo=bean.getClaimPaymentNo().split(",");
			String[] creditAmountCLC=bean.getCreditAmountCLC().split(",");
			String[] creditAmountCLD=bean.getCreditAmountCLD().split(",");
			String[] CLCsettlementRate=bean.getCLCsettlementRate().split(",");
			String[] cldAmount=bean.getCLDAmount().split(",");
			for(int i=0;i<ClaimPayNo.length;i++) {
				if(StringUtils.isNotBlank(ClaimPayNo[i])){
			List<FaculPremiumBean>cashLossList=getCassLossCredit(bean,ClaimPayNo[i]);
			FaculPremiumBean form= cashLossList.get(0);
			if(ClaimPayNo[i].contains(form.getClaimPaymentNo())) {
			Object[] obj=new Object[17];
			obj[0]=bean.getBranchCode();
			obj[1]=form.getContNo();
			obj[2]=form.getClaimNumber();
			obj[3]=form.getClaimPaymentNo();
			obj[4]=form.getCurrencyId();
			obj[5]=creditAmountCLC[i];
			obj[6]=form.getCurrencyValue();
			obj[7]=CLCsettlementRate[i];
			obj[8]=creditAmountCLD[i];
			obj[9]=bean.getTransactionNo();
			obj[10]=bean.getTransaction();
			obj[11]=bean.getBranchCode();
			obj[12] = bean.getRequestNo();
			if("submit".equalsIgnoreCase(bean.getButtonStatus())){
				obj[13] = "A";
			}else{
				obj[13] = "P";
			}
			obj[14] = bean.getProposal_No();
			obj[15] = cldAmount[i];
			obj[16] = bean.getCashlossType();
			String query=getQuery(DBConstants.INSERT_CASS_LOSSCREDIT);
			LOGGER.info("Insert Query==>"+query);
			LOGGER.info("Obj==>"+StringUtils.join(obj,","));
		 	this.mytemplate.update(query, obj);
		 	if("submit".equalsIgnoreCase(bean.getButtonStatus())){
			 	String sql=getQuery(DBConstants.UPDATE_CLAIM_PAYMENT);
			 	Object[] arg=new Object[]{form.getContNo(),bean.getBranchCode(),bean.getRequestNo(),"A",form.getClaimNumber(),form.getClaimPaymentNo(),form.getContNo(),form.getClaimNumber(),form.getClaimPaymentNo()};
			 	LOGGER.info("Update Query==>"+sql);
			 	LOGGER.info("Obj==>"+StringUtils.join(arg,","));
			 	this.mytemplate.update(sql,arg );
		 		}
			}
			}
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.info("InsertCashLossCredit || Enter");
	}
	public String getDepartmentNo(FaculPremiumBean bean) {
		String deptNo="";
		deptNo = premiumapi.getDepartmentNo(bean);
		return deptNo;
	}
	public List<Map<String,Object>> getConstantPeriodDropDown(String CategoryId, String ContractNo,FaculPremiumBean bean) {
		return premiumapi.getConstantPeriodDropDown(CategoryId, ContractNo,bean.getSectionNo(),bean.getProposal_No());
	}
    public List<Map<String, Object>> SlideCommission(FaculPremiumBean bean,String countryId) {
    LOGGER.info("SlideCommission() || Enter");
    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    String cur = "";
    List<String> exhRate = new ArrayList<String>();
    List<String> total = new ArrayList<String>();
    List<String> curId = new ArrayList<String>();
		List<String> curName = new ArrayList<String>();
    String currency ="";
        try {
			Object args[] = null;
			String query = "";
			if ("one".equalsIgnoreCase(bean.getSlideScenario())) {
				query = getQuery("GET_CURRENY_DET");
				args = new Object[7];
				args[0] = bean.getContNo();
				args[1] = bean.getTransaction();
				args[2] = bean.getBranchCode();
				args[3] = bean.getContNo();
				args[4] = bean.getTransaction();
				args[5] = bean.getBranchCode();
				args[6] = bean.getBranchCode();
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				list = this.mytemplate.queryForList(query, args);
			} else if ("two".equalsIgnoreCase(bean.getSlideScenario()) || "three".equalsIgnoreCase(bean.getSlideScenario())) {
				query = getQuery("GET_CURRENY_DET1");
				args = new Object[9];
				args[0] = bean.getContNo();
				args[1] = bean.getDepartmentId();
				args[2] = bean.getTransaction();
				args[3] = bean.getBranchCode();
				args[4] = bean.getContNo();
				args[5] = bean.getDepartmentId();
				args[6] = bean.getTransaction();
				args[7] = bean.getBranchCode();
				args[8] = bean.getBranchCode();
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				list = this.mytemplate.queryForList(query, args);
			}
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					currency = map.get("SHORT_NAME").toString() == null ? "" : map.get("SHORT_NAME").toString();
					cur = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
					if (StringUtils.isNotBlank(bean.getMode()) && ("slide".equalsIgnoreCase(bean.getMode()) || "loss".equalsIgnoreCase(bean.getMode()) ||"profit".equalsIgnoreCase(bean.getMode()))|| "lossRage".equalsIgnoreCase(bean.getMode())|| "profitRage".equalsIgnoreCase(bean.getMode()) || "slideRage".equalsIgnoreCase(bean.getMode())) {
						String rate = dropDownController.GetExchangeRate(cur, bean.getTransaction(), countryId, bean.getBranchCode());
						String gridexchange= dropDownController.GetExchangeRate(bean.getCurrencyId(), bean.getTransaction(), countryId, bean.getBranchCode());
						double exchange = Double.parseDouble(gridexchange) / Double.parseDouble(rate);
						exhRate.add(DropDownControllor.formattereight(Double.toString(exchange)));
					}
					curId.add(cur);
					curName.add(currency);
				}
			}
			if (StringUtils.isNotBlank(bean.getMode()) && ("slide".equalsIgnoreCase(bean.getMode()) ||  "slideView".equalsIgnoreCase(bean.getMode()) || ("profit".equalsIgnoreCase(bean.getMode()) ||  "profitView".equalsIgnoreCase(bean.getMode()) || ("loss".equalsIgnoreCase(bean.getMode()) ||  "lossView".equalsIgnoreCase(bean.getMode()))))  && (bean.getPremiumOC()==null || bean.getPortfolioPremium()==null ||bean.getLossPremiumOC()==null)) {
				if(StringUtils.isBlank(bean.getFlag())){
				bean.setExchRatePrem(exhRate);
				}
			if (list.size() == 1) {
				if (bean.getCurrencyName().equalsIgnoreCase(currency.trim())) {
					bean.setGridShow("N");
				} else {
					bean.setGridShow("Y");
				}
			} else if (list.size() > 1) {
				bean.setGridShow("Y");
			}
		}
			bean.setCurrencyShortName(curName);
			bean.setPreCurrencylist(curId);

        }catch(Exception e){
            LOGGER.debug("Exception @ {" + e + "}");
            e.printStackTrace();
        }
        LOGGER.info("SlideCommission() || Exit");
        return list;
    }

	public String getCurrencyShortName(FaculPremiumBean bean) {
		LOGGER.info("getCurrencyShortName() || Enter");
		String res = "";
		try{
				String query = getQuery("GET_SHRT_NAME");
				Object args[] = new Object[2];
				args[0] = bean.getCurrencyId();
				args[1] = bean.getBranchCode();
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				res = this.mytemplate.queryForObject(query, args, String.class);

		}catch(Exception e){
			LOGGER.debug("Exception @ {" + e + "}");
		}
		LOGGER.info("getCurrencyShortName() || Exit");
		return res;
	}

	public void GetFieldValues(FaculPremiumBean bean) {
		LOGGER.info("GetFieldValues() || Enter");
		Object args[]=null;
		Object prargs[]=null;
		Object orargs[]=null;
		List<Map<String,Object>> comList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> premList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> OSList = new ArrayList<Map<String,Object>>();
		List<String> claimout =new ArrayList<String>();
		List<String> claimRatio =new ArrayList<String>();
		List<String> total = new ArrayList<String>();
		List<String> pretotal = new ArrayList<String>();
		List<String> paiddate = new ArrayList<String>();
		List<String> unearned = new ArrayList<String>();
		List<String> slideCom = new ArrayList<String>();
		List<String> slideAdj = new ArrayList<String>();
		List<String> claimamt =new ArrayList<String>();
		List<String> premiumamt =new ArrayList<String>();
		
		List<Map<String,Object>>finalTest=new ArrayList<Map<String,Object>>();
		double val =0;
		double commission =0;
		double paid =0;
		String paidamount = "";
		String premAmt ="";
		String curId=""   ;
		String precurId="";  
		String query ="";
		String prquery ="";
		String quotaShare = "";
		String comqs = "";
		String claimoutSt = "";
		String slideres="";
		String OSQuery="";
		double premiumAmountt=0;
		
		//DecimalFormat myFormatter = new DecimalFormat("#####.##");
		try{
			if (bean.getPreCurrencylist().size() > 0) {
				 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
				 	total.add("0.00") ;
					 paiddate.add("0.00");
				 	 pretotal.add("0.00");
					 claimout.add("0.00");
					 claimamt.add("0.00");
					 premiumamt.add("0.00");
					 unearned.add("0.00");
				 }
			}
			if("one".equalsIgnoreCase(bean.getSlideScenario())) {
				query = getQuery("GET_PAID_AMOUT");
				args=new Object[4];
				args[0] = bean.getContNo();
				args[1] = bean.getBranchCode();
				args[2] = bean.getTransaction();
				args[3] = bean.getProductId();

				prquery = getQuery("GET_PREMIUM_DETAILS");
				prargs=new Object[8];
				prargs[0] = bean.getTransaction();
				prargs[1] = bean.getContNo();
				prargs[2] = bean.getBranchCode();
				prargs[3] = bean.getContNo();
				prargs[4] = bean.getBranchCode();
				prargs[5] = bean.getContNo();
				prargs[6] = bean.getTransaction();
				prargs[7] = bean.getProductId();
				
				OSQuery = getQuery("GET_PREMIUM_CLAIMS_OUT_VAL");
				orargs=new Object[3];
				orargs[0] = bean.getTransaction();
				orargs[1] = bean.getContNo();
				orargs[2] = bean.getBranchCode();
			}
			else if("two".equalsIgnoreCase(bean.getSlideScenario()) || "three".equalsIgnoreCase(bean.getSlideScenario())) {
				 query =  getQuery("GET_PAID_AMOUT1");
				args=new Object[5];
				args[0] = bean.getContNo();
				args[1] = bean.getBranchCode();
				args[2] = bean.getTransaction();
				args[3] = bean.getDepartmentId();
				args[4] = bean.getProductId();


				prquery = getQuery("GET_PREMIUM_DETAILS1");
				prargs=new Object[9];
				prargs[0] = bean.getTransaction();
				prargs[1] = bean.getContNo();
				prargs[2] = bean.getBranchCode();
				prargs[3] = bean.getContNo();
				prargs[4] = bean.getBranchCode();
				prargs[5] = bean.getContNo();
				prargs[6] = bean.getTransaction();
				prargs[7] = bean.getDepartmentId();
				prargs[8] = bean.getProductId();
				
				OSQuery = getQuery("GET_PREMIUM_CLAIMS_OUT_VAL1");
				orargs=new Object[3];
				orargs[0] = bean.getTransaction();
				orargs[1] = bean.getContNo();
				orargs[2] = bean.getBranchCode();
				
			}
			/*OSQuery = getQuery("GET_PREMIUM_CLAIMS_OUT_VAL");
			orargs=new Object[11];
			orargs[0] = bean.getBranchCode();
			orargs[1] = bean.getContNo();
			orargs[2] = bean.getStatementDate();
			orargs[3] = bean.getStatementDate();
			orargs[4] = bean.getTransaction();
			orargs[5] = bean.getDepartmentId();
			orargs[6] = bean.getBranchCode();
			orargs[7] = bean.getContNo();
			orargs[8] = bean.getStatementDate();
			orargs[9] = bean.getStatementDate();
			orargs[10] = bean.getTransaction();*/
			
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(args, ","));
			LOGGER.info("Query=>"+prquery);
			LOGGER.info("Args=>"+StringUtils.join(prargs, ","));
			LOGGER.info("Query=>"+OSQuery);
			LOGGER.info("Args=>"+StringUtils.join(orargs, ","));
			comList = this.mytemplate.queryForList(query,args);
			premList = this.mytemplate.queryForList(prquery,prargs);
			OSList = this.mytemplate.queryForList(OSQuery,orargs);
			
			if(premList.size()>0){
				for (int i = 0; i < premList.size(); i++) {
						Map<String, Object> map = premList.get(i);
						finalTest.add(premList.get(i));
						comqs =map.get("COMMISSION_PAID_OC").toString()==null?"0.00":map.get("COMMISSION_PAID_OC").toString();
						premAmt = map.get("CLAIM_PAID_AMOUNT").toString()==null?"0.00":map.get("CLAIM_PAID_AMOUNT").toString();
						quotaShare = map.get("PremiumOC").toString()==null?"0.00":map.get("PremiumOC").toString();
						quotaShare = quotaShare.replaceAll(",", "");
						comqs = comqs.replaceAll(",", "");
						premAmt = premAmt.replaceAll(",", "");
						curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
						if (precurId!=null && precurId.equals(curId)) {
							val+=Double.parseDouble(quotaShare);
							commission += Double.parseDouble(comqs);
							premiumAmountt += Double.parseDouble(premAmt);
						}
						else{
							val=0;
							commission=0;
							premiumAmountt=0;
							val+=Double.parseDouble(quotaShare);
							commission += Double.parseDouble(comqs);
							premiumAmountt += Double.parseDouble(premAmt);
						}
						if (bean.getPreCurrencylist().size() > 0) {
						 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
							 	precurId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
							 	
								if (precurId.equals(bean.getPreCurrencylist().get(j))) {
									 paiddate.set(j,DropDownControllor.formatter(Double.toString(commission)));
									 pretotal.set(j,DropDownControllor.formatter(Double.toString(val)));
									 premiumamt.set(j,DropDownControllor.formatter(Double.toString(premiumAmountt)));
									 total.set(j,DropDownControllor.formatter(Double.toString(premiumAmountt)));
									 
								}
							}
						}
					}
				}
			if(OSList.size()>0){
				commission=0;
				
				for (int i = 0; i < OSList.size(); i++) {
						Map<String, Object> map = OSList.get(i);
						claimoutSt = map.get("OSCLAIM_LOSSUPDATE_OC").toString() == null ? "0.00" : map.get("OSCLAIM_LOSSUPDATE_OC").toString();
						curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
						
						claimoutSt = claimoutSt.replaceAll(",", "");
						if (precurId!=null && precurId.equals(curId)) {
								commission += Double.parseDouble(claimoutSt) ;
						}
						else{
							commission=0;
							commission += Double.parseDouble(claimoutSt);
						}
						if (bean.getPreCurrencylist().size() > 0) {
							
							 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
								 	precurId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
									if (precurId.equals(bean.getPreCurrencylist().get(j))) {
										claimout.set(j,DropDownControllor.formatter(Double.toString(commission)));
									}
								}
							}
						
				}
				
			}
			
			if(comList.size()>0) {
				for (int i = 0; i < comList.size(); i++) {
					
					Map<String, Object> map = comList.get(i);
					finalTest.add(comList.get(i));
					curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
					
					if (precurId!=null && precurId.equals(curId)) {
						paidamount = map.get("PAID_AMOUNT_OC").toString() == null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
						paid += Double.parseDouble(paidamount.replaceAll(",",""));
					}else{
						paid =0;
						paidamount = map.get("PAID_AMOUNT_OC").toString() == null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
						paid += Double.parseDouble(paidamount.replaceAll(",",""));
					}
					precurId = curId;
					if (bean.getPreCurrencylist().size() > 0) {
						for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
							curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
							if (curId.equals(bean.getPreCurrencylist().get(j))) {
								paid = paid+Double.parseDouble(premiumamt.get(j).replaceAll(",",""));
								total.set(j,DropDownControllor.formatter(Double.toString(paid)));
							}
						}
					}
				}
			}
			else{
				if ( bean.getPreCurrencylist().size() > 0) {
					for (int j = 0; j <  bean.getPreCurrencylist().size(); j++) {
						total.set(j,DropDownControllor.formatter(premiumamt.get(j).replaceAll(",","")));
					}
				}
			}
			for (int i = 0; i < bean.getPreCurrencylist().size(); i++) {
				double ans = 0.00;
				double slideresult = 0.00;
				String prtotal = pretotal.get(i).replaceAll(",", "");
				String claimou = claimout.get(i).replaceAll(",", "");
				String unerned = unearned.get(i).replaceAll(",", "");
				String tot = total.get(i).replaceAll(",", "");
				if (Double.parseDouble(prtotal) > 0) {
					ans =( (Double.parseDouble(tot) + Double.parseDouble(claimou)) / (Double.parseDouble(prtotal)-Double.parseDouble(unerned))) * 100;
					ans=Double.parseDouble(dropDownController.GetTwoDecimalFormate(ans));
				}
				claimRatio.add(DropDownControllor.formatter(Double.toString(ans)));
			if(ans>0) {
				slideres = getSlideValue(bean, ans);
				slideres = slideres.replaceAll(",", "");
				if (Double.parseDouble(prtotal) > 0) {
					slideresult = (Double.parseDouble(prtotal) * Double.parseDouble(slideres)) / 100;
				}
			}
				slideCom.add(DropDownControllor.formatter(Double.toString(slideresult)));
				String sliderest = slideCom.get(i).replaceAll(",", "");
				String paiddat = paiddate.get(i).replaceAll(",", "");
				double slideans = Double.parseDouble(sliderest) - Double.parseDouble(paiddat);
				slideAdj.add(DropDownControllor.formatter(Double.toString(slideans)));
			}
			bean.setClaimRatioOC(claimRatio);
			bean.setPremiumOC(pretotal);
			bean.setClaimPaidOC(total);
			bean.setSlideScaleCommOC(slideCom);
			bean.setSlideScaleAdjOC(slideAdj);
			bean.setClaimOutStandingOC(claimout);
			bean.setCommPaidOCTillDate(paiddate);
			bean.setUnearnpremiumOC(unearned);
			bean.setPremiumFinallist2(finalTest);
			
			if(StringUtils.isBlank(bean.getFlag())){
				bean.setManualPremiumOC(pretotal);
				bean.setManualunearnpremiumOC(unearned);
				bean.setManualclaimPaidOC(total);
				bean.setManualclaimOutStandingOC(claimout);
				bean.setManualclaimRatioOC(claimRatio);
			}
		}catch(Exception e){
			LOGGER.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		LOGGER.info("GetFieldValues() || Exit");
	}

	public String getSlideValue(FaculPremiumBean bean ,double ans) {
		LOGGER.info("getSlideValue() || Enter");
		String slidequery = "";
		String slideres ="";
		Object slideArgs[] = null;
	try{
		if("ALL".equalsIgnoreCase(bean.getDepartmentId())){
			slidequery = getQuery("GET_SILDE_VALUE") ;
			slideArgs = new Object[6];
			slideArgs[0] = bean.getContNo();
			slideArgs[1] = bean.getProductId();
			slideArgs[2] = bean.getBranchCode();
			slideArgs[3] = ans;
			slideArgs[4] = "SSC";
			slideArgs[5] = bean.getProposal_No();
		}else{
			slidequery = getQuery("GET_SILDE_VALUE_DEPT") ;
			slideArgs = new Object[7];
			slideArgs[0] = bean.getContNo();
			slideArgs[1] = bean.getProductId();
			slideArgs[2] = bean.getBranchCode();
			slideArgs[3] = ans;
			slideArgs[4] = "SSC";
			slideArgs[5] = bean.getDepartmentId();
			slideArgs[6] = bean.getProposal_No();
			}
		LOGGER.info("Query=>"+slidequery);
		LOGGER.info("Args=>"+StringUtils.join(slideArgs, ","));
		slideres = this.mytemplate.queryForObject(slidequery, slideArgs, String.class);
	}
	catch(Exception e){
		LOGGER.debug("Exception @ {" + e + "}");
		e.printStackTrace();
	}
	LOGGER.info("getSlideValue() || Exit");
	return slideres;
	}

	public void insertSlideCommission(FaculPremiumBean bean) { /*for statistic button creation its removed insert don't want
		LOGGER.info("insertSlideCommission() || Enter");
		try{
			//String qry=getQuery("SLIDE_COMM_DELETE");SLIDE_MAX_AMEND_ID
			String qry=getQuery("SLIDE_MAX_AMEND_ID");
			Object arg[]=new Object[6];
			arg[0] = bean.getTransactionNo();
			arg[1] = bean.getTransaction();
			arg[2] = bean.getBranchCode();
			arg[3] = bean.getProductId();
			arg[4] = bean.getContNo();
			arg[5] = bean.getDepartmentId();
			LOGGER.info("Query=>"+qry);
			LOGGER.info("Args=>"+StringUtils.join(arg, ","));
			String amendId = this.mytemplate.queryForObject(qry,arg,String.class);
			
			if(bean.getPremiumOC()!=null){
				String query=getQuery("SLIDE_COMM_INSERT");
				Object args[]=new Object[17];
			for(int i=0;i<bean.getExchRatePrem().size();i++){
				args[0]=bean.getPremiumOC().get(i).replaceAll(",", "");
				args[1]=bean.getClaimPaidOC().get(i).replaceAll(",", "");
				args[2]=bean.getClaimOutStandingOC().get(i).replaceAll(",", "");
				args[3]=bean.getClaimRatioOC().get(i).replaceAll(",", "");
				args[4]=bean.getSlideScaleCommOC().get(i).replaceAll(",", "");
				args[5]=bean.getCommPaidOCTillDate().get(i).replaceAll(",", "");
				args[6]=bean.getSlideScaleAdjOC().get(i).replaceAll(",", "");
				args[7] = bean.getPreCurrencylist().get(i).replaceAll(",","");
				args[8]=bean.getExchRatePrem().get(i).replaceAll(",", "");
				args[9]=bean.getContNo();
				args[10]=bean.getBranchCode();
				args[11]=bean.getDepartmentId();
				args[12]=StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
				args[13]=StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
				args[14] = bean.getLoginId();
				args[15] = bean.getProductId();
				args[16] = amendId;
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);
			}
			if(bean.getConPremiumOC()!=null&&StringUtils.isNotBlank(bean.getConPremiumOC()) && !"null".equalsIgnoreCase(bean.getConPremiumOC())){
				args[0]=bean.getConPremiumOC().replaceAll(",", "");
				args[1]=bean.getConClaimPaidOC().replaceAll(",", "");
				args[2]=bean.getConClaimOutStandingOC().replaceAll(",", "");
				args[3]=bean.getConClaimRatioOC().replaceAll(",", "");
				args[4]=bean.getConSlideScaleCommOC().replaceAll(",", "");
				args[5]=bean.getConCommPaidOC().replaceAll(",", "");
				args[6]=bean.getConSlideScaleAdjOC().replaceAll(",", "");
				args[7]="";//bean.getCurrency();
				args[8]="";//bean.getExchRate();
				args[9]=bean.getContNo();
				args[10]=bean.getBranchCode();
				args[11]=bean.getDepartmentId();
				args[12]=StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
				args[13]=StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
				args[14] = bean.getLoginId();
				args[15] = bean.getProductId();
				args[16] = amendId;
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);
			}
			}
			else if(bean.getLossPremiumOC()!=null){
				String query=getQuery("LOSS_PART_INSERT");
				Object args[]=new Object[19];
			for(int i=0;i<bean.getExchRatePrem().size();i++){
				args[0]=bean.getLossPremiumOC().get(i).replaceAll(",", "");
				args[1]=bean.getLossClaimPaidOC().get(i).replaceAll(",", "");
				args[2]=bean.getLossClaimOutStandingOC().get(i).replaceAll(",", "");
				args[3]=bean.getLossClaimRatioOC().get(i).replaceAll(",", "");
				args[4]=bean.getLossPartOC().get(i).replaceAll(",", "");
				args[5]=bean.getLossPartRefTillDate().get(i).replaceAll(",", "");
				args[6]=bean.getLossPartRefAdjOC().get(i).replaceAll(",", "");
				args[7] = bean.getPreCurrencylist().get(i).replaceAll(",","");
				args[8]=bean.getExchRatePrem().get(i).replaceAll(",", "");
				args[9]=bean.getContNo();
				args[10]=bean.getBranchCode();
				args[11]=bean.getDepartmentId();
				args[12]=StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
				args[13]=StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
				args[14] = bean.getLoginId();
				args[15] = bean.getProductId();
				args[16] = amendId;
				args[17] =bean.getClaimPaidRatioOC().get(i).replaceAll(",", "");
				args[18] =bean.getAdjToOutLoss().replaceAll(",", "");
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);
			}
			if(bean.getConlossPremiumOC()!=null&&StringUtils.isNotBlank(bean.getConlossPremiumOC())){
				args[0]=bean.getConlossPremiumOC().replaceAll(",", "");
				args[1]=bean.getConlossClaimPaidOC().replaceAll(",", "");
				args[2]=bean.getConlossClaimOutStandingOC().replaceAll(",", "");
				args[3]=bean.getConlossClaimRatioOC().replaceAll(",", "");
				args[4]=bean.getConlossOC().replaceAll(",", "");
				args[5]=bean.getConlossTillOC().replaceAll(",", "");
				args[6]=bean.getConlossAdjOC().replaceAll(",", "");
				args[7]="";//bean.getCurrency();
				args[8]="";//bean.getExchRate();
				args[9]=bean.getContNo();
				args[10]=bean.getBranchCode();
				args[11]=bean.getDepartmentId();
				args[12]=StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
				args[13]=StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
				args[14] = bean.getLoginId();
				args[15] = bean.getProductId();
				args[16] = amendId;
				args[17] =bean.getConClaimPaidRatioOC().replaceAll(",", "");
				args[18] =bean.getAdjToOutLoss().replaceAll(",", "");
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);
			}
			}
			else if(bean.getPortfolioPremium()!=null){
				String query=getQuery("PREMIUM_SLIDE_SCALE_INSERT");
				Object args[]=new Object[24];
				for(int i=0;i<bean.getExchRatePrem().size();i++){
				args[0] = bean.getPortfolioPremium().get(i).replaceAll(",", "");
				args[1] = bean.getAccCostBrokerage().get(i).replaceAll(",", "");
				args[2] = bean.getProClaimPaidOC().get(i).replaceAll(",", "");
				args[3] = bean.getProClaimOutStandingOC().get(i).replaceAll(",", "");
				args[4] = bean.getManagExp().get(i).replaceAll(",", "");
				args[5] = bean.getProfitLoss().get(i).replaceAll(",", "");
				args[6] = bean.getOtherAdj().get(i).replaceAll(",", "");
				args[7] = bean.getTreatyAdj().get(i).replaceAll(",", "");
				args[8] = bean.getProfitCommAdj().get(i).replaceAll(",", "");
				args[9] = bean.getProfitRatio().get(i).replaceAll(",", "");
				args[10] = bean.getLossRatio().get(i).replaceAll(",", "");
				args[11] = bean.getProfitCommission().get(i).replaceAll(",", "");
				args[12] = bean.getSuperProfitComm().get(i).replaceAll(",", "");
				args[13] = bean.getPayedTillDate().get(i).replaceAll(",", "");
				//args[14] = bean.getProfitCommAdj().get(i).replaceAll(",", "");
				args[14] = bean.getPreCurrencylist().get(i).replaceAll(",", "");
				args[15] = bean.getContNo();
				args[16] = bean.getBranchCode();
				args[17] =bean.getDepartmentId();
				args[18] = StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
				args[19] = StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
				args[20] =bean.getLoginId();
				args[21] =bean.getExchRatePrem().get(i).replaceAll(",", "");
				args[22]=bean.getProductId();
				args[23]=amendId;
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);
				}
				if(null!= bean.getConPortfolioPremium()&&StringUtils.isNotBlank(bean.getConPortfolioPremium())&&!"null".equalsIgnoreCase(bean.getConPortfolioPremium())){
				args[0] = bean.getConPortfolioPremium().replaceAll(",", "");
				args[1] = bean.getConAccCostBrokerage().replaceAll(",", "");
				args[2] = bean.getConProClaimPaidOC().replaceAll(",", "");
				args[3] = bean.getConProClaimOutStandingOC().replaceAll(",", "");
				args[4] = bean.getConManagExp().replaceAll(",", "");
				args[5] = bean.getConProfitLoss().replaceAll(",", "");
				args[6] = bean.getConOtherAdj().replaceAll(",", "");
				args[7] = bean.getConTreatyAdj().replaceAll(",", "");
				args[8] = bean.getConProfitCommAdj().replaceAll(",", "");
				args[9] = bean.getConProfitRatio().replaceAll(",", "");
				args[10] = bean.getConLossRatio().replaceAll(",", "");
				args[11] = bean.getConProfitCommission().replaceAll(",", "");
				args[12] = bean.getConSuperProfitComm().replaceAll(",", "");
				args[13] = bean.getConPayedTillDate().replaceAll(",", "");
				//args[14] = bean.getConProfitCommAdj().replaceAll(",", "");
				args[14] = "";
				args[15] = bean.getContNo();
				args[16] = bean.getBranchCode();
				args[17] =bean.getDepartmentId();
				args[18] = StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
				args[19] = StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
				args[20] =bean.getLoginId();
				args[21] ="";
				args[22]=bean.getProductId();
				args[23]=amendId;
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);
				}
			}
		}catch(Exception e){
			LOGGER.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		LOGGER.info("insertSlideCommission() || Exit");*/

	}

	/*public void PremSlideEditFieldValue(FaculPremiumBean bean,String type) {
		LOGGER.info("PremSlideEditFieldValue() || Enter");
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> slide=new ArrayList<Map<String,Object>>();
		String exchangeRate = "0.00";
		List<String> preOc=new ArrayList<String>();
		List<String> claimpaidOc=new ArrayList<String>();
		List<String> claimratioOc=new ArrayList<String>();
		List<String> claimpaidratioOc=new ArrayList<String>();
		List<String> claimoutOc=new ArrayList<String>();
		List<String> slideOc=new ArrayList<String>();
		List<String> slideadjOc=new ArrayList<String>();
		List<String> compaidOc=new ArrayList<String>();
		List<String> ExOc=new ArrayList<String>();
		
		List<String> acqCost=new ArrayList<String>();
		List<String> manExo=new ArrayList<String>();
		List<String> profitLoss=new ArrayList<String>();
		List<String> otherAdj=new ArrayList<String>();
		List<String> treatyRes=new ArrayList<String>();
		List<String> profitRatio=new ArrayList<String>();
		List<String> lossRatio=new ArrayList<String>();
		List<String> profitComm=new ArrayList<String>();
		List<String> supProfitComm=new ArrayList<String>();
		List<String> profitCommAdj=new ArrayList<String>();
		
		List<String> currId=new ArrayList<String>();
		List<String> currName=new ArrayList<String>();
		List<String> exhRate=new ArrayList<String>();
		try {
			String query = getQuery("SELECT_EDIT_FIELD_VALUES");
			Object args[] = new Object[6];
			args[0] = bean.getTransactionNo();
			args[1] = bean.getTransaction();
			args[2] = bean.getBranchCode();
			args[3] = bean.getProductId();
			args[4] = bean.getContNo();
			args[5] = bean.getDepartmentId();
			list = this.mytemplate.queryForList(query, args);
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(args, ","));
			if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				exchangeRate = map.get("EXCHANGE_RATE")==null ? "0.00" : map.get("EXCHANGE_RATE").toString();
				currId.add(map.get("CURRENCY_ID") == null ? "0.00" : map.get("CURRENCY_ID").toString());
				currName.add(map.get("SHORT_NAME") == null ? "0.00" : map.get("SHORT_NAME").toString());
				if(exchangeRate!="0.00"){
				ExOc.add(exchangeRate);
				slide.add(list.get(i));
				}
				if("slide".equalsIgnoreCase(type)){
				if(exchangeRate=="0.00"){
					bean.setGridShow("Y");
					bean.setConPremiumOC(map.get("PREM_OC")==null ? "0.00" : DropDownControllor.formatter(map.get("PREM_OC").toString()));
					bean.setConClaimPaidOC(  map.get("CLAIM_PAID_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_OC").toString()));
					bean.setConClaimRatioOC(map.get("CLAIM_RATIO_OC")== null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_RATIO_OC").toString()));
					bean.setConClaimOutStandingOC(map.get("CLAIM_OS_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_OS_OC").toString()));
					bean.setConSlideScaleCommOC(map.get("SLIDE_SCLE_COM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("SLIDE_SCLE_COM_OC").toString()));
					bean.setConSlideScaleAdjOC(map.get("SLIDE_SCLE_ADJ_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("SLIDE_SCLE_ADJ_OC").toString()));
					bean.setConCommPaidOC(map.get("COMM_PAID_TIL_DATE_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("COMM_PAID_TIL_DATE_OC").toString()));
					bean.setConClaimPaidRatioOC(map.get("CLAIM_PAID_RATIO_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_RATIO_OC").toString()));
				}else{
				preOc.add(map.get("PREM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("PREM_OC").toString()));
				claimpaidOc.add(map.get("CLAIM_PAID_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_OC").toString()));
				claimratioOc.add(map.get("CLAIM_RATIO_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_RATIO_OC").toString()));
				claimratioOc.add(map.get("CLAIM_RATIO_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_RATIO_OC").toString()));
				claimoutOc.add(map.get("CLAIM_OS_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_OS_OC").toString()));
				slideOc.add(map.get("SLIDE_SCLE_COM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("SLIDE_SCLE_COM_OC").toString()));
				slideadjOc.add(map.get("SLIDE_SCLE_ADJ_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("SLIDE_SCLE_ADJ_OC").toString()));
				
				
				}
				}
				else if("loss".equalsIgnoreCase(type)){
					if(exchangeRate=="0.00"){
						bean.setGridShow("Y");
						bean.setConlossPremiumOC(map.get("PREM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("PREM_OC").toString()));
						bean.setConlossClaimPaidOC(  map.get("CLAIM_PAID_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_OC").toString()));
						bean.setConlossClaimRatioOC(map.get("CLAIM_RATIO_OC")== null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_RATIO_OC").toString()));
						bean.setConlossClaimOutStandingOC(map.get("CLAIM_OS_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_OS_OC").toString()));
						bean.setConlossOC(map.get("SLIDE_SCLE_COM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("SLIDE_SCLE_COM_OC").toString()));
						bean.setConlossAdjOC(map.get("SLIDE_SCLE_ADJ_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("SLIDE_SCLE_ADJ_OC").toString()));
						bean.setConlossTillOC(map.get("COMM_PAID_TIL_DATE_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("COMM_PAID_TIL_DATE_OC").toString()));
					}else{
					preOc.add(map.get("PREM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("PREM_OC").toString()));
					claimpaidOc.add(map.get("CLAIM_PAID_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_OC").toString()));
					claimratioOc.add(map.get("CLAIM_RATIO_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_RATIO_OC").toString()));
					claimoutOc.add(map.get("CLAIM_OS_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_OS_OC").toString()));
					slideOc.add(map.get("SLIDE_SCLE_COM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("SLIDE_SCLE_COM_OC").toString()));
					slideadjOc.add(map.get("SLIDE_SCLE_ADJ_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("SLIDE_SCLE_ADJ_OC").toString()));
					compaidOc.add(map.get("COMM_PAID_TIL_DATE_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("COMM_PAID_TIL_DATE_OC").toString()));
					claimpaidratioOc.add(map.get("CLAIM_PAID_RATIO_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_RATIO_OC").toString()));
					bean.setAdjToOutLoss(map.get("ADJTOOUT_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("ADJTOOUT_OC").toString()));
					}
					
					}
				else{
					if(exchangeRate=="0.00"){
						bean.setGridShow("Y");
						bean.setConPortfolioPremium(map.get("PREM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("PREM_OC").toString()));
						bean.setConAccCostBrokerage(map.get("ACQ_COST_BROKR") == null ? "0.00" : DropDownControllor.formatter(map.get("ACQ_COST_BROKR").toString()));
						bean.setConManagExp(map.get("MANAGMT_EXP") == null ? "0.00" : DropDownControllor.formatter(map.get("MANAGMT_EXP").toString()));
						bean.setConProClaimPaidOC(map.get("CLAIM_PAID_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_OC").toString()));
						bean.setConProClaimOutStandingOC(map.get("CLAIM_OS_OC") == null ? "0.00" :DropDownControllor.formatter( map.get("CLAIM_OS_OC").toString()));
						bean.setConProfitLoss(map.get("PRFT_LOSS_FRWD") == null ? "0.00" : DropDownControllor.formatter(map.get("PRFT_LOSS_FRWD").toString()));
						bean.setConOtherAdj(map.get("OTH_ARJ") == null ? "0.00" :DropDownControllor.formatter( map.get("OTH_ARJ").toString()));
						bean.setConTreatyAdj(map.get("TRTY_RES_AFTR_ADJ") == null ? "0.00" : DropDownControllor.formatter(map.get("TRTY_RES_AFTR_ADJ").toString()));
						bean.setConProfitRatio(map.get("PROFIT_RATIO") == null ? "0.00" : DropDownControllor.formatter(map.get("PROFIT_RATIO").toString()));
						bean.setConLossRatio(map.get("LOSS_RATIO") == null ? "0.00" : DropDownControllor.formatter(map.get("LOSS_RATIO").toString()));
						bean.setConProfitCommission(map.get("PROFIT_COMM") == null ? "0.00" : DropDownControllor.formatter(map.get("PROFIT_COMM").toString()));
						bean.setConSuperProfitComm(map.get("SUP_PROFIT_COMM") == null ? "0.00" :DropDownControllor.formatter( map.get("SUP_PROFIT_COMM").toString()));
						bean.setConPayedTillDate(map.get("COMM_PAID_TIL_DATE_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("COMM_PAID_TIL_DATE_OC").toString()));
						bean.setConProfitCommAdj(map.get("PROFIT_COMM_ADJ") == null ? "0.00" : DropDownControllor.formatter(map.get("PROFIT_COMM_ADJ").toString()));
					}else{
						preOc.add(map.get("PREM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("PREM_OC").toString()));
						acqCost.add(map.get("ACQ_COST_BROKR") == null ? "0.00" : DropDownControllor.formatter(map.get("ACQ_COST_BROKR").toString()));
						manExo.add(map.get("MANAGMT_EXP") == null ? "0.00" : DropDownControllor.formatter(map.get("MANAGMT_EXP").toString()));
						profitLoss.add(map.get("PRFT_LOSS_FRWD") == null ? "0.00" : DropDownControllor.formatter(map.get("PRFT_LOSS_FRWD").toString()));
						otherAdj.add(map.get("OTH_ARJ") == null ? "0.00" : DropDownControllor.formatter(map.get("OTH_ARJ").toString()));
						treatyRes.add(map.get("TRTY_RES_AFTR_ADJ") == null ? "0.00" : DropDownControllor.formatter(map.get("TRTY_RES_AFTR_ADJ").toString()));
						profitRatio.add(map.get("PROFIT_RATIO") == null ? "0.00" : DropDownControllor.formatter(map.get("PROFIT_RATIO").toString()));
						lossRatio.add(map.get("LOSS_RATIO") == null ? "0.00" : DropDownControllor.formatter(map.get("LOSS_RATIO").toString()));
						profitComm.add(map.get("PROFIT_COMM") == null ? "0.00" : DropDownControllor.formatter(map.get("PROFIT_COMM").toString()));
						supProfitComm.add(map.get("SUP_PROFIT_COMM") == null ? "0.00" : DropDownControllor.formatter(map.get("SUP_PROFIT_COMM").toString()));
						profitCommAdj.add(map.get("PROFIT_COMM_ADJ") == null ? "0.00" : DropDownControllor.formatter(map.get("PROFIT_COMM_ADJ").toString()));
						claimpaidOc.add(map.get("CLAIM_PAID_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_OC").toString()));
						claimoutOc.add(map.get("CLAIM_OS_OC") == null ? "0.00" :DropDownControllor.formatter( map.get("CLAIM_OS_OC").toString()));
						compaidOc.add(map.get("COMM_PAID_TIL_DATE_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("COMM_PAID_TIL_DATE_OC").toString()));
					}
					
				}
				
			}
			bean.setLossPremiumOC(preOc);
			bean.setLossClaimPaidOC(claimpaidOc);
			bean.setLossClaimRatioOC(claimratioOc);
			bean.setLossClaimOutStandingOC(claimoutOc);
			bean.setLossPartOC(slideOc);
			bean.setLossPartRefTillDate(compaidOc);
			bean.setLossPartRefAdjOC(slideadjOc);
			bean.setClaimPaidRatioOC(claimpaidratioOc);
			
			bean.setClaimRatioOC(claimratioOc);
			bean.setPremiumOC(preOc);
			bean.setClaimPaidOC(claimpaidOc);
			bean.setSlideScaleCommOC(slideOc);
			bean.setSlideScaleAdjOC(slideadjOc);
			bean.setClaimOutStandingOC(claimoutOc);
			bean.setCommPaidOCTillDate(compaidOc);
			
			bean.setPortfolioPremium(preOc);
			bean.setAccCostBrokerage(acqCost);
			bean.setManagExp(manExo);
			bean.setProClaimPaidOC(claimpaidOc);
			bean.setProClaimOutStandingOC(claimoutOc);
			bean.setProfitLoss(profitLoss);
			bean.setOtherAdj(otherAdj);
			bean.setTreatyAdj(treatyRes);
			bean.setProfitRatio(profitRatio);
			bean.setLossRatio(lossRatio);
			bean.setProfitCommission(profitComm);
			bean.setSuperProfitComm(supProfitComm);
			bean.setPayedTillDate(compaidOc);
			bean.setProfitCommAdj(profitCommAdj);
			bean.setSlideCommission(slide);
			}
			bean.setExchRatePrem(ExOc);
			bean.setPreCurrencylist(currId);
			bean.setCurrencyShortName(currName);
		}
		catch(Exception e){
			LOGGER.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		LOGGER.info("PremSlideEditFieldValue() || Enter");
	}*/

	public void PremiumContractDetails(FaculPremiumBean bean, String countryId) {
		     LOGGER.info("PremiumContractDetails() || Enter");
		     String query="";
		     Object args[] = null;
		     List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
		     try {
				 query = getQuery("PREMIUM_CONTRACT_DETAILS");
				 args = new Object[5];
				 
				 args[0] = bean.getContNo();
				 args[1] = bean.getBranchCode();
				 args[2] = bean.getBranchCode();
				 args[3] = bean.getContNo();
				 args[4] = bean.getProposal_No();
				 LOGGER.info("Query=>"+query);
				 LOGGER.info("Args=>"+StringUtils.join(args, ","));
				 res = this.mytemplate.queryForList(query, args);
				 if (res.size() > 0) {
					 for (int i = 0; i < res.size(); i++) {
						 Map<String, Object> map = res.get(i);
						    bean.setContNo(map.get("RSK_CONTRACT_NO")== null ? "" : map.get("RSK_CONTRACT_NO").toString());
						    bean.setAmendId(map.get("RSK_ENDORSEMENT_NO")== null ? "" : map.get("RSK_ENDORSEMENT_NO").toString());
						    bean.setSubProfit_center(map.get("TMAS_DEPARTMENT_NAME")== null ? "" : map.get("TMAS_DEPARTMENT_NAME").toString());
						    bean.setCedingCo(map.get("COMPANY")== null ? "" : map.get("COMPANY").toString());
						    bean.setTreatyName_type(map.get("RSK_TREATYID")== null ? "" : map.get("RSK_TREATYID").toString());
						    bean.setProposal_No(map.get("RSK_PROPOSAL_NUMBER")== null ? "" : map.get("RSK_PROPOSAL_NUMBER").toString());
						    bean.setUwYear(map.get("RSK_UWYEAR")== null ? "" : map.get("RSK_UWYEAR").toString());
						    bean.setLayerno(map.get("RSK_LAYER_NO")== null ? "" : map.get("RSK_LAYER_NO").toString());
						    bean.setInsDate(map.get("INS_DATE")== null ? "" : map.get("INS_DATE").toString());
						    bean.setExpDate(map.get("EXP_DATE")== null ? "" : map.get("EXP_DATE").toString());
						    bean.setBroker(map.get("BROKER")== null ? "" : map.get("BROKER").toString());
						    bean.setDepartmentId(map.get("RSK_DEPTID")== null ? "" : map.get("RSK_DEPTID").toString());
					 }
				 }
			 }
			 catch(Exception e)   {
		     	e.printStackTrace();
		     	LOGGER.debug	("Exception @ {" + e + "}");
		     }
			 LOGGER.info("PremiumContractDetails() || Enter");
	}

public void addFieldValue(FaculPremiumBean bean){
		LOGGER.info("addFieldValue() || Enter");
		String name="";
		String premium="";
		String cliam="";
		String cliamAMT="";
		String paid ="";
		String acqCost="";
		String claimout="";
		String claimpaid="";
		String total="";
		String proComm="";
		String val1[];
		boolean flag=true;
		String[] val=bean.getSlideExchangeRate().split(",");
		List<Map<String,Object>> test= new ArrayList<Map<String,Object>>();
	try{
 		Map<String,Object> list = new HashMap<String,Object>();
				if (bean.getPremiumFinallist2().size() > 0) {
					for (int i = 0; i < bean.getPremiumFinallist2().size(); i++) {
						Map<String, Object> map = bean.getPremiumFinallist2().get(i);
						flag = true;
						if("profitView".equalsIgnoreCase(bean.getMode())){
							name = map.get("SHORT_NAME").toString() == null ? "" : map.get("SHORT_NAME").toString();
							premium = map.get("PremiumOC").toString() == null ? "" : map.get("PremiumOC").toString();
							acqCost = map.get("ACQUI_COST").toString() == null ? "" : map.get("ACQUI_COST").toString();
							cliamAMT =map.get("CLAIM_PAID_AMOUNT").toString() == null ? "" : map.get("CLAIM_PAID_AMOUNT").toString();
							paid =map.get("PAID_AMOUNT_OC").toString() == null ? "" : map.get("PAID_AMOUNT_OC").toString();
							claimout = map.get("CliamOSOC").toString() == null ? "" : map.get("CliamOSOC").toString();
							proComm = map.get("PROFIT_COMMISSION_OC").toString() == null ? "" : map.get("PROFIT_COMMISSION_OC").toString();
							cliam = Double.toString(Double.parseDouble(paid)+Double.parseDouble(cliamAMT));
							total = DropDownControllor.formatter(cliam);
							if (bean.getCurrencyShortName().size() > 0) {
								for (int j = 0; j < bean.getCurrencyShortName().size(); j++) {
									if(name.equalsIgnoreCase(bean.getCurrencyShortName().get(j))){
										//bean.getExchRatePrem().get(i);
										val1 = val[j].split("~");
										if(val1[0].equalsIgnoreCase(name) && flag){
											map.put("EXCHG_RATE",DropDownControllor.formattereight(val1[1]));
											premium =DropDownControllor.formatter(Double.toString(Double.parseDouble(premium) * Double.parseDouble(val1[1])));
											acqCost =DropDownControllor.formatter(Double.toString(Double.parseDouble(acqCost) * Double.parseDouble(val1[1])));
											claimout =DropDownControllor.formatter(Double.toString(Double.parseDouble(claimout) * Double.parseDouble(val1[1])));
											cliam =DropDownControllor.formatter(Double.toString(Double.parseDouble(cliam) * Double.parseDouble(val1[1])));
											proComm = DropDownControllor.formatter(Double.toString(Double.parseDouble(proComm) * Double.parseDouble(val1[1])));
											map.put("PREMIUM_DC",premium);
											map.put("ACQ_COST_DC",acqCost);
											map.put("CLAIMOUT_DC",claimout);
											map.put("CLAIMPAID_DC",cliam);
											map.put("PROFIT_COMMISSION",proComm);
											map.put("CLAIMPAID_OC",total);
											flag = false;
										}

									}
								}
								}
						}else if("lossView".equalsIgnoreCase(bean.getMode())){
							name = map.get("SHORT_NAME")== null ? "0.00" : map.get("SHORT_NAME").toString();
							premium = map.get("PremiumOC") == null ? "0.00" : map.get("PremiumOC").toString();
							cliam = map.get("PAID_AMOUNT_OC") == null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
							cliamAMT =map.get("CLAIM_PAID_AMOUNT") == null ? "0.00" : map.get("CLAIM_PAID_AMOUNT").toString();
							claimout = map.get("CliamOSOC") == null ? "0.00" : map.get("CliamOSOC").toString();
							claimpaid = map.get("COMMISSION_PAID_OC") == null ? "0.00" : map.get("COMMISSION_PAID_OC").toString();
							cliam = Double.toString(Double.parseDouble(cliam)+Double.parseDouble(cliamAMT));
							total = DropDownControllor.formatter(cliam);
							if (bean.getCurrencyShortName().size() > 0) {
								for (int j = 0; j < bean.getCurrencyShortName().size(); j++) {
									if(name.equalsIgnoreCase(bean.getCurrencyShortName().get(j))){
										//bean.getExchRatePrem().get(i);
										val1 = val[j].split("~");
										if(val1[0].equalsIgnoreCase(name) && flag){
											map.put("EXCHG_RATE",DropDownControllor.formattereight(val1[1]));
											premium =DropDownControllor.formatter(Double.toString(Double.parseDouble(premium) * Double.parseDouble(val1[1])));
											cliam =DropDownControllor.formatter(Double.toString(Double.parseDouble(cliam) * Double.parseDouble(val1[1])));
											claimout =DropDownControllor.formatter(Double.toString(Double.parseDouble(claimout) * Double.parseDouble(val1[1])));
											claimpaid =DropDownControllor.formatter(Double.toString(Double.parseDouble(claimpaid) * Double.parseDouble(val1[1])));
											map.put("PREMIUM_DC",premium);
											map.put("CLAIM_DC",cliam);
											map.put("CLAIMOUT_DC",claimout);
											map.put("CLAIMPAID_DC",claimpaid);
											map.put("CLAIM_PREMIUM_VAL",total);
											flag = false;
										}

									}
								}
								}
							
						}
						else{
						name = map.get("SHORT_NAME").toString() == null ? "" : map.get("SHORT_NAME").toString();
						premium = map.get("PremiumOC") == null ? "0.00" : map.get("PremiumOC").toString();
						cliam = map.get("PAID_AMOUNT_OC") == null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
						cliamAMT =map.get("CLAIM_PAID_AMOUNT") == null ? "0.00" : map.get("CLAIM_PAID_AMOUNT").toString();
						claimout = map.get("CliamOSOC") == null ? "0.00" : map.get("CliamOSOC").toString();
						claimpaid = map.get("COMMISSION_PAID_OC") == null ? "0.00" : map.get("COMMISSION_PAID_OC").toString();
						cliam = Double.toString(Double.parseDouble(cliam)+Double.parseDouble(cliamAMT));
						total = DropDownControllor.formatter(cliam);
						if (bean.getCurrencyShortName().size() > 0) {
							for (int j = 0; j < bean.getCurrencyShortName().size(); j++) {
								if(name.equalsIgnoreCase(bean.getCurrencyShortName().get(j))){
									//bean.getExchRatePrem().get(i);
									val1 = val[j].split("~");
									if(val1[0].equalsIgnoreCase(name) && flag){
										map.put("EXCHG_RATE",DropDownControllor.formattereight(val1[1]));
										premium =DropDownControllor.formatter(Double.toString(Double.parseDouble(premium) * Double.parseDouble(val1[1])));
										cliam =DropDownControllor.formatter(Double.toString(Double.parseDouble(cliam) * Double.parseDouble(val1[1])));
										claimout =DropDownControllor.formatter(Double.toString(Double.parseDouble(claimout) * Double.parseDouble(val1[1])));
										claimpaid =DropDownControllor.formatter(Double.toString(Double.parseDouble(claimpaid) * Double.parseDouble(val1[1])));
										map.put("PREMIUM_DC",premium);
										map.put("CLAIM_DC",cliam);
										map.put("CLAIMOUT_DC",claimout);
										map.put("CLAIMPAID_DC",claimpaid);
										map.put("CLAIM_PREMIUM_VAL",total);
										flag = false;
									}

								}
							}
							}
						}
						test.add(map);
			}
		}
		bean.setPremiumFinallist2(test);
	}
	catch(Exception e){
		e.printStackTrace();
		}
	LOGGER.info("addFieldValue() || Exit");
	}

	public List<FaculPremiumBean> getPremiumReserved(FaculPremiumBean bean, String prTransNo,String countryId) {
		List<FaculPremiumBean> cashLossList = new ArrayList<FaculPremiumBean>();
		
		List<Map<String,Object>> list = premiumapi.getPremiumReserved(bean,prTransNo,countryId);
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
			Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
			FaculPremiumBean tempBean = new FaculPremiumBean();
			tempBean.setTransactionNo(tempMap.get("TransactionNo")==null?"":tempMap.get("TransactionNo").toString());
			tempBean.setContNo(tempMap.get("ContNo")==null?"":tempMap.get("ContNo").toString());
			tempBean.setPaidDate(tempMap.get("PaidDate")==null?"":tempMap.get("PaidDate").toString());
			tempBean.setCurrencyValue(tempMap.get("CurrencyValue")==null?"":tempMap.get("CurrencyValue").toString());
			tempBean.setRequestNo(tempMap.get("RequestNo")==null?"":tempMap.get("RequestNo").toString());
			tempBean.setSerial_no(tempMap.get("Serialno")==null?"":tempMap.get("Serialno").toString());
			tempBean.setPayamount(tempMap.get("Payamount")==null?"":tempMap.get("Payamount").toString());
			tempBean.setCurrencyId(tempMap.get("CurrencyId")==null?"":tempMap.get("CurrencyId").toString());
			tempBean.setPrallocatedTillDate(tempMap.get("PrallocatedTillDate")==null?"":tempMap.get("PrallocatedTillDate").toString());
			tempBean.setCurrencyValueName(tempMap.get("CurrencyValueName")==null?"":tempMap.get("CurrencyValueName").toString());
			tempBean.setCurrencyIdName(tempMap.get("currencyIdName")==null?"":tempMap.get("currencyIdName").toString());
			tempBean.setStatus(tempMap.get("Status")==null?"":tempMap.get("Status").toString());
			tempBean.setCreditAmountCLC(tempMap.get("CreditAmountCLC")==null?"":tempMap.get("CreditAmountCLC").toString());
			tempBean.setCreditAmountCLD(tempMap.get("CreditAmountCLD")==null?"":tempMap.get("CreditAmountCLD").toString());
			tempBean.setCLCsettlementRate(tempMap.get("CLCsettlementRate")==null?"":tempMap.get("CLCsettlementRate").toString());
			tempBean.setCheck(tempMap.get("Check")==null?"":tempMap.get("Check").toString());
			cashLossList.add(tempBean);
			}
		}
		return cashLossList;
	}
	public List<Map<String, Object>> getOSBList(FaculPremiumBean bean) {
		return premiumapi.getOSBList(bean);
	}
	public int getCountCleanCUT(FaculPremiumBean bean) {
		return premiumapi.getCountCleanCUT(bean);
	}
	public int getCountAccountPeriod(FaculPremiumBean bean) {
		LOGGER.info("Enter in to getCountAccountPeriod method");
		int count=0;
		int count1=1;
		try {
			String query=getQuery("GET_ACCOUNT_PERIOD_COUNT");
			count=this.mytemplate.queryForInt(query,new Object[]{bean.getBranchCode(),bean.getAccountPeriodDate(),bean.getContNo(),bean.getDepartmentId(),bean.getContNo(),bean.getDepartmentId()});
			if(!(count>=1)){
			query=getQuery("GET_ACCOUNT_PERIOD_COUNT1");
			count1=this.mytemplate.queryForInt(query,new Object[]{bean.getAccountPeriodDate(),bean.getContNo(),bean.getDepartmentId()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Exit in to getCountAccountPeriod method");
		return count1;
	}
	public void PremiumGetFieldValues(FaculPremiumBean bean) {
		LOGGER.info("Enter in to PremiumGetFieldValues method");
		Object args[]=null;
		Object prargs[]=null;
		Object orargs[]=null;
		List<Map<String,Object>> comList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> premList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> OSList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> profitList = new ArrayList<Map<String,Object>>();
		List<String> claimout =new ArrayList<String>();
		List<String> treatyRes =new ArrayList<String>();
		List<String> total = new ArrayList<String>();
		List<String> pretotal = new ArrayList<String>();
		List<String> paiddate = new ArrayList<String>();
		List<String> manExp = new ArrayList<String>();
		List<String> profitComm = new ArrayList<String>();
		List<String> profitRatio = new ArrayList<String>();
		List<String> claimamt =new ArrayList<String>();
		List<String> premiumamt =new ArrayList<String>();
		List<String> acCost =new ArrayList<String>();
		List<String> lossRatio =new ArrayList<String>();
		List<String> suprProfit =new ArrayList<String>();
		List<String> profitComAdj =new ArrayList<String>();
		List<String> profitLoss =new ArrayList<String>();
		List<String> otherAdj = new ArrayList<String>();
		List<String> unearned = new ArrayList<String>();
		
		List<Map<String,Object>> finalTest=new ArrayList<Map<String,Object>>();
		double val =0;
		double commission =0;
		double paid =0;
		double managementVal=0;
		double proComAdj =0;
		double paidAmt =0;
		double premiumAmt=0;
		double accoucationCost=0;
		String paidamount = "";
		String premAmt ="";
		String curId=""   ;
		String precurId="";  
		String query ="";
		String prquery ="";
		String premium="";
		String commissionOc ="";
		String claimoutSt = "";
		String proComm="";
		//String preCommVal="";
		double proftRatio=0.00;
		double lossVal=0.00;
		String proftComm ="";
		String profComVal ="0";
		String managementExp="";
		String OSQuery="";
		String acqCos="";
		String superProfit="";
		String superProfitVal="";
		String paidtilldate="";
		String stepUp="";
		try{
			if (bean.getPreCurrencylist().size() > 0) {
				 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
				 	total.add("0.00") ;
					 paiddate.add("0.00");
				 	 pretotal.add("0.00");
					 claimout.add("0.00");
					 claimamt.add("0.00");
					 premiumamt.add("0.00");
					 acCost.add("0.00");
					 manExp.add("0.00");
					 profitLoss.add("0.00");
					 otherAdj.add("0.00");
					 unearned.add("0.00");
				 }
			}
			if("one".equalsIgnoreCase(bean.getSlideScenario())) {
				query = getQuery("GET_PAID_AMOUT");
				args=new Object[4];
				args[0] = bean.getContNo();
				args[1] = bean.getBranchCode();
				args[2] = bean.getTransaction();
				args[3] = bean.getProductId();

				prquery = getQuery("GET_PREMIUM_DETAILS");
				prargs=new Object[8];
				prargs[0] = bean.getTransaction();
				prargs[1] = bean.getContNo();
				prargs[2] = bean.getBranchCode();
				prargs[3] = bean.getContNo();
				prargs[4] = bean.getBranchCode();
				prargs[5] = bean.getContNo();
				prargs[6] = bean.getTransaction();
				prargs[7] = bean.getProductId();
				
				OSQuery = getQuery("GET_PREMIUM_CLAIMS_OUT_VAL");
				orargs=new Object[3];
				orargs[0] = bean.getTransaction();
				orargs[1] = bean.getContNo();
				orargs[2] = bean.getBranchCode();
				
			}
			else if("two".equalsIgnoreCase(bean.getSlideScenario()) || "three".equalsIgnoreCase(bean.getSlideScenario())) {
				 query =  getQuery("GET_PAID_AMOUT1");
				args=new Object[5];
				args[0] = bean.getContNo();
				args[1] = bean.getBranchCode();
				args[2] = bean.getTransaction();
				args[3] = bean.getDepartmentId();
				args[4] = bean.getProductId();

				prquery = getQuery("GET_PREMIUM_DETAILS1");
				prargs=new Object[9];
				prargs[0] = bean.getTransaction();
				prargs[1] = bean.getContNo();
				prargs[2] = bean.getBranchCode();
				prargs[3] = bean.getContNo();
				prargs[4] = bean.getBranchCode();
				prargs[5] = bean.getContNo();
				prargs[6] = bean.getTransaction();
				prargs[7] = bean.getDepartmentId();
				prargs[8] = bean.getProductId();
				
				OSQuery = getQuery("GET_PREMIUM_CLAIMS_OUT_VAL1");
				orargs=new Object[3];
				orargs[0] = bean.getTransaction();
				orargs[1] = bean.getContNo();
				orargs[2] = bean.getBranchCode();
				
			}
			
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(args, ","));
			LOGGER.info("Query=>"+prquery);
			LOGGER.info("Args=>"+StringUtils.join(prargs, ","));
			LOGGER.info("Query=>"+OSQuery);
			LOGGER.info("Args=>"+StringUtils.join(orargs, ","));
			comList = this.mytemplate.queryForList(query,args);
			premList = this.mytemplate.queryForList(prquery,prargs);
			OSList = this.mytemplate.queryForList(OSQuery,orargs);
			String qry=getQuery("GET_MANAGEMENT_EXP");
			if(!"profitView".equalsIgnoreCase(bean.getMode())){
			profitList = this.mytemplate.queryForList(qry, new Object[]{bean.getProposal_No()});
			if(profitList.size()>0){
				for (int i = 0; i < profitList.size(); i++) {
					Map<String, Object> map = profitList.get(i);
					managementExp =map.get("RSK_PRO_MANAGEMENT_EXP")==null?"0.00":map.get("RSK_PRO_MANAGEMENT_EXP").toString();
					proComm =map.get("RSK_PRO_COMM_TYPE")==null?"":map.get("RSK_PRO_COMM_TYPE").toString();
					proftComm = map.get("RSK_PRO_COMM_PER")==null?"0.00":map.get("RSK_PRO_COMM_PER").toString();
					superProfit =  map.get("RSK_PRO_SUP_PRO_COM")==null?"0.00":map.get("RSK_PRO_SUP_PRO_COM").toString();
					stepUp = map.get("RSK_PRO_SET_UP")==null?"0.00":map.get("RSK_PRO_SET_UP").toString();
					bean.setProfitCommType(proComm);
					bean.setSuperProfitCom(superProfit);
					bean.setManagementExp(managementExp);
					bean.setStepUp(stepUp);
					bean.setProfitComPer(proftComm);
				}
			}
			if(premList.size()>0){
				for (int i = 0; i < premList.size(); i++) {
						Map<String, Object> map = premList.get(i);
						finalTest.add(premList.get(i));
						premAmt = map.get("CLAIM_PAID_AMOUNT")==null?"0.00":map.get("CLAIM_PAID_AMOUNT").toString();
						commissionOc =map.get("COMMISSION_PAID_OC")==null?"0.00":map.get("COMMISSION_PAID_OC").toString();
						premium = map.get("PremiumOC")==null?"0.00":map.get("PremiumOC").toString();
						acqCos = map.get("ACQUI_COST")==null?"0.00":map.get("ACQUI_COST").toString();
						paidtilldate = map.get("PROFIT_COMMISSION_OC")==null?"0.00":map.get("PROFIT_COMMISSION_OC").toString();
						paidtilldate = paidtilldate.replaceAll(",", "");
						curId = map.get("CURRENCY_ID") == null ? "" : map.get("CURRENCY_ID").toString();
						if (precurId!=null && precurId.equals(curId)) {
							val += Double.parseDouble(premium);
							commission += Double.parseDouble(commissionOc);
							paidAmt +=Double.parseDouble(paidtilldate);
							premiumAmt+=Double.parseDouble(premAmt);
							accoucationCost+=Double.parseDouble(acqCos);
						}
						else{
							val=0;
							commission=0;
							paidAmt=0;
							premiumAmt=0;
							accoucationCost=0;
							val += Double.parseDouble(premium);
							commission += Double.parseDouble(commissionOc);
							paidAmt +=Double.parseDouble(paidtilldate);
							premiumAmt+=Double.parseDouble(premAmt);
							accoucationCost+=Double.parseDouble(acqCos);
						}
						managementVal = val*(Double.parseDouble(managementExp)/100);
						
						if (bean.getPreCurrencylist().size() > 0) {
						 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
							 	precurId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
								if (precurId.equals(bean.getPreCurrencylist().get(j))) {
									 paiddate.set(j,DropDownControllor.formatter(Double.toString(paidAmt)));
									 claimamt.set(j,DropDownControllor.formatter(Double.toString(commission)));
									 pretotal.set(j,DropDownControllor.formatter(Double.toString(val)));
									 premiumamt.set(j,DropDownControllor.formatter(Double.toString(premiumAmt)));
									 acCost.set(j, DropDownControllor.formatter(Double.toString(accoucationCost)));
									 manExp.set(j, DropDownControllor.formatter(Double.toString(managementVal)));
									 total.set(j,DropDownControllor.formatter(Double.toString(premiumAmt)));
								}
							}
						}
					}
				}
			if(OSList.size()>0){
				commission=0;
				for (int i = 0; i < OSList.size(); i++) {
						Map<String, Object> map = OSList.get(i);
						claimoutSt = map.get("OSCLAIM_LOSSUPDATE_OC")== null ? "0.00" : map.get("OSCLAIM_LOSSUPDATE_OC").toString();
						curId = map.get("CURRENCY_ID")== null ? "" : map.get("CURRENCY_ID").toString();
						claimoutSt = claimoutSt.replaceAll(",", "");
						if (precurId!=null && precurId.equals(curId)) {
								commission += Double.parseDouble(claimoutSt) ;
						}
						else{
							commission=0;
							commission += Double.parseDouble(claimoutSt);
						}
						if (bean.getPreCurrencylist().size() > 0) {
							 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
								 	precurId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
									if (precurId.equals(bean.getPreCurrencylist().get(j))) {
										claimout.set(j,DropDownControllor.formatter(Double.toString(commission)));
									}
								}
							}
				}
			}
			if(comList.size()>0) {
				for (int i = 0; i < comList.size(); i++) {
					Map<String, Object> map = comList.get(i);
					finalTest.add(comList.get(i));
					curId = map.get("CURRENCY_ID")== null ? "" : map.get("CURRENCY_ID").toString();
					if (precurId!=null && precurId.equals(curId)) {
						paidamount = map.get("PAID_AMOUNT_OC")== null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
						paid += Double.parseDouble(paidamount.replaceAll(",",""));
					}else{
						paid =0;
						paidamount = map.get("PAID_AMOUNT_OC")== null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
						paid += Double.parseDouble(paidamount.replaceAll(",",""));
					}
					precurId = curId;
					if (bean.getPreCurrencylist().size() > 0) {
						for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
							curId = map.get("CURRENCY_ID")== null ? "" : map.get("CURRENCY_ID").toString();
							if (curId.equals(bean.getPreCurrencylist().get(j))) {
								paid = paid+Double.parseDouble(premiumamt.get(j).replaceAll(",",""));
								total.set(j,DropDownControllor.formatter(Double.toString(paid)));
							}
						}
					}
				}
			}
			else{
				if ( bean.getPreCurrencylist().size() > 0) {
					for (int j = 0; j <  bean.getPreCurrencylist().size(); j++) {
						total.set(j,DropDownControllor.formatter(premiumamt.get(j).replaceAll(",","")));
					}
				}
			}
				for (int i = 0; i < bean.getPreCurrencylist().size(); i++) {
						double ans = 0.00;
						String prtotal = pretotal.get(i).replaceAll(",", "");
						String claimou = claimout.get(i).replaceAll(",", "");
						String aqCost = acCost.get(i).replaceAll(",", "");
						String tot = total.get(i).replaceAll(",", "");
						String manag = manExp.get(i).replaceAll(",", "");
						double trtyPrem=((ans*Double.parseDouble(prtotal))/100);
						
						if (Double.parseDouble(prtotal) > 0) {
							ans = Double.parseDouble(prtotal) - Double.parseDouble(aqCost) - Double.parseDouble(claimou) -Double.parseDouble(tot)-Double.parseDouble(manag);
						}
						if(proComm.equalsIgnoreCase("PR")){
							proftRatio =  (ans/Double.parseDouble(prtotal))*100;
							proftRatio=Double.parseDouble(dropDownController.GetTwoDecimalFormate(proftRatio));
						 }
						else{
							proftRatio = 0.00;
						}
						if(proComm.equalsIgnoreCase("LR")){
							lossVal = ((Double.parseDouble(tot)+Double.parseDouble(claimou))/Double.parseDouble(prtotal))*100;
							lossVal=Double.parseDouble(dropDownController.GetTwoDecimalFormate(lossVal));
						}
						else{
							lossVal =0.00;
						}
						if(ans>0){
						if(proComm.equalsIgnoreCase("PC")){
							profComVal = DropDownControllor.formatter(Double.toString((ans*Double.parseDouble(proftComm))/100));
						}
						else if(proComm.equalsIgnoreCase("PR") && "N".equalsIgnoreCase(stepUp)){
							profComVal=DropDownControllor.formatter(Double.toString((ans*proftRatio)/100));
						}
						else if(proComm.equalsIgnoreCase("PR") && "Y".equalsIgnoreCase(stepUp)){
							String popUpVal = getPremiumValue(bean,proComm,Double.toString(proftRatio),prtotal);
							profComVal=DropDownControllor.formatter(popUpVal);
						}
						else if(proComm.equalsIgnoreCase("LR") && "N".equalsIgnoreCase(stepUp)){
							profComVal=DropDownControllor.formatter(Double.toString((ans*lossVal)/100));
						}
						else if(proComm.equalsIgnoreCase("LR") && "Y".equalsIgnoreCase(stepUp)){
							String popUpVal = getPremiumValue(bean,proComm,Double.toString(lossVal),prtotal);
							profComVal=DropDownControllor.formatter(popUpVal);
						}
						}
						if("Y".equalsIgnoreCase(superProfit)){
							String ratio = (Double.toString((ans/Double.parseDouble(prtotal))*100));
							String popUpVal = getPremiumValue(bean,proComm,ratio,prtotal);
							superProfitVal=DropDownControllor.formatter(popUpVal);
						}
						else{
							superProfitVal="0.00";
						}
						treatyRes.add(DropDownControllor.formatter(Double.toString(ans)));
						profitComm.add(profComVal);
						profitRatio.add(DropDownControllor.formatter(Double.toString(proftRatio)));
						lossRatio.add(DropDownControllor.formatter(Double.toString(lossVal)));
						suprProfit.add(DropDownControllor.formatter(superProfitVal));
						profitComAdj.add(DropDownControllor.formatter(Double.toString(proComAdj)));
						if("NA".equalsIgnoreCase(profitComm.get(i))){
						proComAdj = Double.parseDouble(suprProfit.get(i).replaceAll(",", ""))-Double.parseDouble(paiddate.get(i).replaceAll(",", ""));
						}
						else{
							profitComm.set(i,profitComm.get(i));
							proComAdj = Double.parseDouble(profitComm.get(i).replaceAll(",", ""))+Double.parseDouble(suprProfit.get(i).replaceAll(",", ""))-Double.parseDouble(paiddate.get(i).replaceAll(",", ""));
						}
						}
			bean.setUnearnpremiumOC(unearned);
			bean.setPortfolioPremium(pretotal);
			bean.setAccCostBrokerage(acCost);
			bean.setManagExp(manExp);
			bean.setProClaimPaidOC(total);
			bean.setProClaimOutStandingOC(claimout);
			bean.setTreatyAdj(treatyRes);
			bean.setProfitRatio(profitRatio);
			bean.setLossRatio(lossRatio);
			bean.setProfitCommission(profitComm);
			bean.setSuperProfitComm(suprProfit);
			bean.setPayedTillDate(paiddate);
			bean.setProfitCommAdj(profitComAdj);
			bean.setProfitLoss(profitLoss);
			bean.setOtherAdj(otherAdj);
			
			 if(StringUtils.isBlank(bean.getFlag())){
			bean.setManualportfolioPremium(pretotal);
			bean.setManualunearnpremiumOC(unearned);
			bean.setManualaccCostBrokerage(acCost);
			bean.setManualtreatyAdj(treatyRes);
			bean.setManualproClaimPaidOC(total);
			bean.setManualproClaimOutStandingOC(claimout);
			bean.setManualmanagExp(manExp);
			bean.setManualprofitRatio(profitRatio);
			bean.setManualotherAdj(otherAdj);
			//bean.setManualprofitRatio(profitRatio);
			bean.setManuallossRatio(lossRatio);
			}
			
			
			}
			else{
				if(premList.size()>0){
					for (int i = 0; i < premList.size(); i++) {
							finalTest.add(premList.get(i));
					}
				}
				if(comList.size()>0) {
					for (int i = 0; i < comList.size(); i++) {
						finalTest.add(comList.get(i));
					}
				}
			}
			bean.setPremiumFinallist2(finalTest);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Exit in to PremiumGetFieldValues method");
	}


	public String getPremiumValue(FaculPremiumBean bean ,String proComm,String proftRatio,String prtotal) {
		LOGGER.info("Enter in to getPremiumValue method");
		String from="",to="",profitComm="",sno="";
		String valu="0";
		try{
			double val=0.0;
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			String query=getQuery("GET_POP_UP_PROFIT_COMM");
			Object args[]=new Object[6];
			args[0]=bean.getContNo();
			args[1] = bean.getProposal_No();
			args[2] = bean.getProductId();
			args[3] = bean.getBranchCode();
			args[4] = proComm;
			args[5] = proftRatio ;
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(args, ","));
			list = this.mytemplate.queryForList(query,args);
			
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object> map = list.get(i);
					sno =map.get("SNO")==null?"":map.get("SNO").toString();
					from =map.get("COMM_FROM")==null?"":map.get("COMM_FROM").toString();
					to =map.get("COMM_TO")==null?"":map.get("COMM_TO").toString();
					profitComm =map.get("PROFIT_COMM")==null?"":map.get("PROFIT_COMM").toString();
					if(Integer.parseInt(sno)!=(list.size()+1)){
						val+=((((Double.parseDouble(prtotal)*Double.parseDouble(to))/100) -((Double.parseDouble(prtotal)*Double.parseDouble(from))/100)) *Double.parseDouble(profitComm))/100;
					}
					else{
						val+=((((Double.parseDouble(prtotal)*Double.parseDouble(proftRatio))/100) -((Double.parseDouble(prtotal)*Double.parseDouble(from))/100)) *Double.parseDouble(profitComm))/100;
					}
				}
			}
			valu = Double.toString(val);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Exit in to getPremiumValue method");
		return valu;
	}
	
	public void GetLossFieldValues(FaculPremiumBean bean) {
		LOGGER.info("Enter in to GetLossFieldValues method");
		Object args[]=null;
		Object prargs[]=null;
		Object orargs[]=null;
		List<Map<String,Object>> comList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> premList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> OSList = new ArrayList<Map<String,Object>>();
		List<String> claimout =new ArrayList<String>();
		List<String> claimRatio =new ArrayList<String>();
		List<String> claimPaidRatio =new ArrayList<String>();
		List<String> total = new ArrayList<String>();
		List<String> pretotal = new ArrayList<String>();
		List<String> paiddate = new ArrayList<String>();
		List<String> lossPartOC = new ArrayList<String>();
		List<String> lossPartRefAdjO = new ArrayList<String>();
		List<String> claimamt =new ArrayList<String>();
		List<String> premiumamt =new ArrayList<String>();
		List<Map<String,Object>>finalTest=new ArrayList<Map<String,Object>>();
		List<String> unearned = new ArrayList<String>();
		double val =0;
		double commission =0;
		double paid =0;
		String paidamount = "";
		double premAmt =0;
		String curId=""   ;
		String precurId="";  
		String query ="";
		String prquery ="";
		String quotaShare = "";
		String comqs = "";
		String claimoutSt = "";
		String slideres="";
		String claimres="0.00";
		String OSQuery="";
		String out="";
		String paidtilldate="";
		DecimalFormat myFormatter = new DecimalFormat("#####.##");
		try{
			if (bean.getPreCurrencylist().size() > 0) {
				 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
				 	total.add("0.00") ;
					 paiddate.add("0.00");
				 	 pretotal.add("0.00");
					 claimout.add("0.00");
					 claimamt.add("0.00");
					 premiumamt.add("0.00");
					 unearned.add("0.00");
					 
				 }
			}
			if("one".equalsIgnoreCase(bean.getSlideScenario())) {
				query = getQuery("GET_PAID_AMOUT_LOSS");
				args=new Object[4];
				args[0] = bean.getContNo();
				args[1] = bean.getBranchCode();
				args[2] = bean.getTransaction();
				args[3] = bean.getProductId();

				prquery = getQuery("GET_PREMIUM_DETAILS_LOSS");
				prargs=new Object[8];
				prargs[0] = bean.getTransaction();
				prargs[1] = bean.getContNo();
				prargs[2] = bean.getBranchCode();
				prargs[3] = bean.getContNo();
				prargs[4] = bean.getBranchCode();
				prargs[5] = bean.getContNo();
				prargs[6] = bean.getTransaction();
				prargs[7] = bean.getProductId();
				
				OSQuery = getQuery("GET_PREMIUM_CLAIMS_OUT_VAL_LOSS");
				orargs=new Object[3];
				orargs[0] = bean.getTransaction();
				orargs[1] = bean.getContNo();
				orargs[2] = bean.getBranchCode();
				
				
			}
			else if("two".equalsIgnoreCase(bean.getSlideScenario()) || "three".equalsIgnoreCase(bean.getSlideScenario())) {
				 query =  getQuery("GET_PAID_AMOUT_LOSS1");
				args=new Object[5];
				args[0] = bean.getContNo();
				args[1] = bean.getBranchCode();
				args[2] = bean.getTransaction();
				args[3] = bean.getDepartmentId();
				args[4] = bean.getProductId();


				prquery = getQuery("GET_PREMIUM_DETAILS_LOSS1");
				prargs=new Object[9];
				prargs[0] = bean.getTransaction();
				prargs[1] = bean.getContNo();
				prargs[2] = bean.getBranchCode();
				prargs[3] = bean.getContNo();
				prargs[4] = bean.getBranchCode();
				prargs[5] = bean.getContNo();
				prargs[6] = bean.getTransaction();
				prargs[7] = bean.getDepartmentId();
				prargs[8] = bean.getProductId();
				
			OSQuery = getQuery("GET_PREMIUM_CLAIMS_OUT_VAL_LOSS1");
			orargs=new Object[3];
			orargs[0] = bean.getTransaction();
			orargs[1] = bean.getContNo();
			orargs[2] = bean.getBranchCode();
			}
			
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(args, ","));
			LOGGER.info("Query=>"+prquery);
			LOGGER.info("Args=>"+StringUtils.join(prargs, ","));
			LOGGER.info("Query=>"+OSQuery);
			LOGGER.info("Args=>"+StringUtils.join(orargs, ","));
			comList = this.mytemplate.queryForList(query,args);
			premList = this.mytemplate.queryForList(prquery,prargs);
			OSList = this.mytemplate.queryForList(OSQuery,orargs);
			
			if(premList.size()>0){
				for (int i = 0; i < premList.size(); i++) {
						Map<String, Object> map = premList.get(i);
						finalTest.add(premList.get(i));
						 comqs =map.get("COMMISSION_SURPLUS_OC").toString()==null?"0.00":map.get("COMMISSION_SURPLUS_OC").toString();
						 paidtilldate = map.get("CLAIM_PAID_AMOUNT").toString()==null?"0.00":map.get("CLAIM_PAID_AMOUNT").toString();
						 quotaShare = map.get("PremiumOC").toString()==null?"0.00":map.get("PremiumOC").toString();
						quotaShare = quotaShare.replaceAll(",", "");
						comqs = comqs.replaceAll(",", "");
						paidtilldate = paidtilldate.replaceAll(",", "");
						curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
						if (precurId!=null && precurId.equals(curId)) {
							val+=Double.parseDouble(quotaShare);
							commission += Double.parseDouble(comqs);
							premAmt +=Double.parseDouble(paidtilldate);
						}
						else{
							val=0;
							commission=0;
							val+=Double.parseDouble(quotaShare);
							commission += Double.parseDouble(comqs);
							premAmt +=Double.parseDouble(paidtilldate);
						}
						if (bean.getPreCurrencylist().size() > 0) {
						 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
							 	precurId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
								if (precurId.equals(bean.getPreCurrencylist().get(j))) {
									 paiddate.set(j,DropDownControllor.formatter(Double.toString(commission)));
									 pretotal.set(j,DropDownControllor.formatter(Double.toString(val)));
									 premiumamt.set(j,DropDownControllor.formatter(Double.toString(premAmt)));
									 total.set(j,DropDownControllor.formatter(Double.toString(premAmt)));
								}
							}
						}
					}
				}
			if(OSList.size()>0){
				commission=0;
				for (int i = 0; i < OSList.size(); i++) {
						Map<String, Object> map = OSList.get(i);
						claimoutSt = map.get("OSCLAIM_LOSSUPDATE_OC").toString() == null ? "0.00" : map.get("OSCLAIM_LOSSUPDATE_OC").toString();
						curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
						claimoutSt = claimoutSt.replaceAll(",", "");
						if (precurId!=null && precurId.equals(curId)) {
								commission += Double.parseDouble(claimoutSt) ;
						}
						else{
							commission=0;
							commission += Double.parseDouble(claimoutSt);
						}
						if (bean.getPreCurrencylist().size() > 0) {
							 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
								 	precurId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
									if (precurId.equals(bean.getPreCurrencylist().get(j))) {
										claimout.set(j,DropDownControllor.formatter(Double.toString(commission)));
									}
								}
							}
				}
			}
			if(comList.size()>0) {
				for (int i = 0; i < comList.size(); i++) {
					
					Map<String, Object> map = comList.get(i);
					finalTest.add(comList.get(i));
					curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
					
					if (precurId!=null && precurId.equals(curId)) {
						paidamount = map.get("PAID_AMOUNT_OC").toString() == null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
						paid += Double.parseDouble(paidamount.replaceAll(",",""));
					}else{
						paid =0;
						paidamount = map.get("PAID_AMOUNT_OC").toString() == null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
						paid += Double.parseDouble(paidamount.replaceAll(",",""));
					}
					precurId = curId;
					if (bean.getPreCurrencylist().size() > 0) {
						for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
							curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
							if (curId.equals(bean.getPreCurrencylist().get(j))) {
								paid = paid+Double.parseDouble(premiumamt.get(j).replaceAll(",",""));
								total.set(j,DropDownControllor.formatter(Double.toString(paid)));
							}
						}
					}
				}
			}
			else{
				if ( bean.getPreCurrencylist().size() > 0) {
					for (int j = 0; j <  bean.getPreCurrencylist().size(); j++) {
						total.set(j,DropDownControllor.formatter(premiumamt.get(j).replaceAll(",","")));
					}
				}
			}
			//if(bean.getPremiumOC()==null) {
				for (int i = 0; i < bean.getPreCurrencylist().size(); i++) {
					double ans = 0.00;
					double ans1 = 0.00;
					
					//double slideresult = 0.00;
					String prtotal = pretotal.get(i).replaceAll(",", "");
					String claimou = claimout.get(i).replaceAll(",", "");
					String tot = total.get(i).replaceAll(",", "");
					if (Double.parseDouble(prtotal) > 0) {
						ans = ((Double.parseDouble(tot) + Double.parseDouble(claimou)) / (Double.parseDouble(prtotal))) * 100;
						ans1 = ((Double.parseDouble(tot)) / Double.parseDouble(prtotal)) * 100;
						ans=Double.parseDouble(dropDownController.GetTwoDecimalFormate(ans));
						ans1=Double.parseDouble(dropDownController.GetTwoDecimalFormate(ans1));
					}
					claimRatio.add(DropDownControllor.formatter(Double.toString(ans)));
					claimPaidRatio.add(DropDownControllor.formatter(Double.toString(ans1)));
				if(ans>0) {
					claimres = getLossValue(bean, ans,prtotal);
					claimres = claimres.replaceAll(",", "");
				}
				if(ans1>0) {
					slideres = getLossValue(bean, ans1,prtotal);
					slideres = slideres.replaceAll(",", "");
					/*if (Double.parseDouble(prtotal) > 0) {
						slideresult = Double.parseDouble(prtotal) * Double.parseDouble(slideres) / 100;*/
				}
					lossPartOC.add(DropDownControllor.formatter(StringUtils.isBlank(slideres)?"0":slideres));
					String sliderest = lossPartOC.get(i).replaceAll(",", "");
					String paiddat = paiddate.get(i).replaceAll(",", "");
					double slideans = Double.parseDouble(StringUtils.isBlank(sliderest)?"0":sliderest) - Double.parseDouble(paiddat);
					lossPartRefAdjO.add(DropDownControllor.formatter(Double.toString(slideans)));
					double adjtooutloss = Double.parseDouble(StringUtils.isBlank(claimres)?"0":claimres) - Double.parseDouble(StringUtils.isBlank(slideres)?"0":slideres);
					out=DropDownControllor.formatter(Double.toString(adjtooutloss));
				}
		//	}
				if(bean.getPreCurrencylist()!=null &&bean.getPreCurrencylist().size()==1){
					bean.setAdjToOutLoss(out);
				}
			bean.setUnearnpremiumOC(unearned);
			bean.setLossClaimRatioOC(claimRatio);
			bean.setClaimPaidRatioOC(claimPaidRatio);
			bean.setLossPremiumOC(pretotal);
			bean.setLossClaimPaidOC(total);
			bean.setLossPartOC(lossPartOC);
			bean.setLossPartRefAdjOC(lossPartRefAdjO);
			bean.setLossClaimOutStandingOC(claimout);
			bean.setLossPartRefTillDate(paiddate);
			bean.setPremiumFinallist2(finalTest);
			
			if(StringUtils.isBlank(bean.getFlag())){
				bean.setManualPremiumOC(pretotal);
				bean.setManualunearnpremiumOC(unearned);
				bean.setManuallossClaimPaidOC(total);
				bean.setManuallossClaimOutStandingOC(claimout);
				bean.setManuallossClaimRatioOC(claimRatio);
				bean.setManuallossclaimPaidRatioOC(claimPaidRatio);
			}
			
			
		}catch(Exception e){
			LOGGER.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		LOGGER.info("Exit in to GetLossFieldValues method");
	}


	public String getLossValue(FaculPremiumBean bean ,double ans,String prtotal) {
		LOGGER.info("Enter in to getLossValue method");
		String from="",to="",profitComm="";
		String valu="0";
		try{
			double val=0.0;
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
			String query=getQuery("GET_POP_UP_LOSS_PART");
			Object args[]=new Object[6];
			args[0]=bean.getContNo();
			args[1] = bean.getProductId();
			args[2] = bean.getBranchCode();
			args[3] = ans;
			args[4] = "LPC";
			args[5] = bean.getDepartmentId();
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(args, ","));
			list = this.mytemplate.queryForList(query,args);
			query=getQuery("GET_POP_UP_LOSS_PART_GRE");
			args=new Object[6];
			args[0]=bean.getContNo();
			args[1] = bean.getProductId();
			args[2] = bean.getBranchCode();
			args[3] = ans;
			args[4] = "LPC";
			args[5] = bean.getDepartmentId();
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(args, ","));
			list1 = this.mytemplate.queryForList(query,args);
			query=getQuery("GET_POP_UP_LOSS_PART_LESS");
			args=new Object[6];
			args[0]=bean.getContNo();
			args[1] = bean.getProductId();
			args[2] = bean.getBranchCode();
			args[3] = ans;
			args[4] = "LPC";
			args[5] = bean.getDepartmentId();
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(args, ","));
			list2 = this.mytemplate.queryForList(query,args);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object> map = list.get(i);
					from =map.get("LCB_FROM")==null?"":map.get("LCB_FROM").toString();
					to =map.get("LCB_TO")==null?"":map.get("LCB_TO").toString();
					profitComm =map.get("LCB_PERCENTAGE")==null?"":map.get("LCB_PERCENTAGE").toString();
					if((i<(list.size()-1))){
						val+=((((Double.parseDouble(prtotal)*Double.parseDouble(to))/100) -((Double.parseDouble(prtotal)*Double.parseDouble(from))/100)) *Double.parseDouble(profitComm))/100;
					}
					else{
						val+=((((Double.parseDouble(prtotal)*(ans))/100) -((Double.parseDouble(prtotal)*Double.parseDouble(from))/100)) *Double.parseDouble(profitComm))/100;
					}
				}
			}else if(list1.size()>0){
				for(int i=0;i<list1.size();i++){
					Map<String,Object> map = list1.get(i);
					from =map.get("LCB_FROM")==null?"":map.get("LCB_FROM").toString();
					to =map.get("LCB_TO")==null?"":map.get("LCB_TO").toString();
					profitComm =map.get("LCB_PERCENTAGE")==null?"":map.get("LCB_PERCENTAGE").toString();
					val+=((((Double.parseDouble(prtotal)*Double.parseDouble(to))/100) -((Double.parseDouble(prtotal)*Double.parseDouble(from))/100)) *Double.parseDouble(profitComm))/100;
					
				}
			}
			else{
				val=0.00;
			}
			valu = Double.toString(val);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Exit in to getLossValue method");
		return valu;
	}


	public void insertSection(FaculPremiumBean bean) {
		try {
			Object [] obj=new Object[3];
			String query = getQuery("GET_MAX_SECTION_NO");
			obj[0]=bean.getContNo();
			obj[1]=bean.getDepartmentId();
			obj[2]=bean.getBranchCode();
			String secNo = this.mytemplate.queryForObject(query,obj,String.class);
			query=getQuery("INSERT_SECTION_DETAILS");
			obj=new Object[6];
			obj[0]=secNo;
			obj[1]=bean.getContNo();
			obj[2]=bean.getDepartmentId();
			obj[3]=bean.getSectionName();
			obj[4]=bean.getBranchCode();
			obj[5]=bean.getLoginId();
			bean.setSectionName(secNo);
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(obj, ","));
			this.mytemplate.update(query,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int getSectionCount(FaculPremiumBean bean) {
		int count=0;
		try {
			String Query=getQuery("GET_SECTION_COUNT");
			Object obj[]=new Object[4];
			obj[0]=bean.getContNo();
			obj[1]=bean.getDepartmentId();
			obj[2]=bean.getBranchCode();
			obj[3]=bean.getSectionName();
			LOGGER.info("Query=>"+Query);
			LOGGER.info("Args=>"+StringUtils.join(obj, ","));
			count=this.mytemplate.queryForInt(Query,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
		
	}
	public int getDepositReleaseCount(FaculPremiumBean bean) {
		int res =0;
		try{
			String sql = "";
			Object args[] = new Object[4];
			if("cashcountStatus".equalsIgnoreCase(bean.getDropDown())){
				args = new Object[3];
				args[0] = bean.getContNo();
				args[1] = bean.getBranchCode();
				args[2] = "P";
				sql = getQuery("GET_COUNT_CASHLOSS_PREM");
			}else{
				args[0] = bean.getContNo();
				args[1] = bean.getBranchCode();
				args[2] = "P";
				args[3] = bean.getType();
				sql = getQuery("GET_COUNT_DEPOSIT_PREM");
			}
			res = this.mytemplate.queryForInt(sql, args);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	@Override
	public List<Object> getAllocatedCassLossCredit(FaculPremiumBean bean) {
		List<Object>result=new ArrayList<>();
		List<Map<String,Object>>list=new ArrayList<>();
		list = premiumapi.getAllocatedCassLossCredit(bean);
		if(list!=null && list.size()>0) {
		for(int i=0;i<list.size();i++) {
		Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
		FaculPremiumBean tempBean = new FaculPremiumBean();
		//tempBean.setCreditTrxnNo(tempMap.get("CreditTrxnNo")==null?"":tempMap.get("CreditTrxnNo").toString());
		tempBean.setContractNo(tempMap.get("ContractNo")==null?"":tempMap.get("ContractNo").toString());
		//tempBean.setClaimNo(tempMap.get("ClaimNo")==null?"":tempMap.get("ClaimNo").toString());
		tempBean.setClaimPaymentNo(tempMap.get("ClaimPaymentNo")==null?"":tempMap.get("ClaimPaymentNo").toString());
		//tempBean.setCreditDate(tempMap.get("CreditDate")==null?"":tempMap.get("CreditDate").toString());
		tempBean.setcLDAmount(tempMap.get("CldAmount")==null?"":tempMap.get("CldAmount").toString());
		//tempBean.setCldCurrencyId(tempMap.get("CldCurrencyId")==null?"":tempMap.get("CldCurrencyId").toString());
		//tempBean.setClcCurrencyId(tempMap.get("ClcCurrencyId")==null?"":tempMap.get("ClcCurrencyId").toString());
		tempBean.setCreditAmountCLD(tempMap.get("CreditAmountCld")==null?"":tempMap.get("CreditAmountCld").toString());
		tempBean.setCreditAmountCLC(tempMap.get("CreditAmountClc")==null?"":tempMap.get("CreditAmountClc").toString());
		tempBean.setExchangerate(tempMap.get("ExchangeRate")==null?"":tempMap.get("ExchangeRate").toString());
		result.add(tempBean);
		}
		}
			return result;
	}
	@Override
	public List<Object> getAllocatedTransList(FaculPremiumBean bean) {
		return premiumapi.getAllocatedTransList(bean);
	}
	@Override
	public double getReverseCassLossCredit(FaculPremiumBean bean) {
		LOGGER.info("getReverseCassLossCredit() || Enter");
	     String query="";
	     double value1=0.0;
	     List<Map<String, Object>>result=new ArrayList<>();
	     try {
			query=getQuery("GET_REVERSE_TRANS_LIST");
			result=this.mytemplate.queryForList(query,new Object[] {bean.getProposal_No(),bean.getCashlosstranId()});
			for(int i=0;i<result.size();i++) {
				Map<String,Object>map=result.get(i);
				value1=value1+(map.get("CREDITAMOUNTCLC")==null?0.0:Double.parseDouble(map.get("CREDITAMOUNTCLC").toString()));
						
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value1;
	}
	@Override
	public void InsertReverseCashLossCredit(FaculPremiumBean bean) {
		try{
			 String query="";
			 List<Map<String, Object>>result=new ArrayList<>();
			 String clc="",cld="",paymentNo="",claimNo="";
			LOGGER.info("InsertReverseCashLossCredit || Enter");
			if(StringUtils.isNotBlank(bean.getCashlosstranId())){
				query=getQuery("GET_REVERSE_TRANS_LIST");
				result=this.mytemplate.queryForList(query,new Object[] {bean.getProposal_No(),bean.getCashlosstranId()});
				for(int i=0;i<result.size();i++) {
					Map<String,Object>map=result.get(i);
					clc=map.get("CREDITAMOUNTCLC")==null?"0":map.get("CREDITAMOUNTCLC").toString();
					cld=map.get("CREDITAMOUNTCLD")==null?"0":map.get("CREDITAMOUNTCLD").toString();
					paymentNo=map.get("CLAIMPAYMENT_NO")==null?"0":map.get("CLAIMPAYMENT_NO").toString();
					claimNo=map.get("CLAIM_NO")==null?"0":map.get("CLAIM_NO").toString();
					Object[] obj=new Object[7];
					obj[0]=bean.getCashlossType();
					obj[1]=clc;
					obj[2]=cld;
					obj[3]="0";
					obj[4]="0";
					obj[5]=map.get("CREDITTRXNNO")==null?0.0:map.get("CREDITTRXNNO").toString();
					obj[6]=map.get("CLC_NO")==null?0.0:map.get("CLC_NO").toString();
					
					query=getQuery("UPDATE_REVERSE_CASH_LOSS");
					LOGGER.info("Insert Query==>"+query);
					LOGGER.info("Obj==>"+StringUtils.join(obj,","));
				 	this.mytemplate.update(query, obj);
				 	String sql=getQuery("UPDATE_CASH_LOSS_PAYMENT");
				 	Object[] arg=new Object[]{clc,bean.getContNo(),claimNo,paymentNo};
				 	LOGGER.info("Update Query==>"+sql);
				 	LOGGER.info("Obj==>"+StringUtils.join(arg,","));
				 	this.mytemplate.update(sql,arg );
				}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.info("InsertCashLossCredit || Enter");
	}
	@Override
	public void cashLossmailTrigger(FaculPremiumBean bean) {
		String result="";
		CommonDAO dao=new CommonDAO();
		try {
		Object[] args = new Object[3];
		args[0] = bean.getContNo();
		args[1] = bean.getContNo();
		args[2] = bean.getDepartmentId();
		String selectQry = getQuery("GET_CASH_LOSS_CREADIT_MAIL");
		LOGGER.info("Query=>"+selectQry);
		List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
		if(list!=null && list.size()>0) {
			String query=getQuery("GET_CASSLOSS_ALLOCATE_COUNT");
			int count=this.mytemplate.queryForInt(query,new Object[] {bean.getProposal_No()});
			if(count==0) {
				result = dao.sendCashLossMail(list,bean);
			}else {
				String query1=getQuery("GET_CASSLOSS_ALLOCATE_AMOUNT_COUNT");
				int count1=this.mytemplate.queryForInt(query1,new Object[] {bean.getProposal_No(),bean.getTransactionNo()});
				if(count1>0) {
					selectQry = getQuery("GET_REVERSE_TRANS_LIST");
					LOGGER.info("Query=>"+selectQry);
					list = this.mytemplate.queryForList(selectQry,new Object[] {bean.getProposal_No(),bean.getTransactionNo()});
					result = dao.sendCashLossMail(list,bean);
				}
			} 
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Map<String, Object>> getRipremiumList(FaculPremiumBean bean) {
		return premiumapi.getRipremiumList(bean);
	}
	@Override
	public void updateRiStatus(FaculPremiumBean bean) {
		premiumapi.updateRiStatus(bean);
		
	}
}


