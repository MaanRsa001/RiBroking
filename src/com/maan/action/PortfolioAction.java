package com.maan.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.bean.FaculitivesBean;
import com.maan.bean.PortfolioBean;
import com.maan.bean.ReportsBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.MenuService;
import com.maan.service.PortfolioService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PortfolioAction extends ActionSupport implements
		ModelDriven<PortfolioBean> {
	private static final long serialVersionUID = 1L;
	final static org.slf4j.Logger logger = LogUtil.getLogger(PortfolioAction.class);
	final HttpServletRequest request = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	Map<String, Object> session = ActionContext.getContext().getSession();
	PortfolioBean bean = new PortfolioBean();
	FaculitivesBean bean1 = new FaculitivesBean();
	private String countryId =session.get("countryId") == null ? "" : session.get("countryId").toString();
	private String userId =session.get("UserId") == null ? "" : (String) session.get("UserId");
	private String processId =session.get("processId") == null ? "" : session.get("processId").toString();
	private static String department = "Department";
	private String manufactureID = session.get("mfrid") == null ? "" : session .get("mfrid").toString();
	private static final String MFRID = "mfrid";
	private String branchCode = session.get("BRANCH_CODE") == null ? "" :session.get("BRANCH_CODE").toString();
	private String userType =session.get("UserType") == null ? "" : session.get("UserType").toString();
	private PortfolioService service = new PortfolioService();
	DropDownControllor dropDownController = new DropDownControllor();
	
	private InputStream inputStream;
	
	public PortfolioBean getModel() {
		return bean;
	}
	
	public String Init() {
		// new DropDownControllor().insertSessionTrackingdetails(session);
		logger.info("PortfolioDispatchAction Init() || Enter");
		if(StringUtils.isNotBlank(bean.getBackMode())&& "back".equalsIgnoreCase(bean.getBackMode())){
			bean.setMenuId(new DropDownControllor().getMenuId(processId)); 
			session.put("MenuRights",new MenuService().getRigthsOfProcess(bean.getMenuId(), processId,userId));
		}
		else if (StringUtils.contains(session.get("MenuRights").toString(), ",,")) {
			session.put("MenuRights", ",EN,V,P,C,PN,PE,PV,CN,CE,CV,R,");
		}
		String forward = "";
		if (null != bean.getProposalNo() && StringUtils.isNotBlank(bean.getProposalNo())) {
			new DropDownControllor().updateEditMode(bean.getProposalNo(), "N","");
			// if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
			String proposal = new DropDownControllor().getBaseProposal(bean.getProposalNo());
			new DropDownControllor().updateEditMode(proposal, "N","");
			new DropDownControllor().updateSubEditMode(proposal, "N","");
			// }
			// else{
			new DropDownControllor().updateSubEditMode(bean.getProposalNo(),"N","");
			// }
			new DropDownControllor().updateRenewalEditMode(bean.getProposalNo(),"N","");
		}
		
		List<PortfolioBean> PortfolioList = null;
		final PortfolioService BussinessCB = new PortfolioService();
		bean.setProductId(session.get(MFRID).toString());
		bean.setBranchCode(session.get("BRANCH_CODE").toString());
		bean.setUserType(session.get("UserType").toString());
		bean.setLoginId(session.get("UserId").toString());
		bean.setAttachedUW(session.get("ATTACHED_UW").toString());
		if (StringUtils.isNotBlank(bean.getMenuId())) {
			session.put("menuId", bean.getMenuId());
		}
		if (session.get("DepartmentId") != null)
			bean.setDeptId(session.get("DepartmentId").toString());
		else if (request.getParameter(department) != null)
			bean.setDeptId(request.getParameter(department));
		try {
			
			if (StringUtils.isNotBlank(bean.getEndtMode())
					&& "endorsment".equalsIgnoreCase(bean.getEndtMode())) {
				dropDownController.riskDetailsEndorsement(bean.getProposalNo(),	
						StringUtils.isBlank(bean.getEndorsementStatus())?"N":bean.getEndorsementStatus());
			}
			
			PortfolioList = BussinessCB.getContractsList(bean,session.get("MenuRights"));
			bean.setPortfolioList(PortfolioList);
			bean.setMode("");
			if (StringUtils.isBlank(bean.getReqFrom()))
				forward = "portfolioList";
			else {
				request.setAttribute("display", "ContractList");
				forward = "ajaxSearch";
			}
		} catch (Exception exe) {
			exe.printStackTrace();
		}
		logger.info("PortfolioDispatchAction Init() || Exit");
		System.gc();
		return forward;
	}

	public String gridList() {
		logger.info("PortfolioDispatchAction gridList() || Exit");
		List<PortfolioBean> PortfolioList = null;
		final PortfolioService BussinessCB = new PortfolioService();
		PortfolioList = BussinessCB.getContractsList(bean,session.get("MenuRights"));
		bean.setPortfolioList(PortfolioList);
		logger.info("PortfolioDispatchAction gridList() || Exit");
		return "gridAjax";
	}

	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public String getHistory() {
		logger.info("PortfolioDispatchAction getHistory() || Enter1");
		final PortfolioService BussinessCB = new PortfolioService();
		bean.setProductId(session.get(MFRID).toString());
		bean.setBranchCode(session.get("BRANCH_CODE").toString());
		bean.setUserType(session.get("UserType").toString());
		bean.setLoginId(session.get("UserId").toString());
		bean.setMode("");
		if (session.get("DepartmentId") != null)
			bean.setDeptId(session.get("DepartmentId").toString());
		else if (request.getParameter(department) != null)
			bean.setDeptId(request.getParameter(department));
		try {
			bean.setDisplay("History");
			final List<PortfolioBean> historyList = BussinessCB
					.getHistoryList(bean);
			bean.setPortfolioList(historyList);
		} catch (Exception exe) {
			exe.printStackTrace();
		}
		logger.info("PortfolioDispatchAction getHistory() || Exit");
		return "portfolioList";
	}

	public String menu() {
		if (bean.getMode() == null) {
			bean.setMode("");
		}
		String forward = "";
		final PortfolioService BussinessCB = new PortfolioService();
		try {
			if(StringUtils.isNotBlank(bean.getBackMode())&& "back".equalsIgnoreCase(bean.getBackMode())){
				bean.setMenuId(new DropDownControllor().getMenuId(processId)); 
				session.put("MenuRights",new MenuService().getRigthsOfProcess(bean.getMenuId(), processId,userId));
			}
			else if (StringUtils.contains(session.get("MenuRights").toString(), ",,")) {
				session.put("MenuRights", ",EN,V,P,C,PN,PE,PV,CN,CE,CV,R,");
			}
			if (null != bean.getProposalNo() && StringUtils.isNotBlank(bean.getProposalNo())) {
				new DropDownControllor().updateEditMode(bean.getProposalNo(),"N","");
						
				// if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
				String proposal = new DropDownControllor().getBaseProposal(bean.getProposalNo());
						
				new DropDownControllor().updateEditMode(proposal, "N","");
				new DropDownControllor().updateSubEditMode(proposal, "N","");
				// }
				// else{
				new DropDownControllor().updateSubEditMode(
						bean.getProposalNo(), "N","");
				// }
				new DropDownControllor().updateRenewalEditMode(bean.getProposalNo(),"N","");
			}
			final PortfolioService portfoloibusiness = new PortfolioService();
			List<PortfolioBean> portfoloioList = null;
			bean.setProductId(session.get(MFRID).toString());
			bean.setBranchCode(session.get("BRANCH_CODE").toString());
			bean.setUserType(session.get("UserType").toString());
			bean.setLoginId(session.get("UserId").toString());
			if (session.get("DepartmentId") != null)
				bean.setDeptId(session.get("DepartmentId").toString());
			else if (request.getParameter(department) != null)
				bean.setDeptId(request.getParameter(department));
			logger.info("Flag==>" + bean.getFlag());
			if (StringUtils.isNotBlank(bean.getMode())
					&& "Renewal".equalsIgnoreCase(bean.getMode())
					&& "1".equalsIgnoreCase(bean.getProductId())) {
				// new
				// DropDownControllor().getRenewalCopyQuote("Cancel",bean.getProductId(),
				// bean.getBranchCode(), bean.getProposalNo());
				new DropDownControllor().UpdateRenewalStatus(bean
						.getProposal_no());
			}
			if (StringUtils.isNotBlank(bean.getMode())
					&& "Renewal".equalsIgnoreCase(bean.getMode())
					&& ("2".equalsIgnoreCase(bean.getProductId())
							|| "4".equalsIgnoreCase(bean.getProductId()) || "5"
							.equalsIgnoreCase(bean.getProductId()))) {
				// new
				// DropDownControllor().getRenewalCopyQuote("Cancel",bean.getProductId(),
				// bean.getBranchCode(), bean.getProposalNo());
				new DropDownControllor().UpdateRenewalStatus(bean
						.getProposalNo1());
			}
			if (StringUtils.isBlank(bean.getFlag())
					|| "N".equalsIgnoreCase(bean.getFlag())
					|| "RP".equalsIgnoreCase(bean.getFlag())) {
				String Mode = "";
				if (StringUtils.isBlank(bean.getFlag()))
					Mode = "QUO";// Pending
				else if ("N".equals(bean.getFlag()))
					Mode = "NTU";// Not Taken Up
				else if ("RP".equals(bean.getFlag()))
					Mode = "RP";// Renewal Pending
				portfoloioList = portfoloibusiness.getPendingList(bean,session.get("MenuRights"));
				// bean.setTilte(Mode);
				bean.setTitle(Mode);
			} else if ("C".equalsIgnoreCase(bean.getFlag())
					|| "RD".equalsIgnoreCase(bean.getFlag())
					|| "renewal".equalsIgnoreCase(bean.getFlag())) {
				// if("renewal".equalsIgnoreCase(bean.getFlag())&&
				// (bean.getProductId().equalsIgnoreCase("2")||bean.getProductId().equalsIgnoreCase("81")||bean.getProductId().equalsIgnoreCase("4"))){
				if ("renewal".equalsIgnoreCase(bean.getFlag())
						&& "Renewal".equalsIgnoreCase(bean.getMode())) {
					bean.setFlag("RD");
				}
				portfoloioList = BussinessCB.getContractsList(bean,session.get("MenuRights"));
			} else {
				portfoloioList = portfoloibusiness.getRejectedList(bean);
				// bean.setTilte("REJE");
				bean.setTitle("REJE");
			}
			bean.setPortfolioList(portfoloioList);
			if ("C".equalsIgnoreCase(bean.getFlag())
					|| "RD".equalsIgnoreCase(bean.getFlag())
					|| "renewal".equalsIgnoreCase(bean.getFlag())) {
				forward = "portfolioList";
			} else {
				if (StringUtils.isBlank(bean.getReqFrom()))
					forward = "first";
				else {
					bean.setDisplay("PendingList");
					forward = "ajaxSearch";
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
		}
		System.gc();
		return forward;
	}

	public String commonList() {
		String ForwardName = "";
		final PortfolioService BussinessCB = new PortfolioService();

		try {
			if(StringUtils.isNotBlank(bean.getBackMode())&& "back".equalsIgnoreCase(bean.getBackMode())){
				bean.setMenuId(new DropDownControllor().getMenuId(processId)); 
				session.put("MenuRights",new MenuService().getRigthsOfProcess(bean.getMenuId(), processId,userId));
			}
			else if (StringUtils.contains(session.get("MenuRights").toString(), ",,")) {
				session.put("MenuRights", ",EN,V,P,C,PN,PE,PV,CN,CE,CV,R,");
			}
			if (null != bean.getProposalNo() && StringUtils.isNotBlank(bean.getProposalNo())) {
				new DropDownControllor().updateEditMode(bean.getProposalNo(),
						"N","");
				// if( (StringUtils.isNotBlank(bean.getBaseLayer()))){
				String proposal = new DropDownControllor().getBaseProposal(bean
						.getProposalNo());
				new DropDownControllor().updateEditMode(proposal, "N","");
				new DropDownControllor().updateSubEditMode(proposal, "N","");
				// }
				// else{
				new DropDownControllor().updateSubEditMode(
						bean.getProposalNo(), "N","");
				// }
				new DropDownControllor().updateRenewalEditMode(bean.getProposalNo(),"N","");
			}
			if (StringUtils.isNotBlank(bean.getMode())
					&& "Renewal".equalsIgnoreCase(bean.getMode())
					&& "3".equalsIgnoreCase(manufactureID)) {
				// new
				// DropDownControllor().getRenewalCopyQuote("Cancel",bean.getProductId(),
				// bean.getBranchCode(), bean.getProposalNo());
				new DropDownControllor().UpdateRenewalStatus(bean
						.getProposalNo1());
			}

			bean.setDepartmentId(session.get("DepartmentId").toString());
			bean.setAttachedUW(session.get("ATTACHED_UW").toString());
			List<PortfolioBean> portfoloioList = null;
			String Mode = null;
			if (StringUtils.isBlank(bean.getFlag())
					|| "N".equalsIgnoreCase(bean.getFlag())
					|| "RP".equalsIgnoreCase(bean.getFlag())
					|| "P".equalsIgnoreCase(bean.getFlag())) {
				if (StringUtils.isBlank(bean.getFlag())
						|| "P".equals(bean.getFlag()))
					Mode = "QUO";// Pending
				else if ("N".equals(bean.getFlag()))
					Mode = "NTU";// Not Taken Up
				else if ("RP".equals(bean.getFlag()))
					Mode = "RP";// Renewal Pending
				bean.setFlag(bean.getFlag());
				bean.setProductId(manufactureID);
				bean.setBranchCode(branchCode);
				bean.setUserType(userType);
				bean.setLoginId(userId);
				bean.setDeptId(bean.getDepartmentId());
				portfoloioList = service.getPendingList(bean,session.get("MenuRights"));
				bean.setPortfolioList(portfoloioList);
				// new
				// DropDownControllor().getRenewalCopyQuote("NewCancel",bean.getProductId(),
				// bean.getBranchCode(), bean.getProposalNo());
			} else if ("C".equalsIgnoreCase(bean.getFlag())
					|| "RD".equalsIgnoreCase(bean.getFlag())) {
				bean.setProductId(manufactureID);
				bean.setBranchCode(branchCode);
				portfoloioList = BussinessCB.getContractsList(bean,session.get("MenuRights"));
				bean.setBaseLayer("");
			} else if (manufactureID.equalsIgnoreCase("3")
					&& "renewal".equalsIgnoreCase(bean.getFlag())) {
				bean.setFlag("RD");
				bean.setProductId(manufactureID);
				portfoloioList = BussinessCB.getContractsList(bean,session.get("MenuRights"));
			} else {
				Mode = "REJE";// Rejected
				bean.setProductId(manufactureID);
				bean.setBranchCode(branchCode);
				bean.setUserType(userType);
				bean.setLoginId(userId);
				bean.setDeptId(bean.getDepartmentId());
				portfoloioList = service.getRejectedList(bean);
				bean.setPortfolioList(portfoloioList);
			}
			if (manufactureID.equalsIgnoreCase("3")) {
				request.setAttribute("xol", "layer");
			} else {
				request.setAttribute("other", "layer");
			}
			bean.setTitle(Mode);
			logger.info("=====>" + portfoloioList.size());
			bean.setPortfolioList(portfoloioList);
			if ("C".equalsIgnoreCase(bean.getFlag())
					|| "RD".equalsIgnoreCase(bean.getFlag())
					|| "renewal".equalsIgnoreCase(bean.getFlag())) {
				ForwardName = "portfolioList";
			} else if ("2".equalsIgnoreCase(manufactureID)
					|| "3".equalsIgnoreCase(manufactureID)
					|| "4".equalsIgnoreCase(manufactureID)
					|| "5".equalsIgnoreCase(manufactureID)) {

				ForwardName = "first";
			} else if ("1".equalsIgnoreCase(manufactureID)
					|| "6".equalsIgnoreCase(manufactureID)
					|| "11".equalsIgnoreCase(manufactureID)) {
				if (session.get("DepartmentId") != null) {
					if ("1".equalsIgnoreCase(manufactureID)) {

						ForwardName = "first";
					} else if ("11".equalsIgnoreCase(manufactureID)) {
						if ("1".equals(bean.getDepartmentId())) {
							// session.put("AllocateType","PT");
						} else {
							// session.put("AllocateType","RT");
						}

						request.setAttribute("LIST_RECEIPT_NO",
								"LIST_RECEIPT_NO");
						ForwardName = "Payment";
					} else if ("6".equalsIgnoreCase(manufactureID)) {
						if (request.getParameter("Department") == null) {
							ForwardName = "Second";
						} else
							ForwardName = "Reports";
					}

				} else {
					ForwardName = "Second";
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			logger.debug("Login Controller method() - Exit");
		}
		System.gc();
		return ForwardName;
	}

	public String autoFacul() {
		bean.setProductId("1");
		bean.setMenuId(session.get("menuId").toString());
		bean.setBranchCode(session.get("BRANCH_CODE").toString());
		bean.setUserType(session.get("UserType").toString());
		bean.setLoginId(session.get("UserId").toString());
		bean.setBranchCode(branchCode);
		bean1.setBranchCode(branchCode);
		new DropDownControllor().getOpenPeriod(bean1);
		if (StringUtils.isNotBlank(bean1.getOpstartDate())
				&& StringUtils.isNotBlank(bean1.getOpendDate())
				&& StringUtils.isNotBlank(bean.getDueDate())) {
			if (new DropDownControllor().Validatethree(branchCode, bean.getDueDate()) == 0) {
				bean.setAutoopenDate("Yes");
			}
		}
		if (bean.getMode() != null && bean.getMode().equalsIgnoreCase("post") ) {
			service.procAuto(bean, bean.getMenuId(), countryId);
			bean.setMode("view");
		} else if (bean.getDueDate() != null && !bean.getDueDate().isEmpty()) {
			bean.setPortfolioList(service.getAutoPendingList(bean, countryId));
		}
		if("error".equalsIgnoreCase(bean.getMode())){
			addActionError(getText("multi.error.mode"));
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
		}
		System.gc();
		return "autoList";
	}

	public String autoNonProp() {
		bean.setProductId("3");
		bean.setBranchCode(session.get("BRANCH_CODE").toString());
		bean.setUserType(session.get("UserType").toString());
		bean.setLoginId(session.get("UserId").toString());
		bean.setBranchCode(branchCode);
		bean1.setBranchCode(branchCode);
		new DropDownControllor().getOpenPeriod(bean1);
		if (StringUtils.isNotBlank(bean1.getOpstartDate())
				&& StringUtils.isNotBlank(bean1.getOpendDate())
				&& StringUtils.isNotBlank(bean.getDueDate())) {
			if (new DropDownControllor().Validatethree(branchCode, bean.getDueDate()) == 0) {
				bean.setAutoopenDate("Yes");
			}
		}
		if (bean.getMode() != null && bean.getMode().equalsIgnoreCase("post")) {
			service.procAuto(bean, bean.getMenuId(), countryId);
			if(!"error".equalsIgnoreCase(bean.getTransactionError())){
				bean.setMode("view");
			}
		} else if (bean.getDueDate() != null && !bean.getDueDate().isEmpty()) {
			bean.setPortfolioList(service.getAutoPendingList(bean, countryId));
		}if("error".equalsIgnoreCase(bean.getMode()) ){
			addActionError(getText("multi.error.mode"));
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
		}else if("error".equalsIgnoreCase(bean.getTransactionError())){
			addActionError("This activity is not allowed");
			logger.info("This activity is not allowed");
		}else if (bean.getMode() != null && bean.getMode().equalsIgnoreCase("view")) {
			addActionMessage("This activity Completed Successfully");
			logger.info("This activity Completed Successfully");
		}
		return "autoList";
	}

	public String openPeriod() {
		bean.setBranchCode(session.get("BRANCH_CODE").toString());
		if (bean.getTransactionDate() == null
				|| bean.getTransactionDate().isEmpty()) {
			bean.setIsValid(false);
		} else {
			bean.setIsValid(service.validateOpenPeriod(bean));
		}
		return "json";
	}
	public String  DuplicateCopy(){
		bean.setProductId((String) session.get("mfrid"));
		bean.setBranchCode(branchCode);
		bean.setProposalNo(new DropDownControllor().getRenewalCopyQuote("DupCopy",bean.getProductId(), bean.getBranchCode(), bean.getProposal_no()));
		return "copyPage";
	}
	
}
