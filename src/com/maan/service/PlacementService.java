package com.maan.service;

import java.util.List;
import java.util.Map;

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
	
}
