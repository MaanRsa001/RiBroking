package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.PaymentBean;
import com.maan.dao.PaymentDAO;
import com.maan.dao.impl.PaymentDAOImpl;

public class PaymentBusinessService  {
      
	PaymentDAO dao=(PaymentDAO)new PaymentDAOImpl();
	public boolean insertReceiptNO(PaymentBean payform) 
	{
		  return dao.insertReceiptNO(payform);
	}

	public String GetCedingCo(final String broker) 
	{
		return dao.getCedingCo(broker);
	}
	public void getAllocateView(PaymentBean payform,String contract, String trancsaction,String ceding, String brokerid) 
	{
		dao.getAllocateView(payform,contract,trancsaction,ceding,brokerid);
	}

	public void getReceiptGeneration(PaymentBean payform)
	{
		dao.getReceiptGeneration(payform);
	}

	public void generationInsert(final PaymentBean payform)
	{
		dao.generationInsert(payform);
	}

	public List getReceiptAllocate(final PaymentBean payform)
	{
		return dao.getReceiptAllocate(payform);
	}

	public List getReceiptContract(final PaymentBean payform)
	{
		return dao.getReceiptContract(payform);
	}

	public List getTransContract(final PaymentBean payform)
	{
		return dao.getTransContract(payform);
	}

	public List getReceiptList(String transType,String branchCode,String type)
	{
		return dao.getReceiptList(transType,branchCode,type);
	}

	public void getReceiptEdit(PaymentBean payform)
	{
		dao.getReceiptEdit(payform);
	}

	public List getReceiptViewList(PaymentBean payform)
	{
		return dao.getReceiptViewList(payform);
	}

	public void getallocateTransaction(PaymentBean payform) 
	{
		dao.getallocateTransaction(payform);
	}

	public String ajaxCheck(String contNo,String transNo,String checkPC,String amount,String mode,String payRecNo,String cedId,String brokId,String curId,String type,String branchCode)  {
		return dao.ajaxCheck(contNo,transNo,checkPC,amount,mode,payRecNo,cedId,brokId,curId,type,branchCode);
	}
	public Map allocateView(final PaymentBean payform)  {
		  return dao.allocateView(payform);
	}

	public void getSecondPageInfo(PaymentBean payform)  {
		dao.getSecondPageInfo(payform);
	}
	public List getReversalInfo(PaymentBean payform)  {
		   return dao.getReversalInfo(payform);
	}

	public Object allocateDetails(PaymentBean payform)  {
		// TODO Auto-generated method stub
		  return dao.allocateDetails(payform);
	}

	public Object reverseInsert(PaymentBean payform)  {
		// TODO Auto-generated method stub
		 return dao.reverseInsert(payform);
	}

	public Object reverseView(PaymentBean payform)  {
		// TODO Auto-generated method stub
		 return dao.reverseView(payform);
	}
	public Object getAllocatedStatus(PaymentBean payform)  {
		// TODO Auto-generated method stub
		 return dao.getAllocatedStatus(payform);
	}
	
	public String getCurrecyAmount(final String branchCode,final String currValu,final String serialNo)
	{
		return dao.getCurrecyAmount(branchCode,currValu,serialNo);
	}
}
