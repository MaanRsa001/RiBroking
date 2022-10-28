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
										Placement - Summary
									</div>
									<div class="panel-body">
										<div class="boxcontent" >
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.bouquetinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
															<div>
																<table width="100%" class="table table-bordered" >
																	<thead>
																	<tr>
																		<th width="7%"> <s:text name="label.sno" /> </th>
																		<th width="10%"><s:text name="label.bouquetNo" /></th>
																		<th width="10%"><s:text name="label.baseproposal" /></th>
																		<th width="10%"><s:text name="label.proposalNo" /></th>
																		<th width="10%"><s:text name="label.cedingCompany" /></th>
																		<th width="10%"><s:text name="label.reinsureName" /></th>
																		<th width="10%"><s:text name="label.placingBroker" /></th>
																		<th width="10%"><s:text name="label.shareOffer" /></th>
																		<th width="10%"><s:text name="label.written" /></th>
																		<th width="10%"><s:text name="label.proposedWL" /></th>
																		<th width="10%"><s:text name="label.signedLine" /></th>
																		<th width="5%"><s:text name="label.brokerage" /></th>
																		<th width="5%"><s:text name="label.status" /></th>
																		<th width="5%"><s:text name="label.action" /></th>
																	</tr>
																	</thead>
																	<tbody>	
																	<s:iterator value="#eplacementInfoList" var="list"  status="stat">									
																	<tr>
																		<td>
																			<s:property value="%{#stat.count}"/>
																		</td>
																		<td>
																			<s:property value="#list.BOUQUET_NO"/>
																		</td>
																		<td>
																			<s:property value="#list.BASE_PROPOSAL_NO"/>
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
																		<td>
																			<s:property value="#list.SHARE_OFFERED"/>
																		</td>
																		<td>
																			<s:property value="#list.SHARE_WRITTEN"/>
																		</td>
																		
																		<td>
																			<s:property value="#list.SHARE_PROPOSAL_WRITTEN"/>
																		</td>
																		<td>
																			<s:property value="#list.SHARE_SIGNED"/>
																		</td>
																		<td>
																			<s:property value="#list.BROKERAGE"/>
																		</td>
																		<td>
																			<s:property value="#list.STATUS"/>
																		</td>
																		<td>
																			<input type="button" value="Update" class="btn btn-sm btn-warning"   onclick="FnUpdate('<s:property value="#list.PROPOSAL_NO"/>','<s:property value="#list.REINSURER_ID"/>','<s:property value="#list.BROKER_ID"/>')" />
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
									<br class="clear"/>									
									
									
								</div>
							</div>
							
								</div>
							
													
						</div>						
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							
							
								<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onclick="FnCancel()" />
								<!-- <input type="button"  value="Next"  class="btn btn-sm btn-success"  onclick="FnNext()" />	
								<input type="button"  value="Submit"  class="btn btn-sm btn-warning"  onclick="FnSumbit()" /> -->											
							</div>
						</div>	
						
						<s:hidden name="size" />
						<s:hidden name="proposalNo" id="proposalNo"></s:hidden>
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

function FnCancel(){
	document.placement.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.placement.submit();
}
function FnUpdate(val,val1,val2){
	document.getElementById('proposalNo').value=val;
	document.getElementById('reinsurerId').value=val1;
	document.getElementById('brokerId').value=val2;
	document.placement.action='${pageContext.request.contextPath}/updateInfoPlacement.action'
	document.placement.submit();
}

</script>		
</body>
</html>
