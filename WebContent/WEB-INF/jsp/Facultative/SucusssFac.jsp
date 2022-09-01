<%@ page language="java"   pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'Sucuesspage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
	function Proposal()
	{
	document.sucusssFac.action='${pageContext.request.contextPath}/commonListPortfolio.action?backMode=back&manufactureID=<s:property value="#session.mfrid"/>';
	document.sucusssFac.submit();
	}
	function Contract()
	{
	document.location='${pageContext.request.contextPath}/InitPortfolio.action?backMode=back&flag=C&menuId=<s:property value="#session.menuId"/>';
	}
	
	function Rejected(val)
	{ 
	document.location='${pageContext.request.contextPath}/commonListPortfolio.action?backMode=back&manufactureID=<s:property value="#session.mfrid"/>&flag='+val;
	<%--document.location='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<%=session.getAttribute("mfrid")%>&flag='+val;--%>
	<%--	document.location='<%=request.getContextPath()%>/menu.do?method=menu&manufactureID='+<%=session.getAttribute("mfrid")%>+'&flag='+val;--%>
	}
	</script>
  </head>
  
  <body>
 <s:form name="sucusssFac" id="sucusssFac" action="/FaculitivesDispatchAction.do" method="post" theme="simple">
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
				<tr><td width="100%"><br><br></td></tr></table>
					
					<table width="100%" border="0" cellspacing="1" 	cellpadding="1">
						<tr>
						  
						<td align="center">
							<STRONG> 
							<s:if test='!"".equals(contractGendration) && null!=contractGendration'>
							   <font color="blue"> <s:property value="status" /> </font>
							</s:if>
							</strong></td>
							</tr>
								<tr><td align="center"><br><br><br>
								
								<s:if test='"Con".equalsIgnoreCase(backmode)'>
								    <input type="button" class="btn btn-sm btn-danger" value="Back" onclick="Contract()">
								</s:if>
								<s:elseif test='"Pro".equalsIgnoreCase(backmode)'>
								    <input type="button" class="btn btn-sm btn-danger" value="Back" onclick="Proposal()">
								</s:elseif>
								<s:elseif test='"NTU".equalsIgnoreCase(backmode)'>
								    <input type="button" class="btn btn-sm btn-danger" value="Back" onclick="Rejected('N')">
								</s:elseif>
								<s:elseif test='"Reje".equalsIgnoreCase(backmode)'>
								    <input type="button" class="btn btn-sm btn-danger" value="Back" onclick="Rejected('R')">
								</s:elseif>
								<s:hidden name="Department" value="%{#session.DepartmentId}" />
								</td></tr>
								</table></div></div>
								</s:form>
  </body>
</html>
