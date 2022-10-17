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
					<s:form name="treaty" theme="simple" action="/TreatyMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
						
						<s:if test='"ListTreatyMaster".equals(path)'>
						
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
													<s:select list="#{}" name="searchby" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.enterDataForSearch" />
												</div>
												<div class="tbox">
													<s:textfield name="customer_names" cssClass="inputBox" />
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">
								<input type="button"  value="Go"   class="btn btn-sm btn-success" onClick="searchdetails()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="javascript:back()" />
							</div>
						</div>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="treaty.TREATYMASTERLIST" />
									</div>
									<div class="panel-body">
										<display:table name="TreatyList" pagesize="10" requestURI="/TreatyMasterDispatchAction.do?method=InsertTreatyMaster&BusinessMode=list" excludedParams="*" class="table table-bordered" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />

											<display:column sortable="true" style="text-align:center;" title="Treaty Id" property="treatyId" />
											<display:column sortable="true" style="text-align:left;" title="Treaty Name" property="treatyName" />
											<display:column sortable="true" style="text-align:center;" title="Status" property="status" />
											<display:column sortable="true"	style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.treatyId}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										
										</display:table>
										<s:hidden name="product_id" id="product_id" />
										<s:hidden name="treatyId" id="treatyId" />																			
									</div>
								</div>
							</div>
						</div>
						
						</s:if>
						
						<s:if test='"InsertTreatyMaster".equals(path)'>
						
						<s:if test='"true".equals(success)'>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="Heading.TreatyMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="treaty.TreatyId" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="treatyId" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="treaty.TreatyName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="treatyName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="treaty.CoreCompanyCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="coreCompanyCode" />
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
								<input type="button"  value="Back"   class="btn btn-sm btn-danger" onClick="Lists()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="AddNew" class="btn btn-sm btn-primary" onClick="back()"/>
								<s:hidden value="treatyId" />		
							</div>						
						</div>
						</s:if>
						<s:else>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="Heading.TreatyMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="treaty.TreatyId" />
												</div>
												<div class="tbox">												
													:&nbsp;&nbsp;&nbsp;<s:property value="treatyId" />
													<s:hidden name="hiddenvalue" id="update" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="treaty.TreatyName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="treatyName" cssClass="inputBox" />
												</div>
											</div>																						
											<div class="textfield">
												<div class="text">
													<s:text name="treaty.CoreCompanyCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="coreCompanyCode" cssClass="inputBox" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="treaty.Active" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="active" />
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
								<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="Lists()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="submitValues()"  />
								<s:hidden name="treatyId" />
							</div>
						</div>
						</s:else>						
						
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
		document.treaty.action="<%=request.getContextPath()%>/TreatyMasterDispatchAction.do?method=InsertTreatyMaster&BusinessMode=insert";
		document.treaty.submit();
	}
    
    function List() {
		document.treaty.action="<%=request.getContextPath()%>/TreatyMasterDispatchAction.do?method=InsertTreatyMaster&BusinessMode=list";
		document.treaty.submit();
	}
	
	function back() {
		document.treaty.action="<%=request.getContextPath()%>/TreatyMasterDispatchAction.do?method=Init";
		document.treaty.submit();
	}
	
	function funEditMode(val) {
		document.getElementById("treatyId").value=val;
		document.treaty.action="<%=request.getContextPath()%>/TreatyMasterDispatchAction.do?method=InsertTreatyMaster&BusinessMode=Edit";
		document.treaty.submit();	       
	}
</script>		
</body>
</html>
