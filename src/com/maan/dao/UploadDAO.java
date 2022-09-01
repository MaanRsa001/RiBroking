package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.UploadBean;

public interface UploadDAO {
	List<Map<String,Object>> getDocList(UploadBean bean,String branchCode);
	List<Map<String,Object>> getDocType(String branchCode, String productId,String moduleType);
	String doDeleteDocDetails(UploadBean bean,String loginId, String branchCode);
	String doUploadDocDetails(List<Object[]> list, Object[] args);
	List<Map<String, Object>> allmoduleList(UploadBean bean, String branchCode);
	List<Map<String, Object>> moduleTypeList(String branchCode,UploadBean bean);
}
