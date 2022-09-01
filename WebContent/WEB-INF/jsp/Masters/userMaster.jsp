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
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script language="JavaScript" type="text/javascript">
        function stopRKey(evt) {
            var evt = (evt) ? evt : ((event) ? event : null);
            var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
            if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
        }
        document.onkeypress = stopRKey;
	</script >
	<style type="text/css">
		.btn-group {
		width: 100%;
		}
		.btn-group .btn {
		width: 90%;
		padding: 3px 12px;
		}
	</style>
</head>

<body>
<div class="table0" style="width: 100%; margin: 0 auto;height: 600px;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="user" theme="simple" action="" method="post">
						<div class="table2">
							<div class="tablerow">
								<span style="color:red;"><s:actionerror/></span>
							</div>

							<s:if test='"ListUserMaster".equals(path)'>

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
															<s:select list="#{'UN':'User Name'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
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
									<div class="boxcontent" align="center">
										<input type="button"  value="Go"   class="btn btn-sm btn-success" onClick="List()" /> &nbsp;&nbsp;&nbsp;
										<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="LoginUserHome()" />
									</div>
								</div>

								<div class="tablerow">
									<div class="boxcontent">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="user.UserMasterList" />
											</div>
											<div class="panel-body">
												<display:table name="userListMaster" pagesize="10" requestURI="" excludedParams="*" class="footable" uid="row" id="record">
													<display:setProperty name="paging.banner.one_item_found" value="" />
													<display:setProperty name="paging.banner.one_items_found" value="" />
													<display:setProperty name="paging.banner.all_items_found" value="" />
													<display:setProperty name="paging.banner.some_items_found" value="" />
													<display:setProperty name="paging.banner.placement"	value="bottom" />
													<display:setProperty name="paging.banner.onepage" value="" />
													<display:column sortable="true" style="text-align:left;" title="Login Id" property="LOGIN_ID" />
													<display:column sortable="true"	style="text-align:left;" title="User Name" property="USERNAME" />
													<display:column sortable="true"	style="text-align:center;" title="Status" property="STATUS_ACTIVE" />
													<display:column sortable="true" style="text-align:center;" title=" Edit">
														<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="LoginEdit('${record.LOGIN_ID}')" alt="Edit Contract " width="12" height="17">
													</display:column>
													<display:column style="text-align:center;" sortable="true" title="Password">
														<%--<s:set id="login_id" name="record" value="${record.LOGIN_ID}"  />
                                                        <a class="btn btn-sm btn-info" href="${pageContext.request.contextPath}/insertUserMasterAdmin.action?mode=PasswordDisp&sno=1&loginId=<s:property value='login_id' />">Change</a>--%>
														<a class="btn btn-sm btn-info" onclick="ChangePassword('${record.LOGIN_ID}');" >Change</a>
													</display:column>
													<display:column style="text-align:center;" sortable="true" title="Menus">
														<%--	<s:set id="login_id" name="record" value="loginId" />
                                                            <s:set id="usertype" name="record" value="userType" />
                                                            <a href="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=menuAllocations&BusinessMode=Menu&loginId=<s:property value='login_id' />&usertype=<s:property value='usertype' />">Allocated</a> --%>
														<a  class="btn btn-sm btn-info" onclick="MenuAllocation('${record.LOGIN_ID}','${record.USERTYPE}')">Allocated</a>
													</display:column>
												</display:table>
												<s:hidden name="lid" id="lid" />
												<s:hidden name="login_id" id="login_id" />
												<s:hidden name="product_Id" id="product_id" />
											</div>
										</div>
									</div>
								</div>

							</s:if>

							<s:if test='"ListRights".equals(path)'>

								<s:if test='"true".equals(success)'>
									<div class="tablerow">
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="heading.RightAllocation" />
												</div>
												<div class="panel-body">
													<div class="boxcontent" align="center">
														<s:text name="user.success" /> <br/>
														<input type="button" value="List" class="btn btn-sm btn-warning" onClick="List()" /> &nbsp;&nbsp;&nbsp;
														<input type="button" value="Allocate" class="btn btn-sm btn-primary" onclick="dispalyRights()" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="tablerow">
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-body">
													<div class="boxcontent">
														<div style="width: 50%; margin: 0 auto;">
															<div class="text">
																<s:text name="user.selectProduct" /> &nbsp;<span style="color: red;">*</span>
															</div>
															<div class="tbox">
																<s:select list="#{}" name="manufacturerId" onchange="dispalyRights()" listKey="0" listValue="---Select---" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="tablerow">
										<div class="boxcontent" align="center">
											<input type="button" value="View" class="btn btn-sm btn-primary" onclick="ViewRights()" /> &nbsp;&nbsp;&nbsp;
											<input type="button" value="List" class="btn btn-sm btn-warning" onclick="List()"/>
										</div>
									</div>
								</s:else>
								<s:if test="menuList != '' || menuList != null">
									<div class="tablerow">
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="heading.RightAllocation" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div style="width: 50%; margin: 0 auto;">
															<div class="text">
																<s:text name="user.selectMenu" />
															</div>
															<div class="tbox">
																<s:select list="#{}" name="menuName" onchange="changeRights(this.value)" listKey="0" listValue="---Select---" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</s:if>

								<s:if test="subMenuList != '' || subMenuList != null">
									<div class="tablerow">
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="heading.RightAllocation" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<table width="100%" class="footable">
															<!-- Iterator Starts Here id="smlist" property="subMenuList" name="UserMasterForm" -->
															<s:iterator id="smlist" value="subMenuList">

																<s:if test='smlist != "" || smlist != null'>
																	<tr>
																		<td>

																			<s:property value="key"/>
																			<s:property value="value"/>
																				<%--
                                                                                <bean:define id="id" property="key" name="smlist" />
                                                                                <html:multibox property="selectSubMenu"
                                                                                    value="<%=(String) id%>" />
                                                                                <bean:write name="smlist" property="key" />
                                                                                 --%>
																		</td>
																		<td>
																			<s:set id="gvalue" value="value"/>
																			<s:property value="value"/>
																				<%--<s:iterator id="limitList" id="limitsList">
                                                                                    <s:set id="lname" name="limitList"/>
                                                                                    <s:property value="key"  />
                                                                                    <s:if test="id" >
                                                                                    <s:property value="key" />
                                                                                        <s:set id="lvalue" />
                                                                                        <s:property value="value" />
                                                                                        <s:set id="gvalue" value="lvalue"/>
                                                                                    </s:if>
                                                                                </s:iterator>--%>
																			<input type="text" name="id" value="gvalue"/>
																		</td>
																	</tr>
																</s:if>
															</s:iterator>
															<!-- Iterator Ends here -->
															<s:if test='smlist != "" || smlist != null'>
																<tr>
																	<td colspan="2">
																		<input type="button" class="btn btn-sm btn-danger" value="Cancel" onclick="List()" /> &nbsp;&nbsp;&nbsp;
																		<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="saverights()" />
																	</td>
																</tr>
															</s:if>
															<s:else>
																<tr>
																	<td>
																		<s:text name="user.limitLabel" />
																	</td>
																	<td>
																		<s:textfield name="limitValue" cssClass="inputBox" />
																	</td>
																</tr>
																<tr>
																	<td colspan="2">
																		<input type="button" class="btn btn-sm btn-danger" value="Cancel" onclick="List()" /> &nbsp;&nbsp;&nbsp;
																		<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="saveSingleLimit()"  />
																	</td>
																</tr>
															</s:else>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
									<s:hidden name="menuName" />
									<s:hidden name="manufacturerId" />
								</s:if>
								<s:hidden name="loginId" />
							</s:if>

							<s:if test='"ViewRight".equals(path)'>
								<div class="tablerow">
									<div class="boxcontent">
										<div class="panel panel-primary">
											<div class="panel-body">
												<div class="boxcontent">
													<div style="width: 50%; margin: 0 auto;">
														<div class="text">
															<s:text name="user.selectProduct" />
														</div>
														<div class="tbox">
															<s:select list="#{}" name="manufacturerId" onchange="ViewRights()" cssClass="inputBoxS" headerKey="0" headerValue="---Select---" />
														</div>
													</div>
													<br class="clear"/>
													<table class="footable" width="100%">
														<!-- Iterator Starts Here id="mlist" name="UserMasterForm" property="rightList" -->
														<s:if test="mlist != '' || mlist != null">
															<tr>
																<td>
																	<s:set id="menuName" name="key" />
																	<s:set id="lid" name="loginId" />
																	<s:set id="mfid" name="manufacturerId" />
																		<%--
                                                                        <a
                                                                            href="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=LinkChangeRights&menuName=<%=menuName %>&mfid=<%=mfid %>&l_id=<%=lid %>"><span
                                                                            style="color: blue;"><bean:write name="mlist"
                                                                                    property="key" />
                                                                        </span>
                                                                        </a> --%>
																</td>
																<td>
																	<!-- Iterator Starts Here id="mlist" name="UserMasterForm" property="rightList" --> -->
																	<s:if test="slist != '' || slist != null">
																		<table width="100%" class="footable">
																			<tr>
																				<td width="50%">
																					<s:property value="key"/>
																				</td>
																				<td width="50%">
																					<s:if test='slist != "" || slist != null'>
																						<s:text name="user.selectedLable" />
																					</s:if>
																					<s:else>
																						<s:text name="user.notSelectedLable" />
																					</s:else>
																				</td>
																			</tr>
																		</table>
																	</s:if>
																	<!-- Iterator Ends Here -->
																</td>
															</tr>
														</s:if>
														<!-- Iterator Ends Here -->
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tablerow">
									<div class="boxcontent" align="center">
										<input type="button" value="List" class="btn btn-sm btn-warning" onclick="List()" /> &nbsp;&nbsp;&nbsp;
										<input type="button" value="Allocate" class="btn btn-sm btn-primary" onclick="dispalyRights()" />
									</div>
								</div>
								<s:hidden name="loginId" />
							</s:if>

							<s:if test='"MenuDisp".equals(path)'>
								<div class="tablerow">
									<div class="boxcontent">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="heding.MenuList" />
											</div>
											<div class="panel-body">
												<s:if test='"success".equals(entry)'>
													<div class="boxcontent" align="center">
														<s:text name="user.success" />
													</div>
													<div class="boxcontent" align="center">
														<input type="button" value="List" class="btn btn-sm btn-warning" onclick="List()"/>
													</div>
												</s:if>
												<s:else>
													<div class="boxcontent">
														<div style="width: 50%; margin: 0 auto;">
															<div class="text">
																<s:text name="user.selectProduct" />
															</div>
															<div class="tbox">
																<s:select list="#{}" name="manufacturerId" onchange="MenuDisp()" listKey="0" listValue="---Select---" />
															</div>
														</div>
													</div>
													<div class="boxcontent" align="center">
														<input type="button" value="List" class="btn btn-sm btn-warning" onclick="List()"/>
													</div>
												</s:else>
												<s:if test='"1".equals(entry)'>
													<table class="footable" width="100%">
														<!-- Iterator Starts id="MenuList" name="mainMenuList" -->
														<tr>
															<td>
																	<%--
                                                                    <bean:define id="id" name="MenuList" property="value" />
                                                                    <html:multibox property="selectedMainMenus"
                                                                        value="<%=(String) id%>" />
                                                                    <bean:write name="MenuList" property="key" />
                                                                    --%>
															</td>
														</tr>
														<!-- Iterator Ends -->
														<tr>
															<td colspan="2" align="center">
																<input type="button" class="btn btn-sm btn-danger" value="Cancel" onclick="menuDispCancel('<bean:write name="UserMasterForm" property="loginId"/>')" /> &nbsp;&nbsp;&nbsp;
																<input type="button" class="btn btn-sm btn-success" value="Submit" onclick="updateMainMenu()" />
															</td>
														</tr>
													</table>
												</s:if>
											</div>
										</div>
									</div>
								</div>
								<s:hidden name="loginId" />
							</s:if>

							<s:if test='"LoginDisp".equals(path)'>
								<div class="tablerow">
									<div class="boxcontent">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="heading.UserMaster" />
											</div>
											<div class="panel-body">
												<div class="boxcontent">
													<s:if test='"new".equals(useroperation)'>
														<div class="textfield">
															<div class="text">
																<s:text name="user.loginId" /> &nbsp;<span style="color: red;">*</span>
															</div>
															<div class="tbox">
																<s:textfield name="loginId" cssClass="inputBox" />
															</div>
														</div>
													</s:if>
													<s:else>
														<div class="textfield">
															<div class="text">
																<s:text name="user.loginId" />
															</div>
															<div class="tbox">
																:&nbsp;&nbsp;&nbsp;<s:property value="loginId" />
																<s:hidden name="loginId" />
															</div>
														</div>
													</s:else>
													<div class="textfield">
														<div class="text">
															<s:text name="user.userName" /> &nbsp;<span style="color: red;">*</span>
														</div>
														<div class="tbox">
															<s:textfield name="userName" cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="user.userType" /> &nbsp;<span style="color: red;">*</span>
														</div>
														<div class="tbox">
															<s:select list="#{'User':'User','Admin':'Admin','superuser':'superuser'}" cssClass="inputBoxS" name="userType" headerKey="0" headerValue="---Select---" />
														</div>
													</div>

													<div class="textfield">
														<div class="text">
															<s:text name="user.email" /> &nbsp;<span style="color: red;">*</span>
														</div>
														<div class="tbox">
															<s:textfield name="email" cssClass="inputBox"  id="email"/>
														</div>
													</div>
													<s:if test='"new".equals(useroperation)'>
														<div class="textfield">
															<div class="text">
																<s:text name="user.password" /> &nbsp;<span style="color: red;">*</span>
															</div>
															<div class="tbox">
																<s:password name="password" cssClass="inputBox" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="user.repassword" /> &nbsp;<span style="color: red;">*</span>
															</div>
															<div class="tbox">
																<s:password name="rePassword" cssClass="inputBox" />
															</div>
														</div>
													</s:if>
													<s:hidden name="useroperation" />
													<div class="textfield">
														<div class="text">
															<s:text name="Attached UW" /> &nbsp;<span style="color: red;">*</span>
														</div>
														<div class="tbox">
															<s:select list="attachUnderWriterList" name="attachedUW" id="attachedUW" cssClass="inputBoxS" headerKey="ALL" headerValue="ALL" multiple="true" listKey="UWR_CODE" listValue="UNDERWRITTER"></s:select>
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="user.status" /> &nbsp;<span style="color: red;">*</span>
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
										<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="updateUser()"/>
									</div>
								</div>
							</s:if>

							<s:if test='"PasswordDisp".equals(path)'>
								<div class="tablerow">
									<div class="boxcontent">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="heading.UserMaster" />
											</div>
											<div class="panel-body">
												<div class="boxcontent">
													<div class="textfield">
														<div class="text">
															<s:text name="user.password" /> &nbsp;<span style="color: red;">*</span>
														</div>
														<div class="tbox">
															<s:password name="password" cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="user.repassword" /> &nbsp;<span style="color: red;">*</span>
														</div>
														<div class="tbox">
															<s:password name="rePassword" cssClass="inputBox" />
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
										<input type="button"  value="Cancel" class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
										<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="changePass()"/>
									</div>
								</div>
								<s:hidden name="loginId" />
							</s:if>

							<s:if test='"MenuPage".equals(path)'>
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
															<s:text name="menumaster.product" /> &nbsp;<span style="color: red;">*</span>
														</div>
														<div class="tbox">
															<s:select list="#{}" name="product" id="pid" onchange="getProduct(this.value,'department')" headerKey="-1" headerValue="---Select---" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="menumaster.department" /> &nbsp;<span style="color: red;">*</span>
														</div>
														<div class="tbox">
															<s:select list="#{}" name="department" onchange="getProduct(this.value,'process')" headerKey="-1" headerValue="---Select---" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="menumaster.process" /> &nbsp;<span style="color: red;">*</span>
														</div>
														<div class="tbox">
															<s:select list="#{}" name="process" onchange="getProduct(this.value,'menu')" headerKey="-1" headerValue="---Select---" />
														</div>
													</div>
													<br class="clear"/>
												</div>
												<div class="boxcontent">
													<s:if test="menuList != '' || menuList != null">

														<!-- Iterator Starts Here id="mlist" name="menuList" -->
														<s:set id="id" name="key" />
														<%--	<bean:define id="id" name="mlist" property="key" />
                                                            <html:multibox property="selectedMenus"	name="UserMasterForm" value="<%=(String) id%>" />
                                                            <bean:write name="mlist" property="value" /> --%>
													</s:if>

												</div>
											</div>
										</div>
									</div>
								</div>
								<s:hidden name="userType" id="uid" />
								<s:hidden name="loginId" id="lid" />
							</s:if>

							<s:if test='"MenuInfo".equals(path)'>
								<s:if test='"User".equals(userType)'>
									<s:iterator value="menuinfo" var="menuinfoVar" status="menuinfoStatus">
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<span data-target="#menuInfo_0<s:property value="%{#menuinfoStatus.count}"/>" data-toggle="collapse" aria-expanded="true" style="cursor: pointer" title="Click here to view the Main Menu -> Child Menus -> Buttons for <s:property value='#menuinfoVar.key'/>"><i class="more-less glyphicon glyphicon-plus"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="selectedProduct" disabled="true" fieldValue='%{#menuinfoVar.key}' value='true'/>&nbsp;&nbsp;<s:property value='#menuinfoVar.key'/> 
													</div>
													<div class="collapse" id="menuInfo_0<s:property value="%{#menuinfoStatus.count}"/>">
														<table width="100%" class="footable" id='<s:property value='#menuinfoVar.key'/>'>
														<tr align="center"><td width="20%" align="center">
														Select All &nbsp;<span class="cr" onclick="productselectAllOptions(this)" style="cursor: pointer" title="Click here to Select / Deselect Child Menus & Buttons for <s:property value='%{#menuinfoVar.key}'/>"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
														</td>
														</tr>
															<s:iterator value="#menuinfoVar.value" var="productmapVar" status="productmapStatus">
																<tr>
																	<td width="20%">
																		<s:checkbox name="selectedDepartment" disabled="true" fieldValue='%{#menuinfoVar.key+","+#productmapVar.key}' value='%{selectedDepartment.contains(#menuinfoVar.key+","+#productmapVar.key)}'/><s:property value='#productmapVar.key'/>  <span class="cr" onclick="selectAllOptions(this)" style="cursor: pointer" title="Click here to Select / Deselect Child Menus & Buttons for <s:property value='%{#menuinfoVar.key+"->"+#productmapVar.key}'/>"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
																	</td>
																	<td width="80%">
																		<table width="100%" class="footable">
																			<s:set id="deptinfo" name="productinfo" value="value" />
																			<s:iterator value="#productmapVar.value" var="productInfoVar" status="productInfoStatus">
																				<tr>
																					<td width="30%">
																						<s:checkbox name="selectedProcess" disabled="true" fieldValue='%{#productInfoVar.key.substring(0,#productInfoVar.key.indexOf("_"))}' value='%{selectedProcess.contains(#productInfoVar.key.substring(0,#productInfoVar.key.indexOf("_")))}'/><s:property value="#productInfoVar.key.substring(#productInfoVar.key.indexOf('_')+1, #productInfoVar.key.length())"/> <span class="cr" onclick="selectAllOptions(this)" style="cursor: pointer" title="Click here to Select / Deselect Child Menus & Buttons for <s:property value='%{#menuinfoVar.key+"->"+#productmapVar.key}'/>"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
																					</td>
																					<td width="70%">
																						<s:set id="lid" name="loginId" value="loginId"/>
																						<table width="100%" class="footable">
																							<s:iterator id="subMenuInfo" value="#productInfoVar.value" var="subMenuInfoVar">
																								<tr>
																									<td width="40%">
																										<s:checkbox name="selectedMainMenu" fieldValue='%{#subMenuInfoVar.key.substring(0,#subMenuInfoVar.key.indexOf("_"))}' value='%{selectedMainMenu.contains(#subMenuInfoVar.key.substring(0,#subMenuInfoVar.key.indexOf("_")))}' onclick="selectAllSubMenuButtons(this)"/><s:property value='#subMenuInfoVar.key.substring(#subMenuInfoVar.key.indexOf("_")+1, #subMenuInfoVar.key.length())'/>
																									</td>
																									<td width="40%">
																										<table width="100%" class="footable">
																											<s:iterator id="subMenuInfo" value="#subMenuInfoVar.value" var="subMenuInfoVar">
																												<tr>
																													<td width="100%">
																														<s:if test="#subMenuInfoVar.value==null">
																															&nbsp;
																														</s:if>
																														<s:if test="#subMenuInfoVar.value!=null">
																															<s:checkbox name="selectedSubMenuButton" fieldValue='%{#subMenuInfoVar.key}' value='%{selectedSubMenuButton.contains(#subMenuInfoVar.key)}' onclick="selectParentMenu(this)"/><s:property value='#subMenuInfoVar.value'/>
																														</s:if>
																													</td>
																												</tr>
																											</s:iterator>
																										</table>
																									</td>
																								</tr>
																							</s:iterator>
																						</table>
																					</td>
																				</tr>
																			</s:iterator>
																		</table>
																	</td>
																</tr>
															</s:iterator>
														</table>
													</div>
												</div>
											</div>
										</div>
									</s:iterator>
									<!-- Iterator ends Here -->
									<div class="tablerow">
										<div class="boxcontent" align="center">
											<input type="button"  value="Cancel" class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
											<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="saveGlobalSubmitNew()"/>

										</div>
									</div>
									<s:hidden name="key" />
									<s:hidden name="loginId" />
								</s:if>
								<s:if test='"Admin".equals(userType)'>
									<div class="tablerow">
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="user.menuallocation" />
												</div>
												<div class="panel-body">
													<table width="100%" class="footable">
														<s:iterator var="menuinfoVar" value="menuinfo" status="id">
															<tr>
																<td>
																	<s:checkbox name="selectedMenus" fieldValue='%{#menuinfoVar.key}' value='%{selectedMenus.contains(#menuinfoVar.key)}'/><s:property value='#menuinfoVar.value'/>
																</td>
															</tr>
														</s:iterator>
													</table>
												</div>
											</div>
										</div>
									</div>
									<div class="tablerow">
										<div class="boxcontent" align="center">
											<input type="button"  value="Cancel" class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
											<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="saveAdminMenus()"/>
										</div>
									</div>

									<s:hidden name="loginId" />
								</s:if>
							</s:if>
							<s:if test='"SubMenuInfo".equals(path)'>
								<div class="tablerow">
									<div class="boxcontent">
										<s:iterator var="subMenuInfoVar" value="subMenuInfo">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:property value='#subMenuInfoVar.key'/>
												</div>
												<div class="panel-body">
													<table width="100%" class="footable">
														<s:iterator id="productinfo" value="#subMenuInfoVar.value" var="submenuproductVar">
															<tr>
																<td width="30%">
																	<s:checkbox name="selectedDepartment" fieldValue='%{#subMenuInfoVar.key+","+#submenuproductVar.key}' value='%{selectedDepartment.contains(#subMenuInfoVar.key+","+#submenuproductVar.key)}'/><s:property value='#submenuproductVar.key'/>
																</td>
																<td width="70%">
																	<table width="100%" class="footable">
																		<s:iterator id="deptinfo" value="#submenuproductVar.value" var="deptinfoVar">
																			<tr>
																				<td width="30%">
																					<s:property value='#deptinfoVar.key'/>
																				</td>
																				<td width="70%">
																					<table width="100%" class="footable">
																						<s:iterator id="menuinfo" value="#deptinfoVar.value" var="menuinfoVar">
																							<tr>
																								<td width="50%">
																									<s:property value='#menuinfoVar.value'/>
																								</td>
																								<td width="30%">
																									<s:if test='"false".equals(lsatus)'>
																										<input type="button" class="btn btn-sm btn-info" value="Allocate" onclick="menuAllocate('submenuscreen','<s:property  value="key"/>','<s:property value="loginId"/>')"/>
																									</s:if>
																									<s:else >
																										<input type="button" class="btn btn-sm btn-info" value="Allocate" onclick="menuAllocate('submenuscreen','<s:property  value="key"/>','<s:property value="loginId"/>')"/>
																									</s:else>
																									<s:hidden name="productId"/>
																									<s:hidden name="menuId"/>
																									<s:hidden name="processId" />
																									<s:hidden name="value" />
																									<s:hidden name="processId" />
																								</td>
																							</tr>
																						</s:iterator>
																					</table>
																				</td>
																			</tr>
																		</s:iterator>
																	</table>
																</td>
															</tr>
														</s:iterator>
													</table>
												</div>
											</div>
										</s:iterator>
									</div>
								</div>
								<div class="tablerow">
									<div class="boxcontent" align="center">
										<input type="button"  value="Cancel" class="btn btn-sm btn-danger" onClick="List()" /> &nbsp;&nbsp;&nbsp;
										<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="saveGlobalSubmit('SubMenu')" />
										<s:hidden name="loginId" />
										<!--<s:hidden name="selectedDepartments" />
								--><!--<s:hidden name="uwlimitMap" />-->
									</div>
								</div>
								<s:hidden name="" />
							</s:if>
							<s:if test='"success".equals(path) || "error".equals(path)'>
								<div class="tablerow">
									<div class="boxcontent">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:if test='"menu".equals(mode)'>
													<s:text name="user.menuallocation" />
												</s:if>
												<s:if test='"SubMenu".equals(mode)'>
													<s:text name="user.submenuallocation" />
												</s:if>
											</div>
											<s:if test='"success".equals(path)'>
												<div class="panel-body">
													<s:text name="user.success" />
												</div>
											</s:if>
											<s:else>
												<div class="panel-body">
													<s:text name="user.error" />
												</div>
											</s:else>
										</div>
									</div>
								</div>
								<div class="tablerow">
									<div class="boxcontent" align="center">
										<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="List()" />
									</div>
								</div>
							</s:if>

							<s:if test='"none".equals(path)'>
								<div class="tablerow">
									<div class="boxcontent">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="user.submenuallocation" />
											</div>
											<div class="panel-body">
												<s:text name="Nothig To Display" />
											</div>
										</div>
									</div>
								</div>
								<div class="tablerow">
									<div class="boxcontent" align="center">
										<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="List()" />
									</div>
								</div>
							</s:if>
						</div>
						<s:hidden name="selectedDepartments" />
<s:hidden name="preVal" id="preVal"/>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    function menuAllocate(val,processId,loginId) {
        //var pid=prosid;
        //alert(loginId)
        var URL="<%=request.getContextPath()%>/SubMenuAllocationAdmin.do?mode="+val+"&processId="+processId+"&loginId="+loginId;
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

    function saveAdminMenus() {
//document.forms[0].action='${pageContext.request.contextPath}/globalSubmitAdmin.action';
        document.forms[0].action="<%=request.getContextPath()%>/globalSubmitAdmin.action?method=menuAllocations&mode=adminmenusave";
        document.forms[0].submit();
    }



    function getProduct(value,bvalue) {
        if (value=="-1") {
            if(bvalue=='department') {
                document.getElementById("deptDropdown").innerHTML="";
                document.getElementById("processDropdown").innerHTML="";
                document.getElementById("menuDisplay").innerHTML="";
                //document.getElementById("menuDropDown").innerHTML="";
                return;
            }
            else if(bvalue=='process') {
                document.getElementById("processDropdown").innerHTML="";
                document.getElementById("menuDisplay").innerHTML="";
                //document.getElementById("menuDropDown").innerHTML="";
                return;
            }
            else if(bvalue=='menu') {
                document.getElementById("menuDisplay").innerHTML="";
            }
        }
        var xmlHttp;
        if (window.XMLHttpRequest) { // Mozilla, Safari, ...
            var xmlHttp = new XMLHttpRequest();
        }else if (window.ActiveXObject) { // IE
            var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        var pid;
        var uid;
        var lid;

        pid=document.getElementById("pid").value;
        uid=document.getElementById("uid").value;
        if(bvalue=='menu')
        {
            lid=document.getElementById("lid").value;
        }

        xmlHttp.open('POST', "<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=getDropDownValues&BusinessMode="+bvalue+"&dvalue="+value+"&pid="+pid+"&uid="+uid+"&lid="+lid, true);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4) {
                if(bvalue=='department') {
                    document.getElementById("deptDropdown").innerHTML=xmlHttp.responseText;
                    document.getElementById("processDropdown").innerHTML="";
                    document.getElementById("menuDisplay").innerHTML="";
                    //document.getElementById("menuDropDown").innerHTML="";
                }
                else if(bvalue=='process') {
                    document.getElementById("processDropdown").innerHTML=xmlHttp.responseText;
                    document.getElementById("menuDisplay").innerHTML="";
                    //document.getElementById("menuDropDown").innerHTML="";
                }
                else if(bvalue=='menu') {
                    document.getElementById("menuDisplay").innerHTML=xmlHttp.responseText;
                }
            }
        }
        xmlHttp.send(null);
    }

    function saveMenu() {
        document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=saveAllocation&BusinessMode=menus";
        document.forms[0].submit();
    }

    function submitValues() {
        document.forms[0].action="<%=request.getContextPath()%>/userListMasterAdmin.action?method=InsertUserMaster&BusinessMode=insert";
        document.forms[0].submit();
    }

    function List() {
        document.forms[0].action='${pageContext.request.contextPath}/userListMasterAdmin.action';
        //document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=list";
        document.forms[0].submit();
    }

    function back() {
        document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=Init";
        document.forms[0].submit();
    }

    function funEditMode(val) {
        document.getElementById("product_Id").value=val;
        document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=Edit";
        document.forms[0].submit();
    }

    function dispalyRights() {
        document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=DisplayRights";
        document.forms[0].submit();
    }

    function saverights() {
        document.forms[0].action='${pageContext.request.contextPath}/insertUserMasterAdmin.action?';
        //document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=SaveRights";
        document.forms[0].submit();
    }

    function changeRights(val) {
        if(val!='0') {
            document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=ChangeRights";
            document.forms[0].submit();
        }
        else if(val=='0') {
            document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=DisplayRights";
            document.forms[0].submit();
        }
    }

    function ViewRights() {
        document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=ViewRights";
        document.forms[0].submit();
    }

    function MenuDisp() {
        document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=DisplayMenus&entry=0"
        document.forms[0].submit();
    }

    function updateMainMenu() {
        document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=DisplayMenus&entry=3"
        document.forms[0].submit();
    }

    function MenuDispHome() {
        document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=DisplayMenus&entry=1";
        document.forms[0].submit();
    }

    function LoginEdit(val) {
        document.forms[0].lid.value=val;
        document.forms[0].action='${pageContext.request.contextPath}/insertUserMasterAdmin.action?mode=LoginEdit';
        //document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=LoginEdit";
        document.forms[0].submit();
    }
    function ChangePassword(val) {
        //document.forms[0].lid.value=val;
        document.forms[0].action='${pageContext.request.contextPath}/insertUserMasterAdmin.action?mode=PasswordDisp&sno=1&loginId='+val;
        //document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=LoginEdit";
        document.forms[0].submit();
    }

    function MenuAllocation(login,usertype) {
        document.forms[0].action='${pageContext.request.contextPath}/MenuAllocationAdmin.action?mode=Menu&loginId='+login+'&userType='+usertype;
        document.forms[0].submit();
    }
    function SubMenuAllocation(login,usertype) {
        document.forms[0].action='${pageContext.request.contextPath}/SubMenuAllocationAdmin.action?mode=SubMenu&loginId='+login+'&userType='+usertype;
        document.forms[0].submit();
    }

    function updateUser() {
        document.forms[0].action='${pageContext.request.contextPath}/insertUserMasterAdmin.action?mode=LoginUpdate';
        //document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=LoginUpdate";
        document.forms[0].submit();
    }

    function LoginUserHome() {
        document.forms[0].action='${pageContext.request.contextPath}/addUserListAdmin.action';
        //document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=LoginUserHome";
        document.forms[0].submit();
    }

    function changePass() {
        document.forms[0].action='${pageContext.request.contextPath}/insertUserMasterAdmin.action?mode=PasswordChange';
        //document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=PasswordChange";
        document.forms[0].submit();
    }
    function saveGlobalSubmit(val) {
        //'menu'
        document.forms[0].action='${pageContext.request.contextPath}/globalSubmitAdmin.action?mode='+val;
        //document.forms[0].action="<%=request.getContextPath()%>/globalSubmitAdmin.action?method=globalSubmit&BusinessMode="+value;
        document.forms[0].submit();
    }
    function saveGlobalSubmitNew() {
        //'menu'
        document.forms[0].action='${pageContext.request.contextPath}/globalSubmitNewAdmin.action';
        //document.forms[0].action="<%=request.getContextPath()%>/globalSubmitAdmin.action?method=globalSubmit&BusinessMode="+value;
        document.forms[0].submit();
    }
    function menuDispCancel(val) {
        document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=DisplayMenus&sno=1&entry=1&loginId="+val;
        document.forms[0].submit();
    }

    function saveSingleLimit() {
        document.forms[0].action='${pageContext.request.contextPath}/insertUserMasterAdmin.action';
        //document.forms[0].action="<%=request.getContextPath()%>/UserMasterDispatchAction.do?method=InsertUserMaster&BusinessMode=saveSingleLimit";
        document.forms[0].submit();
    }

    function selectAllOptions(obj){
        var checkBoxes = $(obj).closest('td').next('td').find('td input:checkbox');
        checkBoxes.prop("checked", !checkBoxes.prop("checked"));
    }

    function selectAllSubMenuButtons(obj) {
        var checkBoxes = $(obj).closest('td').next('td').find('td input:checkbox');
        checkBoxes.prop("checked", !checkBoxes.prop("checked"));
    }

    function selectParentMenu(obj) {
        var checkBoxes = $(obj).parent().parent().parent().parent().parent().prev().find('input:checkbox');
        checkBoxes.prop("checked", "checked");
    }
     function productselectAllOptions(obj){
    	 var checkBoxes = $(obj).closest('table').find('table input:checkbox');
        checkBoxes.prop("checked", !checkBoxes.prop("checked"));
    }
     $(document).ready(function() { 
    	    $('#attachedUW').multiselect({ 
    	      	includeSelectAllOption: false,
    	        enableFiltering:true,
    	        numberDisplayed: 0,
    	        enableCaseInsensitiveFiltering: true,
    	        onChange: function(element, checked) {
    	          var val = $('#attachedUW').val();
    	          var val1 =document.getElementById("preVal").value;
    	          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
    	          $("#attachedUW").multiselect('clearSelection');
    	          val = removeElementsWithValue(val, 'ALL');
    	          $("#attachedUW").val(val);
    	           $("#attachedUW").multiselect("refresh");
    	           document.getElementById("preVal").value = '';
    	          }
    	          else if (val !=null && val[0]=='ALL' ) {
    	          	$("#attachedUW").multiselect('clearSelection');
    	          	$("#attachedUW").val('ALL');
    	          	 $("#attachedUW").multiselect("refresh");
    	          	 document.getElementById("preVal").value = 'ALL';
    	          }
    	          else if(val== null || val[0]==''){
    	          $("#attachedUW").multiselect('clearSelection');
    	          $("#attachedUW").multiselect("refresh");
    	          document.getElementById("preVal").value = '';
    	          }
    	      	}                     
    	    }); 
    	    
    	     <s:if test='attachedUW!=null && !"".equals(attachedUW)'>	
    	 		var uwgrade='<s:property value="attachedUW"/>';
    			 var data=uwgrade.replace(/ /g,'');	
    		   	 var dataArray=data.split(",");   	 
    		   	$("#attachedUW").val(dataArray);
    			 $("#attachedUW").multiselect("refresh");
    		</s:if>    
    	          
    	});
</script>
</body>
</html>
