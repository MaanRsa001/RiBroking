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
		$( "#transaction" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#amendmentDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#inception_Date" ).datepicker({
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
			<s:set var="eplacementInfoList" value='%{placementInfoList}'/>
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
										Placement - View
									</div>
									<div class="panel-body">
									<s:if test='"viewlist".equals(mode)'>
										<div class="boxcontent" >
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.placementinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
															<div>
																<table width="100%" class="table table-bordered" >
																	<thead>
																	<tr>
																		<th width="5%"> <s:text name="label.sno" /> </th>
																		<th width="8%"><s:text name="label.offerNo" /></th>
																		<th width="8%"><s:text name="label.bouquetNo" /></th>
																		<th width="8%"><s:text name="label.baseproposal" /></th>
																		<th width="8%"><s:text name="label.proposalNo" /></th>
																		<th width="8%"><s:text name="label.reinsureName" /></th>
																		<th width="8%"><s:text name="label.placingBroker" /></th>
																		<th width="6%"><s:text name="label.statusDate" /></th>
																		<th width="6%"><s:text name="label.statusFrom" /></th>
																		<th width="6%"><s:text name="label.statusTo" /></th>
																		<th width="6%"><s:text name="label.emailBy" /></th>
																		<th width="5%"><s:text name="label.view" /></th>
																	</tr>
																	</thead>
																	<tbody>	
																	<s:iterator value="placementviewInfo" var="list"  status="stat">									
																	<tr>
																		<td>
																			<s:property value="%{#stat.count}"/>
																		</td>
																		<td>
																			<s:property value="#list.OFFER_NO"/>
																		</td>
																		<td>
																			<s:property value="#list.BOUQUET_NO"/>
																		</td>
																		<td>
																		<s:if test="#list.BASE_PROPOSAL_NO==null">
																			<s:property value="#list.PROPOSAL_NO"/>
																		</s:if>
																		<s:else>
																			<s:property value="#list.BASE_PROPOSAL_NO"/>
																		</s:else>
																			
																		</td>
																		<td>
																			<s:property value="#list.PROPOSAL_NO"/>
																		</td>
																		<td>
																			<s:property value="#list.REINSURER_NAME"/>
																		</td>
																		<td>
																			<s:property value="#list.BROKER_NAME"/>
																		</td>
																		<td>
																			<s:property value="#list.UPDATE_DATE"/>
																		</td>
																		<td>
																			<s:property value="#list.CURRENT_STATUS"/>
																		</td>
																		<td>
																			<s:property value="#list.NEW_STATUS"/>
																		</td>
																		<td>
																			<s:property value="#list.EMAIL_BY"/>
																		</td>
																		<td>
																			<input type="button" value="View" class="btn btn-sm btn-primary"    onclick="FnView('<s:property value="#list.PROPOSAL_NO"/>','<s:property value="#list.REINSURER_ID"/>','<s:property value="#list.BROKER_ID"/>','<s:property value="#list.STATUS"/>','<s:property value="#list.CORRESPONDENT_ID"/>')" />
																		</td>
																	</tr>												
																	</s:iterator>
																	</tbody>
																</table>											
															</div> 
														
														
													</div>
												</div>
											</div>
									</div>
									<s:hidden name="newStatus" id="newStatus"/>
									
									</s:if>
									<s:else>
									<div class="boxcontent" >										
										<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.offerinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text txtB">
																<s:text name="label.emailBy" />
															</div> 
															<div class="tbox">
																<s:radio list="emailByList" listKey="TYPE" listValue="DETAIL_NAME"  name="emailBy"  id="emailBy" value="emailBy==null || emailBy==''?'1':emailBy" disabled="true"/>												
															</div>
														</div>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="label.updateDate" />
															</div> 
															<div class="tbox">
																<s:textfield name="updateDate" id="updateDate" cssClass="inputBox"  disabled="true"/>												
															</div>
														</div>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="label.currentStatus" />
															</div> 
															<div class="tbox">
																<s:select list="statusList" listKey="STATUS_CODE" listValue="STATUS_NAME" name="currentStatus" id="currentStatus" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  disabled="true"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="label.newStatus" />
															</div> 
															<div class="tbox">
																<s:select list="statusList" listKey="STATUS_CODE" listValue="STATUS_NAME" name="newStatus" id="newStatus" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  disabled="true"/>
															</div>
														</div>
														<div class="boxcontent">
															<div class="textfieldA25">
																<s:text name="label.cedentCorrespondent" />
															</div>
															<div class="textfieldA75">
																<s:textarea name="cedentCorrespondent" id="cedentCorrespondent" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;" disabled="true"/>
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
																<s:textarea name="reinsurerCorrespondent" id="reinsurerCorrespondent" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;" disabled="true"/>
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
																<s:textarea name="tqrCorrespondent" id="tqrCorrespondent" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;" disabled="true"/>
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
															<th width="10%">
																<s:text name="upload.docType" />
															</th>
															<th width="35%">
																<s:text name="upload.docDesc" />
															</th>
															<th width="20%">
																<s:text name="File Name" />
															</th>
															<th width="10%">
																<s:text name="Download" />
															</th>
															
														</tr>
														</thead>
														<s:if test="existingAttachList!=null && existingAttachList.size()>0">
														<tbody>
														<s:iterator value="existingAttachList" var="list"  status="stat">
															<tr>
																<td>
																	<s:property value="%{#stat.count}"/>
																</td>
																<td>
																	<s:property value="#list.DOC_TYPE"/>
																</td>
																<td>
																	<s:property value="#list.DOC_DESCRIPTION"/>
																</td>
																<td>
																	<s:property value="#list.ORG_FILE_NAME"/>
																</td>
																<td>
																	<button type="submit" class="btn btn-sm btn-primary" onclick="DownloadFile('<s:property value="#list.DOC_ID"/>','<s:property value="#list.ORG_FILE_NAME"/>')"><span class="glyphicon glyphicon-download"></span></button>
																</td>
															</tr>
														</s:iterator>
														
														</tbody>
														</s:if>
														<s:else>
														<tfoot><tr><td colspan="5" align="center">Nothing Found</td></tr></tfoot>
														
														</s:else>
													</table>											
												</div>
											</div>
										
									</div>
									</div>								
									</s:else>
									
								</div>
							</div>
							
								</div>
								
													
						</div>						
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<s:if test='"viewlist".equals(mode)'>
									<input type="button"  value="Back"  class="btn btn-sm btn-danger"  onclick="FnCancel()" />
								</s:if>
								<s:else>
									<input type="button"  value="Back"  class="btn btn-sm btn-danger"  onclick="FnViewCancel()" />
								</s:else>
							
								
																		
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
						<s:hidden name="docId" id="docId"></s:hidden>
						<s:hidden name="fileName" id="fileName"></s:hidden>
						<s:hidden name="corresId" id="corresId"/>
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
function FnCancel(){
	document.placement.action='${pageContext.request.contextPath}/summaryPlacement.action';
	document.placement.submit();
}
function FnViewCancel(){
	document.placement.action='${pageContext.request.contextPath}/viewInfoPlacement.action';
	document.placement.submit();
}

function FnView(val,val1,val2,val3,val4){
	document.getElementById('eproposalNo').value=val;
	document.getElementById('reinsurerId').value=val1;
	document.getElementById('brokerId').value=val2;
	document.getElementById('newStatus').value=val3;
	document.getElementById('corresId').value=val4;
	document.placement.action='${pageContext.request.contextPath}/viewDetailPlacement.action'
	document.placement.submit();
}
function DownloadFile(id,name){
	document.getElementById('docId').value=id
	document.getElementById('fileName').value=name;
	document.placement.action='${pageContext.request.contextPath}/downloadFilePlacement.action';
	document.placement.submit();
}
</script>		
</body>
</html>
