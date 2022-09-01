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
					<s:form name="riskgrade" theme="simple" action="/RiskGradeMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
								<span style="color:green"><s:actionmessage/></span>
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
													<s:select list="#{'RG':'RiskGrade Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
							</div><s:hidden name="mode" id="mode" value="search"/>
							<div class="boxcontent" align="center">
								<input type="button"  value="Go"   class="btn btn-sm btn-success" onClick="List()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="Addnew()" />
							</div>
						</div>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="riskgrade.RiskGradeMasterList" />
									</div>
									<div class="panel-body">
										<display:table name="RiskGradeList" pagesize="10" requestURI=""	excludedParams="*" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
										
											<display:column sortable="true"	style="text-align:center;" title="RiskGrade Id"	property="GRADE_ID" />
											<display:column sortable="true"	style="text-align:left;" title="RiskGrade Name"	property="GRADE_DESC" />
											<display:column sortable="true"	style="text-align:center;" title="Status" property="STATUS_NAME" />
											<display:column sortable="true"	style="text-align:center;" title=" Edit">
												<img border='0'	src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.GRADE_ID}')"  alt="Edit Contract " width="12" height="17">
											</display:column>
										
										</display:table>
										<!--<s:hidden name="product_id" id="product_id" />-->																		
										
									
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
										<s:text name="headding.RiskGradeMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<s:if test='"update".equals(mode)'>
											<div class="textfield">
												<div class="text">
													<s:text name="riskgrade.RiskGradeId" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="riskGradeId" />
												</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text">
													<s:text name="riskgrade.RiskGradeName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="riskGradeName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="riskgrade.CoreAppCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="coreCompanyCode" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="riskgrade.Active" />
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
								<s:hidden value="riskGradeId" />		
							</div>						
						</div>
						</s:if>
						<s:if test='"InsertTerritoryMaster12".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="headding.RiskGradeMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<s:if test='"update".equals(mode)'>
											<div class="textfield">
												<div class="text">
													<s:text name="riskgrade.RiskGradeId" />
												</div>
												<div class="tbox">
														<s:property value="riskGradeId" />
														<s:hidden name="riskGradeId"/>
												</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text">
													<s:text name="riskgrade.RiskGradeName" />&nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
														<s:textfield name="riskGradeName" cssClass="inputBox" />
												</div>
											</div>
											<!--<div class="textfield">
												<div class="text">
													<s:text name="treaty.CoreCompanyCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="coreCompanyCode" cssClass="inputBox" />
												</div>
											</div>
											--><div class="textfield">
												<div class="text">
													<s:text name="riskgrade.Active" /> &nbsp;<span style="color: red;">*</span>
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
								<!--<s:hidden name="riskGradeId" />-->
								<s:hidden name="mode" />
								
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
	document.forms[0].action='${pageContext.request.contextPath}/insertriskgradeMasterAdmin.action';
	document.forms[0].submit();
}

function List() {
	document.forms[0].action='${pageContext.request.contextPath}/riskGradeMasterAdmin.action';
	document.forms[0].submit();
}

function back() {
	document.riskgrade.action="<%=request.getContextPath()%>/RiskGradeMasterDispatchAction.do?method=Init";
	document.riskgrade.submit();
}

function funEditMode(val) {
    document.forms[0].mode.value='Edit';
	document.forms[0].action='${pageContext.request.contextPath}/insertriskgradeMasterAdmin.action?riskGradeId='+val;
	document.forms[0].submit();     
}
function Addnew(){
     document.forms[0].action='${pageContext.request.contextPath}/editriskgradeMasterAdmin.action';
     document.forms[0].submit();
     }
</script>		
</body>
</html>
