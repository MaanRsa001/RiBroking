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
										<s:text name="branch.branchMaster"></s:text>
									</div>
								</div>
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
								<s:if test='"branchlist".equals(mode)'>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent" align="right">
										<s:submit type="button" action="editBranchAdmin" value="Add New" cssClass="btn2 rEdge" />
									</div>					 				
									<div class="boxcontent">
										<table width="100%" class="footable">
											<thead>
												<tr>
													<th>Branch Code</th>
													<th>Branch Name</th>									
													<th>Status</th>
													<th>Edit</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>AMRELI</td>
													<td>MO</td>													
													<td>Active</td>
													<td>
														<s:submit type="button" action="editBranchAdmin" value="Edit" cssClass="btn2 rEdge" />
													</td>													
												</tr>
											</tbody>
										</table>
									</div>							
								</div>
								</s:if>
								<s:if test='"editbranch".equals(mode)'>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent">
										<table width="50%" style="margin: 0 auto;">
										<tr>
											<td width="50%"><s:text name="branch.branchCode" /> <font color="red">*</font></td>
											<td width="50%"><s:textfield name="" cssClass="inputBox" /></td>
										</tr>
										<tr>
											<td width="50%"><s:text name="branch.branchName" /> <font color="red">*</font></td>
											<td width="50%"><s:textfield name="" cssClass="inputBox" /></td>
										</tr>										
										<tr>
											<td width="50%"><s:text name="branch.status" /> <font color="red">*</font></td>
											<td width="50%"><s:radio list="#{'Y':'Active','N':'Deactivate'}" name="status" value='%{status==null?"N":status}' /></td>
										</tr>
										</table>										
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
