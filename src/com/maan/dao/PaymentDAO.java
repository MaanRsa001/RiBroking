package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.PaymentBean;

public interface PaymentDAO {

	String getCedingCo(String broker);

	void getAllocateView(PaymentBean payform,String contract, String trancsaction,String ceding, String brokerid);

	boolean insertReceiptNO(PaymentBean payform);

	void getReceiptGeneration(PaymentBean payform);

	void generationInsert(PaymentBean payform);

	List getReceiptAllocate(PaymentBean payform);

	List getReceiptContract(PaymentBean payform);

	List getTransContract(PaymentBean payform);

	List getReceiptList(String transType,String branchCode,String type);

	void getReceiptEdit(PaymentBean payform);

	List getReceiptViewList(PaymentBean payform);

	void getallocateTransaction(PaymentBean payform);

	String ajaxCheck(String contNo,String transNo, String checkPC,String amount,String mode,String payRecNo,String cedId,String brokId,String curId,String type,String branchCode);
	
	Map allocateView(PaymentBean payform);
	
	void getSecondPageInfo(PaymentBean payform) ;

	List allocateDetails(PaymentBean payform) ;

	List reverseInsert(PaymentBean payform) ;
	
	List reverseView(PaymentBean payform) ;
	
	List getAllocatedStatus(PaymentBean payform);

	List getReversalInfo(PaymentBean payform);
	
	String getCurrecyAmount(final String branchCode,final String currValu,final String serialNo);

	 
}
