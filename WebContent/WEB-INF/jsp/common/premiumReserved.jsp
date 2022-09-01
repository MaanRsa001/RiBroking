<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">		    
		<link rel="stylesheet" type="text/css" href="css/select2-3.4.1.css"/>
    	<link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>	
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">
                <s:form id="premiumreserved" name="premiumreserved" theme="simple" action="" method="post" >
                <div class="boxcontent" >
					<div class="panel panel-primary">
                		<div class="panel-heading">
							<s:if test='"PRR".equals(type)'>
							Premium Reserved
							</s:if>
							<s:elseif test='"LRR".equals(type)'>
							Loss Reserved
							</s:elseif>			
						</div>
                   <div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
                    <div class="panel-body">
								<div class="row">
								<div class="col-xs-12">
								<s:if test="premiumReservedList!=null && premiumReservedList.size>0">
								   <table   class="footable"  width="100%" >
									<thead>
									<tr>
										<th><s:text name="Check Box" /></th>
										<th class="no-sort"><s:text name="Sr No."/></th>
										<th><s:text name="Contract No" /></th>
										<th><s:text name="Rt. Transaction Number" /></th>
										<th><s:text name="Rt. Transaction Date" /></th>
										<th><s:text name="Rt. Currency" /></th>
										<th><s:text name="Rt. Amount" /></th>
										<th><s:text name="Rl. Currency" /></th>
										<th><s:text name="Rl. Amount" /></th>
										<th><s:text name="Equivalent Amount in Rt. Currency" /></th>
										<th><s:text name="Exchange Rate" /></th>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="premiumReservedList" var="list" status="stat">
											<tr>
												<td align="center"><s:checkbox name="chkbox[%{#stat.index}]" id="chkbox[%{#stat.index}]" fieldValue="true" value="%{check}"/></td><td><s:property value="#stat.count" /></td>
												<td align="center"><s:property value="contNo" /></td>
												<td align="center"><s:property value="transactionNo" /><s:hidden name="claimPaymentNos[%{#stat.index}]" value="%{transactionNo}"/></td>
												<td align="center"><s:property value="paidDate" /></td>
												<td align="center"><s:property value="currencyValueName" /></td>
												<td align="right"><s:property value="payamount" /><s:hidden name="payamountList[%{#stat.index}]" value="%{payamount}"/></td>
												<td align="center"><s:property value="currencyIdName" /></td>
												<td><s:textfield name="creditAmountCLClist[%{#stat.index}]" id="creditAmountCLClist[%{#stat.index}]" cssClass="inputBox" cssStyle="text-align:right;" theme="simple" value="%{creditAmountCLC}" onblur="this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);" readonly="true" /></td>
												<td><s:textfield name="creditAmountCLDlist[%{#stat.index}]" id="creditAmountCLDlist[%{#stat.index}]" cssClass="inputBox" cssStyle="text-align:right;" theme="simple" value="%{creditAmountCLD}" onblur="this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);setAmount(this.id,'creditAmountCLDlist[%{#stat.index}]','CLCsettlementRatelist[%{#stat.index}]','creditAmountCLClist[%{#stat.index}]')"  /></td>
												<td><s:textfield name="CLCsettlementRatelist[%{#stat.index}]" id="CLCsettlementRatelist[%{#stat.index}]"  cssClass="inputBox" cssStyle="text-align:right;" theme="simple" value="%{CLCsettlementRate}" readonly="%{status}" onkeyup="allow8DigitDecValues(this);setAmount(this.id,'creditAmountCLDlist[%{#stat.index}]','CLCsettlementRatelist[%{#stat.index}]','creditAmountCLClist[%{#stat.index}]')" /></td>
												<s:hidden name="prallocatedTillList[%{#stat.index}]" value="%{prallocatedTillDate}"/>
											</tr>
										</s:iterator>
									</tbody>
									</table>
									</s:if>
									<s:else>Nothing found to display</s:else>
								</div>	
								<s:hidden name="contNo"/>
								<s:hidden name="currencyId"/>
								<s:hidden name="transaction"/>
								<s:hidden name="type"/>
										
							</div>
							<br>
							<div class="boxcontent" align="center">
							<s:if test="premiumReservedList!=null && premiumReservedList.size>0">
							<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="fnSubmit();" />
							</s:if>
							<input type="button" value="Close" class="btn btn-sm btn-danger" onclick="window.close()" />
					</div>
						</div>
                    </div>
                    </div>
                    </s:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function fnSubmit(){
	document.premiumreserved.action="${pageContext.request.contextPath}/submitPremiumReservedProportionPremium.action";
	document.premiumreserved.submit();
}
function loadValueFromParent() {
            document.getElementById('tb1').value =  window.opener.document.getElementById('childWindowValue1').value;
            document.getElementById('tb2').value =  window.opener.document.getElementById('childWindowValue2').value;
            document.getElementById('tb3').value =  window.opener.document.getElementById('childWindowValue3').value;
            document.getElementById('tb4').value =  window.opener.document.getElementById('childWindowValue4').value;
        }

function setAmount(id,val,val1,val2){
	var amount=document.getElementById(val).value; 
	var amount1=document.getElementById(val1).value;
	amount=amount.replace(new RegExp(',', 'g'),'');
	amount1=amount1.replace(new RegExp(',', 'g'),'');
	if(amount!='' && amount1!=''){
	var amount3=parseFloat(amount)/parseFloat(amount1);
		document.getElementById(val2).value=Comma(amount3.toFixed(2));
	}else{
		document.getElementById(val2).value='';
	}
}
$("input[type='checkbox']").on('change', function(){
  $(this).val(this.checked ? "true" : "false");
})
</script>
</body>
</html>
