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
					<s:form name="exchange" theme="simple" action="/ExchangeMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						
						<s:if test='"ListExchangeMaster".equals(path)'>
						
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
													<s:select list="#{'SN':'Sr No'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
							</div>
							<s:hidden name="mode" value="search"/>
							<div class="boxcontent" align="center">
								<input type="button"  value="Go"   class="btn btn-sm btn-success" onClick="List();" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="AddNew();" />
							</div>
						</div>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="exchange.ExchangeMasterList" />
									</div>
									<div class="panel-body">
									<!--  	<display:table name="exchangeList" pagesize="10" requestURI="" excludedParams="*" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											
											<display:column sortable="true" style="text-align:center;" title="Exchange Id" property="EXCHANGE_ID" />
											<display:column sortable="true"	style="text-align:left;" title="Currency Name" property="CURRENCY_NAME" />
											<display:column sortable="true"	style="text-align:left;" title="Exchange Rate"	property="EXCHANGE_RATE" />
											<display:column sortable="true"	style="text-align:center;" title="Rate Effective From" property="IN_DATE" />
											<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS_NAME" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.EXCHANGE_ID}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										
										</display:table>-->
										
										<display:table name="exchangeList" pagesize="10" requestURI="" excludedParams="*" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<s:set name="myrow" value="#attr.record"/>
											<display:column sortable="true" style="text-align:center;" title="Sr No" property="AMEND_ID" />
											<display:column sortable="true"	style="text-align:center;" title="Rate Effective From" property="INCEPTION_DATE" />
											<display:column sortable="true"	style="text-align:center;" title="Rate Effective To"	property="EXPIRY_DATE" />
											<display:column sortable="true"	style="text-align:center;" title="Updated on" property="ENTRY_DATE" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
											<s:if test="#myrow.AMEND_ID==#myrow.MAX_AMEND_ID">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.AMEND_ID}')" alt="Edit Contract " width="12" height="17">
											</s:if>
											</display:column>
											<display:column sortable="true" style="text-align:center;" title=" view">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funViewMode('${record.AMEND_ID}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										
										</display:table>
										<s:hidden name="product_Id" id="product_id" />																		
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
										<s:text name="heading.exchangeMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">								
											<div class="textfield">
												<div class="text">
													<s:text name="exchange.exchangeId" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="exchangeId" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="exchange.exchangeRate" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="exchangeRate" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="exchange.CoreAppCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="coreAppCode" />
												</div>
											</div>	
											<div class="textfield">
												<div class="text">
													<s:text name="exchange.InceptionDate" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="inceptionDate" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="exchange.Remarks" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="remarks" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="exchange.Active" />
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
								<input type="button" value="AddNew" class="btn btn-sm btn-primary" onClick="AddNew()"/>									
							</div>						
						</div>
						</s:if>
						<s:if test='"InsertExchangeMaster".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="heading.exchangeMaster" />
									</div>
									<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body">
									<s:if test='"update".equals(mode) || "view".equals(mode)'>
									<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="Rate Efffective From" />
												</div>
												<div class="tbox">
												<s:hidden name="inceptionDate"/><s:property value="inceptionDate"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Rate Updated on" />
												</div>
												<div class="tbox">
												<s:property value="entrydate"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Update ID" />
												</div>
												<div class="tbox">
												<s:property value="amendid"/>
												</div>
										</div>
											<br class="clear"/>
										</div>
										<s:hidden name="amendid"></s:hidden>
									</s:if>
									<s:else>
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="Rate Efffective From" />
												</div>
												<div class="tbox">
												<sj:datepicker name="inceptionDate" id="inceptionDate" cssClass="pullLeft" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; "/>
												</div>
											</div>
											<br class="clear"/>
										</div>
										</s:else>
									</div>
								</div>
							</div>
									<div class="panel-body">
										<div class="boxcontent">
										
										
										<display:table name="exchangeCurrencyList" pagesize="" requestURI="" class="footable" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column style="text-align:center;" title="Exchange ID" >
													<s:hidden name="exchageRateList[%{#attr.record_rowNum-1}]" id="exchageRateList[%{#attr.record_rowNum-1}]" value="%{#myrow.exchangeRateId}" /><s:property value="#myrow.exchangeRateId"/>		
												</display:column>
												<display:column style="text-align:center;" title="Currency Name"  >
													<s:hidden name="currencyNameList[%{#attr.record_rowNum-1}]" id="currencyNameList[%{#attr.record_rowNum-1}]" value="%{#myrow.currencyNameId}" /><s:property value="#myrow.currencyNameId"/>
												</display:column>
												<display:column style="text-align:center;" title="Exchange Rate" >
												<s:if test='"view".equals(mode)'>
												<s:textfield name="exchageRate[%{#attr.record_rowNum-1}]" id="exchageRate[%{#attr.record_rowNum-1}]" value="%{#myrow.exchangeRate}"   onkeyup="allow8DigitDecValues(this)" cssClass="inputBox" cssStyle="text-align:right;" maxlength="30" disabled="true"/>
												</s:if>
												<s:else>
													<s:textfield name="exchageRate[%{#attr.record_rowNum-1}]" id="exchageRate[%{#attr.record_rowNum-1}]" value="%{#myrow.exchangeRate}"   onkeyup="allow8DigitDecValues(this)" cssClass="inputBox" cssStyle="text-align:right;" maxlength="30"/>
													</s:else>	
												</display:column>
											</display:table>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
								<s:if test='!"view".equals(mode)'>
								<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="submitValues()"  />
								</s:if>
								<s:hidden name="mode"/>
								<%--<s:hidden name="exchangeRate" />--%>
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
	document.exchange.action='${pageContext.request.contextPath}/insertExchangeMasterAdmin.action';
	document.exchange.submit();
}

function List() {
	document.exchange.action='${pageContext.request.contextPath}/exchangeMasterAdmin.action';
	document.exchange.submit();
}

function AddNew() {
	document.exchange.action='${pageContext.request.contextPath}/addExchangeMasterAdmin.action';
	document.exchange.submit();		
}

function funEditMode(val) {
    document.exchange.mode.value="Edit"     	
	document.exchange.action='${pageContext.request.contextPath}/insertExchangeMasterAdmin.action?amendid='+val;
	document.exchange.submit();	       
}
function funViewMode(val) {
    document.exchange.mode.value="view"     	
	document.exchange.action='${pageContext.request.contextPath}/insertExchangeMasterAdmin.action?amendid='+val;
	document.exchange.submit();	       
}
</script>		
</body>
</html>
