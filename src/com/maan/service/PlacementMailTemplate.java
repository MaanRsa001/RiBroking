package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.PlacementBean;
import com.maan.common.util.DropDownControllor;

public class PlacementMailTemplate {

	public String getOfferMsg(List<Map<String, Object>> agentWiseReport, PlacementBean bean) {
		String messageContent ="";
		messageContent="<!DOCTYPE html>" + 
				"<html lang=\"en\">" + 
				"<body>" + 
				"    <div style=\"width: 80%;\">" + 
				"<table style=\"width:100%;border: 1px solid #000000;\">" +
				"<thead> <tr> <th width=\"10%\" style=\"border: 1px solid #000000;\">TQR Bouquet Ref </th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">TQR Offer Ref</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Business Type</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Main Class</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Sub Class</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Treaty Type</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Treaty Name</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Layer / Section No</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Inception Date</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Expiry Date</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Max Share Offer %</th>" + 
				"</tr> </thead> <tbody>" ;
		
		
		for(int i=0;i<agentWiseReport.size();i++) {
			Map<String,Object>map=agentWiseReport.get(i);
				messageContent+="<tr>"+
			"<td style=\"border: 1px solid #000000;\">"+(map.get("BOUQUET_NO")==null?"":map.get("BOUQUET_NO").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("OFFER_NO")==null?"":map.get("OFFER_NO").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("BUSINESS_TYPE")==null?"":map.get("BUSINESS_TYPE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("CLASS")==null?"":map.get("CLASS").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("SUB_CLASS")==null?"":map.get("SUB_CLASS").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("TREATY_TYPE")==null?"":map.get("TREATY_TYPE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("RSK_TREATYID")==null?"":map.get("RSK_TREATYID").toString())+"</td>" +
			"<td style=\"border: 1px solid #000000;\">"+(map.get("SECTION_NO")==null?"":map.get("SECTION_NO").toString())+"</td>" +
			"<td style=\"border: 1px solid #000000;\">"+(map.get("INS_DATE")==null?"":map.get("INS_DATE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("EXP_DATE")==null?"":map.get("EXP_DATE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;text-align: right;\">"+(map.get("SHARE_OFFERED")==null?"":DropDownControllor.formattereight(map.get("SHARE_OFFERED").toString()))+"</td>" + 
			"</tr>";
		}
		messageContent+=	"</table>" +
				"    </div>" + 
				"</body>" + 
				"" + 
				"</html>";
	
	return messageContent.toString();
	}
	public String getPropsalWrittenMsg(List<Map<String, Object>> agentWiseReport, PlacementBean bean) {
		String messageContent ="";
		messageContent="<!DOCTYPE html>" + 
				"<html lang=\"en\">" + 
				"<body>" + 
				"    <div style=\"width: 80%;\">" + 
				"<table style=\"width:100%;border: 1px solid #000000;\">" +
				"<thead> <tr> <th width=\"10%\" style=\"border: 1px solid #000000;\">TQR Bouquet Ref </th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">TQR Offer Ref</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Business Type</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Main Class</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Sub Class</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Treaty Type</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Treaty Name</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Layer / Section No</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Inception Date</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Expiry Date</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Max Share Offer %</th>" +
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Proposed Written Line %</th>" + 
				"</tr> </thead> <tbody>" ;
		
		for(int i=0;i<agentWiseReport.size();i++) {
			Map<String,Object>map=agentWiseReport.get(i);
				messageContent+="<tr>"+
			"<td style=\"border: 1px solid #000000;\">"+(map.get("BOUQUET_NO")==null?"":map.get("BOUQUET_NO").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("OFFER_NO")==null?"":map.get("OFFER_NO").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("BUSINESS_TYPE")==null?"":map.get("BUSINESS_TYPE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("CLASS")==null?"":map.get("CLASS").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("SUB_CLASS")==null?"":map.get("SUB_CLASS").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("TREATY_TYPE")==null?"":map.get("TREATY_TYPE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("RSK_TREATYID")==null?"":map.get("RSK_TREATYID").toString())+"</td>" +
			"<td style=\"border: 1px solid #000000;\">"+(map.get("SECTION_NO")==null?"":map.get("SECTION_NO").toString())+"</td>" +
			"<td style=\"border: 1px solid #000000;\">"+(map.get("INS_DATE")==null?"":map.get("INS_DATE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("EXP_DATE")==null?"":map.get("EXP_DATE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;text-align: right;\">"+(map.get("SHARE_OFFERED")==null?"":DropDownControllor.formattereight(map.get("SHARE_OFFERED").toString()))+"</td>" +
			"<td style=\"border: 1px solid #000000;text-align: right;\">"+(map.get("SHARE_PROPOSAL_WRITTEN")==null?"":DropDownControllor.formattereight(map.get("SHARE_PROPOSAL_WRITTEN").toString()))+"</td>" +
			"</tr>";
		}
		messageContent+=	"</table>" +
				"    </div>" + 
				"</body>" + 
				"" + 
				"</html>";
	
	return messageContent.toString();
	}
	public String getPropsalSignedMsg(List<Map<String, Object>> agentWiseReport, PlacementBean bean) {
		String messageContent ="";
		messageContent="<!DOCTYPE html>" + 
				"<html lang=\"en\">" + 
				"<body>" + 
				"    <div style=\"width: 80%;\">" + 
				"<table style=\"width:100%;border: 1px solid #000000;\">" +
				"<thead> <tr> <th width=\"10%\" style=\"border: 1px solid #000000;\">TQR Bouquet Ref </th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">TQR Offer Ref</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Business Type</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Main Class</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Sub Class</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Treaty Type</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Treaty Name</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Layer / Section No</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Inception Date</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Expiry Date</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Max Share Offer %</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Proposed Written Line %</th>" + 
				"<th width=\"10%\" style=\"border: 1px solid #000000;\">Proposed Signed Line %</th>" + 
				"</tr> </thead> <tbody>" ;
		
		
		for(int i=0;i<agentWiseReport.size();i++) {
			Map<String,Object>map=agentWiseReport.get(i);
				messageContent+="<tr>"+
			"<td style=\"border: 1px solid #000000;\">"+(map.get("BOUQUET_NO")==null?"":map.get("BOUQUET_NO").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("OFFER_NO")==null?"":map.get("OFFER_NO").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("BUSINESS_TYPE")==null?"":map.get("BUSINESS_TYPE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("CLASS")==null?"":map.get("CLASS").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("SUB_CLASS")==null?"":map.get("SUB_CLASS").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("TREATY_TYPE")==null?"":map.get("TREATY_TYPE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("RSK_TREATYID")==null?"":map.get("RSK_TREATYID").toString())+"</td>" +
			"<td style=\"border: 1px solid #000000;\">"+(map.get("SECTION_NO")==null?"":map.get("SECTION_NO").toString())+"</td>" +
			"<td style=\"border: 1px solid #000000;\">"+(map.get("INS_DATE")==null?"":map.get("INS_DATE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;\">"+(map.get("EXP_DATE")==null?"":map.get("EXP_DATE").toString())+"</td>" + 
			"<td style=\"border: 1px solid #000000;text-align: right;\">"+(map.get("SHARE_OFFERED")==null?"":DropDownControllor.formattereight(map.get("SHARE_OFFERED").toString()))+"</td>" +
			"<td style=\"border: 1px solid #000000;text-align: right;\">"+(map.get("SHARE_PROPOSAL_WRITTEN")==null?"":DropDownControllor.formattereight(map.get("SHARE_PROPOSAL_WRITTEN").toString()))+"</td>" +
			"<td style=\"border: 1px solid #000000;text-align: right;\">"+(map.get("SHARE_PROPOSED_SIGNED")==null?"":DropDownControllor.formattereight(map.get("SHARE_PROPOSED_SIGNED").toString()))+"</td>" +
			"</tr>";
		} 
		messageContent+=	"</table>" +
				"    </div>" + 
				"</body>" + 
				"" + 
				"</html>";
	
	return messageContent.toString();
	}
}
