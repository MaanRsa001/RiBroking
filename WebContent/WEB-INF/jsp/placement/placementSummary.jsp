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
										<div class="panel-group" id="accordion">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion"
													aria-expanded="true" href="#collapseOne"> <i
													class="glyphicon glyphicon-plus" id="detailsPlus"
													style="cursor: pointer;" onclick="detailsClick(true)">&nbsp;<font face="Arial" ><b>Multiple Search</b></font></i>
													<i class="glyphicon glyphicon-minus" id="detailsMinus"
													style="cursor: pointer; display: none;"
													onclick="detailsClick(false)">&nbsp;<font face="Arial" ><b>Multiple Search</b></font></i> </a>
											</h4>
										</div>

										<div id="detailsData" style="display: none;"
											class="panel-collapse collapse in">
											<div class="panel-body">
												<s:if test='"3".equals(#session.mfrid) || "5".equals(#session.mfrid)'>
													<div class="textfield">
														<div class="text">
															<s:if test=' !"5".equals(#session.mfrid)'>
																<s:text name="Company Name" />
															</s:if>
															<s:else>
																<s:text name="Lead Retrocessionaire" />
															</s:else>
														</div>
														<div class="tbox txtB">
															<s:textfield name="companyNameSearch1" id="companyNameSearch1" cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="Broker Name" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="brokerNameSearch1" id="brokerNameSearch1"
																cssClass="inputBox" />
														</div>
													</div>
												</s:if>
												<s:else>
													<div class="textfield">
														<div class="text">
															<s:if test=' !"4".equals(#session.mfrid)'>
																<s:text name="Company Name" />
															</s:if>
															<s:else>
																<s:text name="Lead Retrocessionaire" />
															</s:else>
														</div>
														<div class="tbox txtB">
															<s:textfield name="companyNameSearch" id="companyNameSearch"
																cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="Broker Name" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="brokerNameSearch" id="brokerNameSearch"
																cssClass="inputBox" />
														</div>
													</div>
												</s:else>
												<s:if test='"1".equals(#session.mfrid) '>
													<div class="textfield">
														<div class="text">
															<s:text name="Insured Name" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="insuredNameSearch" id="insuredNameSearch"
																cssClass="inputBox" />
														</div>
													</div>
												</s:if>
													<div class="textfield">
														<div class="text">
															<s:text name="UW Year From" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="uwYearSearch" id="uwYearSearch"
																cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="UW Year TO" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="uwYearToSearch" id="uwYearToSearch"
																cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="Inception Date" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="incepDateSearch" id="incepDateSearch"
																cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="Expiry Date" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="expDateSearch" id="expDateSearch"
																cssClass="inputBox" />
														</div>
													</div>
												
												<br class="clear"/>
												<div  align="center">
														<input type="button" class="btn btn-sm btn-info" value="Search" style="cursor: pointer;" onclick="funSearchMode('S')" />
														<input type="button" class="btn btn-sm btn-success" value="Clear Search" style="cursor: pointer;" onclick="funSearchMode('')" />							
																					
														<s:if test='#session.MenuRights.indexOf("R")!=-1'>
															<input type="button" value="Show Full data" class="btn btn-sm btn-warning" onclick="showFullData();" /> 
														</s:if>
																			
												</div>
											</div>
										</div>
									</div>
								</div>
										<div class="boxcontent">
												<div>
													<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
														<thead>
														<tr>
															<th width="5%"> <s:text name="label.sno" /> </th>
															<th width="8%"><s:text name="label.offerNo" /></th>
															<th width="8%"><s:text name="label.baseproposal" /></th>
															<th width="8%"><s:text name="label.proposalNo" /></th>
															<th width="8%"><s:text name="label.treatyNameType" /></th>
															<th width="8%"><s:text name="label.layerSectionNo" /></th>
															<th width="8%"><s:text name="label.reinsureName" /></th>
															<th width="8%"><s:text name="label.placingBroker" /></th>
															<th width="6%"><s:text name="label.currency" /></th>
															<th width="6%"><s:text name="label.ourAssessedEPIOC100" /></th>
															<th width="6%"><s:text name="label.ourAssessedEPIDC" /><s:property value="shortName"/></th>
															<th width="5%"><s:text name="label.status" /></th>
															<th width="6%"><s:text name="label.signedLine" /></th>
															<th width="5%"><s:text name="label.brokerage" /></th>
															<th width="5%"><s:text name="label.tqrBrokerageAmt" /></th>
															
														</tr>
														</thead>
														<tbody>	
														<s:iterator value="plSummaryInfo" var="list"  status="stat">									
														<tr>
															<td>
																<s:property value="#list.SNO"/>
															</td>
															<td>
																<s:property value="#list.OFFER_NO"/>
															</td>
															<td>
																<s:property value="#list.BASE_PROPOSAL_NO"/>
															</td>
															<td>
																<s:property value="#list.PROPOSAL_NO"/>
															</td>
															<td>
																<s:property value="#list.TREATY_NAME"/>
															</td>
															<td>
																<s:property value="#list.LAYER_SECTION"/>
															</td>
															<td>
																<s:property value="#list.REINSURER_NAME"/>
															</td>
															<td>
																<s:property value="#list.BROKER_NAME"/>
															</td>
															<td>
																<s:property value="#list.CURRENCY"/>
															</td>
															<td align="right">
																<s:property value="#list.EPI_100_OC"/>
															</td>
															<td align="right">
																<s:property value="#list.EPI_100_DC"/>
															</td>
															<td>
																<s:property value="#list.PLACING_STATUS"/>
															</td>
															<td align="right">
																<s:property value="#list.SHARE_SIGNED"/>
															</td>
															<td align="right">
																<s:property value="#list.BROKERAGE"/>
															</td>
															<td align="right">
																<s:property value="#list.BROKERAGE_AMT"/>
															</td>
															
														</tr>												
														</s:iterator>
														</tbody>
														<tfoot>
															<tr>
															<td colspan="7" align="center"></td>
															<td align="center">Risk Total</td>
															<td colspan="2" align="center"></td>
															<td align="right"><span id="risktotal"></span></td>
															<td align="center"></td>
															<td align="right"><span id="risksign"></span></td>
															<td align="center"></td>
															<td align="right"><span id="riskbrokerage"></span></td>
															</tr>
															<tr>
															<td colspan="7" align="center"></td>
															<td align="center">Confirm Total</td>
															<td colspan="2" align="center"></td>
															<td align="right"><span id="confirmtotal"></span></td>
															<td align="center"></td>
															<td align="right"><span id="confirmsign"></span></td>
															<td align="center"></td>
															<td align="right"><span id="confirmbrokerage"></span></td>
															</tr>
														</tfoot>
													</table>											
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
							
							
								<input type="button"  value="Back"  class="btn btn-sm btn-danger"  onclick="FnCancel()" />
																			
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
function FnCancel(){
	document.getElementById("searchType").value='';
	document.placement.action='${pageContext.request.contextPath}/InitCPortfolio.action?flag=CSL';
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
	document.placement.action="${pageContext.request.contextPath}/plSummaryPlacement.action";
	document.placement.submit();
}
function FnNext(){
	document.placement.action='${pageContext.request.contextPath}/updateInfoPlacement.action'
	document.placement.submit();
}
if('S'=='<s:property value="searchType"/>'){
	detailsClick(true);
	}
