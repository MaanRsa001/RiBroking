package com.maan.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.bean.UploadBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.LogUtil;
import com.maan.dao.UploadDAO;

public class UploadDAOImpl extends MyJdbcTemplate implements UploadDAO {
	private static final Logger LOGGER = LogUtil.getLogger(UploadDAOImpl.class);
	
	public List<Map<String,Object>> getDocList(final UploadBean bean,String branchCode) {
		List<Map<String,Object>> list = null;
		try{
			Object[] obj=null;
			String sql=null;
			if("document".equalsIgnoreCase(bean.getType())){
				 sql="select CATEGORY_DETAIL_ID,DETAIL_NAME from  CONSTANT_DETAIL where CATEGORY_ID=22 and BRANCH_CODE=? order by CATEGORY_DETAIL_ID";
				obj=new Object[1];
				obj[0]=branchCode;
			}
			else{
				 sql=getQuery(DBConstants.UPLOAD_GETDOCLIST);
				 if("RDS".equalsIgnoreCase(bean.getModuleType())||"6".equalsIgnoreCase(bean.getModuleType())){
				obj=new Object[6];
				obj[4]=bean.getContarctno();
				obj[5]=StringUtils.isBlank(bean.getLayerNo())?"0":bean.getLayerNo();
				sql+=" "+getQuery(DBConstants.UPLOAD_CONTNO);
			}else
			{
				obj=new Object[5];
				obj[4]=bean.getTranNo();
				sql+=" "+getQuery(DBConstants.UPLOAD_TRANNO);
			}
			obj[0]=branchCode;
			obj[1]=bean.getModuleType();
			obj[2]=bean.getProductId();
			obj[3]="Y";
			}
			LOGGER.info("SQL=>"+sql);
			LOGGER.info("OBj=>"+StringUtils.join(obj,","));
			list=this.mytemplate.queryForList(sql,obj);
		}
		catch(Exception exception) {
			LOGGER.debug("Exception @ { " + exception + " } ");
		}
		return list;
	}
	
	public List<Map<String,Object>> getDocType(String branchCode, String productId,String moduleType) {
		List<Map<String,Object>> docList= null;
		try{
			String query=getQuery(DBConstants.UPLOAD_GETDOCTYPELIST);
			LOGGER.info("Select Query=>"+query);
			LOGGER.info("Args[0]=>"+branchCode);
			LOGGER.info("Args[1]=>"+productId);
			LOGGER.info("Args[2]=>"+moduleType);
			LOGGER.info("Args[3]=>"+"Y");
			if(moduleType==null){
				moduleType="";	
			}
			docList=this.mytemplate.queryForList(query,new Object[]{branchCode,productId,moduleType,"Y"});
		}catch(Exception e){
			LOGGER.debug("Exception @ { " + e + " } ");
		}
		return docList;
	}

	public String doDeleteDocDetails(UploadBean bean,String loginId, String branchCode) {
		String result="";
		try{
			Object[] obj=null;
			String sql=this.getQuery(DBConstants.UPLOAD_UPDATEDOCDETAILS);
			if("RDS".equalsIgnoreCase(bean.getModuleType())||"6".equalsIgnoreCase(bean.getModuleType())){
				obj=new Object[8];
				obj[6]=bean.getContarctno();
				obj[7]=StringUtils.isBlank(bean.getLayerNo())?"0":bean.getLayerNo();
				sql+=" "+this.getQuery(DBConstants.UPLOAD_UPDATECONTLAYERNO);
			}else
			{
				obj=new Object[7];
				obj[6]=bean.getTranNo();
				sql+=" "+this.getQuery(DBConstants.UPLOAD_UPDATETRANNO);
			}
			obj[0]=loginId;
			obj[1]=branchCode;
			obj[2]=bean.getModuleType();
			obj[3]=bean.getProductId();
			obj[4]=bean.getDocumentId();
			obj[5]=bean.getOurFileName();
			LOGGER.info("Sql=>"+sql);
			LOGGER.info("Obj[]=>"+StringUtils.join(obj,","));
			int res=this.mytemplate.update(sql,obj);
			LOGGER.info("Result=>"+res);	
			sql=this.getQuery(DBConstants.UPLOAD_SWAPDOCID);
			if("RDS".equalsIgnoreCase(bean.getModuleType())||"6".equalsIgnoreCase(bean.getModuleType())){
				obj=new Object[7];
				obj[5]=bean.getContarctno();
				obj[6]=StringUtils.isBlank(bean.getLayerNo())?"0":bean.getLayerNo();
				sql+=" "+this.getQuery(DBConstants.UPLOAD_UPDATECONTLAYERNO);
			}else
			{
				obj=new Object[6];
				obj[5]=bean.getTranNo();
				sql+=" "+this.getQuery(DBConstants.UPLOAD_UPDATETRANNO);
			}
			obj[0]=branchCode;
			obj[1]=bean.getModuleType();
			obj[2]=bean.getProductId();
			obj[3]=bean.getDocumentId();
			obj[4]=bean.getOurFileName();
			LOGGER.info("Sql=>"+sql);
			LOGGER.info("Obj[]=>"+StringUtils.join(obj,","));
			res=this.mytemplate.update(sql,obj);
			LOGGER.info("Result=>"+res);	
		}catch(Exception e){
			LOGGER.debug("Exception @ { " + e + " } ");
		}
		return result;
	}
	
	public String doUploadDocDetails(List<Object[]> list, Object[] args) {
		String result="";
		try{
			String sql=getQuery(DBConstants.UPLOAD_INSERTDOCDETAILS);
			LOGGER.info("Sql=>"+sql);
			
			for(Object[] obj:list)
			{
				LOGGER.info("Obj[]=>"+StringUtils.join(obj,","));
				int res=this.mytemplate.update(sql,obj);
				LOGGER.info("Result=>"+res);	
			}
			String query =getQuery("DOC_CON_UPDATE");
			this.mytemplate.update(query,args);
		}catch(Exception e){
			LOGGER.debug("Exception @ { " + e + " } ");
			result = e.getMessage();
		}
		return result;
	}

	public List<Map<String, Object>> allmoduleList(UploadBean bean,String branchCode) {
		List<Map<String,Object>> list = null;
		try{
			Object[] obj=new Object[4];
			String sql=getQuery(DBConstants.UPLOAD_GETMODULEDOCLIST);
			obj[0]=branchCode;
			obj[1]="Y";
			obj[2]=branchCode;
			obj[3]="Y";
			LOGGER.info("SQL=>"+sql);
			LOGGER.info("OBj=>"+StringUtils.join(obj,","));
			list=this.mytemplate.queryForList(sql,obj);
		}
		catch(Exception exception) {
			LOGGER.debug("Exception @ { " + exception + " } ");
		}
		return list;
	}

	public List<Map<String, Object>> moduleTypeList(String branchCode,UploadBean bean) {
		List<Map<String,Object>> docList= null;
		try{
			String query=getQuery(DBConstants.UPLOAD_GETMODULETYPELIST);
			LOGGER.info("Select Query=>"+query);
			Object[] obj=new Object[1];
			obj[0]=branchCode;
			
			docList=this.mytemplate.queryForList(query,obj);
		}catch(Exception e){
			LOGGER.debug("Exception @ { " + e + " } ");
		}
		return docList;
	}
}	
