package com.maan.service;

 
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.maan.bean.FaculitivesBean;
import com.maan.dao.impl.FacultiviesImpl;
 
public class FacultativesService {
	final FacultiviesImpl dao=new FacultiviesImpl();
	
	public boolean InsertFirstPage(final FaculitivesBean beanObj,final boolean flag,final boolean con)  {
		return  dao.firstPageInsert(beanObj,flag,con);
	}
	
	public boolean ShowSecondPagedata(final FaculitivesBean formObj,final FaculitivesBean beanObj)  {
		return dao.showSecondPagedata(formObj,beanObj);
	}
	
	public boolean InsertSecondPage(final FaculitivesBean beanObj,final boolean flag) {
		 return dao.secondPageInsert(beanObj,flag);
	}

	public boolean ShowFirstPageDatas(final FaculitivesBean beanObj)  {
		 return  dao.showFirstPageItems(beanObj);
	}

	public boolean ShowSecondPageEditItems(final FaculitivesBean formObj)  {
	   return dao.secondPageShowItems(formObj);
	}

	public boolean ShowPageViewDatas(final FaculitivesBean formObj)  {
		 return dao.viewMode(formObj);
	}
	
	public String getRetroContractDetails(FaculitivesBean bean)   {
		 return dao.getRetroContractDetails(bean);
	}
	
	public List<Map<String, Object>> getRetroContractDetailsList(FaculitivesBean bean,int flag) {
		 return dao.getRetroContractDetailsList(bean,flag);
	}
	
	public List<Map<String, Object>> getValidation(FaculitivesBean formObj,int mode) {
		return dao.getValidation(formObj,mode);
	}

	public String getShortname(FaculitivesBean bean) {
		
		return dao.getShortname(bean);
	}

	public List<Map<String, Object>> getLowClaimBonusList(FaculitivesBean bean) {
		return dao.getLowClaimBonusList(bean);
	}

	public String LowClaimBonusInser(FaculitivesBean bean) {
		return dao.LowClaimBonusInser(bean);
	}

	public int getBonusListCount(FaculitivesBean bean) {
		return dao.getBonusListCount(bean);
	}

	public int getCrestaCount(FaculitivesBean bean) {
		return dao.getCrestaCount(bean);
	}

	public String doUpload(FaculitivesBean bean, String branchCode,
			String userId, List<File> upload, List<String> uploadFileName) {
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
				obj[7] = StringUtils.isBlank(bean.getProposalNo())?"":bean.getProposalNo();
				obj[8] = StringUtils.isBlank(bean.getContractNo())?"":bean.getContractNo();
				obj[9] = "0";
				obj[10] = "";
				obj[11] = bean.getProductId();
				obj[12] = branchCode;
				obj[13] = userId;
				list.add(obj);
				
				args[0] = StringUtils.isBlank(bean.getProposalNo())?"":bean.getProposalNo();
				args[1] = StringUtils.isBlank(bean.getProposalNo())?"":bean.getProposalNo();
				args[2] = StringUtils.isBlank(bean.getProposalNo())?"":bean.getProposalNo();
				args[3] = StringUtils.isBlank(bean.getProposalNo())?"":bean.getProposalNo();
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

	public boolean GetShareValidation(FaculitivesBean bean) {
		return dao.GetShareValidation(bean);
	}
}
