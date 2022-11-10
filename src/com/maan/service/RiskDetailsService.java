package com.maan.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.xwork.StringUtils;

import com.maan.bean.RiskDetailsBean;
import com.maan.dao.RiskDetailsDAO;
import com.maan.dao.impl.RiskDetailsDAOImpl;

public class RiskDetailsService {
	RiskDetailsDAO dao=new RiskDetailsDAOImpl();
	
	public boolean insertProportionalTreaty(final RiskDetailsBean beanObj,final String pid,final boolean flag,final boolean AmednId){
		return dao.firstInsert(beanObj,pid,flag,AmednId);
	}
	public boolean saveRiskDeatilsSecondForm(final RiskDetailsBean beanObj,final String ProductId) {
		return dao.saveRiskDeatilsSecondForm(beanObj,ProductId);
	}
	public boolean riskEditMode(final RiskDetailsBean beanObj,final boolean contarctmode) {
		return dao.riskDetailsEditMode(beanObj,contarctmode);
	}
	public boolean updateProportionalTreaty(final RiskDetailsBean beanObj,final String pid)  {
		return dao.updateProportionalTreatyDao(beanObj,pid);
	}
	public boolean viewRiskDetails(final RiskDetailsBean beanObj,final  String pid)  {      
		return dao.viewRiskDetails(beanObj,pid);
	}
	public String getNextContractNo()  {
       	return dao.getNextContractNumber();
	}
	public boolean checkProductMatch(final String pid,final String conno,final boolean contarctmode)  {      
		return dao.checkProductMatch(pid,conno,contarctmode);
	}	
	public boolean checkAvialability(final RiskDetailsBean beanObj,final  String pid)  {    
		return dao.checkAvialability(beanObj, pid);
	}
	public boolean saveSecondPage(final RiskDetailsBean beanObj,final  String productId)  {   
		return dao.saveSecondMode(beanObj,productId);
	}
	public boolean showSecondPageData(String proposal,RiskDetailsBean formobj,String pid) {       
		return dao.showSecondPageData(proposal,formobj,pid);
	}
	public int getEditMode(String proposalNo)  {      
		return dao.getEditMode(proposalNo);
	}
	public boolean showLayerBrokerage(final String layerProposalNo,final RiskDetailsBean formobj)  {
		return dao.showLayerBrokerage(layerProposalNo,formobj);
	}
	public boolean showSecondPageData1(String proposal,RiskDetailsBean beanObj,String pid)  {	    
		return dao.showSecondPageData1(proposal,beanObj,pid);
	}
	public void showRetroContracts(RiskDetailsBean beanObj,String productCode,boolean view) {
		dao.showRetroContracts(beanObj,productCode,view);
	}
	public String getRetroContractDetails(RiskDetailsBean beanObj){ 
		return dao.getRetroContractDetails(beanObj);
	}
	public List<Map<String, Object>>getRetroContractDetailsList(RiskDetailsBean beanObj,int flag, String UWYear){ 
		return dao.getRetroContractDetailsList(beanObj,flag,UWYear);
	}
	public List<Map<String, Object>> getValidation(RiskDetailsBean formObj){ 
		return dao.getValidation(formObj);
	}
	public boolean getLayerDuplicationCheck(RiskDetailsBean formObj){ 
		return dao.getLayerDuplicationCheck(formObj);
	}
	public String getShortname(RiskDetailsBean bean) {
		return dao.getShortname(bean);
	}
	public void insertCrestaDetails(RiskDetailsBean bean) {
		dao.insertCrestaDetails(bean);
		
	}
	public List<Map<String, Object>> getCrestaDetailList(RiskDetailsBean bean) {
		return dao.getCrestaDetailList(bean);
	}
	public void BaseLayerStatus(RiskDetailsBean bean, String pid) {
		dao.BaseLayerStatus(bean,pid);
		
	}
	public int getCrestaCount(RiskDetailsBean bean) {
		return dao. getCrestaCount(bean);
	}
	public List<Map<String, Object>> getScaleCommissionList(RiskDetailsBean bean) {
		return dao.getScaleCommissionList(bean);
	}
	public String ScaleCommissionInsert(RiskDetailsBean bean) {
		return dao.ScaleCommissionInsert(bean);
	}
	public int getBonusListCount(RiskDetailsBean bean, String type) {
		return dao.getBonusListCount(bean, type);
	}
	public String CheckCrestaValue(RiskDetailsBean bean, String val,String id, String countryId) {
		return dao.CheckCrestaValue(bean,val,id,countryId);
	}
	public String insertProfitCommission(RiskDetailsBean bean) {
		return dao.insertProfitCommission(bean);
	}
	public List<Map<String, Object>> ProfitCommissionList(RiskDetailsBean bean) {
		return dao.ProfitCommissionList(bean);
	}
	public int getProfitListCount(RiskDetailsBean bean) {
		return dao.getProfitListCount(bean);
	}
	public void getprofitCommissionEdit(RiskDetailsBean bean) {
		dao.getprofitCommissionEdit(bean);
	}
	public void getprofitCommissionDelete(RiskDetailsBean bean) {
		dao.getprofitCommissionDelete(bean);
	}
	public String getprofitCommissionEnable(RiskDetailsBean bean, String type) {
		return dao.getprofitCommissionEnable(bean,type);
	}
	public int CommissionTypeCount(RiskDetailsBean bean) {
		return dao.CommissionTypeCount(bean);
	}
	public String doUpload(RiskDetailsBean bean, String branchCode,String userId, List<File> upload, List<String> uploadFileName) {
		String result="";
		try{
			List<Object[]> list = new ArrayList<Object[]>();
			Object args[]=new Object[5];
			String filePath=bean.getFilePath();
			filePath += "RDS"+"/"+bean.getContractNo()+"/";
			//else if("PR".endsWith(bean.getModuleType())||"CL".endsWith(bean.getModuleType()))
			//{
			//	filePath += bean.getModuleType()+"\\"+bean.getContarctno()+"\\"+bean.getTranNo()+"\\";
			//}
			
			File tmpFile = new File(filePath);
			if(!tmpFile.exists()){
				tmpFile.mkdir();
			}
			
			List<String> docId= bean.getDocId();
			List<String> docType= bean.getDocTypeId();
			List<File> doc = upload;
			List<String> docName = uploadFileName;
			List<String> docDesc= bean.getDocDesc();
			for(int i=0;i<bean.getDocId().size();i++)
			{
				final String orgFileName=docName.get(i);
				Calendar cal = Calendar.getInstance();
				String time = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"
				+cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.HOUR)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
				String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
				String fileName = (bean.getDocId().get(i))+"_"+orgFileName.substring(0, orgFileName.lastIndexOf("."))+"_"+time;
				fileName = fileName + ext;
				final File copyFile = new File(filePath+fileName);
				//FileOutputStream outStream = new FileOutputStream(copyFile);
				//outStream.write(doc.get(i).getFileData());
				//outStream.flush();
				//outStream.close();
				
				FileUtils.copyFile(doc.get(i), copyFile);

				final Object[] obj = new Object[14];
				obj[0] = docId.get(i);
				obj[1] = docType.get(i);
				obj[2] = docDesc.get(i);
				obj[3] = orgFileName;
				obj[4] = fileName;
				obj[5] = filePath;
				obj[6] = "RDS";
				obj[7] = StringUtils.isBlank(bean.getProposal_no())?"":bean.getProposal_no();
				obj[8] = StringUtils.isBlank(bean.getContractNo())?"":bean.getContractNo();
				obj[9] = "0";
				obj[10] = "";
				obj[11] = bean.getProduct_id();
				obj[12] = branchCode;
				obj[13] = userId;
				list.add(obj);
				
				args[0] = StringUtils.isBlank(bean.getProposal_no())?"":bean.getProposal_no();
				args[1] = StringUtils.isBlank(bean.getProposal_no())?"":bean.getProposal_no();
				args[2] = StringUtils.isBlank(bean.getProposal_no())?"":bean.getProposal_no();
				args[3] = StringUtils.isBlank(bean.getProposal_no())?"":bean.getProposal_no();
				args[4] = "0";
				
			}
			if(list.size()>0){
				result = dao.doUploadDocDetails(list,args);
			}
	}
		
		catch(Exception exception) {
			result = "Error while saving the Files => " + exception.getMessage();
		}
		return result;
	}
	
	
	public void CancelProposal(RiskDetailsBean bean, String oldProNo) {
		dao.CancelProposal(bean,oldProNo);
	}
	public boolean GetShareValidation(RiskDetailsBean bean) {
		return dao.GetShareValidation(bean);
	}
	public void getRetentionDetails(RiskDetailsBean bean) {
		dao.getRetentionDetails( bean);
	}
	public void getcalculateSC(RiskDetailsBean bean) {
		dao.getcalculateSC(bean);
		
	}
	public void getSectionEditMode(RiskDetailsBean bean) {
		dao.getSectionEditMode(bean);
		
	}
	public boolean getSectionDuplicationCheck(RiskDetailsBean bean) {
		return dao.getSectionDuplicationCheck(bean);
	}
	
	

	
	
}
