<%@ page language="java"   pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
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
    <title>My JSP 'Sucuesspage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
  </head>
  
  <body>
 <s:form name="authSucusss" id="authSucusss" action="" method="post" theme="simple">
     <table width="90%" border="0" height="15%" cellpadding="1"cellspacing="1" align='center'">
				<tr>
					<td width="100%" align="center">
					</td>
				</tr>
	</table>
			<center></center>
			<div align="center" class="border1">
				<div align="center" class="border2">
				<br/>
				<table width="100%" border="0" height="15%" cellpadding="1" 	cellspacing="1" align="center">
				<tr><td width="100%">
							<span style="color: red;"><s:actionerror /> </span>
							<span style="color: green;"><s:actionmessage /> </span>
				</td></tr></table>
					
					<table width="100%" border="0" cellspacing="1" 	cellpadding="1">
						<tr>
							<s:if test="'multiple'==uploadStatus">
							<td >
							
							<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="tablerow">
									   <s:text name="Heading.Authedication"/>
								</div>
							</div>
							<div class="panel-body">
								<div class="row">
								<div class="col-xs-12">
								   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th   class="no-sort" width="3%" style="text-align: center; vertical-align: middle;">S.No</th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Request No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Contract No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Instant Date / Statement Period" /></th>
										<%--<th style="text-align: center; vertical-align: middle;"><s:text name="Underwriting" /></th>--%>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Used Id" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Transaction No" /></th>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="authenticationList" var="list" status="stat">
											<tr>
												<td style="text-align: center; vertical-align: middle;" > <s:property value="#stat.count"/> </td>
												<td><s:property value="requestNo"/></td>
												<td><s:property value="contNo"/></td>
												<td><s:property value="account_Period"/></td>
												<td><s:property value="loginId"/></td>
												<td><s:property value="transactionNo"/></td>
											</tr>
										</s:iterator>
									</tbody>
									</table>
											</div>			
								</div>
							</div>
							
							</div>
							</s:if>
							<s:else>
							<td align="center">
							<strong>
							 <font color="blue"> <s:property value="successMessage" /> </font>
							 </s:else>
							</strong></td>
							</tr>
								<tr><td align="center"><br><br><br>
								    <input type="button" class="btn btn-sm btn-danger" value="Back" onclick="AuthendicationBack()">
								    <s:if test='"multiple".equals(uploadStatus)'>
									&nbsp;&nbsp;&nbsp;<input type="button" value="Print" id="print" class="btn btn-sm btn-primary" onClick="printpage()" />
									</s:if>
								</td></tr>
								</table></div></div>
								</s:form>
								
<script type="text/javascript">
	function AuthendicationBack(){
	document.authSucusss.action="${pageContext.request.contextPath}/initAuthentication.do";
	document.authSucusss.submit();
	}

</script>
  </body>
</html>
