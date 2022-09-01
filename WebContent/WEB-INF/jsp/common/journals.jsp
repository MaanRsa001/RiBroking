 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script language="JavaScript">
				function stopRKey(evt) { 
				  var evt = (evt) ? evt : ((event) ? event : null); 
				  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
				} 
				document.onkeypress = stopRKey; 
		</script>
	</head>

	<body>
		<div class="table0" style="width: 90%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
					<div class="tablerow">
						<div style="padding:10px; background:#F8F8F8">
							<s:form id="journal" name="journal" method="post" action="" theme="simple">
							<div class="table2">								
								<div class="tablerow">
									<div class="heading">
									</div>
								</div>
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
								<s:if test='"processDownload".equals(parttoShow)'>
								<div class="boxcontent" align="center">
								The final journals have been generated.  Please download the journal entries and proceed for interface.<br>
 
								Note: Current active Open Period has been closed and a new period has started.<br><br>
								<a  id="final" class="btn btn-sm btn-danger" onclick=" back(this.form);"  >Back</a>
								<a  id="final" class="btn btn-sm btn-info" onclick=" Download(this.form ,'process');"  >Download</a>
								</div>
								<s:hidden name="openperiod"/>
								<s:hidden name="journalname"/>
								</s:if><s:else>
					              <div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<table width="75%" style="margin: 0 auto;">
										<tr>
												<td height="5" align="center"><span style="color:blue"><s:text name="label.openperiod" />  </span><br/></td>
											</tr>
											<tr>
												<td>
													<s:select list="openperiodList" name="openperiod" cssClass="inputBoxS" headerKey="" listKey="openperiodkey" listValue="openperiodvalue" headerValue="---Select---"  onchange="getView(this.value);getRetroView(this.value);setOpenPeriodDate(this.value);"/>
												</td>
											</tr>
											<tr>
												<td height="5"></td>
											</tr>
										</table>
									</div>
									</div>
									</div>
									</div>
									<div class="boxcontent" id="retroID">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										Retro Processing
									</div>
									<div class="panel-body">
										<div class="boxcontent" align="center">
										<a  id="retroprocessID" class="btn btn-sm btn-success" onclick="getRetroSumbit()"  >Process</a>&nbsp;&nbsp;&nbsp;
										</div>
										</div>
										</div>
										</div>
										<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										Journal Processing
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<table width="100%" class="footable">
													
											<tbody>												
												<tr>
												<td width="15%">
												</td>
												<td width="25%">
												</td>
												<td width="15%">
												</td>
												<td width="15%">
												</td>
												<td width="15%">
												<s:text name="Start Date"/>
												<s:select list="startDateList" name="startDate" cssClass="inputBoxS" headerKey="" listKey="FROM_DATE" listValue="FROM_DATE" headerValue="---Select---"  />
												</td>
												<td width="15%">
												<s:text name="End Date"/>
												<s:select list="endDateList" name="endDate" cssClass="inputBoxS" headerKey="" listKey="TO_DATE" listValue="TO_DATE" headerValue="---Select---" />
												</td>
												</tr>
												</tbody>
										</table>
										<table width="75%" style="margin: 0 auto;" class="footable">
											<thead>
												<tr>
													<th width="15%"><s:text name="Journal ID"/></th>
													<th width="25%"><s:text name="Journal Name"/></th>
													<th width="15%"><s:text name="Status"/></th>
													<th width="15%"><s:text name="View"/></th>
													<th width="15%"><s:text name="Download"/></th>
													<th width="15%"><s:text name="Download"/></th>
												</tr>
											</thead>
											<s:iterator value="jounalList" var="list" status="stat">
											<tr>
												<td width="15%" align="center"><s:property value="#list.CATEGORY_DETAIL_ID"/></td>
												<td width="25%"><s:text name="#list.DETAIL_NAME"/></td>
												<td width="15%" align="center"><s:property value="#list.STATUS"/></td>
												<td align="center" width="15%">
												<s:if test="'Active'.equals(#list.STATUS)">
												<a  class="btn btn-sm btn-primary" onclick="getJournalView(${CATEGORY_DETAIL_ID});"  >View</a>
												</s:if>
												</td>
												<td align="center" width="15%">
												<s:if test="'Active'.equals(#list.STATUS)">
												<a  class="btn btn-sm btn-success" onclick="getReportsJVDownload('${PERCENT}','${PARAM1}');" >Download</a>
												</s:if>
												<s:else>
												<input type="button" class="btn btn-sm btn-success" style="background:transparent; border:none; color:transparent;">
												</s:else>
												</td>
												<td align="center" width="15%">
													<s:if test="'Active'.equals(#list.STATUS)">
														<s:if test='%{"3"==(#list.CATEGORY_DETAIL_ID)}'>
														<a  class="btn btn-sm btn-success" onclick="getReportsJVDownload('30','TREASURY_JV2');" >Download</a>
														</s:if>
													</s:if>
												</td>
											</tr>
											</s:iterator>
										</table>
										<div align="center">
										<br>
										<a  id="final" class="btn btn-sm btn-warning" onclick="Download(this.form ,'process');"  >Download Processed JVs</a>&nbsp;&nbsp;&nbsp;
										<a  id="interimdown" class="btn btn-sm btn-warning" onclick="getReportsDownload(this.form );"  >Download Interim JVs</a>&nbsp;&nbsp;&nbsp;
										<a  id="interim" class="btn btn-sm btn-info" onclick="checkAll(this.form ,'inprocess');"  >Generate Interim JVs</a>&nbsp;&nbsp;&nbsp;
										<a  id="processId" class="btn btn-sm btn-success" onclick=" return CheckQuaterend(this.form ,'process');"   >Final Process</a>&nbsp;&nbsp;&nbsp;
										
										</div>
										</div>
										</div>
										</div>
										</div>
								</s:else>
							</div>
							<s:hidden name="downloadType"/>
							<s:hidden name="processStatus"/>
							<s:hidden name="typeId"/>
							<s:hidden name="reportType"/>
							<s:hidden name="journalType"/>
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
<script type="text/javascript">
	$(function(){
		$("#selectall").click(function () {
			  $('.case').attr('checked', this.checked);
		});
		$(".case").click(function(){
			if($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}
		});
	});
	function funsubmit() {
		document.getElementById("uploadProgress").style.display = "block";
	}
	function getRetroView(val){
	if(val.indexOf("Y")!= -1) {
		document.getElementById('retroprocessID').style.display = "inline";
		var period=val.split("-");
		var openenddate=period[1];
			var openenddate1=openenddate.split("/");
	  		var openenddate2=openenddate1[0]+"/"+openenddate1[1];
	   		if(openenddate2=="31/03" ||openenddate2=="30/06" ||openenddate2=="30/09" ||openenddate2=="31/12"){
	   			document.getElementById('retroID').style.display = "inline";
	   		}else{
	   			document.getElementById('retroID').style.display = "none";
	  			}
		}else{
			document.getElementById('retroID').style.display = "none";
			document.getElementById('retroprocessID').style.display = "none";
			var period=val.split("-");
		var openenddate=period[1];
			var openenddate1=openenddate.split("/");
	  		var openenddate2=openenddate1[0]+"/"+openenddate1[1];
	   		if(openenddate2=="31/03" ||openenddate2=="30/06" ||openenddate2=="30/09" ||openenddate2=="31/12"){
	   			document.getElementById('retroID').style.display = "inline";
	   		}else{
	   			document.getElementById('retroID').style.display = "none";
	  			}
			
		}
	
	}
	function getView(val){
	//alert(val);
		if(val.indexOf("Y")!= -1) {
			document.getElementById('processId').style.display = "inline";
			document.getElementById('interim').style.display = "inline";
			document.getElementById('interimdown').style.display = "inline";
			document.getElementById('final').style.display = "none";
	
		}else{
			document.getElementById('processId').style.display = "none";
			document.getElementById('interim').style.display = "none";
			document.getElementById('interimdown').style.display = "none";
			document.getElementById('final').style.display = "inline";
		}
	}
	function setOpenPeriodDate(val){
	var period=val.split("-");
		document.journal.startDate.value=period[0];
		document.journal.endDate.value=period[1];
	}
	var status='<s:property value="openperiod"/>';
	if(status!=null || status!=''){
	//alert();
	getView(status);
	getRetroView(status);
	}
 function checkAll(form,val) {
 	
      	//form.downloadType.value='xls';
      	document.journal.processStatus.value=val;
      	document.journal.action="${pageContext.request.contextPath}/journalHomeJournal.action";
		document.journal.submit();
      	//form.processStatus.value=val;
  			//form.action="${pageContext.request.contextPath}/journalHomeJournal.action";
		//form.submit();	
     
    }
    function CheckQuaterend(form,val){
	var openenddate=document.journal.openperiod.value.split("-");
		var openenddate1=openenddate[1].split("/");
  		var openenddate2=openenddate1[0]+"/"+openenddate1[1];
  		//alert(openenddate2);
   if(openenddate2=="31/03" ||openenddate2=="30/06" ||openenddate2=="30/09" ||openenddate2=="31/12"){
   	answer=confirm("Have you completed Retro Process?.If Yes click continue otherwise click cancel.Do Retro Process.");
   		if(answer){
      	checkAll(form,val);
      }else{
      return false;
      }
    }else{
    checkAll(form,val);
    }
   }
