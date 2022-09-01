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
					<s:form name="menus" theme="simple" action="" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
						
						<s:if test='"Home".equals(path)'>						
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="emnumaster.headding" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.type" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:select list="#{'Admin':'Admin','User':'User'}" name="userType" id="uid" onchange="getProduct(this.value,'product')" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.product" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" id="product">
													<s:select list="productList" name="productId" id="pid" onchange="getProduct(this.value,'department')" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.department" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" id="department">
													<s:select list="departmentList" name="department" id="dpid" onchange="getProduct(this.value,'process')" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.process" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" id="process">
													<s:select list="processList" name="process" id="processid" onchange="getProduct(this.value,'menu')" listKey="PROCESS_ID" listValue="PROCESS_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.menuName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" id="menu">
													<s:select list="menuList" name="menuId"  listKey="MENU_ID" listValue="MENU_NAME"  headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
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
								<input type="button"  value="Edit" class="btn btn-sm btn-info" onClick="Edit()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Reset" class="btn btn-sm btn-danger" onClick="home()"  /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onclick="getForm('AddForm')"  />
							</div>
						</div>					
						
						</s:if>
						
						<s:if test='"DispForm".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="emnumaster.headding" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.menuId" />
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="menuId" readonly="true" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.menuName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="menuName" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.url" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="menuUrl" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.type" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:select list="#{'Admin':'Admin','User':'User'}" name="userType" id="uid" onchange="getProduct(this.value,'product')" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.product" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" id="product">
													<s:select list="productList" name="productId" id="pid" onchange="getProduct(this.value,'department')" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.department" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" id="department">
													<s:select list="departmentList" name="department" id="dpid" onchange="getProduct(this.value,'process')" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.process" /> &nbsp;<span style="color: red;">*</span>
												</div>
													<div class="tbox" id="process">
													<s:select list="processList" name="process" id="processid" onchange="getProduct(this.value,'menu')" listKey="PROCESS_ID" listValue="PROCESS_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.orderby" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="orderby" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.startDate" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<sj:datepicker name="inceptionDate" id="inceptionDate" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.endDate" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<sj:datepicker name="expiryDate" id="expiryDate" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>
												</div>
											</div>
											<div class="textfieldA">
												<div class="text">
													<s:text name="menumaster.remarks" />
												</div>
												<div class="tbox">
													<s:textarea rows="1" name="remarks" cssClass="inputBoxA" cssStyle="width: 90%;" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.status" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'1':'Yes','0':'No'}" name="active" value="active==null?'0':active"/>
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
								<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="home()"  /> &nbsp;&nbsp;&nbsp;
								<s:if test='"insert".equals(mode)'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="SubmitForm('insert')"/>
								</s:if>
								<s:if test='"update".equals(mode)'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="SubmitForm('update')"/>
								</s:if>
							</div>
						</div>										
						
						</s:if>
						
						<s:if test='"success".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="emnumaster.headding" />
									</div>
									<div class="panel-body">
										<div class="boxcontent" align="center">											
											<s:text name="menumaster.successMessange" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">								
								<input type="button" value="Home" class="btn btn-sm btn-danger" onClick="home()"  />
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
function getProduct(value,bvalue){
	var userType=document.getElementById("uid").value;
	var proId=document.getElementById("pid").value;
	if(value='process'){
	var deptId=document.getElementById("dpid").value;
	}
	if(value='menu'){
	var processId=document.getElementById("processid").value;
	}
	var URL='${pageContext.request.contextPath}/dropdownAjaxAdmin.action?process='+processId+'&reqform='+bvalue+'&userType='+userType+'&productId='+proId+'&department='+deptId;
	postRequest(URL,bvalue);
}   
    
    	
function funEditMode(val) {       	
	document.getElementById("product_Id").value=val;
	document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=Edit";
	document.forms[0].submit();	       
}
  
function getForm(val) {
	document.menus.action='${pageContext.request.contextPath}/AddNewAdmin.action?mode='+val;
	document.menus.submit();
}

function SubmitForm(value) {
	document.menus.action='${pageContext.request.contextPath}/insertMenuMasterAdmin.action?mode='+value;
	document.menus.submit();
}

function home() {
	document.menus.action='${pageContext.request.contextPath}/menuMasterAdmin.action';
	document.menus.submit();
}

function getMenuList() {
	document.forms[0].action="<%=request.getContextPath()%>/MenuMasterDispatchAction.do?method=getMenuList";
	document.forms[0].submit();
}

function Edit() {
	document.menus.action='${pageContext.request.contextPath}/insertMenuMasterAdmin.action?mode=Edit';
	document.menus.submit();
}

</script>		
</body>
</html>
