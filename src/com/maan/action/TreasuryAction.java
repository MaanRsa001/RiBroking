package com.maan.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.bean.FaculitivesBean;
import com.maan.bean.TreasuryBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.CommonCalculation;
import com.maan.service.TreasuryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TreasuryAction extends ActionSupport implements ModelDriven<TreasuryBean> {
	private static final long serialVersionUID = 1L;
	Logger logger = LogUtil.getLogger(getClass());
	Map<String,Object> session = ActionContext.getContext().getSession();
	private String branchCode= session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	private String countryId = session.get("countryId")==null?"":session.get("countryId").toString();
	private String userId= session.get("UserId")==null?"":(String) session.get("UserId");
	TreasuryService service = new TreasuryService();
	DropDownControllor dropDownControllor = new DropDownControllor();
	TreasuryBean bean = new TreasuryBean();
	FaculitivesBean bean1=new FaculitivesBean();
	CommonCalculation calcu = new CommonCalculation();
	private static final String DROPDOWNAJAX="dropdownajax";

	public static Map<String,String> receivePayAmountMap = new HashMap<String, String>();
	public int spage = 1;
	public int value;

	public TreasuryBean getModel() {
		return bean;
	}

	public String init() {
		try {
			bean.setLogin_id(userId);
			bean1.setBranchCode(branchCode);
			bean.setShortname(service.getShortname(branchCode));
			new DropDownControllor().getOpenPeriod(bean1);
			bean.setOpstartDate(bean1.getOpstartDate());
			bean.setOpendDate(bean1.getOpendDate());
			if(StringUtils.isBlank(bean.getFlag())) {
				bean.setPartToShow("listReceiptNo");
				if("reversal".equalsIgnoreCase(bean.getType())){
					List<TreasuryBean> list=service.getReceiptReversalList((session.get("AllocateType")==null?"":session.get("AllocateType").toString()),branchCode,bean.getType(),bean);
					bean.setDetailsList(list);
				}else if("C".equals(bean.getCancelType())){
					bean.setFlag("EDIT");
					service.insertReceiptNO(bean,branchCode);
					List<TreasuryBean> list=service.getReceiptList((session.get("AllocateType")==null?"":session.get("AllocateType").toString()),branchCode,bean.getType(),bean);
					bean.setDetailsList(list);
					}
				else{
				List<TreasuryBean> list=service.getReceiptList((session.get("AllocateType")==null?"":session.get("AllocateType").toString()),branchCode,bean.getType(),bean);
				bean.setDetailsList(list);
				}
			}
			else if("EDIT".equalsIgnoreCase(bean.getFlag())) {
				service.getReceiptEdit(bean,branchCode);
				bean.setFlag("Edit1");
				bean.setMenuStatus("N");
				receiptdeteails();
			}
			else if("JOURNAL".equalsIgnoreCase(bean.getFlag())) {
				bean.setShortname(service.getShortname(branchCode));
				service.getSecondPageInfo(bean,branchCode);
				service.getReceiptEdit(bean,branchCode);
				service.getReceiptGeneration(bean,branchCode);//view method is used because journal also require same data.
				bean.setTreasuryJournalView(service.getTreasuryJournalView(bean));
				bean.setPartToShow("viewJournal");
			}
			else if("VIEW".equalsIgnoreCase(bean.getFlag())||"REVVIEW".equalsIgnoreCase(bean.getFlag())) {
				service.getReceiptGeneration(bean,branchCode);
				if("reversal".equalsIgnoreCase(bean.getType())) {
					bean.setTransType(session.get("AllocateType")==null?"":session.get("AllocateType").toString());
					bean.setDetailsList(service.getReversalInfo(bean,branchCode));
					if(StringUtils.isNotBlank(bean.getPay_rec_no())){
						bean.setTresauryRecPaytailsList(service.getReceiptTreasuryGeneration(bean,branchCode));
					}else{
						bean.setReversedetailsList(service.reverseView(bean,branchCode));
					}
				}
				else {
					if(StringUtils.isNotBlank(bean.getPay_rec_no())){
						bean.setTresauryRecPaytailsList(service.getReceiptTreasuryGeneration(bean,branchCode));
					}else{
						Map<String,List<TreasuryBean>> viewMap=service.allocateView(bean, branchCode);
						bean.setAllocateViewList(viewMap.get("AllList"));
						bean.setAllocatedList(viewMap.get("AllocatedList"));
						bean.setRevertedList(viewMap.get("RevertedList"));
					}
					bean.setDetailsList(service.getReceiptViewList(bean,branchCode));
				}
				bean.setPartToShow("viewReceiptNo");

			} 
			else if("REVERSE".equalsIgnoreCase(bean.getFlag())) {
				bean.setDetailsList(service.reverseView(bean,branchCode));
				bean.setPartToShow("reversalList");
			}
			if("REVVIEW".equalsIgnoreCase(bean.getFlag())) {
				//forward="PaymentRevView";
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return "payment";
	}
	public String currencyajex(){
		bean.setBankCurrencyList(dropDownControllor.getCurrencyMasterDropDown2(branchCode, countryId,bean.getCurrencyId()));
		return "dropdownajax";
	}
	public String print(){
		bean.setShortname(service.getShortname(branchCode));
		if("PRINT".equalsIgnoreCase(bean.getFlag())) {
			service.getReceiptGeneration(bean,branchCode);
			if("reversal".equalsIgnoreCase(bean.getType())) {
				bean.setTransType(session.get("AllocateType")==null?"":session.get("AllocateType").toString());
				bean.setDetailsList(service.getReversalInfo(bean,branchCode));
				if(StringUtils.isNotBlank(bean.getPay_rec_no())){
					bean.setTresauryRecPaytailsList(service.getReceiptTreasuryGeneration(bean,branchCode));
				}else{
					bean.setReversedetailsList(service.reverseView(bean,branchCode));
				}
			}
			else {
				if(StringUtils.isNotBlank(bean.getPay_rec_no())){
					bean.setTresauryRecPaytailsList(service.getReceiptTreasuryGeneration(bean,branchCode));
				}else{
					bean.setReversedetailsList(service.reverseView(bean,branchCode));
				}
				bean.setDetailsList(service.getReceiptViewList(bean,branchCode));
				
			}
			bean.setPartToShow("viewReceiptNo");
		} 
		return "paymentPrint";
	}
	public String allocation() {
		try {
			bean.setLogin_id(userId);
			bean.setHideprocessType("");
			bean.setPartToShow("receiptAllocation");
			bean.setTransType(session.get("AllocateType")==null?"":session.get("AllocateType").toString());//Payment Or Receipt
			bean.setDetailsList(service.getReceiptAllocate(bean,branchCode));
			if("PA".equalsIgnoreCase(bean.getAllocateType())){
				bean.setType(bean.getAllocateType());
				session.put("MenuRights", "PAS,PAD,PAV");
			}
			if("FA".equalsIgnoreCase(bean.getAllocateType())){
				bean.setType(bean.getAllocateType());
				session.put("MenuRights", "FAV,FAD");
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return "allocation";
	}

	public String allocateDetails() {
		try {
			bean.setLogin_id(userId);
			bean.setPartToShow("alloDet");
			bean.setAllocatedStatusList(service.getAllocatedStatus(bean,branchCode));
			bean.setAllocateDetailsList(service.allocateDetails(bean,branchCode));
			service.getReceiptGeneration(bean,branchCode);
			if("JOURNAL".equalsIgnoreCase(bean.getFlag())) {
				bean.setShortname(service.getShortname(branchCode));
				String allocatedType=session.get("AllocateType")==null?"":session.get("AllocateType").toString();
				bean.setTreasuryJournalView(service.getTreasuryJournalView(bean));
				bean.setPartToShow("viewJournal");
				}
		} 
		catch (Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return "allocation";
	}

	public String allocateView() {
		try {
			bean.setAllocatedStatusList(service.getAllocatedStatus(bean, branchCode));
			Map<String,List<TreasuryBean>> viewMap=service.allocateView(bean, branchCode);
			bean.setAllocateViewList(viewMap.get("AllList"));
			bean.setAllocatedList(viewMap.get("AllocatedList"));
			bean.setRevertedList(viewMap.get("RevertedList"));
			service.getReceiptGeneration(bean,branchCode);
			String allocatedType=session.get("AllocateType")==null?"":session.get("AllocateType").toString();
			if ("View".equalsIgnoreCase(bean.getFlag())||"FullView".equalsIgnoreCase(bean.getFlag())) {
				bean.setPartToShow("allocateView");
			}else if("JOURNAL".equalsIgnoreCase(bean.getFlag())) {
				bean.setShortname(service.getShortname(branchCode));
				bean.setTreasuryJournalView(service.getTreasuryJournalView(bean));
				bean.setPartToShow("viewJournal");
				}
			else {
				bean.setPartToShow("allocateRev");
			}
		} 
		catch (Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return "allocation";
	}
	public String receiptdeteails(){
		String forward = "";
		bean.setProid(session.get("mfrid").toString());
		bean.setDept_no(session.get("DepartmentId")==null?"0":session.get("DepartmentId").toString());
		bean.setLogin_id(session.get("UserId").toString());
		try {
			bean.setTransType((session.get("AllocateType")==null?"":session.get("AllocateType").toString()));
			bean1.setBranchCode(branchCode);
			new DropDownControllor().getOpenPeriod(bean1);
			if(!"Edit1".equalsIgnoreCase(bean.getFlag())){
			validateReceiptSubmit();
			}
			else{
			bean.setFlag("EDIT");
			}
			if(!hasActionErrors()) {
					//service.getReceiptGeneration(bean,branchCode);0
					if("Treasury".equals(bean.getTransactionType())) {
						bean.setReversalInfo(service.getReversalInfo(bean,branchCode));
					}
					else if("Transaction".equals(bean.getTransactionType())) {
						if(!"Edit".equalsIgnoreCase(bean.getFlag())){
						bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));
						if(bean.getHideRowCnt()==null || StringUtils.isBlank(bean.getHideRowCnt()) || bean.getHideRowCnt().equalsIgnoreCase("0")){
						bean.setHideRowCnt("3");
						}
						List<String> tempList=new ArrayList<String>();
						for(int i=0;i<Integer.parseInt(bean.getHideRowCnt());i++){
							tempList.add(String.valueOf(i));
						}
						bean.setPaymentList(tempList);
						List<String> test=new ArrayList<String>();
						List<String> test1=new ArrayList<String>();
						List<String> test2=new ArrayList<String>();
						test.add(bean.getCurrency());
						test1.add(dropDownControllor.formatter(bean.getPaymentamount()));
						test2.add(dropDownControllor.formattereight("1"));
						bean.setCedingCompanyValList(test);
						bean.setPayamountValList(test1);
						bean.setSetExcRateValList(test2);
					}
						else{
							bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));
							service.getSecondPageInfo(bean,branchCode);
						}
					}
					bean.setPartToShow("receiptNoGen");
					bean.setCeddingCompanyList(dropDownControllor.getPersonalInfoDropDown(branchCode, "C",""));//C=>Cedding company
					bean.setBrokerList(dropDownControllor.getPersonalInfoDropDown(branchCode, "B",""));//B=>Broker
					bean.setBankMasterList(dropDownControllor.getBankMasterDropDown(branchCode));
					bean.setTransactionTypeList(dropDownControllor.getConstantDropDown("11"));//Transaction Type
					bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));
					forward = "payment";
			}
			else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if(StringUtils.isNotBlank(bean.getAmend_date())) {
				bean.setAmend_date(dateFormat(bean.getAmend_date()));
				}
				bean.setCeddingCompanyList(dropDownControllor.getPersonalInfoDropDown(branchCode, "C",""));//C=>Cedding company
				bean.setBrokerList(dropDownControllor.getPersonalInfoDropDown(branchCode, "B",""));//B=>Broker
				bean.setBankMasterList(dropDownControllor.getBankMasterDropDown(branchCode));
				bean.setTransactionTypeList(dropDownControllor.getConstantDropDown("11"));//Transaction Type
				bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));
				if(StringUtils.isNotBlank(bean.getTr_date())) {
					bean.setTr_date(bean.getTr_date());
				}
				bean.setTransactionType(bean.getTransactionTypeTest());
				bean.setPartToShow("receiptNo");
				forward = "payment";
			}
		}
		catch (Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return forward;
	}
	public String receiptSubmit() {
		String forward = "";
		bean.setProid(session.get("mfrid").toString());
		bean.setDept_no(session.get("DepartmentId")==null?"0":session.get("DepartmentId").toString());
		bean.setLogin_id(session.get("UserId").toString());
		try {
			bean.setTransType((session.get("AllocateType")==null?"":session.get("AllocateType").toString()));
			//bean1.setBranchCode(branchCode);
			//new DropDownControllor().getOpenPeriod(bean1);
			//validateReceiptSubmit();
			getGenerationNO();
			if(!hasActionErrors()) {
				boolean flag=service.insertReceiptNO(bean,branchCode);
				if(flag) { 
					bean.setPartToShow("receiptNoSuc");
					forward= "treasuryPopUp";
					generationInsert();
					if(!"Treasury".equals(bean.getTransactionType())) {
						service.getReceiptGeneration(bean,branchCode);
						}
				} 
			}
			else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if("Treasury".equals(bean.getTransactionType())) {
					bean.setReversalInfo(service.getReversalInfo(bean,branchCode));
				}
				else if("Transaction".equals(bean.getTransactionType())) {
					bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));
					if(bean.getHideRowCnt()==null || StringUtils.isBlank(bean.getHideRowCnt()) || bean.getHideRowCnt().equalsIgnoreCase("0")){
					bean.setHideRowCnt("3");
					}
					List<String> tempList=new ArrayList<String>();
					for(int i=0;i<Integer.parseInt(bean.getHideRowCnt());i++){
						tempList.add(String.valueOf(i));
					}
					bean.setPaymentList(tempList);
				}
				bean.setPartToShow("receiptNoGen");
				bean.setCeddingCompanyList(dropDownControllor.getPersonalInfoDropDown(branchCode, "C",""));//C=>Cedding company
				bean.setBrokerList(dropDownControllor.getPersonalInfoDropDown(branchCode, "B",""));//B=>Broker
				bean.setBankMasterList(dropDownControllor.getBankMasterDropDown(branchCode));
				bean.setTransactionTypeList(dropDownControllor.getConstantDropDown("11"));//Transaction Type
				bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));
				forward = "payment";
			}
		} 
		
		catch (Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return forward;
	}

	public String reverseInsert() {
		try {
			bean1.setBranchCode(branchCode);
			bean.setLogin_id(session.get("UserId").toString());
			new DropDownControllor().getOpenPeriod(bean1);
			validateReverseInsert();
			bean.setTransType((session.get("AllocateType") == null ? "" : session.get("AllocateType").toString()));
			if (getActionErrors().isEmpty()) {
				bean.setAllocateDetailsList(service.reverseInsert(bean,branchCode));
				bean.setAllocatedStatusList(service.getAllocatedStatus(bean,branchCode));
				bean.setPartToShow("revSuccess");
			} 
			else {
				bean.setAllocatedStatusList(service.getAllocatedStatus(bean,branchCode));
				Map<String,List<TreasuryBean>> viewMap=service.allocateView(bean,branchCode);
				if(StringUtils.isNotBlank(bean.getReversalDate())) {
					bean.setReversalDate(bean.getReversalDate());
				}
				bean.setAllocateViewList(viewMap.get("AllList"));
				bean.setPartToShow("allocateRev");
			}
		} 
		catch (Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return "allocation";
	}

	private void validateReverseInsert() {
		final Validation validation = new Validation();
		if(StringUtils.isBlank(bean.getReversalDate())) {
			this.addActionError(getText("errors.payment.reverseDateReq"));

		}
		else if(validation.checkDate(bean.getReversalDate()).equalsIgnoreCase("INVALID")) {
			this.addActionError(getText("errors.payment.reverseDateInv"));
		}
		else if(validation.ValidateTwoDates(bean.getAllocateddate(),bean.getReversalDate()).equalsIgnoreCase("INVALID")) {
			addActionError(getText("errors.payment.revDateGretAlloDate"));
		}
		if(new DropDownControllor().Validatethree(branchCode, bean.getAllocateddate())!=0){
			if(!bean.getAllocateddate().equalsIgnoreCase(bean.getReversalDate())){
				addActionError(getText("period.not.closed"));
			}
		}else{
			if(!validation.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !validation.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !validation.isNull(bean.getReversalDate()).equalsIgnoreCase("") ){
				if(new DropDownControllor().Validatethree(branchCode, bean.getReversalDate())==0){
					addActionError(getText("errors.open.period.date.reversal",new String[] {bean1.getOpenPeriodDate()}));
				}
				}
		}
	}

	public String contractAllocate() {
		try {
			bean.setLogin_id(userId);
			validateContractAllocate();
			if(getActionErrors().isEmpty()) {
				int pagePosition = Integer.parseInt("0".equals(bean.getPagecount()) ? "1" : bean.getPagecount());
				if (StringUtils.isBlank(bean.getExpg())) {
					bean.setOneTime("1");
					//checkedItems = null;
					bean.setSelect(null);
					bean.setPagecount("1");
					bean.setPageLength("0");
					pagePosition = 1;
					receivePayAmountMap = new HashMap<String, String>();
				}

				List<String> transactionNos = bean.getTransactionNos();
				List<String> receivePayAmounts = bean.getReceivePayAmounts();
				List<String> chkbox = bean.getChkbox();
				if(StringUtils.isNotBlank( bean.getHideprocessType())&& bean.getHideprocessType().equals("R")){
					String[] ary=new String[1];
					if(StringUtils.isNotBlank(bean.getSign())){
					ary = bean.getSign().split(",");
					}
				if(transactionNos!=null && transactionNos.size()>0) {
					for(int i=0 ; i<transactionNos.size() ; i++) {
						if("true".equalsIgnoreCase(chkbox.get(i))) {
							if(receivePayAmountMap.containsKey(transactionNos.get(i))) {
								receivePayAmountMap.remove(transactionNos.get(i));
								receivePayAmountMap.put(transactionNos.get(i), ary[i]+receivePayAmounts.get(i));
							}
							else {
								receivePayAmountMap.put(transactionNos.get(i), ary[i]+receivePayAmounts.get(i));						
							}
						}
						else if(receivePayAmountMap!=null && receivePayAmountMap.containsKey(transactionNos.get(i))) {
							receivePayAmountMap.remove(transactionNos.get(i));
						}
					}
				}
				}else{
					if(transactionNos!=null && transactionNos.size()>0) {
						for(int i=0 ; i<transactionNos.size() ; i++) {
							if("true".equalsIgnoreCase(chkbox.get(i))) {
								if(receivePayAmountMap.containsKey(transactionNos.get(i))) {
									receivePayAmountMap.remove(transactionNos.get(i));
									receivePayAmountMap.put(transactionNos.get(i), receivePayAmounts.get(i));
								}
								else {
									receivePayAmountMap.put(transactionNos.get(i), receivePayAmounts.get(i));						
								}
							}
							else if(receivePayAmountMap!=null && receivePayAmountMap.containsKey(transactionNos.get(i))) {
								receivePayAmountMap.remove(transactionNos.get(i));
							}
						}
					}
					
				}
				spage = service.externalPaginationAddon(bean, pagePosition, value, spage);

				//payform.setTransType((session.getAttribute("AllocateType")==null?"":session.getAttribute("AllocateType").toString()));
				bean.setTransType((session.get("AllocateType") == null ? "" : session.get("AllocateType").toString()));
				bean.setBroker(bean.getBrokerid());
				bean.setCedingCo(bean.getCedingid());
				String selcur="";
				String currencyid="";
				String polino="";
				selcur=bean.getHidSelCurrencey();
				StringTokenizer st=new StringTokenizer(selcur,"$");
				while(st.hasMoreElements()) {
					currencyid=st.nextToken();
					polino=st.nextToken();

				}
				bean.setAlloccurrencyid(currencyid);	
				//session.set("currencyid", currencyid);
				bean.setPolicyno(polino);
				String currval=service.getCurrecyAmount(branchCode,currencyid, polino);
				String currencyName="";
				String CurrencyValue="";
				StringTokenizer stc=new StringTokenizer(currval,"$");
				while(stc.hasMoreElements()) {
					CurrencyValue=stc.nextToken();
					currencyName=stc.nextToken();
				}
				bean.setCurrencyName(currencyName);
				bean.setCurrencyValue(CurrencyValue);
				bean.setTr_date(dropDownControllor.getTrans(bean));
				if(StringUtils.isBlank(bean.getHideprocessType())){
				bean.setTransactionContractList(service.getTransContract(bean,branchCode,receivePayAmountMap));
				bean.setPartToShow("transactionAllocation");
				}else if("R".equals(bean.getHideprocessType())){
					bean.setTransactionContractList(service.getRetroTransContract(bean,branchCode,receivePayAmountMap));
					bean.setPartToShow("retrotransactionAllocation");
				}
				bean.setPay_rec_no(bean.getSerial_no());
				bean.setAllocatedStatusList(service.getAllocatedStatus(bean,branchCode));
				
				bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));


				if(StringUtils.isBlank(bean.getUnUtilizedAmt())) {
					bean.setUnUtilizedAmt(dropDownControllor.decimalFormat(Double.valueOf(bean.getCurrencyValue()), 2));
				}

				float pageLength = Float.valueOf(bean.getTotalreccount()) / Float.valueOf(bean.getPaginationsize());
				bean.setPageLength(String.valueOf((int) Math.ceil(pageLength)));
				bean.setLpage(bean.getPageLength());
				bean.setCpage(String.valueOf(pagePosition));
				bean.setTotalpages(bean.getPageLength());
				if ("1".equals(bean.getOneTime())) {
					//checkedItems = new String[Integer.parseInt(bean.getPageLength()) + 1][];
					bean.setCurrentpageno("1");
					bean.setOneTime(String.valueOf(Integer.parseInt(bean.getOneTime()) + 1));
				}
			}
			else {
				allocation();
			}
		}
		catch (Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return "allocation";
	}

	public String generationInsert(){		
		try {
			bean.setTransType((session.get("AllocateType")==null?"":session.get("AllocateType").toString()));
			bean.setLogin_id(session.get("UserId").toString());
			//getGenerationNO();
			if(!hasActionErrors()){
				service.generationInsert(bean,branchCode);
				if("Treasury".equals(bean.getTransactionType())) {
				service.getReceiptGeneration(bean,branchCode);
				}
				if(!"Treasury".equals(bean.getTransactionType())){
					bean.setDetailsList(service.getReceiptViewList(bean,branchCode));
					bean.setOrginalCurrencyList( new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
					if("PT".equalsIgnoreCase(bean.getTransType())){
						addActionError(getText("payment.sucess.msg"));
					}else{
						addActionError(getText("receipt.sucess.msg"));
					}
				}else{
					bean.setDetailsList(service.getReversalInfo(bean, branchCode));

					addActionError(getText("payment.revert.msg"));
				}
				//bean.setHideRowCnt("3");
				//int LoopCount=Integer.parseInt(bean.getHideRowCnt());
				if(bean.getDetailsList().size()>0){
				int LoopCount=Integer.parseInt(bean.getDetailsList().get(0).getHideRowCnt());
				List<String> tempList=new ArrayList<String>();
				for(int i=0;i<LoopCount;i++){
					tempList.add(String.valueOf(i));
				}
				bean.setPaymentList(tempList);
				}
				bean.setOrginalCurrencyList(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
				bean.setPartToShow("receiptNoSuc");
			}
			else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
			}
		} catch (Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
		}
		return "treasuryPopUp";
	}

	private void validateContractAllocate() {
		if(StringUtils.isBlank(bean.getSelects())) {
			this.addActionError(getText("errors.payment.radio"));
		}
		else if(StringUtils.isBlank(bean.getHidSelCurrencey())) {
			this.addActionError(getText("errors.payment.hidSelCurrencey"));
		}
	}

	public String newReceipt() {
		try{    
			
			bean.setMenuStatus("N");
			bean.setCeddingCompanyList(dropDownControllor.getPersonalInfoDropDown(branchCode, "C","")); //C=>Ceding Compacy
			bean.setBrokerList(dropDownControllor.getPersonalInfoDropDown(branchCode, "B",""));//B=>Broker
			bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));
			bean.setBankMasterList(dropDownControllor.getBankMasterDropDown(branchCode));
			bean.setTransactionTypeList(dropDownControllor.getConstantDropDown("11"));//Transaction Type
			//bean.setTransactionType("Transaction");
			bean.setFlag("");
			bean.setPartToShow("receiptNo");
		}
		catch(Exception exception) {   
			logger.debug("Exception @ { " + exception + " } ");
		}
		return "payment";
	}
	
	public String submitAllocation() {
		try {
			//bean.setAlloccurrencyid("");
			bean.setLogin_id(userId);
			bean.setTransType((session.get("AllocateType") == null ? "" : session.get("AllocateType").toString()));
			bean.setTr_date(dropDownControllor.getTrans(bean));
			String transactionNos[] = bean.getSelectTransactionNo().split(",");
			String receivePayAmounts[] = bean.getSelreceivePayAmount().split(",");
			//List<String> transactionNos = bean.getTransactionNos();
			//List<String> receivePayAmounts = bean.getReceivePayAmounts();
			//List<String>incepdate=bean.getInceptiobdate();
			//List<String> chkbox = bean.getChkbox();
			receivePayAmountMap = new HashMap<String, String>();
			if(transactionNos!=null && transactionNos.length>0) {
				for(int i=0 ; i<transactionNos.length ; i++) {
					//if("true".equalsIgnoreCase(chkbox.get(i))) {
						if(receivePayAmountMap.containsKey(transactionNos[i])) {
							receivePayAmountMap.remove(transactionNos[i]);
							receivePayAmountMap.put(transactionNos[i], receivePayAmounts[i]);
						}
						else {
							receivePayAmountMap.put(transactionNos[i], receivePayAmounts[i]);						
						}
					/*}
					else if(receivePayAmountMap!=null && receivePayAmountMap.containsKey(transactionNos.get(i))) {
						receivePayAmountMap.remove(transactionNos.get(i));
					}*/
				}
			}
			bean1.setBranchCode(branchCode);
			new DropDownControllor().getOpenPeriod(bean1);
			validateAllocateTrans();
			if (getActionErrors().isEmpty()) {
				service.getallocateTransaction(bean,branchCode,receivePayAmountMap);
				Map<String,List<TreasuryBean>> allocateView = service.allocateView(bean,branchCode);
				bean.setAllocatedStatusList(service.getAllocatedStatus(bean,branchCode));
				bean.setAllocatedList(allocateView.get("AllocatedList"));
				bean.setFlag("View");
				bean.setPartToShow("allocateView");
			}
			else {
				int pagePosition = Integer.parseInt("0".equals(bean.getPagecount()) ? "1" : bean.getPagecount());
				
				spage = service.externalPaginationAddon(bean, pagePosition, value, spage);
				bean.setPay_rec_no(bean.getSerial_no());
				bean.setAllocatedStatusList(service.getAllocatedStatus(bean,branchCode));
				bean.setTransType((session.get("AllocateType") == null ? "" : session.get("AllocateType").toString()));
				bean.setBroker(bean.getBrokerid());
				bean.setCedingCo(bean.getCedingid());
				String selcur="";
				String currencyid="";
				String polino="";
				selcur=bean.getHidSelCurrencey();
				StringTokenizer st=new StringTokenizer(selcur,"$");
				while(st.hasMoreElements()) {
					currencyid=st.nextToken();
					polino=st.nextToken();

				}
				bean.setAlloccurrencyid(currencyid);	
				//session.set("currencyid", currencyid);
				bean.setPolicyno(polino);
				String currval=service.getCurrecyAmount(branchCode,currencyid, polino);
				String currencyName="";
				String CurrencyValue="";
				StringTokenizer stc=new StringTokenizer(currval,"$");
				while(stc.hasMoreElements()) {
					CurrencyValue=stc.nextToken();
					currencyName=stc.nextToken();
				}
				bean.setCurrencyName(currencyName);
				bean.setCurrencyValue(CurrencyValue);
				if(StringUtils.isNotBlank(bean.getAccountDate())){
					bean.setAccountDate(bean.getAccountDate());
				}
				
				bean.setTransactionContractList(service.getTransContract(bean,branchCode,receivePayAmountMap));


				bean.setPartToShow("transactionAllocation");
				bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));


				if(StringUtils.isBlank(bean.getUnUtilizedAmt())) {
					bean.setUnUtilizedAmt(dropDownControllor.decimalFormat(Double.valueOf(bean.getCurrencyValue()), 2));
				}

				float pageLength = Float.valueOf(bean.getTotalreccount()) / Float.valueOf(bean.getPaginationsize());
				bean.setPageLength(String.valueOf((int) Math.ceil(pageLength)));
				bean.setLpage(bean.getPageLength());
				bean.setCpage(String.valueOf(pagePosition));
				bean.setTotalpages(bean.getPageLength());
				if ("1".equals(bean.getOneTime())) {
					bean.setCurrentpageno("1");
					bean.setOneTime(String.valueOf(Integer.parseInt(bean.getOneTime()) + 1));
				}
			}
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return "allocation";
	}
	public String submitRetroAllocation() {
		try {
			bean.setTransType((session.get("AllocateType") == null ? "" : session.get("AllocateType").toString()));
			bean.setTr_date(dropDownControllor.getTrans(bean));
			List<String> transactionNos = bean.getTransactionNos();
			List<String> receivePayAmounts = bean.getReceivePayAmounts();
			List<String> chkbox = bean.getChkbox();
			//List<String> chkbox1 = bean.getSign();
			String[] ary = bean.getSign().split(",");
			//List<String> chkbox1 =new ArrayList<String>();
			//chkbox1.add(bean.getSign());
			//List<String> wordList = Arrays.asList(ary); 
			if(transactionNos!=null && transactionNos.size()>0) {
				for(int i=0 ; i<transactionNos.size() ; i++) {
					if("true".equalsIgnoreCase(chkbox.get(i))) {
						if(receivePayAmountMap.containsKey(transactionNos.get(i))) {
							receivePayAmountMap.remove(transactionNos.get(i));
							receivePayAmountMap.put(transactionNos.get(i), ary[i]+receivePayAmounts.get(i));
						}
						else {
							receivePayAmountMap.put(transactionNos.get(i), ary[i]+receivePayAmounts.get(i));						
						}
					}
					else if(receivePayAmountMap!=null && receivePayAmountMap.containsKey(transactionNos.get(i))) {
						receivePayAmountMap.remove(transactionNos.get(i));
					}
				}
			}
			bean1.setBranchCode(branchCode);
			bean.setLogin_id(userId);
			new DropDownControllor().getOpenPeriod(bean1);
			validateRetroAllocateTrans();
			if (getActionErrors().isEmpty()) {
				service.getRetroallocateTransaction(bean,branchCode,receivePayAmountMap);
				Map<String,List<TreasuryBean>> allocateView = service.allocateView(bean,branchCode);
				bean.setAllocatedStatusList(service.getAllocatedStatus(bean,branchCode));
				bean.setAllocatedList(allocateView.get("AllocatedList"));
				bean.setFlag("RetroView");
				bean.setPartToShow("RetroallocateView");
			}
			else {
				int pagePosition = Integer.parseInt("0".equals(bean.getPagecount()) ? "1" : bean.getPagecount());
				
				spage = service.externalPaginationAddon(bean, pagePosition, value, spage);
				bean.setPay_rec_no(bean.getSerial_no());
				bean.setAllocatedStatusList(service.getAllocatedStatus(bean,branchCode));
				bean.setTransType((session.get("AllocateType") == null ? "" : session.get("AllocateType").toString()));
				bean.setBroker(bean.getBrokerid());
				bean.setCedingCo(bean.getCedingid());
				String selcur="";
				String currencyid="";
				String polino="";
				selcur=bean.getHidSelCurrencey();
				StringTokenizer st=new StringTokenizer(selcur,"$");
				while(st.hasMoreElements()) {
					currencyid=st.nextToken();
					polino=st.nextToken();

				}
				bean.setAlloccurrencyid(currencyid);	
				bean.setPolicyno(polino);
				String currval=service.getCurrecyAmount(branchCode,currencyid, polino);
				String currencyName="";
				String CurrencyValue="";
				StringTokenizer stc=new StringTokenizer(currval,"$");
				while(stc.hasMoreElements()) {
					CurrencyValue=stc.nextToken();
					currencyName=stc.nextToken();
				}
				bean.setCurrencyName(currencyName);
				bean.setCurrencyValue(CurrencyValue);
				if(StringUtils.isNotBlank(bean.getAccountDate())){
					bean.setAccountDate(bean.getAccountDate());
				}
				
				bean.setTransactionContractList(service.getRetroTransContract(bean,branchCode,receivePayAmountMap));


				bean.setPartToShow("retrotransactionAllocation");
				bean.setOrginalCurrencyList(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));


				if(StringUtils.isBlank(bean.getUnUtilizedAmt())) {
					bean.setUnUtilizedAmt(dropDownControllor.decimalFormat(Double.valueOf(bean.getCurrencyValue()), 2));
				}

				float pageLength = Float.valueOf(bean.getTotalreccount()) / Float.valueOf(bean.getPaginationsize());
				bean.setPageLength(String.valueOf((int) Math.ceil(pageLength)));
				bean.setLpage(bean.getPageLength());
				bean.setCpage(String.valueOf(pagePosition));
				bean.setTotalpages(bean.getPageLength());
				if ("1".equals(bean.getOneTime())) {
					bean.setCurrentpageno("1");
					bean.setOneTime(String.valueOf(Integer.parseInt(bean.getOneTime()) + 1));
				}
			}
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return "allocation";
	}
	
	private void validateReceiptSubmit() {
		final Validation val=new Validation();
		//broker cedingCo
        try {
		if("".equalsIgnoreCase(bean.getBroker())) {
			this.addActionError(getText("errors.payment.broker"));
		}
		if(bean.getBroker().equals("63")) {
			if("0".equalsIgnoreCase(bean.getCedingCo())) {
				this.addActionError(getText("errors.payment.cedingCo"));

			}
		}
		if("0".equalsIgnoreCase(bean.getReceiptBankId())) {
			if("PT".equalsIgnoreCase(bean.getTransType())){
				this.addActionError(getText("errors.payment.bank"));
			}
			else {
				this.addActionError(getText("errors.payment.receiptbank"));
			}
		}
		if("".equalsIgnoreCase(bean.getTr_date())) {
			this.addActionError(getText("errors.payment.tr_date"));

		}
		else if(val.checkDate(bean.getTr_date()).equalsIgnoreCase("INVALID")) {
			this.addActionError(getText("errors.payment.tr_dateInv"));

		}
		if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getTr_date()).equalsIgnoreCase("") && !"EDIT".equalsIgnoreCase(bean.getFlag())){
			if(new DropDownControllor().Validatethree(branchCode, bean.getTr_date())==0){
				addActionError(getText("errors.open.period.date.trans",new String[] {bean1.getOpenPeriodDate()}));
			}
			}
		if("0".equalsIgnoreCase(bean.getCurrency()))
		{
			if("PT".equalsIgnoreCase(bean.getTransType())) {
				this.addActionError(getText("errors.payment.paycurrency"));
			}
			else {
				this.addActionError(getText("errors.payment.currency"));
			}
		}
		if(bean.getExrate().equalsIgnoreCase("")) {
			this.addActionError(getText("errors.payment.exrate"));		 
		}
		bean.setPaymentamount(bean.getPaymentamount().replaceAll(",",""));
		if(bean.getPaymentamount().equalsIgnoreCase("")) {
			this.addActionError(getText("errors.payment.amount"));
		}
		else if(val.numbervalid(bean.getPaymentamount()).equalsIgnoreCase("INVALID")) {
			this.addActionError(getText("errors.payment.amount1"));
		}
		bean.setBankCharges(bean.getBankCharges().replaceAll(",",""));
		if("".equalsIgnoreCase(bean.getBankCharges())) {
			this.addActionError(getText("errors.payment.bankChargesReq"));
		}
		else if("INVALID".equalsIgnoreCase(val.numbervalid(bean.getBankCharges()))) {
			this.addActionError(getText("errors.payment.bankChargesInv"));
		}bean.setPremiumLavy(bean.getPremiumLavy().replaceAll(",",""));
		if("".equalsIgnoreCase(bean.getPremiumLavy())) {
			this.addActionError(getText("errors.payment.premiumLavyReq"));
		}
		else if("INVALID".equalsIgnoreCase(val.numbervalid(bean.getPremiumLavy()))) {
			this.addActionError(getText("errors.payment.premiumLavyInv"));
		}
            bean.setWithHoldingTaxOC(bean.getWithHoldingTaxOC().replaceAll(",", ""));
            if ("".equalsIgnoreCase(bean.getWithHoldingTaxOC())) {
                bean.setWithHoldingTaxOC("0");
            }
            else if("INVALID".equalsIgnoreCase(val.numbervalid(bean.getWithHoldingTaxOC()))) {
            	if("PT".equalsIgnoreCase(bean.getTransType())) {
            		this.addActionError(getText("errors.whtax.check.PT"));
            	}else{
                this.addActionError(getText("errors.whtax.check"));
            	}
            }
		if(StringUtils.isBlank(bean.getBaseCurrencyAmount())){
			this.addActionError(getText("errors.payment.baseCurrency"));
		}else{
			String ans = calcu.calculateTreasuryPayment(bean,"baseCur",0);
			if(Double.parseDouble(ans)!=Double.parseDouble(bean.getBaseCurrencyAmount().replaceAll(",", ""))){
				this.addActionError(getText("error.calcul.mistake"));
				logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
			}else{
				bean.setBaseCurrencyAmount(ans);
			}
		}
		bean.setNetAmt(bean.getNetAmt().replaceAll(",",""));
		if("".equalsIgnoreCase(bean.getNetAmt())) {
			this.addActionError(getText("errors.payment.netAmtReq"));
		}
		else if("INVALID".equalsIgnoreCase(val.numbervalid(bean.getNetAmt()))) {
			this.addActionError(getText("errors.payment.netAmtInv"));
		}else{
			String ans = calcu.calculateTreasuryPayment(bean,"netAmt",0);
			if(Double.parseDouble(ans)!=Double.parseDouble(bean.getNetAmt())){
				this.addActionError(getText("error.calcul.mistake"));
				logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
			}else{
				bean.setNetAmt(ans);
			}
			//bean.setNetAmt(bean.getNetAmt().replaceAll(",",""));
		}
		if("EDIT".equalsIgnoreCase(bean.getFlag())){
                /*bean.setAmend_date(dropDownControllor.getAmend(bean));
                   bean.setMaxDate(Validation.getMaxDateValidate(bean.getTr_date(), bean.getAmend_date()));*/
		if("".equalsIgnoreCase(bean.getAmend_date())) {
			this.addActionError(getText("errors.payment.amend_date"));
		}
		else if(val.checkDate(bean.getAmend_date()).equalsIgnoreCase("INVALID")) {
			this.addActionError(getText("errors.payment.amend_dateInv"));
            }/*else  if ("Invalid".equalsIgnoreCase(validation.ValidateTwo(bean.getMaxDate(), bean.getAmend_date()))) {
                addActionError(getText("errors.endoDate.invalid"));
		}*/
		if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAmend_date()).equalsIgnoreCase("") ){
			if(new DropDownControllor().Validatethree(branchCode, bean.getAmend_date())==0){
				addActionError(getText("errors.open.period.date.trans.amend",new String[] {bean1.getOpenPeriodDate()}));
			}
			}
	}
        } catch (Exception e) {
            this.addActionError(getText("error.validate.data"));
        }
	}

	private void getGenerationNO() {
		//ActionErrors errors=new ActionErrors();
		final Validation val=new Validation();
		try{
			DecimalFormat df = new DecimalFormat("#.##");
			if(!"Treasury".equalsIgnoreCase(bean.getTransactionType())){
				String pay="";	
				double payvalue=0.0;
				int k=0;
				double diffamt=0.0;
				double totamt=0.0;
				double totConRecCur=0.0;
				double paymentamount=0.0;
				double exchangeRate=0.0;
				double convertreceipt=0.0;
				double sumConRec=0;
				double sumtotAmt=0;
				if(StringUtils.isNotBlank(bean.getPaymentamount())){
				bean.setPaymentamount(bean.getPaymentamount().replaceAll(",", ""));
				}
				if(StringUtils.isNotBlank(bean.getReceiptamt())){
				bean.setReceiptamt(bean.getReceiptamt().replaceAll(",", ""));
				}
				if(StringUtils.isNotBlank(bean.getBankCharges())){
					bean.setBankCharges(bean.getBankCharges().replaceAll(",", ""));
				}if(StringUtils.isNotBlank(bean.getPremiumLavy())){
					bean.setPremiumLavy(bean.getPremiumLavy().replaceAll(",", ""));
				}
				//Validation val=new Validation();
				int LoopCount=Integer.parseInt(bean.getHideRowCnt());
				int y=0;
				logger.info("LoopCount==>"+LoopCount);
				for(int i=0;i<LoopCount;i++){
					if(!"0".equalsIgnoreCase(bean.getCedingCompanyValList().get(i))){
						k++;			 
					}
					if(i>0){
						y=i-1;
						for(y=0;y<i;y++){
							if(!"0".equalsIgnoreCase(bean.getCedingCompanyValList().get(i))  && !"0".equalsIgnoreCase(bean.getCedingCompanyValList().get(y))){
								if(bean.getCedingCompanyValList().get(i).equals(bean.getCedingCompanyValList().get(y))){
									this.addActionError(getText("errors.payment.ceddinalready"));  
								}
							}
						}
					}if(!"".equalsIgnoreCase(bean.getPayamountValList().get(i)) || !"".equalsIgnoreCase(bean.getSetExcRateValList().get(i)) ||!"0.00".equalsIgnoreCase(bean.getConRecCurValList().get(i))){
						
					if("0".equalsIgnoreCase(bean.getCedingCompanyValList().get(i))){
						this.addActionError(getText("errors.payment.CedingCompay",new String[]{String.valueOf(i+1)}));
					}
					}
					if(!"0".equalsIgnoreCase(bean.getCedingCompanyValList().get(i))){
						//if("".equalsIgnoreCase(bean.getPayamountValList().get(i)) || "0".equalsIgnoreCase(bean.getPayamountValList().get(i))){
							if("".equalsIgnoreCase(bean.getPayamountValList().get(i).replaceAll(",", ""))){
							this.addActionError(getText("errors.payment.paidAmountv",new String[]{String.valueOf(i+1)}));
						} 
						else if(val.numbervalid(bean.getPayamountValList().get(i)==null?"":bean.getPayamountValList().get(i).replaceAll(",", "")).equalsIgnoreCase("INVALID")){
							this.addActionError(getText("errors.payment.payamount1",new String[]{String.valueOf(i+1)}));
						}
						else if(!"".equalsIgnoreCase(bean.getPayamountValList().get(i)==null?"":bean.getPayamountValList().get(i).replaceAll(",", ""))){
							pay=bean.getPayamountValList().get(i).replaceAll(",", "");
							try{
								payvalue=Double.parseDouble(pay);					 		 
							}
							catch(Exception ex){
								this.addActionError(getText("errors.payment.ValidPayAmount"));	 
							}
						}					
						totamt=Double.parseDouble(bean.getHidTotalAmt()==null?"0":bean.getHidTotalAmt().replaceAll(",", ""));
						double txtdiffper=Double.parseDouble(bean.getTxtDiffPer()==null?"0":bean.getTxtDiffPer().replaceAll(",", ""));
						 totamt=Double.parseDouble(df.format(totamt+txtdiffper));
						logger.info("Total Amount===>"+totamt);
						
						if("".equalsIgnoreCase(bean.getSetExcRateValList().get(i)) || "0".equalsIgnoreCase(bean.getSetExcRateValList().get(i).replaceAll(",", ""))){
							this.addActionError(getText("errors.payment.setExcRate.req",new String[]{String.valueOf(i+1)}));
						}else if(val.percentageValid(bean.getSetExcRateValList().get(i).replaceAll(",", "")).equalsIgnoreCase("INVALID")){
							this.addActionError(getText("errors.payment.setExcRate.inv",new String[]{String.valueOf(i+1)}));		
						}else{
							if(!"".equalsIgnoreCase(bean.getPayamountValList().get(i)) || !"".equalsIgnoreCase(bean.getConRecCurValList().get(i))){
								String ans = calcu.calculateTreasuryPayment(bean,"setExchangeRate",i);
								if(Double.parseDouble(ans)!=Double.parseDouble(bean.getSetExcRateValList().get(i).replaceAll(",", ""))){
									this.addActionError(getText("error.calcul.mistake"));
									logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
								}else{
									bean.getSetExcRateValList().set(i,(ans));
								}
							}
							exchangeRate=Double.parseDouble(bean.getSetExcRateValList().get(i).replaceAll(",", ""));
							totConRecCur=Double.parseDouble(bean.getTotalConRecCur()==null?"0":bean.getTotalConRecCur().replaceAll(",", ""));
							paymentamount=Double.parseDouble(bean.getPaymentamount().replaceAll(",", ""));
							logger.info("totConRecCur Amount====>"+totConRecCur);
							logger.info("payment Amount====>"+paymentamount);
						}
						if("".equalsIgnoreCase(bean.getExachangeValList().get(i)) || "0".equalsIgnoreCase(bean.getExachangeValList().get(i).replaceAll(",", ""))){
							this.addActionError(getText("errors.payment.setExcRate.req1",new String[]{String.valueOf(i+1)}));
						}else if(val.percentageValid(bean.getExachangeValList().get(i)).equalsIgnoreCase("INVALID")){
							this.addActionError(getText("errors.payment.setExcRate.inv1",new String[]{String.valueOf(i+1)}));		
						}
						}
					if(!"".equalsIgnoreCase(bean.getPayamountValList().get(i)) || !"".equalsIgnoreCase(bean.getSetExcRateValList().get(i))){
						String ans = calcu.calculateTreasuryPayment(bean,"ConRec",i);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getConRecCurValList().get(i).replaceAll(",", ""))){
							this.addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.getConRecCurValList().set(i,(ans));
						}
						sumConRec = sumConRec + Double.parseDouble(bean.getConRecCurValList().get(i).replaceAll(",", "")); 
					}if(!"".equalsIgnoreCase(bean.getPayamountValList().get(i)) || !"".equalsIgnoreCase(bean.getExachangeValList().get(i))){
						String ans = calcu.calculateTreasuryPayment(bean,"totalAmt",i);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getRowamountValList().get(i).replaceAll(",", ""))){
							this.addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.getRowamountValList().set(i,(ans));
						}
						 sumtotAmt = sumtotAmt + Double.parseDouble(bean.getRowamountValList().get(i).replaceAll(",", ""));
					}
				}	
				sumConRec = Double.parseDouble(bean.getPaymentamount().replaceAll(",", "")) - sumConRec;
				sumtotAmt = Double.parseDouble(bean.getBaseCurrencyAmount().replaceAll(",", "")) - sumtotAmt;
				sumConRec = Double.parseDouble(DropDownControllor.formatter(Double.toString(sumConRec)).replaceAll(",", ""));
				sumtotAmt =Double.parseDouble(DropDownControllor.formatter(Double.toString(sumtotAmt)).replaceAll(",", ""));
				logger.info("Jsp Calculation Rounding Off Converted Paid Amount"+bean.getConvertedDiffAmount());
				if(sumConRec!=Double.parseDouble(bean.getConvertedDiffAmount().replaceAll(",", ""))){
					this.addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.setConvertedDiffAmount(DropDownControllor.formatter(Double.toString(sumConRec)).replaceAll(",", ""));
				}
				logger.info("Java Calculation Rounding Off Converted Paid Amount"+DropDownControllor.formatter(Double.toString(sumConRec)).replaceAll(",", ""));
				
				logger.info("Jsp Calculation Rounding Off Total Amount"+bean.getTxtDiffPer());
				if(sumtotAmt!=Double.parseDouble(bean.getTxtDiffPer().replaceAll(",", ""))){
					this.addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.setTxtDiffPer(DropDownControllor.formatter(Double.toString(sumtotAmt)).replaceAll(",", ""));
				}
				logger.info("Java Calculation Rounding Off Total Amount"+DropDownControllor.formatter(Double.toString(sumtotAmt)).replaceAll(",", ""));
				/*if(k==0){
					this.addActionError(getText("errors.payment.CedingCompay"));
				}	*/	
				if(bean.getTotalConRecCur().equals("")){
					this.addActionError(getText("errors.payment.Type"));	
				}
				
				if (!bean.getTotalConRecCur().equals("")){
					try{	
						diffamt=Double.parseDouble(bean.getTotalConRecCur()==null?"0":bean.getTotalConRecCur().replaceAll(",", ""));
					}
					catch(Exception e){
						this.addActionError(getText("errors.payment.ValidDifferentAmount"));		
					}
				} 
				/*if("B".equalsIgnoreCase(bean.getDifftype())){
					//totamt=totamt+diffamt;
				}
				if("M".equalsIgnoreCase(bean.getDifftype())){
					//totamt=totamt-diffamt;
				}*/
				double receiptamt=0.0;
				if(StringUtils.isNotBlank(bean.getPaymentamount())&&StringUtils.isNotBlank(bean.getExrate())){
				//if(!bean.getReceiptamt().equals("")){
					//receiptamt=Double.parseDouble(bean.getReceiptamt());
					double payment=Double.parseDouble(bean.getPaymentamount().replaceAll(",", ""));
					double receipt=Double.parseDouble(bean.getExrate().replaceAll(",", ""));
					
					receiptamt=Double.parseDouble(df.format(payment/receipt));
				}
				logger.info("receiptamt Amount===>"+receiptamt);
				logger.info("Total Amount===>"+totamt);
				//Changed for check only integer numbers by sathish on 26/09/12-start
				double convertedDiffAmount=Double.parseDouble(bean.getConvertedDiffAmount()==null?"0":bean.getConvertedDiffAmount().replaceAll(",", ""));
				double conreccur=totConRecCur+convertedDiffAmount;
				//conreccur = (int)(totConRecCur);
				double recamt = receiptamt;
				double payRecAmt=paymentamount;
				//if(totConRecCur!=receiptamt)
				//Changed for check only integer numbers by sathish on 26/09/12-End
				if(totConRecCur!=paymentamount){
					if("PT".equalsIgnoreCase(bean.getTransType())){
						//this.addActionError(getText("errors.payment.AmtEqualtotConPaidAmtCheck"));
					}else{
						//this.addActionError(getText("errors.payment.AmtEqualtotConRecAmtCheck"));
					}
				}else if(recamt!=totamt){
						if("PT".equalsIgnoreCase(bean.getTransType())){
							//this.addActionError(getText("errors.payment.AmountPaidCheck"));
						}else{
							//this.addActionError(getText("errors.payment.AmountCheck"));
						}
					}
				double x= Double.parseDouble(bean.getPaymentamount()==null?"0":bean.getPaymentamount().replaceAll(",", "") )-Double.parseDouble(bean.getTotalConRecCur()==null?"0":bean.getTotalConRecCur().replaceAll(",", ""));
				if(Math.abs(x)>=5){
					if("PT".equalsIgnoreCase(bean.getTransType())){
						//addActionError(getText("gross.amout.error.payment"));
					}
					else{
						//addActionError(getText("gross.amout.error.receipt"));
					}
				}
			}else{
				logger.info("Pay Rec No=>"+bean.getPay_rec_no());
				//Validation val=new Validation();
				if("".equals(val.isNull(bean.getPay_rec_no())) || null==bean.getPay_rec_no()){
					this.addActionError(getText("errors.payment.revNo"));
				}
			}
			if("EDIT".equalsIgnoreCase(bean.getFlag())){
                /*bean.setAmend_date(dropDownControllor.getAmend(bean));
                   bean.setMaxDate(Validation.getMaxDateValidate(bean.getTr_date(), bean.getAmend_date()));*/
		if("".equalsIgnoreCase(bean.getAmend_date())) {
			this.addActionError(getText("errors.payment.amend_date"));
		}
		else if(val.checkDate(bean.getAmend_date()).equalsIgnoreCase("INVALID")) {
			this.addActionError(getText("errors.payment.amend_dateInv"));
            }/*else  if ("Invalid".equalsIgnoreCase(validation.ValidateTwo(bean.getMaxDate(), bean.getAmend_date()))) {
                addActionError(getText("errors.endoDate.invalid"));
		}*/
		if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAmend_date()).equalsIgnoreCase("") ){
			if(new DropDownControllor().Validatethree(branchCode, bean.getAmend_date())==0){
				addActionError(getText("errors.open.period.date.trans.amend",new String[] {bean1.getOpenPeriodDate()}));
			}
			}
	}
			
			
		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
		}
		//return errors;
	}

	public String getExcRate(){
		try {
			//bean.setUsCurrencyRate(dropDownControllor.GetExchangeRate(bean.getOrginalCurrency(),bean.getIncepDate(),countryId,branchCode));
			bean.setExrate(dropDownControllor.GetExchangeRate(bean.getOrginalCurrency(),bean.getIncepDate(),countryId,branchCode));
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		return DROPDOWNAJAX;
	}
	private void validateRetroAllocateTrans() {
		final Validation val=new Validation();
		Double a=0.0,b=0.0,c=0.0;
		boolean check=true;
		bean.setAmend_date(dropDownControllor.getAmend(bean));
		if(StringUtils.isBlank(bean.getAmend_date())) {
			bean.setAmend_date(dropDownControllor.getTrans(bean));
		}
		if(StringUtils.isBlank(bean.getAccountDate())) {
			this.addActionError(getText("errors.payment.allocationDateReq"));

		}
		else if(val.checkDate(bean.getAccountDate()).equalsIgnoreCase("INVALID")) {
			this.addActionError(getText("error.allocationDate"));
		}
		else if(val.ValidateTwoDates(bean.getAmend_date(),bean.getAccountDate()).equalsIgnoreCase("INVALID")) {
			addActionError(getText("errors.payment.allocDateGretAlloDate"));
		}
		if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAccountDate()).equalsIgnoreCase("") ){
			if(new DropDownControllor().Validatethree(branchCode, bean.getAccountDate())==0){
				addActionError(getText("errors.open.period.date.allocate",new String[] {bean1.getOpenPeriodDate()}));
			}
			}
		
	}
	private void validateAllocateTrans() {
		final Validation val=new Validation();
		Double a=0.0,b=0.0,c=0.0;
		boolean check=true;
		bean.setAmend_date(dropDownControllor.getAmend(bean));
		if(StringUtils.isBlank(bean.getAmend_date())) {
			bean.setAmend_date(dropDownControllor.getTrans(bean));
		}
		if(StringUtils.isBlank(bean.getAccountDate())) {
			this.addActionError(getText("errors.payment.allocationDateReq"));

		}
		else if(val.checkDate(bean.getAccountDate()).equalsIgnoreCase("INVALID")) {
			this.addActionError(getText("error.allocationDate"));
		}
		else if(val.ValidateTwoDates(bean.getAmend_date(),bean.getAccountDate()).equalsIgnoreCase("INVALID")) {
			addActionError(getText("errors.payment.allocDateGretAlloDate"));
		}
		if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAccountDate()).equalsIgnoreCase("") ){
			if(new DropDownControllor().Validatethree(branchCode, bean.getAccountDate())==0){
				addActionError(getText("errors.open.period.date.allocate",new String[] {bean1.getOpenPeriodDate()}));
			}
			}
		/*if(val.checkDate(bean.getAccountDate()).equalsIgnoreCase("Invalid"))
		{
			this.addActionError(getText("error.allocationDate"));
		}*/
		List<TreasuryBean> payList=service.getAllTransContract(bean,branchCode);
		for(TreasuryBean Obj : payList)//int i=0;i<payList.size();i++
		{	
			
			TreasuryBean form = Obj;
			//if("Y".equalsIgnoreCase(form.getCheckYN()))
			if(receivePayAmountMap.containsKey(form.getTransactionno())) {
				check=false;
				if("P".equalsIgnoreCase(form.getCheckPC())){
					if(receivePayAmountMap.get(form.getTransactionno())==null || "".equalsIgnoreCase(receivePayAmountMap.get(form.getTransactionno())))
					 {			 
						 this.addActionError(getText("payment.accPremiumReq",new String[]{form.getTransactionno()}));
					 }else if(val.numbervalid((receivePayAmountMap.get(form.getTransactionno()))).equalsIgnoreCase("INVALID"))
					{
						 this.addActionError(getText("payment.accPremiumInv",new String[]{form.getTransactionno()}));
					}else
					{
						String sign1;
						String sign2;
						if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))<0)
							sign1="-";
						else
							sign1="+";
						if(Double.parseDouble(form.getNetdue())<0)
							sign2="-";
						else
							sign2="+";
						 if(!sign1.equals(sign2))
						 {
							 this.addActionError(getText("error.premiumAmount",new String[] {form.getTransactionno()}));
						 }else if("-".equals(sign2)&&"-".equals(sign1))
							{
								if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))*(-1)>Double.parseDouble(form.getNetdue())*(-1))
								{
									//this.addActionError(getText("error.claimAmountGreater",new String[] {form.getTransactionno()}));
								}else{
									a=a+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
								}
								a=a+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
							}else if("+".equals(sign2)&&"+".equals(sign1))
							{
								if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))>Double.parseDouble(form.getNetdue()))
								{
									//this.addActionError(getText("error.claimAmountGreater",new String[] {form.getTransactionno()}));
								}else{
									a=a+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
								}
								a=a+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
							}
					}
					}else if("C".equalsIgnoreCase(form.getCheckPC()))
					{
					logger.info("accClaim==>"+receivePayAmountMap.get(form.getTransactionno()));
					logger.info("payment==>"+form.getPayamount());
					if(receivePayAmountMap.get(form.getTransactionno())==null || "".equalsIgnoreCase(receivePayAmountMap.get(form.getTransactionno())))
					 {			 
						 this.addActionError(getText("payment.accClaimReq",new String[]{form.getTransactionno()}));
					 }else if(val.numbervalid((receivePayAmountMap.get(form.getTransactionno()))).equalsIgnoreCase("INVALID"))
					{
						 this.addActionError(getText("payment.accClaimInv",new String[]{form.getTransactionno()}));
					}else
					{
						String sign1;
						String sign2;
						if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))<0)
							sign1="-";
						else
							sign1="+";
						if(Double.parseDouble(form.getPayamount())<0)
							sign2="-";
						else
							sign2="+";
						if(!sign1.equals(sign2))
						{
							this.addActionError(getText("error.claimAmount",new String[] {form.getTransactionno()}));
						}else if("-".equals(sign2)&&"-".equals(sign1))
						{
							if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))*(-1)>Double.parseDouble(form.getPayamount())*(-1))
								{
									//this.addActionError(getText("error.claimAmountGreater",new String[] {form.getTransactionno()}));
								}else{
									b=b+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));	
								}
							b=b+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
						}else if("+".equals(sign2)&&"+".equals(sign1))
						{
							if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))>Double.parseDouble(form.getPayamount()))
							{
								//this.addActionError(getText("error.claimAmountGreater",new String[] {form.getTransactionno()}));
							}else{
								b=b+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));	
							}
							b=b+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
						}
					}
					
				}
				
			}else
			{
					if("P".equalsIgnoreCase(form.getCheckPC())){
						logger.info("Acc Premium===>"+receivePayAmountMap.get(form.getTransactionno()));
						if(receivePayAmountMap.get(form.getTransactionno())!=null && !"".equalsIgnoreCase(receivePayAmountMap.get(form.getTransactionno())))
						{
							this.addActionError(getText("error.check",new String[] {form.getTransactionno()}));
							if(val.numbervalid((receivePayAmountMap.get(form.getTransactionno()))).equalsIgnoreCase("INVALID"))
							{
								 this.addActionError(getText("payment.accPremiumInv",new String[]{form.getTransactionno()}));
							}else 
							{
								String sign1;
								String sign2;
								if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))<0)
									sign1="-";
								else
									sign1="+";
								if(Double.parseDouble(form.getNetdue())<0)
									sign2="-";
								else
									sign2="+";
								 if(!sign1.equals(sign2))
								{
									 this.addActionError(getText("error.premiumAmount",new String[] {form.getTransactionno()}));
								}else if("-".equals(sign2)&&"-".equals(sign1))
								{
									if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))*(-1)>Double.parseDouble(form.getNetdue())*(-1))
									{
										//this.addActionError(getText("error.claimAmountGreater",new String[] {form.getTransactionno()}));
									}
								}else if("+".equals(sign2)&&"+".equals(sign1))
								{
									if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))>Double.parseDouble(form.getNetdue()))
									{
										//this.addActionError(getText("error.claimAmountGreater",new String[] {form.getTransactionno()}));
									}
								}
								 
							}
						}
					}else if("C".equalsIgnoreCase(form.getCheckPC()))
					{
						logger.info("Acc Claim===>"+receivePayAmountMap.get(form.getTransactionno()));
						if(receivePayAmountMap.get(form.getTransactionno())!=null && !"".equalsIgnoreCase(receivePayAmountMap.get(form.getTransactionno())))
						{
							this.addActionError(getText("error.check",new String[] {form.getTransactionno()}));
							if(val.numbervalid((receivePayAmountMap.get(form.getTransactionno()))).equalsIgnoreCase("INVALID"))
							{
								 this.addActionError(getText("payment.accClaimInv",new String[]{form.getTransactionno()}));
							}else
							{
									String sign1;
									String sign2;
									if (Double.parseDouble(receivePayAmountMap.get(form.getTransactionno())) < 0)
										sign1 = "-";
									else
										sign1 = "+";
									if (Double.parseDouble(form.getPayamount()) < 0)
										sign2 = "-";
									else
										sign2 = "+";
									if(!sign1.equals(sign1))
									{
										this.addActionError(getText("error.claimAmount",new String[] {form.getTransactionno()}));
									}else if("-".equals(sign2)&&"-".equals(sign1))
									{
										if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))*(-1)>Double.parseDouble(form.getPayamount())*(-1))
											{
												//this.addActionError(getText("error.claimAmountGreater",new String[] {form.getTransactionno()}));
											}
									}else if("+".equals(sign2)&&"+".equals(sign1))
									{
										if(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))>Double.parseDouble(form.getPayamount()))
										{
											//this.addActionError(getText("error.claimAmountGreater",new String[] {form.getTransactionno()}));
										}										
									}
								}
						}
					
				}
				
			}
		}
		if(check) {
			this.addActionError(getText("error.one.check"));
		}
		if(Double.parseDouble(bean.getCurrencyValue())>=0){//condition added for -amount allocation for claim by sathish on 10/09/2012
			if(bean.getTransType().equalsIgnoreCase("RT"))
			{
			//c=a-b;
			c=Math.round((a-b) * 100.0) / 100.0;
			//c=a+b;
			logger.info("Sum of Accepted Premium A==>"+a);
			logger.info("Sum of Accepted Premium B==>"+b);
			logger.info("Sum of Accepted Premium A-B==>"+c);
			//if(b<0 || c<0 )
			if(c<0)
			{
				this.addActionError(getText("error.sumAmountInvalidPremium"));
			}
			}else if(bean.getTransType().equalsIgnoreCase("PT"))
			{
				//c=b-a;
				c=Math.round((b-a) * 100.0) / 100.0;
				//c=b+a;
				logger.info("Sum of Accepted Premium A==>"+a);
				logger.info("Sum of Accepted Premium B==>"+b);
				logger.info("Sum of Accepted Premium A-B==>"+c);
				//if(a<0 || c<0)
				if(c<0)
				{
					this.addActionError(getText("error.sumAmountInvalidCliam"));
				}
				
			}
			logger.info("Recipt Amount ==>"+bean.getCurrencyValue());
			if(c>Double.parseDouble(bean.getCurrencyValue()))
			{
				//this.addActionError(getText("error.receiptAmount"));
			}
		}
			
	/*	if("0".equalsIgnoreCase(request.getParameter("trans")))
		{
			this.addActionError("currency",getText("errors.payment.checkboxs"));
		}*/
		
	}

	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	
	public String directCeding() {
        bean.setDropDown("directCeding");
        String branchCode;
        try {
            branchCode = session.get("BRANCH_CODE").toString();
        } catch (Exception e) {
            branchCode = "";
        }
        List<Map<String, Object>> a = service.getDirectCeding(branchCode, null);
        StringBuilder sb = new StringBuilder();
        String delimiter = "";
        for (Map m : a) {
            logger.info(m.toString());
            sb.append(delimiter).append(m.get("ID")).append("~").append(m.get("NAME"));
            delimiter = "~";
        }
        bean.setName(sb.toString());
        return DROPDOWNAJAX;
    }
	
}
