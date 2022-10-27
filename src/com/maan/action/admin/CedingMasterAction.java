package com.maan.action.admin;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;


import com.maan.bean.admin.CedingMasterBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.service.admin.CedingMasterService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CedingMasterAction extends ActionSupport implements ModelDriven<CedingMasterBean>{
	private static final String PATH="path";
	private static final long serialVersionUID = 1L;
	Map<String,Object> session = ActionContext.getContext().getSession();
	final String pid = (String) session.get("mfrid");
	private String branchCode=session.get("BRANCH_CODE").toString();
	private String userId=(String) session.get("UserId")==null?"":(String) session.get("UserId");
	CedingMasterBean bean= new CedingMasterBean();
	CedingMasterService service=new CedingMasterService();
	Logger logger = LogUtil.getLogger(this.getClass());
	public CedingMasterBean getModel() {
		return bean;
	}
	
	/*public String Init(){
		String forward="";
		try{
		  bean.setSuccess("");
		  PATH="InsertCedingMaster";
		  List<String> list=new ArrayList<String>();
		  list.add("Broker");
		  list.add("Company");
		  request.setAttribute("typelist", list);
		  forward=mapping.findForward("CedingMasterDispatchAction");
		}
		catch (Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}*/

	
	public String ViewMode(){
	    String forward="";
	    forward="PopUpView";
	 	try{
	 		bean.setBankCurrencyList(new DropDownControllor().getbankCurrencyList(bean));
			bean.setBrokercountryList(new DropDownControllor().getCountryDropDown(branchCode));
			bean.setBroGroupList(new DropDownControllor().getbroGroupList(bean));
		if(StringUtils.isEmpty(bean.getMode())){
		List<Map<String,Object>> tempList=service.GetListCedingMaster(bean);
		bean.setViewList(tempList);
		bean.setCeddingList(tempList);
		bean.setView("ceddingviewpage");
		forward="CeddingCompanyView";
		}
		else{
		   if(!"0".equalsIgnoreCase(bean.getCeddingcompany())){
		    	bean.setCustomerId(bean.getCeddingcompany());
		    	String productId = pid;
		    	bean.setBranchCode(branchCode);
		    	service.SearchCeding(bean,productId,true);
		    	forward="PopUpView";
		   }
		}	
	 	}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}
	
	public String init(){
		bean.setBranchCode(branchCode);
		bean.setProposalList(service.getproposalList(bean));
		return "proposalcach";
	}
	
	public String updateEditMode(){
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		
		ValidationCache();
		
		if(!hasActionErrors()){
			if(StringUtils.isNotBlank(bean.getContractNo()) &&("BE".equalsIgnoreCase(bean.getEditMode()) ||"BEF".equalsIgnoreCase(bean.getEditMode()) ||"SE".equalsIgnoreCase(bean.getEditMode()) ||"SEF".equalsIgnoreCase(bean.getEditMode()))){
				new DropDownControllor().riskDetailsEndorsement(bean.getProposalNo(),"N");	
			}
			if("BR".equalsIgnoreCase(bean.getEditMode()) ||"BRF".equalsIgnoreCase(bean.getEditMode()) ||"CL".equalsIgnoreCase(bean.getEditMode()) ||"CLF".equalsIgnoreCase(bean.getEditMode())){
				service.CancelProposal(bean,bean.getProposalNo());
			}
			if (null != bean.getProposalNo() && StringUtils.isNotBlank(bean.getProposalNo())) {
				new DropDownControllor().updateEditMode(bean.getProposalNo(),"N","");
				String proposal = new DropDownControllor().getBaseProposal(bean.getProposalNo());
				new DropDownControllor().updateEditMode(proposal, "N","");
				new DropDownControllor().updateSubEditMode(proposal, "N","");
				new DropDownControllor().updateSubEditMode(bean.getProposalNo(), "N","");
				new DropDownControllor().updateRenewalEditMode(bean.getProposalNo(),"N","");
			}
			if(StringUtils.isNotBlank(bean.getBouquetNo())) {
				new DropDownControllor().updateBqEditMode(bean.getBouquetNo(),"N","");
			}
			if("BR".equalsIgnoreCase(bean.getEditMode()) ||"BRF".equalsIgnoreCase(bean.getEditMode())){
				new DropDownControllor().UpdateRenewalStatus(bean.getProposalNo());
			}
		}else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
		}
		bean.setProposalList(service.getproposalList(bean));
		return "proposalcach";
	}

	private void ValidationCache() {
		if(service.getUserDetails(bean)){
			addActionError(getText("error.retro.user.list",new String[]{bean.getLoginIdList()}));
		}
		
	}
	
}