function detailsClick(val) {
	if(val){
  		document.getElementById('detailsData').style.display='block';		
		document.getElementById('detailsMinus').style.display='block';
		document.getElementById('detailsPlus').style.display='none';
    }else{
    	document.getElementById('detailsData').style.display='none';
		document.getElementById('detailsMinus').style.display='none';
		document.getElementById('detailsPlus').style.display='block';
    }
}
$('#proposalNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(1).search(this.value).draw();
} );
$('#contractNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(2).search(this.value).draw();
} );
$('#companyNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(3).search(this.value).draw();
} );
$('#brokerNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(4).search(this.value).draw();
} );
$('#departmentNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(5).search(this.value).draw();
} );
$('#insuredNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(8).search(this.value).draw();
} );
$('#underwriterSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(10).search(this.value).draw();
} );
$('#underwriterSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(9).search(this.value).draw();
} );
$('#companyNameSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(4).search(this.value).draw();
} );
$('#brokerNameSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(5).search(this.value).draw();
} );
$('#departmentNameSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(6).search(this.value).draw();
} );
$('#uwYearSearch2').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(9).search(this.value).draw();
} );
$('#uwYearSearch3').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(8).search(this.value).draw();
} );
getAnnualAggNo();
function getAnnualAggNo(){
	var ctotal=0;var rtotal=0;var csign=0;var rsign=0;var cbro=0;var rbro=0;
	<s:iterator value="plSummaryInfo" var="list"  status="stat">
	 var status = '<s:property value="#list.NEW_STATUS"/>';
	 var val='<s:property value="#list.EPI_100_DC"/>';
	 var sign='<s:property value="#list.SHARE_SIGNED"/>';
	 var bro='<s:property value="#list.BROKERAGE_AMT"/>';
	 if(status=="CSL"){
		 ctotal=parseInt(ctotal)+parseInt(val);
		 csign=parseInt(csign)+parseInt(sign);
		 cbro=parseInt(cbro)+parseInt(bro);
	 }else{
	  	rtotal=parseInt(rtotal)+parseInt(val);
	  	rsign=parseInt(rsign)+parseInt(sign);
	  	rbro=parseInt(rbro)+parseInt(bro);
	 }
	 </s:iterator>
	document.getElementById("risktotal").innerHTML=rtotal;
	document.getElementById("confirmtotal").innerHTML=ctotal;
	document.getElementById("risksign").innerHTML=rsign.toFixed(4);
	document.getElementById("confirmsign").innerHTML=csign.toFixed(4);
	document.getElementById("riskbrokerage").innerHTML=rbro.toFixed(2);
	document.getElementById("confirmbrokerage").innerHTML=cbro.toFixed(2);
	}
</script>		
</body>
</html>
