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
					<s:form name="branch" theme="simple" action="/BranchMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green"><s:actionmessage/></span>
						</div>
						
						<s:if test='"ListBranchMaster".equals(path)'>
						
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
													<s:select list="#{'BN':'Branch Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
										<s:text name="branch.BranchMasterList" />
									</div>
									<div class="panel-body">
										<display:table name="BranchList" pagesize="10" requestURI="" excludedParams="*" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											
											<display:column sortable="true" style="text-align:center;" title="Branch Code" property="BRANCH_CODE" />
											<display:column sortable="true" style="text-align:left;" title="Branch Name" property="BRANCH_NAME" />
											<display:column sortable="true" style="text-align:left;" title="Base Currency Name" property="BASE_CURRENCY_NAME" />
											<display:column sortable="true" style="text-align:left;" title="Desc Currency Name" property="DESC_CURRENCY_NAME" />
											<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS_NAME" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.BRANCH_CODE}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										
										</display:table>
									<!--<s:hidden name="mode" id="mode"/>-->
										<s:hidden name="product_Id" id="product_Id" />																		
									</div>
								</div>
							</div>
						</div>
						
						</s:if>
						
						<s:if test='"InsertBranchMaster".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="heading.branchMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<s:if test='"update".equals(page)'>
												<s:hidden name="sno" />
												<s:hidden name="exchangeId" />
												<s:hidden name="hiddenValue" value="update" />
												<s:hidden name="amendId" />																																			
											</s:if>
											<!--  <div class="textfield">
												<div class="text">
													<s:text name="exchange.exchangeCurrencyId" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:select list="#{}" name="currencyId" listKey="0" listValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>-->
											
											<div class="textfield">
												<div class="text">
													<s:text name="Branch Code" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="branchCode1" cssClass="inputBox" readonly="true"/>
												<!--  	:&nbsp;&nbsp;&nbsp;<s:property value="branchCode1"  />-->
													
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="Branch Name" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="branchName" cssClass="inputBox" />
												</div>
											</div>																			
											<!--<div class="textfield">
											<div class="text">
													<s:text name="exchange.CoreAppCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="coreCompanyCode" cssClass="inputBox" />
												</div>
											</div>
											--><div class="textfield">
												<div class="text">
													<s:text name="branch.baseCurrency" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
												
													<s:select list="baseCurrencyList" name="baseCurrencyId" cssClass="inputBoxS" headerKey="0" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerValue="---Select---" />
												
												
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="branch.descCurrency" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
												
													<s:select list="dcurrencyList" name="descCurrencyId" cssClass="inputBoxS" headerKey="0" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerValue="---Select---" />
												
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="branch.country" /> &nbsp;<span style="color: red;">*</span>
											</div>
												<div class="tbox">
													<s:select list="CountryIdList" name="country" cssClass="inputBoxS" headerKey="0" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" headerValue="---Select---" />
												</div>
											</div>											
											<div class="textfield">
												<div class="text">
													<s:text name="exchange.Active" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="active" value="active==null?'N':active"/>
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
						   		<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="submitValues()" /> 
						                                  				
							<!-- 	<s:hidden name="exchangeRate" /> --> 
						<s:hidden name="currencyId"/>	 <s:hidden name="mode"/><s:hidden name="countryId"/>
							</div>
						</div>					
						
						</s:if>
						
						
						<s:if test='"success".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="heading.branchMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent" align="center">
											<s:text name="user.successMessage" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">
							<div class="boxcontent" align="center">
								<input class="btn btn-sm btn-warning" type="button" value="Home" onclick="List()"/>
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
	document.forms[0].action='${pageContext.request.contextPath}/insertbranchMasterAdmin.action'
	document.forms[0].submit();
}

function List() {
	document.forms[0].action='${pageContext.request.contextPath}/branchMasterAdmin.action'
	document.forms[0].submit();
	}

function back() {
	document.forms[0].action='${pageContext.request.contextPath}/getbranchCodeAdmin.action'
	document.forms[0].submit();	
}

function funEditMode(val) {         	
	document.forms[0].mode.value='Edit';
	document.forms[0].action='${pageContext.request.contextPath}/insertbranchMasterAdmin.action?branchCode1='+val;
	document.forms[0].submit();	       
}

function edit(val) {
	document.branch.action="<%=request.getContextPath()%>/BranchMasterDispatchAction.do?method=InsertBranchMaster&BusinessMode=Edit&identity="+val;
	document.branch.submit();
}
function Addnew(){
document.forms[0].action='${pageContext.request.contextPath}/editbranchMasterAdmin.action';
document.forms[0].submit();
}
</script>		
</body>
</html>
