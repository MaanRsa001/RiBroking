<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="" name="" theme="simple" action="/PremiumInit"	method="post">					
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
						<div class="tablerow">
							<s:hidden name="nr" value="0" />
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.contractno" />
												</div>
												<div class="tbox">
													<s:property value="contNo"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.profitCenter" />
												</div>
												<div class="tbox">
													<s:property value="profit_Center"/>
												</div>
											</div>
											<s:if test="productId == 1">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.insuredName" />
												</div>
												<div class="tbox">
													<s:property value="insured_name"/>
												</div>
											</div>
											</s:if>
											<s:if test="productId == 3">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.layerNo" />
												</div>
												<div class="tbox">
													<s:property value="layerno"/>
												</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.subProfitCenter" />
												</div>
												<div class="tbox">
													<s:property value="subProfit_center"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.underwritingYear" />
												</div>
												<div class="tbox">
													<s:property value="uwYear"/>
												</div>
											</div>
											<s:if test="productId != 1">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.policyBranch" />
												</div>
												<div class="tbox">
													<s:property value="policyBranch"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.treatyName" />
												</div>
												<div class="tbox">
													<s:property value="treatyName_type"/>
												</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.cedingCompany" />
												</div>
												<div class="tbox">
													<s:property value="cedingCo"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.broker" />
												</div>
												<div class="tbox">
													<s:property value="broker"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.inceptionDate" />
												</div>
												<div class="tbox">
													<s:property value="insDate"/>
													<s:hidden name="insDate" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.expiryDate" />
												</div>
												<div class="tbox">
													<s:property value="expDate"/>
													<s:hidden name="expDate" />
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
									<br class="clear"/>
									<s:if test="productId == 1">
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.commissionP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="commission_view"/>
												</div>
											</div>											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.brokerageP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="brokerage_view"/>
													<s:hidden name="brokerage_S" value="brokerage_view" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.taxP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="tax_view"/>
													<s:hidden name="tax_S" value="tax_view" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.otherCostP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="otherCostView"/>
													<s:hidden name="otherCost_S" value="otherCostView" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.epiBooked" />
												</div>
												<div class="tbox" align="right">
													<s:set id="prebal" value="sum_of_paid_premium" />
													<s:hidden name="sum_of_paid_premium" />
													<s:set id="pregwpi" value="gwpiOS" />
													<s:hidden name="gwpiOS" />
													<s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(sumofPaidPre)})}"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.gwpiOurShare" />
												</div>
												<div class="tbox" align="right">
													<s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(gwpiOSFor)})}"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.epiBalance" />
												</div>
												<div class="tbox" align="right">
													<s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(preBooked)})}"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.settlementStatus" />
												</div>
												<div class="tbox">
													Pending
													<s:hidden name="settlement_status" value="Pending"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
									</s:if>									
									<s:if test="productId == 1">
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.commissionQSP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="commission_view"/>
												</div>
											</div>											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.commissionSurpP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="commissionSurb_view"/>
													<s:hidden name="commssion_Surp" value="commissionSurb_view" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.overriderP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="overRider_view"/>
													<s:hidden name="overRider_S" value="overRider_view" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.brokerageP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="brokerage_view"/>
													<s:hidden name="brokerage_S" value="brokerage_view" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.taxP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="tax_view"/>
													<s:hidden name="tax_S" value="tax_view" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.otherCostP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="otherCostView"/>
													<s:hidden name="otherCost_S" value="otherCostView" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.premiumReserveP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="premium_Reserve_view"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.lossReserveP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="loss_reserve_view"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.xlCostOurShareOC" />
												</div>
												<div class="tbox" align="right">
													<s:property value="xl_cost_view"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.premiumQS" />
												</div>
												<div class="tbox" align="right">
													<s:property value="premiumQuota_view"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.premiumSurplus" />
												</div>
												<div class="tbox" align="right">
													<s:property value="premiumsurp_view"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.settlementStatus" />
												</div>
												<div class="tbox">
													Pending
													<s:hidden name="settlement_status" value="Pending"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
									</s:if>
									<s:if test="productId == 3">
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.brokerageP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="brokerage_view"/>
													<s:hidden name="brokerage_S" value="brokerage_view" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.taxP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="tax_view"/>
													<s:hidden name="tax_S" value="tax_view" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.otherCostP" />
												</div>
												<div class="tbox" align="right">
													<s:property value="otherCostView"/>
													<s:hidden name="otherCost_S" value="otherCostView" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.MDPremium" />
												</div>
												<div class="tbox" align="right">
													<s:property value="md_premium_view"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.settlementStatus" />
												</div>
												<div class="tbox">
													Pending
													<s:hidden name="settlement_status" value="Pending"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
									</s:if>
								</div>
							</div>
							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.areYouEntering" />
												</div>
												<div class="tbox">
													<s:radio list="#{'1':'100%'}" name="enteringMode" />													
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.signed" />
												</div>
												<div class="tbox">
													<s:radio list="shareSigned" name="shareSigned"></s:radio>											
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="label.transactionDate" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
														<sj:datepicker name="transaction" id="transaction" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onblur="GetExchangeRate();" />
													</div>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.currency" />
												</div>
												<div class="tbox">
													<s:select name="currency" list="#{}" headerKey="" cssClass="inputBoxS" headerValue="---Select---" disabled="%{productId==1?true:false}" />												
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.cedantBrokerDocReference" />
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="cedentRef" />												
												</div>
											</div>											
											<div class="textfield">
												<div class="text">
													<s:text name="label.exchangeRate" />
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="exchRate" readonly="true" cssStyle="text-align:right;" />												
												</div>
											</div>
											<s:if test="productId == 1">
											<div class="textfield">
												<div class="text">
													<s:text name="label.statementReceivedDate" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
														<sj:datepicker name="inception_Date" id="inception_Date" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>												
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.installmentDate" />
												</div>
												<div class="tbox">
													<s:if test="transactionNo == ''">
														<s:select list="#{}" headerKey="" headerValue="---Select---" name="account_Period" cssClass="inputBoxS" onchange="GetFacInstalment(this.value);" />
													</s:if>
													<s:else>
														<s:textfield name="account_Period" readonly="true" cssClass="inputBox" />
													</s:else>
												</div>
											</div>
											</s:if>
											<s:else>
											<div class="textfield">
												<div class="text">
													<s:text name="label.statementReceivedDate" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
														<sj:datepicker name="inception_Date" id="inception_Date" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>												
												</div>
											</div>
											<s:if test="productId == 2">
											<div class="textfield">
												<div class="text">
													<s:text name="label.accountPeriod" />
												</div>
												<div class="tbox">													
													<s:select list="#{}" headerKey="" headerValue="---Select---" name="account_Period" onchange="GetFacInstalment(this.value);" cssClass="inputBoxS" cssStyle="width: 50%;" />
													<s:set name="insDate" id="date" />
													<s:select list="#{}" headerKey="" headerValue="Year" name="account_Period_year" cssClass="inputBoxS" cssStyle="width: 50%;" />
												</div>
											</div>
											<br class="clear"/>
											</s:if>
											<s:if test="productId == 3">
											<div class="textfield">
												<div class="text">
													<s:text name="label.installmentDate" />
												</div>
												<div class="tbox">		
													<s:if test="transactionNo == ''">											
													<s:select list="#{}" headerKey="" headerValue="---Select---" name="account_Period" onchange="GetMdInstalment(this.value);facNetDue()" cssClass="inputBoxS" />
													</s:if>
													<s:else>
													<s:textfield cssClass="inputBox" name="account_Period" readonly="true" />
													</s:else>
												</div>
											</div>
											</s:if>
											</s:else>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
											<table width="100%" class="table table-bordered">
												<thead>
												<tr>
													<th width="25%"></th>
													<th width="25%"><s:text name="label.credit" /></th>
													<th width="25%"></th>
													<th width="25%"><s:text name="label.debit" /></th>													
												</tr>
												</thead>
												<tbody>
												<s:if test="productId == 1">
												<tr>
													<td><s:text name="label.premium" /> </td>
													<td>
														<s:if test="account_Period == 'EP'">
														<s:textfield cssClass="inputBox" name="premiumQuotaShare" onblur="Caculate(this.value);facNetDue();" onkeyup="this.value=Comma(this.value);" cssStyle="text-align: right;" />
														</s:if>
														<s:else>
														<s:textfield cssClass="inputBox" name="premiumQuotaShare" onblur="Caculate(this.value);facNetDue();" onkeyup="this.value=Comma(this.value);" cssStyle="text-align: right;" readonly="true" />
														</s:else>
													</td>
													<td> <s:text name="label.commission" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="commissionQuotaShare" onblur="facNetDue()" onkeyup="this.value=Comma(this.value);" cssStyle="text-align: right;" />														
													</td>
												</tr>
												<tr>
													<td colspan="2"> </td>
													<td> <s:text name="label.brokerage" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="brokerage" onblur="facNetDue()" onkeyup="this.value=Comma(this.value);" cssStyle="text-align: right;" />
													</td>
												</tr>
												<tr>
													<td colspan="2"> </td>
													<td> <s:text name="label.tax" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="tax" onblur="facNetDue()" onkeyup="this.value=Comma(this.value);" cssStyle="text-align: right;" />
													</td>
												</tr>
												<tr>
													<td colspan="2"></td>
													<td> <s:text name="label.otherCost" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="otherCost" onblur="facNetDue()" onkeyup="this.value=Comma(this.value);" cssStyle="text-align: right;" />
													</td>
												</tr>
												<tr>
													<td> <s:text name="label.total" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="totalCredit" readonly="true" onkeyup="this.value=Comma(this.value);" cssStyle="text-align: right;" />
													</td>
													<td> <s:text name="label.total" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="totalDebit" readonly="true" onkeyup="this.value=Comma(this.value);" cssStyle="text-align: right;" />
													</td>
												</tr>
												<tr>
													<td colspan="2"> </td>
													<td> <s:text name="label.netDue" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="netDue" readonly="true" onkeyup="this.value=Comma(this.value);" cssStyle="text-align: right;" />
													</td>
												</tr>
												<tr>
													<td> <s:text name="label.reamrks" /> </td>
													<td colspan="3">
														<s:textarea rows="3" id="remarks" name="remarks" cssClass="inputBoxA" cssStyle="width: 100%;" />
														<br/>
														<span class="textAreaRemaining"><label id="remarks_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
													</td>
												</tr>
												</s:if>												
												</tbody>
											</table>										
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
						</div>
													
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button" value="Previous" class="btn btn-sm btn-warning" onclick="FunctionBackMode()">								
								<s:if test="amend_Id_Mode == '' && amend_Id_Mode == null">
									<input type="button" class="btn btn-sm btn-danger" onclick="CancelPage('%{proStatus}')" value="Cancel">									
									&nbsp;&nbsp;&nbsp;
									<input type="button" class="btn btn-sm btn-info" onclick="SavePage()" value="Save">
								</s:if>								
								<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="SubmitPage()">
							</div>
						</div>
						
						<s:hidden name="proposalno" value="%{proposal_no}" />
						<s:hidden name="baseLayer" value="%{baseLayer}" />
						<s:hidden name="layerProposalNo" value="%{layerProposalNo}" />
						<s:hidden name="brokerage" value="%{brokerage}" />
						<s:hidden name="tax" value="%{tax}" />
						<s:hidden name="contarctno" value="%{contNo}" />
						
						<s:hidden name="proposalType" />
						<s:hidden name="broker" />
						<s:hidden name="gms_Approval" />
						<s:hidden name="layerNo" />
						<s:hidden name="incepDate" />
						<s:hidden name="expDate" />
						<s:hidden name="limitOrigCur" />
						<s:hidden name="proposal_no" />
						<s:hidden name="layerProposalNo" />
						<s:hidden name="amend_Id_Mode" />
						<s:hidden name="baseLayer" />
						<s:hidden name="renewal_contract_no" />
						<s:hidden name="renewalFlag" />
						
						<s:hidden name="instlmentperod" value="line" />
						<s:hidden name="contractno" />
						<s:hidden name="lay" />
						<s:hidden name="baseLoginID" />
						<s:hidden name="amendId" />
						<s:hidden name="no_Insurer" />
						<s:hidden name="m_d_InstalmentNumber" />
						<s:hidden name="retroType" />
					</div>
										
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

		function insertupdate(mode)
		{ 
	 	document.getElementById("mode").value=mode;
		document.forms[0].action="<%=request.getContextPath()%>/PremiumInit.do?method=InsertAction&mode="+mode;
		document.forms[0].submit();
		}	 				
		function EditBack()
		{
		document.forms[0].action="<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=Init";
		document.forms[0].submit();
		}
				
		function EditMode()
		{
		document.forms[0].action="<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=PremiumSearch";
		}

		function back()
		{
		document.forms[0].action="<%=request.getContextPath()%>/PremiumInit.do?method=PremiumedList"
		document.forms[0].submit();
		}
		 function cancel()
   		{
  			   document.location='<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=Init';
 		}
		
		function Calculation(value)
		{
		var PremiumQshare=document.forms[0].premiumQuotaShare.value;
		PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
		var commssion=document.forms[0].commssion_S.value;
		var percentage=parseFloat(commssion)/100;
		var b=value.replace(new RegExp(',', 'g'),'');
		var a=parseFloat(b)*percentage;
	 	if(value!="")
		{
		document.forms[0].commissionQuotaShare.value=Comma(a.toFixed(2));
		}
		else
		{
		document.forms[0].commissionQuotaShare.value='';
		}
		var brokerage=document.forms[0].brokerage_S.value;
		var brokerpercentage=parseFloat(brokerage)/100;
	    var premiumSurplus=document.forms[0].premiumSurplus.value;
	    premiumSurplus=premiumSurplus.replace(new RegExp(',', 'g'),'');
			if(PremiumQshare!="" && premiumSurplus!="" )
			{
				var b=parseFloat(premiumSurplus)+parseFloat(PremiumQshare);
				var c=b*brokerpercentage;
				document.forms[0].brokerage.value=Comma(c.toFixed(2));
				var tax_view=document.forms[0].tax_view.value;
				var tax_percen=parseFloat(tax_view)/100;
				var d=b*tax_percen;
				document.forms[0].tax.value=Comma(d.toFixed(2));
				var otherCost=document.forms[0].otherCost_S.value;
				var percentage3=parseFloat(otherCost)/100;
				var e=b*percentage3;
				document.forms[0].otherCost.value=Comma(e.toFixed(2));
				var overrider=document.forms[0].overRider_S.value;
				var percentage4=parseFloat(overrider)/100;
				var f=b*percentage4;
				document.forms[0].overrider.value=Comma(f.toFixed(2));
				
			}
				else if(PremiumQshare!="")
				{
					var b=parseFloat(PremiumQshare);
					var c=b*brokerpercentage;
					document.forms[0].brokerage.value=Comma(c.toFixed(2));
					var tax_view=document.forms[0].tax_view.value;
					var tax_percen=parseFloat(tax_view)/100;
					var d=b*tax_percen;
					document.forms[0].tax.value=Comma(d.toFixed(2));
					var otherCost=document.forms[0].otherCost_S.value;
					var percentage3=parseFloat(otherCost)/100;
					var e=b*percentage3;
					document.forms[0].otherCost.value=Comma(e.toFixed(2));
					var overrider=document.forms[0].overRider_S.value;
					var percentage4=parseFloat(overrider)/100;
					var f=b*percentage4;
					document.forms[0].overrider.value=Comma(f.toFixed(2));
				}
					else if(premiumSurplus!="")
					{
						var b=parseFloat(premiumSurplus);
						var c=b*brokerpercentage;
						document.forms[0].brokerage.value=c.toFixed(2);
						var tax_view=document.forms[0].tax_view.value;
						var tax_percen=parseFloat(tax_view)/100;
						var d=b*tax_percen;
						document.forms[0].tax.value=d.toFixed(2);
						var otherCost=document.forms[0].otherCost_S.value;
						var percentage3=parseFloat(otherCost)/100;
						var e=b*percentage3;
						document.forms[0].otherCost.value=Comma(e.toFixed(2));
						var overrider=document.forms[0].overRider_S.value;
						var percentage4=parseFloat(overrider)/100;
						var f=b*percentage4;
						document.forms[0].overrider.value=Comma(f.toFixed(2));
					}
					else if(PremiumQshare== "" && premiumSurplus=="")
					{
						document.forms[0].commissionQuotaShare.value=''
					    document.forms[0].brokerage.value='';
						document.forms[0].tax.value='';
						document.forms[0].otherCost.value='';
						document.forms[0].overrider.value='';
					} 
					
					
	    
		}
		function Caculate(value)
		{
			if(value!=null && value!="")
			{
					var PremiumQshare=document.forms[0].premiumQuotaShare.value;
					PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
					var commssion=document.forms[0].commssion_S.value;
					var percentage=parseFloat(commssion)/100;
					var a=parseFloat(PremiumQshare)*percentage;
					document.forms[0].commissionQuotaShare.value=Comma(a.toFixed(2));
					var brokarage=document.forms[0].brokerage_S.value;
					var percentage1=parseFloat(brokarage)/100;
					var b=parseFloat(PremiumQshare)*percentage1;
					document.forms[0].brokerage.value=Comma(b.toFixed(2));
					var tax=document.forms[0].tax_S.value;
					var percentage2=parseFloat(tax)/100;
					var c=parseFloat(PremiumQshare)*percentage2;
					document.forms[0].tax.value=Comma(c.toFixed(2));
					var otherCost=document.forms[0].otherCost_S.value;
					var percentage3=parseFloat(otherCost)/100;
					var d=parseFloat(PremiumQshare)*percentage3;
					document.forms[0].otherCost.value=Comma(d.toFixed(2));
		    }
		    else
		    {
				    document.forms[0].commissionQuotaShare.value='';
				    document.forms[0].brokerage.value='';
				    document.forms[0].tax.value='';
				    document.forms[0].otherCost.value='';
			}
		}
        function GetMdInstalment(value)
        {
	        if(value!=0)
	        {
		        var contract=document.forms[0].contarctno.value;
		        var brokerage=document.forms[0].brokerage_S.value;
				var brokerpercentage=parseFloat(brokerage)/100;
				var tax_view=document.forms[0].tax_view.value;
				var tax_percen=parseFloat(tax_view)/100;
				var otherCost_view=document.forms[0].otherCost_S.value;
				var otherCost_percen=parseFloat(otherCost_view)/100;
		        if(value!="RP" && value!="AP")
		        {		        							        	
		        	document.getElementById("currId").value='<bean:write property="baseCurrencyId" name="PremiumActionForm"/>';
		        	document.getElementById("enable2").checked=true;
			       	if (window.XMLHttpRequest)
			  		{
			   			xmlhttp=new XMLHttpRequest();
			    	} 	
					else
					{
						xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.onreadystatechange=function()
					{
						if(xmlhttp.readyState==4 && xmlhttp.status==200)
						{
					 	document.forms[0].md_premium.value=xmlhttp.responseText;
					 	document.forms[0].recuirement_premium.style.display='None';
						document.forms[0].md_premium.style.display='Inline';
						document.forms[0].adjustment_premium.style.display='None';
						document.forms[0].recuirement_premium.value='';
						document.forms[0].adjustment_premium.value='';
						document.forms[0].brokerage.value='';
						document.forms[0].tax.value='';
						document.forms[0].otherCost.value='';
						var a=(parseFloat(document.forms[0].md_premium.value))*brokerpercentage;
						document.getElementById("BrokerAgeXol").value=Comma(a.toFixed(2));
						var b=parseFloat(document.forms[0].md_premium.value)*tax_percen;
						document.forms[0].tax.value=Comma(b.toFixed(2));
						var c=(parseFloat(document.forms[0].md_premium.value))*otherCost_percen;
						document.forms[0].otherCost.value=Comma(c.toFixed(2));
						xolNetDue();
						}
					}
					xmlhttp.open("POST",'<%=request.getContextPath()%>/PremiumInit.do?method=GetInstalmentPremium&contract='+contract+'&value='+value,true);
					xmlhttp.send();
				}
				else if(value=='RP')
				{
				 	document.forms[0].recuirement_premium.style.display='Inline';
					document.forms[0].md_premium.style.display='None';
					document.forms[0].adjustment_premium.style.display='None';
					document.forms[0].md_premium.value='';
					document.forms[0].recuirement_premium.value='';
					document.forms[0].adjustment_premium.value='';
					document.forms[0].brokerage.value='';
					document.forms[0].tax.value='';
					document.forms[0].otherCost.value='';
				}
				else if(value=='AP')
				{
					document.forms[0].adjustment_premium.style.display='Inline';
					document.forms[0].recuirement_premium.style.display='none';
					document.forms[0].md_premium.style.display='none';
					document.forms[0].adjustment_premium.value=document.forms[0].adjustment_premium_temp.value;
					document.forms[0].recuirement_premium.value='';
					document.forms[0].md_premium.value='';
					document.forms[0].brokerage.value='';
					document.forms[0].tax.value='';
					document.forms[0].otherCost.value='';
					document.forms[0].brokerage.value=(parseFloat(document.forms[0].adjustment_premium.value))*brokerpercentage;
					document.forms[0].tax.value=(parseFloat(document.forms[0].adjustment_premium.value))*tax_percen;
					document.forms[0].otherCost.value=(parseFloat(document.forms[0].adjustment_premium.value))*otherCost_percen;
					//document.forms[0].otherCost.value=Comma(c.toFixed(2));
				}
		    }
		    else
		    {
			    document.forms[0].md_premium.style.display='none';
			    document.forms[0].adjustment_premium.style.display='none';
			    document.forms[0].recuirement_premium.style.display='none';
			    document.forms[0].adjustment_premium.value='';
				document.forms[0].brokerage.value='';
				document.forms[0].tax.value='';
				document.forms[0].otherCost.value='';
		    }
	    
	    }
	     function GetFacInstalment(value)
        {
	        if(value!=0 && value!='EP')
	        {
//	        	    document.forms[0].premiumQuotaShare.setAttribute('readonly', 'readonly');
					var contract=document.forms[0].contarctno.value;
		        	document.getElementById("enable2").checked=true;
			       	if (window.XMLHttpRequest)
				  	{
				   		xmlhttp=new XMLHttpRequest();
				    } 	
					else
					{
						xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.onreadystatechange=function()
					{
						if(xmlhttp.readyState==4 && xmlhttp.status==200)
						{
						 	document.forms[0].premiumQuotaShare.value=Comma(xmlhttp.responseText);
						 	Caculate(document.forms[0].premiumQuotaShare.value);
						 	facNetDue();
						}
					}
					xmlhttp.open("POST",'<%=request.getContextPath()%>/PremiumInit.do?method=GetInstalmentPremium&contract='+contract+'&value='+value,true);
					xmlhttp.send();
			}else
			{
        	    document.forms[0].premiumQuotaShare.removeAttribute('readonly');
				document.forms[0].premiumQuotaShare.value="";
				Caculate(document.forms[0].premiumQuotaShare.value);
				facNetDue();
			}
	    }
		function calculationSurplus(value)
		{
			var premiumSurplus=document.forms[0].premiumSurplus.value;
			premiumSurplus=premiumSurplus.replace(new RegExp(',','g'),'');
			var commssion=document.forms[0].commssion_Surp.value;
			var percentage=parseFloat(commssion)/100;
			var a=parseFloat(premiumSurplus)*percentage;
			if(value!="")
			{
			document.forms[0].commissionSurplus.value=Comma(a.toFixed(2));
			}
			else
			{
			document.forms[0].commissionSurplus.value='';
			}
			var brokerage=document.forms[0].brokerage_S.value;
			var brokerpercentage=parseFloat(brokerage)/100;
			var PremiumQshare=document.forms[0].premiumQuotaShare.value;
			PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
		if(PremiumQshare!="" && premiumSurplus!="" )
		{
			var b=parseFloat(premiumSurplus)+parseFloat(PremiumQshare);
			var c=b*brokerpercentage;
			document.forms[0].brokerage.value=Comma(c.toFixed(2));
			var tax_view=document.forms[0].tax_view.value;
			var tax_percen=parseFloat(tax_view)/100;
			var d=b*tax_percen;
			document.forms[0].tax.value=Comma(d.toFixed(2));
			var otherCost=document.forms[0].otherCost_S.value;
			var percentage3=parseFloat(otherCost)/100;
			var e=b*percentage3;
			document.forms[0].otherCost.value=Comma(e.toFixed(2));
			var overrider=document.forms[0].overRider_S.value;
			var percentage4=parseFloat(overrider)/100;
			var f=b*percentage4;
			document.forms[0].overrider.value=Comma(f.toFixed(2));
		}
		else if(PremiumQshare!="")
		{
			var b=parseFloat(PremiumQshare);
			var c=b*brokerpercentage;
			document.forms[0].brokerage.value=Comma(c.toFixed(2));
			var tax_view=document.forms[0].tax_view.value;
			var tax_percen=parseFloat(tax_view)/100;
			var d=b*tax_percen;
			document.forms[0].tax.value=Comma(d.toFixed(2));
			var otherCost=document.forms[0].otherCost_S.value;
			var percentage3=parseFloat(otherCost)/100;
			var e=b*percentage3;
			document.forms[0].otherCost.value=Comma(e.toFixed(2));
			var overrider=document.forms[0].overRider_S.value;
			var percentage4=parseFloat(overrider)/100;
			var f=b*percentage4;
			document.forms[0].overrider.value=Comma(f.toFixed(2));
		}
		else if(premiumSurplus!="")
		{
			var b=parseFloat(premiumSurplus);
			var c=b*brokerpercentage;
			document.forms[0].brokerage.value=Comma(c.toFixed(2));
			var tax_view=document.forms[0].tax_view.value;
			var tax_percen=parseFloat(tax_view)/100;
			var d=b*tax_percen;
			document.forms[0].tax.value=d.toFixed(2);
			var otherCost=document.forms[0].otherCost_S.value;
			var percentage3=parseFloat(otherCost)/100;
			var e=b*percentage3;
			document.forms[0].otherCost.value=Comma(e.toFixed(2));
			var overrider=document.forms[0].overRider_S.value;
			var percentage4=parseFloat(overrider)/100;
			var f=b*percentage4;
			document.forms[0].overrider.value=Comma(f.toFixed(2));
		}
		else if(PremiumQshare== "" && premiumSurplus=="")
		{
		document.forms[0].tax.value='';
		document.forms[0].brokerage.value='';
		document.forms[0].commissionSurplus.value='';
		document.forms[0].otherCost.value='';
		document.forms[0].overrider.value='';
		}
	
	 }
		 
		function ReInstmentPremium(Amount)
		{
	 	 	if(Amount!=null && Amount!="")
			{
		 	if(document.forms[0].adjustment_premium.style.display='None')
		 	{
				var brokerage=document.forms[0].brokerage_S.value;
				var brokerpercentage=parseFloat(brokerage)/100;
				var tax_view=document.forms[0].tax_view.value;
				var tax_percen=parseFloat(tax_view)/100;
				Amount=Amount.replace(new RegExp(',','g'),'');
				document.getElementById("BrokerAgeXol").value=Comma(((parseFloat(Amount))*brokerpercentage).toFixed(2));
				document.forms[0].tax.value=Comma((parseFloat(Amount)*tax_percen).toFixed(2));
				var otherCost=document.forms[0].otherCost_S.value;
				var percentage3=parseFloat(otherCost)/100;
				var e=parseFloat(Amount)*percentage3;
				document.forms[0].otherCost.value=Comma(e.toFixed(2));
			} 
			}else
			{
			document.getElementById("BrokerAgeXol").value='';
			document.forms[0].tax.value='';
			document.forms[0].otherCost.value='';
			}
		}
		
		
		function AdjustmentPremium(value)
		{
		if(value=='AP')
		{
		document.forms[0].adjustment_premium.style.display='inline'
		document.forms[0].recuirement_premium.style.display='None';
		document.forms[0].md_premium.style.display='none';
		}
		else if(value=='RP')
		{
		document.forms[0].recuirement_premium.style.display='Inline';
		document.forms[0].adjustment_premium.style.display='None';
		document.forms[0].md_premium.style.display='none';
		}
		else if(value==0)
		{
		document.forms[0].recuirement_premium.style.display='None';
		document.forms[0].adjustment_premium.style.display='None';
		document.forms[0].md_premium.style.display='none';
		}
		else 
		{
		document.forms[0].md_premium.style.display='inline';
		document.forms[0].recuirement_premium.style.display='None';
		document.forms[0].adjustment_premium.style.display='None'
		}
		}
		function CalculationXOL(value)
		{
		if(value!=null && value!="")
		{
		value=value.replace(new RegExp(',','g'),'');
		var brokarage=document.forms[0].brokerage_S.value;
		brokarage=brokarage.replace(new RegExp(',','g'),'');
		var percentage1=parseFloat(brokarage)/100;
		var b=parseFloat(value)*percentage1;
		document.forms[0].brokerage.value=Comma(b.toFixed(2));
		var tax=document.forms[0].tax_S.value;
		var percentage2=parseFloat(tax)/100;
		var c=parseFloat(value)*percentage2;
		document.forms[0].tax.value=Comma(c.toFixed(2));
		var otherCost=document.forms[0].otherCost_S.value;
		var percentage3=parseFloat(otherCost)/100;
		var e=parseFloat(value)*percentage3;
		document.forms[0].otherCost.value=Comma(e.toFixed(2));
	    }
	    else
	    {
    		document.forms[0].brokerage.value='';
    		document.forms[0].tax.value='';
    		document.forms[0].otherCost.value='';
	    }
		
		}
		
		function GetExchangeRate()
		{
				var month=document.forms[0].transaction.value;
				//var year=document.forms[0].transaction_year.value;
				var Currecny=document.getElementById("currId").value;
				for(var i=0;;i++){
					if(document.getElementById("currencyIdDis"+i)!=null){
						document.getElementById("currencyIdDis"+i).value=Currecny;
						document.getElementById("currencyId"+i).value=Currecny;
					}else
						break;
				}
			 	if(month!=null && month!="" && Currecny!="0" )
				{
					document.forms[0].currencyId.value=Currecny;
					//var InceptionDate=month+"-"+year;
				  
					if (window.XMLHttpRequest)
					 {
						 xmlhttp=new XMLHttpRequest();
					 } 	
					else
					{
						xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.onreadystatechange=function()
					{
						if (xmlhttp.readyState==4 && xmlhttp.status==200)
						{
						 	document.forms[0].exchRate.value=xmlhttp.responseText;
						}
					}
					xmlhttp.open("POST",'<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=getExcRate&InceptionDate='+month+'&Currecny='+Currecny,true);
					xmlhttp.send();
			}else
			{
						document.forms[0].exchRate.value='';
			}
		}
			function Commas(value)
		{
		
		  	if(value=="2")
			{
			    <%--if(ClaimNos.size()==0){--%>
			    document.forms[0].cashLoss_Credit.value='0';
			    document.forms[0].cashLoss_Credit.setAttribute('readonly', 'readonly');
			    <%--}--%>
			    toggleCashLossCredit(document.forms[0].cashLoss_Credit.value);
			    document.forms[0].cashLoss_Credit.value=Comma(document.forms[0].cashLoss_Credit.value);
			    document.forms[0].cliam_portfolio_out.value=Comma(document.forms[0].cliam_portfolio_out.value);
			    document.forms[0].premiumportifolioout.value=Comma(document.forms[0].premiumportifolioout.value);
			    document.forms[0].premiumReserve_QuotaShare.value=Comma(document.forms[0].premiumReserve_QuotaShare.value);
			    document.forms[0].profit_Commission.value=Comma(document.forms[0].profit_Commission.value);
			    
			    document.forms[0].cash_LossPaid.value=Comma(document.forms[0].cash_LossPaid.value);
			    document.forms[0].claims_paid.value=Comma(document.forms[0].claims_paid.value);
			    document.forms[0].premiumSurplus.value=Comma(document.forms[0].premiumSurplus.value);
			    document.forms[0].commissionSurplus.value=Comma(document.forms[0].commissionSurplus.value);
			    document.forms[0].premiumportifolioIn.value=Comma(document.forms[0].premiumportifolioIn.value);
			    
			    document.forms[0].otherCost.value=Comma(document.forms[0].otherCost.value);
			    document.forms[0].lossReserveReleased.value=Comma(document.forms[0].lossReserveReleased.value);
			    document.forms[0].xl_Cost.value=Comma(document.forms[0].xl_Cost.value);
			    document.forms[0].brokerage.value=Comma(document.forms[0].brokerage.value);
			    document.forms[0].premium_Reserve_Released.value=Comma(document.forms[0].premium_Reserve_Released.value);

			    document.forms[0].tax.value=Comma(document.forms[0].tax.value);
			    document.forms[0].cliamPortfolioin.value=Comma(document.forms[0].cliamPortfolioin.value);
			    document.forms[0].loss_ReserveRetained.value=Comma(document.forms[0].loss_ReserveRetained.value);
			    document.forms[0].commissionQuotaShare.value=Comma(document.forms[0].commissionQuotaShare.value);
			    document.forms[0].premiumQuotaShare.value=Comma(document.forms[0].premiumQuotaShare.value);
			    document.forms[0].netDue.value=Comma(document.forms[0].netDue.value);
			    proposalNetDue();
			}
		  	else if(value=="3")
			{
				    document.forms[0].otherCost.value=Comma(document.forms[0].otherCost.value);
				    document.forms[0].brokerage.value=Comma(document.forms[0].brokerage.value);
					document.forms[0].tax.value=Comma(document.forms[0].tax.value);
					document.forms[0].recuirement_premium.value=Comma(document.forms[0].recuirement_premium.value);
					document.forms[0].md_premium.value=Comma(document.forms[0].md_premium.value);
					document.forms[0].adjustment_premium.value=Comma(document.forms[0].adjustment_premium.value);
					AdjustmentPremium(document.forms[0].account_Period.value);
					xolNetDue();
		    }
		  	else if(value=="1")
		  	{
		  		document.forms[0].premiumQuotaShare.value=Comma(document.forms[0].premiumQuotaShare.value);
		  		document.forms[0].commissionQuotaShare.value=Comma(document.forms[0].commissionQuotaShare.value);
		  	  	document.forms[0].otherCost.value=Comma(document.forms[0].otherCost.value);
			    document.forms[0].brokerage.value=Comma(document.forms[0].brokerage.value);
				document.forms[0].tax.value=Comma(document.forms[0].tax.value);
				document.forms[0].netDue.value=Comma(document.forms[0].netDue.value);
				facNetDue()
            }
				
		}
		function facNetDue()
		{
		
				var PremiumQshare=document.forms[0].premiumQuotaShare.value;
				if(PremiumQshare!=null && PremiumQshare!="")
					PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
				else 
					PremiumQshare=0;
				var CommissionQshare=document.forms[0].commissionQuotaShare.value;
				if(CommissionQshare!=null && CommissionQshare!="")
					CommissionQshare=CommissionQshare.replace(new RegExp(',', 'g'),'');
				else
					CommissionQshare=0;
				var Brokerage=document.forms[0].brokerage.value;
				if(Brokerage!=null && Brokerage!="")
					Brokerage=Brokerage.replace(new RegExp(',', 'g'),'');
				else
				 	Brokerage=0;
				
				var Tax=document.forms[0].tax.value;
				if(Tax!=null && Tax!="")
					Tax=Tax.replace(new RegExp(',', 'g'),'');
				else
				 	Tax=0;
				var OtherCost=document.forms[0].otherCost.value;
				if(OtherCost!=null && OtherCost!="")
					OtherCost=OtherCost.replace(new RegExp(',', 'g'),'');
				else
				 	OtherCost=0;				
				var A=parseFloat(PremiumQshare);
				var B=parseFloat(CommissionQshare)+parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(OtherCost)
				document.forms[0].totalCredit.value=Comma(A.toFixed(2));
				document.forms[0].totalDebit.value=Comma(B.toFixed(2));
				var NetDue=parseFloat(PremiumQshare)-parseFloat(CommissionQshare)-parseFloat(Brokerage)-parseFloat(Tax)-parseFloat(OtherCost);
				document.forms[0].netDue.value=Comma(NetDue.toFixed(2));
		}
		function proposalNetDue()
		{
				var PremiumQshare=document.forms[0].premiumQuotaShare.value;
				if(PremiumQshare!=null && PremiumQshare!="")
					PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
				else 
					PremiumQshare=0;
				var PremiumSurplus=document.forms[0].premiumSurplus.value;
				if(PremiumSurplus!=null && PremiumSurplus!="")
					PremiumSurplus=PremiumSurplus.replace(new RegExp(',', 'g'),'');
				else
				 	PremiumSurplus=0;
				
				var PremiumportifolioIn=document.forms[0].premiumportifolioIn.value;
				if(PremiumportifolioIn!=null && PremiumportifolioIn!="")
					PremiumportifolioIn=PremiumportifolioIn.replace(new RegExp(',', 'g'),'');
				else
				 	PremiumportifolioIn=0;
				
				var CliamPortfolioin=document.forms[0].cliamPortfolioin.value;
				if(CliamPortfolioin!=null && CliamPortfolioin!="")
					CliamPortfolioin=CliamPortfolioin.replace(new RegExp(',', 'g'),'');
				else
				 	CliamPortfolioin=0;
				var Premium_Reserve_Released=document.forms[0].premium_Reserve_Released.value;
				if(Premium_Reserve_Released!=null && Premium_Reserve_Released!="")
					Premium_Reserve_Released=Premium_Reserve_Released.replace(new RegExp(',', 'g'),'');
				else
				 	Premium_Reserve_Released=0;
				var LossReserveReleased=document.forms[0].lossReserveReleased.value;
				if(LossReserveReleased!=null && LossReserveReleased!="")
					LossReserveReleased=LossReserveReleased.replace(new RegExp(',', 'g'),'');
				else
				 	LossReserveReleased=0;
				var Interest=document.forms[0].interest.value;
				if(Interest!=null && Interest!="")
					Interest=Interest.replace(new RegExp(',', 'g'),'');
				else
				 	Interest=0; 	
				var CashLoss_Credit=document.forms[0].cashLoss_Credit.value;
				if(CashLoss_Credit!=null && CashLoss_Credit!="")
					CashLoss_Credit=CashLoss_Credit.replace(new RegExp(',', 'g'),'');
				else
				 	CashLoss_Credit=0;
			
				
				var CommissionQshare=document.forms[0].commissionQuotaShare.value;
				if(CommissionQshare!=null && CommissionQshare!="")
					CommissionQshare=CommissionQshare.replace(new RegExp(',', 'g'),'');
				else
					CommissionQshare=0;
				
				var CommissionSurplus=document.forms[0].commissionSurplus.value;
				if(CommissionSurplus!=null && CommissionSurplus!="")
					CommissionSurplus=CommissionSurplus.replace(new RegExp(',', 'g'),'');
				else
				 	CommissionSurplus=0;
				var Brokerage=document.forms[0].brokerage.value;
				if(Brokerage!=null && Brokerage!="")
					Brokerage=Brokerage.replace(new RegExp(',', 'g'),'');
				else
				 	Brokerage=0;
				var Tax=document.forms[0].tax.value;
				if(Tax!=null && Tax!="")
					Tax=Tax.replace(new RegExp(',', 'g'),'');
				else
				 	Tax=0;
				var overrider=document.forms[0].overrider.value;
				if(overrider!=null && overrider!="")
					overrider=overrider.replace(new RegExp(',', 'g'),'');
				else
				 	overrider=0; 	
				
				var OtherCost=document.forms[0].otherCost.value;
				if(OtherCost!=null && OtherCost!="")
					OtherCost=OtherCost.replace(new RegExp(',', 'g'),'');
				else
				 	OtherCost=0;
				
				var Xl_Cost=document.forms[0].xl_Cost.value;
				if(Xl_Cost!=null && Xl_Cost!="")
					Xl_Cost=Xl_Cost.replace(new RegExp(',', 'g'),'');
				else
				 	Xl_Cost=0;
				var Premiumportifolioout=document.forms[0].premiumportifolioout.value;
				if(Premiumportifolioout!=null && Premiumportifolioout!="")
					Premiumportifolioout=Premiumportifolioout.replace(new RegExp(',', 'g'),'');
				else
				 	Premiumportifolioout=0;
				var Cliam_portfolio_out=document.forms[0].cliam_portfolio_out.value;
				if(Cliam_portfolio_out!=null && Cliam_portfolio_out!="")
					Cliam_portfolio_out=Cliam_portfolio_out.replace(new RegExp(',', 'g'),'');
				else
				 	Cliam_portfolio_out=0;
				
				var PremiumReserve_QuotaShare=document.forms[0].premiumReserve_QuotaShare.value;
				if(PremiumReserve_QuotaShare!=null && PremiumReserve_QuotaShare!="")
					PremiumReserve_QuotaShare=PremiumReserve_QuotaShare.replace(new RegExp(',', 'g'),'');
				else
				 	PremiumReserve_QuotaShare=0;
				var Loss_ReserveRetained=document.forms[0].loss_ReserveRetained.value;
				if(Loss_ReserveRetained!=null && Loss_ReserveRetained!="")
					Loss_ReserveRetained=Loss_ReserveRetained.replace(new RegExp(',', 'g'),'');
				else
				 	Loss_ReserveRetained=0;
				var Profit_Commission=document.forms[0].profit_Commission.value;
				if(Profit_Commission!=null && Profit_Commission!="")
					Profit_Commission=Profit_Commission.replace(new RegExp(',', 'g'),'');
				else
				 	Profit_Commission=0;
				
				var Cash_LossPaid=document.forms[0].cash_LossPaid.value;
				if(Cash_LossPaid!=null && Cash_LossPaid!="")
					Cash_LossPaid=Cash_LossPaid.replace(new RegExp(',', 'g'),'');
				else
				 	Cash_LossPaid=0;
				var Claims_paid=document.forms[0].claims_paid.value;
				if(Claims_paid!=null && Claims_paid!="")
					Claims_paid=Claims_paid.replace(new RegExp(',', 'g'),'');
				else
				 	Claims_paid=0;
				var A=parseFloat(PremiumQshare)+parseFloat(PremiumSurplus)+parseFloat(PremiumportifolioIn)+parseFloat(CliamPortfolioin)+parseFloat(Premium_Reserve_Released)+parseFloat(LossReserveReleased)+parseFloat(Interest)+parseFloat(CashLoss_Credit);
				var B=parseFloat(CommissionQshare)+parseFloat(CommissionSurplus)+parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(overrider)+parseFloat(OtherCost)+parseFloat(Xl_Cost)+parseFloat(Premiumportifolioout)+parseFloat(Cliam_portfolio_out)+parseFloat(PremiumReserve_QuotaShare)+parseFloat(Loss_ReserveRetained)+parseFloat(Profit_Commission)+parseFloat(Cash_LossPaid)+parseFloat(Claims_paid);
				document.forms[0].totalCredit.value=Comma(A.toFixed(2));
				document.forms[0].totalDebit.value=Comma(B.toFixed(2));
				var NetDue=(parseFloat(PremiumQshare)+parseFloat(PremiumSurplus)+parseFloat(PremiumportifolioIn)+parseFloat(CliamPortfolioin)+parseFloat(Premium_Reserve_Released)+parseFloat(LossReserveReleased)+parseFloat(Interest)+parseFloat(CashLoss_Credit))-(parseFloat(CommissionQshare)+parseFloat(CommissionSurplus)+parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(overrider)+parseFloat(OtherCost)+parseFloat(Xl_Cost)+parseFloat(Premiumportifolioout)+parseFloat(Cliam_portfolio_out)+parseFloat(PremiumReserve_QuotaShare)+parseFloat(Loss_ReserveRetained)+parseFloat(Profit_Commission)+parseFloat(Cash_LossPaid)+parseFloat(Claims_paid));
				document.forms[0].netDue.value=Comma(NetDue.toFixed(2));
		}
		function xolNetDue()
		{
				var insDate=document.forms[0].account_Period.value;
				if(insDate!="0")
				{
				var Premium;
				if(insDate=='RP')
				{
				Premium =document.forms[0].recuirement_premium.value;
				if(Premium!=null && Premium!="")
					Premium=Premium.replace(new RegExp(',', 'g'),'');
				else
				 	Premium=0;
				}else if(insDate=='AP')
				{
					Premium =document.forms[0].adjustment_premium.value;
				if(Premium!=null && Premium!="")
					Premium=Premium.replace(new RegExp(',', 'g'),'');
				else
				 	Premium=0;
				
				}else
				{
					Premium =document.forms[0].md_premium.value;
				if(Premium!=null && Premium!="")
					Premium=Premium.replace(new RegExp(',', 'g'),'');
				else
				 	Premium=0;
				}
				var Brokerage=document.forms[0].brokerage.value;
				if(Brokerage!=null && Brokerage!="")
					Brokerage=Brokerage.replace(new RegExp(',', 'g'),'');
				else
				 	Brokerage=0;
				
				var Tax=document.forms[0].tax.value;
				if(Tax!=null && Tax!="")
					Tax=Tax.replace(new RegExp(',', 'g'),'');
				else
				 	Tax=0;
				var OtherCost=document.forms[0].otherCost.value;
				if(OtherCost!=null && OtherCost!="")
					OtherCost=OtherCost.replace(new RegExp(',', 'g'),'');
				else
				 	OtherCost=0;	
				var A=NetDue=parseFloat(Premium);
				var B=parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(OtherCost);
				document.forms[0].totalCredit.value=Comma(A.toFixed(2));
				document.forms[0].totalDebit.value=Comma(B.toFixed(2)); 
				var NetDue=parseFloat(Premium)-parseFloat(Brokerage)-parseFloat(Tax)-parseFloat(OtherCost);
				document.forms[0].netDue.value=Comma(NetDue.toFixed(2));
				}
		}
		function chkPremiumQS(){
		if(document.forms[0].premiumQuotaShare.value==null || document.forms[0].premiumQuotaShare.value==""){
			document.forms[0].commissionQuotaShare.value="";
		}
		}
		
		function chkPremiumSurplus(){
			if(document.forms[0].premiumSurplus.value==null || document.forms[0].premiumSurplus.value==""){
				document.forms[0].commissionSurplus.value="";
			}
		}
		function getViewContractDetails(prodId,proposalNo,amendId)
	    {
	    	var URL ='';
	    	if(prodId=='1'){
 				URL ='<%=request.getContextPath()%>/FaculitivesDispatchAction.do?method=ViewMethod&proposalno='+proposalNo+'&flag=PR&amendId='+amendId;
 			}else
 			{
 				URL ='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=ViewMethod&proposalno='+proposalNo+'&flag=PR&amendId='+amendId;
 			}
			var windowName = "Contract Details";
			var width  = screen.availWidth;
			var height = screen.availHeight;
			var w = 800;
			var h = 400;
			var features =
				'width='          + w +
				',height='		  + h +
				',left='		  + ((width - w - 10) * .5)  +
				',top='			  + ((height - h - 30) * .5) +
				',directories=no' +
				',location=no'	  +
				',menubar=no'	  +
				',scrollbars=yes' +
				',status=no'	  +
				',toolbar=no'	  +
				',resizable=no';
			var strOpen = window.open (URL, windowName, features);
			strOpen.focus();
			return false;
	}
	function toggleCashLossCredit(val)
	{
		if(val!=null && val!="")
			val=val.replace(new RegExp(',', 'g'),'');
		else
		 	val=0;
		if(val>0){
			document.getElementById('CashLossCreditOC').style.display='block';
		}else
		{
			document.getElementById('CashLossCreditOC').style.display='none';
		}
	
	}
	
	$(function() {
		    	
	    $('#remarks').keyup(function() {
	        update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
	    });	    
	    update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
	    
	});

	function update_chars_left(max_len, target_input, display_element) {
	   var text_len = target_input.value.length;
	   if (text_len >= max_len) {
	       target_input.value = target_input.value.substring(0, max_len); // truncate
	       display_element.html("0");
	   } else {
	       display_element.html(max_len - text_len);
	   }
	}
</script>		
</body>
</html>
