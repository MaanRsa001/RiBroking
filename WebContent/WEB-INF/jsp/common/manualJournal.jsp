<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="stylesheet" type="text/css" href="css/chosen.css"/>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<style type="text/css">
    .multiselect-container {
        width: 100% !important;
    }
</style>
<script type="text/javascript">
	 $(function() {
	    $( "#transactionDate" ).datepicker({
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
		$( "#reversalDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		
	  });	
	  </script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>

</head>
<s:form name="manualJournal" theme="simple">
<div class="row" style="display: table:width: 100%; margin: 0 auto;" >
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<span style="color:green"><s:actionmessage/></span>
	</div>
</div>
	<div class="boxcontent">
		
		<s:if test='"list".equals(mode)'>
		<div class="panel panel-primary">
			<div class="panel-heading">
				Manual Journal List
				<span class="pullRight">
				<input type="button" value="New Journal" class="btn btn-sm btn-success" onclick="funNew()" />
				</span>
			</div>
			<div class="panel-body">
				<div class="boxcontent">
				<div >
					<table class="footable" width="100%" id="newgen">
						<thead>
							<tr>
								<th width="15.8%"> <s:text name="label.journalRef" />  </th>
								<th width="15.8%"> <s:text name="label.transDate" />  </th>
								<th width="15.8%"> <s:text name="label.currency" />  </th>
								
								<th width="15.8%"> <s:text name="label.edit" /> </th>
								<th width="15.8%"> <s:text name="label.view" /> </th>
								<th width="15.8%"> <s:text name="label.reversal" /> </th>
								<th width="15.8%"> <s:text name="label.document" />  </th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="ledgerEntryList" var="list" status="stat">
							<tr>
									<td align="center">
				 					<s:property value="tranId"/>
				 					</td>
				 					<td align="center">
				 					<s:property value="transactionDate"/>
				 					</td>
				 					<td align="center">
				 					<s:property value="currency"/>
				 					</td>
									<td align="center">
									<s:if test='"Y".equals(transOpenperiodStatus) && "Y".equals(reversalStatus) '>
									<input type="button" value="Edit" class="btn btn-sm btn-primary" onclick="funEdit('<s:property value="tranId"/>')" />
									</s:if>
									</td>
									<td align="center">
									<input type="button" value="View" class="btn btn-sm btn-info" onclick="funView('<s:property value="tranId"/>')" />
									</td>
									<td align="center">
									
									<s:if test='"Y".equals(transOpenperiodStatus) && "Y".equals(reversalStatus) '>
									<input type="button" value="Reverse" class="btn btn-sm btn-info" onclick="funReverse('<s:property value="tranId"/>')" />
									</s:if>
									</td>
									<td align="center">
									<input type="button" value="Document" class="btn btn-sm btn-warning" onclick="getDocList('<s:property value="tranId"/>')" />
									</td>
								</tr>
								</s:iterator>
							</tbody>
							</table>
						</div>
					</div>
				</div>
				
			</div>
		</s:if>
		<s:elseif test='"new".equals(mode) || "edit".equals(mode)|| "view".equals(mode)|| "reverse".equals(mode)'>
		<div class="panel panel-primary">
			<div class="panel-heading">
			Manual Journal
			</div>
			<div class="panel-body">
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
								
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">									
									<div class="boxcontent">
									
									<table width="100%" class="footable">
													
											<tbody>
											<s:if test="tranId!=null && tranId!='' && 'edit'.equals(mode)">
											<tr >
												<td width="40%">
													<div class="textfield">
														<div class="text txtB">
															<s:text name="Amendment Date" />
														</div>
														<div class="tbox">
															<s:textfield name="amendmentDate" id="amendmentDate"  cssClass="inputBox" cssStyle="width:150%"  onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate()" disabled="'view'.equals(mode)?true:false"/>
														</div>
													</div>
												</td>
												<td width="60%">
												
												</td>
											</tr>
											</s:if>
												<tr >
												<td width="40%">
													<div class="textfield">
													<s:if test='"reverse".equals(mode)'>
														<div class="text txtB">
															<s:text name="Reversal Date" />
														</div>
														<div class="tbox">
															<s:textfield name="reversalDate" id="reversalDate"  cssClass="inputBox" cssStyle="width:150%"  onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate()" />
														</div>
													</s:if>
													<s:else>
														<div class="text txtB">
															<s:text name="Transaction Date" />
														</div>
														<div class="tbox">
															<s:textfield name="transactionDate" id="transactionDate"  cssClass="inputBox" cssStyle="width:150%"  onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate()" disabled="('view'.equals(mode) || 'reverse'.equals(mode))?true:false"/>
														</div>
														</s:else>
													</div>
												</td>
												<td width="60%">
												<div class="textfield" style="width:70%">
														<div class="text txtB" style="width:35%">
													<s:text name="label.currency" />
												</div>
												<div class="tbox"  style="width:65%">
													<s:select name="currency" id="currId" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" cssStyle="width:40%;float:left;" headerValue="---Select---" onchange="GetExchangeRate()" disabled="('view'.equals(mode) || 'reverse'.equals(mode))?true:false"/>												
													<span id="exchRate">
													<s:textfield cssClass="inputBox" name="exchRate" readonly="true"   cssStyle="width:60%;float:right;text-align:right;"  disabled="('view'.equals(mode) || 'reverse'.equals(mode))?true:false"/>
													</span>
													</div>
													</div>
												</td>
											</tr>
											<tr >
												<td width="50%">
												<s:label key="Class Name"/>
												</td>
												<td width="50%">
												<s:select list="ledgerdeptList" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="ledClass" id="ledClass" cssClass="inputBoxS"  headerKey="NONE" headerValue="None" disabled="('view'.equals(mode) || 'reverse'.equals(mode))?true:false"/>
												</td>
												
											</tr>
											
											
											</tbody>
											</table>
											<table width="100%" style="margin: 0 auto;" class="footable display" id="newgen" >
											<thead>	
											
																
													<tr>
														<th width="40%"> <s:text name="Ledger" /> </th>
														<th  width="12.5%"> <s:text name="Debit (OC)" /> </th>
														<th  width="12.5%"> <s:text name="Credit (OC)" /> </th>
														<th  width="12.5%"> <s:text name="Debit " />(<s:property value="shortname"/>) </th>
														<th  width="12.5%"> <s:text name="Credit " />(<s:property value="shortname"/>) </th>
														<th  width="10%"> <s:text name="Delete " /> </th>
													</tr>			
												</thead>
												
												<tbody>
													<s:iterator value="ledgerdetailList" var="list" status="stat">
														<tr>
															<td><s:select list="ledgerList" listValue="LEDGER_NAME" listKey="LEDGER_ID" name="ledgerId[%{#stat.count-1}]" id="ledgerId[%{#stat.count-1}]" cssClass="chosen-select" cssStyle="width:100%"  headerKey="" headerValue="---Select---" disabled="('view'.equals(mode) || 'reverse'.equals(mode))?true:false"/></td>
															<td ><s:textfield name="debitOC[%{#stat.count-1}]" id="debitOC%{#stat.count-1}" cssClass="inputBox" cssStyle="width:100%;text-align:right;"  onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" onchange="getDebitDC(this.value,%{#stat.count-1});getTotalOCValue('debitOC','totaldebitOC');getTotalDCValue('debitDC','creditDC','totaldebitDC','totalcreditDC');" disabled="('view'.equals(mode) || 'reverse'.equals(mode))?true:false" /></td>
															<td ><s:textfield name="creditOC[%{#stat.count-1}]" id="creditOC%{#stat.count-1}" cssClass="inputBox" cssStyle="width:100%;text-align:right;"  onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" onchange="getCreditDC(this.value,%{#stat.count-1});getTotalOCValue('creditOC','totalcreditOC');getTotalDCValue('debitDC','creditDC','totaldebitDC','totalcreditDC');" disabled="('view'.equals(mode) || 'reverse'.equals(mode))?true:false"/></td>
															<td ><s:textfield name="debitDC[%{#stat.count-1}]" id="debitDC%{#stat.count-1}" cssClass="inputBox" cssStyle="width:100%;text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" disabled="('view'.equals(mode) || 'reverse'.equals(mode))?true:false" onchange="getTotalDCValue('debitDC','creditDC','totaldebitDC','totalcreditDC');" maxlength="30"/></td>
															<td ><s:textfield name="creditDC[%{#stat.count-1}]" id="creditDC%{#stat.count-1}" cssClass="inputBox" cssStyle="width:100%;text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" disabled="('view'.equals(mode) || 'reverse'.equals(mode))?true:false" onchange="getTotalDCValue('debitDC','creditDC','totaldebitDC','totalcreditDC');" maxlength="30"/></td>
															<td align="center">
															<s:if test='0!=(#stat.count-1)'>
															<s:if test="!'view'.equals(mode) && !'reverse'.equals(mode)">
															<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteFacRow('<s:property value="%{#stat.count-1}"/>')"  />
															</s:if>
															</s:if>
															</td>
														</tr>
														
													</s:iterator>
													<s:hidden name="loopcount" id="loopcount"></s:hidden>
													<!--<tr>
														<td ><s:label key="label.narration"/></td> 
														<td colspan="4"><s:property value='spcCurrencyList.get(#status.count-1).get("NARRATION")'/></td>
													</tr>
												--></tbody>
																						
											</table>
											<table class="footable" width="100%" id="newgen">
															<tr>
																<td  style=" vertical-align: middle;" width="40%" ><s:label key="forexDiffName"/></td>
																<td width="12.5%"></td>
																<td width="12.5%"></td>
																<td  width="12.5%"><s:textfield name="exdebitDC" id="exdebitDC" cssClass="inputBox" cssStyle="width:100%;text-align:right;" disabled="true"/></td>
																<td width="12.5%"><s:textfield name="excreditDC" id="excreditDC" cssClass="inputBox" cssStyle="width:100%;text-align:right;" disabled="true"/></td>
																<td> </td>
															</tr>
															<tr>
																<td  style="vertical-align: middle;" width="40%" ><s:label key="Total"/></td>
																<td ><s:textfield name="totaldebitOC" id="totaldebitOC" cssClass="inputBox" cssStyle="width:100%;text-align:right;" disabled="true"/></td>
																<td ><s:textfield name="totalcreditOC" id="totalcreditOC" cssClass="inputBox" cssStyle="width:100%;text-align:right;" disabled="true"/></td>
																<td ><s:textfield name="totaldebitDC" id="totaldebitDC" cssClass="inputBox" cssStyle="width:100%;text-align:right;" disabled="true"/></td>
																<td ><s:textfield name="totalcreditDC" id="totalcreditDC" cssClass="inputBox" cssStyle="width:100%;text-align:right;" disabled="true" /></td>
																<td> &nbsp;</td>
															</tr>
															<tr>
																<td  style="vertical-align: middle;"  width="40%" ><s:label key="label.narration"/></td>
																<td colspan="5"><s:textarea name="narration" id="narration" cssClass="inputBox" disabled="'view'.equals(mode)?true:false" maxlength="500"/></td>
															</tr>
															
															</table>
										<br/><br/>
									</div>
								</div>
								<s:if test="!'view'.equals(mode) && !'reverse'.equals(mode)">
								<div class="boxcontent" align="right">
																<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insLedgerRow('newgen');" />
															</div>
															</s:if>
								<div class="boxcontent" align="center">
									<input type="button"  value="Back"  class="btn btn-sm btn-danger"  onclick="Back()" />
									<s:if test="!'view'.equals(mode)">
									&nbsp;&nbsp;&nbsp;<input type="button"  value="Save"   class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionSaveOption()" />
									</s:if>
								</div>
							</div>
			
		<br>
		</div>
		  			

<s:hidden name="currencyId" />
<s:hidden name="tranId" id="tranId" />

	</s:elseif>
</div>
<s:hidden name="mode" id="mode"/>
</s:form>

<script>
function GetExchangeRate()
{
		var month=document.manualJournal.transactionDate.value;
		//var year=document.manualJournal.transaction_year.value;
		var Currecny=document.getElementById("currId").value;								
	 	if(month!=null && month!="" && Currecny!="0" )
		{
			document.manualJournal.currencyId.value=Currecny;
			var URL='<%=request.getContextPath()%>/getExcRateRiskDetails.action?incepDate='+month+'&orginalCurrency='+Currecny+'&dropDown=exchRate';
	 				postRequest(URL,'exchRate');					
		}else
		{
				document.manualJournal.exchRate.value='';
		}
}
function getDebitDC(val,id){
var sumInsured=val.replace(new RegExp(',', 'g'),'');
var exchangeRate=document.manualJournal.exchRate.value;	
exchangeRate=exchangeRate.replace(new RegExp(',', 'g'),'');
if(sumInsured!=null && sumInsured!='' && exchangeRate!=null &&exchangeRate!=''){
var calc=(parseFloat(sumInsured)/parseFloat(exchangeRate));
document.getElementById("debitDC"+id).value=Comma(calc.toFixed(2));
document.getElementById("creditOC"+id).disabled=true;
document.getElementById("creditDC"+id).disabled=true;

}else{
document.getElementById("debitDC"+id).value='';
document.getElementById("creditOC"+id).disabled=false;
document.getElementById("creditDC"+id).disabled=false;
}
document.getElementById("creditOC"+id).value='';
document.getElementById("creditDC"+id).value='';
}
function getCreditDC(val,id){
var sumInsured=val.replace(new RegExp(',', 'g'),'');
var exchangeRate=document.manualJournal.exchRate.value;	
exchangeRate=exchangeRate.replace(new RegExp(',', 'g'),'');
if(sumInsured!=null && sumInsured!='' && exchangeRate!=null &&exchangeRate!=''){
var calc=(parseFloat(sumInsured)/parseFloat(exchangeRate));
document.getElementById("creditDC"+id).value=Comma(calc.toFixed(2));
document.getElementById("debitOC"+id).disabled=true;
document.getElementById("debitDC"+id).disabled=true;
}else{
document.getElementById("creditDC"+id).value='';
document.getElementById("debitOC"+id).disabled=false;
document.getElementById("debitDC"+id).disabled=false;
}
document.getElementById("debitOC"+id).value='';
document.getElementById("debitDC"+id).value='';
}
function insLedgerRow(tableID)
{
var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
		
       		
			var cell1 = row.insertCell(0);
			createLedgerCell(cell1, rowCount)
		
			var cell2 = row.insertCell(1);
			var element1 = document.createElement("input");
			element1.type = "text";
			element1.name = "debitOC["+(rowCount-1)+"]";
      		element1.id = "debitOC"+(rowCount-1)+"";
			element1.className = "inputBox";
			element1.setAttribute("onkeyup", "Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element1.setAttribute("onchange", "getDebitDC(this.value,"+(rowCount-1)+");getTotalOCValue('debitOC','totaldebitOC');getTotalDCValue('debitDC','creditDC','totaldebitDC','totalcreditDC');");
			element1.setAttribute("maxlength",'30');
			element1.setAttribute("style", "width:100%;text-align:right;");
			cell2.appendChild(element1);
			
			var cell3 = row.insertCell(2);
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "creditOC["+(rowCount-1)+"]";
      		element2.id = "creditOC"+(rowCount-1)+"";
			element2.className = "inputBox";
			element2.setAttribute("onkeyup", "Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element2.setAttribute("onchange", "getCreditDC(this.value,"+(rowCount-1)+");getTotalOCValue('creditOC','totalcreditOC');getTotalDCValue('debitDC','creditDC','totaldebitDC','totalcreditDC');");
			element2.setAttribute("maxlength",'30');
			element2.setAttribute("style", "width:100%;text-align:right;");
			cell3.appendChild(element2);
			var cell4 = row.insertCell(3);
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "debitDC["+(rowCount-1)+"]";
      		element3.id = "debitDC"+(rowCount-1)+"";
			element3.className = "inputBox";
			element3.setAttribute("onkeyup", "Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			
			element3.setAttribute("maxlength",'30');
			element3.setAttribute("style", "width:100%;text-align:right;");
			element3.setAttribute("disabled", "('view'.equals(mode) || 'reverse'.equals(mode))?true:false");
			cell4.appendChild(element3);
			var cell5 = row.insertCell(4);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "creditDC["+(rowCount-1)+"]";
      		element4.id = "creditDC"+(rowCount-1)+"";
			element4.className = "inputBox";
			element4.setAttribute("onkeyup", "Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element4.setAttribute("maxlength",'30');
			element4.setAttribute("style", "width:100%;text-align:right;");
			element4.setAttribute("disabled", "('view'.equals(mode) || 'reverse'.equals(mode))?true:false");
			cell5.appendChild(element4);
			
		var cell6 = row.insertCell(5);
			cell6.setAttribute("align","center");
			var element6 = document.createElement("input");
			element6.type = "button";
			element6.value="Delete";
			element6.setAttribute("onclick", "disableForm(this.form,false,'');deleteFacRow('"+(rowCount-1)+"')");
			element6.className="btn btn-sm btn-danger"
			cell6.appendChild(element6);
			
			
			 document.getElementById("loopcount").value =parseInt(rowCount);
			 //getEgnpiCal();
			// getdeductableCal();
			// getDedcCal();
}	

function createLedgerCell(cell, rowCount){
 	 $(".chosen-select").chosen('destroy');
		element = document.createElement("select");
        element.name = "ledgerId["+(rowCount-1)+"]";
      	element.id = "ledgerId["+(rowCount-1)+"]";
        element.setAttribute("class", "chosen-select");
        element.setAttribute("style", "width:100%;");
        populateledger(element);
        cell.appendChild(element);
        $(".chosen-select").chosen();
}
function populateledger(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='ledgerList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='LEDGER_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='LEDGER_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }	
 function Itnegative(id,val){
if(parseInt(val)<0){
	document.getElementById(id).value='';
	}
	else if(val=='-'){
		document.getElementById(id).value='';
	}
}	
function FunctionSaveOption(){
document.manualJournal.action="${pageContext.request.contextPath}/insertmanualJVJournal.action";
document.manualJournal.submit();
}
function funEdit(tranId){
document.getElementById("mode").value = "edit";
document.manualJournal.action="${pageContext.request.contextPath}/getEditLedgerDetailsJournal.action?tranId="+tranId;
document.manualJournal.submit();
}

function funView(tranId){
document.getElementById("mode").value = "view";
document.manualJournal.action="${pageContext.request.contextPath}/getEditLedgerDetailsJournal.action?tranId="+tranId;
document.manualJournal.submit();
}
function funReverse(tranId){
document.getElementById("mode").value = "reverse";
document.manualJournal.action="${pageContext.request.contextPath}/getEditLedgerDetailsJournal.action?tranId="+tranId;
document.manualJournal.submit();
}
function funNew()
{
	document.manualJournal.action ="${pageContext.request.contextPath}/newJvJournal.action";
	document.manualJournal.submit();
}
function Back()
{
	document.manualJournal.action="${pageContext.request.contextPath}/manualJVJournal.action";
	document.manualJournal.submit();
}
//getTotalDebitOC(id1,id2);
function getTotalOCValue(id1,id2){
    var texts = document.getElementById("loopcount").value;
	var val=0;
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}
	for(var i=0;i<texts;i++){
	
		var cover = document.getElementById(id1+i).value;
		
		if(cover ==''){
		cover=0;
		}
		else{
		cover = cover.replace(new RegExp(',', 'g'),'');
		document.getElementById(id1+i).value=Comma(parseFloat(cover).toFixed(2));
		}
		val +=parseFloat(cover);
	}
	document.getElementById(id2).value= Comma(parseFloat(val).toFixed(2));
	
}
function getTotalDCValue(id1,id2,id3,id4){
    var texts = document.getElementById("loopcount").value;
	var val1=0;
	var val2=0;
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}
	for(var i=0;i<texts;i++){
	
		var cover = document.getElementById(id1+i).value;
		
		if(cover ==''){
		cover=0;
		}
		else{
		cover = cover.replace(new RegExp(',', 'g'),'');
		document.getElementById(id1+i).value=Comma(parseFloat(cover).toFixed(2));
		}
		val1 +=parseFloat(cover);
	}
	for(var i=0;i<texts;i++){
	
		var cover = document.getElementById(id2+i).value;
		
		if(cover ==''){
		cover=0;
		}
		else{
		cover = cover.replace(new RegExp(',', 'g'),'');
		document.getElementById(id2+i).value=Comma(parseFloat(cover).toFixed(2));
		}
		val2 +=parseFloat(cover);
	}
	document.getElementById(id3).value= Comma(parseFloat(val1).toFixed(2));
	document.getElementById(id4).value= Comma(parseFloat(val2).toFixed(2));
	var val3=val1-val2;
	document.getElementById("totaldebitDC").value=Comma(parseFloat(val1).toFixed(2));
	document.getElementById("totalcreditDC").value=Comma(parseFloat(val2).toFixed(2));
if(val3<0){
document.getElementById("exdebitDC").value=Comma(parseFloat(Math.abs(val3)).toFixed(2));
document.getElementById("excreditDC").value='';
document.getElementById("totaldebitDC").value=Comma((parseFloat(val1)+parseFloat(Math.abs(val3))).toFixed(2));
}else if (val3>0){
document.getElementById("excreditDC").value=Comma(parseFloat(Math.abs(val3)).toFixed(2));
document.getElementById("exdebitDC").value='';
document.getElementById("totalcreditDC").value=Comma((parseFloat(val2)+parseFloat(Math.abs(val3))).toFixed(2));
}else{
document.getElementById("excreditDC").value='';
document.getElementById("exdebitDC").value='';
}
}

function deleteFacRow(id){
	document.manualJournal.action ="${pageContext.request.contextPath}/deleteJvJournal.action?deleteId="+id;
	document.manualJournal.submit();
}
function getDocList(tranId){			
			document.manualJournal.action="${pageContext.request.contextPath}/documentUpload.action?productId=6&type=journal&tranNo="+tranId;
			document.manualJournal.submit();
		} 
</script>
<script type="text/javascript" src="js/chosen.jquery.js"></script>
<script type="text/javascript">
 $(function() {
$(".chosen-select").chosen({});
});
</script>
</html>