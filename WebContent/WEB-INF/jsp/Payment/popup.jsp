<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/reset.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<%
	    String brokerid=(String) request.getAttribute("brokerid");
	    System.out.println("brokerid:"+brokerid);
   	%>
   	<script type="text/javascript">
        $(function(){
            var wht = $('input[name="withHoldingTaxOC"]');
            wht.val(wht.val().replace(new RegExp(',', 'g'),'') || "0");
        });
   	function back1() {
	  	document.popup.flag.value='';
	  	
		document.popup.action="${pageContext.request.contextPath}/initTreasury.action?cancelType=C";
		document.popup.submit();		
	}

	function back2() {
		document.popup.action="${pageContext.request.contextPath}/initTreasury.action";
		document.popup.submit();		
	}
	
	function Allocates() {
		document.popup.action="${pageContext.request.contextPath}/generationInsertTreasury.action";
		document.popup.submit();	
	}
	
	var rowIdCNT=1;
	function addRow(tab) {		 	
	    var tabObj;
	    var len=0;
	    tabObj=document.getElementById(tab);
	    len=tabObj.rows.length;
	    document.getElementById("hideRowCnt").value=len;
	    //var len1=parseInt(len);
	    var len1=parseInt(len)-1;
	    var len2=parseInt(len);
	    var Chemtype="";
	    Chemtype+='<option value="0">--Select---';
	    
	    <s:iterator value="orginalCurrencyList" status="stat">
	    	Chemtype+='<option value="<s:property value="CURRENCY_ID"/>"><s:property value="CURRENCY_NAME"/>';
	    </s:iterator>
	     <%-- if(cedding!=null && cedding.size()>0){
			  for (int i=0;i<cedding.size();i++) {
				  	 Map  entry=(Map) cedding.get(i);%>						
	   				Chemtype+='<option value="<%=entry.get("CURRENCY_ID").toString()%>"><%=entry.get("CURRENCY_NAME").toString() %>';
	   	<%}}--%>
	   	
	   	var val=new Array();
	   	//val[0]='<span class="formtxtf"  style="width:4%;text-align:right;" ><input type="hidden" name="recNo'+len1+'" value="">'+(len1)+'&nbsp;&nbsp;&nbsp;</span>';
	    //val[1]='<span class="formtxtf"  style="width:16%;text-align:right;" ><select name="cedingCompany'+len1+'" id="cedingCompany'+len1+'" onchange="GetExchangeRate(this.value,'+len1+');calcu();" class="inputBoxS">'+Chemtype+'</select></span>';  
	    //val[2]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="payamount'+len1+'" id="payamount'+len1+'"  class="inputBox" onblur="calcu();getConRecCurrency();"  onkeyup="allow2DigitDecValues(this)"></span> ';
	    //val[3]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="setExcRate'+len1+'" id="setExcRate'+len1+'"  class="inputBox" onblur="getConRecCurrency();"    onkeyup="checkDecimals15(this)"></span> ';
	    //val[4]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="conRecCur'+len1+'" id="conRecCur'+len1+'" readonly="true" class="inputBox"   maxlength=10 ></span> ';
	    //val[5]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="exachange'+len1+'" id="exachange'+len1+'" readonly="true" class="inputBox"    maxlength=10 ></span>';
	    //val[6]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="rowamount'+len1+'" id="rowamount'+len1+'" readonly="true" class="inputBox"   maxlength=10 ></span>';
		
		val[0]='<input type="hidden" name="recNoValList['+len1+']" value="">'+(len2)+'&nbsp;&nbsp;&nbsp;</span>';
	    val[1]='<select name="cedingCompanyValList['+len1+']" id="cedingCompanyValList['+len1+']" onchange="GetExchangeRate(this.value,'+len2+');calcu();" class="inputBoxS">'+Chemtype+'</select>';  
	    val[2]='<input type="text" style="text-align:right;" name="payamountValList['+len1+']" id="payamountValList['+len1+']"  class="inputBox" onkeyup="allow2DigitDecValues(this);" onchange="calcu();getConRecCurrency();">';
	    val[3]='<input type="text" style="text-align:right;" name="setExcRateValList['+len1+']" id="setExcRateValList['+len1+']" class="inputBox" onchange="getConRecCurrency();" onkeyup="checkDecimalsValue(this,8);">';
	    val[4]='<input type="text" style="text-align:right;" name="conRecCurValList['+len1+']" id="conRecCurValList['+len1+']" value="0.00" readonly="true" class="inputBox" maxlength=10 >';
	    val[5]='<input type="text" style="text-align:right;" name="exachangeValList['+len1+']" id="exachangeValList['+len1+']" readonly="true" class="inputBox" maxlength=10 >';
	    val[6]='<input type="text" style="text-align:right;" name="rowamountValList['+len1+']" id="rowamountValList['+len1+']" value="0.00" readonly="true" class="inputBox" maxlength=10 >';
		  
	    var temprowIdCNT=rowIdCNT;
	    row = tabObj.insertRow(len);
	    //row.id ='rowId'+temprowIdCNT;
	    //document.getElementById("hideRowCnt").value=len1;
	    document.getElementById("hideRowCnt").value=len2;
	    row.onclick=function(){
	
	    };
	
	    for(i=0;i<val.length;i++){
			cell = row.insertCell(i);
			if(i==5) {
	        	cell.id = "exachange" + len2;
	        } 
	        cell.innerHTML=val[i];
	    }
	    rowIdCNT++;
	}
	
	function delRow(tabId) {
	    var table=document.getElementById(tabId);
	    var len=document.getElementById(tabId).rows.length-1;
	    document.getElementById("hideRowCnt").value =parseInt(len)-1;
	    if(len<0){
	        alert("No Records to Delete");
	    }
	    else{
	        var r=confirm("Do you want to delete the Last Row ? ");
	        if (r==true)
	        {
	            table.deleteRow(len);
	        }
	    }
	}
	
	function GetExchangeRate(value,i) {
	var incDate=document.popup.tr_date.value;
		var Currecny=value;
		if(incDate!=null && incDate !=""){
	 		var URL='${pageContext.request.contextPath}/getExcRateRiskDetails.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=exachange&count='+(i-1);
  	 		postRequest(URL,'exachange'+i);
        }else{
			document.proportional.exchRate.value='';
		}	
}
	
	function diff(val) {
		var totpageval=parseFloat(document.getElementById("txtTotalAmt").value);
		var diffval=parseFloat(document.getElementById("txtDiffAmt").value)
		////alert("totdbval"+totdbval);
		///alert("totpageval"+totpageval);
		////alert("diffval"+diffval);
		/////alert("val"+val)
		if(document.getElementById("txtDiffAmt").value=="") {
			//alert("Please Enter Differen Value")
			document.getElementById("txtDiffAmt").focus();
			return false		
		}
		if(val=="B") {
			document.getElementById("txtTotalAmt").value=parseFloat(totpageval+diffval).toFixed(2)		
		}
		else {
			document.getElementById("txtTotalAmt").value=parseFloat(totpageval)-parseFloat(diffval).toFixed(2)
		}
	}
	
	function funBackMode(value) {
		document.popup.flag.value=value;
		document.popup.action="<%=request.getContextPath()%>/initTreasury.action";
		document.popup.submit();
	}
	 
	function funNewModes(recNo,brokId) {
		var URL ='<%=request.getContextPath()%>/initTreasury.action?flag=REVVIEW&serial_no='+recNo+'&broker='+brokId;
		var windowName = "Details";
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
</script>
</head>
<body onload="calcu(),getConRecCurrency();">
<div class="table0">
	<div class="tablerow">
		<span style="color: red;"> <s:actionerror/> </span>
	</div>
	<div class="tablerow">
		<s:if test='"receiptNoGen".equals(partToShow)'>
		<s:form name="popup" id="popup" action="" method="post" theme="simple">
		<div class="boxcontent">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:if test='"PT".equals(allocType)'>
						<s:text name="heading.payment" />
					</s:if>
					<s:else>
						<s:text name="heading.Receipt" />
					</s:else>
				</div>
				<div class="panel-body">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:if test='"PT".equals(allocType)'>
								<s:text name="heading.payment" />
							</s:if>
							<s:else>
								<s:text name="heading.Receipt" />
							</s:else>
						</div>
						<div class="panel-body">
							<%-- String hidRow=(request.getAttribute("HidRowCnt")==null?"3":request.getAttribute("HidRowCnt")).toString();
								int line=Integer.parseInt(hidRow); --%>
							<table width="75%" style="margin: 0 auto;" class="table table-bordered">
								<tr>
									<td width="25%">
										<s:if test='"PT".equals(allocType)'>
											<s:text name="Premium.PaymentNo" />
										</s:if>
										<s:else>
											<s:text name="Premium.ReceiptNo" />
										</s:else>
									</td>
									<td width="25%" class="txtB">
										<s:property value="serial_no" />
										<s:hidden name="serial_no" />
									</td>
									<td width="25%"></td>
									<td width="25%" class="txtB"></td>
								</tr>
								<tr>
									<td>
										<s:text name="RiskDetails.Broker" />
									</td>
									<td class="txtB">
										<s:property value="brokername" />
									</td>
									<s:if test="broker == 63">
									<td>
										<s:text name="RiskDetails.CedingCompany" />
									</td>
									<td class="txtB">
										<s:property value="cedingCo"/>
									</td>
									</s:if>
									<s:else>
									<td></td>
									<td class="txtB"></td>
									</s:else>
									<s:hidden name="broker" />
								</tr>
								<tr>
									<td>
										<s:if test='"PT".equals(allocType)'>
											<s:text name="payment.bank" />
										</s:if>
										<s:else>
											<s:text name="payment.receiptBank" />
										</s:else>
									</td>
									<td class="txtB">
										<s:property value="receiptBankName"/>
									</td>											
									<td>
										<s:if test='"PT".equals(allocType)'>
											<s:text name="payment.paymentCurrency" />
										</s:if>
										<s:else>
											<s:text name="payment.receivedCurrency" />
										</s:else>
									</td>
									<td class="txtB">
										<s:property value="currency"/> &nbsp; <s:property value="exchangerate"/>
										<s:hidden name="exchangerate" />
									</td>
								</tr>
								<tr>
									<td>
										<s:text name="Payment.TransactionDate" />
									</td>
									<td class="txtB">
										<s:property value="tr_date"/>
										<s:hidden name="tr_date" />
									</td>											
									<td>
										<s:text name="payment.amount" />
									</td>
									<td class="txtB">
										<s:property value="paymentamount"/>
										<s:hidden name="paymentamount" />
									</td>
								</tr>
								<tr>
									<td>
										<s:text name="payment.bankCharges" />
									</td>
									<td class="txtB">
										<s:property value="bankCharges"/>
									</td>											
									<td>
										<s:if test='"PT".equals(allocType)'>
											<s:text name="payment.paymentAmount" />
										</s:if>
										<s:else>
											<s:text name="payment.receiptAmount" />
										</s:else>
									</td>
									<td class="txtB">
										<s:property value="totalexchaamount"/>
										<s:hidden name="receiptamt" value="%{totalexchaamount}" />
									</td>
								</tr>
								<tr>
									<td>
                                        <s:text name="label.WithHoldingTax" />
                                    </td>
									<td class="txtB">
                                        <s:property value="withHoldingTaxOC"/>
                                        <s:hidden name="withHoldingTaxOC" value="%{withHoldingTaxOC}"/>
                                    </td>
                                    <td>
                                        <s:text name="label.PremiumLavy" />
                                    </td>
                                    <td class="txtB">
                                        <s:property value="premiumLavy" />
                                    </td>
								</tr>
								<tr>
                                  <td>
                                        <s:text name="payment.netAmt" />
                                    </td>
                                    <td class="txtB">
                                        <s:property value="netAmt"/>
                                    </td>
                                    <td>
                                    </td>
                                    <td class="txtB">
                                    </td>
                                  </tr>
							</table>
							<br class="clear"/>
							<s:if test='"Transaction".equals(transactionType)'>
							<table class="table table-bordered" width="100%" id="gen">
								<thead>
								<tr>
									<th> S.No. </th>
									<th width="15.8%"> <s:text name="Payment.Currency" /> </th>
									<th width="15.8%"> <s:text name="payment.amountoc" /> </th>
									<th width="15.8%"> <s:text name="payment.setExcRate" /> </th>
									<th width="15.8%"> <s:if test='"PT".equals(allocType)'>
											<s:text name="payment.conPaidCurrency" />
										</s:if>
										<s:else>
											<s:text name="payment.conRecCurrency" />
										</s:else>
									</th>
									<th width="15.8%"> <s:text name="payment.exchangeRate" /> </th>
									<th width="15.8%"> <s:text name="payment.totalAmount" /> </th>
								</tr>
								</thead>
								<tbody>
								<s:iterator value="paymentList" var="pmt" status="stat">
								<tr>
									<td>
										<s:hidden name="recNoValList[%{#stat.count-1}]"/>
										<s:property value='%{#stat.count}'/>
									</td>											
									<td>
										<s:select name="cedingCompanyValList[%{#stat.count-1}]" id="cedingCompanyValList[%{#stat.count-1}]" list="orginalCurrencyList" listKey="CURRENCY_ID" listValue="CURRENCY_NAME"  cssClass="inputBoxS" headerKey="0" headerValue="---Select---" onchange="GetExchangeRate(this.value,'%{#stat.count}');calcu();" />
									</td>
									<td>
										<s:textfield name="payamountValList[%{#stat.count-1}]" id="payamountValList[%{#stat.count-1}]" cssStyle="text-align: right;" cssClass="inputBox" onchange="calcu();getConRecCurrency();" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);" maxlength="40" />
									</td>
									<td>
										<s:textfield name="setExcRateValList[%{#stat.count-1}]" id="setExcRateValList[%{#stat.count-1}]" cssStyle="text-align: right;" cssClass="inputBox" onchange="getConRecCurrency();" onkeyup="checkDecimalsValue(this,8);middleMinusRestriction(this);" maxlength="40"/>
									</td>
									<td>
										<s:textfield name="conRecCurValList[%{#stat.count-1}]" id="conRecCurValList[%{#stat.count-1}]" readonly="true" cssStyle="text-align: right;" cssClass="inputBox" maxlength="30"/>
									</td>
												
									<td id="exachange<s:property value='%{#stat.count}'/>">
										<s:textfield name="exachangeValList[%{#stat.count-1}]" id="exachangeValList[%{#stat.count-1}]" readonly="true" cssStyle="text-align: right;" cssClass="inputBox" maxlength="30"/>
									</td>
									<td>
										<s:textfield name="rowamountValList[%{#stat.count-1}]" id="rowamountValList[%{#stat.count-1}]" readonly="true" cssStyle="text-align: right;" cssClass="inputBox" maxlength="30"/>
									</td>
								</tr>
								</s:iterator>
								</tbody>
							</table>
							<div class="boxcontent" align="right">
								<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="addRow('gen');" />
							</div>
							<s:hidden name="hideRowCnt" id="hideRowCnt"/>
							<br class="clear"/>									
							<table>
								<tbody>
								<tr>
									<td>
										<s:if test='"PT".equals(allocType)'>
											<s:text name="payment.totConPaidCurrency" />
										</s:if>
										<s:else>
											<s:text name="payment.totConRecCurrency" />
										</s:else>
									</td>
									<td class="txtB">
										<s:textfield name="totalConRecCur" id="totConRecCur" cssClass="inputBox" readonly="true" />
									</td>
									<td>
										<s:text name="payment.diffAmount" />
									</td>
									<td class="txtB">
										<s:select name="difftype" id="difftype" list="#{'B':'Add','M':'Subract'}" cssClass="inputBoxS" headerKey="N" headerValue="---Select---" />
									</td>
									
									<td class="txtB">
										<s:textfield name="txtDiffAmt" id="txtDiffAmt" cssClass="inputBox" onblur="calcu();" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);" maxlength="40"/>
									</td>
									<td class="txtB">
									<s:text name="payment.diffPercentage"/> <s:label id="diffAmount"></s:label>
									</td>
									<td class="txtB">
										<s:textfield name="txtDiffPer" id="txtDiffPer"  value="%{txtDiffAmt}" cssClass="inputBox" readonly="true" />
									</td>
									<td class="txtB">
										<s:textfield name="txtTotalAmt" id="txtTotalAmt" cssClass="inputBox" readonly="true" />
									</td>
									<s:hidden name="hidTotalAmt" id="hidTotalAmt" value="%{txtTotalAmt}"/>
								</tr>
								</tbody>
							</table>
							</s:if>
							<s:if test='"Treasury".equals(transactionType)'>
							<s:hidden name="type" />
								<display:table name="ReversalInfo" pagesize="0" requestURI="/initTreasury.action&flag=VIEW" class="table table-bordered" uid="row" id="record">
									<display:setProperty name="paging.banner.one_item_found" value="" />
									<display:setProperty name="paging.banner.one_items_found" value="" />
									<display:setProperty name="paging.banner.all_items_found" value="" />
									<display:setProperty name="paging.banner.some_items_found"	value="" />
									<display:setProperty name="paging.banner.placement" value="bottom" />
									<display:setProperty name="paging.banner.onepage" value="" />
									<display:column sortable="true" style="text-align:center;" title="Select">
									<input type="checkbox" name="pay_rec_no" id="pay_rec_no" value="${record.pay_rec_no}" />
										<!-- html:radio property="pay_rec_no" value="${record.pay_rec_no}"  -->
									</display:column>
									<s:if test='"PT".equals(allocType)'>
									<display:column sortable="true" style="text-align:center;" title="Receipt No" property="pay_rec_no" />
									</s:if>
									<s:else>
									<display:column sortable="true" style="text-align:center;" title="Payment No" property="pay_rec_no" />
									</s:else>
									<display:column sortable="true" style="text-align:left;" title="Broker Name" property="brokername" />
									<display:column sortable="true" style="text-align:left;" title="Ceding Company" property="cedingCo" />
									<display:column sortable="true" style="text-align:left;" title="Currency" property="currencyName" />
									<display:column sortable="true" style="text-align:right;" title="Exchange Rate" property="exrate" />
									<s:if test='"PT".equals(allocType)'>
									<display:column sortable="true" style="text-align:right;" title="Receipt Amount" property="paymentamount" />
									</s:if>
									<s:else>
									<display:column sortable="true" style="text-align:right;" title="Payment Amount" property="paymentamount" />
									</s:else>
									<display:column sortable="true" style="text-align:center;"  title="View" >
										<a class="tooltip" title="View"><img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif"  
										onclick="funNewModes('${record.pay_rec_no}','${record.brokerid}')"  alt="View" width="12" height="17"></a>
									</display:column>
								</display:table>
								<s:hidden name="currencyValue"/>
								<s:hidden name="currency" value="currencyValue"/>
								<s:hidden name="exrate"/>
							</s:if>
							<!--<s:hidden name="hideRowCnt" id="hideRowCnt"/>
							--><s:hidden name="flag"/>
							<s:hidden name="transactionType"/>
						</div>
					</div>							
				</div>
			</div>					
		</div>
		<div class="boxcontent" align="center">
			<!--<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="funBackMode('EDIT')" />
			--><input type="button"  value="Submit"  class="btn btn-sm btn-success" onclick="Allocates()" />
       		<input type="button"  value="Cancel"  class="btn btn-sm btn-warning" onclick="back1()" />
		</div>
		<s:hidden name="allocType"/>
		<s:hidden name="type"/>
		<!--<s:hidden name="currency"/>
		<s:hidden name="exrate"/>
		<s:hidden name="receiptBankId"/>
		<s:hidden name="proid"/>
		<s:hidden name="dept_no"/>
		<s:hidden name="transType"/>
		<s:hidden name="bankCharges"/>
		<s:hidden name="netAmt"/>
		<s:hidden name="amend_date"/>
		<s:hidden name="remarks"/>
		--></s:form>
		</s:if>
		
		<s:if test='"receiptNoSuc".equalsIgnoreCase(partToShow)'>
		<s:form name="popup" id="popup" action="" method="post" theme="simple">
		<div class="boxcontent">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<s:if test='"PT".equals(allocType)'>
						<s:text name="heading.payment" />
					</s:if>
					<s:else>
						<s:text name="heading.Receipt" />
					</s:else>
				</div>
				<div class="panel-body">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:if test='"PT".equals(allocType)'>
								<s:text name="heading.payment" />
							</s:if>
							<s:else>
								<s:text name="heading.Receipt" />
							</s:else>
						</div>
						<div class="panel-body">
							<%-- String hidRow=(request.getAttribute("HidRowCnt")==null?"3":request.getAttribute("HidRowCnt")).toString();
								int line=Integer.parseInt(hidRow); --%>
							<table width="75%" style="margin: 0 auto;" class="table table-bordered">
								<tr>
									<td width="25%">
										<s:if test='"PT".equals(allocType)'>
											<s:text name="Premium.PaymentNo" />
										</s:if>
										<s:else>
											<s:text name="Premium.ReceiptNo" />
										</s:else>
									</td>
									<td width="25%" class="txtB">
										<s:property value="serial_no" />
										<s:hidden name="serial_no" />
									</td>
									<td width="25%"></td>
									<td width="25%" class="txtB"></td>
								</tr>
								<tr>
									<td>
										<s:text name="RiskDetails.Broker" />
									</td>
									<td class="txtB">
										<s:property value="brokername" />
									</td>
									<s:if test="broker == 63">
									<td>
										<s:text name="RiskDetails.CedingCompany" />
									</td>
									<td class="txtB">
										<s:property value="cedingCo"/>
									</td>
									</s:if>
									<s:else>
									<td></td>
									<td class="txtB"></td>
									</s:else>
									<s:hidden name="broker" />
								</tr>
								<tr>
									<td>
										<s:if test='"PT".equals(allocType)'>
											<s:text name="payment.bank" />
										</s:if>
										<s:else>
											<s:text name="payment.receiptBank" />
										</s:else>
									</td>
									<td class="txtB">
										<s:property value="receiptBankName"/>
									</td>											
									<td>
										<s:if test='"PT".equals(allocType)'>
											<s:text name="payment.paymentCurrency" />
										</s:if>
										<s:else>
											<s:text name="payment.receivedCurrency" />
										</s:else>
									</td>
									<td class="txtB">
										<s:property value="currency"/> &nbsp; <s:property value="exchangerate"/>
										<s:hidden name="exchangerate" />
									</td>
								</tr>
								<tr>
									<td>
										<s:text name="Payment.TransactionDate" />
									</td>
									<td class="txtB">
										<s:property value="tr_date"/>
										<s:hidden name="tr_date" />
									</td>											
									<td>
										<s:text name="payment.amount" />
									</td>
									<td class="txtB">
										<s:property value="paymentamount"/>
										<s:hidden name="paymentamount" />
									</td>
								</tr>
								<tr>
									<td>
										<s:text name="payment.bankCharges" />
									</td>
									<td class="txtB">
										<s:property value="bankCharges"/>
									</td>											
									<td>
										<s:if test='"PT".equals(allocType)'>
											<s:text name="payment.paymentAmount" />
										</s:if>
										<s:else>
											<s:text name="payment.receiptAmount" />
										</s:else>
									</td>
									<td class="txtB">
										<s:property value="totalexchaamount"/>
										<s:hidden name="receiptamt" value="%{totalexchaamount}" />
									</td>
								</tr>
								<tr>
                                    <td>
                                        <s:text name="label.WithHoldingTax" />
                                    </td>
                                    <td class="txtB">
                                        <s:property value="withHoldingTaxOC"/>
                                        <s:hidden name="withHoldingTaxOC" value="%{withHoldingTaxOC}"/>
                                    </td>
                                    <td>
                                        <s:text name="label.PremiumLavy" />
                                    </td>
                                    <td class="txtB">
                                        <s:property value="premiumLavy" />
                                    </td>
                                    
								</tr>
                                <tr>
                                    <td> <s:text name="Exchange Diff Amount" /></td>
                                     <td class="txtB">
                                        <s:property value="diffAmount" />
                                    </td>
                                    <td>
                                        <s:text name="Round Off Amount" />
                                    </td>
                                    <td class="txtB">
                                        <s:property value="convDiffAmount" />
                                    </td>
                                </tr>
                                <tr>
                                  <td>
                                        <s:text name="payment.netAmt" />
                                    </td>
                                    <td class="txtB">
                                        <s:property value="netAmt"/>
                                    </td>
                                    <td>
                                    </td>
                                    <td class="txtB">
                                    </td>
                                  </tr>
							</table>
							<br class="clear"/>
							<s:if test='"Transaction".equalsIgnoreCase(transactionType)'>
							<display:table name="detailsList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record">
								<display:setProperty name="paging.banner.one_item_found" value="" />
								<display:setProperty name="paging.banner.one_items_found" value="" />
								<display:setProperty name="paging.banner.all_items_found" value="" />
								<display:setProperty name="paging.banner.some_items_found"	value="" />
								<display:setProperty name="paging.banner.placement" value="bottom" />
								<display:setProperty name="paging.banner.onepage" value="" />
								
								<display:column sortable="true" style="text-align:center;" title="S.No." property="pay_res" />
								<s:if test='"PT".equals(allocType)'>
									<display:column sortable="true" style="text-align:center;" title="Payment No" property="serial_no" />
									<display:column sortable="true" style="text-align:center;" title="Payment Currency" property="currency" />
								</s:if>
								<s:else>
									<display:column sortable="true" style="text-align:center;" title="Receipt No" property="serial_no" />
									<display:column sortable="true" style="text-align:center;" title="Received Currency" property="currency" />
								</s:else>
								<display:column sortable="true" style="text-align:right;" title="Amount" property="payamount" />
								<display:column sortable="true" style="text-align:right;" title="Settled Exchange Rate" property="setExcRate" />
								<s:if test='"PT".equals(allocType)'>
									<display:column sortable="true" style="text-align:right;" title="Converted Paid Amount" property="conRecCur" />
								</s:if>
								<s:else>
									<display:column sortable="true" style="text-align:right;" title="Converted Receipt Amount" property="conRecCur" />
								</s:else>
								<display:column sortable="true" style="text-align:right;" title="Exchange Rate" property="exrate" />
								<display:column sortable="true" style="text-align:right;" title="Total Amount" property="totAmount" />
							</display:table>
						</s:if>
						<s:if test='"Treasury".equalsIgnoreCase(transactionType)'>
							<display:table name="detailsList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record">
								<display:setProperty name="paging.banner.one_item_found" value="" />
								<display:setProperty name="paging.banner.one_items_found" value="" />
								<display:setProperty name="paging.banner.all_items_found" value="" />
								<display:setProperty name="paging.banner.some_items_found"	value="" />
								<display:setProperty name="paging.banner.placement" value="bottom" />
								<display:setProperty name="paging.banner.onepage" value="" />
							
								<display:column sortable="true" style="text-align:center;" title="Select">
									<html:radio property="pay_rec_no" value="${record.pay_rec_no}" />
								</display:column>
								<s:if test='"PT".equals(allocType)'>
									<display:column sortable="true" style="text-align:center;" title="Receipt No" property="pay_rec_no" />
								</s:if>
								<s:else>
									<display:column sortable="true" style="text-align:center;" title="Payment No" property="pay_rec_no" />
								</s:else>
								<display:column sortable="true" style="text-align:left;" title="Broker Name" property="brokername" />
								<display:column sortable="true" style="text-align:left;" title="Ceding Company" property="cedingCo" />
								<display:column sortable="true" style="text-align:left;" title="Currency" property="currencyName" />
								<display:column sortable="true" style="text-align:right;" title="Exchange Rate" property="exrate" />
								<s:if test='"PT".equals(allocType)'>
									<display:column sortable="true" style="text-align:right;" title="Receipt Amount" property="paymentamount" />
								</s:if>
								<s:else>
									<display:column sortable="true" style="text-align:right;" title="Payment Amount" property="paymentamount" />
								</s:else>
								<display:column sortable="true" style="text-align:center;"  title="View" >
									<a class="" title="View"><img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="funNewModes('${record.pay_rec_no}','${record.brokerid}')"  alt="View" width="12" height="17"></a>
								</display:column>
							</display:table>	
						</s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="boxcontent" align="center">
			<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="back2()" />
		</div>
		<s:hidden name="hideRowCnt" id="hideRowCnt"/>
		<s:hidden name="allocType"/>
		<s:hidden name="type"/>
		</s:form>
		</s:if>
		
	</div>
