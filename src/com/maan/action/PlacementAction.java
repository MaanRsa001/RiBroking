package com.maan.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;

import com.maan.bean.PlacementBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.service.PlacementService;
import com.maan.service.UploadService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PlacementAction extends ActionSupport implements ModelDriven<PlacementBean>{
	/**
	 * 
	 */
	Logger logger = LogUtil.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	Map<String,Object> session = ActionContext.getContext().getSession();
	private String branchCode=session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	private String countryId=session.get("countryId")==null?"":session.get("countryId").toString();
	private String userId=(String) session.get("UserId")==null?"":(String) session.get("UserId");
	private static final Logger LOGGER = LogUtil.getLogger(PlacementAction.class);
	PlacementBean bean=new  PlacementBean();
	final String pid = session.get("mfrid") == null ? "" :(String) session.get("mfrid");
	PlacementService service=new PlacementService();
	@Override
	public PlacementBean getModel() {
		bean.setBranchCode(branchCode);
		bean.setUserId(userId);
		bean.setProductId(pid);
		return bean;
	}
	 private InputStream inputStream;
	 
	 
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	DropDownControllor dropDownController=new DropDownControllor();
	public List<Map<String,Object>>getReinsurerList(){
		return dropDownController.getPersonalInfoDropDown(branchCode,"R",pid);
	}
	public List<Map<String,Object>>getBrokerList(){
		return dropDownController.getPersonalInfoDropDown(branchCode,"B",pid);
	}
	public List<Map<String,Object>> getBouquetExistingList(){
		return dropDownController.getBouquetExistingList(branchCode,bean.getBouquetNo(),bean.getBouquetModeYN());
	}
	public List<Map<String,Object>> getBaseLayerExistingList(){
		return dropDownController.getBaseLayerExistingList(branchCode,bean.getBaseProposalNo());
	}
	public List<Map<String,Object>>getEmailByList(){
		return dropDownController.getConstantDropDown("55");
	}
	public List<Map<String,Object>>getStatusList(){
		return dropDownController.getStatusDropDown(branchCode);
	}
	public List<Map<String,Object>>getSubStatusList(){
		return dropDownController.getSubStatusDropDown(branchCode,bean.getCurrentStatus());
	}
	public List<Map<String,Object>>getPlacedProposalList(){
		return dropDownController.getPlacedProposalList(bean);
	}
	public List<Map<String,Object>>getNotPlacedProposalList(){
		return dropDownController.getNotPlacedProposalList(bean);
	}
	public List<Map<String,Object>>getMailToList(){
		return service.getMailToList(bean);
	}
	public List<Map<String,Object>>getExistingReinsurerList(){
		return service.getExistingReinsurerList(bean);
	}
	public List<Map<String,Object>>getExistingBrokerList(){
		return service.getExistingBrokerList(bean);
	}
	public List<Map<String,Object>>getExistingAttachList(){
		return service.getExistingAttachList(bean);
	}
	
	public List<Map<String,Object>>getDocTypeList(){
		return new UploadService().getDocType(branchCode,"12","PL");
	}
	public String init() {
		String forward="placement";
		service.proposalInfo(bean);
		bean.setReinsurerInfoList(service.getReinsurerInfo(bean));
		if(CollectionUtils.isEmpty(bean.getReinsurerInfoList())) {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			List<String> reinsSNo=new ArrayList<String>();
			for(int i=0;i<1;i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			reinsSNo.add("1");
			}
			bean.setReinsurerInfoList(list);
			bean.setReinsSNo(reinsSNo);
			bean.setMode("placing");
			if(StringUtils.isBlank(bean.getReinsurerType()))
			bean.setReinsurerType("Y");
		}else {
			bean.setMode("placing");
			if(StringUtils.isBlank(bean.getReinsurerType()))
			bean.setReinsurerType("N");
		}
		
		return forward;
		
	}
	public String summary() {
		String forward="placementList";
		service.proposalInfo(bean);
		bean.setPlacementInfoList(service.getPlacementInfoList(bean));
		if(StringUtils.isBlank(bean.getSearchType())) {
			bean.setSearchReinsurerId("");
			bean.setSearchBrokerId("");
			bean.setSearchStatus("");
		}
		return forward;
	}
	
	public String removeRow(){
		String forward = "dropdownajax";
		List<String> reinsSNo=new ArrayList<String>();
		List<String> reinsureName=new ArrayList<String>();
		List<String> placingBroker=new ArrayList<String>();
		List<String> shareOffer=new ArrayList<String>();
		List<String> deleteStatus=new ArrayList<String>();
		List<String> changeStatus=new ArrayList<String>();
			bean.getReinsSNo().remove(bean.getDeleteId());
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for(int i=0;i<bean.getReinsSNo().size();i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			}
			int j=1;

			for(int k=0;k<bean.getReinsSNo().size();k++){
				int value=Integer.parseInt(bean.getDeleteId());
				if(k<value){
					reinsSNo.add(bean.getReinsSNo().get(k));
					reinsureName.add(bean.getReinsureName().get(k));
					placingBroker.add(bean.getPlacingBroker().get(k));
					shareOffer.add(bean.getShareOffer().get(k));
					deleteStatus.add(bean.getDeleteStatus().get(k));
					changeStatus.add(bean.getChangeStatus().get(k));
				}
				else{
					if(StringUtils.isNotBlank(bean.getReinsSNo().get(j))){
						reinsSNo.add(bean.getReinsSNo().get(j));
					}
				if(StringUtils.isNotBlank(bean.getReinsureName().get(j))){
					reinsureName.add(bean.getReinsureName().get(j));
				}
				if(StringUtils.isNotBlank(bean.getPlacingBroker().get(j))){
					placingBroker.add(bean.getPlacingBroker().get(j));
							}
				if(StringUtils.isNotBlank(bean.getShareOffer().get(j))){
					shareOffer.add(bean.getShareOffer().get(j));
				}
				if(StringUtils.isNotBlank(bean.getDeleteStatus().get(j))){
					shareOffer.add(bean.getDeleteStatus().get(j));
				}
				if(StringUtils.isNotBlank(bean.getChangeStatus().get(j))){
					shareOffer.add(bean.getChangeStatus().get(j));
				}
				}
				j++;
			}
			bean.setReinsSNo(reinsSNo);
			bean.setReinsureName(reinsureName);
			bean.setPlacingBroker(placingBroker);
			bean.setShareOffer(shareOffer);
			bean.setReinsurerInfoList(list);
		
		return forward;
	}
	public String savePlacing() {
		String forward="placement";
		validatePlacing();
		if(!hasActionErrors()) {
			if(!"Next".equals(bean.getMode()))
			service.savePlacing(bean);			
			if("Submit".equals(bean.getMode())) {
				forward="pendingList";
			}else if("Save".equals(bean.getMode())) {
				bean.setMode("placing");
				bean.setReinsurerType("N");
				init();
			}else {
				service.proposalInfo(bean);
				bean.setReinsurerInfoList(service.getPlacingInfo(bean));
				bean.setMode("mail");
			}
			
		}else {
			service.proposalInfo(bean);
			bean.setMode("placing");
		}
		return forward;
	}
	private void validatePlacing() {
		if(StringUtils.isNotBlank(bean.getBouquetNo()) || StringUtils.isNotBlank(bean.getBaseProposalNo())) {
			if(StringUtils.isBlank(bean.getPlacementMode())) {
				addActionError(getText("error.placementmode.required"));
			}
			else if("S".equals(bean.getPlacementMode())) {
				if(StringUtils.isBlank(bean.getNotplacedProposal()) && StringUtils.isBlank(bean.getPlacedProposal())) {
					addActionError(getText("error.placednotpalced.required"));
				}
			}
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(!CollectionUtils.isEmpty(bean.getReinsSNo())) {
			List<String>rebrlist=new ArrayList<String>();
		for(int i=0;i<bean.getReinsSNo().size();i++) {
			if(StringUtils.isBlank(bean.getReinsureName().get(i))) {
				addActionError(getText("error.reinsuere.required")+" "+(i+1));
			}
			if(StringUtils.isBlank(bean.getPlacingBroker().get(i))) {
				addActionError(getText("error.placingbroker.required")+" "+(i+1));
			}
			
			if(StringUtils.isNotBlank(bean.getReinsureName().get(i)) && StringUtils.isNotBlank(bean.getPlacingBroker().get(i))) {
				rebrlist.add(bean.getReinsureName().get(i)+"~"+bean.getPlacingBroker().get(i));
			}
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			
		}
		bean.setReinsurerInfoList(list);
		int count=0;
		for(int i=0;i<bean.getReinsSNo().size();i++) {
			String rebr=bean.getReinsureName().get(i)+"~"+bean.getPlacingBroker().get(i);
			if (StringUtils.isNotEmpty(rebr)) {
				if (java.util.Collections.frequency(rebrlist, rebr) > 1) {
					if(count==0) {
					addActionError(getText("error.placingbroker.duplicate"));
					count=1;
					}
				}
			}
		} 
	}
		
	}
	public String updateInfo() {
		//bean.setCurrentStatus("O");
		//service.proposalInfo(bean);
		List<Integer> docList=new ArrayList<Integer>();
		for(int i=0;i<1;i++)
			docList.add(i);
		bean.setDocuList(docList);
		bean.setPlacementeditInfo(service.editPlacingDetails(bean));
		service.proposalInfo(bean);
		return "placementStatus";
		 
	}
	
	public String updateStatus() {
		String forward="placementStatus";
		validationStatus();
		if(!hasActionErrors()) {
			service.updatePlacement(bean);
			bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
			service.uploadDocument(bean);
			if("email".equals(bean.getMode())) {
				bean.setMailType(bean.getNewStatus());
				getMailTemplate();
				forward="placement";
			}else {
				bean.setSearchReinsurerId("");
				bean.setSearchStatus("");
				bean.setSearchBrokerId("");
				bean.setSearchType("");
			bean.setPlacementInfoList(service.getPlacementInfoList(bean));
			forward= "placementList";
			}
			
		}else {
			service.proposalInfo(bean);
			List<Integer> docList=new ArrayList<Integer>();
			for(int i=0;i<1;i++)
				docList.add(i);
			bean.setDocuList(docList);
			restPlacement();
		}
		return forward;
		
	}
	private void restPlacement() {
		if(!CollectionUtils.isEmpty(bean.getProposalNos())){
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
			for(int i=0;i<bean.getProposalNos().size();i++){
				
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
			}
			
			bean.setPlacementeditInfo(result);
		}
	}
	private void validationStatus() {
		if(StringUtils.isBlank(bean.getEmailBy())) {
			addActionError(getText("error.emailBy.required"));
		}if(StringUtils.isBlank(bean.getUpdateDate())) {
			addActionError(getText("error.updatedate.required"));
		}if(StringUtils.isBlank(bean.getNewStatus())) {
			addActionError(getText("error.newstatus.required"));
		}else {
			if(!CollectionUtils.isEmpty(bean.getProposalNos())) {
				for(int i=0;i<bean.getProposalNos().size();i++) {
					if("P".equals(bean.getNewStatus())) {
						if(StringUtils.isBlank(bean.getShareOffered().get(i))) {
							addActionError(getText("error.shareoffer.required")+" "+(i+1));
						}
					}else if("A".equals(bean.getNewStatus())) {
						if(StringUtils.isBlank(bean.getWrittenLine().get(i))) {
							addActionError(getText("error.writtenLine.required")+" "+(i+1));
						}if(StringUtils.isBlank(bean.getWrittenvaliditydate().get(i))) {
							addActionError(getText("error.writtenvaliditydate.required")+" "+(i+1));
						}if(StringUtils.isBlank(bean.getWrittenvalidityRemarks().get(i))) {
							addActionError(getText("error.writtenvalidityRemarks.required")+" "+(i+1));
						}if(StringUtils.isBlank(bean.getBrokerage().get(i))) {
							addActionError(getText("error.brokeragep.required")+" "+(i+1));
						}
					}else if("RO".equals(bean.getNewStatus())) {
						if(StringUtils.isBlank(bean.getReoffer().get(i))) {
							addActionError(getText("error.reoffer.required")+" "+(i+1));
						}
					}else if("PWL".equals(bean.getNewStatus())) {
						if(StringUtils.isBlank(bean.getProposedWL().get(i))) {
							addActionError(getText("error.proposedWL.required")+" "+(i+1));
						}else {
							if(StringUtils.isNotBlank(bean.getWrittenLine().get(i))) {
								if(Double.parseDouble(bean.getProposedWL().get(i))>Double.parseDouble(bean.getWrittenLine().get(i))) {
									addActionError(getText("error.proposedWL.valid")+" "+(i+1));	
								}
							}
						}
					}else if("SL".equals(bean.getNewStatus())) {
						if(StringUtils.isBlank(bean.getSignedLine().get(i))) {
							addActionError(getText("error.signedLine.required")+" "+(i+1));
						}else {
							if(StringUtils.isNotBlank(bean.getWrittenLine().get(i))) {
								if(Double.parseDouble(bean.getSignedLine().get(i))>Double.parseDouble(bean.getWrittenLine().get(i))) {
									addActionError(getText("error.signedLine.valid")+" "+(i+1));	
								}
							}
						}if(StringUtils.isBlank(bean.getSignedLineValidity().get(i))) {
							addActionError(getText("error.signedLineValidity.required")+" "+(i+1));
						}if(StringUtils.isBlank(bean.getSignedLineRemarks().get(i))) {
							addActionError(getText("error.signedLineRemarks.required")+" "+(i+1));
						}
					}else if("PSL".equals(bean.getNewStatus())) {
						if(StringUtils.isBlank(bean.getProposedSL().get(i))) {
							addActionError(getText("error.proposedSL.required")+" "+(i+1));
						}
					}else if("CSL".equals(bean.getNewStatus())) {
						if(StringUtils.isBlank(bean.getSignedLine().get(i))) {
							addActionError(getText("error.signedLine.required")+" "+(i+1));
						}if(StringUtils.isNotBlank(bean.getPsignedLine().get(i))) {
							if(Double.parseDouble(bean.getSignedLine().get(i))>Double.parseDouble(bean.getPsignedLine().get(i))) {
								addActionError(getText("error.psignedLine.valid")+" "+(i+1));	
							}
						}
						
					}
				
			}
		}
		}
		
	}
	public String getStatusChange() {
		bean.setPlacementeditInfo(service.editPlacingDetails(bean));
		return "dropdownajax";
	}
	public String getMailTemplate() {
		bean.setMode("template");
		service.getMailTemplate(bean);
		List<Integer> docList=new ArrayList<Integer>();
		for(int i=0;i<1;i++)
			docList.add(i);
		bean.setDocuList(docList);
		//bean.setReinsurerInfoList(service.getReinsurerInfo(bean));
		return "placement";
	}
	public String sendMail() {
		String forward="placement";
		validateMail();
		if(!hasActionErrors()) {
		bean.setMode("mail");
		service.proposalInfo(bean);
		bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
		service.attachFile(bean);
		service.sendMail(bean);
		bean.setReinsurerInfoList(service.getPlacingInfo(bean));
		if("PWL".equalsIgnoreCase(bean.getMailType())) {
			forward=summary();
		}
		}else {
			forward=getMailTemplate();
		}
		
		return forward;
	}
	private void validateMail() {
		if(StringUtils.isBlank(bean.getMailTo())) {
			addActionError(getText("error.mailto.required"));
		}
		
	}
	public String mailInfo() {
		String forward="placement";
		if("PWL".equalsIgnoreCase(bean.getMailType())) {
			forward=summary();
		}else {
			bean.setMode("mail");
			service.proposalInfo(bean);
			bean.setReinsurerInfoList(service.getPlacingInfo(bean));
		}
		return forward;
	}
	public String getreinsurerInfo() { 
		bean.setReinsurerInfoList(service.getReinsurerInfo(bean));
		if(CollectionUtils.isEmpty(bean.getReinsurerInfoList())) {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for(int i=0;i<1;i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			}
			bean.setReinsSNo(new ArrayList<String>());
			bean.setReinsureName(new ArrayList<String>());
			bean.setPlacingBroker(new ArrayList<String>());
			bean.setShareOffer(new ArrayList<String>());
			bean.setMailStatus(new ArrayList<String>());
			bean.setProposalNos(new ArrayList<String>());
			bean.setReinsurerInfoList(list);
		} 
		return "dropdownajax";
	}
	public String attachFile() {
		bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
		String result=service.attachFile(bean);
		bean.setMode("template");
		service.getMailTemplate(bean);
		return "placement";
	}
	public String deleteFile() {
		String result=service.deleteFile(bean);
		bean.setMode("template");
		service.getMailTemplate(bean);
		return "placement";
	}
	public String downloadFile() {
		try {
			String filePath=ServletActionContext.getServletContext().getRealPath("/")+"documents/";
			String orginalFile=service.downloadFile(bean);
			inputStream = new FileInputStream(filePath+orginalFile);

		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug("Exception @ { " + e + " } ");
		}
		return "stream";
	}
	public String uploadDoc() {
		bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
		service.attachFile(bean);
		updateInfo();
		return "placementStatus";
	}
	public String viewInfo() {
		bean.setMode("viewlist");
		bean.setPlacementviewInfo(service.getPlacementViewList(bean));
		return "placementView";
	}
	public String viewDetail() {
		service.getPlacementView(bean);
		return "placementView";
	}
	public String plSummary() {
		bean.setPlSummaryInfo(service.placementSummary(bean));
		return "placementSummary";
	}
	
}
