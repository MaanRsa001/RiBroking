package com.maan.action;

import com.maan.bean.FaculitivesBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.CommonCalculation;
import com.maan.service.RdsCalculation;
import com.maan.service.RetroService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

public class RetroAction extends ActionSupport implements ModelDriven<RiskDetailsBean> {
	private static final long serialVersionUID = 1L;
	RiskDetailsBean bean =new RiskDetailsBean();
	FaculitivesBean bean1=new FaculitivesBean();
	RetroService service=new RetroService();
	DropDownControllor dropDownControllor= new DropDownControllor();
	Logger logger = LogUtil.getLogger(this.getClass());
	Map<String,Object> session = ActionContext.getContext().getSession();
	String pid = session.get("mfrid") == null ? "" :(String) session.get("mfrid");
	private String userId=session.get("UserId") == null ? "" :(String) session.get("UserId");
	private String processId=session.get("processId") == null ? "" :session.get("processId").toString();
	private String countryId=session.get("countryId") == null ? "" :session.get("countryId").toString();
	private String branchCode=session.get("BRANCH_CODE") == null ? "" :session.get("BRANCH_CODE").toString();
	private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
	private String attachedUW = session.get("ATTACHED_UW") ==null ?"":session.get("ATTACHED_UW").toString();
	private static final String DROPDOWNAJAX="dropdownajax";
	CommonCalculation calcu = new CommonCalculation();
	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public RiskDetailsBean getModel() {
		return bean;
	}
	
