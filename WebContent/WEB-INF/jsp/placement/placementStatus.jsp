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
		$( ".signedLineValidity" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( ".writtenvaliditydate" ).datepicker({
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
				<s:form id="placement" name="placement" theme="simple" action=""	method="post" autocomplete="off" enctype="multipart/form-data">					
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
												
												<div class="boxcontent">
											<table class="table table-bordered" id="lcDoctable">
												<thead>
												<tr>
													<th width="5%">
														<s:text name="upload.docId" />
													</th>
													<th width="20%">
														<s:text name="upload.docType" />
													</th>
													<th width="45%">
														<s:text name="upload.docDesc" />
													</th>
													<th width="30%">
														<s:text name="upload.selectdoc" />
													</th>
													<th width="30%">
														<s:text name="Delete" />
													</th>
												</tr>
												</thead>
												<tbody>
												<s:iterator value="docuList" id="index" status="stat">
												<tr>
													<td class="formCon" style="text-align: center;">${index+1}
												 		<s:hidden name="docId[%{index}]" id="vdocId" value="%{#index+1}"/>
												 	</td>
												 	<s:if test='!"null".equals(docTypeList)'>
													<td class="formCon"  valign="top">
														<s:select name="docTypeId[%{index}]" list="docTypeList" listKey="DOC_TYPE" listValue="DOC_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
													</td>
													</s:if>
													<s:else>
													<td class="formCon"  valign="top">
														<s:select name="docTypeId[%{index}]" list="#{}"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
													</td>
													</s:else>
													<td >
														<s:textarea name="docDesc[%{index}]"  rows="2" cols="70" />												 
													</td>													
													<td class="formCon">
														<s:file name="upload"  cssClass="inputBox" id="upload"/>
													</td>
													<td class="formCon">
														<s:if test='0!=(#stat.count-1)'>
																<input type="button" value="Delete" class="btn btn-sm btn-danger"   onclick="deleteUpload('<s:property value="%{#stat.count-1}"/>')" />
														</s:if>
														
													</td>
												</tr>
												</s:iterator>
												</tbody>
											</table>											
										</div>
										<div class="boxcontent" align="center">
											<input type="button"  value="Add More" class="btn btn-sm btn-info" onClick="addMorelc();" />
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
																			<s:textfield name="writtenvaliditydate[%{#stat.count-1}]" id="writtenvaliditydate[%{#stat.count-1}]" cssClass="inputBox writtenvaliditydate" />
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
																			<s:textfield name="signedLineValidity[%{#stat.count-1}]" id="signedLineValidity[%{#stat.count-1}]" cssClass="inputBox signedLineValidity" cssStyle="text-align: right;"/>
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
						<s:hidden name="searchType" id="searchType"></s:hidden>
						<s:hidden name="bouquetNo" id="bouquetNo"></s:hidden>
						<s:hidden name="baseProposalNo" id="baseProposalNo"></s:hidden>
						<s:hidden name="searchReinsurerId" id="searchReinsurerId"></s:hidden>
						<s:hidden name="searchStatus" id="searchStatus"></s:hidden>
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
function addMorelc(){
		var table = document.getElementById('lcDoctable');
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);

		var cell1 = row.insertCell(0)
		var element1 = document.createElement("p");
		element1.innerHTML = rowCount;
   		cell1.appendChild(element1);
   		cell1.setAttribute("align","center");
		
		cell = row.insertCell(1);
		element = document.createElement("select");
		element.name = "docTypeId["+(rowCount-1)+"]";
		element.id = "docTypeId["+(rowCount-1)+"]";
 	element.className = "form-control inputBoxS";
 	
		var objOption = document.createElement("option");
    objOption.text = '---Select---';
    objOption.value = '';
    if(document.all && !window.opera){
    	element.add(objOption);
    }else{
    	element.add(objOption, null);
    }
   	<s:iterator value='docTypeList'>
			var objOption = document.createElement("option");
			objOption.text = "<s:property value='DOC_TYPE' />".replace("&amp;", "&");
			objOption.value = "<s:property value='DOC_NAME' />";
			if(document.all && !window.opera){
				element.add(objOption);
				
				
			}else{
				element.add(objOption, null);
			}
	</s:iterator>
		cell.appendChild(element);
		
		cell = row.insertCell(2);
		var element = document.createElement("textarea");
		element.name = "docDesc["+(rowCount-1)+"]";
		element.id = "docDesc["+(rowCount-1)+"]";
		element.row="2";
		element.cols="70";
		cell.appendChild(element);
		
		cell = row.insertCell(3);
		var element = document.createElement("input");
		element.className = "inputBox";
		element.name = "upload";
		element.id = "upload";
		element.type = "file";
		cell.appendChild(element);
		
		
		
		cell = row.insertCell(4);
		var element = document.createElement("input");
		element.type = "checkbox";
		element.id = "chk"+rowCount;
		element.align = "right";
		element.onclick = function () {deleteRow(this.id,this)};	
	    cell.appendChild(element);
}
function deleteRow(id, el) {
var decision = confirm("Row will be deleted. Do You Want to continue? ","");{
if (decision==true){
	while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
		el = el.parentNode;
	}
	if (el.parentNode && el.parentNode.rows.length > 1) {
		el.parentNode.removeChild(el);
	}
} else {
	document.getElementById(id).checked=false;
}	   
}
}
</script>		
</body>
</html>
