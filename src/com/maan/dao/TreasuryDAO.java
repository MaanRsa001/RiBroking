package com.maan.dao;

import com.maan.bean.TreasuryBean;

import java.util.List;
import java.util.Map;

public interface TreasuryDAO {
	List<TreasuryBean> getReceiptList(String transType,String branchCode,String type,TreasuryBean bean);
	void getReceiptEdit(TreasuryBean bean, String branchCode);
	void getReceiptGeneration(TreasuryBean bean,String branchCode);
	List<TreasuryBean> getReceiptAllocate(TreasuryBean bean,String branchCode);
	List<TreasuryBean> reverseView(final TreasuryBean bean,String branchCode);
	List<TreasuryBean> getReversalInfo(TreasuryBean bean,String branchCode);
	List<TreasuryBean> getReceiptViewList(TreasuryBean bean,String branchCode);
	List<TreasuryBean> getAllocatedStatus(final TreasuryBean bean,String branchCode);
	List<TreasuryBean> allocateDetails(final TreasuryBean bean,String branchCode);
	Map<String,List<TreasuryBean>> allocateView(final TreasuryBean bean,String branchCode);
	List<TreasuryBean> reverseInsert(TreasuryBean bean,String branchCode);
	String getCurrecyAmount(final String branchCode,final String currValu,final String serialNo);
	List<TreasuryBean> getTransContract(TreasuryBean bean, String branchCode,Map<String,String> receivePayAmountMap);
	boolean insertReceiptNO(TreasuryBean bean,String branchCode);
	void getSecondPageInfo(TreasuryBean bean, String branchCode);
	void generationInsert(TreasuryBean bean, String branchCode);
	List<TreasuryBean> getAllTransContract(TreasuryBean payform, String branchCode);
	void getallocateTransaction(TreasuryBean bean, String branchCode, Map<String,String> receivePayAmountMap);
	List<TreasuryBean> getReceiptReversalList(String string, String branchCode,String type,TreasuryBean bean);
	List<Map<String, Object>> getDirectCeding(String brokerId,String branchId);
	String getShortname(String branchCode);
	List<Map<String, Object>> getTreasuryJournalView(TreasuryBean bean);
	List<TreasuryBean> getReceiptTreasuryGeneration(TreasuryBean bean,
			String branchCode);
	List<TreasuryBean> getRetroTransContract(TreasuryBean bean,
			String branchCode, Map<String, String> receivePayAmountMap);
	void getRetroallocateTransaction(TreasuryBean bean, String branchCode,
			Map<String, String> receivePayAmountMap);
}
