<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
	
	<script type="text/javascript">
	jQuery(function ($) {
			try {
				var data = $('#gridTableMake').dataTable( {
					"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
					"order": [[ 0, "asc" ]],
					"columnDefs": [ {
			          "targets": 'no-sort',
			          "orderable": false
				    } ],
				   // "stateSave": true,
					responsive: true
				});				 
			} catch(err){}
		} );		
	</script>
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
										Placement - Summary
									</div>
									<div class="panel-body">
										<div class="boxcontent" >
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.searchinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield33">
															<div class="text txtB">
																<s:text name="label.reinsureName" />
															</div> 
															<div class="tbox">
																<s:select list="existingReinsurerList" listKey="REINSURER_ID" listValue="REINSURER_NAME" name="searchReinsurerId" id="searchReinsurerId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" />
															</div>
														</div>
														<div class="textfield33">
															<div class="text txtB">
																<s:text name="label.placingBroker" />
															</div> 
															<div class="tbox">
																<s:select list="existingBrokerList" listKey="BROKER_ID" listValue="BROKER_NAME" name="searchBrokerId" id="searchBrokerId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" />
															</div>
														</div>
														<div class="textfield33">
															<div class="text txtB">
																<s:text name="label.currentStatus" />
															</div> 
															<div class="tbox">
																<s:select list="statusList" listKey="STATUS_CODE" listValue="STATUS_NAME" name="searchStatus" id="searchStatus" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"/>
															</div>
														</div>
														<br/>
														<div  align="center">
																<input type="button" class="btn btn-sm btn-info" value="Search" style="cursor: pointer;" onclick="funSearchMode('S')" />
																<input type="button" class="btn btn-sm btn-success" value="Clear Search" style="cursor: pointer;"	onclick="funSearchMode('')" />	 					
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="boxcontent" >
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.bouquetinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
															<div>
																<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
																	<thead>
																	<tr>
																		<th width="5%"> <s:text name="label.sno" /> </th>
																		<th width="8%"><s:text name="label.offerNo" /></th>
																		<th width="8%"><s:text name="label.bouquetNo" /></th>
																		<th width="8%"><s:text name="label.baseproposal" /></th>
																		<th width="8%"><s:text name="label.proposalNo" /></th>
																		<th width="8%"><s:text name="label.cedingCompany" /></th>
																		<th width="8%"><s:text name="label.reinsureName" /></th>
																		<th width="8%"><s:text name="label.placingBroker" /></th>
																		<th width="6%"><s:text name="label.shareOffer" /></th>
																		<th width="6%"><s:text name="label.written" /></th>
																		<th width="6%"><s:text name="label.proposedWL" /></th>
																		<th width="6%"><s:text name="label.signedLine" /></th>
																		<th width="5%"><s:text name="label.brokerage" /></th>
																		<th width="5%"><s:text name="label.status" /></th>
																		<s:if test='!"S".equals(searchType)'>
																		<th width="5%"><s:text name="label.action" /></th>
																		<th width="5%"><s:text name="label.view" /></th>
																		</s:if>
																	</tr>
																	</thead>
																	<tbody>	
																	<s:iterator value="#eplacementInfoList" var="list"  status="stat">									
																	<tr>
																		<td>
																			<s:property value="#list.SNO"/>
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
																			<s:property value="#list.CEDING_COMPANY_ID"/>
																		</td>
																		<td>
																			<s:property value="#list.REINSURER_NAME"/>
																		</td>
																		<td>
																			<s:property value="#list.BROKER_NAME"/>
																		</td>
																		<td align="right">
																			<s:property value="#list.SHARE_OFFERED"/>
																		</td>
																		<td align="right">
																			<s:property value="#list.SHARE_WRITTEN"/>
																		</td>
																		<td align="right">
																			<s:property value="#list.SHARE_PROPOSAL_WRITTEN"/>
																		</td>
																		<td align="right">
																			<s:property value="#list.SHARE_SIGNED"/>
																		</td>
																		<td align="right">
																			<s:property value="#list.BROKERAGE_PER"/>
																		</td>
																		<td>
																			<s:property value="#list.STATUS"/>
																		</td>
																		<s:if test='!"S".equals(searchType)'>
																		<td>
																			<input type="button" value="Update" class="btn btn-sm btn-warning"   onclick="FnUpdate('<s:property value="#list.PROPOSAL_NO"/>','<s:property value="#list.REINSURER_ID"/>','<s:property value="#list.BROKER_ID"/>')" />
																		</td>
																		<td>
																			<input type="button" value="View" class="btn btn-sm btn-primary"   onclick="FnView('<s:property value="#list.PROPOSAL_NO"/>','<s:property value="#list.REINSURER_ID"/>','<s:property value="#list.BROKER_ID"/>')" />
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
							
							
								<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onclick="FnCancel()" />
								<s:if test='"S".equals(searchType)'>
								<input type="button"  value="Update"  class="btn btn-sm btn-success"  onclick="FnNext()" />
								</s:if>
								
								<!-- <input type="button"  value="Next"  class="btn btn-sm btn-success"  onclick="FnNext()" />	
								<input type="button"  value="Submit"  class="btn btn-sm btn-warning"  onclick="FnSumbit()" /> -->											
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
$(function() {
	$('.select1').select2({ });
	});
function FnCancel(){
	document.getElementById("searchType").value='';
	document.placement.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.placement.submit();
}
function FnUpdate(val,val1,val2){
	document.getElementById('eproposalNo').value=val;
	document.getElementById('reinsurerId').value=val1;
	document.getElementById('brokerId').value=val2;
	document.placement.action='${pageContext.request.contextPath}/updateInfoPlacement.action'
	document.placement.submit();
}
function FnView(val,val1,val2){
	document.getElementById('eproposalNo').value=val;
	document.getElementById('reinsurerId').value=val1;
	document.getElementById('brokerId').value=val2;
	document.placement.action='${pageContext.request.contextPath}/viewInfoPlacement.action'
	document.placement.submit();
}
function funSearchMode(mode){
	document.getElementById("searchType").value=mode;
	document.placement.action="${pageContext.request.contextPath}/summaryPlacement.action";
	document.placement.submit();
}
function FnNext(){
	document.placement.action='${pageContext.request.contextPath}/updateInfoPlacement.action'
	document.placement.submit();
}
</script>		
</body>
</html>
