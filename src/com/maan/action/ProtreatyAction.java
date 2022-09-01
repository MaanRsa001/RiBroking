package com.maan.action;

import com.maan.bean.CommonBean;

public class ProtreatyAction {
	
	private String optionMode;
	private String mode;
	
	CommonBean cmbean = new CommonBean();
	
	public void setOptionMode(String optionMode) {
		this.optionMode = optionMode;
	}
	
	public String getOptionMode() {
		return optionMode;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}
	
	public String protreaty1(){
		cmbean.setProductId("2");
		return "protreaty1";
	}
	
	public String protreaty2(){
		cmbean.setProductId("2");
		return "protreaty2";
	}
	
}
