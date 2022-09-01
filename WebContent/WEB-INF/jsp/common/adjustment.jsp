<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
    <sj:head jqueryui="true" jquerytheme="start"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script language="JavaScript" type="text/javascript">
    </script>
    <link href="${pageContext.request.contextPath}/css/autoProcess.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">
                    <div class="table2">
                    
                    <s:if test='"init".equals(mode)'>
                        <form action="" method="post" id="adjustment" name="adjustment">
                            <div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<!--<s:property value="headMes" />
													--></div>
													<div class="panel-body">
													<div class="tablerow">
                                        				<span style="color:red;"><s:actionerror/></span>
                                   					 </div>
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Adjustment.broker" />
																</div>
																<div class="tbox">
																	<s:select list="BrokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId" id="brokerId" headerKey="-1"	headerValue="---Select---" onchange="GetCedingCompany(this.value)"/>
																</div>
															</div>
															<div class="textfield" id="enddiv" style="display:none;">
																<div class="text txtB">
																	<s:text name="Adjustment.CedingCompany" />
																</div>
																<div class="tbox" >
																	<s:select list="ceddingCompanyList" cssClass="inputBoxS" name="cedingId" id="cedingId" listKey="CUSTOMER_ID" listValue="NAME" headerKey="-1"	headerValue="---Select---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Adjustment.transactionType" />
																</div>
																<div class="tbox">
																	<s:select list="#{'PC':'Premium and Claim','RP':'Receipt and Payment'}"  cssClass="inputBoxS" name="transactionType" id="transactionType" headerKey="-1" headerValue="---Select---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Adjustment.transactionCurrency" />
																</div>
																<div class="tbox">
																	<s:select list="currencyList" cssClass="inputBoxS" name="currencyId" id="currencyId" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="-1"	headerValue="---Select---" />
																</div>
															</div>
					                                        <div class="textfield">
					                                            <div class="text txtB">
					                                                <s:text name="label.adjustmentType"/>
					                                            </div>
					                                            <div class="tbox">
					                                                   <s:select list="#{'W':'Write Off','R':'Round Off'}"  cssClass="inputBoxS" name="adjustType" id="adjustType" headerKey="-1" headerValue="---Select---" />
					                                            </div>
					                                        </div>
					                                         <div class="textfield">
					                                            <div class="text txtB">
					                                                <s:text name="Adjustment.Amount"/>
					                                            </div>
					                                            <div class="tbox">
					                                                   <s:select list="#{'1':'=','2':'<=','3':'>='}" cssClass="inputBoxS" cssStyle="width: 30%; float:left;" name="amountType" id="amountType" headerKey="-1" headerValue="---Select---" />
					                                                   <s:textfield cssClass="inputBox" cssStyle="width: 60%; float:left;" name="amount" ></s:textfield>
					                                            </div>
					                                        </div><!--
					                                        <div class="textfield" >
																<div class="text txtB">
																	<s:text name="Adjustment.Amount" />
																</div>
																	<div class="tbox">
																	<s:textfield cssClass="inputBox" name="amount" ></s:textfield>
																</div>
																</div>	
					                                        --><div class="textfield">
																<div class="text txtB">
																	<s:text name="Adjustment.transactionNo" />
																</div>
																<div class="tbox">
																<s:radio name="test" id="test1" list="#{'ALL':'ALL','S':'Selected'}" value="test==null?'ALL':test"   onclick="contractScript(this.value)"/>
																</div>
															</div>
															<div class="textfield" id="endtlabel" style="display:inline;">
																<div class="text txtB">
																	<s:text name="Adjustment.transactionNo1" />
																</div>
																	<div class="tbox">
																	<s:textfield cssClass="inputBox" name="transactionNo" onkeyup="checkNumbers(this);"></s:textfield>
																</div>
																</div>	
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Back"	class="btn btn-sm btn-danger" onclick="getBack();" />
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="getReports();" />
											</div>
										</div>
                        </form>
                        </s:if>
                        <s:if test='"list".equals(mode)'>
                        <div class="tablerow">
                         <s:form action="" id="postForm" method="post" theme="simple">
                        	<div class="panel panel-primary">
                        		<div class="panel-body">
                        		<div class="tablerow">
                                        <span style="color:red;"><s:actionerror/></span>
                                    </div>
                        			<div class="boxcontent">
                                        <div class="textfield">
                                            <div class="text">
                                                <s:text name="label.adjustmentDate"/>
                                            </div>
                                            <div class="tbox">
                                                <div class="adjustmentDate">
                                                    <sj:datepicker name="adjustmentDate" id="adjustmentDate" displayFormat="dd/mm/yy" cssClass="dp1 inputBoxS pullLeft"/>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <br class="clear">
                                       </div>
                        		</div>
                        	</div>
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                        <div class="pull-right">
                                        <div>
                                            <button class="btn btn-default btn-sm dropdown-toggle" type="button" id="menu1" data-toggle="dropdown" style="margin-top: -4px">Items
                                                per page
                                                <span class="caret"></span></button>
                                            <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                                                <li role="presentation" class="perPageToggle p10" data-page="10"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-check text-blue"></i> 10 items</a></li>
                                                <li role="presentation" class="perPageToggle p20" data-page="20"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked text-blue"></i> 20 items</a></li>
                                                <%--<li role="presentation" class="divider"></li>--%>
                                                <li role="presentation" class="perPageToggle p50" data-page="50"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked text-blue"></i> 50 items</a></li>
                                                <li role="presentation" class="perPageToggle p100" data-page="100"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked text-blue"></i> 100 items</a></li>
                                            </ul>
                                            <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" style="margin-top: -4px">Checkbox Toggle
                                                <span class="caret"></span></button>
                                            <ul class="dropdown-menu" role="menu" aria-labelledby="menu2">
                                                <li role="presentation" class="checkToggle check"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-check"></i> Check All</a></li>
                                                <li role="presentation" class="checkToggle uncheck"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked"></i> UnCheck All</a></li>
                                                <li role="presentation" class="divider"></li>
                                                <li role="presentation" class="checkToggle page check"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-check text-blue"></i> Check All (page)</a></li>
                                                <li role="presentation" class="checkToggle page uncheck"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked text-blue"></i> UnCheck All (page)</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <br class="clear">
                                </div>
                                <div class="panel-body" style="overflow: auto;">
                                    <s:set name="results" value="adjustmentList"/>
                                    <s:set var="noOfResults" name="noOfResults" value="#results!= null ? #results.size() : 0"/>
                                    
                                   
                                    <c:choose>
                                            <c:when test="${noOfResults>0}">
                                                <h4 id="loader">Loading.. Please wait</h4>
                                             <div class="table-wrapper">
                                             <display:table name="adjustmentList" requestURI="" pagesize="" export="false" class="footable record_editable"
                                                                   style="width:2200px;display:none;"
                                                                   uid="row" id="record">
                                                        <display:setProperty name="paging.banner.one_item_found" value=""/>
                                                        <display:setProperty name="paging.banner.one_items_found" value=""/>
                                                        <display:setProperty name="paging.banner.all_items_found" value=""/>
                                                        <display:setProperty name="paging.banner.some_items_found" value=""/>
                                                        <display:setProperty name="paging.banner.placement" value="bottom"/>
                                                        <display:setProperty name="paging.banner.onepage" value=""/>

                                                        <s:set name="myrow" value="#attr.record"/>
                                                        <display:column sortable="true"  style="width: 15px;text-align: center;" title="Transaction No">
                                                           <s:hidden name="transactionNos[%{#attr.record_rowNum-1}]" id="transactionno[%{#attr.record_rowNum-1}]" value="%{#myrow.transactionNo}" /><s:property value="#myrow.transactionNo"/>
                                                        </display:column>
                                                        <display:column sortable="true" title="Transaction Date" style="width: 15px;text-align: center;">
                                                           <s:hidden name="transactionDates[%{#attr.record_rowNum-1}]" id="transactionDates[%{#attr.record_rowNum-1}]" value="%{#myrow.transactionDate}" /> <s:property value="#myrow.transactionDate"/></display:column>
                                                         <display:column sortable="true" title="Contract No" style="width: 15px;text-align: center;">
                                                             <s:property value="#myrow.contractNo"/></display:column>
                                                        <display:column sortable="true" title="Layer" style="width: 15px;text-align: center;">
                                                            <s:property value="#myrow.layerNo"/></display:column>
                                                        <display:column sortable="true" title="Broker" style="width: 150px;text-align: center;">
                                                            <s:property value="#myrow.brokerName"/></display:column>
                                                        <display:column sortable="true" title="Ceding Company" style="width: 150px;text-align: center;">
                                                            <s:property value="#myrow.cedingName"/>
                                                        </display:column>
                                                        <display:column sortable="true" title="Currency" style="width: 15px;text-align: center;">
                                                            <s:property value="#myrow.currency"/></display:column>
                                                          <display:column sortable="true" title="Type" style="width: 15px;text-align: center;">
                                                          <s:property value="#myrow.type"/></display:column>
                                                        <display:column sortable="true" title="Pending Amount" class="w80 textRight" style="text-align:right;">
                                                            <s:property value="#myrow.pendingAmount"/></display:column>
                                                        <display:column sortable="true" title="Amount to be Adjusted" class="w80 textRight" >
                                                           <s:textfield name="adjustmentAmounts[%{#attr.record_rowNum-1}]" id="adjustmentAmounts" value="%{#myrow.adjustAmount}" onchange="validateFloatKeyPress(this);numberWithCommas();" onkeyup="middleMinusRestriction(this);" maxlength="30" />
                                                           <s:hidden name="previousValue[%{#attr.record_rowNum-1}]" id="previousValue[%{#attr.record_rowNum-1}]"/></display:column>
                                                        <display:column  title="Adjustment Type" style="width: 15px;text-align: center;">
                                                        <s:select list="#{'W':'Write Off','R':'Round Off'}" cssClass="inputBoxS" name="adjustmentType[%{#attr.record_rowNum-1}]" id="adjustmentType" value="%{#myrow.adjustType}"></s:select>
                                                        </display:column>
                                                         <display:column sortable="true" title="Post" class="w80 toggleCheck" style="text-align:center;">
                                                   			<s:checkbox name="chkbox[%{#attr.record_rowNum-1}]" id="isPost" value="%{#myrow.check==null?true:#myrow.check}"  onchange="setAmount(this.name,'adjustmentAmounts[%{#attr.record_rowNum-1}]','%{#myrow.count}','%{#myrow.adjustAmount}','previousValue[%{#attr.record_rowNum-1}]','%{#myrow.type}');"/> 
                                                           </display:column>
                                                    </display:table>
                                                </div>
                                                <div>
                                                    <ul id="paging"></ul>
                                                </div>
                                                
                                                </c:when>
                                            <%-- <c:otherwise>
                                                <h4>No data found..</h4>
                                            </c:otherwise>--%>
                                        </c:choose>
                                        <div class="btnContainer" style="text-align: center;">
                                                    <input type="button" value="Back"	class="btn btn-sm btn-danger" onclick="back(this.form);" />
                                                    <input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="oncheck();" />
                                                    <s:hidden name="currencyId"/>
                                                     <s:hidden name="brokerId"/>
                                                      <s:hidden name="cedingId"/>
	                                                   <s:hidden name="transactionType"/>
	                                                   <s:hidden name="test"/>
	                                                   <s:hidden name="adjustType"/>
	                                                   <s:hidden name="transactionNo"/>
	                                                   <s:hidden name="amountType"/>
	                                                   <s:hidden name="amount"/>
                                                   </div>
                                  </div>
                            </div>
                             </s:form>
                        </div>
                      </s:if>
                      <s:if test='"view".equals(mode)'>
                      <s:form action="" id="postForm" method="post" theme="simple">
                      <div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Adjustment Details" />
									</div>									
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedList" pagesize="10" requestURI="" class="footable" uid="row" id="record2" >
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record2"/>
												<display:column style="text-align:center;" title="SNo." property="serialNo" />
												<display:column style="text-align:center;" title="Adjustment Date" property="allocateddate" />
												<s:if test='"P".equals(#myrow.type) || "C".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="transactionNo" />
												</s:if>
												<s:elseif test='"PT".equals(#myrow.type) || "RT".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="receiptNo" />
												</s:elseif>
												<display:column style="text-align:center;" title="Contract No" property="contractNo" />
												<display:column style="text-align:center;" title="Currency" property="currencyName" />
												<display:column style="text-align:center;" title="Business Type" property="productName" />
												<display:column style="text-align:left;" title="TYPE">
													<s:if test='"P".equals(#myrow.type)'>
														<s:text name="PREMIUM" />
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														<s:text name="CLAIM" />
													</s:if>
													<s:if test='"RT".equals(#myrow.type)'>
														<s:text name="RECEIPT" />
													</s:if>
													<s:if test='"PT".equals(#myrow.type)'>
														<s:text name="PAYMENT" />
													</s:if>
												</display:column>
												<display:column style="text-align:right;" title="Amount" >
														<s:property value="#myrow.payamount"/>
												</display:column>
												<display:column style="text-align:left;" title="Status" property="adjustType" />
												<display:setProperty name="export.excel" value="false" />
												<display:setProperty name="export.excel.filename" value="Allocated Contracts2.xls"/>
												<display:setProperty name="export.csv" value="false" />
												<display:setProperty name="export.xml" value="false" />
												<display:setProperty name="export.pdf" value="false" />
											</display:table>
										</div>
									</div>
									<div class="boxcontent" align="right">
											<div class="textfield33"></div>
											<div class="textfield33"></div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="adjust.unUtilizedAmount" />
												</div>
												<div class="tbox">
													<s:textfield name="unUtilizedAmt" id="unUtilizedAmt" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
												</div>
											</div>
											<br class="clear"/>
										</div>
								</div>
							</div>
                      		<div class="btnContainer" style="text-align: center;">
                            	<input type="button" value="Back"	class="btn btn-sm btn-danger" onclick="getViewBack(this.form);" />
                            	<input type="button" value="Print" id="print" class="btn btn-sm btn-primary"	onClick="printpage()"/>	
                            	<s:hidden name="mode"/>
                            	<s:hidden name="serialNo"/>
                            </div>
                            </s:form>
                      </s:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function getReports(){
	document.adjustment.action="${pageContext.request.contextPath}/adjustmentListAdjustment.action";
	document.adjustment.submit();
}
function back(form){
	
		form.action="${pageContext.request.contextPath}/adjustmentHomeAdjustment.action";
		form.submit();
	
}
function getBack(form){
	
		document.adjustment.action="${pageContext.request.contextPath}/initAdjustment.action";
		document.adjustment.submit();
	
}
function getViewBack(form){
	
		document.postForm.action="${pageContext.request.contextPath}/initAdjustment.action";
		document.postForm.submit();
	
}
function oncheck(){
      if(document.getElementById("adjustmentDate").value==null || document.getElementById("adjustmentDate").value=='' ){
        	alert("Please Enter Adjustment Entry Date");
        }else{
        allocateAdjustment();
        }
        
}
function allocateAdjustment(){
		document.postForm.action="${pageContext.request.contextPath}/saveadjustmentAdjustment.action";
		document.postForm.submit();
	
}
function printpage()
		{
		document.postForm.mode.value="print";
		document.postForm.action="${pageContext.request.contextPath}/adjustmentViewAdjustment.action";
		document.postForm.submit();
		}
		
