package com.maan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.maan.bean.FaculPremiumBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.CommonDAO;
import com.maan.dao.ProportionalPremiumDAO;
import com.opensymphony.xwork2.ActionContext;

public class ProportionalPremiumDaoImpl extends MyJdbcTemplate implements ProportionalPremiumDAO {
	DropDownControllor dropDownController=new DropDownControllor();
	private static final Logger LOGGER = LogUtil.getLogger(ProportionalPremiumDaoImpl.class);
	public boolean premiumInsertMethod(final FaculPremiumBean beanObj, String countryId){
		LOGGER.info("PremiumDAOImpl premiumInsertMethod || Enter");
		boolean saveFlag = false;
		try {
				String query="";
				int result;
				String[] args = insertArguments(beanObj);
			 	String netDueOc="0";
			 	String transNo="";
			 	query=getQuery("PREMIUM_INSERT_TREATYPREMIUM_TEMP");
		 		netDueOc=args[33];
		 		transNo=args[1];
			 	LOGGER.info("Insert Query==>"+query);
			 	result=this.mytemplate.update(query, args);
			 	LOGGER.info("Insert Result==>"+result);
			 	if("submit".equalsIgnoreCase(beanObj.getButtonStatus())){
			 		beanObj.setTransactionNo(new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction()));
					query = getQuery("FAC_TEMP_STATUS_UPDATE");
					args = new String[5];
			 		args[0] = "A";
			 		args[1] = beanObj.getLoginId();
			 		args[2] =beanObj.getTransactionNo()==null?"":beanObj.getTransactionNo();
			 		args[3]= beanObj.getRequestNo() ;
			 		args[4]= beanObj.getBranchCode() ;
			 		this.mytemplate.update(query,args);
			 		getTempToMainMove(beanObj,netDueOc);
			 		saveFlag = true;
			 	}
				InsertPremiumReserved(beanObj,transNo,countryId);
				InsertLossReserved(beanObj,transNo,countryId);
		}
		catch (Exception exe) {
			LOGGER.debug(""+exe);
			exe.printStackTrace();
		}
		LOGGER.info("PremiumDAOImpl premiumInsertMethod || Exit");
		return saveFlag;
	}


private void getTempToMainMove(FaculPremiumBean beanObj, String netDueOc) {
	try{
		String query="";
		String[] args = null;
		if(!"Main".equalsIgnoreCase(beanObj.getTableType())){
			
			query =getQuery("FAC_PREMIUM_TEMP_TO_MAIN");
	 		args = new String[2];
	 		args[0] = beanObj.getRequestNo();
	 		args[1] = beanObj.getBranchCode();
	 		this.mytemplate.update(query,args);
				query=getQuery(DBConstants.PREMIUM_SP_RETROSPLIT);
				LOGGER.info("SP Name==>"+query);
				args = new String[16];
				args[0]=beanObj.getContNo();
				args[1]=StringUtils.isEmpty(beanObj.getLayerno())?"0":beanObj.getLayerno();
				args[2]=beanObj.getProductId();
				args[3]=beanObj.getTransactionNo();
				args[4]=beanObj.getTransaction();
				args[5]=beanObj.getCurrencyId();
				args[6]=beanObj.getExchRate();
				args[7]=beanObj.getBranchCode();
				args[8]="P";
				args[9]=beanObj.getAmendmentDate()==null?"":beanObj.getAmendmentDate();
				args[10]="";
				args[11]="";
				args[12]="";
				args[13]="";
				args[14]="";
				args[15]=beanObj.getRi_cession();
				for(int i=0;i<args.length;i++)
				LOGGER.info("Args["+i+"]==>"+args[i]);
				int spresult=this.mytemplate.update(query,args);
				LOGGER.info("SP Result==>"+spresult);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
		
	}


@SuppressWarnings("unchecked")
public boolean contractDetails(final FaculPremiumBean bean,final String countryId){
	LOGGER.info("PremiumDAOImpl contractDetails || Enter");
	 String query="";
	 String[] args=null;
	 boolean saveFlag=false;
	 try {
		 query+=getQuery(DBConstants.PREMIUM_SELECT_TREATYCONTDET1);
		 query+=" AND RK.RSK_DEPTID=?";
		 query+=getQuery(DBConstants.PREMIUM_SELECT_TREATYCONTDET2);
		 query+=" and RSK_DEPTID=RK.RSK_DEPTID";
		 query+=getQuery(DBConstants.PREMIUM_SELECT_TREATYCONTDET3);
		 	args =new String[10];
		 	args[0] = bean.getProductId();
			args[1] = bean.getContNo();
			args[2] = bean.getDepartmentId();
			args[3] = bean.getBranchCode();
			args[4] = bean.getBranchCode();
			//if("add".equalsIgnoreCase(bean.getMode())){
			//args[3] = bean.getProductId();
			//}
			//else{
			args[5] = bean.getProductId();
			//}
			args[6] = bean.getBranchCode();
			args[7] = bean.getBranchCode();
			args[8] = bean.getContNo();
			args[9] = bean.getBranchCode();
			LOGGER.info("Select Query=>"+query);
			for(int i=0;i<args.length;i++)
			LOGGER.info("Args["+i+"]=>"+args[i]);
			List list = this.mytemplate.query(query, args,new RowMapper() {
					public Object mapRow(ResultSet contDet, int rowNum) throws SQLException {
					bean.setContNo(contDet.getString("RSK_CONTRACT_NO"));
					bean.setAmendId(contDet.getString("RSK_ENDORSEMENT_NO"));
					bean.setProfit_Center(contDet.getString("TMAS_PFC_NAME"));
					bean.setSubProfit_center(contDet.getString("RSK_SPFCID"));
					if(!"ALL".equalsIgnoreCase(bean.getSubProfit_center())){
						bean.setSubProfit_center(contDet.getString("TMAS_SPFC_NAME")==null?"ALL":contDet.getString("TMAS_SPFC_NAME"));
						}
					//bean.setSubProfit_center(contDet.getString("TMAS_SPFC_NAME"));
					bean.setCedingCo(contDet.getString("COMPANY"));
					bean.setBroker(contDet.getString("BROKER"));
					bean.setTreatyName_type(contDet.getString("RSK_TREATYID"));
					bean.setProposal_No(contDet.getString("RSK_PROPOSAL_NUMBER"));
					bean.setUwYear(contDet.getString("RSK_UWYEAR"));
					bean.setLayerno(contDet.getString("RSK_LAYER_NO"));
					bean.setInsDate(contDet.getString("INS_DATE"));
					bean.setExpDate(contDet.getString("EXP_DATE"));
					bean.setMonth(contDet.getString("MONTH"));
					bean.setBaseCurrencyId(contDet.getString("RSK_ORIGINAL_CURR"));
					bean.setBaseCurrencyName(contDet.getString("CURRENCY_NAME"));
					bean.setPolicyBranch(contDet.getString("TMAS_POL_BRANCH_NAME"));
					bean.setAddress(contDet.getString("Address"));
					bean.setDepartmentId(contDet.getString("RSK_DEPTID"));
					String count="";
					if("2".equals(bean.getProductId())){
						count=new DropDownControllor().getCombinedClass(bean.getBranchCode(),bean.getProductId(),bean.getDepartmentId());
					}
					if(StringUtils.isBlank(count)){
					bean.setPredepartment(contDet.getString("RSK_DEPTID"));
					bean.setConsubProfitId(contDet.getString("RSK_SPFCID"));
					}
					bean.setTreatyType(contDet.getString("TREATYTYPE"));
					bean.setBusinessType(contDet.getString("INWARD_BUS_TYPE")==null?"":contDet.getString("INWARD_BUS_TYPE"));
					bean.setAcceptenceDate(contDet.getString("RSK_ACCOUNT_DATE"));
					return bean;
				}
			});
			if(list!=null && list.size()>0)
				saveFlag = true;
				args=new String[2];
				args[0] = bean.getProposal_No();
				args[1] = bean.getProposal_No();
				query=getQuery(DBConstants.PREMIUM_SELECT_COMMISSIONDETAILS);
				LOGGER.info("Select Query=>"+query);
				this.mytemplate.query(query, args,new RowMapper() {
					public Object mapRow(ResultSet commission, int rowNum) throws SQLException {
						bean.setCommission_view(DropDownControllor.formatter(commission.getString("RSK_COMM_QUOTASHARE")));
						bean.setPremium_Reserve_view(DropDownControllor.formatter(commission.getString("RSK_PREMIUM_RESERVE")));
						bean.setLoss_reserve_view(DropDownControllor.formatter(commission.getString("RSK_LOSS_RESERVE")));
						bean.setProfitCommYN(DropDownControllor.formatter(commission.getString("RSK_PROFIT_COMM")));
						bean.setCommissionSurb_view(DropDownControllor.formatter(commission.getString("RSK_COMM_SURPLUS")));
						bean.setOverRider_view(DropDownControllor.formatter(commission.getString("RSK_OVERRIDER_PERC")));
						bean.setBrokerage_view(DropDownControllor.formatter(commission.getString("RSK_BROKERAGE")));
						bean.setBrokerage_view((commission.getString("RSK_BROKERAGE")));
						bean.setTax_view(DropDownControllor.formatter(commission.getString("RSK_TAX")));
						bean.setOtherCostView(DropDownControllor.formatter(commission.getString("RSK_OTHER_COST")));
						bean.setOurAssessmentOfOrginal(commission.getString("RSK_OUR_ASS_ACQ_COST")==null?"0.00":DropDownControllor.formatter(commission.getString("RSK_OUR_ASS_ACQ_COST")));
						bean.setPremiumReserve(commission.getString("RSK_PREMIUM_RESERVE"));
						
						return null;
					}
				});
				args[0] = bean.getProposal_No();
				args[1] = bean.getProposal_No();
				query=getQuery(DBConstants.PREMIUM_SELECT_TREATYPROPOSALDETAILS);
				this.mytemplate.query(query, args,new RowMapper() {
					public Object mapRow(ResultSet proposalDetails, int rowNum) throws SQLException {
						bean.setShareSigned(proposalDetails.getString("RSK_SHARE_SIGNED"));
						bean.setPremiumQuota_view(DropDownControllor.formatter(proposalDetails.getString("RSK_PREMIUM_QUOTA_SHARE")));
						bean.setPremiumsurp_view(DropDownControllor.formatter(proposalDetails.getString("RSK_PREMIUM_SURPULS")));
						bean.setXl_cost_view(DropDownControllor.formatter(proposalDetails.getString("RSK_XLCOST_OS_OC")));
						String eps = (proposalDetails.getString("RSK_EPI_OSOE_OC"));
						bean.setRdsExchageRate(proposalDetails.getString("RSK_EXCHANGE_RATE"));
						double val= Double.parseDouble(eps)/Double.parseDouble(bean.getRdsExchageRate());
						bean.setEpioc(DropDownControllor.formatter(Double.toString(val)));
						return null;
					}
				});
				query=getQuery(DBConstants.PREMIUM_SELECT_GETOSCLAIMLOSSUPDATE);
				Object[] obj=new Object[0];
				LOGGER.info("Trans No=>"+bean.getTransactionNo());
				if(StringUtils.isEmpty(bean.getTransactionNo()))
				{
					obj=new Object[3];
					obj[0]= bean.getBranchCode();
					obj[1] = bean.getContNo();
					obj[2] = bean.getContNo();
				}
				else
				{
					query+=" "+getQuery(DBConstants.PREMIUM_SELECT_GETOSCLAIMLOSSUPDATEEDIT);
					obj=new Object[4];
					obj[0] = bean.getBranchCode();
					obj[1] = bean.getContNo();
					obj[2] = bean.getContNo();
					obj[3] = bean.getTransactionNo();
				}
				query+=getQuery(DBConstants.PREMIUM_SELECT_GETOSCLAIMLOSSUPDATENEW);
				LOGGER.info("Select Query==>"+query);
				int i=0;
				for(Object o:obj){
					LOGGER.info("Obj["+i+"]==>"+o);
					i++;
				}
				ArrayList li=null;
				li=(ArrayList)this.mytemplate.queryForList(query,obj);
				final Map<String,Double> osClaimLossMap = new HashMap();
				if(li!=null && li.size()>0){
					for (int j = 0; j < li.size(); j++) {
						final Map tempMap = (Map) li.get(j);
						osClaimLossMap.put("Previous Outstanding Loss position For "+tempMap.get("CURRENCY_NAME").toString(), Double.parseDouble((tempMap.get("OSCLAIM_LOSSUPDATE_OC")==null?"0":tempMap.get("OSCLAIM_LOSSUPDATE_OC").toString())));
					}
					LOGGER.info("List Size==>"+li.size());
					LOGGER.info("Map Size==>"+osClaimLossMap.size());
				}
				bean.setOsClaimLoss(osClaimLossMap);

				//For Cash Loss Credit update added by sathish =>Start
				if(StringUtils.isNotBlank(bean.getTransactionNo())){
					query=getQuery(DBConstants.PREMIUM_SELECT_CASHLOSSCREDITUPDATE);
					LOGGER.info("Select Query=>"+query);
					obj=new Object[2];
					obj[0]=bean.getContNo();
					obj[1]=bean.getTransactionNo();
					LOGGER.info("Obj[0]==>"+bean.getContNo()+"Obj[1]==>"+bean.getTransactionNo());
					List claimlist = this.mytemplate.queryForList(query,obj);
					if(claimlist!=null){
						List<String> claimno=new ArrayList<String>();
						List<String> currencyid=new ArrayList<String>();
						List<String> cashoc=new ArrayList<String>();
						List<String> cashdc=new ArrayList<String>();
						for(int k=0;k<claimlist.size();k++){
							Map temp=(Map)claimlist.get(k);
							claimno.add(temp.get("CLAIM_NO")==null?"":temp.get("CLAIM_NO").toString());
							currencyid.add(temp.get("CURRENCY_ID")==null?"":temp.get("CURRENCY_ID").toString());
							cashoc.add(temp.get("CASH_LOSS_CREDIT_OC")==null?"":temp.get("CASH_LOSS_CREDIT_OC").toString());
							cashdc.add(temp.get("CASH_LOSS_CREDIT_DC")==null?"":temp.get("CASH_LOSS_CREDIT_DC").toString());
							//bean.getRequest().setAttribute("claimNo"+j, map.get("CLAIM_NO").toString());
							//bean.getRequest().setAttribute("currencyId"+j, map.get("CURRENCY_ID").toString());
							//bean.getRequest().setAttribute("cashLossCreditOC"+j, map.get("CASH_LOSS_CREDIT_OC").toString());
							//bean.getRequest().setAttribute("cashLossCreditDC"+j, map.get("CASH_LOSS_CREDIT_DC").toString());
						}
						bean.setClaimNo(claimno);
						bean.setCurrencyIds(currencyid);
						bean.setCashLossCreditOC(cashoc);
						bean.setCashLossCreditDC(cashdc);
					}
				}
				query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY_NAME);
			   	bean.setCurrencyName((String)this.mytemplate.queryForObject(query,new Object[]{bean.getBranchCode()},String.class));
			   	query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFPAIDPREMIUM);
				LOGGER.info("Select Query==>"+query);
				LOGGER.info("Arg[0]====>"+bean.getContNo());
				bean.setSum_of_paid_premium((String)this.mytemplate.queryForObject(query,new Object[]{bean.getContNo()},String.class));
				query=getQuery("GETSETTLEMET_STATUS");
				List<Map<String,Object>> premlist = new ArrayList<Map<String,Object>>();
				premlist = this.mytemplate.queryForList(query,new Object[]{bean.getContNo()});
				if(premlist.size()>0){
					for(int j=0;j<premlist.size();j++){
						Map<String,Object> map = premlist.get(j);
							String allocate = map.get("ALLOCATED_TILL_DATE")==null?"0":map.get("ALLOCATED_TILL_DATE").toString();
							String net = map.get("NETDUE_OC").toString();
							if("0".equalsIgnoreCase(allocate)){
								bean.setSettlement_status("Pending");
							}else if(Double.parseDouble(allocate) == Double.parseDouble(net)){
								bean.setSettlement_status("Allocated");
							}else{
								bean.setSettlement_status("Partially Allocated");
							}
					}
				}
	} catch (Exception exe) {
		LOGGER.debug("contractDetails"+exe);
	}
	LOGGER.info("PremiumDAOImpl contractDetails || Exit");
	return saveFlag;
}


