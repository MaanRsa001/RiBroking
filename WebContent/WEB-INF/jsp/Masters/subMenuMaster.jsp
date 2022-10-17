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
					<s:form name="country" theme="simple" action="/SubMenuMasterDispatchAction" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green"><s:actionmessage/></span>
						</div>
						<s:if test='"ListSubMenu".equals(path)'>
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
													<s:select list="#{'SN':'Sub Menu Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
							</div><!--<s:hidden name="mode" value="search"/>-->
							<div class="boxcontent" align="center">
								<input type="button"  value="Go"   class="btn btn-sm btn-success" onClick="List()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="Addnew()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Allocate" class="btn btn-sm btn-warning" onclick="allocation('info')" />
							</div>
						</div>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="SubMenu.SubMenuMasterList" />
									</div>
									<div class="panel-body">
										<display:table name="subMenuList" pagesize="10" requestURI="" excludedParams="*" class="table table-bordered" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement"	value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
										
											<display:column sortable="true" style="text-align:left;" title="Sub Menu Name" property="SUB_MENU_NAME" />
											<display:column sortable="true" style="text-align:left;" title="Sub Menu Code" property="SUB_MENU_CODE" />
											<display:column sortable="true"	style="text-align:center;" title="Status" property="STATUS_NAME" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${record.SUB_MENU_CODE}')" alt="Edit Contract " width="12" height="17">
											</display:column>
											
										
										</display:table>																	
									</div>
								</div>
							</div>
						</div>
						<s:hidden name="product_Id" id="product_id" />
						<!--<s:hidden name="mode"/>	
						--></s:if>
						
						<s:if test='"DispForm".equals(path)'>
						
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="submenu.hedding" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<s:if test='"edit".equals(mode)'>
												<s:hidden name="submenuId" />
											</s:if>		
											<s:if test='"update".equals(mode)'>
											<div class="textfield">
												<div class="text">
													<s:text name="Sub Menu Id" />
												</div>
												<div class="tbox">
														<s:property value="submenuId" />
														<s:hidden name="submenuId"/>
												</div>
											</div>
											</s:if>		
																			
											<div class="textfield">
												<div class="text">
													<s:text name="submenu.smenuName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="subMenuName" cssClass="inputBox" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="submenu.smenucode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="subMenuCode" cssClass="inputBox" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="submenu.remarks" />
												</div>
												<div class="tbox">
													<s:textarea rows="1" name="remarks" cssClass="inputBoxA" cssStyle="width: 90%;" />
												</div>
											</div>											
											<div class="textfield">
												<div class="text">
													<s:text name="submenu.satus" /> &nbsp;<span style="color: red;">*</span>
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
								<input type="button" value="Submit" class="btn btn-sm btn-success"  onClick="submitValues()"  />
									<s:hidden name="mode"/>						
							</div>
						</div>
							
						</s:if>
						
						<s:if test='"Allocate".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="menumaster.product" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.product" />
												</div>
												<div class="tbox">
													<s:select list="#{}" name="productId" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="getMenuList()" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="menumaster.menu" />
												</div>
												<div class="tbox">
													<s:select list="#{}" name="menuName" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="getSubMenuList()" />
												</div>
											</div>
											
											<br class="clear"/>
										</div>
										<div class="boxcontent">
										<s:if test='subMenuList != null || subMenuList != ""'>
										<table class="table table-bordered" width="100%">
											<!-- Iterator Starts Here -->
											<tr>
												<td><%--
													<bean:define id="id" name="smlist" property="key"></bean:define>
													<html:multibox property="selectedSubMenus" value="<%=(String)id %>"/>
													<bean:write name="smlist" property="value"/>  --%>
												</td>
											</tr>
											<tr>
												<td align="center">
													<input type="button" class="btn btn-sm btn-danger" value="Cancel" onclick="allocation()"/> &nbsp;&nbsp;&nbsp;
													<input type="button" class="btn btn-sm btn-success" value="Save" onclick="saveAllocation()"/>
												</td>
											</tr>
											<!-- Iterator Ends Here -->
										</table><s:hidden name="mode"/>
										</s:if>
										</div>										
									</div>
								</div>
							</div>
						</div>
						
						</s:if>
						
						<s:if test='"Success".equals(path)'>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="submenumaster.headding" />
									</div>
									<div class="panel-body">
										<div class="boxcontent" align="center">
											<s:text name="submenumenumaster.successMessange" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow" align="center">							
							<input type="button" class="btn btn-sm btn-info" value="List" onclick="List()"/>
						</div>	
						</s:if>
						<s:if test='"AllocateSuccess".equals(path)'>
						
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
						<div class="tablerow" align="center">							
							<input type="button" class="btn btn-sm btn-info" value="List" onclick="List()"/> &nbsp;&nbsp;&nbsp;
							<input type="button" value="Allocate" class="btn btn-sm btn-primary" onclick="allocation('info')"/>
						</div>	
						</s:if>
						<s:if test='"SubMenuInfo".equals(path)'>
					
						<div class="tablerow">							
							<div class="boxcontent">
							
								<!-- Iterator Starts Here id="menumap" name="submenuinfo" -->
								<s:iterator var="menumap" value="info">
				
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:iterator value="menumap">
											<s:text name="%{key}" />
										</s:iterator></div>
										<!--<s:set id="prodname" name="menumap" value="%{menumap.key}" />
										
									</div>
									<s:set id="prod" name="menumap" value="%{#menumap.key}" />
									<s:set id="productmap" name="menumap" value="%{menumap.key}" />-->
									<div class="panel-body">
									
										<table width="100%" class="table table-bordered">
											<!-- Iterator Starts Here id="productinfo" name="productmap" -->
											<s:iterator id="productinfo" value="value">
										
											<tr>
												<td width="30%">
												<%--	<bean:define id="dept" name="productinfo" property="key"/>
													<html:multibox property="selectedDepartments"	name="UserMasterForm" value="<%=prod.toString()+','+dept.toString()%>" />
													<bean:define id="deptname" name="productinfo" property="key"/>
													<bean:write name="productinfo" property="key"/> 
													--%>
													
													<s:text name="key" />
													<!--<s:text name="%{key}" />-->
												</td>
												<td width="70%">
													<table width="100%" class="table table-bordered">
														<s:set id="deptmap" name="productinfo" value="%{productinfo.userListMaster}" />
														<!-- Iterator Starts Here -->
														<s:iterator id="deptinfo" value="value">
														<tr>
															<td width="30%">
																<s:text name="key" />
																
															</td>
															<td width="70%">
																<table width="100%" class="table table-bordered">
																	<!-- Iterator Starts Here id="mmap" name="menuinf" -->
																	<s:iterator id="menuinfo" value="value">
																	<tr>
																		<td width="50%">
																			<s:text name="value" />
																			
																		</td>
																		<!--<td width="20%">
													
																	<s:if test='gvalue != null || gvalue != ""'>
																	<s:textfield cssClass="inputBox" name="%{processinfo.processid}" value="%{processinfo.gvalue}" onkeyup="checkDecimals15(this)" size="15" />
																</s:if></td> -->
											
															<td width="30%">
															
																<!--<s:set id="lid" name="loginId" value="loginId" />
																<input type="button" class="btn btn-sm btn-info" value="Allocate" onclick="menuAllocate('menuscreen','%{processid}','%{lid}')"/>-->	
																	<input type="button" class="btn btn-sm btn-warning" value="Allocate" onclick="submenuAllocate('submenuscreen','<s:property value="key" />')" />																
																	</td>
																	</tr>
																	<!-- Iterator Ends Here -->
																	</s:iterator>
																</table>
															</td>
														</tr>
														<!-- Iterator Ends Here -->
														</s:iterator>
													</table>
												</td>
											</tr>
											<!-- Iterator Ends Here -->
											</s:iterator>
										</table>
									</div>
								</div>
								</s:iterator>
								<!-- Iterator Ends Here -->
								
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button"  value="Cancel" class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
				
											</div>
						</div>
						<s:hidden name="loginId" />
						</s:if>
						<s:if test='"success".equals(path) || "error".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">								
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:if test='"menu".equals(root)'>
										<s:text name="user.menuallocation" />
										</s:if>
										<s:if test='"submenu".equals(root)'>
										<s:text name="user.submenuallocation" />
										</s:if>
									</div>
									<div class="panel-body">
										<s:text name="user.successMessage" />
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button"  value="Cancel" class="btn btn-sm btn-danger" onClick="List()" />
							</div>
						</div>
						</s:if>
						
						<!--<s:if test='"SubMenuInfo".equals(path)'>
						<!-- Iterator Starts Here id="menumap" name="submenuinfo" 
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:property value="%{menumap.key}" />
									</div>
									<div class="panel-body">
										<s:set id="prod" name="" value="%{menumap.key}" />
										<s:set id="productmap" name="" value="%{menumap.value}" />					
										<div class="boxcontent" align="center">
											<table class="table table-bordered" width="100%">
												<!-- Iterator Starts Here id="productinfo" name="productmap" 
												<tr>
													<td width="30%">
														<s:property value="%{productinfo.key}"/>
													</td>
													<td width="70%">														
														<table width="100%" class="table table-bordered">
															<s:set id="deptmap" name="" value="%{productinfo.value}" />
															<!-- Iterator Starts Here id="processinfo" name="deptmap"
															<tr>
																<td width="30%">
																	<s:property value="%{processinfo.key}"/>
																</td>
																<td width="70%">
																	<table width="100%" class="table table-bordered">
																		<s:set id="menuinf" name="" value="%{processinfo.value}" />
																		<!-- Iterator Starts Here id="mmap" name="menuinf"
																		<tr>
																			<td width="50%">&nbsp;/>
																				<s:property value="%{mmap.value}" />
																			</td>
																			<td width="50%">
																				<s:set id="processid" name="" value="%{mmap.key}" />																				
																				<input type="button" class="btn btn-sm btn-warning" value="Allocate" onclick="submenuAllocate('submenuscreen','<s:property value="processid" />')"/>
																			</td>
																		</tr>
																		<!-- Iterator Ends Here 
																	</table>
																</td>
															</tr>
															<!-- Iterator Ends Here 
														</table>
													</td>
												</tr>
												<!-- Iterator Ends Here 
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Iterator Ends Here 
						</s:if>		-->				
						
					</div>				
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function allocation(val) {
	document.forms[0].action='${pageContext.request.contextPath}/SubMenuAllocationsAdmin.action?mode='+val;
    document.forms[0].submit();
}



