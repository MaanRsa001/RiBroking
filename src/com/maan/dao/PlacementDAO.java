package com.maan.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

import com.maan.bean.PlacementBean;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.login.LogInService;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;

public class PlacementDAO extends MyJdbcTemplate{
	
	
private static final Logger logger = LogUtil.getLogger(PlacementDAO.class);

	String SMTP_AUTH_USER; 
	String SMTP_AUTH_PWD;
	public List<Map<String,Object>> proposalInfo(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		try {
			String proposal=StringUtils.isBlank(bean.getEproposalNo())?bean.getProposalNo():bean.getEproposalNo();
			String query=getQuery("GET_EXISTING_PROPOSAL");
			Object[] obj=new Object[2];
			obj[0]=bean.getBranchCode();
			obj[1]=proposal;
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
				bean.setOfferNo(map.get("OFFER_NO")==null?"":map.get("OFFER_NO").toString());
				if(StringUtils.isBlank(bean.getEproposalNo()))
				bean.setEproposalNo(bean.getProposalNo());
				/*String sql=getQuery("GET_BASE_LAYER_COUNT");
				int count=this.mytemplate.queryForInt(sql,new Object[] {proposal});
				if(count>0) {
					bean.setBaseProposalNo(proposal);
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}


	public void savePlacing(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		try {
			if("C".equalsIgnoreCase(bean.getPlacementMode())) {
				if(StringUtils.isNotBlank(bean.getBouquetNo())) {
					list=new DropDownControllor().getBouquetExistingList(bean.getBranchCode(),bean.getBouquetNo(),bean.getBouquetModeYN());
				}else {
					list=new DropDownControllor().getBaseLayerExistingList(bean.getBranchCode(),bean.getBaseProposalNo());
				}
				for(int i=0;i<list.size();i++) {
					bean.setEproposalNo(list.get(i).get("PROPOSAL_NO")==null?"":list.get(i).get("PROPOSAL_NO").toString());
					insertPlacing(bean);
				}
			}else {
				insertPlacing(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
	}

	public void insertPlacing(PlacementBean bean) {
		String placementNo="";
		try {
			proposalInfo(bean);
			String query=getQuery("GET_PLACEMENT_NO_SEQ");
			placementNo=this.mytemplate.queryForObject(query, String.class);
			bean.setPlacementNo(placementNo);
			DeletePlacement(bean);
			query=getQuery("INSERT_PLACEMENT_INFO");
			for(int i=0;i<bean.getReinsSNo().size();i++) {
				bean.setReinsurerId(bean.getReinsureName().get(i));
				bean.setBrokerId(bean.getPlacingBroker().get(i));;
				if(!"N".equals(bean.getDeleteStatus().get(i))) {
					Object[] obj = new Object[15];
					obj[0]=bean.getPlacementNo();
					obj[1]=bean.getReinsSNo().get(i);
					obj[2]=bean.getBouquetNo();
					obj[3]=bean.getBaseProposalNo();
					obj[4]=bean.getEproposalNo();
					obj[5]=bean.getContractNo();
					obj[6]=bean.getLayerNo();
					obj[7]=bean.getSectionNo();
					obj[8]="0";
					obj[9]=bean.getReinsureName().get(i);
					obj[10]=bean.getPlacingBroker().get(i);
					obj[11]=bean.getShareOffer().get(i);
					obj[12]=bean.getBranchCode();
					obj[13]=bean.getCedingCompany();
					obj[14]=bean.getPlacementMode();
					logger.info("Query=>"+query);
					logger.info("Args=>"+StringUtils.join(obj, ","));	
					this.mytemplate.update(query,obj);
				}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
	}

	private void DeletePlacement(PlacementBean bean) {
		String query="";
		int mailcount=0;
		try {
			Object[] obj=new Object[2];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getEproposalNo();
			query=getQuery("GET_PROPOSAL_MAIL_COUNT");
			mailcount=this.mytemplate.queryForInt(query,obj);
			if(mailcount==0) {
			query=getQuery("DELETE_PLACEMENT_INFO");
			
			//obj[2]=bean.getReinsurerId();
			//obj[3]=bean.getBrokerId();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			this.mytemplate.update(query,obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
	}

	public List<Map<String, Object>> getPlacingInfo(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		try {
			Object[] obj=new Object[2];
			if(StringUtils.isNotBlank(bean.getBouquetNo())) {
				query=getQuery("GET_PLACING_BOUQUET_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBouquetNo();
			}else if(StringUtils.isNotBlank(bean.getBaseProposalNo())) {
				query=getQuery("GET_PLACING_BASELAYER_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBaseProposalNo();
			}else {
				query=getQuery("GET_PLACING_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=StringUtils.isBlank(bean.getEproposalNo())?bean.getProposalNo():bean.getEproposalNo();
			}
			if(StringUtils.isNotBlank(bean.getSearchType())) {
				query=query+" AND P.REINSURER_ID='"+bean.getSearchReinsurerId()+"' AND P.STATUS='"+bean.getSearchStatus()+"'";
			} 
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}
	public List<Map<String, Object>> getPlacementInfoList(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		try {
			Object[] obj=new Object[2];
			if(StringUtils.isNotBlank(bean.getBouquetNo())) {
				query=getQuery("GET_PLACEMENT_BOUQUET_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBouquetNo();
			}else if(StringUtils.isNotBlank(bean.getBaseProposalNo())) {
				query=getQuery("GET_PLACEMENT_BASE_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBaseProposalNo();
			}else {
				query=getQuery("GET_PLACEMENT_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=StringUtils.isBlank(bean.getEproposalNo())?bean.getProposalNo():bean.getEproposalNo();
			}
			if(StringUtils.isNotBlank(bean.getSearchType())) {
				query=query+" AND P.REINSURER_ID='"+bean.getSearchReinsurerId()+"' AND P.STATUS='"+bean.getSearchStatus()+"'";
			}
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}

	public List<Map<String, Object>> getReinsurerInfo(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		int mailcount=0;
		List<String> reinsSNo=new ArrayList<String>();
		List<String> reinsureName=new ArrayList<String>();
		List<String> placingBroker=new ArrayList<String>();
		List<String> shareOffer=new ArrayList<String>();
		List<String> mailStatus=new ArrayList<String>();
		List<String> proposalNos=new ArrayList<String>();
		List<String> deleteStatus=new ArrayList<String>();
		try {
			Object[] obj=new Object[2];
			query=getQuery("GET_REINSURER_INFO_NOTIN");
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getEproposalNo();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
			if(!CollectionUtils.isEmpty(list)) {
			bean.setPlacementMode(list.get(0).get("PLACEMENT_MODE")==null?"":list.get(0).get("PLACEMENT_MODE").toString());
			bean.setPlacementDisabled("Y");
			}
			query=getQuery("GET_PROPOSAL_MAIL_COUNT");
			mailcount=this.mytemplate.queryForInt(query,obj);
			if(StringUtils.isNotBlank(bean.getBouquetNo()) && "C".equals(bean.getPlacementMode())) {
				query=getQuery("GET_REINSURER_INFO_BOUQUET_NOTIN");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBouquetNo();
				logger.info("Query=>"+query);
				logger.info("Args=>"+StringUtils.join(obj, ","));
				list=this.mytemplate.queryForList(query, obj);
				query=getQuery("GET_BOUQUET_MAIL_COUNT");
				mailcount=this.mytemplate.queryForInt(query,obj);
			}
			if(!CollectionUtils.isEmpty(list)) {
				for(int i=0;i<list.size();i++) {
					Map<String,Object>map=list.get(i);
					String mailstatus=map.get("MAIL_STATUS")==null?"":map.get("MAIL_STATUS").toString();
					reinsSNo.add(map.get("SNO")==null?"":map.get("SNO").toString());
					reinsureName.add(map.get("REINSURER_ID")==null?"":map.get("REINSURER_ID").toString());
					placingBroker.add(map.get("BROKER_ID")==null?"":map.get("BROKER_ID").toString());
					shareOffer.add(map.get("SHARE_OFFERED")==null?"":map.get("SHARE_OFFERED").toString());
					mailStatus.add(mailstatus);
					proposalNos.add(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
					if(mailcount>0) {
						deleteStatus.add("N");
					}else {
						deleteStatus.add("Y");
					}
				}
				bean.setReinsSNo(reinsSNo);
				bean.setReinsureName(reinsureName);
				bean.setPlacingBroker(placingBroker);
				bean.setShareOffer(shareOffer);
				bean.setMailStatus(mailStatus);
				bean.setProposalNos(proposalNos);
				bean.setDeleteStatus(deleteStatus);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}


	public List<Map<String, Object>> editPlacingDetails(PlacementBean bean) {
		List<Map<String,Object>>result=null;
		List<Map<String,Object>>list=null;
		List<String> snos=new ArrayList<String>();
		List<String> bouquetNos=new ArrayList<String>();
		List<String>baseproposalNos=new ArrayList<String>();
		List<String> reinsurerIds=new ArrayList<String>();
		List<String> brokerIds=new ArrayList<String>();
		List<String>proposalNos=new ArrayList<String>();
		List<String> reinsurerNames=new ArrayList<String>();
		List<String> brokerNames=new ArrayList<String>();
		List<String>cedingCompanys=new ArrayList<String>();
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
		List<String>psignedLine=new ArrayList<String>();
		
		try {
			String query="";
			Object[]obj=null;
			if(StringUtils.isBlank(bean.getSearchType())) {
				result=GetPlacementEdit(bean);
			}else {
				result=GetPlacementSearchEdit(bean);
			}
			
			if(!CollectionUtils.isEmpty(result)) {
				for(int i=0;i<result.size();i++) {
					Map<String,Object>map=result.get(i);
					snos.add(map.get("SNO")==null?"":map.get("SNO").toString());
					bouquetNos.add(map.get("BOUQUET_NO")==null?"":map.get("BOUQUET_NO").toString());
					baseproposalNos.add(map.get("BASE_PROPOSAL_NO")==null?"":map.get("BASE_PROPOSAL_NO").toString());
					reinsurerIds.add(map.get("REINSURER_ID")==null?"":map.get("REINSURER_ID").toString());
					brokerIds.add(map.get("BROKER_ID")==null?"":map.get("BROKER_ID").toString());
					proposalNos.add(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
					reinsurerNames.add(map.get("REINSURER_NAME")==null?"":map.get("REINSURER_NAME").toString());
					brokerNames.add(map.get("BROKER_NAME")==null?"":map.get("BROKER_NAME").toString());
					cedingCompanys.add(map.get("CEDING_COMPANY_ID")==null?"":map.get("CEDING_COMPANY_ID").toString());
					shareOffered.add(map.get("SHARE_OFFERED")==null?"":map.get("SHARE_OFFERED").toString());
					writtenLine.add(map.get("SHARE_WRITTEN")==null?"":map.get("SHARE_WRITTEN").toString());
					brokerage.add(map.get("BROKERAGE_PER")==null?"":map.get("BROKERAGE_PER").toString());
					writtenvaliditydate.add(map.get("WRITTEN_LINE_VALIDITY")==null?"":map.get("WRITTEN_LINE_VALIDITY").toString());
					writtenvalidityRemarks.add(map.get("WRITTEN_LINE_REMARKS")==null?"":map.get("WRITTEN_LINE_REMARKS").toString());
					proposedWL.add(map.get("SHARE_PROPOSAL_WRITTEN")==null?"":map.get("SHARE_PROPOSAL_WRITTEN").toString());
					signedLine.add(map.get("SHARE_SIGNED")==null?"":map.get("SHARE_SIGNED").toString());
					psignedLine.add(map.get("SHARE_SIGNED")==null?"":map.get("SHARE_SIGNED").toString());
					proposedSL.add(map.get("SHARE_PROPOSED_SIGNED")==null?"":map.get("SHARE_PROPOSED_SIGNED").toString());
					reoffer.add(map.get("SHARE_OFFERED")==null?"":map.get("SHARE_OFFERED").toString());
					tqrBrokerageAmt.add(map.get("TQR_BROKERAGE_AMT")==null?"":map.get("TQR_BROKERAGE_AMT").toString());
					signedLineValidity.add(map.get("SHARE_LINE_VALIDITY")==null?"":map.get("SHARE_LINE_VALIDITY").toString());
					signedLineRemarks.add(map.get("SHARE_LINE_REMARKS")==null?"":map.get("SHARE_LINE_REMARKS").toString());
					emailStatus.add(map.get("MAIL_STATUS")==null?"":map.get("MAIL_STATUS").toString());
				}
				bean.setSnos(snos);
				bean.setBaseproposalNos(baseproposalNos);
				bean.setBouquetNos(bouquetNos);
				bean.setReinsurerIds(reinsurerIds);
				bean.setReinsurerNames(reinsurerNames);
				bean.setBrokerNames(brokerNames);
				bean.setCedingCompanys(cedingCompanys);
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
				bean.setPsignedLine(psignedLine);
			}
			query=getQuery("GET_PLACEMENT_STATUS_EDIT");
			obj=new Object[4];
			obj[0]=bean.getEproposalNo();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getReinsurerId();
			obj[3]=bean.getBrokerId(); 
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
			if(!CollectionUtils.isEmpty(list)) {
				Map<String,Object>map=list.get(0);
				//bean.setEmailBy(map.get("EMAIL_BY")==null?"":map.get("EMAIL_BY").toString());
				bean.setCurrentStatus(map.get("NEW_STATUS")==null?"":map.get("NEW_STATUS").toString());
				//bean.setCedentCorrespondent(map.get("CEDENT_CORRESPONDENCE")==null?"":map.get("CEDENT_CORRESPONDENCE").toString());
				//bean.setReinsurerCorrespondent(map.get("REINSURER_CORRESPONDENCE")==null?"":map.get("REINSURER_CORRESPONDENCE").toString());
				//bean.setTqrCorrespondent(map.get("TQR_CORRESPONDENCE")==null?"":map.get("TQR_CORRESPONDENCE").toString());
				//bean.setUpdateDate(map.get("UPDATE_DATE")==null?"":map.get("UPDATE_DATE").toString());
				
			}else {
				if(StringUtils.isBlank(bean.getNewStatus())) {
				bean.setCurrentStatus(StringUtils.isBlank(bean.getSearchStatus())?"O":bean.getSearchStatus());
				//bean.setNewStatus(bean.getCurrentStatus());
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
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
			if("RO".equalsIgnoreCase(bean.getNewStatus())) {
				obj[10]=bean.getReoffer().get(i);
			}else {
				obj[10]=bean.getShareOffered().get(i);
			}
			obj[11]=bean.getProposalNos().get(i);
			obj[12]=bean.getReinsurerIds().get(i);
			obj[13]=bean.getBrokerIds().get(i);
			logger.info("Query=>"+query);   
			logger.info("Args=>"+StringUtils.join(obj, ",")); 
			this.mytemplate.update(query,obj);
			}
			updateStatus(bean,"");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		
	}
	public void updateStatus(PlacementBean bean,String status) {
		String corresId="";
		try {
			String query=getQuery("GET_PLACEMENT_NO_SEQ");
			corresId=this.mytemplate.queryForObject(query, String.class);
			bean.setCorresId(corresId);
			 query=getQuery("INSERT_PLACEMENT_STATUS");
			Object obj[]=new Object[19];
			for(int i=0;i<bean.getProposalNos().size();i++) {
				obj[0]=bean.getSnos().get(i);
				obj[1]=bean.getBouquetNos().get(i);
				obj[2]=bean.getBaseproposalNos().get(i);
				obj[3]=bean.getProposalNos().get(i);
				obj[4]=bean.getProposalNos().get(i);
				obj[5]=bean.getReinsurerIds().get(i);
				obj[6]=bean.getBrokerIds().get(i);
				obj[7]=bean.getReinsurerIds().get(i);
				obj[8]=bean.getBrokerIds().get(i);
				obj[9]=bean.getEmailBy();
				obj[10]=bean.getCurrentStatus();
				obj[11]=StringUtils.isBlank(bean.getNewStatus())?status:bean.getNewStatus();
				obj[12]=bean.getCedentCorrespondent();
				obj[13]=bean.getReinsurerCorrespondent();
				obj[14]=bean.getTqrCorrespondent();
				obj[15]=bean.getUpdateDate();
				obj[16]="Y";
				obj[17]=bean.getBranchCode();
				obj[18]=bean.getCorresId();
				
				logger.info("Query=>"+query);
				logger.info("Args=>"+StringUtils.join(obj, ","));
				this.mytemplate.update(query,obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
	}


	public void getMailTemplate(PlacementBean bean) {
		
		List<Map<String,Object>>result=null;
		String query=getQuery("GET_MAIL_TEMPLATE");
		Object[] obj=new Object[1];
		obj[0]=bean.getMailType();
		
		logger.info("Query=>"+query);
		logger.info("Args=>"+StringUtils.join(obj, ","));
		result=this.mytemplate.queryForList(query, obj);
		if(!CollectionUtils.isEmpty(result)) {
			Map<String,Object>map=result.get(0);
			bean.setMailSubject(map.get("MAIL_SUBJECT")==null?"":map.get("MAIL_SUBJECT").toString());
			bean.setMailBody(map.get("MAIL_BODY")==null?"":map.get("MAIL_BODY").toString());
			if(StringUtils.isBlank(bean.getMailTo()))
			bean.setMailTo(map.get("EMAIL_TO")==null?"":map.get("EMAIL_TO").toString());
			if(StringUtils.isBlank(bean.getMailCC()))
			bean.setMailCC(map.get("EMAIL_CC")==null?"":map.get("EMAIL_CC").toString());
			bean.setMailRegards(map.get("MAIL_REGARDS")==null?"":map.get("MAIL_REGARDS").toString());
			
	}
		GetMailBodyFrame(bean);
	}


	private void GetMailBodyFrame(PlacementBean bean) {
		String mailbody=bean.getMailBody(),mailsub=bean.getMailSubject();
		List<Map<String,Object>>list=proposalInfo(bean);
		if(!CollectionUtils.isEmpty(list)) {
			Map<String,Object>map=list.get(0);
			String bouquetNo=map.get("BOUQUET_NO")==null?"":map.get("BOUQUET_NO").toString();
			String proposalNo=map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString();
			String baseproposalNo=map.get("BASE_LAYER")==null?"":map.get("BASE_LAYER").toString();
			String offerNo=map.get("OFFER_NO")==null?"":map.get("OFFER_NO").toString();
			if(StringUtils.isNotBlank(bouquetNo)) {
				mailsub=mailsub+" "+bouquetNo+"";
			}else if(StringUtils.isNotBlank(offerNo)) {
				mailsub=mailsub+" "+offerNo+" ";
			}
			for (Map.Entry entry: map.entrySet()) {
				if(mailbody.contains(entry.getKey().toString()) == true) {
					mailbody = mailbody.replace("{"+entry.getKey().toString()+"}", entry.getValue()==null?"":entry.getValue().toString());
					
				}
				
				if(mailsub.contains(entry.getKey().toString()) == true) {
					mailsub = mailsub.replace("{"+entry.getKey().toString()+"}", entry.getValue()==null?"":entry.getValue().toString());
				}
			}
		}
		mailbody+=BodyTableFrame(bean);
		bean.setMailBody(mailbody);
		bean.setMailSubject(mailsub);
	}


	private String BodyTableFrame(PlacementBean bean) {
		List<Map<String,Object>>list=MailproposalInfo(bean);
		String msg=getTableMsg(list,bean);
		return msg;
	}
	private List<Map<String, Object>> MailproposalInfo(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		try {
			Object[] obj=new Object[2];
			if("C".equals(bean.getPlacementMode())) {
				if(StringUtils.isNotBlank(bean.getBouquetNo())) {
					query=getQuery("GET_MAILTEPLATE_BOUQUET");
					obj[0]=bean.getBranchCode();
					obj[1]=bean.getBouquetNo();
				}else {
					query=getQuery("GET_MAILTEPLATE_BASELAYER");
					obj[0]=bean.getBranchCode();
					obj[1]=bean.getBaseProposalNo();
				}
			}else {
				query=getQuery("GET_MAILTEPLATE_PROPOSAL");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getProposalNo();
			}
			
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}


	private String getTableMsg(List<Map<String, Object>> agentWiseReport, PlacementBean bean) {
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
			"<td style=\"border: 1px solid #000000;text-align: right;\">"+(map.get("SAHRE_MAX")==null?DropDownControllor.formatterpercentage(bean.getMaxSharePercent()):map.get("SAHRE_MAX").toString())+"</td>" + 
			"</tr>";
		}
		messageContent+=	"</table>" +
				"    </div>" + 
				"</body>" + 
				"" + 
				"</html>";
		
		
	
	return messageContent.toString();
	}


	public void sendMail(PlacementBean bean) {
		try {
			Map<String, String> mapt=new CommonDAO().getMailDetails("51");
			LogInService service=new LogInService();
			String hostName=(String)mapt.get("SMTP_HOST");
			String user = mapt.get("SMTP_USER");
			String pwd = mapt.get("SMTP_PWD");
			String port=mapt.get("SMTP_PORT");
			String mailform = mapt.get("SMTP_ADDRESS");
			String shortAddress = mapt.get("SMTP_SHORT_ADDRESS");
			String subject = bean.getMailSubject();
			String toAddress = bean.getMailTo();
			String ccAddress = bean.getMailCC();
			String mailBody= bean.getMailBody()+"<br/>"+bean.getMailRemarks()+""+bean.getMailRegards();
			bean.setMailBody(mailBody);
			if(toAddress!=null && !"".equals(toAddress)){
				String[] toAddresses = (toAddress.indexOf(",")!=-1)?toAddress.split(","):new String[]{toAddress};
				String[] ccAddresses = new String[0];
				if(ccAddress!=null && !"".equals(ccAddress)){
					ccAddresses = (ccAddress.indexOf(",")!=-1)?ccAddress.split(","):new String[]{ccAddress};
				}
			insertMailDetails(bean);
			Multipart multipart=GetMailAttachment(bean);
			String status=sendResponseMail(hostName, user, pwd, mailform, subject, multipart, toAddresses, ccAddresses, shortAddress,port);
			if("Success".equals(status)) {
				updateStatus(bean,"P");
			}
			updateMailDetails(bean,status);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}

	}


	private Multipart GetMailAttachment(PlacementBean bean) {
		Multipart multipart = new MimeMultipart();
		try {
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setContent(bean.getMailBody(),"text/html;charset=UTF-8");
			multipart.addBodyPart(messageBodyPart1);
			List<Map<String,Object>>list=getExistingAttachList(bean);
			if(!CollectionUtils.isEmpty(list)) {
				for(int i=0;i<list.size();i++) {
					Map<String,Object>map=list.get(i);
					BodyPart messageBodyPart = new MimeBodyPart();
					String filePath=ServletActionContext.getServletContext().getRealPath("/")+"documents/";
					String fileName=map.get("ORG_FILE_NAME")==null?"":map.get("ORG_FILE_NAME").toString();
					String orgfileName=map.get("OUR_FILE_NAME")==null?"":map.get("OUR_FILE_NAME").toString();
					if(fileName!=null ){
						DataSource source = new FileDataSource(filePath+orgfileName);
						messageBodyPart.setDataHandler(new DataHandler(source));
						messageBodyPart.setFileName(fileName);
						multipart.addBodyPart(messageBodyPart);
					 }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return multipart;
	}


	private void updateMailDetails(PlacementBean bean,String status) {
		String query="";
		try {
		
		for(int i=0;i<bean.getProposalNos().size();i++) {
		query=getQuery("UPDATE_MAIL_NOTIFICATION");
		Object[] obj=new Object[5];
		obj[0]=status;
		obj[1]=bean.getProposalNos().get(i);;
		obj[2]=bean.getReinsurerIds().get(i);
		obj[3]=bean.getBrokerIds().get(i);
		obj[4]=bean.getBranchCode();
		logger.info("Query=>"+query);
		logger.info("Args=>"+StringUtils.join(obj, ","));	
		this.mytemplate.update(query,obj);
		
		obj[0]=bean.getMailType();
		query=getQuery("UPDATE_PLACEMENT_STATUS");
		logger.info("Query=>"+query);
		logger.info("Args=>"+StringUtils.join(obj, ","));	
		this.mytemplate.update(query,obj);
		
		obj=new Object[4];
		obj[0]=bean.getProposalNos().get(i);;
		obj[1]=bean.getReinsurerIds().get(i);
		obj[2]=bean.getBrokerIds().get(i);
		obj[3]=bean.getBranchCode();
		query=getQuery("UPDATE_ATTACHMENT_MAIL");
		logger.info("Query=>"+query);
		logger.info("Args=>"+StringUtils.join(obj, ","));	
		this.mytemplate.update(query,obj);
		
		
		}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		
	}


	private void insertMailDetails(PlacementBean bean) {
		String query="";
		List<String> snos=new ArrayList<String>();
		List<String> bouquetNos=new ArrayList<String>();
		List<String>baseproposalNos=new ArrayList<String>();
		List<String> reinsurerIds=new ArrayList<String>();
		List<String> brokerIds=new ArrayList<String>();
		List<String>proposalNos=new ArrayList<String>();
		List<Map<String,Object>>list=null;
		try {
			query=getQuery("INSERT_MAIL_NOTIFICATION");
			if("C".equals(bean.getPlacementMode())) {
				list=GetPlacementBouquet(bean);
			}
			else {
				list=GetPlacementEdit(bean);
			}
			
			if(!CollectionUtils.isEmpty(list)) {
				for(int  i=0;i<list.size();i++) {
					Map<String,Object>map=list.get(i);
					bean.setSno(map.get("SNO")==null?"":map.get("SNO").toString());
					bean.setBouquetNo(map.get("BOUQUET_NO")==null?"":map.get("BOUQUET_NO").toString());
					bean.setBaseProposalNo(map.get("BASE_PROPOSAL_NO")==null?"":map.get("BASE_PROPOSAL_NO").toString());
					bean.setBrokerId(map.get("BROKER_ID")==null?"":map.get("BROKER_ID").toString());
					bean.setReinsurerId(map.get("REINSURER_ID")==null?"":map.get("REINSURER_ID").toString());
					bean.setEproposalNo(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
					snos.add(bean.getSno());
					bouquetNos.add(bean.getBouquetNo());
					baseproposalNos.add(bean.getBaseProposalNo());
					reinsurerIds.add(bean.getReinsurerId());
					brokerIds.add(bean.getBrokerId());
					proposalNos.add(bean.getEproposalNo());
					Object[] obj = new Object[19];
					obj[0]=bean.getSno();
					obj[1]=bean.getBouquetNo();
					obj[2]=bean.getBaseProposalNo();
					obj[3]=bean.getEproposalNo();
					obj[4]=map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString();
					obj[5]=map.get("LAYER_NO")==null?"":map.get("LAYER_NO").toString();
					obj[6]=map.get("SECTION_NO")==null?"":map.get("SECTION_NO").toString();
					obj[7]=map.get("CEDING_COMPANY_ID")==null?"":map.get("CEDING_COMPANY_ID").toString();
					obj[8]=bean.getReinsurerId();
					obj[9]=bean.getBrokerId();
					obj[10]="63".equals(bean.getBrokerId())?bean.getReinsurerId():bean.getBrokerId();
					obj[11]=bean.getMailType();
					obj[12]=bean.getMailTo();
					obj[13]=bean.getMailCC();
					obj[14]=bean.getMailSubject();
					obj[15]=bean.getMailBody();
					obj[16]=bean.getBranchCode();
					obj[17]=bean.getUserId();
					obj[18]="Pending";
					logger.info("Query=>"+query);
					logger.info("Args=>"+StringUtils.join(obj, ","));	
					this.mytemplate.update(query,obj);
				}
				bean.setSnos(snos);
				bean.setBouquetNos(bouquetNos);
				bean.setBaseproposalNos(baseproposalNos);
				bean.setReinsurerIds(reinsurerIds);
				bean.setBrokerIds(brokerIds);
				bean.setProposalNos(proposalNos);
			}
			
					
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
	}


	private List<Map<String, Object>> GetPlacementBouquet(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		try {
			Object[] obj=new Object[4];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getBouquetNo();
			obj[2]=bean.getReinsurerId();
			obj[3]=bean.getBrokerId();
			if(StringUtils.isNotBlank(bean.getBouquetNo())) {
				query=getQuery("GET_PLACEMENT_BOUQUET");
			}else {
				query=getQuery("GET_PLACEMENT_BASELAYER");
				obj[1]=bean.getBaseProposalNo();
			}
			
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}


	private List<Map<String, Object>> GetPlacementEdit(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		try {
			String query=getQuery("GET_PLACEMENT_EDIT");
			Object[] obj=new Object[4];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getEproposalNo();
			obj[2]=bean.getReinsurerId();
			obj[3]=bean.getBrokerId();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}
	private List<Map<String, Object>> GetPlacementSearchEdit(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		try {
			Object[] obj=new Object[4];
			obj[0]=bean.getBranchCode();
			obj[2]=bean.getSearchReinsurerId();
			obj[3]=bean.getSearchStatus();
			if(StringUtils.isNotBlank(bean.getBouquetNo())) {
				query=getQuery("GET_PLACEMENT_SEARCHBQ_EDIT");
				obj[1]=bean.getBouquetNo();
			}else if(StringUtils.isNotBlank(bean.getBaseProposalNo())) {
				query=getQuery("GET_PLACEMENT_SEARCHBP_EDIT");
				obj[1]=bean.getBaseProposalNo();
			}
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}
	

	public List<Map<String, Object>> getExReinsurerInfo(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		try {
			Object[] obj=new Object[2];
			if(StringUtils.isNotBlank(bean.getBouquetNo()) || "C".equals(bean.getPlacementMode())) {
				query=getQuery("GET_REINSURER_INFO_BOUQUET_IN");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBouquetNo();
			}else {
				query=getQuery("GET_REINSURER_INFO_IN");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getEproposalNo();
			}
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}


	public List<Map<String, Object>> getExistingReinsurerList(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		try {
			Object[] obj=new Object[2];
			if(StringUtils.isNotBlank(bean.getBouquetNo())) {
				query=getQuery("GET_EX_REINSURER_BOUQUET_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBouquetNo();
			}else if(StringUtils.isNotBlank(bean.getBaseProposalNo())) {
				query=getQuery("GET_EX_REINSURER_BASE_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBaseProposalNo();
			}else {
				query=getQuery("GET_EX_REINSURER_PRO_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=StringUtils.isBlank(bean.getEproposalNo())?bean.getProposalNo():bean.getEproposalNo();
			}
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}
	public String attachFile(PlacementBean bean) {
		String query="",result="";
		List<Map<String,Object>>list=null;
		try {
			
			if("C".equals(bean.getPlacementMode())) {
				list=GetPlacementBouquet(bean);
			}
			else {
				list=GetPlacementEdit(bean);
			}
			
			if(!CollectionUtils.isEmpty(list)) {
				for(int  i=0;i<list.size();i++) {
					Map<String,Object>map=list.get(i);
					bean.setSno(map.get("SNO")==null?"":map.get("SNO").toString());
					bean.setBouquetNo(map.get("BOUQUET_NO")==null?"":map.get("BOUQUET_NO").toString());
					bean.setBaseProposalNo(map.get("BASE_PROPOSAL_NO")==null?"":map.get("BASE_PROPOSAL_NO").toString());
					bean.setBrokerId(map.get("BROKER_ID")==null?"":map.get("BROKER_ID").toString());
					bean.setReinsurerId(map.get("REINSURER_ID")==null?"":map.get("REINSURER_ID").toString());
					bean.setEproposalNo(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
					insertDocdetails(bean);
					}
			}
			
					
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return result;
	}


	public List<Map<String, Object>> getExistingAttachList(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		try {
			Object[] obj=new Object[5];
			/*if(StringUtils.isNotBlank(bean.getBouquetNo())) {
				query=getQuery("GET_EX_DOC_BOUQUET_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBouquetNo();
				obj[2]=bean.getReinsurerId();
				obj[3]=bean.getBrokerId();
			}else if(StringUtils.isNotBlank(bean.getBaseProposalNo())) {
				query=getQuery("GET_EX_DOC_BASE_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getBaseProposalNo();
				obj[2]=bean.getReinsurerId();
				obj[3]=bean.getBrokerId();
			}else {*/
				query=getQuery("GET_EX_DOC_PRO_LIST");
				obj[0]=bean.getBranchCode();
				obj[1]=StringUtils.isBlank(bean.getEproposalNo())?bean.getProposalNo():bean.getEproposalNo();
				obj[2]=bean.getReinsurerId();
				obj[3]=bean.getBrokerId();
				obj[4]=bean.getCorresId();
				/* } */
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}


	public String deleteFile(PlacementBean bean) {
		String query="";
		try {
		
		query=getQuery("DELETE_ATTACHED_FILE");
		Object[] obj=new Object[2];
		obj[0]=bean.getDocId();
		obj[1]=bean.getFileName();;
		logger.info("Query=>"+query);
		logger.info("Args=>"+StringUtils.join(obj, ","));	
		this.mytemplate.update(query,obj);
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return "";
	}


	public String downloadFile(PlacementBean bean) {
		String query="";
		String result="";
		try {
		
		query=getQuery("DOWNLOAD_ATTACHED_FILE");
		Object[] obj=new Object[2];
		obj[0]=bean.getDocId().get(0);
		obj[1]=bean.getFileName();
		logger.info("Query=>"+query);
		logger.info("Args=>"+StringUtils.join(obj, ","));	
		result=this.mytemplate.queryForObject(query, obj,String.class);
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return result;
	}
	public String sendResponseMail(final String SMTP_HOST_NAME, final String user,  final String pwd, final String SMTP_MAIL_FROM, final String subject,
    		final Multipart message, final String[] toAddress, final String[] ccAddress, final String SMTP_SHORT_ADDRESS,final String SMTP_PORT){
    	 logger.info("Enter sendResponseMail");
    	 String status="";
    	SMTP_AUTH_USER = user;
    	SMTP_AUTH_PWD = pwd;
    	try{
    		
    		
    		 Properties props = System.getProperties();
    		 props.setProperty("mail.smtp.host", SMTP_HOST_NAME);
    		 props.put("mail.smtp.port", SMTP_PORT);
    		 props.put("mail.smtp.starttls.enable", "true");
    		 props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    		 props.put("mail.debug", "true");
	    	
			Session session = null; 
			if(SMTP_AUTH_PWD != null && !"".equals(SMTP_AUTH_PWD.trim())){
				props.put("mail.smtp.auth", "true");
				Authenticator auth = new SMTPAuthenticator();
				session = Session.getInstance(props, auth);
			}else{
				props.put("mail.smtp.auth", "false");
				session = Session.getInstance(props);
			}
			session.setDebug(false);
			
			Message msg1 = new MimeMessage(session);
			
			InternetAddress addressFrom = new InternetAddress(SMTP_MAIL_FROM, SMTP_SHORT_ADDRESS);
			msg1.setFrom(addressFrom);
			if(toAddress != null && toAddress.length>0){
				InternetAddress[] addressTo = new InternetAddress[toAddress.length];			
				for (int i = 0; i < toAddress.length; i++){
					addressTo[i] = new InternetAddress(toAddress[i]);
					msg1.addRecipient(Message.RecipientType.TO, addressTo[i]);
				}
			}
			if(ccAddress != null && ccAddress.length>0){
				InternetAddress[] addressToCC = new InternetAddress[ccAddress.length];			
				for(int i=0;i<ccAddress.length;i++){
					addressToCC[i] = new InternetAddress(ccAddress[i]);
					msg1.addRecipient(Message.RecipientType.CC, addressToCC[i]);
				}
			}
			msg1.setSubject(subject);
				
			msg1.setContent(message);
			
			System.out.println(msg1);
			logger.info("Mail msg"+msg1);
			Transport transport = session.getTransport("smtp");
            transport.connect(SMTP_HOST_NAME, Integer.parseInt(SMTP_PORT),SMTP_AUTH_USER, SMTP_AUTH_PWD);
            transport.sendMessage(msg1, msg1.getAllRecipients());
			status="Success";
			System.out.println("Mail successfully sent");
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("Mail successfully Not Senrsent");
			status="Failed";
			logger.error("Exception:", e);
		}
    	logger.info("Exit sendResponseMail");
    	return status;
    }
	 private class SMTPAuthenticator extends javax.mail.Authenticator{
			public PasswordAuthentication getPasswordAuthentication(){
				String username = SMTP_AUTH_USER;
				String password = SMTP_AUTH_PWD;
				return new PasswordAuthentication(username, password);
			}
	}
	 public void insertDocdetails(PlacementBean bean) {
		 
		 try {
			 if(bean.getUpload()!=null) {
				 String filePath=bean.getFilePath();
				
				File tmpFile = new File(filePath);
				if(!tmpFile.exists()){
					tmpFile.mkdir();
				}
				String sql=getQuery("GET_DOC_SEQUENCE");
				String docId=this.mytemplate.queryForObject(sql, String.class);
				for(int i=0;i<bean.getDocTypeId().size();i++) {
				final String orgFileName=bean.getUploadFileName().get(i);
				Calendar cal = Calendar.getInstance();
				String time = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"
				+cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.HOUR)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
				String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
				String fileName = orgFileName.substring(0, orgFileName.lastIndexOf("."))+"_"+time;
				fileName = fileName + ext;
				final File copyFile = new File(filePath+fileName);
				
				FileUtils.copyFile(bean.getUpload().get(i), copyFile);
				bean.setFileName(fileName);
				
				
				//bean.setDocId(StringUtils.isBlank(docId)?"1":docId);
				String query=getQuery("INSET_NOTIFY_ATTACHEMENT");
					
					
					Object[] obj = new Object[15];
					obj[0]=StringUtils.isBlank(docId)?"1":docId;
					obj[1]=bean.getDocTypeId().get(i);
					obj[2]=bean.getSno();
					obj[3]=bean.getBouquetNo();
					obj[4]=bean.getBaseProposalNo();
					obj[5]=bean.getEproposalNo();
					obj[6]=bean.getReinsurerId();
					obj[7]=bean.getBrokerId();
					obj[8]=bean.getUploadFileName().get(i);
					obj[9]=bean.getFileName();
					obj[10]=bean.getFilePath();
					obj[11]=bean.getBranchCode();
					obj[12]=bean.getUserId();
					obj[13]=StringUtils.isBlank(bean.getCorresId())?"":bean.getCorresId();
					obj[14]=bean.getDocDesc().get(i);
					logger.info("Query=>"+query);
					logger.info("Args=>"+StringUtils.join(obj, ","));	
					this.mytemplate.update(query,obj);
				}
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		 
	 }


	public void uploadDocument(PlacementBean bean) {
		for(int i=0;i<bean.getProposalNos().size();i++) {
			bean.setSno(bean.getSnos().get(i));
			bean.setBouquetNo(bean.getBouquetNos().get(i));
			bean.setBaseProposalNo(bean.getBaseproposalNos().get(i));
			bean.setBrokerId(bean.getBrokerIds().get(i));
			bean.setReinsurerId(bean.getReinsurerIds().get(i));
			bean.setEproposalNo(bean.getProposalNos().get(i));
			insertDocdetails(bean);
		}
		
	}
	
	 public static void main(String[] args) throws Exception {

	        Properties properties = System.getProperties();
	        properties.setProperty("mail.smtp.host", "smtp.zeptomail.com");
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.from", "fromaddress");
	        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        Session session = Session.getDefaultInstance(properties);

	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("noreply@nsuretqr.com"));
	                message.addRecipient(Message.RecipientType.TO, new InternetAddress("3s@aromgrips.com"));
	            message.setSubject("Test Email");
	                message.setText("Test email sent successfully.");
	            Transport transport = session.getTransport("smtp");
	            transport.connect("smtp.zeptomail.com", 587, "emailapikey", "wSsVR60j+hSmBvt6nDOtdb08m1ldUVKjRk5+2FSj6CevGfyW9sdqkBefDVX0FfROEmI6QTdAorgpkU9SgzJdj4gly1hRDiiF9mqRe1U4J3x17qnvhDzPVm1clBGJLIsJxQtpmWVoF8gk+g==");
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	            System.out.println("Mail successfully sent");
	        } catch (Exception ex) {
	            System.out.print(ex.getMessage());
	        }
	    }


	public List<Map<String, Object>> getPlacementViewList(PlacementBean bean) {
		List<Map<String,Object>>list=null;
		String query="";
		try {
			Object[] obj=new Object[4];
			
				query=getQuery("GET_PLACEMENT_VIEW");
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getEproposalNo();
				obj[2]=bean.getReinsurerId();
				obj[3]=bean.getBrokerId();
			
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
		return list;
	}
	public void getPlacementView(PlacementBean bean) {
		String query="";
		Object[]obj=null;
		List<Map<String,Object>>list=null;
		try {
			query=getQuery("GET_PLACEMENT_STATUS_VIEW");
			obj=new Object[5];
			obj[0]=bean.getEproposalNo();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getReinsurerId();
			obj[3]=bean.getBrokerId(); 
			obj[4]=bean.getNewStatus(); 
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			list=this.mytemplate.queryForList(query, obj);
			if(!CollectionUtils.isEmpty(list)) {
				Map<String,Object>map=list.get(0);
				bean.setEmailBy(map.get("EMAIL_BY")==null?"":map.get("EMAIL_BY").toString());
				bean.setCurrentStatus(map.get("NEW_STATUS")==null?"":map.get("NEW_STATUS").toString());
				bean.setCedentCorrespondent(map.get("CEDENT_CORRESPONDENCE")==null?"":map.get("CEDENT_CORRESPONDENCE").toString());
				bean.setReinsurerCorrespondent(map.get("REINSURER_CORRESPONDENCE")==null?"":map.get("REINSURER_CORRESPONDENCE").toString());
				bean.setTqrCorrespondent(map.get("TQR_CORRESPONDENCE")==null?"":map.get("TQR_CORRESPONDENCE").toString());
				bean.setUpdateDate(map.get("UPDATE_DATE")==null?"":map.get("UPDATE_DATE").toString());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:", e);
		}
	}
	
	
}


	
	


