<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!--<script src="src/js/jquery.filepicker.js"></script>-->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
<script type="text/javascript">
    	$(window).on('beforeunload', function () {
	         $("input[type=submit], input[type=button]").prop("disabled", false);
	     });
    	</script>
	<script type="text/javascript">
		jQuery(function ($) {
			try {
				var data = $('#gridTableMake').dataTable( {
					"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
					"order": [[ 0, "asc" ]],
					"columnDefs": [ {
			          "targets": 'no-sort',
			          "orderable": false
				    } ],
					responsive: true
				});
			} catch(err){}
		} );
	 </script>	
	 
	 
	 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js" type="text/javascript"></script>
<!-- RERO's custom jQuery-UI theme -->
<link rel="stylesheet" href="http://demo.multivio.org/css/rero-theme/jquery-ui-1.8.16.custom.css" type="text/css" />
<!-- Multivio preview's custom CSS and JS -->
<link rel="stylesheet" href="http://demo.multivio.org/css/1.0/multivio-min.css" type="text/css" />
<script src="http://demo.multivio.org/js/1.0/multivio-dev.js" type="text/javascript"></script>
	 <script type="text/javascript">
  // selector for the second example
  $('.multivio-preview-example-2 a').enableMultivio({
    className: 'dark-and-bold',
    method: 'overlay',
    previewWidth: 120,
    previewHeight: 120,
    downloadButton: false,
    quickViewButton: true,
    previewAttr: 'href',
    language: 'fr'
  });
  // selector for the third example
  $('.multivio-preview-example-3 a').enableMultivio({
    className: 'light-and-soft',
    method: 'newwindow',
    previewWidth: 80,
    previewHeight: 80,
    downloadButton: true,
    quickViewButton: true,
    previewAttr: 'href',
    language: 'en'
  });
</script>
<style>
.light-and-soft {
  background-color: #F7F7F0;
  border-color: #AAAAAA;
  border-radius: 8px 8px 8px 8px;
  border-style: solid;
  border-width: 1px;
  box-shadow: 4px 4px 8px #AAAAAA;
}
</style>


	 <script>