function menuSelection(){
  
	var URL='${pageContext.request.contextPath}/getMenuSelectionAdmin.do';  

	var windowName = "Selection";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 720;
	var h = 550;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 0) * .4)  +
		',top='			  + ((height - h - 0) * .4) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=yes'	  +
		',toolbar=no'	  +
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
}
	function getJournalView(val){
	//alert(val);
	try{
		document.journal.action="${pageContext.request.contextPath}/getjournalViewJournal?journalID="+val;
		document.journal.submit();
		}catch(err){alert(err)}
	}
	function getReportsDownload(form)
	{
		document.journal.downloadType.value='xls';
		document.journal.action="${pageContext.request.contextPath}/journalReportJournal.action";
		document.journal.submit();
		//form.downloadType.value='xls';
	    //form.action="${pageContext.request.contextPath}/journalReportJournal.action";
		//form.submit();		
	}
	function Download(form,val)
	{
		document.journal.downloadType.value='xls';
		document.journal.processStatus.value=val;
		document.journal.action="${pageContext.request.contextPath}/journalReportJournal.action";
		document.journal.submit();
		//form.downloadType.value='xls';
		//form.processStatus.value=val;
	    //form.action="${pageContext.request.contextPath}/journalReportJournal.action";
		//form.submit();		
	}
	function back(form){
		document.journal.processStatus.value="";
		document.journal.openperiod.value="";
		document.journal.action="${pageContext.request.contextPath}/journalHomeJournal.action";
		document.journal.submit();
	//form.processStatus.value="";
	//form.openperiod.value="";
	//form.action="${pageContext.request.contextPath}/journalHomeJournal.action";
	//form.submit();
	}
	function getRetroSumbit(){
	try{
		document.journal.action="${pageContext.request.contextPath}/retroInsertJournal";
		document.journal.submit();
		}catch(err){alert(err)}
	}
	function getReportsJVDownload(val,val2)
	{
		document.journal.typeId.value=val;
		document.journal.journalType.value=val2;
		document.journal.reportType.value='xml';
		document.journal.action="${pageContext.request.contextPath}/getjournalDownloadJournal";
		//document.journal.action="${pageContext.request.contextPath}/getRlistReports";
		document.journal.submit();
				
	}
	
		</script>
	</body>
</html>
