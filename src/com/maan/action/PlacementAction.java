package com.maan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;

import com.maan.bean.PlacementBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.service.PlacementService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PlacementAction extends ActionSupport implements ModelDriven<PlacementBean>{
	/**
	 * 
	 */
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
		return bean;
	}
	DropDownControllor dropDownController=new DropDownControllor();
	public String init() {
		String forward="placement";
		service.proposalInfo(bean);
		//bean.setPlacementInfoList(service.getPlacingInfo(bean));
		if(!CollectionUtils.isEmpty(bean.getPlacementInfoList())) {
			 forward="placementList";
		}else {
			bean.setReinsurerInfoList(service.getReinsurerInfo(bean));
			if(CollectionUtils.isEmpty(bean.getReinsurerInfoList())) {
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				for(int i=0;i<2;i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				}
				bean.setReinsurerInfoList(list);
				bean.setMode("placing");
			}else {
				bean.setMode("mail");
				//bean.setMailType("PLACING");
				//service.getMailTemplate(bean);
			}
		}
		return forward;
		
	}
	public List<Map<String,Object>>getReinsurerList(){
		return dropDownController.getPersonalInfoDropDown(branchCode,"R",pid);
	}
	public List<Map<String,Object>>getBrokerList(){
		return dropDownController.getPersonalInfoDropDown(branchCode,"B",pid);
	}
	public List<Map<String,Object>> getBouquetExistingList(){
		return dropDownController.getBouquetExistingList(branchCode,bean.getBouquetNo(),bean.getBouquetModeYN());
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
	public String removeRow(){
		String forward = "dropdownajax";
		List<String> reinsureName=new ArrayList<String>();
		List<String> placingBroker=new ArrayList<String>();
		List<String> shareOffer=new ArrayList<String>();
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
					reinsureName.add(bean.getReinsureName().get(k));
					placingBroker.add(bean.getPlacingBroker().get(k));
					shareOffer.add(bean.getShareOffer().get(k));
				}
				else{
				if(StringUtils.isNotBlank(bean.getReinsureName().get(j))){
					reinsureName.add(bean.getReinsureName().get(j));
				}
				if(StringUtils.isNotBlank(bean.getPlacingBroker().get(j))){
					placingBroker.add(bean.getPlacingBroker().get(j));
							}
				if(StringUtils.isNotBlank(bean.getShareOffer().get(j))){
					shareOffer.add(bean.getShareOffer().get(j));
				}
				}
				j++;
			}
			bean.setReinsureName(reinsureName);
			bean.setPlacingBroker(placingBroker);
			bean.setShareOffer(shareOffer);
			bean.setReinsurerInfoList(list);
		
		return forward;
	}
	public String savePlacing() {
		validatePlacing();
		if(!hasActionErrors()) {
			service.savePlacing(bean);
			bean.setReinsurerInfoList(service.getPlacingInfo(bean));
			bean.setMode("mail");
		}else {
			bean.setMode("placing");
		}
		return "placement";
	}
	private void validatePlacing() {
		// TODO Auto-generated method stub
		
	}
	public String updateInfo() {
		bean.setCurrentStatus("O");
		service.proposalInfo(bean);
		bean.setPlacementeditInfo(service.editPlacingDetails(bean));
		return "placementStatus";
		 
	}
	public String updateStatus() {
		String forward="placementStatus";
		validationStatus();
		if(!hasActionErrors()) {
			service.updatePlacement(bean);
			bean.setPlacementInfoList(service.getPlacingInfo(bean));
			forward= "placementList";
		}else {
			service.proposalInfo(bean);	
		}
		return forward;
		
	}
	private void validationStatus() {
		// TODO Auto-generated method stub
		
	}
	public String getStatusChange() {
		bean.setPlacementeditInfo(service.editPlacingDetails(bean));
		return "dropdownajax";
	}
	public String getMailTemplate() {
		bean.setMode("template");
		service.getMailTemplate(bean);
		bean.setReinsurerInfoList(service.getReinsurerInfo(bean));
		return "placement";
	}
}
