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
</head>

<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="department" theme="simple" action="/DepartmentMasterDispatchActio" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						
						<s:if test='"success".equals(path)'>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="Heading.DepartmentMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
									<!-- <div class="textfield">
											  	<div class="text">
													<s:text name="Department.DepartmentId" />
												</div>
												<div class="tbox"> 
													&gt;:&nbsp;&nbsp;&nbsp;<s:property value="departmentId" />
												</div>
											</div> -->
											<div class="textfield">
												<div class="text">
													<s:text name="Department.DepartmentName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="departmentName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Department.ProductName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="productId" />
													
												</div>
											</div>
											<!--  <div class="textfield">
												<div class="text">
													<s:text name="Profit.CoreCompCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="coreCompanyCode" />
												</div>
											</div>-->
											<div class="textfield">
												<div class="text">
													<s:text name="Department.Active" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="status" />
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button"  value="Back"   class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="AddNew" class="btn btn-sm btn-primary" onClick="Addnew()"/>
								<s:hidden value="departmentId" />		
							</div>						
						</div>
						</s:if>
							<s:if test='"InsertDepartmentMaster".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="Heading.DepartmentMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<s:if test='"update".equals(mode)'>
										 <div class="textfield">
												<div class="text">
													<s:text name="Department.DepartmentId" />
												</div>
												<div class="tbox">												
													:&nbsp;&nbsp;&nbsp;<s:property value="departmentId" />
													<s:hidden name="hiddenvalue" id="update" />
													<s:hidden name="departmentId" />
												</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text">
													<s:text name="Department.DepartmentName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="departmentName" cssClass="inputBox" maxlength="100" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Department.ProductName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
												<s:if test='"insert".equals(mode)'>
													<s:select list="productList" name="productId" onchange="dispalyRights()" cssClass="inputBoxS" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="" headerValue="---Select---" />
													</s:if>
													<s:else>
													<s:select list="productList" name="productId" onchange="dispalyRights()" cssClass="inputBoxS" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="" headerValue="---Select---" disabled="true"/>
													<s:hidden name="productId"/>
													</s:else>
												
										</div>
		
											</div>											
											<!-- <div class="textfield">
												<div class="text">
													<s:text name="Department.CoreCompanyCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="coreCompanyCode" cssClass="inputBox" maxlength="100"/>
												</div>
											</div> -->
											<div class="textfield">
												<div class="text">
													<s:text name="Department.Active" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="status" value="status==null?'N':status"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="submitValues()"  />
								<s:hidden name="mode" />
								<!--<s:hidden name="subProfitCenter_Id" />
								<s:hidden name="productId" />-->
							</div>
						</div>					
						</s:if>
						
						<s:if test='"ListDepartmentMaster".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="variant.searchBy" />
												</div>
												<div class="tbox">
													<s:select list="#{'DN':'Department Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.enterDataForSearch" />
												</div>
												<div class="tbox">
													<s:textfield name="searchValue" cssClass="inputBox" />
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div><s:hidden name="mode" value="search"/>
							<div class="boxcontent" align="center">
								<input type="button"  value="Go"   class="btn btn-sm btn-success" onClick="List()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="Addnew()" />
							</div>
						</div>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Department.DEPARTMENTMASTERLIST" />
									</div>
									<div class="panel-body">
										<display:table name="DepartmentList" pagesize="10" requestURI="" excludedParams="*" class="footable" uid="row" id="record">
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.placement"	value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />
											<display:column sortable="true" style="text-align:center;" title="Product Id" property="TMAS_PRODUCT_ID" />	
											<display:column sortable="true" style="text-align:center;" title="DepartmentId"	property="TMAS_DEPARTMENT_ID" />
											<display:column sortable="true" style="text-align:left;" title="DepartmentName"	property="TMAS_DEPARTMENT_NAME" />
											<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS" />
											<display:column sortable="true"	style="text-align:center;" title=" Edit">
											<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.TMAS_DEPARTMENT_ID}','${record.TMAS_PRODUCT_ID}')"  alt="Edit Contract " width="12" height="17">
												
											</display:column>
										</display:table>
										<!--<s:hidden name="mode" />
										<s:hidden name="productId" id="productId" />
										<s:hidden name="departmentId" id="departmentId" />
										<s:hidden name="status" id="status" />										
									--></div>
								</div>
							</div>
						</div>
						
						</s:if>
																			
					</div>				
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function submitValues() {
		document.forms[0].action='${pageContext.request.contextPath}/insertDepartmentAdmin.action';
		document.forms[0].submit();
	}
    
	function List() {
	document.forms[0].action='${pageContext.request.contextPath}/departmentAdmin.action'
		document.forms[0].submit();
	}
	function Addnew() {
		document.forms[0].action='${pageContext.request.contextPath}/editDepartmentAdmin.action?mode=insert';
		document.forms[0].submit();
	}
	function back() {
		document.forms[0].action="<%=request.getContextPath()%>/DepartmentMasterDispatchAction.do?method=Init";
		document.forms[0].submit();
	}
	
	function funEditMode(val,val1) {
	document.forms[0].mode.value='Edit';
	//document.getElementById("departmentId").value=val;
	//document.getElementById("productId").value=val1;
	document.forms[0].action='${pageContext.request.contextPath}/insertDepartmentAdmin.action?departmentId='+val+'&productId='+val1;
	document.forms[0].submit();	       
}

</script>
</body>
</html>