@SuppressWarnings("unchecked")
public boolean getPremiumDetails(final FaculPremiumBean bean,final String TransactionNo,final String countryId)  {
	LOGGER.info("PremiumDAOImpl getPremiumDetails || Enter");
	String query="";
	try{

		String[] args=new String[3];
   	  	args[0]=bean.getProductId();
	   	args[1]=bean.getContNo();
	   	if("Temp".equalsIgnoreCase(bean.getTableType())){
	   		args[2]=bean.getRequestNo();
	   		query=getQuery("PREMIUM_SELECT_TREATYPREMIUMVIEW_TEMP");
	   	}else{
	   		args[2]=TransactionNo;
	   		query=getQuery(DBConstants.PREMIUM_SELECT_TREATYPREMIUMVIEW);
	   	}
   		LOGGER.info("Query=>"+query);
	   	this.mytemplate.query(query, args,new RowMapper() {
			public Object mapRow(ResultSet treatyView, int rowNum) throws SQLException {
				bean.setContNo(treatyView.getString("CONTRACT_NO"));
				bean.setTransactionNo(treatyView.getString("TRANSACTION_NO"));
				//bean.setRequestNo(treatyView.getString("REQUEST_NO"));
				bean.setTransaction(treatyView.getString("TRANS_DATE"));
				bean.setBrokerage(DropDownControllor.formatter(treatyView.getString("BROKERAGE_AMT_OC")));
				bean.setTax(DropDownControllor.formatter(treatyView.getString("TAX_AMT_OC")));
				bean.setPremiumQuotaShare(DropDownControllor.formatter(treatyView.getString("PREMIUM_QUOTASHARE_OC")));
				bean.setCommissionQuotaShare(DropDownControllor.formatter(treatyView.getString("COMMISSION_QUOTASHARE_OC")));
				bean.setPremiumSurplus(DropDownControllor.formatter(treatyView.getString("PREMIUM_SURPLUS_OC")));
				bean.setCommissionSurplus(DropDownControllor.formatter(treatyView.getString("COMMISSION_SURPLUS_OC")));
				bean.setPremiumportifolioIn(DropDownControllor.formatter(treatyView.getString("PREMIUM_PORTFOLIOIN_OC")));
				bean.setCliamPortfolioin(DropDownControllor.formatter(treatyView.getString("CLAIM_PORTFOLIOIN_OC")));
				bean.setPremiumportifolioout(DropDownControllor.formatter(treatyView.getString("PREMIUM_PORTFOLIOOUT_OC")));
				bean.setLossReserveReleased(DropDownControllor.formatter(treatyView.getString("LOSS_RESERVE_RELEASED_OC")));
				bean.setPremiumReserve_QuotaShare(DropDownControllor.formatter(treatyView.getString("PREMIUMRESERVE_QUOTASHARE_OC")));
				bean.setCashLoss_Credit(DropDownControllor.formatter(treatyView.getString("CASH_LOSS_CREDIT_OC")));
				bean.setLoss_ReserveRetained(DropDownControllor.formatter(treatyView.getString("LOSS_RESERVERETAINED_OC")));
				bean.setProfit_Commission(DropDownControllor.formatter(treatyView.getString("PROFIT_COMMISSION_OC")));
				bean.setCash_LossPaid(DropDownControllor.formatter(treatyView.getString("CASH_LOSSPAID_OC")));
				bean.setNetDue(DropDownControllor.formatter(treatyView.getString("NETDUE_OC")));
				bean.setReceipt_no(treatyView.getString("RECEIPT_NO"));
				//bean.setSettlement_status(treatyView.getString("SETTLEMENT_STATUS"));
				bean.setClaims_paid(DropDownControllor.formatter(treatyView.getString("CLAIMS_PAID_OC")));
				bean.setInception_Date(treatyView.getString("ENTRY_DATE"));
				bean.setXl_Cost(DropDownControllor.formatter(treatyView.getString("XL_COST_OC")));
				bean.setCliam_portfolio_out(DropDownControllor.formatter(treatyView.getString("CLAIM_PORTFOLIO_OUT_OC")));
				bean.setPremium_Reserve_Released(DropDownControllor.formatter(treatyView.getString("PREMIUM_RESERVE_REALSED_OC")));
				bean.setAccount_Period(treatyView.getString("ACCOUNT_PERIOD_QTR"));
				bean.setAccount_Period_year(treatyView.getString("ACCOUNT_PERIOD_YEAR"));
				bean.setCurrencyId(treatyView.getString("CURRENCY_ID"));
				bean.setOtherCost(DropDownControllor.formatter(treatyView.getString("OTHER_COST_OC")));
				bean.setBrokerage_usd(DropDownControllor.formatter(treatyView.getString("BROKERAGE_AMT_DC")));
				bean.setTax_usd(DropDownControllor.formatter(treatyView.getString("TAX_AMT_DC")));
				bean.setPremiumQuotaShare_usd(DropDownControllor.formatter(treatyView.getString("PREMIUM_QUOTASHARE_DC")));
				bean.setCommsissionQuotaShare_usd(DropDownControllor.formatter(treatyView.getString("COMMISSION_QUOTASHARE_DC")));
				bean.setPremium_surplus_usd(DropDownControllor.formatter(treatyView.getString("PREMIUM_SURPLUS_DC")));
				bean.setComission_surplus_usd(DropDownControllor.formatter(treatyView.getString("COMMISSION_SURPLUS_DC")));
				bean.setPremium_portfolio_in_usd(DropDownControllor.formatter(treatyView.getString("PREMIUM_PORTFOLIOIN_DC")));
				bean.setCliam_portfolio_usd(DropDownControllor.formatter(treatyView.getString("CLAIM_PORTFOLIOIN_DC")));
				bean.setPremium_PortfolioOut_usd(DropDownControllor.formatter(treatyView.getString("PREMIUM_PORTFOLIOOUT_DC")));
				bean.setLoss_Reserve_released_usd(DropDownControllor.formatter(treatyView.getString("LOSS_RESERVE_RELEASED_DC")));
				bean.setPremium_reserve_quota_share_usd(DropDownControllor.formatter(treatyView.getString("PREMIUMRESERVE_QUOTASHARE_DC")));
				bean.setCash_loss_credit_usd(DropDownControllor.formatter(treatyView.getString("CASH_LOSS_CREDIT_DC")));
				bean.setLoss_reserve_retained_usd(DropDownControllor.formatter(treatyView.getString("LOSS_RESERVERETAINED_DC")));
				bean.setProfit_commission_usd(DropDownControllor.formatter(treatyView.getString("PROFIT_COMMISSION_DC")));
				bean.setCash_loss_paid_usd(DropDownControllor.formatter(treatyView.getString("CASH_LOSSPAID_DC")));
				bean.setClams_paid_usd(DropDownControllor.formatter(treatyView.getString("CLAIMS_PAID_DC")));
				bean.setXl_cost_usd(DropDownControllor.formatter(treatyView.getString("XL_COST_DC")));
				bean.setCliam_portfolio_out_usd(DropDownControllor.formatter(treatyView.getString("CLAIM_PORTFOLIO_OUT_DC")));
				bean.setPremium_Reserve_Released_usd(DropDownControllor.formatter(treatyView.getString("PREMIUM_RESERVE_REALSED_DC")));
				bean.setNet_due_usd(DropDownControllor.formatter(treatyView.getString("NETDUE_DC")));
				bean.setOtherCostUSD(DropDownControllor.formatter(treatyView.getString("OTHER_COST_DC")));
				bean.setCedentRef(treatyView.getString("CEDANT_REFERENCE"));
				bean.setRemarks(treatyView.getString("REMARKS"));
				bean.setTotalCredit(DropDownControllor.formatter(treatyView.getString("TOTAL_CR_OC")));
				bean.setTotalCreditDC(DropDownControllor.formatter(treatyView.getString("TOTAL_CR_DC")));
				bean.setTotalDebit(DropDownControllor.formatter(treatyView.getString("TOTAL_DR_OC")));
				bean.setTotalDebitDC(DropDownControllor.formatter(treatyView.getString("TOTAL_DR_DC")));
				bean.setInterest(DropDownControllor.formatter(treatyView.getString("INTEREST_OC")));
				bean.setInterestDC(DropDownControllor.formatter(treatyView.getString("INTEREST_DC")));
				bean.setOsClaimsLossUpdateOC(DropDownControllor.formatter(treatyView.getString("OSCLAIM_LOSSUPDATE_OC")));
				bean.setOsClaimsLossUpdateDC(DropDownControllor.formatter(treatyView.getString("OSCLAIM_LOSSUPDATE_DC")));
				bean.setOverrider(DropDownControllor.formatter(treatyView.getString("OVERRIDER_AMT_OC")));
				bean.setOverriderUSD(DropDownControllor.formatter(treatyView.getString("OVERRIDER_AMT_DC")));
				bean.setAmendmentDate(treatyView.getString("AMENDMENT_DATE"));
                bean.setWithHoldingTaxOC(DropDownControllor.formatter(treatyView.getString("WITH_HOLDING_TAX_OC")));
                bean.setWithHoldingTaxDC(DropDownControllor.formatter(treatyView.getString("WITH_HOLDING_TAX_DC")));
                bean.setDueDate(treatyView.getString("due_date"));
                bean.setCreditsign(treatyView.getString("NETDUE_OC"));
                bean.setRi_cession(treatyView.getString("RI_CESSION"));
                bean.setTaxDedectSource(DropDownControllor.formatter(treatyView.getString("TDS_OC")));
				bean.setTaxDedectSourceDc(DropDownControllor.formatter(treatyView.getString("TDS_DC")));
				bean.setServiceTax(DropDownControllor.formatter(treatyView.getString("ST_OC")));
				bean.setServiceTaxDc(DropDownControllor.formatter(treatyView.getString("ST_DC")));
				bean.setLossParticipation(DropDownControllor.formatter(treatyView.getString("LPC_OC")));
				bean.setLossParticipationDC(DropDownControllor.formatter(treatyView.getString("LPC_DC")));
				bean.setSlideScaleCom(DropDownControllor.formatter(treatyView.getString("SC_COMM_OC")));
				bean.setSlideScaleComDC(DropDownControllor.formatter(treatyView.getString("SC_COMM_DC")));
				bean.setSubProfitId(treatyView.getString("SUB"));
				if(!"ALL".equalsIgnoreCase(bean.getSubProfitId())){
				bean.setSubProfitId(treatyView.getString("PREMIUM_SUBCLASS"));
				}
				bean.setExchRate(DropDownControllor.exchRateFormat(treatyView.getString("EXCHANGE_RATE")));
				bean.setStatementDate(treatyView.getString("STATEMENT_DATE"));
				 bean.setPremiumClass(treatyView.getString("TMAS_DEPARTMENT_NAME"));
	                bean.setPremiumSubClass(treatyView.getString("SUB"));
	                if(!"ALL".equalsIgnoreCase(bean.getPremiumSubClass())){
	                	bean.setPremiumSubClass(treatyView.getString("PREMIUM_SUBCLASS"));
	                }
	                bean.setOsbYN(treatyView.getString("OSBYN"));
	                bean.setSectionName(treatyView.getString("SECTION_NAME"));
	                bean.setAccDate(treatyView.getString("ACCOUNTING_PERIOD_DATE").toString()) ;
			return null;
			}
		});

		 	query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFPAIDPREMIUM);
			LOGGER.info("Select Query==>"+query);
			LOGGER.info("Arg[0]====>"+bean.getContNo());
			bean.setSum_of_paid_premium((String)this.mytemplate.queryForObject(query,new Object[]{bean.getContNo()},String.class));
			LOGGER.info("Result===>"+bean.getSum_of_paid_premium());

	   	if(StringUtils.isNotBlank(bean.getCurrencyId())){
			query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY);
			LOGGER.info("Select Query==>"+query);
			LOGGER.info("Arg[0]====>"+bean.getCurrencyId());
			LOGGER.info("Arg[1]====>"+bean.getBranchCode());
			bean.setCurrency((String)this.mytemplate.queryForObject(query,new Object[]{bean.getCurrencyId(),bean.getBranchCode()},String.class));
			LOGGER.info("Result==>"+bean.getCurrency());
	   	}
	   	query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY_NAME);
	   	bean.setCurrencyName((String)this.mytemplate.queryForObject(query,new Object[]{bean.getBranchCode()},String.class));

		LOGGER.info("PremiumDAOImpl getPremiumDetails || Exit");
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);e.printStackTrace();
	}
	return false;
}

