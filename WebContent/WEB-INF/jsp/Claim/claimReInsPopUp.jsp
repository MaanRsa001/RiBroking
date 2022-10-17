<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
    <sj:head jqueryui="true" jquerytheme="start"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${pageContext.request.contextPath}/css/autoProcess.css" type="text/css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
   <!--  <link rel="stylesheet" type="text/css" href="css/select2-3.4.1.css"/>
    <link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/> -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/css/bootstrap.min.css"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
    <script src="tableToExcel.js"></script>
    <style>
        .button {background-color: #4CAF50; /* Green */}

        .button2 {background-color: #008CBA;} /* Blue */
        .button3 {background-color: #f44336;} /* Red */
        .button4 {background-color: #e7e7e7; color: black;} /* Gray */
        .button5 {background-color: #555555;} /* Black */
    </style>
    <style>
			#loading {
				width: 100%;
				height: 100%;
				top: 0px;
				left: 0px;
				position: fixed;
				display: block;
				opacity: 0.7;
				background-color: #fff;
				z-index: 99;
				text-align: center;
			}

			#loading-image {
				position: absolute;
				top: 30%;
				left: 45%;
				z-index: 100;
				width: 100px;
				height: 100px;
			}

		</style>
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
 
 </style>
 <SCRIPT language="javascript">
$(function(){

	 $("#selectall").click(function () {
        if ($("#selectall").is(':checked')) {
            $(".checkItem").prop("checked", true);
        } else {
            $(".checkItem").prop("checked", false);
        }
        getTotalVal();
    });
    
     $(".checkItem").click(function () {
            $("#selectall").prop("checked", false);
            getTotalVal();
    });
    
});

</SCRIPT>
</head>
<body>
<div id="loading" style="width:100%;" >
		 <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif" />
</div>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">
                    <div class="boxcontent">
                        <s:form id="reins" name="reins"  theme="simple" action="" method="post" >
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Reinstatement Premium 
                            </div>
						   <div class="panel-body">
               					<div class="boxcontent">
                                    <div class="panel panel-primary">
                                        <div class="panel-body">
                                            <div class="boxcontent">
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.contractno" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="policy_Contract_No"/>
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
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.premiumClass" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="departmentName"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.cedingCompany" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="ceding_company_Name"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.broker" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="broker_Name"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.treatyName" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="treaty_Name"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.inceptionDate" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="from"/>
                                                        <s:hidden name="from" />
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.expiryDate" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="to"/>
                                                        <s:hidden name="to" />
                                                    </div>
                                                </div>
                                                <br class="clear"/>
                                               
                                            </div>
                                        </div>
                                        <br class="clear"/>
                                    </div>
                                </div>
                                 <div class="panel panel-primary">
		                            <div class="panel-heading">
		                               	XL Premium <s:if test="XlPremiumList.size()>0"> <input type="button" value="Reset"  onclick="getcheckBoxCheck();" class="btn btn-sm btn-info" /></s:if>
		                            </div>
								   <div class="panel-body">
								   <div class="boxcontent" >
										<div class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;">
										<s:if test="XlPremiumList.size()>0">
											<table class="table table-bordered" id="Contract" width="100%">
											<thead>
											<tr>
												<th   class="no-sort" width="3%" style="text-align: center; vertical-align: middle;"><s:text name="Select" /><input type="checkbox" id="selectall"/></th>
												<th class="tableColWidth1" style="text-align: center; vertical-align: middle;" ><s:text name="Transaction No" /></th>
												<th class="tableColWidth1" style="text-align: center; vertical-align: middle;">Class</th>
												<th class="tableColWidth1" style="text-align: center; vertical-align: middle;"><s:text name="Transaction Date" /></th>
												<th class="tableColWidth1" style="text-align: center; vertical-align: middle;"><s:text name="Installment No" /></th>
												<th class="tableColWidth1" style="text-align: center; vertical-align: middle;"><s:text name="Currency" /></th>
												<th class="tableColWidth" style="text-align: center; vertical-align: middle;"><s:text name="Booked Premium - OC" /></th>
												<th class="tableColWidth" style="text-align: center; vertical-align: middle;"><s:text name="Booked Premium -  " /><s:property value="shortname"/></th>
												<th class="tableColWidth" style="text-align: center; vertical-align: middle;"><s:text name="Exchange Rate to - "/><s:property value="currencyName"/></th>
												<th class="tableColWidth" style="text-align: center; vertical-align: middle;"><s:text name="Booked Premium - " /><s:property value="currencyName"/></th>
											</tr>
											</thead>
											<tbody>
												<s:iterator value="XlPremiumList" var="list" status="stat">
												<s:set name="myrow" value="#list.record"/>
													<tr >
														<td style="text-align: center; vertical-align: middle;" > <input type="checkbox" value="${TRANSACTION_NO}"  id="chkbox${stat.count-1}"  class="checkItem"  name="checkItem" onclick="getTotalVal();"/>
														  </td>
														<td>${TRANSACTION_NO}</td>
														<td>${TMAS_DEPARTMENT_NAME}</td>
														<td>${TRANSACTION_MONTH_YEAR}</td>
														<td><s:if test='"AP".equals(#list.INSTALMENT_NUMBER)'>
														${INSTALMENT_NUMBER}
														</s:if>
														<s:else>
														MDP :${INSTALMENT_NUMBER}
														</s:else></td>
														<td>${CURRENCY_NAME}</td>
														<td><s:textfield name="claimPremOC[%{#stat.count-1}]" id="claimPremOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{claimPremOC[#stat.count-1]==null?'0.00':claimPremOC[#stat.count-1]}"/></td>
														<td><s:textfield name="claimPremDC[%{#stat.count-1}]" id="claimPremDC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{claimPremDC[#stat.count-1]==null?'0.00':claimPremDC[#stat.count-1]}"/></td>
														<s:if test='"Y" == fielddisableXlPrm[#stat.count-1]'>
															<td id="excbook<s:property value="#stat.count-1"/>" > <s:textfield name="claimExcRate[%{#stat.count-1}]" id="claimExcRate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow8DigitDecValues(this);disableForm(this.form,false,'');getBookedPremiumVal('%{#stat.count-1}');"   maxlength="30"  value="%{claimExcRate[#stat.count-1]==null?'0.00':claimExcRate[#stat.count-1]}" disabled='%{("Y" == fielddisableXlPrm[#stat.count-1])?true:false}'/></td>
															<td id="claimbook<s:property value="#stat.count-1"/>" > <s:textfield name="claimBookedPremium[%{#stat.count-1}]" id="claimBookedPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);disableForm(this.form,false,'');getExchangeRate('%{#stat.count-1}');"   maxlength="30" value="%{claimBookedPremium[#stat.count-1]==null?'0.00':claimBookedPremium[#stat.count-1]}" disabled='%{("Y" == fielddisableXlPrm[#stat.count-1])?true:false}'/></td>
														</s:if>
														<s:else>
															<td id="excbook<s:property value="#stat.count-1"/>" style="background-color:#C6E2FF;"> <s:textfield name="claimExcRate[%{#stat.count-1}]" id="claimExcRate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow8DigitDecValues(this);disableForm(this.form,false,'');getBookedPremiumVal('%{#stat.count-1}');"   maxlength="30"  value="%{claimExcRate[#stat.count-1]==null?'0.00':claimExcRate[#stat.count-1]}" disabled='%{("Y" == fielddisableXlPrm[#stat.count-1])?true:false}'/></td>
															<td id="claimbook<s:property value="#stat.count-1"/>" style="background-color:#C6E2FF;"> <s:textfield name="claimBookedPremium[%{#stat.count-1}]" id="claimBookedPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);disableForm(this.form,false,'');getExchangeRate('%{#stat.count-1}');"   maxlength="30" value="%{claimBookedPremium[#stat.count-1]==null?'0.00':claimBookedPremium[#stat.count-1]}" disabled='%{("Y" == fielddisableXlPrm[#stat.count-1])?true:false}'/></td>
														</s:else>
														
													<s:hidden  name="baseExchRate[%{#stat.count-1}]" id="baseExchRate%{#stat.count-1}"/>
													<s:hidden  name="insNum[%{#stat.count-1}]" id="insNum%{#stat.count-1}" value="%{#list.INSTALMENT_NUMBER}"/>
													</tr>
												</s:iterator>
												<tr id="total" style="display:none">
												
														<td>Total :</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td id="book" style="background-color:#C6E2FF;"><s:textfield name="totalBookedPremium" id="totalBookedPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);fieldDisableXl();"   maxlength="30" value="%{claimBookedPremium[#stat.count-1]==null?'0.00':claimBookedPremium[#stat.count-1]}"/></td>
													</tr>
											</tbody>
											</table>
											</s:if>
											 <s:else>
					                              <div class="panel panel-primary" >
					                                  <div class="panel-body">
					                                  <table class="table table-bordered" id="Contract" width="100%">
					                                  </table>
					                                      No Data Available
					                                  </div>
					                              </div>
					                          </s:else>
										</div>
								   	</div>
								 	</div>  
								 </div>
								 <div class="panel panel-primary">
		                            <div class="panel-heading">
		                               		Claims Paid  
		                            </div>
								   <div class="panel-body">
								   <div class="boxcontent" >
										<div class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;">
										<s:if test="claimPaidList.size()>0">
											<table class="table table-bordered" id="Paid" class="grid" width="100%">
											<thead>
											<tr >
												<th class="no-sort" width="1%"><s:text name="Sr No" /></th>
												<th  class="index" width="2%"><s:text name="Reinstatement Order" /></th>
												<th style="text-align: center; vertical-align: middle;">Claim No</th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Claim Payment No" /></th>
												<th style="text-align: center; vertical-align: middle;">Class</th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Payment Date" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Claim Currency" /></th>
												<th style="text-align: center; vertical-align: middle;" ><s:text name="Treaty Currency (TC) " /></th>
												<th style="text-align: center; vertical-align: middle;" ><s:text name="Claim Paid - OC"/></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Claim Paid - " /><s:property value="shortname"/></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Exchange Rate to TC" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Claim Paid – TC" /></th>
											</tr>
											</thead>
											<tbody>
												<s:iterator value="claimPaidList" var="list" status="stat">
													<tr >
														<td  ><s:property value="#stat.count" />   </td>
														<td class="index" style="text-align: center; vertical-align: middle;" > <s:property value="#stat.count" />
														
														</td>
														<td class="tableColWidth1">${CLAIM_NO}</td>
														<td class="tableColWidth1">${CLAIM_PAYMENT_NO}</td>
														<td class="tableColWidth1">${TMAS_DEPARTMENT_NAME}</td>
														<td class="tableColWidth1">${INCEPTION_DATE}</td>
														<td class="tableColWidth1">${CURRENCY_NAME}</td>
														<td class="tableColWidth1">${RDS_CURRENCY_NAME}</td>
														<td class="disabled tableColWidth" ><s:textfield name="claimPaidOC[%{#stat.count-1}]" id="claimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{claimPaidOC[#stat.count-1]==null?'0.00':claimPaidOC[#stat.count-1]}"/></td>
														<td class="tableColWidth"><s:textfield name="claimPaidDC[%{#stat.count-1}]" id="claimPaidDC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{claimPaidDC[#stat.count-1]==null?'0.00':claimPaidDC[#stat.count-1]}"/></td>
														<s:if test='"Y" == fielddisableXlPrm[#stat.count-1]'>
														<td class="tableColWidth" id="claimExc<s:property value="#stat.count-1"/>" > <s:textfield name="claimEcxhangeRate[%{#stat.count-1}]" id="claimEcxhangeRate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow8DigitDecValues(this);getClaimPaid('%{#stat.count-1}');"   maxlength="30"  value="%{claimEcxhangeRate[#stat.count-1]==null?'0.00':claimEcxhangeRate[#stat.count-1]}" disabled='%{("Y" == fielddisableClaimsPaid[#stat.count-1])?true:false}'/></td>
														<td class="tableColWidth" id="claimPaid<s:property value="#stat.count-1"/>" > <s:textfield name="claimPaidPremium[%{#stat.count-1}]" id="claimPaidPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);middleMinusRestriction(this);getClaimPaidExchRate('%{#stat.count-1}');"   maxlength="30" value="%{claimPaidPremium[#stat.count-1]==null?'0.00':claimPaidPremium[#stat.count-1]}" disabled='%{("Y" == fielddisableClaimsPaid[#stat.count-1])?true:false}'/></td>
														</s:if>
														<s:else>
														<td class="tableColWidth" id="claimExc<s:property value="#stat.count-1"/>" style="background-color:#C6E2FF;"> <s:textfield name="claimEcxhangeRate[%{#stat.count-1}]" id="claimEcxhangeRate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow8DigitDecValues(this);getClaimPaid('%{#stat.count-1}');"   maxlength="30"  value="%{claimEcxhangeRate[#stat.count-1]==null?'0.00':claimEcxhangeRate[#stat.count-1]}" disabled='%{("Y" == fielddisableClaimsPaid[#stat.count-1])?true:false}'/></td>
														<td class="tableColWidth" id="claimPaid<s:property value="#stat.count-1"/>" style="background-color:#C6E2FF;"> <s:textfield name="claimPaidPremium[%{#stat.count-1}]" id="claimPaidPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);getClaimPaidExchRate('%{#stat.count-1}');"   maxlength="30" value="%{claimPaidPremium[#stat.count-1]==null?'0.00':claimPaidPremium[#stat.count-1]}" disabled='%{("Y" == fielddisableClaimsPaid[#stat.count-1])?true:false}'/></td>
														</s:else>
													<s:hidden  name="claimBaseExchRate[%{#stat.count-1}]" id="claimBaseExchRate%{#stat.count-1}"/>
													<s:hidden  name="claimNoList[%{#stat.count-1}]" id="claimNoList%{#stat.count-1}"/>
													<s:hidden  name="paidSno[%{#stat.count-1}]" id="paidSno%{#stat.count-1}"/>
													<s:hidden  name="claimPaymentNoList[%{#stat.count-1}]" id="claimPaymentNoList%{#stat.count-1}"/>
													<s:hidden  name="departmentNameList[%{#stat.count-1}]" id="departmentNameList%{#stat.count-1}"/>
													<s:hidden  name="inceptionDateList[%{#stat.count-1}]" id="inceptionDateList%{#stat.count-1}"/>
													<s:hidden  name="currencyNameList[%{#stat.count-1}]" id="currencyNameList%{#stat.count-1}"/>
													<s:hidden  name="rdsCurrencyNameList[%{#stat.count-1}]" id="rdsCurrencyNameList%{#stat.count-1}"/>
													<s:hidden  name="reinsNum[%{#stat.count-1}]" id="reinsNum%{#stat.count-1}"/>
													</tr>
												</s:iterator>
											</tbody>
											</table>
											</s:if>
											 <s:else>
					                              <div class="panel panel-primary" >
					                                  <div class="panel-body">
					                                      No Data Available
					                                  </div>
					                              </div>
					                          </s:else>
										</div>
										<br class="clear"/>
										<br class="clear"/>
										<div class="boxcontent" align="center">
		                                    <input type="button" value="Calculate Reinst. Premium"  onclick="disableForm(this.form,false,'');getCalculationValue();" class="btn btn-sm btn-info" id="calc"/>
		                                </div>
								   	</div>
								 	</div>  
								 </div>
								 <div id="Calc">
								 <s:hidden name="tbleorder" id="tbleorder" value="N"/>
								 </div>
                                <div class="boxcontent" align="center">
                                    <input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
                                    <s:if test='"view"!=paymentFlag'>
                                    <input type="button" value="Submit"  onclick="disableForm(this.form,false,'');reInssub();" class="btn btn-sm btn-success" />
                                    </s:if>
                                </div>
                                <s:hidden name="departmentId" id="departmentId"/>
                                <s:hidden name="layerNo" id="layerNo"/>
                                <s:hidden name="reinstType" id="reinstType"/>
                                <s:hidden name="proposal_No" id="proposal_No"/>
                                <s:hidden name="policy_Contract_No" id="policy_Contract_No"/>
                                <s:hidden name="productId" id="productId"/>
                                <s:hidden name="shortname" id="shortname"/>
                                <s:hidden name="paid_claim_os" id="paid_claim_os"/>
                                <s:hidden name="surveyor_fee_os" id="surveyor_fee_os"/>
                                <s:hidden name="other_prof_fee_os" id="other_prof_fee_os"/>
                                 <s:hidden name="claim_No" id="claim_No"/>
                                 <s:hidden name="paymentFlag" id="paymentFlag"/>
                                 <s:hidden name="claimPaymentNo" id="claimPaymentNo"/>
                                 
                                 
                            </div>
                        </div>
                        </s:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

var tbleorder = document.getElementById("tbleorder").value;

if("N"==tbleorder){
var fixHelperModified = function(e, tr) {
    var $originals = tr.children();
    var $helper = tr.clone();
    $helper.children().each(function(index) {
        $(this).width($originals.eq(index).width())
    });
    return $helper;
},
    updateIndex = function(e, ui) {
    var tbl = document.getElementById("Paid").tBodies[0];
        $('td.index', ui.item.parent()).each(function (i) {
        	$(this).html(i + 1);
        	var row = tbl.rows[i];
       		 	var numor = parseFloat(row.cells[0].textContent || row.cells[0].innerText);
            	document.getElementById("paidSno"+i).value= numor;
            	var newr=numor-1;
	       
        });
    };
    
$("#Paid tbody").sortable({
    helper: fixHelperModified,
    update: updateIndex,
}).disableSelection();

}
	function reInssub(){
	var pid= document.getElementById("productId").value;
	if(pid==4 || pid==5){
		document.reins.action = "${pageContext.request.contextPath}/submitReInsRetroclm.do";
	}else{
		document.reins.action = "${pageContext.request.contextPath}/submitReInsClaim.do";
		}
    	document.reins.submit();
	}
    function fnRecalculate(){
    	document.profitPremiumpopup.action = "${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode=profit&flag=recal";
    	document.profitPremiumpopup.submit();
    }
    function getBookedPremiumVal(id){
    	var rate = document.getElementById("claimExcRate"+id).value;
    	var premium =  document.getElementById("claimPremOC"+id).value;
    	if(rate==''){
    	rate=0;
    	}
    	else{
    	rate=rate.replace(new RegExp(',', 'g'),'');
    	}
    	premium = premium.replace(new RegExp(',', 'g'),'');
    	//if(parseFloat(rate)>0 &&parseFloat(premium)>0){
    	var total = parseFloat(premium)*parseFloat(rate);
    	//}
    	document.getElementById("claimBookedPremium"+id).value = Comma(total.toFixed(2));
    	getTotalVal();
    }
    
    
    function getExchangeRate(id){
   		var rate = document.getElementById("claimBookedPremium"+id).value;
    	var premium =  document.getElementById("claimPremOC"+id).value;
    	var total = 0;
    	if(rate==''){
    	rate=0;
    	}
    	else{
    	rate=rate.replace(new RegExp(',', 'g'),'');
    	}
    	premium = premium.replace(new RegExp(',', 'g'),'');
    	//if(parseFloat(rate)>0 &&parseFloat(premium)>0){
    	 total = parseFloat(rate)/parseFloat(premium);
    	//}
    	if(total!=''){
    	document.getElementById("claimExcRate"+id).value = Comma(total.toFixed(8));
    	getTotalVal();
    	}
    }
    
    
    function fieldDisableXl(){
     var table = document.getElementById('Contract');
	var rowCount = table.rows.length-2;
	for(var c= 0;c<rowCount;c++){
		document.getElementById("claimBookedPremium"+c).disabled=true;
		document.getElementById("claimExcRate"+c).disabled=true;
		document.getElementById("claimbook"+c).style.backgroundColor ="white";
		document.getElementById("excbook"+c).style.backgroundColor ="white";
		document.getElementById("chkbox"+c).disabled=true;
		
	}
    }
function getTotalVal(){
   var table = document.getElementById('Contract');
	var val=0;
	var rowCount = table.rows.length-2;
	for(var c= 0;c<rowCount;c++){
	var va2 =document.getElementById("chkbox"+c).checked;
	if(document.getElementById("chkbox"+c).checked){
		 var sum = document.getElementById("claimBookedPremium"+c).value;
		 sum= sum.replace(new RegExp(',', 'g'),'');
		if(sum==''){
		 sum =0;
		 }
	 	val +=parseFloat(sum);
	 	document.getElementById("total").style.display='table-row';		
	 	}
	}
	if(val!=''){
	 	val =Comma(val.toFixed(2));
	    document.getElementById("totalBookedPremium").value=val;
	    }
   }
   
   function getClaimPaid(id){
		 var sum = document.getElementById("claimEcxhangeRate"+id).value;
		 var claimPaidOC = document.getElementById("claimPaidOC"+id).value;
		 var val=0;
		 sum= sum.replace(new RegExp(',', 'g'),'');
		 claimPaidOC = claimPaidOC.replace(new RegExp(',', 'g'),'');
		if(sum==''){
			 sum =0;
		 }
		// if(parseFloat(sum)>0 &&parseFloat(claimPaidOC)>0){
	 			val =parseFloat(sum)*parseFloat(claimPaidOC);
	 //	}
	 	//document.getElementById("total").style.display='table-row';		
	 	val =Comma(val.toFixed(2));
	    document.getElementById("claimPaidPremium"+id).value=val;
   }
   
   function getClaimPaidExchRate(id){
		 var sum = document.getElementById("claimPaidPremium"+id).value;
		 var claimPaidOC = document.getElementById("claimPaidOC"+id).value;
		 var val=0;
		 sum= sum.replace(new RegExp(',', 'g'),'');
		 claimPaidOC = claimPaidOC.replace(new RegExp(',', 'g'),'');
		if(sum==''){
		 sum =0;
		 }
		 //if(parseFloat(sum)>0 &&parseFloat(claimPaidOC)>0){
	 		val =parseFloat(sum)/parseFloat(claimPaidOC);
	 	//}
	 	if(val!=''){
	 	document.getElementById("total").style.display='table-row';		
	 	val =Comma(val.toFixed(8));
	    document.getElementById("claimEcxhangeRate"+id).value=val;
	    }
   }
   
   
   function getCalculationValue(){
   	var table = document.getElementById('Contract');
	var rowCount = table.rows.length-2;
	var table1 = document.getElementById('Paid');
	var rowCount1 = table1.rows.length-1;
	var answer=true;
	var sno="";
	var sno1="";
	var selec="";
	for(var c= 0;c<rowCount;c++){
	var va2 =document.getElementById("chkbox"+c).checked;
	if(document.getElementById("chkbox"+c).checked){
	selec=selec+","+c;
	}
	
	var exchangesRate = document.getElementById("claimExcRate"+c).value;
	var cessionVal = document.getElementById("baseExchRate"+c).value;
	if (""==cessionVal){
		cessionVal ='0';
	}
	else {
		cessionVal = cessionVal.replace(new RegExp(',', 'g'),'');
	}
	if (""==exchangesRate){
	exchangesRate ='0';
	}
	var per =((exchangesRate - cessionVal)/cessionVal)*100;
	if (per>5){
		sno +=c+1+","; 
	}
	}
	for(var c= 0;c<rowCount1;c++){
	var exchangesRate = document.getElementById("claimEcxhangeRate"+c).value;
	var cessionVal = document.getElementById("claimBaseExchRate"+c).value;
	if (""==cessionVal){
		cessionVal ='0';
	}
	else {
		cessionVal = cessionVal.replace(new RegExp(',', 'g'),'');
	}
	if (""==exchangesRate){
	exchangesRate ='0';
	}
	var per =((exchangesRate - cessionVal)/cessionVal)*100;
	if (per>5){
		sno1 +=c+1+","; 
	}
	}
	if(sno!=""){
	answer = confirm("The exchange rate keyed in Sr No."+sno+" has a variance of 5% or more.  Do you wish to continue?");
	}
	if(answer){
		if(sno1!=""){
			answer = confirm("The exchange rate keyed in Sr No."+sno1+" has a variance of 5% or more.  Do you wish to continue?");
		}
     	if(answer){
     	var pid= document.getElementById("productId").value;
     	 var path="";
		if(pid==4 || pid==5){
		path="reInsCalculationRetroclm.do?dropDown=Calc";
		}
		else{
		path="reInsCalculationClaim.do?dropDown=Calc&selectedRows="+selec;
		}
    		document.getElementById("calc").style.display='none';
			var form = $('#reins');
			$.ajax({
	        type: "POST",
	        data: form.serialize(),
	        url: '${pageContext.request.contextPath}/'+path,
	        success: function(data){
	         $('#Calc').html(data);
		           for(var c= 0;c<rowCount1;c++){
	    			 document.getElementById("claimPaidPremium"+c).disabled=true;
	    			 document.getElementById("claimEcxhangeRate"+c).disabled=true;
	    			 document.getElementById("claimExc"+c).style.backgroundColor ="white";
					 document.getElementById("claimPaid"+c).style.backgroundColor ="white";
	    			}
	    			for(var c= 0;c<rowCount;c++){
				     	document.getElementById("claimBookedPremium"+c).disabled=true;
				     	document.getElementById("claimExcRate"+c).disabled=true;
					    document.getElementById("totalBookedPremium").disabled=true;
					    document.getElementById("book").style.backgroundColor ="white";
					    document.getElementById("claimbook"+c).style.backgroundColor ="white";
					    document.getElementById("excbook"+c).style.backgroundColor ="white";
					    document.getElementById("chkbox"+c).disabled=true;
					    
				     	}
				     	$("#Paid tbody").sortable("disable");
										     	
	        	}
	        });
	      
     	}
	}
   }
   
function changeTableRow1(val){
 
    var tbl = document.getElementById("Paid").tBodies[0];
    var store = [];
    var len=tbl.rows.length;
    for(var i=0;i<len; i++){
    for(var j=i;j<len; j++){
        var row = tbl.rows[j];
        //alert("j"+j);
        //alert(document.getElementById("reinsNum"+j).value);
        var sortnr = parseFloat(document.getElementById("reinsNum"+j).value);
        if(parseInt(val)>sortnr){
        //document.getElementById("reinsNum"+i).value =sortnr+1;
        }else{
       // document.getElementById("reinsNum"+i).value =sortnr;
        }
        if(!isNaN(sortnr)) store.push([sortnr, row]);
        }
    }
    store.sort(function(x,y){
        return x[0] - y[0];
    });
    for(var i=0, len=store.length; i<len; i++){
        tbl.appendChild(store[i][1]);
    }
    store = null;	
}


getcheckBoxCheck();
function getcheckBoxCheck(){
	var val = document.getElementById("reinstType").value;
	if("MDP"==val){
		checkNumberVal();
	}
	else if("ADP"== val|| "FDP"==val){
		checkAll();
	}
	else if("NA"==val){
		uncheckAll();
	}
	getTotalVal();
}

function checkAll()
{
	$("#selectall").prop("checked", true);
    $('.checkItem').prop("checked", true);
}
function uncheckAll()
{
	 $("#selectall").prop("checked", false);
	 $('.checkItem').prop("checked", false);
	 
}

function checkNumberVal(){
	var table = document.getElementById('Contract');
	var rowCount = table.rows.length-2;
	for(var c= 0;c<rowCount;c++){
		var va2 =document.getElementById("insNum"+c).value;
		if(!isNaN(va2)){
		document.getElementById("chkbox"+c).checked=true;
		}
		else{
		 $("#selectall").prop("checked", false);
		document.getElementById("chkbox"+c).checked=false;
		}
	}
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
