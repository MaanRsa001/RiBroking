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
					<s:form name="process" theme="simple" action="/ProcessMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						
						<s:if test='"ListProcessMaster".equals(path)'>
						
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
													<s:select list="#{'PN':'ProcessName'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="AddNew()" />
							</div>
						</div>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="process.processMasterList" />
									</div>
									<div class="panel-body">
										<display:table name="ProcessList" pagesize="10" requestURI="" excludedParams="*" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<display:column sortable="true" style="text-align:left;" title="Product Name" property="TMAS_PRODUCT_NAME" />
											<display:column sortable="true" style="text-align:left;" title="Department Name" property="TMAS_DEPARTMENT_NAME" />
											<display:column sortable="true" style="text-align:left;" title="Process Name" property="PROCESS_NAME" />
											<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0'	src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.PROCESS_ID}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										</display:table>
										<!--<s:hidden name="mode" />	-->															
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
										<s:text name="process.processMasterHeading" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="process.processName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="processName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="process.Product" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="productId" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="process.Department" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="departmentId" />
												</div>
											</div>
											<div class="textfield">
											<div class="text">
											<s:text name="process.OrderNo"/>
											</div>
											<div class="tbox">
											:&nbsp;&nbsp;&nbsp;<s:property value="orderno" />
											</div></div>
											<div class="textfield">
											<div class="text">
											<s:text name="process.UnderWritingLimit"/>
											</div>
											<div class="tbox">
											:&nbsp;&nbsp;&nbsp;<s:property value="uwlimit" />
											</div></div>
											<div class="textfield">
											<div class="text">
											<s:text name="process.Status"/>
											</div>
											<div class="tbox">
											:&nbsp;&nbsp;&nbsp;<s:property value="status" />
											</div></div>
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
								<!--<s:hidden value="productId" />		-->
							</div>						
						</div>
						</s:if>
						<s:if test='"InsertPage".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="process.processMasterHeading" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<s:if test='"update".equals(mode)'>
										 <div class="textfield">
												<div class="text">
													<s:text name="process.processId" />
												</div>
												<div class="tbox">												
													:&nbsp;&nbsp;&nbsp;<s:property value="processId" />
													<s:hidden name="hiddenvalue" id="update" />
													<s:hidden name="processId" />
												</div>
											</div>
											</s:if>											
											<div class="textfield">
												<div class="text">
													<s:text name="process.processName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="processName" cssClass="inputBox" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="process.Product" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:select list="ProductList" name="productId" id="pid" cssClass="inputBoxS" onchange="getProduct(this.value,'departmentId','')"  listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="-1" headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text" >
													<s:text name="process.Department" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" id="departmentId">
												<s:select list="departmentList" name="departmentId" cssClass="inputBoxS"  headerKey="-1" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME"  headerValue="---Select---" />
												</div>
												
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="process.OrderNo" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="orderno" cssClass="inputBox" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="process.UnderWritingLimit" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="uwlimit" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="process.Status" /> &nbsp;<span style="color: red;">*</span>
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
								<input type="button" value="Submit" class="btn btn-sm btn-success"  onClick="addSave()"  />
								<s:hidden name="mode" />
							<!--  	<s:hidden name="processId" />-->
							</div>
						</div>
						
						</s:if>
						
						<s:if test='"InsertPagesuccess".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="process.processMasterHeading" />
									</div>
									<div class="panel-body">
										<div class="boxcontent" align="center">
											Action completed successfully
										</div>
									</div>
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
var product=document.getElementById("pid").value;
if(product!=-1){
getProduct('<s:property value="productId"/>','departmentId','<s:property value="departmentId"/>');
}
function getProduct(value,bvalue,cvalue) {
var URL='${pageContext.request.contextPath}/DepartmentdropDownAdmin.action?productId='+value+'&reqform='+bvalue+'&departmentId='+cvalue;
postRequest(URL,'departmentId');
}
function getDepartmentList(value)
{
	document.forms[0].action='${pageContext.request.contextPath}/editprocessMasterAdmin.action?pid='+val;
	document.forms[0].submit();
}
function getProcessList()
{
	document.process.action="<%=request.getContextPath()%>/ProcessMasterDispatchAction.do?method=display&BusinessMode=process";
	document.process.submit();
}
function saveMenu()
{
	document.process.action="<%=request.getContextPath()%>/ProcessMasterDispatchAction.do?method=save&BusinessMode=menu";
	document.process.submit();
}
function addProcess(value)
{
	document.process.action="<%=request.getContextPath()%>/ProcessMasterDispatchAction.do?method=display&BusinessMode="+value;
	document.process.submit();
}
function AddNew() {
		document.forms[0].action='${pageContext.request.contextPath}/editprocessMasterAdmin.action';
		document.forms[0].submit();
	}
function addSave()
{
	document.forms[0].action='${pageContext.request.contextPath}/insertprocessMasterAdmin.action';
	document.forms[0].submit();
}
	function funEditMode(val) {
	document.forms[0].mode.value='Edit';
	document.forms[0].action='${pageContext.request.contextPath}/insertprocessMasterAdmin.action?processId='+val;
	document.forms[0].submit();	       
}

function List()
{
	document.process.action='${pageContext.request.contextPath}/processMasterAdmin.action';;
	document.process.submit();
}
</script>	
</body>
</html>
