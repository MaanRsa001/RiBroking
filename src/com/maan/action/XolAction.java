package com.maan.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;

import com.maan.bean.FaculitivesBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.CommonCalculation;
import com.maan.service.RdsCalculation;
import com.maan.service.RiskDetailsService;
import com.maan.service.XolService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class XolAction extends ActionSupport implements ModelDriven<RiskDetailsBean> {
	private static final long serialVersionUID = 1L;

	RiskDetailsBean bean=new RiskDetailsBean();
	FaculitivesBean bean1=new FaculitivesBean();
	XolService service=new XolService();
	RiskDetailsService rservice = new RiskDetailsService();
	DropDownControllor dropDownController=new DropDownControllor();
	Logger logger = LogUtil.getLogger(this.getClass());
	Map<String,Object> session = ActionContext.getContext().getSession();
	final String pid = session.get("mfrid")==null?"":(String) session.get("mfrid");
	private String userId=session.get("UserId")==null?"":(String) session.get("UserId");
	private String branchCode=session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	private String countryId=session.get("countryId")==null?"":session.get("countryId").toString();
	private String processId=session.get("processId")==null?"":session.get("processId").toString();
	private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
	private String attachedUW = session.get("ATTACHED_UW") ==null ?"":session.get("ATTACHED_UW").toString();
	CommonCalculation calcu = new CommonCalculation();
	private static final String DROPDOWNAJAX="dropdownajax";
	private InputStream inputStream;
	private String fields;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public RiskDetailsBean getModel() {
		return bean;
	}
	public List<Map<String,Object>> getBonusList(){
    	return dropDownController.getBonusList();
    }
	public String getDisableStatus(){
    	return dropDownController.getDisableStatus(bean.getContNo(),bean.getLayerNo());
    }
	public String getDisableStatus1(){
    	return dropDownController.getDisableStatus1(bean.getContNo(),bean.getLayerNo());
    }
	public List<Map<String,Object>> getReinstatementOptionList(){
		return dropDownController.getReinstatementOptionList(bean);
	}
	public List<Map<String,Object>> getReinstatementTypeList(){
		return dropDownController.getReinstatementTypeList(bean);
	}
	
	public List<Map<String,Object>> getUnderwriterList(){
		return dropDownController.getPersonalInfoDropDown(branchCode,"L",pid);
	}
	public List<Map<String,Object>> getEndosTypelist(){
    	return dropDownController.getConstantDropDownET("37",bean.getContNo());
    }
	public List<Map<String,Object>> getDocType(){
    	return dropDownController.getDocType(branchCode,pid,"RDS");
    }
	public List<Map<String,Object>> getBouquetExistingList(){
		return dropDownController.getBouquetExistingList(branchCode,bean.getBouquetNo(),bean.getBouquetModeYN());
	}
	public String UnderwritingLimit(){
		//bean.setMaxLimit_Product(dropDownController.getUnderWriterLimmit(bean.getUnderwriter(),(String)session.get("processId"), pid, (String)session.get("DepartmentId")));
		bean.setMaxLimit_Product(dropDownController.getUnderWriterLimmit(bean.getUnderwriter(),(String)session.get("processId"), pid, "0"));
	return "dropdownajax";
}
	public List<Map<String,Object>> getTerritoryRegionList(){
    	return dropDownController.getTerritoryRegionList(branchCode);
    }
	@JSON(serialize=false)
	private List<Map<String,Object>> getYearList(){
		List<Map<String,Object>> yearsList=new ArrayList<Map<String,Object>>();
		Validation val = new Validation();
		if(StringUtils.isNotBlank(bean.getIncepDate()) && !val.checkDate(bean.getIncepDate()).equalsIgnoreCase("INVALID")){
		yearsList = dropDownController.getYearListValue(bean.getIncepDate());
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
		if(StringUtils.isNotBlank(bean.getIncepDate()) && !val.checkDate(bean.getIncepDate()).equalsIgnoreCase("INVALID")){
			if(StringUtils.isNotBlank(bean.getExpDate()) && !val.checkDate(bean.getExpDate()).equalsIgnoreCase("INVALID")){
				yearsList = dropDownController.getYearToListValue(bean.getIncepDate(),bean.getExpDate());
			}
		}
		
		return yearsList;
	}
	public String ajaxValue(){	
		if("country".equalsIgnoreCase(bean.getDropDown())){
			bean.setUnderwriterCountryList(dropDownController.getUnderwriterCountryList(bean,branchCode));
		}//else if("coversubclass".equalsIgnoreCase(bean.getDropDown())){
		//subclass//bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
		//}
		else{
		bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
		}return DROPDOWNAJAX;
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
			bean.setUsCurrencyRate(dropDownController.GetExchangeRate(bean.getOrginalCurrency(),date,countryId,bean.getBranchCode()));
			bean.setExchRate(dropDownController.GetExchangeRate(bean.getOrginalCurrency(),date,countryId,bean.getBranchCode()));
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return DROPDOWNAJAX;
	}
	public  String Init() {
		bean.setBranchCode(branchCode);
		bean.setShortname(service.getShortname(bean));
		String forward = "xol1";
		bean.setMenuStatus("N");
		if(StringUtils.isNotBlank(bean.getProposal_no())){
		bean.setProposal_no("");
		}
		ShowDropDown("");
		//bean.setMaxLimit_Product(dropDownController.getUWLimmit((String)session.get("UserId"),(String)session.get("processId"),pid, "0"));
		if (pid.equalsIgnoreCase("5")){
			forward="retroxol1";
		}
		//List<List<Map<String,Object>>> coverList=new ArrayList<List<Map<String,Object>>>(5);
		 Map<String,Object> doubleMap = new HashMap<String,Object>();
		 //subclass//List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
		 List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
		 doubleMap.put("one",new Double(1.0));
		 creList.add(doubleMap);
		 bean.setCoverList(creList);
		 bean.setCount(Integer.toString(creList.size()));
		 List<String> inslist=new ArrayList<String>();
		 inslist.add("");
		 bean.setInstalList(inslist);
		 bean.setOurAssessment("100");
		try {
			bean.setSubProfitList(new ArrayList<Map<String,Object>>());
			//subclass//coversubdeptList.add(dropDownController.getSubProfitCentreDropDown("",branchCode,pid));
			//subclass//bean.setCoversubDepartList(coversubdeptList);
			bean.setAmendId("0");
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
			 bean.setBaseLayer("");
			 //bean.setNo_Insurer("1");
			 bean.setNoRetroCess("1");
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	public String FirstPageSaveMethod(){
		String forward = "xol1";
		try {
			bean.setProduct_id(pid);
			bean.setLoginId(userId);
			bean.setProcessId(session.get("processId").toString());
			bean.setBranchCode(branchCode);
			bean.setShortname(service.getShortname(bean));
			if(StringUtils.isNotBlank(bean.getDepartId())){
				session.put("DepartmentId",bean.getDepartId());
			}
			if(StringUtils.isNotBlank(bean.getLayerLayerNo())) {
				bean.setLayerNo(bean.getLayerLayerNo());
			}
			if (pid.equalsIgnoreCase("5")){
				forward="retroxol1";
			}
			bean.setRskCountCheck("Yes");
			validateoffer();
			/*if("A".equalsIgnoreCase(bean.getProStatus())|| "5".equalsIgnoreCase(pid)){
				validatenext();
			}
			else{
				validatesave();
			}*/
			if (!hasActionErrors()) {
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				boolean  SaveFlag=false;
				if(StringUtils.isBlank(bean.getProposal_no())) {
				  SaveFlag = service.insertProportionalTreaty(bean,pid, false, false);
				}else {
				  SaveFlag = service.updateProportionalTreaty(bean,pid);
				}
				if (SaveFlag) {
					service.saveSecondPage(bean, bean.getProduct_id());
					bean.setStatus(bean.getContractGendration());
					if(StringUtils.isBlank(bean.getBaseLayer()) && !hasActionErrors()){
						dropDownController.updateSubClass(bean.getProposal_no(),"Save");
					}
					if(StringUtils.isNotBlank(bean.getReferenceNo())) {
						dropDownController.updateProposalno(bean);
					}
					if(StringUtils.isNotBlank(bean.getContNo())) {
						bean.setBackmode("Con");
					}
					else if ("P".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("Pro");
					}
					else if ("R".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("Reje");
					}
					else if ("A".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("Pro");
					}
					else if ("0".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("Pro");
					}
					else if ("N".equalsIgnoreCase(bean.getProStatus())) {
						bean.setBackmode("NTU");
					}
					if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
						dropDownController.updateRenewalEditMode(bean.getProposal_no(),"N","");
					}
					dropDownController.updateEditMode(bean.getProposal_no(),"N","");
					if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
						dropDownController.updateEditMode(bean.getBaseLayer(),"N","");
						dropDownController.updateSubEditMode(bean.getBaseLayer(),"N","");
					}
					else{
						dropDownController.updateSubEditMode(bean.getProposal_no(),"N","");
					}
					
					if("S".equals(bean.getEditMode())) {
						forward="SucusssFac";
					}else {
						ShowDropDown("edit");
						EditMode();
						forward="xol1";
					}
				} else {
					ShowDropDown("");
					resetCoverLimit();
					resetRemarks();
					resetIntallment();
					if ("3".equalsIgnoreCase(pid)||"5".equalsIgnoreCase(pid)) {
						addActionError(getText("error.layerNo.Exists"));
					}
				}
			} else {
				if(StringUtils.isNotBlank(bean.getProposal_no())) {
					bean.setFlag("layer");
					bean.setLayerMode("layer");
				}else if(StringUtils.isNotBlank(bean.getProposalNo())) {
					bean.setFlag("copy");
					bean.setLayerMode("layer");
				}
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if(bean.getDepartId().equalsIgnoreCase("")||bean.getDepartId()==null){
					bean.setSubProfitList(new ArrayList<Map<String,Object>>());
				}else{
					bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
				}
				ShowDropDown("");
				resetCoverLimit();
				resetRemarks();
				resetIntallment();
			}
			if(StringUtils.isNotBlank(bean.getProposalNo()) /*&&("layer".equals(bean.getLayerMode()) || StringUtils.isNotBlank(bean.getLayerLayerNo()))*/) {
				bean.setProposal_no(bean.getProposalNo());
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	private void validateoffer() {
		try {
			final Validation val = new Validation();
			Map<String, Object> map = null;
			List<Map<String, Object>> list = service.getValidation(bean);
			if (list != null && list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			}
			if(StringUtils.isBlank(bean.getBouquetModeYN())) {
				addActionError(getText("error.bouquetModeYn.required"));
			}
			
			if (val.isSelect(bean.getCedingCo()).equalsIgnoreCase("")) {
				addActionError(getText("error.cedingCo.required"));
			}
			if (val.isNull(bean.getIncepDate()).equalsIgnoreCase("")) {
				addActionError(getText("error.incepDate.required"));
			} else if (val.checkDate(bean.getIncepDate()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.incepDate.check"));
			} else if (StringUtils.isNotBlank((bean.getRenewal_contract_no()))&& !"0".equals(bean.getRenewal_contract_no())&& map != null) {
				if ("Invalid".equalsIgnoreCase(val.getDateValidate((String) map.get("EXPIRY_DATE"), bean.getIncepDate()))) {
					addActionError(getText("errors.InceptionDate.invalid"));
				}else {
					bean.setRenewalFlag("NEWCONTNO");
				}
			}
			if (val.isNull(bean.getExpDate()).equalsIgnoreCase("")) {
				addActionError(getText("error.expDate.required"));
			} else if (val.checkDate(bean.getExpDate()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("errors.ExpiryDate.Error"));
			}
			if (!bean.getIncepDate().equalsIgnoreCase("")&& !bean.getExpDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.expDate.check"));
				}
			}
			if (val.isSelect(bean.getUwYear()).equalsIgnoreCase("")) {
				addActionError(getText("error.uwYear.required"));
			}if (val.isSelect(bean.getUwYearTo()).equalsIgnoreCase("")) {
				addActionError(getText("error.uwYearTo.required"));
			}
			if (StringUtils.isNotBlank(bean.getIncepDate())&& StringUtils.isNotBlank(bean.getExpDate())) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check1"));
				}
			}
			if (val.isNull(bean.getLayerNo()).equalsIgnoreCase("")) {
				addActionError(getText("error.layerNo.required"));
			} else if (val.isValidNo(bean.getLayerNo()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.layerNo.error"));
			}
			if (!val.isNull(bean.getLayerNo()).equalsIgnoreCase("")) {
				if (service.getLayerDuplicationCheck(bean)) {
					logger.info("// PMD Changes");
					addActionError(getText("error.layer.duplicate"));
				}
			}
			if(StringUtils.isBlank(bean.getRiskdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getBrokerdetYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getPremiumdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getInstallYN())) {
					addActionError(getText("error.InstallYN.required"));
			}
			if(StringUtils.isBlank(bean.getAcqdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getReinstdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}
	public String check() {
		String forward="";
		if("5".equalsIgnoreCase(pid)){
			forward="retroxol1";
		}else{
			forward="xol1";
		}
		String Status="";
		bean.setLay(bean.getLay1());
		if(StringUtils.isNotBlank(bean.getContractno1())) {
			bean.setContractno(bean.getContractno1());
		}
		if(StringUtils.isNotBlank(bean.getDepartId())){
			session.put("DepartmentId",bean.getDepartId());
		}
		bean.setBranchCode(branchCode);
		bean.setProduct_id(pid);
		bean.setLoginId(userId);
		bean.setCountryID(bean.getCountryID());
		bean.setProcessId(processId);
		bean.setShortname(service.getShortname(bean));
		bean1.setBranchCode(branchCode);
		new DropDownControllor().getOpenPeriod(bean1);
		if("A".equalsIgnoreCase(bean.getProStatus())|| "5".equalsIgnoreCase(pid)){
			validatenext();
		}
		else{
			validatesave();
		}
		try {
			if (!hasActionErrors()) {
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				session.put("Exchangerate", bean.getExchRate());
				RdsCalculation.SecondPageCaculation(bean,pid);
				
				bean.setLoginId(userId);
				if("5".equalsIgnoreCase(pid)){
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
				if (SaveFlag) {
					service.showSecondPageData1(bean.getProposal_no(), bean, pid);
					bean.setAnualAggregateLiability(service.getSumOfCover(bean,pid));
					if((!bean.getLayerProposalNo().equals(""))){
						service.showLayerBrokerage(bean.getLayerProposalNo(),bean);
					}
					if("3".equalsIgnoreCase(pid)){
						service.showRetroContracts(bean,pid,false);
					}
					bean.setShare_Profit_Commission("2");
					ShowDropDown("");
					resetCoverLimit();
					if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
						if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
							Status = "SRF";
						}else{
							Status = "BRF";
						}
						dropDownController.updateRenewalEditMode(bean.getProposal_no(),Status,bean.getProposal_no());
					}
					dropDownController.updateEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BRF":"BEF",bean.getProposal_no());
					if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
						dropDownController.updateEditMode(bean.getBaseLayer(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SRF":"SEF",bean.getProposal_no());
						dropDownController.updateSubEditMode(bean.getBaseLayer(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SRF":"SEF",bean.getProposal_no());
					}
					else{
						dropDownController.updateSubEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BRF":"BEF",bean.getProposal_no());
					}
					if("5".equalsIgnoreCase(pid)){
						forward="retroxol2";
					}else{
						forward="xol2";
					}
				} else {
					ShowDropDown("");
					resetCoverLimit();
					addActionError(getText("error.insererror.error"));
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
					bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
				}
				ShowDropDown("");
				resetCoverLimit();
				resetRemarks();
			}	
			if (StringUtils.isNotEmpty(bean.getLayerProposalNo())) {
				RdsCalculation.SecondPageCaculation(bean,pid);
			}
		} 
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}
	
	public String getRetroContractDetails(){
		try {
			bean.setProduct_id("4");
			bean.setRetroType("TR");
			bean.setBranchCode(branchCode);
			bean.setIncepDate(bean.getInceptionDate());
			service.getRetroContractDetails(bean);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return DROPDOWNAJAX;
	}

	public String ViewMethod(){
		String forward="";
		String sumInsured = "";
		String deductible = "";
		String coverPer = "";
		String dedPer = "";
		try {
			if (StringUtils.isBlank(bean.getProposal_no())) {
				bean.setActionMessage((getText("error.proposalNoView.required")));
				bean.setFlag("true");
				forward="redirectAction";
			} else {
				final boolean CheckAvilable = service.checkAvialability(bean, pid);
				if (CheckAvilable) {
					bean.setProduct_id(pid);
					bean.setBranchCode(branchCode);
					bean.setShortname(service.getShortname(bean));
					final boolean ViewFlag = service.viewRiskDetails(bean, pid);
					//String country = dropDownController.getUnderwriterCountryName(bean,branchCode);
					//bean.setLeader_Underwriter_country(country);
					
						if(StringUtils.isNotBlank(bean.getM_d_InstalmentNumber()) && Integer.parseInt(bean.getM_d_InstalmentNumber())>0){
						List<String> instalList=new ArrayList<String>();
						for(int i=0;i<Integer.parseInt(bean.getM_d_InstalmentNumber());i++){
							instalList.add(String.valueOf(i));
						}
						bean.setInstalList(instalList);
					}
					if (ViewFlag) {
						if("5".equalsIgnoreCase(pid)){
						forward="viewRetroXol";
						}
						else{
						forward="viewXol";
						}
					} else {
						bean.setActionMessage((getText("error.proposalNoView.required")));
						bean.setManufactureID(pid);
						forward="redirectAction1";
					}
				} else {
					bean.setActionMessage((getText("error.proposalNoView.required")));
					bean.setManufactureID(pid);
					forward="redirectAction1";
				}
			}
			if(pid.equalsIgnoreCase("5")){
				service.showRetroCess1(bean,3);
				if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
					List<String> retroCessList1=new ArrayList<String>();
					for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
						retroCessList1.add(String.valueOf(i));
					}
					bean.setRetroCessList(retroCessList1);
				}
				ShowDropDown("");
			}
			bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return forward;
	}

	public String EditMode(){
		String forward="";
		String editMode ="N";
		String Status="";
		try {
			if( !"edit".equalsIgnoreCase(bean.getMultiuserMode())){
			editMode = dropDownController.EditModeStatus(bean.getProposal_no(),bean.getLayerNo());
		}
		if(!"N".equalsIgnoreCase(editMode)){
			forward ="portfoliList";	
			bean.setMultiuserError("error");
		}
		else{
		bean.setBranchCode(branchCode);
		bean.setShortname(service.getShortname(bean));
		bean.setMenuStatus("N");
		
		if("5".equalsIgnoreCase(pid)){
			forward="retroxol1";
		}else{
			forward="xol1";
		}
		
		bean.setProposalNo1(bean.getProposal_no());
		//bean.setBranchCode(branchCode);
		
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
				dropDownController.riskDetailsEndorsement(bean.getProposal_no(),bean.getEndorsementStatus());
				
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
			else if("Renewal".equalsIgnoreCase(bean.getReMode())) {
				bean.setRenewalProposalNo(bean.getProposal_no());
				bean.setProposal_no(new DropDownControllor().getRenewalCopyQuote("Renewal","3".equalsIgnoreCase(pid)?pid:"6", branchCode,bean.getProposal_no() ));
				bean.setRenewalMode("RenewalMode");
				bean.setMode("");
				bean.setProposalReference("Renewal");
			}
			 List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
			bean.setProduct_id(pid);
			service.riskEditMode(bean, false);
			if("Renewal".equalsIgnoreCase(bean.getReMode())){
				dropDownController.updateSubClass(bean.getProposal_no(),"Renewal");
				service.riskEditMode(bean, false);
			}
			bean.setYearList(getYearList());
			/*for(int i=0;i<bean.getCoverdepartIdS().size();i++){
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				doubleMap.put("one",new Double(1.0));
				creList.add(doubleMap);
			}
			bean.setCoverList(creList);*/
			bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
			RdsCalculation.SecondPageCaculation(bean,pid);
			bean.setAnualAggregateLiability(service.getSumOfCover(bean,pid));
			if(StringUtils.isNotBlank(bean.getMode())&& "Renewal".equalsIgnoreCase(bean.getMode())){
				bean.setContNo("");
				//bean.setFlag("renewal");
				bean.setFlagTest("renewal");
			}
			if(StringUtils.isNotBlank(bean.getEndtMode())&& "endorsment".equalsIgnoreCase(bean.getEndtMode())){
				bean.setEndorsmenttype("");
			}
			bean.setEdit(bean.getMode());
			service.BaseLayerStatus(bean,pid);
			if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
				if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
					Status = "SR";
				}else{
					Status = "BR";
				}
				dropDownController.updateRenewalEditMode(bean.getProposal_no(),Status,bean.getProposal_no());
			}
			dropDownController.updateEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BR":"BE",bean.getProposal_no());
			if(StringUtils.isNotBlank(bean.getBouquetNo()))
			dropDownController.updateBqEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BR":"BE",bean.getBouquetNo());
			
			if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
				String proposal = dropDownController.getBaseProposal(bean.getProposal_no());
				dropDownController.updateEditMode(proposal,"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SR":"SE",bean.getProposal_no());
				dropDownController.updateSubEditMode(proposal,"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SR":"SE",bean.getProposal_no());
			}
			else{
				dropDownController.updateSubEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BR":"BE",bean.getProposal_no());
			}
		}
		ShowDropDown("edit");
		if(StringUtils.isNotBlank(bean.getContNo())){
			bean.setProdisableStatus("Y");
		}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	public String RiskSecondPage(){
		String forward="xol2";

		try {
			/*if(StringUtils.isNotBlank(session.get("EditContractNo")==null?"":session.get("EditContractNo").toString())){
				bean.setContNo(session.get("EditContractNo").toString());
			}*/
			final String ProductId = (String) session.get("Product");
			bean.setBranchCode(branchCode);
			bean.setLoginId(userId);
			bean1.setBranchCode(branchCode);
			bean.setProduct_id(pid);
			if(StringUtils.isNotBlank(bean.getContNo()))
			bean.setPreviousendoDate(new DropDownControllor().getpreamendDate(bean.getContNo()));
			new DropDownControllor().getOpenPeriod(bean1);
			if("A".equalsIgnoreCase(bean.getProStatus()) || "5".equalsIgnoreCase(pid)){
				validateSecondPage();
				}
				else{
					validateSecodnPageSaveMethod();	
				}
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && "Y".equalsIgnoreCase(bean.getDocStatus())) {
			validateUploadDocument();
			}
			if (!hasActionErrors()) {
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				bean.setExchRate((session.get("Exchangerate").toString()));
				service.saveRiskDeatilsSecondForm(bean,ProductId);
				bean.setContractGendration(bean.getContractGendration());
				if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && "Y".equalsIgnoreCase(bean.getDocStatus())) {
				bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
				String result=service.doUpload(bean,branchCode,userId,bean.getUpload(),bean.getUploadFileName());
				}
				if (StringUtils.isNotBlank(bean.getContNo())) {
					bean.setBackmode("Con");
				}
				else if ("P".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Pro");
				}
				else if ("N".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("NTU");
				}
				else if ("R".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Reje");
				}
				bean.setStatus(bean.getContractGendration());
				bean.setMode("");
				if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
					dropDownController.updateRenewalEditMode(bean.getProposal_no(),"N","");
				}
				dropDownController.updateEditMode(bean.getProposal_no(),"N","");
				if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
					dropDownController.updateEditMode(bean.getBaseLayer(),"N","");
					dropDownController.updateSubEditMode(bean.getBaseLayer(),"N","");
				}
				else{
					dropDownController.updateSubEditMode(bean.getProposal_no(),"N","");
				}
				forward="SucusssFac";
			} else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				/*if(StringUtils.isNotBlank(bean.getEndorsementDate())){
				bean.setEndorsementDate(dateFormat(bean.getEndorsementDate()));
				}*/
				if("3".equalsIgnoreCase(pid)){
					resetRetroList();
				}else{
					if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
						List<String> retroCessList1=new ArrayList<String>();
						for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
							retroCessList1.add(String.valueOf(i));
						}
						bean.setRetroCessList(retroCessList1);
					}
				}
				ShowDropDown("");
				if(StringUtils.isNotBlank(bean.getM_d_InstalmentNumber()) && Integer.parseInt(bean.getM_d_InstalmentNumber())>0){
					List<String> instalList=new ArrayList<String>();
					for(int i=0;i<Integer.parseInt(bean.getM_d_InstalmentNumber());i++){
						instalList.add(String.valueOf(i));
					}
					bean.setInstalList(instalList);
				}
				service.showSecondPageData(bean.getProposal_no(), bean, ProductId);
				if("5".equalsIgnoreCase(ProductId)){
					service.showRetroCess(bean,2);
				}
				if("3".equalsIgnoreCase(ProductId)){
					bean.setNo_Insurer((bean.getNo_Insurer()));
				}

				if("5".equalsIgnoreCase(pid)){
					forward="retroxol2";
				}else{
					forward="xol2";
				}
				if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0){
				List<Integer> docList=new ArrayList<Integer>();
				for(int i=0;i<bean.getDocId().size();i++){
					docList.add(i);
				}
				bean.setDocuList(docList);
				}
				resetRemarks();
			}
			if(StringUtils.isNotBlank(bean.getLayerProposalNo())){
				service.showLayerBrokerage(bean.getLayerProposalNo(),bean);
			}
			if(StringUtils.isBlank(bean.getBaseLayer()) && !hasActionErrors()){
				dropDownController.updateSubClass(bean.getProposal_no(),"Endt");
			}
			
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	private void resetRetroList() {
		if(StringUtils.isNotBlank(bean.getNo_Insurer()) && Integer.parseInt(bean.getNo_Insurer())>0){
			List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
			List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
			for(int i=0;i<Integer.parseInt(bean.getNo_Insurer());i++){
				RiskDetailsBean tempBean=new RiskDetailsBean();
				tempBean.setProduct_id("4");
				tempBean.setRetroType("TR");
				tempBean.setUwYear(bean.getRetroYear().get(i));
				tempBean.setIncepDate(bean.getIncepDate());
				tempBean.setBranchCode(bean.getBranchCode());
			/*	if(0==i){
					//retroDupList.add(service.getRetroContractDetailsList(tempBean, 3));
					retroFinalList.add(new ArrayList<Map<String,Object>>());
				}else{*/
				retroFinalList.add(service.getRetroContractDetailsList(tempBean,2,bean.getRetroYear().get(i)));
				//}
			}
			bean.setRetroDupList(retroDupList);
			bean.setRetroFinalList(retroFinalList);
			bean.setProduct_id("4");
			bean.setRetroType("TR");
			bean.setRetroUwyear(service.getRetroContractDetailsList(bean,1,""));
			bean.setProduct_id(pid);
		}
		
	}
	public String AmednIdInsert(){
		String forward = "";
		if("5".equalsIgnoreCase(pid)){
			forward="retroxol1";
			bean.setCedingId(bean.getCedingCo());
			bean.setBrokerId(bean.getBroker()); 
		}else{
			forward="xol1";
		}
		try { 
			bean.setAmend_Id_Mode("true");
			bean.setLoginId(userId);
			bean.setProcessId(processId);
			bean.setContNo(bean.getContractNo());	
			if(StringUtils.isNotBlank(bean.getContractNo())){
				bean.setContNo(bean.getContractNo());
			}
			if(StringUtils.isBlank(bean.getContractNo())){
				//bean.setContNo(session.get("EditContractNo").toString());
			}
			bean.setBranchCode(branchCode);
			bean1.setBranchCode(branchCode);
			if(StringUtils.isNotBlank(bean.getDepartId())){
				session.put("DepartmentId",bean.getDepartId());
			}
			new DropDownControllor().getOpenPeriod(bean1);
			if("A".equalsIgnoreCase(bean.getProStatus())|| "5".equalsIgnoreCase(pid)){
				validatenext();
			}
			else{
				validatesave();
			}
			if (!hasActionErrors()) {
				bean.setAmend_Id_Mode("true");
				RdsCalculation.SecondPageCaculation(bean,pid);
				bean.setAnualAggregateLiability(service.getSumOfCover(bean,pid));
				bean.setAmend_Id_Mode("true");
				bean.setLoginId(userId);
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				//final boolean SaveFlag = service.insertProportionalTreaty(bean,pid, false, true);
			 	final boolean SaveFlag = service.insertProportionalTreaty(bean,pid, false, false);
				session.put("Exchangerate", bean.getExchRate());
				if (SaveFlag) {
					session.put("Exchangerate", bean.getExchRate());
					bean.setAmend_Id_Mode("true");
					bean.setProposal_no(bean.getProposal_no());
					service.showSecondPageData(bean.getProposal_no(), bean, pid);
					if("5".equalsIgnoreCase(pid)){
						service.showRetroCess(bean,1);
					}
					if("3".equalsIgnoreCase(pid)){
						service.showRetroContracts(bean,pid,false);
						logger.info("No. Of Ins==>"+bean.getNo_Insurer());
					}
					if("5".equalsIgnoreCase(pid)){
						service.showRetroCess(bean,1);
						if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
							List<String> retroCessList1=new ArrayList<String>();
							for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
								retroCessList1.add(String.valueOf(i));
							}
							bean.setRetroCessList(retroCessList1);
						}
					}
					if(StringUtils.isNotBlank(bean.getM_d_InstalmentNumber()) && Integer.parseInt(bean.getM_d_InstalmentNumber())>0){
						List<String> instalList=new ArrayList<String>();
						for(int i=0;i<Integer.parseInt(bean.getM_d_InstalmentNumber());i++){
							instalList.add(String.valueOf(i));
						}
						bean.setInstalList(instalList);
					}
					ShowDropDown("");
					resetCoverLimit();
					if("5".equalsIgnoreCase(pid)){
						forward="retroxol2";
					}else{
						forward="xol2";
					}
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
					bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
				}
				ShowDropDown("");
				resetCoverLimit();
				session.put("Exchangerate", bean.getExchRate());
				bean.setAmend_Id_Mode("true");
			}		 
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	public  String LayerEditMode(){
		String forward="";
		String editMode ="N";
		String Status="";
		try {
			
		if(!"N".equalsIgnoreCase(editMode)){
			forward ="redirectAction";	
			bean.setFlag("error");
		}
		else{
		bean.setBranchCode(branchCode);
		bean.setShortname(service.getShortname(bean));
		
		if("5".equalsIgnoreCase(pid)){
			forward="retroxol1";
		}else{
			forward="xol1";
		}
		final String contractNo=bean.getRenewal_contract_no();
		bean.setLayerNo(bean.getLayerLayerNo());
		final String layerNo=bean.getLayerNo();
		bean.setLay1(layerNo);
		bean.setContractno1(contractNo);
		/*if (session.get("EditProposalNo") != null) {
			bean.setProposal_no(session.get("EditProposalNo").toString());
		}*/
		if(StringUtils.isNotBlank(bean.getLayerProposalNo())){
			//session.put("EditProposalNo",bean.getLayerProposalNo());
			bean.setProposal_no(bean.getLayerProposalNo());
		}
		bean.setRenewalProposalNo(bean.getProposal_no());
		String proposalNo = new DropDownControllor().getCopyQuote("Copy","3".equalsIgnoreCase(pid)?pid:"6", bean.getBranchCode(), bean.getProposal_no());
		bean.setProposal_no(proposalNo);bean.setProposalReference("Layer");
			final String pid = (String) session.get("mfrid");
			session.put("Product", pid);
			final int CheckEditMode = service.getEditMode(bean.getProposal_no());
			if (CheckEditMode == 2) {
				bean.setAmend_Id_Mode("true");
			}
			if (bean.getProposal_no().equalsIgnoreCase("")) {
				ShowDropDown("edit");
				addActionError(getText("error.proposal.required"));
			} else {
				bean.setProposal_no(bean.getProposal_no());
				bean.setProduct_id(pid);
				final boolean CheckFlag = service.checkProductMatch(pid, bean.getProposal_no(), false);
				if (CheckFlag) {
					final boolean Editmode = service.riskEditMode(bean, false);
					if (Editmode) {
						if(StringUtils.isBlank(bean.getDepartId())){
							bean.setSubProfitList(new ArrayList<Map<String,Object>>());
						}else{
							bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
						}
						ShowDropDown("edit");
					} else {
						ShowDropDown("edit");
						addActionError(getText("error.proposal.notAvilable"));
					}
				} else {
					if(StringUtils.isBlank(bean.getDepartId())){
						bean.setSubProfitList(new ArrayList<Map<String,Object>>());
					}else{
						bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
					}
					ShowDropDown("edit");
					addActionError(getText("error.proposal.notAvilable"));
				}
			}
			bean.setLayerProposalNo(bean.getProposal_no());
			//bean.setProposal_no("");
			bean.setAmendId("0");
			bean.setLayerMode("Yes");
			bean.setLayerNo("");
			bean.setAmend_Id_Mode("");
			//bean.setProposalReference("Renewal");
			if(bean.getProStatus().equalsIgnoreCase("A") && StringUtils.isBlank(bean.getContractno1())){
				bean.setProStatus("P");
			}
			if(StringUtils.isBlank(bean.getContractno1())){
				bean.setProdisableStatus("Y");
			}
			if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
				if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
					Status = "CL";
				}else{
					Status = "CL";
				}
				dropDownController.updateRenewalEditMode(bean.getProposal_no(),Status,bean.getProposal_no());
			}
			dropDownController.updateEditMode(bean.getProposal_no(),"CL",bean.getProposal_no());
			dropDownController.updateBaseLayer(bean.getProposalNo(),bean.getProposal_no());
			if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
				dropDownController.updateEditMode(bean.getBaseLayer(),"CL",bean.getProposal_no());
				dropDownController.updateSubEditMode(bean.getBaseLayer(),"CL",bean.getProposal_no());
			}
			else{
				dropDownController.updateSubEditMode(bean.getProposal_no(),"CL",bean.getProposal_no());
			}
		}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}

	public String getFields() {
		fields=getText("xol.field")+",";
		if("Y".equalsIgnoreCase(bean.getProfitCommissionEnable())){
		fields=fields+"share_Profit_Commission,commissionSubClass,";
		}
		if("Y".equalsIgnoreCase(bean.getSlideEnable())){
			fields=fields+"slideScaleCommission,";
		}
		if("Y".equalsIgnoreCase(bean.getLossParEnable())){
			fields=fields+"lossParticipants,";
		}
		if("Y".equalsIgnoreCase(bean.getCrestaEnable())){
			fields=fields+"crestaStatus,";
		}
		fields=fields.substring(0, fields.lastIndexOf(","));
	return fields;
	}
	public String FirstPageUpdateMode(){
		String forward="";
		String Status="";
		bean.setMenuStatus("N");
		if(StringUtils.isBlank(bean.getContractNo()) && StringUtils.isNotBlank(bean.getContractNo())) {
			bean.setContNo(bean.getContractNo());
		}
		if(StringUtils.isNotBlank(bean.getDepartId())){
			session.put("DepartmentId",bean.getDepartId());
		}
		if("5".equalsIgnoreCase(pid)){
			forward="retroxol1";
			bean.setCedingId(bean.getCedingCo());
			bean.setBrokerId(bean.getBroker()); 
		}else{
			forward="xol1";
		}
		bean.setLay("");
		bean.setContractno("");
		try {
			final String pid = session.get("Product").toString();
			bean.setBranchCode(branchCode);
			if(bean.getProposalReference() ==null || bean.getProposalReference().equalsIgnoreCase("")){
			final int CheckEditMode = service.getEditMode(bean.getProposal_no());
			if (CheckEditMode == 2) {
				bean.setAmend_Id_Mode("true");
			}
			}
			bean.setLoginId(userId);
			bean.setProcessId(session.get("processId").toString());
			bean.setShortname(service.getShortname(bean));
			bean1.setBranchCode(branchCode);
			new DropDownControllor().getOpenPeriod(bean1);
			if("A".equalsIgnoreCase(bean.getProStatus())|| "5".equalsIgnoreCase(pid)){
				validatenext();
			}
			else{
				validatesave();
			}
			if (!hasActionErrors()) {
				RdsCalculation.SecondPageCaculation(bean,pid);
				bean.setAnualAggregateLiability(service.getSumOfCover(bean,pid));
				final boolean SaveFlag = service.updateProportionalTreaty(bean,pid);
				if(SaveFlag){
					if(StringUtils.isBlank(bean.getBaseLayer()) && !hasActionErrors()){
						dropDownController.updateSubClass(bean.getProposal_no(),"Save");
					}
					session.put("Exchangerate", bean.getExchRate());
					bean.setEditMode("EditMode");
					if(StringUtils.isNotBlank(bean.getM_d_InstalmentNumber()) && Integer.parseInt(bean.getM_d_InstalmentNumber())>0){
						List<String> instalList=new ArrayList<String>();
						for(int i=0;i<Integer.parseInt(bean.getM_d_InstalmentNumber());i++){
							instalList.add(String.valueOf(i));
						}
						bean.setInstalList(instalList);
					}
					if("5".equalsIgnoreCase(pid)){
						service.showRetroCess(bean,1);
						if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
							List<String> retroCessList1=new ArrayList<String>();
							for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
								retroCessList1.add(String.valueOf(i));
							}
							bean.setRetroCessList(retroCessList1);
						}
					}
					service.showSecondPageData(bean.getProposal_no(), bean, pid);
					if("3".equalsIgnoreCase(pid)){
						service.showRetroContracts(bean,pid,false);
					}

					RdsCalculation.SecondPageCaculation(bean,pid);
					bean.setAnualAggregateLiability(service.getSumOfCover(bean,pid));
					if(bean.getProStatus().equalsIgnoreCase("R")){
						bean.setBackmode("Reje");
						bean.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+bean.getProposal_no());
						bean.setStatus(bean.getContractGendration());
						if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
							dropDownController.updateRenewalEditMode(bean.getProposal_no(),"N","");
						}
						dropDownController.updateEditMode(bean.getProposal_no(),"N","");
						if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
							dropDownController.updateEditMode(bean.getBaseLayer(),"N","");
							dropDownController.updateSubEditMode(bean.getBaseLayer(),"N","");
						}
						else{
							dropDownController.updateSubEditMode(bean.getProposal_no(),"N","");
						}
						forward ="SucusssFac";
					}else{
						if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
							if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
								Status = "SRF";
							}else{
								Status = "BRF";
							}
							dropDownController.updateRenewalEditMode(bean.getProposal_no(),Status,bean.getProposal_no());
						}if(StringUtils.isNotBlank(bean.getRenewalEditMode()) && "Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
							 if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
									Status = "SRF";
								}else{
									Status = "BRF";
								}
						}else if(StringUtils.isNotBlank(bean.getProposalReference())){
							Status = "CLF";
						}else if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
							Status = "SEF";
						}else{
							Status = "BEF";
						}
						
						dropDownController.updateEditMode(bean.getProposal_no(), Status,bean.getProposal_no());
						if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
							dropDownController.updateEditMode(bean.getBaseLayer(),Status,bean.getProposal_no());
							dropDownController.updateSubEditMode(bean.getBaseLayer(),Status,bean.getProposal_no());
						}
						else{
							dropDownController.updateSubEditMode(bean.getProposal_no(),Status,bean.getProposal_no());
						}
						if("5".equalsIgnoreCase(pid)){
							forward="retroxol2";
						}else{
							
							forward="xol2";
						}
					}
					if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0){
						List<Integer> docList=new ArrayList<Integer>();
					for(int i=0;i<1;i++){
						docList.add(i);
					}
					bean.setStartIndex("0");
					bean.setEndIndex("1");
					bean.setDocuList(docList);
					}
				}
				else{
					ShowDropDown("");
					addActionError(getText("error.add.layerNo.NotExist"));
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
					bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
				}	
				ShowDropDown("");
				resetCoverLimit();
			}
			if(bean.getLayerProposalNo()!=null){
				service.showLayerBrokerage(bean.getLayerProposalNo(),bean)	;
			}
			resetCoverLimit();
			ShowDropDown("");
			resetRemarks();
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		finally{
			//This layer and contract no fields should not contain any data while saving first page.So set the values at the last.
			//Added to carry layer mode and contract no in xol layer while clicking previous button in second page.
			bean.setLay(bean.getLay1());
			bean.setContractno(bean.getContractno1());	
		}
		return forward;
	}

	public String SecondPageSave(){
		String forward="";
		try {
			final String ProductId = (String) session.get("Product");
			bean.setBranchCode(branchCode);
			bean.setLoginId(userId);
			bean.setProduct_id(ProductId);
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			if("A".equalsIgnoreCase(bean.getProStatus())|| "5".equalsIgnoreCase(pid)){
			validateSecondPage();
			}
			else{
				validateSecodnPageSaveMethod();	
			}
			if(bean.getProposalReference() ==null || bean.getProposalReference().equalsIgnoreCase("")){
			final int CheckEditMode = service.getEditMode(bean.getProposal_no());
			if (CheckEditMode == 2) {
				bean.setAmend_Id_Mode("true");
			}
			}
			if (!hasActionErrors()) {
				RdsCalculation.SecondPageCaculation(bean,ProductId);
				bean.setAnualAggregateLiability(service.getSumOfCover(bean,pid));
				bean.setExchRate((String) session.get("Exchangerate"));
				service.saveSecondPage(bean, ProductId);
				bean.setStatus(bean.getContractGendration());
				if (StringUtils.isNotBlank(bean.getContNo())) {
					bean.setBackmode("Con");
				}
				else if ("P".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Pro");
				}
				else if ("R".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Reje");
				}
				else if ("A".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Pro");
				}
				else if ("N".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("NTU");
				}
				if(StringUtils.isBlank(bean.getBaseLayer()) && !hasActionErrors()){
					dropDownController.updateSubClass(bean.getProposal_no(),"Save");
				}
				bean.setMode("");
				if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
					dropDownController.updateRenewalEditMode(bean.getProposal_no(),"N","");
				}
				dropDownController.updateEditMode(bean.getProposal_no(),"N","");
				if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
					dropDownController.updateEditMode(bean.getBaseLayer(),"N","");
					dropDownController.updateSubEditMode(bean.getBaseLayer(),"N","");
				}
				else{
					dropDownController.updateSubEditMode(bean.getProposal_no(),"N","");
				}
				forward="SucusssFac";
			} else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if("3".equalsIgnoreCase(pid)){
					resetRetroList();
				}else{
					if(StringUtils.isNotBlank(bean.getNoRetroCess()) && Integer.parseInt(bean.getNoRetroCess())>0){
						List<String> retroCessList1=new ArrayList<String>();
						for(int i=0;i<Integer.parseInt(bean.getNoRetroCess());i++){
							retroCessList1.add(String.valueOf(i));
						}
						bean.setRetroCessList(retroCessList1);
					}
				}
				if(StringUtils.isNotBlank(bean.getM_d_InstalmentNumber()) && Integer.parseInt(bean.getM_d_InstalmentNumber())>0){
					List<String> instalList=new ArrayList<String>();
					for(int i=0;i<Integer.parseInt(bean.getM_d_InstalmentNumber());i++){
						instalList.add(String.valueOf(i));
						//bean.getInstalmentDateList().set(i,dateFormat(bean.getInstalmentDateList().get(i)));
					}
					bean.setInstalList(instalList);
				}
				ShowDropDown("");
				service.showSecondPageData(bean.getProposal_no(), bean, ProductId);
				if("5".equalsIgnoreCase(ProductId)){
					service.showRetroCess(bean,2);
				}
				if("3".equals(pid)){
					forward="xol2";
				}else{
				    forward = "retroxol2";
				}
				resetRemarks();
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
				if(!hasActionErrors()){
				dropDownController.UpdateInstallmentTransaction(bean.getProposal_no());
				if("3".equalsIgnoreCase(pid)){
				dropDownController.updatepositionMasterEndtStatus(bean.getProposalNo(),pid,bean.getEndorsementDate(),bean.getCeaseStatus());
				}
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
				forward = SecondPageSave();
				//service.MoveReinstatementEmptyData(bean);
				if(!hasActionErrors()){
				dropDownController.riskDetailsEndorsement(bean.getProposal_no(),bean.getEndorsementStatus());
				}
			}
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return forward;
	}

	private void ShowDropDown(String type) {
		if("3".equalsIgnoreCase(pid) && (StringUtils.isBlank(type)||StringUtils.isNotBlank(bean.getContractListVal()))){
			String sumInsured = "";
			String deductible = "";
			String coverPer = "";
			String dedPer = "";
			List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
			if(StringUtils.isNotBlank(bean.getCedingCo())&&StringUtils.isNotBlank(bean.getIncepDate())&&StringUtils.isNotBlank(bean.getExpDate())&&StringUtils.isNotBlank(bean.getUwYear())&&StringUtils.isNotBlank(bean.getOrginalCurrency())&&StringUtils.isNotBlank(bean.getDepartId())&&StringUtils.isNotBlank(bean.getBusinessType())&&StringUtils.isNotBlank(bean.getProfit_Center())&&StringUtils.isNotBlank(bean.getLayerNo())){
				if(StringUtils.isNotBlank(bean.getBusinessType())&&("4".equalsIgnoreCase(bean.getBusinessType()) || "1".equalsIgnoreCase(bean.getBusinessType())|| "2".equalsIgnoreCase(bean.getBusinessType())|| "3".equalsIgnoreCase(bean.getBusinessType())|| "6".equalsIgnoreCase(bean.getBusinessType()) ||"7".equalsIgnoreCase(bean.getBusinessType()))||"8".equalsIgnoreCase(bean.getBusinessType())){
				if(bean.getCoverLimitOC() !=null &&bean.getCoverLimitOC().size()>0 ){
						for(int i=0;i<bean.getCoverLimitOC().size();i++){
							sumInsured =StringUtils.isEmpty(bean.getCoverLimitOC().get(i))? "0":bean.getCoverLimitOC().get(i).replaceAll(",", "");
							deductible = StringUtils.isEmpty(bean.getDeductableLimitOC().get(i))? "0":bean.getDeductableLimitOC().get(i).replaceAll(",", ""); 
						}
					}
				}
				else if(StringUtils.isNotBlank(bean.getBusinessType())&&"5".equalsIgnoreCase(bean.getBusinessType())){
					if(bean.getCoverLimitOC() !=null &&bean.getCoverLimitOC().size()>0 ){
						for(int i=0;i<bean.getCoverLimitOC().size();i++){
							sumInsured =StringUtils.isEmpty(bean.getCoverLimitOC().get(i))? "0":bean.getCoverLimitOC().get(i).replaceAll(",", "");
							deductible = StringUtils.isEmpty(bean.getDeductableLimitOC().get(i))? "0":bean.getDeductableLimitOC().get(i).replaceAll(",", ""); 
							coverPer = StringUtils.isEmpty(bean.getCoverLimitPercent().get(i))? "0":bean.getCoverLimitPercent().get(i).replaceAll(",", "");
							dedPer =StringUtils.isEmpty(bean.getDeductableLimitPercent().get(i))? "0":bean.getDeductableLimitPercent().get(i).replaceAll(",", ""); 
						}
					}	
				}
				bean.setContractTypelist(dropDownController.getContractValidation(pid,bean.getCedingCo(),bean.getIncepDate(),bean.getExpDate(),bean.getUwYear(),bean.getOrginalCurrency(),bean.getDepartId(),bean.getBusinessType(),sumInsured,StringUtils.isEmpty(bean.getContNo())? "0":bean.getContNo(),bean.getProfit_Center(),deductible,coverPer,dedPer,bean.getLayerNo(),bean.getBranchCode()));
				}
			else if(bean.getContractTypelist()==null){
				bean.setContractTypelist(list);
			}
		}
		bean.setYearList(getYearList());
		bean.setYearToList(getYearToList());
		bean.setProfit_Centerlist(dropDownController.getProfitCentreDropDown(branchCode));
		if(StringUtils.isBlank(bean.getDepartId())){
			bean.setSubProfitList(new ArrayList<Map<String,Object>>());
		}else{
			bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
		}
		bean.setUnderWritterlist(dropDownController.getUnderWritterDropDown(branchCode,attachedUW));
		bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
		bean.setPolBrlist(dropDownController.getPolicyBranchDropDown(branchCode));
		bean.setProposallist(dropDownController.getConstantDropDown("1"));
		bean.setAccontPeriodlist(dropDownController.getConstantDropDown("5"));
		bean.setPNOCDayslist(dropDownController.getConstantDropDown("3"));
		bean.setBrokerlist(dropDownController.getPersonalInfoDropDown(branchCode,"B",pid));
		bean.setProposaltypelist(dropDownController.getConstantDropDown("4"));
		bean.setOrginalCurrencylist(dropDownController.getCurrencyMasterDropDown(branchCode, countryId));
		bean.setTerritortylist(dropDownController.getTerritoryDropDown(branchCode));
		bean.setCeddingCompanylist(dropDownController.getPersonalInfoDropDown(branchCode,"C",pid));
		bean.setRetroTypelist(dropDownController.getConstantDropDown("8"));
		bean.setInwardBusinessTypelist(dropDownController.getConstantDropDown("24"));
		//bean.setBusinessTypelist(dropDownController.getConstantDropDownBusinessType("29",pid));
		getTypeOfBusiness();
		bean.setPaymentPartnerlist(dropDownController.getPaymentPartnerlist(branchCode,bean.getCedingCo(),bean.getBroker()));
		if("RI02".equalsIgnoreCase(sourceId)){
		bean.setPerilCoveredlist(dropDownController.getConstantDropDown("30"));
		}
		bean.setPremiumBasicList(dropDownController.getConstantDropDown("31"));
		bean.setCoverDepartmentList(dropDownController.getCoverDEpartmentDropDown(branchCode,pid,StringUtils.isBlank(bean.getDepartId())?"":bean.getDepartId()));
		bean.setBouquetList(dropDownController.getBouquetList(branchCode));   
		session.put("Product", pid);
	}

	public void validatesave(){
		try {
			boolean cedCheck = true;
			boolean cedflag = true;
			final Validation val = new Validation();
			if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0 && (pid.equalsIgnoreCase("3") ||  pid.equalsIgnoreCase("5"))){
				if(StringUtils.isBlank(bean.getEndorsmenttype())){
					addActionError(getText("end.type.error"));
				}
			}
			if(StringUtils.isBlank(bean.getBouquetModeYN())) {
				addActionError(getText("error.bouquetModeYn.required"));
			}
			if (val.isSelect(bean.getDepartId()).equalsIgnoreCase("")) {
				addActionError(getText("error.departId.required"));
			}if (val.isSelect(bean.getCedingCo()).equalsIgnoreCase("")) {
				addActionError(getText("error.cedingCo.required"));
			}
			Map<String, Object> map = null;
			List<Map<String, Object>> list = service.getValidation(bean);
			if (list != null && list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			}
			if (val.isNull(bean.getIncepDate()).equalsIgnoreCase("")) {
				addActionError(getText("error.incepDate.required"));
			} else if (val.checkDate(bean.getIncepDate()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.incepDate.check"));
			} else if (StringUtils.isNotBlank((bean.getRenewal_contract_no()))&& !"0".equals(bean.getRenewal_contract_no())&& map != null) {
				if ("Invalid".equalsIgnoreCase(val.getDateValidate((String) map.get("EXPIRY_DATE"), bean.getIncepDate()))) {
					addActionError(getText("errors.InceptionDate.invalid"));
				}else {
					bean.setRenewalFlag("NEWCONTNO");
				}
			}
			if (val.isNull(bean.getExpDate()).equalsIgnoreCase("")) {
				addActionError(getText("error.expDate.required"));
			} else if (val.checkDate(bean.getExpDate()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("errors.ExpiryDate.Error"));
			}
			if (!bean.getIncepDate().equalsIgnoreCase("")&& !bean.getExpDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.expDate.check"));
				}
			}
			if (val.isSelect(bean.getUwYear()).equalsIgnoreCase("")) {
				addActionError(getText("error.uwYear.required"));
			}if (val.isSelect(bean.getUwYearTo()).equalsIgnoreCase("")) {
				addActionError(getText("error.uwYearTo.required"));
			}
			
			
			if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAccDate()).equalsIgnoreCase("") && !bean.getEdit().equalsIgnoreCase("endorsment") && !val.checkDate(bean.getAccDate()).equalsIgnoreCase("INVALID")){
				if(new DropDownControllor().Validatethree(branchCode, bean.getAccDate())==0){
					addActionError(getText("errors.open.period.date",new String[] {bean1.getOpenPeriodDate()}));
				}
			}
			if (StringUtils.isNotBlank(bean.getIncepDate())&& StringUtils.isNotBlank(bean.getExpDate())) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check1"));
				}
			}if (val.isNull(bean.getLayerNo()).equalsIgnoreCase("")) {
				addActionError(getText("error.layerNo.required"));
			} else if (val.isValidNo(bean.getLayerNo()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.layerNo.error"));
			}
			if (StringUtils.isBlank(val.isSelect(bean.getOrginalCurrency()))) {
				addActionError(getText("error.orginalCurrency.required"));
			}
			if (StringUtils.isBlank(bean.getExchRate())) {
				addActionError(getText("error.exchRate.required"));
				cedCheck = false;
			} else if (val.isValidNo(bean.getExchRate().trim().toString()).equalsIgnoreCase("invalid")) {
				addActionError(getText("error.exchRate.check"));
				cedCheck = false;
			}
			
			if(StringUtils.isBlank(bean.getBusinessType())){
				addActionError(getText("error.BusinessType.required"));
			}
			
			if (StringUtils.isBlank(bean.getBroker())) {
				addActionError(getText("error.broker.required"));
			}
			if(StringUtils.isBlank(bean.getPaymentPartner())) {
				addActionError(getText("error.PaymentPartner.required"));
			}
			
			double maxlimit = 0.0;
			boolean spflag = true;
			if ("3".equals(pid)) {
				
				if("4".equals(bean.getDepartId())|| "2".equals(bean.getDepartId()) || "10".equals(bean.getDepartId())){
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
			}
			if("3".equals(pid) || "5".equals(pid)){
				if(StringUtils.isNotBlank(bean.getBusinessType()) &&(!"5".equalsIgnoreCase(bean.getBusinessType()))){
					//SList<String>coversubdepartId=new ArrayList<String>();
					for(int i=0;i<bean.getCoverLimitOC().size();i++){
						if(StringUtils.isBlank(bean.getCoverdepartId().get(i))){
							addActionError(getText("error.enter.CoverdepartId",new String[] { String.valueOf(i + 1) }));
						}
						//subclass//if(StringUtils.isBlank(bean.getCoversubdepartId().get(i))){
							//addActionError(getText("error.enter.CoverdepartId",new String[] { String.valueOf(i + 1) }));
						//}
						if(StringUtils.isBlank(bean.getCoverLimitOC().get(i))){
							addActionError(getText("error.enter.CoverLimitOC",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getDeductableLimitOC().get(i))){
							//addActionError(getText("error.enter.DeductableLimitOC",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getEgnpiAsPerOff().get(i)) ){
							addActionError(getText("error.enter.egnpi.as.per.off",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isNotBlank(bean.getEndorsmenttype()) &&"GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
							if(StringUtils.isBlank(bean.getGnpiAsPOSlide().get(i)) ){
								addActionError(getText("error.enter.gnpi.as.per.off",new String[] { String.valueOf(i + 1) }));
							}
						}
					}
					/*for (int j = 0; j < bean.getCoverLimitOC().size();j++){
						   for(int k = j+1; k < bean.getCoverLimitOC().size(); k++){
						       if(bean.getCoverdepartId().get(j).equals(bean.getCoverdepartId().get(k))){
						    	   if(bean.getCoversubdepartId().get(j).equals("ALL")){
						    		   addActionError(getText("error.enter.sub.class",new String[] { String.valueOf(k + 1) }));
						    	   }
						    	   else if(bean.getCoversubdepartId().get(k).contains(bean.getCoversubdepartId().get(j))){
						        	 addActionError(getText("error.enter.sub.class",new String[] { String.valueOf(k + 1) }));
						         }
						       }
						   }
						}*/
					//bean.setCoversubdepartId(coversubdepartId);
					if(DropDownControllor.findDuplicates(bean.getCoverdepartId()).size()>0){
						addActionError(getText("error.CoverdepartId.duplicate"));
					}
					if("17".equalsIgnoreCase(bean.getDepartId()) || "18".equalsIgnoreCase(bean.getDepartId()) || "19".equalsIgnoreCase(bean.getDepartId())){
						if(bean.getCoverdepartId().contains(bean.getDepartId()) && bean.getCoverdepartId().size()>1){
							addActionError(getText("error.CoverdepartId.groped"));
						}
					}
					
				}
				if(StringUtils.isNotBlank(bean.getBusinessType()) &&("5".equalsIgnoreCase(bean.getBusinessType()))){
					for(int i=0;i<bean.getCoverLimitAmount().size();i++){
						if(StringUtils.isBlank(bean.getCoverdepartIdS().get(i)) ){
							addActionError(getText("error.enter.CoverdepartId",new String[] { String.valueOf(i + 1) }));
						} 
						if(StringUtils.isBlank(bean.getCoverLimitAmount().get(i)) && StringUtils.isNotBlank(bean.getDeductableLimitAmount().get(i))){
							addActionError(getText("error.enter.CoverLimitAmount",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getDeductableLimitAmount().get(i))&& StringUtils.isNotBlank(bean.getCoverLimitAmount().get(i))){
							addActionError(getText("error.enter.DeductableLimitAmount",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getCoverLimitPercent().get(i)) &&StringUtils.isNotBlank(bean.getDeductableLimitPercent().get(i))){
							addActionError(getText("error.enter.CoverLimittPercent",new String[] { String.valueOf(i + 1) }));	
						}
						if(StringUtils.isBlank(bean.getDeductableLimitPercent().get(i)) && StringUtils.isNotBlank(bean.getCoverLimitPercent().get(i))){
							addActionError(getText("error.enter.DeductableLimitPercent",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getCoverLimitAmount().get(i)) && StringUtils.isBlank(bean.getCoverLimitPercent().get(i)) && StringUtils.isBlank(bean.getDeductableLimitPercent().get(i)) && StringUtils.isBlank(bean.getDeductableLimitAmount().get(i))){
							addActionError(getText("error.enter.CoverLimitAmount",new String[] { String.valueOf(i + 1) }));
							addActionError(getText("error.enter.CoverLimittPercent",new String[] { String.valueOf(i + 1) }));
							addActionError(getText("error.enter.DeductableLimitAmount",new String[] { String.valueOf(i + 1) }));
							addActionError(getText("error.enter.DeductableLimitPercent",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getEgnpiAsPerOffSlide().get(i)) ){
							addActionError(getText("error.enter.egnpi.as.per.off",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isNotBlank(bean.getEndorsmenttype()) &&"GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
							if(StringUtils.isBlank(bean.getGnpiAsPOSlide().get(i)) ){
								addActionError(getText("error.enter.gnpi.as.per.off",new String[] { String.valueOf(i + 1) }));
							}
						}
					}
					if(DropDownControllor.findDuplicates(bean.getCoverdepartIdS()).size()>0){
						addActionError(getText("error.CoverdepartId.duplicate"));
					}
					if("17".equalsIgnoreCase(bean.getDepartId()) || "18".equalsIgnoreCase(bean.getDepartId()) || "19".equalsIgnoreCase(bean.getDepartId())){
						if(bean.getCoverdepartIdS().contains(bean.getDepartId()) && bean.getCoverdepartIdS().size()>1){
							addActionError(getText("error.CoverdepartId.groped"));
						}
					}
				}
				
				if(StringUtils.isNotBlank(bean.getBusinessType()) &&(!"4".equalsIgnoreCase(bean.getBusinessType()) && !"5".equalsIgnoreCase(bean.getBusinessType()))){
					
				
				if((StringUtils.isNotBlank(bean.getUmbrellaXL()) && "Y".equalsIgnoreCase(bean.getUmbrellaXL())) || (StringUtils.isNotBlank(bean.getBusinessType()) && "6".equalsIgnoreCase(bean.getBusinessType())) ){
				
				if(StringUtils.isBlank(bean.getCoverLimitXL())){
					addActionError(getText("error.CoverLimitXL.required"));
					}else {
						bean.setCoverLimitXL((bean.getCoverLimitXL()).replaceAll(",", ""));
						if (val.isValidNo(bean.getCoverLimitXL().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.CoverLimitXL.required.format"));
						}
					}
				if(StringUtils.isBlank(bean.getDeductLimitXL())){
					addActionError(getText("error.DeductLimitXL.required"));
					}else {
						bean.setDeductLimitXL((bean.getDeductLimitXL()).replaceAll(",", ""));
						if (val.isValidNo(bean.getDeductLimitXL().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.DeductLimitXL.required.format"));
						}
					}
				
				}
			}
				
				if("5".equals(pid)){
				if(StringUtils.isBlank(bean.getEgnpiOffer())){
					addActionError(getText("error.egnpi.required"));
				}else {
					bean.setEgnpiOffer((bean.getEgnpiOffer()).replaceAll(",", ""));
					if (val.isValidNo(bean.getEgnpiOffer().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.egnpi.required"));
					}
				}
				}
				if("3".equals(pid)){
				if (StringUtils.isBlank(bean.getSubPremium())) {
					addActionError(getText("error.subPremium.required"));
				} else {
					bean.setSubPremium((bean.getSubPremium()).replaceAll(",", ""));
					if (val.isValidNo(bean.getSubPremium().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.subPremium.required.format"));
					}
					String ans = calcu.calculateXOL(bean,"EGNPI",0,sourceId);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getSubPremium().replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.setSubPremium(ans);
					}
				}
				}
				if(StringUtils.isNotBlank(bean.getPml()) && "Y".equalsIgnoreCase(bean.getPml())){
					if(StringUtils.isBlank(bean.getPmlPercent())){
						addActionError(getText("error.pmlpercent.required"));
					} else if (val.isValidNo(bean.getPmlPercent().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.pmlpercent.required.format"));
					} else if (val.percentageValid(bean.getPmlPercent().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.pmlpercent.checkgreater"));
					}
					
				}
				if(StringUtils.isBlank(bean.getPremiumbasis())){
					addActionError(getText("error.Premiumbasis.required"));
				}
				else if("1".equalsIgnoreCase(bean.getPremiumbasis())){
					if (StringUtils.isBlank(bean.getAdjRate())) {
						addActionError(getText("error.adjRate.required"));
					} else if (val.isValidNo(bean.getAdjRate().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.adjRate.required"));
					} else if (val.percentageValid(bean.getAdjRate().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.adjrate.checkgreater"));
					}
					if(StringUtils.isNotBlank(bean.getBusinessType()) && "5".equalsIgnoreCase(bean.getBusinessType())) {
						if (StringUtils.isBlank(bean.getMinimumRate())) {
							addActionError(getText("error.MinimumRate.required"));
						} else if (val.isValidNo(bean.getMinimumRate().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.MinimumRate.required"));
						} else if (val.percentageValid(bean.getMinimumRate().trim()).equalsIgnoreCase("greater")) {
							addActionError(getText("error.MinimumRate.checkgreater"));
						}
						if (StringUtils.isBlank(bean.getMaximumRate())) {
							addActionError(getText("error.MaximumRate.required"));
						} else if (val.isValidNo(bean.getMaximumRate().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.MaximumRate.required"));
						} else if (val.percentageValid(bean.getMaximumRate().trim()).equalsIgnoreCase("greater")) {
							addActionError(getText("error.MaximumRate.checkgreater"));
						}
						double min=Double.parseDouble(bean.getMinimumRate());
						double max=Double.parseDouble(bean.getMaximumRate());
						if(min>max){
							addActionError(getText("min.rate.less"));
						}
					}
				}
				else if("2".equalsIgnoreCase(bean.getPremiumbasis())){
					if (StringUtils.isBlank(bean.getMinimumRate())) {
						addActionError(getText("error.MinimumRate.required"));
					} else if (val.isValidNo(bean.getMinimumRate().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.MinimumRate.required"));
					} else if (val.percentageValid(bean.getMinimumRate().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.MinimumRate.checkgreater"));
					}
					if (StringUtils.isBlank(bean.getMaximumRate())) {
						addActionError(getText("error.MaximumRate.required"));
					} else if (val.isValidNo(bean.getMaximumRate().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.MaximumRate.required"));
					} else if (val.percentageValid(bean.getMaximumRate().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.MaximumRate.checkgreater"));
					}
					double min=Double.parseDouble((bean.getMinimumRate()==null||bean.getMinimumRate().equalsIgnoreCase(""))?"0":bean.getMinimumRate());
					double max=Double.parseDouble((bean.getMaximumRate()==null||bean.getMaximumRate().equalsIgnoreCase(""))?"0":bean.getMaximumRate());
					if(min>max){
						addActionError(getText("min.rate.less"));
					}
					
					if (val.percentageValid(bean.getBurningCostLF().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.BurningCostLF().checkgreater"));
					}
					
				}
				if (StringUtils.isBlank(bean.getEpi())) {
					addActionError(getText("error.epiperCent.required"));
				} else {
					bean.setEpi((bean.getEpi()).replaceAll(",", ""));
					if (val.isValidNo(bean.getEpi().trim()).equalsIgnoreCase("Invalid")) {
						addActionError(getText("error.epiperCent.required.format"));
					}else{
						if(!"3".equalsIgnoreCase(bean.getPremiumbasis())){
							String ans = calcu.calculateXOL(bean,"EPI",0,sourceId);
							if(Double.parseDouble(ans)!=Double.parseDouble(bean.getEpi().replaceAll(",",""))){
								//addActionError(getText("error.calcul.mistake"));
								logger.info("Amount Difference available for JSP EPI and Java Calculation EPI");
							}else{
								bean.setEpi(ans);
							}
						}
					}
				}
				if("3".equalsIgnoreCase(bean.getPremiumbasis())){
					bean.setMinimumRate(bean.getEpi());
				}
				if(StringUtils.isBlank(bean.getMinPremium())){
					 addActionError(getText("error.MinPremium.required"));
					}else {
						bean.setMinPremium((bean.getMinPremium()).replaceAll(",", ""));
						if (val.isValidNo(bean.getMinPremium().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.MinPremium.required.format"));
						}else {
								bean.setMinPremium((bean.getMinPremium()).replaceAll(",", ""));
								if (val.isValidNo(bean.getMinPremium().trim()).equalsIgnoreCase("INVALID")) {
									addActionError(getText("error.MinPremium.required.format"));
								}else if("2".equalsIgnoreCase(bean.getPremiumbasis())){
									String ans = calcu.calculateXOL(bean,"MinPremium",0,sourceId);
									if(Double.parseDouble(ans)!=Double.parseDouble(bean.getMinPremium().replaceAll(",",""))){
										//addActionError(getText("error.calcul.mistake"));
										logger.info("Amount Difference available for JSP Minimum Premium - 100% - OC and Java Calculation Minimum Premium - 100% - OC");
									}else{
										bean.setMinPremium(ans);
									}
								}
							}
					}
				if (StringUtils.isBlank(bean.getM_dPremium())) {
					addActionError(getText("error.depositPremium.required"));
				} else {
					bean.setM_dPremium((bean.getM_dPremium()).replaceAll(",", ""));
					if (val.isValidNo(bean.getM_dPremium().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.depositPremium.required"));
					}
				}
				}
				if (val.isNull(bean.getM_d_InstalmentNumber()).equalsIgnoreCase("")) {
					addActionError(getText("error.no.of.Instalment.Required"));
				} else if (val.isValidNo(bean.getM_d_InstalmentNumber()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("error.no.of.Instalment.Required"));
				}
				
				if(StringUtils.isBlank(bean.getPaymentDuedays())){
					addActionError(getText("error.PaymentDuedays.Required"));
				}
				double cedPer = 0.0;
				
				if("5".equalsIgnoreCase(pid)){
					if(StringUtils.isBlank(bean.getNoRetroCess())){
						addActionError(getText("error.no.of.retro.cession"));
					}
					else if("0".equalsIgnoreCase(bean.getNoRetroCess())){
						addActionError(getText("error.no.of.retro.cession.greater.than.zero"));
					}
				}
				Validation validation = new Validation();
				if(StringUtils.isBlank(bean.getInstallYN())) {
					addActionError(getText("error.InstallYN.required"));
				}else if("Y".equalsIgnoreCase(bean.getInstallYN())) {
					if ("3".equals(pid) || "5".equals(pid)) {
						int instalmentperiod = Integer.parseInt(bean.getM_d_InstalmentNumber());
						double mndPremiumOC=Double.parseDouble(bean.getMd_premium_our_service().replaceAll(",", ""));
						double totalInstPremium=0.0;
						boolean tata = false;
						for (int i = 0; i < instalmentperiod; i++) {
							
							if (!validation.isNull(bean.getInstalmentDateList().get(0)).equalsIgnoreCase("")) {
								if (validation.ValidateINstallDates(bean.getIncepDate(),bean.getInstalmentDateList().get(0)).equalsIgnoreCase("Invalid")) {
									tata = true;
								}
							}
							if (!validation.isNull(	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
								if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
									addActionError(getText("Error.Select.Expirydate", new String[] { String.valueOf(i + 1)}));
								}
							}
							if (!validation.isNull(bean.getInstallmentPremium().get(i))	.equalsIgnoreCase("")) {
								try{
					            	totalInstPremium+=Double.parseDouble(bean.getInstallmentPremium().get(i).replaceAll(",", ""));                	
					            }catch (Exception e) {
					            	//addActionError(getText("Error.installment.Premium",new String[]{String.valueOf(i+1)}));
								}
							}
							if (i != 0) {
								if (!validation.isNull(	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
									if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i-1),	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("Invalid")) {
										addActionError(getText("Error.required.InstalDate",new String[] { String.valueOf(i + 1)}));
									}
								}
							}
							
						}
						BigDecimal bd = new BigDecimal(totalInstPremium).setScale(2, RoundingMode.HALF_EVEN);
						totalInstPremium = bd.doubleValue();
						if((totalInstPremium)!=mndPremiumOC){
							//addActionError(getText("Error.total.installment.premium",new String[]{" Deposit Premium - Our Share - OC"}));
					    }
						if (tata == true) {
							addActionError(getText("Error.Select.AfterInceptionDate"));
						}
					}
					
				}
				
					double amt = 0.0;
					if ("3".equalsIgnoreCase(pid) || "5".equalsIgnoreCase(pid)) {
						if (StringUtils.isBlank(bean.getLimitOrigCur())) {
							//addActionError(getText("error.limit.required"));
							cedCheck = false;
						} else {
							bean.setLimitOrigCur((bean.getLimitOrigCur()).replaceAll(",", ""));
							if (val.isValidNo(bean.getLimitOrigCur()).equalsIgnoreCase("invalid")) {
								addActionError(getText("error.limit.check"));
								cedCheck = false;
							} else {
								amt = Double.parseDouble(bean.getLimitOrigCur());
							}
						}
						if (StringUtils.isBlank(bean.getDeduc_hunPercent())) {
							addActionError(getText("error.deduc_hunPercent.required"));
						} else {
							bean.setDeduc_hunPercent((bean.getDeduc_hunPercent()).replaceAll(",", ""));
							if (val.isValidNo(bean.getDeduc_hunPercent().trim()).equalsIgnoreCase("INVALID")) {
								addActionError(getText("error.deduc_hunPercent.check"));
							}
						}
					}
					if ("3".equals(pid)){
						if (cedCheck) {
							double amount = (amt * (cedPer / 100.0));
							amount = amount / Double.parseDouble(bean.getExchRate());
							maxlimit = Double.parseDouble(bean.getMaxLimit_Product());
							logger.info("Cedent Amount=>" + amount);
							logger.info("Max Limit=>" + maxlimit);
							if (amount > maxlimit) {
								addActionError(getText("error.accAmtlessUWAmt"));
							}
						}
					}
					if(StringUtils.isNotBlank(bean.getM_dPremium()) && StringUtils.isNotBlank(bean.getSharSign())){
						final  DecimalFormat twoDigit = new DecimalFormat("###0.00");
						final double dvalue = (Double.parseDouble(bean.getM_dPremium())* (Double.parseDouble(bean.getSharSign())) / 100);
						logger.info("Gwpi Our Share Calculated Value=>" + dvalue);
						final double dround = Math.round(dvalue * 100.0) / 100.0;
						logger.info("Rounded Value=>" + dround);
						final double valu = Double.parseDouble(twoDigit.format(dround));//A
						logger.info("Formated Value=>" + valu);
						if(StringUtils.isNotBlank(bean.getContNo())){
						double sumInst=new DropDownControllor().getSumOfInstallmentBooked(bean.getContNo(), bean.getLayerNo());//B
							if(valu<sumInst){
								addActionError(getText("error.installment.premiumbooked.xol"));
							}
						}
					}if(StringUtils.isNotBlank(bean.getM_d_InstalmentNumber())){
						int count=new DropDownControllor().getCountOfInstallmentBooked(bean.getContNo(), bean.getLayerNo());
						if(Double.parseDouble(bean.getM_d_InstalmentNumber())<count){
							addActionError(getText("error.no.installment.premiumbooked.xol"));
						}
					}
					
					
				
			
				if (StringUtils.isNotBlank(bean.getM_dPremium()) && StringUtils.isNotBlank(bean.getEpi())) {
					bean.setM_dPremium((bean.getM_dPremium()).replaceAll(",", ""));
					bean.setEpi((bean.getEpi()).replaceAll(",", ""));
					if (!val.isValidNo(bean.getM_dPremium().trim())	.equalsIgnoreCase("INVALID")&& !val.isValidNo(bean.getEpi().trim()).equalsIgnoreCase("INVALID")) {

						final float mdpremium = Float.parseFloat(bean.getM_dPremium());
						final float Pepi = Float.parseFloat(bean.getEpi());

						if (mdpremium > Pepi) {
							addActionError(getText("error.mdandpremium.difference.invalid"));
						}
					}
				}
			
			if (StringUtils.isNotBlank(bean.getM_dPremium()) && StringUtils.isNotBlank(bean.getMinPremium())) {
				bean.setM_dPremium((bean.getM_dPremium()).replaceAll(",", ""));
				bean.setMinPremium((bean.getMinPremium()).replaceAll(",", ""));
				if (!val.isValidNo(bean.getM_dPremium().trim())	.equalsIgnoreCase("INVALID")&& !val.isValidNo(bean.getMinPremium().trim()).equalsIgnoreCase("INVALID")) {

					final float mdpremium = Float.parseFloat(bean.getM_dPremium());
					final float minp = Float.parseFloat(bean.getMinPremium());

					if (mdpremium > minp) {
						addActionError(getText("error.mdandminpremium.difference.invalid"));
					}
				}
			}
			if (!val.isNull(bean.getLayerNo()).equalsIgnoreCase("")) {
				if (service.getLayerDuplicationCheck(bean)) {
					logger.info("// PMD Changes");
					addActionError(getText("error.layer.duplicate"));
				}
			}
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && StringUtils.isBlank(bean.getDocStatus())) {
				addActionError(getText("doc.status"));
			}
			if("3".equalsIgnoreCase(pid)){
			validationContract();
			}
			validationRemarks();
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		
	}
	private void validationContract() {
		String sumInsured="";
		String deductible="";
		String coverPer="";
		String dedPer="";
		
		try{
			if(StringUtils.isNotBlank(bean.getCedingCo())&&StringUtils.isNotBlank(bean.getIncepDate())&&StringUtils.isNotBlank(bean.getExpDate())&&StringUtils.isNotBlank(bean.getUwYear())&&StringUtils.isNotBlank(bean.getOrginalCurrency())&&StringUtils.isNotBlank(bean.getDepartId())&&StringUtils.isNotBlank(bean.getBusinessType())&&StringUtils.isNotBlank(bean.getProfit_Center())&&StringUtils.isNotBlank(bean.getLayerNo())){
				if(StringUtils.isNotBlank(bean.getBusinessType())&&("4".equalsIgnoreCase(bean.getBusinessType()) || "1".equalsIgnoreCase(bean.getBusinessType())|| "2".equalsIgnoreCase(bean.getBusinessType())|| "3".equalsIgnoreCase(bean.getBusinessType())|| "6".equalsIgnoreCase(bean.getBusinessType()) || "7".equalsIgnoreCase(bean.getBusinessType())|| "8".equalsIgnoreCase(bean.getBusinessType()))){
				if(bean.getCoverLimitOC() !=null &&bean.getCoverLimitOC().size()>0 ){
						for(int i=0;i<bean.getCoverLimitOC().size();i++){
							sumInsured =StringUtils.isEmpty(bean.getCoverLimitOC().get(i))? "0":bean.getCoverLimitOC().get(i).replaceAll(",", "");
							deductible = StringUtils.isEmpty(bean.getDeductableLimitOC().get(i))? "0":bean.getDeductableLimitOC().get(i).replaceAll(",", ""); 
						}
					}
				}
				else if(StringUtils.isNotBlank(bean.getBusinessType())&&"5".equalsIgnoreCase(bean.getBusinessType())){
					if(bean.getCoverLimitOC() !=null &&bean.getCoverLimitOC().size()>0 ){
						for(int i=0;i<bean.getCoverLimitOC().size();i++){
							sumInsured =StringUtils.isEmpty(bean.getCoverLimitOC().get(i))? "0":bean.getCoverLimitOC().get(i).replaceAll(",", "");
							deductible = StringUtils.isEmpty(bean.getDeductableLimitOC().get(i))? "0":bean.getDeductableLimitOC().get(i).replaceAll(",", ""); 
							coverPer = StringUtils.isEmpty(bean.getCoverLimitPercent().get(i))? "0":bean.getCoverLimitPercent().get(i).replaceAll(",", "");
							dedPer =StringUtils.isEmpty(bean.getDeductableLimitPercent().get(i))? "0":bean.getDeductableLimitPercent().get(i).replaceAll(",", ""); 
						}
					}	
				}
				bean.setContractTypelist(dropDownController.getContractValidation(pid,bean.getCedingCo(),bean.getIncepDate(),bean.getExpDate(),bean.getUwYear(),bean.getOrginalCurrency(),bean.getDepartId(),bean.getBusinessType(),sumInsured,StringUtils.isEmpty(bean.getContNo())? "0":bean.getContNo(),bean.getProfit_Center(),deductible,coverPer,dedPer,bean.getLayerNo(),bean.getBranchCode()));
				}
				if(bean.getContractTypelist()!=null && bean.getContractTypelist().size()>0){
					if(StringUtils.isBlank(bean.getContractListVal())){
						addActionError(getText("error.contract.list"));
					}
				}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}
	private void validatenext() {
		try {
			boolean flags = true;
			boolean cedCheck = true;
			boolean cedflag = true;
			final Validation val = new Validation();
			Map<String, Object> map = null;
			List<Map<String, Object>> list = service.getValidation(bean);
			if (list != null && list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			}
			if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0 && pid.equalsIgnoreCase("3")){
				if(StringUtils.isBlank(bean.getEndorsmenttype())){
					addActionError(getText("end.type.error"));
				}
			}
			if(StringUtils.isBlank(bean.getBouquetModeYN())) {
				addActionError(getText("error.bouquetModeYn.required"));
			}
			
			if (val.isSelect(bean.getCedingCo()).equalsIgnoreCase("")) {
				addActionError(getText("error.cedingCo.required"));
			}
			if (val.isNull(bean.getIncepDate()).equalsIgnoreCase("")) {
				addActionError(getText("error.incepDate.required"));
			} else if (val.checkDate(bean.getIncepDate()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.incepDate.check"));
			} else if (StringUtils.isNotBlank((bean.getRenewal_contract_no()))&& !"0".equals(bean.getRenewal_contract_no())&& map != null) {
				if ("Invalid".equalsIgnoreCase(val.getDateValidate((String) map.get("EXPIRY_DATE"), bean.getIncepDate()))) {
					addActionError(getText("errors.InceptionDate.invalid"));
				}else {
					bean.setRenewalFlag("NEWCONTNO");
				}
			}
			if (val.isNull(bean.getExpDate()).equalsIgnoreCase("")) {
				addActionError(getText("error.expDate.required"));
			} else if (val.checkDate(bean.getExpDate()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("errors.ExpiryDate.Error"));
			}
			if (!bean.getIncepDate().equalsIgnoreCase("")&& !bean.getExpDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.expDate.check"));
				}
			}
			if (val.isSelect(bean.getUwYear()).equalsIgnoreCase("")) {
				addActionError(getText("error.uwYear.required"));
			}if (val.isSelect(bean.getUwYearTo()).equalsIgnoreCase("")) {
				addActionError(getText("error.uwYearTo.required"));
			}
			if (StringUtils.isNotBlank(bean.getIncepDate())&& StringUtils.isNotBlank(bean.getExpDate())) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check1"));
				}
			}
			if (val.isNull(bean.getLayerNo()).equalsIgnoreCase("")) {
				addActionError(getText("error.layerNo.required"));
			} else if (val.isValidNo(bean.getLayerNo()).equalsIgnoreCase("INVALID")) {
				addActionError(getText("error.layerNo.error"));
			}
			if (!val.isNull(bean.getLayerNo()).equalsIgnoreCase("")) {
				if (service.getLayerDuplicationCheck(bean)) {
					logger.info("// PMD Changes");
					addActionError(getText("error.layer.duplicate"));
				}
			}
			if(StringUtils.isBlank(bean.getRiskdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getRiskdetailYN())) {
				if (StringUtils.isBlank(val.isSelect(bean.getOrginalCurrency()))) {
					addActionError(getText("error.orginalCurrency.required"));
				}
				/*if(StringUtils.isBlank(bean.getExchangeType())){
					addActionError(getText("error.ExchangeType.required"));
				}*/
				if (StringUtils.isBlank(bean.getExchRate())) {
					addActionError(getText("error.exchRate.required"));
					cedCheck = false;
				} else if (val.isValidNo(bean.getExchRate().trim().toString()).equalsIgnoreCase("invalid")) {
					addActionError(getText("error.exchRate.check"));
					cedCheck = false;
				}
				if (StringUtils.isBlank(bean.getTreatyName_type())) {
					if("3".equalsIgnoreCase(pid)) {
						addActionError(getText("error.treatyName_type.required"));
					}
					else if("5".equalsIgnoreCase(pid)){
						addActionError(getText("error.retroTreatyName.required"));
					}
				}
				if(StringUtils.isBlank(bean.getBusinessType())){
					addActionError(getText("error.BusinessType.required"));
				}
				if (val.isSelect(bean.getDepartId()).equalsIgnoreCase("")) {
					addActionError(getText("error.departId.required"));
				}
				if (StringUtils.isBlank(bean.getBasis())) {
					addActionError(getText("error.basic.required"));
				}
				if(StringUtils.isNotBlank(bean.getBusinessType()) &&(!"5".equalsIgnoreCase(bean.getBusinessType()))){
					for(int i=0;i<bean.getCoverLimitOC().size();i++){
						if(StringUtils.isBlank(bean.getCoverdepartId().get(i))){
							addActionError(getText("error.enter.CoverdepartId",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getCoverLimitOC().get(i))){
							addActionError(getText("error.enter.CoverLimitOC",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getDeductableLimitOC().get(i))){
							addActionError(getText("error.enter.DeductableLimitOC",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getEgnpiAsPerOff().get(i)) ){
							addActionError(getText("error.enter.egnpi.as.per.off",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isNotBlank(bean.getEndorsmenttype()) &&"GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
							if(StringUtils.isBlank(bean.getGnpiAsPO().get(i)) ){
								addActionError(getText("error.enter.gnpi.as.per.off",new String[] { String.valueOf(i + 1) }));
							}
						}
					}
					if(DropDownControllor.findDuplicates(bean.getCoverdepartId()).size()>0){
						addActionError(getText("error.CoverdepartId.duplicate"));
					}
					if("17".equalsIgnoreCase(bean.getDepartId()) || "18".equalsIgnoreCase(bean.getDepartId()) || "19".equalsIgnoreCase(bean.getDepartId())){
						if(bean.getCoverdepartId().contains(bean.getDepartId()) && bean.getCoverdepartIdS().size()>1){
							addActionError(getText("error.CoverdepartId.groped"));
						}
					}
				}
				if(StringUtils.isNotBlank(bean.getBusinessType()) &&("5".equalsIgnoreCase(bean.getBusinessType()))){
					for(int i=0;i<bean.getCoverLimitAmount().size();i++){
						if(StringUtils.isBlank(bean.getCoverdepartIdS().get(i)) ){
							addActionError(getText("error.enter.CoverdepartId",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getCoverLimitAmount().get(i)) && StringUtils.isNotBlank(bean.getCoverLimitPercent().get(i))){
							addActionError(getText("error.enter.CoverLimitAmount",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getCoverLimitPercent().get(i))&& StringUtils.isNotBlank(bean.getCoverLimitAmount().get(i))){
							addActionError(getText("error.enter.CoverLimittPercent",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getDeductableLimitAmount().get(i)) &&StringUtils.isNotBlank(bean.getDeductableLimitPercent().get(i))){
							addActionError(getText("error.enter.DeductableLimitAmount",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getDeductableLimitPercent().get(i)) && StringUtils.isNotBlank(bean.getDeductableLimitAmount().get(i))){
							addActionError(getText("error.enter.DeductableLimitPercent",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getCoverLimitAmount().get(i)) && StringUtils.isBlank(bean.getCoverLimitPercent().get(i)) && StringUtils.isBlank(bean.getDeductableLimitPercent().get(i)) && StringUtils.isBlank(bean.getDeductableLimitAmount().get(i))){
							addActionError(getText("error.enter.CoverLimitAmount",new String[] { String.valueOf(i + 1) }));
							addActionError(getText("error.enter.CoverLimittPercent",new String[] { String.valueOf(i + 1) }));
							addActionError(getText("error.enter.DeductableLimitAmount",new String[] { String.valueOf(i + 1) }));
							addActionError(getText("error.enter.DeductableLimitPercent",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(bean.getEgnpiAsPerOffSlide().get(i)) ){
							addActionError(getText("error.enter.egnpi.as.per.off",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isNotBlank(bean.getEndorsmenttype()) &&"GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
							if(StringUtils.isBlank(bean.getGnpiAsPOSlide().get(i)) ){
								addActionError(getText("error.enter.gnpi.as.per.off",new String[] { String.valueOf(i + 1) }));
							}
						}
						
					}
					if(DropDownControllor.findDuplicates(bean.getCoverdepartIdS()).size()>0){
						addActionError(getText("error.CoverdepartId.duplicate"));
					}
					if("17".equalsIgnoreCase(bean.getDepartId()) || "18".equalsIgnoreCase(bean.getDepartId()) || "19".equalsIgnoreCase(bean.getDepartId())){
						if(bean.getCoverdepartIdS().contains(bean.getDepartId()) && bean.getCoverdepartIdS().size()>1){
							addActionError(getText("error.CoverdepartId.groped"));
						}
					}
				}
				
				if(StringUtils.isNotBlank(bean.getBusinessType()) &&(!"4".equalsIgnoreCase(bean.getBusinessType()) && !"5".equalsIgnoreCase(bean.getBusinessType()))){
					
				if(!"5".equalsIgnoreCase(bean.getBusinessType())){
					/*if(StringUtils.isBlank(bean.getEvent_limit())){
						addActionError(getText("error.eventLimit.required"));
						}else {
							bean.setEvent_limit((bean.getEvent_limit()).replaceAll(",", ""));
							if (val.isValidNo(bean.getEvent_limit().trim()).equalsIgnoreCase("INVALID")) {
								addActionError(getText("error.eventLimit.required"));
							}
						}*/
				}
				if(StringUtils.isNotBlank(bean.getUmbrellaXL()) && "Y".equalsIgnoreCase(bean.getUmbrellaXL())){
				
				if(StringUtils.isBlank(bean.getCoverLimitXL())){
					addActionError(getText("error.CoverLimitXL.required"));
					}else {
						bean.setCoverLimitXL((bean.getCoverLimitXL()).replaceAll(",", ""));
						if (val.isValidNo(bean.getCoverLimitXL().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.CoverLimitXL.required.format"));
						}
					}
				if(StringUtils.isBlank(bean.getDeductLimitXL())){
					addActionError(getText("error.DeductLimitXL.required"));
					}else {
						bean.setDeductLimitXL((bean.getDeductLimitXL()).replaceAll(",", ""));
						if (val.isValidNo(bean.getDeductLimitXL().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.DeductLimitXL.required.format"));
						}
					}
				
				}
			}
			}
			if(StringUtils.isBlank(bean.getBrokerdetYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getBrokerdetYN())) {
				if (StringUtils.isBlank(bean.getBroker())) {
					addActionError(getText("error.broker.required"));
				}
				if(StringUtils.isBlank(bean.getPaymentPartner())) {
					addActionError(getText("error.PaymentPartner.required"));
				}
				if (val.isNull(bean.getLeader_Underwriter()).equalsIgnoreCase("")) {
					addActionError(getText("errors.leader_Underwriter.second"));
				}
				if("RI02".equalsIgnoreCase(sourceId) && "3".equalsIgnoreCase(bean.getProduct_id())){
					if(StringUtils.isBlank(bean.getLeader_Underwriter_country())){
						addActionError(getText("errors.leader_Underwriter.second.country"));
					}
				}
				if (val.isNull(bean.getUnderwriter_Recommendations()).equalsIgnoreCase("")) {
					//addActionError(getText("errors.underwriter_Recommendations.second"));
				}
				if (val.isNull(bean.getLeader_Underwriter_share()).equalsIgnoreCase("")) {
					addActionError(getText("errors.leader_Underwriter_share.second"));
				} else if (val.percentageValid(bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.leader_Underwriter_share.second1"));
				} else if (val.percentageValid(bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.leader_Underwriter_share.secondless"));
				} else if (val.percentageValid(bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.leader_Underwriter_share.secondgreater"));
				}
				/*if(StringUtils.isNotBlank(bean.getLeader_Underwriter_share()) && !"64".equalsIgnoreCase(bean.getLeader_Underwriter())){
					if(service.GetShareValidation(bean)){
					addActionError(getText("errors.leader_Underwriter_share.greater.signed"));
				}
				}else{
					if(dropDownController.GetShareEqualValidation(bean.getProduct_id(),bean.getLeader_Underwriter_share(),bean.getProposal_no())){
						addActionError(getText("errors.leader_Underwriter_share.equals.signed"));
					} 
				}*/
				
			}
			if(StringUtils.isBlank(bean.getPremiumdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getPremiumdetailYN())) {
				if (StringUtils.isBlank(bean.getSubPremium())) {
					addActionError(getText("error.subPremium.required"));
				} else {
					bean.setSubPremium((bean.getSubPremium()).replaceAll(",", ""));
					if (val.isValidNo(bean.getSubPremium().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.subPremium.required.format"));
					}
					
				}
				if(StringUtils.isBlank(bean.getPremiumbasis())){
					addActionError(getText("error.Premiumbasis.required"));
				}
				else if("1".equalsIgnoreCase(bean.getPremiumbasis())){
					if (StringUtils.isBlank(bean.getAdjRate())) {
						addActionError(getText("error.adjRate.required"));
					} else if (val.isValidNo(bean.getAdjRate().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.adjRate.required.format"));
					} else if (val.percentageValid(bean.getAdjRate().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.adjrate.checkgreater"));
					}
					if(StringUtils.isNotBlank(bean.getBusinessType()) && "5".equalsIgnoreCase(bean.getBusinessType())) {
						if (StringUtils.isBlank(bean.getMinimumRate())) {
							addActionError(getText("error.MinimumRate.required"));
						} else if (val.isValidNo(bean.getMinimumRate().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.MinimumRate.required.format"));
						} else if (val.percentageValid(bean.getMinimumRate().trim()).equalsIgnoreCase("greater")) {
							addActionError(getText("error.MinimumRate.checkgreater"));
						}
						if (StringUtils.isBlank(bean.getMaximumRate())) {
							addActionError(getText("error.MaximumRate.required"));
						} else if (val.isValidNo(bean.getMaximumRate().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.MaximumRate.required.format"));
						} else if (val.percentageValid(bean.getMaximumRate().trim()).equalsIgnoreCase("greater")) {
							addActionError(getText("error.MaximumRate.checkgreater"));
						}
						double min=Double.parseDouble(bean.getMinimumRate());
						double max=Double.parseDouble(bean.getMaximumRate());
						if(min>max){
							addActionError(getText("min.rate.less"));
						}
					}
				}
				else if("2".equalsIgnoreCase(bean.getPremiumbasis())){
					if (StringUtils.isBlank(bean.getMinimumRate())) {
						addActionError(getText("error.MinimumRate.required"));
					} else if (val.isValidNo(bean.getMinimumRate().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.MinimumRate.required"));
					} else if (val.percentageValid(bean.getMinimumRate().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.MinimumRate.checkgreater"));
					}
					if (StringUtils.isBlank(bean.getMaximumRate())) {
						addActionError(getText("error.MaximumRate.required"));
					} else if (val.isValidNo(bean.getMaximumRate().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.MaximumRate.required"));
					} else if (val.percentageValid(bean.getMaximumRate().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.MaximumRate.checkgreater"));
					}
					double min=Double.parseDouble(bean.getMinimumRate());
					double max=Double.parseDouble(bean.getMaximumRate());
					if(min>max){
						addActionError(getText("min.rate.less"));
					}
					if(StringUtils.isBlank(bean.getBurningCostLF())){
						addActionError(getText("error.BurningCostLF.required"));
					}
					if (val.percentageValid(bean.getBurningCostLF().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("error.BurningCostLF().checkgreater"));
					}
					
				}
				if (StringUtils.isBlank(bean.getEpi())) {
					addActionError(getText("error.epiperCent.required"));
				} else {
					bean.setEpi((bean.getEpi()).replaceAll(",", ""));
					if (val.isValidNo(bean.getEpi().trim()).equalsIgnoreCase("Invalid")) {
						addActionError(getText("error.epiperCent.required.format"));
					}else{
						if(!"3".equalsIgnoreCase(bean.getPremiumbasis())){
							String ans = calcu.calculateXOL(bean,"EPI",0,sourceId);
							if(Double.parseDouble(ans)!=Double.parseDouble(bean.getEpi().replaceAll(",",""))){
								//addActionError(getText("error.calcul.mistake"));
								logger.info("Amount Difference available for JSP EPI and Java Calculation EPI");
							}else{
								bean.setEpi(ans);
							}
						}
					}
				}
				if("3".equalsIgnoreCase(bean.getPremiumbasis())){
					bean.setMinimumRate(bean.getEpi());
				}
				if(StringUtils.isBlank(bean.getMinPremium())){
					 addActionError(getText("error.MinPremium.required"));
					}else {
						bean.setMinPremium((bean.getMinPremium()).replaceAll(",", ""));
						if (val.isValidNo(bean.getMinPremium().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("error.MinPremium.required.format"));
						}else if("2".equalsIgnoreCase(bean.getPremiumbasis())){
							String ans = calcu.calculateXOL(bean,"MinPremium",0,sourceId);
							if(Double.parseDouble(ans)!=Double.parseDouble(bean.getMinPremium().replaceAll(",",""))){
								//addActionError(getText("error.calcul.mistake"));
								logger.info("Amount Difference available for JSP Minimum Premium - 100% - OC and Java Calculation Minimum Premium - 100% - OC");
							}else{
								bean.setMinPremium(ans);
							}
						}
					}
				if (StringUtils.isBlank(bean.getM_dPremium())) {
					addActionError(getText("error.m_dPremium.required1"));
				} else {
					bean.setM_dPremium((bean.getM_dPremium()).replaceAll(",", ""));
					if (val.isValidNo(bean.getM_dPremium().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("error.m_dPremium.required.format"));
					}
				}
				if (StringUtils.isNotBlank(bean.getM_dPremium()) && StringUtils.isNotBlank(bean.getEpi())) {
					bean.setM_dPremium((bean.getM_dPremium()).replaceAll(",", ""));
					bean.setEpi((bean.getEpi()).replaceAll(",", ""));
					if (!val.isValidNo(bean.getM_dPremium().trim())	.equalsIgnoreCase("INVALID")&& !val.isValidNo(bean.getEpi().trim()).equalsIgnoreCase("INVALID")) {

						final float mdpremium = Float.parseFloat(bean.getM_dPremium());
						final float Pepi = Float.parseFloat(bean.getEpi());

						if (mdpremium > Pepi) {
							addActionError(getText("error.mdandpremium.difference.invalid1"));
						}
					}
				}
			
			if (StringUtils.isNotBlank(bean.getM_dPremium()) && StringUtils.isNotBlank(bean.getMinPremium())) {
				bean.setM_dPremium((bean.getM_dPremium()).replaceAll(",", ""));
				bean.setMinPremium((bean.getMinPremium()).replaceAll(",", ""));
				if (!val.isValidNo(bean.getM_dPremium().trim())	.equalsIgnoreCase("INVALID")&& !val.isValidNo(bean.getMinPremium().trim()).equalsIgnoreCase("INVALID")) {

					final float mdpremium = Float.parseFloat(bean.getM_dPremium());
					final float minp = Float.parseFloat(bean.getMinPremium());

					if (mdpremium > minp) {
						addActionError(getText("error.mdandminpremium.difference.invalid"));
					}
				}
			}
				if (val.isNull(bean.getM_d_InstalmentNumber()).equalsIgnoreCase("")) {
					addActionError(getText("error.Instalment.error"));
					
				} else if (val.isValidNo(bean.getM_d_InstalmentNumber()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("error.Instalment.Required"));
				}
				if(StringUtils.isBlank(bean.getRateOnLine())) {
					addActionError(getText("error.rateonline.Required"));
				}
			}
			if(StringUtils.isBlank(bean.getInstallYN())) {
					addActionError(getText("error.InstallYN.required"));
			}else if("Y".equalsIgnoreCase(bean.getInstallYN()) && StringUtils.isNotBlank(bean.getM_d_InstalmentNumber())) {
				if ("3".equals(pid) || "5".equals(pid)) {
					int instalmentperiod = Integer.parseInt(bean.getM_d_InstalmentNumber());
					double totalInstPremium=0.0;
					boolean tata = false;
					for (int i = 0; i < instalmentperiod; i++) {
						
						if (!val.isNull(bean.getInstalmentDateList().get(0)).equalsIgnoreCase("")) {
							if (val.ValidateINstallDates(bean.getIncepDate(),bean.getInstalmentDateList().get(0)).equalsIgnoreCase("Invalid")) {
								tata = true;
							}
						}
						if (!val.isNull(	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
							if (val.ValidateTwoDates(bean.getInstalmentDateList().get(i),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
								addActionError(getText("Error.Select.Expirydate", new String[] { String.valueOf(i + 1)}));
							}
						}
						if (!val.isNull(bean.getInstallmentPremium().get(i))	.equalsIgnoreCase("")) {
							try{
				            	totalInstPremium+=Double.parseDouble(bean.getInstallmentPremium().get(i).replaceAll(",", ""));                	
				            }catch (Exception e) {
				            	//addActionError(getText("Error.installment.Premium",new String[]{String.valueOf(i+1)}));
							}
						}
						if (i != 0) {
							if (!val.isNull(	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
								if (val.ValidateTwoDates(bean.getInstalmentDateList().get(i-1),	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("Invalid")) {
									addActionError(getText("Error.required.InstalDate",new String[] { String.valueOf(i + 1)}));
								}
							}
						}
						
					}
					BigDecimal bd = new BigDecimal(totalInstPremium).setScale(2, RoundingMode.HALF_EVEN);
					totalInstPremium = bd.doubleValue();
					
					if (tata == true) {
						addActionError(getText("Error.Select.AfterInceptionDate"));
					}
				}
				if(StringUtils.isNotBlank(bean.getM_d_InstalmentNumber())){
					int count=new DropDownControllor().getCountOfInstallmentBooked(bean.getContNo(), bean.getLayerNo());
					if(Double.parseDouble(bean.getM_d_InstalmentNumber())<count){
						addActionError(getText("error.no.installment.premiumbooked.xol"));
					}
				}
				
			}
			if(StringUtils.isBlank(bean.getAcqdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getAcqdetailYN())) {
				if (val.isNull(bean.getBrokerage()).equalsIgnoreCase("")) {
					addActionError(getText("errors.brokerage.second"));
				} else if (val.percentageValid(bean.getBrokerage()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.brokerage.second1"));
				} else if (val.percentageValid(bean.getBrokerage()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.brokerage.secondgreater"));
				}
				if (val.isNull(bean.getTax()).equalsIgnoreCase("")) {
					addActionError(getText("errors.tax.second"));
				} else if (val.percentageValid(bean.getTax()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.tax.second1"));
				} else if (val.percentageValid(bean.getTax()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.tax.secondless"));
				} else if (val.percentageValid(bean.getTax()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.tax.secondgreater"));
				}
				if (val.isNull(bean.getOthercost()).equalsIgnoreCase("")) {
					addActionError(getText("errors.othercost.second"));
				} else if (val.percentageValid(bean.getOthercost()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.othercost.secondinvalid"));
				} else if (val.percentageValid(bean.getOthercost()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.othercost.secondless"));
				} else if (val.percentageValid(bean.getOthercost()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.othercost.secondgreater"));
				}
				if ((!"4".equalsIgnoreCase(pid)) && (!"5".equalsIgnoreCase(pid))) {
					
					if(StringUtils.isBlank(bean.getAcqBonus())){
						//addActionError(getText("bonus.error.bonus"));
					}
					else{
						if("LCB".equalsIgnoreCase(bean.getAcqBonus())){
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
				if (val.isNull(bean.getAcquisition_Cost()).equalsIgnoreCase("")) {
					addActionError(getText("errors.acquisition_Cost.second"));
				} else {
					bean.setAcquisition_Cost((bean.getAcquisition_Cost()).replaceAll(",", ""));
					if (val.isValidNo(bean.getAcquisition_Cost()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.acquisition_Cost.second1"));
					}
					else{
						String ans = calcu.calculateXOL(bean,"AcqCost",0,sourceId);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getAcquisition_Cost().replaceAll(",",""))){
							addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setAcquisition_Cost(ans);
						}

					}
				}
			}
			if(StringUtils.isBlank(bean.getReinstdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getReinstdetailYN())) {
				if(StringUtils.isBlank(bean.getReInstatementPremium())){
					addActionError(getText("Please Select Reinstatement Premium"));
				}
				else if("Y".equalsIgnoreCase(bean.getReInstatementPremium())){
					if(StringUtils.isBlank(bean.getReinsPopUp())){
	                    addActionError(getText("reins.popup.recheck"));
	                }else{
					int count=service.getReInstatementCount(bean,"");
					if(count<=0){
						addActionError(getText("errors.reinstatement.schedule"));
					}
	                }
				}
				if (val.isNull(bean.getAnualAggregateLiability()).equalsIgnoreCase("")) {
					addActionError(getText("errors.AnualAggregateLiability.number"));
				} else {
					bean.setAnualAggregateLiability((bean.getAnualAggregateLiability()).replaceAll(",", ""));
					if (val.isValidNo(bean.getAnualAggregateLiability()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.AnualAggregateLiability.number.format"));
					}
				}
				
				if (val.isNull(bean.getAnualAggregateDeduct()).equalsIgnoreCase("")) {
					addActionError(getText("errors.AnualAggregateDeduct.number"));
				} else {
					bean.setAnualAggregateDeduct((bean.getAnualAggregateDeduct()).replaceAll(",", ""));
					if (val.isValidNo(bean.getAnualAggregateDeduct()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.AnualAggregateDeduct.number.format"));
					}
				}
			}
			
			if("3".equalsIgnoreCase(pid)){
				validationContract();
				}
			validationRemarks();
			
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}

	private void validateSecondPage() {
		try {
			Validation validation = new Validation();
            
			String limitOurShare = validation.isNull(bean.getLimitOurShare());
			if ("3".equals(pid) || "5".equals(pid)) {
				int instalmentperiod = Integer.parseInt(bean.getM_d_InstalmentNumber());
				double mndPremiumOC=Double.parseDouble(bean.getMd_premium_our_service().replaceAll(",", ""));
				double totalInstPremium=0.0;
				boolean tata = false;
				for (int i = 0; i < instalmentperiod; i++) {
					if (validation.isNull(bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
						addActionError(getText("Error.required.InstalDate",new String[] { String.valueOf(i + 1) }));
					} else if (validation.checkDate(bean.getInstalmentDateList().get(i)).equalsIgnoreCase("Invalid")) {
						addActionError(getText("Error.Error.InstalDate", new String[] { String.valueOf(i + 1)}));
					}
					if (!validation.isNull(bean.getInstalmentDateList().get(0)).equalsIgnoreCase("")) {
						if (validation.ValidateINstallDates(bean.getIncepDate(),bean.getInstalmentDateList().get(0)).equalsIgnoreCase("Invalid")) {
							tata = true;
						}
					}
					if (!validation.isNull(	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
						if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
							addActionError(getText("Error.Select.Expirydate", new String[] { String.valueOf(i + 1)}));
						}
					}
					if (validation.isNull(bean.getInstallmentPremium().get(i))	.equalsIgnoreCase("")) {
						addActionError(getText("Error.required.InstallPremium",new String[]{String.valueOf(i+1)}));
					} else if (validation.isValidNo(bean.getInstallmentPremium().get(i).replaceAll(",", "")).equalsIgnoreCase("Invalid")) {
						addActionError(getText("Error.error.InstallPremium",new String[]{String.valueOf(i+1)}));
					}else{
						try{
			            	totalInstPremium+=Double.parseDouble(bean.getInstallmentPremium().get(i).replaceAll(",", ""));                	
			            }catch (Exception e) {
			            	addActionError(getText("Error.installment.Premium",new String[]{String.valueOf(i+1)}));
						}
					}
					if (i != 0) {
						if (!validation.isNull(	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
							if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i-1),	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("Invalid")) {
								addActionError(getText("Error.required.InstalDate",new String[] { String.valueOf(i + 1)}));
							}
						}
					}
					if(StringUtils.isBlank(bean.getPaymentDueDays().get(i))){
		            	addActionError(getText("Error.payement.due.day",new String[]{String.valueOf(i+1)}));
					}
				}
				BigDecimal bd = new BigDecimal(totalInstPremium).setScale(2, RoundingMode.HALF_EVEN);
				totalInstPremium = bd.doubleValue();
				if((totalInstPremium)!=mndPremiumOC){
					addActionError(getText("Error.total.installment.premium",new String[]{" Deposit Premium - Our Share - OC"}));
			    }
				if (tata == true) {
					addActionError(getText("Error.Select.AfterInceptionDate"));
				}
			}
			if ("3".equals(pid)) {
				String epiAsPerOffer = validation.isNull(bean.getEpiAsPerOffer());
				if (epiAsPerOffer.equalsIgnoreCase("")) {
					addActionError(getText("errors.epiAsPerOffer.second.val"));
				} else {
					if (validation.isValidNo(bean.getEpiAsPerOffer().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.epiAsPerOffer.second1.val"));
					}
				}
				if (validation.isNull(bean.getMd_premium_our_service()).equalsIgnoreCase("")) {
					addActionError(getText("errors.mdpremiumourservice.number.val"));
				} else {
					if (validation.isValidNo(bean.getMd_premium_our_service()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.mdpremiumourservice.number.format"));
					}
				}
				
				
				if (validation.isNull(bean.getAnualAggregateLiability()).equalsIgnoreCase("")) {
					addActionError(getText("errors.AnualAggregateLiability.number"));
				} else {
					bean.setAnualAggregateLiability((bean.getAnualAggregateLiability()).replaceAll(",", ""));
					if (validation.isValidNo(bean.getAnualAggregateLiability()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.AnualAggregateLiability.number.format"));
					}
				}
				
				if (validation.isNull(bean.getAnualAggregateDeduct()).equalsIgnoreCase("")) {
					addActionError(getText("errors.AnualAggregateDeduct.number"));
				} else {
					bean.setAnualAggregateDeduct((bean.getAnualAggregateDeduct()).replaceAll(",", ""));
					if (validation.isValidNo(bean.getAnualAggregateDeduct()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.AnualAggregateDeduct.number.format"));
					}
				}
				if("2".endsWith(bean.getBusinessType()) ||"3".endsWith(bean.getBusinessType()) ||  "7".equalsIgnoreCase(bean.getBusinessType())||  "8".equalsIgnoreCase(bean.getBusinessType())){
				if (validation.isNull(bean.getOcc_limit()).equalsIgnoreCase("")) {
					addActionError(getText("errors.occlimit.number.req"));
				} else {
					bean.setOcc_limit((bean.getOcc_limit()).replaceAll(",", ""));
					if (validation.isValidNo(bean.getOcc_limit()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.occlimit.number"));
					}
				}
				}
				/*if (validation.isNull(bean.getReinstNo()).equalsIgnoreCase("")) {
					addActionError(getText("errors.NoofReinstatment.Required"));
				}
				else {
					bean.setReinstNo((bean.getReinstNo()).replaceAll(",",""));
					if (validation.isValidNo(bean.getReinstNo()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.NoofReinstatment.Invalid"));
					}
				}
				if (validation.isNull(bean.getReinstAdditionalPremium()).equalsIgnoreCase("")) {
					addActionError(getText("errors.ReinstAditionalPremium.number"));
				}
				else {
					bean.setReinstAdditionalPremium((bean.getReinstAdditionalPremium()).replaceAll(",", ""));
					if (validation.isValidNo(bean.getReinstAdditionalPremium()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.ReinstAditionalPremium.number"));
					}
				}*/
			}
			if(StringUtils.isBlank(bean.getReInstatementPremium())){
				addActionError(getText("Please Select Reinstatement Premium"));
			}
			else if("Y".equalsIgnoreCase(bean.getReInstatementPremium())){
				if(StringUtils.isBlank(bean.getReinsPopUp())){
                    addActionError(getText("reins.popup.recheck"));
                }else{
				int count=service.getReInstatementCount(bean,"");
				if(count<=0){
					addActionError(getText("errors.reinstatement.schedule"));
				}
                }
			}
			
			if ((!"4".equalsIgnoreCase(pid)) && (!"5".equalsIgnoreCase(pid))) {
				if (limitOurShare.equalsIgnoreCase("")) {
					addActionError(getText("errors.limitOurShare.second"));	
				} else if (validation.isValidNo(bean.getLimitOurShare().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.limitOurShare.second1"));
				}
				
				if (pid.equalsIgnoreCase("3")) {
					/*if (validation.isNull(bean.getReinstAditionalPremium_percent()).equalsIgnoreCase("")) {
						addActionError(getText("errors.reinsaddprem.null"));
					} else {
						bean.setReinstAditionalPremium_percent((bean.getReinstAditionalPremium_percent()).replaceAll(",", ""));
						if (validation.percentageValid(	bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("INVALID")|| validation.percentageValid(bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("less")|| Double.parseDouble(bean.getReinstAditionalPremium_percent())>200) {
							addActionError(getText("errors.reinsaddprem.invalid"));
						}
					}
					if (validation.isNull(bean.getBurningCost()).equalsIgnoreCase("")) {
						addActionError(getText("errors.burning.required"));
					} else {
						bean.setBurningCost((bean.getBurningCost()).replaceAll(",", ""));
						if (validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("INVALID")|| validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("less")|| validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("greater")) {
							addActionError(getText("errors.burning.invalid"));
						}
					}*/
				}
				
				
				if (validation.isNull(bean.getAcquisition_Cost()).equalsIgnoreCase("")) {
					addActionError(getText("errors.acquisition_Cost.second"));
				} else {
					bean.setAcquisition_Cost((bean.getAcquisition_Cost()).replaceAll(",", ""));
					if (validation.isValidNo(bean.getAcquisition_Cost()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.acquisition_Cost.second1"));
					}
					else{
						String ans = calcu.calculateXOL(bean,"AcqCost",0,sourceId);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getAcquisition_Cost().replaceAll(",",""))){
							addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setAcquisition_Cost(ans);
						}

					}
				}
			
				
				if (validation.isNull(bean.getLeader_Underwriter()).equalsIgnoreCase("")) {
					addActionError(getText("errors.leader_Underwriter.second"));
				}
				if("RI02".equalsIgnoreCase(sourceId) && "3".equalsIgnoreCase(bean.getProduct_id())){
					if(StringUtils.isBlank(bean.getLeader_Underwriter_country())){
						addActionError(getText("errors.leader_Underwriter.second.country"));
					}
				}
				if (validation.isNull(bean.getUnderwriter_Recommendations()).equalsIgnoreCase("")) {
					addActionError(getText("errors.underwriter_Recommendations.second"));
				}
				if (validation.isNull(bean.getLeader_Underwriter_share()).equalsIgnoreCase("")) {
					addActionError(getText("errors.leader_Underwriter_share.second"));
				} else if (validation.percentageValid(bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.leader_Underwriter_share.second1"));
				} else if (validation.percentageValid(bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("less")) {
					addActionError(getText("errors.leader_Underwriter_share.secondless"));
				} else if (validation.percentageValid(bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("greater")) {
					addActionError(getText("errors.leader_Underwriter_share.secondgreater"));
				}
				if(StringUtils.isNotBlank(bean.getLeader_Underwriter_share()) && !"64".equalsIgnoreCase(bean.getLeader_Underwriter())){
					if(service.GetShareValidation(bean)){
					addActionError(getText("errors.leader_Underwriter_share.greater.signed"));
				}
				}else{
					if(dropDownController.GetShareEqualValidation(bean.getProduct_id(),bean.getLeader_Underwriter_share(),bean.getProposal_no())){
						addActionError(getText("errors.leader_Underwriter_share.equals.signed"));
					} 
				}
			}
			if("Y".equalsIgnoreCase(bean.getEndorsementStatus())) {
				bean.setAccDate((new DropDownControllor().getAcceptanceDate(bean.getProposal_no())));
				bean.setMaxDate(Validation.getMaxDateValidate(bean.getAccDate(), bean.getPreviousendoDate()));
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
								addActionError(getText("errors.endoDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
							}
							else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
								addActionError(getText("errors.rectificationDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
							}
							else if("GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
								addActionError(getText("errors.gnpiDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
							}
					}
				if(!validation.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !validation.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !validation.isNull(bean.getEndorsementDate()).equalsIgnoreCase("")){
					if(new DropDownControllor().Validatethree(branchCode, bean.getEndorsementDate())==0){
						addActionError(getText("errors.open.period.date.endo",new String[] {bean1.getOpenPeriodDate()}));
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
			if (pid.equalsIgnoreCase("3")) {
				/*	if (validation.isNull(bean.getReinstAditionalPremium_percent()).equalsIgnoreCase("")) {
					addActionError(getText("errors.reinsaddprem.null"));
				} else {
					bean.setReinstAditionalPremium_percent((bean.getReinstAditionalPremium_percent()).replaceAll(",", ""));
					if (validation.percentageValid(bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("INVALID")	|| validation.percentageValid(bean.getReinstAditionalPremium_percent()).trim().equalsIgnoreCase("less")||Double.parseDouble(bean.getReinstAditionalPremium_percent())>200){
						addActionError(getText("errors.reinsaddprem.invalid"));
					}
				}
				if (validation.isNull(bean.getBurningCost()).equalsIgnoreCase("")) {
					addActionError(getText("errors.burning.required"));
				} else {
					bean.setBurningCost((bean.getBurningCost())	.replaceAll(",", ""));
					if (validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("INVALID")|| validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("less")|| validation.percentageValid(bean.getBurningCost()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.burning.invalid"));
					}
				}*/
			}
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && StringUtils.isBlank(bean.getDocStatus())) {
				addActionError(getText("doc.status"));
			}
			if ("3".equalsIgnoreCase(pid)) {
				final int LoopCount = Integer.parseInt(bean.getNo_Insurer());
				double totPer = 0.0;
				boolean flag = true;
				if (LoopCount != 0) {
					if (validation.isNull(bean.getRetentionPercentage()).equalsIgnoreCase("")) {
						addActionError(getText("Error.RetentionPercentage.Required"));
						flag = false;
					} else if (validation.percentageValid(bean.getRetentionPercentage()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("Error.RetentionPercentage.invalid"));
						flag = false;
					} else if (validation.percentageValid(bean.getRetentionPercentage()).equalsIgnoreCase("greater")) {
						addActionError(getText("Error.RetentionPercentage.greater"));
						flag = false;
					} else {
						totPer += Double.parseDouble(bean.getRetentionPercentage());
					}
				}
				boolean dupCheck = true;
				for (int i = 0; i < LoopCount; i++) {
					if (StringUtils.isBlank(bean.getRetroYear().get(i) )) {
						addActionError(getText("error.uwYear.Required",new String[] { String.valueOf(i + 1) }));
						dupCheck = false;
					}
					if(bean.getRetroCeding() ==null){
						addActionError(getText("Error.CeddingCompany.Required",new String[] { String.valueOf(i + 1) }));
						dupCheck = false;
					}
					if (StringUtils.isBlank(bean.getRetroCeding().get(i))) {
						addActionError(getText("Error.CeddingCompany.Required",new String[] { String.valueOf(i + 1) }));
						dupCheck = false;
					}
					if (validation.isNull(bean.getPercentRetro().get(i)).equalsIgnoreCase("")) {
						addActionError(getText("Error.RetroPercentahge.Required",new String[] { String.valueOf(i + 1) }));
						flag = false;
					} else if (validation.percentageValid(bean.getPercentRetro().get(i)).equalsIgnoreCase("INVALID")) {
						addActionError(getText("Error.RetroPercentahge.invalid",new String[] { String.valueOf(i + 1) }));
						flag = false;
					} else if (validation.percentageValid(bean.getPercentRetro().get(i)).equalsIgnoreCase("greater")) {
						addActionError(getText("Error.RetroPercentahge.greater",new String[] { String.valueOf(i + 1) }));
						flag = false;
					} else {
						totPer += Double.parseDouble(bean.getPercentRetro().get(i));
					}

					RiskDetailsBean bean = new RiskDetailsBean();
					logger.info("ProductCode===>" + pid);
					if ("2".equals(pid)) {
						bean.setProduct_id("4");
						bean.setRetroType("TR");
					} else if ("3".equals(pid)) {
						bean.setProduct_id("4");
						bean.setRetroType("TR");
					}
				}
				if (dupCheck) {
					for (int i = 0; i < LoopCount - 1; i++) {
						for (int j = i + 1; j < LoopCount; j++) {
							if ((bean.getRetroCeding().get(i)).equalsIgnoreCase((bean.getRetroCeding().get(j)))) {
								addActionError(getText("error.RetroContract.Repeat",new String[] { String.valueOf(j + 1 )}));
							}
						}
					}
				}
				if (LoopCount != 0) {
					if (flag) {
						DecimalFormat df = new DecimalFormat("#.##");
						totPer=Double.parseDouble(df.format(totPer));
						if (totPer != 100) {
							addActionError(getText("error.totPercentage.invalid"));
						}
					}
				}
			}
			if("5".equals(pid)){
				int NoRetroCess = Integer.parseInt(bean.getNoRetroCess()== null ? "0" : bean.getNoRetroCess());
				for (int i = 0; i < NoRetroCess; i++) {
					String cedComp = bean.getCedingCompany().get(i) == null ? "0" : bean.getCedingCompany().get(i);
					String broker = bean.getRetroBroker().get(i) == null ? "0" : bean.getRetroBroker().get(i);
					String shAccep = bean.getShareAccepted().get(i)== null ? "" : bean.getShareAccepted().get(i);
					String shSign = bean.getShareSigned().get(i)== null ? "" : bean.getShareSigned().get(i);
					//String comm = bean.getCommission().get(i)== null ? "" : bean.getCommission().get(i);
					String proStatus = bean.getProposalStatus().get(i) == null ? "0" : bean.getProposalStatus().get(i);
					if (StringUtils.isBlank(validation.isSelect(cedComp))) {
						addActionError(getText("errors.reinsurersName.required",	new String[] { String.valueOf(i + 1)}));
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
					/*if (StringUtils.isBlank(comm)) {
						addActionError(getText("errors.commPer.required",	new String[] { String.valueOf(i + 1)}));
					} else if (validation.percentageValid(comm).equalsIgnoreCase("INVALID")	|| validation.percentageValid(comm)	.equalsIgnoreCase("less")|| validation.percentageValid(comm).equalsIgnoreCase("greater")) {
						addActionError(getText("errors.commPer.invalid",new String[] { String.valueOf(i + 1)}));
					}*/

				}
				if (!hasActionErrors()) {
					double totShAcc = 0.0;
					double totShsg = 0.0;
					for (int i = 0; i < NoRetroCess; i++) {

						String cedComp = bean.getCedingCompany().get(i) == null ? "0" : bean.getCedingCompany().get(i);
						String broker = bean.getRetroBroker().get(i) == null ? "0" : bean.getRetroBroker().get(i);
						String shAccep = bean.getShareAccepted().get(i)== null ? "" : bean.getShareAccepted().get(i);
						String shSign = bean.getShareSigned().get(i)== null ? "" : bean.getShareSigned().get(i);
						totShAcc += Double.parseDouble(shAccep);
						totShsg += Double.parseDouble(shSign);
						for (int j = i + 1; j < NoRetroCess; j++) {							
							String cedComp1 = bean.getCedingCompany().get(j) == null ? "0" : bean.getCedingCompany().get(j);
							String broker1 = bean.getRetroBroker().get(j) == null ? "0" : bean.getRetroBroker().get(j);
							String shAccep1 = bean.getShareAccepted().get(j)== null ? "" : bean.getShareAccepted().get(j);
							String shSign1 = bean.getShareSigned().get(j)== null ? "" : bean.getShareSigned().get(j);
							if (cedComp.equals(cedComp1)&& broker.equals(broker1))
								addActionError(getText("errors.cedCompBroker.invalid",	new String[] { String.valueOf(j + 1)}));
							logger.info(i + 1 + " " + j + " " + NoRetroCess	+ " " + totShAcc);
							if (((i + 1) == NoRetroCess) && (j == NoRetroCess)) {
								totShAcc += Double.parseDouble(shAccep1);
								totShsg += Double.parseDouble(shSign1);
							}
						}
					}
					if(StringUtils.isBlank(bean.getRetroDupContract()) &&"3".equalsIgnoreCase(bean.getProduct_id())){
						this.addActionError(getText("errors.dummy.contract",new String[]{bean.getUwYear()}));
					}
					if (totShsg != 100)
						addActionError(getText("errors.shSign.invalid"));
				}
			}
			if ("5".equalsIgnoreCase(pid)) {
				/*if (StringUtils.isBlank(validation.isNull(bean.getReinstNo()))) {
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
				}*/
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
            
			String limitOurShare = validation.isNull(bean.getLimitOurShare());
			if ("3".equals(pid) || "5".equals(pid)) {
				int instalmentperiod = Integer.parseInt(bean.getM_d_InstalmentNumber());
				double mndPremiumOC=Double.parseDouble(bean.getMd_premium_our_service().replaceAll(",", ""));
				double totalInstPremium=0.0;
				boolean tata = false;
				for (int i = 0; i < instalmentperiod; i++) {
					
					if (!validation.isNull(bean.getInstalmentDateList().get(0)).equalsIgnoreCase("")) {
						if (validation.ValidateINstallDates(bean.getIncepDate(),bean.getInstalmentDateList().get(0)).equalsIgnoreCase("Invalid")) {
							tata = true;
						}
					}
					if (!validation.isNull(	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
						if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
							addActionError(getText("Error.Select.Expirydate", new String[] { String.valueOf(i + 1)}));
						}
					}
					if (!validation.isNull(bean.getInstallmentPremium().get(i))	.equalsIgnoreCase("")) {
						try{
			            	totalInstPremium+=Double.parseDouble(bean.getInstallmentPremium().get(i).replaceAll(",", ""));                	
			            }catch (Exception e) {
			            	//addActionError(getText("Error.installment.Premium",new String[]{String.valueOf(i+1)}));
						}
					}
					if (i != 0) {
						if (!validation.isNull(	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("")) {
							if (validation.ValidateTwoDates(bean.getInstalmentDateList().get(i-1),	bean.getInstalmentDateList().get(i)).equalsIgnoreCase("Invalid")) {
								addActionError(getText("Error.required.InstalDate",new String[] { String.valueOf(i + 1)}));
							}
						}
					}
					
				}
				BigDecimal bd = new BigDecimal(totalInstPremium).setScale(2, RoundingMode.HALF_EVEN);
				totalInstPremium = bd.doubleValue();
				if((totalInstPremium)!=mndPremiumOC){
					//addActionError(getText("Error.total.installment.premium",new String[]{" Deposit Premium - Our Share - OC"}));
			    }
				if (tata == true) {
					addActionError(getText("Error.Select.AfterInceptionDate"));
				}
			}
			if ("3".equals(pid)) {
				String epiAsPerOffer = validation.isNull(bean.getEpiAsPerOffer());
				if (epiAsPerOffer.equalsIgnoreCase("")) {
					addActionError(getText("errors.epiAsPerOffer.second.val"));
				} else {
					if (validation.isValidNo(bean.getEpiAsPerOffer().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.epiAsPerOffer.second1.val"));
					}
				}
				if (validation.isNull(bean.getMd_premium_our_service()).equalsIgnoreCase("")) {
					addActionError(getText("errors.mdpremiumourservice.number.val"));
				} else {
					if (validation.isValidNo(bean.getMd_premium_our_service()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.mdpremiumourservice.number.format"));
					}
				}
				
				if(StringUtils.isBlank(bean.getReInstatementPremium())){
					addActionError(getText("errors.reinstatement.blank"));
				}
				else if("Y".equalsIgnoreCase(bean.getReInstatementPremium())){
					if(StringUtils.isBlank(bean.getReinsPopUp())){
	                    addActionError(getText("reins.popup.recheck"));
	                }else{
					int count=service.getReInstatementCount(bean,"");
					if(count<=0){
						addActionError(getText("errors.reinstatement.schedule"));
					}
	                }
				}
				if (!validation.isNull(bean.getAnualAggregateLiability()).equalsIgnoreCase("")) {
					bean.setAnualAggregateLiability((bean.getAnualAggregateLiability()).replaceAll(",", ""));
					
				}
				
				if (!validation.isNull(bean.getAnualAggregateDeduct()).equalsIgnoreCase("")) {
					bean.setAnualAggregateDeduct((bean.getAnualAggregateDeduct()).replaceAll(",", ""));
					
				}
				if("2".endsWith(bean.getBusinessType()) ||"3".endsWith(bean.getBusinessType()) || "7".equalsIgnoreCase(bean.getBusinessType()) ||  "8".equalsIgnoreCase(bean.getBusinessType())){
				if (!validation.isNull(bean.getOcc_limit()).equalsIgnoreCase("")) {
					bean.setOcc_limit((bean.getOcc_limit()).replaceAll(",", ""));
				}
				}
				
			}
			if ((!"4".equalsIgnoreCase(pid)) && (!"5".equalsIgnoreCase(pid))) {
				if (limitOurShare.equalsIgnoreCase("")) {
					addActionError(getText("errors.limitOurShare.second"));	
				} else if (validation.isValidNo(bean.getLimitOurShare().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.limitOurShare.second1"));
				}
			
				if (!validation.isNull(bean.getAcquisition_Cost()).equalsIgnoreCase("")) {
					bean.setAcquisition_Cost((bean.getAcquisition_Cost()).replaceAll(",", ""));
						String ans = calcu.calculateXOL(bean,"AcqCost",0,sourceId);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getAcquisition_Cost().replaceAll(",",""))){
							addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setAcquisition_Cost(ans);
						}
				}
				if("Y".equalsIgnoreCase(bean.getEndorsementStatus())) {
				bean.setAccDate((new DropDownControllor().getAcceptanceDate(bean.getProposal_no())));
				bean.setMaxDate(Validation.getMaxDateValidate(bean.getAccDate(), bean.getPreviousendoDate()));
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
							addActionError(getText("errors.endoDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
						}
						else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
							addActionError(getText("errors.rectificationDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
						}
						else if("GNPI".equalsIgnoreCase(bean.getEndorsmenttype())){
							addActionError(getText("errors.gnpiDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
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
				
			}
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && StringUtils.isBlank(bean.getDocStatus())) {
				addActionError(getText("doc.status"));
			}
			if ("3".equalsIgnoreCase(pid)) {
				final int LoopCount = Integer.parseInt(bean.getNo_Insurer());
				double totPer = 0.0;
				boolean flag = true;
				if (LoopCount != 0) {
					if (validation.isNull(bean.getRetentionPercentage()).equalsIgnoreCase("")) {
						addActionError(getText("Error.RetentionPercentage.Required"));
						flag = false;
					} else if (validation.percentageValid(bean.getRetentionPercentage()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("Error.RetentionPercentage.invalid"));
						flag = false;
					} else if (validation.percentageValid(bean.getRetentionPercentage()).equalsIgnoreCase("greater")) {
						addActionError(getText("Error.RetentionPercentage.greater"));
						flag = false;
					} else {
						totPer += Double.parseDouble(bean.getRetentionPercentage());
					}
				}
				boolean dupCheck = true;
				for (int i = 0; i < LoopCount; i++) {
					if (StringUtils.isBlank(bean.getRetroYear().get(i) )) {
						addActionError(getText("error.uwYear.Required",new String[] { String.valueOf(i + 1) }));
						dupCheck = false;
					}
					if(bean.getRetroCeding() ==null){
						addActionError(getText("Error.CeddingCompany.Required",new String[] { String.valueOf(i + 1) }));
						dupCheck = false;
					}
					if (StringUtils.isBlank(bean.getRetroCeding().get(i))) {
						addActionError(getText("Error.CeddingCompany.Required",new String[] { String.valueOf(i + 1) }));
						dupCheck = false;
					}
					if (validation.isNull(bean.getPercentRetro().get(i)).equalsIgnoreCase("")) {
						addActionError(getText("Error.RetroPercentahge.Required",new String[] { String.valueOf(i + 1) }));
						flag = false;
					} else if (validation.percentageValid(bean.getPercentRetro().get(i)).equalsIgnoreCase("INVALID")) {
						addActionError(getText("Error.RetroPercentahge.invalid",new String[] { String.valueOf(i + 1) }));
						flag = false;
					} else if (validation.percentageValid(bean.getPercentRetro().get(i)).equalsIgnoreCase("greater")) {
						addActionError(getText("Error.RetroPercentahge.greater",new String[] { String.valueOf(i + 1) }));
						flag = false;
					} else {
						totPer += Double.parseDouble(bean.getPercentRetro().get(i));
					}

					RiskDetailsBean bean = new RiskDetailsBean();
					logger.info("ProductCode===>" + pid);
					if ("2".equals(pid)) {
						bean.setProduct_id("4");
						bean.setRetroType("TR");
					} else if ("3".equals(pid)) {
						bean.setProduct_id("4");
						bean.setRetroType("TR");
					}
				}
				if (dupCheck) {
					for (int i = 0; i < LoopCount - 1; i++) {
						for (int j = i + 1; j < LoopCount; j++) {
							if ((bean.getRetroCeding().get(i)).equalsIgnoreCase((bean.getRetroCeding().get(j)))) {
								addActionError(getText("error.RetroContract.Repeat",new String[] { String.valueOf(j + 1 )}));
							}
						}
					}
				}
				if (LoopCount != 0) {
					if (flag) {
						DecimalFormat df = new DecimalFormat("#.##");
						totPer=Double.parseDouble(df.format(totPer));
						if (totPer != 100) {
							addActionError(getText("error.totPercentage.invalid"));
						}
					}
				}
			}
			if("5".equals(pid)){
				int NoRetroCess = Integer.parseInt(bean.getNoRetroCess()== null ? "0" : bean.getNoRetroCess());
				for (int i = 0; i < NoRetroCess; i++) {
					String cedComp = bean.getCedingCompany().get(i) == null ? "0" : bean.getCedingCompany().get(i);
					String broker = bean.getRetroBroker().get(i) == null ? "0" : bean.getRetroBroker().get(i);
					String shAccep = bean.getShareAccepted().get(i)== null ? "" : bean.getShareAccepted().get(i);
					String shSign = bean.getShareSigned().get(i)== null ? "" : bean.getShareSigned().get(i);
					String proStatus = bean.getProposalStatus().get(i) == null ? "0" : bean.getProposalStatus().get(i);
					if (StringUtils.isBlank(validation.isSelect(cedComp))) {
						addActionError(getText("errors.reinsurersName.required",	new String[] { String.valueOf(i + 1)}));
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
				

				}
				if (!hasActionErrors()) {
					double totShAcc = 0.0;
					double totShsg = 0.0;
					for (int i = 0; i < NoRetroCess; i++) {

						String cedComp = bean.getCedingCompany().get(i) == null ? "0" : bean.getCedingCompany().get(i);
						String broker = bean.getRetroBroker().get(i) == null ? "0" : bean.getRetroBroker().get(i);
						String shAccep = bean.getShareAccepted().get(i)== null ? "" : bean.getShareAccepted().get(i);
						String shSign = bean.getShareSigned().get(i)== null ? "" : bean.getShareSigned().get(i);
						totShAcc += Double.parseDouble(shAccep);
						totShsg += Double.parseDouble(shSign);
						for (int j = i + 1; j < NoRetroCess; j++) {							
							String cedComp1 = bean.getCedingCompany().get(j) == null ? "0" : bean.getCedingCompany().get(j);
							String broker1 = bean.getRetroBroker().get(j) == null ? "0" : bean.getRetroBroker().get(j);
							String shAccep1 = bean.getShareAccepted().get(j)== null ? "" : bean.getShareAccepted().get(j);
							String shSign1 = bean.getShareSigned().get(j)== null ? "" : bean.getShareSigned().get(j);
							if (cedComp.equals(cedComp1)&& broker.equals(broker1))
								addActionError(getText("errors.cedCompBroker.invalid",	new String[] { String.valueOf(j + 1)}));
							logger.info(i + 1 + " " + j + " " + NoRetroCess	+ " " + totShAcc);
							if (((i + 1) == NoRetroCess) && (j == NoRetroCess)) {
								totShAcc += Double.parseDouble(shAccep1);
								totShsg += Double.parseDouble(shSign1);
							}
						}
					}
					if (totShsg != 100)
						addActionError(getText("errors.shSign.invalid"));
				}
			}
			if(StringUtils.isBlank(bean.getRetroDupContract())&&"3".equalsIgnoreCase(bean.getProduct_id())){
				this.addActionError(getText("errors.dummy.contract",new String[]{bean.getUwYear()}));
			}
			
			validationRemarks();
		 } catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}
public String removeClassLimit(){
	List<String> coverdepartId = new ArrayList<String>();
	List<String> coverLimitAmount = new ArrayList<String>();
	List<String> deductableLimitAmount = new ArrayList<String>();
	List<String> egni = new ArrayList<String>();
	List<String> gni = new ArrayList<String>();
	List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
	//bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
	bean.setCoverDepartmentList(dropDownController.getCoverDEpartmentDropDown(branchCode,pid,bean.getDepartId()));
	if("delete".equalsIgnoreCase(bean.getMode())){
		bean.getCoverSNo().remove(bean.getDeleteId());
	}
		for(int i=0;i<bean.getCoverSNo().size();i++){
			Map<String,Object> doubleMap = new HashMap<String,Object>();
			 doubleMap.put("one",new Double(1.0));
			 result.add(doubleMap);
			
			}
		 bean.setCoverList(result);
		 bean.setLoopcount(Integer.toString(result.size()));
		 bean.setEgnpiOffer("100");
			int j=1;
			if("delete".equalsIgnoreCase(bean.getMode())){
			for(int k=0;k<bean.getCoverSNo().size();k++){
				int value=Integer.parseInt(bean.getDeleteId());
				if(k<value){
					coverdepartId.add(bean.getCoverdepartId().get(k));
					coverLimitAmount.add(bean.getCoverLimitOC().get(k));
					deductableLimitAmount.add(bean.getDeductableLimitOC().get(k));
					egni.add(bean.getEgnpiAsPerOff().get(k));
					if(bean.getGnpiAsPO()!=null){
					gni.add(bean.getGnpiAsPO().get(k));
					}
				}
				else{
				if(StringUtils.isNotBlank(bean.getCoverdepartId().get(j))){
					coverdepartId.add(bean.getCoverdepartId().get(j));
				}
				if(StringUtils.isNotBlank(bean.getCoverLimitOC().get(j))){
					coverLimitAmount.add(bean.getCoverLimitOC().get(j));
				}
				if(StringUtils.isNotBlank(bean.getDeductableLimitOC().get(j))){
					deductableLimitAmount.add(bean.getDeductableLimitOC().get(j));
				}
				if(StringUtils.isNotBlank(bean.getEgnpiAsPerOff().get(j))){
					egni.add(bean.getEgnpiAsPerOff().get(j));
				}
				if(bean.getGnpiAsPO()!=null){
				if(StringUtils.isNotBlank(bean.getGnpiAsPO().get(j))){
					egni.add(bean.getGnpiAsPO().get(j));
				}
				}
				}
				j++;
			}
			bean.setCoverdepartId(coverdepartId);
			bean.setCoverLimitOC(coverLimitAmount);
			bean.setDeductableLimitOC(deductableLimitAmount);
			bean.setEgnpiAsPerOff(egni);
			if(bean.getGnpiAsPO()!=null){
			bean.setGnpiAsPO(gni);
			}
			}
		return "dropdownajax";
	}
public String removeClassLimitAmount(){
	try {
		List<String> coverdepartId = new ArrayList<String>();

		List<String> coverLimitAmount = new ArrayList<String>();
		List<String> coverLimitPercent = new ArrayList<String>();
		List<String> deductableLimitAmount = new ArrayList<String>();
		List<String> deductableLimitPercent = new ArrayList<String>();
		List<String> egni = new ArrayList<String>();
		List<String> gni = new ArrayList<String>();
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		//bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
		bean.setCoverDepartmentList(dropDownController.getCoverDEpartmentDropDown(branchCode,pid,bean.getDepartId()));
		if("delete".equalsIgnoreCase(bean.getMode())){
			bean.getCoverSNoS().remove(bean.getDeleteId());
		}
			for(int i=0;i<bean.getCoverSNoS().size();i++){
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
				
				}
			 bean.setCoverList(result);
			 bean.setCount(Integer.toString(result.size()));
				int j=1;
				if("delete".equalsIgnoreCase(bean.getMode())){
				for(int k=0;k<bean.getCoverSNoS().size();k++){
					int value=Integer.parseInt(bean.getDeleteId());
					if(k<value){
						coverdepartId.add(bean.getCoverdepartIdS().get(k));
						coverLimitAmount.add(bean.getCoverLimitAmount().get(k));
						coverLimitPercent.add(bean.getCoverLimitPercent().get(k));
						deductableLimitAmount.add(bean.getDeductableLimitAmount().get(k));
						deductableLimitPercent.add(bean.getDeductableLimitPercent().get(k));
						egni.add(bean.getEgnpiAsPerOffSlide().get(k));
						if(bean.getGnpiAsPOSlide()!=null){
						gni.add(bean.getGnpiAsPOSlide().get(k));
						}
					}
					else{
					if(StringUtils.isNotBlank(bean.getCoverdepartIdS().get(j))){
						coverdepartId.add(bean.getCoverdepartIdS().get(j));
					}
					if(StringUtils.isNotBlank(bean.getCoverLimitAmount().get(j))){
						coverLimitAmount.add(bean.getCoverLimitAmount().get(j));
					}
					if(StringUtils.isNotBlank(bean.getCoverLimitPercent().get(j))){
						coverLimitPercent.add(bean.getCoverLimitPercent().get(j));
					}
					if(StringUtils.isNotBlank(bean.getDeductableLimitAmount().get(j))){
						deductableLimitAmount.add(bean.getDeductableLimitAmount().get(j));
					}
					if(StringUtils.isNotBlank(bean.getDeductableLimitPercent().get(j))){
						deductableLimitPercent.add(bean.getDeductableLimitPercent().get(j));
					}
					if(StringUtils.isNotBlank(bean.getEgnpiAsPerOffSlide().get(j))){
						egni.add(bean.getEgnpiAsPerOffSlide().get(j));
					}
					if(bean.getGnpiAsPOSlide()!=null){
					if(StringUtils.isNotBlank(bean.getGnpiAsPOSlide().get(j))){
						gni.add(bean.getGnpiAsPOSlide().get(j));
					}
					}
					}
					j++;
				}
				bean.setCoverdepartIdS(coverdepartId);
				bean.setCoverLimitAmount(coverLimitAmount);
				bean.setCoverLimitPercent(coverLimitPercent);
				bean.setDeductableLimitAmount(deductableLimitAmount);
				bean.setDeductableLimitPercent(deductableLimitPercent);
				bean.setEgnpiAsPerOffSlide(egni);
				if(bean.getGnpiAsPOSlide()!=null){
				bean.setGnpiAsPOSlide(gni);
				}
				}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "dropdownajax";
	}
private void resetCoverLimit() {
	if(StringUtils.isNotBlank(bean.getBusinessType()) && "5".equalsIgnoreCase(bean.getBusinessType())){
		if(bean.getCoverLimitAmount()!=null && bean.getCoverLimitAmount().size()>0){
			List<String> coverdepartId = new ArrayList<String>();
			//subclass//List<String> coversubdepartId = new ArrayList<String>();
			List<String> coverLimitAmount = new ArrayList<String>();
			List<String> coverLimitPercent = new ArrayList<String>();
			List<String> deductableLimitAmount = new ArrayList<String>();
			List<String> deductableLimitPercent = new ArrayList<String>();
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
			//subclass//List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
			for(int i=0;i<bean.getCoverLimitAmount().size();i++){
				coverdepartId.add(bean.getCoverdepartIdS().get(i));
				//subclass//coversubdepartId.add(bean.getCoversubdepartId().get(i));
				coverLimitAmount.add(bean.getCoverLimitAmount().get(i));
				deductableLimitAmount.add(bean.getDeductableLimitAmount().get(i));
				coverLimitPercent.add(bean.getCoverLimitPercent().get(i));
				deductableLimitPercent.add(bean.getDeductableLimitPercent().get(i));
				//subclass//coversubdeptList.add(new DropDownControllor().getSubProfitCentreDropDown(bean.getCoverdepartIdS().get(i),branchCode,pid));
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
			}
			bean.setCoverdepartIdS(coverdepartId);
			//subclass//bean.setCoversubdepartId(coversubdepartId);
			bean.setCoverLimitAmount(coverLimitAmount);
			bean.setCoverLimitPercent(coverLimitPercent);
			bean.setDeductableLimitAmount(deductableLimitAmount);
			bean.setDeductableLimitPercent(deductableLimitPercent);
			//subclass//bean.setCoversubDepartList(coversubdeptList);
			 bean.setCoverList(result);
			 bean.setCount(Integer.toString(result.size()));
		}
	}else{
		if(bean.getCoverLimitOC()!=null && bean.getCoverLimitOC().size()>0){
			List<String> coverdepartId = new ArrayList<String>();
			//subclass//List<String> coversubdepartId = new ArrayList<String>();
			List<String> coverLimitAmount = new ArrayList<String>();
			List<String> deductableLimitAmount = new ArrayList<String>();
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
			//subclass//List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
			for(int i=0;i<bean.getCoverLimitOC().size();i++){
				coverdepartId.add(bean.getCoverdepartId().get(i));
				//subclass//coversubdepartId.add(bean.getCoversubdepartId().get(i));
				coverLimitAmount.add(bean.getCoverLimitOC().get(i));
				deductableLimitAmount.add(bean.getDeductableLimitOC().get(i));
				//subclass//coversubdeptList.add(new DropDownControllor().getSubProfitCentreDropDown(bean.getCoverdepartId().get(i),branchCode,pid));
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
			}
			bean.setCoverdepartId(coverdepartId);
			//subclass//bean.setCoversubdepartId(coversubdepartId);
			bean.setCoverLimitOC(coverLimitAmount);
			bean.setDeductableLimitOC(deductableLimitAmount);
			//subclass//bean.setCoversubDepartList(coversubdeptList);
			 bean.setCoverList(result);
			 bean.setCount(Integer.toString(result.size()));
		}
	}
	
}

	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	
	public String viewPopUp(){
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		bean.setProduct_id(pid);
		bean.setReInstatementDetailsList(service.getReInstatementDetailsList(bean));
		bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
		if(bean.getReInstatementDetailsList().size()<=0){
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			bean.setReInstatementDetailsList(list);
		}
		bean.setAnualAggregateLiability(DropDownControllor.formatter(service.getSumOfCover(bean,pid)));
		bean.setTotalNoOfRows( Integer.toString(bean.getReInstatementDetailsList().size()));
		return "popup";
	}
	
	public String insReinstatementDetails(){
		String forward="streamResult";
		String value ="";
		validationReinstatementDetails();
		
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		bean.setProduct_id(pid);
		bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			if(bean.getErrorList().size()<=0){
				String result = service.insertReinstatementDetails(bean);
				forward="streamResult";
				bean.setAnualAggregateLiability(service.getSumOfCover(bean,pid));
				if(pid.equalsIgnoreCase("3")){
					//value="<script type='text/javascript'>window.opener.xol2.anualAggregateLiability.value='"+bean.getAnualAggregateLiability()+"';window.close();</script>";
				}
				else if(pid.equalsIgnoreCase("5")){
					//value="<script type='text/javascript'>window.opener.retroxol2.anualAggregateLiability.value='"+bean.getAnualAggregateLiability()+"';window.close();</script>";
				}
				value="<script type='text/javascript'>$('#companyModal1').modal('toggle');document.getElementById('referenceNo').value="+bean.getReferenceNo()+"</script>";

				byte[] byteArray = value.getBytes();
				inputStream=new ByteArrayInputStream(byteArray);
			}
			else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error =bean.getErrorList().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				for(int i=0;i<bean.getReinstatementNo().size();i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				}
				 bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
				resetReinsCoverLimit();
				bean.setReInstatementDetailsList(list);
				forward ="popup";
			}
		return forward;
	}
	
	
	private void resetReinsCoverLimit() {
	
			if(bean.getCoverLimitOC()!=null && bean.getCoverLimitOC().size()>0){
				List<String> coverdepartId = new ArrayList<String>();
				List<String> coverLimitAmount = new ArrayList<String>();
				List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
				for(int i=0;i<bean.getCoverLimitOC().size();i++){
					coverdepartId.add(bean.getCoverdepartId().get(i));
					coverLimitAmount.add(bean.getCoverLimitOC().get(i));
					Map<String,Object> doubleMap = new HashMap<String,Object>();
					 doubleMap.put("one",new Double(1.0));
					 result.add(doubleMap);
				}
				bean.setCoverdepartId(coverdepartId);
				bean.setCoverLimitOC(coverLimitAmount);
				 bean.setCoverList(result);
				 bean.setCount(Integer.toString(result.size()));
			}
		
		
	}
	public String viewBonusPopUp(){
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		bean.setProduct_id(pid);
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
	public String insBonusDetails(){
		String forward="streamResult";
		validation();
		if(!hasActionErrors()){
			bean.setBranchCode(branchCode);
			bean.setLoginId(userId);
			bean.setProduct_id(pid);
			bean.setDepartmentId((String) session.get("DepartmentId"));
			String result = service.LowClaimBonusInser(bean);
			String value="<script type='text/javascript'>$('#companyModal2').modal('toggle');</script>";
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
	public String removeRowBonus(){
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
	
	public String removeRow(){
		
		 List<String>  sno = new ArrayList<String>();
         List<String> type = new ArrayList<String>();
         List<String> amount = new ArrayList<String>();
         List<String> minamount = new ArrayList<String>();
         List<String> minTime = new ArrayList<String>();
         List<String> coverOC = new ArrayList<String>();
         bean.getReinstatementNo().remove(bean.getDeleteId());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> coverlist = new ArrayList<Map<String,Object>>();
		for(int i=0;i<bean.getReinstatementNo().size();i++){
		Map<String,Object> string = new HashMap<String,Object>();
		string.put("1","1");
		list.add(string);
		}
		for(int i=0;i<bean.getCoverLimitOC().size();i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			coverlist.add(string);
			double val= Double.parseDouble(bean.getHcoverLimitOC().get(i).replaceAll(",", ""))*Double.parseDouble(bean.getTotalNoOfRows());
			coverOC.add(DropDownControllor.formatter(Double.toString(val)));
			}
		
		int j=1;
		
		for(int k=0;k<bean.getReinstatementNo().size();k++){
			int value=Integer.parseInt(bean.getDeleteId());
			if(k<value){
				//sno.add(bean.getReinstatementNo().get(k));
				type.add(bean.getReinstatementTypeId().get(k));
				amount.add(bean.getReinstatementAmount().get(k));
				if("RI02".equalsIgnoreCase(sourceId)){
					minamount.add(bean.getReinstatementMinAmount().get(k));
					minTime.add(bean.getReinstatementMinTime().get(k));
				}
			}
			else{
			/*if(StringUtils.isNotBlank(bean.getReinstatementNo().get(j))){
				sno.add(bean.getReinstatementNo().get(j));
			}*/
			if(StringUtils.isNotBlank(bean.getReinstatementTypeId().get(j))){
				type.add(bean.getReinstatementTypeId().get(j));	
						}
			if(StringUtils.isNotBlank(bean.getReinstatementAmount().get(j))){
				amount.add(bean.getReinstatementAmount().get(j));
			}
			if("RI02".equalsIgnoreCase(sourceId)){
				if(StringUtils.isNotBlank(bean.getReinstatementMinAmount().get(j))){
					minamount.add(bean.getReinstatementMinAmount().get(j));
							}
				if(StringUtils.isNotBlank(bean.getReinstatementMinTime().get(j))){
					minTime.add(bean.getReinstatementMinTime().get(j));
				}
			}
			}
			j++;
		}
		// bean.setReinstatementNo(sno);
         bean.setReinstatementTypeId(type);
         bean.setReinstatementAmount(amount);
         if("RI02".equalsIgnoreCase(sourceId)){
        	 bean.setReinstatementMinAmount(minamount);
        	 bean.setReinstatementMinTime(minTime);
         }
         bean.setReInstatementDetailsList(list);
         bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
 		bean.setCoverLimitOC(coverOC);
 		bean.setCoverList(coverlist);
        bean.setTotalNoOfRows(String.valueOf(bean.getReInstatementDetailsList().size()));
		return "popup";
	}
	private void validationReinstatementDetails() {
		List<String> list1 = new ArrayList<String>();
		try {
			int j=1;
			Double val=0.00;
			List<String> list=new ArrayList<String>();
				for(int i=0;i<bean.getReinstatementNo().size();i++){
					if(StringUtils.isBlank(bean.getReinstatementTypeId().get(i))){
							list.add(getText("Scale.error.typeId.val",new String[] { String.valueOf(i + 1) }));					
					}
					
					if(StringUtils.isBlank(bean.getReinstatementAmount().get(i))){
						list.add(getText("Scale.error.amount.val",new String[] { String.valueOf(i + 1) }));					
					}
					if(StringUtils.isNotBlank(bean.getReinstatementTypeId().get(i))){
							if(list.size()==2){
								list=new ArrayList<String>();
							}
					}
					if(list.size()!=2){
						for(int k=0;k<list.size();k++){
							list1.add(list.get(k));
							}
							list=new ArrayList<String>();
						}
				j++;
				}
				if(StringUtils.isBlank(bean.getReinstatementOption())){
					list1.add(getText("Scale.error.option.val"));					
				}
				for(int i=0;i<bean.getCoverLimitOCRe().size();i++){
					if(StringUtils.isBlank(bean.getCoverLimitOCRe().get(i))){
						list1.add(getText("error.coverList",new String[] { String.valueOf(i + 1) }));
					}
					if(StringUtils.isNotBlank(bean.getCoverLimitOCRe().get(i))){
					val+=Double.parseDouble(bean.getCoverLimitOCRe().get(i).replace(",", ""));
					}
					if(StringUtils.isNotBlank(bean.getReinstatementOption()) && "S".equals(bean.getReinstatementOption())){
						if(StringUtils.isNotBlank(bean.getCoverLimitOCRe().get(i)) && StringUtils.isNotBlank(bean.getHcoverLimitOC().get(i)) ){
							if(Double.parseDouble(bean.getCoverLimitOCRe().get(i).replace(",", ""))<(Double.parseDouble(bean.getHcoverLimitOC().get(i).replace(",", ""))*Double.parseDouble(bean.getTotalNoOfRows()))){
								String dept=new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), bean.getCoverdepartId().get(i));
								list1.add(getText("error.annual.aggre.less",new String[] {dept, String.valueOf(Double.parseDouble(bean.getHcoverLimitOC().get(i).replace(",", ""))*Double.parseDouble(bean.getTotalNoOfRows()))}));
							}
							if(Double.parseDouble(bean.getCoverLimitOCRe().get(i).replace(",", ""))>(Double.parseDouble(bean.getHcoverLimitOC().get(i).replace(",", ""))*(Double.parseDouble(bean.getTotalNoOfRows())+1))){
								String dept=new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), bean.getCoverdepartId().get(i));
								list1.add(getText("error.annual.aggre.greater",new String[] { dept, String.valueOf(Double.parseDouble(bean.getHcoverLimitOC().get(i).replace(",", ""))*(Double.parseDouble(bean.getTotalNoOfRows())+1))}));
							}
						}
					}
					else{
						if(StringUtils.isNotBlank(bean.getCoverLimitOCRe().get(i)) && StringUtils.isNotBlank(bean.getHcoverLimitOC().get(i)) ){
							if(Double.parseDouble(bean.getCoverLimitOCRe().get(i).replace(",", ""))<(Double.parseDouble(bean.getHcoverLimitOC().get(i).replace(",", "")))){
								String dept=new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), bean.getCoverdepartId().get(i));
								list1.add(getText("error.annual.aggre.less",new String[] {dept, String.valueOf(bean.getHcoverLimitOC().get(i)) }));
							}
						}
					}
				}
				if(StringUtils.isBlank(bean.getAnualAggregateLiability()) || val!=Double.parseDouble(bean.getAnualAggregateLiability().replace(",", ""))){
					list1.add(getText("error.annual.aggre.total"));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
			bean.setErrorList(list1);
	}
private void validateUploadDocument() {

	int emptyCount = 0;
	String emptyRows = "";
	boolean empty = false;
	int size = Integer.parseInt(bean.getEndIndex());
	for (int i = (Integer.parseInt(bean.getStartIndex())); i < Integer.parseInt(bean.getEndIndex()); i++) {
		if (bean.getUpload() == null) {
			this.addActionError(getText("upload.file.required", new String[]{String.valueOf(i + 1)}));
		} else {

			if (bean.getUpload().size() > i) {
				if ("".equals(bean.getDocTypeId().get(i)) && (StringUtils.isNotBlank(bean.getDocDesc().get(i)) || bean.getUpload().get(i).length() > 0)) {
					this.addActionError(getText("upload.docType.required", new String[]{String.valueOf(i + 1)}));
				}
				if (StringUtils.isBlank(bean.getDocDesc().get(i)) && (!"".equals(bean.getDocTypeId().get(i)) || bean.getUpload().get(i).length() > 0)) {
					this.addActionError(getText("upload.docDec.required", new String[]{String.valueOf(i + 1)}));
				}
				if (bean.getUpload().get(i).length() <= 0 && (StringUtils.isNotBlank(bean.getDocDesc().get(i)) || !"0".equals(bean.getDocTypeId().get(i)))) {
					this.addActionError(getText("upload.file.required", new String[]{String.valueOf(i + 1)}));
				}
				if (StringUtils.isBlank(bean.getDocDesc().get(i)) && "0".equals(bean.getDocTypeId().get(i)) && bean.getUpload().get(i).length() <= 0) {
					emptyCount++;
				}
				if (!empty && StringUtils.isBlank(bean.getDocDesc().get(i)) && "0".equals(bean.getDocTypeId().get(i)) && bean.getUpload().get(i).length() <= 0) {
					if (size == Integer.parseInt(bean.getEndIndex()))
						size = i;
					emptyRows += (i + 1) + ",";

				} else if (emptyRows.length() > 0) {
					empty = true;
				}
				if (empty) {
					this.addActionError(getText("upload.emptyRows.Required", new String[]{emptyRows.substring(0, emptyRows.lastIndexOf(","))}));
				}
				if ((Integer.parseInt(bean.getEndIndex()) - Integer.parseInt(bean.getStartIndex())) == emptyCount) {
					this.addActionError(getText("upload.oneRow.Required"));
				}
			} else {
				this.addActionError(getText("upload.file.required", new String[]{String.valueOf(i + 1)}));
			}
		}
	}
}
public String ieModule(){
	bean.setBranchCode(branchCode);
	bean.setInclusionExlist(service.getInclusionExList(bean));
	bean.setMode("list");
	if(bean.getInclusionExlist().size()==0){
		bean.setMode("new");
	bean.setProductlist(dropDownController.getProductieModuleDropDown(branchCode,"","","",""));
	bean.setInwardBusinessTypelist(dropDownController.getInwardBusinessTypeDropDown("24","","","",""));
	bean.setTreatyTypelist(dropDownController.getTreatyTypeDropDown("43","","","",""));
	bean.setProfit_Centerlist(dropDownController.getProfitCentreieModuleDropDown(branchCode,"","","",""));
	bean.setDepartIdlist(dropDownController.getDepartmentieModuleDropDown(branchCode,"","","",""));
	//bean.setSubClasslist(dropDownController.getSubClassDropDown(branchCode,"","","",""));
	}
	return "iemodule";
	
}
public String insertieModule(){
	bean.setBranchCode(branchCode);
	bean.setLoginId(userId);
	ValidationIEModule();
	if(!hasActionErrors()){
	service.insertIEModule(bean);
	bean.setInclusionExlist(service.getInclusionExList(bean));
	if(bean.getInclusionExlist().size()!=0){
		bean.setMode("list");

	}
	}else{
		logger.info("##########Validation Message Start###########");
		Iterator<String> error = getActionErrors().iterator();
		while(error.hasNext()){
			logger.info(error.next());
		}
		logger.info("##########Validation Message End###########");
		bean.setMode("new");
		bean.setProductlist(dropDownController.getProductieModuleDropDown(branchCode,"","","",""));
		bean.setInwardBusinessTypelist(dropDownController.getInwardBusinessTypeDropDown("24","","","",""));
		bean.setTreatyTypelist(dropDownController.getTreatyTypeDropDown("43","","","",""));
		bean.setProfit_Centerlist(dropDownController.getProfitCentreieModuleDropDown(branchCode,"","","",""));
		bean.setDepartIdlist(dropDownController.getDepartmentieModuleDropDown(branchCode,"","","",""));
		//bean.setSubClasslist(dropDownController.getSubClassDropDown(branchCode,"","","",""));
	}
	return "iemodule";
	
}
private void ValidationIEModule() {
	try{
		if(StringUtils.isNotBlank(bean.getExcludeProposalNo())){
		
			String proposal[] = bean.getExcludeProposalNo().split(",");
			String errorProposal ="";
			HashMap<String,String> receiveAdjustAmountMap = dropDownController.getProposalStatus(bean.getExcludeProposalNo());
			for(int i=0;i<proposal.length;i++){
				if(receiveAdjustAmountMap.containsKey(proposal[i])) {
					String status = receiveAdjustAmountMap.get(proposal[i]);
					if("P".equalsIgnoreCase(status.trim())){
						errorProposal += proposal[i]+",";
					
					}
				}else{
					errorProposal += proposal[i]+",";
				}
			}
			if(StringUtils.isNotBlank(errorProposal)){
				errorProposal = errorProposal.substring(0, errorProposal.length() - 1);
				addActionError(getText("error.in.proposal.no",new String[] { String.valueOf(errorProposal) }));
			}
			
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
public String editieModule(){
	//bean.setMode("edit");
	bean.setProductlist(dropDownController.getProductieModuleDropDown(branchCode,bean.getProposalNo(),bean.getTransactionNo(),"I","edit"));
	bean.setProductExlist(dropDownController.getProductieModuleDropDown(branchCode,bean.getProposalNo(),bean.getTransactionNo(),"E","edit"));
	bean.setInwardBusinessTypelist(dropDownController.getInwardBusinessTypeDropDown("24",bean.getProposalNo(),bean.getTransactionNo(),"I","edit"));
	bean.setInwardBusinessTypeExlist(dropDownController.getInwardBusinessTypeDropDown("24",bean.getProposalNo(),bean.getTransactionNo(),"E","edit"));
	bean.setTreatyTypelist(dropDownController.getTreatyTypeDropDown("43",bean.getProposalNo(),bean.getTransactionNo(),"I","edit"));
	bean.setTreatyTypeExlist(dropDownController.getTreatyTypeDropDown("43",bean.getProposalNo(),bean.getTransactionNo(),"E","edit"));
	bean.setProfit_Centerlist(dropDownController.getProfitCentreieModuleDropDown(branchCode,bean.getProposalNo(),bean.getTransactionNo(),"I","edit"));
	bean.setProfit_CenterExlist(dropDownController.getProfitCentreieModuleDropDown(branchCode,bean.getProposalNo(),bean.getTransactionNo(),"E","edit"));
	bean.setDepartIdlist(dropDownController.getDepartmentieModuleDropDown(branchCode,bean.getProposalNo(),bean.getTransactionNo(),"I","edit"));
	bean.setDepartIdExlist(dropDownController.getDepartmentieModuleDropDown(branchCode,bean.getProposalNo(),bean.getTransactionNo(),"E","edit"));
	//bean.setSubClasslist(dropDownController.getSubClassDropDown(branchCode,bean.getProposalNo(),bean.getTransactionNo(),"I","edit"));
	//bean.setSubClassExlist(dropDownController.getSubClassDropDown(branchCode,bean.getProposalNo(),bean.getTransactionNo(),"E","edit"));
	bean.setExcludeProposalNo(dropDownController.getExcludeProposalNo(branchCode,bean.getProposalNo(),bean.getTransactionNo(),"E","edit"));
	return "iemodule";
	
}
public String removeRemarks(){
	try {
		List<String> description = new ArrayList<String>();
		List<String> remark1 = new ArrayList<String>();
		List<String> remark2 = new ArrayList<String>();
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
			bean.getRemarkSNo().remove(bean.getDeleteId());
			for(int i=0;i<bean.getRemarkSNo().size();i++){
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
				
				}
			 bean.setRemarkList(result);
			 bean.setRemarkCount(Integer.toString(result.size()));
				int j=1;
				
				for(int k=0;k<bean.getRemarkSNo().size();k++){
					int value=Integer.parseInt(bean.getDeleteId());
					if(k<value){
						description.add(bean.getDescription().get(k));
						remark1.add(bean.getRemark1().get(k));
						remark2.add(bean.getRemark2().get(k));
					}
					else{
					if(StringUtils.isNotBlank(bean.getDescription().get(j))){
						description.add(bean.getDescription().get(j));
					}
					if(StringUtils.isNotBlank(bean.getRemark1().get(j))){
						remark1.add(bean.getRemark1().get(j));
					}
					if(StringUtils.isNotBlank(bean.getRemark2().get(j))){
						remark2.add(bean.getRemark2().get(j));
					}
					}
					j++;
				}
				bean.setDescription(description);
				bean.setRemark1(remark1);
				bean.setRemark2(remark2);
				bean.setRemarkList(result);
				bean.setRemarkCount(Integer.toString(result.size()));
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "dropdownajax";
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
private void resetIntallment() {
	if(bean.getInstalmentDateList()!=null && bean.getInstalmentDateList().size()>0){
		List<String> instalmentDate = new ArrayList<String>();
		List<String> installmentPremium = new ArrayList<String>();
		List<String> paymentDueDays = new ArrayList<String>();
		List<String> instalList = new ArrayList<String>();
		for(int i=0;i<bean.getInstalmentDateList().size();i++){
			instalmentDate.add(bean.getInstalmentDateList().get(i));
			installmentPremium.add(bean.getInstallmentPremium().get(i));
			paymentDueDays.add(bean.getPaymentDueDays().get(i));
			instalList.add(String.valueOf(i));
		}
			bean.setInstalmentDateList(instalmentDate);
			bean.setInstallmentPremium(installmentPremium);
			bean.setPaymentDueDays(paymentDueDays);
			bean.setInstalList(instalList);
	}else {
		if(StringUtils.isNotBlank(bean.getM_d_InstalmentNumber()) && Integer.parseInt(bean.getM_d_InstalmentNumber())>0){
			List<String> instalList=new ArrayList<String>();
			for(int i=0;i<Integer.parseInt(bean.getM_d_InstalmentNumber());i++){
				instalList.add(String.valueOf(i));
			}
			bean.setInstalList(instalList);
		}
	}
}
public String  removeRowInst() {
	List<String> instalmentDate = new ArrayList<String>();
	List<String> installmentPremium = new ArrayList<String>();
	List<String> paymentDueDays = new ArrayList<String>();
	List<String> instalList = new ArrayList<String>();
	bean.getInstallsno().remove(bean.getDeleteId());
	for(int i=0;i<bean.getInstallsno().size();i++){
		instalList.add("0");
	}
	int j=1;
	
	for(int k=0;k<bean.getInstallsno().size();k++){
		int value=Integer.parseInt(bean.getDeleteId());
		if(k<value){
			instalmentDate.add(bean.getInstalmentDateList().get(k));
			installmentPremium.add(bean.getInstallmentPremium().get(k));
			paymentDueDays.add(bean.getPaymentDueDays().get(k));
		}
		else{
		if(StringUtils.isNotBlank(bean.getInstalmentDateList().get(j))){
			instalmentDate.add(bean.getInstalmentDateList().get(j));
		}
		if(StringUtils.isNotBlank(bean.getInstallmentPremium().get(j))){
			installmentPremium.add(bean.getInstallmentPremium().get(j));	
					}
		if(StringUtils.isNotBlank(bean.getPaymentDueDays().get(j))){
			paymentDueDays.add(bean.getPaymentDueDays().get(j));
		}
		}
		j++;
	}
	bean.setInstalmentDateList(instalmentDate);
	bean.setInstallmentPremium(installmentPremium);
	bean.setPaymentDueDays(paymentDueDays);
	bean.setInstalList(instalList);
	return "dropdownajax";
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

public String getTypeOfBusiness(){
	bean.setBusinessTypelist(dropDownController.getConstantDropDownBusinessType("29",pid,bean.getDepartId()));
	/*if(StringUtils.isNotBlank(bean.getDepartId())&&"16".equalsIgnoreCase(bean.getDepartId())){
	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	Map<String,Object> mapval =new HashMap<String,Object>();
	for(int i=0;i<bean.getBusinessTypelist().size();i++){
		Map<String,Object> map = bean.getBusinessTypelist().get(i);
		String val= map.get("TYPE").toString();
		if(!val.equalsIgnoreCase("2")){
			map.put("DETAIL_NAME",map.get("DETAIL_NAME").toString());
			map.put("TYPE",map.get("TYPE").toString());
			list.add(map);
		}
	}
	bean.setBusinessTypelist(list);
	}*/
	if(StringUtils.isNotBlank(bean.getDepartId())&&"16".equalsIgnoreCase(bean.getDepartId())){
		bean.setBasislist(dropDownController.getConstantDropDownBasis("6",pid,bean.getDepartId()));
	}else{
		bean.setBasislist(dropDownController.getConstantDropDownBasis("6",pid,""));
	}
	return "dropdownajax";
}

public String getCoverDepartment(){
	List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
	bean.setCoverDepartmentList(dropDownController.getCoverDEpartmentDropDown(branchCode,pid,bean.getDepartId()));
	for(int i=0;i<bean.getCoverSNoS().size();i++){
		Map<String,Object> doubleMap = new HashMap<String,Object>();
		 doubleMap.put("one",new Double(1.0));
		 result.add(doubleMap);
		
		}
	 bean.setCoverList(result);
	 bean.setCount(Integer.toString(result.size()));
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
public List<Map<String, Object>> getLayerInfo(){
	return service.getLayerInfo(bean);
}
public String EditLayer(){
	String forward="";
	try {
		
	bean.setBranchCode(branchCode);
	bean.setShortname(service.getShortname(bean));
	bean.setMenuStatus("N");
	
	if("5".equalsIgnoreCase(pid)){
		forward="retroxol1";
	}else{
		forward="xol1";
	}
	//bean.setLayerProposalNo(bean.getProposalNo()); 
	bean.setLayerProposalNo(bean.getProposalNo1());
	bean.setProposal_no(bean.getProposalNo1());
		if(StringUtils.isBlank(bean.getMode())|| "edit".equalsIgnoreCase(bean.getMode())) {
			if(StringUtils.isNotBlank(bean.getContractno())){
				bean.setContNo(bean.getContractno());
			}
			
			final int CheckEditMode = service.getEditMode(bean.getProposal_no());
			if (CheckEditMode == 2) {
				bean.setAmend_Id_Mode("true");
			}
		}
		bean.setProduct_id(pid);
		service.riskEditMode(bean, false);
		bean.setYearList(getYearList());
		if(CollectionUtils.isEmpty(bean.getInstalList())) {
			 List<String> inslist=new ArrayList<String>();
			 inslist.add("");
			 bean.setInstalList(inslist);
		}
		bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
		//RdsCalculation.SecondPageCaculation(bean,pid);
		bean.setAnualAggregateLiability(service.getSumOfCover(bean,pid));
		
		bean.setEdit(bean.getMode());
		service.BaseLayerStatus(bean,pid);
		
		dropDownController.updateEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BR":"BE",bean.getProposal_no());
		if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
			String proposal = dropDownController.getBaseProposal(bean.getProposal_no());
			dropDownController.updateEditMode(proposal,"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SR":"SE",bean.getProposal_no());
			dropDownController.updateSubEditMode(proposal,"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SR":"SE",bean.getProposal_no());
		}
		else{
			dropDownController.updateSubEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BR":"BE",bean.getProposal_no());
		}
	
	ShowDropDown("edit");
	if(StringUtils.isNotBlank(bean.getContNo())){
		bean.setProdisableStatus("Y");
	}
	if("layer".equals(bean.getLayerMode())) {
		bean.setProposal_no(bean.getProposalNo());
	}
	if("copy".equals(bean.getFlag())) {
		bean.setLayerNo("");
		bean.setTreatyName_type("");
		bean.setDepartId("");
		bean.setSubProfit_center("");
	}
	} catch (Exception e) {
		logger.debug("Exception @ {" + e + "}");
		e.printStackTrace();
	}
	return forward;
}
public String layerView() {
	bean.setProduct_id(pid);
	bean.setProposal_no(bean.getProposalNo1());
	service.riskEditMode(bean, false);
	ShowDropDown("edit");
	return "layerview";
	
}
public String CancelProposal(){
	if(!"1".equalsIgnoreCase(pid)){
	bean.setProposal_no(bean.getProposalNo());
	}
	ShowDropDown("edit");
	service.riskEditMode(bean, false);
	service.CancelProposal(bean,bean.getRenewalProposalNo());
	return "xol1";
}
public String savepage() {
	ShowDropDown("edit");
	service.riskEditMode(bean, false);
	if("layer".equals(bean.getLayerMode())) {
		bean.setProposal_no(bean.getProposalNo());
	}
	return "xol1";
}
public String paymentPartnerAjax(){
	bean.setPaymentPartnerlist(dropDownController.getPaymentPartnerlist(branchCode,bean.getCedingId(),bean.getBrokerId()));
return "dropdownajax";
}
}