public List<FaculPremiumBean> getPremiumedList(final FaculPremiumBean beanObj, String type)
{
	List<FaculPremiumBean> finalList = new ArrayList<FaculPremiumBean>();
	LOGGER.info("PremiumDAOImpl getPremiumedList || Enter");
	String query="";
    Object[] args=null;
      	args=new String[4];
    	args[0]=beanObj.getContNo();
    	args[1]=beanObj.getBranchCode();
    	args[2]=beanObj.getContNo();
    	args[3]=beanObj.getDepartmentId();
    	if("Main".equalsIgnoreCase(type)){
    		query=getQuery(DBConstants.PREMIUM_SELECT_PREMIUMEDLIST1);
    		query+=" AND RSK_DEPTID =  TRA.SUB_CLASS ";
    		query+=getQuery(DBConstants.PREMIUM_SELECT_PREMIUMEDLIST2);

    		query+=" AND RSK_DEPTID=? AND TRA.SUB_CLASS=RSK_DEPTID ";

    		query+=getQuery(DBConstants.PREMIUM_SELECT_PREMIUMEDLIST3);
    	}else{
    		query = getQuery("PTTY_PREMIUM_LIST_TEMP");
    	}
	
	LOGGER.info("Query=>"+query);
	LOGGER.info("Args=>"+StringUtils.join(args));
	List<Map<String,Object>> list=this.mytemplate.queryForList(query, args);
	for(int i=0 ; i<list.size() ; i++) {
		Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
		FaculPremiumBean tempBean = new FaculPremiumBean();
		tempBean.setRequestNo(tempMap.get("REQUEST_NO")==null?"":tempMap.get("REQUEST_NO").toString());
		tempBean.setProposal_No(tempMap.get("RSK_PROPOSAL_NUMBER")==null?"":tempMap.get("RSK_PROPOSAL_NUMBER").toString());
		tempBean.setContNo(tempMap.get("RSK_CONTRACT_NO")==null?"":tempMap.get("RSK_CONTRACT_NO").toString());
		tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME")==null?"":tempMap.get("COMPANY_NAME").toString());
		tempBean.setBroker(tempMap.get("BROKER_NAME")==null?"":tempMap.get("BROKER_NAME").toString());
		tempBean.setLayerno(tempMap.get("RSK_LAYER_NO")==null?"":tempMap.get("RSK_LAYER_NO").toString());
		tempBean.setTransactionNo(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
		tempBean.setAccount_Period(tempMap.get("ACC_PER")==null?"":tempMap.get("ACC_PER").toString());
		tempBean.setAccountPeriodDate(tempMap.get("ACCOUNTING_PERIOD_DATE")==null?"":tempMap.get("ACCOUNTING_PERIOD_DATE").toString());
		tempBean.setTransDropDownVal(tempMap.get("REVERSE_TRANSACTION_NO")==null?"":tempMap.get("REVERSE_TRANSACTION_NO").toString());
		if(Double.parseDouble(tempMap.get("ALLOC_AMT").toString())!=0)
			tempBean.setEndtYN("Yes");
		else
			tempBean.setEndtYN("No");
		tempBean.setProductId("2");
		tempBean.setInception_Date(tempMap.get("INS_DATE")==null?"":tempMap.get("INS_DATE").toString());
		tempBean.setStatementDate(tempMap.get("STATEMENT_DATE")==null?"":tempMap.get("STATEMENT_DATE").toString());
		tempBean.setMovementYN(tempMap.get("MOVEMENT_YN")==null?"":tempMap.get("MOVEMENT_YN").toString());
		//tempBean.setSettlement_Status(tempMap.get("SETTLEMENT_STATUS")==null?"":tempMap.get("SETTLEMENT_STATUS").toString());
		tempBean.setTransDate(tempMap.get("TRANSACTION_DATE")==null?"":tempMap.get("TRANSACTION_DATE").toString());
		if((StringUtils.isNotBlank(beanObj.getOpstartDate()))&& (StringUtils.isNotBlank(beanObj.getOpendDate()))){
			if(new DropDownControllor().Validatethree(beanObj.getBranchCode(), tempBean.getTransDate())==0){
				tempBean.setTransOpenperiodStatus("N");
			}else
			{
				tempBean.setTransOpenperiodStatus("Y");
			}
			}
		tempBean.setAllocatedYN((String)this.mytemplate.queryForObject("select Decode (count(*),0,'Y','N') allocatedYN from TTRN_ALLOCATED_TRANSACTION where CONTRACT_NO =? and TRANSACTION_NO=? and LAYER_NO=? and TYPE='P' and STATUS='Y'",new Object[]{tempBean.getContNo(),tempBean.getTransactionNo(),tempBean.getLayerno()},String.class));
			int count=new DropDownControllor().Validatethree(beanObj.getBranchCode(), tempBean.getTransDate());
			Object args2[]=new String[1];
			args2[0]=tempBean.getTransactionNo();
			query=getQuery(DBConstants.ALLOCATION_STATUS_COMPARITION);
			int allocationstatus=this.mytemplate.queryForInt(query,args2);
			query=getQuery(DBConstants.RETRO_PRCL_STATUS_COMPARITION);
			int retroPrclStatus=this.mytemplate.queryForInt(query,args2);
			int retroPrclStatus1=0;
			if(retroPrclStatus!=0){
			query=getQuery(DBConstants.RETRO_PRCL_STATUS_COMPARITION1);
			retroPrclStatus1=this.mytemplate.queryForInt(query,args2);
			}
			if(count!=0 && allocationstatus ==0 &&  retroPrclStatus1 ==0 ){
				tempBean.setDeleteStatus("Y");
			}
		finalList.add(tempBean);
	}
	LOGGER.info("PremiumDAOImpl getPremiumedList || Exit List Size==>"+list.size());
	return finalList;
}

@SuppressWarnings("unchecked")
public boolean premiumEdit(final FaculPremiumBean bean,final String countryId) {
	 LOGGER.info("PremiumDAOImpl premiumEdit || Enter");
	 String query="";
	 boolean saveFlag=false;
		 	String[] args = new String[2];
			args[0] = bean.getContNo();
	 if("Temp".equalsIgnoreCase(bean.getTableType())){
		 query=getQuery("PTTY_PREMIUM_EDIT_TEMP");
			args[1] = bean.getRequestNo();
		}else{
			query=getQuery(DBConstants.PREMIUM_SELECT_TREETYXOLPREMIUMEDIT);
			args[1] = bean.getTransactionNo();
		}
			
			LOGGER.info("Query=>"+query.toString());
			List list=this.mytemplate.query(query, args,new RowMapper() {
				public Object mapRow(ResultSet editPremium, int rowNum) throws SQLException {
					bean.setTransaction(editPremium.getString("TRANS_DATE"));
					bean.setAccount_Period(editPremium.getString("ACCOUNT_PERIOD_QTR"));
					bean.setAccount_Period_year(editPremium.getString("ACCOUNT_PERIOD_YEAR"));
					bean.setCurrencyId(editPremium.getString("CURRENCY_ID"));
					bean.setCurrency(editPremium.getString("CURRENCY_ID"));
					if(null==editPremium.getString("EXCHANGE_RATE")){
						bean.setExchRate(new DropDownControllor().GetExchangeRate(bean.getCurrencyId(),bean.getTransaction(),countryId,bean.getBranchCode()));
					}
					else{
					bean.setExchRate(DropDownControllor.exchRateFormat(editPremium.getString("EXCHANGE_RATE")));
					}
					bean.setBrokerage(editPremium.getString("BROKERAGE_AMT_OC"));
					bean.setTax(editPremium.getString("TAX_AMT_OC"));
					bean.setPremiumQuotaShare(editPremium.getString("PREMIUM_QUOTASHARE_OC"));
					bean.setCommissionQuotaShare(editPremium.getString("COMMISSION_QUOTASHARE_OC"));
					bean.setPremiumSurplus(editPremium.getString("PREMIUM_SURPLUS_OC"));
					bean.setCommissionSurplus(editPremium.getString("COMMISSION_SURPLUS_OC"));
					bean.setPremiumportifolioIn(editPremium.getString("PREMIUM_PORTFOLIOIN_OC"));
					bean.setCliamPortfolioin(editPremium.getString("CLAIM_PORTFOLIOIN_OC"));
					bean.setPremiumportifolioout(editPremium.getString("PREMIUM_PORTFOLIOOUT_OC"));
					bean.setLossReserveReleased(editPremium.getString("LOSS_RESERVE_RELEASED_OC"));
					bean.setPremiumReserve_QuotaShare(editPremium.getString("PREMIUMRESERVE_QUOTASHARE_OC"));
					bean.setCashLoss_Credit(editPremium.getString("CASH_LOSS_CREDIT_OC"));
					bean.setLoss_ReserveRetained(editPremium.getString("LOSS_RESERVERETAINED_OC"));
					bean.setProfit_Commission(editPremium.getString("PROFIT_COMMISSION_OC"));
					bean.setCash_LossPaid(editPremium.getString("CASH_LOSSPAID_OC"));
					bean.setStatus(editPremium.getString("STATUS"));
					bean.setNetDue(editPremium.getString("NETDUE_OC"));
					bean.setEnteringMode(editPremium.getString("ENTERING_MODE").trim());
					bean.setReceipt_no(editPremium.getString("RECEIPT_NO"));
					bean.setClaims_paid(editPremium.getString("CLAIMS_PAID_OC"));
					//bean.setSettlement_status(editPremium.getString("SETTLEMENT_STATUS"));
				    bean.setMd_premium(editPremium.getString("M_DPREMIUM_OC"));
				    bean.setAdjustment_premium(editPremium.getString("ADJUSTMENT_PREMIUM_OC"));
				    bean.setRecuirement_premium(editPremium.getString("REC_PREMIUM_OC"));
				    bean.setCommission(editPremium.getString("COMMISSION"));
				    bean.setInstlmentNo(editPremium.getString("INSTALMENT_NUMBER"));
				    bean.setInception_Date(editPremium.getString("INS_DATE"));
				    bean.setXl_Cost(editPremium.getString("XL_COST_OC"));
				    bean.setCliam_portfolio_out(editPremium.getString("CLAIM_PORTFOLIO_OUT_OC"));
				    bean.setPremium_Reserve_Released(editPremium.getString("PREMIUM_RESERVE_REALSED_OC"));
				    bean.setOtherCost(editPremium.getString("OTHER_COST_OC"));
				    bean.setCedentRef(editPremium.getString("CEDANT_REFERENCE"));
					bean.setRemarks(editPremium.getString("REMARKS"));
					bean.setNetDue(editPremium.getString("NETDUE_OC"));
					bean.setInterest(DropDownControllor.formatter(editPremium.getString("INTEREST_OC")));
					bean.setOsClaimsLossUpdateOC(DropDownControllor.formatter(editPremium.getString("OSCLAIM_LOSSUPDATE_OC")));
					bean.setOverrider(editPremium.getString("OVERRIDER_AMT_OC"));
					bean.setOverriderUSD(editPremium.getString("OVERRIDER_AMT_DC"));
					bean.setAmendmentDate(editPremium.getString("AMENDMENT_DATE"));
                    bean.setWithHoldingTaxOC(DropDownControllor.formatter(editPremium.getString("WITH_HOLDING_TAX_OC")));
                    bean.setWithHoldingTaxDC(DropDownControllor.formatter(editPremium.getString("WITH_HOLDING_TAX_DC")));
                    bean.setRi_cession(editPremium.getString("RI_CESSION"));
                    bean.setTaxDedectSource(DropDownControllor.formatter(editPremium.getString("TDS_OC")));
    				bean.setTaxDedectSourceDc(DropDownControllor.formatter(editPremium.getString("TDS_DC")));
    				bean.setServiceTax(DropDownControllor.formatter(editPremium.getString("ST_OC")));
    				bean.setServiceTaxDc(DropDownControllor.formatter(editPremium.getString("ST_DC")));
    				bean.setLossParticipation(DropDownControllor.formatter(editPremium.getString("LPC_OC")));
    				bean.setLossParticipationDC(DropDownControllor.formatter(editPremium.getString("LPC_DC")));
					bean.setSlideScaleCom(DropDownControllor.formatter(editPremium.getString("SC_COMM_OC")));
					bean.setSlideScaleComDC(DropDownControllor.formatter(editPremium.getString("SC_COMM_DC")));
					bean.setSubProfitId(editPremium.getString("PREMIUM_SUBCLASS"));
					bean.setPrAllocatedAmount(editPremium.getString("PRD_ALLOCATED_TILL_DATE"));
					bean.setLrAllocatedAmount(editPremium.getString("LRD_ALLOCATED_TILL_DATE"));
					bean.setStatementDate(editPremium.getString("STATEMENT_DATE"));
					bean.setOsbYN(editPremium.getString("OSBYN"));
					bean.setSectionName(editPremium.getString("SECTION_NAME"));
					bean.setSectionType("2");
					bean.setAccountPeriodDate(editPremium.getString("ACCOUNTING_PERIOD_DATE")) ;
					bean.setPredepartment(editPremium.getString("PREMIUM_CLASS")) ;
					return bean;
				}
			});
			if(list!=null && list.size()>0)
				saveFlag = true;
			LOGGER.info("PremiumDAOImpl premiumEdit || Exit");
		return saveFlag;
		}

@SuppressWarnings("unchecked")
public boolean getPreList(final FaculPremiumBean bean) {
	LOGGER.info("PremiumDAOImpl getPreList || Enter");
	String query="";
	boolean saveFlag=false;
	String[] args = null;
	args = new String[2];
	args[0] = bean.getContNo();
	args[1] = bean.getDepartmentId();
	query=getQuery(DBConstants.PREMIUM_SELECT_FACTREATYPRELIST);
	LOGGER.info("Select Query=>"+query);
	List list=this.mytemplate.query(query, args,new RowMapper() {
		public Object mapRow(ResultSet preList, int rowNum) throws SQLException {
			bean.setContNo(preList.getString("CONTRACT_NO"));
			bean.setDepartment_Name(preList.getString("TMAS_DEPARTMENT_NAME"));
			bean.setUwYear(preList.getString("UW_YEAR"));
			bean.setCeding_Company_Name(preList.getString("COMPANY_NAME"));
			bean.setBrokername(preList.getString("Broker_name"));
			bean.setLayerno(preList.getString("LAYER_NO"));
			bean.setProposal_No(preList.getString("PROPOSAL_NO"));
			bean.setProductId(preList.getString("PRODUCT_ID"));
			return bean;
		}
	});
	if(list!=null && list.size()>0)
		saveFlag = true;
	LOGGER.info("PremiumDAOImpl getPreList || Exit");
	if(StringUtils.isNotBlank(bean.getProposal_No()) ){
			bean.setCeaseStatus((String)this.mytemplate.queryForObject("select CEASE_STATUS from position_master pm where PROPOSAL_NO=? and  pm.amend_id=(Select Max(Amend_id) From Position_master p where p.PROPOSAL_NO=pm.PROPOSAL_NO )", new Object[]{bean.getProposal_No()}, String.class));
	}
	return saveFlag;
}

public boolean premiumUpdateMethod(final FaculPremiumBean beanObj){
	LOGGER.info("PremiumDAOImpl premiumUpdateMethod || Enter");
	String query="";
	boolean saveFlag = false;
	try {
		String[] args = updateAruguments(beanObj);
		String netDueOc="0";
		netDueOc=args[30];
		if("Temp".equalsIgnoreCase(beanObj.getTableType())){
			query=getQuery("PREMIUM_UPDATE_TREATYUPDATEPRE_TEMP");
		}else{
			query=getQuery(DBConstants.PREMIUM_UPDATE_TREATYUPDATEPRE);
		}
		this.mytemplate.update(query,args);
		if("Submit".equalsIgnoreCase(beanObj.getButtonStatus()) && "Temp".equalsIgnoreCase(beanObj.getTableType())){
			beanObj.setTransactionNo(new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction()));
			query = getQuery("FAC_TEMP_STATUS_UPDATE");
			args = new String[5];
	 		args[0] = "A";
	 		args[1] = beanObj.getLoginId();
	 		args[2] =beanObj.getTransactionNo()==null?"":beanObj.getTransactionNo();
	 		args[3]= beanObj.getRequestNo() ;
	 		args[4]= beanObj.getBranchCode() ;
	 		this.mytemplate.update(query,args);
	 		
	 		getTempToMainMove(beanObj,netDueOc);

	 		String sql = getQuery("UPDATE_TRANSACTION_NO_STATUS");
		 	LOGGER.info("Update Query==>"+sql);
		 	this.mytemplate.update(sql, new Object[]{beanObj.getTransactionNo(),"A",beanObj.getContNo(),beanObj.getRequestNo()});
	 	
		 	
			sql=getQuery("UPDATE_PREMIUM_RESERVE");
		 	LOGGER.info("Update Query==>"+sql);
		 	this.mytemplate.update(sql, new Object[]{beanObj.getContNo(),beanObj.getRequestNo(),"A",beanObj.getContNo(),beanObj.getTransactionNo()});
		 	

		 	sql=getQuery("UPDATE_LOSS_RESERVE");
		 	LOGGER.info("Update Query==>"+sql);
		 	this.mytemplate.update(sql, new Object[]{beanObj.getContNo(),beanObj.getRequestNo(),"A",beanObj.getContNo(),beanObj.getTransactionNo()});
	 	
		 	sql = getQuery("UPDATE_CASHLOSS_STATUS");
		 	LOGGER.info("Update Query==>"+sql);
		 	this.mytemplate.update(sql, new Object[]{beanObj.getTransactionNo(),"A",beanObj.getContNo(),beanObj.getRequestNo()});
		 	
		 	 List<FaculPremiumBean>cashLossList=getCassLossCredit(beanObj,"");
		 	 for(int i=0;i<cashLossList.size();i++){
		 		FaculPremiumBean form= cashLossList.get(0);
		 		 sql=getQuery(DBConstants.UPDATE_CLAIM_PAYMENT);
	             LOGGER.info("Update Query==>"+sql);
	             this.mytemplate.update(sql, new Object[]{form.getContNo(),beanObj.getBranchCode(),beanObj.getRequestNo(),"A",form.getClaimNumber(),form.getClaimPaymentNo(),form.getContNo(),form.getClaimNumber(),form.getClaimPaymentNo()});
		 	 }
		LOGGER.info("SP Name=>"+getQuery(DBConstants.PREMIUM_DETAIL_ARCHIVE));
		LOGGER.info("Args[]==>"+beanObj.getContNo()+","+(StringUtils.isBlank(beanObj.getLayerno())?"0":beanObj.getLayerno())+","+beanObj.getTransactionNo()+","+beanObj.getCurrencyId()+","+beanObj.getExchRate()+","+netDueOc+","+beanObj.getDepartmentId()+","+beanObj.getProductId());
		int spresult=this.mytemplate.update(getQuery(DBConstants.PREMIUM_DETAIL_ARCHIVE),new String[]{beanObj.getContNo(),(StringUtils.isBlank(beanObj.getLayerno())?"0":beanObj.getLayerno()),beanObj.getTransactionNo(),beanObj.getCurrencyId(),beanObj.getExchRate(),netDueOc,beanObj.getDepartmentId(),beanObj.getProductId()});
		LOGGER.info("SP Result==>"+spresult);
		LOGGER.info("Update Query=>"+query);
		int update=this.mytemplate.update(query,args);
		LOGGER.info("Update Result=>"+update);
		saveFlag=true;
		}
	} catch (Exception exe) {
		saveFlag=false;
		LOGGER.debug("Exception "+exe);
	}
	LOGGER.info("PremiumDAOImpl premiumUpdateMethod || Exit");
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

private String[] insertArguments(final FaculPremiumBean beanObj)
{
	LOGGER.info("PremiumDAOImpl insertArguments || Enter");
	String[] args=null;
		args=new String[94];
		double premiumsurpInsert=0.0;
		double premiumInsert=0.0;
	    args[0]=beanObj.getContNo();
	    args[1] = getRequestNo(beanObj);
		//args[1]=maxTransationNo(beanObj.getProductId(),beanObj.getBranchCode());
	   // if("06".equalsIgnoreCase(beanObj.getBranchCode())){
			//args[1]=new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction());
		/*}else
	    args[1]=new DropDownControllor().getPolicyNo("3","0",beanObj.getBranchCode());*/
		//args[2]=beanObj.getTransaction()+"-"+beanObj.getTransaction_year();
		args[2]=beanObj.getTransaction();
		args[3]=beanObj.getAccount_Period();
		args[4]=beanObj.getAccount_Period_year();
		args[5]=beanObj.getCurrencyId();
		args[6]=beanObj.getExchRate();
		args[7]=beanObj.getBrokerage_view();
		args[8]=getModeOfTransaction(beanObj.getBrokerage(),beanObj);
		args[35]=DropDownControllor.GetDesginationCountry(args[8], beanObj.getExchRate());
		args[9]=beanObj.getTax_view();
		args[10]=getModeOfTransaction(beanObj.getTax(),beanObj);
		args[36]=DropDownControllor.GetDesginationCountry(args[10], beanObj.getExchRate());
		args[67]=getModeOfTransaction(beanObj.getOverrider(),beanObj);
		args[68]=DropDownControllor.GetDesginationCountry(args[67],beanObj.getExchRate());
		args[69]=beanObj.getOverRider_view();
        args[70]=getModeOfTransaction(beanObj.getWithHoldingTaxOC(),beanObj);
        args[71]=DropDownControllor.GetDesginationCountry(args[70], beanObj.getExchRate());
		args[11]=StringUtils.isEmpty(beanObj.getInception_Date()) ?"" :beanObj.getInception_Date();
		args[12]=getModeOfTransaction(beanObj.getPremiumQuotaShare(),beanObj);
		args[37]=DropDownControllor.GetDesginationCountry(args[12], beanObj.getExchRate());
		args[13]=getModeOfTransaction(beanObj.getCommissionQuotaShare(),beanObj);
		args[38]=DropDownControllor.GetDesginationCountry(args[13], beanObj.getExchRate());
		args[14]=getModeOfTransaction(beanObj.getPremiumSurplus(),beanObj);
		args[39]=DropDownControllor.GetDesginationCountry(args[14], beanObj.getExchRate());
		args[15]=getModeOfTransaction(beanObj.getCommissionSurplus(),beanObj);
		args[40]=DropDownControllor.GetDesginationCountry(args[15], beanObj.getExchRate());
		args[16]=getModeOfTransaction(beanObj.getPremiumportifolioIn(),beanObj);
		args[41]=DropDownControllor.GetDesginationCountry(args[16], beanObj.getExchRate());
		args[72]=beanObj.getRi_cession();
		//args[73]=userId;
		args[73]= beanObj.getLoginId();
		args[74]=beanObj.getBranchCode();
		args[75]=beanObj.getDepartmentId();
		args[76] = getModeOfTransaction(beanObj.getTaxDedectSource(),beanObj);
		args[77] = DropDownControllor.GetDesginationCountry(args[76], beanObj.getExchRate());
		args[78] = getModeOfTransaction(beanObj.getServiceTax(),beanObj);
		args[79] = DropDownControllor.GetDesginationCountry(args[78], beanObj.getExchRate());
		args[80] = getModeOfTransaction(beanObj.getSlideScaleCom(),beanObj);
		args[81] = DropDownControllor.GetDesginationCountry(args[80], beanObj.getExchRate());
		args[82] = beanObj.getPredepartment();
		args[83] = beanObj.getSubProfitId().replace(" ", "");
		args[84] = beanObj.getAccountPeriodDate();
		args[85] = beanObj.getStatementDate();
		args[86] = beanObj.getOsbYN();
		args[87] = getModeOfTransaction(beanObj.getLossParticipation(),beanObj);
		args[88] = DropDownControllor.GetDesginationCountry(args[87], beanObj.getExchRate());
		args[89] = beanObj.getSectionName();
		args[90] = beanObj.getProposal_No();
		args[91] = beanObj.getProductId();
		if("submit".equalsIgnoreCase(beanObj.getButtonStatus())){
			args[92] = "A";
		}else{
			args[92] = "P";
		}
		args[93] = beanObj.getMode();
		//Added by sathish for java script failure cases-Start
		LOGGER.info("Before");
		LOGGER.info("Premium==>"+beanObj.getPremiumQuotaShare());
		LOGGER.info("Commission==>"+beanObj.getCommissionQuotaShare());
		LOGGER.info("Brokerate==>"+beanObj.getBrokerage());
		LOGGER.info("Tax===>"+beanObj.getTax());
		LOGGER.info("Overrider===>"+beanObj.getOverrider());
		LOGGER.info("PremiumSurplus===>"+beanObj.getPremiumSurplus());
		LOGGER.info("comsurp===>"+beanObj.getCommissionSurplus());

		if(!StringUtils.isEmpty(beanObj.getPremiumQuotaShare())||!StringUtils.isEmpty(beanObj.getPremiumSurplus()))
		{
			LOGGER.info("After");

			if(!StringUtils.isEmpty(beanObj.getPremiumQuotaShare()))
			{
				premiumInsert=Double.parseDouble(beanObj.getPremiumQuotaShare());
			}
			if(StringUtils.isEmpty(beanObj.getCommissionQuotaShare()))
			{
				final double commission=premiumInsert*(Double.parseDouble(beanObj.getCommission_view())/100);
				LOGGER.info("Commission===>"+commission);
				args[13]=getModeOfTransaction(commission+" ",beanObj);
				args[38]=DropDownControllor.GetDesginationCountry(args[13], beanObj.getExchRate());
			}
			if(!StringUtils.isEmpty(beanObj.getPremiumSurplus()))
			{
				premiumsurpInsert=(Double.parseDouble(beanObj.getPremiumSurplus()));
			}
			if(StringUtils.isEmpty(beanObj.getCommissionSurplus()))
			{
				LOGGER.info("comsurp==>"+beanObj.getCommssion_Surp());
				final double comsurp=premiumsurpInsert*(Double.parseDouble(beanObj.getCommssion_Surp())/100);
				LOGGER.info("comsurp===>"+comsurp);
				args[15]=getModeOfTransaction(comsurp+" ",beanObj);
				args[40]=DropDownControllor.GetDesginationCountry(args[15], beanObj.getExchRate());
			}
			if(StringUtils.isEmpty(beanObj.getBrokerage()))
			{
				final double brokerage=(premiumInsert+premiumsurpInsert)*(Double.parseDouble(beanObj.getBrokerage_view())/100);
				args[8]=getModeOfTransaction(brokerage+" ",beanObj);
				args[35]=DropDownControllor.GetDesginationCountry(args[8], beanObj.getExchRate());
				LOGGER.info("Brokerate===>"+brokerage);
			}
			if(StringUtils.isEmpty(beanObj.getTax()))
			{
				final double tax=(premiumInsert+premiumsurpInsert)*(Double.parseDouble(beanObj.getTax_view())/100);
				args[10]=getModeOfTransaction(tax+" ",beanObj);
				args[36]=DropDownControllor.GetDesginationCountry(args[10], beanObj.getExchRate());
				LOGGER.info("Tax===>"+tax);
			}
			if(StringUtils.isEmpty(beanObj.getOverrider()))
			{
				double overrider=(premiumInsert+premiumsurpInsert)*(Double.parseDouble(beanObj.getOverRider_view())/100);
				args[67]=getModeOfTransaction(overrider+" ",beanObj);
				args[68]=DropDownControllor.GetDesginationCountry(args[67], beanObj.getExchRate());
				LOGGER.info("Overrider===>"+overrider);
			}

		}
		//Added by sathish for java script failure cases-End
		args[17]=getModeOfTransaction(beanObj.getCliamPortfolioin(),beanObj);
		args[42]=DropDownControllor.GetDesginationCountry(args[17], beanObj.getExchRate());
		args[18]=getModeOfTransaction(beanObj.getPremiumportifolioout(),beanObj);
		args[43]=DropDownControllor.GetDesginationCountry(args[18], beanObj.getExchRate());
		args[19]=getModeOfTransaction(beanObj.getLossReserveReleased(),beanObj);
		args[44]=DropDownControllor.GetDesginationCountry(args[19], beanObj.getExchRate());
		args[20]=getModeOfTransaction(beanObj.getPremiumReserve_QuotaShare(),beanObj);
		args[45]=DropDownControllor.GetDesginationCountry(args[20], beanObj.getExchRate());
		args[21]=getModeOfTransaction(beanObj.getCashLoss_Credit(),beanObj);
		args[46]=DropDownControllor.GetDesginationCountry(args[21], beanObj.getExchRate());
		args[22]=getModeOfTransaction(beanObj.getLoss_ReserveRetained(),beanObj);
		args[47]=DropDownControllor.GetDesginationCountry(args[22], beanObj.getExchRate());
		args[23]=getModeOfTransaction(StringUtils.isBlank(beanObj.getProfit_Commission()) ? "0" : beanObj.getProfit_Commission(),beanObj);
		args[48]=DropDownControllor.GetDesginationCountry(args[23], beanObj.getExchRate());
		args[24]=getModeOfTransaction(beanObj.getCash_LossPaid(),beanObj);
		args[49]=DropDownControllor.GetDesginationCountry(args[24], beanObj.getExchRate());
		args[25]="Y";
		args[26]="2";
		args[27]=beanObj.getReceipt_no();
		args[28]=getModeOfTransaction(beanObj.getClaims_paid(),beanObj);
		args[50]=DropDownControllor.GetDesginationCountry(args[28], beanObj.getExchRate());
		args[29]=beanObj.getSettlement_status();
		args[30]=getModeOfTransaction(beanObj.getXl_Cost(),beanObj);
		args[51]=DropDownControllor.GetDesginationCountry(args[30], beanObj.getExchRate());
		args[31]=getModeOfTransaction(beanObj.getCliam_portfolio_out(),beanObj);
		args[52]=DropDownControllor.GetDesginationCountry(args[31], beanObj.getExchRate());
		args[32]=getModeOfTransaction(beanObj.getPremium_Reserve_Released(),beanObj);
		args[53]=DropDownControllor.GetDesginationCountry(args[32], beanObj.getExchRate());
		args[34]=getModeOfTransaction(beanObj.getOtherCost(),beanObj);
		args[55]=DropDownControllor.GetDesginationCountry(args[34], beanObj.getExchRate());
		/*args[33]=getNetDueAmount(args,getModeOfTransaction(beanObj.getClaims_paid(),beanObj));
		args[54]=DropDownControllor.GetDesginationCountry(args[33], beanObj.getExchRate());*/
		args[56]=beanObj.getCommission_view();
		args[57]=beanObj.getCedentRef();
		args[58]=beanObj.getRemarks();
		args[59]=getModeOfTransaction(beanObj.getTotalCredit(),beanObj);
		args[60]=DropDownControllor.GetDesginationCountry(args[59],beanObj.getExchRate());
		args[61]=getModeOfTransaction(beanObj.getTotalDebit(),beanObj);
		args[62]=DropDownControllor.GetDesginationCountry(args[61],beanObj.getExchRate());
		args[63]=getModeOfTransaction(beanObj.getInterest(),beanObj);
		args[64]=DropDownControllor.GetDesginationCountry(args[63],beanObj.getExchRate());
		args[33]=getNetDueAmount(args,getModeOfTransaction(beanObj.getClaims_paid(),beanObj));
		args[54]=DropDownControllor.GetDesginationCountry(args[33], beanObj.getExchRate());
		args[65]=StringUtils.isEmpty(beanObj.getOsClaimsLossUpdateOC())?"0":getModeOfTransaction(beanObj.getOsClaimsLossUpdateOC(),beanObj);
		args[66]=DropDownControllor.GetDesginationCountry(args[65], beanObj.getExchRate());

		//beanObj.setTransactionNo(args[1]);
		beanObj.setRequestNo(args[1]);
	for(int i=0;i>args.length;i++)
	{
		LOGGER.info("Args=>"+args[i]);
	}
	final String[] copiedArray = new String[args.length];
	System.arraycopy(args, 0, copiedArray, 0, args.length);
	LOGGER.info("PremiumDAOImpl maxTransationNo || Exit");
	return copiedArray;
}
private String getRequestNo(FaculPremiumBean beanObj) {
	String reqNo = "";
	try{
		String query=getQuery("GET_SEQ_NAME");
		String name=this.mytemplate.queryForObject(query, new String[]{beanObj.getBranchCode()},String.class);
		query="SELECT LPAD("+name+".nextval,6,0) FROM DUAL";
		reqNo = this.mytemplate.queryForObject(query,String.class);
		reqNo ="92"+reqNo;
	}catch(Exception e){
		e.printStackTrace();
	}
	return reqNo;
}


/*private  String maxTransationNo(final String productId,final String branchCode) {
	LOGGER.info("PremiumDAOImpl maxTransationNo || Enter");
	String query="";
	LOGGER.info("BranchCode=>"+branchCode);
	query = getQuery(DBConstants.PREMIUM_SELECT_MAXTRANSATION);
	final String TransactionId=(String)this.mytemplate.queryForObject(query,new Object[]{"11",branchCode},String.class);
	LOGGER.info("Transaction Id=>"+TransactionId);
	LOGGER.info("PremiumDAOImpl maxTransationNo || Exit");
	return TransactionId;
}*/
private static String getModeOfTransaction(final String Value,final FaculPremiumBean beanObj) {
LOGGER.info("PremiumDAOImpl getModeOfTransaction || Enter");
LOGGER.info("Value=>"+Value);
LOGGER.info("Entering Mode=>"+beanObj.getEnteringMode());
LOGGER.info("ShareSigned=>"+beanObj.getShareSigned());
String result="0";
double shareSigned=0.0;
DecimalFormat twoDForm = new DecimalFormat("#.##");
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
private static String getNetDueFac(final String[] args,final int flag) {
	LOGGER.info("PremiumDAOImpl getNetDueFac || Enter");
	Double Net = 0.0;
	if (flag == 1) {
		if (StringUtils.isNotEmpty(args[8]))
		{
			Net += Double.parseDouble(args[8]);
		}
		if (StringUtils.isNotEmpty(args[10]))
		{
			Net -= Double.parseDouble(args[10]);
		}
		if (StringUtils.isNotEmpty(args[12]))
		{
			Net -= Double.parseDouble(args[12]);
		}
		if (StringUtils.isNotEmpty(args[14]))
		{
			Net -= Double.parseDouble(args[14]);
		}
		if (StringUtils.isNotEmpty(args[20]))
		{
			Net -= Double.parseDouble(args[20]);
		}
	} else if (flag == 2) {
		if (StringUtils.isNotEmpty(args[5]))
		{
			Net += Double.parseDouble(args[5]);
		}
		if (StringUtils.isNotEmpty(args[7]))
		{
			Net -= Double.parseDouble(args[7]);
		}
		if (StringUtils.isNotEmpty(args[9]))
		{
			Net -= Double.parseDouble(args[9]);
		}
		if (StringUtils.isNotEmpty(args[11]))
		{
			Net -= Double.parseDouble(args[11]);
		}
		if (StringUtils.isNotEmpty(args[17]))
		{
			Net -= Double.parseDouble(args[17]);
		}
	}
	LOGGER.info("PremiumDAOImpl getNetDueFac || Exit");
	return String.valueOf(Net);
}
private static String getNetDueAmount(final String[] args,final String CliamPaid) {
	LOGGER.info("PremiumDAOImpl getNetDueAmount || Enter");
	double Abt=0;
	double Bbt=0;
	if(StringUtils.isNotEmpty(args[12]))
	{
	Abt+=Double.parseDouble(args[12]);
	}
	if(StringUtils.isNotEmpty(args[14]))
	{
	Abt+=Double.parseDouble(args[14]);
	}
	if(StringUtils.isNotEmpty(args[16]))
	{
	Abt+=Double.parseDouble(args[16]);
	}
	if(StringUtils.isNotEmpty(args[17]))
	{
	Abt+=Double.parseDouble(args[17]);
	}
	if(StringUtils.isNotEmpty(args[19]))
	{
	Abt+=Double.parseDouble(args[19]);
	}
	if(StringUtils.isNotEmpty(args[21]))
	{
	Abt+=Double.parseDouble(args[21]);
	}
	if(StringUtils.isNotEmpty(args[32]))
	{
		Abt+=Double.parseDouble(args[32]);
	}
	if(StringUtils.isNotEmpty(args[63]))
	{
		Abt+=Double.parseDouble(args[63]);
	}
	if(StringUtils.isNotEmpty(args[76]))
	{
	Abt+=Double.parseDouble(args[76]);
	}
	if(StringUtils.isNotEmpty(args[78]))
	{
	Abt+=Double.parseDouble(args[78]);
	}
	
	if(StringUtils.isNotEmpty(args[87]))
	{
	Abt+=Double.parseDouble(args[87]);
	}
	if(StringUtils.isNotEmpty(args[13]))
	{
		Bbt+=Double.parseDouble(args[13]);
	}
	if(StringUtils.isNotEmpty(args[15]))
	{
		Bbt+=Double.parseDouble(args[15]);
	}
	if(StringUtils.isNotEmpty(args[8]))
	{
		Bbt+=Double.parseDouble(args[8]);
	}
	if(StringUtils.isNotEmpty(args[10]))
	{
		Bbt+=Double.parseDouble(args[10]);
	}
	if(StringUtils.isNotEmpty(args[18]))
	{
		Bbt+=Double.parseDouble(args[18]);
	}
	if(StringUtils.isNotEmpty(args[20]))
	{
		Bbt+=Double.parseDouble(args[20]);
	}
	if(StringUtils.isNotEmpty(args[22]))
	{
		Bbt+=Double.parseDouble(args[22]);
	}
	if(StringUtils.isNotEmpty(args[23]))
	{
		Bbt+=Double.parseDouble(args[23]);
	}
	if(StringUtils.isNotEmpty(args[24]))
	{
		Bbt+=Double.parseDouble(args[24]);
	}
	if(StringUtils.isNotEmpty(CliamPaid))
	{
		Bbt+=Double.parseDouble(CliamPaid);
	}
	if(StringUtils.isNotEmpty(args[31]))
	{
		Bbt+=Double.parseDouble(args[31]);
	}
	if(StringUtils.isNotEmpty(args[30]))
	{
		Bbt+=Double.parseDouble(args[30]);
	}
	if(StringUtils.isNotEmpty(args[34]))
	{
		Bbt+=Double.parseDouble(args[34]);
	}
	if(StringUtils.isNotEmpty(args[67]))
	{
		Bbt+=Double.parseDouble(args[67]);
	}
	if(StringUtils.isNotEmpty(args[70]))
	{
		Bbt+=Double.parseDouble(args[70]);
	}
	if(StringUtils.isNotEmpty(args[80]))
	{
		Bbt+=Double.parseDouble(args[80]);
	}
	LOGGER.info("A==>"+Abt);
	LOGGER.info("B==>"+Bbt);
    final double cbt=Abt-Bbt;
    LOGGER.info("Net Due==>"+cbt);
    LOGGER.info("PremiumDAOImpl getNetDueAmount || Exit");
	return String.valueOf(cbt);
}
private static String getNetDueXol(final String[] args) {
	LOGGER.info("PremiumDAOImpl getNetDueXol || Enter");
	final double Ant=StringUtils.isEmpty(args[13]) ? 0 :Double.parseDouble(args[13]) ;
	final double Bnt=StringUtils.isEmpty(args[14]) ? 0 :Double.parseDouble(args[14]) ;
	final double Cnt=StringUtils.isEmpty(args[15]) ? 0 :Double.parseDouble(args[15]) ;
	final double Dnt=StringUtils.isEmpty(args[8]) ? 0 :Double.parseDouble(args[8]) ;
	final double Ent=StringUtils.isEmpty(args[10]) ? 0 :Double.parseDouble(args[10]) ;
	final double Fnt=StringUtils.isEmpty(args[12]) ? 0 :Double.parseDouble(args[12]) ;
	final double Hnt=StringUtils.isEmpty(args[23]) ? 0 :Double.parseDouble(args[23]) ;
	LOGGER.info("A==>"+Ant);
	LOGGER.info("B==>"+Bnt);
    final double cnt=(Ant+Bnt+Cnt)-(Dnt+Ent+Fnt+Hnt);
    LOGGER.info("Net Due==>"+cnt);
    LOGGER.info("PremiumDAOImpl getNetDueXol || Exit");
	return String.valueOf(cnt);
}
public static String[] updateAruguments(final FaculPremiumBean beanObj) {
	LOGGER.info("PremiumDAOImpl updateAruguments || Enter");
	String[] args=null;
		args=new String[87];
		double premiumsurp=0.0;
		double premium=0.0;
		//args[0]=beanObj.getTransaction()+"-"+beanObj.getTransaction_year();
		args[0]=beanObj.getTransaction();
		args[1]=beanObj.getAccount_Period();
		args[2]=beanObj.getAccount_Period_year();
		args[3]=beanObj.getCurrencyId();
		args[4]=beanObj.getExchRate();
		args[5]=beanObj.getBrokerage_view();
		args[6]=getModeOfTransaction(beanObj.getBrokerage(),beanObj);
		args[32]=DropDownControllor.GetDesginationCountry(args[6], beanObj.getExchRate());
		args[7]=beanObj.getTax_view();
		args[8]=getModeOfTransaction(beanObj.getTax(),beanObj);
		args[33]=DropDownControllor.GetDesginationCountry(args[8], beanObj.getExchRate());
		args[63]=getModeOfTransaction(beanObj.getOverrider(),beanObj);
		args[64]=DropDownControllor.GetDesginationCountry(args[63],beanObj.getExchRate());
		args[65]=beanObj.getOverRider_view();
		args[9]=StringUtils.isEmpty(beanObj.getInception_Date()) ?"" :beanObj.getInception_Date();
		args[10]=getModeOfTransaction(beanObj.getPremiumQuotaShare(),beanObj);
		args[34]=DropDownControllor.GetDesginationCountry(args[10], beanObj.getExchRate());
		args[11]=getModeOfTransaction(beanObj.getCommissionQuotaShare(),beanObj);
		args[35]=DropDownControllor.GetDesginationCountry(args[11], beanObj.getExchRate());
		args[12]=getModeOfTransaction(beanObj.getPremiumSurplus(),beanObj);
		args[36]=DropDownControllor.GetDesginationCountry(args[12], beanObj.getExchRate());
		args[13]=getModeOfTransaction(beanObj.getCommissionSurplus(),beanObj);
		args[37]=DropDownControllor.GetDesginationCountry(args[13], beanObj.getExchRate());
		//Added by sathish for java script failure cases-Start
		LOGGER.info("Before");
		LOGGER.info("Premium==>"+beanObj.getPremiumQuotaShare());
		LOGGER.info("Commission==>"+beanObj.getCommissionQuotaShare());
		LOGGER.info("Brokerate==>"+beanObj.getBrokerage());
		LOGGER.info("Tax===>"+beanObj.getTax());
		LOGGER.info("Overrider===>"+beanObj.getOverrider());
		LOGGER.info("PremiumSurplus===>"+beanObj.getPremiumSurplus());
		LOGGER.info("comsurp===>"+beanObj.getCommissionSurplus());

		if(!StringUtils.isEmpty(beanObj.getPremiumQuotaShare())||!StringUtils.isEmpty(beanObj.getPremiumSurplus()))
		{
			LOGGER.info("After");

			if(!StringUtils.isEmpty(beanObj.getPremiumQuotaShare()))
			{
				premium=Double.parseDouble(beanObj.getPremiumQuotaShare());
			}
			if(StringUtils.isEmpty(beanObj.getCommissionQuotaShare()))
			{
				final double commission=premium*(Double.parseDouble(beanObj.getCommission_view())/100);
				LOGGER.info("Commission===>"+commission);
				args[11]=getModeOfTransaction(commission+" ",beanObj);
				args[35]=DropDownControllor.GetDesginationCountry(args[11], beanObj.getExchRate());
			}
			if(!StringUtils.isEmpty(beanObj.getPremiumSurplus()))
			{
				premiumsurp=(Double.parseDouble(beanObj.getPremiumSurplus()));
			}
			if(StringUtils.isEmpty(beanObj.getCommissionSurplus()))
			{
				LOGGER.info("comsurp==>"+beanObj.getCommssion_Surp());
				double comsurp=premiumsurp*(Double.parseDouble(beanObj.getCommssion_Surp())/100);
				LOGGER.info("comsurp===>"+comsurp);
				args[13]=getModeOfTransaction(comsurp+" ",beanObj);
				args[37]=DropDownControllor.GetDesginationCountry(args[13], beanObj.getExchRate());

			}
			if(StringUtils.isEmpty(beanObj.getBrokerage()))
			{
				double brokerage=(premium+premiumsurp)*(Double.parseDouble(beanObj.getBrokerage_view())/100);
				args[6]=getModeOfTransaction(brokerage+" ",beanObj);
				args[32]=DropDownControllor.GetDesginationCountry(args[6], beanObj.getExchRate());
				LOGGER.info("Brokerate===>"+brokerage);
			}
			if(StringUtils.isEmpty(beanObj.getTax()))
			{
				double tax=(premium+premiumsurp)*(Double.parseDouble(beanObj.getTax_view())/100);
				args[8]=getModeOfTransaction(tax+" ",beanObj);
				args[33]=DropDownControllor.GetDesginationCountry(args[8], beanObj.getExchRate());
				LOGGER.info("Tax===>"+tax);
			}
			if(StringUtils.isEmpty(beanObj.getOverrider()))
			{
				double overrider=(premium+premiumsurp)*(Double.parseDouble(beanObj.getOverRider_view())/100);
				args[63]=getModeOfTransaction(overrider+" ",beanObj);
				args[64]=DropDownControllor.GetDesginationCountry(args[8], beanObj.getExchRate());
				LOGGER.info("Overrider===>"+overrider);
			}
		}
		//Added by sathish for java script failure cases-End
		args[14]=getModeOfTransaction(beanObj.getPremiumportifolioIn(),beanObj);
		args[38]=DropDownControllor.GetDesginationCountry(args[14], beanObj.getExchRate());
		args[15]=getModeOfTransaction(beanObj.getCliamPortfolioin(),beanObj);
		args[39]=DropDownControllor.GetDesginationCountry(args[15], beanObj.getExchRate());
		args[16]=getModeOfTransaction(beanObj.getPremiumportifolioout(),beanObj);
		args[40]=DropDownControllor.GetDesginationCountry(args[16], beanObj.getExchRate());
		args[17]=getModeOfTransaction(beanObj.getLossReserveReleased(),beanObj);
		args[41]=DropDownControllor.GetDesginationCountry(args[17], beanObj.getExchRate());
		args[18]=getModeOfTransaction(beanObj.getPremiumReserve_QuotaShare(),beanObj);
		args[42]=DropDownControllor.GetDesginationCountry(args[18], beanObj.getExchRate());
		args[19]=getModeOfTransaction(beanObj.getCashLoss_Credit(),beanObj);
		args[43]=DropDownControllor.GetDesginationCountry(args[19], beanObj.getExchRate());
		args[20]=getModeOfTransaction(beanObj.getLoss_ReserveRetained(),beanObj);
		args[44]=DropDownControllor.GetDesginationCountry(args[20], beanObj.getExchRate());
		args[21]=getModeOfTransaction((StringUtils.isEmpty(beanObj.getProfit_Commission()) ? "0" : beanObj.getProfit_Commission()),beanObj);
		args[45]=DropDownControllor.GetDesginationCountry(args[21], beanObj.getExchRate());
		args[22]=getModeOfTransaction(beanObj.getCash_LossPaid(),beanObj);
		args[46]=DropDownControllor.GetDesginationCountry(args[22], beanObj.getExchRate());
		//args[23]=beanObj.getEnteringMode();
		args[23]="2";
		args[24]=beanObj.getReceipt_no();
		args[25]=getModeOfTransaction(beanObj.getClaims_paid(),beanObj);
		args[47]=DropDownControllor.GetDesginationCountry(args[25], beanObj.getExchRate());
		args[26]=beanObj.getSettlement_status();
		args[27]=getModeOfTransaction(beanObj.getXl_Cost(),beanObj);
		args[48]=DropDownControllor.GetDesginationCountry(args[27], beanObj.getExchRate());
		args[28]=getModeOfTransaction(beanObj.getCliam_portfolio_out(),beanObj);
		args[49]=DropDownControllor.GetDesginationCountry(args[28], beanObj.getExchRate());
		args[29]=getModeOfTransaction(beanObj.getPremium_Reserve_Released(),beanObj);
		args[50]=DropDownControllor.GetDesginationCountry(args[29], beanObj.getExchRate());
		args[31]=getModeOfTransaction(beanObj.getOtherCost(),beanObj);
		args[52]=DropDownControllor.GetDesginationCountry(args[31], beanObj.getExchRate());
		/*args[30]=updateNetDue(args,getModeOfTransaction(beanObj.getClaims_paid(),beanObj));
		args[51]=DropDownControllor.GetDesginationCountry(args[30], beanObj.getExchRate());*/
		args[53]=beanObj.getCedentRef();
		args[54]=beanObj.getRemarks();
		args[55]=getModeOfTransaction(beanObj.getTotalCredit(),beanObj);
		args[56]=DropDownControllor.GetDesginationCountry(args[55],beanObj.getExchRate());
		args[57]=getModeOfTransaction(beanObj.getTotalDebit(),beanObj);
		args[58]=DropDownControllor.GetDesginationCountry(args[57],beanObj.getExchRate());
		args[59]=getModeOfTransaction(beanObj.getInterest(),beanObj);
		args[60]=DropDownControllor.GetDesginationCountry(args[59],beanObj.getExchRate());
		args[67] = StringUtils.isEmpty(beanObj.getWithHoldingTaxOC()) ? "0" : beanObj.getWithHoldingTaxOC();
		args[61]=StringUtils.isEmpty(beanObj.getOsClaimsLossUpdateOC())?"0":getModeOfTransaction(beanObj.getOsClaimsLossUpdateOC(),beanObj);
		args[62]=DropDownControllor.GetDesginationCountry(args[61],beanObj.getExchRate());
		args[66]=beanObj.getAmendmentDate();
		args[68] = DropDownControllor.GetDesginationCountry(args[67], beanObj.getExchRate());
		args[69] = beanObj.getRi_cession();
		args[70] = beanObj.getDepartmentId();
		args[71] = getModeOfTransaction(beanObj.getTaxDedectSource(),beanObj);
		args[72] = DropDownControllor.GetDesginationCountry(args[71], beanObj.getExchRate());
		args[73] = getModeOfTransaction(beanObj.getServiceTax(),beanObj);
		args[74] = DropDownControllor.GetDesginationCountry(args[73], beanObj.getExchRate());
		args[75] = getModeOfTransaction(beanObj.getSlideScaleCom(),beanObj);
		args[76] = DropDownControllor.GetDesginationCountry(args[75], beanObj.getExchRate());
		args[77] = beanObj.getPredepartment();
		args[78] = beanObj.getSubProfitId().replace(" ", "");
		args[79] = beanObj.getAccountPeriodDate();
		args[80] = beanObj.getStatementDate();
		args[81] = beanObj.getOsbYN();
		args[82] = getModeOfTransaction(beanObj.getLossParticipation(),beanObj);
		args[83] = DropDownControllor.GetDesginationCountry(args[82], beanObj.getExchRate());
		args[84] = beanObj.getSectionName();
		args[85] = beanObj.getContNo();
		 if(StringUtils.isBlank(beanObj.getTransactionNo())){
		    	args[86]=beanObj.getRequestNo();
		    }else{
		    	args[86]=beanObj.getTransactionNo();
		    
		    }

		args[30]=updateNetDue(args,getModeOfTransaction(beanObj.getClaims_paid(),beanObj));
		args[51]=DropDownControllor.GetDesginationCountry(args[30], beanObj.getExchRate());
		for(int i=0;i<args.length;i++)
		{
			LOGGER.info("Args["+i+"]=>"+args[i]);
		}
	final String[] copiedArray = new String[args.length];
	System.arraycopy(args, 0, copiedArray, 0, args.length);
	LOGGER.info("PremiumDAOImpl updateAruguments || Exit");
	return copiedArray;
}
private static String updateNetDue(final String[] args,final String claimpaid) {
	LOGGER.info("PremiumDAOImpl updateNetDue || Enter");
	double Aut=0;
	double But=0;
	if(StringUtils.isNotEmpty(args[10]))
	{
	Aut+=Double.parseDouble(args[10]);
	}
	if(StringUtils.isNotEmpty(args[12]))
	{
	Aut+=Double.parseDouble(args[12]);
	}
	if(StringUtils.isNotEmpty(args[14]))
	{
	Aut+=Double.parseDouble(args[14]);
	}
	if(StringUtils.isNotEmpty(args[15]))
	{
	Aut+=Double.parseDouble(args[15]);
	}
	if(StringUtils.isNotEmpty(args[17]))
	{
	Aut+=Double.parseDouble(args[17]);
	}
	if(StringUtils.isNotEmpty(args[19]))
	{
	Aut+=Double.parseDouble(args[19]);
	}
	if(StringUtils.isNotEmpty(args[29]))
	{
		Aut+=Double.parseDouble(args[29]);
	}
	if(StringUtils.isNotEmpty(args[59]))
	{
		Aut+=Double.parseDouble(args[59]);
	}
	if(StringUtils.isNotEmpty(args[71]))
	{
		Aut+=Double.parseDouble(args[71]);
	}
	if(StringUtils.isNotEmpty(args[73]))
	{
		Aut+=Double.parseDouble(args[73]);
	}
	if(StringUtils.isNotEmpty(args[82]))
	{
		Aut+=Double.parseDouble(args[82]);
	}
	if(StringUtils.isNotEmpty(args[11]))
	{
		But+=Double.parseDouble(args[11]);
	}
	if(StringUtils.isNotEmpty(args[13]))
	{
		But+=Double.parseDouble(args[13]);
	}
	if(StringUtils.isNotEmpty(args[6]))
	{
		But+=Double.parseDouble(args[6]);
	}
	if(StringUtils.isNotEmpty(args[8]))
	{
		But+=Double.parseDouble(args[8]);
	}
	if(StringUtils.isNotEmpty(args[16]))
	{
		But+=Double.parseDouble(args[16]);
	}
	if(StringUtils.isNotEmpty(args[18]))
	{
		But+=Double.parseDouble(args[18]);
	}
	if(StringUtils.isNotEmpty(args[20]))
	{
		But+=Double.parseDouble(args[20]);
	}
	if(StringUtils.isNotEmpty(args[21]))
	{
		But+=Double.parseDouble(args[21]);
	}
	if(StringUtils.isNotEmpty(args[22]))
	{
		But+=Double.parseDouble(args[22]);
	}
	if(StringUtils.isNotEmpty(claimpaid))
	{
		But+=Double.parseDouble(claimpaid);
	}
	if(StringUtils.isNotEmpty(args[31]))
	{
		But+=Double.parseDouble(args[31]);
	}
	if(StringUtils.isNotEmpty(args[27]))
	{
		But+=Double.parseDouble(args[27]);
	}
	if(StringUtils.isNotEmpty(args[28]))
	{
		But+=Double.parseDouble(args[28]);
	}
	if(StringUtils.isNotEmpty(args[63]))
	{
		But+=Double.parseDouble(args[63]);
	}
	if(StringUtils.isNotEmpty(args[67]))
	{
		But+=Double.parseDouble(args[67]);
	}
	if(StringUtils.isNotEmpty(args[75]))
	{
		But+=Double.parseDouble(args[75]);
	}
	LOGGER.info("A==>"+Aut);
	LOGGER.info("B==>"+But);
    double cut=Aut-But;
    LOGGER.info("Net Due==>"+cut);
    LOGGER.info("PremiumDAOImpl updateNetDue || Exit");
	return String.valueOf(cut);
}
private static String getNetDueXolUpdate(final String[] args) {
	LOGGER.info("PremiumDAOImpl getNetDueXolUpdate || Enter");
	double Ant=StringUtils.isEmpty(args[9]) ? 0 :Double.parseDouble(args[9]) ;
	double Bnt=StringUtils.isEmpty(args[10]) ? 0 :Double.parseDouble(args[10]) ;
	double Cnt=StringUtils.isEmpty(args[11]) ? 0 :Double.parseDouble(args[11]) ;
	double Dnt=StringUtils.isEmpty(args[4]) ? 0 :Double.parseDouble(args[4]) ;
	double Ent=StringUtils.isEmpty(args[6]) ? 0 :Double.parseDouble(args[6]) ;
	double Fnt=StringUtils.isEmpty(args[15]) ? 0 :Double.parseDouble(args[15]) ;
	LOGGER.info("A==>"+Ant);
	LOGGER.info("B==>"+Bnt);
	double c=(Ant+Bnt+Cnt)-(Dnt+Ent+Fnt);
    LOGGER.info("Net Due==>"+c);
    LOGGER.info("PremiumDAOImpl getNetDueXolUpdate || Exit");
	return String.valueOf(c);
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

	LOGGER.info("FaculPremiumDAOImpl allocateView() || Enter");
	List<FaculPremiumBean> allocateList = new ArrayList<FaculPremiumBean>();
	Double a=0.0;
	try{
		String[] args = new String[4];
		args[0] = beanObj.getContNo();
		args[1] = beanObj.getTransactionNo();
		args[2] = beanObj.getContNo();
		args[3] = beanObj.getTransactionNo();
		String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOTRANSACTION);
		selectQry = selectQry + " ORDER BY SNO DESC";
		LOGGER.info("Query=>"+selectQry);
		List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
		LOGGER.info("Result Size=>"+list.size());
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
				FaculPremiumBean tempBean = new FaculPremiumBean();
				tempBean.setSerial_no(tempMap.get("SNO")==null?"":tempMap.get("SNO").toString());
				tempBean.setAllocateddate(tempMap.get("INCEPTION_DATE")==null?"":tempMap.get("INCEPTION_DATE").toString());
				tempBean.setProductname(tempMap.get("PRODUCT_NAME")==null?"":tempMap.get("PRODUCT_NAME").toString());
				tempBean.setType(tempMap.get("TYPE")==null?"":tempMap.get("TYPE").toString());
				tempBean.setPayamount(tempMap.get("PAID_AMOUNT")==null?"":tempMap.get("PAID_AMOUNT").toString());
				tempBean.setCurrencyValue(tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString());
				tempBean.setAlloccurrencyid(tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString());
				tempBean.setAllocateType(tempMap.get("ADJUSTMENT_TYPE")==null?"":tempMap.get("ADJUSTMENT_TYPE").toString());
				tempBean.setStatus((tempMap.get("STATUS")==null?"":tempMap.get("STATUS").toString()));
				tempBean.setPay_rec_no(tempMap.get("RECEIPT_NO")==null?"":tempMap.get("RECEIPT_NO").toString());
				tempBean.setSettlementType(tempMap.get("TRANS_TYPE")==null?"":tempMap.get("TRANS_TYPE").toString());
				if(tempBean.getPay_rec_no()!=""){
				beanObj.setAllocateType(tempMap.get("ALLOCATE_TYPE")==null?"":tempMap.get("ALLOCATE_TYPE").toString());
				//beanObj.setAllocateType((String)this.mytemplate.queryForObject("SELECT TRANS_TYPE FROM TTRN_PAYMENT_RECEIPT tpr WHERE PAYMENT_RECEIPT_NO=? and tpr.amend_id=(select max(amend_id) from Ttrn_Payment_Receipt where  PAYMENT_RECEIPT_NO=tpr.PAYMENT_RECEIPT_NO and branch_code=tpr.branch_code)",new Object[]{tempBean.getPay_rec_no()},String.class));
				}
				a=a+Double.parseDouble(tempMap.get("PAID_AMOUNT")==null?"":tempMap.get("PAID_AMOUNT").toString());
				allocateList.add(tempBean);
			}
		}
		if(a>0){
		beanObj.setTotalAmount(DropDownControllor.formatter(Double.toString(a)));
		}
		else{
			beanObj.setTotalAmount("");
		}
	}
  	catch(Exception exe)
	{
	LOGGER.debug("Exception"+exe);
	}
  	LOGGER.info("FaculPremiumDAOImpl allocateView() || Exit Map");
	return allocateList;
}
public List getBrokerAndCedingName(FaculPremiumBean beanObj) {
	// TODO Auto-generated method stub
	LOGGER.info("PremiumDAOImpl getRetroContracts() || Enter");
	List list=null;
	try{
		String query="";
		Object args[]=null;
		args=new Object[4];
		args[0]=beanObj.getContNo();
		args[1]=beanObj.getBranchCode();
		args[2]=beanObj.getContNo();
		args[3]=beanObj.getBranchCode();
		query=this.getQuery(DBConstants.BROKER_AND_CEDING_NAME);
		LOGGER.info("Select BrokerAndCedingName Query=>"+query);
		LOGGER.info("Contract No=>"+beanObj.getProposal_No());
		list=this.mytemplate.queryForList(query, args);
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);
	}
	LOGGER.info("PremiumDAOImpl getRetroContracts() || Exit List Size"+list.size());
	return list;
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
	LOGGER.info("PremiumDAOImpl currencyList() || Enter");
	List<Map<String,Object>> list=null;
	try{
		String query="";
		Object args[]=null;
		args=new Object[2];
		args[0]=bean.getBranchCode();
		args[1]=bean.getBranchCode();
		query=this.getQuery(DBConstants.CURRENCY_LIST);
		list=this.mytemplate.queryForList(query, args);
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);
	}
	LOGGER.info("PremiumDAOImpl currencyList() || Exit List Size"+list.size());
	return list;
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
	//return list;
}
public String GetPreviousPremium(FaculPremiumBean bean) {
	String premium="";
	LOGGER.info("Enter Into GetPreviousPremium()");
	try{
		String query=getQuery("PREMIUM_QUOTA_SHARE");
		LOGGER.info("Query==>"+query);
		LOGGER.info("obj==>"+bean.getContNo());
		premium=(String)this.mytemplate.queryForObject(query, new Object[]{bean.getContNo()},String.class);
	}catch(Exception e){
		LOGGER.debug("Exception "+e);
	}
	LOGGER.info("Exit from GetPreviousPremium()"+premium);
	return premium;
}

