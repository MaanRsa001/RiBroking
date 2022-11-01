<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style type="text/css">
		.btn-group {
		width: 100%;
		}
		.btn-group .btn {
		width: 90%;
		padding: 3px 12px;
		}
	</style>
<script type="text/javascript">
	 $(function() {
		$( "#updateDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#signedLineValidity" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#writtenvaliditydate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#statementDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });	
	  </script>		
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
			<s:set var="ebouquetExistingList" value='%{bouquetExistingList}'/>
				<div style="padding:10px; background:#F8F8F8">
				<s:form id="placement" name="placement" theme="simple" action=""	method="post" autocomplete="off">					
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
						<div class="tablerow">
							<s:hidden name="nr" value="0" />
							<div class="boxcontent">
								<div class="panel panel-primary">
								<div class="panel-heading" align="center">
										Placement Update
									</div>
									<div class="panel-body">
										<div class="boxcontent" >										
										<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.offerinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<%-- <div class="text txtB">
																<s:text name="label.emailBy" />
															</div> --%>
															<div class="tbox">
																<s:radio list="emailByList" listKey="TYPE" listValue="DETAIL_NAME"  name="emailBy"  id="emailBy" value="emailBy==null || emailBy==''?'1':emailBy" />												
															</div>
														</div>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="label.updateDate" />
															</div> 
															<div class="tbox">
																<s:textfield name="updateDate" id="updateDate" cssClass="inputBox" />												
															</div>
														</div>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="label.currentStatus" />
															</div> 
															<div class="tbox">
																<s:select list="statusList" listKey="STATUS_CODE" listValue="STATUS_NAME" name="currentStatus" id="currentStatus" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  disabled="true"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="label.newStatus" />
															</div> 
															<div class="tbox">
																<s:select list="subStatusList" listKey="SUB_STATUS_CODE" listValue="SUB_STATUS_NAME" name="newStatus" id="newStatus" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  onchange="getStatuschange(this.value);"/>
															</div>
														</div>
														<div class="boxcontent">
															<div class="textfieldA25">
																<s:text name="label.cedentCorrespondent" />
															</div>
															<div class="textfieldA75">
																<s:textarea name="cedentCorrespondent" id="cedentCorrespondent" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;" />
																<br/>
																<%-- <span class="textAreaRemaining"><label id="exclusion_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span> --%>
															</div>
															<br class="clear"/>
														</div>
														<div class="boxcontent">
															<div class="textfieldA25">
																<s:text name="label.reinsurerCorrespondent" />
															</div>
															<div class="textfieldA75">
																<s:textarea name="reinsurerCorrespondent" id="reinsurerCorrespondent" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;" />
																<br/>
																<%-- <span class="textAreaRemaining"><label id="exclusion_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span> --%>
															</div>
															<br class="clear"/>
														</div>
														<div class="boxcontent">
															<div class="textfieldA25">
																<s:text name="label.tqrCorrespondent" />
															</div>
															<div class="textfieldA75">
																<s:textarea name="tqrCorrespondent" id="tqrCorrespondent" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;" />
																<br/>
																<%-- <span class="textAreaRemaining"><label id="exclusion_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span> --%>
															</div>
															<br class="clear"/>
														</div>
														<br class="clear"/>
													</div>
												</div>
										</div>
									
									
									
									</div>
									<div class="boxcontent" >										
										<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.offerinfo" />
												</div>
												<div class="panel-body">
													<div id="statusChange">
													<div  class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;overflow-y: visible;">
																<table width="100%" class="table table-bordered" >
																	<thead>
																	<tr>
																		<th width="3%"> <s:text name="label.sno" /> </th>
																		<th width="10%"><s:text name="label.proposalNo" /></th>
																		<th width="10%"><s:text name="label.cedingCompany" /></th>
																		<th width="10%"><s:text name="label.reinsureName" /></th>
																		<th width="10%"><s:text name="label.placingBroker" /></th>
																		<th width="10%"><s:text name="label.shareOffer" /></th>
																		<s:if test='"A".equals(newStatus) || "RO".equals(newStatus) || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<th width="10%"><s:text name="label.written" /></th>
																		</s:if>
																		<s:if test='"A".equals(newStatus)'>
																		<th width="10%"><s:text name="label.writtenvaliditydate" /></th>
																		<th width="10%"><s:text name="label.writtenvalidityRemarks" /></th>
																		</s:if>
																		<s:if test='"RO".equals(newStatus) || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<th width="10%"><s:text name="label.proposedWL" /></th>
																		</s:if>
																		<s:if test='"RO".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<th width="10%"><s:text name="label.signedLine" /></th>
																		</s:if>
																		<s:if test='"SL".equals(newStatus)'>
																		<th width="10%"><s:text name="label.signedLineValidity" /></th>
																		<th width="10%"><s:text name="label.signedLineRemarks" /></th>
																		</s:if>
																		<s:if test='"RO".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<th width="10%"><s:text name="label.proposedSL" /></th>
																		</s:if>
																		<s:if test='"RO".equals(newStatus)'>
																		<th width="10%"><s:text name="label.reoffer" /></th>
																		</s:if>
																		<s:if test='"PWL".equals(newStatus)  || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<th width="10%"><s:text name="label.tqrBrokerageAmt" /></th>
																		</s:if>
																		<s:if test='"A".equals(newStatus)  || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<th width="5%"><s:text name="label.brokerage" /></th>
																		</s:if>
																		<s:if test='"P".equals(newStatus) || "D".equals(newStatus) || "NPWL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<th width="5%"><s:text name="label.emailStatus" /></th>
																		<th width="5%"><s:text name="label.previewsendmail" /></th>
																		</s:if>
																	</tr>
																	</thead>
																	<tbody>	
																	<s:iterator value="placementeditInfo" var="list"  status="stat">									
																	<tr>
																		<td>
																			<s:property value="%{#stat.count}"/>
																		</td>
																		<td>
																			<s:property value="#list.PROPOSAL_NO"/>
																			<s:hidden name="proposalNos[%{#stat.count-1}]"/>
																			<s:hidden name="reinsurerIds[%{#stat.count-1}]"/>
																			<s:hidden name="brokerIds[%{#stat.count-1}]"/>
																		</td>
																		<td>
																			<s:property value="#list.CEDING_COMPANY_ID"/>
																		</td>
																		<td>
																			<s:property value="#list.REINSURER_NAME"/>
																		</td>
																		<td>
																			<s:property value="#list.BROKER_NAME"/>
																		</td>
																		<td>
																			<s:textfield name="shareOffered[%{#stat.count-1}]" id="shareOffered[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		<s:if test='"A".equals(newStatus) || "RO".equals(newStatus) || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<td>
																			<s:textfield name="writtenLine[%{#stat.count-1}]" id="writtenLine[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		</s:if>
																		<s:else>
																		<s:hidden name="writtenLine[%{#stat.count-1}]"/>
																		</s:else>
																		<s:if test='"A".equals(newStatus)'>
																		<td>
																			<s:textfield name="writtenvaliditydate[%{#stat.count-1}]" id="writtenvaliditydate[%{#stat.count-1}]" cssClass="inputBox" />
																		</td>
																		<td>
																			<s:textfield name="writtenvalidityRemarks[%{#stat.count-1}]" id="writtenvalidityRemarks[%{#stat.count-1}]" cssClass="inputBox" />
																		</td>
																		</s:if>
																		<s:else>
																		<s:hidden name="writtenvaliditydate[%{#stat.count-1}]"/>
																		<s:hidden name="writtenvalidityRemarks[%{#stat.count-1}]"/>
																		</s:else>
																		<s:if test='"RO".equals(newStatus) || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<td>
																			<s:textfield name="proposedWL[%{#stat.count-1}]" id="proposedWL[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		</s:if>
																		<s:else>
																		<s:hidden name="proposedWL[%{#stat.count-1}]"/>
																		</s:else>
																		<s:if test='"RO".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<td>
																			<s:textfield name="signedLine[%{#stat.count-1}]" id="signedLine[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		</s:if>
																		<s:else>
																		<s:hidden name="signedLine[%{#stat.count-1}]"/>
																		</s:else>
																		<s:if test='"SL".equals(newStatus)'>
																		<td>
																			<s:textfield name="signedLineValidity[%{#stat.count-1}]" id="signedLineValidity[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		<td>
																			<s:textfield name="signedLineRemarks[%{#stat.count-1}]" id="signedLineRemarks[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		</s:if>
																		<s:else>
																		<s:hidden name="signedLineValidity[%{#stat.count-1}]"/>
																		<s:hidden name="signedLineRemarks[%{#stat.count-1}]"/>
																		</s:else>
																		<s:if test='"RO".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<td>
																			<s:textfield name="proposedSL[%{#stat.count-1}]" id="proposedSL[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		</s:if>
																		<s:else>
																		<s:hidden name="proposedSL[%{#stat.count-1}]"/>
																		</s:else>
																		<s:if test='"RO".equals(newStatus)'>
																		<td>
																			<s:textfield name="reoffer[%{#stat.count-1}]" id="reoffer[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		</s:if>
																		<s:else>
																		<s:hidden name="reoffer[%{#stat.count-1}]"/>
																		</s:else>
																		<s:if test='"PWL".equals(newStatus)  || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<td>
																			<s:textfield name="tqrBrokerageAmt[%{#stat.count-1}]" id="tqrBrokerageAmt[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		</s:if>
																		<s:else>
																		<s:hidden name="tqrBrokerageAmt[%{#stat.count-1}]"/>
																		</s:else>
																		<s:if test='"A".equals(newStatus)  || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<td>
																			<s:textfield name="brokerage[%{#stat.count-1}]" id="brokerage[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"/>
																		</td>
																		</s:if>
																		<s:else>
																		<s:hidden name="brokerage[%{#stat.count-1}]"/>
																		</s:else>
																		<s:if test='"P".equals(newStatus) || "D".equals(newStatus) || "NPWL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
																		<td>
																			<s:property value="#list.MAIL_STATUS"/>
																		</td>
																		<td>
																			<input type="button" value="Update" class="btn btn-sm btn-info"   onclick="FnUpdate('<s:property value="%{#stat.count-1}"/>')" />
																		</td>
																		</s:if>
																	</tr>												
																	</s:iterator>
																	</tbody>
																</table>											
															</div> 
													</div>
													<%-- <div class="boxcontent">
														<div class="textfield" style="display:table;">
															<div class="text txtB">
																<s:text name="label.proposalNo" />
															</div>
															<div class="tbox">
																<s:property value="proposalNo"/>
															</div>
														</div>
														<div class="textfield" style="display:table;">
																<div class="text txtB">
																	<s:text name="label.cedingCompany" />
																</div>
																<div class="tbox">
																	<s:property value="cedingCompany"/>
																</div>
															</div>
														<div class="textfield" style="display:table;">
																<div class="text txtB">
																	<s:text name="label.reinsureName" />
																</div>
																<div class="tbox">
																	<s:property value="cedingCompany"/>
																</div>
															</div>
														<div class="textfield" style="display:table;">
																<div class="text txtB">
																	<s:text name="label.treatyNameType" />
																</div>
																<div class="tbox">
																	<s:property value="treatyName"/>
																</div>
															</div>
														<div class="textfield" id="shareOfferid">
															<div class="text txtB">
																<s:text name="label.shareOffer" />
															</div> 
															<div class="tbox">
																<s:textfield name="shareOffered" id="shareOffered" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield" id="writtenLineid">
															<div class="text txtB">
																<s:text name="label.written" />
															</div> 
															<div class="tbox">
																<s:textfield name="writtenLine" id="writtenLine" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield" id="brokerageid">
															<div class="text txtB">
																<s:text name="label.brokerage" />
															</div> 
															<div class="tbox">
																<s:textfield name="brokerage" id="brokerage" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield"  id="writtendateid">
															<div class="text txtB">
																<s:text name="label.writtenvaliditydate" />
															</div> 
															<div class="tbox">
																<s:textfield name="writtenvaliditydate" id="writtenvaliditydate" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield"  id="writtenremid">
															<div class="text txtB">
																<s:text name="label.writtenvalidityRemarks" />
															</div> 
															<div class="tbox">
																<s:textfield name="writtenvalidityRemarks" id="writtenvalidityRemarks" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield"  id="proposedWLid">
															<div class="text txtB">
																<s:text name="label.proposedWL" />
															</div> 
															<div class="tbox">
																<s:textfield name="proposedWL" id="proposedWL" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield" id="signedLineid">
															<div class="text txtB">
																<s:text name="label.signedLine" />
															</div> 
															<div class="tbox">
																<s:textfield name="signedLine" id="signedLine" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield" id="proposedSLid">
															<div class="text txtB">
																<s:text name="label.proposedSL" />
															</div> 
															<div class="tbox">
																<s:textfield name="proposedSL" id="proposedSL" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield" id="reofferid">
															<div class="text txtB">
																<s:text name="label.reoffer" />
															</div> 
															<div class="tbox">
																<s:textfield name="reoffer" id="reoffer" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield" id="tqrBrokerageAmtid">
															<div class="text txtB">
																<s:text name="label.tqrBrokerageAmt" />
															</div> 
															<div class="tbox">
																<s:textfield name="tqrBrokerageAmt" id="tqrBrokerageAmt" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														<div class="textfield" id="signedLineValidityid">
															<div class="text txtB">
																<s:text name="label.signedLineValidity" />
															</div> 
															<div class="tbox">
																<s:textfield name="signedLineValidity" id="signedLineValidity" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														
														<div class="textfield" id="signedLineRemarksid">
															<div class="text txtB">
																<s:text name="label.signedLineRemarks" />
															</div> 
															<div class="tbox">
																<s:textfield name="signedLineRemarks" id="signedLineRemarks" cssClass="inputBox" cssStyle="text-align: right;"/>
															</div>
														</div>
														
														<div class="textfield" id="emailStatusid">
															<div class="text txtB">
																<s:text name="label.emailStatus" />
															</div> 
															<div class="tbox">
																<s:property value="emailStatus"/>
															</div>
														</div>
														<div class="textfield" id="previewsendmailid">
															<div class="text txtB">
																<s:text name="label.previewsendmail" />
															</div> 
															<div class="tbox">
																<input type="button" value="Update" class="btn btn-sm btn-info"   onclick="FnUpdate('<s:property value="%{#stat.count-1}"/>')" />
															</div>
														</div>
															<br class="clear"/>
													</div> --%>
												</div>
										</div>
									
									
									
									</div>
									<br class="clear"/>									
									
									
								</div>
							</div>
							
								</div>
							
													
						</div>						
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							
							
								<input type="button"  value="Back"  class="btn btn-sm btn-danger"  onclick="FnBack()" />
								<input type="button"  value="Submit"  class="btn btn-sm btn-success"  onclick="FnSumbit()" />											
							</div>
						</div>	
						
						<s:hidden name="size" />
						<s:hidden name="proposalNo" id="proposalNo"></s:hidden>
						<s:hidden name="eproposalNo" id="eproposalNo"></s:hidden>
						<s:hidden name="reinsurerId" id="reinsurerId"></s:hidden>
						<s:hidden name="brokerId" id="brokerId"></s:hidden>
						
					</div>	
					<div id="premiumSubmit">
					</div>									
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('.select1').select2({ });

function FnBack(){
	document.placement.action='${pageContext.request.contextPath}/summaryPlacement.action';
	document.placement.submit();
}

function FnSumbit(){
	document.placement.currentStatus.disabled=false;
	document.placement.action='${pageContext.request.contextPath}/updateStatusPlacement.action';
	document.placement.submit();
}
<s:if test='newStatus!=null && !"".equals(newStatus)'>
getStatuschange('<s:property value="newStatus"/>')
</s:if>
function getStatuschange(val){
	postFormRequest("${pageContext.request.contextPath}/getStatusChangePlacement.action?dropDown=statusChange", "statusChange", "placement");
	
}
</script>		
</body>
</html>
