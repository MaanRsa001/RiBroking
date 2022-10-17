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
					<s:form name="ceding" theme="simple" action="/CedingMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
								<span style="color:green"><s:actionmessage/></span>
						</div>
						
						<s:if test='"ListCedingMaster".equals(path)'>
						
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
												<s:if test='"C".equals(customerType)'>
													<s:select list="#{'CN':'Company Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
												</s:if>
												<s:elseif test='"B".equals(customerType)'>
												<s:select list="#{'BN':'Broker Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
												</s:elseif>
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
							<s:if test='"C".equals(customerType)'>
								<input type="button"  value="Go"   class="btn btn-sm btn-success" onClick="List()" /> &nbsp;&nbsp;&nbsp;
							</s:if>
							<s:else>
							<input type="button"  value="Go"   class="btn btn-sm btn-success" onClick="List1()" /> &nbsp;&nbsp;&nbsp;
							</s:else>
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="Addnew()" />
								
							</div>
						</div>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:if test='"C".equals(customerType)'>
											<s:text name="heading.CedingMasterList" />
										</s:if>
										<s:if test='"B".equals(customerType)'>
											<s:text name="heading.BrokerMasterList" />
										</s:if>
										
									</div>
									<div class="panel-body">
										<s:if test='"C".equals(customerType)  '>
										<display:table name="CedingList" pagesize="10" requestURI="" excludedParams="*" class="table table-bordered" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											
											<display:column sortable="true" style="text-align:left;" title="Company Name" property="COMPANY_NAME" />
											
											<display:column sortable="true"	style="text-align:center;" title="Phone" property="TELEPHONE" />
											<display:column sortable="true" style="text-align:center;" title="Mobile" property="MOBILE" />
											<display:column sortable="true" style="text-align:left;" title="Email" property="EMAIL" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.CUSTOMER_ID}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										
										</display:table>
										</s:if>
										<s:if test='"B".equals(customerType)'>
										<display:table name="CedingList" pagesize="10" requestURI="" excludedParams="*" class="table table-bordered" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement"	value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											
											<display:column sortable="true"	style="text-align:left;" title="Broker Name" property="FIRST_NAME" />
											<display:column sortable="true"	style="text-align:center;" title="Phone" property="TELEPHONE" />
											<display:column sortable="true" style="text-align:center;" title="Mobile" property="MOBILE" />
											<display:column sortable="true" style="text-align:left;" title="Email" property="EMAIL" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.CUSTOMER_ID}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										
										</display:table>
										</s:if>
										
										<s:hidden name="product_Id" id="product_id" />		
										<!-- <s:hidden name="mode"/>	-->		<s:hidden name="customerType"/>
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
										<s:if test='"C".equals(customerType)'>
											<s:text name="heading.CedingMasterList" />
										</s:if>
										<s:if test='"B".equals(customerType)'>
											<s:text name="heading.BrokerMasterList" />
										</s:if>
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											
											<s:if test='"C".equals(customerType)'>											
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.companyName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="companyName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.companyCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="companyCode" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.contactPerson" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="firstName" />
												</div>
											</div>
											</s:if>
											<s:if test='"B".equals(customerType)'>											
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.title" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="title" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.firstName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="firstName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.lastName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="lastName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.designation" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="designation" />
												</div>
											</div>
											</s:if>
											<!--<div class="textfield">
												<div class="text">
													<s:text name="ceding.coreAppCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="coreCompanyCode" />
												</div>
											</div>
											--><div class="textfield">
												<div class="text">
													<s:text name="ceding.inceptionDate" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="inceptionDate" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.emailAddress" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="email" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.phone" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="telephone" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.mobile" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="mobile" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.faxNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="faxNo" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.address1" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="address1" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.address2" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="address2" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.city" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="city" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.zipcode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="zipcode" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.remarks" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="remarks" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.active" />
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
								<s:if test='"C".equals(customerType)'>
								<input type="button"  value="Back"   class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
							</s:if>
							<s:else>
							<input type="button"  value="Back"   class="btn btn-sm btn-danger" onClick="List1()" /> &nbsp;&nbsp;&nbsp;
							</s:else>
								<input type="button" value="AddNew" class="btn btn-sm btn-primary" onClick="Addnew()"/>
														
							</div>						
						</div>
						</s:if>
						
							<s:if test='"InsertCedingMaster".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:if test='"C".equals(customerType)'>
											<s:text name="heading.CedingMasterList" />
										</s:if>
										<s:if test='"B".equals(customerType)'>
											<s:text name="heading.BrokerMasterList" />
										</s:if>
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<s:if test='"update".equals(page)'>
												<s:hidden name="hiddenValue" value="update" />
											</s:if>
											<s:if test='"C".equals(customerType)'>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.companyName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">													
													<s:textfield name="companyName" cssClass="inputBox" maxlength="200"/>													
												</div>
											</div>
											<!--<div class="textfield">
												<div class="text">
													<s:text name="ceding.companyCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="companyCode" cssClass="inputBox" maxlength="30"/>
												</div>
											</div>
											--><div class="textfield">
												<div class="text">
													<s:text name="ceding.contactPerson" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="firstName" cssClass="inputBox" maxlength="200"/>
												</div>
											</div>
											</s:if>
											<s:if test='"B".equals(customerType)'>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.title" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:select list="#{'Mr':'Mr','Mrs':'Mrs','Miss':'Miss','Ms':'Ms'}" name="title" id="" cssClass="inputBoxS" headerKey="0"  headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.firstName" />&nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">													
													<s:textfield name="firstName" cssClass="inputBox" maxlength="200"/>													
												</div>
											</div>
											<!--<div class="textfield">
												<div class="text">
													<s:text name="ceding.lastName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="lastName" cssClass="inputBox" maxlength="25"/>
												</div>
											</div>
											--><div class="textfield">
												<div class="text">
													<s:text name="ceding.designation" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="designation" cssClass="inputBox" maxlength="50"/>
												</div>
											</div>
											</s:if>
											<!--<div class="textfield">
												<div class="text">
													<s:text name="ceding.coreAppCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="coreCompanyCode" cssClass="inputBox" maxlength="25"/>
												</div>
											</div>
											--><div class="textfield">
												<div class="text">
													<s:text name="ceding.inceptionDate" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<sj:datepicker name="inceptionDate" id="inceptionDate" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.address1" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="address1" cssClass="inputBox" maxlength="500"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.address2" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="address2" cssClass="inputBox" maxlength="150"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.city" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="city" cssClass="inputBox" maxlength="200"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.country" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
												<s:select list="brokercountryList" name="country" id="country" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" cssClass="inputBoxS" headerKey=""  headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.zipcode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="zipcode" cssClass="inputBox" maxlength="25"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.emailAddress" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="email" cssClass="inputBox" maxlength="100"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.phone" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="telephone" cssClass="inputBox" maxlength="100"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.mobile" />&nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="mobile" cssClass="inputBox" maxlength="20"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.faxNo" />&nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="faxNo" cssClass="inputBox" maxlength="20"/>
												</div>
											</div>
											<s:if test='"B".equals(customerType)'>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.panNo" />
													</div>
													<div class="tbox">
														<s:textfield name="panNo" cssClass="inputBox" maxlength="10"/>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.isIndividual" />&nbsp;<span style="color: red;">*</span>
													</div>
													<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="isIndividual"></s:radio>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.isNonResident" />&nbsp;<span style="color: red;">*</span>
													</div>
													<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="isNonResident"></s:radio>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.specialRate" />&nbsp;<span style="color: red;">*</span>
													</div>
													<div class="tbox">
														<s:textfield name="specialRate" cssClass="inputBox" maxlength="2" onkeyup="checkNumbers(this);" />
													</div>
												</div>
												</s:if>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.active" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="active" value="active==null?'N':active"/>
												</div>
											</div>
											<div class="textfieldA">
												<div class="text">
													<s:text name="country.Remarks" />
												</div>
												<div class="tbox">
													<s:textarea rows="1" name="remarks" cssClass="inputBoxA" cssStyle="width: 90%;" />
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
								<s:if test='"C".equals(customerType)'>
								<input type="button"  value="Back"   class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
							</s:if>
							<s:else>
							<input type="button"  value="Back"   class="btn btn-sm btn-danger" onClick="List1()" /> &nbsp;&nbsp;&nbsp;
							</s:else>
								<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="submitValues()"  />
								<s:hidden name="categoryName" />
								<s:hidden name="mode"/><s:hidden name="customerId"/>
								<s:hidden name="customerType"/>
							</div>
							<s:if test='"B".equals(customerType)'>
							Note: Key in Special Rate as 0 only if the rate of deduction is zero% for the broker.
							</s:if>
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
	document.forms[0].action='${pageContext.request.contextPath}/insertcedingMasterAdmin.action';
	document.forms[0].submit();
}

function List(){
	document.forms[0].action='${pageContext.request.contextPath}/cedingMasterAdmin.action';
	document.forms[0].submit();
}
function List1(){
	document.forms[0].action='${pageContext.request.contextPath}/brokerMasterAdmin.action';
	document.forms[0].submit();
}
function back() {
	document.ceding.action="<%=request.getContextPath()%>/CedingMasterDispatchAction.do?method=Init";
	document.ceding.submit();		
}

function funEditMode(val) {     
    document.forms[0].mode.value='Edit';
   	document.forms[0].action='${pageContext.request.contextPath}/insertcedingMasterAdmin.action?customerId='+val;
	document.forms[0].submit();	       
}
function Addnew(){
document.forms[0].action='${pageContext.request.contextPath}/editcedingMasterAdmin.action';
document.forms[0].submit();
}

</script>		
</body>
</html>
