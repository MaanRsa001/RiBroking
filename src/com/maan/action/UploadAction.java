package com.maan.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.maan.bean.UploadBean;
import com.maan.common.util.LogUtil;
import com.maan.service.UploadService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UploadAction extends ActionSupport implements ModelDriven<UploadBean> {
	private static final long serialVersionUID = 1L;
	Logger logger = LogUtil.getLogger(getClass());
	Map<String,Object> session = ActionContext.getContext().getSession();
	private String loginId=(String) session.get("UserId")==null?"":(String) session.get("UserId");
	private String branchCode=session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	
	UploadService service = new UploadService();
	UploadBean bean=new UploadBean();
	
	public String document() { 
		try {
			if("claimPayment".equalsIgnoreCase(bean.getType())){
				bean.setModuleType("CL");
			}
			if("journal".equalsIgnoreCase(bean.getType())){
				bean.setModuleType("MJ");
			}
			if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(session.get("mfrid").toString());
			}
			else{
				session.put("mfrid",bean.getProductId());
			}
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>() ;
			if("document".equalsIgnoreCase(bean.getType())){
				list=service.allmoduleList(bean,branchCode);	
			}
			else{
				list=service.getDocList(bean,branchCode);
			}
			if(StringUtils.isBlank(bean.getDisplay())||"list".equalsIgnoreCase(bean.getDisplay())){
				if(list.size()>0) {
					if("adjustList".equalsIgnoreCase(bean.getType()) && "".equalsIgnoreCase(bean.getCompanyName())){
						bean.setCompanyName("N/A");
					}
					bean.setDisplay("list");
					bean.setDocList(list);
				}
				else {
					if("adjustList".equalsIgnoreCase(bean.getType()) && "".equalsIgnoreCase(bean.getCompanyName())){
						bean.setCompanyName("N/A");
					}
					bean.setDisplay("upload");		
					bean.setStartIndex("0");
					bean.setEndIndex("1");
					bean.setStartValue(String.valueOf(list.size()+1));
					List<Integer> docList=new ArrayList<Integer>();
					for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++)
					docList.add(i);
					bean.setDocuList(docList);
					bean.setDocType(service.getDocType(branchCode,bean.getProductId(),bean.getModuleType()));
				}
			}
			else if("upload".equalsIgnoreCase(bean.getDisplay())){
				if("document".equalsIgnoreCase(bean.getType())){
					bean.setModuleTypeList(service.moduleTypeList(branchCode,bean));
					bean.setDocType(service.getDocType(branchCode,bean.getProductId(),bean.getModuleType()));
					}
				else{
					bean.setDocType(service.getDocType(branchCode,bean.getProductId(),bean.getModuleType()));
				}
				bean.setDisplay("upload");
				bean.setStartIndex("0");
				bean.setEndIndex("1");
				bean.setStartValue(String.valueOf(list.size()+1));
				List<Integer> docList=new ArrayList<Integer>();
				for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++)
					docList.add(i);
				bean.setDocuList(docList);
				
			}
			else if("addnew".equalsIgnoreCase(bean.getDisplay())){
				bean.setModuleTypeList(service.moduleTypeList(branchCode,bean));
				bean.setEndIndex(String.valueOf(Integer.parseInt(bean.getEndIndex())+1));
				//bean.setStartValue(String.valueOf(list.size()+1));
				List<Integer> docList=new ArrayList<Integer>();
				for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++){
					docList.add(i);
				}
				bean.setDocuList(docList);
				bean.setDocType(service.getDocType(branchCode,bean.getProductId(),bean.getModuleType()));
			}

		} 
		catch (Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return "fileUpload";
	}

	public void downloadDocument() {
		try {
			HttpServletResponse response=ServletActionContext.getResponse();
			OutputStream oout = response.getOutputStream();
			bean.setOurFileName(bean.getOurFileName().trim());
			String val = bean.getOurFileName().substring(bean.getOurFileName().length()-1);
			if(",".equalsIgnoreCase(val)){
				bean.setOurFileName(bean.getOurFileName().replace(bean.getOurFileName().substring(bean.getOurFileName().length()-1), ""));
			}
			response.setHeader("Content-disposition",
					"attachment;filename="+bean.getOrgFileName());
			response.setContentType("application/binary");
			String filePath=ServletActionContext.getServletContext().getRealPath("/")+"documents/";
			if("RDS".equalsIgnoreCase(bean.getModuleType())) {
				
				filePath += bean.getModuleType()+"/"+bean.getContarctno()+"/";
			}
			else if("PR".endsWith(bean.getModuleType())||"CL".endsWith(bean.getModuleType())) {
				filePath += bean.getModuleType()+"/"+bean.getContarctno()+"/"+bean.getTranNo()+"/";
			}
			else {
				filePath += bean.getModuleType()+"/"+bean.getTranNo()+"/";
			}
			FileInputStream fis = new FileInputStream(filePath+bean.getOurFileName());
			byte[] buf = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = fis.read(buf)) != -1) {
				oout.write(buf, 0, bytesRead);
			}
			oout.close();
			fis.close();

		}
		catch(Exception e){
			logger.debug("Exception @ { " + e + " } ");
		}
	}
	public String previewDocument() {
			String forward = "pdfReport";
			try {
				
				if("RDS".equalsIgnoreCase(bean.getModuleType())) {
					bean.setFileName("documents/"+bean.getModuleType()+"/"+bean.getContarctno()+"/"+bean.getOurFileName());
				}
				else if("PR".endsWith(bean.getModuleType())||"CL".endsWith(bean.getModuleType())) {
					bean.setFileName("documents/"+bean.getModuleType()+"/"+bean.getContarctno()+"/"+bean.getTranNo()+"/"+bean.getOurFileName());
				}
				else {
					bean.setFileName("documents/"+bean.getModuleType()+"/"+bean.getTranNo()+"/"+bean.getOurFileName());
				}
				if(StringUtils.isNotBlank(bean.getFileName())) {
					String filePath=ServletActionContext.getServletContext().getRealPath(bean.getFileName());
					File file=new File(filePath);
					if(file.exists()){
						forward = "viewPDF";
					}
				}
			}
			catch(Exception exception) {
				logger.debug(""+exception);
			}
			return forward;
			
	}

	public String deleteDocument() {
		try {
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			String loginId = session.get("UserId").toString();
			if(StringUtils.isNotBlank(bean.getProductId())&&bean.getProductId().equalsIgnoreCase("")){
			bean.setProductId(session.get("mfrid").toString());
			}
			bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
			String result=service.doDeleteDocDetails(bean,loginId,branchCode);
			if(result.length()<=0&&"".equals(result)) {
				if("document".equalsIgnoreCase(bean.getType())){
					list=service.allmoduleList(bean,branchCode);	
				}
				else{
				 list=service.getDocList(bean,branchCode);
				}
				this.addActionMessage(getText("msg.DeleteSucess"));
				if(list.size()>0) {
				bean.setDisplay("list");
				bean.setDocList(list);
				}
			}
			else {
				this.addActionError(result);
			}
		} 
		catch (Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return "fileUpload";
	}

	public String uploadDocument() {
		try {
			validateUploadDocument();
			if(!hasActionErrors()) {
				List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
				int size=Integer.parseInt(bean.getEndIndex());
				bean.setEndIndex(size+"");
				bean.setProductId(session.get("mfrid").toString());
				bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
				String result=service.doUpload(bean,branchCode,loginId,bean.getUpload(),bean.getUploadFileName());
				if(result.length()<=0&&"".equals(result)){
					addActionMessage("Success");
					bean.setDisplay("list");
					if("document".equalsIgnoreCase(bean.getType())){
						list=service.allmoduleList(bean,branchCode);	
					}
					else{
					 list=service.getDocList(bean,branchCode);
					}
					bean.setDocList(list);
				}else{
					List<Integer> docList=new ArrayList<Integer>();
					for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++)
						docList.add(i);
					bean.setDocuList(docList);
					this.addActionError(result);
					bean.setDisplay("upload");
					
				}
			}else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setProductId(session.get("mfrid").toString());
				List<Integer> docList=new ArrayList<Integer>();
				for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++)
				docList.add(i);
				bean.setDocuList(docList);
				bean.setDisplay("upload");
				if("document".equalsIgnoreCase(bean.getType())){
					bean.setModuleTypeList(service.moduleTypeList(branchCode,bean));
					bean.setDocType(service.getDocType(branchCode,bean.getProductId(),bean.getModuleType()));
					}
				else{
					bean.setDocType(service.getDocType(branchCode,bean.getProductId(),bean.getModuleType()));
				}
			}
		} 
		catch (Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return "fileUpload";
	}

	private void validateUploadDocument() {
		int emptyCount=0;
		String emptyRows="";
		boolean empty=false;
		int size=Integer.parseInt(bean.getEndIndex());
		if("document".equalsIgnoreCase(bean.getType())){
		if(StringUtils.isBlank(bean.getModuleType())||"".equalsIgnoreCase(bean.getModuleType())){
			this.addActionError(getText("select.module.type"));
		}
		if((StringUtils.isBlank(bean.getContarctno())||"".equalsIgnoreCase(bean.getContarctno())) && (StringUtils.isBlank(bean.getTranNo())||"".equalsIgnoreCase(bean.getTranNo()))){
			this.addActionError(getText("enter.contract.or.transaction.type"));
		}
		}
		for(int i=(Integer.parseInt(bean.getStartIndex()));i<Integer.parseInt(bean.getEndIndex());i++) {
			if(bean.getUpload()==null){
				this.addActionError(getText("upload.file.required",new String[]{String.valueOf(i+1)}));
			}else{
				
			if( bean.getUpload().size()>i){
			if("".equals(bean.getDocTypeId().get(i)) && (StringUtils.isNotBlank(bean.getDocDesc().get(i)) || bean.getUpload().get(i).length() >0)){
				this.addActionError(getText("upload.docType.required",new String[]{String.valueOf(i+1)}));			
			}
			if(StringUtils.isBlank(bean.getDocDesc().get(i))&&(!"".equals(bean.getDocTypeId().get(i))|| bean.getUpload().get(i).length() >0)){
				this.addActionError(getText("upload.docDec.required",new String[]{String.valueOf(i+1)}));			
			}
			if(bean.getUpload().get(i).length() <= 0&&(StringUtils.isNotBlank(bean.getDocDesc().get(i))||!"0".equals(bean.getDocTypeId().get(i)))){
				this.addActionError(getText("upload.file.required",new String[]{String.valueOf(i+1)}));			
			}
			if(StringUtils.isBlank(bean.getDocDesc().get(i))&&"0".equals(bean.getDocTypeId().get(i))&& bean.getUpload().get(i).length() <= 0)
			{
				emptyCount++;
			}
			if(!empty&&StringUtils.isBlank(bean.getDocDesc().get(i))&&"0".equals(bean.getDocTypeId().get(i))&& bean.getUpload().get(i).length() <= 0)
			{
				if(size==Integer.parseInt(bean.getEndIndex()))
					size=i;
				emptyRows+=(i+1)+",";

			}else if(emptyRows.length()>0)
			{
				empty=true;
			}
		if(empty) {
			this.addActionError(getText("upload.emptyRows.Required",new String[]{emptyRows.substring(0,emptyRows.lastIndexOf(","))}));
		}
		if((Integer.parseInt(bean.getEndIndex())-Integer.parseInt(bean.getStartIndex()))==emptyCount) {
			this.addActionError(getText("upload.oneRow.Required"));
		}
			}
			else{
				this.addActionError(getText("upload.file.required",new String[]{String.valueOf(i+1)}));
			}
		}
	}
	}
	public UploadBean getModel() {
		return bean;
	}
	public String getAjaxVal(){
	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>() ;
	list=service.allmoduleList(bean,branchCode);	
	bean.setProductId(session.get("mfrid").toString());
	bean.setStartIndex("0");
	bean.setEndIndex("1");
	bean.setStartValue(String.valueOf(list.size()+1));
	List<Integer> docList=new ArrayList<Integer>();
	for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++)
		docList.add(i);
	bean.setDocuList(docList);
	bean.setDocType(service.getDocType(branchCode,bean.getProductId(),bean.getModuleType()));
	return "dropdownajax";
	}
	
	public String newDocDelete(){
		bean.setModuleTypeList(service.moduleTypeList(branchCode,bean));
		bean.setEndIndex(String.valueOf(Integer.parseInt(bean.getEndIndex())-1));
		//bean.setStartValue(String.valueOf(list.size()+1));
		List<Integer> docList=new ArrayList<Integer>();
		for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++){
			docList.add(i);
		}
		bean.setDocuList(docList);
		bean.setDocType(service.getDocType(branchCode,bean.getProductId(),bean.getModuleType()));
		List<String> docDesc = new ArrayList<String>();
		List<String> typeId = new ArrayList<String>();
		 int j=1;
		 bean.getDocId().remove(bean.getDeleteId());
		for(int i=0;i< bean.getDocId().size();i++){
			int val = Integer.parseInt(bean.getDeleteId());
			if(i<val){
				docDesc.add(bean.getDocDesc().get(i));
				typeId.add(bean.getDocTypeId().get(i));
			}else{
				docDesc.add(bean.getDocDesc().get(j));
				typeId.add(bean.getDocTypeId().get(j));
			}
			j++;
		}
		//typeId.remove(bean.getDeleteId());
	    bean.setDocDesc(docDesc);
	    bean.setDocTypeId(typeId);
		return "fileUpload";
	}
	
}
