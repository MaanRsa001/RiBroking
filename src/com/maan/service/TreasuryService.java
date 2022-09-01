package com.maan.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.maan.bean.TreasuryBean;
import com.maan.common.util.LogUtil;
import com.maan.dao.TreasuryDAO;
import com.maan.dao.impl.TreasuryDAOImpl;

public class TreasuryService {
	Logger logger = LogUtil.getLogger(getClass());
	TreasuryDAO dao = new TreasuryDAOImpl();

	public List<TreasuryBean> getReceiptList(String transType,String branchCode,String type,TreasuryBean bean) {
		return dao.getReceiptList(transType,branchCode,type,bean);
	}
	public void getReceiptEdit(TreasuryBean bean, String branchCode) {
		dao.getReceiptEdit(bean,branchCode);
	}
	public void getReceiptGeneration(TreasuryBean bean,String branchCode) {
		dao.getReceiptGeneration(bean,branchCode);
	}
	public List<TreasuryBean> getReceiptAllocate(TreasuryBean bean,String branchCode) {
		return dao.getReceiptAllocate(bean,branchCode);
	}
	public List<TreasuryBean> reverseView(final TreasuryBean bean,String branchCode) {
		return dao.reverseView(bean,branchCode);
	}
	public List<TreasuryBean> getReversalInfo(TreasuryBean bean,String branchCode) {
		return dao.getReversalInfo(bean,branchCode);
	}
	public List<TreasuryBean> getReceiptViewList(TreasuryBean bean,String branchCode) {
		return dao.getReceiptViewList(bean,branchCode);
	}
	public List<TreasuryBean> getAllocatedStatus(final TreasuryBean bean,String branchCode) {
		return dao.getAllocatedStatus(bean,branchCode);
	}
	public List<TreasuryBean> allocateDetails(final TreasuryBean bean,String branchCode) {
		return dao.allocateDetails(bean,branchCode);
	}
	public Map<String,List<TreasuryBean>> allocateView(final TreasuryBean bean,String branchCode) {
		return dao.allocateView(bean,branchCode);
	}
	public List<TreasuryBean> reverseInsert(TreasuryBean bean,String branchCode) {
		return dao.reverseInsert(bean,branchCode);
	}
	public String getCurrecyAmount(final String branchCode,final String currValu,final String serialNo) {
		return dao.getCurrecyAmount(branchCode,currValu,serialNo);
	}
	public List<TreasuryBean> getTransContract(TreasuryBean bean, String branchCode,Map<String,String> receivePayAmountMap) {
		return dao.getTransContract(bean,branchCode,receivePayAmountMap);
	}
	public boolean insertReceiptNO(TreasuryBean bean, String branchCode) {
		return dao.insertReceiptNO(bean,branchCode);
	}
	public void getSecondPageInfo(TreasuryBean bean, String branchCode) {
		dao.getSecondPageInfo(bean,branchCode);
	}
	public void generationInsert(TreasuryBean bean, String branchCode) {
		dao.generationInsert(bean,branchCode);
	}
	public List<TreasuryBean> getAllTransContract(TreasuryBean payform, String branchCode) {
		return dao.getAllTransContract(payform,branchCode);
	}
	public void getallocateTransaction(TreasuryBean bean, String branchCode, Map<String,String> receivePayAmountMap) {
		dao.getallocateTransaction(bean,branchCode,receivePayAmountMap);
	}
	public int externalPaginationAddon(TreasuryBean bean, int pagePosition,int value, int spage) {
		if(Integer.parseInt(bean.getTotalreccount())!=0) {	
			if(!(pagePosition>Integer.parseInt(bean.getPageLength()))) {
				value=pagePosition*Integer.parseInt(bean.getPaginationsize());
				if(value!=0) {
					bean.setEndrownum(String.valueOf(value));
					bean.setStartrownum(String.valueOf(value-(Integer.parseInt(bean.getPaginationsize())-1)));
				}
				else {
					bean.setStartrownum("1");
					bean.setEndrownum(bean.getPaginationsize());
				}

			}
			if(pagePosition>=5 ) {
				spage=pagePosition-4;
			}
			else {
				spage=1;
			}
		}
		return spage;
	}
	public List<TreasuryBean> getReceiptReversalList(String string,String branchCode, String type,TreasuryBean bean) {
		return dao.getReceiptReversalList( string,branchCode,  type,bean);
		
	}
	public List<Map<String, Object>> getDirectCeding(String branchId, String brokerId) {
        return dao.getDirectCeding(brokerId, branchId);
    }
	public String getShortname(String branchCode) {
		return dao.getShortname(branchCode);
	}
	public List<Map<String, Object>> getTreasuryJournalView(TreasuryBean bean) {
		return dao.getTreasuryJournalView(bean);
	}
	public List<TreasuryBean> getReceiptTreasuryGeneration(TreasuryBean bean,String branchCode) {
		return dao.getReceiptTreasuryGeneration(bean,branchCode);
	}
	public List<TreasuryBean> getRetroTransContract(TreasuryBean bean,String branchCode, Map<String, String> receivePayAmountMap) {
		return dao.getRetroTransContract(bean,branchCode,receivePayAmountMap);
	}
	public void getRetroallocateTransaction(TreasuryBean bean,String branchCode, Map<String, String> receivePayAmountMap) {
		dao.getRetroallocateTransaction(bean,branchCode,receivePayAmountMap);
		
	}
}
