

package com.maan.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.maan.bean.FaculitivesBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.CommonCalculation;
import com.maan.service.FacultativesService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class FacultativeAction extends ActionSupport implements ModelDriven<FaculitivesBean>{
	private static final long serialVersionUID = 1L;
	private InputStream inputStream;
	final static org.slf4j.Logger logger = LogUtil.getLogger(FacultativeAction.class);
	//Logger logger = LogUtil.getLogger(this.getClass());
	FacultativesService service=new FacultativesService();
	final HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session1 = request.getSession();
	//session.setMaxInactiveInterval(20*60);
	final HttpServletResponse response = ServletActionContext.getResponse();
	Map<String, Object> session = ActionContext.getContext().getSession();
	FaculitivesBean bean=new FaculitivesBean();
	private String productId=(String) session.get("mfrid")==null?"":(String) session.get("mfrid");
	private String userId=(String) session.get("UserId")==null?"":(String) session.get("UserId");
	private String countryId=session.get("countryId")==null?"":session.get("countryId").toString();
	private String branchCode=session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
	private String attachedUW = session.get("ATTACHED_UW") ==null ?"":session.get("ATTACHED_UW").toString();
	DropDownControllor dropDownController = new DropDownControllor();
	UploadAction upload = new UploadAction();
	CommonCalculation calcu = new CommonCalculation();
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public FaculitivesBean getModel() {
		return bean;
	}
    public String getDisableStatus(){
    	return dropDownController.getDisableStatus(bean.getContractNo(),"0");
    }
    public String getDisableStatus1(){
    	return dropDownController.getDisableStatus1(bean.getContractNo(),"0");
    }
    public String getAllocationDisableStatus(){
    	return dropDownController.getAllocationDisableStatus(bean.getContractNo(),"0");
    }
    public String UnderwritingLimit(){
			bean.setMaxiumlimit(dropDownController.getUnderWriterLimmit(bean.getUnderwriter(),(String)session.get("processId"), "1", (String)session.get("DepartmentId")));
		return "dropdownajax";
	}
    
    public List<Map<String,Object>> getUnderwriterList(){
		return dropDownController.getPersonalInfoDropDown(branchCode,"L",productId);
	}
    
    public List<Map<String,Object>> getTerritoryRegionList(){
    	return dropDownController.getTerritoryRegionList(branchCode);
    }
    public List<Map<String,Object>> getInwardBusinessTypelist(){
    	return dropDownController.getConstantDropDown("24");
    }
    public List<Map<String,Object>> getEndosTypelist(){
    	return dropDownController.getConstantDropDown("37");
    }
    public List<Map<String,Object>> getDocType(){
    	return dropDownController.getDocType(branchCode,productId,"RDS");
    }
    
	public  String InitMethod() {
		try {
			bean.setMode("");
			bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
			
			GetDropDownLists(request,session.get("mfrid").toString(),"");
			bean.setCedRetenType("A");
			//bean.setNo_Insurer("1");
			//bean.setSpRetro("Y");
			//bean.setSipml("SI");
			bean.setNoOfInst("1");
			bean.setBranchCode(branchCode);
			bean.setProposalNo("");
			bean.setMenuStatus("N");
			bean.setShortname(service.getShortname(bean));
			bean.setYearList(getYearList());
			List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
			coversubdeptList.add(dropDownController.getSubProfitCentreDropDown("",branchCode,productId));
			bean.setCoversubDepartList(coversubdeptList);
			bean.setCoverxolsubDepartList(coversubdeptList);
			
			 Map<String,Object> doubleMap = new HashMap<String,Object>();
			 List<Map<String,Object>> remarkList=new ArrayList<Map<String,Object>>();
			 doubleMap.put("one",new Double(1.0));
			 remarkList.add(doubleMap);
			 bean.setCoverdeductableList(remarkList);
			 bean.setXolCoverdeductableList(remarkList);
			 bean.setXolLoopcount("0");
			 bean.setLoopcount("0");
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
		return "faculengg1";
	}

	public  String InsertFirstPage(){
		String forward=null;
		try{
			bean.setBroke(new DropDownControllor().getCompanyName(branchCode, bean.getBroker(), "B"));
			bean.setMenuStatus("N");
			bean.setProductId((String) session.get("mfrid"));
			bean.setFacultativeDepartment((String) session.get("DepartmentId"));
			bean.setDepartmentId((String) session.get("DepartmentId"));
			bean.setLoginid(userId);
			bean.setProcessId(session.get("processId").toString());
			bean.setCountryID(countryId);
			bean.setBranchCode(branchCode);
			new DropDownControllor().getOpenPeriod(bean);
			if(StringUtils.isNotBlank(bean.getProStatus()) &&"A".equalsIgnoreCase(bean.getProStatus())){
				getFirstPageSubmitError();
			}
			else{
				getFirstPageSaveError();
			}
			if(!hasActionErrors()){
				bean.setBranchCode(branchCode);
				this.BusinessCalculation(bean);
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
				for(int i=0;i<5;i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				}
				bean.setLossRecordList(list);
				bean.setLossCount("5");
				service.InsertFirstPage(bean,false,false);
				service.ShowSecondPagedata(bean,bean);
				bean.setFireProt("N");
				bean.setMbind("N");
				bean.setMlopYN("N");
				bean.setAlopYN("N");
				bean.setLossRecord("N");
				bean.setReftoHO("N");
				service.ShowSecondPageEditItems(bean);
				bean.setEQWSIndDrop(new DropDownControllor().getConstantDropDown("2"));
				bean.setRiskGradeList(new DropDownControllor().getRiskGradeDropDown(branchCode));
				bean.setCategoryList(new DropDownControllor().getCategoryZoneDropDown(branchCode));
				bean.setProposalNo(bean.getProposalNo());
				bean.setShortname(service.getShortname(bean));
				if(bean.getProStatus().equalsIgnoreCase("R")){
					bean.setContractGendration("Yes");
					bean.setBackmode(bean.getBackmode());
					request.setAttribute("status",bean.getStatus());
					if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
						dropDownController.updateRenewalEditMode(bean.getProposalNo(),"N","");
					}
					dropDownController.updateEditMode(bean.getProposalNo(),"N","");
					forward="SucusssFac";
				}else{
					request.setAttribute("CeddingCompany",new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",productId));//C=>Ceding Compacy	
					bean.setRisklist1(new DropDownControllor().getRiskGradeDropDown(branchCode));
					bean.setCategory(new DropDownControllor().getCategoryZoneDropDown(branchCode));
					bean.setEQWSIndDrop(new DropDownControllor().getConstantDropDown("2"));
					bean.setBonusList(new DropDownControllor().getBonusList());
					if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
						dropDownController.updateRenewalEditMode(bean.getProposalNo(),"BRF",bean.getProposalNo());
						dropDownController.updateEditMode(bean.getProposalNo(),"BRF",bean.getProposalNo());
					}else{
					dropDownController.updateEditMode(bean.getProposalNo(),"BEF",bean.getProposalNo());
					}
					forward ="faculengg2";
				}				
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setShortname(service.getShortname(bean));
				this.GetDropDownLists(request,bean.getProductId(),"");
				bean.setDepartmentId((String) session.get("DepartmentId"));
				resetRemarks();
				resetCoverDeductable();
				resetXolCoverDeductable();
				forward ="faculengg1";
			}
			if(StringUtils.isNotBlank(bean.getEndorsmentno())&& Integer.parseInt(bean.getEndorsmentno())>0){
				List<Integer> docList=new ArrayList<Integer>();
			for(int i=0;i<1;i++){
				docList.add(i);
			}
				bean.setStartIndex("0");
				bean.setEndIndex("1");
			bean.setDocuList(docList);
			if(StringUtils.isBlank(bean.getNodoc())){
				bean.setNodoc("1");
			}
		}
			request.setAttribute("NoInsurar",bean.getNo_Insurer());
			request.setAttribute("NoOfInstallments",bean.getNoOfInst());
			
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	public String viewBonusPopUp(){
		bean.setBranchCode(branchCode);
		bean.setLoginid(userId);
		bean.setProductId(productId);
		bean.setLowClaimBonusList(service.getLowClaimBonusList(bean));
		if(bean.getLowClaimBonusList().size()<=0){
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
			for(int i=0;i<5;i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			}
			bean.setLowClaimBonusList(list);
		}
		return "bonusPopUp";
	}
	
	public String removeRow(){
		List<String> from=new ArrayList<String>();
		List<String> to=new ArrayList<String>();
		List<String> per=new ArrayList<String>();
		bean.getBonusSNo().remove(bean.getDeleteId());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
		for(int i=0;i<bean.getBonusSNo().size();i++){
		Map<String,Object> string = new HashMap<String,Object>();
		string.put("1","1");
		list.add(string);
		}
		int j=1;
		
		for(int k=0;k<bean.getBonusSNo().size();k++){
			int value=Integer.parseInt(bean.getDeleteId());
			if(k<value){
				from.add(bean.getBonusFrom().get(k));
				to.add(bean.getBonusTo().get(k));
				per.add(bean.getBonusLowClaimBonus().get(k));
			}
			else{
			if(StringUtils.isNotBlank(bean.getBonusFrom().get(j))){
				from.add(bean.getBonusFrom().get(j));
			}
			if(StringUtils.isNotBlank(bean.getBonusFrom().get(j))){
				to.add(bean.getBonusTo().get(j));	
						}
			if(StringUtils.isNotBlank(bean.getBonusLowClaimBonus().get(j))){
				per.add(bean.getBonusLowClaimBonus().get(j));
			}
			}
			j++;
		}
		bean.setBonusFrom(from);
		bean.setBonusTo(to);
		bean.setBonusLowClaimBonus(per);
		bean.setLowClaimBonusList(list);
		return "bonusPopUp";
	}
	
	public String insBonusDetails(){
		String forward="streamResult";
		validation();
		if(!hasActionErrors()){
			bean.setBranchCode(branchCode);
			bean.setLoginid(userId);
			bean.setProductId(productId);
			bean.setDepartmentId((String) session.get("DepartmentId"));
			String result = service.LowClaimBonusInser(bean);
			String value="<script type='text/javascript'>window.close();</script>";
			byte[] byteArray = value.getBytes();
			inputStream=new ByteArrayInputStream(byteArray);
		}
		else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
			for(int i=0;i<bean.getBonusSNo().size();i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			}
			bean.setLowClaimBonusList(list);
			forward = "bonusPopUp";
		}
		return  forward;
	}
	
	private void validation() {
		int j=1;
		int k=2;
		if(StringUtils.isBlank(bean.getBonusTypeId())){
			addActionError(getText("bonus.error.type"));
			}
			else{
			for(int i=0;i<bean.getBonusSNo().size();i++){
				
				if(StringUtils.isBlank(bean.getBonusFrom().get(i)) || StringUtils.isBlank(bean.getBonusTo().get(i)) || StringUtils.isBlank(bean.getBonusLowClaimBonus().get(i))){
					if(StringUtils.isBlank(bean.getBonusFrom().get(i)) && StringUtils.isNotBlank(bean.getBonusTo().get(i)) && StringUtils.isNotBlank(bean.getBonusLowClaimBonus().get(i))){
						addActionError(getText("bonus.error.from.val",new String[] { String.valueOf(i + 1) }));
					}
					if(StringUtils.isNotBlank(bean.getBonusFrom().get(i)) && StringUtils.isBlank(bean.getBonusTo().get(i)) && StringUtils.isNotBlank(bean.getBonusLowClaimBonus().get(i))){
						addActionError(getText("bonus.error.to.val",new String[] { String.valueOf(i + 1) }));
					}
					if(StringUtils.isNotBlank(bean.getBonusFrom().get(i)) && StringUtils.isNotBlank(bean.getBonusTo().get(i)) && StringUtils.isBlank(bean.getBonusLowClaimBonus().get(i))){
						addActionError(getText("bonus.error.lcb.val",new String[] { String.valueOf(i + 1) }));
					}
					if(StringUtils.isBlank(bean.getBonusFrom().get(i)) && StringUtils.isBlank(bean.getBonusTo().get(i)) && StringUtils.isNotBlank(bean.getBonusLowClaimBonus().get(i))){
						addActionError(getText("bonus.error.from.val",new String[] { String.valueOf(i + 1) }));
						addActionError(getText("bonus.error.to.val",new String[] { String.valueOf(i + 1) }));
					}
					if(StringUtils.isNotBlank(bean.getBonusFrom().get(i)) && StringUtils.isBlank(bean.getBonusTo().get(i)) && StringUtils.isBlank(bean.getBonusLowClaimBonus().get(i))){
						addActionError(getText("bonus.error.to.val",new String[] { String.valueOf(i + 1) }));
						addActionError(getText("bonus.error.lcb.val",new String[] { String.valueOf(i + 1) }));
					}
					if(StringUtils.isBlank(bean.getBonusFrom().get(i)) && StringUtils.isNotBlank(bean.getBonusTo().get(i)) && StringUtils.isBlank(bean.getBonusLowClaimBonus().get(i))){
						addActionError(getText("bonus.error.from.val",new String[] { String.valueOf(i + 1) }));
						addActionError(getText("bonus.error.lcb.val",new String[] { String.valueOf(i + 1) }));
					}
				}
				if(StringUtils.isNotBlank(bean.getBonusFrom().get(i)) && StringUtils.isNotBlank(bean.getBonusTo().get(i)) && StringUtils.isNotBlank(bean.getBonusLowClaimBonus().get(i))){
					if(Double.parseDouble(bean.getBonusFrom().get(i).replace(",", ""))>Double.parseDouble(bean.getBonusTo().get(i).replace(",", ""))){
						addActionError(getText("bonus.loss.error.from.to.ratio.limit",new String[] { String.valueOf(i + 1) }));
					}
					if("LR".equals(bean.getBonusTypeId())){
						if(Double.parseDouble(bean.getBonusFrom().get(i).replace(",", ""))>1000){
							addActionError(getText("bonus.loss.from.ratio.limit",new String[] { String.valueOf(i + 1) }));
						}
						if(Double.parseDouble(bean.getBonusTo().get(i).replace(",", ""))>1000){
							addActionError(getText("bonus.loss.to.ratio.limit",new String[] { String.valueOf(i + 1) }));
						}
					}
					if(Double.parseDouble(bean.getBonusLowClaimBonus().get(i).replace(",", ""))>100){
						addActionError(getText("bonus.loss.lcb.ratio.limit",new String[] { String.valueOf(i + 1) }));
					}
					if(bean.getBonusSNo().size()>j){
						if(StringUtils.isNotBlank(bean.getBonusFrom().get(j)) ){
						double from =Double.parseDouble(bean.getBonusFrom().get(j).replace(",", ""));
						double to = Double.parseDouble(bean.getBonusTo().get(i).replace(",", ""));
						if(to>from){
							addActionError(getText("bonus.from.to.limit",new String[] { String.valueOf(i + 1) ,String.valueOf(i + 2)} ));
						}
						}
					}
					}
					j++;
					k++;
					}
						
			}
	}
	public  String SavePage(){
		String forward=null;
		try{
			bean.setLoginid(userId);
			bean.setBranchCode(branchCode);
			bean.setShortname(service.getShortname(bean));
			bean.setProcessId(session.get("processId").toString());
			bean.setProductId((String) session.get("mfrid"));
			bean.setFacultativeDepartment((String) session.get("DepartmentId"));
			bean.setDepartmentId((String) session.get("DepartmentId"));
			//getSaveFirstPage();
			if(StringUtils.isNotBlank(bean.getProStatus()) &&"A".equalsIgnoreCase(bean.getProStatus())){
				getFirstPageSubmitError();
			}
			else{
				getFirstPageSaveError();
			}
			if(!hasActionErrors()){
				this.BusinessCalculation(bean);
				if(service.InsertFirstPage(bean,false,false)){
					bean.setContractGendration("Yes");
					bean.setBackmode(bean.getBackmode());
					request.setAttribute("status",bean.getStatus());
					forward="SucusssFac";
					if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
						dropDownController.updateRenewalEditMode(bean.getProposalNo(),"N","");
					}
					dropDownController.updateEditMode(bean.getProposalNo(),"N","");
				}
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				this.GetDropDownLists(request,bean.getProductId(),"");
				bean.setDepartmentId((String) session.get("DepartmentId"));
				resetRemarks();
				resetCoverDeductable();
				resetXolCoverDeductable();
				forward="faculengg1";
			}
			
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	
	private void getFirstPageSaveError() {
		try{
			 if(StringUtils.isNotBlank(bean.getType()) && "1".equalsIgnoreCase(bean.getType()) ){
				 if(StringUtils.isNotBlank(bean.getTotalDeductible())){
						String ans = calcu.calculateFacultative(bean,"TotDetuctible",0);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getTotalDeductible().replaceAll(",",""))){
							addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setTotalDeductible(ans);
							bean.setDeductible(bean.getTotalDeductible().replace(",", ""));
						}
				}
				if(StringUtils.isNotBlank(bean.getTotalCoverage())){
						String ans = calcu.calculateFacultative(bean,"TotCoverage",0);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getTotalCoverage().replaceAll(",",""))){
							addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setTotalCoverage(ans);
							bean.setDeductibleFacXol(bean.getTotalCoverage().replace(",", ""));
							bean.setSumInsured(bean.getTotalCoverage().replace(",", ""));
						}
				}
				if(StringUtils.isNotBlank(bean.getTotalGWPI())){
					String ans = calcu.calculateFacultative(bean,"TotGwpi",0);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getTotalGWPI().replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.setTotalGWPI(ans);
						bean.setGwpi(bean.getTotalGWPI().replace(",", ""));
					}
				}
			}
			else if(StringUtils.isNotBlank(bean.getType()) && "2".equalsIgnoreCase(bean.getType()) ){
				
				bean.setGwpi(bean.getXoltotalGWPI().replace(",", ""));	
			}
			final Validation val = new Validation();
			boolean flaging = true;
			if (StringUtils.isNotBlank(bean.getEndorsmentno()) && Integer.parseInt(bean.getEndorsmentno()) > 0) {
				if (StringUtils.isBlank(bean.getEndorsmenttype())) {
					addActionError(getText("end.type.error"));
				}
			}
			if (val.isSelect(bean.getSubProfitCenter()).equalsIgnoreCase("")) {
				addActionError(getText("errors.subProfitCenter.required"));
			}else{
				bean.setSubProfitCenter((bean.getSubProfitCenter()).replaceAll(" ", ""));
			}

			if (val.isNull(bean.getUnderwriter()).equalsIgnoreCase("0")) {
				addActionError(getText("errors.underwriter.required"));
			}
			boolean cedCheck = true;
			if (val.isNull(bean.getMaxiumlimit()).equalsIgnoreCase("")) {
				addActionError(getText("errors.ourmaxiumlimit.required"));
				cedCheck = false;
			} else {
				bean.setMaxiumlimit((bean.getMaxiumlimit().replaceAll(",", "")));
				if (val.isValidNo(bean.getMaxiumlimit()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.ourmaxiumlimit.invalid"));
					cedCheck = false;
				} else {
					String uwLimit = dropDownController.getUnderWriterLimmit(bean.getUnderwriter(), (String) session.get("processId"), "1", (String) session.get("DepartmentId"));
					uwLimit = uwLimit.replaceAll(",", "");
					if (Double.parseDouble(uwLimit) == 0) {
						addActionError(getText("error.maxLimitProduct.config",new String[] { uwLimit }));
						cedCheck = false;
					} else if (Double.parseDouble(bean.getMaxiumlimit()) > Double.parseDouble(uwLimit)) {
						addActionError(getText("error.maxLimitProduct.exceedLimit",new String[] { uwLimit }));
						cedCheck = false;
					}
				}
			}
			Map<String, Object> map = null;
			List<Map<String, Object>> list = service.getValidation(bean, 1);
			if (list != null && list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			}

			if (val.isSelect(bean.getPolicyBranch()).equalsIgnoreCase("")) {
				addActionError(getText("errors.polBranch.required"));
			}
			if (val.isSelect(bean.getType()).equalsIgnoreCase("")) {
				addActionError(getText("errors.Type.required"));
			}
			if (bean.getCedingCompany().equalsIgnoreCase("-1")) {
				addActionError(getText("errors.cedingCompany.required"));
			}
			if (val.isSelect(bean.getBroker()).equalsIgnoreCase("")) {
				addActionError(getText("errors.broker.required"));
			}

			if (val.isNull(bean.getInceptionDate()).equalsIgnoreCase("")) {
				addActionError(getText("errors.InceptionDate.required"));
			} else if ("INVALID".equalsIgnoreCase(val.checkDate(bean.getInceptionDate()))) {
				addActionError(getText("errors.InceptionDate.Error"));
			} else if (!"".equals(bean.getRenewal_Contract_no()) && map != null) {
				if ("Invalid".equalsIgnoreCase(val.getDateValidate((String) map.get("EXPIRY_DATE"), bean.getInceptionDate()))) {
					addActionError(getText("errors.InceptionDate.invalid"));
				} else {
					bean.setRenewalFlag("NEWCONTNO");
				}
			}
			if (val.isNull(bean.getExpiryDate()).equalsIgnoreCase("")) {
				addActionError(getText("errors.ExpiryDate.required"));
			} else if ("INVALID".equalsIgnoreCase(val.checkDate(bean
					.getExpiryDate()))) {
				addActionError(getText("errors.ExpiryDate.Error"));
			}
			if (!bean.getInceptionDate().equalsIgnoreCase("")&& !bean.getExpiryDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getInceptionDate(),
						bean.getExpiryDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.expDate.check"));
				}
			}
			if( !"".equalsIgnoreCase(bean.getAccountDate())){
				if("INVALID".equalsIgnoreCase(val.checkDate(bean.getAccountDate()))){
					addActionError(getText("errors.AccountDate.Error"));
				}
				if (!bean.getExpiryDate().equalsIgnoreCase("")&& !bean.getAccountDate().equalsIgnoreCase("")) {
					if (Validation.ValidateTwo(bean.getAccountDate(),bean.getExpiryDate()).equalsIgnoreCase("Invalid")) {
						addActionError(getText("error.accDate.check"));
					}
				}
			}
			if (val.isSelect(bean.getYear()).equalsIgnoreCase("")) {
				addActionError(getText("errors.year.required"));
			} else if (!"".equals(bean.getRenewal_Contract_no())
					&& map != null
					&& Integer.parseInt((String) map.get("UW_YEAR")) >= Integer
							.parseInt(bean.getYear())) {
				//addActionError(getText("errors.year.invalid"));
			}
			if (val.isNull(bean.getUsCurrencyRate()).equalsIgnoreCase("")) {
				addActionError(getText("errors.usCurrencyRate.required"));
				cedCheck = false;
			}
			boolean spflag = true;
			if(val.isSelect(bean.getOriginalCurrency()).equalsIgnoreCase("")){
				addActionError(getText("errors.originalCurrency.required"));
			}
			if (val.isNull(bean.getSpRetro()).equalsIgnoreCase("")) {
				addActionError(getText("errors.SpRetro.error"));
				spflag = false;
			}

			if (StringUtils.isBlank(bean.getLocIssued())) {
				addActionError(getText("locissued.error"));
			}

			double amt = 0.0;
			/*if (val.isNull(bean.getSipml()).equalsIgnoreCase("")) {
				addActionError(getText("errors.sipml.required"));
			} else {
				logger.info(" SIPML =>" + bean.getSipml());
				if (bean.getSipml().equalsIgnoreCase("PML")) {
					if (val.isNull(bean.getPml()).equalsIgnoreCase("")) {
						addActionError(getText("errors.pml.required"));
					} else if ("INVALID".equalsIgnoreCase(val.percentageValid(bean.getPml().trim()))) {
						addActionError(getText("errors.pml.percentages"));
					} else if (val.percentageValid(bean.getPml().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("errors.pml.greater"));
					}

				}
			}*/
			if (val.isNull(bean.getNr()).equalsIgnoreCase("")) {
				addActionError(getText("errors.nr.required"));
			}

			boolean gwpiFlag = true;
			
			if (StringUtils.isNotBlank(bean.getType())&& "2".equalsIgnoreCase(bean.getType())) {
				//bean.setSumInsured("");
				if (!val.isNull(bean.getDeductibleFacXol()).equalsIgnoreCase("")) {
					bean.setDeductibleFacXol((bean.getDeductibleFacXol()).replaceAll(",", ""));
					if (val.isValidNo(bean.getDeductibleFacXol()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.deductibleFacXOL.Invalid"));
						gwpiFlag = false;
					}
				}
				/*if (bean.getSipml().equalsIgnoreCase("PML")) {
					if (StringUtils.isBlank(bean.getDeductibleFacXolPml())) {
						addActionError(getText("cover.pml"));
					} else {
						bean.setDeductibleFacXolPml(bean
								.getDeductibleFacXolPml().replaceAll(",", ""));
					}
				}*/
			}
			boolean cedflag = true;
			if (StringUtils.isNotBlank(bean.getType())&& "1".equalsIgnoreCase(bean.getType())) {
				if (val.isNull(bean.getCedRetenType()).equalsIgnoreCase("")) {
					addActionError(getText("error.cedRentType.required"));
					cedflag = false;
				}
			}
			if(StringUtils.isNotBlank(bean.getType()) && "2".equalsIgnoreCase(bean.getType())){
			/*if(StringUtils.isNotBlank(bean.getSipml()) && !"LL".equalsIgnoreCase(bean.getSipml())) {*/
				if (val.isNull(bean.getPremiumrate()).equalsIgnoreCase("")) {
					addActionError(getText("errors.premium1.required"));
					gwpiFlag = false;
				} else if (val.percentageValid(bean.getPremiumrate().trim())
						.equalsIgnoreCase("less")) {
					addActionError(getText("errors.premium1.percentagesless"));
					gwpiFlag = false;
				} else if (val.percentageValid(bean.getPremiumrate().trim())
						.equalsIgnoreCase("greater")) {
					addActionError(getText("errors.premium1.percentagesgreater"));
					gwpiFlag = false;
				}
			}
			if (StringUtils.isNotBlank(bean.getType())&& "1".equalsIgnoreCase(bean.getType())) {
				/*if (val.isNull(bean.getSumInsured()).equalsIgnoreCase("")) {
					addActionError(getText("errors.sumInsured.required"));
					cedCheck = false;
					gwpiFlag = false;
				} else {
					bean.setSumInsured((bean.getSumInsured()).replaceAll(",",""));
					if (val.isValidNo(bean.getSumInsured().trim()).equalsIgnoreCase("INVALID")) {
						if ("4".equalsIgnoreCase(session.get("DepartmentId").toString())) {
							addActionError(getText("errors.turn.sumInsured.percentages"));
						} else {
							addActionError(getText("errors.sumInsured.percentages"));
						}
						cedCheck = false;
						gwpiFlag = false;
					} else {
						if (bean.getSipml().equalsIgnoreCase("SI")) {
							amt = Double.parseDouble(bean.getSumInsured());
						}
					}
				}
				/*if (bean.getSipml().equalsIgnoreCase("PML")) {
					if (StringUtils.isBlank(bean.getSumInsuredPml())) {
						if ("4".equalsIgnoreCase(session.get("DepartmentId")
								.toString())) {
							addActionError(getText("errors.turn.sumInsured.pml"));
						} else {
							addActionError(getText("sum.pml"));
						}
					} else {
						bean.setSumInsuredPml(bean.getSumInsuredPml()
								.replaceAll(",", ""));
					}
				}*/
			}
			/*if (val.isNull(bean.getGwpi()).equalsIgnoreCase("")) {
				addActionError(getText("errors.gwpi.required"));
				gwpiFlag = false;
			} else {
				bean.setGwpi((bean.getGwpi()).replaceAll(",", ""));
				if (val.isValidNo(bean.getGwpi().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.gwpi.percentages"));
					gwpiFlag = false;
				}
			}*/
			//if(StringUtils.isNotBlank(bean.getSipml()) && !"LL".equalsIgnoreCase(bean.getSipml())) {
			if(StringUtils.isNotBlank(bean.getType()) && "2".equalsIgnoreCase(bean.getType())){
			if (gwpiFlag&& ("".equalsIgnoreCase(bean.getContractNo()) || "0".equalsIgnoreCase(bean.getContractNo()))) {
				double si = bean.getType().equalsIgnoreCase("1") ? Double.parseDouble(bean.getSumInsured()) : Double.parseDouble(bean.getDeductibleFacXol());
				double rate = Double.parseDouble(bean.getPremiumrate());
				if (rate != 0) {
					double preAmt = (si * rate) / 100.0;
					double pre10Per = (((si * rate) / 100.0) * 10.0) / 100.0;
					double gwpi = Double.parseDouble(bean.getGwpi());
					logger.info("Sum Insured 100%>" + si);
					logger.info("Premium Rate =>" + rate);
					logger.info("Premium Amount=>" + preAmt);
					logger.info("(preAmt+pre10Per)=>" + (preAmt + pre10Per));
					logger.info("(preAmt-pre10Per)=>" + (preAmt - pre10Per));
					logger.info("GWPI 100%=>" + gwpi);
					if (!(gwpi >= (preAmt - pre10Per) && gwpi <= (preAmt + pre10Per))) {
						addActionError(getText("errors.gwpi.exceedPremiumLimit"));
					}
				}
			}
		}
			if (val.isNull(bean.getNoOfInst()).equalsIgnoreCase("")) {
				addActionError(getText("errors.noOfInstallments.required"));
			} else if ("INVALID".equalsIgnoreCase(val.isValidNo(bean.getNoOfInst()))) {
				addActionError(getText("errors.noOfInstallments.invalid"));
			} else if (Integer.parseInt(bean.getNoOfInst()) <= 0) {
				addActionError(getText("errors.noOfInstallments.grZero"));
			}
			if (StringUtils.isBlank(bean.getReceiptofPayment())) {
				addActionError(getText("payment.days.required"));
			}
			final String proStatus = val.isSelect(bean.getProStatus());

			if (proStatus.equalsIgnoreCase("")) {
				addActionError(getText("errors.Status.percentages"));
			}
			flaging = true;
			double cedPer = 0.0;
			if (val.isNull(bean.getShWt()).equalsIgnoreCase("")) {
				addActionError(getText("errors.shWt.required"));
				flaging = false;
				cedCheck = false;
			} else if ("INVALID".equalsIgnoreCase(val.percentageValid(bean.getShWt().trim()))) {
				addActionError(getText("errors.shWt.percentages"));
				flaging = false;
				cedCheck = false;
			} else if (val.percentageValid(bean.getShWt().trim()).equalsIgnoreCase("greater")) {
				addActionError(getText("errors.shWt.greater"));
				flaging = false;
				cedCheck = false;
			} 

			if ("A".equalsIgnoreCase(proStatus)) {
				if ("INVALID".equalsIgnoreCase(val.percentageNewValid(bean.getShWt().trim()))) {
					addActionError(getText("errors.shWt.percentages"));
					flaging = false;
					cedCheck = false;
				}
				if (val.isNull(bean.getShSd()).equalsIgnoreCase("")) {
					addActionError(getText("error.shareSign.required"));
					flaging = false;
					cedCheck = false;
				} else if ("INVALID".equalsIgnoreCase(val.percentageValid(bean.getShSd().trim()))) {
					addActionError(getText("error.shareSign.per"));
					flaging = false;
					cedCheck = false;
				} else if (val.percentageValid(bean.getShSd().trim()).equalsIgnoreCase("greater")) {
					addActionError(getText("errors.shSd.greater"));
					flaging = false;
					cedCheck = false;
				} else {
					cedPer = Double.parseDouble(bean.getShSd());
				}
				if (flaging == true) {
					if (Double.parseDouble(bean.getShSd().equalsIgnoreCase("") ? "0": bean.getShSd()) > Double.parseDouble(bean.getShWt().equalsIgnoreCase("") ? "0" : bean.getShWt())) {
						addActionError(getText("error.shareSign.invalid"));
						cedCheck = false;
					}
				}
				if (cedCheck) {
					double amount = ((amt * (cedPer / 100.0)) / Double.parseDouble(bean.getUsCurrencyRate()));
					double maxlimit = Double.parseDouble(bean.getMaxiumlimit());
					logger.info("Cedent Amount=>" + amount);
					logger.info("Max Limit=>" + maxlimit);
					if (amount > maxlimit) {
						addActionError(getText("error.accAmtlessUWAmt"));
					}
				}
			}
			if (StringUtils.isNotBlank(bean.getGwpi())
					&& StringUtils.isNotBlank(bean.getShSd())) {
				final DecimalFormat twoDigit = new DecimalFormat("###0.00");
				final double dvalue = (Double.parseDouble(bean.getGwpi())* (Double.parseDouble(bean.getShSd())) / 100);
				logger.info("Gwpi Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final double valu = Double.parseDouble(twoDigit.format(dround));// A
				logger.info("Formated Value=>" + valu);
				if (StringUtils.isNotBlank(bean.getContractNo())) {
					double sumInst = new DropDownControllor().getSumOfInstallmentBooked(bean.getContractNo(),
									"0");// B
					if (valu < sumInst) {
						addActionError(getText("error.installment.premiumbooked"));
					}
				}
			}
			if (StringUtils.isNotBlank(bean.getNoOfInst())) {
				int count = new DropDownControllor().getCountOfInstallmentBooked(bean.getContractNo(), "0");
				if (Double.parseDouble(bean.getNoOfInst()) < count) {
					addActionError(getText("error.no.installment.premiumbooked"));
				}
			}
			validationContract();
			validationRemarks();
			if(StringUtils.isNotBlank(bean.getType())&&"1".equalsIgnoreCase(bean.getType()) ){
			validationCoverDeductable();
			}else{
				validationXolCoverDeductable();
			}

		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}

	}
	
	public  String InsertSecondPage(){
		String forward=null;
		try{
			bean.setLoginid(userId);
			bean.setProductId((String) session.get("mfrid"));
			bean.setFacultativeDepartment((String) session.get("DepartmentId"));
			bean.setDepartmentId((String) session.get("DepartmentId"));
			bean.setBranchCode(branchCode);
			if(StringUtils.isNotBlank(bean.getContractNo()))
			bean.setPreviousendoDate(new DropDownControllor().getpreamendDate(bean.getContractNo()));
			new DropDownControllor().getOpenPeriod(bean);
			if("A".equalsIgnoreCase(bean.getProStatus())){
				getSecondSubmitError();
				validationBonusSubmit("save");
			}
			else{
				getSecondSaveError();
			}	
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && "Y".equalsIgnoreCase(bean.getDocStatus())) {
				validateUploadDocument();
			}
			if(!hasActionErrors()){
				if(service.InsertSecondPage(bean,true)){
					if("Y".equalsIgnoreCase(bean.getEndorsementStatus())){
					DropDownControllor dropDownController = new DropDownControllor();
					dropDownController.updatepositionMasterEndtStatus(bean.getProposalNo(),bean.getProductId(),bean.getEndorsementDate(),"");
					}
					
					bean.setContractGendration("Yes");
					bean.setBackmode(bean.getBackmode());
					request.setAttribute("status",bean.getStatus());
					forward="SucusssFac";
				}
				if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && "Y".equalsIgnoreCase(bean.getDocStatus())) {
				bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
				String result=service.doUpload(bean,branchCode,userId,bean.getUpload(),bean.getUploadFileName());
				}
				bean.setMode("");
				if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
					dropDownController.updateRenewalEditMode(bean.getProposalNo(),"N","");
				}
				dropDownController.updateEditMode(bean.getProposalNo(),"N","");
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				resetRetroContracts();
				resetRemarks();
				bean.setRisklist1(new DropDownControllor().getRiskGradeDropDown(branchCode));
				bean.setCategory(new DropDownControllor().getCategoryZoneDropDown(branchCode));
				bean.setEQWSIndDrop(new DropDownControllor().getConstantDropDown("2"));
				bean.setBonusList(new DropDownControllor().getBonusList());
				service.ShowSecondPagedata(bean,bean);
				List<String> dateList=bean.getInstalmentDateList();
				List<String> premiumList=bean.getInstallmentPremium();
				List<String> paymentDay=bean.getPaymentDueDays();
				this.BusinessCalculation(bean);
				//To show current values entered on the screen instead of values from DB.
				bean.setInstallmentPremium(premiumList);
				bean.setInstalmentDateList(dateList);
				bean.setPaymentDueDays(paymentDay);
				this.GetDropDownLists(request,bean.getProductId(),"");
				forward="faculengg2";
				if(StringUtils.isNotBlank(bean.getEndorsmentno())&& Integer.parseInt(bean.getEndorsmentno())>0){
					List<Integer> docList=new ArrayList<Integer>();
					if(bean.getDocId().size()>0){
						for(int i=0;i<bean.getDocId().size();i++){
							docList.add(i);
						}
					}
					else{
					for(int i=0;i<1;i++){
						docList.add(i);
					}
					}
				bean.setDocuList(docList);
				
			}
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
				if(bean.getLossYear()!=null){
				for(int i=0;i<bean.getLossYear().size();i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				}
				}else{
					for(int i=0;i<5;i++){
						Map<String,Object> string = new HashMap<String,Object>();
						string.put("1","1");
						list.add(string);
						}
				}
				bean.setLossRecordList(list);
				
			}
			//bean.setMode("");
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	private void resetRetroContracts() {
		if(StringUtils.isNotBlank(bean.getNo_Insurer()) && Integer.parseInt(bean.getNo_Insurer())>0){
			List<List<Map<String,Object>>> uwlList=new ArrayList<List<Map<String,Object>>>();
			List<List<Map<String,Object>>> retrolList=new ArrayList<List<Map<String,Object>>>();
			List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
			for(int i=0;i<Integer.parseInt(bean.getNo_Insurer());i++){
				FaculitivesBean tempBean=new FaculitivesBean();
				tempBean.setProductId("4");
				tempBean.setInceptionDate(bean.getInceptionDate());
				tempBean.setBranchCode(branchCode);
				if(bean.getRetroTypeValList()!=null){
					if(i<bean.getRetroTypeValList().size()){
						tempBean.setRetroType(bean.getRetroTypeValList().get(i)==null?"":bean.getRetroTypeValList().get(i));
					}
				}else{
					tempBean.setRetroType("");
				}
				uwlList.add(service.getRetroContractDetailsList(tempBean, 1));
				tempBean.setYear(bean.getUwYearValList().get(i));
				if(0==i){
					retroDupList.add(service.getRetroContractDetailsList(tempBean, 3));
				}
					retrolList.add(service.getRetroContractDetailsList(tempBean, 2));
				
			}
			bean.setUwlList(uwlList);
			bean.setUwYearValList(bean.getUwYearValList());
			bean.setRetrolList(retrolList);
			bean.setRetroDupList(retroDupList);
		}
	}
	
	public  String SavePageSecond(){
		String forward=null;
		bean.setLoginid(userId);
		try{
			bean.setProductId((String) session.get("mfrid"));
			bean.setFacultativeDepartment((String) session.get("DepartmentId"));
			bean.setDepartmentId((String) session.get("DepartmentId"));
			bean.setBranchCode(branchCode);
			if("A".equalsIgnoreCase(bean.getProStatus())){
				getSecondSubmitError();
				validationBonusSubmit("save");
			}
			else{
				getSecondSaveError();
			}			
			if(!hasActionErrors()){
				
				service.InsertSecondPage(bean,false);
				bean.setContractGendration("Yes");
				bean.setBackmode(bean.getBackmode());
				request.setAttribute("status",bean.getStatus());
				if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
					dropDownController.updateRenewalEditMode(bean.getProposalNo(),"N","");
				}
				dropDownController.updateEditMode(bean.getProposalNo(),"N","");
				forward="SucusssFac";
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				resetRetroContracts();
				resetRemarks();
				bean.setRisklist1(new DropDownControllor().getRiskGradeDropDown(branchCode));
				bean.setCategory(new DropDownControllor().getCategoryZoneDropDown(branchCode));
				bean.setEQWSIndDrop(new DropDownControllor().getConstantDropDown("2"));
				bean.setBonusList(new DropDownControllor().getBonusList());
				service.ShowSecondPagedata(bean,bean);
				List<String> dateList=bean.getInstalmentDateList();
				List<String> premiumList=bean.getInstallmentPremium();
				List<String> paymentDay=bean.getPaymentDueDays();
				this.BusinessCalculation(bean);
				//To show current values entered on the screen instead of values from DB.
				bean.setInstallmentPremium(premiumList);
				bean.setInstalmentDateList(dateList);
				bean.setPaymentDueDays(paymentDay);
				this.GetDropDownLists(request,bean.getProductId(),"");
				if(bean.getLossYear()!=null){
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
					if(bean.getLossYear()!=null){
					for(int i=0;i<bean.getLossYear().size();i++){
					Map<String,Object> string = new HashMap<String,Object>();
					string.put("1","1");
					list.add(string);
					}
					}else{
						for(int i=0;i<5;i++){
							Map<String,Object> string = new HashMap<String,Object>();
							string.put("1","1");
							list.add(string);
							}
					}
					bean.setLossRecordList(list);
				//bean.setLossinsuredName(val);
				}
				forward="faculengg2";
			}
			//bean.setMode("");
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	private void getSecondSaveError() {
		try{
			final Validation validation=new Validation();
			logger.info("Risk grade==>"+bean.getRiskGrade());
			/*if(bean.getSipml().equalsIgnoreCase("SI") && bean.getType().equalsIgnoreCase("1")) {
				if(validation.isNull(bean.getSumInsuredOurShare()).equalsIgnoreCase("")) {
					addActionError(getText("errors.sumInsuredOurShare.required"));
				} else if("INVALID".equalsIgnoreCase(validation.isValidNo(bean.getSumInsuredOurShare()))) {
					addActionError(getText("errors.sumInsuredOurShare.invalid"));
				}
			}*/
			if(validation.isNull(bean.getGwpiOurShare()).equalsIgnoreCase("")) {
				addActionError(getText("errors.gwpiOurShare.required"));
			} else if("INVALID".equalsIgnoreCase(validation.isValidNo(bean.getGwpiOurShare()))) {
				addActionError(getText("errors.gwpiOurShare.invalid"));
			}
			
			/*	if(validation.isNull(bean.getTplOurShare()).equalsIgnoreCase("")){
					addActionError(getText("errors.tplOurShare.required"));
				} else if("INVALID".equalsIgnoreCase(validation.isValidNo(bean.getTplOurShare()))) {
					addActionError(getText("errors.tplOurShare.invalid"));
				}*/

			boolean tata = false;
			double totalInstPremium=0.0;
			if (!validation.isNull(bean.getInstalmentDateList().get(0)).equalsIgnoreCase("")) {
				if (validation.ValidateINstallDates(bean.getInceptionDate(),bean.getInstalmentDateList().get(0)).equalsIgnoreCase("Invalid")) {
					tata = true;
				}
			}
			BigDecimal bd = new BigDecimal(totalInstPremium).setScale(2, RoundingMode.HALF_EVEN);
			totalInstPremium = bd.doubleValue();
		   if (tata == true) {
				addActionError(getText("Error.Select.AfterInceptionDate"));
			}
		   if("Y".equalsIgnoreCase(bean.getEndorsementStatus())) {
		   bean.setAccountDate((new DropDownControllor().getAcceptanceDate(bean.getProposalNo())));
		   bean.setMaxDate(Validation.getMaxDateValidate(bean.getAccountDate(), bean.getPreviousendoDate()));
			final String endorseDate=validation.checkDate(bean.getEndorsementDate());
			if (validation.isNull(bean.getEndorsementDate()).equalsIgnoreCase("")) {
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.endoDate.required"));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.rectification.required"));
				}
				
			} else if (endorseDate.equalsIgnoreCase("INVALID")) {
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.endoDate.check"));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.rectification.check"));
				}
				
			} else  if ("Invalid".equalsIgnoreCase(Validation.ValidateTwo(bean.getMaxDate(), bean.getEndorsementDate()))) {
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.endoDate.invalid",new String[] {bean.getAccountDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.rectificationDate.invalid",new String[] {bean.getAccountDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}
				
			}
		   if(!validation.isNull(bean.getOpstartDate()).equalsIgnoreCase("")&& !validation.isNull(bean.getOpendDate()).equalsIgnoreCase("") && !validation.isNull(bean.getEndorsementDate()).equalsIgnoreCase("")){
				if(new DropDownControllor().Validatethree(branchCode, bean.getEndorsementDate())==0){
					addActionError(getText("errors.open.period.date.endo",new String[] {bean.getOpenPeriodDate()}));
				}
			}
		   }
		   if("7".equals(bean.getDepartmentId())||"9".equals(bean.getDepartmentId())){
			if(validation.isNull(bean.getFireProt()).equalsIgnoreCase("")){
				addActionError(getText("errors.fireProt.required"));
			}
			
			if(validation.isNull(bean.getMbind()).equalsIgnoreCase("")){			
				addActionError(getText("errors.mbind.required"));
			}
			if(validation.isNull(bean.getMlopYN()).equalsIgnoreCase("")) {
				addActionError(getText("errors.mlop.required"));
			}
			if(validation.isNull(bean.getAlopYN()).equalsIgnoreCase("")) {
				addActionError(getText("errors.alop.required"));
			}
			/*if(validation.isSelect(bean.getCategoryZone()).equalsIgnoreCase("")){
				addActionError(getText("errors.categoryZone.required"));
			}*/
			boolean wseq=true;
			if(validation.isNull(bean.getEqwsInd()).equalsIgnoreCase("-1")) {
				addActionError(getText("errors.eqwsInd.required"));
				wseq=false;
			}
			if(wseq){
				if(validation.isNull(bean.getWsThreat()).equalsIgnoreCase("")) {
					addActionError(getText("errors.wsThreat.required"));
				}
				if(validation.isNull(bean.getEqThreat()).equalsIgnoreCase("")){
					addActionError(getText("errors.eqThreat.required"));
				}
			}
		   }
			final int LoopCount=Integer.parseInt(bean.getNo_Insurer());
			double totPer=0.0;
			boolean flag=true;
			List<List<Map<String,Object>>> uwlList = new ArrayList<List<Map<String,Object>>>();
			List<List<Map<String,Object>>> retrolList = new ArrayList<List<Map<String,Object>>>();
			List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
			if(LoopCount!=0){
				if(validation.isNull(bean.getRetper()).equalsIgnoreCase("")){
					addActionError(getText("Error.RetentionPercentage.Required"));
					flag=false;
				}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getRetper()))){
					addActionError(getText("Error.RetentionPercentage.invalid"));
					flag=false;
				}else if(validation.percentageValid(bean.getRetper()).equalsIgnoreCase("greater")){		
					addActionError(getText("Error.RetentionPercentage.greater"));
					flag=false;
				}else{
					totPer+=Double.parseDouble(bean.getRetper());
				}
			}
			boolean dupCheck=true;
			for(int i=0;i<LoopCount;i++){
				
				List<String> list = new ArrayList<String>();
				bean.setRetroDupVal(list);
				if(validation.isNull(bean.getRetroPercentage()==null?"":bean.getRetroPercentage().get(i)).equalsIgnoreCase("")){
					addActionError(getText("Error.RetroPercentahge.Required",new String[]{String.valueOf(i+1)}));
					flag=false;
				}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getRetroPercentage().get(i)))){
					addActionError(getText("Error.RetroPercentahge.invalid",new String[]{String.valueOf(i+1)}));
					flag=false;
				}else if(validation.percentageValid(bean.getRetroPercentage().get(i)).equalsIgnoreCase("greater")){
					addActionError(getText("Error.RetroPercentahge.greater",new String[]{String.valueOf(i+1)}));
					flag=false;
				}else{
					totPer+=Double.parseDouble(bean.getRetroPercentage().get(i));
				}
				uwlList.add(new ArrayList<Map<String,Object>>());
				retrolList.add(new ArrayList<Map<String,Object>>());
				retroDupList.add(new ArrayList<Map<String,Object>>());
			}
			bean.setUwlList(uwlList);
			bean.setRetrolList(retrolList);
			bean.setRetroDupList(retroDupList);
			if (dupCheck) {
				for (int i = 0; i < LoopCount - 1; i++) {
					for (int j = i + 1; j < LoopCount; j++) {
						if (bean.getCedingCompanyValList().get(i).equalsIgnoreCase(bean.getCedingCompanyValList().get(j))) {
							addActionError(getText("error.RetroContract.Repeat",new String[]{String.valueOf(j+1)}));
						}
					}
				}
			}
			if(LoopCount!=0){	
				if(flag){	
					DecimalFormat df = new DecimalFormat("#.##");
					totPer=Double.parseDouble(df.format(totPer));
					if(totPer!=100){
						addActionError(getText("error.totPercentage.invalid"));
					}
				}
			}
			
			if(bean.getCrestaStatus().equalsIgnoreCase("Y")){
				if(StringUtils.isBlank(bean.getCrestaPopUp())){
					addActionError(getText("cresta.popup.check"));
				}
				 
				else if(service.getCrestaCount(bean)==0){
					addActionError(getText("error.creasta.invalid"));
				}
			}
			
			if(validation.isNull(bean.getLossRecord()).equalsIgnoreCase("")){
				addActionError(getText("errros.lossRecord.required"));
			}
			else if("Y".equalsIgnoreCase(bean.getLossRecord())){
				validationLossRecord();
			}
			if(validation.isNull(bean.getCu()).equalsIgnoreCase("")){
				addActionError(getText("errors.cu.required"));
			}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getCu()))){
				addActionError(getText("errors.cu.percentages"));
			}else if(validation.percentageValid(bean.getCu()).equalsIgnoreCase("greater")){
				addActionError(getText("errors.cu.greater"));
			} 
			if(validation.isNull(bean.getCuRsn()).equalsIgnoreCase("")){
				addActionError(getText("errors.cuRsn.required"));
			}
		
			/*if(validation.isNull(bean.getReftoHO()).equalsIgnoreCase("")){
				addActionError(getText("errors.reftoHO.required"));
			}*/
			
			if(validation.isNull(bean.getAcqCost()).equalsIgnoreCase("")){
				addActionError(getText("errors.acquisition_Cost.second1"));
			}else{
				bean.setAcqCost((bean.getAcqCost()).replaceAll(",",""));
				if("INVALID".equalsIgnoreCase(validation.isValidNo(bean.getAcqCost()))){
					addActionError(getText("errors.acquisition_Cost.second1"));
				}
			}
			bean.setAcqCost((bean.getAcqCost()).replaceAll(",",""));
			logger.info("ACQCost==>"+bean.getAcqCost());
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && StringUtils.isBlank(bean.getDocStatus())) {
				addActionError(getText("doc.status"));
			}
			if(StringUtils.isBlank(bean.getRetroDupContract())){
				this.addActionError(getText("errors.dummy.contract",new String[]{bean.getYear()}));
			}
			validationContract();
			validationRemarks();
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		
	}
	private void validationLossRecord() {
		try{
			final Validation val = new Validation();
			Validation validation=new Validation();
			List<String> error=new ArrayList<String>();
			for(int i=0;i<bean.getLossYear().size();i++){
				if(StringUtils.isBlank(bean.getLossYear().get(i))){
					error.add(getText("err.year",new String[]{String.valueOf(i+1)}));
				}
				if(StringUtils.isBlank(bean.getLossInceptionDate().get(i))){
					error.add(getText("err.inception",new String[]{String.valueOf(i+1)}));
				}else if ("INVALID".equalsIgnoreCase(val.checkDate(bean.getLossInceptionDate().get(i)))) {
					error.add(getText("err.inception.format",new String[]{String.valueOf(i+1)}));
				}
				if(StringUtils.isBlank(bean.getLossExpiryDate().get(i))){
					error.add(getText("err.expiry",new String[]{String.valueOf(i+1)}));
				}else if ("INVALID".equalsIgnoreCase(val.checkDate(bean.getLossExpiryDate().get(i)))) {
					error.add(getText("err.expiry.format",new String[]{String.valueOf(i+1)}));
				}
				if(StringUtils.isBlank(bean.getLossDateOfLoss().get(i))){
					error.add(getText("err.dateofloass",new String[]{String.valueOf(i+1)}));
				}else if ("INVALID".equalsIgnoreCase(val.checkDate(bean.getLossDateOfLoss().get(i)))) {
					error.add(getText("err.dateofloass.format",new String[]{String.valueOf(i+1)}));
				}
				
				
				if(StringUtils.isBlank(bean.getLossInsuredClaim().get(i))){
					error.add(getText("err.incurredClaim",new String[]{String.valueOf(i+1)}));
					}
				if(StringUtils.isBlank(bean.getLossPremium().get(i))){
					error.add(getText("err.lossPremium",new String[]{String.valueOf(i+1)}));
				}
				if(StringUtils.isBlank(bean.getLossRatio().get(i))){
					error.add(getText("err.ratio",new String[]{String.valueOf(i+1)}));
				}else{
					String ans = calcu.calculateFacultative(bean,"LossRatio",i);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getLossRatio().get(i).replaceAll(",",""))){
						error.add(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.getLossRatio().set(i,ans);
					}
				}
				if(error.size()==7){
					error=new ArrayList<String>();
				}else{
					if(StringUtils.isNotBlank(bean.getLossInceptionDate().get(i)) && StringUtils.isNotBlank(bean.getLossExpiryDate().get(i)) && !"INVALID".equalsIgnoreCase(val.checkDate(bean.getLossInceptionDate().get(i))) && !"INVALID".equalsIgnoreCase(val.checkDate(bean.getLossExpiryDate().get(i)))){
						if (validation.ValidateINstallDates(bean.getLossInceptionDate().get(i),bean.getLossExpiryDate().get(i)).equalsIgnoreCase("Invalid")) {
							error.add(getText("err.expiry.greater",new String[]{String.valueOf(i+1)}));
						}
					}
					if(StringUtils.isNotBlank(bean.getLossInceptionDate().get(i)) && StringUtils.isNotBlank(bean.getLossDateOfLoss().get(i)) && !"INVALID".equalsIgnoreCase(val.checkDate(bean.getLossInceptionDate().get(i))) && !"INVALID".equalsIgnoreCase(val.checkDate(bean.getLossDateOfLoss().get(i)))){
						if (validation.ValidateINstallDates(bean.getLossInceptionDate().get(i),bean.getLossDateOfLoss().get(i)).equalsIgnoreCase("Invalid")) {
							error.add(getText("err.dateofloss.greater",new String[]{String.valueOf(i+1)}));
						}
					}
					for(int k=0;k<error.size();k++){
						addActionError(error.get(k));
					}
					error=new ArrayList<String>();
				}
				
			}
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
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
	private void validationCoverDeductable(){
		try{
			List<String> error=new ArrayList<String>();
			for(int i=0;i<bean.getCoverLimitOC().size();i++){
				if(StringUtils.isBlank(bean.getCoverdepartId().get(i))){
					error.add(getText("error.cover.dept",new String[]{String.valueOf(i+1)}));
				}
				if(StringUtils.isBlank(bean.getCoversubdepartId().get(i))){
					error.add(getText("error.cover.subdept",new String[]{String.valueOf(i+1)}));
				}
				if(StringUtils.isBlank(bean.getCoverTypeId().get(i))){
					error.add(getText("error.CoverType",new String[]{String.valueOf(i+1)}));
				}
				else if("2".equalsIgnoreCase(bean.getCoverTypeId().get(i))){
					if(StringUtils.isBlank(bean.getPmlPerList().get(i))){
					error.add(getText("error.pmlper",new String[]{String.valueOf(i+1)}));
					
				}else{
					String ans = calcu.calculateFacultative(bean,"Pmlper",i);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getPmlPerList().get(i).replaceAll(",",""))){
						error.add(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.getPmlPerList().set(i,ans);
					}
				}
				if(StringUtils.isBlank(bean.getPmlHundredPer().get(i))){
					error.add(getText("error.pmlhundredper",new String[]{String.valueOf(i+1)}));
				}
				}
				if(StringUtils.isBlank(bean.getCoverLimitOC().get(i)) && StringUtils.isNotBlank(bean.getDeductableLimitOC().get(i))){
				if(StringUtils.isBlank(bean.getCoverLimitOC().get(i))){
					error.add(getText("error.Cover.limit",new String[]{String.valueOf(i+1)}));
				}
				}
				if(StringUtils.isNotBlank(bean.getCoverLimitOC().get(i)) && StringUtils.isBlank(bean.getDeductableLimitOC().get(i))){
					if(StringUtils.isBlank(bean.getDeductableLimitOC().get(i))){
						error.add(getText("error.Cover.deductable",new String[]{String.valueOf(i+1)}));
					}
				}
				if(StringUtils.isBlank(bean.getCoverageDays().get(i)) && StringUtils.isNotBlank(bean.getDeductableDays().get(i))){
				if(StringUtils.isBlank(bean.getCoverageDays().get(i))){
					error.add(getText("error.Coverage.days.deductable",new String[]{String.valueOf(i+1)}));
				}
				}
				if(StringUtils.isNotBlank(bean.getCoverageDays().get(i)) && StringUtils.isBlank(bean.getDeductableDays().get(i))){
					if(StringUtils.isBlank(bean.getDeductableDays().get(i))){
						error.add(getText("error.ded.days.deductable",new String[]{String.valueOf(i+1)}));
					}
				}
				if(StringUtils.isBlank(bean.getEgnpiAsPerOff().get(i))){
					error.add(getText("error.egnpi.days.deductable",new String[]{String.valueOf(i+1)}));
				}
				else if(Double.parseDouble(bean.getEgnpiAsPerOff().get(i).replaceAll(",","")) > ((1.1* (Double.parseDouble(StringUtils.isBlank(bean.getCoverLimitOC().get(i))?"0":bean.getCoverLimitOC().get(i).replaceAll(",",""))) * Double.parseDouble(bean.getPremiumRateList().get(i).replaceAll(",",""))))/100) {
					error.add(getText("gwpi.calculated.wrongly",new String[]{String.valueOf(i+1)}));
				}
				else if(Double.parseDouble(bean.getEgnpiAsPerOff().get(i).replaceAll(",","")) < ((0.9*(Double.parseDouble(StringUtils.isBlank(bean.getCoverLimitOC().get(i))?"0":bean.getCoverLimitOC().get(i).replaceAll(",",""))) * Double.parseDouble(bean.getPremiumRateList().get(i).replaceAll(",",""))))/100) {
					error.add(getText("gwpi.calculated.wrongly",new String[]{String.valueOf(i+1)}));
				}else{
					String ans = calcu.calculateFacultative(bean,"Egnpi",i);
					double changeans=Double.parseDouble(ans)*10/100;
					if((Double.parseDouble(ans)-changeans)>Double.parseDouble(bean.getEgnpiAsPerOff().get(i).replaceAll(",",""))){
						error.add(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else if((Double.parseDouble(ans)+changeans)<Double.parseDouble(bean.getEgnpiAsPerOff().get(i).replaceAll(",",""))){
						error.add(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.getEgnpiAsPerOff().set(i,bean.getEgnpiAsPerOff().get(i));
					}
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
	private void validationXolCoverDeductable() {
		try{
			List<String> error=new ArrayList<String>();
			for(int i=0;i<bean.getXolcoverLimitOC().size();i++){
				if(StringUtils.isBlank(bean.getXolcoverdepartId().get(i))){
					error.add(getText("error.cover.dept",new String[]{String.valueOf(i+1)}));
				}
				if(StringUtils.isBlank(bean.getXolcoversubdepartId().get(i))){
					error.add(getText("error.cover.subdept",new String[]{String.valueOf(i+1)}));
				}
			
				if(StringUtils.isBlank(bean.getXolcoverLimitOC().get(i)) && StringUtils.isNotBlank(bean.getXoldeductableLimitOC().get(i))){
				if(StringUtils.isBlank(bean.getXolcoverLimitOC().get(i))){
					error.add(getText("error.Cover.limit.xol",new String[]{String.valueOf(i+1)}));
				}
				}
				if(StringUtils.isNotBlank(bean.getXolcoverLimitOC().get(i)) && StringUtils.isBlank(bean.getXoldeductableLimitOC().get(i))){
					if(StringUtils.isBlank(bean.getXoldeductableLimitOC().get(i))){
						error.add(getText("error.Cover.deductable",new String[]{String.valueOf(i+1)}));
					}
				}
				if(StringUtils.isBlank(bean.getXolgwpiOC().get(i))){
					error.add(getText("error.egnpi.days.deductable",new String[]{String.valueOf(i+1)}));
				}else{
					String ans = calcu.calculateFacultative(bean,"Gwpi",i);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getXolgwpiOC().get(i).replaceAll(",",""))){
						error.add(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.getXolgwpiOC().set(i,ans);
					}
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
	private  void GetDropDownLists(final HttpServletRequest request,final String pid, String method)  {
		if(!"view".equalsIgnoreCase(method) &&(!"edit".equalsIgnoreCase(method) || StringUtils.isNotBlank(bean.getContractListVal()))){
		String sumInsured = "";
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(bean.getCedingCompany())&&StringUtils.isNotBlank(bean.getInceptionDate())&&StringUtils.isNotBlank(bean.getExpiryDate())&&StringUtils.isNotBlank(bean.getYear())&&StringUtils.isNotBlank(bean.getOriginalCurrency())&&StringUtils.isNotBlank(bean.getDepartmentId())&&StringUtils.isNotBlank(bean.getType())&&StringUtils.isNotBlank(bean.getProfitCenterCode())){
		if(StringUtils.isNotBlank(bean.getType())&&"1".equalsIgnoreCase(bean.getType())){
				sumInsured =StringUtils.isEmpty(bean.getSumInsured())? "0":bean.getSumInsured();
			}
			else{
				sumInsured = StringUtils.isEmpty(bean.getDeductibleFacXol())? "0":bean.getDeductibleFacXol();
			}	
			bean.setContractTypelist(dropDownController.getContractValidation(productId,bean.getCedingCompany(),bean.getInceptionDate(),bean.getExpiryDate(),bean.getYear(),bean.getOriginalCurrency(),bean.getDepartmentId(),bean.getType(),sumInsured,StringUtils.isEmpty(bean.getContractNo())? "0":bean.getContractNo(),bean.getProfitCenterCode(),"","","","0",bean.getBranchCode()));
		}
		else if(bean.getContractTypelist()==null){
			bean.setContractTypelist(list);
		}
		}
		bean.setProfit_Center((new DropDownControllor().getProfitCentreDropDown(branchCode)));
		/*if(StringUtils.isBlank(bean.getEndtMode())) {
		bean.setProfitCenterCode("FAC");
		}*/
		bean.setSubProfit_center(new DropDownControllor().getSubProfitCentreDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString()));
		bean.setUnderWritter(new DropDownControllor().getUnderWritterDropDown(branchCode,attachedUW));
		bean.setDepartId(new DropDownControllor().getDepartmentDropDown(branchCode,session.get("mfrid").toString(),"Y","","","","",""));
		bean.setPolBr(new DropDownControllor().getPolicyBranchDropDown(branchCode));
		bean.setBrokers(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",productId));//B=>Broker
		bean.setProposal(new DropDownControllor().getConstantDropDown("1"));//Proposal Status
		bean.setEQWSIndDrop(new DropDownControllor().getConstantDropDown("2"));//EQWSIndDropDown
		bean.setTypeDropDown(new DropDownControllor().getConstantDropDown("13"));//TypeDropDown
		bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
		bean.setTerritorty(new DropDownControllor().getTerritoryDropDown(branchCode));
		bean.setCeddingCompany(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",productId));//C=>Ceding Compacy
		bean.setYearList(getYearList());
		if("4".equals(((String)session.get("DepartmentId")))) {
			bean.setModeOfTransports(new DropDownControllor().getModeOfTransportDropDown(branchCode,(String)session.get("DepartmentId")));	
		}
		bean.setCoverTypelist(new DropDownControllor().getConstantDropDown("44"));
		bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,(String)session.get("mfrid"),"Y","","","","",""));
	}


	public  String EditMethod(){
		String forward="";
		String editMode ="N";
		try {
			if(StringUtils.isBlank(bean.getProposalNo())) {
				bean.setProposalNo(bean.getProposal_no());
			}
		if( !"edit".equalsIgnoreCase(bean.getMultiuserMode()) && StringUtils.isNotBlank(bean.getProposalNo())){
			try{
				if(StringUtils.isNotBlank(bean.getMode()) &&   "Renewal".equalsIgnoreCase(bean.getMode())){
			 editMode = dropDownController.EditModeStatus(bean.getProposal_no(),"0");
				}
				else{
					editMode = dropDownController.EditModeStatus(bean.getProposalNo(),"0");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(!"N".equalsIgnoreCase(editMode)){
			forward ="portfoliList";	
			bean.setMultiuserError("error");
		}
		else{
			bean.setProductId((String) session.get("mfrid"));
			bean.setBranchCode(branchCode);
			bean.setMenuStatus("N");
			
			
			if(StringUtils.isNotBlank(bean.getReMode()) &&  "Renewal".equalsIgnoreCase(bean.getReMode())){
				bean.setRenewalProposalNo(bean.getProposal_no());
				bean.setProposalNo(new DropDownControllor().getRenewalCopyQuote("Renewal",bean.getProductId(), bean.getBranchCode(), bean.getProposal_no()));
			}
			else if(StringUtils.isNotBlank(bean.getEndtMode()) &&  "endorsment".equalsIgnoreCase(bean.getEndtMode())){
				dropDownController.riskDetailsEndorsement(bean.getProposalNo(),bean.getEndorsementStatus());
			}
			bean.setDepartmentName(new DropDownControllor().getDepartmentName(branchCode, (String)session.get("mfrid"), (String)session.get("DepartmentId")));
			service.ShowFirstPageDatas(bean);
			this.GetDropDownLists(request,bean.getProductId(),"edit");
			if(StringUtils.isNotBlank(bean.getMode()) &&  ! "Renewal".equalsIgnoreCase(bean.getMode())){
				if(StringUtils.isNotBlank(bean.getContractNo())){
					bean.setConmode("Yes");
				}
			}
			else{
				bean.setContractNo("");
				bean.setNr("2");
				bean.setRenewalMode("RenewalMode");
			}
			if(StringUtils.isNotBlank(bean.getEndtMode())&& "endorsment".equalsIgnoreCase(bean.getEndtMode())){
				bean.setEndorsmenttype("");
			}
			bean.setEdit(bean.getMode());
			bean.setShortname(service.getShortname(bean));
			if(StringUtils.isNotBlank(bean.getReMode()) &&  "Renewal".equalsIgnoreCase(bean.getReMode())){
				bean.setProposalReference("Renewal");//for cancel button
			}
			if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
				dropDownController.updateRenewalEditMode(bean.getProposalNo(),"BR",bean.getProposalNo());
				dropDownController.updateEditMode(bean.getProposalNo(),"BR",bean.getProposalNo());
			}
			//if( !"edit".equalsIgnoreCase(bean.getMultiuserMode())){
			else{
			dropDownController.updateEditMode(bean.getProposalNo(),"BE",bean.getProposalNo());
			}
			//}
			forward="faculengg1";
		}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	public  String ConInsertFirstPage(){
		String forward=null;
		try{
			bean.setConmode("Yes");
			bean.setProductId((String) session.get("mfrid"));
			bean.setFacultativeDepartment((String) session.get("DepartmentId"));
			bean.setDepartmentId((String) session.get("DepartmentId"));
			bean.setLoginid(userId);
			bean.setBranchCode(branchCode);
			bean.setProcessId(session.get("processId").toString());
			 new DropDownControllor().getOpenPeriod(bean);
			 if(StringUtils.isNotBlank(bean.getProStatus()) &&"A".equalsIgnoreCase(bean.getProStatus())){
					getFirstPageSubmitError();
				}
				else{
					getFirstPageSaveError();
				}
			if(!hasActionErrors()){
				
				bean.setBranchCode(branchCode);
				this.BusinessCalculation(bean);
				service.InsertFirstPage(bean,false,true);
				service.ShowSecondPagedata(bean,bean);
				service.ShowSecondPageEditItems(bean);
				bean.setProposalNo(bean.getProposalNo());
				bean.setContractNo(session.get("EditContarctNumber").toString());
				request.setAttribute("CeddingCompany",new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",productId));//C=>Ceding Compacy
				forward="faculengg2";
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setContractNo(session.get("EditContarctNumber").toString());
				this.GetDropDownLists(request,bean.getProductId(),"");
				bean.setDepartmentId((String) session.get("DepartmentId"));
				forward ="faculengg1";
			}
			request.setAttribute("NoInsurar",bean.getNo_Insurer());

		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}	
		return forward;
	}

	public  String ConEditMethod(){
		String forward=null;
		try{
			bean.setDepartmentId(request.getParameter("Department"));
			service.ShowFirstPageDatas(bean);
			this.GetDropDownLists(request,bean.getProductId(),"");
			forward="faculengg1";
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}
	public String ViewMethod(){
		String forward=null;
		try{
			if(StringUtils.isBlank(bean.getProposalNo())) {
				bean.setProposalNo(bean.getProposal_no());
			}
			bean.setBranchCode(branchCode);
			bean.setShortname(service.getShortname(bean));
			service.ShowPageViewDatas(bean);
			this.GetDropDownLists(request,bean.getProductId(),"view");
			forward="FacView";
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		} 
		return forward;
	}

	public String getRetroContractDetails()  {
		try {
			bean.setProductId("4");
			bean.setBranchCode(branchCode);
			service.getRetroContractDetails(bean);
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "dropdownajax";
	}
	private List<Map<String,Object>> getYearList(){
		Validation val =new Validation();
		List<Map<String,Object>> yearsList=new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(bean.getInceptionDate()) && !val.checkDate(bean.getInceptionDate()).equalsIgnoreCase("INVALID")){
		yearsList = dropDownController.getYearListValue(bean.getInceptionDate());
		}
		/*Calendar cal=Calendar.getInstance(); 
		for(int j=(cal.get(Calendar.YEAR))-4;j<=(cal.get(Calendar.YEAR))+1;j++){
			Map<String,Object> year=new HashMap<String, Object>();
			year.put("YEAR", j);
			yearsList.add(year);
		}*/
		return yearsList;
	}
	private List<Map<String,Object>> getYearToList(){
		Validation val =new Validation();
		List<Map<String,Object>> yearsList=new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(bean.getInceptionDate()) && !val.checkDate(bean.getInceptionDate()).equalsIgnoreCase("INVALID")){
			if(StringUtils.isNotBlank(bean.getExpiryDate()) && !val.checkDate(bean.getExpiryDate()).equalsIgnoreCase("INVALID")){
				yearsList = dropDownController.getYearToListValue(bean.getInceptionDate(),bean.getExpiryDate());
			}
		}
		
		return yearsList;
	}
	private  void BusinessCalculation(final FaculitivesBean formObj) {
		final  DecimalFormat twoDigit = new DecimalFormat("###0.00");
		formObj.setShareValue(formObj.getShSd());
		if (StringUtils.isNotEmpty(formObj.getShSd())) {
			if (StringUtils.isNotEmpty(formObj.getSumInsured())	&& StringUtils.isNotEmpty(formObj.getShSd())) {
				double	dvalue=Double.parseDouble(formObj.getSumInsured())*((Double.parseDouble(formObj.getShSd())/100.0));
				logger.info("Sum Insured Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setSumInsuredOurShare(valu);
				formObj.setSiOSViewOC(DropDownControllor.formatter(valu));
				formObj.setSiOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getSumInsuredOurShare(),formObj.getUsCurrencyRate())));
				//formObj.setSumusd(DropDownControllor.GetDesginationCountry(formObj.getSumInsuredOurShare(),formObj.getUsCurrencyRate()));
			}

			if (StringUtils.isNotEmpty(formObj.getGwpi())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getGwpi())* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Gwpi Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setGwpiOurShare(valu);
				formObj.setGwpiOSViewOC(DropDownControllor.formatter(valu));
				formObj.setGwpiOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getGwpiOurShare(),formObj.getUsCurrencyRate())));
			}
			if (StringUtils.isNotEmpty(formObj.getDeductibleFacXol())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getDeductibleFacXol())* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Deductible Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setCoverlimitOurShare(valu);
				formObj.setCoverlimitOSOC(DropDownControllor.formatter(valu));
				formObj.setCoverlimitOSDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getCoverlimitOurShare(),formObj.getUsCurrencyRate())));
				formObj.setSumusd(DropDownControllor.GetDesginationCountry(formObj.getCoverlimitOurShare(),formObj.getUsCurrencyRate()));
			}
			if (StringUtils.isNotEmpty(formObj.getTotalCoverageVal())){
				final double dvalue = (Double.parseDouble(formObj.getTotalCoverageVal().replaceAll(",", ""))* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Total Cover Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				//formObj.setSumusd(DropDownControllor.GetDesginationCountry(valu,formObj.getUsCurrencyRate()));
			}
			if (StringUtils.isNotEmpty(formObj.getDeductible())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getDeductible())* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Cover Limit Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setDeductibleOurShare(valu);
				formObj.setDeductibleOSOC(DropDownControllor.formatter(valu));
				formObj.setDeductibleOSDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getDeductibleOurShare(),formObj.getUsCurrencyRate())));
			}
			if (StringUtils.isNotEmpty(formObj.getXolOC())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getXolOC())* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("XOL Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setXolOSOC(valu);
				formObj.setXolOSViewOC(DropDownControllor.formatter(valu));
				formObj.setXolOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getXolOSOC(),formObj.getUsCurrencyRate())));
			}

			/*if (StringUtils.isNotEmpty(formObj.getSipml())&&"PML".equalsIgnoreCase(formObj.getSipml())) {
				if (StringUtils.isNotEmpty(formObj.getPmll())&& StringUtils.isNotEmpty(formObj.getShSd())) {
					double	dvalue=Double.parseDouble(formObj.getPmll())*((Double.parseDouble(formObj.getShSd())/100.0));
					logger.info("Pml Our Share Calculated Value=>" + dvalue);
					final double dround = Math.round(dvalue * 100.0) / 100.0;
					logger.info("Rounded Value=>" + dround);
					final String valu = twoDigit.format(dround);
					logger.info("Formated Value=>" + valu);
					formObj.setPmlOurShare(valu);
					formObj.setPmlOSViewOC(DropDownControllor.formatter(valu));
					formObj.setPmlOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getPmlOurShare(),formObj.getUsCurrencyRate())));
				}
			}*/

			if (StringUtils.isNotEmpty(formObj.getTpl())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getTpl())	* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setTplOurShare(valu);
				formObj.setTplOSViewOC(DropDownControllor.formatter(valu));
				formObj.setTplOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getTplOurShare(),formObj.getUsCurrencyRate())));

			}
			//new created:
			if (StringUtils.isNotEmpty(formObj.getDeductibleFacXolPml())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getDeductibleFacXolPml())	* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setDeductibleFacXolPmlOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getSumInsuredPml())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getSumInsuredPml())	* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setSumInsuredPmlOurShare(valu);
			}
			
			if (StringUtils.isNotEmpty(formObj.getGwpiPml())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getGwpiPml())	* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setGwpiPmlOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPslOC())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getPslOC())	* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPslOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPslOC())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getPslOC())	* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPslOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPllOC())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getPllOC())	* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPllOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPblOC())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getPblOC())	* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPblOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPmll())&& StringUtils.isNotEmpty(formObj.getShSd())) {
				final double dvalue = (Double.parseDouble(formObj.getPmll())	* (Double.parseDouble(formObj.getShSd())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPmlOCOurShare(valu);
			}
			

		} else {
			if (StringUtils.isNotEmpty(formObj.getSumInsured())	&& StringUtils.isNotEmpty(formObj.getShWt())) {
				double	dvalue=Double.parseDouble(formObj.getSumInsured())*((Double.parseDouble(formObj.getShWt())/100.0));
				logger.info("Sum Insured Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setSumInsuredOurShare(valu);
				formObj.setSiOSViewOC(DropDownControllor.formatter(valu));
				formObj.setSiOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getSumInsuredOurShare(),formObj.getUsCurrencyRate())));
				//formObj.setSumusd(DropDownControllor.GetDesginationCountry(formObj.getSumInsuredOurShare(),formObj.getUsCurrencyRate()));
			}

			if (StringUtils.isNotEmpty(formObj.getGwpi())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getGwpi())* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Gwpi Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setGwpiOurShare(valu);
				formObj.setGwpiOSViewOC(DropDownControllor.formatter(valu));
				formObj.setGwpiOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getGwpiOurShare(),formObj.getUsCurrencyRate())));
			}
			if (StringUtils.isNotEmpty(formObj.getDeductibleFacXol())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getDeductibleFacXol())* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Deductible Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setCoverlimitOurShare(valu);
				formObj.setCoverlimitOSOC(DropDownControllor.formatter(valu));
				formObj.setCoverlimitOSDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getCoverlimitOurShare(),formObj.getUsCurrencyRate())));
				formObj.setSumusd(DropDownControllor.GetDesginationCountry(formObj.getCoverlimitOurShare(),formObj.getUsCurrencyRate()));
			}
			if (StringUtils.isNotEmpty(formObj.getTotalCoverageVal())){
				final double dvalue = (Double.parseDouble(formObj.getTotalCoverageVal().replaceAll(",", ""))* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Total Cover Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				//formObj.setSumusd(DropDownControllor.GetDesginationCountry(valu,formObj.getUsCurrencyRate()));
			}
			
			
			if (StringUtils.isNotEmpty(formObj.getDeductible())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getDeductible())* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Cover Limit Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setDeductibleOurShare(valu);
				formObj.setDeductibleOSOC(DropDownControllor.formatter(valu));
				formObj.setDeductibleOSDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getDeductibleOurShare(),formObj.getUsCurrencyRate())));
			}

			if (StringUtils.isNotEmpty(formObj.getXolOC())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getXolOC())* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("XOL Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setXolOSOC(valu);
				formObj.setXolOSViewOC(DropDownControllor.formatter(valu));
				formObj.setXolOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getXolOSOC(),formObj.getUsCurrencyRate())));
			}

			/*if (StringUtils.isNotEmpty(formObj.getSipml())&&"PML".equalsIgnoreCase(formObj.getSipml())) {
				if (StringUtils.isNotEmpty(formObj.getPmll())&& StringUtils.isNotEmpty(formObj.getShWt())){
					double	dvalue=Double.parseDouble(formObj.getPmll())*((Double.parseDouble(formObj.getShWt())/100.0));
					logger.info("Pml Our Share Calculated Value=>" + dvalue);
					final double dround = Math.round(dvalue * 100.0) / 100.0;
					logger.info("Rounded Value=>" + dround);
					final String valu = twoDigit.format(dround);
					logger.info("Formated Value=>" + valu);
					formObj.setPmlOurShare(valu);
					formObj.setPmlOSViewOC(DropDownControllor.formatter(valu));
					formObj.setPmlOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getPmlOurShare(),formObj.getUsCurrencyRate())));
				}
			}*/
			if (StringUtils.isNotEmpty(formObj.getTpl())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getTpl())	* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setTplOurShare(valu);
				formObj.setTplOSViewOC(DropDownControllor.formatter(valu));
				formObj.setTplOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formObj.getTplOurShare(),formObj.getUsCurrencyRate())));
			}
			
			if (StringUtils.isNotEmpty(formObj.getDeductibleFacXolPml())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getDeductibleFacXolPml())	* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setDeductibleFacXolPmlOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getSumInsuredPml())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getSumInsuredPml())	* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setSumInsuredPmlOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getGwpiPml())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getGwpiPml())	* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setGwpiPmlOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPslOC())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getPslOC())	* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPslOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPslOC())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getPslOC())	* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPslOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPllOC())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getPllOC())	* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPllOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPblOC())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getPblOC())	* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPblOurShare(valu);
			}
			if (StringUtils.isNotEmpty(formObj.getPmll())&& StringUtils.isNotEmpty(formObj.getShWt())) {
				final double dvalue = (Double.parseDouble(formObj.getPmll())	* (Double.parseDouble(formObj.getShWt())) / 100);
				logger.info("Tpl Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				formObj.setPmlOCOurShare(valu);
			}
		}
		int nos=0;
		double total=0;
		if(formObj.getNoOfInst()!=null&&formObj.getNoOfInst().length()>0){
			nos=Integer.parseInt(formObj.getNoOfInst());
		}
		if(StringUtils.isNotBlank(formObj.getGwpiOurShare()) && nos!=0){
			DropDownControllor controller=new DropDownControllor();
			boolean status = true;
			if(null == bean.getInstalmentDateList()){
			 status=controller.getInstallments(formObj);
			}
			if(!status){
				total=Double.parseDouble(formObj.getGwpiOurShare())/nos;
				final double dround = Math.round(total * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final String valu = twoDigit.format(dround);
				logger.info("Formated Value=>" + valu);
				List<String> insprelist =new ArrayList<String>();
				double Sum=0.0;
				for(int i=0;i<nos-1;i++){
					insprelist.add(DropDownControllor.formatter(valu));
					 Sum=Sum+Double.parseDouble(valu);
					 logger.info("Formated Value1=>" + Sum);
				}
				Double Gwpi=Double.parseDouble(formObj.getGwpiOurShare());
				String retotal =twoDigit.format(Gwpi-Sum);
				 logger.info("Formated retotal=>" + retotal);
				insprelist.add(DropDownControllor.formatter(retotal));
				bean.setInstallmentPremium(insprelist);
			}
		}
	}
	private void getFirstPageSubmitError(){
		try{
			if(StringUtils.isNotBlank(bean.getType()) && "1".equalsIgnoreCase(bean.getType()) ){
				if(StringUtils.isNotBlank(bean.getTotalCoverage())){
					String ans = calcu.calculateFacultative(bean,"TotCoverage",0);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getTotalCoverage().replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						//bean.setTotalGWPI(ans);
						bean.setDeductibleFacXol(bean.getTotalCoverage().replace(",", ""));
						bean.setSumInsured(bean.getTotalCoverage().replace(",", ""));
					}
				}
				
				if(StringUtils.isNotBlank(bean.getTotalGWPI())){
					String ans = calcu.calculateFacultative(bean,"TotGwpi",0);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getTotalGWPI().replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.setTotalGWPI(ans);
						bean.setGwpi(bean.getTotalGWPI().replace(",", ""));
					}
				}
				 /*if(StringUtils.isNotBlank(bean.getTotalDeductible())){
						String ans = calcu.calculateFacultative(bean,"TotDetuctible",0);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getTotalDeductible().replaceAll(",",""))){
							addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setTotalDeductible(ans);
							bean.setDeductible(bean.getTotalDeductible().replace(",", ""));
						}
				}*/
				//bean.setGwpi(bean.getTotalGWPI().replace(",", ""));
				//bean.setDeductible(bean.getTotalDeductible().replace(",", ""));
			}
			else if(StringUtils.isNotBlank(bean.getType()) && "2".equalsIgnoreCase(bean.getType()) ){//&& !"4".equalsIgnoreCase(bean.getDepartmentId())
				bean.setGwpi(bean.getXoltotalGWPI().replace(",", ""));	
			}
			final Validation val=new Validation();
			boolean flaging=true;
			if(StringUtils.isNotBlank(bean.getEndorsmentno())&& Integer.parseInt(bean.getEndorsmentno())>0){
				if(StringUtils.isBlank(bean.getEndorsmenttype())){
					addActionError(getText("end.type.error"));
				}
			}
			if(val.isSelect(bean.getSubProfitCenter()).equalsIgnoreCase("")){
				addActionError(getText("errors.subProfitCenter.required"));
			}else{
				bean.setSubProfitCenter((bean.getSubProfitCenter()).replaceAll(" ", ""));
			}
			if(val.isSelect(bean.getProfitCenterCode()).equalsIgnoreCase("")){
				addActionError(getText("errors.profitCenterCode.required"));
			}
			if(StringUtils.isBlank(bean.getInwardType())||"0".equalsIgnoreCase(bean.getInwardType()) ){
				if("RI01".equalsIgnoreCase(sourceId)){
					addActionError(getText("error.inwardtype"));
				}else{
					addActionError(getText("error.inwardtype02"));
				}
			}
			if(val.isNull(bean.getUnderwriter()).equalsIgnoreCase("0")){
				addActionError(getText("errors.underwriter.required"));
			}
			boolean cedCheck=true;
			if(val.isNull(bean.getMaxiumlimit()).equalsIgnoreCase("")){
				addActionError(getText("errors.ourmaxiumlimit.required"));
				cedCheck=false;
			}else {
				bean.setMaxiumlimit((bean.getMaxiumlimit().replaceAll(",","")));
				if(val.isValidNo(bean.getMaxiumlimit()).equalsIgnoreCase("INVALID")){
					addActionError(getText("errors.ourmaxiumlimit.invalid"));		
					cedCheck=false;
				}else {
					String uwLimit=dropDownController.getUnderWriterLimmit(bean.getUnderwriter(),(String)session.get("processId"), "1", (String)session.get("DepartmentId"));
					uwLimit=uwLimit.replaceAll(",","");
					if(Double.parseDouble(uwLimit)==0){
						addActionError(getText("error.maxLimitProduct.config",new String[]{uwLimit}));
						cedCheck=false;
					}else if(Double.parseDouble(bean.getMaxiumlimit())>Double.parseDouble(uwLimit)){
						addActionError(getText("error.maxLimitProduct.exceedLimit",new String[]{uwLimit}));
						cedCheck=false;
					}
				}
			}
			Map<String,Object> map=null;
			List<Map<String, Object>> list=service.getValidation(bean,1);
			if(list!=null&&list.size()>0){
				map=(Map<String,Object>)list.get(0);
			}
			
			
			if(val.isSelect(bean.getPolicyBranch()).equalsIgnoreCase("")){
				addActionError(getText("errors.polBranch.required"));
			}
			if(val.isSelect(bean.getType()).equalsIgnoreCase("")){
				addActionError(getText("errors.Type.required"));
			}
			if(bean.getCedingCompany().equalsIgnoreCase("-1")){
				addActionError(getText("errors.cedingCompany.required"));
			}
			if(val.isSelect(bean.getBroker()).equalsIgnoreCase("")){
				addActionError(getText("errors.broker.required"));
			}
			
			
			if(val.isNull(bean.getInceptionDate()).equalsIgnoreCase("")){
				addActionError(getText("errors.InceptionDate.required"));			
			}
			else if("INVALID".equalsIgnoreCase(val.checkDate(bean.getInceptionDate()))){
				addActionError(getText("errors.InceptionDate.Error"));
			}else if(!"".equals(bean.getRenewal_Contract_no())&&map!=null){
				if("Invalid".equalsIgnoreCase(val.getDateValidate((String)map.get("EXPIRY_DATE"),bean.getInceptionDate()))){
					addActionError(getText("errors.InceptionDate.invalid"));
				}else{
					bean.setRenewalFlag("NEWCONTNO");
				}
			}
			if(val.isNull(bean.getExpiryDate()).equalsIgnoreCase("")){
				addActionError(getText("errors.ExpiryDate.required"));			
			}
			else if("INVALID".equalsIgnoreCase(val.checkDate(bean.getExpiryDate()))){
				addActionError(getText("errors.ExpiryDate.Error"));
			}
			if (!bean.getInceptionDate().equalsIgnoreCase("")&& !bean.getExpiryDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getInceptionDate(),bean.getExpiryDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.expDate.check"));
				}
			}
			if(val.isSelect(bean.getYear()).equalsIgnoreCase("")){			
				addActionError(getText("errors.year.required"));
			}else if(!"".equals(bean.getRenewal_Contract_no())&& map!=null && Integer.parseInt((String)map.get("UW_YEAR"))>=Integer.parseInt(bean.getYear())){
				//addActionError(getText("errors.year.invalid"));
			}
			boolean excFlag=true;
			if((StringUtils.isNotBlank(bean.getProStatus()) &&"A".equalsIgnoreCase(bean.getProStatus())) || !"".equalsIgnoreCase(bean.getAccountDate())){
			if(val.isNull(bean.getAccountDate()).equalsIgnoreCase("")){
				addActionError(getText("errors.AccountDate.required"));
				excFlag=false;
			}
			else if("INVALID".equalsIgnoreCase(val.checkDate(bean.getAccountDate()))){
				addActionError(getText("errors.AccountDate.Error"));
				excFlag=false;
			}
			if (!bean.getExpiryDate().equalsIgnoreCase("")&& !bean.getAccountDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getAccountDate(),bean.getExpiryDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check"));
				}
			}
			if(!val.isNull(bean.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAccountDate()).equalsIgnoreCase("") && !bean.getEdit().equalsIgnoreCase("endorsment")){
			if(new DropDownControllor().Validatethree(branchCode, bean.getAccountDate())==0){
				addActionError(getText("errors.open.period.date",new String[] {bean.getOpenPeriodDate()}));
			}
			}
			if(val.isSelect(bean.getOriginalCurrency()).equalsIgnoreCase("")){
				addActionError(getText("errors.originalCurrency.required"));
				excFlag=false;
			}
			if(excFlag && StringUtils.isBlank(bean.getUsCurrencyRate())){
				bean.setUsCurrencyRate(new DropDownControllor().GetExchangeRate(bean.getOriginalCurrency(),bean.getAccountDate(),bean.getCountryID(),bean.getBranchCode()));
			}
			}
			if(val.isNull(bean.getUsCurrencyRate()).equalsIgnoreCase("")){
				addActionError(getText("errors.usCurrencyRate.required"));
				cedCheck=false;
			}
			boolean spflag=true;
			if(val.isNull(bean.getSpRetro()).equalsIgnoreCase("")){
				addActionError(getText("errors.SpRetro.error"));
				spflag=false;
			}
			if(val.isNull(bean.getNo_Insurer()).equalsIgnoreCase("")){
				addActionError(getText("Errors.No_Insurar.Required"));
			}else if("INVALID".equalsIgnoreCase(val.isValidNo(bean.getNo_Insurer()))){
				addActionError(getText("Errors.No_Insurar.NumberFormat"));
			}else if(spflag && "Y".equals(bean.getSpRetro()) && Integer.parseInt(bean.getNo_Insurer())<=0){
				addActionError(getText("Errors.No_Insurar.gr0"));
			}

			if(StringUtils.isBlank(bean.getTerritory())){
				addActionError(getText("errors.territoryCode.required"));
			}if(StringUtils.isBlank(bean.getCountryIncludedList())){
				addActionError(getText("errors.CountryInclude.required"));
			}/*if(StringUtils.isBlank(bean.getCountryExcluded())){
				addActionError(getText("errors.CountryExclude.required"));
			}*/
			if(val.isNull(bean.getInsuredName()).equalsIgnoreCase("")){
				addActionError(getText("errors.insuredName.required"));
			}
			if(val.isNull(bean.getInterest()).equalsIgnoreCase("")){
				addActionError(getText("errors.interestCoverage.required"));
			}
			if(val.isNull(bean.getCity()).equalsIgnoreCase("")){
				addActionError(getText("errors.city.required"));
			}
			if(val.isNull(bean.getLocation()).equalsIgnoreCase("")){
				addActionError(getText("errors.location.required"));
			}
			if(StringUtils.isBlank(bean.getLatitude())){
				//addActionError(getText("latitude.error"));
			}
			if(StringUtils.isBlank(bean.getLongitude())){
				//addActionError(getText("longtitude.error"));
			}
			if(StringUtils.isBlank(bean.getLocIssued())){
				addActionError(getText("locissued.error"));
			}
			else if("Y".equalsIgnoreCase(bean.getLocIssued())){
				if(StringUtils.isBlank(bean.getLocBankName())){
					addActionError(getText("error.locbank.required"));
				}
				if(StringUtils.isBlank(bean.getLocCreditPrd())){
					addActionError(getText("error.loccrditPerd.required"));
				}
				if(StringUtils.isBlank(bean.getLocCreditAmt())){
					addActionError(getText("error.loccreditAmt.required"));
				}else{
					bean.setLocCreditAmt(bean.getLocCreditAmt().replaceAll(",", ""));
				}
				if(StringUtils.isBlank(bean.getLocBeneficerName())){
					addActionError(getText("error.locbenifName.required"));
				}
				
				}
			double amt=0.0;
			/*if(val.isNull(bean.getSipml()).equalsIgnoreCase("")){
				addActionError(getText("errors.sipml.required"));
			}else{
				logger.info(" SIPML =>"+bean.getSipml());
				if(bean.getSipml().equalsIgnoreCase("PML")){
					if(val.isNull(bean.getPml()).equalsIgnoreCase(""))	{
						addActionError(getText("errors.pml.required"));
					}else if("INVALID".equalsIgnoreCase(val.percentageValid(bean.getPml().trim()))){
						addActionError(getText("errors.pml.percentages"));
					}else if(val.percentageValid(bean.getPml().trim()).equalsIgnoreCase("greater")){
						addActionError(getText("errors.pml.greater"));
					} 
					/*if(val.isNull(bean.getPmll()).equalsIgnoreCase("")){
						addActionError(getText("errors.pmll.required"));
						cedCheck=false;
					}else{
						bean.setPmll(bean.getPmll().replaceAll(",",""));
						if("INVALID".equalsIgnoreCase(val.isValidNo(bean.getPmll().trim()))){
							addActionError(getText("errors.pmll.percentages"));
							cedCheck=false;
						}else{
							amt=Double.parseDouble(bean.getPmll());
						}
					}*
				}
			}*/
			if(val.isNull(bean.getNr()).equalsIgnoreCase("")){
				addActionError(getText("errors.nr.required"));
			}
			if("4".equals(bean.getDepartmentId())){
				if(val.isSelect(bean.getModeOfTransport()).equalsIgnoreCase("")){
					addActionError(getText("errors.modeofTransport.required"));
				}
				if(val.isNull(bean.getVesselName()).equalsIgnoreCase("")){
					addActionError(getText("errors.vesselName.required"));
				}
				if(val.isNull(bean.getVesselAge()).equalsIgnoreCase("")){
					addActionError(getText("errors.vesselAge.required"));
				}
				else if("INVALID".equalsIgnoreCase(val.isValidNo(bean.getVesselAge()))){
					addActionError(getText("errors.vesselAge.invalid"));
				}else if(Integer.parseInt(bean.getVesselAge())>100){
					addActionError(getText("errors.vesselAge.lessEqualHundred"));
				}
					if(StringUtils.isBlank(bean.getVessaletonnage())){
						addActionError(getText("empty.vessal.age"));
					}
				if(val.isNull(bean.getLimitPerVesselOC()).equalsIgnoreCase("")){
					addActionError(getText("errors.LimitPerVesselOC.required"));
				}else{
					bean.setLimitPerVesselOC((bean.getLimitPerVesselOC()).replaceAll(",",""));
					if(val.isValidNo(bean.getLimitPerVesselOC().trim()).equalsIgnoreCase("INVALID")){
						addActionError(getText("errors.LimitPerVesselOC.invalid"));
					}
				}
				if(val.isNull(bean.getLimitPerLocationOC()).equalsIgnoreCase("")){
					addActionError(getText("errors.LimitPerLocationOC.required"));
				}else{
					bean.setLimitPerLocationOC((bean.getLimitPerLocationOC()).replaceAll(",",""));
					if(val.isValidNo(bean.getLimitPerLocationOC().trim()).equalsIgnoreCase("INVALID")){
						addActionError(getText("errors.LimitPerLocationOC.invalid"));
					}
				}
			}
			
			
			boolean gwpiFlag=true;
			
			//if(!"4".equalsIgnoreCase(session.get("DepartmentId").toString())){
				//bean.setSumInsured("");
				
			//}
			if(StringUtils.isNotBlank(bean.getType()) && "2".equalsIgnoreCase(bean.getType())){
				if(val.isNull(bean.getDeductible()).equalsIgnoreCase("")){
					addActionError(getText("errors.deductible.required"));
				}else{
					bean.setDeductible((bean.getDeductible()).replaceAll(",",""));
					if(val.isValidNo(bean.getDeductible()).equalsIgnoreCase("INVALID")){
						addActionError(getText("errors.deductible.Error"));
					}
				}
				//bean.setSumInsured("");
				if(!val.isNull(bean.getDeductibleFacXol()).equalsIgnoreCase("")){
					bean.setDeductibleFacXol((bean.getDeductibleFacXol()).replaceAll(",",""));
					if(val.isValidNo(bean.getDeductibleFacXol()).equalsIgnoreCase("INVALID")){
						addActionError(getText("errors.deductibleFacXOL.Invalid"));
						gwpiFlag=false;
					}
				}
				/*if(bean.getSipml().equalsIgnoreCase("PML")){
					if(StringUtils.isBlank(bean.getDeductibleFacXolPml())){
						addActionError(getText("cover.pml"));
					}
					else{
						bean.setDeductibleFacXolPml(bean.getDeductibleFacXolPml().replaceAll(",", ""));
						}
				}*/
				}else{
					
				}
			
			
			boolean cedflag=true;
			if(StringUtils.isNotBlank(bean.getType()) && "1".equalsIgnoreCase(bean.getType())){
			if (val.isNull(bean.getCedRetenType()).equalsIgnoreCase("")) {
				addActionError( getText("error.cedRentType.required"));
				cedflag=false;
			}else{
				if(val.isNull(bean.getCedantsRet()).equalsIgnoreCase("")){
					addActionError(getText("errors.cedantsRet.required"));
					cedflag=false;
				}else{
					bean.setCedantsRet((bean.getCedantsRet().replaceAll(",","")));
					if("A".equalsIgnoreCase(bean.getCedRetenType())){						
						cedflag=false;
						if("INVALID".equalsIgnoreCase(val.isValidNo(bean.getCedantsRet()))){
							addActionError(getText("error.cedRentAmt.required"));
						}
					}else if("P".equalsIgnoreCase(bean.getCedRetenType())){
						if (val.percentageValid(bean.getCedantsRet()).trim().equalsIgnoreCase("Invalid")||val.percentageValid(bean.getCedantsRet().trim()).equalsIgnoreCase("less")||val.percentageValid(bean.getCedantsRet().trim()).equalsIgnoreCase("greater")) {
							addActionError( getText("error.cedRentPer.required"));
							cedflag=false;
						}
					}
				}
			}
			}
			
			
			if(StringUtils.isNotBlank(bean.getType()) && "2".equalsIgnoreCase(bean.getType())){
				//if(StringUtils.isNotBlank(bean.getSipml()) && !"LL".equalsIgnoreCase(bean.getSipml())) {
					if(val.isNull(bean.getPremiumrate()).equalsIgnoreCase("")){
						addActionError(getText("errors.premium1.required"));
						gwpiFlag=false;
					}else if(val.percentageValid(bean.getPremiumrate().trim()).equalsIgnoreCase("less")){
						addActionError(getText("errors.premium1.percentagesless"));
						gwpiFlag=false;
					}else if(val.percentageValid(bean.getPremiumrate().trim()).equalsIgnoreCase("greater")){
						addActionError(getText("errors.premium1.percentagesgreater"));
						gwpiFlag=false;
					}
			}
				//bean.setDeductible("");
				//bean.setDeductibleFacXol("");
			/*	if("4".equalsIgnoreCase(session.get("DepartmentId").toString())){
			if(val.isNull(bean.getSumInsured()).equalsIgnoreCase("")){
				addActionError(getText("errors.sumInsured.required"));
				cedCheck=false;
				gwpiFlag=false;
			}else{
				bean.setSumInsured((bean.getSumInsured()).replaceAll(",",""));
				if(val.isValidNo(bean.getSumInsured().trim()).equalsIgnoreCase("INVALID")){
					if("4".equalsIgnoreCase(session.get("DepartmentId").toString())){
						addActionError(getText("errors.turn.sumInsured.percentages"));
					}
					else{
					addActionError(getText("errors.sumInsured.percentages"));
					}
					cedCheck=false;
					gwpiFlag=false;
				}else{
					//if(bean.getSipml().equalsIgnoreCase("SI")){
						amt=Double.parseDouble(bean.getSumInsured());
					//}
				}
			}	
				}*/
			/*if(bean.getSipml().equalsIgnoreCase("PML")){
				if(StringUtils.isBlank(bean.getSumInsuredPml())){
					if("4".equalsIgnoreCase(session.get("DepartmentId").toString())){
						addActionError(getText("errors.turn.sumInsured.pml"));
					}
					else{
					addActionError(getText("sum.pml"));
					}
				}
				else{
					bean.setSumInsuredPml(bean.getSumInsuredPml().replaceAll(",",""));
				}
			}*/
			if("RI02".equalsIgnoreCase(sourceId) && "4".equalsIgnoreCase(session.get("DepartmentId").toString()) && StringUtils.isNotBlank(bean.getType()) && "1".equalsIgnoreCase(bean.getType())){
				if(StringUtils.isBlank(bean.getPslOC())){
					addActionError(getText("psl.error"));
				}
				else{
					bean.setPslOC(bean.getPslOC().replaceAll(",",""));
				}
				if(StringUtils.isBlank(bean.getPllOC())){
					addActionError(getText("pll.error"));
				}
				else{
					bean.setPllOC(bean.getPllOC().replaceAll(",",""));
				}
				if(StringUtils.isBlank(bean.getPblOC())){
					addActionError(getText("pbl.error"));
				}
				else{
					bean.setPblOC(bean.getPblOC().replaceAll(",",""));
				}
				if(val.isNull(bean.getPmll()).equalsIgnoreCase("")){
					addActionError(getText("errors.pmll.required"));
					cedCheck=false;
				}else{
					bean.setPmll(bean.getPmll().replaceAll(",",""));
					if("INVALID".equalsIgnoreCase(val.isValidNo(bean.getPmll().trim()))){
						addActionError(getText("errors.pmll.percentages"));
						cedCheck=false;
					}else{
						amt=Double.parseDouble(bean.getPmll());
					}
				}
			}
			
			/*if(val.isNull(bean.getGwpi()).equalsIgnoreCase("")){
				addActionError(getText("errors.gwpi.required"));
				gwpiFlag=false;
			}else{
				bean.setGwpi((bean.getGwpi()).replaceAll(",",""));
				if(val.isValidNo(bean.getGwpi().trim()).equalsIgnoreCase("INVALID")){
					addActionError(getText("errors.gwpi.percentages"));
					gwpiFlag=false;
				}
			}*/
			/*if(bean.getSipml().equalsIgnoreCase("PML")){
				if(StringUtils.isBlank(bean.getGwpiPml())){
					addActionError(getText("Gwpi.pml.error"));
				}
				else{
					bean.setGwpiPml((bean.getGwpiPml()).replaceAll(",",""));
				}
			}*/
			if(StringUtils.isNotBlank(bean.getType()) && "2".equalsIgnoreCase(bean.getType())){//need to check
			//if(StringUtils.isNotBlank(bean.getSipml()) && !"LL".equalsIgnoreCase(bean.getSipml())) {
			if(gwpiFlag&&("".equalsIgnoreCase(bean.getContractNo())||"0".equalsIgnoreCase(bean.getContractNo()))){
				double si=bean.getType().equalsIgnoreCase("1")?Double.parseDouble(bean.getSumInsured()):Double.parseDouble(bean.getDeductibleFacXol());
				double rate=Double.parseDouble(bean.getPremiumrate());
				if(rate!=0){
					double preAmt=(si*rate)/100.0;
					double pre10Per=(((si*rate)/100.0)*10.0)/100.0;
					double gwpi=Double.parseDouble(bean.getGwpi());
					logger.info("Sum Insured 100%>"+si);
					logger.info("Premium Rate =>"+rate);
					logger.info("Premium Amount=>"+preAmt);
					logger.info("(preAmt+pre10Per)=>"+(preAmt+pre10Per));
					logger.info("(preAmt-pre10Per)=>"+(preAmt-pre10Per));
					logger.info("GWPI 100%=>"+gwpi);
					if(!(gwpi>=(preAmt-pre10Per)&&gwpi<=(preAmt+pre10Per))){
						addActionError(getText("errors.gwpi.exceedPremiumLimit"));
				}
			}
		}
			}
			if("RI01".equalsIgnoreCase(sourceId)){
				if(val.isNull(bean.getTpl()).equalsIgnoreCase("")){
					addActionError(getText("errors.tpl.required"));
				}else{
					bean.setTpl((bean.getTpl()).replaceAll(",",""));
					if("INVALID".equalsIgnoreCase(val.isValidNo(bean.getTpl().trim()))){
						addActionError(getText("errors.tpl.percentages"));
					}
				}
			}
			if(val.isNull(bean.getNoOfInst()).equalsIgnoreCase("")){
				addActionError(getText("errors.noOfInstallments.required"));
			}
			else if("INVALID".equalsIgnoreCase(val.isValidNo(bean.getNoOfInst()))){
				addActionError(getText("errors.noOfInstallments.invalid"));
			}else if(Integer.parseInt(bean.getNoOfInst())<=0){
				addActionError(getText("errors.noOfInstallments.grZero"));
			}
			if(StringUtils.isBlank(bean.getReceiptofPayment())){
				addActionError(getText("payment.days.required"));
			}
			final String proStatus=val.isSelect(bean.getProStatus());

			if(proStatus.equalsIgnoreCase("")) {
				addActionError(getText("errors.Status.percentages"));
			}
			
			flaging=true;
			double cedPer=0.0;
			if(val.isNull(bean.getShWt()).equalsIgnoreCase("")){
				addActionError(getText("errors.shWt.required"));
				flaging=false;
				cedCheck=false;
			}else if("INVALID".equalsIgnoreCase(val.percentageValid(bean.getShWt().trim()))){
				addActionError(getText("errors.shWt.percentages"));
				flaging=false;
				cedCheck=false;
			}else if(val.percentageValid(bean.getShWt().trim()).equalsIgnoreCase("greater")){
				addActionError(getText("errors.shWt.greater"));
				flaging=false;
				cedCheck=false;
			}else{
				cedPer=Double.parseDouble(bean.getShWt());
				if("P".equalsIgnoreCase(bean.getCedRetenType())){
				if(cedflag && Double.parseDouble(bean.getShWt())+Double.parseDouble(StringUtils.isEmpty(bean.getCedantsRet())? "0":bean.getCedantsRet())>100){
					addActionError(getText("error.SWCedPer.invalid"));
				}}
			}

			if("A".equalsIgnoreCase(proStatus)){
				if("INVALID".equalsIgnoreCase(val.percentageNewValid(bean.getShWt().trim()))){
					addActionError(getText("errors.shWt.percentages"));
					flaging=false;
					cedCheck=false;
				}
				if(val.isNull(bean.getShSd()).equalsIgnoreCase("")){
					addActionError(getText("error.shareSign.required"));
					flaging=false;
					cedCheck=false;
				}else if("INVALID".equalsIgnoreCase(val.percentageValid(bean.getShSd().trim()))){
					addActionError(getText("error.shareSign.per"));
					flaging=false;
					cedCheck=false;
				}else if(val.percentageValid(bean.getShSd().trim()).equalsIgnoreCase("greater")){
					addActionError(getText("errors.shSd.greater"));
					flaging=false;
					cedCheck=false;
				}else{
					cedPer=Double.parseDouble(bean.getShSd());
				}
				if(flaging==true){
					if(Double.parseDouble(bean.getShSd().equalsIgnoreCase("")?"0":bean.getShSd())>Double.parseDouble(bean.getShWt().equalsIgnoreCase("")?"0":bean.getShWt())){
						addActionError(getText("error.shareSign.invalid"));
						cedCheck=false;
					}
				}
				if(cedCheck){
					double amount=((amt*(cedPer/100.0))/Double.parseDouble(bean.getUsCurrencyRate()));
					double maxlimit=Double.parseDouble(bean.getMaxiumlimit());
					logger.info("Cedent Amount=>"+amount);
					logger.info("Max Limit=>"+maxlimit);
					if(amount>maxlimit){
						addActionError(getText("error.accAmtlessUWAmt"));
					}
				}
			}
			if(StringUtils.isNotBlank(bean.getGwpi()) && StringUtils.isNotBlank(bean.getShSd())){
				final  DecimalFormat twoDigit = new DecimalFormat("###0.00");
				final double dvalue = (Double.parseDouble(bean.getGwpi())* (Double.parseDouble(bean.getShSd())) / 100);
				logger.info("Gwpi Our Share Calculated Value=>" + dvalue);
				final double dround = Math.round(dvalue * 100.0) / 100.0;
				logger.info("Rounded Value=>" + dround);
				final double valu = Double.parseDouble(twoDigit.format(dround));//A
				logger.info("Formated Value=>" + valu);
				if(StringUtils.isNotBlank(bean.getContractNo())){
				double sumInst=new DropDownControllor().getSumOfInstallmentBooked(bean.getContractNo(), "0");//B
				if(valu<sumInst){
					addActionError(getText("error.installment.premiumbooked"));
				}
				}
			}if(StringUtils.isNotBlank(bean.getNoOfInst())){
				int count=new DropDownControllor().getCountOfInstallmentBooked(bean.getContractNo(), "0");
				if(Double.parseDouble(bean.getNoOfInst())<count){
					addActionError(getText("error.no.installment.premiumbooked"));
				}
			}
			validationContract();
			validationRemarks();
			if(StringUtils.isNotBlank(bean.getType())&&"1".equalsIgnoreCase(bean.getType()) ){
			validationCoverDeductable();
			}else{
				validationXolCoverDeductable();
			}
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}

	private void getSecondSubmitError(){
		try{
			final Validation validation=new Validation();
			logger.info("Risk grade==>"+bean.getRiskGrade());
			/*if(bean.getSipml().equalsIgnoreCase("SI") && bean.getType().equalsIgnoreCase("1")) {
				if(validation.isNull(bean.getSumInsuredOurShare()).equalsIgnoreCase("")) {
					addActionError(getText("errors.sumInsuredOurShare.required"));
				} else if("INVALID".equalsIgnoreCase(validation.isValidNo(bean.getSumInsuredOurShare()))) {
					addActionError(getText("errors.sumInsuredOurShare.invalid"));
				}
			}*/
			if(validation.isNull(bean.getGwpiOurShare()).equalsIgnoreCase("")) {
				addActionError(getText("errors.gwpiOurShare.required"));
			} else if("INVALID".equalsIgnoreCase(validation.isValidNo(bean.getGwpiOurShare()))) {
				addActionError(getText("errors.gwpiOurShare.invalid"));
			}
			/*if(bean.getSipml().equalsIgnoreCase("PML")){
				if(validation.isNull(bean.getPmlOurShare()).equalsIgnoreCase("")){
					addActionError(getText("errors.pmlOurShare.required"));
				} else if("INVALID".equalsIgnoreCase(validation.isValidNo(bean.getPmlOurShare()))) {
					addActionError(getText("errors.pmlOurShare.invalid"));
				}
			}
				if(validation.isNull(bean.getTplOurShare()).equalsIgnoreCase("")){
					addActionError(getText("errors.tplOurShare.required"));
				} else if("INVALID".equalsIgnoreCase(validation.isValidNo(bean.getTplOurShare()))) {
					addActionError(getText("errors.tplOurShare.invalid"));
				}*/
			boolean tata = false;
			double gwpiOSViewOC=Double.parseDouble(bean.getGwpiOSViewOC().replaceAll(",", ""));
			double totalInstPremium=0.0;
			  
			for (int i = 0; i < Integer.parseInt(bean.getNoOfInst()); i++) {
				if (validation.isNull(bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
					addActionError( getText("Error.required.InstalDate",new String[] { String.valueOf(i + 1) }));
				} else if (validation.checkDate(bean.getInstalmentDateList().get(i)).equalsIgnoreCase(	"Invalid")) {
					addActionError(getText("Error.Error.InstalDate",new String[] { String.valueOf(i + 1) }));
				}
				if (!validation.isNull(bean.getInstalmentDateList().get(0)).equalsIgnoreCase("")) {
					if (validation.ValidateINstallDates(bean.getInceptionDate(),bean.getInstalmentDateList().get(0)).equalsIgnoreCase("Invalid")) {
						tata = true;
					}
				}
				if (!validation.isNull(	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
					if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i),bean.getExpiryDate()).equalsIgnoreCase("Invalid")) {
						addActionError( getText("Error.Select.Expirydate",new String[] { String.valueOf(i + 1) }));
					}
				}
				if (validation.isNull(bean.getInstallmentPremium().get(i)).equalsIgnoreCase("")) {
					addActionError( getText("Error.required.InstallPremium"));
				} else if (validation.isValidNo(bean.getInstallmentPremium().get(i).replaceAll(",", "")).equalsIgnoreCase("Invalid")) {
					addActionError(getText("Error.error.InstallPremium"));
				}else{
					try{
		            	totalInstPremium+=Double.parseDouble(bean.getInstallmentPremium().get(i).replaceAll(",", ""));                	
		            }catch (Exception e) {
		            	addActionError(getText("Error.installment.Premium",new String[]{String.valueOf(i+1)}));
					}
				}
				if (i != 0) {
					if (!validation.isNull(bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
						if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i-1),bean.getInstalmentDateList().get(i)).equalsIgnoreCase("Invalid")) {
							addActionError(getText("Error.Error.InstalDate",new String[] {String.valueOf( i + 1 )}));
						}
					}
				}
				if(StringUtils.isBlank(bean.getPaymentDueDays().get(i))){
					addActionError(getText("payment.due.days",new String[] {String.valueOf( i + 1 )}));
				}
				
			}
			
			BigDecimal bd = new BigDecimal(totalInstPremium).setScale(2, RoundingMode.HALF_EVEN);
			totalInstPremium = bd.doubleValue();
			if((totalInstPremium)!=gwpiOSViewOC){
				addActionError(getText("Error.total.installment.premium" ,new String[]{"GWPI Our Share"}));
		    }
		   if (tata == true) {
				addActionError(getText("Error.Select.AfterInceptionDate"));
			}
		   if("Y".equalsIgnoreCase(bean.getEndorsementStatus())) {
		   bean.setAccountDate((new DropDownControllor().getAcceptanceDate(bean.getProposalNo())));
		   bean.setMaxDate(Validation.getMaxDateValidate(bean.getAccountDate(), bean.getPreviousendoDate()));
			final String endorseDate=validation.checkDate(bean.getEndorsementDate());
			if (validation.isNull(bean.getEndorsementDate()).equalsIgnoreCase("")) {
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.endoDate.required"));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.rectification.required"));
				}
				else if("GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.gnpiDate.required"));
				}
			} else if (endorseDate.equalsIgnoreCase("INVALID")) {
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.endoDate.check"));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.rectification.check"));
				}
				else if("GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("error.gnpiDate.check"));
				}
			} else  if ("Invalid".equalsIgnoreCase(Validation.ValidateTwo(bean.getMaxDate(), bean.getEndorsementDate()))) {
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.endoDate.invalid",new String[] {bean.getAccountDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.rectificationDate.invalid",new String[] {bean.getAccountDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}
				else if("GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.gnpiDate.invalid",new String[] {bean.getAccountDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}
			}
		   }
		   if("7".equals(bean.getDepartmentId())||"9".equals(bean.getDepartmentId())){
			if(validation.isSelect(bean.getRiskGrade()).equalsIgnoreCase("")){
				addActionError(getText("errors.riskGrade.required"));
			}
			if(validation.isNull(bean.getOccCode()).equalsIgnoreCase("")){
				addActionError(getText("errors.occCode.required"));
			}
			if(validation.isNull(bean.getRiskDetail()).equalsIgnoreCase("")){
				addActionError(getText("errors.riskDetail.required"));
			}
			if(validation.isNull(bean.getFireProt()).equalsIgnoreCase("")){
				addActionError(getText("errors.fireProt.required"));
			}
			if(validation.isNull(bean.getScope()).equalsIgnoreCase("")) {
				addActionError(getText("errors.scope.required"));
			}
			if(validation.isNull(bean.getMbind()).equalsIgnoreCase("")){			
				addActionError(getText("errors.mbind.required"));
			}
			if(validation.isNull(bean.getMlopYN()).equalsIgnoreCase("")) {
				addActionError(getText("errors.mlop.required"));
			}
			if(validation.isNull(bean.getAlopYN()).equalsIgnoreCase("")) {
				addActionError(getText("errors.alop.required"));
			}
			boolean wseq=true;
			if(validation.isNull(bean.getEqwsInd()).equalsIgnoreCase("-1")) {
				addActionError(getText("errors.eqwsInd.required"));
				wseq=false;
			}
			if(wseq){
				if(validation.isNull(bean.getWsThreat()).equalsIgnoreCase("")) {
					addActionError(getText("errors.wsThreat.required"));
				}
				if(validation.isNull(bean.getEqThreat()).equalsIgnoreCase("")){
					addActionError(getText("errors.eqThreat.required"));
				}
			}
			/*if(validation.isSelect(bean.getCategoryZone()).equalsIgnoreCase("")){

				addActionError(getText("errors.categoryZone.required"));

			}*/
		   }
			final int LoopCount=Integer.parseInt(bean.getNo_Insurer());
			double totPer=0.0;
			boolean flag=true;
			List<List<Map<String,Object>>> uwlList = new ArrayList<List<Map<String,Object>>>();
			List<List<Map<String,Object>>> retrolList = new ArrayList<List<Map<String,Object>>>();
			List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
			if(LoopCount!=0){
				if(validation.isNull(bean.getRetper()).equalsIgnoreCase("")){
					addActionError(getText("Error.RetentionPercentage.Required"));
					flag=false;
				}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getRetper()))){
					addActionError(getText("Error.RetentionPercentage.invalid"));
					flag=false;
				}else if(validation.percentageValid(bean.getRetper()).equalsIgnoreCase("greater")){		
					addActionError(getText("Error.RetentionPercentage.greater"));
					flag=false;
				}else{
					totPer+=Double.parseDouble(bean.getRetper());
				}
			}
			boolean dupCheck=true;
			for(int i=0;i<LoopCount;i++){
				if("".equals(bean.getRetroTypeValList()==null?"":bean.getRetroTypeValList().get(i))){
					addActionError(getText("error.retroType.Required",new String[]{String.valueOf(i+1)}));
					dupCheck=false;
				}
				if("".equals(bean.getUwYearValList()==null?"":bean.getUwYearValList().get(i)) || "0".equals(bean.getUwYearValList()==null?"":bean.getUwYearValList().get(i))){
					addActionError(getText("error.uwYear.Required",new String[]{String.valueOf(i+1)}));
					dupCheck=false;
				}			
				if("".equalsIgnoreCase(bean.getCedingCompanyValList()==null?"":bean.getCedingCompanyValList().get(i)) || "0".equalsIgnoreCase(bean.getCedingCompanyValList()==null?"":bean.getCedingCompanyValList().get(i))){
					addActionError(getText("Error.CeddingCompany.Required",new String[]{String.valueOf(i+1)}));
					dupCheck=false;
				}
				/*else if(dupCheck==true&&"SR".equals(bean.getRetroTypeValList().get(i)))//&&!"".equals(bean.getContractNo())&&!"0".equals(bean.getContractNo())
				{
					FaculitivesBean form=new FaculitivesBean();
					form.setContractNo(bean.getCedingCompanyValList().get(i));
					List<Map<String,Object>> li= service.getValidation(form, 2);
					if(li!=null&&li.size()>0){
						Map<String,Object> map=(Map<String,Object>)li.get(0);
						if(!"".equals(bean.getContractNo())&&!"0".equals(bean.getContractNo())){
							if(!"0".equals(map.get("FAC_CONTRACT_NO").toString())&&!map.get("FAC_CONTRACT_NO").toString().equals(bean.getContractNo())){
								addActionError(getText("error.retor.mapped", new String[]{bean.getCedingCompanyValList().get(i),map.get("FAC_CONTRACT_NO").toString(),String.valueOf(i+1)}));
							}
						}else{
							if(!"0".equals(map.get("FAC_CONTRACT_NO").toString())){
								addActionError(getText("error.retor.mapped",new String[]{bean.getCedingCompanyValList().get(i),map.get("FAC_CONTRACT_NO").toString(),String.valueOf(i+1)}));
							}
						}
					}
				}*/
				List<String> list = new ArrayList<String>();
				bean.setRetroDupVal(list);
				if(validation.isNull(bean.getRetroPercentage()==null?"":bean.getRetroPercentage().get(i)).equalsIgnoreCase("")){
					addActionError(getText("Error.RetroPercentahge.Required",new String[]{String.valueOf(i+1)}));
					flag=false;
				}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getRetroPercentage().get(i)))){
					addActionError(getText("Error.RetroPercentahge.invalid",new String[]{String.valueOf(i+1)}));
					flag=false;
				}else if(validation.percentageValid(bean.getRetroPercentage().get(i)).equalsIgnoreCase("greater")){
					addActionError(getText("Error.RetroPercentahge.greater",new String[]{String.valueOf(i+1)}));
					flag=false;
				}else{
					totPer+=Double.parseDouble(bean.getRetroPercentage().get(i));
				}
				uwlList.add(new ArrayList<Map<String,Object>>());
				retrolList.add(new ArrayList<Map<String,Object>>());
				retroDupList.add(new ArrayList<Map<String,Object>>());
			}
			bean.setUwlList(uwlList);
			bean.setRetrolList(uwlList);
			bean.setRetroDupList(retroDupList);
			if (dupCheck) {
				for (int i = 0; i < LoopCount - 1; i++) {
					for (int j = i + 1; j < LoopCount; j++) {
						if(StringUtils.isNotBlank(bean.getCedingCompanyValList().get(i))){
						if (bean.getCedingCompanyValList().get(i).equalsIgnoreCase(bean.getCedingCompanyValList().get(j))) {
							addActionError(getText("error.RetroContract.Repeat",new String[]{String.valueOf(j+1)}));
						}
						}
					}
				}
			}
			if(LoopCount!=0){	
				if(flag){	
					DecimalFormat df = new DecimalFormat("#.##");
					totPer=Double.parseDouble(df.format(totPer));
					if(totPer!=100){
						addActionError(getText("error.totPercentage.invalid"));
					}
				}
			}
			if(StringUtils.isNotBlank(bean.getCrestaStatus())){
			if(bean.getCrestaStatus().equalsIgnoreCase("Y")){
				if(StringUtils.isBlank(bean.getCrestaPopUp())){
					addActionError(getText("cresta.popup.check"));
				}
				else if(service.getCrestaCount(bean)==0){
					addActionError(getText("error.creasta.invalid"));
				}
			}
			}
			if(validation.isNull(bean.getCommn()).equalsIgnoreCase("")){
				addActionError(getText("errors.commn.required"));
			}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getCommn()))){
				addActionError(getText("errors.commn.percentage"));
			}else if(validation.percentageValid(bean.getCommn()).equalsIgnoreCase("greater")){
				addActionError(getText("errors.commn.greater"));
			}		 
			if(validation.isNull(bean.getOthercost()).equalsIgnoreCase("")) {
				addActionError(getText("errors.othercost.required"));
			}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getOthercost()))) {
				addActionError(getText("errors.othercost.secondinvalid"));
			}else if(validation.percentageValid(bean.getOthercost()).equalsIgnoreCase("greater")){
				addActionError(getText("errors.othercost.secondgreater"));
			}else if(validation.percentageValid(bean.getOthercost()).equalsIgnoreCase("less")){
				addActionError(getText("errors.othercost.secondless"));
			}
			if(validation.isNull(bean.getBrokerage()).equalsIgnoreCase("")){
				addActionError(getText("errors.brokerage.required"));
			}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getBrokerage()))){
				addActionError(getText("errors.brokerage.percentage"));
			}else if(validation.percentageValid(bean.getBrokerage()).equalsIgnoreCase("greater")){
				addActionError(getText("errors.brokerage.greater"));
			}
			if(validation.isNull(bean.getTax()).equalsIgnoreCase("")){
				addActionError(getText("errors.tax.required"));
			}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getTax()))){
				addActionError(getText("errors.tax.percentage"));
			}else if(validation.percentageValid(bean.getTax()).equalsIgnoreCase("greater")){
				addActionError(getText("errors.tax.greater"));
			}
			if(StringUtils.isNotBlank(bean.getAcqCostPer())){
				String ans = calcu.calculateFacultative(bean,"AcqPer",0);
				if(Double.parseDouble(ans)!=Double.parseDouble(bean.getAcqCostPer().replaceAll(",",""))){
					addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.setAcqCostPer(ans);
				}
			}if(StringUtils.isNotBlank(bean.getAcqCost())){
				String ans = calcu.calculateFacultative(bean,"AcqCost",0);
				if(Double.parseDouble(ans)!=Double.parseDouble(bean.getAcqCost().replaceAll(",",""))){
					addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.setAcqCost(ans);
				}
			}
			if(validation.isNull(bean.getLossRecord()).equalsIgnoreCase("")){
				addActionError(getText("errros.lossRecord.required"));
			}else if("Y".equalsIgnoreCase(bean.getLossRecord())){
				validationLossRecord();
			}
			if(validation.isNull(bean.getCu()).equalsIgnoreCase("")){
				addActionError(getText("errors.cu.required"));
			}else if("INVALID".equalsIgnoreCase(validation.percentageValid(bean.getCu()))){
				addActionError(getText("errors.cu.percentages"));
			}else if(validation.percentageValid(bean.getCu()).equalsIgnoreCase("greater")){
				addActionError(getText("errors.cu.greater"));
			} else{
				String ans = calcu.calculateFacultative(bean,"CapacityUti",0);
				if(Double.parseDouble(ans)!=Double.parseDouble(bean.getCu().replaceAll(",",""))){
					addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.setCu(ans);
				}
			}
			if(validation.isNull(bean.getCuRsn()).equalsIgnoreCase("")){
				addActionError(getText("errors.cuRsn.required"));
			}
			
			if(StringUtils.isBlank(bean.getLeader_Underwriter())){
				addActionError(getText("lead.under.req"));
			}
			if("RI02".equalsIgnoreCase(sourceId)){
				if(StringUtils.isBlank(bean.getLeader_Underwriter_country())){
					addActionError(getText("error.underwriter.country"));
				}
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
			}if(StringUtils.isNotBlank(bean.getLeader_Underwriter()) &&    !"64".equalsIgnoreCase(bean.getLeader_Underwriter())){
				
				 if(service.GetShareValidation(bean)){
					addActionError(getText("errors.leader_Underwriter_share.greater.signed"));
						}
				}else if(!"64".equalsIgnoreCase(bean.getLeader_Underwriter())){
					if(dropDownController.GetShareEqualValidation(bean.getProductId(),bean.getLeader_Underwriter_share(),bean.getProposal_no())){
						addActionError(getText("errors.leader_Underwriter_share.equals.signed"));
				}
					
				}
			if(validation.isNull(bean.getUwRecommendation()).equalsIgnoreCase("")){
				addActionError(getText("errors.uwRecommendation.required"));
			}
			

			/*if(validation.isNull(bean.getReftoHO()).equalsIgnoreCase("")){
				addActionError(getText("errors.reftoHO.required"));
			}
			 */
			
			if(validation.isNull(bean.getAcqCost()).equalsIgnoreCase("")){
				addActionError(getText("errors.acquisition_Cost.second"));
			}else{
				bean.setAcqCost((bean.getAcqCost()).replaceAll(",",""));
				if("INVALID".equalsIgnoreCase(validation.isValidNo(bean.getAcqCost()))){
					addActionError(getText("errors.acquisition_Cost.second1"));
				}
			}
			bean.setAcqCost((bean.getAcqCost()).replaceAll(",",""));
			logger.info("ACQCost==>"+bean.getAcqCost());
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && StringUtils.isBlank(bean.getDocStatus())) {
				addActionError(getText("doc.status"));
			}
			if(StringUtils.isBlank(bean.getRetroDupContract())){
				this.addActionError(getText("errors.dummy.contract",new String[]{bean.getYear()}));
			}
			validationRemarks();
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}
	
	
	private void validationContract() {
		String sumInsured="";
		try{
			if(StringUtils.isNotBlank(bean.getCedingCompany())&&StringUtils.isNotBlank(bean.getInceptionDate())&&StringUtils.isNotBlank(bean.getExpiryDate())&&StringUtils.isNotBlank(bean.getYear())&&StringUtils.isNotBlank(bean.getOriginalCurrency())&&StringUtils.isNotBlank(bean.getDepartmentId())&&StringUtils.isNotBlank(bean.getType())&&StringUtils.isNotBlank(bean.getProfitCenterCode())){
				if(StringUtils.isNotBlank(bean.getType())&&"1".equalsIgnoreCase(bean.getType())){
						sumInsured =StringUtils.isEmpty(bean.getSumInsured())? "0":bean.getSumInsured();
					}
					else{
						sumInsured = StringUtils.isEmpty(bean.getDeductibleFacXol())? "0":bean.getDeductibleFacXol();
					}	
				bean.setContractTypelist(dropDownController.getContractValidation(productId,bean.getCedingCompany(),bean.getInceptionDate(),bean.getExpiryDate(),bean.getYear(),bean.getOriginalCurrency(),bean.getDepartmentId(),bean.getType(),sumInsured,StringUtils.isEmpty(bean.getContractNo())? "0":bean.getContractNo(),bean.getProfitCenterCode(),"","","","0",bean.getBranchCode()));
				if(bean.getContractTypelist().size()>0){
					if(StringUtils.isBlank(bean.getContractListVal())){
						/*String conNo="";
						for(int i=0;i<bean.getContractTypelist().size();i++){
						Map<String,Object> map=bean.getContractTypelist().get(i);
						if(i==bean.getContractTypelist().size()-1){
							conNo +=map.get("RSK_CONTRACT_NO"); 
						}
						else{
							conNo +=map.get("RSK_CONTRACT_NO")+","; 
						}
						}*/
					addActionError(getText("error.contract.list"));
					}
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}
	public String submitSecondPageEndorsement() {
		String forward = "";
		try {
			if("Y".equalsIgnoreCase(bean.getEndorsementStatus())) {
				forward = InsertSecondPage();
				if(!hasActionErrors()){
				dropDownController.UpdateInstallmentTransaction(bean.getProposalNo());
				dropDownController.updatepositionMasterEndtStatus(bean.getProposalNo(),productId,bean.getEndorsementDate(),bean.getCeaseStatus());
				}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				}
			}
			else {
				forward = SavePageSecond();
				if(!hasActionErrors()){
				dropDownController.riskDetailsEndorsement(bean.getProposalNo(),bean.getEndorsementStatus());
				}else{
					logger.info("##########Validation Message Start###########");
					Iterator<String> error = getActionErrors().iterator();
					while(error.hasNext()){
						logger.info(error.next());
					}
					logger.info("##########Validation Message End###########");
				}
			}
			
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return forward;
	}
		
	
	private void validationBonusSubmit(String val) {
		if(StringUtils.isBlank(bean.getAcqBonus())){
			//addActionError(getText("bonus.error.bonus"));
			}
			else{
			if("LCB".equalsIgnoreCase(bean.getAcqBonus()) && "save".equalsIgnoreCase(val)){
				if(StringUtils.isBlank(bean.getBonusPopUp())){
					addActionError(getText("bonus.popup.recheck"));
				}else{
				int count = service.getBonusListCount(bean);
				if(count<=0){
					addActionError(getText("bonus.error.lcb.table.empty"));
				}
				}
			}
			else if("NCB".equalsIgnoreCase(bean.getAcqBonus())){
			   if(StringUtils.isBlank(bean.getAcqBonusPercentage())){
				   addActionError(getText("bonus.error.noclaimbonu.per"));
					}
			   else if(100<Double.parseDouble(bean.getAcqBonusPercentage())){
				addActionError(getText("bonus.error.low.claim.bonus.range"));
				}
				}
			
			}
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
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
	
	public String RetroDupCheck(){
		final int LoopCount=Integer.parseInt(bean.getNo_Insurer());
		List<String> list = new ArrayList<String>();
		boolean dupCheck=true;
		if(bean.getRetroTypeValList()==null){
			addActionError(getText("error.fac.retroType.Required"));
		}else{
		int count = bean.getRetroTypeValList().size();
		
		for(int i=0;i<LoopCount;i++){
			if(i==1 && count>i ){
				if(	"".equals(bean.getRetroTypeValList()==null?"":bean.getRetroTypeValList().get(i))){
				addActionError(getText("error.fac.retroType.Required",new String[]{String.valueOf(i+1)}));
				dupCheck=false;
			}
			}
			else if(i==1){
				addActionError(getText("error.fac.retroType.Required",new String[]{String.valueOf(i+1)}));
				dupCheck=false;
			}
			if("".equals(bean.getUwYearValList()==null?"":bean.getUwYearValList().get(i)) || "0".equals(bean.getUwYearValList()==null?"":bean.getUwYearValList().get(i))){
				addActionError(getText("error.uwYear.Required",new String[]{String.valueOf(i+1)}));
				dupCheck=false;
			}	
			if("".equalsIgnoreCase(bean.getCedingCompanyValList()==null?"":bean.getCedingCompanyValList().get(i)) || "0".equalsIgnoreCase(bean.getCedingCompanyValList()==null?"":bean.getCedingCompanyValList().get(i))){
				addActionError(getText("Error.CeddingCompany.Required",new String[]{String.valueOf(i+1)}));
				dupCheck=false;
			}
			else if(dupCheck==true&&"SR".equals(bean.getRetroTypeValList().get(i)))//&&!"".equals(bean.getContractNo())&&!"0".equals(bean.getContractNo())
			{
				FaculitivesBean form=new FaculitivesBean();
				form.setContractNo(bean.getCedingCompanyValList().get(i));
				List<Map<String,Object>> li= service.getValidation(form, 2);
				if(li!=null&&li.size()>0){
					Map<String,Object> map=(Map<String,Object>)li.get(0);
					if(!"".equals(bean.getContractNo())&&!"0".equals(bean.getContractNo())){
						if(!"0".equals(map.get("FAC_CONTRACT_NO").toString())&&!map.get("FAC_CONTRACT_NO").toString().equals(bean.getContractNo())){
							list.add(getText("error.retor.mapped", new String[]{bean.getCedingCompanyValList().get(i),map.get("FAC_CONTRACT_NO").toString(),String.valueOf(i+1)}));
						}
					}else{
						if(!"0".equals(map.get("FAC_CONTRACT_NO").toString())){
							list.add(getText("error.retor.mapped",new String[]{bean.getCedingCompanyValList().get(i),map.get("FAC_CONTRACT_NO").toString(),String.valueOf(i+1)}));
						}
					}
				}
			}
			
		}
		}
		bean.setRetroDupVal(list);
		resetRetroContracts();
		resetRemarks();
		bean.setRisklist1(new DropDownControllor().getRiskGradeDropDown(branchCode));
		bean.setCategory(new DropDownControllor().getCategoryZoneDropDown(branchCode));
		bean.setEQWSIndDrop(new DropDownControllor().getConstantDropDown("2"));
		bean.setBonusList(new DropDownControllor().getBonusList());
		service.ShowSecondPagedata(bean,bean);
		List<String> dateList=bean.getInstalmentDateList();
		List<String> premiumList=bean.getInstallmentPremium();
		List<String> paymentDay=bean.getPaymentDueDays();
		List<String> ins=bean.getInstalList();
		this.BusinessCalculation(bean);
		//To show current values entered on the screen instead of values from DB.
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		for(int i=0;i<bean.getInstalmentDateList().size();i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list1.add(string);
			}
		bean.setInstallmentPremium(premiumList);
		bean.setInstalmentDateList(dateList);
		bean.setPaymentDueDays(paymentDay);
		bean.setInstalList(ins);
		this.GetDropDownLists(request,bean.getProductId(),"");
		list1 = new ArrayList<Map<String,Object>>();
		if(bean.getLossYear()!=null){
		for(int i=0;i<bean.getLossYear().size();i++){
		Map<String,Object> string = new HashMap<String,Object>();
		string.put("1","1");
		list1.add(string);
		}
		}else{
			for(int i=0;i<5;i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list1.add(string);
				}
		}
		bean.setLossRecordList(list1);
		if(StringUtils.isNotBlank(bean.getEndorsmentno())&& Integer.parseInt(bean.getEndorsmentno())>0){
			List<Integer> docList=new ArrayList<Integer>();
			if(bean.getDocId().size()>0){
				for(int i=0;i<bean.getDocId().size();i++){
					docList.add(i);
				}
			}
			else{
			for(int i=0;i<1;i++){
				docList.add(i);
			}
			}
		bean.setDocuList(docList);
		}
		return "faculengg2";
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
	
	public String removeLoss(){
		List<String> year = new ArrayList<String>();
		List<String> no = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		List<String> ins = new ArrayList<String>();
		List<String> exp = new ArrayList<String>();
		List<String> lossdate = new ArrayList<String>();
		List<String> cause = new ArrayList<String>();
		List<String> claim = new ArrayList<String>();
		List<String> premium = new ArrayList<String>();
		List<String> ratio = new ArrayList<String>();
		List<String> leader = new ArrayList<String>();
		List<String> share = new ArrayList<String>();
		bean.getLossSNoS().remove(bean.getDeleteId());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
		if(bean.getLossYear()!=null){
		for(int i=0;i<bean.getLossSNoS().size();i++){
		Map<String,Object> string = new HashMap<String,Object>();
		string.put("1","1");
		list.add(string);
		}
		}else{
			for(int i=0;i<5;i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				}
		}
		bean.setLossRecordList(list);
		int j=1;

		for(int k=0;k<bean.getLossSNoS().size();k++){
			int value=Integer.parseInt(bean.getDeleteId());
			if(k<value){
				year.add(bean.getLossYear().get(k));
				no.add(bean.getLossNo().get(k));
				name.add(bean.getLossinsuredName().get(k));
				ins.add(bean.getLossInceptionDate().get(k));
				exp.add(bean.getLossExpiryDate().get(k));
				lossdate.add(bean.getLossDateOfLoss().get(k));
				cause.add(bean.getLossCauseOfLoss().get(k));
				claim.add(bean.getLossInsuredClaim().get(k));
				premium.add(bean.getLossPremium().get(k));
				ratio.add(bean.getLossRatio().get(k));
				leader.add(bean.getLossLeader().get(k));
				share.add(bean.getLossITIReShare().get(k));
			}
			else{
			if(StringUtils.isNotBlank(bean.getLossYear().get(j))){
				year.add(bean.getLossYear().get(j));
			}
			if(StringUtils.isNotBlank(bean.getLossNo().get(j))){
				no.add(bean.getLossNo().get(j));
						}
			if(StringUtils.isNotBlank(bean.getLossinsuredName().get(j))){
				name.add(bean.getLossinsuredName().get(j));
			}
			if(StringUtils.isNotBlank(bean.getLossInceptionDate().get(j))){
				ins.add(bean.getLossInceptionDate().get(j));
			}
			if(StringUtils.isNotBlank(bean.getLossExpiryDate().get(j))){
				exp.add(bean.getLossExpiryDate().get(j));
						}
			if(StringUtils.isNotBlank(bean.getLossDateOfLoss().get(j))){
				lossdate.add(bean.getLossDateOfLoss().get(j));
			}
			if(StringUtils.isNotBlank(bean.getLossCauseOfLoss().get(j))){
				cause.add(bean.getLossCauseOfLoss().get(j));
			}
			if(StringUtils.isNotBlank(bean.getLossInsuredClaim().get(j))){
				claim.add(bean.getLossInsuredClaim().get(j));
						}
			if(StringUtils.isNotBlank(bean.getLossPremium().get(j))){
				premium.add(bean.getLossPremium().get(j));
			}
			if(StringUtils.isNotBlank(bean.getLossRatio().get(j))){
				ratio.add(bean.getLossRatio().get(j));
			}
			if(StringUtils.isNotBlank(bean.getLossLeader().get(j))){
				leader.add(bean.getLossLeader().get(j));
						}
			if(StringUtils.isNotBlank(bean.getLossITIReShare().get(j))){
				share.add(bean.getLossITIReShare().get(j));
			}
			
			}
			j++;
		}
		
		bean.setLossYear(year);
		bean.setLossNo(no);
		bean.setLossinsuredName(name);
		bean.setLossInceptionDate(ins);
		bean.setLossExpiryDate(exp);
		bean.setLossDateOfLoss(lossdate);
		bean.setLossCauseOfLoss(cause);
		bean.setLossInsuredClaim(claim);
		bean.setLossPremium(premium);
		bean.setLossRatio(ratio);
		bean.setLossLeader(leader);
		bean.setLossITIReShare(share);
		bean.setLossRecordList(list);
		bean.setLossCount(Integer.toString(year.size()));
		//bean.setLossSNoS(sno);
		return "dropdownajax";
		
		
	}
	
	public String calculateValue(){
		List<String> year = new ArrayList<String>();
		List<String> loss1 = new ArrayList<String>();
		List<String> pre1 = new ArrayList<String>();
		List<String> ratio1 = new ArrayList<String>();
		if(bean.getLossYear() !=null && bean.getLossYear().size()>0){
		ArrayList<String> values = (ArrayList<String>) bean.getLossYear();//Your values
		Set<String> hashsetList = new HashSet<String>(values);
		for(String aSiteId: hashsetList) {
			if(StringUtils.isNotBlank(aSiteId)){
			double loss=0;
			double premium=0;
			for(int i=0;i<bean.getLossYear().size();i++){
				if(StringUtils.isNotBlank(bean.getLossYear().get(i)) && bean.getLossYear().get(i).equalsIgnoreCase(aSiteId)){
					loss += Double.parseDouble(StringUtils.isBlank(bean.getLossInsuredClaim().get(i))?"0":bean.getLossInsuredClaim().get(i).replaceAll(",", ""));
					premium += Double.parseDouble(StringUtils.isBlank(bean.getLossPremium().get(i))?"0":bean.getLossPremium().get(i).replaceAll(",", ""));
				}
			}
			loss1.add(DropDownControllor.formatter(String.valueOf(loss)));
			pre1.add(DropDownControllor.formatter(String.valueOf(premium)));
			ratio1.add(DropDownControllor.formatter(String.valueOf((loss/premium)*100)));
			year.add(aSiteId);
			}
		}
		bean.setLossSum(loss1);
		bean.setPremSum(pre1);
		bean.setRatioSum(ratio1);
		bean.setYearSum(year);
		}
		return "dropdownajax";	
	}
	private void resetCoverDeductable() {
		if(bean.getCoverLimitOC()!=null && bean.getCoverLimitOC().size()>0){
			List<String> coverdepartId = new ArrayList<String>();
			List<String> pmlPer = new ArrayList<String>();
			List<String> pmlhunPer = new ArrayList<String>();
			List<String> coversubdepartId = new ArrayList<String>();
			List<String> coverTypeId = new ArrayList<String>();
			List<String> coverLimitAmount = new ArrayList<String>();
			List<String> deductableLimitAmount = new ArrayList<String>();
			List<String> coverageDays = new ArrayList<String>();
			List<String> deductableDays = new ArrayList<String>();
			List<String> premiumRateList = new ArrayList<String>();
			List<String> gwpi = new ArrayList<String>();
			double cover=0;
			double gwp =0;
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
			List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
			bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
			bean.setCoverTypelist(new DropDownControllor().getConstantDropDown("44"));
			for(int i=0;i<bean.getCoverLimitOC().size();i++){
				coverdepartId.add(bean.getCoverdepartId().get(i));
				coversubdepartId.add(bean.getCoversubdepartId().get(i));
				coverTypeId.add(bean.getCoverTypeId().get(i));
				coverLimitAmount.add(bean.getCoverLimitOC().get(i));
				deductableLimitAmount.add(bean.getDeductableLimitOC().get(i));
				coverageDays.add(bean.getCoverageDays().get(i));
				deductableDays.add(bean.getDeductableDays().get(i));
				premiumRateList.add(bean.getPremiumRateList().get(i));
				pmlPer.add(bean.getPmlPerList().get(i));
				pmlhunPer.add(bean.getPmlHundredPer().get(i));
				gwpi.add(bean.getEgnpiAsPerOff().get(i));
				cover +=Double.parseDouble(StringUtils.isBlank(bean.getCoverLimitOC().get(i))?"0":bean.getCoverLimitOC().get(i).replaceAll(",",""));
				gwp +=Double.parseDouble(StringUtils.isBlank(bean.getCoverLimitOC().get(i))?"0":bean.getCoverLimitOC().get(i).replaceAll(",",""));
				coversubdeptList.add(dropDownController.getSubProfitCentreDropDown(bean.getCoverdepartId().get(i),branchCode,productId));
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
			}
			bean.setCoverdepartId(coverdepartId);
			bean.setCoversubdepartId(coversubdepartId);
			bean.setCoverTypeId(coverTypeId);
			bean.setCoverLimitOC(coverLimitAmount);
			bean.setDeductableLimitOC(deductableLimitAmount);
			bean.setCoverageDays(coverageDays);
			bean.setDeductableDays(deductableDays);
			bean.setPremiumRateList(premiumRateList);
			bean.setEgnpiAsPerOff(gwpi);
			bean.setCoversubDepartList(coversubdeptList);
			 bean.setCoverdeductableList(result);
			bean.setLoopcount(Integer.toString(result.size()));
			bean.setPmlPerList(pmlPer);
			bean.setTotalCoverageVal(dropDownController.formatter(Double.toString(cover)));
			bean.setTotalGWPIVal(dropDownController.formatter(Double.toString(gwp)));
			bean.setPmlHundredPer(pmlhunPer);
		}
}
	private void resetXolCoverDeductable() {
		if(bean.getXolcoverLimitOC()!=null && bean.getXolcoverLimitOC().size()>0){
			List<String> xolcoverdepartId = new ArrayList<String>();
			List<String> xolcoversubdepartId = new ArrayList<String>();
			List<String> xolcoverLimitAmount = new ArrayList<String>();
			List<String> xoldeductableLimitAmount = new ArrayList<String>();
			List<String> xolpremiumRateList = new ArrayList<String>();
			List<String> xolgwpi = new ArrayList<String>();
			double cover=0;
			double deduct =0;
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
			List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
			bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
			for(int i=0;i<bean.getXolcoverLimitOC().size();i++){
				xolcoverdepartId.add(bean.getXolcoverdepartId().get(i));
				xolcoversubdepartId.add(bean.getXolcoversubdepartId().get(i));
				xolcoverLimitAmount.add(bean.getXolcoverLimitOC().get(i));
				xoldeductableLimitAmount.add(bean.getXoldeductableLimitOC().get(i));
				xolpremiumRateList.add(bean.getXolpremiumRateList().get(i));
				xolgwpi.add(bean.getXolgwpiOC().get(i));
				cover +=Double.parseDouble(StringUtils.isBlank(bean.getXolcoverLimitOC().get(i))?"0":bean.getXolcoverLimitOC().get(i).replaceAll(",",""));
				deduct +=Double.parseDouble(StringUtils.isBlank(bean.getXoldeductableLimitOC().get(i))?"0":bean.getXoldeductableLimitOC().get(i).replaceAll(",",""));
				coversubdeptList.add(dropDownController.getSubProfitCentreDropDown(bean.getXolcoverdepartId().get(i),branchCode,productId));
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
			}
			bean.setXolcoverdepartId(xolcoverdepartId);
			bean.setXolcoversubdepartId(xolcoversubdepartId);
			bean.setXolcoverLimitOC(xolcoverLimitAmount);
			bean.setXoldeductableLimitOC(xoldeductableLimitAmount);
			bean.setXolpremiumRateList(xolpremiumRateList);
			bean.setXolgwpiOC(xolgwpi);
			bean.setCoverxolsubDepartList(coversubdeptList);
			bean.setXolCoverdeductableList(result);
			bean.setLoopcount(Integer.toString(result.size()));
			bean.setDeductibleFacXol(Double.toString(cover));
			bean.setDeductible(Double.toString(deduct));
		}
}
	public String removeCoverDeductable(){
		List<String> coverdepartId = new ArrayList<String>();
		List<String> coversubdepartId = new ArrayList<String>();
		List<String> coverTypeId = new ArrayList<String>();
		List<String> coverLimitAmount = new ArrayList<String>();
		List<String> deductableLimitAmount = new ArrayList<String>();
		List<String> coverageDays = new ArrayList<String>();
		List<String> deductableDays = new ArrayList<String>();
		List<String> premiumRateList = new ArrayList<String>();
		List<String> gwpi = new ArrayList<String>();
		List<String> coverRemark = new ArrayList<String>();
		List<String> pmlPer = new ArrayList<String>();
		List<String> pmlHunPer = new ArrayList<String>();
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
		double cover=0;
		double gwp =0;
		bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
		bean.setSubProfit_center(new DropDownControllor().getSubProfitCentreDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString()));
		bean.setCoverTypelist(new DropDownControllor().getConstantDropDown("44"));
			bean.getCoverSNo().remove(bean.getDeleteId());
			for(int i=0;i<bean.getCoverSNo().size();i++){
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
				
				}
			 bean.setCoverdeductableList(result);
			 bean.setLoopcount(Integer.toString(result.size()));
			 //bean.setEgnpiOffer("100");
				int j=1;
				
				for(int k=0;k<bean.getCoverSNo().size();k++){
					int value=Integer.parseInt(bean.getDeleteId());
					if(k<value){
						coverdepartId.add(bean.getCoverdepartId().get(k));
						coversubdepartId.add(bean.getCoversubdepartId().get(k));
						coverTypeId.add(bean.getCoverTypeId().get(k));
						coverLimitAmount.add(bean.getCoverLimitOC().get(k));
						deductableLimitAmount.add(bean.getDeductableLimitOC().get(k));
						coverageDays.add(bean.getCoverageDays().get(k));
						deductableDays.add(bean.getDeductableDays().get(k));
						premiumRateList.add(bean.getPremiumRateList().get(k));
						gwpi.add(bean.getEgnpiAsPerOff().get(k));
						coverRemark.add(bean.getCoverRemark().get(k));
						pmlHunPer.add(StringUtils.isBlank(bean.getPmlHundredPer().get(k))?"":bean.getPmlHundredPer().get(k));
						cover +=Double.parseDouble(StringUtils.isBlank(bean.getCoverLimitOC().get(k))?"0":bean.getCoverLimitOC().get(k).replaceAll(",", ""));
						gwp +=Double.parseDouble(StringUtils.isBlank(bean.getCoverLimitOC().get(k))?"0":bean.getEgnpiAsPerOff().get(k).replaceAll(",", ""));
						coversubdeptList.add(dropDownController.getSubProfitCentreDropDown(bean.getCoverdepartId().get(k),branchCode,productId));
						pmlPer.add(StringUtils.isBlank(bean.getPmlPerList().get(k))?"":bean.getPmlPerList().get(k));
						
					}
					else{
					if(StringUtils.isNotBlank(bean.getCoverdepartId().get(j))){
						coverdepartId.add(bean.getCoverdepartId().get(j));
					}
					if(StringUtils.isNotBlank(bean.getCoversubdepartId().get(j))){
						coversubdepartId.add(bean.getCoversubdepartId().get(j));
					}
					if(StringUtils.isNotBlank(bean.getCoverTypeId().get(j))){
						coverTypeId.add(bean.getCoverTypeId().get(j));
					}
					if(StringUtils.isNotBlank(bean.getCoversubdepartId().get(j))){
						coversubdeptList.add(dropDownController.getSubProfitCentreDropDown(bean.getCoverdepartId().get(k),branchCode,productId));
					}
					if(StringUtils.isNotBlank(bean.getCoverLimitOC().get(j))){
						coverLimitAmount.add(bean.getCoverLimitOC().get(j));
						cover +=Double.parseDouble(bean.getCoverLimitOC().get(j));
						
					}
					if(StringUtils.isNotBlank(bean.getDeductableLimitOC().get(j))){
						deductableLimitAmount.add(bean.getDeductableLimitOC().get(j));
					}
					if(StringUtils.isNotBlank(bean.getCoverageDays().get(j))){
						coverageDays.add(bean.getCoverageDays().get(j));
					}
					if(StringUtils.isNotBlank(bean.getDeductableDays().get(j))){
						deductableDays.add(bean.getDeductableDays().get(j));
					}
					if(StringUtils.isNotBlank(bean.getPremiumRateList().get(j))){
						premiumRateList.add(bean.getPremiumRateList().get(j));
					}
					if(StringUtils.isNotBlank(bean.getEgnpiAsPerOff().get(j))){
						gwpi.add(bean.getEgnpiAsPerOff().get(j));
						gwp +=Double.parseDouble(bean.getEgnpiAsPerOff().get(j));
					}
					if(StringUtils.isNotBlank(bean.getCoverRemark().get(j))){
						coverRemark.add(bean.getCoverRemark().get(j));
					}
					pmlPer.add(StringUtils.isBlank(bean.getPmlPerList().get(j))?"":bean.getPmlPerList().get(j));
					pmlHunPer.add(StringUtils.isBlank(bean.getPmlHundredPer().get(j))?"":bean.getPmlHundredPer().get(j));
					}
					j++;
				}
				bean.setCoverdepartId(coverdepartId);
				bean.setCoversubdepartId(coversubdepartId);
				bean.setCoverTypeId(coverTypeId);
				bean.setCoverLimitOC(coverLimitAmount);
				bean.setDeductableLimitOC(deductableLimitAmount);
				bean.setCoverageDays(coverageDays);
				bean.setDeductableDays(deductableDays);
				bean.setPremiumRateList(premiumRateList);
				bean.setEgnpiAsPerOff(gwpi);
				bean.setCoverRemark(coverRemark);
				bean.setCoversubDepartList(coversubdeptList);
				bean.setPmlPerList(pmlPer);
				bean.setTotalCoverageVal(dropDownController.formatter(Double.toString(cover)));
				bean.setTotalGWPIVal(dropDownController.formatter(Double.toString(gwp)));
				
			return "dropdownajax";
		}
	public String removeXolCoverDeductable(){
		List<String> xolcoverdepartId = new ArrayList<String>();
		List<String> xolcoversubdepartId = new ArrayList<String>();
		List<String> xolcoverLimitAmount = new ArrayList<String>();
		List<String> xoldeductableLimitAmount = new ArrayList<String>();
		List<String> xolpremiumRateList = new ArrayList<String>();
		List<String> xolgwpi = new ArrayList<String>();
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
		double cover=0;
		double gwp =0;
		bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
		bean.setSubProfit_center(new DropDownControllor().getSubProfitCentreDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString()));
			bean.getXolcoverSNo().remove(bean.getDeleteId());
			for(int i=0;i<bean.getXolcoverSNo().size();i++){
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
				
				}
			 bean.setXolCoverdeductableList(result);
			 bean.setXolLoopcount(Integer.toString(result.size()));
			 //bean.setEgnpiOffer("100");
				int j=1;
				
				for(int k=0;k<bean.getXolcoverSNo().size();k++){
					int value=Integer.parseInt(bean.getDeleteId());
					if(k<value){
						xolcoverdepartId.add(bean.getXolcoverdepartId().get(k));
						xolcoversubdepartId.add(bean.getXolcoversubdepartId().get(k));
						xolcoverLimitAmount.add(bean.getXolcoverLimitOC().get(k));
						xoldeductableLimitAmount.add(bean.getXoldeductableLimitOC().get(k));
						xolpremiumRateList.add(bean.getXolpremiumRateList().get(k));
						xolgwpi.add(bean.getXolgwpiOC().get(k));
						cover +=Double.parseDouble(StringUtils.isBlank(bean.getXolcoverLimitOC().get(k))?"0":bean.getXolcoverLimitOC().get(k).replaceAll(",", ""));
						gwp +=Double.parseDouble(StringUtils.isBlank(bean.getXoldeductableLimitOC().get(k))?"0":bean.getXoldeductableLimitOC().get(k).replaceAll(",", ""));
						coversubdeptList.add(dropDownController.getSubProfitCentreDropDown(bean.getXolcoverdepartId().get(k),branchCode,productId));
						
					}
					else{
					if(StringUtils.isNotBlank(bean.getXolcoverdepartId().get(j))){
						xolcoverdepartId.add(bean.getXolcoverdepartId().get(j));
					}
					if(StringUtils.isNotBlank(bean.getXolcoversubdepartId().get(j))){
						xolcoversubdepartId.add(bean.getXolcoversubdepartId().get(j));
					}
					if(StringUtils.isNotBlank(bean.getXolcoversubdepartId().get(j))){
						coversubdeptList.add(dropDownController.getSubProfitCentreDropDown(bean.getXolcoverdepartId().get(k),branchCode,productId));
					}
					if(StringUtils.isNotBlank(bean.getXolcoverLimitOC().get(j))){
						xolcoverLimitAmount.add(bean.getXolcoverLimitOC().get(j));
						cover +=Double.parseDouble(bean.getXolcoverLimitOC().get(j));
						
					}
					if(StringUtils.isNotBlank(bean.getXoldeductableLimitOC().get(j))){
						xoldeductableLimitAmount.add(bean.getXoldeductableLimitOC().get(j));
						gwp +=Double.parseDouble(bean.getXoldeductableLimitOC().get(j));
					}
					
					if(StringUtils.isNotBlank(bean.getXolpremiumRateList().get(j))){
						xolpremiumRateList.add(bean.getXolpremiumRateList().get(j));
					}
					if(StringUtils.isNotBlank(bean.getXolgwpiOC().get(j))){
						xolgwpi.add(bean.getXolgwpiOC().get(j));
					}
					}
					j++;
				}
				bean.setXolcoverdepartId(xolcoverdepartId);
				bean.setXolcoversubdepartId(xolcoversubdepartId);
				bean.setXolcoverLimitOC(xolcoverLimitAmount);
				bean.setXoldeductableLimitOC(xoldeductableLimitAmount);
				bean.setXolpremiumRateList(xolpremiumRateList);
				bean.setXolgwpiOC(xolgwpi);
				bean.setCoverxolsubDepartList(coversubdeptList);
				bean.setDeductibleFacXol(Double.toString(cover));
				bean.setDeductible(Double.toString(gwp));
			return "dropdownajax";
		}
	public String ajaxValue(){	
		if("coversubclass".equalsIgnoreCase(bean.getDropDown()) || "xolcoversubclass".equalsIgnoreCase(bean.getDropDown()) ){
		bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getCoverdepart(),branchCode,productId));
		}
		else if("yearId".equalsIgnoreCase(bean.getDropDown())){
			bean.setYearList(getYearList());
		}else if("yearIdto".equalsIgnoreCase(bean.getDropDown())){
			bean.setYearToList(getYearToList());
		}
		return "dropdownajax";
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
