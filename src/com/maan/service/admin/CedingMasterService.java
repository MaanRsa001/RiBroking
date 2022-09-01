package com.maan.service.admin;

import com.maan.bean.admin.CedingMasterBean;
import com.maan.dao.admin.CedingMasterDAO;
import com.maan.dao.impl.admin.CedingMasterDAOImpl;

import java.util.List;
import java.util.Map;

public class CedingMasterService {

	CedingMasterDAO dao=new CedingMasterDAOImpl();
	
	public List<Map<String,Object>> GetListCedingMaster(CedingMasterBean bean){
		return dao.GetListCeding(bean);	
	}
	
	public boolean SearchCeding(final CedingMasterBean bean,final String productId,final boolean view){
		return dao.searchmethod(bean,productId,view);
	}

	public List<Map<String, Object>> getproposalList(CedingMasterBean bean) {
		return dao.getproposalList(bean);
	}

	public boolean getUserDetails(CedingMasterBean bean) {
		return dao.getUserDetails(bean);
	}

	public void CancelProposal(CedingMasterBean bean, String proposalNo) {
		 dao.CancelProposal(bean,proposalNo);
		
	}

}
