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
					<s:form name="product" theme="simple" action="/TreatyMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						
						<s:if test='"ListProductMaster".equals(path)'>
						
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
													<s:select list="#{'PN':'Product Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="javascript:back()" />
							</div>
						</div>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="product.ProductMasterList" />
									</div>
									<div class="panel-body">
										<display:table name="productMasterList" pagesize="10"	requestURI="" excludedParams="*" class="footable" uid="row" id="record">
										
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											
											<display:column sortable="true" style="text-align:center;" title="Product Id" property="TMAS_PRODUCT_ID" />
											<display:column sortable="true" style="text-align:left;" title="Product Name" property="TMAS_PRODUCT_NAME" />
											<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.TMAS_PRODUCT_ID}')" alt="Edit Contract " width="12" height="17">
											</display:column>

										</display:table>
										<!--<s:hidden name="mode" />
										<s:hidden name="product_id" id="product_id" />
										<s:hidden name="productId" id="productId" />-->																		
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
										<s:text name="heading.ProductMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<!--  <div class="textfield">
												  <div class="text">
													<s:text name="product.ProductId" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="productId" />
												</div>
											</div>-->
											<div class="tbox">
												<s:if test='"insert".equals(mode)'></s:if>
																							
										</div>
										
		
											<div class="textfield">
												<div class="text">
													<s:text name="product.ProductName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="productName" />
												</div>
											</div>
											<!--  <div class="textfield">
												<div class="text">
													<s:text name="product.CoreCompanyCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="coreCompanyCode" />
												</div>
											</div>-->
											<div class="textfield">
												<div class="text">
													<s:text name="product.Active" />
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
								<!--<s:hidden value="productId" />		-->
							</div>						
						</div>
						</s:if>
						<s:if test='"InsertProductMaster".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="heading.ProductMaster" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
									<!--  	<div class="textfield">
												<div class="text">
													<s:text name="product.ProductId" />
												</div>
												<div class="tbox">												
													:&nbsp;&nbsp;&nbsp;<s:property value="productId" />
													<s:hidden name="hiddenvalue" id="update" />
												</div>
											</div>-->
											
										<s:if test='"update".equals(mode)'>
										 <div class="textfield">
												<div class="text">
													<s:text name="product.ProductId" />
												</div>
												<div class="tbox">												
													:&nbsp;&nbsp;&nbsp;<s:property value="productId" />
													<s:hidden name="hiddenvalue" id="update" />
													<s:hidden name="productId" />
												</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text">
													<s:text name="product.ProductName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="productName" cssClass="inputBox" maxlength="100"/>
												</div>
											</div>																						
											<!-- <div class="textfield">
												<div class="text">
													<s:text name="product.CoreCompanyCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="coreCompanyCode" cssClass="inputBox" maxlength="100"/>
												</div>
											</div> -->
											<div class="textfield">
												<div class="text">
													<s:text name="product.Active" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'1':'Yes','0':'No'}" name="status"  value="status==null?'0':status"/>
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
							<!--<s:hidden name="treatyId" />-->
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
		document.forms[0].action='${pageContext.request.contextPath}/insertProductMasterAdmin.action';
		document.forms[0].submit();
	}
	
	function List() {
document.product.action='${pageContext.request.contextPath}/productMasterAdmin.action'
		document.product.submit();
	}
	function Addnew() {
		document.forms[0].action='${pageContext.request.contextPath}/editProductMasterAdmin.action?mode=insert';
		document.forms[0].submit();
	}
	function back() {
		document.forms[0].action='${pageContext.request.contextPath}/editProductMasterAdmin.action?mode=insert';
		document.product.submit();
	}
	
	
	function funEditMode(val) {
	document.forms[0].mode.value='Edit';
	//document.getElementById("departmentId").value=val;
	//document.getElementById("productId").value=val1;
	document.forms[0].action='${pageContext.request.contextPath}/insertProductMasterAdmin.action?productId='+val;
	document.forms[0].submit();	       
}
</script>		
</body>
</html>
