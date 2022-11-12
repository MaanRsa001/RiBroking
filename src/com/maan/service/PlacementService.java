package com.maan.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.maan.bean.AdjustmentBean;
import com.maan.bean.PlacementBean;
import com.maan.dao.AdjustmentDAO;
import com.maan.dao.PlacementDAO;

public class PlacementService {
	PlacementDAO dao=new PlacementDAO();

public void proposalInfo(PlacementBean bean) {
	dao.proposalInfo(bean);
}

public void savePlacing(PlacementBean bean) {
	dao.savePlacing(bean);
	
}

public List<Map<String,Object>> getPlacingInfo(PlacementBean bean) {
	
	return dao.getPlacingInfo(bean);
}

public List<Map<String, Object>> getReinsurerInfo(PlacementBean bean) {
	return dao.getReinsurerInfo( bean);
}

public List<Map<String, Object>> editPlacingDetails(PlacementBean bean) {
	return dao.editPlacingDetails(bean);
}

public void updatePlacement(PlacementBean bean) {
	dao.updatePlacement(bean);
}

public void getMailTemplate(PlacementBean bean) {
dao.getMailTemplate(bean);
	
}

public void sendMail(PlacementBean bean) {
	dao.sendMail( bean);
}

public List<Map<String, Object>> getExReinsurerInfo(PlacementBean bean) {
	return dao.getExReinsurerInfo(bean);
}

public List<Map<String, Object>> getExistingReinsurerList(PlacementBean bean) {
	return dao.getExistingReinsurerList(bean);
}

public List<Map<String, Object>> getPlacementInfoList(PlacementBean bean) {
	return dao.getPlacementInfoList( bean);
}

public String attachFile(PlacementBean bean) {
	return dao.attachFile(bean);
}

public List<Map<String, Object>> getExistingAttachList(PlacementBean bean) {
	return dao.getExistingAttachList( bean);
}

public String deleteFile(PlacementBean bean) {
	return dao.deleteFile( bean);
}

public String downloadFile(PlacementBean bean) {
	return dao.downloadFile(bean);
}

public void uploadDocument(PlacementBean bean) {
	 dao.uploadDocument(bean) ; 
}
	
}
