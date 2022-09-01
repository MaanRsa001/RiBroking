<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
                <s:form id="clcredit" name="clcredit" theme="simple" action="" method="post" >
                   <div class="boxcontent" >
					<div class="panel panel-primary">
                		<div class="panel-heading">
									Cash Loss Credit	
						</div>
		                  <div class="tablerow">
								<span style="color:red;"><s:actionerror/></span>
							</div>
							
		                   <div class="panel-body">
		                   		<div class="row" style="padding:10px">
			                   		<div class="textfield">
										<div class="text">
											<s:text name="Type" />
										</div>
										<div class="tbox">
											<s:radio list="#{'A':'Allocation','R':'Reversal'}" name="cashlossType" id="cashlossType" onclick="GetCashLossType(this.value)" value="%{(cashlossType==null || cashlossType=='')?'A':cashlossType}"  /><%--disabled='%{("Y".equals(disableStatus1))?true:false}' --%>
										</div>
									</div>
									<div class="textfield">
										<div class="text">
											<s:textarea name="contractsearch" id="contractsearch" cssClass="inputBox"/>
										</div>
										<div class="tbox">
											<input type="button" value="Search" class="btn btn-sm btn-success" onclick="fnContract();" />
										</div>
									</div>
								</div>
								<div class="row" id="allocation">
									<div class="col-xs-12">
									<s:if test="CashlossCreditList!=null && CashlossCreditList.size()>0">
									   <table class="footable" width="100%" cellspacing="0">
										<thead>
										<tr>
											<th><s:text name="Check Box" /></th>
											<th class="no-sort"><s:text name="S No."/></th>
											<th><s:text name="Contract No" /></th>
											<th><s:text name="Claim No" /></th>
											<th><s:text name="Claim Payment No" /></th>
											<th><s:text name="Paid Date" /></th>
											<th><s:text name="CLD Currency" /></th>
											<th><s:text name="CLD Amount" /></th>
											<th><s:text name="CLC Currency" /></th>
											<th><s:text name="Credit Amount in CLC currency" /></th>
											<th><s:text name="Credit Amount in CLD currency" /></th>
											<th><s:text name="CLC Settlement Rate" /></th>
											
										</tr>
										</thead>
										<tbody>
											<s:iterator value="CashlossCreditList" var="list" status="stat">
												<tr>
													<td align="center"><s:checkbox name="chkbox[%{#stat.index}]" id="chkbox[%{#stat.index}]" fieldValue="true" value="%{check}" onclick="setdefaultAmount(%{#stat.index},id)"/></td><td><s:property value="#stat.count" /></td>
													<td align="center"><s:property value="contNo" /></td>
													<td align="center"><s:property value="claimNumber" /></td>
													<td align="center"><s:property value="claimPaymentNo" /><s:hidden name="claimPaymentNos[%{#stat.index}]" value="%{claimPaymentNo}"/></td>
													<td align="center"><s:property value="paidDate" /></td>
													<td align="center"><s:property value="currencyValueName" /><s:hidden name="currencyValueNameT[%{#stat.index}]" id="currencyValueNameT[%{#stat.index}]" value="%{currencyValueName}"/></td>
													<td align="right"><s:property value="payamount" /><s:hidden name="payamountList[%{#stat.index}]" value="%{payamount}"/></td>
													<td align="center"><s:property value="currencyIdName" /><s:hidden name="currencyIdNameT[%{#stat.index}]" id="currencyIdNameT[%{#stat.index}]" value="%{currencyIdName}"/></td>
													<td><s:textfield name="creditAmountCLClist[%{#stat.index}]" id="creditAmountCLClist[%{#stat.index}]" theme="simple" value="%{creditAmountCLC}" cssClass="inputBox" onblur="this.value=Comma(this.value);"  cssStyle="text-align:right;" readonly="true" onchange="setCLDAmount('%{#stat.index}');setAmount(this.id,'creditAmountCLClist[%{#stat.index}]','creditAmountCLDlist[%{#stat.index}]','CLCsettlementRatelist[%{#stat.index}]','%{#stat.index}');checkRage('%{#stat.index}');"/>
														<s:hidden name="creditAmountCLClistT[%{#stat.index}]" id="creditAmountCLClistT[%{#stat.index}]" value="%{creditAmountCLCTemp}"/>
													</td>
													<td><s:textfield name="creditAmountCLDlist[%{#stat.index}]" id="creditAmountCLDlist[%{#stat.index}]" theme="simple" value="%{creditAmountCLD}" cssClass="inputBox" onblur="this.value=Comma(this.value);"  cssStyle="text-align:right;" readonly="true"  onchange="setAmount(this.id,'creditAmountCLClist[%{#stat.index}]','creditAmountCLDlist[%{#stat.index}]','CLCsettlementRatelist[%{#stat.index}]','%{#stat.index}');checkRage('%{#stat.index}');"/>
														<s:hidden name="creditAmountCLDlistT[%{#stat.index}]" id="creditAmountCLDlistT[%{#stat.index}]" value="%{creditAmountCLDTemp}"/>
													</td>
													<td><s:textfield name="CLCsettlementRatelist[%{#stat.index}]" id="CLCsettlementRatelist[%{#stat.index}]" theme="simple" value="%{CLCsettlementRate}" cssClass="inputBox" cssStyle="text-align:right;" readonly="true" onchange="setAmount(this.id,'creditAmountCLClist[%{#stat.index}]','CLCsettlementRatelist[%{#stat.index}]','creditAmountCLDlist[%{#stat.index}]','%{#stat.index}')"/>
													<s:hidden name="CLCsettlementRatelistT[%{#stat.index}]" id="CLCsettlementRatelistT[%{#stat.index}]" value="%{CLCsettlementRateTemp}"/>
													<s:hidden name="excessRateTemp[%{#stat.index}]" id="excessRateTemp[%{#stat.index}]" value="%{excessRatePercent}"/>
													</td>
												</tr>
											</s:iterator>
										</tbody>
										</table>
										</s:if>
										<s:else>Nothing found to display</s:else>
									</div>
									<br/><br/>
								<div class="boxcontent" align="center">
									<s:if test="CashlossCreditList!=null && CashlossCreditList.size()>0">
									<input type="button" value="Allocate" class="btn btn-sm btn-success" onclick="fnSubmit();" />
									</s:if>
								</div>	
							</div>
							<div class="row" id="reversal" style="display:none;padding:10px">
								<s:iterator value="allocatedTransList" var="alloted" status="stat">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<div  id="plus<s:property value="%{#stat.count}"/>" onclick="normalForm('1','<s:property value="%{#stat.count}"/>');" style="display: block; cursor: pointer;">
											<span data-target="#menuInfo_0<s:property value="%{#stat.count}"/>" data-toggle="collapse" aria-expanded="true" style="cursor: pointer" title="Click here to view the Sub cover-> Buttons for <s:property value='#alloted.CREDITTRXNNO'/>">
											<i class="more-less glyphicon glyphicon-plus"></i>Premium Transaction <s:property value='#alloted.CREDITTRXNNO'/></span>
											</div>
											<div  id="miuns<s:property value="%{#stat.count}"/>" onclick="normalForm('2','<s:property value="%{#stat.count}"/>');" style="display: none; cursor: pointer;">
											<span data-target="#menuInfo_0<s:property value="%{#stat.count}"/>" data-toggle="collapse" aria-expanded="true" style="cursor: pointer" title="Click here to view the Sub cover-> Buttons for <s:property value='#alloted.CREDITTRXNNO'/>">
											<i class="more-less glyphicon glyphicon-minus"></i>Premium Transaction <s:property value='#alloted.CREDITTRXNNO'/></span>
											</div>			
										</div>
											<br/>
											<div class="collapse" id="menuInfo_0<s:property value="%{#stat.count}"/>">
											<table width="100%" style="margin: 0 auto;" class="footable display" >
											<thead>						
													<tr>
														<th class="no-sort"><s:text name="S No."/></th>
														<th><s:text name="Contract No" /></th>
														<th><s:text name="Claim No" /></th>
														<th><s:text name="Claim Payment No" /></th>
														<th><s:text name="Paid Date" /></th>
														<th><s:text name="CLD Currency" /></th>
														<th><s:text name="CLD Amount" /></th>
														<th><s:text name="CLC Currency" /></th>
														<th><s:text name="Credit Amount in CLC currency" /></th>
														<th><s:text name="Credit Amount in CLD currency" /></th>
														<th><s:text name="CLC Settlement Rate" /></th>
													</tr>			
												</thead>
												
												<tbody>
													<s:iterator value="allocatedcashLossList" var="record" status="stat">
													<s:if test="#alloted.CREDITTRXNNO.equals(#record.CREDITTRXNNO)">
														<tr>
															<td><s:property value="#stat.count" /></td>
															<td align="center"><s:property value="CONTRACT_NO" /></td>
															<td align="center"><s:property value="CLAIM_NO" /></td>
															<td align="center"><s:property value="CLAIMPAYMENT_NO" /></td>
															<td align="center"><s:property value="CREDITDATE" /></td>
															<td align="center"><s:property value="CLDCURRENCY_ID" /></td>
															<td align="right"><s:property value="CLD_AMOUNT" /></td>
															<td align="center"><s:property value="CLCCURRENCY_ID" /></td>
															<td align="center"><s:property value="CREDITAMOUNTCLC" /></td>
															<td align="center"><s:property value="CREDITAMOUNTCLD" /></td>
															<td align="center"><s:property value="EXCHANGE_RATE" /></td>
														</tr>
													</s:if>
													</s:iterator>
												</tbody>
																						
											</table>
											<br/>
											<div class="boxcontent" align="center">
												<s:if test="allocatedTransList!=null && allocatedTransList.size()>0">
												<input type="button" value="Reverse" class="btn btn-sm btn-success" onclick="fnReSubmit('<s:property value='#alloted.CREDITTRXNNO'/>');" />
												</s:if>
												
											</div>
											</div>
											
											</div>
										</s:iterator>
								</div>	
										
							</div>
							<s:hidden name="contNo"/>
								<s:hidden name="currencyId"/>
								<div class="boxcontent" align="center">
							<input type="button" value="Close" class="btn btn-sm btn-danger" onclick="window.close()" />
							</div>
						</div>
                    </div>
                    <s:hidden name="exchRate" id="exchRate"/>
                    <s:hidden name="proposal_No"/>
                    <s:hidden name="cashlosstranId" id="cashlosstranId"/>
                    </s:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function GetCashLossType(type){
	if(type=='A'){
		document.getElementById("reversal").style.display='none';
		document.getElementById("allocation").style.display='';
	}else{
		document.getElementById("allocation").style.display='none';
		document.getElementById("reversal").style.display='';
	}
	
}
function fnSubmit(){
	document.clcredit.action="${pageContext.request.contextPath}/submitCreditProportionPremium.action";
	document.clcredit.submit();
}
function fnContract(){
	document.clcredit.action="${pageContext.request.contextPath}/getCreditProportionPremium.action";
	document.clcredit.submit();
}
function fnReSubmit(val){
	document.getElementById("cashlosstranId").value=val; 
	document.clcredit.action="${pageContext.request.contextPath}/reverseCreditProportionPremium.action";
	document.clcredit.submit();
}
function setCLDAmount(id){
	var A=document.getElementById("currencyValueNameT["+id+"]").value;
	var B=document.getElementById("currencyIdNameT["+id+"]").value;
	if(A==B){
		document.getElementById("creditAmountCLDlist["+id+"]").value=document.getElementById("creditAmountCLClist["+id+"]").value;
	}/* else{
		document.getElementById("creditAmountCLDlist["+id+"]").value='';
	} */
}
function setAmount(id,val,val1,val2,index){
	var amount=document.getElementById(val).value; 
	var amount1=document.getElementById(val1).value;
	amount=amount.replace(new RegExp(',', 'g'),'');
	amount1=amount1.replace(new RegExp(',', 'g'),'');
	//alert(amount+"amount");
	//alert(amount1+"amount1");
	if(amount!='' && amount1!=''){
	//alert("check");
	var amount3=parseFloat(amount)/parseFloat(amount1);
	//alert(amount3);
		document.getElementById(val2).value=Comma(amount3.toFixed(8));
	}
	
	
}
function checkRage(index){
	 var A=document.getElementById("exchRate").value; 
	var B=document.getElementById("CLCsettlementRatelist["+index+"]").value;
	var C=document.getElementById("excessRateTemp["+index+"]").value;
	var E=parseFloat(1)/parseFloat(B);
	var D=((parseFloat(A)-(parseFloat(E)))/(parseFloat(E)))*100;
	//alert("E"+E);
	//alert("B"+B);
	//alert("C"+C);
	//alert("D"+D);
	if(parseFloat(D)>parseFloat(C)){
		alert("difference between Settled rate in Premium module CLC popup & monthly exchange rate in System exceed"+C +"%");
	} 
}
function setdefaultAmount(val,id){
	if(document.getElementById(id).checked){
		document.getElementById("creditAmountCLClist["+val+"]").readOnly=false;
		document.getElementById("creditAmountCLDlist["+val+"]").readOnly=false;
		document.getElementById("CLCsettlementRatelist["+val+"]").readOnly=false;
		document.getElementById("creditAmountCLClist["+val+"]").value=document.getElementById("creditAmountCLClistT["+val+"]").value;
		document.getElementById("creditAmountCLDlist["+val+"]").value=document.getElementById("creditAmountCLDlistT["+val+"]").value;
		document.getElementById("CLCsettlementRatelist["+val+"]").value=document.getElementById("CLCsettlementRatelistT["+val+"]").value;
	}else{
		document.getElementById("creditAmountCLClist["+val+"]").readOnly=true;
		document.getElementById("creditAmountCLDlist["+val+"]").readOnly=true;
		document.getElementById("CLCsettlementRatelist["+val+"]").readOnly=true;
		document.getElementById("creditAmountCLClist["+val+"]").value='';
		document.getElementById("creditAmountCLDlist["+val+"]").value='';
		document.getElementById("CLCsettlementRatelist["+val+"]").value='';
	}
	
}
function normalForm(val,stat){
	if(val=="1"){
		document.getElementById('miuns'+stat).style.display='block';
	    document.getElementById('plus'+stat).style.display='none';
	}else{
		document.getElementById('miuns'+stat).style.display='none';
	    document.getElementById('plus'+stat).style.display='block';
	}
	
}
function Comma(Num)
	{
	  Num += '';
      Num = Num.trim().replace(/,/g, '');
      if(Num.length>20)
      {
     	 alert('Exceeding Your Limit');
     	 Num=Num.substring(0,20);
      }else if(Num.length==0){
          Num = '0';
      }
      x = Num.split('.');
      x1 = x[0];
      x2 = x.length > 1 ? '.' + x[1] : '.00';
      var rgx = /(\d+)(\d{3})/;
      while (rgx.test(x1))
      x1 = x1.replace(rgx, '$1' + ',' + '$2');
      return x1 + x2;
	}
$("input[type='checkbox']").on('change', function(){
  $(this).val(this.checked ? "true" : "false");
})

</script>
</body>
</html>
