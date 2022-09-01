<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>

table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 5px;
  text-align: left;    
}
body {
  font-size: 12px;
}
	    
</style>

<s:iterator value="claimView" var="claim">
	<table style="width: 100%">
		<tr>
			<th style="width: 25%">
				<b>Claim Number</b>
			</th>
			<td>
				<s:property value="%{#claim.CLAIM_NO}" />
			</td>
			<th style="width: 25%">
				<b>Claim Payment Numbers</b>:
			</th>
			<td>
				<s:property value="%{#claim.CLAIM_PAYMENT_NO}" />
			</td>
		</tr>
		<tr>
			<th style="width: 25%">
				<b>Cedant</b>
			</th>
			<td>
				<s:property value="%{#claim.COMPANY_NAME}" />
			</td>
			<th style="width: 25%">
				<b>Broker</b>
			</th>
			<td>
				<s:property value="%{#claim.BROKER_NAME}" />
			</td>
		</tr>
		<tr>
			<th>
				<b>Insured</b>
			</th>
			<td>
				<s:property value="%{#claim.INSURED_NAME}" />
			</td>
			<th>
				<b>Date of Loss</b>
			</th>
			<td>
				<s:property value="%{#claim.DATE_OF_LOSS}" />
			</td>
		</tr>
		<tr>
			<th>
				<b>Circumstances</b>
			</th>
			<td colspan="3" >
				<s:property value="%{#claim.LOSS_DETAILS}" />
			</td>
		</tr>
		<tr>
			<th>
				<b>Claim after Deductible/Retention – 100% (<s:property value="%{#claim.CURRENCY}" />)</b> 
			</th>
			<td>
				<s:property value="getText('number.format',{#claim.LOSS_ESTIMATE_OC})"/>
			</td>
			<th>
				<b>Provisional / Final</b>
			</th>
			<td>
				<s:property value="%{#claim.PAYMENT_TYPE}" />
			</td>
		</tr>
		<tr>
			<th>
				<b>Our Share (%)</b>
			</th>
			<td>
				<s:property value="%{#claim.SHARE_SIGNED}" /> %
			</td>
			<th>
				<b>Our Share (Amount) (<s:property value="%{#claim.CURRENCY}" />) </b>
			</th>
			<td>
				<s:property value="getText('number.format',{#claim.LOSS_ESTIMATE_OS_OC})"/>
			</td>
		</tr>
		<tr>
			<th>
				<b>Contract Number</b>
			</th>
			<td>
				<s:property value="%{#claim.CONTRACT_NO}" />
			</td>
			<th>
				<b>Treaty/Fac Name</b>
			</th>
			<td>
				<s:property value="%{#claim.RSK_INSURED_NAME}" />
			</td>
		</tr>
		<tr>
			<th>
				<b>Reinstatement Premium – Our Share (<s:property value="%{#claim.CURRENCY}" />)</b>
			</th>
			<td>
				<s:property value="getText('number.format',{#claim.REINSPREMIUM_OURSHARE_OC})" default="0" />
			</td>
			<th>
				<b>Net Claim – Our Share (<s:property value="%{#claim.CURRENCY}" />) </b>
			</th>
			<td>
				<s:property value="getText('number.format',{#claim.PAID_AMOUNT_OC})" default="0"/>
			</td>
		</tr>
		<tr >
		
			<th>
				<b>Outstanding Claim Amount – Our Share (<s:property value="%{#claim.CURRENCY}" />) </b>
			</th>
			<td colspan="3" >
				<s:property value="getText('number.format',{#claim.OSLR})" default="0"/>
			</td>
		</tr>
	</table>
<br/>
<table style="width: 100%">
	<tr>
		<th colspan="2" >
			<u><b>For Accounts Use Only:</b></u>
		</th>
	</tr>
	<tr>
		<th>
			Premium Paid Status
		</th>
		<td>
			<s:checkbox name="Yes" id="yes" fieldValue="true"  theme="simple" onclick="changeCheckBox('1')" /> &nbsp; Yes &nbsp;
			 <s:checkbox name="No" id="no" fieldValue="true"  theme="simple"  onclick="changeCheckBox('2')" /> &nbsp; No &nbsp;
			<s:checkbox name="Partial" id="partial" fieldValue="true"  theme="simple"  onclick="changeCheckBox('3')" />&nbsp; Partial
			
			<div id="statBtn">
				<input type="button" value="Statistics" onclick="funDownload();" class="btn btn-sm btn-info">
			</div> 
		</td>
	</tr>
</table>
<br/>
<table style="width: 100%">
	<tr style="width: 50%">
		<th colspan="2" >
			Particular
		</th>
		<td style="width: 25%">
			<b>Name</b>
			<br/>
		</td>
		<td style="width: 25%">
			<b>Date</b>
			<br/>
		</td>
		<td style="width: 25%">
			<b>Signature</b>
			<br/>
		</td>
	</tr>
	<tr style="width: 50%">
		<th colspan="2" >
			Technical Officer / Assistant
		</th>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
	</tr>
	<tr>
		<th colspan="2" >
			Head , Technical Operations
		</th>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
	</tr>
	<tr>
		<th colspan="2" >
			Head , Finance Operations
		</th>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
	</tr>
	<tr>
		<th colspan="2" >
			Chief Executive Officer
		</th>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
		<td style="width: 25%">
			<br/>
			<br/>
		</td>
	</tr>
</table>

</s:iterator>
<br/>
<div class="boxcontent" align="center" id="buttonDivId">
	<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="getPrint()"/>
	<input type="button"  value="Close" class="btn btn-sm btn-danger" onclick="window.close()"/>
	<s:if test="multiPaymentNoList.size()>0">
		<input type="button"  value="View Details" class="btn btn-sm btn-success" onclick="ViewClaimAuthDetail()"/>	
	</s:if>
</div>