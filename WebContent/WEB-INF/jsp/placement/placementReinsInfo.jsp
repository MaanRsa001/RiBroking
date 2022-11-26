<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
<div class="boxcontent">
<div class="panel panel-primary">											
		<div class="panel-heading" style="background-color: #5eb860;border-color:#5eb860">
			<s:text name="label.Reinsureinfo" />
		</div>
		<div class="panel-body">
			<div class="boxcontent">
				<div  class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;overflow-y: visible;">
					<table width="100%" class="table table-bordered" id="ereinsTbl">
						<thead>
						<tr>
							<th width="5%"> <s:text name="label.sno" /> </th>
							<th class="tableColWidth"><s:text name="label.reinsureName" /></th>
							<th class="tableColWidth"><s:text name="label.placingBroker" /></th>
							<th class="tableColWidth"><s:text name="label.placementStatus" />(Current)</th>
							<th class="tableColWidth"><s:text name="label.placementStatus" />(New)</th>
							<th class="tableColWidth"><s:text name="label.shareOffer" /></th>
							<th class="tableColWidth"><s:text name="label.written" /></th>
							<th class="tableColWidth"><s:text name="label.proposedWL" /></th>
							<th class="tableColWidth"><s:text name="label.proposedSL" /></th>
							<th class="tableColWidth"><s:text name="label.signedLine" /></th>
							<th class="tableColWidth"><s:text name="label.brokerage" /></th>
						</tr>
						</thead>
						<tbody>	
						<s:iterator value="placementInfoList" var="list"  status="stat">									
						<tr>
							<td align="center">
								<s:textfield name="snos[%{#stat.count-1}]" id="snos[%{#stat.count-1}]" cssClass="inputBox"  readonly="true" cssStyle="text-align: center;"/>
								<s:hidden name="bouquetNos[%{#stat.count-1}]" id="bouquetNos[%{#stat.count-1}]"></s:hidden>
								<s:hidden name="baseproposalNos[%{#stat.count-1}]" id="baseproposalNos[%{#stat.count-1}]"></s:hidden>
							</td>
							<td>
								<s:select list="reinsurerList" listKey="CUSTOMER_ID" listValue="NAME" name="reinsurerIds[%{#stat.count-1}]" id="reinsurerIds[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  disabled='%{"N".equals(changeStatus[#stat.count-1])}'/>
							</td>
							<td>
								<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" name="brokerIds[%{#stat.count-1}]" id="brokerIds[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  disabled='%{"N".equals(changeStatus[#stat.count-1])}'/>
							</td>
							<td>
								<s:select list="statusList" listKey="STATUS_CODE" listValue="STATUS_NAME" name="currentStatus[%{#stat.count-1}]" id="currentStatus[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  disabled="true"/>
							</td>
							<td>
								<%-- <s:select list="subStatusList" listKey="SUB_STATUS_CODE" listValue="SUB_STATUS_NAME" name="newStatus" id="newStatus" cssClass="inputBoxS" headerKey="" headerValue="---Select---"/> --%>
								<s:select list="statusList" listKey="STATUS_CODE" listValue="STATUS_NAME" name="newStatus[%{#stat.count-1}]" id="newStatus[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  disabled="true"/>
							</td>
							<td>
								<s:textfield name="shareOffered[%{#stat.count-1}]" id="shareOffered[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="true"/>
							</td>
							<td>
								<s:textfield name="writtenLine[%{#stat.count-1}]" id="writtenLine[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="true"/>
							</td>
							<td>
								<s:textfield name="proposedWL[%{#stat.count-1}]" id="proposedWL[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="true"/>
							</td>
							<td>
								<s:textfield name="proposedSL[%{#stat.count-1}]" id="proposedSL[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="true"/>
							</td>
							<td>
								<s:textfield name="signedLine[%{#stat.count-1}]" id="signedLine[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="true"/>
							</td>
							<td>
								<s:textfield name="brokerages[%{#stat.count-1}]" id="brokerages[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" disabled="true"/>
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
</body>
</html>