public String GetContractPremium(FaculPremiumBean bean) {
	String premium="";
	LOGGER.info("Enter Into GetContractPremium()");
	try{
		String query=getQuery("GET_CONT_PREM");
		LOGGER.info("Query==>"+query);
		LOGGER.info("obj==>"+bean.getContNo()+","+bean.getDepartmentId()+","+bean.getBranchCode());
		premium=(String) this.mytemplate.queryForObject(query, new Object[]{bean.getContNo(),bean.getDepartmentId(),bean.getBranchCode()},String.class);
	}catch(Exception e){
		LOGGER.debug("Exception "+e);
	}
	LOGGER.info("Exit from GetContractPremium()"+premium);
	return premium;
}

public List<FaculPremiumBean> getCassLossCredit(FaculPremiumBean bean, String claimPayNo) {

	LOGGER.info("getCassLossCredit() || Enter");
	List<FaculPremiumBean> cashLossList = new ArrayList<FaculPremiumBean>();
	Double a=0.0;
	Double b=0.0;
	Object[] args=null;
	String selectQry="";
	try{
		if(StringUtils.isBlank(bean.getContractsearch())) {
		 args = new Object[3];
		args[0] = bean.getContNo();
		args[1] = bean.getContNo();
		args[2] = bean.getDepartmentId();
		selectQry = getQuery("GET_CASH_LOSS_CREADIT");
		if(StringUtils.isNotBlank(claimPayNo)){
			selectQry+=" AND CLAIM_PAYMENT_NO='"+claimPayNo+"'";
		}
		}else  {
			selectQry = getQuery("GET_CASH_LOSS_CREADIT1");
			selectQry += " AND TCP.CONTRACT_NO IN (select * from table(SPLIT_TEXT_FN('"+bean.getContractsearch()+"')))";
		}
		String excessRatePercent=this.mytemplate.queryForObject(getQuery("GET_EXCESS_RATE_PERCENT"),String.class);
		LOGGER.info("Query=>"+selectQry);
		List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
		String query=getQuery("GET_CURRENCY_NAME");
		LOGGER.info("Result Size=>"+list.size());
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
				FaculPremiumBean tempBean = new FaculPremiumBean();
				tempBean.setSerial_no(tempMap.get("SNO")==null?"":tempMap.get("SNO").toString());
				tempBean.setContNo(tempMap.get("CONTRACT_NO")==null?"":tempMap.get("CONTRACT_NO").toString());
				tempBean.setPaidDate(tempMap.get("INCEPTION_DATE")==null?"":tempMap.get("INCEPTION_DATE").toString());
				tempBean.setClaimNumber(tempMap.get("CLAIM_NO")==null?"":tempMap.get("CLAIM_NO").toString());
				tempBean.setClaimPaymentNo(tempMap.get("CLAIM_PAYMENT_NO")==null?"":tempMap.get("CLAIM_PAYMENT_NO").toString());
				tempBean.setPayamount(DropDownControllor.formatter(tempMap.get("PAID_AMOUNT_OC")==null?"":tempMap.get("PAID_AMOUNT_OC").toString()));
				tempBean.setCurrencyValue(tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString());
				tempBean.setExcessRatePercent(excessRatePercent);
				tempBean.setCurrencyId(bean.getCurrencyId());
				tempBean.setCurrencyValueName((String)this.mytemplate.queryForObject(query, new Object[]{bean.getBranchCode(),tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString()},String.class));
				tempBean.setCurrencyIdName((String)this.mytemplate.queryForObject(query, new Object[]{bean.getBranchCode(),bean.getCurrencyId()},String.class));
				if(StringUtils.isNotBlank(bean.getMainclaimPaymentNos()) && bean.getMainclaimPaymentNos().contains(tempMap.get("CLAIM_PAYMENT_NO")==null?"":tempMap.get("CLAIM_PAYMENT_NO").toString())){
					if(tempBean.getCurrencyValue().equalsIgnoreCase(tempBean.getCurrencyId())){
						tempBean.setStatus("true");
					}
					String [] value=bean.getMaincreditAmountCLClist().split(",");
					String [] value1=bean.getMaincreditAmountCLDlist().split(",");
					String [] value2=bean.getMainCLCsettlementRatelist().split(",");
					String [] value3=bean.getMainclaimPaymentNos().split(",");
					tempBean.setCreditAmountCLC(DropDownControllor.formatter(value[i]));
					tempBean.setCreditAmountCLD(DropDownControllor.formatter(value1[i]));
					tempBean.setCLCsettlementRate(value2[i]);
					tempBean.setCheck("true");
				}
				else if(StringUtils.isNotBlank(bean.getMode()) && "error".equals(bean.getMode()) ){
					if(tempBean.getCurrencyValue().equalsIgnoreCase(tempBean.getCurrencyId())){
						tempBean.setStatus("true");
					}
					if(bean.getCreditAmountCLClist()!=null && bean.getCreditAmountCLClist().size()>0 && StringUtils.isNotBlank(bean.getCreditAmountCLClist().get(i))){
					tempBean.setCreditAmountCLC((bean.getCreditAmountCLClist().get(i)));
					}else{
						tempBean.setCreditAmountCLC("");
					}
					if(bean.getCreditAmountCLDlist()!=null && bean.getCreditAmountCLDlist().size()>0 &&  StringUtils.isNotBlank(bean.getCreditAmountCLDlist().get(i))){
					tempBean.setCreditAmountCLD((bean.getCreditAmountCLDlist().get(i)));
					}else{
						tempBean.setCreditAmountCLD("");
					}
					if(bean.getCLCsettlementRatelist()!=null && bean.getCLCsettlementRatelist().size()>0 &&  StringUtils.isNotBlank(bean.getCLCsettlementRatelist().get(i))){
					tempBean.setCLCsettlementRate(bean.getCLCsettlementRatelist().get(i));
					}else{
						tempBean.setCLCsettlementRate("");
					}
				}
				else {
					if(tempBean.getCurrencyValue().equalsIgnoreCase(tempBean.getCurrencyId())){
					tempBean.setStatus("true");
					tempBean.setCreditAmountCLCTemp(DropDownControllor.formatter(tempMap.get("PAID_AMOUNT_OC")==null?"":tempMap.get("PAID_AMOUNT_OC").toString()));
					tempBean.setCreditAmountCLDTemp(tempBean.getCreditAmountCLCTemp());
					a=Double.parseDouble(tempMap.get("PAID_AMOUNT_OC")==null?"0":tempMap.get("PAID_AMOUNT_OC").toString());
					b=Double.parseDouble(tempBean.getCreditAmountCLDTemp().replace(",", ""));
					String c=Double.toString(a/b);
					tempBean.setCLCsettlementRateTemp(c);
						tempBean.setCreditAmountCLC("");
						tempBean.setCreditAmountCLD("");
						tempBean.setCLCsettlementRate("");
					}else{
						tempBean.setCreditAmountCLC("");
						tempBean.setCreditAmountCLD("");
						tempBean.setCLCsettlementRate("");
					}
					} 
				if((bean.getChkbox()!=null)&& bean.getChkbox().get(i).equalsIgnoreCase("true")){
					tempBean.setCheck("true");
				}
				cashLossList.add(tempBean);
			}
		}

	}
  	catch(Exception exe)
	{
	LOGGER.debug("Exception"+exe);
	}
  	LOGGER.info("getCassLossCredit() || Exit Map");
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
	try{
		String query = getQuery("GET_DEPARTMENT_NO");
		Object args[]=new Object[1];
		args[0] = bean.getContNo();
		deptNo = this.mytemplate.queryForObject(query,args,String.class);

	}
	catch(Exception e){

	}
	return deptNo;
}

	public List<Map<String,Object>> getConstantPeriodDropDown(String categoryId, String contNo,FaculPremiumBean bean) {
		LOGGER.info("getConstantPeriodDropDown() || Enter");
		List<Map<String,Object>> constantList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		boolean val = false;
		boolean preval=false;
		boolean lossval=false;
		
		String slide  = "";
		String combine ="";
		String premium ="";
		String preCombine="";
		String loss ="";
		String lossCombine="";
		String DeptNo ="";
		String proposalNo = "";
		String base="";
		
		
		try{
			String query="";
			LOGGER.info("Select Query==> " + query);
			LOGGER.info("Args[0]==> " + categoryId);
			LOGGER.info("Args[1]==> " + "Y");
			query =getQuery("GET_ACC_PERIOD");
			Object args[] = null;
			args=new Object[1];
			args[0] = bean.getProposal_No();
			String accPeriod = this.mytemplate.queryForObject(query,args,String.class);
			query = getQuery("COMMON_SELECT_GETCONSTDET_PTTY");
			args=new Object[2];
			args[0] = categoryId;
			args[1]= "Y";
			if("1".equalsIgnoreCase(accPeriod)){
				query +="AND REMARKS IN ('P','Q') ORDER BY   DETAIL_NAME";
			}else if("2".equalsIgnoreCase(accPeriod)){
				query +="AND REMARKS IN ('P','H') ORDER BY   DETAIL_NAME";
			}else if("3".equalsIgnoreCase(accPeriod)){
				query +="AND REMARKS IN ('P','Y') ORDER BY   DETAIL_NAME";
			}
			
			constantList=this.mytemplate.queryForList(query,args);
			query = getQuery("GET_BASE_LAYER");
			args=new Object[2];
			args[0] = contNo;
			args[1] = bean.getDepartmentId();
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args=>"+StringUtils.join(args, ","));
			
            result = this.mytemplate.queryForList(query,args);
            for(int i=0;i<result.size();i++) {
                Map<String, Object> map = result.get(i);
                proposalNo = map.get("PROPOSAL_NO").toString();
                base = map.get("BASE_LAYER").toString();
            }
			if(base.equalsIgnoreCase("0")){
                args=new Object[2];
                args[0] = proposalNo;
                args[1] = bean.getDepartmentId();
                LOGGER.info("Query=>"+query);
    			LOGGER.info("Args=>"+StringUtils.join(args, ","));
				query = getQuery("GET_SLIDE_COMM_VALUE");
				result = this.mytemplate.queryForList(query,args);
				for(int i=0;i<result.size();i++){
					Map<String,Object> map = result.get(i);
					 slide  = map.get("RSK_SLADSCALE_COMM").toString();
					 combine  = map.get("RSK_SLIDE_COMBIN_SUB_CLASS").toString();
					 premium = map.get("RSK_PROFIT_COMM").toString();
					 preCombine =map.get("RSK_COMBIN_SUB_CLASS").toString();
					 loss = map.get("RSK_LOSS_PART_CARRIDOR").toString();
					 lossCombine =map.get("RSK_LOSS_COMBIN_SUB_CLASS").toString();
					if(slide.equalsIgnoreCase("Y")){
						val = true ;
                        if(combine.equalsIgnoreCase("1")){
                           bean.setSlideScenario("one");
                        }
                        else if(combine.equalsIgnoreCase("2")){
                            bean.setSlideScenario("two");
                        }
					}
					if(premium.equalsIgnoreCase("1")){
						preval=true;
						if(preCombine.equalsIgnoreCase("1")){
							bean.setSlideScenario("one");
						}
						else if(preCombine.equalsIgnoreCase("2")){
                            bean.setSlideScenario("two");
                        }
					}
					if(loss.equalsIgnoreCase("Y")){
						lossval=true;
						if(lossCombine.equalsIgnoreCase("1")){
							bean.setSlideScenario("one");
						}
						else if(lossCombine.equalsIgnoreCase("2")){
                            bean.setSlideScenario("two");
                        }
					}
				}
			}
			else{
				args = new Object[1];
				args[0] = base;
				query = getQuery("GET_DEPT_ID");
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
                result = this.mytemplate.queryForList(query,args);
                for(int i=0;i<result.size();i++){
                    Map<String,Object> map = result.get(i);
                    DeptNo  = map.get("DEPT_ID").toString();
                }
                args=new Object[2];
                args[0] = base;
                args[1] = DeptNo;
				query = getQuery("GET_SLIDE_COMM_VALUE1");
				LOGGER.info("Query=>"+query);
				LOGGER.info("Args=>"+StringUtils.join(args, ","));
				result = this.mytemplate.queryForList(query,args);
				for(int i=0;i<result.size();i++){
                    Map<String,Object> map = result.get(i);
					 slide  = map.get("RSK_SLADSCALE_COMM").toString();
					 combine  = map.get("RSK_SLIDE_COMBIN_SUB_CLASS").toString();
					 premium = map.get("RSK_PROFIT_COMM").toString();
					 preCombine =map.get("RSK_COMBIN_SUB_CLASS").toString();
					 loss = map.get("RSK_LOSS_PART_CARRIDOR").toString();
					 lossCombine =map.get("RSK_LOSS_COMBIN_SUB_CLASS").toString();
				}
				if(combine.equalsIgnoreCase("2") || preCombine.equalsIgnoreCase("2")|| lossCombine.equalsIgnoreCase("2")) {
                    args=new Object[2];
                    args[0] = proposalNo;
                    args[1] = bean.getDepartmentId();
					query = getQuery("GET_SLIDE_COMM_VALUE2");
					LOGGER.info("Query=>"+query);
					LOGGER.info("Args=>"+StringUtils.join(args, ","));
					result = this.mytemplate.queryForList(query, args);
					for (int i = 0; i < result.size(); i++) {
                        Map<String,Object> map =result.get(i);
						 slide  = map.get("RSK_SLADSCALE_COMM").toString();
						 premium = map.get("RSK_PROFIT_COMM").toString();
						 loss = map.get("RSK_LOSS_PART_CARRIDOR").toString();
						if(slide.equalsIgnoreCase("Y") ){
							val = true ;
                            bean.setSlideScenario("three");
						}
						if(premium.equalsIgnoreCase("1")){
							preval= true;
							bean.setSlideScenario("three");
						}
						if(loss.equalsIgnoreCase("Y") ){
							lossval = true ;
                            bean.setSlideScenario("three");
						}
					}
				}
			}
			if(!val) {
                for (int i = 0; i < constantList.size(); i++) {
                    Map<String, Object> val1 = constantList.get(i);
                    String type = val1.get("TYPE").toString();
                    if (type.equalsIgnoreCase("8")) {
                        val1.remove(i);
                    } else {
                        res.add(val1);
                    }
                }
                constantList = res;
            }
			if(!preval){
				res=new ArrayList<Map<String,Object>>();
				 for (int i = 0; i < constantList.size(); i++) {
	                    Map<String, Object> val1 = constantList.get(i);
	                    String type = val1.get("TYPE").toString();
	                    if (type.equalsIgnoreCase("7")) {
	                        val1.remove(i);
	                    } else {
	                        res.add(val1);
	                    }
	                }
				 constantList = res;
			}
			if(!lossval){
				res=new ArrayList<Map<String,Object>>();
				 for (int i = 0; i < constantList.size(); i++) {
	                    Map<String, Object> val1 = constantList.get(i);
	                    String type = val1.get("TYPE").toString();
	                    if (type.equalsIgnoreCase("9")) {
	                        val1.remove(i);
	                    } else {
	                        res.add(val1);
	                    }
	                }
				 constantList = res;
			}


		}catch(Exception e){
			LOGGER.debug("Exception @ {" + e + "}");
		}
		LOGGER.info("getConstantPeriodDropDown() || Exit");
		return constantList;
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
		LOGGER.info("getPremiumReserved() || Enter");
		List<FaculPremiumBean> cashLossList = new ArrayList<FaculPremiumBean>();
		Double a=0.0;
		Double b=0.0;
		try{
			String selectQry="";
			Object[] args = new Object[3];
			args[0] = bean.getContNo();
			args[1] = bean.getDepartmentId();
			args[2] = bean.getTransaction();
			if("PRR".equals(bean.getType())){
			selectQry = getQuery("GET_PREMIUM_RESERVED_DETAILS");
			}
			else if("LRR".equals(bean.getType())){
				selectQry = getQuery("GET_LOSS_RESERVED_DETAILS");
			}
			if(StringUtils.isNotBlank(prTransNo)){
				selectQry+=" AND TRANSACTION_NO='"+prTransNo+"'";
			}
			LOGGER.info("Query=>"+selectQry);
			LOGGER.info("Agrs=>"+StringUtils.join(args,","));
			List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
			String query=getQuery("GET_CURRENCY_NAME");
			LOGGER.info("Result Size=>"+list.size());
			if (list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
					FaculPremiumBean tempBean = new FaculPremiumBean();
					tempBean.setSerial_no(tempMap.get("SNO")==null?"":tempMap.get("SNO").toString());
					tempBean.setContNo(tempMap.get("CONTRACT_NO")==null?"":tempMap.get("CONTRACT_NO").toString());
					tempBean.setTransactionNo(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
					tempBean.setPaidDate(tempMap.get("TRANSACTION_MONTH_YEAR")==null?"":tempMap.get("TRANSACTION_MONTH_YEAR").toString());
					tempBean.setPayamount(DropDownControllor.formatter(tempMap.get("PREMIUMRESERVE_QUOTASHARE_OC")==null?"":tempMap.get("PREMIUMRESERVE_QUOTASHARE_OC").toString()));
					tempBean.setCurrencyValue(tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString());
					tempBean.setCurrencyId(bean.getCurrencyId());
					tempBean.setPrallocatedTillDate(tempMap.get("ALLOCATE_TILLDATE")==null?"":tempMap.get("ALLOCATE_TILLDATE").toString());
					tempBean.setCurrencyValueName((String)this.mytemplate.queryForObject(query, new Object[]{bean.getBranchCode(),tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString()},String.class));
					tempBean.setCurrencyIdName((String)this.mytemplate.queryForObject(query, new Object[]{bean.getBranchCode(),bean.getCurrencyId()},String.class));
					if(StringUtils.isNotBlank(bean.getMainclaimPaymentNos()) && bean.getMainclaimPaymentNos().contains(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString())){
						if(tempBean.getCurrencyValue().equalsIgnoreCase(tempBean.getCurrencyId())){
							tempBean.setStatus("true");
						}
						String [] value=bean.getMaincreditAmountCLClist().split(",");
						String [] value1=bean.getMaincreditAmountCLDlist().split(",");
						String [] value2=bean.getMainCLCsettlementRatelist().split(",");
						String [] value3=bean.getMainclaimPaymentNos().split(",");
						tempBean.setCreditAmountCLC(DropDownControllor.formatter(value[i]));
						tempBean.setCreditAmountCLD(DropDownControllor.formatter(value1[i]));
						tempBean.setCLCsettlementRate(value2[i]);
						tempBean.setCheck("true");
					}
					else if(StringUtils.isNotBlank(bean.getMode()) && "error".equals(bean.getMode()) ){
						if(tempBean.getCurrencyValue().equalsIgnoreCase(tempBean.getCurrencyId())){
							tempBean.setStatus("true");
						}
						if(bean.getCreditAmountCLClist()!=null && bean.getCreditAmountCLClist().size()>0 && StringUtils.isNotBlank(bean.getCreditAmountCLClist().get(i))){
						tempBean.setCreditAmountCLC(DropDownControllor.formatter(bean.getCreditAmountCLClist().get(i).replaceAll(",", "")));
						}else{
							tempBean.setCreditAmountCLC("");
						}
						if(bean.getCreditAmountCLDlist()!=null && bean.getCreditAmountCLDlist().size()>0 &&  StringUtils.isNotBlank(bean.getCreditAmountCLDlist().get(i))){
						tempBean.setCreditAmountCLD(DropDownControllor.formatter(bean.getCreditAmountCLDlist().get(i).replaceAll(",", "")));
						}else{
							tempBean.setCreditAmountCLD("");
						}
						if(bean.getCLCsettlementRatelist()!=null && bean.getCLCsettlementRatelist().size()>0 &&  StringUtils.isNotBlank(bean.getCLCsettlementRatelist().get(i))){
						tempBean.setCLCsettlementRate(bean.getCLCsettlementRatelist().get(i).replaceAll(",", ""));
						}else{
							tempBean.setCLCsettlementRate("");
						}
					}
					else {
						if(tempBean.getCurrencyValue().equalsIgnoreCase(tempBean.getCurrencyId())){
							tempBean.setStatus("true");
							tempBean.setCreditAmountCLC(DropDownControllor.formatter(tempMap.get("PREMIUMRESERVE_QUOTASHARE_OC")==null?"":tempMap.get("PREMIUMRESERVE_QUOTASHARE_OC").toString()));
							tempBean.setCreditAmountCLD(tempBean.getCreditAmountCLC());
							a=Double.parseDouble(tempMap.get("PREMIUMRESERVE_QUOTASHARE_OC")==null?"0":tempMap.get("PREMIUMRESERVE_QUOTASHARE_OC").toString());
							b=Double.parseDouble(tempBean.getCreditAmountCLD().replaceAll(",", ""));
							String c=Double.toString(a/b);
							tempBean.setCLCsettlementRate(DropDownControllor.formattereight(c));
						}else{
							tempBean.setCreditAmountCLC(DropDownControllor.formatter(tempMap.get("PREMIUMRESERVE_QUOTASHARE_OC")==null?"":tempMap.get("PREMIUMRESERVE_QUOTASHARE_OC").toString()));
							String RTExchange=new DropDownControllor().GetExchangeRate(tempBean.getCurrencyValue(),bean.getTransaction(),countryId,bean.getBranchCode());
							String RLExchange=new DropDownControllor().GetExchangeRate(tempBean.getCurrencyId(),bean.getTransaction(),countryId,bean.getBranchCode());
							String c=Double.toString(Double.parseDouble(RTExchange)/Double.parseDouble(RLExchange));
							tempBean.setCLCsettlementRate(DropDownControllor.formattereight(c));
							tempBean.setCreditAmountCLD(DropDownControllor.formatter(Double.toString(Double.parseDouble(tempBean.getCreditAmountCLC().replaceAll(",", ""))/Double.parseDouble(c))));
							//tempBean.setStatus("false");
						}
						} 
					if((bean.getChkbox()!=null)&& bean.getChkbox().get(i).equalsIgnoreCase("true")){
						tempBean.setCheck("true");
					}
					cashLossList.add(tempBean);
				}
			}

		}
	  	catch(Exception exe)
		{
		LOGGER.debug("Exception"+exe);
		}
	  	LOGGER.info("getPremiumReserved() || Exit Map");
		return cashLossList;
	}
	
	private void InsertPremiumReserved(FaculPremiumBean bean, String transNo,String countryId) {
		try{
			
			LOGGER.info("InsertPremiumReserved || Enter");
			if(StringUtils.isNotBlank(bean.getPRTransNo())){
			String[] prTransNo=bean.getPRTransNo().split(",");
			String[] prAmount=bean.getPRAmount().split(",");
			String[] preAmount=bean.getPREAmount().split(",");
			String[] preRate=bean.getPRERate().split(",");
			bean.setType("PRR");
			
			String sql1="(SELECT nvl(MAX(RL_NO),5001)+1 FROM TTRN_DEPOSIT_RELEASE WHERE BRANCH_CODE=?)";
			String rLNo=this.mytemplate.queryForObject(sql1, new Object[]{bean.getBranchCode()},String.class);
			for(int i=0;i<prTransNo.length;i++) {
			if(StringUtils.isNotBlank(prTransNo[i])){
				List<FaculPremiumBean>cashLossList=getPremiumReserved(bean,prTransNo[i],countryId);
				FaculPremiumBean form= cashLossList.get(0);
				if(prTransNo[i].equals(form.getTransactionNo())) {
				Object[] obj=new Object[18];
				obj[0]="";
				obj[1]=form.getContNo();
				obj[2]=bean.getDepartmentId();
				obj[3]="PRR";
				obj[4]=rLNo;
				obj[5]=form.getTransactionNo();
				obj[6]=bean.getTransaction();
				obj[7]=prTransNo[i];
				obj[8]=form.getPaidDate();
				obj[9]=form.getCurrencyValue();
				obj[10]=bean.getCurrency();
				obj[11]=prAmount[i];
				obj[12]=preAmount[i];
				obj[13]=preRate[i];
				obj[14]=bean.getLoginId();
				obj[15]=bean.getBranchCode();
				if("submit".equalsIgnoreCase(bean.getButtonStatus())){
					obj[16] = "A";
				}else{
					obj[16] = "P";
				}
				obj[17] = bean.getRequestNo();
			String query=getQuery("INSERT_PREMIUM_RESERVE");
			LOGGER.info("Insert Query==>"+query);
			LOGGER.info("Obj==>"+StringUtils.join(obj,","));
		 	this.mytemplate.update(query, obj);
		 	if("submit".equalsIgnoreCase(bean.getButtonStatus())){
			 	String sql=getQuery("UPDATE_PREMIUM_RESERVE");
			 	LOGGER.info("Update Query==>"+sql);
			 	this.mytemplate.update(sql, new Object[]{form.getContNo(),form.getRequestNo(),"A",form.getContNo(),form.getTransactionNo()});
			 	}
			}
				}
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.info("InsertPremiumReserved || Exit");
			
		}
	private void InsertLossReserved(FaculPremiumBean bean, String transNo,String countryId) {
		try{
			
			LOGGER.info("InsertLossReserved || Enter");
			if(StringUtils.isNotBlank(bean.getLRTransNo())){
			String[] prTransNo=bean.getLRTransNo().split(",");
			String[] prAmount=bean.getLRAmount().split(",");
			String[] preAmount=bean.getLREAmount().split(",");
			String[] preRate=bean.getLRERate().split(",");
			bean.setType("LRR");
			
			String sql1="(SELECT nvl(MAX(RL_NO),5001)+1 FROM TTRN_DEPOSIT_RELEASE WHERE BRANCH_CODE=?)";
			String rLNo=this.mytemplate.queryForObject(sql1, new Object[]{bean.getBranchCode()},String.class);
			for(int i=0;i<prTransNo.length;i++) {
				if(StringUtils.isNotBlank(prTransNo[i])){
			List<FaculPremiumBean>cashLossList=getPremiumReserved(bean,prTransNo[i],countryId);
			FaculPremiumBean form= cashLossList.get(0);
			if(prTransNo[i].equals(form.getTransactionNo())) {
			Object[] obj=new Object[18];
			obj[0]="";
			obj[1]=form.getContNo();
			obj[2]=bean.getDepartmentId();
			obj[3]="LRR";
			obj[4]=rLNo;
			obj[5]=transNo;
			obj[6]=bean.getTransaction();
			obj[7]=prTransNo[i];
			obj[8]=form.getPaidDate();
			obj[9]=form.getCurrencyValue();
			obj[10]=bean.getCurrency();
			obj[11]=prAmount[i];
			obj[12]=preAmount[i];
			obj[13]=preRate[i];
			obj[14]=bean.getLoginId();
			obj[15]=bean.getBranchCode();
			if("submit".equalsIgnoreCase(bean.getButtonStatus())){
				obj[16] = "A";
			}else{
				obj[16] = "P";
			}
			obj[17] = bean.getRequestNo();
			String query=getQuery("INSERT_PREMIUM_RESERVE");
			LOGGER.info("Insert Query==>"+query);
			LOGGER.info("Obj==>"+StringUtils.join(obj,","));
		 	this.mytemplate.update(query, obj);
		 	if("submit".equalsIgnoreCase(bean.getButtonStatus())){
			 	String sql=getQuery("UPDATE_LOSS_RESERVE");
			 	LOGGER.info("Update Query==>"+sql);
			 	this.mytemplate.update(sql, new Object[]{form.getContNo(),form.getRequestNo(),"A",form.getContNo(),form.getTransactionNo()});
		 	}
		 	
			}
			}
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.info("InsertLossReserved || Exit");
			
		}


	public List<Map<String, Object>> getOSBList(FaculPremiumBean bean) {
		LOGGER.info("Enter in to getOSBList method");
		List<Map<String,Object>>list=null;
		double sum=0.00;
		try {
			String query=getQuery("GET_OBS_LIST");
			Object [] obj=new String[3];
			obj[0]=bean.getTransaction();
			obj[1]=bean.getContNo();
			obj[2]=bean.getBranchCode();
			
			LOGGER.info("Insert Query==>"+query);
			LOGGER.info("Obj==>"+StringUtils.join(obj,","));
			list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object>tempMap=list.get(i);
					sum+=Double.parseDouble(tempMap.get("OSCLAIM_LOSSUPDATE_OC")==null?"0":tempMap.get("OSCLAIM_LOSSUPDATE_OC").toString());
				}
			}
			bean.setTotalOSB(Double.toString(sum));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Exit in to getOSBList method");
		return list;
	}


	public int getCountCleanCUT(FaculPremiumBean bean) {
		LOGGER.info("Enter in to getCountCleanCUT method");
		int count=0;
		int count1=1;
		try {
			String query=getQuery("GET_CLEAN_CUT_CONT_COUNT");
			count=this.mytemplate.queryForInt(query,new Object[]{bean.getContNo()});
			if(count>0){
			query=getQuery("GET_CLEAN_CUT_COUNT");
			count1=this.mytemplate.queryForInt(query,new Object[]{bean.getContNo()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Exit in to getCountCleanCUT method");
		return count1;
		
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
		
		 LOGGER.info("PremiumContractDetails() || Enter");
	     String query="";
	     List<Object>result=new ArrayList<>();
	     List<Map<String,Object>>list=new ArrayList<>();
	     try {
			query=getQuery("GET_ALLOCATED_CASH_LOSS");
			 list=this.mytemplate.queryForList(query,new Object[] {bean.getProposal_No()});
			 if(list!=null && list.size()>0) {
				 for(int i=0;i<list.size();i++) {
				 Map<String,Object>res=new HashMap<>();
				 Map<String,Object>map=list.get(i);
				 res.put("CREDITTRXNNO", map.get("CREDITTRXNNO")==null?"":map.get("CREDITTRXNNO"));
				 res.put("CONTRACT_NO", map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO"));
				 res.put("CLAIM_NO", map.get("CLAIM_NO")==null?"":map.get("CLAIM_NO"));
				 res.put("CLAIMPAYMENT_NO", map.get("CLAIMPAYMENT_NO")==null?"":map.get("CLAIMPAYMENT_NO"));
				 res.put("CREDITDATE", map.get("CREDITDATE")==null?"":map.get("CREDITDATE"));
				 res.put("CLD_AMOUNT", map.get("CLD_AMOUNT")==null?"":map.get("CLD_AMOUNT"));
				 res.put("CLDCURRENCY_ID", (String)this.mytemplate.queryForObject(getQuery("GET_CURRENCY_NAME"), new Object[]{bean.getBranchCode(),map.get("CLDCURRENCY_ID")==null?"":map.get("CLDCURRENCY_ID").toString()},String.class));
				 res.put("CLCCURRENCY_ID", (String)this.mytemplate.queryForObject(getQuery("GET_CURRENCY_NAME"), new Object[]{bean.getBranchCode(),map.get("CLCCURRENCY_ID")==null?"":map.get("CLCCURRENCY_ID").toString()},String.class));
				 res.put("CREDITAMOUNTCLC", map.get("CREDITAMOUNTCLC")==null?"":map.get("CREDITAMOUNTCLC"));
				 res.put("CREDITAMOUNTCLD", map.get("CREDITAMOUNTCLD")==null?"":map.get("CREDITAMOUNTCLD"));
				 res.put("EXCHANGE_RATE", map.get("EXCHANGE_RATE")==null?"":map.get("EXCHANGE_RATE"));
				 result.add(res);
				}
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public List<Object> getAllocatedTransList(FaculPremiumBean bean) {
		LOGGER.info("PremiumContractDetails() || Enter");
	     String query="";
	     List<Object>result=new ArrayList<>();
	     List<Map<String,Object>>list=new ArrayList<>();
	     try {
			query=getQuery("GET_ALLOCATED_TRANS_LIST");
			 list=this.mytemplate.queryForList(query,new Object[] {bean.getProposal_No()});
			 if(list!=null && list.size()>0) {
				 for(int i=0;i<list.size();i++) {
				 Map<String,Object>res=new HashMap<>();
				 Map<String,Object>map=list.get(i);
				 res.put("CREDITTRXNNO", map.get("CREDITTRXNNO")==null?"":map.get("CREDITTRXNNO"));
				 res.put("CONTRACT_NO", map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO"));
				
				 result.add(res);
				}
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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


	
	
}