function GetCedingCompany(val)
	{		
		if(val!='63') {
			document.getElementById("enddiv").style.display = "none";
	 		return false;
		}
		else {
	 		document.getElementById("enddiv").style.display="inline";
		}
	}
	function setAmount(chkboxId,amountId,rownum,amountVal1,previousVal,checkPC) {
		if(document.getElementById('unUtilizedAmt').value==null || document.getElementById('unUtilizedAmt').value=='' ){
		document.getElementById('unUtilizedAmt').value="0";
		}
		var unUtiliziedAmt = document.getElementById('unUtilizedAmt').value
		var unUtil = unUtiliziedAmt.replace(new RegExp(',', 'g'),'');
		var amountVal = amountVal1.replace(new RegExp(',', 'g'),'');
		//alert(unUtil);
		var result = 0;
		if(document.getElementById(chkboxId).checked==true) {
		//if(document.getElementsByName(chkboxId).checked==true) {
			document.getElementById(amountId).value = amountVal;
			if((checkPC=="Premium")) {
				result = parseFloat(unUtil) + parseFloat(amountVal);
			}
			else if(checkPC=="Claim") {
				result = parseFloat(unUtil) - parseFloat(amountVal);
			}
			else if(checkPC=="Payment") {
				result = parseFloat(unUtil) + parseFloat(amountVal);
			}
			else if(checkPC=="Receipt") {
				result = parseFloat(unUtil) - parseFloat(amountVal);
			}
			document.getElementById('unUtilizedAmt').value =  result.toFixed(2);
			document.getElementById(previousVal).value = amountVal;
		}
		else {
			var prevVal = document.getElementById(previousVal).value;
			if((checkPC=="Premium")) {
				result = parseFloat(unUtil) - parseFloat(amountVal);
			}
			else if(checkPC=="Claim") {
				result = parseFloat(unUtil) + parseFloat(amountVal);
			}
			else if(checkPC=="Payment") {
				result = parseFloat(unUtil) - parseFloat(amountVal);
			}
			else if(checkPC=="Receipt") {
				result = parseFloat(unUtil) + parseFloat(amountVal);
			}
			//var result = parseFloat(unUtil) - parseFloat(amountVal);
			//var prevVal = document.getElementById(previousVal).value;
			//var result = parseFloat(unUtil) - parseFloat(prevVal);
			//document.getElementById(amountId).value = "";
			document.getElementById('unUtilizedAmt').value = result.toFixed(2);
		}
	}	
