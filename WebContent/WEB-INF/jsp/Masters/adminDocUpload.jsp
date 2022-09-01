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

	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<SCRIPT type="text/javascript">
	function doDownload(orgFile,ourFile) {
			document.docUpload.action = "${pageContext.request.contextPath}/downloadDocumentAdmin.action?orgFileName="+orgFile+"&ourFileName="+ourFile;
			document.docUpload.submit();
	}
	function doPreview(orgFile,ourFile) {
	//var ModuleType=	ModuleType=document.getElementById('moduleType').value;
	//var Contarctno=document.getElementById('contarctno').value;
	var customerId=document.getElementById('customerId').value;
	var URL = "${pageContext.request.contextPath}/previewDocumentAdmin.action?orgFileName="+orgFile+"&ourFileName="+ourFile+"&customerId="+customerId;
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
			document.docUpload.documentId.value=docId;
			document.docUpload.ourFileName.value=ourFile;
			document.docUpload.method.value='delete';
			document.docUpload.action = "${pageContext.request.contextPath}/deleteDocumentAdmin.action";
			document.docUpload.submit();
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
					<s:form id="docUpload" name="docUpload"  theme="simple" action="" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						<div class="tablerow">							
							<s:if test="'list'.equals(mode)">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="upload.heading.documentsupload" />
										<span class="pullRight">
											<input type="button"  value="New Document"   class="btn btn-sm btn-success" onClick="newUpload()" />
										</span>
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<%-- <div class="textfield">
												<div class="text txtB">
													<s:text name="doc.name" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="firstName"/>
												</div>--%>
											</div>
											
											<br class="clear"/>										
										</div>
									<div class="panel-body">
									<div class="row">
									<div class="col-xs-12">
								   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th><s:text name="Doc Id" /></th>
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
										<td>${DOC_DESC}</td>
										<td>${ORG_FILE_NAME}</td>	
										<td>
										<center><a href="#" class="" title="download" style="cursor: pointer;" onclick="doDownload('${ORG_FILE_NAME}','${OUR_FILE_NAME}')">  <img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Download" width="12" height="17"> </a></center>
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
											</div>
										</div>
										</div>
										<div class="boxcontent" align="center">
										<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="getBack();" /> 
										</div>
									</div>
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
											<div class="textfield">
												<div class="text txtB">
													<s:text name="doc.name" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="firstName"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
										<div class="boxcontent">
											<table class="footable" id="DocTable">
												<thead>
												<tr>
													<th width="5%">
														<s:text name="upload.docId" />
													</th>
													<%-- <th width="20%">
														<s:text name="upload.docType" />
													</th>--%>
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
												 	<%--<s:if test='!"null".equals(docType)'>
													<td class="formCon"  valign="top">
														<s:select name="docTypeId[%{index}]" list="docType" listKey="DOC_TYPE" listValue="DOC_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
													</td>
													</s:if>
													<s:else>
													<td class="formCon"  valign="top">
														<s:select name="docTypeId[%{index}]" list="#{}"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
													</td>
													</s:else>--%>
													<td class="formCon" style="text-align: center;">
														<s:textarea name="docDesc[%{index}]"  rows="2" cols="70" />												 
													</td>													
													<td class="formCon">
														<s:file name="upload"  cssClass="inputBox" id="upload"/>
													</td>
												</tr>
												</s:iterator>
												</tbody>
											</table>											
										</div>
										<div class="boxcontent" align="right">
											<input type="button"  value="Add More" class="btn btn-sm btn-info" onClick="addUpload();" /> 
										</div>
									</div>
								<div class="boxcontent" align="center">
									<s:if test='"client".equals(flag)'>
										<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="getBack();" /> 
									</s:if>
									<s:else>
										<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="getDocBack();" /> 
									</s:else>
									&nbsp;&nbsp;&nbsp;
									<input type="button"  value="Upload" class="btn btn-sm btn-success" onClick="doUpload();" />
								</div>
								</div>
							</div>
							</s:else>
													
						</div>
					</div>
					<s:hidden name="startIndex" id="startIndex"/>
					<s:hidden name="endIndex" id="endIndex" />
					<s:hidden name="firstName" id="firstName" />
					<s:hidden name="customerId" id="customerId" />
					<s:hidden name="flag" id="flag" />
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

function cancel1()
		{
		document.forms[0].action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium";
      	document.forms[0].submit();
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
	document.docUpload.action = "${pageContext.request.contextPath}/docUploadAdmin.action?mode=newDoc";
	document.docUpload.submit();
}
function addUpload(){
	document.docUpload.action = "${pageContext.request.contextPath}/docUploadAdmin.do?mode=addnew";
	document.docUpload.submit();
}

function doUpload() {
		document.docUpload.action="${pageContext.request.contextPath}/uploadDocumentAdmin.action";
		document.docUpload.enctype="multipart/form-data";
	    document.docUpload.submit();
	}


function getBack(){
	 document.docUpload.action="${pageContext.request.contextPath}/clientMasterAdmin.action";
	 document.docUpload.submit();
	 }
function getDocBack(){
 	document.docUpload.action="${pageContext.request.contextPath}/docUploadAdmin.action";
	document.docUpload.submit();
}

</script>			
</body>
</html>