function submenuAllocate(value,processId) {

	var URL="<%=request.getContextPath()%>/SubMenuAllocationsAdmin.action?mode="+value+"&processId="+processId;
	
	var windowName = "Details";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 800;
	var h = 400;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 10) * .5)  +
		',top='			  + ((height - h - 30) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=no';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}
		
function submitValues() {
	document.forms[0].action='${pageContext.request.contextPath}/insertsubmenuMasterAdmin.action'
    document.forms[0].submit();
}
   	
function back() {
	document.forms[0].action="<%=request.getContextPath()%>/SubMenuMasterDispatchAction.do?method=Init";
	document.forms[0].submit();
}

function funEditMode(val) {
   // document.forms[0].mode.value="Edit";
	document.forms[0].action='${pageContext.request.contextPath}/insertsubmenuMasterAdmin.action?subMenuCode='+val+'&mode=Edit';
	document.forms[0].submit();	       
}

function SubmitForm() {
	document.forms[0].action="<%=request.getContextPath()%>/SubMenuMasterDispatchAction.do?method=InsertSubMenuMaster&BusinessMode=list";
	document.forms[0].submit();
}

function List() {
	document.forms[0].action='${pageContext.request.contextPath}/submenuMasterAdmin.action';
	document.forms[0].submit();
}

function getMenuList(){
	document.forms[0].action="<%=request.getContextPath()%>/SubMenuMasterDispatchAction.do?method=AllocateInit&BusinessMode=Product";
	document.forms[0].submit();
}

function getSubMenuList() {
	document.forms[0].action="<%=request.getContextPath()%>/SubMenuMasterDispatchAction.do?method=AllocateInit&BusinessMode=Menu";
	document.forms[0].submit();
}

function saveAllocation() {
	document.forms[0].action="<%=request.getContextPath()%>/SubMenuMasterDispatchAction.do?method=AllocateInit&BusinessMode=save";
	document.forms[0].submit();
}
function Addnew(){
document.forms[0].action='${pageContext.request.contextPath}/editsubmenuMasterAdmin.action';
document.forms[0].submit();

}
</script>		
</body>
</html>