var openPeriod = {
        start: false,
        end: false
    };
    var itemsPerPage = 10;
    $(function () {
        var curPage = 1;
        _$.fn.datepicker = $.fn.datepicker; //copy function from old jquery
        _$.datepicker = $.datepicker;
        (function ($) {
            var mode = $('input[name="mode"]');
            var dueDateRaw = $('[name="adjustmentDate"]').first();
            var dVal = dueDateRaw.attr('value').split(',');
            var dueDate = dueDateRaw.val((dVal.length > 0) ? dVal[0] : dVal).val();
            var btnContainer = $('.btnContainer').first();
            var boxContent = $('.boxcontent').first();
            //handle post form
            var postForm = $('#postForm').on('submit', function () {
//                if(!confirm("Have you checked all the entries?  Processing will be done for all the marked items. Do you wish to continue?"))return false;
                try {
                    var tbody = $(this).find('tbody');
                    var rows = tbody.find('tr');
                    var form = $('#dummyForm').empty();
                    var total = 0;
                    if (postForm.processCurrent)
                        rows = rows.filter(':visible');
                    var markItems = rows.filter(function () {
                        return $(this).find('[name="isPost"]').is(':checked');
                    });
                    var markItemsCount = markItems.length;
                    var msg = (postForm.processCurrent) ?
                    "Have you checked all the entries in the <b>current page</b>?  <br />" +
                    "Processing will be done for all the marked items [" + markItemsCount + " " + ((markItemsCount) > 1 ? 'items' : 'item') + "]. <br />" +
                    "Do you wish to continue?" :
                    "Have you checked all the entries in <b>all pages</b>?  <br />" +
                    "Processing will be done for all the marked items [" + markItemsCount + " " + ((markItemsCount) > 1 ? 'items' : 'item') + "].<br />" +
                    "Do you wish to continue?";
                    if (markItemsCount < 1) {
                        msmsAlert("There are no marked items in your list to process.<br />Please recheck and try again.");
                        return false;
                    }
                    msmsAlert(msg, {
                        cancelable: true,
                        okFn: function () {
                           if (dueDateRaw.val()) {
                                form.append(dueDateRaw.clone().hide())
                                       .append($('<input type="hidden" name="mode" />').val('post'))
                                       .append($('<input type="hidden" name="faculPremiumBean.settlement_status" />').val('Pending'))
                               ;
                            }
                            $.each(markItems, function () {
                                var row = $(this);
//                                var isPost = row.find('[name="isPost"]').is(':checked');
                                //                    console.log(isPost);
//                                if(isPost){
                                var currentId = total++;
                                $.each(row.find('input'), function () {
                                    var $i = $(this);
                                    var i = $('<input type="hidden" />')
                                            .attr('name', 'adjustmentList[' + currentId + '].' + $i.attr('name'))
                                            .attr('value', $i.val());
                                            //alert(i);
                                    form.append(i);
                                });
//                                }
                            });
                            form.submit();
                        }
                    });

                } catch (e) {
                }
//                $.post("", data2sent,function(data){});
//                var data1 = JSON.stringify(data2sent);
                return false;
            });
            btnContainer.find('.btnProcess').on('click', function () {
                var _btn = $(this);
                postForm.processCurrent = _btn.hasClass('page');
                postForm.submit();
                return false;
            });
            btnContainer.find('.btnPrintView').on('click', function () {
                viewPrintList();
                return false;
            });
            var sameDueDate = ["transactionDate", "receiveDate"];
            if (dueDate && mode.val() != 'view') {
                $.each(sameDueDate, function (i, v) {
                    $('[name="' + v + '"]').val(dueDate);
                });
            } else {

            }
            var records = $('#record');
            var loader = $('#loader');
            var pagingHolder = $('ul#paging');

            makePaging(curPage);
            function makePaging(page) {
                records.fadeIn();
                page = page || curPage;
                var btn2show = 3;
                loader.hide();
                var rows = records.find('tbody>tr');
                var maxPage = Math.ceil(rows.length / itemsPerPage);
                var tbody = records.find('tbody').fadeOut(150, function () {
                    rows.hide();
//                    var lastPageItemsLength = maxPage * perPage / rows.length;
                    if (page < 1)page = 1;
                    if (page > maxPage)page = maxPage;
                    var cur = page * itemsPerPage - itemsPerPage;
                    while (++cur <= itemsPerPage * page) {
                        if (cur > rows.length)break;
                        tbody.find(':nth-child(' + cur + ')').show();
                    }
                    tbody.fadeIn();
                });

                curPage = page;
                function makePageBtn() {
                    var i, btn = $('<a href="#" />').addClass('btn btn-xs btn-warning');
                    pagingHolder.empty().append(makeBtn('first'))
                            .append(makeBtn('prev'));
                    for (i = curPage - btn2show; i < curPage; i++) {
                        if (i > 0)
                            pagingHolder.append(makeBtn(i));
                    }
                    pagingHolder.append(makeBtn(curPage));
                    for (i = curPage + 1; i <= maxPage && i <= curPage + btn2show; i++)
                        pagingHolder.append(makeBtn(i));
                    pagingHolder.append(makeBtn('next'))
                            .append(makeBtn('last'));
                    function makeBtn(p) {
                        var text = (p == 'first') ? 'First' : ((p == 'prev') ? 'Prev' : ((p == 'next') ? 'Next' : ((p == 'last') ? 'Last (' + maxPage + ')' : p))),
                                item = $('<li />').append(btn.clone().text(text));
                        if (p == curPage || ((p == 'first' || p == 'prev') && curPage == 1) || ((p == 'last' || p == 'next') && curPage >= maxPage)) {
                            var a = item.on('click', function () {
                                return false;
                            }).find('a').attr('disabled', true).removeClass('btn-warning').addClass((p == curPage) ? 'btn-danger' : 'btn-default');
                        } else {
                            item.on('click', function () {
                                p = (p == 'first') ? 1 : ((p == 'prev') ? curPage - 1 : ((p == 'next') ? curPage + 1 : ((p == 'last') ? maxPage : p)));
                                makePaging(p);
                            });
                        }
                        return item;
                    }

                }

                makePageBtn();
            }

            function validateBeforePost(rows) {

            }

            boxContent.on('dblclick', '[readonly]', function () {
                msmsAlert("This item not available to modified or click!");
            });

            if (mode.val() == 'view') {
                records.find('tr input').prop('disabled', true);
                btnContainer.find('.btnProcess').hide();
                btnContainer.append($('<a href="">').attr("href", "#").addClass("btn btn-warning btn-sm").text("Back").on('click', function () {
                    window.history.back();
                    return false;
                }));
            }
            records.find('input.date').datepicker({
                dateFormat: 'dd/mm/yy',
                onClose: function () {
                    $(this).trigger('dateChange');
                }
            });

//            Event handling
            records.find('.premiumOC,.adjustAmount,.cost>input').on('blur', function () {
                $(this).val(makeMoney(parseMoney($(this).val())));
            });
            records.find('input').on('keyup dateChange', function () {
                if ($(this).hasClass('premiumOC'))fillCost(this);
                checkVal($(this));
            });
            //validate transactionDate
            records.find('input[name="adjustmentDate"]').on('dateChange', function () {
                var tDate = $(this);
                var row = $(tDate).parents('tr');
                var rDate = row.find('input[name="adjustmentDate"]');
                try {
                    var _date = $.datepicker.parseDate('dd/mm/yy', tDate.val());
                    var _rDate = $.datepicker.parseDate('dd/mm/yy', rDate.val());
                    if (openPeriod.date && openPeriod.date.start && openPeriod.date.end) {
                        if (!(_date >= openPeriod.date.start && _date <= openPeriod.date.end) || tDate.val().length < 10) {
                            showErr(tDate, true);
                            msmsAlert("Not a valid transaction date, only value <b>between " + openPeriod.start + " and  " + openPeriod.end + " accepted</b>.");
                            tDate.val(dueDate);
                        }
                        showErr(tDate, false);
                    }
                    if (_rDate > getDate(tDate.val()))rDate.val(tDate.val());
                } catch (e) {
                    msmsAlert("Not a valid transaction date, only value <b>between " + openPeriod.start + " and  " + openPeriod.end + " accepted</b>.");
                }
            });
            records.find('input[name="receiveDate"]').on('dateChange', function () {
                var tDate = records.find('input[name="transactionDate"]');
                var rDate = $(this);
                var errMsg = "Not a valid statement receive date. Date must be <b>less than or equal to transaction date</b>";
                try {
                    var _tDate = $.datepicker.parseDate('dd/mm/yy', tDate.val());
                    var _date = $.datepicker.parseDate('dd/mm/yy', rDate.val());
                    if (_tDate && _date) {
                        if (!(_date <= _tDate) || rDate.val().length < 10) {
                            showErr(rDate, true);
                            msmsAlert(errMsg);
                            rDate.val(tDate.val());
                        }
                        showErr(rDate, false);
                    }
                } catch (e) {
                    msmsAlert(errMsg);
                }
            });

            $.each(records.find('td.sid'), function () {
                if (mode.val() != 'view')
                    fillCost(this);
                checkVal(this);
            });

            records.find('.premiumOC,.cost>input').map(function () {
                $(this).trigger('blur');
            });
            function fillCost(item) {
                item = $(item);
                if (item) {
                    var row = $(item).parents('tr');
                    var pItem = row.find('[name="premiumOC"]');
                    var premium = parseMoney(pItem.val());
                    row.find('.cost>input').each(function () {
                        if ($(this).attr('data-cost')) {
                            var costItem = parseMoney($(this).val(makeMoney((parseMoney($(this).attr('data-cost')) / 100) * (premium || 0))).val()) || 0;
                            $(this).attr('placeholder', makeMoney(costItem));
                            return costItem;
                        }
                    }).get();
                }
            }

            function checkVal(item) {
                item = $(item);
                if (item) {
                    var row = item.parents('tr');
                    if (item.hasClass('date')) {
                        if (item.val().length == 10) {
                            getExcRate(item);
                            showErr(item, false);
                        } else {
                            showErr(item, true);
                        }
                    } else {
                        var premium = parseMoney(row.find('[name="premiumOC"]').val());
                        var cost = sumArray(row.find('.cost>input[type="text"]').map(function () {
                                    return parseMoney($(this).val()) || 0;
                                }).get()) || 0;
//                        console.log(premium);
                        row.find('[name="faculPremiumBean.netDue"]').val(makeMoney(premium - cost));
                    }
                }
            }

            function getExcRate(dateInput) {
                var row = dateInput.parents('tr');
                var url = 'getExcRateRiskDetails.action?dropDown=exchRate';
                $.get(url, {
                    incepDate: row.find('[name="transactionDate"]').first().val(),
                    orginalCurrency: row.find('[name="currencyId"]').first().val()
                }, function (response) {
                    var exRate = parseFloat($(response).find('#exchRate').val()) || 0;
                    row.find('[name="exchangeRate"]').val(exRate);
                });
            }

            function showErr(input, bool) {
                input = $(input);
                if (bool) {
                    input.css({
                        background: '#fff1e8'
                    });
                } else {
                    input.css({
                        background: ''
                    });
                }
            }

            $.post('openPeriodJson.do', {transactionDate: '01/01/2000'}, function (response) {
                if (!(response && response.model && response.model.valid)) {
                    openPeriod.start = response.model.openPeriodStartDate;
                    openPeriod.end = response.model.openPeriodEndDate;
                    try {
                        openPeriod.date = {
                            start: $.datepicker.parseDate("dd/mm/yy", openPeriod.start),
                            end: $.datepicker.parseDate("dd/mm/yy", openPeriod.end)
                        }
                    } catch (e) {
                    }
                }
            }, 'json');

            function getDate(dateStr, def) {
                try {
                    return $.datepicker.parseDate('dd/mm/yy', dateStr);
                } catch (e) {
                    return def || dateStr;
                }
            }

            //init Checkbox Toggle
         var isOpen = false;
            var dropdownToggle = $('[data-toggle="dropdown"]').on('click', function () {
                var _toggle = $(this), _menu = _toggle.next('ul');
                var container = $('.contents>.boxcontent').first();
                if (!_menu.is(':visible')) {
                    _menu.css({
                        top: _toggle.position().top + _toggle.outerHeight() + container.scrollTop(),
                        left: _toggle.position().left
                    }).fadeIn(function () {
                        isOpen = true;
                    });
                }
                _menu.find('.checkToggle').on('click', function () {
                    var _i = $(this), o = {
                        pageOnly: _i.hasClass('page'),
                        check: _i.hasClass('check')
                    };
                    var cbs = records.find('tbody>tr [id="isPost"]');
                    if (o.pageOnly) cbs = cbs.filter(':visible');
                    cbs.prop('checked', !!(o.check));
                });
                _menu.find('.perPageToggle[data-page]').on('click', function () {
                    var _item = $(this);
                    try {
                        _menu.find('.perPageToggle a>i').removeClass('glyphicon-check').addClass('glyphicon-unchecked');
                        _item.find('a>i').removeClass('glyphicon-unchecked').addClass('glyphicon-check');
                        var p = _item.attr('data-page');
                        itemsPerPage = parseInt(p) || 10;
                        makePaging(1);
                    } catch (e) {
                    }
                });
            }), dropdownMenu = dropdownToggle.next('ul');


            $(document || window).on('click', function () {
                if (isOpen)
                    dropdownMenu.fadeOut(function () {
                        isOpen = false;
                    });
            });

            function msmsAlert(txt, opt) {
                opt = $.extend({okFn: false, cancelable: false}, opt);
                $('.containerAlert').remove();
                var root = $('body').first();
                var modalContainer = $('<div class="modal fade containerAlert" />').appendTo(root).show();
                var alertButtons = $('<div class="alert-btn" />')
                        .append($('<a href="#" />').addClass('btn btn-sm btn-info').text('Ok').on('click', function () {
                            if (typeof opt.okFn == "function") {
                                opt.okFn();
                            }
                            modalContainer.slideUp(150, function () {
                                $(this).remove();
                            });
                        }));
                if (opt.cancelable)
                    alertButtons.append($('<a href="#" />').addClass('btn btn-sm btn-danger').text('Cancel').on('click', function () {
                        modalContainer.slideUp(150, function () {
                            $(this).remove();
                        });
                    }));
                $('<div class="modal-dialog" />')
                        .append($('<div class="modal-content" />')
                                .append($('<p />').html(txt))
                                .append(alertButtons)
                ).appendTo(modalContainer.addClass('in'));
            }

            function viewPrintList() {
                $('.containerPrint').remove();
                var root = $('body').first();
                var modalContainer = $('<div class="modal fade containerPrint" />').appendTo(root).show();
                var rows = records.find('tr:visible');
                var tbl = $('<table id="content2print"/>');
                var btns = $('<div class="alert-btn" />')
                        .append($('<a href="#" />').addClass('btn btn-sm btn-warning').text('Select All').on('click', function () {
                            selectElementContents(tbl.get(0));
                            return false;
                        }))
                        .append($('<a href="#" />').addClass('btn btn-sm btn-info').text('Close').on('click', function () {
                            modalContainer.slideUp(150, function () {
                                $(this).remove();
                            });
                            return false;
                        }));
                rows.each(function () {
                    var row = $(this);
                    var pRow = $('<tr/>');
                    row.find('th,td').each(function () {
                        var _o = $(this), _i;
                        if (row.parent().prop('tagName') == "THEAD") {
                            _i = $('<th/>').text(_o.text());
                        } else {
                            _i = $('<td/>');
                            var _i2 = _o.find('input:visible').first();
                            _i.text((_i2.is(':checkbox')) ? (_i2.is(':checked') ? 'yes' : 'no') : ((_i2.length > 0) ? "" + _i2.val().replace(/(\d{2})\/(\d{2})\/(\d{4})/g,"$1/$2/$3.") : _o.text()));
                        }
                        pRow.append(_i);
                    });
                    tbl.append(pRow);
                });
                $('<div class="modal-dialog" />')
                        .append($('<div class="modal-content" />')
                                .append($('<div class="printView" />').html($('<h4>Export View</h4>')).append(tbl)
                                        .append($('<p />').text('Press Ctrl + C to copy selected items.')))
                                .append(btns)
                ).appendTo(modalContainer.addClass('in'));
                selectElementContents(tbl.get(0));
            }

            function selectElementContents(el) {
                var body = document.body, range, sel;
                if (document.createRange && window.getSelection) {
                    range = document.createRange();
                    sel = window.getSelection();
                    sel.removeAllRanges();
                    try {
                        range.selectNodeContents(el);
                        sel.addRange(range);
                    } catch (e) {
                        range.selectNode(el);
                        sel.addRange(range);
                    }
                } else if (body.createTextRange) {
                    range = body.createTextRange();
                    range.moveToElementText(el);
                    range.select();
                }
            }
        })(_$);
    });

    function makeMoney(n) {
        if (!n)return "0";
        return n.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
    }
    function parseMoney(n) {
        n = n + "";
        if (!n) return 0;
        return parseFloat(n.replace(/[^\d\.\- ]/g, '').trim());
    }
    function sumArray(arr) {
        var n = arr.length, sum = 0;
        while (n--)
            sum += parseMoney(arr[n]) || 0;
        return sum;
    }
function numberWithCommas()
{
	var pml=document.getElementById('adjustmentAmounts').value
	if(pml!=null && pml!="")
	{		
	document.getElementById('adjustmentAmounts').value=Comma(pml);
	}
} 
    var test=document.adjustment.test.value;
	if (test=="ALL"){
	contractScript("ALL");
	}
function contractScript(value) {
		if(value=="S") {
			document.getElementById('endtlabel').style.display='block';			
		}
		else if(value=="ALL") {
			document.getElementById('endtlabel').style.display='none';
		}
	}
	if(document.getElementById("brokerId").value=='63'){
		GetCedingCompany('63');
		}
	function validateFloatKeyPress(el) {
	var el1=el.value;
	el1=el1.replace(/[\s,]+/g,'').trim();	
    var v = parseFloat(el1);
    el.value = (isNaN(v)) ? '' : v.toFixed(2);
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
