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
										<s:text name="menu.menuMaster"></s:text>
									</div>
								</div>
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
								<s:if test='"menulist".equals(mode)'>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent" align="right">
										<s:submit type="button" action="editMenuAdmin" value="Add New" cssClass="btn2 rEdge" />
									</div>									
									<div class="boxcontent">
										<table width="100%" class="table table-bordered">
											<thead>
												<tr>
													<th>Menu Name</th>
													<th>User Type</th>
													<th>Parent Menu</th>
													<th>Remark</th>
													<th>Status</th>
													<th>Edit</th>																										
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>Upload</td>
													<td>Upload User</td>
													<td>Head</td>
													<td>File Upoload User</td>
													<td>Y</td>
													<td>
														<s:submit type="button" action="editMenuAdmin" value="Edit" cssClass="btn2 rEdge" />
													</td>													
												</tr>
											</tbody>
										</table>
									</div>
									<!-- 
									<div class="boxcontent">
										<display:table name="" pagesize="10" htmlId="distable"
											requestURI="" class="table table-bordered" uid="row" id="record">		
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
								<s:if test='"editmenu".equals(mode)'>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent">
										<div class="textfield33">
											<div class="text">
												<s:text name="menu.menuName" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="menu.menuLink" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="menu.userType" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:select cssClass="inputSelect" list="#{}" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="menu.parentMenu" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:select cssClass="inputSelect" list="#{}" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="menu.orderBy" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="menu.startDate" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<div class="inputAppend">
													<sj:datepicker id="" name="" cssClass="inputBox1" cssStyle="border: none;" displayFormat="dd/mm/yy" readonly="true" showAnim="slideDown" duration="fast" disabled="#disable2" />
												</div>
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="menu.endDate" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<div class="inputAppend">
													<sj:datepicker id="" name="" cssClass="inputBox1" cssStyle="border: none;" displayFormat="dd/mm/yy" readonly="true" showAnim="slideDown" duration="fast" disabled="#disable2" />
												</div>
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
		</script>
	</body>
</html>
