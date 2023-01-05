package com.maan.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import com.maan.bean.FaculPremiumBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.XolPremiumDAO;
import com.maan.dao.ApiCaller.ApiForXolPremium;

public class XolPremiumDAOImpl extends MyJdbcTemplate implements XolPremiumDAO {
		
	ApiForXolPremium xolpremium = new ApiForXolPremium();
		private static final Logger LOGGER = LogUtil.getLogger(ProportionalPremiumDaoImpl.class);
		public boolean premiumInsertMethod(final FaculPremiumBean beanObj){
			boolean saveFlg = false;
			try {
				Map<String,Object> resMap = xolpremium.premiumInsertMethod(beanObj);
				if(resMap!=null) {
				beanObj.setRequestNo(resMap.get("RequestNo")==null?"":resMap.get("RequestNo").toString());
				beanObj.setTransactionNo(resMap.get("TransactionNo")==null?"":resMap.get("TransactionNo").toString());
			}
				saveFlg = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return saveFlg;
		}
	public boolean contractDetails(final FaculPremiumBean bean,final String countryId){
		boolean savFlg = false;
				try {
					Map<String,Object> resMap = xolpremium.contractDetails(bean,countryId);
					if(resMap!=null) {
					bean.setContNo(resMap.get("ContNo")==null?"":resMap.get("ContNo").toString());
					bean.setAmendId(resMap.get("AmendId")==null?"":resMap.get("AmendId").toString());
					bean.setProfit_Center(resMap.get("ProfitCenter")==null?"":resMap.get("ProfitCenter").toString());
					bean.setSubProfit_center(resMap.get("SubProfitcenter")==null?"":resMap.get("SubProfitcenter").toString());
					bean.setCedingCo(resMap.get("CedingCo")==null?"":resMap.get("CedingCo").toString());
					bean.setBroker(resMap.get("Broker")==null?"":resMap.get("Broker").toString());
					bean.setTreatyName_type(resMap.get("TreatyNametype")==null?"":resMap.get("TreatyNametype").toString());					
					bean.setProposal_No(resMap.get("ProposalNo")==null?"":resMap.get("ProposalNo").toString());
					bean.setUwYear(resMap.get("UwYear")==null?"":resMap.get("UwYear").toString());
					bean.setLayerno(resMap.get("Layerno")==null?"":resMap.get("Layerno").toString());
					bean.setInsDate(resMap.get("InsDate")==null?"":resMap.get("InsDate").toString());
					bean.setExpDate(resMap.get("ExpDate")==null?"":resMap.get("ExpDate").toString());
					bean.setMonth(resMap.get("Month")==null?"":resMap.get("Month").toString());
					bean.setBaseCurrencyId(resMap.get("BaseCurrencyId")==null?"":resMap.get("BaseCurrencyId").toString());
					bean.setBaseCurrencyName(resMap.get("BaseCurrencyName")==null?"":resMap.get("BaseCurrencyName").toString());
					bean.setPolicyBranch(resMap.get("PolicyBranch")==null?"":resMap.get("PolicyBranch").toString());
					bean.setAddress(resMap.get("Address")==null?"":resMap.get("Address").toString());
					bean.setDepartmentId(resMap.get("DepartmentId")==null?"":resMap.get("DepartmentId").toString());
					bean.setDepartment_Name(resMap.get("DepartmentName")==null?"":resMap.get("DepartmentName").toString());
					bean.setAcceptenceDate(resMap.get("AcceptenceDate")==null?"":resMap.get("AcceptenceDate").toString());
					bean.setCommission_view(resMap.get("Commissionview")==null?"":resMap.get("Commissionview").toString());
					bean.setPremium_Reserve_view(resMap.get("PremiumReserveview")==null?"":resMap.get("PremiumReserveview").toString());
					bean.setLoss_reserve_view(resMap.get("Lossreserveview")==null?"":resMap.get("Lossreserveview").toString());
					bean.setProfitCommYN(resMap.get("ProfitCommYN")==null?"":resMap.get("ProfitCommYN").toString());
					bean.setCommissionSurb_view(resMap.get("CommissionSurbview")==null?"":resMap.get("CommissionSurbview").toString());
					bean.setOverRider_view(resMap.get("OverRiderview")==null?"":resMap.get("OverRiderview").toString());
					bean.setBrokerage_view(resMap.get("Brokerageview")==null?"":resMap.get("Brokerageview").toString());
					bean.setTax_view(resMap.get("Taxview")==null?"":resMap.get("Taxview").toString());
					bean.setOtherCostView(resMap.get("OtherCostView")==null?"":resMap.get("OtherCostView").toString());
					bean.setEPI_our_share_view(resMap.get("EPIourshareview")==null?"":resMap.get("EPIourshareview").toString());
					bean.setMd_premium_view(resMap.get("Mdpremiumview")==null?"":resMap.get("Mdpremiumview").toString());
					bean.setAdjustment_premium_temp(resMap.get("Adjustmentpremiumtemp")==null?"":resMap.get("Adjustmentpremiumtemp").toString());
					bean.setCurrencyName(resMap.get("CurrencyName")==null?"":resMap.get("CurrencyName").toString());
					bean.setShareSigned(resMap.get("ShareSigned")==null?"":resMap.get("ShareSigned").toString());
					bean.setRdsExchageRate(resMap.get("RdsExchageRate")==null?"":resMap.get("RdsExchageRate").toString());
					
					}
					savFlg = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
		return savFlg;
	}
	@SuppressWarnings("unchecked")
	public boolean getPremiumDetails(final FaculPremiumBean bean,final String TransactionNo,final String countryId)  {
		boolean savFlg = false;
				Map<String,Object> resMap = xolpremium.getPremiumDetails(bean,TransactionNo,countryId);
				if(resMap!=null) {
				bean.setContNo(resMap.get("ContNo")==null?"":resMap.get("ContNo").toString());
				bean.setTransactionNo(resMap.get("TransactionNo")==null?"":resMap.get("TransactionNo").toString());
				bean.setTransaction(resMap.get("Transaction")==null?"":resMap.get("Transaction").toString()); 
				bean.setBrokerage(resMap.get("Brokerage")==null?"":resMap.get("Brokerage").toString());
				bean.setTax(resMap.get("Tax")==null?"":resMap.get("Tax").toString());
				bean.setMd_premium(resMap.get("Mdpremium")==null?"":resMap.get("Mdpremium").toString());
				bean.setAdjustment_premium(resMap.get("Adjustmentpremium")==null?"":resMap.get("Adjustmentpremium").toString());							
				bean.setRecuirement_premium(resMap.get("Recuirementpremium")==null?"":resMap.get("Recuirementpremium").toString());
				bean.setNetDue(resMap.get("NetDue")==null?"":resMap.get("NetDue").toString());
				bean.setLayerno(resMap.get("Layerno")==null?"":resMap.get("Layerno").toString());
				bean.setEnteringMode(resMap.get("EnteringMode")==null?"":resMap.get("EnteringMode").toString());
				bean.setAccount_Period(resMap.get("AccountPeriod")==null?"":resMap.get("AccountPeriod").toString());
				bean.setCurrencyId(resMap.get("CurrencyId")==null?"":resMap.get("CurrencyId").toString());
				bean.setOtherCost(resMap.get("OtherCost")==null?"":resMap.get("OtherCost").toString());
				bean.setBrokerage_usd(resMap.get("Brokerageusd")==null?"":resMap.get("Brokerageusd").toString());
				bean.setTax_usd(resMap.get("Taxusd")==null?"":resMap.get("Taxusd").toString());
				bean.setMd_premium_usd(resMap.get("Mdpremiumusd")==null?"":resMap.get("Mdpremiumusd").toString());
				bean.setAdjustment_premium_usd(resMap.get("Adjustmentpremiumusd")==null?"":resMap.get("Adjustmentpremiumusd").toString());
				bean.setRecuirement_premium_usd(resMap.get("Recuirementpremiumusd")==null?"":resMap.get("Recuirementpremiumusd").toString());
				bean.setNet_due_usd(resMap.get("Netdueusd")==null?"":resMap.get("Netdueusd").toString());
				bean.setOtherCostUSD(resMap.get("OtherCostUSD")==null?"":resMap.get("OtherCostUSD").toString());
				bean.setInception_Date(resMap.get("InceptionDate")==null?"":resMap.get("InceptionDate").toString());
				bean.setCedentRef(resMap.get("CedentRef")==null?"":resMap.get("CedentRef").toString());
				bean.setRemarks(resMap.get("Remarks")==null?"":resMap.get("Remarks").toString());
				bean.setTotalCredit(resMap.get("TotalCredit")==null?"":resMap.get("TotalCredit").toString());
				bean.setTotalCreditDC(resMap.get("TotalCreditDC")==null?"":resMap.get("TotalCreditDC").toString());
				bean.setTotalDebit(resMap.get("TotalDebit")==null?"":resMap.get("TotalDebit").toString());
				bean.setTotalDebitDC(resMap.get("TotalDebitDC")==null?"":resMap.get("TotalDebitDC").toString());
				bean.setAmendmentDate(resMap.get("AmendmentDate")==null?"":resMap.get("AmendmentDate").toString());
                bean.setWithHoldingTaxOC(resMap.get("WithHoldingTaxOC")==null?"":resMap.get("WithHoldingTaxOC").toString());
                bean.setWithHoldingTaxDC(resMap.get("WithHoldingTaxDC")==null?"":resMap.get("WithHoldingTaxDC").toString());
                bean.setDueDate(resMap.get("DueDate")==null?"":resMap.get("DueDate").toString());
                bean.setCreditsign(resMap.get("Creditsign")==null?"":resMap.get("Creditsign").toString());
                bean.setRi_cession(resMap.get("Ricession")==null?"":resMap.get("Ricession").toString());
                bean.setPredepartment(resMap.get("Predepartment")==null?"":resMap.get("Predepartment").toString());
                bean.setDepartmentId(resMap.get("DepartmentId")==null?"":resMap.get("DepartmentId").toString());
                bean.setTaxDedectSource(resMap.get("TaxDedectSource")==null?"":resMap.get("TaxDedectSource").toString());
                bean.setTaxDedectSourceDc(resMap.get("TaxDedectSourceDc")==null?"":resMap.get("TaxDedectSourceDc").toString());
                bean.setVatPremium(resMap.get("VatPremium")==null?"":resMap.get("VatPremium").toString());
                bean.setVatPremiumDc(resMap.get("VatPremiumDc")==null?"":resMap.get("VatPremiumDc").toString());
                bean.setBrokerageVat(resMap.get("BrokerageVat")==null?"":resMap.get("BrokerageVat").toString());
                bean.setBrokerageVatDc(resMap.get("BrokerageVatDc")==null?"":resMap.get("BrokerageVatDc").toString());
                bean.setDocumentType(resMap.get("DocumentType")==null?"":resMap.get("DocumentType").toString());
                bean.setBonus(resMap.get("Bonus")==null?"":resMap.get("Bonus").toString());
                bean.setBonusDc(resMap.get("BonusDc")==null?"":resMap.get("BonusDc").toString());
				bean.setExchRate(resMap.get("ExchRate")==null?"":resMap.get("ExchRate").toString());
                bean.setGnpiDate(resMap.get("GnpiDate")==null?"":resMap.get("GnpiDate").toString());
                bean.setStatementDate(resMap.get("StatementDate")==null?"":resMap.get("StatementDate").toString());
                bean.setChooseTransaction(resMap.get("ChooseTransaction")==null?"":resMap.get("ChooseTransaction").toString());
                bean.setTransDropDownVal(resMap.get("TransDropDownVal")==null?"":resMap.get("TransDropDownVal").toString());
                bean.setCurrencyName(resMap.get("CurrencyName")==null?"":resMap.get("CurrencyName").toString());
                bean.setCurrency(resMap.get("Currency")==null?"":resMap.get("Currency").toString());
                
                List<Map<String, Object>> retList = (List<Map<String, Object>>) resMap.get("SettlementstatusList");
                if(retList!=null) {
                for(int i=0;i<retList.size();i++) {
                Map<String,Object> setstatus = (Map<String,Object>)retList.get(i);
                bean.setSettlement_status(setstatus.get("Settlementstatus")==null?"":setstatus.get("Settlementstatus").toString());
                }
                }
			}
			savFlg = true;
		return savFlg;
	}
	public List<FaculPremiumBean> getPremiumedList(final FaculPremiumBean beanObj,String type)
	{
		List<FaculPremiumBean> Facullist = new ArrayList<FaculPremiumBean>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = xolpremium.getPremiumedList(beanObj,type);
			if(list!=null) {
				for(int i=0;i<list.size();i++){
					Map<String,Object> resMap = (Map<String, Object>)list.get(i);
					FaculPremiumBean tempBean = new FaculPremiumBean();
					tempBean.setProposal_No(resMap.get("ProposalNo")==null?"":resMap.get("ProposalNo").toString());
					tempBean.setContNo(resMap.get("ContNo")==null?"":resMap.get("ContNo").toString());
					tempBean.setCeding_Company_Name(resMap.get("CedingCompanyName")==null?"":resMap.get("CedingCompanyName").toString());
					tempBean.setBroker(resMap.get("Broker")==null?"":resMap.get("Broker").toString());
					tempBean.setLayerno(resMap.get("Layerno")==null?"":resMap.get("Layerno").toString());
					tempBean.setTransactionNo(resMap.get("TransactionNo")==null?"":resMap.get("TransactionNo").toString());
					tempBean.setAccount_Period(resMap.get("AccountPeriod")==null?"":resMap.get("AccountPeriod").toString());
					tempBean.setTransDropDownVal(resMap.get("TransDropDownVal")==null?"":resMap.get("TransDropDownVal").toString());
					tempBean.setRequestNo(resMap.get("RequestNo")==null?"":resMap.get("RequestNo").toString());
					tempBean.setEndtYN(resMap.get("EndtYN")==null?"":resMap.get("EndtYN").toString());
					tempBean.setInception_Date(resMap.get("InceptionDate")==null?"":resMap.get("InceptionDate").toString());
					tempBean.setStatementDate(resMap.get("StatementDate")==null?"":resMap.get("StatementDate").toString());
					tempBean.setMovementYN(resMap.get("MovementYN")==null?"":resMap.get("MovementYN").toString());
					tempBean.setTransDate(resMap.get("TransDate")==null?"":resMap.get("TransDate").toString());
					tempBean.setTransOpenperiodStatus(resMap.get("TransOpenperiodStatus")==null?"":resMap.get("TransOpenperiodStatus").toString());
					tempBean.setAllocatedYN(resMap.get("AllocatedYN")==null?"":resMap.get("AllocatedYN").toString());
					tempBean.setProductId(resMap.get("ProductId")==null?"":resMap.get("ProductId").toString());
					Facullist.add(tempBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Facullist;
	}
	public boolean premiumEdit(final FaculPremiumBean bean,final String countryId) {
		boolean saveFlag=false;
			List<Map<String, Object>> list = xolpremium.premiumEdit(bean,countryId);
			for(int i=0;i<list.size();i++) {
			Map<String,Object>resMap = (Map<String,Object>)list.get(i);
			
			bean.setBrokerage(resMap.get("Brokerage")==null?"":resMap.get("Brokerage").toString());
			bean.setTax((resMap.get("Tax")==null?"":resMap.get("Tax").toString()));
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
			bean.setReceipt_no(resMap.get("Receiptno")==null?"":resMap.get("Receiptno").toString());
		    bean.setMd_premium(resMap.get("Mdpremium")==null?"":resMap.get("Mdpremium").toString());
		    bean.setAdjustment_premium(resMap.get("Adjustmentpremium")==null?"":resMap.get("Adjustmentpremium").toString());
		    bean.setRecuirement_premium(resMap.get("Recuirementpremium")==null?"":resMap.get("Recuirementpremium").toString());
		    bean.setCommission(resMap.get("Commission")==null?"":resMap.get("Commission").toString());
		    bean.setXl_Cost(resMap.get("XlCost")==null?"":resMap.get("XlCost").toString());
		    bean.setCliam_portfolio_out(resMap.get("Cliamportfolioout")==null?"":resMap.get("Cliamportfolioout").toString());
		    bean.setPremium_Reserve_Released(resMap.get("PremiumReserveReleased")==null?"":resMap.get("PremiumReserveReleased").toString());
		    bean.setOtherCost(resMap.get("OtherCost")==null?"":resMap.get("OtherCost").toString());
			bean.setInterest(resMap.get("Interest")==null?"":resMap.get("Interest").toString());
			bean.setOsClaimsLossUpdateOC(resMap.get("OsClaimsLossUpdateOC")==null?"":resMap.get("OsClaimsLossUpdateOC").toString());
			bean.setOverrider(resMap.get("Overrider")==null?"":resMap.get("Overrider").toString());
			bean.setOverriderUSD(resMap.get("OverriderUSD")==null?"":resMap.get("OverriderUSD").toString());	
            bean.setWithHoldingTaxOC(resMap.get("WithHoldingTaxOC")==null?"":resMap.get("WithHoldingTaxOC").toString());
            bean.setPredepartment(resMap.get("Predepartment")==null?"":resMap.get("Predepartment").toString());
            bean.setDepartmentId(resMap.get("DepartmentId")==null?"":resMap.get("DepartmentId").toString());
            bean.setTaxDedectSource((resMap.get("TaxDedectSource")==null?"":resMap.get("TaxDedectSource").toString()));
            bean.setServiceTax(resMap.get("ServiceTax")==null?"":resMap.get("ServiceTax").toString());
            bean.setBonus(resMap.get("Bonus")==null?"":resMap.get("Bonus").toString());
            bean.setTotalCredit((resMap.get("TotalCredit")==null?"":resMap.get("TotalCredit").toString()));
			bean.setTotalDebit((resMap.get("TotalDebit")==null?"":resMap.get("TotalDebit").toString()));
            bean.setWithHoldingTaxDC(resMap.get("WithHoldingTaxDC")==null?"":resMap.get("WithHoldingTaxDC").toString());
            bean.setTaxDedectSourceDc(resMap.get("TaxDedectSourceDc")==null?"":resMap.get("TaxDedectSourceDc").toString());
            bean.setVatPremium(resMap.get("VatPremium")==null?"":resMap.get("VatPremium").toString());
            bean.setVatPremiumDc(resMap.get("VatPremiumDc")==null?"":resMap.get("VatPremiumDc").toString());
            bean.setBrokerageVat(resMap.get("BrokerageVat")==null?"":resMap.get("BrokerageVat").toString());
            bean.setBrokerageVatDc(resMap.get("BrokerageVatDc")==null?"":resMap.get("BrokerageVatDc").toString());
            bean.setBonusDc(resMap.get("BonusDc")==null?"":resMap.get("BonusDc").toString());
			bean.setBrokerage_usd(resMap.get("Brokerageusd")==null?"":resMap.get("Brokerageusd").toString());
			bean.setTax_usd(resMap.get("Taxusd")==null?"":resMap.get("Taxusd").toString());
			bean.setMd_premium_usd(resMap.get("Mdpremiumusd")==null?"":resMap.get("Mdpremiumusd").toString());
			bean.setAdjustment_premium_usd(resMap.get("Adjustmentpremiumusd")==null?"":resMap.get("Adjustmentpremiumusd").toString());
			bean.setRecuirement_premium_usd(resMap.get("Recuirementpremiumusd")==null?"0":resMap.get("Recuirementpremiumusd").toString());
			bean.setNet_due_usd(resMap.get("Netdueusd")==null?"":resMap.get("Netdueusd").toString());
			bean.setOtherCostUSD(resMap.get("OtherCostUSD")==null?"":resMap.get("OtherCostUSD").toString());
			bean.setTotalCreditDC(resMap.get("TotalCreditDC")==null?"":resMap.get("TotalCreditDC").toString());
			bean.setTotalDebitDC(resMap.get("TotalDebitDC")==null?"":resMap.get("TotalDebitDC").toString());
            bean.setDueDate(resMap.get("DueDate")==null?"":resMap.get("DueDate").toString());
            bean.setCreditsign(resMap.get("Creditsign")==null?"":resMap.get("Creditsign").toString());	
            bean.setCurrencyId(resMap.get("CurrencyId")==null?"":resMap.get("CurrencyId").toString());
			bean.setCurrency(resMap.get("Currency")==null?"":resMap.get("Currency").toString());
			bean.setExchRate(resMap.get("ExchRate")==null?"":resMap.get("ExchRate").toString());
			bean.setAccount_Period(resMap.get("AccountPeriod")==null?"":resMap.get("AccountPeriod").toString());
            if(!"transEdit".equalsIgnoreCase(bean.getMode())) {
		        bean.setTransaction(resMap.get("Transaction")==null?"":resMap.get("Transaction").toString()); 
				bean.setAccount_Period_year(resMap.get("AccountPeriodyear")==null?"":resMap.get("AccountPeriodyear").toString());
				bean.setEnteringMode(resMap.get("EnteringMode")==null?"":resMap.get("EnteringMode").toString().trim());
			    bean.setInstlmentNo(resMap.get("InstlmentNo")==null?"":resMap.get("InstlmentNo").toString());
			    bean.setInception_Date(resMap.get("InceptionDate")==null?"":resMap.get("InceptionDate").toString());
			    bean.setCedentRef(resMap.get("CedentRef")==null?"":resMap.get("CedentRef").toString());
				bean.setRemarks(resMap.get("Remarks")==null?"":resMap.get("Remarks").toString());
				bean.setAmendmentDate(resMap.get("AmendmentDate")==null?"":resMap.get("AmendmentDate").toString());	
				bean.setRi_cession(resMap.get("Ricession")==null?"":resMap.get("Ricession").toString());
				bean.setStatus(resMap.get("Status")==null?"":resMap.get("Status").toString());
				bean.setClaims_paid(resMap.get("Claimspaid")==null?"":resMap.get("Claimspaid").toString());
				bean.setDocumentType(resMap.get("DocumentType")==null?"":resMap.get("DocumentType").toString());
				bean.setGnpiDate(resMap.get("GnpiDate")==null?"":resMap.get("GnpiDate").toString());
		        bean.setStatementDate(resMap.get("StatementDate")==null?"":resMap.get("StatementDate").toString());
		        bean.setChooseTransaction(resMap.get("ChooseTransaction")==null?"":resMap.get("ChooseTransaction").toString() );
		        bean.setTransDropDownVal(resMap.get("TransDropDownVal")==null?"":resMap.get("TransDropDownVal").toString());
            }
		}
			saveFlag = true;
		return saveFlag;
	}
	public boolean getPreList(final FaculPremiumBean bean) {
		boolean savFlg = false;
		try {
					Map<String,Object> resMap = xolpremium.getPreList(bean);
					if(resMap!=null) {
					bean.setContNo(resMap.get("ContNo")==null?"":resMap.get("ContNo").toString());
					bean.setDepartment_Name(resMap.get("DepartmentName")==null?"":resMap.get("DepartmentName").toString());
					bean.setUwYear(resMap.get("UwYear")==null?"":resMap.get("UwYear").toString());
					bean.setCeding_Company_Name(resMap.get("CedingCompanyName")==null?"":resMap.get("CedingCompanyName").toString());
					bean.setLayerno(resMap.get("Layerno")==null?"":resMap.get("Layerno").toString());
					bean.setBrokername(resMap.get("Brokername")==null?"":resMap.get("Brokername").toString());
					bean.setCeaseStatus(resMap.get("CeaseStatus")==null?"":resMap.get("CeaseStatus").toString());
					
				}
				savFlg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}
		
		
		
//		LOGGER.info("PremiumDAOImpl getPreList || Enter");
//		String query="";
//		boolean saveFlag=false;
//		String[] args = null;
//		args = new String[2];
//		args[0] = bean.getContNo();
//		args[1] = bean.getLayerno();
//		LOGGER.info("Layer No.in preLIst===>" + bean.getLayerno());
//		query=getQuery(DBConstants.PREMIUM_SELECT_XOLPRELIST);
//		LOGGER.info("Select Query=>"+query);
//		LOGGER.info("Args=>"+StringUtils.join(args,","));
//		List list=this.mytemplate.query(query, args,new RowMapper() {
//			public Object mapRow(ResultSet preList, int rowNum) throws SQLException {
//				bean.setContNo(preList.getString("CONTRACT_NO"));
//				bean.setDepartment_Name(preList.getString("TMAS_DEPARTMENT_NAME"));
//				bean.setUwYear(preList.getString("UW_YEAR"));
//				bean.setCeding_Company_Name(preList.getString("COMPANY_NAME"));
//				bean.setLayerno(preList.getString("LAYER_NO"));
//				bean.setBrokername(preList.getString("FIRST_NAME"));
//				return bean;
//			}
//		});
//		if(list!=null && list.size()>0)
//			saveFlag = true;
//		if(StringUtils.isNotBlank(bean.getContNo())){
//			try{
//			bean.setCeaseStatus((String)this.mytemplate.queryForObject("select CEASE_STATUS from position_master pm where CONTRACT_NO=? AND LAYER_NO =? and  pm.amend_id=(Select Max(Amend_id) From Position_master p where p.CONTRACT_NO=pm.CONTRACT_NO  AND p.LAYER_NO = pm.LAYER_NO) AND CONTRACT_STATUS='A'", new Object[]{bean.getContNo(),bean.getLayerno()}, String.class));
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			}
//		LOGGER.info("PremiumDAOImpl getPreList || Exit");
//		return saveFlag;
//	}

	public boolean premiumUpdateMethod(final FaculPremiumBean beanObj){
		boolean saveFlag = false;
		try {
		Map<String,Object>	resMap=xolpremium.premiumUpdateMethod(beanObj);
			if(resMap!=null) {
				beanObj.setRequestNo(resMap.get("RequestNo")==null?"":resMap.get("RequestNo").toString());
				beanObj.setTransactionNo(resMap.get("TransactionNo")==null?"":resMap.get("TransactionNo").toString());
			}
			saveFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveFlag;
	}
	public List<Map<String,Object>> mdInstallmentDates(final String contNo,final String layerNo,String sourceId,String productId) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = xolpremium.mdInstallmentDates(contNo,layerNo,sourceId,productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	private static String getModeOfTransaction(final String Value,final FaculPremiumBean beanObj) {
	LOGGER.info("PremiumDAOImpl getModeOfTransaction || Enter");
	LOGGER.info("Value=>"+Value);
	LOGGER.info("Entering Mode=>"+beanObj.getEnteringMode());
	LOGGER.info("ShareSigned=>"+beanObj.getShareSigned());
	DecimalFormat twoDForm = new DecimalFormat("#.##");
	String result="0";
	double shareSigned=0.0;
	if(beanObj.getEnteringMode()!=null)
	{
		if("1".equalsIgnoreCase(beanObj.getEnteringMode()))
		{
			shareSigned=Double.parseDouble(beanObj.getShareSigned());
		}
		else if("2".equalsIgnoreCase(beanObj.getEnteringMode()))
		{
			shareSigned=100;
		}
		//shareSigned=100;	
		LOGGER.info("Value==>"+Value);
		if(!"".equalsIgnoreCase(Value))
		{
				double finalValue=Double.parseDouble(Value) *shareSigned/100;
				LOGGER.info("Final Value==>"+finalValue);
				result=String.valueOf(Double.valueOf(twoDForm.format(finalValue)));
		} 
	}
	LOGGER.info("PremiumDAOImpl getModeOfTransaction || Exit");
	return result;
	}
	public List getMandDInstallments(String contNo, String layerNo,String productId)
			{
		LOGGER.info("PremiumDAOImpl getMandDInstallments || Enter");
		List list=null;
		try{
			String query="";
			if("3".equalsIgnoreCase(productId)){
				query=getQuery(DBConstants.PREMIUM_SELECT_MDINSTALLMENTS);
			}else{
			 query=this.getQuery("XOL_PREMIUM_SELECT_MDINSTALLMENTS");
				}
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

	public String GetInstalmentAmount(String contractNo,String layerNo,String getAmount){
		String result = null;
		try {
			result = xolpremium.GetInstalmentAmount(contractNo,layerNo,getAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return result;
		}
	public String getRPPremiumOC(String contractNo, String layerNo,String productId)
			{
		LOGGER.info("PremiumDAOImpl getRPPremiumOC || Enter");
		String  string=null;
		try{
			String query="";
			if("3".equalsIgnoreCase(productId)){
		 	query=getQuery(DBConstants.PREMIUM_SELECT_RPPREMIUMOC);
			}else{
			 query=this.getQuery("XOL_PREMIUM_SELECT_RPPREMIUMOC");
			}
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

	public List getSPRetroList(String contNo,String productId, String layerNo)
			{
		LOGGER.info("PremiumDAOImpl getSPRetroList || Enter");
		List list=null;
		try{
			String query="";
			Object args[]=null;		
			args=new Object[1];
			args[0]=contNo;
			query=this.getQuery(DBConstants.PREMIUM_SELECT_GETTREATYSPRETRO);	
			 LOGGER.info("Select SpRetro Query=>"+query);
			 LOGGER.info("Product Code No=>"+productId);
			 LOGGER.info("Contract No=>"+contNo);
			 LOGGER.info("Layer No=>"+layerNo);
			 list=this.mytemplate.queryForList(query, args);
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);		
		}
		LOGGER.info("PremiumDAOImpl getSPRetroList || Exit List Size"+list.size());
		return list;
	}

	public List getRetroContracts(FaculPremiumBean beanObj) {
		// TODO Auto-generated method stub
		LOGGER.info("PremiumDAOImpl getRetroContracts() || Enter");
		List list=null;
		try{
			String query="";
			Object args[]=null;
			args=new Object[2];
			args[0]=beanObj.getProposal_No();
			args[1]=beanObj.getNoOfRetro();
			query=this.getQuery(DBConstants.PREMIUM_SELECT_INSDETAILS);
			LOGGER.info("Select SpRetro Query=>"+query);
			LOGGER.info("Proposal No=>"+beanObj.getProposal_No());
			LOGGER.info("No of Retro=>"+beanObj.getNoOfRetro());
			list=this.mytemplate.queryForList(query, args);
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);	
		}
		LOGGER.info("PremiumDAOImpl getRetroContracts() || Exit List Size"+list.size());
		return list;
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

	public List<Map<String, Object>> getClaimNosDropDown(String contNo)
			{
			LOGGER.info("PremiumDAOImpl getClaimNosDropDown() || Enter Contract No=>"+contNo);
		 	List<Map<String, Object>> claimNos=new ArrayList<Map<String,Object>>();
			try{
			 	String query=getQuery(DBConstants.PREMIUM_SELECT_GETCLAIMNODROPDOWN);
			    LOGGER.info("Select Query=>"+query);
			    LOGGER.info("Args[0]=>"+contNo);
			    claimNos=this.mytemplate.queryForList(query,new Object[]{contNo});				
			}catch(Exception e){
				LOGGER.debug("Exception "+e);			
			}
		    LOGGER.info("PremiumDAOImpl getClaimNosDropDown() || Exit List size=>"+claimNos.size());
		    return claimNos;
	}

	public boolean getCashLossUpdateValidation(String contractNo,
			String transactionNo, String claimNo,String cashLOossUpdateOc,String excRate) {
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
	public List<FaculPremiumBean> getAllocatedList(final FaculPremiumBean beanObj)
	{
		List<FaculPremiumBean> list = new ArrayList<FaculPremiumBean>();
		try {
					Map<String,Object> resMap = xolpremium.getAllocatedList(beanObj);
					if(resMap!=null) {
					FaculPremiumBean tempBean = new FaculPremiumBean();
					tempBean.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
					tempBean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
					tempBean.setProductname(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
					tempBean.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
					tempBean.setPayamount(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
					tempBean.setCurrencyValue(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
					tempBean.setAlloccurrencyid(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
					tempBean.setStatus(("R".equals(resMap.get("STATUS")==null?"":resMap.get("STATUS").toString())?"Reverted":"Allocated"));			
					tempBean.setPay_rec_no(resMap.get("RECEIPT_NO")==null?"":resMap.get("RECEIPT_NO").toString());
					tempBean.setSettlementType(resMap.get("TRANS_TYPE")==null?"":resMap.get("TRANS_TYPE").toString());
					tempBean.setAllocateType(resMap.get("ALLOCATE_TYPE")==null?"":resMap.get("ALLOCATE_TYPE").toString());
					tempBean.setTotalAmount(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
					list.add(tempBean);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List getBrokerAndCedingName(FaculPremiumBean beanObj) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = xolpremium.getBrokerAndCedingName(beanObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public String GetPreviousPremium(FaculPremiumBean bean) {
		String premium="";
		try {
			premium = xolpremium.GetPreviousPremium(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return premium;
	}
	public String GetContractPremium(FaculPremiumBean bean) {
		String premium="";
		try {
			premium = xolpremium.GetContractPremium(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return premium;
	}
	public List<Map<String, Object>> currencyList(FaculPremiumBean bean) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = xolpremium.currencyList(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void insertBonus(FaculPremiumBean bean) {
		try{
			String qry=getQuery("BONUS_MAX_AMEND_ID");
			Object arg[]=new Object[6];
			arg[0] = bean.getTransactionNo();
			arg[1] = bean.getTransaction();
			arg[2] = bean.getBranchCode();
			arg[3] = bean.getProductId();
			arg[4] = bean.getContNo();
			arg[5] = bean.getLayerno();
			String amendId = this.mytemplate.queryForObject(qry,arg,String.class);
			
			if(bean.getPremiumOC()!=null){
				String query=getQuery("BONUS_CAL_INSERT");
				Object args[]=new Object[17];
			for(int i=0;i<bean.getExchRatePrem().size();i++){
				args[0]=bean.getContNo();
				args[1]=bean.getLayerno();
				args[2] = bean.getProductId();
				args[3]=StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
				args[4]=StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
				args[5]=bean.getPremiumOC().get(i).replaceAll(",", "");
				args[6]=bean.getClaimPaidOC().get(i).replaceAll(",", "");
				args[7]=bean.getClaimOutStandingOC().get(i).replaceAll(",", "");
				args[8]=bean.getClaimRatioOC().get(i).replaceAll(",", "");
				args[9]=bean.getBonusOC().get(i).replaceAll(",", "");
				args[10]=bean.getBonusPaidOCTillDate().get(i).replaceAll(",", "");
				args[11]=bean.getBonusAdjOC().get(i).replaceAll(",", "");
				args[12] = bean.getPreCurrencylist().get(i).replaceAll(",","");
				args[13]=bean.getExchRatePrem().get(i).replaceAll(",", "");
				args[14] = amendId;
				args[15] = bean.getLoginId();
				args[16]=bean.getBranchCode();
				LOGGER.info("Query==>"+query);
				LOGGER.info("obj==>"+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);
			}
			if(bean.getConPremiumOC()!=null&&StringUtils.isNotBlank(bean.getConPremiumOC())){
				args[0]=bean.getContNo();
				args[1]=bean.getLayerno();
				args[2] = bean.getProductId();
				args[3]=StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
				args[4]=StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
				args[5]=bean.getConPremiumOC().replaceAll(",", "");
				args[6]=bean.getConClaimPaidOC().replaceAll(",", "");
				args[7]=bean.getConClaimOutStandingOC().replaceAll(",", "");
				args[8]=bean.getConClaimRatioOC().replaceAll(",", "");
				args[9]=bean.getConbonusOC().replaceAll(",", "");
				args[10]=bean.getConbonusPaidOC().replaceAll(",", "");
				args[11]=bean.getConbonusAdjOC().replaceAll(",", "");
				args[12] ="";
				args[13]="";
				args[14] = amendId;
				args[15] = bean.getLoginId();
				args[16]=bean.getBranchCode();
				LOGGER.info("Query==>"+query);
				LOGGER.info("obj==>"+StringUtils.join(args, ","));
				this.mytemplate.update(query,args);
			}
			}
			
		}catch(Exception e){
			LOGGER.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}

	}

	public String GetAdjPremium(FaculPremiumBean bean) {
		String result="";
		try {
			result = xolpremium.GetAdjPremium(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getDepartmentId(FaculPremiumBean bean) {
		String DepartId = "";
		String proposalNo = "";
		try {
			Map<String,Object> resMap = xolpremium.getDepartmentId(bean);
			if(resMap!=null) {
				DepartId = resMap.get("DepartmentId") == null ? "": resMap.get("DepartmentId").toString();
				proposalNo= resMap.get("ProposalNo") == null ? "": resMap.get("ProposalNo").toString();;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DepartId;
	}

	@Override
	public void getVatInfo(FaculPremiumBean bean) {
		String vatPremium = "";
		String brokerageVat = "";
		try {
			Map<String,Object> resMap = xolpremium.getVatInfo(bean);
			if(resMap!=null) {
				vatPremium = resMap.get("PremiumVat") == null ? "": resMap.get("PremiumVat").toString();
				brokerageVat= resMap.get("BrokerageVat") == null ? "": resMap.get("BrokerageVat").toString();;	
			}
			bean.setVatPremium(vatPremium);
			bean.setBrokerageVat(brokerageVat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Map<String, Object>> getRipremiumList(FaculPremiumBean bean) {
		return xolpremium.getRipremiumList(bean);
	}
	@Override
	public void updateRiStatus(FaculPremiumBean bean) {
		xolpremium.updateRiStatus(bean);
	}
}


