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
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						
						<s:if test='"ListUWLimitMaster".equals(path)'>
						
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
													<s:select list="#{'UN':'UnderWritter Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
										<s:text name="label.UWlimitMasters" />
									</div>
									<div class="panel-body">
										<display:table name="UWLimitMasterList" pagesize="10" requestURI="" excludedParams="*" class="table table-bordered" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement"	value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											
											<display:column sortable="true"	style="text-align:left;" title="Product Name" property="PRODUCT_NAME" />
											<display:column sortable="true"	style="text-align:left;" title="Department Name" property="DEPARTMENTNAME" />
											<display:column sortable="true"	style="text-align:center;" title="UW Name" property="UNDERWRITTER" />
											<display:column sortable="true"	style="text-align:center;" title="UW Limit" property="UNDERWRITTER_LIMIT" />
											<display:column sortable="true"	style="text-align:center;" title="Status" property="STATUS" />
											<display:column sortable="true"	style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.PRODUCT_ID}','${record.DEPARTMENTID}','${record.UNDERWRITERID}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										
										</display:table><!--<s:hidden name="mode" />
										<s:hidden name="product_Id" id="product_id" />		-->																
									</div>
								</div>
							</div>
						</div>
						
						</s:if>
						<s:if test='"InsertUWLimitMaster".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="heading.UWLimitMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="process.Product" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:select list="ProductList" name="productId" id="pid" cssClass="inputBoxS" onchange="getProduct(this.value,'departmentId1','')"  listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="-1" headerValue="---Select---" disabled="mode=='update'?true:false"/>
													<s:if test='"update".equals(mode)'>
													<s:hidden name="productId"></s:hidden>
													</s:if>
												</div>
											</div>
											<div class="textfield">
												<div class="text" >
													<s:text name="process.Department" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" id="departmentId1">
												<s:select list="departmentList" name="departmentId" cssClass="inputBoxS"  headerKey="-1" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME"  headerValue="---Select---" disabled="mode=='update'?true:false"/>
												<s:if test='"update".equals(mode)'>
												<s:hidden name="departmentId"></s:hidden>
												</s:if>
												</div>
												
											</div>
											<div class="textfield">
												<div class="text" >
													<s:text name="label.UWName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" >
												<s:select list="underWritterList" name="underwritter" cssClass="inputBoxS"  headerKey="-1" listKey="UWR_CODE" listValue="UNDERWRITTER"  headerValue="---Select---" disabled="mode=='update'?true:false"/>
												<s:if test='"update".equals(mode)'>
												<s:hidden name="underwritter"></s:hidden>
												</s:if>
												</div>
												
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.UWLimit" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="uwlimit" cssClass="inputBox" maxlength="100"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Country.Active" /> &nbsp;<span style="color: red;">*</span>
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
								<!--<s:hidden name="categoryName" />-->
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
getProduct('<s:property value="productId"/>','departmentId1','<s:property value="departmentId"/>');
}
function getProduct(value,bvalue,cvalue) {
var URL='${pageContext.request.contextPath}/DepartmentdropDownAdmin.action?productId='+value+'&reqform='+bvalue+'&departmentId='+cvalue+'&mode=<s:property value="mode"/>';
postRequest(URL,'departmentId1');
}
	function submitValues() {
		document.forms[0].action='${pageContext.request.contextPath}/insertUWLImitMasterAdmin.action';
		document.forms[0].submit();
	}
	
	function List() {
document.forms[0].action='${pageContext.request.contextPath}/underwriterLimitMasterAdmin.action'
		document.forms[0].submit();
	}
	function Addnew() {
		document.forms[0].action='${pageContext.request.contextPath}/editUWLImitMasterAdmin.action?mode=insert';
		document.forms[0].submit();
	}
	function funEditMode(val,val1,val2) {
	document.forms[0].mode.value='Edit';
	document.forms[0].action='${pageContext.request.contextPath}/insertUWLImitMasterAdmin.action?productId='+val+'&departmentId='+val1+'&underwritter='+val2;
	document.forms[0].submit();	       
}
</script>		
</body>
</html>
