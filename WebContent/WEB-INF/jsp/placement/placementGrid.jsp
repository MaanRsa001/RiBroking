<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<style type="text/css">
 .tableColWidth {
 	min-width: 200px;
 	max-width: 750px;
 	width: 200px;
 	white-space: normal;
 }
 
  .tableColWidth1 {
 	min-width: 80px;
 	max-width: 750px;
 	width: 450px;
 	white-space: normal;
 }
 
 .tableColNoWrap {
 	min-width: 100px;
 	max-width: 750px;
 	width: 100px;
 	white-space: nowrap;
 }
 .table-overflow{
    overflow: scrollX;
 }
 </style>
<body>
<s:if test='!"D".equals(newStatus) &&  !"NPWL".equals(newStatus)'>
<s:set name="ep" value="'P'.equals(newStatus)"/>
<s:set name="ea" value="'A'.equals(newStatus)"/>
<s:set name="ed" value="'D'.equals(newStatus)"/>
<s:set name="ero" value="'RO'.equals(newStatus)"/>
<s:set name="epwl" value="'PWL'.equals(newStatus)"/>
<s:set name="esl" value="'SL'.equals(newStatus)"/>
<s:set name="epsl" value="'PSL'.equals(newStatus)"/>
<s:set name="enpwl" value="'NPWL'.equals(newStatus)"/>
<s:set name="ecsl" value="'CSL'.equals(newStatus)"/>
<s:set name="emailstatus" value="!'Success'.equals(emailStatus[%{#stat.count-1}])"/>

