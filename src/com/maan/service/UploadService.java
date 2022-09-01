package com.maan.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.action.UploadAction;
import com.maan.bean.FaculitivesBean;
import com.maan.bean.UploadBean;
import com.maan.common.util.LogUtil;
import com.maan.dao.UploadDAO;
import com.maan.dao.impl.UploadDAOImpl;

public class UploadService {
	UploadDAO dao = new UploadDAOImpl();
	Logger logger = LogUtil.getLogger(getClass());
	
	public List<Map<String,Object>> getDocList(UploadBean bean,String branchCode) {
		return dao.getDocList(bean,branchCode);
	}

	public List<Map<String,Object>> getDocType(String branchCode, String productId,String moduleType) {
		return dao.getDocType(branchCode, productId,moduleType);
	}
	public String doDeleteDocDetails(UploadBean bean,String loginId, String branchCode) {
		return dao.doDeleteDocDetails(bean,loginId,branchCode);
	}
	public String doUpload(UploadBean bean,String branchCode,String loginId, List<File> upload, List<String> uploadFileName) {
		String result="";
		try{
			List<Object[]> list = new ArrayList<Object[]>();
			Object args[]=new Object[5];
			String filePath=bean.getFilePath();
			if("RDS".equalsIgnoreCase(bean.getModuleType())||"PR".equalsIgnoreCase(bean.getModuleType())||"CL".equalsIgnoreCase(bean.getModuleType()))
			{
				filePath += bean.getModuleType()+"/"+bean.getContarctno()+"/";
			}
			//else if("PR".endsWith(bean.getModuleType())||"CL".endsWith(bean.getModuleType()))
			//{
			//	filePath += bean.getModuleType()+"\\"+bean.getContarctno()+"\\"+bean.getTranNo()+"\\";
			//}
			else {
				filePath += bean.getModuleType()+"/"+bean.getTranNo()+"/";
			}
			File tmpFile = new File(filePath);
			if(!tmpFile.exists()){
				tmpFile.mkdir();
			}
			if("PR".equalsIgnoreCase(bean.getModuleType())||"CL".equalsIgnoreCase(bean.getModuleType())){
				filePath +=bean.getTranNo()+"/";
				tmpFile = new File(filePath);
				if(!tmpFile.exists())
					tmpFile.mkdir();
			}
			List<String> docId= bean.getDocId();
			List<String> docType= bean.getDocTypeId();
			List<File> doc = upload;
			List<String> docName = uploadFileName;
			List<String> docDesc= bean.getDocDesc();
			for(int i=(Integer.parseInt(bean.getStartIndex()));i<Integer.parseInt(bean.getEndIndex());i++)
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
				obj[6] = bean.getModuleType();
				obj[7] = StringUtils.isBlank(bean.getProposalno())?"":bean.getProposalno();
				obj[8] = StringUtils.isBlank(bean.getContarctno())?"":bean.getContarctno();
				obj[9] = StringUtils.isBlank(bean.getLayerNo())?"0":bean.getLayerNo();
				obj[10] = StringUtils.isBlank(bean.getTranNo())?"":bean.getTranNo();
				obj[11] = bean.getProductId();
				obj[12] = branchCode;
				obj[13] = loginId;
				list.add(obj);
				
				args[0] = StringUtils.isBlank(bean.getProposalno())?"":bean.getProposalno();
				args[1] = StringUtils.isBlank(bean.getProposalno())?"":bean.getProposalno();
				args[2] = StringUtils.isBlank(bean.getProposalno())?"":bean.getProposalno();
				args[3] = StringUtils.isBlank(bean.getProposalno())?"":bean.getProposalno();
				args[4] = StringUtils.isBlank(bean.getProposalno())?"":bean.getProposalno();
				
			}
			if(list.size()>0){
				result = dao.doUploadDocDetails(list,args);
			}
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception +" } ");
			result = "Error while saving the Files => " + exception.getMessage();
		}
		return result;
	}

	public List<Map<String, Object>> allmoduleList(UploadBean bean,String branchCode) {
		return dao.allmoduleList(bean,branchCode);
	}

	public List<Map<String, Object>> moduleTypeList(String branchCode,UploadBean bean) {
		return dao.moduleTypeList(branchCode,bean);
	}

	
}
