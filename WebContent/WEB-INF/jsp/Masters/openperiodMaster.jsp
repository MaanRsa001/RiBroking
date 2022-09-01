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
					<s:form name="openperiod" theme="simple" action="/OpenperiodMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green"><s:actionmessage/></span>
						</div>
						
						<s:if test='"ListOpenMaster".equals(path)'>
						
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
													<s:select list="#{'SD':'Start Date','ED':'End Date'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
										<s:text name="OPEN PERIOD LIST" />
									</div>
									<div class="panel-body">
										<display:table name="openList" pagesize="10" requestURI="" excludedParams="*" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											
											<display:column sortable="true" style="text-align:center;" title="S.NO" property="SNO" />
											<display:column sortable="true" style="text-align:left;" title="Start Date" property="START_DATE" />
											<display:column sortable="true" style="text-align:left;" title="End Date" property="END_DATE" />
											<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS" />
											<%--<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()--%/images/icon_view_schedule.gif" onClick="funEditMode('${record.sno}')" alt="Edit Contract " width="12" height="17">
											</display:column>--%>
										
										</display:table>
									<!--<s:hidden name="mode" id="mode"/>-->
										<s:hidden name="product_Id" id="product_Id" />																		
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
										<s:text name="Heading.Openperiod" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										
											<div class="textfield">
												<div class="text">
													<s:text name="Start Date" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="startDate" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="End Date" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="endDate" />
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="Department.Active" />
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
								<s:hidden value="subProfitCenter_Id" />
								<s:hidden value="productId" />	<s:hidden name="mode"/>				
							</div>						
						</div>
						</s:if>
						
						<s:if test='"InsertOpenMaster".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="Heading.Openperiod" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<s:if test='"update".equals(page)'>
												<s:hidden name="sno" />
												<s:hidden name="exchangeId" />
												<s:hidden name="hiddenValue" value="update" />
												<s:hidden name="amendId" />																																			
											</s:if>
											
											</div>	
											<s:if test='"update".equals(mode)'>
											<div class="textfield">
												<div class="text">
													<s:text name="S.NO" />
												</div>
												<div class="tbox">
														<s:property value="sno" />
														<s:hidden name="sno"/>
												</div>
											</div>
											</s:if>																			
											<div class="textfield">
												<div class="text">
													<s:text name="Start Date" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<sj:datepicker name="startDate" id="startDate" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="End Date" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<sj:datepicker name="endDate" id="endDate" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>
												</div>
											</div>

																													<div class="textfield">
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
					</div>
						</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	
														
<script type="text/javascript">
function submitValues() {
	document.forms[0].action='${pageContext.request.contextPath}/insertopenperiodMasterAdmin.action';
	document.forms[0].submit();
}

function List() {
	document.forms[0].action='${pageContext.request.contextPath}/openperiodMasterAdmin.action';
	document.forms[0].submit();
	}

function back() {
	document.forms[0].action='${pageContext.request.contextPath}/getbranchCodeAdmin.action'
	document.forms[0].submit();	
}

function funEditMode(val) {         	
	document.forms[0].mode.value='Edit';
	document.forms[0].action='${pageContext.request.contextPath}/insertopenperiodMasterAdmin.action?sno='+val;
	document.forms[0].submit();	       
}

function edit(val) {
	document.branch.action="<%=request.getContextPath()%>/BranchMasterDispatchAction.do?method=InsertBranchMaster&BusinessMode=Edit&identity="+val;
	document.branch.submit();
}
function Addnew(){
document.forms[0].action='${pageContext.request.contextPath}/editopenperiodMasterAdmin.action';
document.forms[0].submit();
}
</script>		
</body>
</html>