<div class="boxcontent" >										
	<div class="panel panel-primary">											
			<div class="panel-heading">
				<s:text name="label.offerinfo" />
			</div>
			<div class="panel-body">
				<div  class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;overflow-y: visible;">
				<table width="100%" class="table table-bordered" >
					<thead>
					<tr>
						<th width="3%"> <s:text name="label.sno" /> </th>
						<th class="tableColWidth"><s:text name="label.proposalNo" /></th>
						<th class="tableColWidth"><s:text name="label.cedingCompany" /></th>
						<th class="tableColWidth"><s:text name="label.reinsureName" /></th>
						<th class="tableColWidth"><s:text name="label.placingBroker" /></th>
						<th class="tableColWidth"><s:text name="label.epi" /></th>
						<th class="tableColWidth"><s:text name="label.shareOffer" /></th>
						<s:if test='("A".equals(newStatus)) || (#ero) || (#epwl) || (#enpwl) || (#esl) || (#epsl) || (#ecsl)'>
						<th class="tableColWidth"><s:text name="label.written" /></th>
						</s:if>
						<s:if test='("A".equals(newStatus))'>
						<th class="tableColWidth"><s:text name="label.writtenvaliditydate" /></th>
						<th class="tableColWidth"><s:text name="label.writtenvalidityRemarks" /></th>
						</s:if>
						<s:if test='(#ero) || (#epwl) || (#enpwl) || (#esl) || (#epsl) || (#ecsl)'>
						<th class="tableColWidth"><s:text name="label.proposedWL" /></th>
						</s:if>
						<s:if test='(#ero) || (#esl) || (#epsl) || (#ecsl)'>
						<th class="tableColWidth"><s:text name="label.signedLine" /></th>
						</s:if>
						<s:if test='(#esl)'>
						<th class="tableColWidth"><s:text name="label.signedLineValidity" /></th>
						<th class="tableColWidth"><s:text name="label.signedLineRemarks" /></th>
						</s:if>
						<s:if test='(#ero)  || (#epsl) || (#ecsl)'>
						<th class="tableColWidth"><s:text name="label.proposedSL" /></th>
						</s:if>
						<s:if test='(#ero)'>
						<th class="tableColWidth"><s:text name="label.reoffer" /></th>
						</s:if>
						<s:if test='(#epwl)  || (#enpwl) || (#esl) || (#epsl) || (#ecsl)'>
						<th class="tableColWidth"><s:text name="label.tqrBrokerageAmt" /></th>
						</s:if>
						<s:if test='("A".equals(newStatus))  || (#epwl) || (#enpwl) || (#esl) || (#epsl) || (#ecsl)'>
						<th class="tableColWidth"><s:text name="label.brokerage" /></th>
						</s:if>
						<%-- <s:if test='(#ep) || (#ed) || (#enpwl) || (#epsl) || (#ecsl)'>
						<th class="tableColWidth"><s:text name="label.emailStatus" /></th>
						<th class="tableColWidth"><s:text name="label.previewsendmail" /></th>
						</s:if> --%>
					</tr>
					</thead>
					<tbody>	
					<s:iterator value="placementeditInfo" var="list"  status="stat">									
						<tr>
							<td>
								<s:property value="%{#stat.count}"/>
							</td>
							<td>
								<s:property value="proposalNos[#stat.count-1]"/>
								<s:hidden name="snos[%{#stat.count-1}]"/>
								<s:hidden name="bouquetNos[%{#stat.count-1}]"/>
								<s:hidden name="baseproposalNos[%{#stat.count-1}]"/>
								<s:hidden name="proposalNos[%{#stat.count-1}]"/>
								<s:hidden name="reinsurerIds[%{#stat.count-1}]"/>
								<s:hidden name="brokerIds[%{#stat.count-1}]"/>
							</td>
							<td>
								<s:property value="cedingCompanyNames[#stat.count-1]"/>
								<s:hidden name="cedingCompanys[%{#stat.count-1}]"/>
								<s:hidden name="cedingCompanyNames[%{#stat.count-1}]"/>
							</td>
							<td>
								<s:property value="reinsurerNames[#stat.count-1]"/>
								<s:hidden name="reinsurerNames[%{#stat.count-1}]"/>
							</td>
							<td>
								<s:property value="brokerNames[#stat.count-1]"/>
								<s:hidden name="brokerNames[%{#stat.count-1}]"/>
							</td>
							<td>
								<s:property value="epiAmount[#stat.count-1]"/>
								<s:hidden name="epiAmount[%{#stat.count-1}]"/>
							</td>
							<td>
							<s:if test='("A".equals(newStatus))'>
								<s:textfield name="shareOffered[%{#stat.count-1}]" id="shareOffered[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="true"/>
							</s:if>
							<s:else>
								<s:textfield name="shareOffered[%{#stat.count-1}]" id="shareOffered[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="%{(#emailstatus) || (#ero) || (#epwl) || (#esl) || (#epsl) || (#ecsl)}"/>
							</s:else>
								
							</td>
							<s:if test='("A".equals(newStatus)) || (#ero) || (#epwl) || (#enpwl) || (#esl) || (#epsl) || (#ecsl)'>
							<td>
								<s:textfield name="writtenLine[%{#stat.count-1}]" id="writtenLine[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="%{(#ero) || (#epwl) || (#esl) || (#epsl) || (#ecsl)}"/>
								
							</td>
							</s:if>
							<s:else>
							<s:hidden name="writtenLine[%{#stat.count-1}]"/>
							</s:else>
							<s:if test='("A".equals(newStatus))'>
							<td>
								<s:textfield name="writtenvaliditydate[%{#stat.count-1}]" id="writtenvaliditydate[%{#stat.count-1}]" cssClass="inputBox writtenvaliditydate" theme="simple" onchange="validatedate(this.id,this.value);"/>
							</td>
							<td>
								<s:textfield name="writtenvalidityRemarks[%{#stat.count-1}]" id="writtenvalidityRemarks[%{#stat.count-1}]" cssClass="inputBox" theme="simple"/>
							</td>
							</s:if>
							<s:else>
							<s:hidden name="writtenvaliditydate[%{#stat.count-1}]"/>
							<s:hidden name="writtenvalidityRemarks[%{#stat.count-1}]"/>
							</s:else>
							<s:if test='(#ero) || (#epwl) || (#enpwl) || (#esl) || (#epsl) || (#ecsl)'>
							<td>
								<s:textfield name="proposedWL[%{#stat.count-1}]" id="proposedWL[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="%{(#ero)|| (#esl) || (#epsl) || (#ecsl)}"/>
							</td>
							</s:if>
							<s:else>
							<s:hidden name="proposedWL[%{#stat.count-1}]"/>
							</s:else>
							<s:if test='(#ero) || (#esl) || (#epsl) || (#ecsl)'>
							<td>
								<s:textfield name="signedLine[%{#stat.count-1}]" id="signedLine[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="%{(#ero) || (#epsl)}"/>
								<s:hidden name="psignedLine[%{#stat.count-1}]"/>
							</td>
							</s:if>
							<s:else>
							<s:hidden name="signedLine[%{#stat.count-1}]"/>
							<s:hidden name="psignedLine[%{#stat.count-1}]"/>
							</s:else>
							<s:if test='(#esl)'>
							<td>
								<s:textfield name="signedLineValidity[%{#stat.count-1}]" id="signedLineValidity[%{#stat.count-1}]" cssClass="inputBox signedLineValidity"  theme="simple" onchange="validatedate(this.id,this.value);"/>
							</td>
							<td>
								<s:textfield name="signedLineRemarks[%{#stat.count-1}]" id="signedLineRemarks[%{#stat.count-1}]" cssClass="inputBox"  theme="simple"/>
							</td>
							</s:if>
							<s:else>
							<s:hidden name="signedLineValidity[%{#stat.count-1}]"/>
							<s:hidden name="signedLineRemarks[%{#stat.count-1}]"/>
							</s:else>
							<s:if test='(#ero) || (#epsl) || (#ecsl)'>
							<td>
								<s:textfield name="proposedSL[%{#stat.count-1}]" id="proposedSL[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="%{(#ero) || (#ecsl)}"/>
							</td>
							</s:if>
							<s:else>
							<s:hidden name="proposedSL[%{#stat.count-1}]"/>
							</s:else>
							<s:if test='(#ero)'>
							<td>
								<s:textfield name="reoffer[%{#stat.count-1}]" id="reoffer[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
							</td>
							</s:if>
							<s:else>
							<s:hidden name="reoffer[%{#stat.count-1}]"/>
							</s:else>
							<s:if test='(#epwl)  || (#enpwl) || (#esl) || (#epsl) || (#ecsl)'>
							<td>
								<s:textfield name="tqrBrokerageaccmt[%{#stat.count-1}]" id="tqrBrokerageAmt[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple" disabled="%{(#epwl) || (#esl) || (#epsl) || (#ecsl)}"/>
							</td>
							</s:if>
							<s:else>
							<s:hidden name="tqrBrokerageAmt[%{#stat.count-1}]"/>
							</s:else>
							<s:if test='("A".equals(newStatus))  || (#epwl) || (#enpwl) || (#esl) || (#epsl) || (#ecsl)'>
							<td>
								<s:textfield name="brokerage[%{#stat.count-1}]" id="brokerage[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple" disabled="%{(#epwl) || (#esl) || (#epsl) || (#ecsl)}"/>
							</td>
							</s:if>
							<s:else>
							<s:hidden name="brokerage[%{#stat.count-1}]"/>
							</s:else>
							<%-- <s:if test='(#ep) || (#ed) || (#enpwl) || (#epsl) || (#ecsl)'>
							<td>
								<s:property value="emailStatus[#stat.count-1]"/>
								<s:hidden name="emailStatus[%{#stat.count-1}]"/>
							</td>
							<td>
								<input type="button" value="Update" class="btn btn-sm btn-info"   onclick="FnUpdate('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
							</td>
							</s:if> --%>
						</tr>												
						</s:iterator>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</s:if>
<s:else>
<s:iterator value="placementeditInfo" var="list"  status="stat">
<s:hidden name="snos[%{#stat.count-1}]"/>
<s:hidden name="bouquetNos[%{#stat.count-1}]"/>
<s:hidden name="baseproposalNos[%{#stat.count-1}]"/>
<s:hidden name="proposalNos[%{#stat.count-1}]"/>
<s:hidden name="reinsurerIds[%{#stat.count-1}]"/>
<s:hidden name="brokerIds[%{#stat.count-1}]"/>

<s:hidden name="shareOffered[%{#stat.count-1}]"/>
<s:hidden name="writtenLine[%{#stat.count-1}]"/>
<s:hidden name="writtenvaliditydate[%{#stat.count-1}]"/>
<s:hidden name="writtenvalidityRemarks[%{#stat.count-1}]"/>
<s:hidden name="proposedWL[%{#stat.count-1}]"/>
<s:hidden name="signedLine[%{#stat.count-1}]"/>
<s:hidden name="psignedLine[%{#stat.count-1}]"/>
<s:hidden name="signedLineValidity[%{#stat.count-1}]"/>
<s:hidden name="signedLineRemarks[%{#stat.count-1}]"/>
<s:hidden name="proposedSL[%{#stat.count-1}]"/>
<s:hidden name="reoffer[%{#stat.count-1}]"/>
<s:hidden name="tqrBrokerageAmt[%{#stat.count-1}]"/>
<s:hidden name="brokerage[%{#stat.count-1}]"/>
</s:iterator>
</s:else>
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
</body>
</html>



