package com.maan.bean;

import java.util.List;

public class MenuBean  {
	private String url;
	private String content;
	private String id;
	private List list;
	private String departmentId;
	private String departmentName;
	private String menuId;
	private String menuUrl;
	private String listPosition;
	private String positionType;
	private String menuName;
	private String processId;
	private String productName;
	private String productId;
	private String processName;
	private String proposalNo;
	private String menumode;
	private String menumodeStatus;
	private List<PortfolioBean> portfolioList;
	
	public List<PortfolioBean> getPortfolioList() {
		return portfolioList;
	}

	public void setPortfolioList(List<PortfolioBean> portfolioList) {
		this.portfolioList = portfolioList;
	}

	private String manufactureID;
	private String flag;
	private String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getManufactureID() {
		return manufactureID;
	}

	public void setManufactureID(String manufactureID) {
		this.manufactureID = manufactureID;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content; 
	}

	public List getList() {
		return list;
	}

	public void setList(final List list) {
		this.list = list;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getListPosition() {
		return listPosition;
	}

	public void setListPosition(String listPosition) {
		this.listPosition = listPosition;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getMenumode() {
		return menumode;
	}

	public void setMenumode(String menumode) {
		this.menumode = menumode;
	}

	public String getMenumodeStatus() {
		return menumodeStatus;
	}

	public void setMenumodeStatus(String menumodeStatus) {
		this.menumodeStatus = menumodeStatus;
	}

}
