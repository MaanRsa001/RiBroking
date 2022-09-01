package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.AdjustmentBean;
import com.maan.dao.AdjustmentDAO;

public class AdjustmentService {
AdjustmentDAO dao=new AdjustmentDAO();
	public List<AdjustmentBean> getAdjustmentList(AdjustmentBean bean, Map<String, String> receiveAdjustAmountMap) {
		return dao.getAdjustmentList(bean,receiveAdjustAmountMap);
	}
	public void AddAdjustment(AdjustmentBean bean, String branchCode,Map<String, String> receiveAdjustAmountMap) {
		dao.AddAdjustment(bean, branchCode, receiveAdjustAmountMap);
	}
	public List<AdjustmentBean> getAdjustmentPayRecList(AdjustmentBean bean, Map<String, String> receiveAdjustAmountMap) {
		return dao.getAdjustmentPayRecList(bean,receiveAdjustAmountMap);
	}
	public void AddAdjustmentPayRec(AdjustmentBean bean, String branchCode,Map<String, String> receiveAdjustAmountMap) {
		dao.AddAdjustmentPayRec(bean,  branchCode,receiveAdjustAmountMap);
	}
	public Map<String, List<AdjustmentBean>> adjustmentView(AdjustmentBean bean) {
		return dao.adjustmentView(bean) ;
	}
	public List<AdjustmentBean> getAdjustmentDoneList(AdjustmentBean bean) {
		return dao.getAdjustmentDoneList(bean);
	}
	public String GetMaxDate(AdjustmentBean bean) {
		return dao.GetMaxDate(bean);
	}
	public String insertReverse(AdjustmentBean bean) {
		return dao.insertReverse(bean);
	}
	public String openPerionDate(AdjustmentBean bean) {
	 return dao.openPerionDate(bean);	
	}
	public String allocatedDate(AdjustmentBean bean) {
		return dao.allocatedDate(bean);
		
	}
}
