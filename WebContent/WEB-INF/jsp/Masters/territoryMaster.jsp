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
					<s:form name="territory" theme="simple" action="/TerritoryMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						
						<s:if test='"ListTerritoryMaster".equals(path)'>
						
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
													<s:select list="#{'TN':'Territory Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
										<s:text name="territory.TerritoryMasterList" />
									</div>
									<div class="panel-body">
										<display:table name="TerritoryList" pagesize="10" requestURI=""	excludedParams="*" class="table table-bordered" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
										
											<display:column sortable="true" style="text-align:center;" title="Territory Id" property="TERRITORY_ID" />
											<display:column sortable="true"	style="text-align:center;" title="Territory Code" property="TERRITORY_CODE" />
											<display:column sortable="true" style="text-align:left;" title="Territory Name" property="TERRITORY_DESC" />
											<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0'	src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.TERRITORY_CODE}')" alt="Edit Contract " width="12" height="17">
											</display:column>										
										</display:table>
										<!-- 
										<s:hidden name="territoryId" id="territoryid" /> -->																	
									</div>
								</div>
							</div>
						</div>
					<!--  	<s:hidden name="mode" />-->
						</s:if>
						<s:if test='"success".equals(path)'>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="heading.TerritoryMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											 <div class="textfield">
												<div class="text">
													<s:text name="territory.TerritoryCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="territoryCode" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="territory.TerritoryName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="territoryDesc" />
												</div>
											</div>
											<!-- <div class="textfield">
												<div class="text">
													<s:text name="territory.CoreCompanyCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="coreCompanyCode" />
												</div>
											</div> -->
											<div class="textfield">
												<div class="text">
													<s:text name="territory.Active" />
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
								<s:hidden value="territoryId" />		
							</div>						
						</div>
						</s:if>
						<s:if test='"InsertTerritoryMaster".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="heading.TerritoryMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											  <div class="textfield">
												<div class="text">
													<s:text name="Territory Code" />&nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
												<s:if test='"insert".equals(mode)'>
													<s:textfield name="territoryCode" cssClass="inputBox" />
													</s:if>	
													<s:else >
													<s:textfield name="territoryCode" cssClass="inputBox" readonly="true" />
													</s:else>	
												</div>
											</div>
												<!--<s:if test='"update".equals(mode)'>
									 <div class="textfield">
												<div class="text">
													<s:text name="country.CurrencyId" />
												</div>
												<div class="tbox">												
													:&nbsp;&nbsp;&nbsp;<s:property value="currencyId" />
													<s:hidden name="hiddenvalue" id="update" />
													<s:hidden name="sno" />
													<s:hidden name="currencyId" />
												</div>
											</div>
							
											</s:if>	-->
											<div class="textfield">
												<div class="text">
													<s:text name="territory.TerritoryName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="territoryDesc" cssClass="inputBox" maxlength="100"/>
												</div>
											</div>																						
											<!--  <div class="textfield">
												<div class="text">
													<s:text name="territory.CoreCompanyCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="coreCompanyCode" cssClass="inputBox" maxlength="100"/>
												</div>
											</div>-->
											<div class="textfield">
												<div class="text">
													<s:text name="territory.Active" /> &nbsp;<span style="color: red;">*</span>
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
							<!--<s:hidden name="territoryCode" />-->
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
		document.forms[0].action='${pageContext.request.contextPath}/insertterritoryMasterAdmin.action';
		document.forms[0].submit();
	}
	
	function List() {
document.forms[0].action='${pageContext.request.contextPath}/territoryMasterAdmin.action'
		document.forms[0].submit();
	}
	function Addnew() {
		document.forms[0].action='${pageContext.request.contextPath}/editterritoryMasterAdmin.action?mode=insert';
		document.forms[0].submit();
	}
	function funEditMode(val) {
	document.forms[0].mode.value='Edit';
	//document.getElementById("departmentId").value=val;

	document.forms[0].action='${pageContext.request.contextPath}/insertterritoryMasterAdmin.action?territoryCode='+val;
	document.forms[0].submit();	       
}
</script>		
</body>
</html>