</div>	

<script type="text/javascript">
	function getConRecCurrency() {	
		var totamtval=0;
     	var amount=0;
    	var setExcrate=0
     	var totamt=0;
		var id=document.getElementById("hideRowCnt").value;
		<%-- 
		<s:iterator value="paymentList" var="pmt" status="stat">
			totamt=0;
			if(document.getElementById('setExcRateValList[<s:property value="%{#stat.count-1}"/>]').value!=null && document.getElementById('setExcRateValList[<s:property value="%{#stat.count-1}"/>]').value!="" && document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value!=null && document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value!="") {
				 amount=parseFloat(document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value);	
			     setExcrate=parseFloat(document.getElementById('setExcRateValList[<s:property value="%{#stat.count-1}"/>]').value)
			}else {
				 amount=0;
				 setExcrate=0;
			}	
			if(setExcrate!='0') {
				 totamt=amount/setExcrate;
				 totamtval+=totamt;
			}	 
			document.getElementById('conRecCurValList[<s:property value="%{#stat.count-1}"/>]').value=parseFloat(totamt).toFixed(2);
			document.getElementById("totConRecCur").value=parseFloat(totamtval).toFixed(2);
		</s:iterator>
		--%>
		
		var rowCount = parseInt(document.getElementById("hideRowCnt").value);
		for(i=0;i<rowCount;i++) {
			totamt=0;
			if(document.getElementById('setExcRateValList['+i+']').value!=null && document.getElementById('setExcRateValList['+i+']').value!="" && document.getElementById('payamountValList['+i+']').value!=null && document.getElementById('payamountValList['+i+']').value!="") {
				 amount=parseFloat(document.getElementById('payamountValList['+i+']').value);	
			     setExcrate=parseFloat(document.getElementById('setExcRateValList['+i+']').value);
			}else {
				 amount=0;
				 setExcrate=0;
			}	
			if(setExcrate!='0') {
				 totamt=amount/setExcrate;
				 totamtval+=totamt;
			}	 
			document.getElementById('conRecCurValList['+i+']').value=parseFloat(totamt).toFixed(2);
			document.getElementById("totConRecCur").value=parseFloat(totamtval).toFixed(2);
		}
	}
	function calcu() {
		var totamtval=0;
		var amount=0;
		var rate=0
		var totamt=0;
		var diffPer=0;
		id=document.getElementById("hideRowCnt").value;
		var rowCount = parseInt(document.getElementById("hideRowCnt").value);
		<%--
		<s:iterator value="paymentList" var="pmt" status="stat">
		 
			totamt=0;
			if(document.getElementById('exachangeValList[<s:property value="%{#stat.count-1}"/>]')!=null && document.getElementById('exachangeValList[<s:property value="%{#stat.count-1}"/>]').value!="" && document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]')!=null && document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value!="") {
	 			amount=parseFloat(document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value);	
     			rate=parseFloat(document.getElementById('exachangeValList[<s:property value="%{#stat.count-1}"/>]').value)	
			}
			else {
				amount=0;
				rate=0;
			}	
		 	if(rate!='0') {
				totamt=amount/rate;
			}	 
			document.getElementById('rowamountValList[<s:property value="%{#stat.count-1}"/>]').value=parseFloat(totamt).toFixed(2);
			totamtval=totamtval+parseFloat(totamt);
		</s:iterator>
		--%>
		
		for(i=0;i<rowCount;i++) {
			totamt=0;
			if(document.getElementById('exachangeValList['+i+']')!=null && document.getElementById('exachangeValList['+i+']').value!="" && document.getElementById('payamountValList['+i+']')!=null && document.getElementById('payamountValList['+i+']').value!="") {
	 			amount=parseFloat(document.getElementById('payamountValList['+i+']').value);	
     			rate=parseFloat(document.getElementById('exachangeValList['+i+']').value);
			}
			else {
				amount=0;
				rate=0;
			}	
		 	if(rate!='0') {
				totamt=amount/rate;
			}	 
			document.getElementById('rowamountValList['+i+']').value=parseFloat(totamt).toFixed(2);
			totamtval=totamtval+parseFloat(totamt);
		}
	
		var diffType=document.popup.difftype.value;
		var diffVal=document.getElementById("txtDiffAmt").value;
		if(diffType=="B") {
			if(diffVal!=null && diffVal !="") {
				totamtval=parseFloat(totamtval)+parseFloat(diffVal)
				if(document.popup.receiptamt.value!=null && document.popup.receiptamt.value!="" && document.popup.receiptamt.value!=0){
					diffPer=(parseFloat(diffVal)/parseFloat(document.popup.receiptamt.value))*100;	
				}
			}
		}
		else if(diffType=="M") {
			if(diffVal!=null && diffVal !="") {
			totamtval=parseFloat(totamtval)-parseFloat(diffVal);		
			if(document.popup.receiptamt.value!=null && document.popup.receiptamt.value!="" && diffVal!=0)
					diffPer=(parseFloat(diffVal)/parseFloat(document.popup.receiptamt.value))*100;	
			}
		} 	
		document.getElementById("txtTotalAmt").value=parseFloat(totamtval).toFixed(2);
		document.getElementById("txtDiffPer").value=parseFloat(diffPer).toFixed(2);	
		document.getElementById("hidTotalAmt").value=parseFloat(totamtval).toFixed(2);
		
		var receiptAmount='<s:property value="totalexchaamount"/>';
		var totalAmount=parseFloat(totamtval).toFixed(2);
		var receiptAmount1 = receiptAmount.replace(new RegExp(',', 'g'),'');
		var diffAmount=parseFloat(receiptAmount1)-parseFloat(totalAmount);
		document.getElementById('diffAmount').innerHTML=parseFloat(diffAmount).toFixed(2);
	 
	}
	
 function middleMinusRestriction(txt) {
	var prevValue='';
    var min = 0;
    var length = txt.value.length;
    var text = txt.value;
    for(var i=0; i<length; i++) {
	    if(i!=0){
	        if(text[i]=='-') min++;
	        if(min<1) { 
	        	prevValue += text[i];
	        }
	        }else{
	        	prevValue += text[i];
	        }
        }
    txt.value= prevValue;
    return false;
}	
</script>

</body>
</html>
