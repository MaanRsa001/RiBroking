package com.maan.dao.admin;

import java.util.List;
import java.util.Map;

import com.maan.bean.admin.CedingMasterBean;

public interface CedingMasterDAO {

	List<Map<String, Object>> GetListCeding(CedingMasterBean bean);
	boolean searchmethod(CedingMasterBean bean, String productId, boolean view);
	List<Map<String, Object>> getproposalList(CedingMasterBean bean);
	boolean getUserDetails(CedingMasterBean bean);
	void CancelProposal(CedingMasterBean bean, String proposalNo);
	List<Map<String, Object>> gettdsTypeList(CedingMasterBean bean, String string);
	

}