//$(document).ready(function() {
///$("input[type='file'].demo").filepicker({
//style: 'bootstrap' // default, bootstrap or jquery-ui
//});
//});
</script>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<SCRIPT type="text/javascript">
	function doDownload(orgFile,ourFile) {
			//$('input[name="upload"]').simpleFilePreview();
			//document.UploadForm.orgFileName.value=orgFile;
			//document.UploadForm.ourFileName.value=ourFile;
			//document.UploadForm.method.value='download';
			document.UploadForm.action = "${pageContext.request.contextPath}/downloadDocumentUpload.action?orgFileName="+orgFile+"&ourFileName="+ourFile;
			document.UploadForm.submit();
	}
	function doPreview(orgFile,ourFile) {
	var ModuleType=	ModuleType=document.getElementById('moduleType').value;
	var Contarctno=document.getElementById('contarctno').value;
	var TranNo=document.getElementById('tranNo').value;
	var URL = "${pageContext.request.contextPath}/previewDocumentUpload.action?orgFileName="+orgFile+"&ourFileName="+ourFile+"&tranNo="+TranNo+"&contarctno="+Contarctno+"&moduleType="+ModuleType;
	var windowName = "QuotatiionPrint";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 900;
	var h =	500;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 10) * .5)  +
		',top='			  + ((height - h - 30) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
	}
	function doPreviewDocModule(orgFile,ourFile,Contarctno,TranNo,ModuleType){
	var URL = "${pageContext.request.contextPath}/previewDocumentUpload.action?orgFileName="+orgFile+"&ourFileName="+ourFile+"&tranNo="+TranNo+"&contarctno="+Contarctno+"&moduleType="+ModuleType;
	var windowName = "QuotatiionPrint";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 900;
	var h =	500;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 10) * .5)  +
		',top='			  + ((height - h - 30) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
	}
	function doDelete(docId,ourFile) {
		if(confirm('Document will be deleted. Do You Want to continue?')){
			document.UploadForm.documentId.value=docId;
			document.UploadForm.ourFileName.value=ourFile;
			document.UploadForm.method.value='delete';
			document.UploadForm.action = "${pageContext.request.contextPath}/deleteDocumentUpload.do";
			document.UploadForm.submit();
		}
		else { 
			return false;
		}
	}
	function doDelete1(docId,moduleType,TransactionNo,ourFile,conNo,productId) {
		//if(confirm('Document will be deleted. Do You Want to continue?')){
			//document.UploadForm.action = "${pageContext.request.contextPath}/deleteDocumentUpload.do?method=modify&documentId="+docId+"&ourFileName="+ourFile+"&tranNo="+TransactionNo+"&moduleType="+moduleType+"&contarctno="+conNo+"&productId="+productId;
			//document.UploadForm.submit();
		//}
		//else { 
	    //		return false;
		//}
	}
	</SCRIPT>
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="UploadForm" name="UploadForm"  theme="simple" action="" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						<div class="tablerow">							
							<s:if test="'list'.equals(display)">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="upload.heading.documentsupload" />
										<s:if test='!"document".equals(type)'>
										<span class="pullRight">
											<input type="button"  value="New Document"  id="new"  class="btn btn-sm btn-success" onClick="newUpload()" />
										</span>
										</s:if>
									</div>
									<div class="panel-body">
									<s:if test='!"document".equals(type)'>
										<div class="boxcontent">
											<s:if test='!"11".equals(productId) && !"6".equals(productId)'>											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.ContractNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="contarctno"/>
												</div>
											</div>
											</s:if>
											<s:if test='"3".equals(productId)'>											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="RiskDetails.LayerNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="layerNo"/>
												</div>
											</div>
											</s:if>
											<s:if test='"5".equals(productId)'>											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="RiskDetails.LayerNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="layerNo"/>
												</div>
											</div>
											</s:if>
											<s:if test='!"3".equals(productId)'>											
												<s:if test='"RDS".equals(productId)'>
												<div class="textfield">
													<div class="text txtB">
														<s:text name="Premium.TransactionNo" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="tranNo"/>
													</div>
												</div>
												</s:if>
											</s:if>
											<s:if test='"6".equals(productId)'>											
												<div class="textfield">
													<div class="text txtB">
														<s:text name="Premium.TransactionNo" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="tranNo"/>
													</div>
												</div>
											</s:if>
											<s:if test='!"RDS".equals(productId)'>											
												<s:if test='"PT".equals(moduleType) ||"RT".equals(moduleType) '>
												<div class="textfield">
													<div class="text txtB">
														<s:text name="Premium.TransactionNo" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="tranNo"/>
													</div>
												</div>
												</s:if>
											</s:if>
											<s:if test='companyName != null && companyName !=""'>																							
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Ceding.CompanyName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="companyName"/>
												</div>
											</div>
											</s:if>
											<s:if test='brokerName != null && brokerName !=""'>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Claim.BrokerName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="brokerName"/>
												</div>
											</div>
											</s:if>
											<br class="clear"/>										
										</div>
										</s:if>
										<s:if test='"document".equals(type)'>
										<div class="panel-body">
											<div class="row">
											<div class="col-xs-12">
											   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
												<thead>
												<tr>
												    <th><s:text name="S.No" /></th>
													<th><s:text name="Proposal No" /></th>
													<th><s:text name="Contract No" /></th>
													<th><s:text name="Transaction No" /></th>
													<th><s:text name="Layer No" /></th>
													<th><s:text name="Company" /></th>
													<th><s:text name="Broker" /></th>
													<th><s:text name="Department" /></th>
													<th><s:text name="Inception Date" /></th>
													<th><s:text name="Expiry Date" /></th>
													<th><s:text name="UW Year" /></th>
													<th><s:text name="Under Writer" /></th>
													<th><s:text name="Document Id" /></th>
													<th><s:text name="Upload Date" /></th>
													<th><s:text name="Document Type" /></th>
													<th><s:text name="View" /></th>
													<th><s:text name="Download" /></th>
												</tr>
												</thead>
												<tbody>
												<s:iterator value="DocList" var="list" status="stat">
														<tr>
															<td><s:property value="#stat.count"/></td>
															<td>${Proposal_no}</td>
															<td>${CONTRACT_NO}</td>
															<td>${TRAN_NO}</td>
															<td>${LAYER_NO}</td>
															<td>${Customer_name}</td>
															<td>${Broker_name}</td>
															<td>${TMAS_DEPARTMENT_NAME}</td>
															<td>${INCEPTION_DATE}</td>
															<td>${Expiry_date}</td>
															<td>${UW_YEAR}</td>
															<td>${UNDERWRITTER}</td>
															<td>${DOC_ID}</td>
															<td>${EFF_DATE}</td>
															<td>${DOC_DESC}</td>
															<td>
																<a href="#" class="" title="View" style="cursor: pointer;" onclick="doPreviewDocModule('${ORG_FILE_NAME}','${OUR_FILE_NAME}','${CONTRACT_NO}','${TRAN_NO}','${MODULE_TYPE}')"><img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Preview" width="25" height="25"></a>
															
															</td>
															
															<td>
																<a href="#" class="" title="download" style="cursor: pointer;" onclick="doDownload('${ORG_FILE_NAME}','${OUR_FILE_NAME}')"> <img border='0' align="middle" src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Download" width="25" height="25"> </a>
															</td>
														</tr>
												</s:iterator>
												</tbody>
												</table>
											</div>			
											</div>
										</div>
										</s:if>
										<s:else>
											<!--<div class="boxcontent">
										<display:table name="DocList" pagesize="10" requestURI=""  class="footable" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"  value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" /> 
												
												<display:column  style="text-align:center;"	title="Doc Id"  property="DOC_ID"/>
												<display:column  style="text-align:left;"	title="Doc Name"  property="DOC_NAME"/>
												<display:column  style="text-align:left;"	title="Document Description"  property="DOC_DESC"  maxLength="40"/>
												<display:column  style="text-align:left;"	title="File Nmae"  property="ORG_FILE_NAME"/>
												<display:column  style="text-align:center;"  title="Download" >
													<a href="#" class="" title="download" style="cursor: pointer;" onclick="doDownload('${record.ORG_FILE_NAME}','${record.OUR_FILE_NAME}')">  <img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Download" width="12" height="17"> </a>
												</display:column>
												<display:column  title="Delete">
													<center><a href="#" class="" title="Delete" style="cursor: pointer;" onclick="doDelete('${record.DOC_ID}','${record.OUR_FILE_NAME}')"><img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Delete" width="12" height="17"></a></center>
												</display:column>  
												<display:column  title="Preview">
													<center><a href="#" class="" title="Delete" style="cursor: pointer;" onclick="doPreview('${record.ORG_FILE_NAME}','${record.OUR_FILE_NAME}')"><img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Preview" width="12" height="17"></a></center>
												</display:column>      
											</display:table>-->
									<div class="panel-body">
									<div class="row">
									<div class="col-xs-12">
								   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th><s:text name="Doc Id" /></th>
										<th><s:text name="Doc Name" /></th>
										<th><s:text name="Document" /></th>
										<th><s:text name="File Name" /></th>
										<th><s:text name="Download" /></th>
										<th><s:text name="Delete" /></th>
										<th><s:text name="Preview" /></th>
										</tr>
										</thead>
										<tbody>
										<s:iterator value="DocList" var="list" status="stat">
										<tr>
										<td>${DOC_ID}</td>
										<td>${DOC_NAME}</td>
										<td>${DOC_DESC}</td>
										<td>${ORG_FILE_NAME}</td>	
										<td>
										<a href="#" class="" title="download" style="cursor: pointer;" onclick="doDownload('${ORG_FILE_NAME}','${OUR_FILE_NAME}')">  <img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Download" width="12" height="17"> </a>
										</td>		
										<td>
										<center><a href="#" class="" title="Delete" style="cursor: pointer;" onclick="doDelete('${DOC_ID}','${OUR_FILE_NAME}')"><img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Delete" width="12" height="17"></a></center>
										</td>
										<td>
										<center><a href="#" class="" title="View" style="cursor: pointer;" onclick="doPreview('${ORG_FILE_NAME}','${OUR_FILE_NAME}')"><img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Preview" width="12" height="17"></a></center>
										</td>							
										</tr>
										</s:iterator>
										</tbody>
										</table>
										
											<s:hidden name="documentId" />
											<s:hidden name="ourFileName" />
											<s:hidden name="orgFileName" />
											<!--<s:hidden name="display" />
										--></div>
										</div>
										</div>
										<div class="boxcontent" align="center">
										<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="getBack('<s:property value="type"/>','<s:property value="productId"/>');" /> 
										</div>
										</s:else>
									</div>
									<!--<div class="boxcontent" align="center">
									<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="getBack();" /> 
									</div>
								--></div>
								
							</div>
							</s:if>
							<s:else>
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="upload.heading.documentsupload" />
										
									</div>
									
									<div class="panel-body">
										<div class="boxcontent">
										<s:if test='"document".equals(type)'>
										<!--  <div class="textfield">
												<div class="text txtB">
													<s:text name="Module Type" />
												</div>
												<div class="tbox">
												<s:select name="moduleType" id="moduleType" list="moduleTypeList" listKey="DETAIL_NAME" listValue="DETAIL_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange='GetDocList(this.value,"DocListDiv","productId")' />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.ContractNo" />
												</div>
												<div class="tbox">
													<s:textfield name="contarctno" id="contarctno" cssClass="inputBoxS"></s:textfield>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="RiskDetails.LayerNo" />
												</div>
												<div class="tbox">
													<s:textfield name="layerNo" id="layerNo" cssClass="inputBoxS"></s:textfield>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.TransactionNo" />
												</div>
												<div class="tbox">
													<s:textfield name="tranNo" id="tranNo" cssClass="inputBoxS"></s:textfield>
												</div>
											</div>
											 <div id="DocListDiv">
											<div class="boxcontent">
											<table class="footable" id="DocTable">
												<thead>
												<tr>
													<th width="5%">
														<s:text name="upload.docId" />
													</th>
													<th width="20%">
														<s:text name="upload.docType" />
													</th>
													<th width="45%">
														<s:text name="upload.docDesc" />
													</th>
													<th width="30%">
														<s:text name="upload.selectdoc" />
													</th>
												</tr>
												</thead>
												<tbody>
												
												<s:iterator value="docuList" id="index">
												<tr>
													<td class="formCon" style="text-align: center;">${index+1}
												 		<s:hidden name="docId[%{index}]" id="docId" value="%{#index+1}"/>
												 	</td>
													<td class="formCon"  valign="top" >
														<s:select name="docTypeId[%{index}]" list="docType" listKey="DOC_TYPE" listValue="DOC_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
													</td>
													<td class="formCon" style="text-align: center;">
														<s:textarea name="docDesc[%{index}]"  rows="2" cols="70" />												 
													</td>													
													<td class="formCon">
														<s:file name="upload"  cssClass="inputBox"/>
													</td>
												</tr>
												
												</s:iterator>
												
												</tbody>
											</table>											
										</div>
										</div>-->
											</s:if>
											<s:else>
											<s:if test='!"11".equals(productId) && !"6".equals(productId)'>											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.ContractNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="contarctno"/>
												</div>
											</div>
											</s:if>
											<s:if test='"3".equals(productId)'>											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="RiskDetails.LayerNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="layerNo"/>
												</div>
											</div>
											</s:if>
											<s:if test='"5".equals(productId)'>											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="RiskDetails.LayerNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="layerNo"/>
												</div>
											</div>
											</s:if>
											<s:if test='"6".equals(productId)'>											
												<div class="textfield">
													<div class="text txtB">
														<s:text name="Premium.TransactionNo" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="tranNo"/>
													</div>
												</div>
											</s:if>
											<s:if test='!"3".equals(productId)'>											
												<s:if test='"RDS".equals(productId)'>
												<div class="textfield">
													<div class="text txtB">
														<s:text name="Premium.TransactionNo" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="tranNo"/>
													</div>
												</div>
												</s:if>
											</s:if>
											<s:if test='!"RDS".equals(productId)'>											
												<s:if test='"PT".equals(moduleType) ||"RT".equals(moduleType) '>
												<div class="textfield">
													<div class="text txtB">
														<s:text name="Premium.TransactionNo" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="tranNo"/>
													</div>
												</div>
												</s:if>
											</s:if>
											<s:if test='"adjustList".equals(type)'>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Adjusment Number" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="tranNo"/>
												</div>
											</div>	
											</s:if>	
											
											<s:if test='companyName != null && companyName !=""'>																							
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Ceding.CompanyName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="companyName"/>
												</div>
											</div>
											</s:if>
											<s:if test='brokerName != null && brokerName !=""'>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Claim.BrokerName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="brokerName"/>
												</div>
											</div>
											</s:if>
											<br class="clear"/>
											</s:else>
										</div>
										<s:if test='!"document".equals(type)'>
										<div class="boxcontent">
											<table class="footable" id="DocTable">
												<thead>
												<tr>
													<th width="5%">
														<s:text name="upload.docId" />
													</th>
													<th width="20%">
														<s:text name="upload.docType" />
													</th>
													<th width="45%">
														<s:text name="upload.docDesc" />
													</th>
													<th width="30%">
														<s:text name="upload.selectdoc" />
													</th>
													<th width="30%">
														<s:text name="Delete" />
													</th>
												</tr>
												</thead>
												<tbody>
												<s:iterator value="docuList" id="index" status="stat">
												<tr>
													<td class="formCon" style="text-align: center;">${index+1}
												 		<s:hidden name="docId[%{index}]" id="docId" value="%{#index+1}"/>
												 	</td>
												 	<s:if test='!"null".equals(docType)'>
													<td class="formCon"  valign="top">
														<s:select name="docTypeId[%{index}]" list="docType" listKey="DOC_TYPE" listValue="DOC_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
													</td>
													</s:if>
													<s:else>
													<td class="formCon"  valign="top">
														<s:select name="docTypeId[%{index}]" list="#{}"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
													</td>
													</s:else>
													<td class="formCon" style="text-align: center;">
														<s:textarea name="docDesc[%{index}]"  rows="2" cols="70" />												 
													</td>													
													<td class="formCon">
														<s:file name="upload"  cssClass="inputBox" id="upload"/>
													</td>
													<td class="formCon">
														<s:if test='0!=(#stat.count-1)'>
																<input type="button" value="Delete" class="btn btn-sm btn-danger"   onclick="deleteUpload('<s:property value="%{#stat.count-1}"/>')" />
														</s:if>
														
													</td>
												</tr>
												</s:iterator>
												</tbody>
											</table>											
										</div>
										</s:if>
										<div class="boxcontent" align="right">
											<input type="button"  value="Add More" class="btn btn-sm btn-info" onClick="addUpload();" /> 
										</div>
									</div>
								<div class="boxcontent" align="center">
								<s:if test='"adjustList".equals(type)'>
									<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="getBack('Adjusment');" /> 
									&nbsp;&nbsp;&nbsp;
								</s:if>
								<s:elseif test='premiumDisplay.equals("premiumDisplay")'>
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel1()" /> &nbsp;&nbsp;&nbsp;
								</s:elseif>
								<s:elseif test='claimDisplay.equals("claimDisplay")'>
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="BackMethodclaim()" /> &nbsp;&nbsp;&nbsp;
								</s:elseif>
								<s:else>
									<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="getBack('<s:property value="type"/>','<s:property value="productId"/>');" /> 
									&nbsp;&nbsp;&nbsp;
								</s:else>
										<input type="button"  value="Upload" class="btn btn-sm btn-success" onClick="doUpload();" />
								</div>
								</div>
							</div>
							</s:else>
													
						</div>
					</div>
					<s:if test='!"document".equals(type)'>	
					<s:hidden name="contarctno" id="contarctno"/>
					<s:hidden name="tranNo" id="tranNo" />
					<s:hidden name="layerNo" id="layerNo"/>		
					<s:hidden name="moduleType" id="moduleType" />
					<s:hidden name="productId" id="productId"/>
					</s:if>
					<s:hidden name="claimDisplay" />
					<s:hidden name="premiumDisplay" />
					<s:hidden name="proposalno" />
					<s:hidden name="display" />
					<s:hidden name="method" />
					<s:hidden name="startIndex" id="startIndex"/>
					<s:hidden name="endIndex" id="endIndex" />
					<s:hidden name="companyName" />
					<s:hidden name="brokerName" />
					<s:hidden name="filePath"/>
					<s:hidden name="startValue"/>
					<s:hidden name="allocType"/>
					<s:hidden name="type"/>
					<s:hidden name="proposal_No"/>
					<s:hidden name="contNo" value="%{contarctno}"/>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function previewFile() {
  ///var preview = document.querySelector('img');
 // var file    = document.querySelector('input[type=file]').files[0];
 //  var reader  = new FileReader();

 // reader.addEventListener("load", function () {
  //  preview.src = reader.result;
  //}, false);

  //if (file) {
 //   reader.readAsDataURL(file);
 // }
}
function cancel1()
		{
		document.UploadForm.action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium";
      	document.UploadForm.submit();
		}

function GetDocList(searchBy,chkval,productId){
		var URL="${pageContext.request.contextPath}/getAjaxValUpload.do?dropDown=DocList&moduleType="+searchBy+"&productId="+productId;
		postRequest(URL,chkval);
}
function doList(){
	document.UploadForm.display.value='list';
	document.UploadForm.method.value='list';
	document.UploadForm.action = "${pageContext.request.contextPath}/documentUpload.do";
	document.UploadForm.submit();
}
function newUpload(){
	document.UploadForm.display.value='upload';
	document.UploadForm.method.value='list';
	document.UploadForm.action = "${pageContext.request.contextPath}/documentUpload.do";
	document.UploadForm.submit();
}
function addUpload(){
	document.UploadForm.display.value='addnew';
	document.UploadForm.method.value='list';
	//document.getElementById("upload").value="";
	//document.UploadForm.upload.value='';
	document.UploadForm.enctype="multipart/form-data";
	document.UploadForm.action = "${pageContext.request.contextPath}/documentUpload.do";
	document.UploadForm.submit();
}

function deleteUpload(id){
if(confirm('This row will be deleted. Do You Want to continue?')){
	document.UploadForm.display.value='delete';
	document.UploadForm.method.value='list';
	document.UploadForm.enctype="multipart/form-data";
	document.UploadForm.action = "${pageContext.request.contextPath}/newDocDeleteUpload.do?deleteId="+id;
	document.UploadForm.submit();
}
else { 
		return false;
	}
}
function getBack(value,id)
     {
     // document.getElementById("display").value='';
     document.UploadForm.display.value='';
     //if(document.getElementById('productId').value!=null){
     //var product=document.getElementById('productId').value;
    // }alert(product);
    var product=id;
     if(value=='contract'){
      document.UploadForm.action="${pageContext.request.contextPath}/InitPortfolio.action";
      }else if(value=='treasury'){
      document.UploadForm.action="${pageContext.request.contextPath}/initTreasury.action";
      }else if(value=='premium'){
      if(product=='1'){
      document.UploadForm.action="${pageContext.request.contextPath}/premiumListFaculPremium.action";
      }
      else if(product=='2'){
      document.UploadForm.action="${pageContext.request.contextPath}/premiumListProportionPremium.action";
      }
      else if(product=='3'){
      document.UploadForm.action="${pageContext.request.contextPath}/premiumListXolPremium.action";
      }
      else if(product=='5'){
      var layerNo = document.getElementById("layerNo").value;
      document.UploadForm.action="${pageContext.request.contextPath}/premiumListXolPremium.action?layerno="+layerNo;
      }
      }else if(value=='claim'){
      if(product=='4'||product=='5'){
      document.UploadForm.action="${pageContext.request.contextPath}/claimInitRetroclm.action";
      }else{
      document.UploadForm.action="${pageContext.request.contextPath}/claimInitClaim.action";
      }
      }
      else if(value=='Adjusment'){
      document.UploadForm.action="${pageContext.request.contextPath}/initAdjustment.action";
      }
      else if(value=='document'){
      document.UploadForm.action="${pageContext.request.contextPath}/documentUpload.action";
      }
      else if(value=='RD'){
      document.UploadForm.action="${pageContext.request.contextPath}/InitPortfolio.action?flag=RD";
      }
      else if(value=='claimPayment'){
       if(product=='4'||product=='5'){
     document.UploadForm.action="${pageContext.request.contextPath}/cliamPageModesRetroclm.do?BusinessMode=Bmode&paymentFlag=list";
      }else{
      document.UploadForm.action="${pageContext.request.contextPath}/cliamPageModesClaim.do?BusinessMode=Bmode&paymentFlag=list";
      }
      }else if(value=='journal'){
      document.UploadForm.action="${pageContext.request.contextPath}/manualJVJournal.action";
      }
      
      document.UploadForm.submit();
     }
	function doUpload() {
	//alert();
		//document.UploadForm.method.value='upload';
		document.UploadForm.action="${pageContext.request.contextPath}/uploadDocumentUpload.action";
		document.UploadForm.enctype="multipart/form-data";
	      document.UploadForm.submit();
	}
//For Dinamic Row add - Start()
<%--List relationList = (ArrayList)request.getAttribute("DocType");--%>
function addNewRow(){
	var table = document.getElementById('DocTable');
	var docId=document.getElementById('startIndex').value;
	var docvalue = parseInt(document.getElementById("docId").value)+1;
         var rowCount = table.rows.length;
          var row = table.insertRow(rowCount);
          row.id = "new_"+rowCount;
          row.className = "formCon";
          var cell = row.insertCell(0);            
		cell.innerHTML = parseInt(parseInt(rowCount)+parseInt(docId));
		//cell.innerHTML = docvalue;
		cell.setAttribute("style", "text-align: center;");
		
		var element = document.createElement("input");
       element.type = "hidden";
       element.name = "docId["+(parseInt(rowCount)+parseInt(docId)-1)+"]";
       element.id = "docId["+(parseInt(rowCount)+parseInt(docId)-1)+"]";
       //element.value = parseInt(parseInt(rowCount)+parseInt(docId));
       
       element.value = docvalue;
       cell.appendChild(element);
       
       cell = row.insertCell(1);
       cell.className = "formCon";
       cell.setAttribute("style", "text-align: left;");
		createDocTypeCell(cell, parseInt(rowCount)+parseInt(docId))
			
       cell = row.insertCell(2);
       cell.setAttribute("style", "text-align: center;");
		var element = document.createElement("textarea");
       element.name = "docDesc["+(parseInt(rowCount)+parseInt(docId)-1)+"]";
       element.id = "docDesc["+(parseInt(rowCount)+parseInt(docId)-1)+"]";  
       element.setAttribute("cols",'70');          
       cell.appendChild(element);
       
       cell = row.insertCell(3);
          cell.className = "formCon";
          element = document.createElement("input");
          element.type = "file";
          //element.name = "upload["+(parseInt(rowCount)+parseInt(docId)-1)+"]";
          element.name = "upload";
          element.className = "inputBox";
          cell.appendChild(element);
          document.getElementById("endIndex").value = (parseInt(rowCount)+parseInt(docId));
               
}
function createDocTypeCell(cell, rowCount){
	element = document.createElement("select");
          element.name = "docType["+(rowCount-1)+"]";
       element.id = "docType["+(rowCount-1)+"]";
       element.className = "inputBox";            
       populateDocType(element);
          cell.appendChild(element);
}
function populateDocType(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '--Select--';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='docType'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='DOC_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='DOC_TYPE' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 
function BackMethodclaim(){
	 document.UploadForm.action="${pageContext.request.contextPath}/claimMasterListClaim.do?flag=claim";
	 document.UploadForm.submit();
	 }


</script>			
</body>
</html>
