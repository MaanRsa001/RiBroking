<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script language="JavaScript">
				function stopRKey(evt) { 
				  var evt = (evt) ? evt : ((event) ? event : null); 
				  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
				} 
				document.onkeypress = stopRKey; 
		</script>
	</head>

	<body>
		<div class="table0" style="width: 90%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
					<div class="tablerow">
						<div style="padding:10px; background:#F8F8F8">
							<s:form id="" name="" method="post" action="" theme="simple">
							<div class="table2">								
								<div class="tablerow">
									<div class="heading">
										<s:text name="user.userMaster"></s:text>
									</div>
								</div>
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
								<s:if test='"userlist".equals(mode)'>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent" align="right">
										<s:submit type="button" action="editUserAdmin" value="Add New" cssClass="btn2 rEdge" />
									</div>									
									<div class="boxcontent">
										<table width="100%" class="footable">
											<thead>
												<tr>
													<th>Login ID</th>
													<th>User Name</th>
													<th>User Type</th>
													<th>Status</th>
													<th>Edit</th>																																																				
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>admin</td>
													<td>Administrator</td>
													<td>Admin</td>
													<td>Active</td>													
													<td>
														<s:submit type="button" action="editUserAdmin" value="Edit" cssClass="btn2 rEdge" />
													</td>													
												</tr>
											</tbody>
										</table>
									</div>
									<!-- 
									<div class="boxcontent">
										<display:table name="" pagesize="10" htmlId="distable"
											requestURI="" class="footable" uid="row" id="record">		
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
									   		<display:setProperty name="paging.banner.placement" value="bottom" /> 
											<display:setProperty name="paging.banner.onepage" value="" />																						
											<display:column style="text-align:center;" sortable="true" title="Transaction No" property="" class="formtxtc" />			
											<display:column style="text-align:center;" sortable="true" title="Upload Date" class="formtxtc" />
											<display:column sortable="true" style="text-align:center;" title="Valid Records" property="" class="formtxtc"/>
											<display:column sortable="true" style="text-align:center;" title="Invalid Records" property="" class="formtxtc"/>
											<display:column sortable="true" style="text-align:center;" title="Duplicates" property="" class="formtxtc"/>
											<display:column sortable="true" style="text-align:center;" title="PYMT Type Records" property="" class="formtxtc"/>
											<display:column sortable="true" style="text-align:center;" title="Reversal Records" property="" class="formtxtc"/>
											<display:column sortable="true" style="text-align:center;" title="Cheque No. Exists" property="" class="formtxtc"/>											
											<display:column sortable="true" style="text-align:center;" title="Cheque No. Not Exists" property="" class="formtxtc"/>
											<display:column sortable="true" style="text-align:center;" title="Matched" class="" />
											<display:column sortable="true" style="text-align:center;" title="Pending" property="" class="formtxtc"/>											
										</display:table>
									</div>
									 -->									
								</div>
								</s:if>
								<s:if test='"edituser".equals(mode)'>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent">
										<div class="textfield33">
											<div class="text">
												<s:text name="user.userName" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="user.loginID" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="user.password" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:password cssClass="inputBox" />												
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="user.rePassword" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:password cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="user.userType" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:select cssClass="inputSelect" list="#{}" />
											</div> 
										</div>										
										<div class="textfield33">
											<div class="text">
												<s:text name="menu.remarks" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="menu.status" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:radio list="#{'Y':'Active','N':'Deactivate'}" name="status" value='%{status==null?"N":status}' />
											</div> 
										</div>
										<div style="width: 33%; float: left;">
											<span class="text"><s:text name="user.allocatedMenus" /></span><font color="red">*</font><br/>
											<div style="float:left; width:85%;">
												<s:textarea cssClass="inputBoxA" cssStyle="width:100%; color:#777;" rows="3" readonly="true" />										
											</div>	     	
											<div style="float:left; margin-left:10px;">
												<input type="button" name="popUpBtn" class="btn2 rEdge" style="cursor:pointer;float:left;" value="..." onclick="menuSelection();" />
											</div>
											<br class="clear"/>
								    	</div>
										<br class="clear"/>
									</div>
									<div class="boxcontent" align="center" style="margin-top: 10px;">
										<s:submit cssClass="btn2 rEdge" value="Submit" /> &nbsp;&nbsp;&nbsp;
										<s:submit cssClass="btn2 rEdge" value="Cancel" />
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
			function funsubmit() {
				document.getElementById("uploadProgress").style.display = "block";
			}
			
			function menuSelection(){
			  
				var URL='${pageContext.request.contextPath}/getMenuSelectionAdmin.do';  
		
				var windowName = "Selection";
				var width  = screen.availWidth;
				var height = screen.availHeight;
				var w = 720;
				var h = 550;
				var features =
					'width='          + w +
					',height='		  + h +
					',left='		  + ((width - w - 0) * .4)  +
					',top='			  + ((height - h - 0) * .4) +
					',directories=no' +
					',location=no'	  +
					',menubar=no'	  +
					',scrollbars=yes' +
					',status=yes'	  +
					',toolbar=no'	  +
					',resizable=false';
				var strOpen = window.open (URL, windowName, features);
				strOpen.focus();
			}
			
		</script>
	</body>
</html>