	public String ajaxValue(){bean.setSubProfit_centerlist(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));return DROPDOWNAJAX;}
	public List<Map<String,Object>> getEndosTypelist(){
		return dropDownControllor.getConstantDropDown("37");
	}
	public List<Map<String, Object>> getCommissionTypeList() {
		return dropDownControllor.getTypeList("28", branchCode);
	}
	public List<Map<String, Object>> getProfitCommissionList() {
		return dropDownControllor.getTypeList("34", branchCode);
	}
	public List<Map<String, Object>> getTypeYearList() {
		return dropDownControllor.getTypeList("35", branchCode);
	}
	public List<Map<String,Object>> getUnderwriterList(){
		return dropDownControllor.getPersonalInfoDropDown(branchCode,"L",pid);
	}
	public List<Map<String, Object>> getCessionList() {
		return dropDownControllor.getConstantDropDown("40");
	}
	public List<Map<String,Object>> getDocType(){return dropDownControllor.getDocType(branchCode,pid,"RDS");}

	public List<Map<String,Object>> getLOCIssuesedList(){
		return dropDownControllor.getTypeList("39",branchCode);
	}
	
	public List<Map<String, Object>> getCommissionList() {
		return dropDownControllor.getConstantDropDown("41");
	}
	
	public String  getExcRate(){
		try {
			Validation val = new Validation();
			bean.setBranchCode(branchCode);
			String date ="";
			if(StringUtils.isNotBlank(bean.getIncepDate()) && StringUtils.isNotBlank(bean.getAccDate())){
				boolean ins =val.checkDate(bean.getIncepDate()).equalsIgnoreCase("INVALID");
				boolean acc =val.checkDate(bean.getAccDate()).equalsIgnoreCase("INVALID");
				if(!ins&&!acc){
					date = Validation.getMinDateValidate(bean.getIncepDate(), bean.getAccDate());
				}
				if(StringUtils.isBlank(date) && !acc){
					date = bean.getAccDate();
				}else if(StringUtils.isBlank(date) && !ins){
					date = bean.getIncepDate();
				}
			}
			else if(StringUtils.isNotBlank(bean.getIncepDate()) && !val.checkDate(bean.getIncepDate()).equalsIgnoreCase("INVALID")){
				date = bean.getIncepDate();
			}
			else if(StringUtils.isNotBlank(bean.getAccDate()) &&val.checkDate(bean.getAccDate()).equalsIgnoreCase("INVALID")){
				date = bean.getAccDate();
			}
			if(!val.checkDate(date).equalsIgnoreCase("INVALID")){
			bean.setUsCurrencyRate(dropDownControllor.GetExchangeRate(bean.getOrginalCurrency(),date,countryId,bean.getBranchCode()));
			bean.setExchRate(dropDownControllor.GetExchangeRate(bean.getOrginalCurrency(),date,countryId,bean.getBranchCode()));
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return DROPDOWNAJAX;
	}

	public List<Map<String, Object>> getTerritoryRegionList() {
		return dropDownControllor.getTerritoryRegionList(branchCode);
	}
	public  String Init() {
		ShowDropDown();
		try {
			bean.setAmendId("0");
			bean.setMenuStatus("N");
			if(StringUtils.isNotBlank(bean.getProposal_no())){
			bean.setProposal_no("");
			}
			if (StringUtils.isBlank(bean.getCedRetenType())) {
				bean.setCedRetenType("A");
			}
			bean.setSubProfitList(new ArrayList<Map<String,Object>>());
			
			Map<String,Object> doubleMap1 = new HashMap<String,Object>();
			 List<Map<String,Object>> remarkList=new ArrayList<Map<String,Object>>();
			 doubleMap1.put("one",new Double(1.0));
			 remarkList.add(doubleMap1);
			 bean.setRemarkList(remarkList);
			 List<String> description= new ArrayList<String>();
			 List<String> remark1= new ArrayList<String>();
			 for(int i=0;i<remarkList.size();i++){
					description.add("Remarks");
					remark1.add(" ");
				}
			 bean.setRemarkCount(Integer.toString(remarkList.size()));
			 bean.setDescription(description);
			 bean.setRemark1(remark1);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "retro1";
	}

	public String FirstPageSaveMethod(){
		String forward = "retro1";
		try {
			bean.setProduct_id(pid);
			bean.setLoginId(userId);
			bean.setProcessId(session.get("processId").toString());
			bean.setBranchCode(branchCode);
			bean.setEpi(bean.getEpi().replaceAll(",", ""));
			bean.setLimitOrigCur(bean.getLimitOrigCur().replaceAll(",", ""));
			bean.setFaclimitOrigCur(bean.getFaclimitOrigCur().replaceAll(",", ""));
			//bean.setFaclimitOrigCurPml(bean.getFaclimitOrigCurPml().replaceAll(",", ""));
			bean.setCedReten(bean.getCedReten().replaceAll(",", ""));
			bean.setTreatyLimitsurplusOC(bean.getTreatyLimitsurplusOC().replaceAll(",", ""));
			//bean.setLimitOrigCurPml(bean.getLimitOrigCurPml().replaceAll(",", ""));
			bean.setFixedRate(bean.getFixedRate().replaceAll(",", ""));
			//bean.setTreatyLimitsurplusOCPml(bean.getTreatyLimitsurplusOCPml().replaceAll(",", ""));
			//bean.setEpipml(bean.getEpipml().replaceAll(",", ""));
			//bean.setXlCost(bean.getXlCost().replaceAll(",", ""));
			if(StringUtils.isNotBlank(bean.getDepartId())){
				session.put("DepartmentId",bean.getDepartId());
			}
			validatenext();
			if (!hasActionErrors()) {
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				final boolean SaveFlag = service.insertProportionalTreaty(bean,pid, true, false);
				if (SaveFlag) {
					bean.setStatus(bean.getContractGendration());
					if(StringUtils.isNotBlank(bean.getContNo())) {
						bean.setBackmode("Con");
					}
					else if ("P".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("Pro");
					}else if ("R".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("Reje");
					}else if ("A".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("Pro");
					}else if ("0".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("Pro");
					}else if ("N".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("NTU");
					}
					if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
						dropDownControllor.updateRenewalEditMode(bean.getProposal_no(),"N","");
					}
					dropDownControllor.updateEditMode(bean.getProposal_no(),"N","");
					forward="SucusssFac";
				} else {
					if(bean.getDepartId().equalsIgnoreCase("")||bean.getDepartId()==null){
						bean.setSubProfitList(new ArrayList<Map<String,Object>>());
					}else{
						bean.setSubProfitList(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
					}
					/*if(StringUtils.isNotBlank(bean.getIncepDate()))
						//bean.setIncepDate(dateFormat(bean.getIncepDate()));
					if(StringUtils.isNotBlank(bean.getAccDate()))
						//bean.setAccDate(dateFormat(bean.getAccDate()));
					if(StringUtils.isNotBlank(bean.getExpDate()))
						//bean.setExpDate(dateFormat(bean.getExpDate()));*/
					ShowDropDown();
					if (pid.equalsIgnoreCase("2") || pid.equalsIgnoreCase("4")) {
						addActionError(getText("error.insererror.error"));
					}
				}
			} else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if(bean.getDepartId().equalsIgnoreCase("")||bean.getDepartId()==null){
					bean.setSubProfitList(new ArrayList<Map<String,Object>>());
				}else{
					bean.setSubProfitList(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
				}
				resetRemarks();
				ShowDropDown();
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	
	public String ViewMethod(){
		String forward="";
		if(StringUtils.isBlank(bean.getBranchCode())){
		 bean.setBranchCode(branchCode);
		}
		if(StringUtils.isBlank(pid) || !"4".equalsIgnoreCase(pid)){
			pid="4";
		}
		bean.setShortname(service.getShortname(bean));
		try {
			if (StringUtils.isBlank(bean.getProposal_no())) {
				bean.setActionMessage((getText("error.proposalNoView.required")));
				//response.sendRedirect(request.getContextPath()+ "/PortfolioDispatchIntAction.do?method=Init&flag=true");
				bean.setFlag("true");
				forward="redirectAction";
			} else {
				final boolean CheckAvilable = service.checkAvialability(bean, pid);
				if (CheckAvilable) {
					if(StringUtils.isBlank(bean.getBranchCode())){
					 bean.setBranchCode(branchCode);
					}
					final boolean ViewFlag = service.viewRiskDetails(bean, pid);
					if (ViewFlag) {
						forward="retroView";
					} else {
						bean.setActionMessage((getText("error.proposalNoView.required")));
						//response.sendRedirect(request.getContextPath()+ "/menu.do?method=menu&manufactureID=" + pid);
						bean.setManufactureID(pid);
						forward="redirectAction1";
					}
				} else {
					bean.setActionMessage((getText("error.proposalNoView.required")));
					//response.sendRedirect(request.getContextPath()+ "/menu.do?method=menu&manufactureID=" + pid);
					bean.setManufactureID(pid);
					forward="redirectAction1";
				}
			}
				 service.showRetroCess(bean,3);
				 if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
						List<String> retroCessList1=new ArrayList<String>();
						for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
							retroCessList1.add(String.valueOf(i));
						}
						bean.setRetroCessList(retroCessList1);
					}
				 ShowDropDown();
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return forward;
	}
	
	public String check() {
		String forward="retro1";
		bean.setLay(bean.getLay1());
		bean.setContractno(bean.getContractno1());
		bean.setBranchCode(branchCode);
		bean.setProduct_id(pid);
		bean.setLoginId(userId);
		bean.setCountryID(bean.getCountryID());
		bean.setProcessId(processId);
		bean.setShortname(service.getShortname(bean));
		bean.setEpi(bean.getEpi().replaceAll(",", ""));
		bean.setLimitOrigCur(bean.getLimitOrigCur().replaceAll(",", ""));
		bean.setFaclimitOrigCur(bean.getFaclimitOrigCur().replaceAll(",", ""));
		bean.setCedReten(bean.getCedReten().replaceAll(",", ""));
		bean.setTreatyLimitsurplusOC(bean.getTreatyLimitsurplusOC().replaceAll(",", ""));
		bean.setFixedRate(bean.getFixedRate().replaceAll(",", ""));
		if(StringUtils.isNotBlank(bean.getDepartId())){
			session.put("DepartmentId",bean.getDepartId());
		}
		validatenext();
		try {
			if (!hasActionErrors()){
				session.put("Exchangerate", bean.getExchRate());
				RdsCalculation.SecondPageCaculation(bean,pid);
				bean.setLoginId(userId);
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
					List<String> retroCessList1=new ArrayList<String>();
					List<String> ceding = new ArrayList<String>();
					List<String> rBroker = new ArrayList<String>();
					List<String> pStatus = new ArrayList<String>();
					List<String> sWritt = new ArrayList<String>();
					List<String> sSigned = new ArrayList<String>();
					for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
						retroCessList1.add(String.valueOf(i));
						if(i==0){
							ceding.add(bean.getCedingCo());
							rBroker.add(bean.getBroker());
							pStatus.add(bean.getProStatus());
							sWritt.add(bean.getShareWritt().toString());
							sSigned.add(bean.getSharSign().toString());
						}
					}
					bean.setRetroCessList(retroCessList1);
					bean.setCedingCompany(ceding);
					bean.setRetroBroker(rBroker);
					bean.setProposalStatus(pStatus);
					bean.setShareAccepted(sWritt);
					bean.setShareSigned(sSigned);
				}
				final boolean SaveFlag = service.insertProportionalTreaty(bean,pid, false, false);
				if (SaveFlag){
					ShowDropDown();
					service.showSecondPageData1(bean.getProposal_no(), bean, pid);
					bean.setShare_Profit_Commission("2");
					if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
						dropDownControllor.updateRenewalEditMode(bean.getProposal_no(),"BRF",bean.getProposal_no());
						dropDownControllor.updateEditMode(bean.getProposal_no(),"BRF",bean.getProposal_no());
					}else{
					dropDownControllor.updateEditMode(bean.getProposal_no(),"BEF",bean.getProposal_no());
					}
					
					forward="retro2";
				} else {
					if(bean.getDepartId().equalsIgnoreCase("")||bean.getDepartId()==null){
						bean.setSubProfitList(new ArrayList<Map<String,Object>>());
					}else{
						bean.setSubProfitList(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
					}
					/*if(StringUtils.isNotBlank(bean.getIncepDate()))
						//bean.setIncepDate(dateFormat(bean.getIncepDate()));
					if(StringUtils.isNotBlank(bean.getAccDate()))
						//bean.setAccDate(dateFormat(bean.getAccDate()));
					if(StringUtils.isNotBlank(bean.getExpDate()))
						//bean.setExpDate(dateFormat(bean.getExpDate()));*/
					ShowDropDown();
					addActionError(getText("error.insererror.error"));
				}
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if(bean.getDepartId().equalsIgnoreCase("")||bean.getDepartId()==null){
					bean.setSubProfitList(new ArrayList<Map<String,Object>>());
				}else{
					bean.setSubProfitList(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
				}
				resetRemarks();
				ShowDropDown();
			}			
		} 
		catch (Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}
	
	public String AmednIdInsert(){
		String forward = "retro1";
		try {
			bean.setCedingId(bean.getCedingCo());
			bean.setBrokerId(bean.getBroker()); 
			bean.setAmend_Id_Mode("true");
			bean.setLoginId(userId);
			bean.setProcessId(processId);
			bean.setContNo(bean.getContractNo());
			bean.setEpi(bean.getEpi().replaceAll(",", ""));
			bean.setLimitOrigCur(bean.getLimitOrigCur().replaceAll(",", ""));
			bean.setFaclimitOrigCur(bean.getFaclimitOrigCur().replaceAll(",", ""));
			//bean.setFaclimitOrigCurPml(bean.getFaclimitOrigCurPml().replaceAll(",", ""));
			bean.setCedReten(bean.getCedReten().replaceAll(",", ""));
			bean.setTreatyLimitsurplusOC(bean.getTreatyLimitsurplusOC().replaceAll(",", ""));
			//bean.setLimitOrigCurPml(bean.getLimitOrigCurPml().replaceAll(",", ""));
			bean.setFixedRate(bean.getFixedRate().replaceAll(",", ""));
			//bean.setTreatyLimitsurplusOCPml(bean.getTreatyLimitsurplusOCPml().replaceAll(",", ""));
			//bean.setEpipml(bean.getEpipml().replaceAll(",", ""));
			//bean.setXlCost(bean.getXlCost().replaceAll(",", ""));
			if(StringUtils.isNotBlank(bean.getContractNo())){
			session.put("EditContractNo",bean.getContractNo());
			bean.setContNo(bean.getContractNo());
			}
			if(StringUtils.isBlank(bean.getContractNo())){
				bean.setContNo(session.get("EditContractNo").toString());
			}
			if(StringUtils.isNotBlank(bean.getDepartId())){
				session.put("DepartmentId",bean.getDepartId());
			}
			bean.setBranchCode(branchCode);
			validatenext();
			if (!hasActionErrors()) {
				bean.setAmend_Id_Mode("true");
				RdsCalculation.SecondPageCaculation(bean,pid);
				bean.setAmend_Id_Mode("true");
			 	bean.setLoginId(userId);
			 	bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				final boolean SaveFlag = service.insertProportionalTreaty(bean,pid, false, false);
				session.put("Exchangerate", bean.getExchRate());
				if (SaveFlag) {
					session.put("Exchangerate", bean.getExchRate());
					bean.setAmend_Id_Mode("true");
					bean.setProposal_no(bean.getProposal_no());
					service.showSecondPageData(bean.getProposal_no(), bean, pid);
					service.showRetroCess(bean,1);
					if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
						List<String> retroCessList1=new ArrayList<String>();
						for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
							retroCessList1.add(String.valueOf(i));
						}
						bean.setRetroCessList(retroCessList1);
					}
					ShowDropDown();
					if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
						dropDownControllor.updateRenewalEditMode(bean.getProposal_no(),"BRF",bean.getProposal_no());
						dropDownControllor.updateEditMode(bean.getProposal_no(),"BRF",bean.getProposal_no());
					}else{
					dropDownControllor.updateEditMode(bean.getProposal_no(),"BEF",bean.getProposal_no());
					}
					
					forward="retro2";
				} else {
					RdsCalculation.SecondPageCaculation(bean,pid);
					session.put("Product", pid);
					addActionError(getText("error.insererror.error"));
					session.put("Exchangerate", bean.getExchRate());
					bean.setAmend_Id_Mode("true");
				}
			} else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if(StringUtils.isBlank(bean.getDepartId())){
					bean.setSubProfitList(new ArrayList<Map<String,Object>>());
				}else{
					bean.setSubProfitList(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
				}
				ShowDropDown();
				resetRemarks();
				session.put("Exchangerate", bean.getExchRate());
				bean.setAmend_Id_Mode("true");
			}		 
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}
	
	public String SecondPageSave(){
		String forward="";
		try {
			final String ProductId = (String) session.get("Product");
			bean.setBranchCode(branchCode);
			bean.setLoginId(userId);
			validateSecondPage();
			final int CheckEditMode = service.getEditMode(bean.getProposal_no());
			if (CheckEditMode == 2) {
				bean.setAmend_Id_Mode("true");
			}
			if (!hasActionErrors()) {
				RdsCalculation.SecondPageCaculation(bean,ProductId);
				bean.setExchRate((String) session.get("Exchangerate"));
				service.saveSecondPage(bean, ProductId);
				bean.setStatus(bean.getContractGendration());
				if (StringUtils.isNotBlank(bean.getContNo())) {
					bean.setBackmode("Con");
				}else if ("P".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Pro");
				}else if ("R".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Reje");
				}else if ("A".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Pro");
				}else if ("N".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("NTU");
				}
				bean.setMode("");
				if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
					dropDownControllor.updateRenewalEditMode(bean.getProposal_no(),"N","");
				}
				dropDownControllor.updateEditMode(bean.getProposal_no(),"N","");
				forward="SucusssFac";
			} else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setProposal_no(bean.getProposal_no());
				if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
					List<String> retroCessList1=new ArrayList<String>();
					for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
						retroCessList1.add(String.valueOf(i));
					}
					bean.setRetroCessList(retroCessList1);
				}
				ShowDropDown();
				resetRemarks();
				service.showSecondPageData(bean.getProposal_no(), bean, ProductId);
				service.showRetroCess(bean,2);
				if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0) {
					List<Integer> docList = new ArrayList<Integer>();
					for (int i = 0; i < bean.getDocId().size(); i++) {
						docList.add(i);
					}

					bean.setDocuList(docList);
				}
				forward = "retro2";
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	public String RiskSecondPage(){
		String forward="";
		try {
			/*if(StringUtils.isNotBlank(session.get("EditContractNo")==null?"":session.get("EditContractNo").toString())){
				bean.setContNo(session.get("EditContractNo").toString());
			}*/
			final String ProductId = (String) session.get("Product");
			bean.setBranchCode(branchCode);
			bean.setLoginId(userId);
			validateSecondPage();
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && "Y".equalsIgnoreCase(bean.getDocStatus())) {
				validateUploadDocument();
			}
			if (!hasActionErrors()) {
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				bean.setExchRate((session.get("Exchangerate").toString()));
				if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && "Y".equalsIgnoreCase(bean.getDocStatus())) {
					bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
					String result=service.doUpload(bean,branchCode,userId,bean.getUpload(),bean.getUploadFileName());
				}
				service.saveRiskDeatilsSecondForm(bean,ProductId);
				bean.setContractGendration(bean.getContractGendration());
				if (StringUtils.isNotBlank(bean.getContNo())) {
					bean.setBackmode("Con");
				}else if ("P".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Pro");
				}else if ("N".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("NTU");
				}else if ("R".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Reje");
				}
				bean.setStatus(bean.getContractGendration());
				bean.setMode("");
				if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
					dropDownControllor.updateRenewalEditMode(bean.getProposal_no(),"N","");
				}
				dropDownControllor.updateEditMode(bean.getProposal_no(),"N","");
				forward="SucusssFac";
			} else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
					List<String> retroCessList1=new ArrayList<String>();
					for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
						retroCessList1.add(String.valueOf(i));
					}
					bean.setRetroCessList(retroCessList1);
				}
				ShowDropDown();
				resetRemarks();
				if(StringUtils.isNotBlank(bean.getM_d_InstalmentNumber()) && Integer.parseInt(bean.getM_d_InstalmentNumber())>0){
					List<String> instalList=new ArrayList<String>();
					for(int i=0;i<Integer.parseInt(bean.getM_d_InstalmentNumber());i++){
						instalList.add(String.valueOf(i));
					}
					bean.setInstalList(instalList);
				}
				service.showSecondPageData(bean.getProposal_no(), bean, ProductId);
				if("5".equalsIgnoreCase(ProductId)){
					for(int i=0;i<Integer.parseInt(bean.getM_d_InstalmentNumber());i++){
						if(StringUtils.isNotBlank(bean.getInstalmentDateList().get(i))){
							bean.getInstalmentDateList().set(i,dateFormat(bean.getInstalmentDateList().get(i)));
						}
					}
				}
				forward = "retro2";
				if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0){
					List<Integer> docList=new ArrayList<Integer>();
					for(int i=0;i<bean.getDocId().size();i++){
						docList.add(i);
					}
					bean.setDocuList(docList);
				}
			}
			if(StringUtils.isNotBlank(bean.getLayerProposalNo())){
				service.showLayerBrokerage(bean.getLayerProposalNo(),bean)	;
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	public String FirstPageUpdateMode(){
		String forward = "retro1";
		try {
			bean.setMenuStatus("N");
			bean.setCedingId(bean.getCedingCo());
			bean.setBrokerId(bean.getBroker()); 
			final String pid = session.get("Product").toString();
			bean.setBranchCode(branchCode);
			final int CheckEditMode = service.getEditMode(bean.getProposal_no());
			if (CheckEditMode == 2 && !"Renewal".equalsIgnoreCase(bean.getReMode()) ) {
				bean.setAmend_Id_Mode("true");
			}
			bean.setLoginId(userId);
			bean.setProcessId(session.get("processId").toString());
			bean.setShortname(service.getShortname(bean));
			bean.setEpi(bean.getEpi().replaceAll(",", ""));
			bean.setLimitOrigCur(bean.getLimitOrigCur().replaceAll(",", ""));
			bean.setFaclimitOrigCur(bean.getFaclimitOrigCur().replaceAll(",", ""));
			//bean.setFaclimitOrigCurPml(bean.getFaclimitOrigCurPml().replaceAll(",", ""));
			bean.setCedReten(bean.getCedReten().replaceAll(",", ""));
			bean.setTreatyLimitsurplusOC(bean.getTreatyLimitsurplusOC().replaceAll(",", ""));
			//bean.setLimitOrigCurPml(bean.getLimitOrigCurPml().replaceAll(",", ""));
			bean.setFixedRate(bean.getFixedRate().replaceAll(",", ""));
			//bean.setTreatyLimitsurplusOCPml(bean.getTreatyLimitsurplusOCPml().replaceAll(",", ""));
			//bean.setEpipml(bean.getEpipml().replaceAll(",", ""));
			//bean.setXlCost(bean.getXlCost().replaceAll(",", ""));
			if(StringUtils.isNotBlank(bean.getDepartId())){
				session.put("DepartmentId",bean.getDepartId());
			}
			validatenext();
			if (!hasActionErrors()) {
				RdsCalculation.SecondPageCaculation(bean,pid);
				final boolean SaveFlag = service.updateProportionalTreaty(bean,pid);
				if(SaveFlag){
					session.put("Exchangerate", bean.getExchRate());
					bean.setEditMode("EditMode");	
					service.showSecondPageData(bean.getProposal_no(), bean, pid);
					service.showRetroCess(bean,1);
					if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
						List<String> retroCessList1=new ArrayList<String>();
						for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
							retroCessList1.add(String.valueOf(i));
						}
						bean.setRetroCessList(retroCessList1);
					}
					RdsCalculation.SecondPageCaculation(bean,pid);
					if(bean.getProStatus().equalsIgnoreCase("R")){
						bean.setBackmode("Reje");
						bean.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+bean.getProposal_no());
						bean.setStatus(bean.getContractGendration());
						if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
							dropDownControllor.updateRenewalEditMode(bean.getProposal_no(),"N","");
						}
						dropDownControllor.updateEditMode(bean.getProposal_no(),"N","");
						forward ="SucusssFac";
					}else{
						if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0) {
							List<Integer> docList = new ArrayList<Integer>();
							for (int i = 0; i < 1; i++) {
								docList.add(i);
							}
							bean.setStartIndex("0");
							bean.setEndIndex("1");
							bean.setDocuList(docList);
						}
						if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
							dropDownControllor.updateRenewalEditMode(bean.getProposal_no(),"BRF",bean.getProposal_no());
							dropDownControllor.updateEditMode(bean.getProposal_no(),"BRF",bean.getProposal_no());
						}else{
						dropDownControllor.updateEditMode(bean.getProposal_no(),"BEF",bean.getProposal_no());
						}
						forward ="retro2";
					}
				}else{
					if(bean.getDepartId().equalsIgnoreCase("")||bean.getDepartId()==null){
						bean.setSubProfitList(new ArrayList<Map<String,Object>>());
					}else{
						bean.setSubProfitList(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
					}
					/*if(StringUtils.isNotBlank(bean.getIncepDate()))
						bean.setIncepDate(dateFormat(bean.getIncepDate()));
					if(StringUtils.isNotBlank(bean.getAccDate()))
						bean.setAccDate(dateFormat(bean.getAccDate()));
					if(StringUtils.isNotBlank(bean.getExpDate()))
						bean.setExpDate(dateFormat(bean.getExpDate()));*/
					ShowDropDown();
					if(pid.equalsIgnoreCase("5")){
						addActionError(getText("error.add.layerNo.NotExist"));	
					}
				}
			} else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if(bean.getDepartId().equalsIgnoreCase("")||bean.getDepartId()==null){
					bean.setSubProfitList(new ArrayList<Map<String,Object>>());
				}else{
					bean.setSubProfitList(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
				}
				/*if(StringUtils.isNotBlank(bean.getIncepDate()))
					bean.setIncepDate(dateFormat(bean.getIncepDate()));
				if(StringUtils.isNotBlank(bean.getAccDate()))
					bean.setAccDate(dateFormat(bean.getAccDate()));
				if(StringUtils.isNotBlank(bean.getExpDate()))
					bean.setExpDate(dateFormat(bean.getExpDate()));*/
				ShowDropDown();
				resetRemarks();
			}
			if(bean.getLayerProposalNo()!=null){
				service.showLayerBrokerage(bean.getLayerProposalNo(),bean)	;
			}
			ShowDropDown();
			resetRemarks();
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return forward;
	}

	public String EditMode(){
		ShowDropDown();
		String forward = "retro1";
		String editMode ="N";
		try {
			if( !"edit".equalsIgnoreCase(bean.getMultiuserMode()) && StringUtils.isNotBlank(bean.getProposal_no())){
			 editMode = dropDownControllor.EditModeStatus(bean.getProposal_no(),"0");
		}
		if(!"N".equalsIgnoreCase(editMode)){
			forward ="portfoliList";	
			bean.setMultiuserError("error");
		}
		else{
			
			bean.setBranchCode(branchCode);
			bean.setMenuStatus("N");
			bean.setProposalNo1(bean.getProposal_no());
			//if(StringUtils.isBlank(bean.getMode())|| ! "Renewal".equalsIgnoreCase(bean.getMode())){
			if(StringUtils.isBlank(bean.getMode())|| "edit".equalsIgnoreCase(bean.getMode())) {
				if(StringUtils.isNotBlank(bean.getContractno())){
					bean.setContNo(bean.getContractno());
				}
				/*if(session.get("EditContractNo")!=null) {
					bean.setContNo(session.get("EditContractNo").toString());
				}*/
				final int CheckEditMode = service.getEditMode(bean.getProposal_no());
				if (CheckEditMode == 2) {
					bean.setAmend_Id_Mode("true");
				}
			}
			else if("endorsment".equalsIgnoreCase(bean.getEndtMode())) {
				dropDownControllor.riskDetailsEndorsement(bean.getProposal_no(),bean.getEndorsementStatus());
				
				if(StringUtils.isNotBlank(bean.getContractno())) {
					bean.setContNo(bean.getContractno());
				}
				/*if(session.get("EditContractNo")!=null) {
					bean.setContNo(session.get("EditContractNo").toString());
				}*/
				final int CheckEditMode = service.getEditMode(bean.getProposal_no());
				if (CheckEditMode == 2) {
					bean.setAmend_Id_Mode("true");
				}
			}
			//else{
			else if("Renewal".equalsIgnoreCase(bean.getReMode())) {
				bean.setRenewalProposalNo(bean.getProposal_no());
				bean.setRetroType(service.PreviouRetroTypeChect(bean));
				bean.setProposal_no(dropDownControllor.getRenewalCopyQuote("Renewal","SR".equalsIgnoreCase(bean.getRetroType()) ? "5" : "4", bean.getBranchCode(),bean.getProposal_no() ));
				bean.setRenewalMode("RenewalMode");
				bean.setProposalReference("Renewal");
			}
			bean.setProduct_id(pid);
			service.riskEditMode(bean, false);
			bean.setYearList(getYearList());
			bean.setSubProfitList(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
			RdsCalculation.SecondPageCaculation(bean,pid);
			if(StringUtils.isNotBlank(bean.getReMode())&& "Renewal".equalsIgnoreCase(bean.getReMode())){
				bean.setContNo("");
				//bean.setFlag("renewal");
				bean.setFlagTest("renewal");
			}
			if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
				dropDownControllor.updateRenewalEditMode(bean.getProposal_no(),"BR",bean.getProposal_no());
				dropDownControllor.updateEditMode(bean.getProposal_no(),"BR",bean.getProposal_no());
			}else{
			dropDownControllor.updateEditMode(bean.getProposal_no(),"BE",bean.getProposal_no());
			}
		}
		if(StringUtils.isNotBlank(bean.getContractno())){
			bean.setProdisableStatus("Y");
		}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}
	
	public String submitSecondPageEndorsement() {
		String forward = "";
		try {
			if("Y".equalsIgnoreCase(bean.getEndorsementStatus())) {
				forward = RiskSecondPage();
			}
			else {
				forward = SecondPageSave();
				logger.info("Enter");
				dropDownControllor.riskDetailsEndorsement(bean.getProposal_no(),bean.getEndorsementStatus());
				logger.info("Exit");
			}
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return forward;
	}
	
	private List<Map<String,Object>> getYearList(){
		List<Map<String,Object>> yearsList=new ArrayList<Map<String,Object>>();
		/*Calendar cal=Calendar.getInstance(); 
		for(int j=(cal.get(Calendar.YEAR))-4;j<=(cal.get(Calendar.YEAR))+1;j++){
			Map<String,Object> year=new HashMap<String, Object>();
			year.put("YEAR", j);
			yearsList.add(year);
		}*/
		Validation val = new Validation();
		if(StringUtils.isNotBlank(bean.getIncepDate()) && !val.checkDate(bean.getIncepDate()).equalsIgnoreCase("INVALID")){
		yearsList = dropDownControllor.getYearListValue(bean.getIncepDate());
		}
		return yearsList;
	}
	private List<Map<String,Object>> getRetroYearList(){
		List<Map<String,Object>> yearsList=new ArrayList<Map<String,Object>>();
		Calendar cal=Calendar.getInstance(); 
		for(int j=(cal.get(Calendar.YEAR))-5;j<=(cal.get(Calendar.YEAR))+1;j++){
			Map<String,Object> year=new HashMap<String, Object>();
			year.put("YEAR", j);
			yearsList.add(year);
		}
		return yearsList;
	}

	private void ShowDropDown() {
		bean.setYearList(getYearList());
	
		bean.setProfit_Centerlist(dropDownControllor.getProfitCentreDropDown(branchCode));
		//bean.setSubProfit_centerlist(dropDownControllor.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
		bean.setDepartIdlist(dropDownControllor.getDepartmentDropDown(branchCode,pid,"Y",bean.getContractno1(),bean.getEndtMode(),bean.getProposalNo1(),bean.getMode(),bean.getBaseLayer()));
		bean.setUnderWritterlist(dropDownControllor.getUnderWritterDropDown(branchCode,attachedUW));
		bean.setDepartIdlist(dropDownControllor.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
		bean.setPolBrlist(dropDownControllor.getPolicyBranchDropDown(branchCode));
		bean.setProposallist(dropDownControllor.getConstantDropDown("1"));
		bean.setAccontPeriodlist(dropDownControllor.getConstantDropDown("5"));
		bean.setPNOCDayslist(dropDownControllor.getConstantDropDown("3"));
		bean.setBrokerlist(dropDownControllor.getPersonalInfoDropDown(branchCode,"B",pid));
		bean.setBasislist(dropDownControllor.getConstantDropDown("6"));
		bean.setProposaltypelist(dropDownControllor.getConstantDropDown("4"));
		bean.setOrginalCurrencylist(dropDownControllor.getCurrencyMasterDropDown(branchCode, countryId));
		bean.setTerritortylist(dropDownControllor.getTerritoryDropDown(branchCode));
		bean.setCeddingCompanylist(dropDownControllor.getPersonalInfoDropDown(branchCode,"C",pid));
		bean.setRetroTypelist(dropDownControllor.getConstantDropDown("8"));
		bean.setTreatyTypelist(dropDownControllor.getConstantDropDown("25"));
		if("RI02".equalsIgnoreCase(sourceId)){
			bean.setPerilCoveredlist(dropDownControllor.getConstantDropDown("30"));
		}
		bean.setTerritortylist(dropDownControllor.getTerritoryDropDown(branchCode));
		session.put("Product", pid);
	}

	public void validatesave(){
		final Validation val = new Validation();
		try {
			boolean errorFlag = false;
			if (StringUtils.isBlank(bean.getProfit_Center())) {
				addActionError(getText("error.Profit_Center.required"));
			}
			if (StringUtils.isBlank(val.isNull(bean.getUnderwriter()))) {
				addActionError(getText("error.underwriter.required"));
			}
			if (StringUtils.isBlank(bean.getSubProfit_center())) { 
				addActionError(getText("error.subProfit_center.required"));
			}else{
				bean.setSubProfit_center((bean.getSubProfit_center()).replaceAll(" ", ""));
			}
			if (StringUtils.isBlank(bean.getPolBr())) {
				addActionError(getText("error.polBr.required"));
			}
			if (StringUtils.isBlank(bean.getCedingCo())) {
				addActionError(getText("error.retroSessionLeader.required"));
			}
			if (StringUtils.isBlank(bean.getBroker())) {
				addActionError(getText("error.broker.required"));
			}
			if ("4".equals(pid) && StringUtils.isBlank((bean.getRetroType()))) {
				addActionError(getText("error.retroType.required.retro"));
			} else if ("4".equals(pid) && "SR".equalsIgnoreCase(bean.getRetroType()) && StringUtils.isBlank(bean.getInsuredName())) {
				addActionError(getText("errors.insuredName.required"));
			}
			if (!"4".equals(pid)) {
				if (StringUtils.isBlank(val.isSelect(bean.getTerritory()))) {
					addActionError(getText("error.terrtory.required"));
				}
			}
			if (StringUtils.isNotBlank(bean.getIncepDate())) {
				errorFlag = true;
				if ("INVALID".equalsIgnoreCase(bean.getIncepDate())) {
					addActionError(getText("error.incepDate.check"));
				}
			}
			if (StringUtils.isNotBlank(bean.getExpDate())) {
				errorFlag = true;
				if ("INVALID".equalsIgnoreCase(bean.getExpDate())) {
					addActionError(getText("error.expdate.check"));
				}
			}
			/*if (!bean.getIncepDate().equalsIgnoreCase("") && !bean.getAccDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getIncepDate(), bean.getAccDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check"));
				}
			}*/
			if (!bean.getExpDate().equalsIgnoreCase("") && !bean.getAccDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getAccDate(), bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check"));
				}
			}
			if (StringUtils.isBlank(bean.getExchRate())) {
				addActionError(getText( "errors.exchange.required"));
			}
			if ("4".equalsIgnoreCase(pid)) {
				/*if (StringUtils.isNotBlank(bean.getXlCost())) {
					errorFlag = true;
					bean.setXlCost((bean.getXlCost()).replaceAll(",", ""));
					if (val.isValidNo(bean.getXlCost().trim()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.xlCost.check"));
					}
				}*/
				if (StringUtils.isNotBlank(bean.getEpi_origCur())) {
					errorFlag = true;
					bean.setEpi_origCur((bean.getEpi_origCur()).replaceAll(",", ""));
					if (val.isValidNo(bean.getEpi_origCur().trim()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.epiperCent.check"));
					}
				}
				if (StringUtils.isNotBlank(bean.getReceiptofStatements())) {
					errorFlag = true;
					if (val.isValidNo(bean.getReceiptofStatements()).trim().equalsIgnoreCase("Invalid")) {
						addActionError(getText("error.ReceiptofStatements.required"));
					}
				}
				if (StringUtils.isNotBlank(bean.getReceiptofPayment())) {
					errorFlag = true;
					if (val.isValidNo(bean.getReceiptofPayment()).trim().equalsIgnoreCase("Invalid")) {
						addActionError(getText("error.ReceiptofPayment.required"));
					}
				}
				if (!"-1".equalsIgnoreCase(bean.getPnoc())) {
					errorFlag = true;
				}
				if (StringUtils.isNotEmpty(bean.getRiskCovered())) {
					errorFlag = true;
				}
				if (StringUtils.isNotEmpty(bean.getTerritoryscope())) {
					errorFlag = true;
				}
			}else if ("5".equalsIgnoreCase(pid)) {
				if (StringUtils.isNotBlank(bean.getPortfoloCovered())) {
					errorFlag = true;
				}
				if (StringUtils.isNotBlank(bean.getBasis())) {
					errorFlag = true;
				}
				if (StringUtils.isNotBlank(bean.getSubPremium())) {
					errorFlag = true;
					bean.setSubPremium((bean.getSubPremium()).replaceAll(",", ""));
					if (val.isValidNo(bean.getSubPremium().trim()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.SubPremium.check"));
					}
				}
				if (StringUtils.isNotBlank(bean.getDeduc_hunPercent())) {
					errorFlag = true;
					bean.setDeduc_hunPercent((bean.getDeduc_hunPercent()).replaceAll(",", ""));
					if (val.isValidNo(bean.getDeduc_hunPercent().trim()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.deduc.check"));
					}
				}
				if (StringUtils.isNotBlank(bean.getM_dPremium())) {
					errorFlag = true;
					bean.setM_dPremium((bean.getM_dPremium()).replaceAll(",", ""));
					if (val.isValidNo(bean.getM_dPremium().trim()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.mdpremium.check"));
					}
				}
				if (StringUtils.isNotBlank(bean.getAdjRate())) {
					errorFlag = true;
					if (val.percentageValid(bean.getAdjRate().trim()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.adjrate.check"));
					}
					else if (val.percentageValid(bean.getAdjRate().trim()).equalsIgnoreCase("less")) {
						addActionError(getText("error.adjrate.checkless"));
					} else if (val.percentageValid(bean.getAdjRate().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.adjrate.checkgreater"));
					}
				}
				if (StringUtils.isBlank(val.isNull(bean.getLayerNo()))){
					addActionError(getText("error.layerNo.required"));
				}
				if (StringUtils.isNotBlank(val.isNull(bean.getM_d_InstalmentNumber()))) {
					errorFlag = true;
					if (val.isValidNo(bean.getM_d_InstalmentNumber()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.Instalment.error"));
					}
				}
				if (StringUtils.isNotBlank(val.isNull(bean.getLayerNo()))) {
					if (service.getLayerDuplicationCheck(bean)) {
						addActionError(getText("error.layer.duplicate"));
					}
				}
			}
			if (StringUtils.isNotBlank(bean.getExchRate())) {
				errorFlag = true;
				if (val.isValidNo(bean.getExchRate().trim().toString()).equalsIgnoreCase("invalid")) {
					addActionError(getText("error.exchRate.check"));
				}
			}
			if (StringUtils.isNotBlank(bean.getLimitOrigCur())) {
				errorFlag = true;
				bean.setLimitOrigCur((bean.getLimitOrigCur()).replaceAll(",", ""));
				if (val.isValidNo(bean.getLimitOrigCur()).equalsIgnoreCase("invalid")) {
					addActionError(getText("error.retroPolicyLimitOC.invalid"));
				}
			}

			if (StringUtils.isBlank(bean.getEpi())) {
					addActionError(getText("error.epiperCent.required"));
			}
			if ("5".equalsIgnoreCase(pid)) {
				if (StringUtils.isNotBlank(bean.getLimitOrigCur())) {
					errorFlag = true;
					bean.setLimitOrigCur((bean.getLimitOrigCur()).replaceAll(",", ""));
					if (val.isValidNo(bean.getLimitOrigCur()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.limit.check"));
					}
				}
			}
			if (!StringUtils.isBlank(bean.getShareWritt())) {
				errorFlag = true;
				if (val.percentageNewValid(bean.getShareWritt().trim()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.retorWrit.invalid"));
				} 
			}
			if (!bean.getProfit_Center().equalsIgnoreCase("0")) {
				errorFlag = true;
			}
			if (StringUtils.isNotBlank(bean.getIncepDate()) && StringUtils.isNotBlank(bean.getExpDate())) {
				if (val.ValidateTwoDates(bean.getIncepDate(),	bean.getExpDate()).trim().equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.DateRange.Required"));
				}
			}
			/*if (!bean.getIncepDate().equalsIgnoreCase("") && !bean.getAccDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getIncepDate(), bean.getAccDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check"));
				}
			}*/
			if (!bean.getExpDate().equalsIgnoreCase("") && !bean.getAccDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getAccDate(), bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check"));
				}
			}
			if (StringUtils.isBlank(bean.getProStatus())) {
				addActionError(getText("error.proStatus.required"));
			}
			if (!bean.getSubProfit_center().equalsIgnoreCase("0")) {
				errorFlag = true;
			}
			if (!bean.getUwYear().equalsIgnoreCase("0")) {
				errorFlag = true;
			}
			if (!bean.getUnderwriter().equalsIgnoreCase("0")) {
				errorFlag = true;
			}
			if (!bean.getDepartId().equalsIgnoreCase("0")) {
				errorFlag = true;
			}
			if (!bean.getPolBr().equalsIgnoreCase("0")) {
				errorFlag = true;
			}
			if (StringUtils.isNotBlank(bean.getTreatyName_type())) {
				errorFlag = true;
			}
			if (!bean.getBroker().equalsIgnoreCase("0")) {
				errorFlag = true;
			}
			if (!bean.getOrginalCurrency().equalsIgnoreCase("0")) {
				errorFlag = true;
			}
			if (errorFlag == false) {
				addActionError(getText("error.GendralSaveError.required"));
			}
			bean.setContNo(bean.getContNo().replaceAll(",", ""));
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}
	private void validatenext() {
		try {
			boolean flags = true;
			final Validation val = new Validation();
			final String tear_nt = val.isNull(bean.getTreatyName_type());
			final String brok = val.isSelect(bean.getBroker());
			final String incDate = val.checkDate(bean.getIncepDate());
			final String expdate = val.checkDate(bean.getExpDate());
			final String accDate = val.checkDate(bean.getAccDate());
			final String exchRate = val.isNull(bean.getExchRate());
			final String riskCover = val.isNull(bean.getRiskCovered());
			final String terrtyscope = val.isNull(bean.getTerritoryscope());
			final String limitPercent = val.isNull(bean.getLimitOrigCur());
			final String epiPercent = val.isNull(bean.getEpi_origCur());
			final String Epi = val.isNull(bean.getEpi());
			final String xlCost = val.isNull(bean.getXlCost());
			final String proStatus = val.isSelect(bean.getProStatus());
			final String shareWrit = val.isNull(bean.getShareWritt());
			final String shareSign = val.isNull(bean.getSharSign());
			final String orginalCurrency = val.isSelect(bean.getOrginalCurrency());
			if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0){
				if(StringUtils.isBlank(bean.getEndorsmenttype())){
					addActionError(getText("end.type.error"));
				}
			}
			if (StringUtils.isBlank(val.isSelect(bean.getDepartId()))) {
				addActionError(getText("error.departId.required"));
			}
			if (StringUtils.isBlank(val.isSelect(bean.getSubProfit_center()))) {
				addActionError(getText("error.subProfit_center.required"));
			}else{
				bean.setSubProfit_center((bean.getSubProfit_center()).replaceAll(" ", ""));
			}
			if (StringUtils.isBlank(val.isSelect(bean.getProfit_Center()))) {
				addActionError(getText("error.Profit_Center.required"));
			}

			Map<String, Object> map = null;
			List<Map<String, Object>> list = service.getValidation(bean);
			if (list != null && list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			}
			/*if (StringUtils.isBlank(val.isSelect(bean.getUwYear()))) {
				addActionError(getText("error.uwYear.required"));
			} else if (!"".equals(bean.getRenewal_contract_no()) && !"0".equals(bean.getRenewal_contract_no()) && map != null && Integer.parseInt((String) map.get("UW_YEAR")) >= Integer.parseInt(bean.getUwYear())) {
				addActionError(getText("errors.year.invalid"));
			}*/
			if (StringUtils.isBlank(val.isNull(bean.getUnderwriter()))) {
				addActionError(getText("error.underwriter.required"));
			}
			if (StringUtils.isBlank(val.isNull(bean.getPolBr()))) {
				addActionError(getText("error.polBr.required"));
			}
			if (StringUtils.isBlank(val.isSelect(bean.getCedingCo()))) {
				addActionError(getText("error.leadRetrocessionaire.required"));
			}
			if (StringUtils.isBlank(brok)) {
				addActionError(getText("error.leadRetroBroker.required"));
			}
			if (StringUtils.isBlank(bean.getDummyCon())) {
				addActionError(getText("error.dummy.required"));
			}
			if ("4".equals(pid) && StringUtils.isBlank((bean.getRetroType()))) {
				addActionError(getText("error.retroType.required.retro"));
			}
			if ("4".equals(pid) && "SR".equalsIgnoreCase(bean.getRetroType()) && StringUtils.isBlank(bean.getInsuredName())) {
				addActionError(getText("errors.insuredName.required"));
			}
			if("TR".equalsIgnoreCase(bean.getRetroType())){
				if (StringUtils.isBlank(val.isSelect(bean.getTreatyType()))) {
					addActionError(getText("error.retroTreatyType.Reqired"));
				}
			}
			if("TR".equalsIgnoreCase(bean.getRetroType()) || "FO".equalsIgnoreCase(bean.getRetroType())){
				if (StringUtils.isBlank(tear_nt)) {
					addActionError(getText("error.retroTreatyName.required"));
				}
			}
			/*if (StringUtils.isBlank(bean.getRetroType())) {
				addActionError(getText("error.retroType.required"));
			} else if ("SR".equalsIgnoreCase(bean.getRetroType())&& "".equals(bean.getInsuredName())) {
				addActionError(getText("errors.insuredName.required"));
			}*/

			if (StringUtils.isBlank(val.isNull(bean.getIncepDate()))) {
				addActionError(getText("error.incepDate.required"));
			} else if (incDate.equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.incepDate.check"));
			} else if (!"".equals(bean.getRenewal_contract_no())&& !"0".equals(bean.getRenewal_contract_no())&& map != null) {
				if ("Invalid".equalsIgnoreCase(val.getDateValidate((String) map.get("EXPIRY_DATE"), bean.getIncepDate()))) {
					addActionError(getText("errors.InceptionDate.invalid"));
				}else {
					bean.setRenewalFlag("NEWCONTNO");
				}
			}
			if (StringUtils.isBlank(val.isNull(bean.getExpDate()))) {
				addActionError(getText("error.expDate.required"));
			} else if (expdate.equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.expdate.check"));
			}
			if (StringUtils.isBlank(val.isSelect(bean.getUwYear()))) {
				addActionError(getText("error.uwYear.required"));
			} else if (!"".equals(bean.getRenewal_contract_no()) && !"0".equals(bean.getRenewal_contract_no()) && map != null && Integer.parseInt((String) map.get("UW_YEAR")) >= Integer.parseInt(bean.getUwYear())) {
				//addActionError(getText("errors.year.invalid"));
			}
			if("D".equalsIgnoreCase(bean.getDummyCon())){
				int count = dropDownControllor.DuplicateCountCheck(bean.getUwYear(),branchCode,pid,"D",bean.getProposal_no());
				if(count>0){
					addActionError(getText("error.dup.uwyewar"));
				}
			}
			if (StringUtils.isNotBlank(bean.getProStatus()) && "A".equalsIgnoreCase(bean.getProStatus())&&StringUtils.isBlank(bean.getAccDate())) {
				addActionError(getText("error.accDate.required"));
			}else if (StringUtils.isNotBlank(bean.getAccDate()) &&accDate.equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.accDate.checkerror"));
			}
			if (StringUtils.isBlank(orginalCurrency)) {
				addActionError(getText("error.orginalCurrency.required"));
			}
			if (StringUtils.isBlank(bean.getExchRate())) {
				addActionError(getText("errors.exchange.required"));
			}
			if (StringUtils.isBlank(bean.getCessionExgRate())) {
				addActionError(getText("errors.cession.exchange.required"));
			}
			else{
				if("F".equalsIgnoreCase(bean.getCessionExgRate()) && StringUtils.isBlank(bean.getFixedRate())){
					addActionError(getText("errors.fixedrate.required"));
				}
				else{
					bean.setFixedRate(bean.getFixedRate().replace(",", ""));
				}
			}
			if (StringUtils.isBlank(val.isNull(bean.getNoRetroCess()))) {
				addActionError(getText("error.noOfRetroCess.required"));
			} else if (val.isValidNo(bean.getNoRetroCess()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.noOfRetroCess.invalid"));
			} else if (Integer.parseInt(bean.getNoRetroCess()) < 1) {
				addActionError(getText("error.noOfRetroCess.gte1"));
			}

			if (StringUtils.isBlank(bean.getTerritoryscope())) {
				addActionError(getText("error.terrtoryScope.required"));
			}
			if (StringUtils.isBlank(val.isSelect(bean.getProposalType()))) {
				addActionError(getText("error.cleancutoff.required"));
			}
		else if("R".equalsIgnoreCase(bean.getProposalType()) || "H".equalsIgnoreCase(bean.getProposalType())){
				if(StringUtils.isBlank(bean.getRunoffYear())){
					addActionError(getText("error.runoff.required"));	
				}
			}
			if (StringUtils.isBlank(riskCover)) {
				addActionError(getText("error.portfolio.Reqired"));
			}
			if (StringUtils.isBlank(bean.getLOCIssued())) {
				addActionError(getText("error.locissued.required"));
			}
			else if("Y".equalsIgnoreCase(bean.getLOCIssued())){
					if(StringUtils.isBlank(bean.getLocBankName())){
						addActionError(getText("error.locbank.required"));
					}
					if(StringUtils.isBlank(bean.getLocCreditPrd())){
						addActionError(getText("error.loccrditPerd.required"));
					}
					if(StringUtils.isBlank(bean.getLocCreditAmt())){
						addActionError(getText("error.loccreditAmt.required"));
					}
					else{
						bean.setLocCreditAmt(bean.getLocCreditAmt().replaceAll(",", ""));
					}
					if(StringUtils.isBlank(bean.getLocBeneficerName())){
						addActionError(getText("error.locbenifName.required"));
					}
				}
				/*if (StringUtils.isBlank(bean.getPerilCovered())) {
					addActionError(getText("error.perilCovered.required"));
				}*/
			if (bean.getPnoc().equalsIgnoreCase("-1")) {
				addActionError(getText("error.pnoc.required"));
			}
			if (StringUtils.isBlank(val.isSelect(bean.getAccountingPeriod())) || bean.getAccountingPeriod().equalsIgnoreCase("-1")) {
				addActionError(getText("error.AccountionPeriod.reqired"));
			}
			if (StringUtils.isBlank(bean.getReceiptofStatements())) {
				addActionError(getText("Error.ResciptStatments.Required"));
			} else if (val.isValidNo(bean.getReceiptofStatements()).equalsIgnoreCase("invalid")) {
				addActionError(getText("Error.ReceiptofStatmenst.Error"));
			}
			if (StringUtils.isBlank(bean.getReceiptofPayment())) {
				addActionError(getText("error.ReceiptOfStatments.required"));
			} else if (val.isValidNo(bean.getReceiptofPayment()).equalsIgnoreCase("invalid")) {
				addActionError(getText("error.ReceiptOfStatments.Error"));
			}

			if (StringUtils.isBlank(bean.getTerritory())) {
				addActionError(getText("errors.territoryCode.required"));
			}
			if (StringUtils.isBlank(bean.getCountryIncludedList())) {
				addActionError(getText("errors.CountryInclude.required"));
			}
			boolean cedflag = true;
			double amt = 0.0;
			boolean cedCheck = true;
			final String cenRent = val.isNull(bean.getCedReten());
			if (val.isNull(bean.getCedRetenType()).equalsIgnoreCase("")) {
				addActionError(getText("error.cedRentType.required.our"));
				cedflag = false;
			} else {
				if (cenRent.equalsIgnoreCase("")) {
					addActionError(getText("error.cedRent.required.our"));
					cedflag = false;
				} else {
					bean.setCedReten((bean.getCedReten()).replaceAll(",", ""));
					if ("A".equalsIgnoreCase(bean.getCedRetenType())) {
						cedflag = false;
						if (val.isValidNo(bean.getCedReten()).trim().equalsIgnoreCase("Invalid")) {
							addActionError(getText("error.cedRentAmt.required.our"));
						}
					} else if ("P".equalsIgnoreCase(bean.getCedRetenType())) {
						if (val.percentageValid(bean.getCedReten()).trim().equalsIgnoreCase("Invalid") || val.percentageValid(bean.getCedReten().trim()).equalsIgnoreCase("less") || val.percentageValid(bean.getCedReten().trim()).equalsIgnoreCase("greater")) {
							addActionError(getText("error.cedRentPer.required.our"));
							cedflag = false;
						}
					}
				}
			}
			if(StringUtils.isNotBlank(bean.getRetroType()) && "TR".equalsIgnoreCase(bean.getRetroType())){
			if (bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("1")) {
				if (limitPercent.equalsIgnoreCase("")) {
					addActionError(getText("error.fac.limitOrigCurr.required.qs"));
					cedCheck = false;
				} else {
					bean.setLimitOrigCur((bean.getLimitOrigCur()).replaceAll(",", ""));
					if (val.isValidNo(bean.getLimitOrigCur()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.fac.limitOrigCurr.check.qs"));
						cedCheck = false;
					} else {
						amt = Double.parseDouble(bean.getLimitOrigCur());
					}
				}
			}
		
			if (bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("2")) {
				if (StringUtils.isBlank(bean.getTreatynoofLine())) {
					addActionError(getText("error.noonline.required"));
				}
			}
			if (bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("2")) {
				if (StringUtils.isBlank(bean.getTreatyLimitsurplusOC())) {
					addActionError(getText("error.TreatyLimitsurplusOC.required"));
					cedCheck = false;
				} else {
					bean.setTreatyLimitsurplusOC((bean.getTreatyLimitsurplusOC()).replaceAll(",", ""));
					if (val.isValidNo(bean.getTreatyLimitsurplusOC()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.TreatyLimitsurplusOC.check"));
						cedCheck = false;
					} else {
						amt = Double.parseDouble(bean.getTreatyLimitsurplusOC());
					}
				}
			}
			}
			else{
				if (StringUtils.isBlank(bean.getFaclimitOrigCur())) {
					addActionError(getText("error.fac.limitOrigCurr.required"));
					cedCheck = false;
				} else {
					bean.setFaclimitOrigCur((bean.getFaclimitOrigCur()).replaceAll(",", ""));
					if (val.isValidNo(bean.getFaclimitOrigCur()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.fac.limitOrigCurr.check"));
						cedCheck = false;
					} else {
						amt = Double.parseDouble(bean.getFaclimitOrigCur());
					}
				}
			}
			if (StringUtils.isBlank(bean.getPml())) {
				addActionError(getText("error.pml.required"));
			} else if ("Y".equalsIgnoreCase(bean.getPml())) {
				if (StringUtils.isBlank(bean.getPmlPercent())) {
					addActionError(getText("error.pmlpercentage.required"));
				} else {
					double pmlper = Double.parseDouble(bean.getPmlPercent());
					if (pmlper > 100) {
						addActionError(getText("error.pmlpercentage.less.100.required"));
					}
				}
				/*if ("Y".equalsIgnoreCase(bean.getPml())) {
					if (StringUtils.isBlank(bean.getEpipml())) {
						addActionError(getText("error.epipml(retro).invalid"));
					} else {
						bean.setEpipml(bean.getEpipml().replaceAll(",", ""));
					}
				}*/
				/*if(StringUtils.isNotBlank(bean.getRetroType()) && "TR".equalsIgnoreCase(bean.getRetroType())){
				if (bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("1")) {
					if (StringUtils.isBlank(bean.getLimitOrigCurPml())) {
						addActionError(getText("error.limitorgcurpml.required"));
					} else {
						bean.setLimitOrigCurPml(bean.getLimitOrigCurPml().replaceAll(",", ""));
					}
				}
				if (bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("2")) {
					if (StringUtils.isBlank(bean.getTreatyLimitsurplusOCPml())) {
						addActionError(getText("error.limitsurplespml.required"));
					} else {
						bean.setTreatyLimitsurplusOCPml(bean.getTreatyLimitsurplusOCPml().replaceAll(",", ""));
					}
				}
			}
				else{
					if (StringUtils.isBlank(bean.getFaclimitOrigCurPml())) {
						addActionError(getText("error.faclimitorgcurpml.required"));
					} else {
						bean.setFaclimitOrigCurPml(bean.getFaclimitOrigCurPml().replaceAll(",", ""));
					}
				}*/
			}
			
			if (StringUtils.isBlank(bean.getEpi())) {
				addActionError(getText("error.epiRetro.required"));
			}

			/*if (StringUtils.isBlank(xlCost)) {
				addActionError(getText("error.xlCost.required"));
			} else {
				bean.setXlCost((bean.getXlCost()).replaceAll(",", ""));
				if (val.isValidNo(bean.getXlCost()).equalsIgnoreCase("invalid")) {
					addActionError(getText("error.xlCost.check"));
				}
			}*/
			if ("".equalsIgnoreCase(proStatus)) {
				addActionError(getText("error.proStatus.required"));
			}
			validationRemarks();

		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}

	private void validateSecodnPageSaveMethod() {
		try {
			Validation validation = new Validation();
			if (StringUtils.isNotBlank(bean.getLimitOurShare())) {
				if (validation.isValidNo(bean.getLimitOurShare().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.limitOurShare.second1"));
				}
			}
			if (StringUtils.isNotBlank(bean.getEpiAsPerOffer())) {
				if (validation.isValidNo(bean.getEpiAsPerOffer().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.epiAsPerOffer.second1"));
				}
			}
			int NoRetroCess = Integer.parseInt(bean.getNoRetroCess() == null ? "0" : bean.getNoRetroCess());
			for (int i = 0; i < NoRetroCess; i++) {
				String cedComp = bean.getCedingCompany().get(i) == null ? "0" : bean.getCedingCompany().get(i);
				String broker = bean.getRetroBroker().get(i) == null ? "0" : bean.getRetroBroker().get(i);
				String shAccep = bean.getShareAccepted().get(i)== null ? "" : bean.getShareAccepted().get(i);
				String shSign = bean.getShareSigned().get(i)== null ? "" : bean.getShareSigned().get(i);
				String comm = bean.getCommission().get(i)== null ? "" : bean.getCommission().get(i);
				if (StringUtils.isBlank(validation.isSelect(cedComp))) {
					addActionError(getText("errors.reinsurersName.required.retro",new String[] { String.valueOf(i + 1) }));
				}
				if (StringUtils.isBlank(validation.isSelect(broker))) {
					addActionError(getText("errors.brokerRetro.required",new String[] { String.valueOf(i + 1) }));
				}
				boolean shAccSign = true;
				if (StringUtils.isNotBlank(shAccep)) {
					if (validation.percentageValid(shAccep).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.shAccepPer.invalid",	new String[] { String.valueOf(i + 1) }));
						shAccSign = false;
					}
				} else {
					shAccSign = false;
				}
				if (StringUtils.isNotBlank(shSign)) {
					if (validation.percentageValid(shSign).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.shSignPer.invalid",new String[] { String.valueOf(i + 1) }));
						shAccSign = false;
					}
				} else {
					shAccSign = false;
				}
				if (shAccSign) {
					double shac = Double.parseDouble(shAccep);
					double shsign = Double.parseDouble(shSign);
					if (shac < shsign) {
						addActionError(getText("errors.shAccepLessShSign.invalid",new String[] { String.valueOf(i + 1) }));
					}
				}
				if (StringUtils.isNotBlank(comm)) {
					if (validation.percentageValid(comm).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.commPer.invalid",new String[] { String.valueOf(i + 1) }));
					}
				}
			}
			if ("5".equals(pid)) {
				if (StringUtils.isNotBlank(bean.getReinstNo())) {
					bean.setReinstNo((bean.getReinstNo()).replaceAll(",", ""));
					if (validation.isValidNo(bean.getReinstNo()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.NoofReinstatment.Invalid"));
					}
				}
				if (StringUtils.isNotBlank(bean.getReinstAditionalPremium_percent())) {
					bean.setReinstAditionalPremium_percent((bean.getReinstAditionalPremium_percent()).replaceAll(",", ""));
					if (validation.percentageValid(bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("INVALID")	|| validation.percentageValid(bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("less")	|| validation.percentageValid(bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.reinsaddprem.invalid"));
					}
				}
				if (StringUtils.isNotBlank(bean.getBurningCost())) {
					bean.setBurningCost((bean.getBurningCost()).replaceAll(",", ""));
					if (validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("INVALID")|| validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("less")|| validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.burning.invalid"));
					}
				}
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}		
	}
	private void validateSecondPage() {
		try {
			double totShAcc = 0.0;
			double totShsg = 0.0;
			Validation validation = new Validation();
			if ("3".equalsIgnoreCase(bean.getTreatyType()) || "2".equalsIgnoreCase(bean.getTreatyType())) {
				if (StringUtils.isBlank(bean.getPremiumSurplus())) {
					addActionError(getText("Errors.PremiumSurplus.Required"));
				} else if (StringUtils.isNotBlank(bean.getPremiumSurplus())) {
					bean.setPremiumSurplus((bean.getPremiumSurplus()).replaceAll(",", ""));
					if (validation.isValidNo(bean.getPremiumSurplus()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("Errors.PremiumSurplus.Invalid"));
					}else{
						String ans = calcu.calculateXOL(bean,"PremiumSur",0,sourceId);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getPremiumSurplus().replaceAll(",",""))){
							addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setPremiumSurplus(ans);
						}

					}
				}
				if(Double.parseDouble(bean.getPremiumSurplus())<0){
					addActionError(getText("Errors.PremiumSurplus.Isnotless"));
				}
			}
			if ("3".equalsIgnoreCase(bean.getTreatyType()) || "1".equalsIgnoreCase(bean.getTreatyType())) {
				if (StringUtils.isBlank(bean.getPremiumQuotaShare())) {
					addActionError(getText("Errors.PremiumQuotaShare.Required"));
				} else if (StringUtils.isNotBlank(bean.getPremiumQuotaShare())) {
					bean.setPremiumQuotaShare((bean.getPremiumQuotaShare()).replaceAll(",", ""));
					if (validation.isValidNo(bean.getPremiumQuotaShare()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("Errors.PremiumQuota.Invalid"));
					}
				}
			}
			if(StringUtils.isBlank(bean.getTreatyType())){
				if (StringUtils.isBlank(bean.getPremiumQuotaShare())) {
					addActionError(getText("Errors.Premium100OC.Required"));
				} else if (StringUtils.isNotBlank(bean.getPremiumQuotaShare())) {
					bean.setPremiumQuotaShare((bean.getPremiumQuotaShare()).replaceAll(",", ""));
					if (validation.isValidNo(bean.getPremiumQuotaShare()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("Errors.Premium100OC.Invalid"));
					}
				}
			}
			if(StringUtils.isBlank(bean.getLocRate())){
				addActionError(getText("label.rate.year.error"));
			}
			if(StringUtils.isBlank(bean.getRetroCommissionType())){
				addActionError(getText("label.com.type.error"));
			}
			if ("3".equalsIgnoreCase(bean.getTreatyType()) || "1".equalsIgnoreCase(bean.getTreatyType())) {
				if (StringUtils.isBlank(bean.getCommissionQ_S())) {
					addActionError(getText("errors.commissionQ_S.second"));
				} else if (!bean.getCommissionQ_S().equalsIgnoreCase("")) {
					if (validation.percentageValid(bean.getCommissionQ_S().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.commissionQ_S.second1"));
					} else if (validation.percentageValid(bean.getCommissionQ_S().trim()).equalsIgnoreCase("less")) {
						addActionError(getText("errors.commissionQ_S.second1less"));
					} else if (validation.percentageValid(bean.getCommissionQ_S().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("errors.commissionQ_S.second1greater"));
					}
					bean.setCommissionQ_SAmt(validation.isNull(bean.getCommissionQ_SAmt()).replaceAll(",", ""));
				}
			}
			if ("3".equalsIgnoreCase(bean.getTreatyType()) || "2".equalsIgnoreCase(bean.getTreatyType())) {
				if (StringUtils.isBlank(bean.getCommission_surp())) {
					addActionError(getText("errors.commission_surp.second.req"));
				} else if (!bean.getCommission_surp().equalsIgnoreCase("")) {
					if (validation.percentageValid(bean.getCommission_surp().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.commission_surp.second1"));
					} else if (validation.percentageValid(bean.getCommission_surp().trim()).equalsIgnoreCase("less")) {
						addActionError(getText("errors.commission_surp.secondless"));
					} else if (validation.percentageValid(bean.getCommission_surp().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("errors.commission_surp.secondgreater"));
					}
					bean.setCommission_surpAmt(validation.isNull(bean.getCommission_surpAmt()).replaceAll(",", ""));
				}
			}

			if (StringUtils.isBlank(bean.getOverRidder())) {
				addActionError(getText("errors.overRidder.second.req"));
			} else if (StringUtils.isNotBlank(bean.getOverRidder())) {
				if (validation.percentageValid(bean.getOverRidder().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.overRidder.second1"));
				} else if (validation.percentageValid(bean.getOverRidder().trim()).equalsIgnoreCase("less")) {
					addActionError(getText("errors.overRidder.secondless"));
				} else if (validation.percentageValid(bean.getOverRidder().trim()).equalsIgnoreCase("greater")) {
					addActionError(getText("errors.overRidder.secondgreater"));
				}
			}

			if (StringUtils.isBlank(bean.getBrokerage())) {
				addActionError(getText("errors.brokerage.second"));
			} else if (StringUtils.isNotBlank(bean.getBrokerage())) {
				if (validation.percentageValid(bean.getBrokerage()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.brokerage.second1"));
				} else if (validation.percentageValid(bean.getBrokerage()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.brokerage.secondless"));
				} else if (validation.percentageValid(bean.getBrokerage()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.brokerage.secondgreater"));
				}
			}

			if (StringUtils.isBlank(bean.getTax())) {
				addActionError(getText("errors.tax.second"));
			} else if (StringUtils.isNotBlank(bean.getTax())) {
				if (validation.percentageValid(bean.getTax()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.tax.second1"));
				} else if (validation.percentageValid(bean.getTax()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.tax.secondless"));
				} else if (validation.percentageValid(bean.getTax()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.tax.secondgreater"));
				}
			}

			if (StringUtils.isBlank(bean.getOthercost())) {
				addActionError(getText("errors.othercost.second"));
			} else if (!validation.isNull(bean.getOthercost()).equalsIgnoreCase("")) {
				if (validation.percentageValid(bean.getOthercost()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.othercost.secondinvalid"));
				} else if (validation.percentageValid(bean.getOthercost()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.othercost.secondless"));
				} else if (validation.percentageValid(bean.getOthercost()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.othercost.secondgreater"));
				}
			}
			if (StringUtils.isBlank(bean.getPremium_Reserve())) {
				addActionError(getText("errors.premium_Reserve.second"));
			} else {
				if (validation.percentageValid(bean.getPremium_Reserve()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.premium_Reserve.second1"));
				} else if (validation.percentageValid(bean.getPremium_Reserve()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.premium_Reserve.secondless"));
				} else if (validation.percentageValid(bean.getPremium_Reserve()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.premium_Reserve.secondgreater"));
				}
			}
			if (StringUtils.isBlank(bean.getLoss_reserve())) {
				addActionError(getText("errors.loss_reserve.second"));
			} else {
				if (validation.percentageValid(bean.getLoss_reserve()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.loss_reserve.second1"));
				} else if (validation.percentageValid(bean.getLoss_reserve()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.loss_reserve.secondless"));
				} else if (validation.percentageValid(bean.getLoss_reserve()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.loss_reserve.secondgreater"));
				}
			}

			if (StringUtils.isBlank(bean.getInterest())) {
				addActionError(getText("errors.interest.second"));
			} else {
				if (validation.percentageValid(bean.getInterest()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.interest.second1"));
				} else if (validation.percentageValid(bean.getInterest()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.interest.secondless"));
				} else if (validation.percentageValid(bean.getInterest()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.interest.secondgreater"));
				}
			}
			if (StringUtils.isBlank(bean.getPortfolio_inout_Loss())) {
				addActionError(getText("errors.portfolio_inout_Loss.second"));
			} else {

				if (validation.percentageValid(bean.getPortfolio_inout_Loss()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.portfolio_inout_Loss.second"));
				} else if (validation.percentageValid(bean.getPortfolio_inout_Loss()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.portfolio_inout_Loss.secondgreater"));
				}
			}
			if (StringUtils.isBlank(bean.getPortfolio_inout_Premium())) {
				addActionError(getText("errors.portfolio_inout_Premium.second"));
			} else {
				if (validation.percentageValid(bean.getPortfolio_inout_Premium()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.portfolio_inout_Premium.premiumNum"));
				} else if (validation.percentageValid(bean.getPortfolio_inout_Premium()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.portfolio_inout_Premium.greater"));
				}
			}

			if (bean.getCrestaStatus().equalsIgnoreCase("Y")) {
				if(StringUtils.isBlank(bean.getCrestaPopUp())){
					addActionError(getText("cresta.popup.check"));
				}
				else if (service.getCrestaCount(bean) == 0) {
					addActionError(getText("error.creasta.invalid"));
				}
			}

			if (StringUtils.isBlank(bean.getSlideScaleCommission())) {
				addActionError(getText("error.slidescale.commission"));
			} else if ("Y".equalsIgnoreCase(bean.getSlideScaleCommission())) {
				if(StringUtils.isBlank(bean.getSlidePopUp())){
					addActionError(getText("error.slide.recheck"));
				}else{
				int count = service.getBonusListCount(bean, "scale");
				if (count <= 0) {
					addActionError(getText("slide.error.lcb.table.empty"));
				}
				}
			}
			if (StringUtils.isBlank(bean.getLossParticipants())) {
				addActionError(getText("error.losspart"));
			} else if ("Y".equalsIgnoreCase(bean.getLossParticipants())) {
				if(StringUtils.isBlank(bean.getLossPopUp())){
					addActionError(getText("error.loss.recheck"));
				}else{
				int count = service.getBonusListCount(bean, "lossparticipates");
				if (count <= 0) {
					addActionError(getText("losspart.error.lcb.table.empty"));
				}
				}
			}
			if (StringUtils.isBlank(bean.getShare_Profit_Commission())) {
				addActionError(getText("errors.share_Profit_Commission.empty"));
			}

			if ("1".equalsIgnoreCase(bean.getShare_Profit_Commission())) {
				if (StringUtils.isBlank(bean.getManagementExpenses())) {
					addActionError(getText("man.exp.req"));
				}
				if (StringUtils.isBlank(bean.getCommissionType())) {
					addActionError(getText("com.type.req"));
				} else if ("PC".equalsIgnoreCase(bean.getCommissionType())) {
					if (StringUtils.isBlank(bean.getProfitCommissionPer())) {
						addActionError(getText("pro.com.per.req"));
					} else if (Double.parseDouble(bean.getProfitCommissionPer()) > 100) {
						addActionError(getText("profit.com.less.hundred"));
					}
					if (StringUtils.isBlank(bean.getSuperProfitCommission())) {
						addActionError(getText("error.super.pro.com"));
					} else {
						if ("Y".equalsIgnoreCase(bean.getSuperProfitCommission())) {
							if(StringUtils.isBlank(bean.getProfitPopUp())){
								addActionError(getText("error.profit.recheck"));
							}else{
							int count = service.CommissionTypeCount(bean);
							if (count <= 0) {
								addActionError(getText("error.commission.schedule"));
							}
						}
						}
					}
				} else if ("PR".equalsIgnoreCase(bean.getCommissionType()) || "LR".equalsIgnoreCase(bean.getCommissionType())) {
					if (StringUtils.isBlank(bean.getSetup())) {
						addActionError(getText("error.setup.req"));
					}
					if(StringUtils.isBlank(bean.getProfitPopUp())){
						addActionError(getText("error.profit.recheck"));
					}else{
					int count = service.CommissionTypeCount(bean);
					if (count <= 0) {
						addActionError(getText("error.commission.schedule"));
					}
					}
				}

				if (StringUtils.isBlank(bean.getLossCarried())) {
					addActionError(getText("loss.carried.req"));
				} else if (!"TE".equalsIgnoreCase(bean.getLossCarried())) {
					if (StringUtils.isBlank(bean.getLossyear())) {
						addActionError(getText("error.loss.year"));
					} else if (Integer.parseInt(bean.getLossyear()) > 100) {
						addActionError(getText("loss.carried.yeas.less.hundred"));
					}
				}
				if (StringUtils.isBlank(bean.getProfitCarried())) {
					addActionError(getText("error.profit.carried"));
				} else if (!"TE".equalsIgnoreCase(bean.getProfitCarried())) {
					if (StringUtils.isBlank(bean.getProfitCarriedForYear())) {
						addActionError(getText("profit.carried.year.req"));
					} else if (Integer.parseInt(bean.getProfitCarriedForYear()) > 100) {
						addActionError(getText("profit.carried.yeas.less.hundred"));
					}
				}
				if (StringUtils.isBlank(bean.getFistpc())) {
					addActionError(getText("req.first.profit.comm"));
				}
				if (StringUtils.isBlank(bean.getProfitMont())) {
					addActionError(getText("error.profit.month"));
				}
				if (StringUtils.isBlank(bean.getSubpc())) {
					addActionError(getText("error.sub.profit.com"));
				}
				if (StringUtils.isBlank(bean.getSubProfitMonth())) {
					addActionError(getText("error.sub.profit.month"));
				}
				if (StringUtils.isBlank(bean.getSubSeqCalculation())) {
					addActionError(getText("error.sub.seq.cal.req"));
				}
				if (StringUtils.isBlank(bean.getProfitCommission())) {
					addActionError(getText("error.profit.commission.req"));
				}
			}

			if (StringUtils.isBlank(bean.getLoss_Advise())) {
				addActionError(getText("errors.loss_Advise.second"));
			} else if (validation.isValidNo(bean.getLoss_Advise().trim()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("errors.loss_Advise.second1"));
			} else {
				bean.setLoss_Advise((bean.getLoss_Advise()).replaceAll(",", ""));
			}
			if (StringUtils.isBlank(bean.getCash_Loss_Limit())) {

				addActionError(getText("errors.cash_Loss_Limit.second"));
			} else {
				bean.setCash_Loss_Limit((bean.getCash_Loss_Limit()).replaceAll(",", ""));
				if (StringUtils.isNotBlank(bean.getLimitOrigCur()) && StringUtils.isBlank(bean.getTreatyLimitsurplusOC())) {
					if (validation.isValidNo(bean.getCash_Loss_Limit()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.cash_Loss_Limit.invalid.retro"));
					} else if (!validation.isValidNo(bean.getLimitOrigCur()).trim().equalsIgnoreCase("INVALID") && (Double.parseDouble(bean.getCash_Loss_Limit()) > Double.parseDouble(bean.getLimitOrigCur())))
						addActionError(getText("errors.cash_Loss_Limit.invalid.retro"));
				} else if (StringUtils.isBlank(bean.getLimitOrigCur()) && StringUtils.isNotBlank(bean.getTreatyLimitsurplusOC())) {
					if (validation.isValidNo(bean.getCash_Loss_Limit()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.cash_Loss_Limit.invalid.retro"));
					} else if (!validation.isValidNo(bean.getTreatyLimitsurplusOC()).trim().equalsIgnoreCase("INVALID") && (Double.parseDouble(bean.getCash_Loss_Limit()) > Double.parseDouble(bean.getTreatyLimitsurplusOC())))
						addActionError(getText("errors.cash_Loss_Limit.invalid.retro"));
				} else if (StringUtils.isNotBlank(bean.getLimitOrigCur()) && StringUtils.isNotBlank(bean.getTreatyLimitsurplusOC())) {
					int t = Double.compare(Double.parseDouble(bean.getLimitOrigCur()), Double.parseDouble(bean.getTreatyLimitsurplusOC()));
					if (validation.isValidNo(bean.getCash_Loss_Limit()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.cash_Loss_Limit.invalid.retro"));
					} else if (!(validation.isValidNo(bean.getLimitOrigCur()).trim().equalsIgnoreCase("INVALID") || validation.isValidNo(bean.getTreatyLimitsurplusOC()).trim().equalsIgnoreCase("INVALID")) && (Double.parseDouble(bean.getCash_Loss_Limit()) > (Double.parseDouble(t > 0 ? bean.getLimitOrigCur() : bean.getTreatyLimitsurplusOC()))))
						addActionError(getText("errors.cash_Loss_Limit.invalid.retro"));
				}
			}


			if (StringUtils.isBlank(bean.getEvent_limit())) {
				addActionError(getText("errors.event_limit.second"));
			} else {
				bean.setEvent_limit(bean.getEvent_limit().replaceAll(",", ""));
				if (validation.isValidNo(bean.getEvent_limit().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.event_limit.invalid"));
				}
			}
			if (StringUtils.isBlank(bean.getAggregate_Limit())) {
				addActionError(getText("errors.aggregate_Limit.second"));
			} else {
				bean.setAggregate_Limit(bean.getAggregate_Limit().replaceAll(",", ""));
				if (validation.isValidNo(bean.getAggregate_Limit().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.aggregate_Limit.invalid"));
				}
			}
			if (StringUtils.isBlank(bean.getOccurrent_Limit())) {
				addActionError(getText("errors.occurrent_Limit.second"));
			} else {
				bean.setOccurrent_Limit(bean.getOccurrent_Limit().replaceAll(",", ""));
				if (validation.isValidNo(bean.getOccurrent_Limit().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.occurrent_Limit.invalid"));
				}
			}

			/*if ("3".equals(pid) || "5".equals(pid)) {
				int instalmentperiod = Integer.parseInt(bean.getM_d_InstalmentNumber());
				boolean tata = false;
				for (int i = 0; i < instalmentperiod; i++) {
					if (StringUtils.isBlank(validation.isNull(bean.getInstalmentDateList().get(i)))) {
						addActionError(getText("Error.required.InstalDate",new String[] { String.valueOf(i + 1) }));
					} else if (validation.checkDate(bean.getInstalmentDateList().get(i)).equalsIgnoreCase("Invalid")) {
						addActionError(getText("Error.Error.InstalDate", new String[] { String.valueOf(i + 1)}));
					}
					if (StringUtils.isNotBlank(validation.isNull(bean.getInstalmentDateList().get(0)))) {
						if (validation.ValidateINstallDates(bean.getIncepDate(),bean.getInstalmentDateList().get(0)).equalsIgnoreCase("Invalid")) {
							tata = true;
						}
					}
					if (StringUtils.isNotBlank(validation.isNull(	bean.getInstalmentDateList().get(i)))) {
						if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
							addActionError(getText("Error.Select.Expirydate", new String[] { String.valueOf(i + 1)}));
						}
					}
					if (StringUtils.isBlank(validation.isNull(bean.getInstallmentPremium().get(i)))) {
						addActionError(getText("Error.required.InstallPremium"));
					} else if (validation.isValidNo(bean.getInstallmentPremium().get(i)).equalsIgnoreCase("Invalid")) {
						addActionError(getText("Error.error.InstallPremium"));
					}
					if (i != 0) {
						if (StringUtils.isNotBlank(validation.isNull(	bean.getInstalmentDateList().get(i)))) {
							if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i-1),	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("Invalid")) {
								addActionError(getText("Error.required.InstalDate",new String[] { String.valueOf(i + 1)}));
							}
						}
					}
				}
				if (tata == true) {
					addActionError(getText("Error.Select.AfterInceptionDate"));
				}
			}*/
			int NoRetroCess = Integer.parseInt(bean.getNoRetroCess()== null ? "0" : bean.getNoRetroCess());
			for (int i = 0; i < NoRetroCess; i++) {
				String cedComp = bean.getCedingCompany().get(i) == null ? "0" : bean.getCedingCompany().get(i);
				String broker = bean.getRetroBroker().get(i) == null ? "0" : bean.getRetroBroker().get(i);
				String shAccep = bean.getShareAccepted().get(i)== null ? "" : bean.getShareAccepted().get(i);
				String shSign = bean.getShareSigned().get(i)== null ? "" : bean.getShareSigned().get(i);
				String comm = bean.getCommission().get(i)== null ? "" : bean.getCommission().get(i);
				String proStatus = bean.getProposalStatus().get(i) == null ? "0" : bean.getProposalStatus().get(i);
				if (StringUtils.isBlank(validation.isSelect(cedComp))) {
					addActionError(getText("errors.reinsurersName.required.retro",	new String[] { String.valueOf(i + 1)}));
					//addActionError(getText("Error.required.InstalDate",new String[] { String.valueOf(i + 1)}));
				}
				if (StringUtils.isBlank(validation.isSelect(broker))) {
					addActionError(getText("errors.brokerRetro.required",	new String[] { String.valueOf(i + 1)}));
				}
				boolean shAccSign = true;
				if (StringUtils.isBlank(shAccep)) {
					addActionError(getText("errors.shAccepPer.required",new String[] { String.valueOf(i + 1)}));
					shAccSign = false;
				} else if (validation.percentageValid(shAccep).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.shAccepPer.invalid",new String[] { String.valueOf(i + 1)}));
					shAccSign = false;
				}
				if (StringUtils.isBlank(validation.isSelect(proStatus))) {
					addActionError(getText("errors.proStatus.required",new String[] { String.valueOf(i + 1)}));
				}
				if (StringUtils.isBlank(shSign) && proStatus.equalsIgnoreCase("A")) {
					addActionError(getText("errors.shSignPer.required",new String[] { String.valueOf(i + 1)}));
					shAccSign = false;
				} else if (validation.percentageValid(shSign).equalsIgnoreCase("INVALID") && !proStatus.equalsIgnoreCase("A")) {
					addActionError(getText("errors.shSignPer.invalid",	new String[] { String.valueOf(i + 1)}));
					shAccSign = false;
				}
				if (shAccSign  && proStatus.equalsIgnoreCase("A") ) {
					double shac = Double.parseDouble(shAccep);
					double shsign = Double.parseDouble(shSign);
					if (shac < shsign) {
						addActionError(getText("errors.shAccepLessShSign.invalid",new String[] { String.valueOf(i + 1)}));
					}

				}
				if(StringUtils.isNotBlank(proStatus)&&"A".equalsIgnoreCase(proStatus)){
				if (StringUtils.isBlank(comm)) {
					addActionError(getText("errors.commPer.required",	new String[] { String.valueOf(i + 1)}));
				} else if (validation.percentageValid(comm).equalsIgnoreCase("INVALID")	|| validation.percentageValid(comm)	.equalsIgnoreCase("less")|| validation.percentageValid(comm).equalsIgnoreCase("greater")) {
					addActionError(getText("errors.commPer.invalid",new String[] { String.valueOf(i + 1)}));
				}
				}

			}
			if (!hasActionErrors()) {
				
				boolean shareFlag = false;
				for (int i = 0; i < NoRetroCess; i++) {

					String cedComp = bean.getCedingCompany().get(i) == null ? "0" : bean.getCedingCompany().get(i);
					String broker = bean.getRetroBroker().get(i) == null ? "0" : bean.getRetroBroker().get(i);
					String shAccep = bean.getShareAccepted().get(i)== null ? "" : bean.getShareAccepted().get(i);
					String shSign = bean.getShareSigned().get(i)== null ? "0" : bean.getShareSigned().get(i);
					if (bean.getProposalStatus().get(i).equalsIgnoreCase("A")) {
						totShAcc += Double.parseDouble(shAccep);
						totShsg += Double.parseDouble(shSign);
						shareFlag =true;
					}
					for (int j = i + 1; j < NoRetroCess; j++) {
						String cedComp1 = bean.getCedingCompany().get(j) == null ? "0" : bean.getCedingCompany().get(j);
						String broker1 = bean.getRetroBroker().get(j) == null ? "0" : bean.getRetroBroker().get(j);
						String shAccep1 = bean.getShareAccepted().get(j)== null ? "" : bean.getShareAccepted().get(j);
						String shSign1 = bean.getShareSigned().get(j)== null ? "" : bean.getShareSigned().get(j);
						if (cedComp.equals(cedComp1)&& broker.equals(broker1))
							addActionError(getText("errors.cedCompBroker.invalid",	new String[] { String.valueOf(j + 1)}));
						logger.info(i + 1 + " " + j + " " + NoRetroCess	+ " " + totShAcc);
						if (((i + 1) == NoRetroCess) && (j == NoRetroCess)) {

							if (bean.getProposalStatus().get(j).equalsIgnoreCase("A")) {
								totShsg += Double.parseDouble(shSign1);
								totShAcc += Double.parseDouble(shAccep1);
							}
						}
					}
				}
				BigDecimal bd = new BigDecimal(totShsg).setScale(2, RoundingMode.HALF_EVEN);
				totShsg = bd.doubleValue();
				if (totShsg != 100 && shareFlag)
					addActionError(getText("errors.shSign.invalid"));
				BigDecimal bigDecimal = new BigDecimal(totShAcc).setScale(2, RoundingMode.HALF_EVEN);
				totShAcc = bigDecimal.doubleValue();
				if (totShAcc > 100)
					addActionError(getText("errors.shAcc.invalid"));
			}

			/*if ("5".equalsIgnoreCase(pid)) {
				if (StringUtils.isBlank(validation.isNull(bean.getReinstNo()))) {
					addActionError(getText("errors.NoofReinstatment.Required"));
				}else {
					bean.setReinstNo((bean.getReinstNo()).replaceAll(",",""));
					if (validation.isValidNo(bean.getReinstNo()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.NoofReinstatment.Invalid"));
					}
				}
				if (StringUtils.isBlank(validation.isNull(bean.getReinstAditionalPremium_percent()))) {
					addActionError(getText("errors.reinsaddprem.null"));
				} else {
					bean.setReinstAditionalPremium_percent((bean.getReinstAditionalPremium_percent()).replaceAll(",", ""));
					if (validation.percentageValid(bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("INVALID")|| validation.percentageValid(bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("less")|| validation.percentageValid(bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.reinsaddprem.invalid"));
					}
				}
				if (StringUtils.isBlank(validation.isNull(bean.getBurningCost()))) {
					addActionError(getText("errors.burning.required"));
				} else {
					bean.setBurningCost((bean.getBurningCost()).replaceAll(",", ""));
					if (validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("INVALID")|| validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("less")|| validation.percentageValid(bean.getBurningCost()).trim()	.equalsIgnoreCase("greater")) {
						addActionError(getText("errors.burning.invalid"));
					}
				}
			}*/
			/*if (validation.isNull(bean.getLeader_Underwriter())
					.equalsIgnoreCase("")) {
				addActionError(getText("errors.leader_Underwriter.second"));
			}

			if(StringUtils.isBlank(bean.getLeader_Underwriter_country())){
				addActionError(getText("error.underwriter.country"));
			}
			if(StringUtils.isBlank(bean.getLeader_Underwriter_share())){
				addActionError(getText("error.underwriter.share"));
			}
			if (StringUtils.isNotBlank(bean.getLeader_Underwriter_share())) {
				if (validation.percentageValid(	bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.leader_Underwriter_share.second"));
				} else if (validation.percentageValid(bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.leader_Underwriter_share.greater"));
				}
			}/*if(StringUtils.isNotBlank(bean.getLeader_Underwriter()) &&    !"64".equalsIgnoreCase(bean.getLeader_Underwriter())){
				if(totShsg+Double.parseDouble(bean.getLeader_Underwriter_share())>100){
					addActionError(getText("errors.leader_Underwriter_share.greater.signed"));
					}
			}*/
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && StringUtils.isBlank(bean.getDocStatus())) {
				addActionError(getText("doc.status"));
			}
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) ) {
				final String endorseDate=validation.checkDate(bean.getEndorsementDate());
				if (validation.isNull(bean.getEndorsementDate()).equalsIgnoreCase("")) {
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.endoDate.required"));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.rectification.required"));
				}
				/*else if("GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.gnpiDate.required"));
				}*/
				} else if (endorseDate.equalsIgnoreCase("INVALID")) {
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.endoDate.check"));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.rectification.check"));
				}
				/*else if("GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.gnpiDate.check"));
				}
				}else  if ("Invalid".equalsIgnoreCase(Validation.ValidateTwo(bean.getMaxDate(), bean.getEndorsementDate()))) {
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.endoDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.rectificationDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}
				/*else if("GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.gnpiDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}*/
				}
			}
			validationRemarks();
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	public String retroprocess(){
		bean.setYearList(getRetroYearList());
		return "retroprocess";
		
	}
	public String retroInsert(){
		validationretroInsert();
		if(!hasActionErrors()){
		bean.setBranchCode(branchCode);
		bean.setEndDate(bean.getRetro_period_Date()+"/"+bean.getRetro_period_Year());
		bean.setStartDate(service.getEndDate(bean));
		service.insertRetroDetails(bean);
		bean.setMode("success");
		}else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
			bean.setYearList(getRetroYearList());
		}
		return "retroprocess";
	}
	public void validationretroInsert(){
		/*bean1.setBranchCode(branchCode);
		new DropDownControllor().getOpenPeriod(bean1);
		if(StringUtils.isBlank(bean.getRetro_period_Date())){
			addActionError(getText("error.retro.period.date"));
		}
		if(StringUtils.isBlank(bean.getRetro_period_Year())){
			addActionError(getText("error.retro.period.year"));
		}if(StringUtils.isNotBlank(bean.getRetro_period_Date()) && StringUtils.isNotBlank(bean.getRetro_period_Year())){
		if(dropDownControllor.Validatethree(bean1.getOpstartDate(),bean1.getOpendDate(), bean.getRetro_period_Date()+"/"+bean.getRetro_period_Year())==0){
			addActionError(getText("error.retro.open.period"));
		}
		}*/
		
	}
	public void validationretroDownload(){
		if(StringUtils.isBlank(bean.getRetro_period_Date())){
			addActionError(getText("error.retro.period.date"));
		}
		if(StringUtils.isBlank(bean.getRetro_period_Year())){
			addActionError(getText("error.retro.period.year"));
		}
	}
	public String retroDownload(){
		String forward="";
		JasperReports reports=new JasperReports();
		try {
			validationretroDownload();
			if(!hasActionErrors()){
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a");
			String date = sdf.format(cal.getTime());
			bean.setFileName("Retro " + date + ".xls");
			bean.setEndDate(bean.getRetro_period_Date()+"/"+bean.getRetro_period_Year());
			bean.setStartDate(service.getEndDate(bean));
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			reports.getRetroReport(bean,branchCode,"xls",bos);
			inputStream = new ByteArrayInputStream(bos.toByteArray());
			forward="stream";
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setYearList(getRetroYearList());
				forward="retroprocess";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	private void validateUploadDocument() {
		int emptyCount=0;
		String emptyRows="";
		boolean empty=false;
		int size=Integer.parseInt(bean.getEndIndex());
		for(int i=(Integer.parseInt(bean.getStartIndex()));i<Integer.parseInt(bean.getEndIndex());i++) {
			if(bean.getUpload()==null){
				this.addActionError(getText("upload.file.required",new String[]{String.valueOf(i+1)}));
			}else{

				if( bean.getUpload().size()>i){
					if("".equals(bean.getDocTypeId().get(i)) && (StringUtils.isNotBlank(bean.getDocDesc().get(i)) || bean.getUpload().get(i).length() >0)){
						this.addActionError(getText("upload.docType.required",new String[]{String.valueOf(i+1)}));
					}
					if(StringUtils.isBlank(bean.getDocDesc().get(i))&&(!"".equals(bean.getDocTypeId().get(i))|| bean.getUpload().get(i).length() >0)){
						this.addActionError(getText("upload.docDec.required",new String[]{String.valueOf(i+1)}));
					}
					if(bean.getUpload().get(i).length() <= 0&&(StringUtils.isNotBlank(bean.getDocDesc().get(i))||!"0".equals(bean.getDocTypeId().get(i)))){
						this.addActionError(getText("upload.file.required",new String[]{String.valueOf(i+1)}));
					}
					if(StringUtils.isBlank(bean.getDocDesc().get(i))&&"0".equals(bean.getDocTypeId().get(i))&& bean.getUpload().get(i).length() <= 0)
					{
						emptyCount++;
					}
					if(!empty&&StringUtils.isBlank(bean.getDocDesc().get(i))&&"0".equals(bean.getDocTypeId().get(i))&& bean.getUpload().get(i).length() <= 0)
					{
						if(size==Integer.parseInt(bean.getEndIndex()))
							size=i;
						emptyRows+=(i+1)+",";

					}else if(emptyRows.length()>0)
					{
						empty=true;
					}
					if(empty) {
						this.addActionError(getText("upload.emptyRows.Required",new String[]{emptyRows.substring(0,emptyRows.lastIndexOf(","))}));
					}
					if((Integer.parseInt(bean.getEndIndex())-Integer.parseInt(bean.getStartIndex()))==emptyCount) {
						this.addActionError(getText("upload.oneRow.Required"));
					}
				}
				else{
					this.addActionError(getText("upload.file.required",new String[]{String.valueOf(i+1)}));
				}
			}
		}
	}
	private void resetRemarks() {
		if(bean.getDescription()!=null && bean.getDescription().size()>0){
			List<String> description = new ArrayList<String>();
			List<String> remark1 = new ArrayList<String>();
			List<String> remark2 = new ArrayList<String>();
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
			for(int i=0;i<bean.getDescription().size();i++){
				description.add(bean.getDescription().get(i));
				remark1.add(bean.getRemark1().get(i));
				remark2.add(bean.getRemark2().get(i));
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
			}
				bean.setDescription(description);
				bean.setRemark1(remark1);
				bean.setRemark2(remark2);
				bean.setRemarkList(result);
				bean.setRemarkCount(Integer.toString(result.size()));
		}
	}
	private void validationRemarks() {
		try{
			List<String> error=new ArrayList<String>();
			for(int i=0;i<bean.getDescription().size();i++){
				if(StringUtils.isBlank(bean.getDescription().get(i))){
					error.add(getText("error.description",new String[]{String.valueOf(i+1)}));
				}
				else if("".equalsIgnoreCase(bean.getRemark1().get(i))){
					error.add(getText("error.remarks1",new String[]{String.valueOf(i+1)}));
				}
				if(StringUtils.isBlank(bean.getRemark2().get(i))){
					//error.add(getText("error.remarks2",new String[]{String.valueOf(i+1)}));
				}
			}
			for(int k=0;k<error.size();k++){
				addActionError(error.get(k));
			}
			error=new ArrayList<String>();
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		
	}
	
	public String newDocDelete(){
		bean.getDocId().remove(bean.getDeleteId());
		int j=1;
		bean.setEndIndex(String.valueOf(Integer.parseInt(bean.getEndIndex())-1));
		List<Integer> docList=new ArrayList<Integer>();
		for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++){
			docList.add(i);
		}
		bean.setDocuList(docList);
		List<String> docDesc = new ArrayList<String>();
		List<String> typeId = new ArrayList<String>();
		for(int i=0;i< bean.getDocId().size();i++){
			int val = Integer.parseInt(bean.getDeleteId());
			if(i<val){
				docDesc.add(bean.getDocDesc().get(i));
				typeId.add(bean.getDocTypeId().get(i));
			}else{
				docDesc.add(bean.getDocDesc().get(j));
				typeId.add(bean.getDocTypeId().get(j));
			}
			j++;
		}
	    bean.setDocDesc(docDesc);
	    bean.setDocTypeId(typeId);
		return "dropdownajax";
	}
}