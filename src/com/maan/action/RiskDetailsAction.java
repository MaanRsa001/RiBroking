package com.maan.action;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;

import com.maan.bean.FaculitivesBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.common.util.CSVConverter;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.StringHelper;
import com.maan.common.util.Validation;
import com.maan.service.CommonCalculation;
import com.maan.service.RdsCalculation;
import com.maan.service.RiskDetailsService;
import com.maan.service.XolService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RiskDetailsAction extends ActionSupport implements ModelDriven<RiskDetailsBean>{

	private static final long serialVersionUID = 1L;
	RiskDetailsBean bean =new RiskDetailsBean();
	FaculitivesBean bean1=new FaculitivesBean();
	DropDownControllor dropDownController=new DropDownControllor();
	RiskDetailsService service =new RiskDetailsService();
	Logger logger = LogUtil.getLogger(this.getClass());
	Map<String,Object> session = ActionContext.getContext().getSession();
	final HttpServletRequest request = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	ServletContext context = request.getSession().getServletContext();
	final String pid = session.get("mfrid") == null ? "" :(String) session.get("mfrid");
	private String userId=session.get("UserId") == null ? "" :(String) session.get("UserId");
	private String processId=session.get("processId") == null ? "" :session.get("processId").toString();
	private String countryId=session.get("countryId") == null ? "" :session.get("countryId").toString();
	private String branchCode=session.get("BRANCH_CODE") == null ? "" :session.get("BRANCH_CODE").toString();
	private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
	private String attachedUW = session.get("ATTACHED_UW") ==null ?"":session.get("ATTACHED_UW").toString();
	CommonCalculation calcu = new CommonCalculation();
	private static final String DROPDOWNAJAX="dropdownajax";
	private InputStream inputStream;
	//private String SAMPLE_FILE=context.getRealPath("/documents/Cresta Folder/Sample/Template.xlsx");
	private String FILE_PATH=context.getRealPath("/documents/Cresta Folder/Upload Files/XLS/");
	private String CSV_PATH=context.getRealPath("/documents/Cresta Folder/Upload Files/CSV/");
	private File crestaupload;
	private String crestauploadFileName;
	private String crestauploadContentType;
	private String fields;
	private  String EXP_FILE=context.getRealPath("/documents/SEC/Export.xlsx");


	public File getCrestaupload() {
		return crestaupload;
	}
	public void setCrestaupload(File crestaupload) {
		this.crestaupload = crestaupload;
	}
	public String getCrestauploadFileName() {
		return crestauploadFileName;
	}
	public void setCrestauploadFileName(String crestauploadFileName) {
		this.crestauploadFileName = crestauploadFileName;
	}
	public String getCrestauploadContentType() {
		return crestauploadContentType;
	}
	public void setCrestauploadContentType(String crestauploadContentType) {
		this.crestauploadContentType = crestauploadContentType;
	}
	public RiskDetailsBean getModel() {
		return bean;
	}
	public String getDisableStatus(){
    	return dropDownController.getDisableStatus(bean.getContNo(),bean.getLayerNo());
    }
	public String getDisableStatus1(){
    	return dropDownController.getDisableStatus1(bean.getContNo(),bean.getLayerNo());
    }
	public List<Map<String,Object>> getEndosTypelist(){
    	return dropDownController.getConstantDropDown("37");
    }
	public String getCeaseaccountStatus(){
    	return dropDownController.getCeaseaccountStatus(bean);
    }
	/*public List<Map<String,Object>>getCrestaIDList(){
		return dropDownController.getCrestaIDList(branchCode);
	}*/
	public List<Map<String,Object>>getCurrencyShortList(){
		return dropDownController.getCurrencyShortList(branchCode,countryId);
	}
	public List<Map<String,Object>>getTerritoryCodelist(){
		return dropDownController.getCountryDropDown(branchCode);
	}
	public List<Map<String,Object>>getCommissionTypeList(){
		return dropDownController.getTypeList("28",branchCode);
	}
	public List<Map<String,Object>> getTerritoryRegionList(){
    	return dropDownController.getTerritoryRegionList(branchCode);
    }
	public List<Map<String,Object>> getUnderwriterList(){
		return dropDownController.getPersonalInfoDropDown(branchCode,"L",pid);
	}
	public List<Map<String,Object>> getProfitCommissionList(){
		return dropDownController.getTypeList("34",branchCode);
	}
	
	public List<Map<String,Object>> getQuotaShareList(){
		return dropDownController.getTypeList("42",branchCode);
	}
	
	public List<Map<String,Object>> getTypeYearList(){
		return dropDownController.getTypeList("35",branchCode);
	}
	public List<Map<String,Object>> getLOCIssuesedList(){
		return dropDownController.getTypeList("39",branchCode);
	}
	public List<Map<String,Object>> getBouquetExistingList(){
		return dropDownController.getBouquetExistingList(branchCode,bean.getBouquetNo(),bean.getBouquetModeYN());
	}
	public List<Map<String,Object>> getSlidingScaleMethodList(){
		return dropDownController.getConstantDropDown("52");
	}
	public List<Map<String,Object>> getSlidingScalePeriodList(){
		return dropDownController.getConstantDropDown("53");
	}
	public List<Map<String,Object>> getLossRationList(){
		return dropDownController.getConstantDropDown("54");
	}
	public String UnderwritingLimit(){
		//bean.setMaxLimit_Product(dropDownController.getUnderWriterLimmit(bean.getUnderwriter(),(String)session.get("processId"), pid, (String)session.get("DepartmentId")));
		bean.setMaxLimit_Product(dropDownController.getUnderWriterLimmit(bean.getUnderwriter(),(String)session.get("processId"), pid, "0"));
	return "dropdownajax";
}
	 public List<Map<String,Object>> getDocType(){
	    	return dropDownController.getDocType(branchCode,pid,"RDS");
	    }
	public  String Init() {
		//new DropDownControllor().insertSessionTrackingdetails(session);
		ShowDropDown();
		try {
			bean.setOurEstimate("100");
			bean.setMenuStatus("N");
			bean.setBaseLayer("");
			bean.setSubProfitList(new ArrayList<Map<String,Object>>());
			bean.setAmendId("0");
			bean.setBranchCode(branchCode);
			bean.setShortname(service.getShortname(bean));
			bean.setOrginalCurrency("1");
			bean.setSectionNo("1");
			bean.setBroker("63");
			if(StringUtils.isNotBlank(bean.getProposal_no())){
			bean.setProposal_no("");
			}
			if(StringUtils.isBlank(bean.getCedRetenType())){
			bean.setCedRetenType("A");
			}
			
			 Map<String,Object> doubleMap = new HashMap<String,Object>();
			 List<Map<String,Object>> remarkList=new ArrayList<Map<String,Object>>();
			 doubleMap.put("one",new Double(1.0));
			 remarkList.add(doubleMap);
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
			 bean.setRetentionYN("N");
			 getCedentRetention();
			 bean.setPaymentPartnerlist(dropDownController.getPaymentPartnerlist(branchCode,bean.getCedingCo(),bean.getBroker()));
			// bean.setNo_Insurer("1");
			//bean.setMaxLimit_Product(dropDownController.getUWLimmit((String)session.get("UserId"),(String)session.get("processId"),pid, "0"));
			if("Y".equals(bean.getBouquetModeYN()) && StringUtils.isNotBlank(bean.getBouquetNo())) {
				dropDownController.getBouquetCedentBrokerInfo(bean);
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return "protreaty1";
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
			yearsList.add(year);*/
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
	public String FirstPageSaveMethod(){
		String forward = "protreaty1";
		try {
			bean.setProduct_id(pid);
			bean.setLoginId(userId);
			bean.setProcessId(session.get("processId").toString());
			bean.setBranchCode(branchCode);
			if(StringUtils.isNotBlank(bean.getDepartId())){
				session.put("DepartmentId",bean.getDepartId());
			}
			bean.setRskCountCheck("Yes");
			//validatenext();
			validateoffer();
			if (!hasActionErrors()) {
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				boolean  SaveFlag=false;
				if(StringUtils.isBlank(bean.getProposal_no())) {
				  SaveFlag = service.insertProportionalTreaty(bean,pid, true, false);
				}else {
				  SaveFlag = service.updateProportionalTreaty(bean,pid);
				}
				if (SaveFlag) {
					service.saveSecondPage(bean, pid);
					if(StringUtils.isBlank(bean.getBaseLayer())){
						dropDownController.updateSubClass(bean.getProposal_no(),"Save");
					}
					if(StringUtils.isNotBlank(bean.getReferenceNo())) {
						dropDownController.updateProposalno(bean);
					}
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
					ShowDropDown();
					EditMode();
					forward="protreaty1";
					}
				} else {
					ShowDropDown();
					addActionError(getText("error.insererror.error"));
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
				ShowDropDown();
				resetRemarks();
				resetCedentRetention();
			}
			if(StringUtils.isNotBlank(bean.getProposalNo()) /*&& ("layer".equals(bean.getLayerMode()) || StringUtils.isNotBlank(bean.getSectionNo()))*/) {
				bean.setProposal_no(bean.getProposalNo());
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return forward;
	}

	private void validateoffer() {
		try {
		
			final Validation val = new Validation();
			final String incDate = val.checkDate(bean.getIncepDate());
			final String expdate = val.checkDate(bean.getExpDate());
			
			Map<String, Object> map = null;
			List<Map<String, Object>> list = service.getValidation(bean);
			if (list != null && list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			}
			
			if(StringUtils.isBlank(bean.getBouquetModeYN())) {
				addActionError(getText("error.bouquetModeYn.required"));
			}
			if (StringUtils.isBlank(bean.getCedingCo())) {
				addActionError(getText("error.cedingCo.required"));
			}
			if (val.isNull(bean.getIncepDate()).equalsIgnoreCase("")) {
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
			if (val.isNull(bean.getExpDate()).equalsIgnoreCase("")) {
				addActionError(getText("error.expDate.required"));
			} else if (expdate.equalsIgnoreCase("INVALID")) {
				addActionError(getText("errors.ExpiryDate.Error"));
			}
			if (!bean.getIncepDate().equalsIgnoreCase("")&& !bean.getExpDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.expDate.check"));
				}
			}
			if (val.isSelect(bean.getUwYear()).equalsIgnoreCase("")) {
				addActionError(getText("error.uwYear.required"));
			}
			if(StringUtils.isBlank(bean.getUwYearTo())) {
				addActionError(getText("error.uwYearto.required"));
			}
			if(StringUtils.isBlank(bean.getSectionNo())) {
				addActionError(getText("error.section.required"));
			}
			if (!val.isNull(bean.getSectionNo()).equalsIgnoreCase("") ) {
				if (service.getSectionDuplicationCheck(bean)) {
					logger.info("// PMD Changes");
					addActionError(getText("error.section.duplicate"));
				}
			}
			if("Y".equals(bean.getBouquetModeYN()) && StringUtils.isNotBlank(bean.getBouquetNo())) {
				if (dropDownController.getBouquetCedentBrokercheck(bean)) {
					addActionError(getText("error.brokercedent.duplicate"));
				}
			}
			if(StringUtils.isBlank(bean.getRiskdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getBrokerdetYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getCoverdetYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getPremiumdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getAcqdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getCommissiondetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getDepositdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			if(StringUtils.isBlank(bean.getLossdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}
			
			
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
	}
	public String check() {
		String Status="";
		String forward="protreaty1";
		bean.setLay(bean.getLay1());
		bean.setContractno(bean.getContractno1());
		bean.setBranchCode(branchCode);
		bean1.setBranchCode(branchCode);
		bean.setProduct_id(pid);
		bean.setLoginId(userId);
		bean.setCountryID(bean.getCountryID());
		bean.setProcessId(processId);
		bean.setProposalNo1(bean.getLayerProposalNo());
		if(StringUtils.isNotBlank(bean.getContractno1())) {
			bean.setContractno(bean.getContractno1());
		}
		if(StringUtils.isNotBlank(bean.getDepartId())){
			session.put("DepartmentId",bean.getDepartId());
		}
		bean.setShortname(service.getShortname(bean));
		new DropDownControllor().getOpenPeriod(bean1);
		if(StringUtils.isNotBlank(bean.getProStatus()) &&"A".equalsIgnoreCase(bean.getProStatus())){
			validatenext();
		}
		else{
			validatesave();
		}
		try {
			if (!hasActionErrors()) {
				session.put("Exchangerate", bean.getExchRate());
				RdsCalculation.SecondPageCaculation(bean,pid);
				bean.setLoginId(userId);
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				final boolean SaveFlag = service.insertProportionalTreaty(bean,pid, false, false);
				if (SaveFlag) {
					if(StringUtils.isBlank(bean.getBaseLayer()) && !hasActionErrors()){
						dropDownController.updateSubClass(bean.getProposal_no(),"Save");
					}
					service.showSecondPageData1(bean.getProposal_no(), bean, pid);
					//bean.setShare_Profit_Commission("2");
					ShowDropDown();
					if(StringUtils.isNotBlank(bean.getBaseLayer())){
						popupEnable(bean);
					}
					if("Renewal".equalsIgnoreCase(bean.getRenewalEditMode())){
						if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
							Status = "SRF";
						}else{
							Status = "BRF";
						}
						dropDownController.updateRenewalEditMode(bean.getProposal_no(),Status,bean.getProposal_no());
					}else{
					dropDownController.updateEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BRF":"BEF",bean.getProposal_no());
					
					if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
						dropDownController.updateEditMode(bean.getBaseLayer(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SRF":"SEF",bean.getProposal_no());
						dropDownController.updateSubEditMode(bean.getBaseLayer(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SRF":"SEF",bean.getProposal_no());
					}
					else{
						dropDownController.updateSubEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BRF":"BEF",bean.getProposal_no());
					}
					}
					if(StringUtils.isNotBlank(bean.getBouquetNo()))
						dropDownController.updateBqEditMode(bean.getBouquetNo(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BR":"BE",bean.getProposal_no()); 
					forward="protreaty2";
				} else {
					ShowDropDown();
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
				//if(StringUtils.isBlank(bean.getProposal_no())){
					//bean.setProdisableStatus("Y");
				//}
				ShowDropDown();
				resetRemarks();
				resetCedentRetention();
				
			}
			
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return forward;
	}

	private void popupEnable(RiskDetailsBean bean2) {
		bean.setProfitCommissionEnable( service.getprofitCommissionEnable(bean,"profit"));
		bean.setCrestaEnable(service.getprofitCommissionEnable(bean,"cresta"));
		bean.setSlideEnable(service.getprofitCommissionEnable(bean,"slide"));
		bean.setLossParEnable(service.getprofitCommissionEnable(bean,"loss"));
		
	}
	public String RiskSecondPage(){
		String forward="";
		String Status ="";
		try {
			bean.setSourceId(sourceId);
			final String ProductId = (String) session.get("Product");
			if(StringUtils.isNotBlank(bean.getContractNo()))
			bean.setPreviousendoDate(new DropDownControllor().getpreamendDate(bean.getContractNo()));
			bean.setBranchCode(branchCode);
			bean1.setBranchCode(branchCode);
			bean.setLoginId(userId);
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			new DropDownControllor().getOpenPeriod(bean1);
			if("A".equalsIgnoreCase(bean.getProStatus())){
				validateSecondPage();
				validationBonusSubmit("");
				}
				else{
					validateSaveSecondPage();
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
				}else if ("P".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Pro");
				}else if ("N".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("NTU");
				}else if ("R".equalsIgnoreCase(bean.getProStatus())) {
					bean.setBackmode("Reje");
				}
				bean.setStatus(bean.getContractGendration());
				if(StringUtils.isBlank(bean.getBaseLayer()) && !hasActionErrors()){
					dropDownController.updateSubClass(bean.getProposal_no(),"Endt");
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
			} else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				if(StringUtils.isNotBlank(bean.getBaseLayer())){
					popupEnable(bean);
				}
				resetRetroList();
				resetRemarks();
				service.showSecondPageData(bean.getProposal_no(), bean, ProductId);
				if(StringUtils.isNotBlank(bean.getEndorsementDate())){
				//bean.setEndorsementDate(dateFormat(bean.getEndorsementDate()));
				}
				forward = "protreaty2";
				if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0){
					List<Integer> docList=new ArrayList<Integer>();
				for(int i=0;i<bean.getDocId().size();i++){
					docList.add(i);
				}
				bean.setDocuList(docList);
			}
			}
			if(bean.getLayerProposalNo()!=null){
				service.showLayerBrokerage(bean.getLayerProposalNo(),bean);
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		return forward;
	}
	public  String ClassEditMode(){
		String forward="protreaty1";
		String editMode ="N";
		String Status="";
		try {
			if( !"edit".equalsIgnoreCase(bean.getMultiuserMode()) && StringUtils.isNotBlank(bean.getProposal_no())){
			editMode = dropDownController.EditModeStatus(bean.getProposal_no(),"0");
		}
		if(!"N".equalsIgnoreCase(editMode)){
			forward ="portfoliList";	
			bean.setMultiuserError("error");
		}
		else{
		if(StringUtils.isNotBlank(bean.getLayerProposalNo())){
			bean.setProposal_no(bean.getLayerProposalNo());
		}
		bean.setBranchCode(branchCode);
		bean.setShortname(service.getShortname(bean));
		final String contractNo=bean.getRenewal_contract_no();
		final String layerNo=bean.getLayerNo();
		bean.setLay1(layerNo);
		bean.setContractno1(contractNo);
		bean.setProposalNo1(bean.getProposal_no());
		bean.setBaseLayer(bean.getProposal_no());
		/*if (session.get("EditProposalNo") != null) {
			bean.setProposal_no(session.get("EditProposalNo").toString());
		}*/
		if(StringUtils.isNotBlank(bean.getLayerProposalNo())){
		bean.setLayerProposalNo(bean.getProposal_no());
		}
		//if("layer".equalsIgnoreCase(layerNo)){
		bean.setRenewalProposalNo(bean.getProposal_no());
			String proposalNo = new DropDownControllor().getCopyQuote("Copy",pid, bean.getBranchCode(), bean.getProposal_no());
			bean.setProposal_no(proposalNo);bean.setProposalReference("Layer");
		//}

		if(StringUtils.isNotBlank(bean.getLayerProposalNo())){
			//session.put("EditProposalNo",bean.getLayerProposalNo());
			bean.setProposal_no(bean.getLayerProposalNo());
		}
			final String pid = (String) session.get("mfrid");
			session.put("Product", pid);
			final int CheckEditMode = service.getEditMode(bean.getProposal_no());
			if (CheckEditMode == 2) {
				bean.setAmend_Id_Mode("true");
			}
			if (bean.getProposal_no().equalsIgnoreCase("")) {
				ShowDropDown();
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
						ShowDropDown();
					} else {
						ShowDropDown();
						addActionError(getText("error.proposal.notAvilable"));
					}
				} else {
					if(StringUtils.isBlank(bean.getDepartId())){
						bean.setSubProfitList(new ArrayList<Map<String,Object>>());
					}else{
						bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
					}
					ShowDropDown();
					addActionError(getText("error.proposal.notAvilable"));
				}
			}
			if(!"layer".equalsIgnoreCase(layerNo)){
			//bean.setProposal_no("");
			}
			bean.setAmendId("0");
			bean.setLayerMode("Yes");
			bean.setCopyQuoteMode("layer");
			//bean.setLayerNo("");
			bean.setYearList(getYearList());
			bean.setYearToList(getYearToList());
			bean.setAmend_Id_Mode("");
			//service.BaseLayerStatus(bean,pid);
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
	private void resetRetroList() {

		if(StringUtils.isNotBlank(bean.getNo_Insurer()) && Integer.parseInt(bean.getNo_Insurer())>0){
			List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
			//List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
			for(int i=0;i<Integer.parseInt(bean.getNo_Insurer());i++){
				RiskDetailsBean tempBean=new RiskDetailsBean();
				//tempBean.setProduct_id(bean.getProduct_id());
				//tempBean.setRetroType(bean.getRetroType());
				tempBean.setProduct_id("4");
				tempBean.setRetroType("TR");
				tempBean.setUwYear(bean.getRetroYear().get(i));
				tempBean.setIncepDate(bean.getIncepDate());
				tempBean.setBranchCode(bean.getBranchCode());
				/*if(0==i){
					retroDupList.add(service.getRetroContractDetailsList(tempBean, 3));
					retroFinalList.add(new ArrayList<Map<String,Object>>());
				}else{*/
				retroFinalList.add(service.getRetroContractDetailsList(tempBean,2,bean.getRetroYear().get(i)));
				//}
			}
			//bean.setRetroDupList(retroDupList);
			bean.setRetroFinalList(retroFinalList);
			RiskDetailsBean tempBean=new RiskDetailsBean();
			tempBean.setProduct_id("4");
			tempBean.setRetroType("TR");
			tempBean.setIncepDate(bean.getIncepDate());
			tempBean.setBranchCode(bean.getBranchCode());
			bean.setProduct_id(pid);
			bean.setRetroUwyear(service.getRetroContractDetailsList(tempBean,1,""));
		}
	}
	public String SecondPageSave(){
		String forward="";
		try {
			final String ProductId = (String) session.get("Product");
			bean.setBranchCode(branchCode);
			bean.setLoginId(userId);
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			if("A".equalsIgnoreCase(bean.getProStatus())){
			validateSecondPage();
			validationBonusSubmit("");
			}
			else{
				validateSaveSecondPage();
			}
			
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
				if(StringUtils.isBlank(bean.getBaseLayer()) && !hasActionErrors()){
					dropDownController.updateSubClass(bean.getProposal_no(),"Endt");
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
				bean.setProposal_no(bean.getProposal_no());
				resetRetroList();
				service.showSecondPageData(bean.getProposal_no(), bean, ProductId);
				//bean.getM_d_InstalmentNumber();
				resetRemarks();
				forward = "protreaty2";
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return forward;
	}

	private void validateSaveSecondPage() {
		try {
			Validation validation = new Validation();

			if("3".equalsIgnoreCase(bean.getTreatyType()) ||"1".equalsIgnoreCase(bean.getTreatyType())  ||"4".equalsIgnoreCase(bean.getTreatyType()) ||"5".equalsIgnoreCase(bean.getTreatyType())){
			if (StringUtils.isNotBlank(bean.getPremiumQuotaShare()) ){
				bean.setPremiumQuotaShare((bean.getPremiumQuotaShare()).replaceAll(",", ""));
				if (validation.isValidNo(bean.getPremiumQuotaShare()).equalsIgnoreCase("INVALID")) {
					if("4".equalsIgnoreCase(bean.getTreatyType()) ||"5".equalsIgnoreCase(bean.getTreatyType())){
						addActionError(getText("Errors.PremiumQuotaShare.Obj.Invalid"));
					}
					else{
					addActionError(getText("Errors.PremiumQuotaShare.Invalid"));
					}
				}
			}
			}
			if("3".equalsIgnoreCase(bean.getTreatyType()) ){

			if (StringUtils.isBlank(bean.getPremiumSurplus())) {
				addActionError(getText("Errors.PremiumSurplus.reqired"));
			} else {
				bean.setPremiumSurplus((bean.getPremiumSurplus()).replaceAll(",", ""));
				logger.info("======>" + bean.getPremiumSurplus());
				if (validation.isValidNo(bean.getPremiumSurplus()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("Errors.PremiumSurplus.Invalid"));
				}
				String ans = calcu.calculatePTTY(bean,"Surplus",0);
				if(Double.parseDouble(ans)!=Double.parseDouble(bean.getPremiumSurplus().replaceAll(",",""))){
					addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.setPremiumSurplus(ans);
				}
			}
			}

			if("2".equalsIgnoreCase(bean.getInwardType())){
				if(StringUtils.isBlank(bean.getOrginalacqcost())){
					addActionError(getText("Errors.org.cost.Invalid"));
				}
				else if("Y".equalsIgnoreCase(bean.getOrginalacqcost())){
				if(StringUtils.isNotBlank(bean.getOurassessmentorginalacqcost())){
					bean.setOurassessmentorginalacqcost(bean.getOurassessmentorginalacqcost().replaceAll(",",""));
				}
				if(StringUtils.isNotBlank(bean.getOuracqCost())){
					bean.setOuracqCost(bean.getOuracqCost().replaceAll(",",""));
				}
				}
			}
			if(StringUtils.isBlank(bean.getLocRate())){
				addActionError(getText("label.rate.year.error"));
			}
			if("Y".equalsIgnoreCase(bean.getEndorsementStatus())) {
			bean.setAccDate((new DropDownControllor().getAcceptanceDate(bean.getProposal_no())));
			if(StringUtils.isNotBlank(bean.getAccDate()) && StringUtils.isNotBlank( bean.getPreviousendoDate())) {
				bean.setMaxDate(Validation.getMaxDateValidate(bean.getAccDate(), bean.getPreviousendoDate()));
			}
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
				//addActionError(getText("errors.endoDate.invalid"));
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
			if(!validation.isNull(bean1.getOpstartDate()).equalsIgnoreCase("") && !validation.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !validation.isNull(bean.getEndorsementDate()).equalsIgnoreCase("")){
				if(new DropDownControllor().Validatethree(branchCode, bean.getEndorsementDate())==0){
					addActionError(getText("errors.open.period.date.endo",new String[] {bean1.getOpenPeriodDate()}));
				}
			}
			}
			/*if (validation.isNull(bean.getAcquisition_Cost())
					.equalsIgnoreCase("")) {
				addActionError(getText("errors.acquisition_Cost.second"));
			} else {
				bean.setAcquisition_Cost((bean.getAcquisition_Cost()).replaceAll(",", ""));
				if (validation.isValidNo(bean.getAcquisition_Cost()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.acquisition_Cost.second"));
				}else{
					String ans = calcu.calculatePTTY(bean,"AcqCost",0);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getAcquisition_Cost().replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.setAcquisition_Cost(ans);
					}
				}
			}*/
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
				if ("".equals(bean.getRetroYear().get(i) )) {
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
				bean.setProduct_id("4");
				bean.setRetroType("TR");
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
			if(bean.getCrestaStatus().equalsIgnoreCase("Y")){
				if(StringUtils.isBlank(bean.getCrestaPopUp())){
					addActionError(getText("cresta.popup.check"));
				}
				
				else if(service.getCrestaCount(bean)==0){
					addActionError(getText("error.creasta.invalid"));
				}
			}

			if(StringUtils.isBlank(bean.getSlideScaleCommission())){
				addActionError(getText("error.slidescale.commission"));
				}
				else if("Y".equalsIgnoreCase(bean.getSlideScaleCommission())){
					if(StringUtils.isBlank(bean.getSlidePopUp())){
						addActionError(getText("error.slide.recheck"));
					}else{
					int count = service.getBonusListCount(bean,"scale");
					if(count<=0){
						addActionError(getText("slide.error.lcb.table.empty"));
					}
					}
				}
			if(StringUtils.isBlank(bean.getBaseLayer())){
				if(StringUtils.isBlank(bean.getSlidecommissionSubClass())){
					addActionError(getText("slide.profit.commission.sub.class"));
				}
			}
				if(StringUtils.isBlank(bean.getLossParticipants())){
					addActionError(getText("error.losspart"));
					}
					else if("Y".equalsIgnoreCase(bean.getLossParticipants())){
						if(StringUtils.isBlank(bean.getLossPopUp())){
							addActionError(getText("error.loss.recheck"));
						}else{
						int count = service.getBonusListCount(bean,"lossparticipates");
						if(count<=0){
							addActionError(getText("losspart.error.lcb.table.empty"));
						}
						}
					}
				if(StringUtils.isBlank(bean.getBaseLayer())){
					if(StringUtils.isBlank(bean.getLosscommissionSubClass())){
						addActionError(getText("loss.profit.commission.sub.class"));
					}
				}
				if(StringUtils.isBlank(bean.getShare_Profit_Commission())){
					addActionError(getText("errors.share_Profit_Commission.empty"));
				}
				if(StringUtils.isBlank(bean.getBaseLayer())){
				if(StringUtils.isBlank(bean.getCommissionSubClass())){
					addActionError(getText("profit.commission.sub.class"));
				}
				}

				
			if (StringUtils.isNotBlank(bean.getLoss_Advise())) {
				
				bean.setLoss_Advise((bean.getLoss_Advise()).replaceAll(",", ""));
			}
			if(StringUtils.isNotBlank(bean.getCash_Loss_Limit())){
				bean.setCash_Loss_Limit((bean.getCash_Loss_Limit()).replaceAll(",", ""));
				if(StringUtils.isNotBlank(bean.getLimitOrigCur()) && StringUtils.isBlank(bean.getTreatyLimitsurplusOC())){
				if (validation.isValidNo(bean.getCash_Loss_Limit()).trim().equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.cash_Loss_Limit.invalid"));
				} else if (!validation.isValidNo(bean.getLimitOrigCur()).trim().equalsIgnoreCase("INVALID")	&& (Double.parseDouble(bean.getCash_Loss_Limit()) > Double.parseDouble(bean.getLimitOrigCur())))
					addActionError(getText("errors.cashLimitGrTreatyLimit"));
				}
				else if(StringUtils.isBlank(bean.getLimitOrigCur()) && StringUtils.isNotBlank(bean.getTreatyLimitsurplusOC())){
					if (validation.isValidNo(bean.getCash_Loss_Limit()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.cash_Loss_Limit.invalid"));
					} else if (!validation.isValidNo(bean.getTreatyLimitsurplusOC()).trim().equalsIgnoreCase("INVALID")	&& (Double.parseDouble(bean.getCash_Loss_Limit()) > Double.parseDouble(bean.getTreatyLimitsurplusOC())))
						addActionError(getText("errors.cashLimitGrTreatyLimit"));
					}
				else if(StringUtils.isNotBlank(bean.getLimitOrigCur()) && StringUtils.isNotBlank(bean.getTreatyLimitsurplusOC())){
					int t=Double.compare(Double.parseDouble(bean.getLimitOrigCur()), Double.parseDouble(bean.getTreatyLimitsurplusOC()));
					if (validation.isValidNo(bean.getCash_Loss_Limit()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.cash_Loss_Limit.invalid"));
					} else if (!(validation.isValidNo(bean.getLimitOrigCur()).trim().equalsIgnoreCase("INVALID")||validation.isValidNo(bean.getTreatyLimitsurplusOC()).trim().equalsIgnoreCase("INVALID"))	&& (Double.parseDouble(bean.getCash_Loss_Limit()) > (Double.parseDouble(t>0?bean.getLimitOrigCur():bean.getTreatyLimitsurplusOC()))))
						addActionError(getText("errors.cashLimitGrTreatyLimit"));
					}
			}
			if(StringUtils.isNotBlank(bean.getEvent_limit())){
				bean.setEvent_limit(bean.getEvent_limit().replaceAll(",", ""));
				
			}
			if(StringUtils.isNotBlank(bean.getAggregate_Limit())){
				bean.setAggregate_Limit(bean.getAggregate_Limit().replaceAll(",", ""));
			}
			if(StringUtils.isNotBlank(bean.getOccurrent_Limit())){
			
				bean.setOccurrent_Limit(bean.getOccurrent_Limit().replaceAll(",", ""));
			}
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && StringUtils.isBlank(bean.getDocStatus())) {
				addActionError(getText("doc.status"));
			}
			if(StringUtils.isBlank(bean.getRetroDupContract())){
				this.addActionError(getText("errors.dummy.contract",new String[]{bean.getUwYear()}));
			}
			validationRemarks();
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		
	}
	public String AmednIdInsert(){
		String forward = "protreaty1";
		try {
			bean.setAmend_Id_Mode("true");
			bean.setLoginId(userId);
			bean.setProcessId(processId);
			bean.setContNo(bean.getContractNo());
			if(StringUtils.isNotBlank(bean.getContractNo()) && StringUtils.isBlank(bean.getContNo())){
				//session.put("EditContractNo",bean.getContractNo());
				bean.setContNo(bean.getContractNo());
			}
			/*if(StringUtils.isBlank(bean.getContractNo())){
				bean.setContNo(session.get("EditContractNo").toString());
			}*/
			bean.setBranchCode(branchCode);
			bean1.setBranchCode(branchCode);
			if(StringUtils.isNotBlank(bean.getDepartId())){
				session.put("DepartmentId",bean.getDepartId());
			}
			new DropDownControllor().getOpenPeriod(bean1);
			if(StringUtils.isNotBlank(bean.getProStatus()) &&"A".equalsIgnoreCase(bean.getProStatus())){
				validatenext();
			}
			else{
				validatesave();
			}
			if (!hasActionErrors()) {
				bean.setAmend_Id_Mode("true");
				RdsCalculation.SecondPageCaculation(bean,pid);
				bean.setAmend_Id_Mode("true");
				bean.setLoginId(userId);
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				//final boolean SaveFlag = service.insertProportionalTreaty(bean,pid, false, true);
				final boolean SaveFlag = service.insertProportionalTreaty(bean,pid, false, false);
				session.put("Exchangerate", bean.getExchRate());
				if (SaveFlag) {
					if(StringUtils.isBlank(bean.getBaseLayer()) && !hasActionErrors()){
						dropDownController.updateSubClass(bean.getProposal_no(),"Save");
					}
					session.put("Exchangerate", bean.getExchRate());
					bean.setAmend_Id_Mode("true");
					bean.setProposal_no(bean.getProposal_no());
					service.showSecondPageData(bean.getProposal_no(), bean, pid);
					service.showRetroContracts(bean,pid,false);
					logger.info("No. Of Ins==>"+bean.getNo_Insurer());
					
					forward="protreaty2";
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
				ShowDropDown();
				session.put("Exchangerate", bean.getExchRate());
				bean.setAmend_Id_Mode("true");
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		return forward;
	}

	public String FirstPageUpdateMode(){
		String forward = "protreaty1";
		String Status ="";
		try {
			//bean.setRequest(request);
			final String pid = session.get("Product").toString();
			bean.setBranchCode(branchCode);
			bean.setMenuStatus("N");
			if(StringUtils.isNotBlank(bean.getContractNo())){
				bean.setContNo(bean.getContractNo());
			}
			bean.setShortname(service.getShortname(bean));
			if(StringUtils.isNotBlank(bean.getDepartId())){
				session.put("DepartmentId",bean.getDepartId());
			}
			final int CheckEditMode = service.getEditMode(bean.getProposal_no());
			if (CheckEditMode == 2) {
				bean.setAmend_Id_Mode("true");
			}
			bean.setLoginId(userId);
			bean1.setBranchCode(branchCode);
			bean.setProcessId(session.get("processId").toString());
			new DropDownControllor().getOpenPeriod(bean1);
			if(StringUtils.isNotBlank(bean.getProStatus()) &&"A".equalsIgnoreCase(bean.getProStatus())){
				validatenext();
			}
			else{
				validatesave();
			}
			if (!hasActionErrors()) {
				RdsCalculation.SecondPageCaculation(bean,pid);
				final boolean SaveFlag = service.updateProportionalTreaty(bean,pid);
				if(SaveFlag){
					if(StringUtils.isBlank(bean.getBaseLayer()) && !hasActionErrors()){
						dropDownController.updateSubClass(bean.getProposal_no(),"Save");
					}
					session.put("Exchangerate", bean.getExchRate());
					bean.setEditMode("EditMode");
					service.showSecondPageData(bean.getProposal_no(), bean, pid);
					service.showRetroContracts(bean,pid,false);

					RdsCalculation.SecondPageCaculation(bean,pid);
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
						
						forward ="protreaty2";
					}
				}else{
					bean.setProposalNo1(bean.getProposal_no());
					ShowDropDown();
					addActionError(getText("error.InsertError.NotExist"));
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
				ShowDropDown();
				resetRemarks();
				resetCedentRetention();
			}
			if(bean.getLayerProposalNo()!=null){
				service.showLayerBrokerage(bean.getLayerProposalNo(),bean)	;
			}
			if(StringUtils.isNotBlank(bean.getBaseLayer())){
				bean.setProfitCommissionEnable( service.getprofitCommissionEnable(bean,"profit"));
				bean.setCrestaEnable(service.getprofitCommissionEnable(bean,"cresta"));
				bean.setSlideEnable(service.getprofitCommissionEnable(bean,"slide"));
				bean.setLossParEnable(service.getprofitCommissionEnable(bean,"loss"));
			}
			
			if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0){
				List<Integer> docList=new ArrayList<Integer>();
			for(int i=0;i<1;i++){
				docList.add(i);
			}
			bean.setStartIndex("0");
			bean.setEndIndex("1");
			bean.setDocuList(docList);
			ShowDropDown();
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return forward;
	}
	
	public String EditMode(){
		String forward ="protreaty1";
		String Status ="";
		String editMode ="N";
		try {
			if( !"edit".equalsIgnoreCase(bean.getMultiuserMode())){
			editMode = dropDownController.EditModeStatus(bean.getProposal_no(),"0");
		}
		if(!"N".equalsIgnoreCase(editMode)){
			forward ="portfoliList";	
			bean.setMultiuserError("error");
		}
		else{
		if(StringUtils.isNotBlank(bean.getBaseLayer())){
			bean.setProposalNo1(bean.getProposal_no());
		}
		ShowDropDown();
		bean.setBranchCode(branchCode);
		bean.setShortname(service.getShortname(bean));
			bean.setProposalNo1(bean.getProposal_no());
			bean.setMenuStatus("N");
			if(StringUtils.isNotBlank(bean.getDepartmentId())){
				session.put("DepartmentId",bean.getDepartmentId());
			}
			if(StringUtils.isBlank(bean.getMode())|| "edit".equalsIgnoreCase(bean.getMode())){
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
			else if("Renewal".equalsIgnoreCase(bean.getReMode())){
				bean.setRenewalProposalNo(bean.getProposal_no());
				bean.setProposal_no(new DropDownControllor().getRenewalCopyQuote("Renewal",pid, branchCode,bean.getProposal_no() ));
				bean.setRenewalMode("RenewalMode");
				bean.setAmend_Id_Mode("");
				bean.setMode("");
				bean.setProposalReference("Renewal");
			}
			bean.setProduct_id(pid);
			service.riskEditMode(bean, false);
			if("Renewal".equalsIgnoreCase(bean.getReMode()) && (StringUtils.isNotBlank(bean.getBaseLayer()) && !hasActionErrors())){
				dropDownController.updateSubClass(bean.getProposal_no(),"Renewal");
			}
			//service.riskEditMode(bean, false);
			bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
			RdsCalculation.SecondPageCaculation(bean,pid);
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
			if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
				String proposal = dropDownController.getBaseProposal(bean.getProposal_no());
				dropDownController.updateEditMode(proposal,"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SR":"SE",bean.getProposal_no());
				dropDownController.updateSubEditMode(proposal,"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SR":"SE",bean.getProposal_no());
			}
			else{
				dropDownController.updateSubEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BR":"BE",bean.getProposal_no());
			}
			if(StringUtils.isNotBlank(bean.getBouquetNo()))
				dropDownController.updateBqEditMode(bean.getBouquetNo(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BR":"BE",bean.getProposal_no()); 
			if(bean.getRetList()==null){
				getCedentRetention();
			}
		}
		bean.setYearList(getYearList());
		bean.setYearToList(getYearToList());
		bean.setPaymentPartnerlist(dropDownController.getPaymentPartnerlist(branchCode,bean.getCedingCo(),bean.getBroker()));
		if(StringUtils.isNotBlank(bean.getContractListVal())){
			getContractListVal();
		}
		if(StringUtils.isNotBlank(bean.getContNo())){
			bean.setProdisableStatus("Y");
		}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		return forward;
	}

	public String ViewMethod(){
		String forward="";
		boolean ViewFlag=false;
		boolean CheckAvilable=false;
		try {
			if (StringUtils.isBlank(bean.getProposal_no())) {
				bean.setActionMessage((getText("error.proposalNoView.required")));
				bean.setFlag("true");
				forward="redirectAction";
			} else {

				if (StringUtils.isBlank(bean.getProduct_id())) {
					 bean.setProduct_id(pid);
				}
				 CheckAvilable = service.checkAvialability(bean, bean.getProduct_id());
				if (CheckAvilable) {
					bean.setBranchCode(branchCode);
					bean.setShortname(service.getShortname(bean));
					/*String sumInsured = "";
					String surplus ="";
					List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
					if(StringUtils.isNotBlank(bean.getCedingCo())&&StringUtils.isNotBlank(bean.getIncepDate())&&StringUtils.isNotBlank(bean.getExpDate())&&StringUtils.isNotBlank(bean.getUwYear())&&StringUtils.isNotBlank(bean.getOrginalCurrency())&&StringUtils.isNotBlank(bean.getDepartmentId())&&StringUtils.isNotBlank(bean.getTreatyType())&&StringUtils.isNotBlank(bean.getProfit_Center())){
						if(StringUtils.isNotBlank(bean.getType())&&("4".equalsIgnoreCase(bean.getType()) || "5".equalsIgnoreCase(bean.getType()))){
								sumInsured =StringUtils.isEmpty(bean.getFaclimitOrigCur())? "0":bean.getFaclimitOrigCur().replaceAll(",", "");
							}
						else if(StringUtils.isNotBlank(bean.getType())&&"3".equalsIgnoreCase(bean.getType())){
								sumInsured =Double.toString(Double.parseDouble(StringUtils.isEmpty(bean.getLimitOrigCur())? "0":bean.getLimitOrigCur().replaceAll(",", "")));
								surplus = 	Double.toString(Double.parseDouble(StringUtils.isEmpty(bean.getTreatyLimitsurplusOC())? "0":bean.getTreatyLimitsurplusOC().replaceAll(",", ""))) ;
							}	
						else if(StringUtils.isNotBlank(bean.getType())&&"1".equalsIgnoreCase(bean.getType())){
							sumInsured = StringUtils.isEmpty(bean.getLimitOrigCur())? "0":bean.getLimitOrigCur().replaceAll(",", "");
						}
						else if(StringUtils.isNotBlank(bean.getType())&&"2".equalsIgnoreCase(bean.getType())){
							surplus = StringUtils.isEmpty(bean.getTreatyLimitsurplusOC())? "0":bean.getTreatyLimitsurplusOC().replaceAll(",", "");
						}
						bean.setContractTypelist(dropDownController.getContractValidation(pid,bean.getCedingCo(),bean.getIncepDate(),bean.getExpDate(),bean.getUwYear(),bean.getOrginalCurrency(),bean.getDepartId(),bean.getTreatyType(),sumInsured,StringUtils.isEmpty(bean.getContNo())? "0":bean.getContNo(),bean.getProfit_Center(),surplus));
					}
					else if(bean.getContractTypelist()==null){
						bean.setContractTypelist(list);
					}*/
					 ViewFlag = service.riskEditMode(bean, false);
							 //service.viewRiskDetails(bean, bean.getProduct_id());

					if (ViewFlag) {
						forward="ProView";
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
			ShowDropDown();
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return forward;
	}

	public String submitSecondPageEndorsement() {
		String forward = "";
		try{
			if("Y".equalsIgnoreCase(bean.getEndorsementStatus())) {
				forward = RiskSecondPage();
			}
			else {
				forward = SecondPageSave();
				dropDownController.riskDetailsEndorsement(bean.getProposal_no(),bean.getEndorsementStatus());
			}
		}
		catch(Exception exception) {

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

	public String getExcRate(){
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

		}
		return DROPDOWNAJAX;
	}

	public String ajaxValue(){
		if("country".equalsIgnoreCase(bean.getDropDown())){
			bean.setUnderwriterCountryList(dropDownController.getUnderwriterCountryList(bean,branchCode));

		}else if("presubclass".equals(bean.getDropDown())){
			bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
		}
		else{
		bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
		}
		return DROPDOWNAJAX;

	}

	private void validatenext() {
		try {
			double amt = 0.0;
			boolean flags = true;
			boolean cedCheck = true;
			boolean cedflag = true;
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
			final String ourEst = val.isNull(bean.getOurEstimate());
			final String epiPercent = val.isNull(bean.getEpi_origCur());
			final String Epi = val.isNull(bean.getEpi());
			final String xlCost = val.isNull(bean.getXlCost());
			final String cenRent = val.isNull(bean.getCedReten());
			final String proStatus = val.isSelect(bean.getProStatus());
			final String shareWrit = val.isNull(bean.getShareWritt());
			final String shareSign = val.isNull(bean.getSharSign());
			final String orginalCurrency = val.isSelect(bean.getOrginalCurrency());
			final String maxLimit_Product = val.isNull(bean.getMaxLimit_Product());
			
			Map<String, Object> map = null;
			List<Map<String, Object>> list = service.getValidation(bean);
			if (list != null && list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			}
			
			if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0){
				if(StringUtils.isBlank(bean.getEndorsmenttype())){
					addActionError(getText("end.type.error"));
				}
			}
			if(StringUtils.isBlank(bean.getBouquetModeYN())) {
				addActionError(getText("error.bouquetModeYn.required"));
			}
			if (StringUtils.isBlank(bean.getCedingCo())) {
				addActionError(getText("error.cedingCo.required"));
			}
			if (val.isNull(bean.getIncepDate()).equalsIgnoreCase("")) {
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
			if (val.isNull(bean.getExpDate()).equalsIgnoreCase("")) {
				addActionError(getText("error.expDate.required"));
			} else if (expdate.equalsIgnoreCase("INVALID")) {
				addActionError(getText("errors.ExpiryDate.Error"));
			}
			if (!bean.getIncepDate().equalsIgnoreCase("")&& !bean.getExpDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.expDate.check"));
				}
			}
			if (val.isSelect(bean.getUwYear()).equalsIgnoreCase("")) {
				addActionError(getText("error.uwYear.required"));
			}
			if(StringUtils.isBlank(bean.getUwYearTo())) {
				addActionError(getText("error.uwYearto.required"));
			}
			if(StringUtils.isBlank(bean.getRiskdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getRiskdetailYN())) {
				if (orginalCurrency.equalsIgnoreCase("")) {
					addActionError(getText("error.orginalCurrency.required"));
				}
				if (exchRate.equalsIgnoreCase("")) {
					addActionError(getText("error.exchRate.required"));
					cedCheck = false;
				} else if (val.isValidNo(exchRate.trim().toString()).equalsIgnoreCase("invalid")) {
					addActionError(getText("error.exchRate.check"));
					cedCheck = false;
				}
				if (tear_nt.equalsIgnoreCase("")) {
					addActionError(getText("error.treatyName_type.required"));
				}
				if (bean.getTreatyType().equalsIgnoreCase("0")) {
					addActionError(getText("error.TreatyType.Reqired"));
				}
				if(bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("1") ){
					if (limitPercent.equalsIgnoreCase("")) {
						addActionError(getText("error.limitOrigCurr.required"));
						cedCheck = false;
					} else {
						bean.setLimitOrigCur((bean.getLimitOrigCur()).replaceAll(",", ""));
						if (val.isValidNo(bean.getLimitOrigCur()).equalsIgnoreCase("invalid")) {
							addActionError(getText("error.limitOrigCurr.check"));
							cedCheck = false;
						} else {
							amt = Double.parseDouble(bean.getLimitOrigCur());
						}
					}
					}
				if(bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("2") ){
					if(StringUtils.isBlank(bean.getTreatynoofLine())){
						addActionError(getText("error.noonline.required"));
					}
				}
				if(bean.getTreatyType().equalsIgnoreCase("4") || bean.getTreatyType().equalsIgnoreCase("5") ){
					if(StringUtils.isBlank(bean.getFaclimitOrigCur())){
						addActionError(getText("error.fac.limit.currency"));
					}
					else {
						bean.setFaclimitOrigCur((bean.getFaclimitOrigCur()).replaceAll(",", ""));
						amt = Double.parseDouble(bean.getFaclimitOrigCur());
					}
				}
				if(bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("2")){
					if(StringUtils.isBlank(bean.getTreatyLimitsurplusOC())){
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
				if (val.isSelect(bean.getDepartId()).equalsIgnoreCase("")) {
					addActionError(getText("error.departId.required"));
				}
				if (val.isSelect(bean.getSubProfit_center()).equalsIgnoreCase("")) {
					addActionError(getText("error.subProfit_center.required"));
				}else{
					bean.setSubProfit_center((bean.getSubProfit_center()).replaceAll(" ", ""));
				}
				if (val.isSelect(bean.getProfit_Center()).equalsIgnoreCase("")) {
					addActionError(getText("error.Profit_Center.required"));
				}
				/*if (val.isNull(bean.getCedRetenType()).equalsIgnoreCase("")) {
					addActionError(getText("error.cedRentType.required"));
					cedflag = false;
				} else {
					if (cenRent.equalsIgnoreCase("")) {
						addActionError(getText("error.cedRent.required"));
						cedflag = false;
					} else {
						bean.setCedReten((bean.getCedReten()).replaceAll(",", ""));
						if ("A".equalsIgnoreCase(bean.getCedRetenType())) {
							cedflag = false;
							if (val.isValidNo(bean.getCedReten()).trim().equalsIgnoreCase("Invalid")) {
								addActionError(getText("error.cedRentAmt.required"));
							}
						} else if ("P".equalsIgnoreCase(bean.getCedRetenType())) {
							if (val.percentageValid(bean.getCedReten()).trim().equalsIgnoreCase("Invalid")|| val.percentageValid(bean.getCedReten().trim()).equalsIgnoreCase("less")|| val.percentageValid(bean.getCedReten().trim()).equalsIgnoreCase("greater")) {
								addActionError(getText("error.cedRentPer.required"));
								cedflag = false;
							}
						}
					}
				}*/
				if (bean.getProposalType().equalsIgnoreCase("0")) {
					addActionError(getText("error.cleancutoff.required"));
				}else if("R".equalsIgnoreCase(bean.getProposalType()) || "H".equalsIgnoreCase(bean.getProposalType())){
					if(StringUtils.isBlank(bean.getRunoffYear())){
						addActionError(getText("error.runoff.required"));	
					}
				}
			}
			if(StringUtils.isBlank(bean.getBrokerdetYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getBrokerdetYN())) {
				if (StringUtils.isBlank(bean.getBroker())) {
					addActionError(getText("error.broker.required"));
				}
				if (StringUtils.isBlank(bean.getPaymentPartner())) {
					addActionError(getText("error.paymentpartner.required"));
				}
				if (val.isNull(bean.getLeader_Underwriter())
						.equalsIgnoreCase("")) {
					addActionError(getText("errors.leader_Underwriter.second"));
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
					if (val.percentageValid(	bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.leader_Underwriter_share.second"));
					} else if (val.percentageValid(bean.getLeader_Underwriter_share()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.leader_Underwriter_share.greater"));
					}
				}/*if(StringUtils.isNotBlank(bean.getLeader_Underwriter()) &&    !"64".equalsIgnoreCase(bean.getLeader_Underwriter())){
					 if(service.GetShareValidation(bean)){
						addActionError(getText("errors.leader_Underwriter_share.greater.signed"));
					} 
				}else{
					if(dropDownController.GetShareEqualValidation(bean.getProduct_id(),bean.getLeader_Underwriter_share(),bean.getProposal_no())){
						addActionError(getText("errors.leader_Underwriter_share.equals.signed"));
					}
				}*/
			}
			if(StringUtils.isBlank(bean.getCoverdetYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getCoverdetYN())) {
				if (StringUtils.isBlank(bean.getTerritoryscope())) {
					addActionError(getText("error.terrtoryScope.required"));
				}
				if (StringUtils.isBlank(bean.getRiskCovered())) {
					addActionError(getText("error.portfolio.Reqired"));
				}
				if(StringUtils.isBlank(bean.getPnoc())){
					addActionError(getText("error.pnoc.required"));
				}
				else if (bean.getPnoc().equalsIgnoreCase("-1")) {
					addActionError(getText("error.pnoc.required"));
				}
			}
			if(StringUtils.isBlank(bean.getPremiumdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getPremiumdetailYN())) {
				if (StringUtils.isBlank(Epi)) {
					addActionError(getText("error.epi.required"));
				} else {
					bean.setEpi((bean.getEpi()).replaceAll(",", ""));
					if (val.isValidNo(bean.getEpi().trim()).equalsIgnoreCase("Invalid")) {
						addActionError(getText("error.epi.invalid"));
					}
					/*String ans = calcu.calculatePTTY(bean,"EPI",0);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getEpi().replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.setEpi(ans);
					}*/
				}
				if("3".equalsIgnoreCase(bean.getTreatyType()) ||"1".equalsIgnoreCase(bean.getTreatyType())  ||"4".equalsIgnoreCase(bean.getTreatyType()) ||"5".equalsIgnoreCase(bean.getTreatyType())){
					if (StringUtils.isNotBlank(bean.getPremiumQuotaShare()) ){
						bean.setPremiumQuotaShare((bean.getPremiumQuotaShare()).replaceAll(",", ""));
						if (val.isValidNo(bean.getPremiumQuotaShare()).equalsIgnoreCase("INVALID")) {
							if("4".equalsIgnoreCase(bean.getTreatyType()) ||"5".equalsIgnoreCase(bean.getTreatyType())){
								addActionError(getText("Errors.PremiumQuotaShare.Obj.Invalid"));
							}
							else{
							addActionError(getText("Errors.PremiumQuotaShare.Invalid"));
							}
						}
					}
				}
				if("3".equalsIgnoreCase(bean.getTreatyType()) ){
					if (StringUtils.isBlank(bean.getPremiumSurplus())) {
						addActionError(getText("Errors.PremiumSurplus.reqired"));
					} else {
						bean.setPremiumSurplus((bean.getPremiumSurplus()).replaceAll(",", ""));
						logger.info("======>" + bean.getPremiumSurplus());
						if (val.isValidNo(bean.getPremiumSurplus()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("Errors.PremiumSurplus.Invalid"));
						}
						String ans = calcu.calculatePTTY(bean,"Surplus",0);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getPremiumSurplus().replaceAll(",",""))){
							addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setPremiumSurplus(ans);
						}
					}
				}
				if (bean.getAccountingPeriod().equalsIgnoreCase("0")) {
					addActionError(getText("error.AccountionPeriod.reqired"));
				}
				if (bean.getReceiptofStatements().equalsIgnoreCase("")) {
					addActionError(getText("Error.ResciptStatments.Required"));
				} else if (val.isValidNo(bean.getReceiptofStatements()).equalsIgnoreCase("invalid")) {
					addActionError(getText("Error.ReceiptofStatmenst.Error"));
				}
				if (bean.getReceiptofPayment().equalsIgnoreCase("")) {
					addActionError(getText("error.ReceiptOfStatments.required"));
				} else if (val.isValidNo(bean.getReceiptofPayment()).equalsIgnoreCase("invalid")) {
					addActionError(getText("error.ReceiptOfStatments.Error"));
				}
			}
			if(StringUtils.isBlank(bean.getAcqdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getAcqdetailYN())) {
				if("3".equalsIgnoreCase(bean.getCommissionType()) ||"1".equalsIgnoreCase(bean.getCommissionType())  ||"4".equalsIgnoreCase(bean.getCommissionType()) ||"5".equalsIgnoreCase(bean.getCommissionType())){
					if(StringUtils.isBlank(bean.getCommissionQ_S())){
						if("4".equalsIgnoreCase(bean.getCommissionType()) ||"5".equalsIgnoreCase(bean.getCommissionType())){
							addActionError(getText("errors.commissionQ_S.Obj.second"));
						}
						else{
							addActionError(getText("errors.commissionQ_S.second"));
						}
					} else {
						if (val.percentageValid(bean.getCommissionQ_S().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("errors.commissionQ_S.second1"));
						} else if (val.percentageValid(bean.getCommissionQ_S().trim()).equalsIgnoreCase("less")) {
							addActionError(getText("errors.commissionQ_S.second1less"));
						} else if (val.percentageValid(bean.getCommissionQ_S().trim()).equalsIgnoreCase("greater")) {
							addActionError(getText("errors.commissionQ_S.second1greater"));
						}
					}
				}
				if("3".equalsIgnoreCase(bean.getCommissionType()) ||"2".equalsIgnoreCase(bean.getCommissionType()) ){
					if(StringUtils.isBlank(bean	.getCommission_surp())){
						addActionError(getText("errors.commission_surp.second.req"));
					} else {
						if (val.percentageValid(bean.getCommission_surp().trim()).equalsIgnoreCase("INVALID")) {
							addActionError(getText("errors.commission_surp.second"));
						} else if (val.percentageValid(bean.getCommission_surp().trim()).equalsIgnoreCase("less")) {
							addActionError(getText("errors.commission_surp.secondless"));
						}else if (val.percentageValid(bean.getCommission_surp().trim()).equalsIgnoreCase("greater")) {
							addActionError(getText("errors.commission_surp.secondgreater"));
						}
					/*if(StringUtils.isBlank(bean.getCommission_surpAmt())){
						addActionError(getText("error.comm.surplus.amount"));
					}
					else{
						bean.setCommission_surpAmt(validation.isNull(bean.getCommission_surpAmt()).replaceAll(",",""));
					}*/
					}
				}
				if(StringUtils.isBlank(bean.getOverRidder())){
					addActionError(getText("errors.overRidder.second.req"));
				} else {
					if (val.percentageValid(bean.getOverRidder().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.overRidder.second1"));
					} else if (val.percentageValid(bean.getOverRidder().trim()).equalsIgnoreCase("less")) {
						addActionError(getText("errors.overRidder.secondless"));
					} else if (val.percentageValid(bean.getOverRidder().trim()).equalsIgnoreCase("greater")) {
						addActionError(getText("errors.overRidder.secondgreater"));
					}
				}
				if (StringUtils.isNotBlank(bean.getBroker())) {
					if (!bean.getBroker().equalsIgnoreCase("Direct")) {
						if (val.isNull(bean.getBrokerage()).equalsIgnoreCase("")) {
							addActionError(getText("errors.brokerage.second"));
						} else {
							if (val.percentageValid(bean.getBrokerage()).trim().equalsIgnoreCase("INVALID")) {
								addActionError(getText("errors.brokerage.second1"));
							}

							else if (val.percentageValid(bean.getBrokerage()).trim().equalsIgnoreCase("less")) {
								addActionError(getText("errors.brokerage.secondless"));
							}

							else if (val.percentageValid(bean.getBrokerage()).trim().equalsIgnoreCase("greater")) {
								addActionError(getText("errors.brokerage.secondgreater"));
							}
						}
					}
				}
				if (val.isNull(bean.getTax()).equalsIgnoreCase("")) {
					addActionError(getText("errors.tax.second"));
				} else if (val.percentageValid(bean.getTax()).trim()
						.equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.tax.second1"));
				} else if (val.percentageValid(bean.getTax()).trim()
						.equalsIgnoreCase("less")) {
					addActionError(getText("errors.tax.secondless"));
				} else if (val.percentageValid(bean.getTax()).trim()
						.equalsIgnoreCase("greater")) {
					addActionError(getText("errors.tax.secondgreater"));
				}
				if (val.isNull(bean.getOthercost()).equalsIgnoreCase("")) {
					addActionError(getText("errors.othercost.second"));
				} else if (val.percentageValid(bean.getOthercost()).trim().equalsIgnoreCase(
						"INVALID")) {
					addActionError(getText("errors.othercost.secondinvalid"));
				} else if (val.percentageValid(bean.getOthercost()).trim().equalsIgnoreCase(
						"less")) {
					addActionError(getText("errors.othercost.secondless"));
				} else if (val.percentageValid(bean.getOthercost()).trim().equalsIgnoreCase(
						"greater")) {
					addActionError(getText("errors.othercost.secondgreater"));
				}
				/*if (val.isNull(bean.getAcquisition_Cost()).equalsIgnoreCase("")) {
					addActionError(getText("errors.acquisition_Cost.second"));
				} else {
					bean.setAcquisition_Cost((bean.getAcquisition_Cost()).replaceAll(",", ""));
					if (val.isValidNo(bean.getAcquisition_Cost()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.acquisition_Cost.second1"));
					}else{
						String ans = calcu.calculatePTTY(bean,"AcqCost",0);
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getAcquisition_Cost().replaceAll(",",""))){
							addActionError(getText("error.calcul.mistake"));
							logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setAcquisition_Cost(ans);
						}
					}
				}*/
			}
			if(StringUtils.isBlank(bean.getCommissiondetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getCommissiondetailYN())) {
				if(StringUtils.isBlank(bean.getSlideScaleCommission())){
					addActionError(getText("error.slidescale.commission"));
				}
				else if("Y".equalsIgnoreCase(bean.getSlideScaleCommission())){
					if(StringUtils.isBlank(bean.getSlidePopUp())){
						addActionError(getText("error.slide.recheck"));
					}else{
					int count = service.getBonusListCount(bean,"scale");
					if(count<=0){
						addActionError(getText("slide.error.lcb.table.empty"));
					}
					}
				}
				if(StringUtils.isBlank(bean.getLossParticipants())){
					addActionError(getText("error.losspart"));
				}
				else if("Y".equalsIgnoreCase(bean.getLossParticipants())){
					if(StringUtils.isBlank(bean.getLossPopUp())){
						addActionError(getText("error.loss.recheck"));
					}else{
					int count = service.getBonusListCount(bean,"lossparticipates");
					if(count<=0){
						addActionError(getText("losspart.error.lcb.table.empty"));
					}
					}
				}
				if(StringUtils.isBlank(bean.getShare_Profit_Commission())){
					addActionError(getText("error.profit.commision"));
				}
				else if("Y".equalsIgnoreCase(bean.getShare_Profit_Commission())){
					
				}
				if("1".equalsIgnoreCase(bean.getShare_Profit_Commission())){
					if(StringUtils.isBlank(bean.getManagementExpenses())){
						addActionError(getText("man.exp.req"));
					}
					if(StringUtils.isBlank(bean.getCommissionType())){
						addActionError(getText("com.type.req"));
										}
					else if("PC".equalsIgnoreCase(bean.getCommissionType())){
					if(StringUtils.isBlank(bean.getProfitCommissionPer())){
						//addActionError(getText("pro.com.per.req"));
					}
					else if (Double.parseDouble(bean.getProfitCommissionPer())>100){
						addActionError(getText("profit.com.less.hundred"));
					}
					if(StringUtils.isBlank(bean.getSuperProfitCommission())){
						addActionError(getText("error.super.pro.com"));
					}else{
						if("Y".equalsIgnoreCase(bean.getSuperProfitCommission())){
							
							 if(StringUtils.isBlank(bean.getProfitPopUp())){
							 //addActionError(getText("error.profit.recheck")); 
							 }
							 else{
								 int count = service.CommissionTypeCount(bean); 
							 if(count<=0){
							  addActionError(getText("error.commission.schedule"));
							  } 
							 }
							 
						}
					}
					}
					else if("PR".equalsIgnoreCase(bean.getCommissionType()) || "LR".equalsIgnoreCase(bean.getCommissionType()) ){
					if(StringUtils.isBlank(bean.getSetup())){
						addActionError(getText("error.setup.req"));
					}
					if(StringUtils.isBlank(bean.getProfitPopUp())){
						addActionError(getText("error.profit.recheck"));
					}else{
					int count = service.CommissionTypeCount(bean);
					if(count<=0){
						addActionError(getText("error.commission.setup.schedule"));
					}
					}
					}

					if(StringUtils.isBlank(bean.getLossCarried())){
						//addActionError(getText("loss.carried.req"));
					}else if(!"TE".equalsIgnoreCase(bean.getLossCarried())){
						if(StringUtils.isBlank(bean.getLossyear())){
							addActionError(getText("error.loss.year"));
						}else if(Integer.parseInt(bean.getLossyear())>100){
							addActionError(getText("loss.carried.yeas.less.hundred"));
						}
					}
					if(StringUtils.isBlank(bean.getProfitCarried())){
						//addActionError(getText("error.profit.carried"));
					}
					else if(!"TE".equalsIgnoreCase(bean.getProfitCarried())){
						if(StringUtils.isBlank(bean.getProfitCarriedForYear())){
							addActionError(getText("profit.carried.year.req"));
						}else if(Integer.parseInt(bean.getProfitCarriedForYear())>100){
							addActionError(getText("profit.carried.yeas.less.hundred"));
						}
					}
					if(StringUtils.isBlank(bean.getFistpc())){
						//addActionError(getText("req.first.profit.comm"));
					}if(StringUtils.isBlank(bean.getProfitMont())){
						//addActionError(getText("error.profit.month"));
					}if(StringUtils.isBlank(bean.getSubpc())){
						//addActionError(getText("error.sub.profit.com"));
					}if(StringUtils.isBlank(bean.getSubProfitMonth())){
						//addActionError(getText("error.sub.profit.month"));
					}if(StringUtils.isBlank(bean.getSubSeqCalculation())){
						addActionError(getText("error.sub.seq.cal.req"));
					}if(StringUtils.isBlank(bean.getProfitCommission())){
						addActionError(getText("error.profit.commission.req"));
					}
				}
				
			}
			if(StringUtils.isBlank(bean.getDepositdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getDepositdetailYN())) {
				if(StringUtils.isBlank(bean.getPremium_Reserve())){
					addActionError(getText("errors.premium_Reserve.second"));
				} else {
					if (val.percentageValid(bean.getPremium_Reserve()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.premium_Reserve.second1"));
					} else if (val.percentageValid(bean.getPremium_Reserve()).trim().equalsIgnoreCase("less")) {
						addActionError(getText("errors.premium_Reserve.secondless"));
					} else if (val.percentageValid(bean.getPremium_Reserve()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.premium_Reserve.secondgreater"));
					}
				}
				if(StringUtils.isBlank(bean.getLoss_reserve())){
					addActionError(getText("errors.loss_reserve.second"));
				} else {
					if (val.percentageValid(bean.getLoss_reserve()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.loss_reserve.second1"));
					}else if (val.percentageValid(bean.getLoss_reserve()).trim().equalsIgnoreCase("less")) {
						addActionError(getText("errors.loss_reserve.secondless"));
					} else if (val.percentageValid(bean.getLoss_reserve()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.loss_reserve.secondgreater"));
					}
				}
				if(StringUtils.isBlank(bean.getInterest())){
					addActionError(getText("errors.interest.second"));
				} else {
					if (val.percentageValid(bean.getInterest()).trim().equalsIgnoreCase("INVALID")){
						addActionError(getText("errors.interest.second1"));
					} else if (val.percentageValid(bean.getInterest()).trim().equalsIgnoreCase("less")) {
						addActionError(getText("errors.interest.secondless"));
					} else if (val.percentageValid(bean.getInterest()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.interest.secondgreater"));
					}
				}
				if(StringUtils.isBlank(bean.getPortfolio_inout_Loss())){
					addActionError(getText("errors.portfolio_inout_Loss.second"));
				} else {

					if (val.percentageValid(bean.getPortfolio_inout_Loss()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.portfolio_inout_Loss.second1"));
					} else if (val.percentageValid(bean.getPortfolio_inout_Loss()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.portfolio_inout_Loss.secondgreater"));
					}
				}
				if(StringUtils.isBlank(bean.getPortfolio_inout_Premium())){
					addActionError(getText("errors.portfolio_inout_Premium.second"));
				} else {
					if (val.percentageValid(bean.getPortfolio_inout_Premium()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.portfolio_inout_Premium.second"));
					} else if (val.percentageValid(bean.getPortfolio_inout_Premium()).trim().equalsIgnoreCase("greater")) {
						addActionError(getText("errors.portfolio_inout_Premium.greater"));
					}
				}
			}
			if(StringUtils.isBlank(bean.getLossdetailYN())) {
				addActionError(getText("error.alldetails.required"));
			}else if("Y".equals(bean.getLossdetailYN())) {
				if (StringUtils.isBlank(bean.getLoss_Advise())) {
					addActionError(getText("errors.loss_Advise.second"));
				} else if (val.isValidNo(bean.getLoss_Advise().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.loss_Advise.second1"));
				}
				else{
					bean.setLoss_Advise((bean.getLoss_Advise()).replaceAll(",", ""));
				}
				if(StringUtils.isBlank(bean.getCash_Loss_Limit())){

					addActionError(getText("errors.cash_Loss_Limit.second"));
				} else {
					bean.setCash_Loss_Limit((bean.getCash_Loss_Limit()).replaceAll(",", ""));
					if(StringUtils.isNotBlank(bean.getLimitOrigCur()) && StringUtils.isBlank(bean.getTreatyLimitsurplusOC())){
					if (val.isValidNo(bean.getCash_Loss_Limit()).trim().equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.cash_Loss_Limit.invalid"));
					} else if (!val.isValidNo(bean.getLimitOrigCur()).trim().equalsIgnoreCase("INVALID")	&& (Double.parseDouble(bean.getCash_Loss_Limit()) > Double.parseDouble(bean.getLimitOrigCur())))
						addActionError(getText("errors.cashLimitGrTreatyLimit"));
					}
					else if(StringUtils.isBlank(bean.getLimitOrigCur()) && StringUtils.isNotBlank(bean.getTreatyLimitsurplusOC())){
						if (val.isValidNo(bean.getCash_Loss_Limit()).trim().equalsIgnoreCase("INVALID")) {
							addActionError(getText("errors.cash_Loss_Limit.invalid"));
						} else if (!val.isValidNo(bean.getTreatyLimitsurplusOC()).trim().equalsIgnoreCase("INVALID")	&& (Double.parseDouble(bean.getCash_Loss_Limit()) > Double.parseDouble(bean.getTreatyLimitsurplusOC())))
							addActionError(getText("errors.cashLimitGrTreatyLimit"));
						}
					else if(StringUtils.isNotBlank(bean.getLimitOrigCur()) && StringUtils.isNotBlank(bean.getTreatyLimitsurplusOC())){
						int t=Double.compare(Double.parseDouble(bean.getLimitOrigCur()), Double.parseDouble(bean.getTreatyLimitsurplusOC()));
						if (val.isValidNo(bean.getCash_Loss_Limit()).trim().equalsIgnoreCase("INVALID")) {
							addActionError(getText("errors.cash_Loss_Limit.invalid"));
						} else if (!(val.isValidNo(bean.getLimitOrigCur()).trim().equalsIgnoreCase("INVALID")||val.isValidNo(bean.getTreatyLimitsurplusOC()).trim().equalsIgnoreCase("INVALID"))	&& (Double.parseDouble(bean.getCash_Loss_Limit()) > (Double.parseDouble(t>0?bean.getLimitOrigCur():bean.getTreatyLimitsurplusOC()))))
							addActionError(getText("errors.cashLimitGrTreatyLimit"));
						}
				}


				if(StringUtils.isBlank(bean.getEvent_limit())){
					addActionError(getText("errors.event_limit.second"));
				}else{
					bean.setEvent_limit(bean.getEvent_limit().replaceAll(",", ""));
					if (val.isValidNo(bean.getEvent_limit().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.event_limit.invalid"));
					}
				}
				if(StringUtils.isBlank(bean.getAggregate_Limit())){
					addActionError(getText("errors.aggregate_Limit.second"));
				}else{
					bean.setAggregate_Limit(bean.getAggregate_Limit().replaceAll(",", ""));
					if (val.isValidNo(bean.getAggregate_Limit().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.aggregate_Limit.invalid"));
					}
				}
				if(StringUtils.isBlank(bean.getOccurrent_Limit())){
					addActionError(getText("errors.occurrent_Limit.second"));
				}else{
					bean.setOccurrent_Limit(bean.getOccurrent_Limit().replaceAll(",", ""));
					if (val.isValidNo(bean.getOccurrent_Limit().trim()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.occurrent_Limit.invalid"));
					}
				}
			}
			
			if(StringUtils.isNotBlank(bean.getRetentionYN()) && "Y".equalsIgnoreCase(bean.getRetentionYN())){
			validationCedentRetention();
			}
			validationContract();
			validationRemarks();
			
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
	}
	private void ShowDropDown() {
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(bean.getCedingCo())&&StringUtils.isNotBlank(bean.getIncepDate())&&StringUtils.isNotBlank(bean.getExpDate())&&StringUtils.isNotBlank(bean.getUwYear())&&StringUtils.isNotBlank(bean.getOrginalCurrency())&&StringUtils.isNotBlank(bean.getDepartmentId())&&StringUtils.isNotBlank(bean.getTreatyType())&&StringUtils.isNotBlank(bean.getProfit_Center())){
			getContractListVal();
		}
		else if(bean.getContractTypelist()==null){
			bean.setContractTypelist(list);
		}
		bean.setYearList(getYearList());
		bean.setYearToList(getYearToList());
		bean.setProfit_Centerlist(dropDownController.getProfitCentreDropDown(branchCode));
		/*if(StringUtils.isBlank(bean.getEndtMode())) {
			bean.setProfit_Center("TPR");
			}*/
		if(StringUtils.isBlank(bean.getDepartId())){
			bean.setSubProfitList(new ArrayList<Map<String,Object>>());
		}else{
			bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
		}
		bean.setUnderWritterlist(dropDownController.getUnderWritterDropDown(branchCode,attachedUW));
		if("Renewal".equalsIgnoreCase(bean.getProposalReference())){
			bean.setDepartIdlist(dropDownController.RenewalDropDown(branchCode,pid,"Y"));
		}
		else{
		bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y",bean.getContractno1(),bean.getEndtMode(),bean.getProposalNo1(),bean.getMode(),bean.getBaseLayer()));
		}
		bean.setPolBrlist(dropDownController.getPolicyBranchDropDown(branchCode));
		bean.setProposallist(dropDownController.getConstantDropDown("1"));
		bean.setAccontPeriodlist(dropDownController.getConstantDropDown("5"));
		bean.setPNOCDayslist(dropDownController.getConstantDropDown("3"));
		bean.setBrokerlist(dropDownController.getPersonalInfoDropDown(branchCode,"B",pid));
		bean.setBasislist(dropDownController.getConstantDropDown("6"));
		bean.setProposaltypelist(dropDownController.getConstantDropDown("4"));
		bean.setOrginalCurrencylist(dropDownController.getCurrencyMasterDropDown(branchCode, countryId));
		bean.setTerritortylist(dropDownController.getTerritoryDropDown(branchCode));
		bean.setCeddingCompanylist(dropDownController.getPersonalInfoDropDown(branchCode,"C",pid));
		bean.setRetroTypelist(dropDownController.getConstantDropDown("8"));
		bean.setInwardBusinessTypelist(dropDownController.getConstantDropDown("24"));
		bean.setTreatyTypelist(dropDownController.getConstantDropDown("43"));
		if("RI02".equalsIgnoreCase(sourceId)){
		bean.setPerilCoveredlist(dropDownController.getConstantDropDown("30"));
		}
		bean.setRetTypeList(dropDownController.getConstantDropDown("45"));
		bean.setBasicTypeList(dropDownController.getConstantDropDown("46"));
		bean.setRetBusinessTypeList(dropDownController.getConstantDropDown("47"));
		bean.setRetdepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
		session.put("Product", pid);
		if(CollectionUtils.isEmpty(bean.getPaymentPartnerlist()))
		bean.setPaymentPartnerlist(dropDownController.getPaymentPartnerlist(branchCode,bean.getCedingCo(),bean.getBroker()));
		bean.setPremiumReserveList(dropDownController.getConstantDropDown("51"));
		bean.setBouquetList(dropDownController.getBouquetList(branchCode));    
		
	}

	private void getContractListVal() {
		String sumInsured = "";
		String surplus ="";
		if(StringUtils.isNotBlank(bean.getTreatyType())&&("4".equalsIgnoreCase(bean.getTreatyType()) || "5".equalsIgnoreCase(bean.getTreatyType()))){
			sumInsured =StringUtils.isEmpty(bean.getFaclimitOrigCur())? "0":bean.getFaclimitOrigCur().replaceAll(",", "");
		}
		else if(StringUtils.isNotBlank(bean.getTreatyType())&&"3".equalsIgnoreCase(bean.getTreatyType())){
				sumInsured =Double.toString(Double.parseDouble(StringUtils.isEmpty(bean.getLimitOrigCur())? "0":bean.getLimitOrigCur().replaceAll(",", "")));
				surplus = 	Double.toString(Double.parseDouble(StringUtils.isEmpty(bean.getTreatyLimitsurplusOC())? "0":bean.getTreatyLimitsurplusOC().replaceAll(",", ""))) ;
			}	
		else if(StringUtils.isNotBlank(bean.getTreatyType())&&"1".equalsIgnoreCase(bean.getTreatyType())){
			sumInsured = StringUtils.isEmpty(bean.getLimitOrigCur())? "0":bean.getLimitOrigCur().replaceAll(",", "");
		}
		else if(StringUtils.isNotBlank(bean.getTreatyType())&&"2".equalsIgnoreCase(bean.getTreatyType())){
			surplus = StringUtils.isEmpty(bean.getTreatyLimitsurplusOC())? "0":bean.getTreatyLimitsurplusOC().replaceAll(",", "");
		}
		bean.setContractTypelist(dropDownController.getContractValidation(pid,bean.getCedingCo(),bean.getIncepDate(),bean.getExpDate(),bean.getUwYear(),bean.getOrginalCurrency(),bean.getDepartId(),bean.getTreatyType(),sumInsured,StringUtils.isEmpty(bean.getContNo())? "0":bean.getContNo(),bean.getProfit_Center(),surplus,"","","0",bean.getBranchCode()));
			
	}
	public void validatesave(){
		try {
			boolean flags = true;
			boolean cedCheck = true;
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
			final String ourEst = val.isNull(bean.getOurEstimate());
			final String epiPercent = val.isNull(bean.getEpi_origCur());
			final String Epi = val.isNull(bean.getEpi());
			final String xlCost = val.isNull(bean.getXlCost());
			final String cenRent = val.isNull(bean.getCedReten());
			final String proStatus = val.isSelect(bean.getProStatus());
			final String shareWrit = val.isNull(bean.getShareWritt());
			final String shareSign = val.isNull(bean.getSharSign());
			final String orginalCurrency = val.isSelect(bean.getOrginalCurrency());
			final String maxLimit_Product = val.isNull(bean.getMaxLimit_Product());
				if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0){
				if(StringUtils.isBlank(bean.getEndorsmenttype())){
					addActionError(getText("end.type.error"));
				}
			}
			if (val.isSelect(bean.getDepartId()).equalsIgnoreCase("")) {
				addActionError(getText("error.departId.required"));
			}
			if (val.isSelect(bean.getSubProfit_center()).equalsIgnoreCase("")) {
				addActionError(getText("error.subProfit_center.required"));
			}else{
				bean.setSubProfit_center((bean.getSubProfit_center()).replaceAll(" ", ""));
			}
			
			if (val.isNull(bean.getUnderwriter()).equalsIgnoreCase("0")) {
				addActionError(getText("error.underwriter.required"));
			}

			if (maxLimit_Product.equalsIgnoreCase("")) {
				//addActionError(getText("error.maxLimitproduct.required"));
				//cedCheck = false;
			} else {
				bean.setMaxLimit_Product((bean.getMaxLimit_Product()).replaceAll(",", ""));
				if (val.isValidNo(bean.getMaxLimit_Product().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("error.maxLimitproduct.invalid"));
					cedCheck = false;
				} else {
					String uwLimit=dropDownController.getUnderWriterLimmit(bean.getUnderwriter(),(String)session.get("processId"), pid, "0");
					uwLimit=uwLimit.replaceAll(",", "");
					if (Double.parseDouble(uwLimit) == 0) {
						addActionError(getText("error.maxLimitProduct.config"));
						cedCheck = false;
					} else if (Double.parseDouble(bean.getMaxLimit_Product()) > Double.parseDouble(uwLimit)) {
						addActionError(getText("error.maxLimitProduct.exceedLimit", new String[]{uwLimit}));
						cedCheck = false;
					}
				}
			}
			Map<String, Object> map = null;
			List<Map<String, Object>> list = service.getValidation(bean);
			if (list != null && list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			}
			if (val.isNull(bean.getPolBr()).equalsIgnoreCase("")) {
				//addActionError(getText("error.polBr.required"));
			}
			if (val.isSelect(bean.getCedingCo()).equalsIgnoreCase("")) {
				addActionError(getText("error.cedingCo.required"));
			}
			if (brok.equalsIgnoreCase("")) {
				addActionError(getText("error.broker.required"));
			}
			if (bean.getTreatyType().equalsIgnoreCase("0")) {
				addActionError(getText("error.TreatyType.Reqired"));
			}
			if (val.isNull(bean.getIncepDate()).equalsIgnoreCase("")) {
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
			if (val.isNull(bean.getExpDate()).equalsIgnoreCase("")) {
				addActionError(getText("error.expDate.required"));
			} else if (expdate.equalsIgnoreCase("INVALID")) {
				addActionError(getText("errors.ExpiryDate.Error"));
			}
			if (!bean.getIncepDate().equalsIgnoreCase("")&& !bean.getExpDate().equalsIgnoreCase("")) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.expDate.check"));
				}
			}
			if(StringUtils.isNotBlank(bean.getAccDate())){
			if (accDate.equalsIgnoreCase("INVALID")) {
				//addActionError(getText("error.accDate.checkerror"));
			}
			}
			if (val.isSelect(bean.getUwYear()).equalsIgnoreCase("")) {
				addActionError(getText("error.uwYear.required"));
			} else if (!"".equals(bean.getRenewal_contract_no())&& !"0".equals(bean.getRenewal_contract_no())&& map != null	&& Integer.parseInt((String) map.get("UW_YEAR")) >= Integer	.parseInt(bean.getUwYear())) {
				//addActionError(getText("errors.year.invalid"));
			}
			
			if (orginalCurrency.equalsIgnoreCase("")) {
				addActionError(getText("error.orginalCurrency.required"));
			}
			if (exchRate.equalsIgnoreCase("")) {
				addActionError(getText("error.exchRate.required"));
				cedCheck = false;
			} else if (val.isValidNo(exchRate.trim().toString()).equalsIgnoreCase("invalid")) {
				addActionError(getText("error.exchRate.check"));
				cedCheck = false;
			}

			double maxlimit = 0.0;
			boolean spflag = true;

			if (val.isNull(bean.getSpRetro()).equalsIgnoreCase("")) {
				addActionError(getText("errors.SpRetro.error"));
				spflag = false;
			}
			
			if( "2".equals(bean.getDepartId()) || "10".equals(bean.getDepartId())){
				if(val.isNull(bean.getLimitPerVesselOC()).equalsIgnoreCase("")){
					//addActionError(getText("errors.LimitPerVesselOC.required"));
				}else{
					bean.setLimitPerVesselOC((bean.getLimitPerVesselOC()).replaceAll(",",""));
					if(val.isValidNo(bean.getLimitPerVesselOC().trim()).equalsIgnoreCase("INVALID")){
						addActionError(getText("errors.LimitPerVesselOC.invalid"));
					}
				}
				if(val.isNull(bean.getLimitPerLocationOC()).equalsIgnoreCase("")){
					//addActionError(getText("errors.LimitPerLocationOC.required"));
				}else{
					bean.setLimitPerLocationOC((bean.getLimitPerLocationOC()).replaceAll(",",""));
					if(val.isValidNo(bean.getLimitPerLocationOC().trim()).equalsIgnoreCase("INVALID")){
						addActionError(getText("errors.LimitPerLocationOC.invalid"));
					}
				}
			}
			
			double amt = 0.0;

			if (bean.getProposalType().equalsIgnoreCase("0")) {
				addActionError(getText("error.cleancutoff.required"));
			}

			if(StringUtils.isBlank(bean.getLOCIssued())){
				addActionError(getText("error.locissued.required"));
			}
			if (bean.getReceiptofPayment().equalsIgnoreCase("")) {
				addActionError(getText("error.ReceiptOfStatments.required"));
			} else if (val.isValidNo(bean.getReceiptofPayment()).equalsIgnoreCase("invalid")) {
				addActionError(getText("error.ReceiptOfStatments.Error"));
			}
			
			boolean cedflag = true;
			if (val.isNull(bean.getCedRetenType()).equalsIgnoreCase("")) {
				addActionError(getText("error.cedRentType.required"));
				cedflag = false;
			} else {
				if (cenRent.equalsIgnoreCase("")) {
					addActionError(getText("error.cedRent.required"));
					cedflag = false;
				} else {
					bean.setCedReten((bean.getCedReten()).replaceAll(",", ""));
					if ("A".equalsIgnoreCase(bean.getCedRetenType())) {
						cedflag = false;
					} 
				}
			}
			if(bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("1") ){
				if (limitPercent.equalsIgnoreCase("")) {
					addActionError(getText("error.limitOrigCurr.required"));
					cedCheck = false;
				} else {
					bean.setLimitOrigCur((bean.getLimitOrigCur()).replaceAll(",", ""));
					if (val.isValidNo(bean.getLimitOrigCur()).equalsIgnoreCase("invalid")) {
						addActionError(getText("error.limitOrigCurr.check"));
						cedCheck = false;
					} else {
						amt = Double.parseDouble(bean.getLimitOrigCur());
					}
				}
				}
			if(bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("2") ){
				if(StringUtils.isBlank(bean.getTreatynoofLine())){
					addActionError(getText("error.noonline.required"));
				}
			}
			if(bean.getTreatyType().equalsIgnoreCase("4") || bean.getTreatyType().equalsIgnoreCase("5") ){
				if(StringUtils.isBlank(bean.getFaclimitOrigCur())){
					addActionError(getText("error.fac.limit.currency"));
				}
				else {
					bean.setFaclimitOrigCur((bean.getFaclimitOrigCur()).replaceAll(",", ""));
					amt = Double.parseDouble(bean.getFaclimitOrigCur());
				}
			}
			if(bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("2")){
				if(StringUtils.isBlank(bean.getTreatyLimitsurplusOC())){
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

			if(StringUtils.isBlank(bean.getPml())){
				//addActionError(getText("error.pml.required"));
			}
			else if("Y".equalsIgnoreCase(bean.getPml())){
				if(StringUtils.isBlank(bean.getPmlPercent())){
					addActionError(getText("error.pmlpercentage.required"));
				}
				else{
					double pmlper =Double.parseDouble(bean.getPmlPercent());
					if(pmlper>100){
						addActionError(getText("error.pmlpercentage.less.100.required"));
					}
				}

			/*if(bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("1")){
				if(StringUtils.isBlank(bean.getLimitOrigCurPml())){
					addActionError(getText("error.limitorgcurpml.required"));
				}
				else{
					bean.setLimitOrigCurPml(bean.getLimitOrigCurPml().replaceAll(",",""));
				}
			}
			if(bean.getTreatyType().equalsIgnoreCase("3") || bean.getTreatyType().equalsIgnoreCase("2") ){
				if(StringUtils.isBlank(bean.getTreatyLimitsurplusOCPml())){
					addActionError(getText("error.limitsurplespml.required"));
				}
				else{
					bean.setTreatyLimitsurplusOCPml(bean.getTreatyLimitsurplusOCPml().replaceAll(",",""));
				}
			}*/
			}
			if (epiPercent.equalsIgnoreCase("")) {
				//addActionError(getText("error.epiperCent.required1"));
			} else {
				bean.setEpi_origCur((bean.getEpi_origCur()).replaceAll(",", ""));
				if (val.isValidNo(bean.getEpi_origCur().trim()).equalsIgnoreCase("invalid")) {
					addActionError(getText("error.epiperCent.check"));
				}
			}
			if (ourEst.equalsIgnoreCase("")) {
				addActionError(getText("error.ourEstimate.required"));
			} else if (val.percentageValid(ourEst.trim()).equalsIgnoreCase("invalid")) {
				addActionError(getText("error.ourEstimate.check"));
			} else if (val.percentageValid(ourEst.trim()).equalsIgnoreCase("less")) {
				addActionError(getText("error.ourEstimate.checkless"));
			} else if (val.percentageValid(ourEst.trim()).equalsIgnoreCase("greater")) {
				addActionError(getText("error.ourEstimate.checkgreater"));
			}else{
				/*String ans = calcu.calculatePTTY(bean,"OurEstmt",0);
				if(Double.parseDouble(ans)!=Double.parseDouble(bean.getOurEstimate().replaceAll(",",""))){
					addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.setOurEstimate(ans);
				}*/
			}
			if (StringUtils.isBlank(Epi)) {
				addActionError(getText("error.epi.required"));
			} else {
				bean.setEpi((bean.getEpi()).replaceAll(",", ""));
				if (val.isValidNo(bean.getEpi().trim()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.epi.invalid"));
				}
				/*String ans = calcu.calculatePTTY(bean,"EPI",0);
				if(Double.parseDouble(ans)!=Double.parseDouble(bean.getEpi().replaceAll(",",""))){
					addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.setEpi(ans);
				}*/
			}
			
			double cedPer = 0.0;
			flags = true;
			if ("".equalsIgnoreCase(proStatus)) {
				addActionError(getText("error.proStatus.required"));
			}

			if (shareWrit.equalsIgnoreCase("")) {
				//addActionError(getText("error.shareWrit.required"));
				//flags = false;
				//cedCheck = false;
			} else if (val.percentageNewValid(bean.getShareWritt().trim()).equalsIgnoreCase("Invalid")) {
				addActionError(getText("error.shareWrit.invalid"));
				flags = false;
				cedCheck = false;
			}
			if (proStatus.equalsIgnoreCase("A")) {
				if("INVALID".equalsIgnoreCase(val.percentageNewValid(bean.getShareWritt().trim()))){
					addActionError(getText("errors.shWt.percentages"));
					flags=false;
					cedCheck=false;
				}
				if (shareSign.equalsIgnoreCase("")) {
					addActionError(getText("error.shareSign.required.pro"));
					flags = false;
					cedCheck = false;
				} else if (val.percentageNewValid(bean.getSharSign().trim()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.shareSign.required"));
					flags = false;
					cedCheck = false;
				} else {
					cedPer = Double.parseDouble(bean.getSharSign());
				}
				if (flags) {
					if (Double.parseDouble(bean.getSharSign().equalsIgnoreCase("") ? "0" : bean.getSharSign()) > Double.parseDouble(bean.getShareWritt().equalsIgnoreCase("") ? "0" : bean.getShareWritt())) {
						addActionError(getText("error.shareSign.invalid"));
						cedCheck = false;
					}
				}
				if (cedCheck) {
					double amount = (amt * (cedPer / 100.0));
					amount = amount / Double.parseDouble(exchRate);
					maxlimit = Double.parseDouble(bean.getMaxLimit_Product());
					logger.info("Cedent Amount=>" + amount);
					logger.info("Max Limit=>" + maxlimit);
					if (amount > maxlimit) {
						addActionError(getText("error.accAmtlessUWAmt"));
					}
				}
			}


			if (!bean.getExpDate().equalsIgnoreCase("") && !bean.getAccDate().equalsIgnoreCase("") &&!accDate.equalsIgnoreCase("INVALID")) {
				if (Validation.ValidateTwo(bean.getAccDate(), bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check"));
				}
			}
			if (!bean.getIncepDate().equalsIgnoreCase("")&& !bean.getExpDate().equalsIgnoreCase("") &&!incDate.equalsIgnoreCase("INVALID")) {
				if (Validation.ValidateTwo(bean.getIncepDate(),bean.getExpDate()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("error.accDate.check1"));
				}
			}
			if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAccDate()).equalsIgnoreCase("") && !bean.getEdit().equalsIgnoreCase("endorsment") &&!accDate.equalsIgnoreCase("INVALID")){
				if(new DropDownControllor().Validatethree(branchCode, bean.getAccDate())==0){
					addActionError(getText("errors.open.period.date",new String[] {bean1.getOpenPeriodDate()}));
				}
				}
			if(StringUtils.isNotBlank(bean.getRetentionYN()) && "Y".equalsIgnoreCase(bean.getRetentionYN())){
				validationCedentRetention();
				}
			validationContract();
			validationRemarks();
			
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();

		}
	}
	private void validationContract() {
		String sumInsured="";
		String surplus="";
		try{
			if(StringUtils.isNotBlank(bean.getCedingCo())&&StringUtils.isNotBlank(bean.getIncepDate())&&StringUtils.isNotBlank(bean.getExpDate())&&StringUtils.isNotBlank(bean.getUwYear())&&StringUtils.isNotBlank(bean.getOrginalCurrency())&&StringUtils.isNotBlank(bean.getDepartId())&&StringUtils.isNotBlank(bean.getTreatyType())&&StringUtils.isNotBlank(bean.getProfit_Center())){
				if(StringUtils.isNotBlank(bean.getTreatyType())&&("4".equalsIgnoreCase(bean.getTreatyType()) || "5".equalsIgnoreCase(bean.getTreatyType()))){
						sumInsured =StringUtils.isEmpty(bean.getFaclimitOrigCur())? "0":bean.getFaclimitOrigCur().replaceAll(",", "");
					}
				else if(StringUtils.isNotBlank(bean.getTreatyType())&&"3".equalsIgnoreCase(bean.getTreatyType())){
						sumInsured =Double.toString(Double.parseDouble(StringUtils.isEmpty(bean.getLimitOrigCur())? "0":bean.getLimitOrigCur().replaceAll(",", "")));
						surplus = 	Double.toString(Double.parseDouble(StringUtils.isEmpty(bean.getTreatyLimitsurplusOC())? "0":bean.getTreatyLimitsurplusOC().replaceAll(",", ""))) ;
					}	
				else if(StringUtils.isNotBlank(bean.getTreatyType())&&"1".equalsIgnoreCase(bean.getTreatyType())){
					sumInsured = StringUtils.isEmpty(bean.getLimitOrigCur())? "0":bean.getLimitOrigCur().replaceAll(",", "");
				}
				else if(StringUtils.isNotBlank(bean.getTreatyType())&&"2".equalsIgnoreCase(bean.getTreatyType())){
					surplus = StringUtils.isEmpty(bean.getTreatyLimitsurplusOC())? "0":bean.getTreatyLimitsurplusOC().replaceAll(",", "");
				}
				bean.setContractTypelist(dropDownController.getContractValidation(pid,bean.getCedingCo(),bean.getIncepDate(),bean.getExpDate(),bean.getUwYear(),bean.getOrginalCurrency(),bean.getDepartId(),bean.getTreatyType(),sumInsured,StringUtils.isEmpty(bean.getContNo())? "0":bean.getContNo(),bean.getProfit_Center(),surplus,"","","0",bean.getBranchCode()));
				if(bean.getContractTypelist().size()>0){
					if(StringUtils.isBlank(bean.getContractListVal())){
						addActionError(getText("error.contract.list"));
					}
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		
	}
	private void validateSecondPage() {
		try {
			Validation validation = new Validation();


			
			/*if (xlcostOurShare.equalsIgnoreCase("")) {
				addActionError(getText("errors.xlcostOurShare.second"));
			} else {
				if (validation.isValidNo(bean.getXlcostOurShare().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.xlcostOurShare.second1"));
				}
			}*/
			

			if("2".equalsIgnoreCase(bean.getInwardType())){
				if(StringUtils.isBlank(bean.getOrginalacqcost())){
					addActionError(getText("Errors.org.cost.Invalid"));
				}
				else if("Y".equalsIgnoreCase(bean.getOrginalacqcost())){
				if(StringUtils.isBlank(bean.getOurassessmentorginalacqcost())){
					addActionError(getText("Errors.org.assessment.Invalid"));
				}
				else{
					bean.setOurassessmentorginalacqcost(bean.getOurassessmentorginalacqcost().replaceAll(",",""));
				}
				if(StringUtils.isBlank(bean.getOuracqCost())){
					addActionError(getText("Errors.org.acqCost.Invalid"));
				}
				else{
					bean.setOuracqCost(bean.getOuracqCost().replaceAll(",",""));
					String ans = calcu.calculatePTTY(bean,"OurAcuCost",0);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getOuracqCost().replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.setOuracqCost(ans);
					}
					
				}
				}
			}
			if(StringUtils.isBlank(bean.getLocRate())){
				addActionError(getText("label.rate.year.error"));
			}
			
			/*
			 * (commissionQ_SAmt
				commission_surpAmt
				acqCostPer)Removed this three
			 * if(StringUtils.isBlank(bean.getCommissionQ_SAmt())){
				addActionError(getText("error.commission.qs"));
			}
			else{
				bean.setCommissionQ_SAmt(validation.isNull(bean.getCommissionQ_SAmt()).replaceAll(",", ""));
			}*/
		
		
			
			/*if (share_Profit_Commission.trim().equalsIgnoreCase("1")) {
				/*if (validation.isNull(bean.getProfit_commission()).equalsIgnoreCase("")) {
					addActionError(getText("errors.profit_commission.empty"));
				}
			}
			/*if (share_Profit_Commission.equalsIgnoreCase("")) {
				addActionError(getText("errors.share_Profit_Commission.second1"));
			}
			/*if (!share_Profit_Commission.equalsIgnoreCase("")) {
				if (bean.getShare_Profit_Commission().equalsIgnoreCase("1")) {
					if (management_Expenses.equalsIgnoreCase("")) {
						addActionError(getText("errors.management_Expenses.required"));
					} else {
						if (validation.percentageValid(bean.getManagement_Expenses()).trim().equalsIgnoreCase("INVALID")|| validation.percentageValid(	bean.getManagement_Expenses()).trim().equalsIgnoreCase("less")	|| validation.percentageValid(bean.getManagement_Expenses()).trim().equalsIgnoreCase("greater")) {
							addActionError(getText("errors.management_Expenses.second"));
						}
					}
					if (lossC_F.equalsIgnoreCase("")) {
						addActionError(getText("errors.lossC_F.second"));
					}
				}
			}*/

			
			
			
			if("Y".equalsIgnoreCase(bean.getEndorsementStatus())) {
			bean.setAccDate((new DropDownControllor().getAcceptanceDate(bean.getProposal_no())));
			if(StringUtils.isNotBlank(bean.getAccDate()) && StringUtils.isNotBlank( bean.getPreviousendoDate())) {
				bean.setMaxDate(Validation.getMaxDateValidate(bean.getAccDate(), bean.getPreviousendoDate()));
			}
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
				//addActionError(getText("errors.endoDate.invalid"));
				if("Endorsement".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.endoDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}
				else if("Rectification".equalsIgnoreCase(bean.getEndorsmenttype())){
					addActionError(getText("errors.rectificationDate.invalid",new String[] {bean.getAccDate(), bean.getPreviousendoDate()==null?"":bean.getPreviousendoDate()}));
				}
				
			}
			if(!validation.isNull(bean1.getOpstartDate()).equalsIgnoreCase("") && !validation.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !validation.isNull(bean.getEndorsementDate()).equalsIgnoreCase("")){
				if(new DropDownControllor().Validatethree(branchCode, bean.getEndorsementDate())==0){
					addActionError(getText("errors.open.period.date.endo",new String[] {bean1.getOpenPeriodDate()}));
				}
			}
			}
			
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
				if ("".equals(bean.getRetroYear().get(i) )) {
					addActionError(getText("error.uwYear.Required",new String[] { String.valueOf(i + 1) }));
					dupCheck = false;
				}
				if(bean.getRetroCeding() ==null){
					addActionError(getText("Error.CeddingCompany.Required",new String[] { String.valueOf(i + 1) }));
					dupCheck = false;
				}
				else if (StringUtils.isBlank(bean.getRetroCeding().get(i))) {
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
				bean.setProduct_id("4");
				bean.setRetroType("TR");
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
			if(bean.getCrestaStatus().equalsIgnoreCase("Y")){
				if(StringUtils.isBlank(bean.getCrestaPopUp())){
					addActionError(getText("cresta.popup.check"));
				}
				else if(service.getCrestaCount(bean)==0){
					addActionError(getText("error.creasta.invalid"));
				}
			}

			
			if(StringUtils.isBlank(bean.getBaseLayer())){
				if(StringUtils.isBlank(bean.getSlidecommissionSubClass())){
					addActionError(getText("slide.profit.commission.sub.class"));
				}
			}
				
				if(StringUtils.isBlank(bean.getBaseLayer())){
					if(StringUtils.isBlank(bean.getLosscommissionSubClass())){
						addActionError(getText("loss.profit.commission.sub.class"));
					}
				}
				if(StringUtils.isBlank(bean.getShare_Profit_Commission())){
					addActionError(getText("errors.share_Profit_Commission.empty"));
				}
				if(StringUtils.isBlank(bean.getBaseLayer())){
				if(StringUtils.isBlank(bean.getCommissionSubClass())){
					addActionError(getText("profit.commission.sub.class"));
				}
				}

				
			
			

			if("Y".equalsIgnoreCase(bean.getPml())){

			/*if(StringUtils.isBlank(bean.getEpiAsPerShare())){
				addActionError(getText("errors.epiAsPerShare.second"));
			} else {
				if (validation.isValidNo(bean.getEpiAsPerShare().trim()).equalsIgnoreCase("INVALID")) {
					addActionError(getText("errors.epiAsPerShare.second1"));
				}
			}*/
			}
			
			if(StringUtils.isNotBlank(bean.getEndorsementStatus())&& "Y".equalsIgnoreCase(bean.getEndorsementStatus()) && StringUtils.isBlank(bean.getDocStatus())) {
				addActionError(getText("doc.status"));
			}
			if(StringUtils.isBlank(bean.getRetroDupContract())){
				this.addActionError(getText("errors.dummy.contract",new String[]{bean.getUwYear()}));
			}
			validationRemarks();

		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
	}

	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	public String crestaPopUp(){
		List<List<Map<String,Object>>> crestaIDList=new ArrayList<List<Map<String,Object>>>();
		List<List<Map<String,Object>>> crestaNameList=new ArrayList<List<Map<String,Object>>>();
		//List<List<Map<String,Object>>> crestaIDList1=new ArrayList<List<Map<String,Object>>>();
		bean.setBranchCode(branchCode);
		service.getCrestaDetailList(bean);
		//crestaIDList1.add(service.getCrestaDetailList(bean));
		//bean.setCrestaList1(crestaIDList1);
		List<String>cur=new ArrayList<String>();
		if(bean.getCrestaList1() !=null && bean.getCrestaList1().size()==0){
			List<List<Map<String,Object>>> crestaList=new ArrayList<List<Map<String,Object>>>(5);
			for(int i=0;i<5;i++){
			 Map<String,Object> doubleMap = new HashMap<String,Object>();
			 List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
			 doubleMap.put("one",new Double(1.0));
			 creList.add(doubleMap);
			 crestaList.add(creList);
			cur.add(bean.getOrginalCurrency());
			crestaIDList.add(dropDownController.getCrestaIDList(branchCode,bean.getCrestaValue()));
			crestaNameList.add(dropDownController.getCrestaNameList(branchCode,bean.getCrestaValue()));
			}
			if(StringUtils.isNotBlank(bean.getMode())&&!bean.getMode().equalsIgnoreCase("View")){
			bean.setCurrencyId(cur);
			}
			bean.setCrestaList1(crestaList);
			bean.setCrestaIDList(crestaIDList);
			bean.setCrestaNameList(crestaNameList);
		}
		else{
			if(StringUtils.isNotBlank(bean.getMode())&&!bean.getMode().equalsIgnoreCase("View")){
			for(int i=0;i<bean.getCrestaList1().size();i++){
				cur.add(bean.getOrginalCurrency());
				bean.setCurrencyId(cur);
			}
			}
		}
		return "crestaPopUp";
	}
	public String submitCrestaDetails(){
		String forward="streamResult";
		bean.setBranchCode(branchCode);
		bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
		boolean res = validationCresta();
		if(!hasActionErrors()  ){
			if(res){
			service.insertCrestaDetails(bean);
			}
			String value="<script type='text/javascript'>$('#companyModal1').modal('toggle');document.getElementById('referenceNo').value="+(StringUtils.isBlank(bean.getReferenceNo())?"0":bean.getReferenceNo())+"</script>";
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
			bean.setCrestacount(bean.getCrestaId().size());
			List<List<Map<String,Object>>> crestaList=new ArrayList<List<Map<String,Object>>>();
				for(int i=0;i<bean.getCrestacount();i++){
					Map<String,Object> doubleMap = new HashMap<String,Object>();
					 List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
					 List<String>cur=new ArrayList<String>();
					 doubleMap.put("one",new Double(1.0));
					 creList.add(doubleMap);
					 crestaList.add(creList);
				}
				bean.setCrestaList1(crestaList);
				resetCrestaList();
			forward="crestaPopUp";
		}
		return forward;
	}
	public String getCrestaAjax(){
		if(bean.getDropDown().equalsIgnoreCase("crestaID")){
			bean.setCrestaIDAjsxList(dropDownController.getCrestaIDList(branchCode,bean.getCrestaValue()));
		}else if(bean.getDropDown().equalsIgnoreCase("crestaName")){
			bean.setCrestaNameAjax(dropDownController.getCrestaName(branchCode,bean.getCrestaValue()));
			//bean.setCrestaNameAjsxList(dropDownController.getCrestaNameList(branchCode,bean.getCrestaValue()));
		}

		return "dropdownajax";

	}
	private boolean validationCresta() {
		/*if(StringUtils.isBlank(bean.getAccumDate())){
			addActionError(getText("error.enter.accumdate"));
		}*/
		int j=0;
		boolean status=true;
		List<String> error = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		List<String> accRisk=new ArrayList<String>();
		for(int i=0;i<bean.getCrestaId().size();i++){
			if(StringUtils.isBlank(bean.getTerritoryCode().get(i))){
				list.add(getText("error.enter.territory",new String[] { String.valueOf(i + 1) }));
			}
			if(StringUtils.isBlank(bean.getCrestaId().get(i))){
				list.add(getText("error.enter.crestaid",new String[] { String.valueOf(i + 1) }));
			}
			if(StringUtils.isBlank(bean.getCrestaName().get(i))){
				list.add(getText("error.enter.crestaname",new String[] { String.valueOf(i + 1) }));
			}
			if(StringUtils.isBlank(bean.getCurrencyId().get(i))){
				list.add(getText("error.enter.currency",new String[] { String.valueOf(i + 1) }));
			}
			if(StringUtils.isBlank(bean.getAccumulationDate().get(i))){
				list.add(getText("error.enter.accumdate",new String[] { String.valueOf(i + 1) }));
			}
			if(StringUtils.isBlank(bean.getAccRisk().get(i))){
				list.add(getText("error.enter.accrisk",new String[] { String.valueOf(i + 1) }));
				accRisk.add(bean.getAccRisk().get(i));
			}else{
				if(!hasActionErrors()){
					accRisk.add(bean.getAccRisk().get(i).replace(",", ""));

				}else{
					accRisk.add(bean.getAccRisk().get(i));
				}

			}
			if(list.size()==6){
				list = new ArrayList<String>();
				j++;
			}
			else if(list.size()==5 && StringUtils.isNotBlank(bean.getCurrencyId().get(i))){
				list = new ArrayList<String>();
				j++;
			}
			else{
				list1 = list;
				list = new ArrayList<String>();
			}
		}
		
		for(int k=0;k<list1.size();k++){
			addActionError(list1.get(k));
		}
		if(j==bean.getCrestaId().size()){
			addActionError(getText("error.cresta.val"));
			status = false;
		}
		bean.setAccRisk(accRisk);
		return status;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	private void resetCrestaList() {
		if(bean.getCrestacount()>0){
			List<String> territoryId = new ArrayList<String>();
			List<String> crestaId = new ArrayList<String>();
			List<String> crestaName = new ArrayList<String>();
			List<String> currencyId = new ArrayList<String>();
			List<String> accRisk = new ArrayList<String>();
			List<String> accDate = new ArrayList<String>();
			List<List<Map<String,Object>>> crestaIDList=new ArrayList<List<Map<String,Object>>>();
			List<List<Map<String,Object>>> crestaNameList=new ArrayList<List<Map<String,Object>>>();
			for(int i=0;i<bean.getCrestacount();i++){
				territoryId.add(bean.getTerritoryCode().get(i)==null?"":bean.getTerritoryCode().get(i));
				crestaId.add(bean.getCrestaId().get(i)==null?"":bean.getCrestaId().get(i));
				crestaName.add(bean.getCrestaName().get(i)==null?"":bean.getCrestaName().get(i));
				currencyId.add(bean.getCurrencyId().get(i)==null?"":bean.getCurrencyId().get(i));
				accRisk.add(bean.getAccRisk().get(i)==null?"":bean.getAccRisk().get(i));
				accDate.add(bean.getAccumulationDate().get(i)==null?"":bean.getAccumulationDate().get(i));
				crestaIDList.add(new DropDownControllor().getCrestaIDList(branchCode,bean.getTerritoryCode().get(i)));
				crestaNameList.add(new DropDownControllor().getCrestaNameList(branchCode,bean.getCrestaId().get(i)));
			}
			bean.setTerritoryCode(territoryId);
			bean.setCrestaId(crestaId);
			bean.setCrestaName(crestaName);
			bean.setCurrencyId(currencyId);
			bean.setAccRisk(accRisk);
			bean.setAccumulationDate(accDate);
			bean.setCrestaIDList(crestaIDList);
			bean.setCrestaNameList(crestaNameList);
		}
	}
	public String getFields() {
		fields=getText("proportionality")+",";
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
	public String viewScaleCommissionPopUp(){
		String forward = "scalePopUp";
		bean.setBranchCode(branchCode);
		bean.setProduct_id(pid);
		bean.setLoginId(userId);
		if("profitCommission".equalsIgnoreCase(bean.getPageFor())){
			bean.setCommissionTypeBasedList(service.ProfitCommissionList(bean));
			if(bean.getCommissionTypeBasedList().size()<=0 && bean.getCommissionFrom().size()<=0){
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				for(int i=0;i<2;i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				}
				bean.setCommissionTypeBasedList(list);
			}
			forward="profitCommission";

		}
		else{
		bean.setScaleCommissionList(service.getScaleCommissionList(bean));
		if(bean.getScaleCommissionList().size()<=0){
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for(int i=0;i<5;i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			}
			bean.setScaleCommissionList(list);
		}
		}
		return forward;
	}
	public String removeRow(){
		String forward = "dropdownajax";
		List<String> from=new ArrayList<String>();
		List<String> to=new ArrayList<String>();
		List<String> per=new ArrayList<String>();
		 List<String> scalemaxpartpercent = new ArrayList<String>();
		if("commission".equalsIgnoreCase(bean.getFlag())){
			bean.getCommissionSNo().remove(bean.getDeleteId());
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for(int i=0;i<bean.getCommissionSNo().size();i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			}
			int j=1;

			for(int k=0;k<bean.getCommissionSNo().size();k++){
				int value=Integer.parseInt(bean.getDeleteId());
				if(k<value){
					from.add(bean.getCommissionFrom().get(k));
					to.add(bean.getCommissionTo().get(k));
					per.add(bean.getCommissionPer().get(k));
				}
				else{
				if(StringUtils.isNotBlank(bean.getCommissionFrom().get(j))){
					from.add(bean.getCommissionFrom().get(j));
				}
				if(StringUtils.isNotBlank(bean.getCommissionTo().get(j))){
					to.add(bean.getCommissionTo().get(j));
							}
				if(StringUtils.isNotBlank(bean.getCommissionPer().get(j))){
					per.add(bean.getCommissionPer().get(j));
				}
				}
				j++;
			}
			bean.setCommissionFrom(from);
			bean.setCommissionTo(to);
			bean.setCommissionPer(per);
			bean.setCommissionTypeBasedList(list);
			forward="profitCommission";
		}
		else{
		bean.getScaleSNo().remove(bean.getDeleteId());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=0;i<bean.getScaleSNo().size();i++){
		Map<String,Object> string = new HashMap<String,Object>();
		string.put("1","1");
		list.add(string);
		}
		int j=1;

		for(int k=0;k<bean.getScaleSNo().size();k++){
			int value=Integer.parseInt(bean.getDeleteId());
			if(k<value){
				from.add(bean.getScaleFrom().get(k));
				to.add(bean.getScaleTo().get(k));
				per.add(bean.getScaleLowClaimBonus().get(k));
				scalemaxpartpercent.add(bean.getScalemaxpartpercent().get(k));
				
			}
			else{
			if(StringUtils.isNotBlank(bean.getScaleFrom().get(j))){
				from.add(bean.getScaleFrom().get(j));
			}
			if(StringUtils.isNotBlank(bean.getScaleFrom().get(j))){
				to.add(bean.getScaleTo().get(j));
						}
			if(StringUtils.isNotBlank(bean.getScaleFrom().get(j))){
				per.add(bean.getScaleLowClaimBonus().get(j));
			}
			if(StringUtils.isNotBlank(bean.getScalemaxpartpercent().get(j))){
				scalemaxpartpercent.add(bean.getScalemaxpartpercent().get(j));
			}
			}
			j++;
		}
		bean.setScaleFrom(from);
		bean.setScaleTo(to);
		bean.setScaleLowClaimBonus(per);
		 bean.setScalemaxpartpercent(scalemaxpartpercent);
		bean.setScaleCommissionList(list);

		}
		return forward;
	}
	public String removeCresta(){

		List<String> territoryId = new ArrayList<String>();
		List<String> crestaId = new ArrayList<String>();
		List<String> crestaName = new ArrayList<String>();
		List<String> currencyId = new ArrayList<String>();
		List<String> accRisk = new ArrayList<String>();
		List<String> accDate = new ArrayList<String>();
		List<List<Map<String,Object>>> crestaIDList=new ArrayList<List<Map<String,Object>>>();
		List<List<Map<String,Object>>> crestaNameList=new ArrayList<List<Map<String,Object>>>();
		bean.getScaleSNo().remove(bean.getDeleteId());
		List<List<Map<String,Object>>> crestaList=new ArrayList<List<Map<String,Object>>>();
		for(int i=0;i<bean.getScaleSNo().size();i++){
			 Map<String,Object> doubleMap = new HashMap<String,Object>();
			 List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
			 doubleMap.put("one",new Double(1.0));
			 creList.add(doubleMap);
			 crestaList.add(creList);
			}
		bean.setCrestaList1(crestaList);
			int j=1;

			for(int k=0;k<bean.getScaleSNo().size();k++){
				int value=Integer.parseInt(bean.getDeleteId());
				if(k<value){
					territoryId.add(bean.getTerritoryCode().get(k));
					crestaId.add(bean.getCrestaId().get(k));
					crestaName.add(bean.getCrestaName().get(k));
					currencyId.add(bean.getCurrencyId().get(k));
					accRisk.add(bean.getAccRisk().get(k));
					accDate.add(bean.getAccumulationDate().get(k));
					crestaIDList.add(new DropDownControllor().getCrestaIDList(branchCode,bean.getTerritoryCode().get(k)));
					crestaNameList.add(new DropDownControllor().getCrestaNameList(branchCode,bean.getCrestaId().get(k)));
				}
				else{
				if(StringUtils.isNotBlank(bean.getTerritoryCode().get(j))){
					territoryId.add(bean.getTerritoryCode().get(j));
					crestaIDList.add(new DropDownControllor().getCrestaIDList(branchCode,bean.getTerritoryCode().get(j)));
				}else{
					crestaIDList.add(new DropDownControllor().getCrestaIDList(branchCode,""));
				}
				if(StringUtils.isNotBlank(bean.getCrestaId().get(j))){
					crestaId.add(bean.getCrestaId().get(j));
					crestaNameList.add(new DropDownControllor().getCrestaNameList(branchCode,bean.getCrestaId().get(j)));
				}else{
					crestaNameList.add(new DropDownControllor().getCrestaNameList(branchCode,""));
				}
				if(StringUtils.isNotBlank(bean.getCrestaName().get(j))){
					crestaName.add(bean.getCrestaName().get(j));
				}
				if(StringUtils.isNotBlank(bean.getCurrencyId().get(j))){
					currencyId.add(bean.getCurrencyId().get(j));
				}
				if(StringUtils.isNotBlank(bean.getAccumulationDate().get(j))){
					accDate.add(bean.getAccumulationDate().get(j));
				}
				if(StringUtils.isNotBlank(bean.getAccRisk().get(j))){
					accRisk.add(bean.getAccRisk().get(j));
				}

				}
				j++;
			}
		bean.setTerritoryCode(territoryId);
		bean.setCrestaId(crestaId);
		bean.setCrestaName(crestaName);
		bean.setCurrencyId(currencyId);
		bean.setAccRisk(accRisk);
		bean.setAccumulationDate(accDate);
		bean.setCrestaIDList(crestaIDList);
		bean.setCrestaNameList(crestaNameList);
		return "crestaPopUp";
	}
	public String insBonusDetails(){
		String forward="streamResult";
		validation();
		if(bean.getErrorList().size()<=0){
			bean.setBranchCode(branchCode);
			bean.setLoginId(userId);
			bean.setProduct_id(pid);
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			String result = service.ScaleCommissionInsert(bean);
			String value="<script type='text/javascript'>$('#companyModal1').modal('toggle');document.getElementById('referenceNo').value="+(StringUtils.isBlank(bean.getReferenceNo())?"0":bean.getReferenceNo())+"</script>";
			byte[] byteArray = value.getBytes();
			inputStream=new ByteArrayInputStream(byteArray);
		}
		else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = bean.getErrorList().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
			for(int i=0;i<bean.getScaleSNo().size();i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			}
			bean.setScaleCommissionList(list);
			forward = "scalePopUp";
		}
		return  forward;
	}
	private void validationBonusSubmit(String val) {

		}
	private void validation() {
		int j=1;
		List<String> list=new ArrayList<String>();
		List<String> list1=new ArrayList<String>();

		for(int i=0;i<bean.getScaleSNo().size();i++){
		if(StringUtils.isBlank(bean.getScaleFrom().get(i))){
		if("scale".equalsIgnoreCase(bean.getPageFor())){
		list.add(getText("Scale.error.from.val",new String[] { String.valueOf(i + 1) }));
		}
		else{
		list.add(getText("losspart.error.from.val",new String[] { String.valueOf(i + 1) }));
		}
		}
		if(StringUtils.isBlank(bean.getScaleTo().get(i)) ){
		if("scale".equalsIgnoreCase(bean.getPageFor())){
		list.add(getText("Scale.error.to.val",new String[] { String.valueOf(i + 1) }));
		}
		else{
		list.add(getText("losspart.error.to.val",new String[] { String.valueOf(i + 1) }));
		}
		}
		if(StringUtils.isBlank(bean.getScaleLowClaimBonus().get(i))){
		if("scale".equalsIgnoreCase(bean.getPageFor())){
		list.add(getText("Scale.error.lcb.val",new String[] { String.valueOf(i + 1) }));
		}
		else{
		list.add(getText("losspart.error.lcb.val",new String[] { String.valueOf(i + 1) }));
		}
		}

		if(list.size()==3){
		list=new ArrayList<String>();
		}
		else{
		if(StringUtils.isNotBlank(bean.getScaleFrom().get(i))){
		double val =Double.parseDouble(bean.getScaleFrom().get(i).replace(",", ""));
		if(val>1000){
		if("scale".equalsIgnoreCase(bean.getPageFor())){
		list.add(getText("Scale.error.from.limit",new String[] { String.valueOf(i + 1) }));
		}
		else{
		list.add(getText("losspart.error.from.limit",new String[] { String.valueOf(i + 1) }));
		}
		}
		}
		if(StringUtils.isNotBlank(bean.getScaleTo().get(i))){
		double val =Double.parseDouble(bean.getScaleTo().get(i).replace(",", ""));
		if(val>1000){
		if("scale".equalsIgnoreCase(bean.getPageFor())){
		list.add(getText("Scale.error.to.limit",new String[] { String.valueOf(i + 1) }));
		}
		else{
		list.add(getText("losspart.error.to.limit",new String[] { String.valueOf(i + 1) }));
		}
		}
		}
		if(StringUtils.isNotBlank(bean.getScaleLowClaimBonus().get(i))){
		double val =Double.parseDouble(bean.getScaleLowClaimBonus().get(i).replace(",", ""));
		if(val>1000){
		if("scale".equalsIgnoreCase(bean.getPageFor())){
		list.add(getText("Scale.loss.to.ratio.limit",new String[] { String.valueOf(i + 1) }));
		}
		else{
		list.add(getText("Scale.loss.to.ratio.limit",new String[] { String.valueOf(i + 1) }));
		}
		}
		}
		if(StringUtils.isNotBlank(bean.getScaleFrom().get(i)) && StringUtils.isNotBlank(bean.getScaleTo().get(i))){
		if(Double.parseDouble(bean.getScaleFrom().get(i).replace(",", ""))>Double.parseDouble(bean.getScaleTo().get(i).replace(",", ""))){
		if("scale".equalsIgnoreCase(bean.getPageFor())){
		list.add(getText("Scale.loss.error.from.to.ratio.limit",new String[] { String.valueOf(i + 1) }));
		}
		else{
		list.add(getText("losspart.loss.error.from.to.ratio.limit",new String[] { String.valueOf(i + 1) }));
		}
		}
		}
		if(bean.getScaleSNo().size()>j){
		if(StringUtils.isNotBlank(bean.getScaleFrom().get(j)) && StringUtils.isNotBlank(bean.getScaleTo().get(i))){
		double from =Double.parseDouble(bean.getScaleFrom().get(j).replace(",", ""));
		double to = Double.parseDouble(bean.getScaleTo().get(i).replace(",", ""));
		if(to>from){
		if("scale".equalsIgnoreCase(bean.getPageFor())){
		list.add(getText("Scale.from.to.limit",new String[] { String.valueOf(i + 1) ,String.valueOf(i + 2)} ));
		}
		else{
		list.add(getText("losspart.from.to.limit",new String[] { String.valueOf(i + 1) ,String.valueOf(i + 2)} ));
		}
		}
		}
		}

		for(int k=0;k<list.size();k++){
		list1.add(list.get(k));
		}
		list=new ArrayList<String>();
		}
		j++;
		}
		if(!"scale".equalsIgnoreCase(bean.getPageFor())){
		 if(StringUtils.isBlank(bean.getBonusTypeId())){
		 list1.add(getText("losspart.error.type"));
		 }
		}
		else if("scale".equalsIgnoreCase(bean.getPageFor())){
		 if(StringUtils.isBlank(bean.getQuotaShare()) && StringUtils.isNotBlank(bean.getTreatyType()) && ("1".equals(bean.getTreatyType()) ||"2".equals(bean.getTreatyType())||"3".equals(bean.getTreatyType()))){
		 list1.add(getText("error.scale.quota.share"));
		 }
		 }
		if(StringUtils.isNotBlank(bean.getFpcType()) && "P".equals(bean.getFpcType())) {
			if(StringUtils.isBlank(bean.getScfistpc())){
				if("scale".equalsIgnoreCase(bean.getPageFor())){
					list1.add(getText("error.scale.firstpc"));
				}
				else{
					list1.add(getText("error.loss.firstpc"));
				}
			}
			if(StringUtils.isBlank(bean.getScprofitMont())){
				if("scale".equalsIgnoreCase(bean.getPageFor())){
					list1.add(getText("error.scale.profitmonth"));
				}
				else{
					list1.add(getText("error.loss.profitmonth"));
				}
			}
			
		}else{
			if(StringUtils.isBlank(bean.getFpcfixedDate())) {
				if("scale".equalsIgnoreCase(bean.getPageFor())){
					list1.add(getText("error.scale.firstfixeddate"));
				}
				else{
					list1.add(getText("error.loss.firstfixeddate"));
				}
			}
		}
		if("N".equals(bean.getScsubSeqCalculation())) {
			if(StringUtils.isBlank(bean.getScsubpc())){
				if("scale".equalsIgnoreCase(bean.getPageFor())){
					list1.add(getText("error.scale.subpc"));
				}
				else{
					list1.add(getText("error.loss.subpc"));
				}
			}
			if(StringUtils.isBlank(bean.getScsubProfitMonth())){
				if("scale".equalsIgnoreCase(bean.getPageFor())){
		
				list1.add(getText("error.scale.sub.profit.month"));
				}
				else{
				list1.add(getText("error.loss.sub.profit.month"));
				}
			}
		}
		bean.setErrorList(list1);
		}
	public void documentDownload(){
		try {
			HttpServletResponse response=ServletActionContext.getResponse();
			OutputStream oout = response.getOutputStream();
			dropDownController.getCountryDate(branchCode,EXP_FILE);
			response.setHeader("Content-disposition","attachment;filename="+"Cresta File.xlsx");
			response.setContentType("application/binary");
			FileInputStream fis = new FileInputStream(EXP_FILE);
			byte[] buf = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = fis.read(buf)) != -1) {
				oout.write(buf, 0, bytesRead);
			}
			oout.close();
			fis.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public String uploadDocument(){
		List<String> teritoryId =new ArrayList<String>();
		List<String> crestaId =new ArrayList<String>();
		List<String> crestaName =new ArrayList<String>();
		List<String> currencyId =new ArrayList<String>();
		List<String> accCost =new ArrayList<String>();
		List<String> date =new ArrayList<String>();
		List<List<Map<String,Object>>> crestaIDList=new ArrayList<List<Map<String,Object>>>();
		List<List<Map<String,Object>>> crestaNameList=new ArrayList<List<Map<String,Object>>>();
		List<String> error =new ArrayList<String>();
		String index;
		bean.setBranchCode(branchCode);

		if(bean.getCrestaId().size()>0){
		for(int i=0;i<bean.getCrestaId().size();i++){
			if(StringUtils.isNotBlank(bean.getCrestaId().get(i))||StringUtils.isNotBlank(bean.getCrestaName().get(i))||StringUtils.isNotBlank(bean.getAccRisk().get(i))||StringUtils.isNotBlank(bean.getTerritoryCode().get(i))||StringUtils.isNotBlank(bean.getAccumulationDate().get(i))){
			 crestaId.add(bean.getCrestaId().get(i));
			 crestaName.add(bean.getCrestaName().get(i));
			 currencyId.add(bean.getCurrencyId().get(i));
			 accCost.add(bean.getAccRisk().get(i));
			 teritoryId.add(bean.getTerritoryCode().get(i));
			 date.add(bean.getAccumulationDate().get(i));
			 crestaIDList.add(dropDownController.getCrestaIDList(branchCode,bean.getTerritoryCode().get(i)));
 			 crestaNameList.add(dropDownController.getCrestaNameList(branchCode,bean.getCrestaId().get(i)));
			}
			}
		}
		if(StringUtils.isBlank(crestauploadFileName)){
			addActionError(getText("upload.file.empty.error"));
		}
		else{
		index = crestauploadFileName.substring(crestauploadFileName.lastIndexOf(".") + 1);

		if(!index.equalsIgnoreCase("xls")&&!index.equalsIgnoreCase("xlsx")){
			addActionError(getText("upload.file.error"));
		}
		else{
			crestauploadFileName=StringHelper.getFileNameFormat(crestauploadFileName,pid);
		File fileToCreate = new File(FILE_PATH, this.crestauploadFileName);
        try {
			FileUtils.copyFile(this.crestaupload, fileToCreate);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("filepath:"+FILE_PATH+"/"+crestauploadFileName);
		new CSVConverter().csvConvertion(FILE_PATH+"/"+crestauploadFileName, CSV_PATH+"/"+crestauploadFileName, "dd/MM/yyyy", "\t");
        File csvFile=new File(CSV_PATH+"/"+crestauploadFileName);
        if(csvFile.exists() && csvFile.canRead()) {
    		String record = "";
    		long totalLinesProcessed = 0l;
    		long maxnum = Long.parseLong(bean.getMaxNum())-1;
    		String[] colum = null;
    		BufferedReader in = null;
    		try {
    			in = new BufferedReader(new FileReader(csvFile));
    			while ((record = in.readLine()) != null ) {
    				if(totalLinesProcessed <=maxnum){
    				System.out.println("status----->" + record);
    				totalLinesProcessed++;
    				record = record.replaceAll("\'", "");
    				record = record.replaceAll("\"", "");
    				record = record.replaceAll("[*]", "");
    				if("xls".equalsIgnoreCase(index)){
    					record = record.replaceAll("~", "");
    				}
    				if ((record.trim()).length() == 0)
    					break;
    				if (totalLinesProcessed != 1) {
    						colum = record.split("\t");
    						if(StringUtils.isNotBlank(colum[0])){
    							String status = service.CheckCrestaValue(bean,colum[0].replaceAll(".0", ""),"teritoryid","");
    							if(status.equalsIgnoreCase("")){
    								error.add(getText("teritory.id.xl",new String[] { String.valueOf(totalLinesProcessed) }));
    							}
    							teritoryId.add(status);
    	    					crestaIDList.add(dropDownController.getCrestaIDList(branchCode,status));
    						}
    						if(StringUtils.isNotBlank(colum[1])){
    							String status = service.CheckCrestaValue(bean,colum[1].replaceAll(".0", ""),"id","");
    							if(status.equalsIgnoreCase("0")){
    								error.add(getText("cresdta.id.xl",new String[] { String.valueOf(totalLinesProcessed) }));
    							}
    							 crestaId.add(colum[1].replaceAll(".0", ""));
    							 crestaName.add(service.CheckCrestaValue(bean,colum[1].replaceAll(".0", ""),"name",""));
    						}
    						/*if(StringUtils.isNotBlank(colum[2])){
    							String status =service.CheckCrestaValue(bean,colum[2].replaceAll(".0", ""),"name","");
    							if(status.equalsIgnoreCase("0")){
    								error.add(getText("cresdta.name.xl",new String[] { String.valueOf(totalLinesProcessed) }));
    							}
    						}*/
    						if(StringUtils.isNotBlank(colum[2])){
    							String status =service.CheckCrestaValue(bean,colum[2].replaceAll(".0", ""),"currency",countryId);
    							if(status.equalsIgnoreCase("")){
    								error.add(getText("cresdta.currency.xl",new String[] { String.valueOf(totalLinesProcessed) }));
    							}
    							currencyId.add(status);
    						}
    						 //crestaName.add(colum[2].replaceAll(".0", ""));
    						 date.add(colum[3]);
    						 String str = colum[4].substring(colum[4].indexOf("."));
    						 if(str.equalsIgnoreCase(".0")){
    							 accCost.add(String.valueOf(colum[4]).split("\\.")[0]);
    						 }
    						 else{
    							 accCost.add(String.valueOf(colum[4]));
    						 }
    						 //accCost.add(String.valueOf(colum[4]));//.split("\\.")[0]);//colum[5].substring(colum[5].lastIndexOf(".") + 1));
    						 bean.setErrorList(error);
    		    			crestaNameList.add(dropDownController.getCrestaNameList(branchCode,colum[1].replaceAll(".0", "")));
    				}
    			}
    		}
    		}
    			catch (Exception e) {
        			e.printStackTrace();
        		}
        		finally {
        			try {
        				in.close();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        		}
    			}
    		}
        }
    			bean.setCrestaId(crestaId);
    			bean.setCrestaName(crestaName);
    			bean.setCurrencyId(currencyId);
    			bean.setAccRisk(accCost);
    			bean.setTerritoryCode(teritoryId);
    			bean.setAccumulationDate(date);
    			bean.setCrestacount(bean.getCrestaId().size());
    			List<List<Map<String,Object>>> crestaList=new ArrayList<List<Map<String,Object>>>();
    				for(int i=0;i<bean.getCrestacount();i++){
    					Map<String,Object> doubleMap = new HashMap<String,Object>();
    					 List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
    					 doubleMap.put("one",new Double(1.0));
    					 creList.add(doubleMap);
    					 crestaList.add(creList);
    				}
    				bean.setCrestaIDList(crestaIDList);
    				bean.setCrestaNameList(crestaNameList);
    				bean.setCrestaList1(crestaList);
    			if(!hasActionErrors()){
    			bean.setImportflag("");
        }
		return "crestaPopUp";
	}

	public String insProfieCommission(){
		String forward="profitCommission";
		validationProfitCommission();
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		bean.setProduct_id(pid);
		bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			if(bean.getErrorList().size()<=0){
				String result = service.insertProfitCommission(bean);
				forward="streamResult";
				String value="<script type='text/javascript'>$('#companyModal1').modal('toggle');document.getElementById('referenceNo').value='"+(StringUtils.isBlank(bean.getReferenceNo())?"":bean.getReferenceNo())+"';</script>";
				byte[] byteArray = value.getBytes();
				inputStream=new ByteArrayInputStream(byteArray);
				}
			else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = bean.getErrorList().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
				for(int i=0;i<bean.getCommissionSNo().size();i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				}
				bean.setCommissionTypeBasedList(list);
				forward ="profitCommission";
			}
			return forward;
	}

	private void validationProfitCommission() {
		int j=1;
		List<String> list=new ArrayList<String>();
		List<String> list1=new ArrayList<String>();
			for(int i=0;i<bean.getCommissionSNo().size();i++){
				if(StringUtils.isBlank(bean.getCommissionFrom().get(i))){
					if("PR".equalsIgnoreCase(bean.getCommissionType()) || "PC".equalsIgnoreCase(bean.getCommissionType())){
						list.add(getText("com.error.from.val",new String[] { String.valueOf(i + 1) }));
					}
					else{
						list.add(getText("com.error.from.val.loss",new String[] { String.valueOf(i + 1) }));
					}
				}
				if(StringUtils.isBlank(bean.getCommissionTo().get(i)) ){
					if("PR".equalsIgnoreCase(bean.getCommissionType()) || "PC".equalsIgnoreCase(bean.getCommissionType())){
							list.add(getText("com.error.to.val",new String[] { String.valueOf(i + 1) }));
					}
					else{
						list.add(getText("com.error.to.val.loss",new String[] { String.valueOf(i + 1) }));
					}
				}
				if(StringUtils.isBlank(bean.getCommissionPer().get(i))){
					if("PR".equalsIgnoreCase(bean.getCommissionType()) || "LR".equalsIgnoreCase(bean.getCommissionType())){
							list.add(getText("com.error.lcb.val",new String[] { String.valueOf(i + 1) }));
					}
					else{
						list.add(getText("com.error.lcb.val.percent",new String[] { String.valueOf(i + 1) }));
					}
				}
				if(list.size()==3){
					list=new ArrayList<String>();
				}
				else{
				if(StringUtils.isNotBlank(bean.getCommissionFrom().get(i))){
					double val =Double.parseDouble(bean.getCommissionFrom().get(i).replace(",", ""));
					if(val>100){
						if("PR".equalsIgnoreCase(bean.getCommissionType()) || "PC".equalsIgnoreCase(bean.getCommissionType())){
								list.add(getText("com.error.from.limit",new String[] { String.valueOf(i + 1) }));
						}
						else{
							list.add(getText("com.error.from.limit.loss",new String[] { String.valueOf(i + 1) }));
						}
						}
			}
			if(StringUtils.isNotBlank(bean.getCommissionTo().get(i))){
				double val =Double.parseDouble(bean.getCommissionTo().get(i).replace(",", ""));
				if(val>100){
					if("PR".equalsIgnoreCase(bean.getCommissionType()) || "PC".equalsIgnoreCase(bean.getCommissionType())){
							list.add(getText("com.error.to.limit",new String[] { String.valueOf(i + 1) }));
					}
					else{
						list.add(getText("com.error.to.limit.loss",new String[] { String.valueOf(i + 1) }));

					}
				}
			}
			if(StringUtils.isNotBlank(bean.getCommissionPer().get(i))){
				double val =Double.parseDouble(bean.getCommissionPer().get(i).replace(",", ""));
				if(val>100){
					if("PR".equalsIgnoreCase(bean.getCommissionType()) || "LR".equalsIgnoreCase(bean.getCommissionType())){

							list.add(getText("com.per",new String[] { String.valueOf(i + 1) }));
					}
					else{
						list.add(getText("com.per.percent",new String[] { String.valueOf(i + 1) }));
					}
				}
			}
			if(StringUtils.isNotBlank(bean.getCommissionFrom().get(i)) && StringUtils.isNotBlank(bean.getCommissionTo().get(i))){
				if(Double.parseDouble(bean.getCommissionFrom().get(i).replace(",", ""))>Double.parseDouble(bean.getCommissionTo().get(i).replace(",", ""))){
					if("PR".equalsIgnoreCase(bean.getCommissionType()) || "PC".equalsIgnoreCase(bean.getCommissionType())){
						list.add(getText("com.from.to.value",new String[] { String.valueOf(i + 1) }));
					}
					else{
						list.add(getText("com.from.to.value.loss",new String[] { String.valueOf(i + 1) }));
					}
				}
			}
			if(bean.getCommissionSNo().size()>j){
				if(StringUtils.isNotBlank(bean.getCommissionFrom().get(j)) && StringUtils.isNotBlank(bean.getCommissionTo().get(i))){
				double from =Double.parseDouble(bean.getCommissionFrom().get(j).replace(",", ""));
				double to = Double.parseDouble(bean.getCommissionTo().get(i).replace(",", ""));
				if(to>from){
					if("PR".equalsIgnoreCase(bean.getCommissionType()) || "PC".equalsIgnoreCase(bean.getCommissionType())){
							list.add(getText("com.from.to.limit",new String[] { String.valueOf(i + 1) ,String.valueOf(i + 2)} ));
					}
					else{
						list.add(getText("com.from.to.limit.loss",new String[] { String.valueOf(i + 1) ,String.valueOf(i + 2)} ));
					}
				}
				}
			}

			for(int k=0;k<list.size();k++){
				list1.add(list.get(k));
				}
				list=new ArrayList<String>();
			}
			j++;
			}

			bean.setErrorList(list1);
	}
	public String getProfitEdit(){
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		bean.setProduct_id(pid);
		service.getprofitCommissionEdit(bean);
		//bean.setProfitCommissionList(service.ProfitCommissionList(bean));
		return "profitCommission";
	}
	public String getProfitDelete(){
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		bean.setProduct_id(pid);
		service.getprofitCommissionDelete(bean);
		//bean.setProfitCommissionList(service.ProfitCommissionList(bean));
		return "profitCommission";
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
	
	resetRetroList();
	//service.showSecondPageData(bean.getProposal_no(), bean, pid);
	if(StringUtils.isNotBlank(bean.getAmendId())&& Integer.parseInt(bean.getAmendId())>0){
		List<Integer> docList=new ArrayList<Integer>();
	for(int i=0;i<bean.getDocId().size();i++){
		docList.add(i);
	}
	bean.setDocuList(docList);
}
	return   "protreaty2";
}
public String CancelProposal(){
	if(!"1".equalsIgnoreCase(pid)){
	bean.setProposal_no(bean.getProposalNo());
	}
	service.CancelProposal(bean,bean.getRenewalProposalNo());
	return "cancelMenu";
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
public void getCedentRetention(){
	
	 bean.setRetTypeList(dropDownController.getConstantDropDown("45"));
	 bean.setBasicTypeList(dropDownController.getConstantDropDown("46"));
	 bean.setRetBusinessTypeList(dropDownController.getConstantDropDown("47"));
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
		Map<String,Object> doubleMap = new HashMap<String,Object>();
		 doubleMap.put("one",new Double(1.0));
		 result.add(doubleMap);
		 List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
	
		coversubdeptList.add(dropDownController.getSubProfitCentreDropDown("",branchCode,pid));
		bean.setRetdepartIdlist(dropDownController.getDepartmentDropDown(branchCode,(String)session.get("mfrid"),"Y","","","","",""));
		bean.setCoversubDepartList(coversubdeptList);
		bean.setRetList(result);
		bean.setLoopcount("0");
}
private void resetCedentRetention(){
	if(bean.getRetSNo()!=null && bean.getRetSNo().size()>0){
		List<String> coverdepartId = new ArrayList<String>();
		List<String> coversubdepartId = new ArrayList<String>();
		List<String> retType = new ArrayList<String>();
		List<String> retBasis = new ArrayList<String>();
		List<String> retBusinessType= new ArrayList<String>();
		List<String> firstretention = new ArrayList<String>();
		List<String> secondretention = new ArrayList<String>();
		List<String> retTreatyFST = new ArrayList<String>();
		List<String> retTreatySST = new ArrayList<String>();
		List<String> retEventFST = new ArrayList<String>();
		List<String> retEventSST = new ArrayList<String>();
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
		bean.setRetdepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
		bean.setRetTypeList(dropDownController.getConstantDropDown("45"));
		 bean.setBasicTypeList(dropDownController.getConstantDropDown("46"));
		 bean.setRetBusinessTypeList(dropDownController.getConstantDropDown("47"));
		for(int i=0;i<bean.getRetSNo().size();i++){
			coverdepartId.add(bean.getCoverdepartId().get(i));
			coversubdepartId.add(bean.getCoversubdepartId().get(i));
			retType.add(bean.getRetType().get(i));
			retBusinessType.add(bean.getRetBusinessType().get(i));
			retBasis.add(bean.getRetBasis().get(i));
			firstretention.add(bean.getFirstretention().get(i));
			secondretention.add(bean.getSecondretention().get(i));
			retTreatyFST.add(bean.getRetTreatyFST().get(i));
			retTreatySST.add(bean.getRetTreatySST().get(i));
			retEventFST.add(bean.getRetEventFST().get(i));
			retEventSST.add(bean.getRetEventSST().get(i));
			coversubdeptList.add(dropDownController.getSubProfitCentreDropDown(bean.getCoverdepartId().get(i),branchCode,pid));
			Map<String,Object> doubleMap = new HashMap<String,Object>();
			 doubleMap.put("one",new Double(1.0));
			 result.add(doubleMap);
		}
		bean.setCoverdepartId(coverdepartId);
		bean.setCoversubdepartId(coversubdepartId);
		bean.setRetType(retType);
		bean.setRetBusinessType(retBusinessType);
		bean.setRetBasis(retBasis);
		bean.setFirstretention(firstretention);
		bean.setSecondretention(secondretention);
		bean.setRetTreatyFST(retTreatyFST);
		bean.setRetTreatySST(retTreatySST);
		bean.setRetEventFST(retEventFST);
		bean.setRetEventSST(retEventSST);
		bean.setCoversubDepartList(coversubdeptList);
		 bean.setRetList(result);
		bean.setLoopcount(Integer.toString(result.size()));
	}
}
public String removeCedentRetention(){

	List<String> coverdepartId = new ArrayList<String>();
	List<String> coversubdepartId = new ArrayList<String>();
	List<String> retType = new ArrayList<String>();
	List<String> retBasis = new ArrayList<String>();
	List<String> retBusinessType= new ArrayList<String>();
	List<String> firstretention = new ArrayList<String>();
	List<String> secondretention = new ArrayList<String>();
	List<String> retTreatyFST = new ArrayList<String>();
	List<String> retTreatySST = new ArrayList<String>();
	List<String> retEventFST = new ArrayList<String>();
	List<String> retEventSST = new ArrayList<String>();
	List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
	bean.getRetSNo().remove(bean.getDeleteId());
	List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
	bean.setRetdepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
	bean.setRetTypeList(dropDownController.getConstantDropDown("45"));
	 bean.setBasicTypeList(dropDownController.getConstantDropDown("46"));
	 bean.setRetBusinessTypeList(dropDownController.getConstantDropDown("47"));
	for(int i=0;i<bean.getRetSNo().size();i++){
		 Map<String,Object> doubleMap = new HashMap<String,Object>();
		 doubleMap.put("one",new Double(1.0));
		 result.add(doubleMap);
		}
	bean.setRetList(result);
		int j=1;

		for(int k=0;k<bean.getRetSNo().size();k++){
			int value=Integer.parseInt(bean.getDeleteId());
			if(k<value){
				coverdepartId.add(bean.getCoverdepartId().get(k));
				coversubdepartId.add(bean.getCoversubdepartId().get(k));
				retType.add(bean.getRetType().get(k));
				retBasis.add(bean.getRetBasis().get(k));
				retBusinessType.add(bean.getRetBusinessType().get(k));
				firstretention.add(bean.getFirstretention().get(k));
				secondretention.add(bean.getSecondretention().get(k));
				retTreatyFST.add(bean.getRetTreatyFST().get(k));
				retTreatySST.add(bean.getRetTreatySST().get(k));
				retEventFST.add(bean.getRetEventFST().get(k));
				retEventSST.add(bean.getRetEventSST().get(k));
				coversubdeptList.add(dropDownController.getSubProfitCentreDropDown(bean.getCoverdepartId().get(k),branchCode,pid));
			}
			else{
			if(StringUtils.isNotBlank(bean.getCoverdepartId().get(j))){
				coverdepartId.add(bean.getCoverdepartId().get(j));
			}
			if(StringUtils.isNotBlank(bean.getCoversubdepartId().get(j))){
				coversubdepartId.add(bean.getCoversubdepartId().get(j));
				coversubdeptList.add(dropDownController.getSubProfitCentreDropDown(bean.getCoverdepartId().get(j),branchCode,pid));
			}else{
				coversubdeptList.add(dropDownController.getSubProfitCentreDropDown("",branchCode,pid));
			}
			if(StringUtils.isNotBlank(bean.getRetType().get(j))){
				retType.add(bean.getRetType().get(j));
			}
			if(StringUtils.isNotBlank(bean.getRetBasis().get(j))){
				retBasis.add(bean.getRetBasis().get(j));
			}
			if(StringUtils.isNotBlank(bean.getRetBusinessType().get(j))){
				retBusinessType.add(bean.getRetBusinessType().get(j));
			}
			if(StringUtils.isNotBlank(bean.getFirstretention().get(j))){
				firstretention.add(bean.getFirstretention().get(j));
			}
			if(StringUtils.isNotBlank(bean.getSecondretention().get(j))){
				secondretention.add(bean.getSecondretention().get(j));
			}
			if(StringUtils.isNotBlank(bean.getRetTreatyFST().get(j))){
				retTreatyFST.add(bean.getRetTreatyFST().get(j));
			}
			if(StringUtils.isNotBlank(bean.getRetTreatySST().get(j))){
				retTreatySST.add(bean.getRetTreatySST().get(j));
			}
			if(StringUtils.isNotBlank(bean.getRetEventFST().get(j))){
				retEventFST.add(bean.getRetEventFST().get(j));
			}
			if(StringUtils.isNotBlank(bean.getRetEventSST().get(j))){
				retEventSST.add(bean.getRetEventSST().get(j));
			}

			}
			j++;
		}
		bean.setCoverdepartId(coverdepartId);
		bean.setCoversubdepartId(coversubdepartId);
		bean.setRetType(retType);
		bean.setRetBasis(retBasis);
		bean.setFirstretention(firstretention);
		bean.setSecondretention(secondretention);
		bean.setRetTreatyFST(retTreatyFST);
		bean.setRetTreatySST(retTreatySST);
		bean.setRetEventFST(retEventFST);
		bean.setRetEventSST(retEventSST);
		bean.setCoversubDepartList(coversubdeptList);
		 bean.setRetList(result);
		bean.setLoopcount(Integer.toString(result.size()));
	return "dropdownajax";
}
private void validationCedentRetention() {
	try{
		List<String> error=new ArrayList<String>();
		for(int i=0;i<bean.getRetSNo().size();i++){
			if(StringUtils.isBlank(bean.getCoverdepartId().get(i))){
				error.add(getText("error.ret.coverdept",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getCoversubdepartId().get(i))){
				error.add(getText("error.ret.coversubdept",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getRetBusinessType().get(i))){
				error.add(getText("error.ret.retbusinessType",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getRetType().get(i))){
				error.add(getText("error.ret.retType",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getRetBasis().get(i))){
				error.add(getText("error.ret.retBasis",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getFirstretention().get(i))){
				error.add(getText("error.ret.firstret",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getSecondretention().get(i))){
				error.add(getText("error.ret.secobdret",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getRetTreatyFST().get(i))){
				error.add(getText("error.ret.retTreatyFST",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getRetTreatySST().get(i))){
				error.add(getText("error.ret.retTreatySST",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getRetEventFST().get(i))){
				error.add(getText("error.ret.retEventFST",new String[]{String.valueOf(i+1)}));
			}
			if(StringUtils.isBlank(bean.getRetEventSST().get(i))){
				error.add(getText("error.ret.retEventFST",new String[]{String.valueOf(i+1)}));
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
public String GetRetentionDetails(){
	bean.setRetdepartIdlist(dropDownController.getDepartmentDropDown(branchCode,pid,"Y","","","","",""));
	bean.setRetTypeList(dropDownController.getConstantDropDown("45"));
	 bean.setBasicTypeList(dropDownController.getConstantDropDown("46"));
	 bean.setRetBusinessTypeList(dropDownController.getConstantDropDown("47"));
	bean.setBranchCode(branchCode);
	bean.setProduct_id(pid);
	service.getRetentionDetails(bean);
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
public List<Map<String, Object>> getSectionInfo(){
	return new XolService().getLayerInfo(bean);
}
public  String EditClass(){
	String forward="protreaty1";
	String editMode ="N";
	String Status="";
	try {
		if( !"edit".equalsIgnoreCase(bean.getMultiuserMode()) && StringUtils.isNotBlank(bean.getProposal_no())){
		editMode = dropDownController.EditModeStatus(bean.getProposal_no(),"0");
	}
	if(!"N".equalsIgnoreCase(editMode)){
		forward ="portfoliList";	
		bean.setMultiuserError("error");
	}
	else{
	bean.setLayerProposalNo(bean.getProposalNo1());
	bean.setProposal_no(bean.getProposalNo1());
	bean.setBranchCode(branchCode);
	bean.setShortname(service.getShortname(bean));
	final String contractNo=bean.getRenewal_contract_no();
	final String layerNo=bean.getLayerNo();
	bean.setLay1(layerNo);
	bean.setContractno1(contractNo);
	bean.setProposalNo1(bean.getProposal_no());
	bean.setBaseLayer(bean.getProposal_no());
	/*if (session.get("EditProposalNo") != null) {
		bean.setProposal_no(session.get("EditProposalNo").toString());
	}*/
	if(StringUtils.isNotBlank(bean.getLayerProposalNo())){
	bean.setLayerProposalNo(bean.getProposal_no());
	}
	//if("layer".equalsIgnoreCase(layerNo)){
	bean.setRenewalProposalNo(bean.getProposal_no());
		String proposalNo = new DropDownControllor().getCopyQuote("Copy",pid, bean.getBranchCode(), bean.getProposal_no());
		bean.setProposal_no(proposalNo);bean.setProposalReference("Layer");
	//}

	if(StringUtils.isNotBlank(bean.getLayerProposalNo())){
		//session.put("EditProposalNo",bean.getLayerProposalNo());
		bean.setProposal_no(bean.getLayerProposalNo());
	}
		final String pid = (String) session.get("mfrid");
		session.put("Product", pid);
		final int CheckEditMode = service.getEditMode(bean.getProposal_no());
		if (CheckEditMode == 2) {
			bean.setAmend_Id_Mode("true");
		}
		if (bean.getProposal_no().equalsIgnoreCase("")) {
			ShowDropDown();
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
					ShowDropDown();
				} else {
					ShowDropDown();
					addActionError(getText("error.proposal.notAvilable"));
				}
			} else {
				if(StringUtils.isBlank(bean.getDepartId())){
					bean.setSubProfitList(new ArrayList<Map<String,Object>>());
				}else{
					bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
				}
				ShowDropDown();
				addActionError(getText("error.proposal.notAvilable"));
			}
		}
		if(!"layer".equalsIgnoreCase(layerNo)){
		//bean.setProposal_no("");
		}
		bean.setAmendId("0");
		bean.setLayerMode("Yes");
		bean.setCopyQuoteMode("layer");
		//bean.setLayerNo("");
		bean.setYearList(getYearList());
		bean.setYearToList(getYearToList());
		bean.setAmend_Id_Mode("");
		//service.BaseLayerStatus(bean,pid);
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
public String EditSection(){
	String forward ="protreaty1";
	String Status ="";
	try {

	bean.setLayerProposalNo(bean.getProposalNo1());
	bean.setProposal_no(bean.getProposalNo1());
	bean.setProposalNo1("");
	
	ShowDropDown();
	bean.setBranchCode(branchCode);
	bean.setShortname(service.getShortname(bean));
		
		bean.setMenuStatus("N");
		if(StringUtils.isNotBlank(bean.getDepartmentId())){
			session.put("DepartmentId",bean.getDepartmentId());
		}
		if(StringUtils.isBlank(bean.getMode())|| "edit".equalsIgnoreCase(bean.getMode())){
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
		else if("Renewal".equalsIgnoreCase(bean.getReMode())){
			bean.setRenewalProposalNo(bean.getProposal_no());
			bean.setProposal_no(new DropDownControllor().getRenewalCopyQuote("Renewal",pid, branchCode,bean.getProposal_no() ));
			bean.setRenewalMode("RenewalMode");
			bean.setAmend_Id_Mode("");
			bean.setMode("");
			bean.setProposalReference("Renewal");
		}
		bean.setProduct_id(pid);
		service.riskEditMode(bean, false);
		if("Renewal".equalsIgnoreCase(bean.getReMode()) && (StringUtils.isNotBlank(bean.getBaseLayer()) && !hasActionErrors())){
			dropDownController.updateSubClass(bean.getProposal_no(),"Renewal");
		}
		//service.riskEditMode(bean, false);
		bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartId(),branchCode,pid));
		RdsCalculation.SecondPageCaculation(bean,pid);
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
		if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
			String proposal = dropDownController.getBaseProposal(bean.getProposal_no());
			dropDownController.updateEditMode(proposal,"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SR":"SE",bean.getProposal_no());
			dropDownController.updateSubEditMode(proposal,"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"SR":"SE",bean.getProposal_no());
		}
		else{
			dropDownController.updateSubEditMode(bean.getProposal_no(),"Renewal".equalsIgnoreCase(bean.getRenewalEditMode()) ?"BR":"BE",bean.getProposal_no());
		}
		
		if(bean.getRetList()==null){
			getCedentRetention();
		}
	
	bean.setYearList(getYearList());
	bean.setYearToList(getYearToList());
	bean.setPaymentPartnerlist(dropDownController.getPaymentPartnerlist(branchCode,bean.getCedingCo(),bean.getBroker()));
	if(StringUtils.isNotBlank(bean.getContractListVal())){
		getContractListVal();
	}
	if(StringUtils.isNotBlank(bean.getContNo())){
		bean.setProdisableStatus("Y");
	}
	if("layer".equals(bean.getLayerMode())) {
		bean.setProposal_no(bean.getProposalNo());
	}
	if("copy".equals(bean.getFlag())) {
		//bean.setSectionNo("");
		bean.setTreatyName_type("");
		bean.setDepartId("");
		bean.setSubProfit_center("");
		//bean.setProposal_no("");
	}
	if("Y".equals(bean.getBouquetModeYN()) && StringUtils.isNotBlank(bean.getBouquetNo())) {
		dropDownController.getBouquetCedentBrokerInfo(bean);
	}
	} catch (Exception e) {
		logger.debug("Exception @ {" + e + "}");

	}
	return forward;
}
public String CancelSection(){
	if(!"1".equalsIgnoreCase(pid)){
	bean.setProposal_no(bean.getProposalNo());
	}
	bean.setProposalNo1("");
	ShowDropDown();
	service.riskEditMode(bean, false);
	bean.setYearList(getYearList());
	bean.setYearToList(getYearToList());
	bean.setPaymentPartnerlist(dropDownController.getPaymentPartnerlist(branchCode,bean.getCedingCo(),bean.getBroker()));
	service.CancelProposal(bean,bean.getRenewalProposalNo());
	return "protreaty1";
}
public String sectionView() {
	bean.setProduct_id(pid);
	bean.setProposal_no(bean.getProposalNo1());
	service.riskEditMode(bean, false);
	ShowDropDown();
	return "sectionview";
	
}
public String calculateSC() {
	validateSCCalculate(bean);
	if(CollectionUtils.isEmpty(bean.getErrorList())) {
	service.getcalculateSC(bean);
	}
	return "scalePopUp";
	
}
private void validateSCCalculate(RiskDetailsBean bean) {
	List<String> list=new ArrayList<String>();
	if(StringUtils.isBlank(bean.getScalementhod())) {
		list.add(getText("error.scale.method"));
	}
	if(StringUtils.isBlank(bean.getScaleminRatio())) {
		list.add(getText("error.scale.minratio"));
	}
	if(StringUtils.isBlank(bean.getScalemaxRatio())) {
		list.add(getText("error.scale.maxratio"));
	}
	if(StringUtils.isBlank(bean.getScalecombine())) {
		list.add(getText("error.scale.combinedratio"));
	}
	if(StringUtils.isBlank(bean.getScalebanding())) {
		list.add(getText("error.scale.banding"));
	}
	if(StringUtils.isBlank(bean.getScaledigit())) {
		list.add(getText("error.scale.digit"));
	}
	if(StringUtils.isNotBlank(bean.getScalecombine()) && StringUtils.isNotBlank(bean.getScalemaxRatio())) {
		if(Double.parseDouble(bean.getScalemaxRatio())>Double.parseDouble(bean.getScalecombine())) {
			list.add(getText("error.scale.max.cobine.valid"));
		}
	}
	if(StringUtils.isNotBlank(bean.getScaleminRatio()) && StringUtils.isNotBlank(bean.getScalemaxRatio())) {
		if(Double.parseDouble(bean.getScalemaxRatio())>Double.parseDouble(bean.getScalecombine())) {
			list.add(getText("error.scale.max.cobine.valid"));
		}
		Double minus=(Double.parseDouble(bean.getScalemaxRatio())-Double.parseDouble(bean.getScaleminRatio()))/Double.parseDouble(bean.getScalebanding());
		boolean result = (minus - Math.floor(minus)) != 0; 
		if(result) {
			list.add(getText("error.scale.max.cobine.valid"));
		}
	}
	bean.setErrorList(list);
}
}

