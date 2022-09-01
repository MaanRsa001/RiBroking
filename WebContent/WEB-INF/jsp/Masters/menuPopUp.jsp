<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
	<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
	</script >
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<style type="text/css">
	table {
	    max-width: 100%;
	    background-color: transparent;
	    border-collapse: separate;
    	border-spacing: 1;
	}

	</style>	
</head>

  <body>
   <s:form name="user" theme="simple" action="/UserMasterDispatchAction" method="post">
  		<s:if test='"menu".equals(path)' >		
  		<table width="90%" border="0" height="15%" cellpadding="1"
			cellspacing="1" >
			<tr>
				<td width="100%" align="center">
					<H4><span class="text-primary"> <s:text  name="user.menuallocation" /> </span></H4>
				</td>
				</tr>
				<tr>
				<td align="center">
				
				<FONT color="red"><s:property value="%{menuPath}"/></FONT></td>
				
			</tr>
			
		</table>
		<table width="80%" border="1" cellspacing="1" cellpadding="1"
						align="center">
					<s:iterator id="menuinfo" value="menuinfo" status="id" var="menuinfoVar">
						<tr class="tabin">
							<td class="t" style="padding-left: 5px">
								<s:checkbox name="selectedMenus" fieldValue='%{#menuinfoVar.key}' value='%{selectedMenus.contains(#menuinfoVar.key)}'/><s:property value='#menuinfoVar.value'/>
							</td>
						</tr>
					</s:iterator> 
					
			<tr class="tabin">
				<td align="center">
					<input type="button"  class="btn btn-sm btn-danger" value="Cancel" onclick="window.close()"/>
					<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="submitAllocation('menusave')"/>
				</td>
			</tr>
		</table>
	
		<s:if test='"success".equals(path)' >
		<s:if test='"menusave".equals(path)' >	
			
		</s:if>
			<table width="80%" border="1" cellspacing="1" cellpadding="1"
						align="center">
				<tr class="tabin">
					<td class='text' align="center">
						<b><s:text name="user.success" /> </b>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="button"  class="btn btn-sm btn-danger" value="Close" onclick="window.close()"/>
					</td>
				</tr>
			</table>
		</s:if>
		<s:if test='"error".equals(path)' >
		<table width="100%" border="1" height="15%" cellpadding="1"
				cellspacing="1" align='center'>
				<tr>
					<td width="100%" align="center">
						<div class="panel-heading">
										<s:if test='"menusave".equals(mode)'>
										<s:text name="user.menuallocation" />
										</s:if>
										<s:if test='"submenusave".equals(mode)'>
										<s:text name="user.submenuallocation" />
										</s:if>
									</div>
					</td>
				</tr>
			</table>
		<table width="100%" border="1" cellspacing="1" cellpadding="1"
						align='center'>
				<tr class="tabin">
					<td class='text' align="center">
						<b> Transaction Not completed successfully try again</b>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="button"  class="btn btn-sm btn-danger" value="Close" onclick="window.close()"/>
					</td>
				</tr>
			</table>
		<s:hidden value="product" name="UserMasterForm"/>
		<s:hidden value="processId" name="UserMasterForm"/>
		<s:hidden value="loginId" name="UserMasterForm"/>
		</s:if>
		</s:if>
		<s:if test='"submenu".equals(path)' >		
  		<table width="90%" border="0" height="15%" cellpadding="1"
			cellspacing="1" align='center'">
			<tr>
				<td width="100%" align="center">
					<H4><span class="text-primary"> <s:text  name="user.submenuallocation" /> </span></H4>
				</td>
			</tr>
			
		</table>
		<table width="80%" border="1" cellspacing="1" cellpadding="1"
						align="center">
			<s:iterator id="submenuinfo" value="submenuinfo" var="submenuinfoVar">
				<tr class="tabin">
					<td class="t">
						<s:checkbox name="selectedSubMenu" fieldValue='%{#submenuinfoVar.key}' value='%{selectedSubMenu.contains(#submenuinfoVar.key)}'/><s:property value='#submenuinfoVar.value'/>
					</td>
				</tr>
			</s:iterator>

			<tr class="tabin">
				<td align="center">
					<input type="button"  class="btn btn-sm btn-danger" value="Cancel" onclick="window.close()"/>
					<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="submitAllocation('submenusave')"/>
				</td>
			</tr>
		</table>
		
		</s:if>
		<s:if test='"submenumaster".equals(path)' >		
  		<table width="90%" border="0" height="15%" cellpadding="1"
			cellspacing="1" align='center'">
			<tr>
				<td width="100%" align="center">
					<H4><span class="text-primary"> <s:text  name="user.submenuallocation" /> </span></H4>
				</td>
			</tr>
			
		</table>
		<table width="80%" border="1" cellspacing="1" cellpadding="1"
						align="center">
			<s:iterator id="submenuinfo" value="submenuinfo" var="submenuinfoVar" >
				<tr class="tabin">
					<td class="t" style="padding-left: 5px">
						<s:checkbox name="selectedSubMenu" fieldValue='%{#submenuinfoVar.key}' value='%{selectedSubMenu.contains(#submenuinfoVar.key)}'/><s:property value='#submenuinfoVar.value'/>	
					</td>
				</tr>
			</s:iterator>

			<tr class="tabin">
				<td align="center">
					<input type="button"  class="btn btn-sm btn-danger" value="Cancel" onclick="window.close()"/>
					<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="submitAllocation('submenumastersave')"/>
				</td>
			</tr>
		</table>
		
		</s:if>
		<s:if test='"success".equals(path)' >
		<table width="90%" border="0" height="15%" cellpadding="1"
			cellspacing="1" align='center'">
			<tr>
				<td width="100%" align="center">
					<span class="heading"> <s:text  name="user.submenuallocation" /> </span>
				</td>
			</tr>
			
		</table>
		<s:if test='"submenusave".equals(path)' >	
			
		</s:if>
			<table width="80%" border="1" cellspacing="1" cellpadding="1"
						align="center">
				<tr class="tabin">
					<td class='text' align="center">
						<b><s:text name="user.success" /> </b>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="button"  class="btn btn-sm btn-danger" value="Close" onclick="window.close()"/>
					</td>
				</tr>
			</table>
		</s:if>
		<s:if test='"error".equals(path)' >
		<table width="100%" border="1" height="15%" cellpadding="1"
				cellspacing="1" align='center'>
				<tr>
					<td width="100%" align="center">
						<div class="panel-heading">
										<s:if test='"menu".equals(mode)'>
										<s:text name="user.menuallocation" />
										</s:if>
										<s:if test='"submenusave".equals(mode)'>
										<s:text name="user.submenuallocation" />
										</s:if>
									</div>
					</td>
				</tr>
			</table>
		<table width="100%" border="1" cellspacing="1" cellpadding="1"
						align='center'>
				<tr class="tabin">
					<td class='text' align="center">
						<b> Transaction Not completed successfully try again</b>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="button"  class="btn btn-sm btn-danger" value="Close" onclick="window.close()"/>
					</td>
				</tr>
			</table>
		</s:if>
		<s:hidden name="selectedSubMenus"></s:hidden>
		<s:hidden name="key" />
		<s:hidden name="processId" />
		<s:hidden name="loginId" />
		<s:hidden name="menuid" />
		<s:hidden name="selectedMenu" />
		</s:form>
			<script>
		function submitAllocation(value)
		{
			document.forms[0].action="<%=request.getContextPath()%>/SubMenuAllocationAdmin.action?mode="+value;
	      	document.forms[0].submit();
		}
		function submitSubAllocation(value)
		{
			document.forms[0].action="<%=request.getContextPath()%>/SubMenuAllocationAdmin.do?mode="+value;
	     	document.forms[0].submit();
		}
		</script>
  </body>
</html>
