<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
										<s:text name="bank.bankMaster"></s:text>
									</div>
								</div>
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
								<s:if test='"banklist".equals(mode)'>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent" align="right">
										<s:submit type="button" action="editBankAdmin" value="Add New" cssClass="btn2 rEdge" />
									</div>					 				
									<div class="boxcontent">
										<table width="100%" class="footable">
											<thead>
												<tr>
													<th>Bank ID</th>
													<th>Bank Name</th>
													<th>Table name</th>
													<th>Cheque No. Field</th>
													<th>Cheque Amount Field</th>																																																				
													<th>Credit / Debit Field</th>
													<th>Return Reason Field</th>
													<th>Receipt No. Field</th>
													<th>Status</th>
													<th>Edit</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>AXB</td>
													<td>AXIS BANK</td>
													<td>AXIS_BANK</td>
													<td>INST_NO</td>
													<td>INSTRUMENT_AMOUNT</td>													
													<td>TYPE</td>
													<td>RETURN_REASON</td>
													<td>SLIP_NO</td>
													<td>Yes</td>
													<td>
														<s:submit type="button" action="editBankAdmin" value="Edit" cssClass="btn2 rEdge" />
													</td>													
												</tr>
											</tbody>
										</table>
									</div>							
								</div>
								</s:if>
								<s:if test='"editbank".equals(mode)'>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent">
										<div class="textfield33">
											<div class="text">
												<s:text name="bank.bankId" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="bank.bankName" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="bank.tableName" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />										
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="bank.chequeNoField" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="bank.chequeAmountField" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>										
										<div class="textfield33">
											<div class="text">
												<s:text name="bank.creditDebitField" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="bank.returnReasonField" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield cssClass="inputBox" />
											</div> 
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="bank.receiptNoField" /> <font color="red">*</font>
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
