package com.maan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

import com.maan.bean.PlacementBean;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.LogUtil;

public class PlacementDAO extends MyJdbcTemplate{
	
	
private static final Logger logger = LogUtil.getLogger(PlacementDAO.class);


	public void proposalInfo(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		try {
			String query=getQuery("GET_EXISTING_PROPOSAL");
			Object[] obj=new Object[2];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProposalNo();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
			if(!CollectionUtils.isEmpty(list)) {
				Map<String,Object>map=list.get(0);
				bean.setCedingCompany(map.get("COMPANY_NAME")==null?"":map.get("COMPANY_NAME").toString());
				bean.setTreatyName(map.get("TREATY_TYPE")==null?"":map.get("TREATY_TYPE").toString());
				bean.setInceptionDate(map.get("INS_DATE")==null?"":map.get("INS_DATE").toString());
				bean.setExpiryDate(map.get("EXP_DATE")==null?"":map.get("EXP_DATE").toString());
				bean.setUwYear(map.get("UW_YEAR")==null?"":map.get("UW_YEAR").toString());
				bean.setUwYearTo(map.get("UW_YEAR_TO")==null?"":map.get("UW_YEAR_TO").toString());
				bean.setBouquetModeYN(map.get("Bouquet_Mode_YN")==null?"":map.get("Bouquet_Mode_YN").toString());
				bean.setBouquetNo(map.get("BOUQUET_NO")==null?"":map.get("BOUQUET_NO").toString());
				bean.setBaseProposalNo(map.get("BASE_LAYER")==null?"":map.get("BASE_LAYER").toString());
				bean.setContractNo(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
				bean.setLayerNo(map.get("LAYER_NO")==null?"0":map.get("LAYER_NO").toString());
				bean.setSectionNo(map.get("SECTION_NO")==null?"":map.get("SECTION_NO").toString());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void savePlacing(PlacementBean bean) {
		String placementNo="";
		String query=getQuery("GET_PLACEMENT_NO_SEQ");
		placementNo=this.mytemplate.queryForObject(query, String.class);
		bean.setPlacementNo(placementNo);
		proposalInfo(bean);
		query=getQuery("INSERT_PLACEMENT_INFO");
		for(int i=0;i<bean.getReinsSNo().size();i++) {
				Object[] obj = new Object[14];
				obj[0]=bean.getPlacementNo();
				obj[1]=bean.getReinsSNo().get(i);
				obj[2]=bean.getBouquetNo();
				obj[3]=bean.getBaseProposalNo();
				obj[4]=bean.getProposalNo();
				obj[5]=bean.getContractNo();
				obj[6]=bean.getLayerNo();
				obj[7]=bean.getSectionNo();
				obj[8]="0";
				obj[9]=bean.getReinsureName().get(i);
				obj[10]=bean.getPlacingBroker().get(i);
				obj[11]=bean.getShareOffer().get(i);
				obj[12]=bean.getBranchCode();
				obj[13]=bean.getCedingCompany();
				logger.info("Query=>"+query);
				logger.info("Args=>"+StringUtils.join(obj, ","));	
				this.mytemplate.update(query,obj);
		}
	}


	public List<Map<String, Object>> getPlacingInfo(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		try {
			String query=getQuery("GET_PLACEMENT_LIST");
			Object[] obj=new Object[2];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProposalNo();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<Map<String, Object>> getReinsurerInfo(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		List<String> reinsSNo=new ArrayList<String>();
		List<String> reinsureName=new ArrayList<String>();
		List<String> placingBroker=new ArrayList<String>();
		List<String> shareOffer=new ArrayList<String>();
		List<String> mailStatus=new ArrayList<String>();
		try {
			String query=getQuery("GET_REINSURER_INFO");
			Object[] obj=new Object[2];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProposalNo();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
			if(!CollectionUtils.isEmpty(list)) {
				for(int i=0;i<list.size();i++) {
					Map<String,Object>map=list.get(i);
					reinsSNo.add(map.get("SNO")==null?"":map.get("SNO").toString());
					reinsureName.add(map.get("REINSURER_ID")==null?"":map.get("REINSURER_ID").toString());
					placingBroker.add(map.get("BROKER_ID")==null?"":map.get("BROKER_ID").toString());
					shareOffer.add(map.get("SHARE_OFFERED")==null?"":map.get("SHARE_OFFERED").toString());
					mailStatus.add(map.get("MAIL_STATUS")==null?"":map.get("MAIL_STATUS").toString());
				}
				bean.setReinsSNo(reinsSNo);
				bean.setReinsureName(reinsureName);
				bean.setPlacingBroker(placingBroker);
				bean.setShareOffer(shareOffer);
				bean.setMailStatus(mailStatus);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<Map<String, Object>> editPlacingDetails(PlacementBean bean) {
		List<Map<String,Object>>result=null;
		List<Map<String,Object>>list=null;
		List<String> reinsurerIds=new ArrayList<String>();
		List<String> brokerIds=new ArrayList<String>();
		List<String>proposalNos=new ArrayList<String>();
		List<String>shareOffered=new ArrayList<String>();
		List<String>writtenLine=new ArrayList<String>();
		List<String>brokerage=new ArrayList<String>();
		List<String>writtenvaliditydate=new ArrayList<String>();
		List<String>writtenvalidityRemarks=new ArrayList<String>();
		List<String>proposedWL=new ArrayList<String>();
		List<String>signedLine=new ArrayList<String>();
		List<String>proposedSL=new ArrayList<String>();
		List<String>reoffer=new ArrayList<String>();
		List<String>tqrBrokerageAmt=new ArrayList<String>();
		List<String>signedLineValidity=new ArrayList<String>();
		List<String>signedLineRemarks=new ArrayList<String>();
		List<String>emailStatus=new ArrayList<String>();
		try {
			String query=getQuery("GET_PLACEMENT_EDIT");
			Object[] obj=new Object[4];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProposalNo();
			obj[2]=bean.getReinsurerId();
			obj[3]=bean.getBrokerId();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			result=this.mytemplate.queryForList(query, obj);
			if(!CollectionUtils.isEmpty(result)) {
				for(int i=0;i<result.size();i++) {
					Map<String,Object>map=result.get(i);
					reinsurerIds.add(map.get("REINSURER_ID")==null?"":map.get("REINSURER_ID").toString());
					brokerIds.add(map.get("BROKER_ID")==null?"":map.get("BROKER_ID").toString());
					proposalNos.add(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
					shareOffered.add(map.get("SHARE_OFFERED")==null?"":map.get("SHARE_OFFERED").toString());
					writtenLine.add(map.get("SHARE_WRITTEN")==null?"":map.get("SHARE_WRITTEN").toString());
					brokerage.add(map.get("BROKERAGE_PER")==null?"":map.get("BROKERAGE_PER").toString());
					writtenvaliditydate.add(map.get("WRITTEN_LINE_VALIDITY")==null?"":map.get("WRITTEN_LINE_VALIDITY").toString());
					writtenvalidityRemarks.add(map.get("WRITTEN_LINE_REMARKS")==null?"":map.get("WRITTEN_LINE_REMARKS").toString());
					proposedWL.add(map.get("SHARE_PROPOSAL_WRITTEN")==null?"":map.get("SHARE_PROPOSAL_WRITTEN").toString());
					signedLine.add(map.get("SHARE_SIGNED")==null?"":map.get("SHARE_SIGNED").toString());
					proposedSL.add(map.get("SHARE_PROPOSED_SIGNED")==null?"":map.get("SHARE_PROPOSED_SIGNED").toString());
					reoffer.add(map.get("SHARE_OFFERED")==null?"":map.get("SHARE_OFFERED").toString());
					tqrBrokerageAmt.add(map.get("TQR_BROKERAGE_AMT")==null?"":map.get("TQR_BROKERAGE_AMT").toString());
					signedLineValidity.add(map.get("SHARE_LINE_VALIDITY")==null?"":map.get("SHARE_LINE_VALIDITY").toString());
					signedLineRemarks.add(map.get("SHARE_LINE_REMARKS")==null?"":map.get("SHARE_LINE_REMARKS").toString());
					emailStatus.add(map.get("EMAIL_STATUS")==null?"":map.get("EMAIL_STATUS").toString());
				}
				bean.setReinsurerIds(reinsurerIds);
				bean.setBrokerIds(brokerIds);
				bean.setProposalNos(proposalNos);
				bean.setShareOffered(shareOffered);
				bean.setWrittenLine(writtenLine);
				bean.setBrokerage(brokerage);
				bean.setWrittenvaliditydate(writtenvaliditydate);
				bean.setWrittenvalidityRemarks(writtenvalidityRemarks);
				bean.setProposedWL(proposedWL);
				bean.setSignedLine(signedLine);
				bean.setProposedSL(proposedSL);
				bean.setReoffer(reoffer);
				bean.setTqrBrokerageAmt(tqrBrokerageAmt);
				bean.setSignedLineValidity(signedLineValidity);
				bean.setSignedLineRemarks(signedLineRemarks);
				bean.setEmailStatus(emailStatus);
				/*bean.setReinsurerId(map.get("REINSURER_ID")==null?"":map.get("REINSURER_ID").toString());
				bean.setBrokerId(map.get("BROKER_ID")==null?"":map.get("BROKER_ID").toString());
				bean.setShareOffered(map.get("SHARE_OFFERED")==null?"":map.get("SHARE_OFFERED").toString());
				bean.setWrittenLine(map.get("SHARE_WRITTEN")==null?"":map.get("SHARE_WRITTEN").toString());
				bean.setBrokerage(map.get("BROKERAGE")==null?"":map.get("BROKERAGE").toString());
				bean.setWrittenvaliditydate(map.get("WRITTEN_LINE_VALIDITY")==null?"":map.get("WRITTEN_LINE_VALIDITY").toString());
				bean.setWrittenvalidityRemarks(map.get("WRITTEN_LINE_REMARKS")==null?"":map.get("WRITTEN_LINE_REMARKS").toString());
				bean.setProposedWL(map.get("SHARE_PROPOSAL_WRITTEN")==null?"":map.get("SHARE_PROPOSAL_WRITTEN").toString());
				bean.setSignedLine(map.get("SHARE_SIGNED")==null?"":map.get("SHARE_SIGNED").toString());
				bean.setProposedSL(map.get("SHARE_PROPOSED_SIGNED")==null?"":map.get("SHARE_PROPOSED_SIGNED").toString());
				bean.setReoffer(map.get("SHARE_OFFERED")==null?"":map.get("SHARE_OFFERED").toString());
				bean.setTqrBrokerageAmt(map.get("TQR_BROKERAGE_AMT")==null?"":map.get("TQR_BROKERAGE_AMT").toString());
				bean.setSignedLineValidity(map.get("SHARE_LINE_VALIDITY")==null?"":map.get("SHARE_LINE_VALIDITY").toString());
				bean.setSignedLineRemarks(map.get("SHARE_LINE_REMARKS")==null?"":map.get("SHARE_LINE_REMARKS").toString());
				bean.setEmailStatus(map.get("EMAIL_STATUS")==null?"":map.get("EMAIL_STATUS").toString());
			}*/
			}
			query=getQuery("GET_PLACEMENT_STATUS_EDIT");
			obj=new Object[4];
			obj[0]=bean.getProposalNo();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getReinsurerId();
			obj[3]=bean.getBrokerId(); 
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
			if(!CollectionUtils.isEmpty(list)) {
				Map<String,Object>map=list.get(0);
				bean.setEmailBy(map.get("EMAIL_BY")==null?"":map.get("EMAIL_BY").toString());
				bean.setCurrentStatus(map.get("NEW_STATUS")==null?"":map.get("NEW_STATUS").toString());
				if(StringUtils.isBlank(bean.getNewStatus()))
				bean.setNewStatus(map.get("NEW_STATUS")==null?"":map.get("NEW_STATUS").toString());
				bean.setCedentCorrespondent(map.get("CEDENT_CORRESPONDENCE")==null?"":map.get("CEDENT_CORRESPONDENCE").toString());
				bean.setReinsurerCorrespondent(map.get("REINSURER_CORRESPONDENCE")==null?"":map.get("REINSURER_CORRESPONDENCE").toString());
				bean.setTqrCorrespondent(map.get("TQR_CORRESPONDENCE")==null?"":map.get("TQR_CORRESPONDENCE").toString());
				bean.setUpdateDate(map.get("UPDATE_DATE")==null?"":map.get("UPDATE_DATE").toString());
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public void updatePlacement(PlacementBean bean) {
		try {
			
			String query=getQuery("UPDATE_PLACEMENT_INFO");
			Object obj[]=new Object[14];
			for(int i=0;i<bean.getProposalNos().size();i++) {
			obj[0]=bean.getNewStatus();
			obj[1]=bean.getWrittenLine().get(i);
			obj[2]=bean.getProposedWL().get(i);
			obj[3]=bean.getWrittenvaliditydate().get(i);
			obj[4]=bean.getWrittenvalidityRemarks().get(i);
			obj[5]=bean.getSignedLine().get(i);
			obj[6]=bean.getSignedLineValidity().get(i);
			obj[7]=bean.getSignedLineRemarks().get(i);
			obj[8]=bean.getProposedSL().get(i);
			obj[9]=bean.getBrokerage().get(i);
			obj[10]=StringUtils.isBlank(bean.getReoffer().get(i))?bean.getShareOffered().get(i):bean.getReoffer().get(i);
			obj[11]=bean.getProposalNos().get(i);
			obj[12]=bean.getReinsurerIds().get(i);
			obj[13]=bean.getBrokerIds().get(i);
			logger.info("Query=>"+query);   
			logger.info("Args=>"+StringUtils.join(obj, ",")); 
			this.mytemplate.update(query,obj);
			}
			updateStatus(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void updateStatus(PlacementBean bean) {
		try {
			String query=getQuery("INSERT_PLACEMENT_STATUS");
			Object obj[]=new Object[15];
			obj[0]=bean.getProposalNo();
			obj[1]=bean.getProposalNo();
			obj[2]=bean.getReinsurerId();
			obj[3]=bean.getBrokerId();
			obj[4]=bean.getReinsurerId();
			obj[5]=bean.getBrokerId();
			obj[6]=bean.getEmailBy();
			obj[7]=bean.getCurrentStatus();
			obj[8]=bean.getNewStatus();
			obj[9]=bean.getCedentCorrespondent();
			obj[10]=bean.getReinsurerCorrespondent();
			obj[11]=bean.getTqrCorrespondent();
			obj[12]=bean.getUpdateDate();
			obj[13]="Y";
			obj[14]=bean.getBranchCode();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			this.mytemplate.update(query,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
	


