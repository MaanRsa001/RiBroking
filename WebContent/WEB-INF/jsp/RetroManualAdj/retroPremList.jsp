<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
				   // "stateSave": true,
					responsive: true
				});				 
			} catch(err){}
		} );		
	</script>    
    <title>My JSP 'retroPremList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <s:form name="retroPremList" id="retroPremList" action="" theme="simple">
<div class="panel panel-primary">
<div class="panel-heading">
	<div class="tablerow">
	 Retro Manual Adj List
	 <span class="pullRight"><a class="btn btn-sm btn-success" title="Edit" style="cursor: pointer;" onclick="funEditMode('new','','','')">New</a></span>
    </div>
    </div>
    <div class="panel-body">
     <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
     <thead>
    <tr>
    <th style="text-align: center; vertical-align: middle;"><s:text name="S.No" /></th>
	<th style="text-align: center; vertical-align: middle;"><s:text name="Transaction No" /></th>
	<th style="text-align:center;vertical-align:middle"><s:text name="Transaction Date"/></th>
	<th style="text-align:center;vertical-align:middle"><s:text name="UW Year"/></th>
	<th style="text-align:center;vertical-align:middle"><s:text name="Retro Contract Number"/></th>
	<th style="text-align:center;vertical-align:middle"><s:text name="Lead Retrocessionaires"/></th>
	<th style="text-align:center;vertical-align:middle"><s:text name="Lead Broker"/></th>
	<th style="text-align:center;vertical-align:middle"><s:text name="Edit"/></th>
	<th style="text-align:center;vertical-align:middle"><s:text name="View"/></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="retroManualAdjList" var="list" status="stat" >
    <tr>
    <td><s:property value="#stat.count"/></td>
    <td>${TRANSACTION_NO}</td>
    <td>${TRANSACTION_MONTH_YEAR}</td>
    <td>${UWY}</td>
    <td>${RETRO_CONTRACT_NUMBER}</td>
    <td>${RETRO_BROKER}</td>
    <td>${RETROCESSIONAIRE}</td>
    <td>
    <s:if test='#session.MenuRights.indexOf("RME")!=-1'>
    <a class="btn btn-sm btn-primary" title="Edit" style="cursor: pointer;" onclick="funEditMode('edit','${list.TRANSACTION_NO}','${list.RETRO_CONTRACT_NUMBER}','${list.PROPOSAL_NO}')">Edit</a>
    </s:if>
    </td>
    <td>
     <s:if test='#session.MenuRights.indexOf("RMV")!=-1'>
    <a class="btn btn-sm btn-primary" title="View" style="cursor: pointer;" onclick="funEditMode('view','${list.TRANSACTION_NO}','${list.RETRO_CONTRACT_NUMBER}','')">View</a>
    </s:if>
    </td>
    </tr>
    </s:iterator>
    </tbody>
    </table>
    </div>
    </div>
    <s:hidden name="multiuserError" id="multiuserError"/>
     </s:form>
<script type="text/javascript">
function funEditMode(mode,transactionNo,conNo,proposal_No){
	     document.retroPremList.action="${pageContext.request.contextPath}/retroInitRetroAdj.action?mode="+mode+"&transactionNo="+transactionNo+"&contNo="+conNo+"&proposal_No="+proposal_No;
	     document.retroPremList.submit();     
}
editModeStatus();
function editModeStatus(){
	var val =document.getElementById("multiuserError").value;
	if('error'==val){
	document.getElementById("multiuserError").value ='';
	alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
	}
}
</script>
</body>
</html>
