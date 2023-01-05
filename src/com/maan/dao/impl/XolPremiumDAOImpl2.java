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
import com.maan.dao.XolPremiumDAO;
import com.opensymphony.xwork2.ActionContext;

public class XolPremiumDAOImpl2 extends MyJdbcTemplate implements XolPremiumDAO {
		
		private static final Logger LOGGER = LogUtil.getLogger(ProportionalPremiumDaoImpl.class);
		public boolean premiumInsertMethod(final FaculPremiumBean beanObj){
			LOGGER.info("PremiumDAOImpl premiumInsertMethod || Enter");
			boolean saveFlag = false;
			try {
					String query="";
					int result;
					String[] args = insertArguments(beanObj);
				 	String netDueOc="0";
				 	String transNo="";
				 	if("3".equalsIgnoreCase(beanObj.getProductId())){
				 		//query=getQuery(DBConstants.PREMIUM_INSERT_XOLPREMIUM);
				 		query=getQuery("PREMIUM_INSERT_XOLPREMIUM_TEMP");
				 		beanObj.setRequestNo(args[1]);
					}else{
						query=getQuery(DBConstants.PREMIUM_INSERT_RETROXOLPREMIUM);
						beanObj.setTransactionNo(args[1]);
					}
			 		netDueOc=args[17];
			 		//transNo=args[1];	
			 		
				 	LOGGER.info("Insert Query==>"+query);
				 	result=this.mytemplate.update(query, args);
				 	LOGGER.info("Insert Result==>"+result);		
				 	if("submit".equalsIgnoreCase(beanObj.getButtonStatus())){
				 		if("3".equalsIgnoreCase(beanObj.getProductId())){
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
				 		}
				 		
					if (result==1) {
						if(!("RP".equalsIgnoreCase(beanObj.getInstlmentNo()) || "RTP".equalsIgnoreCase(beanObj.getInstlmentNo()) || "RVP".equalsIgnoreCase(beanObj.getInstlmentNo()) || "AP".equalsIgnoreCase(beanObj.getInstlmentNo()))){
						query=getQuery("UPDATE_MND_INSTALLMENT");
						args = new String[4];
						args[0]=beanObj.getTransactionNo();
						args[1]=beanObj.getContNo();
						args[2]=StringUtils.isEmpty(beanObj.getLayerno())?"0":beanObj.getLayerno();
						args[3]=beanObj.getInstlmentNo();
						LOGGER.info("Insert Query==>"+query);
						LOGGER.info("Args==>"+StringUtils.join(args, ","));
						this.mytemplate.update(query,args);
						}
						
					}
					//insertBonus(beanObj);
					if("transEdit".equalsIgnoreCase(beanObj.getMode())){
						LOGGER.info("Reversel Transaction Enter Transaction No" +beanObj.getTransactionNo()+"Reverser Transaction No"+beanObj.getTransDropDownVal()+"Proposal No"+beanObj.getProposal_No());
						if("3".equalsIgnoreCase(beanObj.getProductId())){
						query=getQuery("UPDATE_REV_TRANSACTION_NO");
						}else{
						query=getQuery("UPDATE_REV_TRANSACTION_NO_RETRO");
						}
						String  query1=getQuery("UPDATE_REV_TRANSACTION_NO_TEMP");
						args=new String[2];
						args[0] = beanObj.getTransactionNo();
						args[1] = beanObj.getTransDropDownVal();
						this.mytemplate.update(query,args);
						this.mytemplate.update(query1,args);
						args[1] = beanObj.getTransactionNo();
						args[0] = beanObj.getTransDropDownVal();
						this.mytemplate.update(query,args);
						this.mytemplate.update(query1,args);
						query=getQuery("UPDATE_MND_INSTAL");
						args=new String[3];
						args[0] = "";
						args[1] = beanObj.getProposal_No();
						args[2] = beanObj.getTransDropDownVal();
						this.mytemplate.update(query,args);
					}
			}
			}
			catch (Exception exe) {
				LOGGER.debug(""+exe);			
			}
			LOGGER.info("PremiumDAOImpl premiumInsertMethod || Enter");
			return saveFlag;
		}

	private void getTempToMainMove(FaculPremiumBean beanObj, String netDueOc) {
		try{
			String query="";
			String args[] = new String[2];
			if(!"Main".equalsIgnoreCase(beanObj.getTableType())){
				query =getQuery("FAC_PREMIUM_TEMP_TO_MAIN");
		 		args = new String[2];
		 		args[0] = beanObj.getRequestNo();
		 		args[1] = beanObj.getBranchCode();
		 		this.mytemplate.update(query,args);
			}
			if( "3".equalsIgnoreCase(beanObj.getProductId())){
			query=getQuery(DBConstants.PREMIUM_SP_RETROSPLIT);
			LOGGER.info("SP Name==>"+query);
			args = new String[16];
			args[0]=beanObj.getContNo();
			args[1]=StringUtils.isEmpty(beanObj.getLayerno())?"0":beanObj.getLayerno();
			args[2]=beanObj.getProductId();
			args[3]=beanObj.getTransactionNo();
			args[4]=beanObj.getTransaction();
			if("3".equalsIgnoreCase(beanObj.getProductId())){
				args[5]=beanObj.getCurrencyId();
				}
				else{
					args[5]=beanObj.getCurrency();
				}
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
			//saveFlag = true;
			}
			else{
				 query=getQuery("PRCL_DELETE");
				Object arg[]=new Object[3];
				arg[0]=beanObj.getContNo();
				arg[1]=StringUtils.isEmpty(beanObj.getLayerno())?"0":beanObj.getLayerno();
				arg[2]=beanObj.getProductId();
				this.mytemplate.update(query,arg);
			}
		}
		catch (Exception exe) {
			LOGGER.debug(""+exe);			
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
			 query+=getQuery(DBConstants.PREMIUM_SELECT_XOLLAYERNO1);
			 query+=getQuery(DBConstants.PREMIUM_SELECT_TREATYCONTDET2);
			 query+=getQuery(DBConstants.PREMIUM_SELECT_XOLLAYERNO2);
			 query+=getQuery(DBConstants.PREMIUM_SELECT_TREATYCONTDET3);
			 args =new String[11];
			 args[0] = bean.getProductId();
			 args[1] = bean.getContNo();
			 args[2] = bean.getLayerno();
			 args[3] = bean.getBranchCode();
			 args[4] = bean.getBranchCode();
			 args[5] = bean.getProductId();
			 args[6] = bean.getBranchCode();
			 args[7] = bean.getBranchCode();
			 args[8] = bean.getContNo();
			 args[9] = bean.getLayerno();
			 args[10] = bean.getBranchCode();
			 LOGGER.info("LayerNo==>"+bean.getLayerno());
			 LOGGER.info("Select Query=>"+query);
			 for(int i=0;i<args.length;i++)
			 LOGGER.info("Args["+i+"]=>"+args[i]);
				List list = this.mytemplate.query(query, args,new RowMapper() {				
						public Object mapRow(ResultSet contDet, int rowNum) throws SQLException {
						bean.setContNo(contDet.getString("RSK_CONTRACT_NO"));
						bean.setAmendId(contDet.getString("RSK_ENDORSEMENT_NO"));
						bean.setProfit_Center(contDet.getString("TMAS_PFC_NAME"));
						bean.setSubProfit_center(contDet.getString("TMAS_SPFC_NAME"));
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
						bean.setDepartment_Name(contDet.getString("TMAS_DEPARTMENT_NAME"));
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
							bean.setCommission_view(commission.getString("RSK_COMM_QUOTASHARE"));
							bean.setPremium_Reserve_view(commission.getString("RSK_PREMIUM_RESERVE"));
							bean.setLoss_reserve_view(commission.getString("RSK_LOSS_RESERVE"));
							bean.setProfitCommYN(commission.getString("RSK_PROFIT_COMM"));
							bean.setCommissionSurb_view(commission.getString("RSK_COMM_SURPLUS"));
							bean.setOverRider_view(commission.getString("RSK_OVERRIDER_PERC"));
							bean.setBrokerage_view((commission.getString("RSK_BROKERAGE")));
							bean.setTax_view(DropDownControllor.formatter(commission.getString("RSK_TAX")));
							bean.setOtherCostView(DropDownControllor.formatter(commission.getString("RSK_OTHER_COST")));
							return null;
						}
					});
					args[0] = bean.getProposal_No();
					args[1] = bean.getProposal_No();
					query=getQuery(DBConstants.PREMIUM_SELECT_TREATYXOLPROPOSALDETAILS);
					this.mytemplate.query(query, args,new RowMapper() {
						public Object mapRow(ResultSet proposalDetails, int rowNum) throws SQLException {
							bean.setShareSigned(proposalDetails.getString("RSK_SHARE_SIGNED"));
							String mnd = proposalDetails.getString("RSK_MD_PREM_OS_OC")==null?"0":proposalDetails.getString("RSK_MD_PREM_OS_OC");
							String eps = (proposalDetails.getString("RSK_EPI_OSOF_OC")==null?"0":proposalDetails.getString("RSK_EPI_OSOF_OC"));
							bean.setRdsExchageRate(proposalDetails.getString("RSK_EXCHANGE_RATE"));
							double val= Double.parseDouble(eps)/Double.parseDouble(bean.getRdsExchageRate());
							double mndval= Double.parseDouble(mnd)/Double.parseDouble(bean.getRdsExchageRate());
							bean.setEPI_our_share_view(DropDownControllor.formatter(Double.toString(val)));
							bean.setMd_premium_view(DropDownControllor.formatter(Double.toString(mndval)));
							bean.setAdjustment_premium_temp(proposalDetails.getString("ADJ_PRE"));
							return null;
						}
					});
		query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY_NAME);
	   	bean.setCurrencyName((String)this.mytemplate.queryForObject(query,new Object[]{bean.getBranchCode()},String.class));
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

	   		String[] args=new String[6];
	   	 	args[0]=bean.getBranchCode();
		   	args[1]=bean.getProductId();
		   	args[2]=bean.getBranchCode();
		   	args[3]=bean.getProductId();
		   	args[4]=bean.getContNo();		  
		   	LOGGER.info("TransactionNO  ==>"+TransactionNo+ "RequestNo ==>  "+bean.getRequestNo());
			if("Temp".equalsIgnoreCase(bean.getTableType()) && "3".equalsIgnoreCase(bean.getProductId()) || "".equalsIgnoreCase(TransactionNo) ){
		   		args[5] = bean.getRequestNo();
		   		query=getQuery("XOL_PREMIUM_VIEW_DETAILS_TEMP");
		   	}else{
		   		args[5]=TransactionNo;
		   		if("3".equalsIgnoreCase(bean.getProductId())){
				   	query=getQuery(DBConstants.PREMIUM_SELECT_XOLPREMIUMVIEW);
				   	}else{
				   		query=getQuery(DBConstants.PREMIUM_SELECT_RETROXOLPREMIUMVIEW);
				   	}
		   	}
		   
		   	LOGGER.info("Select Query==>"+query);
		   	LOGGER.info("Args==>"+StringUtils.join(args, ","));
		   	
		   			this.mytemplate.query(query, args,new RowMapper() {
						public Object mapRow(ResultSet xolView, int rowNum) throws SQLException {
							bean.setContNo(xolView.getString("CONTRACT_NO"));
							bean.setTransactionNo(xolView.getString("TRANSACTION_NO"));
							bean.setTransaction(xolView.getString("TRANS_DATE")); 
							bean.setBrokerage(DropDownControllor.formatter(xolView.getString("BROKERAGE_AMT_OC")));
							bean.setTax(DropDownControllor.formatter(xolView.getString("TAX_AMT_OC")));
							bean.setMd_premium(DropDownControllor.formatter(xolView.getString("M_DPREMIUM_OC")));
							bean.setAdjustment_premium(DropDownControllor.formatter(xolView.getString("ADJUSTMENT_PREMIUM_OC")));							
							bean.setRecuirement_premium(DropDownControllor.formatter(xolView.getString("REC_PREMIUM_OC")));
							bean.setNetDue(DropDownControllor.formatter(xolView.getString("NETDUE_OC")));
							bean.setLayerno(xolView.getString("LAYER_NO"));
							bean.setEnteringMode(xolView.getString("ENTERING_MODE"));
							bean.setAccount_Period(xolView.getString("INSTALMENT_NUMBER")+(StringUtils.isBlank(xolView.getString("ACCOUNT_PERIOD_QTR"))?"":("_"+xolView.getString("ACCOUNT_PERIOD_QTR"))));
							bean.setCurrencyId(xolView.getString("CURRENCY_ID"));
							bean.setOtherCost(DropDownControllor.formatter(xolView.getString("OTHER_COST_OC")));
							bean.setBrokerage_usd(DropDownControllor.formatter(xolView.getString("BROKERAGE_AMT_DC")));
							bean.setTax_usd(DropDownControllor.formatter(xolView.getString("TAX_AMT_DC")));
							bean.setMd_premium_usd(DropDownControllor.formatter(xolView.getString("M_DPREMIUM_DC")));
							bean.setAdjustment_premium_usd(DropDownControllor.formatter(xolView.getString("ADJUSTMENT_PREMIUM_DC")));
							bean.setRecuirement_premium_usd(DropDownControllor.formatter(xolView.getString("REC_PREMIUM_DC")));
							bean.setNet_due_usd(DropDownControllor.formatter(xolView.getString("NETDUE_DC")));
							bean.setOtherCostUSD(DropDownControllor.formatter(xolView.getString("OTHER_COST_DC")));
							bean.setInception_Date(xolView.getString("ENTRY_DATE"));
							bean.setCedentRef(xolView.getString("CEDANT_REFERENCE"));
							bean.setRemarks(xolView.getString("REMARKS"));
							bean.setTotalCredit(DropDownControllor.formatter(xolView.getString("TOTAL_CR_OC")));
							bean.setTotalCreditDC(DropDownControllor.formatter(xolView.getString("TOTAL_CR_DC")));
							bean.setTotalDebit(DropDownControllor.formatter(xolView.getString("TOTAL_DR_OC")));
							bean.setTotalDebitDC(DropDownControllor.formatter(xolView.getString("TOTAL_DR_DC")));
							//bean.setSettlement_status(xolView.getString("SETTLEMENT_STATUS"));
							bean.setAmendmentDate(xolView.getString("AMENDMENT_DATE"));
                            bean.setWithHoldingTaxOC(DropDownControllor.formatter(xolView.getString("WITH_HOLDING_TAX_OC")));
                            bean.setWithHoldingTaxDC(DropDownControllor.formatter(xolView.getString("WITH_HOLDING_TAX_DC")));
                            bean.setDueDate(xolView.getString("due_date"));
                            bean.setCreditsign(xolView.getString("NETDUE_OC"));
                            bean.setRi_cession(xolView.getString("RI_CESSION"));
                            bean.setPredepartment(xolView.getString("PREMIUM_CLASS"));
                            bean.setDepartmentId(xolView.getString("SUB_CLASS"));
                            bean.setTaxDedectSource(DropDownControllor.formatter(xolView.getString("TDS_OC")));
                            bean.setTaxDedectSourceDc(DropDownControllor.formatter(xolView.getString("TDS_DC")));
                            bean.setServiceTax(DropDownControllor.formatter(xolView.getString("ST_OC")));
                            bean.setServiceTaxDc(DropDownControllor.formatter(xolView.getString("ST_DC")));
                            bean.setBonus(DropDownControllor.formatter(xolView.getString("BONUS_OC")));
                            bean.setBonusDc(DropDownControllor.formatter(xolView.getString("BONUS_DC")));
            				bean.setExchRate(DropDownControllor.exchRateFormat(xolView.getString("EXCHANGE_RATE")));
                            bean.setGnpiDate(xolView.getString("GNPI_ENDT_NO")==null?"":xolView.getString("GNPI_ENDT_NO"));
                            bean.setStatementDate(xolView.getString("STATEMENT_DATE")==null?"":xolView.getString("STATEMENT_DATE"));
                            bean.setChooseTransaction(xolView.getString("REVERSEL_STATUS")==null?"":xolView.getString("REVERSEL_STATUS"));
                            bean.setTransDropDownVal(xolView.getString("REVERSE_TRANSACTION_NO")==null?"":xolView.getString("REVERSE_TRANSACTION_NO"));
						return null;
						}				
			});		 	
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
		   	if("3".equalsIgnoreCase(bean.getProductId())){
		   	query=getQuery("GETSETTLEMET_STATUS");
			List<Map<String,Object>> premlist = new ArrayList<Map<String,Object>>();
			premlist = this.mytemplate.queryForList(query,new Object[]{bean.getContNo()});
			if(premlist.size()>0){
				for(int i=0;i<premlist.size();i++){
					Map<String,Object> map = premlist.get(i);
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
		   	}
			LOGGER.info("PremiumDAOImpl getPremiumDetails || Exit");
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);	
			e.printStackTrace();
		}
		return false;
	}

