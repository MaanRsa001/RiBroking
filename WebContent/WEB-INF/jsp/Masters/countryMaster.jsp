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
					<s:form name="country" theme="simple" action="/CountryMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
								<span style="color:green"><s:actionmessage/></span>
						</div>
						
						<s:if test='"ListCategoryMaster".equals(path)'>
						
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
													<s:select list="#{'CN':'Country Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.enterDataForSearch" />
												</div>
												<div class="tbox">
													<s:textfield name="SearchValue" cssClass="inputBox" />
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
										<s:text name="country.ContryMasterList" />
									</div>
									<div class="panel-body">
										<display:table name="CountryList" pagesize="10" requestURI="" excludedParams="*" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement"	value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											
											<display:column sortable="true"	style="text-align:center;" title="Country Id" property="COUNTRY_ID" />
											<display:column sortable="true"	style="text-align:left;" title="Country Name" property="COUNTRY_NAME" />
											<display:column sortable="true"	style="text-align:center;" title="Effective Date" property="E_DATE" />
											<display:column sortable="true"	style="text-align:center;" title="Status" property="STATUS_NAME" />
											<display:column sortable="true"	style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.COUNTRY_ID}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										
										</display:table>
										<s:hidden name="product_Id" id="product_id" />		
										<!-- <s:hidden name="mode"/>	-->															
									</div>
								</div>
							</div>
						</div>
						
						</s:if>
						
						
						
						<s:if test='"success".equals(path)'>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="hedding.CountryMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="country.CountryName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="countryName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="country.CountrySName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="countryShortName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="country.CoreAppCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="coreCompanyCode" />
												</div>
											</div>	
											<div class="textfield">
												<div class="text">
													<s:text name="country.EffectiveDate" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="effectDate" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="country.Remarks" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="remarks" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Country.Active" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="active" />
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
							</div>						
						</div>
						</s:if>
					<s:if test='"InsertCountryMaster".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="hedding.CountryMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<s:if test='"update".equals(page)'>
											<div class="textfield">
												<div class="text">
													<s:text name="country.cid" />
												</div>
												<div class="tbox">													
													<s:textfield name="countryId" cssClass="inputBox" />
													<s:hidden name="countryId" />
													<s:hidden name="sno" />
													<s:hidden name="hiddenValue" value="update" />
												</div>
											</div>																									
											</s:if>
											<div class="textfield">
												<div class="text">
													<s:text name="country.CountryName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="countryName" cssClass="inputBox" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="country.CountrySName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="countryShortName" cssClass="inputBox" />
												</div>
											</div>
											<!--<div class="textfield">
												<div class="text">
													<s:text name="country.CoreAppCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="coreCompanyCode" cssClass="inputBox" />
												</div>
											</div>
											--><div class="textfield">
												<div class="text">
													<s:text name="country.EffectiveDate" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<sj:datepicker name="effectDate" id="inceptionDate" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="country.Remarks" />
												</div>
												<div class="tbox">
													<s:textarea rows="1" name="remarks" cssClass="inputBoxA" cssStyle="width: 90%;" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Country.Active" /> &nbsp;<span style="color: red;">*</span>
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
								<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="submitValues()"  />
						<!--  		<s:hidden name="categoryName" />-->
						<s:hidden name="sno" />
					<s:hidden name="countryId"/>
						<s:hidden name="mode"/>
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
	document.forms[0].action='${pageContext.request.contextPath}/insertcountryMasterAdmin.action';
     document.forms[0].submit();
}

function List() {
	document.forms[0].action='${pageContext.request.contextPath}/countryMasterAdmin.action';
	document.forms[0].submit();
}

function back() {
	document.country.action="<%=request.getContextPath()%>/CountryMasterDispatchAction.do?method=Init";
	document.country.submit();		
}

function funEditMode(val) {         	
	document.forms[0].mode.value='Edit';
	document.forms[0].action='${pageContext.request.contextPath}/insertcountryMasterAdmin.action?countryId='+val;
	document.forms[0].submit();           
}
function Addnew(){
document.forms[0].action='${pageContext.request.contextPath}/editcountryMasterAdmin.action';
     document.forms[0].submit();
}
</script>		
</body>
</html>