	public List<FaculPremiumBean> getPremiumedList(final FaculPremiumBean beanObj,String type)
	{
		List<FaculPremiumBean> finalList = new ArrayList<FaculPremiumBean>();
		LOGGER.info("PremiumDAOImpl getPremiumedList || Enter");
		String query="";
	    Object[] args=null;
	    args=new String[5];
    	args[0]=beanObj.getContNo();
    	args[1]=beanObj.getBranchCode();
    	args[2]=beanObj.getContNo();
    	args[3]=beanObj.getLayerno();
    	args[4]=beanObj.getLayerno();
    	if("Main".equalsIgnoreCase(type)){
	    	if("3".equalsIgnoreCase(beanObj.getProductId())){
	    	query=getQuery(DBConstants.PREMIUM_SELECT_PREMIUMEDLIST1);
	    	}else{
	    	query=getQuery(DBConstants.PREMIUM_SELECT_RETROPREMIUMEDLIST1);
	    	}
			query += " " + getQuery(DBConstants.PREMIUM_SELECT_XOLLAYER1);
			query += " " + getQuery(DBConstants.PREMIUM_SELECT_PREMIUMEDLIST2);
			query += " " + getQuery(DBConstants.PREMIUM_SELECT_XOLLAYER2);
			query += " " + getQuery(DBConstants.PREMIUM_SELECT_XOLLAYER3);
			query += " " + getQuery(DBConstants.PREMIUM_SELECT_PREMIUMEDLIST3);
    	}else{
    		//if("3".equalsIgnoreCase(beanObj.getProductId())){
    	    	query=getQuery("XOL_PREMIUM_LIST_TEMP");
    	    //	}else{
    	   //	query=getQuery("RETRO_PREMIUM_LIST_TEMP");
    	   //	}
    	}
		LOGGER.info("Query=>"+query);
		LOGGER.info("Args=>"+StringUtils.join(args,","));
		List<Map<String,Object>> list=this.mytemplate.queryForList(query, args);
		for(int i=0 ; i<list.size() ; i++) {
			Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
			FaculPremiumBean tempBean = new FaculPremiumBean();
			tempBean.setProposal_No(tempMap.get("RSK_PROPOSAL_NUMBER")==null?"":tempMap.get("RSK_PROPOSAL_NUMBER").toString());
			tempBean.setContNo(tempMap.get("RSK_CONTRACT_NO")==null?"":tempMap.get("RSK_CONTRACT_NO").toString());
			tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME")==null?"":tempMap.get("COMPANY_NAME").toString());
			tempBean.setBroker(tempMap.get("BROKER_NAME")==null?"":tempMap.get("BROKER_NAME").toString());
			tempBean.setLayerno(tempMap.get("RSK_LAYER_NO")==null?"":tempMap.get("RSK_LAYER_NO").toString());
			tempBean.setTransactionNo(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
			tempBean.setAccount_Period(tempMap.get("ACC_PER")==null?"":tempMap.get("ACC_PER").toString());
			tempBean.setAccount_Period(tempMap.get("INS_DETAIL")==null?"":tempMap.get("INS_DETAIL").toString());
			tempBean.setTransDropDownVal(tempMap.get("REVERSE_TRANSACTION_NO")==null?"":tempMap.get("REVERSE_TRANSACTION_NO").toString());
			tempBean.setRequestNo(tempMap.get("REQUEST_NO")==null?"":tempMap.get("REQUEST_NO").toString());
			if(i==0)
				tempBean.setEndtYN("No");
			else
				tempBean.setEndtYN("Yes");
			if(Double.parseDouble(tempMap.get("ALLOC_AMT").toString())!=0)
				tempBean.setEndtYN("Yes");
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
			tempBean.setProductId("3");
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
			
			if(count!=0 && allocationstatus ==0 && retroPrclStatus1 ==0 ){
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
		 List list;
		 List<Map<String,Object>> transList=new ArrayList<Map<String,Object>>();
		 boolean saveFlag=false;
				String[] args = new String[2];
				args[0] = bean.getContNo();
				if("Temp".equalsIgnoreCase(bean.getTableType()) && "3".equalsIgnoreCase(bean.getProductId())){
					query=getQuery("XOL_PREMIUM_EDIT_TEMP");
					args[1] = bean.getRequestNo();
				}else{
					args[1] = bean.getTransactionNo();
					if("3".equalsIgnoreCase(bean.getProductId())){
					query=getQuery(DBConstants.PREMIUM_SELECT_TREETYXOLPREMIUMEDIT);
					}
					else{
						query=getQuery(DBConstants.PREMIUM_SELECT_RETROTREETYXOLPREMIUMEDIT);	
					}
				}
				LOGGER.info("Query=>"+query.toString());
				if( "transEdit".equalsIgnoreCase(bean.getMode())){
					
					args[0] = bean.getContNo();
					args[1] = bean.getTransDropDownVal();
					LOGGER.info("Query=>"+query.toString());
					transList=this.mytemplate.queryForList(query, args);
					 if(transList.size()>0)
					 {
						 for(int i=0;i<transList.size();i++){
							 Map<String,Object>	 editPremium=transList.get(i);
							 bean.setCurrencyId(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
								bean.setCurrency(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
								if(null==editPremium.get("EXCHANGE_RATE")){
									bean.setExchRate( new DropDownControllor().GetExchangeRate(bean.getCurrencyId(),bean.getTransaction(),countryId,bean.getBranchCode()));
								}
								else{
								bean.setExchRate(DropDownControllor.exchRateFormat(editPremium.get("EXCHANGE_RATE")==null?"":editPremium.get("EXCHANGE_RATE").toString()));
								}
								bean.setBrokerage((editPremium.get("BROKERAGE_AMT_OC")==null?"":editPremium.get("BROKERAGE_AMT_OC").toString()));
								bean.setBrokerage((getMultipleVal(bean.getBrokerage())));
								bean.setTax((editPremium.get("TAX_AMT_OC")==null?"":editPremium.get("TAX_AMT_OC").toString()));
								bean.setTax((getMultipleVal(bean.getTax())));
								bean.setPremiumQuotaShare(editPremium.get("PREMIUM_QUOTASHARE_OC")==null?"":editPremium.get("PREMIUM_QUOTASHARE_OC").toString());
								bean.setPremiumQuotaShare((getMultipleVal(bean.getPremiumQuotaShare())));
								bean.setCommissionQuotaShare(editPremium.get("COMMISSION_QUOTASHARE_OC")==null?"":editPremium.get("COMMISSION_QUOTASHARE_OC").toString());
								bean.setCommissionQuotaShare((getMultipleVal(bean.getCommissionQuotaShare())));
								bean.setPremiumSurplus(editPremium.get("PREMIUM_SURPLUS_OC")==null?"":editPremium.get("PREMIUM_SURPLUS_OC").toString());
								bean.setPremiumSurplus((getMultipleVal(bean.getPremiumSurplus())));
								bean.setCommissionSurplus(editPremium.get("COMMISSION_SURPLUS_OC")==null?"":editPremium.get("COMMISSION_SURPLUS_OC").toString());
								bean.setCommissionSurplus((getMultipleVal(bean.getCommissionSurplus())));
								bean.setPremiumportifolioIn(editPremium.get("PREMIUM_PORTFOLIOIN_OC")==null?"":editPremium.get("PREMIUM_PORTFOLIOIN_OC").toString());
								bean.setPremiumportifolioIn((getMultipleVal(bean.getPremiumportifolioIn())));
								bean.setCliamPortfolioin(editPremium.get("CLAIM_PORTFOLIOIN_OC")==null?"":editPremium.get("CLAIM_PORTFOLIOIN_OC").toString());
								bean.setCliamPortfolioin((getMultipleVal(bean.getCliamPortfolioin())));
								bean.setPremiumportifolioout(editPremium.get("PREMIUM_PORTFOLIOOUT_OC")==null?"":editPremium.get("PREMIUM_PORTFOLIOOUT_OC").toString());
								bean.setPremiumportifolioout((getMultipleVal(bean.getPremiumportifolioout())));
								bean.setLossReserveReleased(editPremium.get("LOSS_RESERVE_RELEASED_OC")==null?"":editPremium.get("LOSS_RESERVE_RELEASED_OC").toString());
								bean.setLossReserveReleased((getMultipleVal(bean.getLossReserveReleased())));
								bean.setPremiumReserve_QuotaShare(editPremium.get("PREMIUMRESERVE_QUOTASHARE_OC")==null?"":editPremium.get("PREMIUMRESERVE_QUOTASHARE_OC").toString());
								bean.setPremiumReserve_QuotaShare((getMultipleVal(bean.getPremiumReserve_QuotaShare())));
								bean.setCashLoss_Credit(editPremium.get("CASH_LOSS_CREDIT_OC")==null?"":editPremium.get("CASH_LOSS_CREDIT_OC").toString());
								bean.setCashLoss_Credit((getMultipleVal(bean.getCashLoss_Credit())));
								bean.setLoss_ReserveRetained(editPremium.get("LOSS_RESERVERETAINED_OC")==null?"":editPremium.get("LOSS_RESERVERETAINED_OC").toString());
								bean.setLoss_ReserveRetained((getMultipleVal(bean.getLoss_ReserveRetained())));
								bean.setProfit_Commission(editPremium.get("PROFIT_COMMISSION_OC")==null?"":editPremium.get("PROFIT_COMMISSION_OC").toString());
								bean.setProfit_Commission((getMultipleVal(bean.getProfit_Commission())));
								bean.setCash_LossPaid(editPremium.get("CASH_LOSSPAID_OC")==null?"":editPremium.get("CASH_LOSSPAID_OC").toString());
								bean.setCash_LossPaid((getMultipleVal(bean.getCash_LossPaid())));
								bean.setStatus(editPremium.get("STATUS")==null?"":editPremium.get("STATUS").toString());
								bean.setNetDue(editPremium.get("NETDUE_OC")==null?"":editPremium.get("NETDUE_OC").toString());
								bean.setNetDue((getMultipleVal(bean.getNetDue())));
								//bean.setEnteringMode(editPremium.get("ENTERING_MODE")==null?"":editPremium.get("ENTERING_MODE").toString().trim());
								bean.setReceipt_no(editPremium.get("RECEIPT_NO")==null?"":editPremium.get("RECEIPT_NO").toString());
								bean.setClaims_paid(editPremium.get("CLAIMS_PAID_OC")==null?"":editPremium.get("CLAIMS_PAID_OC").toString());	
								bean.setClaims_paid((getMultipleVal(bean.getClaims_paid())));
								//bean.setSettlement_status(editPremium.get("SETTLEMENT_STATUS"));		
							    bean.setMd_premium(DropDownControllor.formatter(editPremium.get("M_DPREMIUM_OC")==null?"":editPremium.get("M_DPREMIUM_OC").toString()));
							    bean.setMd_premium((getMultipleVal(bean.getMd_premium())));
							    bean.setAdjustment_premium(editPremium.get("ADJUSTMENT_PREMIUM_OC")==null?"":editPremium.get("ADJUSTMENT_PREMIUM_OC").toString());
							    bean.setAdjustment_premium((getMultipleVal(bean.getAdjustment_premium())));
							    bean.setRecuirement_premium(editPremium.get("REC_PREMIUM_OC")==null?"":editPremium.get("REC_PREMIUM_OC").toString());
							    bean.setRecuirement_premium((getMultipleVal(bean.getRecuirement_premium())));
							    bean.setCommission(editPremium.get("COMMISSION")==null?"":editPremium.get("COMMISSION").toString());
							    bean.setCommission((getMultipleVal(bean.getCommission())));
							   // bean.setInstlmentNo(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString());
							    //bean.setInception_Date(editPremium.get("INS_DATE")==null?"":editPremium.get("INS_DATE").toString());
							    bean.setXl_Cost(editPremium.get("XL_COST_OC")==null?"":editPremium.get("XL_COST_OC").toString());
							    bean.setXl_Cost((getMultipleVal(bean.getXl_Cost())));
							    bean.setCliam_portfolio_out(editPremium.get("CLAIM_PORTFOLIO_OUT_OC")==null?"":editPremium.get("CLAIM_PORTFOLIO_OUT_OC").toString());
							    bean.setCliam_portfolio_out((getMultipleVal(bean.getCliam_portfolio_out())));
							    bean.setPremium_Reserve_Released(editPremium.get("PREMIUM_RESERVE_REALSED_OC")==null?"":editPremium.get("PREMIUM_RESERVE_REALSED_OC").toString());
							    bean.setPremium_Reserve_Released((getMultipleVal(bean.getPremium_Reserve_Released())));
							    bean.setOtherCost((editPremium.get("OTHER_COST_OC")==null?"":editPremium.get("OTHER_COST_OC").toString()));
							    bean.setOtherCost((getMultipleVal(bean.getOtherCost())));
							    //bean.setCedentRef(editPremium.get("CEDANT_REFERENCE")==null?"":editPremium.get("CEDANT_REFERENCE").toString());
								//bean.setRemarks(editPremium.get("REMARKS")==null?"":editPremium.get("REMARKS").toString());
								//bean.setNetDue(editPremium.get("NETDUE_OC")==null?"":editPremium.get("NETDUE_OC").toString());
								//bean.setNetDue((getMultipleVal(bean.getNetDue())));
								bean.setInterest((editPremium.get("INTEREST_OC")==null?"":editPremium.get("INTEREST_OC").toString()));
								bean.setInterest((getMultipleVal(bean.getInterest())));
								bean.setOsClaimsLossUpdateOC(editPremium.get("OSCLAIM_LOSSUPDATE_OC")==null?"":editPremium.get("OSCLAIM_LOSSUPDATE_OC").toString());
								bean.setOsClaimsLossUpdateOC((getMultipleVal(bean.getOsClaimsLossUpdateOC())));
								bean.setOverrider(editPremium.get("OVERRIDER_AMT_OC")==null?"":editPremium.get("OVERRIDER_AMT_OC").toString());
								bean.setOverrider((getMultipleVal(bean.getOverrider())));
								bean.setOverriderUSD(editPremium.get("OVERRIDER_AMT_DC")==null?"":editPremium.get("OVERRIDER_AMT_DC").toString());	
								bean.setOverriderUSD((getMultipleVal(bean.getOverriderUSD())));
								//bean.setAmendmentDate(editPremium.get("AMENDMENT_DATE")==null?"":editPremium.get("AMENDMENT_DATE").toString());	
		                        bean.setWithHoldingTaxOC((editPremium.get("WITH_HOLDING_TAX_OC")==null?"":editPremium.get("WITH_HOLDING_TAX_OC").toString()));
		                        bean.setWithHoldingTaxOC((getMultipleVal(bean.getWithHoldingTaxOC())));
		                        //bean.setWithHoldingTaxDC(DropDownControllor.formatter(editPremium.get("WITH_HOLDING_TAX_DC")==null?"":editPremium.get("WITH_HOLDING_TAX_DC").toString()));
		                        //bean.setRi_cession(editPremium.get("RI_CESSION")==null?"":editPremium.get("RI_CESSION").toString());
		                        bean.setPredepartment(editPremium.get("PREMIUM_CLASS")==null?"":editPremium.get("PREMIUM_CLASS").toString());
		                        bean.setDepartmentId(editPremium.get("SUB_CLASS")==null?"":editPremium.get("SUB_CLASS").toString());
		                        bean.setTaxDedectSource((editPremium.get("TDS_OC")==null?"":editPremium.get("TDS_OC").toString()));
		                        bean.setTaxDedectSource((getMultipleVal(bean.getTaxDedectSource())));
		                        //bean.setTaxDedectSourceDc(DropDownControllor.formatter(editPremium.get("TDS_DC")==null?"":editPremium.get("TDS_DC").toString()));
		                        bean.setServiceTax(DropDownControllor.formatter(editPremium.get("ST_OC")==null?"":editPremium.get("ST_OC").toString()));
		                        bean.setServiceTax((getMultipleVal(bean.getCommissionSurplus())));
		                        //bean.setServiceTaxDc(DropDownControllor.formatter(editPremium.get("ST_DC")==null?"":editPremium.get("ST_DC").toString()));
		                        bean.setBonus(DropDownControllor.formatter(editPremium.get("BONUS_OC")==null?"":editPremium.get("BONUS_OC").toString()));
		                        bean.setBonus((getMultipleVal(bean.getBonus())));
		                        //bean.setBonusDc(DropDownControllor.formatter(editPremium.get("BONUS_DC")==null?"":editPremium.get("BONUS_DC").toString()));
		                        bean.setTotalCredit((editPremium.get("TOTAL_CR_OC")==null?"":editPremium.get("TOTAL_CR_OC").toString()));
		                        bean.setTotalCredit((getMultipleVal(bean.getTotalCredit())));
		    					bean.setTotalDebit((editPremium.get("TOTAL_DR_OC")==null?"":editPremium.get("TOTAL_DR_OC").toString()));
		    					bean.setTotalDebit((getMultipleVal(bean.getTotalDebit())));
						}
					 }
				}
				else{
				if("3".equalsIgnoreCase(bean.getProductId())){
					LOGGER.info("Query=>"+query.toString());
					transList=this.mytemplate.queryForList(query, args);
					 if(transList.size()>0)
					 {
						 for(int i=0;i<transList.size();i++){
							 Map<String,Object>	 editPremium=transList.get(i);
						bean.setTransaction(editPremium.get("TRANS_DATE")==null?"":editPremium.get("TRANS_DATE").toString()); 
						bean.setAccount_Period(editPremium.get("ACCOUNT_PERIOD_QTR")==null?"":editPremium.get("ACCOUNT_PERIOD_QTR").toString());
						bean.setAccount_Period_year(editPremium.get("ACCOUNT_PERIOD_YEAR")==null?"":editPremium.get("ACCOUNT_PERIOD_YEAR").toString());
						bean.setCurrencyId(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
						bean.setCurrency(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
						if(null==editPremium.get("EXCHANGE_RATE")){
							bean.setExchRate( new DropDownControllor().GetExchangeRate(bean.getCurrencyId(),bean.getTransaction(),countryId,bean.getBranchCode()));
						}
						else{
						bean.setExchRate(DropDownControllor.exchRateFormat(editPremium.get("EXCHANGE_RATE")==null?"0":editPremium.get("EXCHANGE_RATE").toString()));
						}
						bean.setBrokerage(DropDownControllor.formatter(editPremium.get("BROKERAGE_AMT_OC")==null?"0":editPremium.get("BROKERAGE_AMT_OC").toString()));
						bean.setTax(DropDownControllor.formatter(editPremium.get("TAX_AMT_OC")==null?"0":editPremium.get("TAX_AMT_OC").toString()));
						bean.setPremiumQuotaShare(editPremium.get("PREMIUM_QUOTASHARE_OC")==null?"":editPremium.get("PREMIUM_QUOTASHARE_OC").toString());
						bean.setCommissionQuotaShare(editPremium.get("COMMISSION_QUOTASHARE_OC")==null?"":editPremium.get("COMMISSION_QUOTASHARE_OC").toString());
						bean.setPremiumSurplus(editPremium.get("PREMIUM_SURPLUS_OC")==null?"":editPremium.get("PREMIUM_SURPLUS_OC").toString());
						bean.setCommissionSurplus(editPremium.get("COMMISSION_SURPLUS_OC")==null?"":editPremium.get("COMMISSION_SURPLUS_OC").toString());
						bean.setPremiumportifolioIn(editPremium.get("PREMIUM_PORTFOLIOIN_OC")==null?"":editPremium.get("PREMIUM_PORTFOLIOIN_OC").toString());
						bean.setCliamPortfolioin(editPremium.get("CLAIM_PORTFOLIOIN_OC")==null?"":editPremium.get("CLAIM_PORTFOLIOIN_OC").toString());
						bean.setPremiumportifolioout(editPremium.get("PREMIUM_PORTFOLIOOUT_OC")==null?"":editPremium.get("PREMIUM_PORTFOLIOOUT_OC").toString());
						bean.setLossReserveReleased(editPremium.get("LOSS_RESERVE_RELEASED_OC")==null?"":editPremium.get("LOSS_RESERVE_RELEASED_OC").toString());
						bean.setPremiumReserve_QuotaShare(editPremium.get("PREMIUMRESERVE_QUOTASHARE_OC")==null?"":editPremium.get("PREMIUMRESERVE_QUOTASHARE_OC").toString());
						bean.setCashLoss_Credit(editPremium.get("CASH_LOSS_CREDIT_OC")==null?"":editPremium.get("CASH_LOSS_CREDIT_OC").toString());
						bean.setLoss_ReserveRetained(editPremium.get("LOSS_RESERVERETAINED_OC")==null?"":editPremium.get("LOSS_RESERVERETAINED_OC").toString());
						bean.setProfit_Commission(editPremium.get("PROFIT_COMMISSION_OC")==null?"":editPremium.get("PROFIT_COMMISSION_OC").toString());
						bean.setCash_LossPaid(editPremium.get("CASH_LOSSPAID_OC")==null?"":editPremium.get("CASH_LOSSPAID_OC").toString());
						bean.setStatus(editPremium.get("STATUS")==null?"":editPremium.get("STATUS").toString());
						bean.setNetDue(editPremium.get("NETDUE_OC")==null?"":editPremium.get("NETDUE_OC").toString());
						bean.setEnteringMode(editPremium.get("ENTERING_MODE")==null?"":editPremium.get("ENTERING_MODE").toString().trim());
						bean.setReceipt_no(editPremium.get("RECEIPT_NO")==null?"":editPremium.get("RECEIPT_NO").toString());
						bean.setClaims_paid(editPremium.get("CLAIMS_PAID_OC")==null?"":editPremium.get("CLAIMS_PAID_OC").toString());				 
						//bean.setSettlement_status(editPremium.get("SETTLEMENT_STATUS"));		
					    bean.setMd_premium(DropDownControllor.formatter(editPremium.get("M_DPREMIUM_OC")==null?"":editPremium.get("M_DPREMIUM_OC").toString()));
					    bean.setAdjustment_premium(editPremium.get("ADJUSTMENT_PREMIUM_OC")==null?"":editPremium.get("ADJUSTMENT_PREMIUM_OC").toString());
					    bean.setRecuirement_premium(editPremium.get("REC_PREMIUM_OC")==null?"":editPremium.get("REC_PREMIUM_OC").toString());
					    bean.setCommission(editPremium.get("COMMISSION")==null?"":editPremium.get("COMMISSION").toString());
					    bean.setInstlmentNo(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString());
				    	if("RP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString())||"AP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()) ||"RTP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()) ||"RVP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
				    	{
				    		bean.setAccount_Period(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString());
				    	}else
				    	{
				    		bean.setAccount_Period(editPremium.get("INSTALMENT_NUMBER")+"_"+editPremium.get("ACCOUNT_PERIOD_QTR"));
				    	}					
					    bean.setInception_Date(editPremium.get("INS_DATE")==null?"":editPremium.get("INS_DATE").toString());
					    bean.setXl_Cost(editPremium.get("XL_COST_OC")==null?"":editPremium.get("XL_COST_OC").toString());
					    bean.setCliam_portfolio_out(editPremium.get("CLAIM_PORTFOLIO_OUT_OC")==null?"":editPremium.get("CLAIM_PORTFOLIO_OUT_OC").toString());
					    bean.setPremium_Reserve_Released(editPremium.get("PREMIUM_RESERVE_REALSED_OC")==null?"":editPremium.get("PREMIUM_RESERVE_REALSED_OC").toString());
					    bean.setOtherCost(DropDownControllor.formatter(editPremium.get("OTHER_COST_OC")==null?"":editPremium.get("OTHER_COST_OC").toString()));
					    bean.setCedentRef(editPremium.get("CEDANT_REFERENCE")==null?"":editPremium.get("CEDANT_REFERENCE").toString());
						bean.setRemarks(editPremium.get("REMARKS")==null?"":editPremium.get("REMARKS").toString());
						bean.setNetDue(editPremium.get("NETDUE_OC")==null?"":editPremium.get("NETDUE_OC").toString());
						bean.setInterest(DropDownControllor.formatter(editPremium.get("INTEREST_OC")==null?"0":editPremium.get("INTEREST_OC").toString()));
						bean.setOsClaimsLossUpdateOC(editPremium.get("OSCLAIM_LOSSUPDATE_OC")==null?"":editPremium.get("OSCLAIM_LOSSUPDATE_OC").toString());
						bean.setOverrider(editPremium.get("OVERRIDER_AMT_OC")==null?"":editPremium.get("OVERRIDER_AMT_OC").toString());
						bean.setOverriderUSD(editPremium.get("OVERRIDER_AMT_DC")==null?"":editPremium.get("OVERRIDER_AMT_DC").toString());	
						bean.setAmendmentDate(editPremium.get("AMENDMENT_DATE")==null?"":editPremium.get("AMENDMENT_DATE").toString());	
                        bean.setWithHoldingTaxOC(DropDownControllor.formatter(editPremium.get("WITH_HOLDING_TAX_OC")==null?"":editPremium.get("WITH_HOLDING_TAX_OC").toString()));
                        bean.setWithHoldingTaxDC(DropDownControllor.formatter(editPremium.get("WITH_HOLDING_TAX_DC")==null?"":editPremium.get("WITH_HOLDING_TAX_DC").toString()));
                        bean.setRi_cession(editPremium.get("RI_CESSION")==null?"":editPremium.get("RI_CESSION").toString());
                        bean.setPredepartment(editPremium.get("PREMIUM_CLASS")==null?"":editPremium.get("PREMIUM_CLASS").toString());
                        bean.setDepartmentId(editPremium.get("SUB_CLASS")==null?"":editPremium.get("SUB_CLASS").toString());
                        bean.setTaxDedectSource(DropDownControllor.formatter(editPremium.get("TDS_OC")==null?"":editPremium.get("TDS_OC").toString()));
                        bean.setTaxDedectSourceDc(DropDownControllor.formatter(editPremium.get("TDS_DC")==null?"":editPremium.get("TDS_DC").toString()));
                        bean.setServiceTax(DropDownControllor.formatter(editPremium.get("ST_OC")==null?"":editPremium.get("ST_OC").toString()));
                        bean.setServiceTaxDc(DropDownControllor.formatter(editPremium.get("ST_DC")==null?"":editPremium.get("ST_DC").toString()));
                        bean.setBonus(DropDownControllor.formatter(editPremium.get("BONUS_OC")==null?"":editPremium.get("BONUS_OC").toString()));
                        bean.setBonusDc(DropDownControllor.formatter(editPremium.get("BONUS_DC")==null?"":editPremium.get("BONUS_DC").toString()));
                        bean.setGnpiDate((editPremium.get("GNPI_ENDT_NO")==null?"":editPremium.get("GNPI_ENDT_NO").toString()));
                        bean.setStatementDate(editPremium.get("STATEMENT_DATE")==null?"":editPremium.get("STATEMENT_DATE").toString());
                        bean.setChooseTransaction(editPremium.get("REVERSEL_STATUS")==null?"":editPremium.get("REVERSEL_STATUS").toString() );
        	            bean.setTransDropDownVal(editPremium.get("REVERSE_TRANSACTION_NO")==null?"":editPremium.get("REVERSE_TRANSACTION_NO").toString() );
					}
					 }
				}
				else{
					LOGGER.info("Query=>"+query.toString());
					transList=this.mytemplate.queryForList(query, args);
					 if(transList.size()>0)
					 {
						 for(int i=0;i<transList.size();i++){
							 Map<String,Object>	 xolView=transList.get(i);
					bean.setContNo(xolView.get("CONTRACT_NO")==null?"":xolView.get("CONTRACT_NO").toString());
					bean.setTransactionNo(xolView.get("TRANSACTION_NO")==null?"":xolView.get("TRANSACTION_NO").toString());
					bean.setTransaction(xolView.get("TRANS_DATE")==null?"":xolView.get("TRANS_DATE").toString()); 
					bean.setBrokerage(DropDownControllor.formatter(xolView.get("BROKERAGE_AMT_OC")==null?"0":xolView.get("BROKERAGE_AMT_OC").toString()));
					bean.setTax(DropDownControllor.formatter(xolView.get("TAX_AMT_OC")==null?"0":xolView.get("TAX_AMT_OC").toString()));
					bean.setMd_premium(DropDownControllor.formatter(xolView.get("M_DPREMIUM_OC")==null?"0":xolView.get("M_DPREMIUM_OC").toString()));
					bean.setAdjustment_premium(DropDownControllor.formatter(xolView.get("ADJUSTMENT_PREMIUM_OC")==null?"0":xolView.get("ADJUSTMENT_PREMIUM_OC").toString()));							
					bean.setRecuirement_premium(DropDownControllor.formatter(xolView.get("REC_PREMIUM_OC")==null?"0":xolView.get("REC_PREMIUM_OC").toString()));
					bean.setNetDue(DropDownControllor.formatter(xolView.get("NETDUE_OC")==null?"0":xolView.get("NETDUE_OC").toString()));
					bean.setLayerno(xolView.get("LAYER_NO")==null?"":xolView.get("LAYER_NO").toString());
					bean.setEnteringMode(xolView.get("ENTERING_MODE")==null?"":xolView.get("ENTERING_MODE").toString());
					bean.setAccount_Period(xolView.get("INSTALMENT_NUMBER")+(xolView.get("ACCOUNT_PERIOD_QTR")==null?"":("_"+xolView.get("ACCOUNT_PERIOD_QTR"))));
					bean.setCurrency(xolView.get("CURRENCY_ID")==null?"":xolView.get("CURRENCY_ID").toString());
					bean.setOtherCost(DropDownControllor.formatter(xolView.get("OTHER_COST_OC")==null?"0":xolView.get("OTHER_COST_OC").toString()));
					bean.setBrokerage_usd(DropDownControllor.formatter(xolView.get("BROKERAGE_AMT_DC")==null?"0":xolView.get("BROKERAGE_AMT_DC").toString()));
					bean.setTax_usd(DropDownControllor.formatter(xolView.get("TAX_AMT_DC")==null?"0":xolView.get("TAX_AMT_DC").toString()));
					bean.setMd_premium_usd(DropDownControllor.formatter(xolView.get("M_DPREMIUM_DC")==null?"0":xolView.get("M_DPREMIUM_DC").toString()));
					bean.setAdjustment_premium_usd(DropDownControllor.formatter(xolView.get("ADJUSTMENT_PREMIUM_DC")==null?"0":xolView.get("ADJUSTMENT_PREMIUM_DC").toString()));
					bean.setRecuirement_premium_usd(DropDownControllor.formatter(xolView.get("REC_PREMIUM_DC")==null?"0":xolView.get("REC_PREMIUM_DC").toString()));
					bean.setNet_due_usd(DropDownControllor.formatter(xolView.get("NETDUE_DC")==null?"0":xolView.get("NETDUE_DC").toString()));
					bean.setOtherCostUSD(DropDownControllor.formatter(xolView.get("OTHER_COST_DC")==null?"0":xolView.get("OTHER_COST_DC").toString()));
					bean.setInception_Date(xolView.get("ENTRY_DATE")==null?"":xolView.get("ENTRY_DATE").toString());
					bean.setCedentRef(xolView.get("CEDANT_REFERENCE")==null?"":xolView.get("CEDANT_REFERENCE").toString());
					bean.setRemarks(xolView.get("REMARKS")==null?"":xolView.get("REMARKS").toString());
					bean.setTotalCredit(DropDownControllor.formatter(xolView.get("TOTAL_CR_OC")==null?"":xolView.get("TOTAL_CR_OC").toString()));
					bean.setTotalCreditDC(DropDownControllor.formatter(xolView.get("TOTAL_CR_DC")==null?"":xolView.get("TOTAL_CR_DC").toString()));
					bean.setTotalDebit(DropDownControllor.formatter(xolView.get("TOTAL_DR_OC")==null?"":xolView.get("TOTAL_DR_OC").toString()));
					bean.setTotalDebitDC(DropDownControllor.formatter(xolView.get("TOTAL_DR_DC")==null?"":xolView.get("TOTAL_DR_DC").toString()));
					//bean.setSettlement_status(xolView.get("SETTLEMENT_STATUS"));
					bean.setAmendmentDate(xolView.get("AMENDMENT_DATE")==null?"":xolView.get("AMENDMENT_DATE").toString());
                    bean.setWithHoldingTaxOC(DropDownControllor.formatter(xolView.get("WITH_HOLDING_TAX_OC")==null?"":xolView.get("WITH_HOLDING_TAX_OC").toString()));
                    bean.setWithHoldingTaxDC(DropDownControllor.formatter(xolView.get("WITH_HOLDING_TAX_DC")==null?"":xolView.get("WITH_HOLDING_TAX_DC").toString()));
                    bean.setDueDate(xolView.get("DUE_DATE")==null?"":xolView.get("DUE_DATE").toString());
                    bean.setCreditsign(xolView.get("NETDUE_OC")==null?"":xolView.get("NETDUE_OC").toString());
                    bean.setRi_cession(xolView.get("RI_CESSION")==null?"":xolView.get("RI_CESSION").toString());
                    bean.setPredepartment(xolView.get("PREMIUM_CLASS")==null?"":xolView.get("PREMIUM_CLASS").toString());
                    bean.setDepartmentId(xolView.get("SUB_CLASS")==null?"":xolView.get("SUB_CLASS").toString());
                    bean.setTaxDedectSource(DropDownControllor.formatter(xolView.get("TDS_OC")==null?"0":xolView.get("TDS_OC").toString()));
                    bean.setTaxDedectSourceDc(DropDownControllor.formatter(xolView.get("TDS_DC")==null?"0":xolView.get("TDS_DC").toString()));
                    bean.setServiceTax(DropDownControllor.formatter(xolView.get("ST_OC")==null?"0":xolView.get("ST_OC").toString()));
                    bean.setServiceTaxDc(DropDownControllor.formatter(xolView.get("ST_DC")==null?"0":xolView.get("ST_DC").toString()));
                    bean.setBonus(DropDownControllor.formatter(xolView.get("BONUS_OC")==null?"0":xolView.get("BONUS_OC").toString()));
                    bean.setBonusDc(DropDownControllor.formatter(xolView.get("BONUS_DC")==null?"0":xolView.get("BONUS_DC").toString()));
    				bean.setExchRate(DropDownControllor.exchRateFormat(xolView.get("EXCHANGE_RATE")==null?"0":xolView.get("EXCHANGE_RATE").toString()));
                    bean.setGnpiDate(xolView.get("GNPI_ENDT_NO")==null?"":xolView.get("GNPI_ENDT_NO").toString());
                    bean.setStatementDate(xolView.get("STATEMENT_DATE")==null?"":xolView.get("STATEMENT_DATE").toString());
                    bean.setChooseTransaction(xolView.get("REVERSEL_STATUS")==null?"":xolView.get("REVERSEL_STATUS").toString() );
    	            bean.setTransDropDownVal(xolView.get("REVERSE_TRANSACTION_NO")==null?"":xolView.get("REVERSE_TRANSACTION_NO").toString() );
				}		
				 }
				}
				
				if(transList!=null && transList.size()>0)
					saveFlag = true;
				}
				LOGGER.info("PremiumDAOImpl premiumEdit || Exit");	
			return saveFlag;
			}

	private String getMultipleVal(String premiumQuotaShare) {
		String res="";double val =0;
		try{
			if(premiumQuotaShare==""){
				premiumQuotaShare="0";
			}
			//if(Double.parseDouble(premiumQuotaShare.replaceAll(",", ""))>0){
				 val = Double.parseDouble(premiumQuotaShare.replaceAll(",", ""))*-1;
			//	}
			 if(val==-0){
				 val = 0; 
			 }
			res = DropDownControllor.formatter(Double.toString(val));
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	@SuppressWarnings("unchecked")
	public boolean getPreList(final FaculPremiumBean bean) {
		LOGGER.info("PremiumDAOImpl getPreList || Enter");
		String query="";
		boolean saveFlag=false;
		String[] args = null;
		args = new String[2];
		args[0] = bean.getContNo();
		args[1] = bean.getLayerno();
		LOGGER.info("Layer No.in preLIst===>" + bean.getLayerno());
		query=getQuery(DBConstants.PREMIUM_SELECT_XOLPRELIST);
		LOGGER.info("Select Query=>"+query);
		LOGGER.info("Args=>"+StringUtils.join(args,","));
		List list=this.mytemplate.query(query, args,new RowMapper() {
			public Object mapRow(ResultSet preList, int rowNum) throws SQLException {
				bean.setContNo(preList.getString("CONTRACT_NO"));
				bean.setDepartment_Name(preList.getString("TMAS_DEPARTMENT_NAME"));
				bean.setUwYear(preList.getString("UW_YEAR"));
				bean.setCeding_Company_Name(preList.getString("COMPANY_NAME"));
				bean.setLayerno(preList.getString("LAYER_NO"));
				bean.setBrokername(preList.getString("FIRST_NAME"));
				return bean;
			}
		});
		if(list!=null && list.size()>0)
			saveFlag = true;
		if(StringUtils.isNotBlank(bean.getContNo())){
			try{
			bean.setCeaseStatus((String)this.mytemplate.queryForObject("select CEASE_STATUS from position_master pm where CONTRACT_NO=? AND LAYER_NO =? and  pm.amend_id=(Select Max(Amend_id) From Position_master p where p.CONTRACT_NO=pm.CONTRACT_NO  AND p.LAYER_NO = pm.LAYER_NO) AND CONTRACT_STATUS='A'", new Object[]{bean.getContNo(),bean.getLayerno()}, String.class));
			}catch(Exception e){
				e.printStackTrace();
			}
			}
		LOGGER.info("PremiumDAOImpl getPreList || Exit");
		return saveFlag;
	}

	public boolean premiumUpdateMethod(final FaculPremiumBean beanObj){
		LOGGER.info("PremiumDAOImpl premiumUpdateMethod || Enter");
		String query="";
        LOGGER.info(beanObj.toString());
		boolean saveFlag = false;
		try {
			String[] args = updateAruguments(beanObj);
			String netDueOc="0";
			if("Temp".equalsIgnoreCase(beanObj.getTableType()) && "3".equalsIgnoreCase(beanObj.getProductId())){
				query=getQuery("XOL_PREMIUM_UPDATE_UPDATE_TEMP");
			}else{
				if("3".equalsIgnoreCase(beanObj.getProductId())){
						query=getQuery(DBConstants.PREMIUM_UPDATE_XOLUPDATEPRE);
					}else{
						query=getQuery(DBConstants.PREMIUM_UPDATE_RETROXOLUPDATEPRE);
					}
			}
			
			netDueOc=args[12];	
			this.mytemplate.update(query,args);
			if("Submit".equalsIgnoreCase(beanObj.getButtonStatus()) && "Temp".equalsIgnoreCase(beanObj.getTableType()) && "3".equalsIgnoreCase(beanObj.getProductId())){
				if("3".equalsIgnoreCase(beanObj.getProductId())){
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
				}
				LOGGER.info("SP Name=>"+getQuery(DBConstants.PREMIUM_DETAIL_ARCHIVE));
				LOGGER.info("Args[]==>"+beanObj.getContNo()+","+(StringUtils.isBlank(beanObj.getLayerno())?"0":beanObj.getLayerno())+","+beanObj.getTransactionNo()+","+beanObj.getCurrencyId()+","+beanObj.getExchRate()+","+netDueOc+","+beanObj.getDepartmentId()+","+beanObj.getProductId());
				int spresult=this.mytemplate.update(getQuery(DBConstants.PREMIUM_DETAIL_ARCHIVE),new String[]{beanObj.getContNo(),(StringUtils.isBlank(beanObj.getLayerno())?"0":beanObj.getLayerno()),beanObj.getTransactionNo(),beanObj.getCurrencyId(),beanObj.getExchRate(),netDueOc,beanObj.getDepartmentId(),beanObj.getProductId()});
				LOGGER.info("SP Result==>"+spresult);
				LOGGER.info("Update Query=>"+query);
				int update=this.mytemplate.update(query,args);
				LOGGER.info("Update Result=>"+update);
			}
			//insertBonus(beanObj);
		} catch (Exception exe) {
			saveFlag=false;
			LOGGER.debug("Exception "+exe);		
		}
		LOGGER.info("PremiumDAOImpl premiumUpdateMethod || Exit");
		return saveFlag;
	}

	public List<Map<String,Object>> mdInstallmentDates(final String contNo,final String layerNo,String sourceId,String productId) {
		LOGGER.info("PremiumDAOImpl mdInstallmentDates || Enter");
		String query="";
		 List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	    try{
	    	 Object[] args=null;
	    	 if("3".equalsIgnoreCase(productId)){
	    		args=new Object[6];
	    	 }else{
	    		args=new Object[4];
	    	 }
			    args[0]=contNo;
			    args[1]=layerNo;
			    args[2]=contNo;
			    args[3]=layerNo;
		    if("3".equalsIgnoreCase(productId)){
		    	args[4]=contNo;
		    	args[5]=layerNo;
		    }
		    //args[5]=layerNo;
		    if("3".equalsIgnoreCase(productId)){
		    	query=getQuery("PREMIUM_MND_INS_LIST");
		    	
		    }else{
		    	query=getQuery(DBConstants.PREMIUM_SELECT_MDINSTALMENTLIST);
		    }
		    
		    LOGGER.info("Select mdInstallmentDates Query=>"+query);
		    list=this.mytemplate.queryForList(query, args);		 
		    Map<String,Object> tempMap1 = new HashMap<String, Object>();
		    Map<String,Object> tempMap2 = new HashMap<String, Object>();
		    Map<String,Object> tempMap3 = new HashMap<String, Object>();
		    Map<String,Object> tempMap4 = new HashMap<String, Object>();
		    if(list.size()==0)
		    {
		    String count="";
		    if("RI02".equalsIgnoreCase(sourceId)){
		    query =getQuery("GET_GNPI_COUNT_PRE");
		     args=new Object[3];
		     args[0]=contNo;
		     args[1]=layerNo;
		     args[2]="GNPI";
		     count = this.mytemplate.queryForObject(query,args,String.class);
		    }
		    tempMap1.put("KEY1","RP");	    
		    tempMap1.put("VALUE","Reinstatement Premium");
		    list.add(tempMap1);	
		    if("RI02".equalsIgnoreCase(sourceId) && !"0".equalsIgnoreCase(count)){
			    tempMap2.put("KEY1","AP");	    
			    tempMap2.put("VALUE","Adjustment Premium");	
			    list.add(tempMap2);	
		    }else if("RI01".equalsIgnoreCase(sourceId)){
		    	tempMap2.put("KEY1","AP");	    
			    tempMap2.put("VALUE","Adjustment Premium");	
			    list.add(tempMap2);	
		    }
		    tempMap3.put("KEY1","RTP");	    
		    tempMap3.put("VALUE","Return Premium");
		    list.add(tempMap3);
		    tempMap4.put("KEY1","RVP");	    
		    tempMap4.put("VALUE","Reversal Premium");
		    list.add(tempMap4);
		    }
		    else
		    {
		    tempMap1.put("KEY1","RP");	    
			tempMap1.put("VALUE","Reinstatement Premium");	
			 list.add(tempMap1);
			tempMap3.put("KEY1","RTP");	    
			tempMap3.put("VALUE","Return Premium");
			 list.add(tempMap3);
			tempMap4.put("KEY1","RVP");	    
			tempMap4.put("VALUE","Reversal Premium");
			 list.add(tempMap4);
		    }		   		    
			}catch (Exception exe) {
				LOGGER.debug("Exception "+exe);
				exe.printStackTrace();
		}
	    LOGGER.info("PremiumDAOImpl mdInstallmentDates || Exit");
		   return list;
	}

	private String[] insertArguments(final FaculPremiumBean beanObj)
	{
		LOGGER.info("PremiumDAOImpl insertArguments || Enter");
		String[] args=null;	
		if("3".equalsIgnoreCase(beanObj.getProductId())){
			args=new String[58];
		}else{
			args=new String[56];
		}
		args[0]=beanObj.getContNo();
		//args[1]=maxTransationNo(beanObj.getProductId(),beanObj.getBranchCode());
		//if("06".equalsIgnoreCase(beanObj.getBranchCode())){
			//args[1]=new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction());
		/*}else
		args[1]=new DropDownControllor().getPolicyNo("3","0",beanObj.getBranchCode());*/
		if("3".equalsIgnoreCase(beanObj.getProductId())){
			args[1] = getRequestNo(beanObj);
		}else{
			args[1] = new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction());
			beanObj.setTransactionNo(args[1]);
		}
		args[2]=beanObj.getTransaction();
		args[3]=StringUtils.isBlank(beanObj.getInstalmentdate())?"":beanObj.getInstalmentdate();
		args[4]=StringUtils.isBlank(beanObj.getAccount_Period_year())?"":beanObj.getAccount_Period_year();
		if("3".equalsIgnoreCase(beanObj.getProductId())){
		args[5]=beanObj.getCurrencyId();
		}
		else{
			args[5]=beanObj.getCurrency();
		}
		args[6]=beanObj.getExchRate();
		args[7]=beanObj.getBrokerage_view();
		args[8] = getModeOfTransaction(beanObj.getBrokerage().replaceAll(",", ""),beanObj);
		args[24]=DropDownControllor.GetDesginationCountry(args[8],beanObj.getExchRate());
		args[9]=beanObj.getTax_view();
		args[10]=getModeOfTransaction(beanObj.getTax().replaceAll(",", ""),beanObj);
		args[25]=DropDownControllor.GetDesginationCountry(args[10],beanObj.getExchRate());
		args[11]=StringUtils.isEmpty(beanObj.getInception_Date()) ?"" :beanObj.getInception_Date();
		args[12]=getModeOfTransaction(beanObj.getCommission().replaceAll(",", ""),beanObj);
		args[13]=getModeOfTransaction(beanObj.getMd_premium().replaceAll(",", ""),beanObj);
		args[26]=DropDownControllor.GetDesginationCountry(args[13],beanObj.getExchRate());
		args[14]=getModeOfTransaction(beanObj.getAdjustment_premium().replaceAll(",", ""),beanObj);
		args[27]=DropDownControllor.GetDesginationCountry(args[14],beanObj.getExchRate());
		args[15]=getModeOfTransaction(beanObj.getRecuirement_premium().replaceAll(",", ""),beanObj);
		args[28]=DropDownControllor.GetDesginationCountry(args[15],beanObj.getExchRate());
		//Added by sathish for java script failure cases-Start
		LOGGER.info("Before");
		LOGGER.info("Instalmentdate==>"+beanObj.getInstalmentdate());
		LOGGER.info("AccountPeriod==>"+beanObj.getAccount_Period_year());
		LOGGER.info("MDPremium==>"+beanObj.getMd_premium());
		LOGGER.info("APremium==>"+beanObj.getAdjustment_premium());
		LOGGER.info("RPremium==>"+beanObj.getRecuirement_premium());
		LOGGER.info("Commission==>"+beanObj.getCommissionQuotaShare());
		LOGGER.info("Brokerate==>"+beanObj.getBrokerage());
		LOGGER.info("Tax===>"+beanObj.getTax());
		LOGGER.info("getInstlmentNo===>"+beanObj.getInstlmentNo()); 
		if("RP".equalsIgnoreCase(beanObj.getInstlmentNo()))
		{
			args[13]=getModeOfTransaction(0+" ",beanObj);
			args[26]=DropDownControllor.GetDesginationCountry(args[13],beanObj.getExchRate());
			args[14]=getModeOfTransaction(0+" ",beanObj);
			args[27]=DropDownControllor.GetDesginationCountry(args[14],beanObj.getExchRate());
			if(!StringUtils.isEmpty(beanObj.getRecuirement_premium()))
			{
				LOGGER.info("After");
				double premiums=Double.parseDouble(beanObj.getRecuirement_premium());
				if(StringUtils.isEmpty(beanObj.getBrokerage()))
				{
					final double brokerage=premiums*(Double.parseDouble(beanObj.getBrokerage_view())/100);
					args[8]=getModeOfTransaction(brokerage+" ",beanObj);
					args[24]=DropDownControllor.GetDesginationCountry(args[8],beanObj.getExchRate());
					LOGGER.info("Brokerate===>"+brokerage);
					
				}
				if(StringUtils.isEmpty(beanObj.getTax()))
				{
					final double tax=premiums*(Double.parseDouble(beanObj.getTax_view())/100);
					args[10]=getModeOfTransaction(tax+" ",beanObj);
					args[25]=DropDownControllor.GetDesginationCountry(args[10],beanObj.getExchRate());
					LOGGER.info("Tax===>"+tax);
				}
			}
			
		}else if("AP".equalsIgnoreCase(beanObj.getInstlmentNo()))
		{
			args[13]=getModeOfTransaction(0+" ",beanObj);
			args[26]=DropDownControllor.GetDesginationCountry(args[13],beanObj.getExchRate());
			args[15]=getModeOfTransaction(0+" ",beanObj);
			args[28]=DropDownControllor.GetDesginationCountry(args[15],beanObj.getExchRate());
			if(!StringUtils.isEmpty(beanObj.getAdjustment_premium()))
			{
			LOGGER.info("After");
			double premiums=Double.parseDouble(beanObj.getAdjustment_premium().replaceAll(",", ""));
			if(StringUtils.isEmpty(beanObj.getBrokerage()))
			{
				final double brokerage=premiums*(Double.parseDouble(beanObj.getBrokerage_view())/100);
				args[8]=getModeOfTransaction(brokerage+" ",beanObj);
				args[24]=DropDownControllor.GetDesginationCountry(args[8],beanObj.getExchRate());
				LOGGER.info("Brokerate===>"+brokerage);
				
			}
			if(StringUtils.isEmpty(beanObj.getTax()))
			{
				final double tax=premiums*(Double.parseDouble(beanObj.getTax_view())/100);
				args[10]=getModeOfTransaction(tax+" ",beanObj);
				args[25]=DropDownControllor.GetDesginationCountry(args[10],beanObj.getExchRate());
				LOGGER.info("Tax===>"+tax);
			}
			
		}
			
		}else
		{
			args[14]=getModeOfTransaction(0+" ",beanObj);
			args[27]=DropDownControllor.GetDesginationCountry(args[14],beanObj.getExchRate());
			args[15]=getModeOfTransaction(0+" ",beanObj);
			args[28]=DropDownControllor.GetDesginationCountry(args[15],beanObj.getExchRate());
			if(!StringUtils.isEmpty(beanObj.getMd_premium()))
			{
			LOGGER.info("After");
			final double premium=Double.parseDouble(beanObj.getMd_premium());
			if(StringUtils.isEmpty(beanObj.getBrokerage()))
			{
				final double brokerage=premium*(Double.parseDouble(beanObj.getBrokerage_view())/100);
				args[8]=getModeOfTransaction(brokerage+" ",beanObj);
				args[24]=DropDownControllor.GetDesginationCountry(args[8],beanObj.getExchRate());
				LOGGER.info("Brokerate===>"+brokerage);
				
			}
			if(StringUtils.isEmpty(beanObj.getTax()))
			{
				final double tax=premium*(Double.parseDouble(beanObj.getTax_view())/100);
				args[10]=getModeOfTransaction(tax+" ",beanObj);
				args[25]=DropDownControllor.GetDesginationCountry(args[10],beanObj.getExchRate());
				LOGGER.info("Tax===>"+tax);
			}
			
		}
		}
		//Added by sathish for java script failure cases-End
		 
		args[16]="Y";
		args[18]=beanObj.getLayerno();
		//args[19]=beanObj.getEnteringMode();
		args[19]="2";
		args[20]=beanObj.getReceipt_no();
		args[21]=beanObj.getInstlmentNo();
		args[22]=StringUtils.isBlank(beanObj.getSettlement_status())?"":beanObj.getSettlement_status();
		args[23]=getModeOfTransaction(beanObj.getOtherCost().replaceAll(",", ""),beanObj);
		args[30]=DropDownControllor.GetDesginationCountry(args[23],beanObj.getExchRate());
		args[31]=beanObj.getCedentRef();
		args[32]=beanObj.getRemarks();
		args[33]=getModeOfTransaction(beanObj.getTotalCredit().replaceAll(",", ""),beanObj);
		args[34]=DropDownControllor.GetDesginationCountry(args[33],beanObj.getExchRate());
		args[35]=getModeOfTransaction(beanObj.getTotalDebit().replaceAll(",", ""),beanObj);
		args[36]=DropDownControllor.GetDesginationCountry(args[35],beanObj.getExchRate());
		args[37]=getModeOfTransaction(beanObj.getWithHoldingTaxOC().replaceAll(",", ""),beanObj);		
		args[38]=DropDownControllor.GetDesginationCountry(args[37],beanObj.getExchRate());
		args[39]=beanObj.getRi_cession();
		args[40] = beanObj.getLoginId();
		args[41] = beanObj.getBranchCode();
		args[42]=beanObj.getDepartmentId();
		args[43] = getModeOfTransaction(beanObj.getTaxDedectSource()==null?"0":beanObj.getTaxDedectSource().replaceAll(",", ""),beanObj);
		args[44] = DropDownControllor.GetDesginationCountry(args[43], beanObj.getExchRate());
		args[45] = getModeOfTransaction(beanObj.getServiceTax()==null?"0":beanObj.getServiceTax().replaceAll(",", ""),beanObj);
		args[46] = DropDownControllor.GetDesginationCountry(args[45], beanObj.getExchRate());
		args[47] = getModeOfTransaction(beanObj.getBonus()==null?"0":beanObj.getBonus().replaceAll(",", ""),beanObj);
		args[48] = DropDownControllor.GetDesginationCountry(args[47], beanObj.getExchRate());
		args[17]=getNetDueXol(args,beanObj.getProductId());
		args[29]=DropDownControllor.GetDesginationCountry(args[17],beanObj.getExchRate());
		beanObj.setTransactionNo(args[1]);
		args[49] = StringUtils.isEmpty(beanObj.getGnpiDate()) ?"" :beanObj.getGnpiDate();
		args[50] ="D";
		args[51]=beanObj.getPredepartment();
		args[52]=beanObj.getStatementDate();
		args[53]=beanObj.getProposal_No();
		args[54]=beanObj.getProductId();
		args[55]=beanObj.getChooseTransaction()==null?"":beanObj.getChooseTransaction();
		if("3".equalsIgnoreCase(beanObj.getProductId())){
			if("submit".equalsIgnoreCase(beanObj.getButtonStatus())){
				args[56] = "A";
			}else{
				args[56] = "P";
			}
			args[57] = beanObj.getMode();
		}
		for(int i=0;i<args.length;i++)
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
		LOGGER.info("A==>"+Abt);
		LOGGER.info("B==>"+Bbt);
	    final double cbt=Abt-Bbt;
	    LOGGER.info("Net Due==>"+cbt);
	    LOGGER.info("PremiumDAOImpl getNetDueAmount || Exit");
		return String.valueOf(cbt);
	}
	private static String getNetDueXol(final String[] args, String id) {
		LOGGER.info("PremiumDAOImpl getNetDueXol || Enter");
		final double Ant=StringUtils.isEmpty(args[13]) ? 0 :Double.parseDouble(args[13]) ;
		final double Bnt=StringUtils.isEmpty(args[14]) ? 0 :Double.parseDouble(args[14]) ;
		final double Cnt=StringUtils.isEmpty(args[15]) ? 0 :Double.parseDouble(args[15]) ;
		final double Dnt=StringUtils.isEmpty(args[8]) ? 0 :Double.parseDouble(args[8]) ;
		final double Ent=StringUtils.isEmpty(args[10]) ? 0 :Double.parseDouble(args[10]) ;
		final double Fnt=StringUtils.isEmpty(args[12]) ? 0 :Double.parseDouble(args[12]) ;
		final double Gnt=StringUtils.isEmpty(args[23]) ? 0 :Double.parseDouble(args[23]) ;
		final double Hnt=StringUtils.isEmpty(args[37]) ? 0 :Double.parseDouble(args[37]) ;
		final double Int=StringUtils.isEmpty(args[43]) ? 0 :Double.parseDouble(args[43]) ;
		final double Jnt=StringUtils.isEmpty(args[45]) ? 0 :Double.parseDouble(args[45]) ;
		 double Knt =0.00;
		//if("3".equalsIgnoreCase(id)){
		 Knt=StringUtils.isEmpty(args[47]) ? 0 :Double.parseDouble(args[47]) ;
		//}
		LOGGER.info("A==>"+Ant);
		LOGGER.info("B==>"+Bnt);
	    final double cnt=(Ant+Bnt+Cnt+Int+Jnt)-(Dnt+Ent+Fnt+Gnt+Hnt+Knt);
	    LOGGER.info("Net Due==>"+cnt);
	    LOGGER.info("PremiumDAOImpl getNetDueXol || Exit");
		return String.valueOf(cnt);
	}
	public static String[] updateAruguments(final FaculPremiumBean beanObj) {
		LOGGER.info("PremiumDAOImpl updateAruguments || Enter");
		String[] args=null;				
		args=new String[45];
		
		LOGGER.info("getInstlmentNo===>"+beanObj.getInstlmentNo());
		//args[0]=beanObj.getTransaction()+"-"+beanObj.getTransaction_year();
		args[0]=beanObj.getTransaction();
		if("3".equalsIgnoreCase(beanObj.getProductId())){
			args[1]=beanObj.getCurrencyId();
			}
			else{
				args[1]=beanObj.getCurrency();
			}
		args[2]=beanObj.getExchRate();
		args[3]=beanObj.getBrokerage_view();
		args[4]=getModeOfTransaction(beanObj.getBrokerage(),beanObj);
		args[16]=DropDownControllor.GetDesginationCountry(args[4], beanObj.getExchRate());
		args[5]=beanObj.getTax_view();
		args[6]=getModeOfTransaction(beanObj.getTax(),beanObj);
		args[17]=DropDownControllor.GetDesginationCountry(args[6], beanObj.getExchRate());
		args[7]=StringUtils.isEmpty(beanObj.getInception_Date()) ?"" :beanObj.getInception_Date();
		args[8]=getModeOfTransaction(beanObj.getCommission(),beanObj);
		args[9]=getModeOfTransaction(beanObj.getMd_premium(),beanObj);
		args[18]=DropDownControllor.GetDesginationCountry(args[9], beanObj.getExchRate());
		args[10]=getModeOfTransaction(beanObj.getAdjustment_premium(),beanObj);
		args[19]=DropDownControllor.GetDesginationCountry(args[10], beanObj.getExchRate());
		args[11]=getModeOfTransaction(beanObj.getRecuirement_premium(),beanObj);
		args[20]=DropDownControllor.GetDesginationCountry(args[11], beanObj.getExchRate());
		//args[32]=getModeOfTransaction(beanObj.getWithHoldingTaxOC(),beanObj);
		//args[33]=DropDownControllor.GetDesginationCountry(args[32], beanObj.getExchRate());
		//args[13]=beanObj.getEnteringMode();
		
		args[13]="2";
		args[14]=beanObj.getReceipt_no();
		//Added by sathish for java script failure cases-Start
		LOGGER.info("Before");
		LOGGER.info("Instalmentdate==>"+beanObj.getInstalmentdate());
		LOGGER.info("AccountPeriod==>"+beanObj.getAccount_Period_year());
		LOGGER.info("MDPremium==>"+beanObj.getMd_premium());
		LOGGER.info("APremium==>"+beanObj.getAdjustment_premium());
		LOGGER.info("RPremium==>"+beanObj.getRecuirement_premium());
		LOGGER.info("Commission==>"+beanObj.getCommissionQuotaShare());
		LOGGER.info("Brokerate==>"+beanObj.getBrokerage());
		LOGGER.info("Tax===>"+beanObj.getTax());
		LOGGER.info("getInstlmentNo===>"+beanObj.getInstlmentNo());
		if(beanObj.getInstlmentNo().equalsIgnoreCase("RP"))
		{
			args[9]=getModeOfTransaction(0+" ",beanObj);
			args[18]=DropDownControllor.GetDesginationCountry(args[9], beanObj.getExchRate());
			args[10]=getModeOfTransaction(0+" ",beanObj);
			args[19]=DropDownControllor.GetDesginationCountry(args[10], beanObj.getExchRate());
			if(!StringUtils.isEmpty(beanObj.getRecuirement_premium()))
			{
				LOGGER.info("After");
				double premiums=Double.parseDouble(beanObj.getRecuirement_premium());
				if(StringUtils.isEmpty(beanObj.getBrokerage()))
				{
					double brokerage=premiums*(Double.parseDouble(beanObj.getBrokerage_view())/100);
					args[4]=getModeOfTransaction(brokerage+" ",beanObj);
					args[16]=DropDownControllor.GetDesginationCountry(args[4], beanObj.getExchRate());
					LOGGER.info("Brokerate===>"+brokerage);
					
				}
				if(StringUtils.isEmpty(beanObj.getTax()))
				{
					double tax=premiums*(Double.parseDouble(beanObj.getTax_view())/100);
					args[6]=getModeOfTransaction(tax+" ",beanObj);
					args[17]=DropDownControllor.GetDesginationCountry(args[6], beanObj.getExchRate());
					LOGGER.info("Tax===>"+tax);
				}
			}
			
		}else if(beanObj.getInstlmentNo().equalsIgnoreCase("AP"))
		{
			args[9]=getModeOfTransaction(0+" ",beanObj);
			args[18]=DropDownControllor.GetDesginationCountry(args[9], beanObj.getExchRate());
			args[11]=getModeOfTransaction(0+" ",beanObj);
			args[20]=DropDownControllor.GetDesginationCountry(args[11], beanObj.getExchRate());
			if(!StringUtils.isEmpty(beanObj.getAdjustment_premium()))
			{
				LOGGER.info("After");
				double premiums=Double.parseDouble(beanObj.getAdjustment_premium());
				if(StringUtils.isEmpty(beanObj.getBrokerage()))
				{
					double brokerage=premiums*(Double.parseDouble(beanObj.getBrokerage_view())/100);
					args[4]=getModeOfTransaction(brokerage+" ",beanObj);
					args[16]=DropDownControllor.GetDesginationCountry(args[4], beanObj.getExchRate());
					LOGGER.info("Brokerate===>"+brokerage);
					
				}
				if(StringUtils.isEmpty(beanObj.getTax()))
				{
					double tax=premiums*(Double.parseDouble(beanObj.getTax_view())/100);
					args[6]=getModeOfTransaction(tax+" ",beanObj);
					args[17]=DropDownControllor.GetDesginationCountry(args[6], beanObj.getExchRate());
					LOGGER.info("Tax===>"+tax);
				}
			}
		}else
		{
			args[10]=getModeOfTransaction(0+" ",beanObj);
			args[19]=DropDownControllor.GetDesginationCountry(args[10], beanObj.getExchRate());
			args[11]=getModeOfTransaction(0+" ",beanObj);
			args[20]=DropDownControllor.GetDesginationCountry(args[11], beanObj.getExchRate());
			if(!StringUtils.isEmpty(beanObj.getMd_premium()))
			{
			LOGGER.info("After");
			double premium=Double.parseDouble(beanObj.getMd_premium());
			if(StringUtils.isEmpty(beanObj.getBrokerage()))
			{
				double brokerage=premium*(Double.parseDouble(beanObj.getBrokerage_view())/100);
				args[4]=getModeOfTransaction(brokerage+" ",beanObj);
				args[16]=DropDownControllor.GetDesginationCountry(args[4], beanObj.getExchRate());
				LOGGER.info("Brokerate===>"+brokerage);
				
			}
			if(StringUtils.isEmpty(beanObj.getTax()))
			{
				double tax=premium*(Double.parseDouble(beanObj.getTax_view())/100);
				args[6]=getModeOfTransaction(tax+" ",beanObj);
				args[17]=DropDownControllor.GetDesginationCountry(args[6], beanObj.getExchRate());
				LOGGER.info("Tax===>"+tax);
			}
		}
		}
		//Added by sathish for java script failure cases-End
		args[15]=getModeOfTransaction(beanObj.getOtherCost(),beanObj);
		args[30]=getModeOfTransaction(beanObj.getWithHoldingTaxOC(), beanObj);
		args[22]=DropDownControllor.GetDesginationCountry(args[15], beanObj.getExchRate());
		args[23]=beanObj.getCedentRef();
		args[24]=beanObj.getRemarks();
		args[25]=getModeOfTransaction(beanObj.getTotalCredit(),beanObj);
		args[26]=DropDownControllor.GetDesginationCountry(args[25],beanObj.getExchRate());
		args[27]=getModeOfTransaction(beanObj.getTotalDebit(),beanObj);
		args[28]=DropDownControllor.GetDesginationCountry(args[27],beanObj.getExchRate());
		args[29]=beanObj.getAmendmentDate()==null?"":beanObj.getAmendmentDate();
        args[31]=DropDownControllor.GetDesginationCountry(args[30], beanObj.getExchRate());
        args[32]=beanObj.getRi_cession();
        args[33]=beanObj.getDepartmentId();
        args[34] = getModeOfTransaction(beanObj.getTaxDedectSource()==null?"0":beanObj.getTaxDedectSource(),beanObj);
        args[35] = DropDownControllor.GetDesginationCountry(args[34], beanObj.getExchRate());
        args[36] = getModeOfTransaction(beanObj.getServiceTax()==null?"0":beanObj.getServiceTax(),beanObj);
        args[37] = DropDownControllor.GetDesginationCountry(args[36], beanObj.getExchRate());
		args[38] = getModeOfTransaction(beanObj.getBonus(),beanObj);
		args[39] = DropDownControllor.GetDesginationCountry(args[38], beanObj.getExchRate());
		args[43]=beanObj.getContNo();
		if("Temp".equalsIgnoreCase(beanObj.getTableType()) &&"3".equalsIgnoreCase(beanObj.getProductId())){
			args[44]=beanObj.getRequestNo();
		}else{
			args[44]=beanObj.getTransactionNo();
		}
		
		args[12]=getNetDueXolUpdate(args,beanObj.getProductId());
		args[21]=DropDownControllor.GetDesginationCountry(args[12], beanObj.getExchRate());
		args[40]=StringUtils.isEmpty(beanObj.getGnpiDate()) ?"" :beanObj.getGnpiDate();
		args[41]=beanObj.getPredepartment();
		args[42]= beanObj.getStatementDate();
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
		LOGGER.info("A==>"+Aut);
		LOGGER.info("B==>"+But);
	    double cut=Aut-But;
	    LOGGER.info("Net Due==>"+cut);
	    LOGGER.info("PremiumDAOImpl updateNetDue || Exit");
		return String.valueOf(cut);
	}
	private static String getNetDueXolUpdate(final String[] args, String id) {
		LOGGER.info("PremiumDAOImpl getNetDueXolUpdate || Enter");
		double Ant=StringUtils.isEmpty(args[9]) ? 0 :Double.parseDouble(args[9]) ;
		double Bnt=StringUtils.isEmpty(args[10]) ? 0 :Double.parseDouble(args[10]) ;
		double Cnt=StringUtils.isEmpty(args[11]) ? 0 :Double.parseDouble(args[11]) ;
		double Dnt=StringUtils.isEmpty(args[4]) ? 0 :Double.parseDouble(args[4]) ;
		double Ent=StringUtils.isEmpty(args[6]) ? 0 :Double.parseDouble(args[6]) ;
		double Fnt=StringUtils.isEmpty(args[15]) ? 0 :Double.parseDouble(args[15]) ;
		double Gnt=StringUtils.isEmpty(args[30]) ? 0 :Double.parseDouble(args[30]) ;
		double Int=StringUtils.isEmpty(args[34]) ? 0 :Double.parseDouble(args[34]) ;
		double Jnt=StringUtils.isEmpty(args[36]) ? 0 :Double.parseDouble(args[36]) ;
		double Knt =0.00;
		//if("3".equalsIgnoreCase(id)){
			Knt =StringUtils.isEmpty(args[38]) ? 0 :Double.parseDouble(args[38]) ;
		//}
		LOGGER.info("A==>"+Ant);
		LOGGER.info("B==>"+Bnt);
		double c=(Ant+Bnt+Cnt+Int+Jnt)-(Dnt+Ent+Fnt+Gnt+Knt);
	    LOGGER.info("Net Due==>"+c);
	    LOGGER.info("PremiumDAOImpl getNetDueXolUpdate || Exit");
		return String.valueOf(c);
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

	public String GetInstalmentAmount(String contractNo,String layerNo,String getAmount)
			{
		LOGGER.info("PremiumDAOImpl GetInstalmentAmount || Enter");
		String  string=null;
		try{
			String query=this.getQuery(DBConstants.PREMIUM_SELECT_MNDPREMIUMOC);
			 final String[] Instalmentno=getAmount.split("_");
			 LOGGER.info("Select mdInstallmentDates Query=>"+query);
			 LOGGER.info("Contract No=>"+contractNo);
			 LOGGER.info("Inst No=>"+Instalmentno[0]);
			 string=(String)this.mytemplate.queryForObject(query, new Object[]{contractNo,layerNo,Instalmentno[0]},String.class);
		}catch(Exception e)
		{
			LOGGER.debug("Exception "+e);		
		}
		LOGGER.info("PremiumDAOImpl GetInstalmentAmount || Exit Ins Amt=>"+string);
		return string;
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
		
		double a=0;
		LOGGER.info("FaculPremiumDAOImpl allocateView() || Enter");
		List<FaculPremiumBean> allocateList = new ArrayList<FaculPremiumBean>();
		try{		
			String[] args = new String[4];
			args[0] = beanObj.getContNo();
			args[1] = beanObj.getTransactionNo();
			args[2] = beanObj.getContNo();
			args[3] = beanObj.getTransactionNo();
			LOGGER.info("Args=>"+args);
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
					tempBean.setStatus(("R".equals(tempMap.get("STATUS")==null?"":tempMap.get("STATUS").toString())?"Reverted":"Allocated"));			
					tempBean.setPay_rec_no(tempMap.get("RECEIPT_NO")==null?"":tempMap.get("RECEIPT_NO").toString());
					tempBean.setSettlementType(tempMap.get("TRANS_TYPE")==null?"":tempMap.get("TRANS_TYPE").toString());
					beanObj.setAllocateType(tempMap.get("ALLOCATE_TYPE")==null?"":tempMap.get("ALLOCATE_TYPE").toString());
					//beanObj.setAllocateType((String)this.mytemplate.queryForObject("SELECT TRANS_TYPE FROM TTRN_PAYMENT_RECEIPT tpr WHERE PAYMENT_RECEIPT_NO=? and tpr.amend_id=(SELECT MAX(AMEND_ID) FROM TTRN_PAYMENT_RECEIPT tr WHERE tr.PAYMENT_RECEIPT_NO= tpr.PAYMENT_RECEIPT_NO )",new Object[]{tempBean.getPay_rec_no()},String.class));
					allocateList.add(tempBean);				
					a=a+Double.parseDouble(tempMap.get("PAID_AMOUNT")==null?"":tempMap.get("PAID_AMOUNT").toString());
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

	public String GetPreviousPremium(FaculPremiumBean bean) {
		String premium="";
		LOGGER.info("Enter into GetPreviousPremium()");
		try{
			String query=" select nvl(sum(M_DPREMIUM_OC+ADJUSTMENT_PREMIUM_OC),0) from rsk_premium_details where CONTRACT_NO = ?";
			LOGGER.info("Query==>"+query);
			LOGGER.info("obj==>"+bean.getContNo());
			premium=(String) this.mytemplate.queryForObject(query, new Object[]{bean.getContNo()},String.class);
		}catch(Exception e){
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("Exit from GetPreviousPremium()"+premium);
		return premium;
	}

	public String GetContractPremium(FaculPremiumBean bean) {
		String premium="";
		LOGGER.info("Enter into GetContractPremium()");
		try{
			String query="SELECT NVL(RSK_SUBJ_PREMIUM_OC,0) FROM  TTRN_RISK_PROPOSAL TRP WHERE RSK_PROPOSAL_NUMBER=(SELECT PROPOSAL_NO FROM POSITION_MASTER PM WHERE CONTRACT_NO=?   AND AMEND_ID = (SELECT   MAX (AMEND_ID) FROM   POSITION_MASTER WHERE   CONTRACT_NO = PM.CONTRACT_NO AND LAYER_NO=PM.LAYER_NO AND CONTRACT_STATUS=PM.CONTRACT_STATUS ) AND CONTRACT_STATUS = 'A'  AND LAYER_NO =?) AND RSK_ENDORSEMENT_NO=(SELECT MAX(RSK_ENDORSEMENT_NO) FROM TTRN_RISK_PROPOSAL WHERE RSK_PROPOSAL_NUMBER=TRP.RSK_PROPOSAL_NUMBER)";
			LOGGER.info("Query==>"+query);
			LOGGER.info("obj==>"+bean.getContNo(),bean.getLayerno());
			premium=(String) this.mytemplate.queryForObject(query, new Object[]{bean.getContNo(),bean.getLayerno()},String.class);
		}catch(Exception e){
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("Exit from GetContractPremium()"+premium);
		return premium;
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
		String query="";
		Object obj[]=null;
		List<Map<String,Object>>list=null;
		String premium="0";
		String signshare="0";
		String dppremium="0";
		String adjpremium="0";
		try {
			String[] value=bean.getGnpiDate().split("_");
			query=getQuery("GET_GNPI_PREMIUM");
			obj=new Object[10];
			obj[0]=bean.getContNo();
			obj[1]=bean.getLayerno();
			obj[2]=bean.getCurrency();
			obj[3]=bean.getLayerno();
			obj[4]=bean.getBranchCode();
			obj[5]=bean.getContNo();
			obj[6]=bean.getLayerno();
			obj[7]=bean.getBranchCode();
			obj[8]=bean.getPredepartment();
			obj[9]=value[0];
			LOGGER.info("Query==>"+query);
			LOGGER.info("obj==>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0){
				Map<String,Object>map=list.get(0);
				premium=map.get("RSK_GNPI_AS_OFF")==null?"0":map.get("RSK_GNPI_AS_OFF").toString();
			}
			query=getQuery("GET_DEPOSIT_PREMIUM");
			obj=new Object[5];
			obj[0]=bean.getContNo();
			obj[1]=bean.getLayerno();
			obj[2]=bean.getPredepartment();
			obj[3]=bean.getCurrency();
			obj[4]=bean.getBranchCode();
			LOGGER.info("Query==>"+query);
			LOGGER.info("obj==>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query,obj);
			
			if(list!=null && list.size()>0){
				Map<String,Object>map=list.get(0);
				dppremium=map.get("MD_PREMIUM")==null?"0":map.get("MD_PREMIUM").toString();
			}
			adjpremium=Double.toString((Double.parseDouble(premium))-(Double.parseDouble(dppremium)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adjpremium;
	}

	public String getDepartmentId(FaculPremiumBean bean) {
		String deptId = "";
		try{
			String query = "SELECT DEPT_ID,PROPOSAL_NO FROM POSITION_MASTER M WHERE M.CONTRACT_NO =? AND M.PRODUCT_ID=?  AND M.AMEND_ID=(SELECT MAX(AMEND_ID) FROM POSITION_MASTER P WHERE P.PROPOSAL_NO=M.PROPOSAL_NO)";
			Object args[] = new Object[2];
			args[0] = bean.getContNo();
			args[1] = bean.getProductId();
			List<Map<String,Object>>list=this.mytemplate.queryForList(query,args);
			if(list!=null &&list.size()>0){
				Map<String,Object>map=list.get(0);
				deptId=map.get("DEPT_ID")==null?"":map.get("DEPT_ID").toString();
				bean.setProposal_No(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
			}
		}catch(Exception e){
			LOGGER.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return deptId;
	}
}

