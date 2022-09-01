<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${pageContext.request.contextPath}/css/autoProcess.css" type="text/css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" type="text/css" href="css/select2-3.4.1.css"/>
    <link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/css/bootstrap.min.css"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
    <script src="tableToExcel.js"></script>
    <style>
        .button {background-color: #4CAF50; /* Green */}

        .button2 {background-color: #008CBA;} /* Blue */
        .button3 {background-color: #f44336;} /* Red */
        .button4 {background-color: #e7e7e7; color: black;} /* Gray */
        .button5 {background-color: #555555;} /* Black */
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
     	<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>

</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">
                    <div class="boxcontent">
                        <s:form id="bonusPremiumpopup" name="bonusPremiumpopup" theme="simple" action="" method="post" >
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                               Bonus Calculation and Adjustment
                              <%--  <s:if test='""!=transactionNo && "recal"!=flag'>
                                               		 <input type="button" value="Re Calculate"  onclick="disableForm(this.form,false,'');fnRecalculate();return false;" class="btn btn-sm btn-success pull-right" />
                                 </s:if>--%>
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
                                                        <s:property value="contNo"/>
                                                    </div>
                                                </div>
                                                <s:if test='"3".equals(#session.mfrid)'>
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
                                                        Class
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="subProfit_center"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        As On Date
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="transaction"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        Currency
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="currencyName"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="facultative.bonuspercentage" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="acqBonusName"/>
                                                    </div>
                                                </div>
                                               <%--  <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.layerNo" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="layerno"/>
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
                                                        <s:text name="label.insuredName" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="insured_name"/>
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
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="facultative.bonuspercentage" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="acqBonusName"/>
                                                    </div>
                                                </div>--%>
                                                <br class="clear"/>
                                            </div>
                                        </div>
                                        <br class="clear"/>
                                    </div>
                                </div>
                                <div class="boxcontent">
                                    <s:if test="bonusCommission.size()>0">
                                    <s:iterator value="bonusCommission" var="list" status="stat">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    Currency - ${SHORT_NAME}

                                                </div>
                                                <div class="col-xs-6">
                                                    <div class="row">
                                                        <div class="col-xs-6" style="padding: 0px;">
                                                            Exchange Rate :(1 ${SHORT_NAME}=
                                                        </div>
                                                        <div class="col-xs-4" style="padding: 0px;">
                                                            <s:textfield name="exchRatePrem[%{#stat.count-1}]" id= "exchRatePrem%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;height: 18px;" onblur="this.value=Comma(this.value);allow8DigitDecValues(this);disableForm(this.form,false,'');getRagneValue('%{#stat.count-1}');"   maxlength="30" onchange="rateRange(this.value,'%{#stat.count-1}');" disabled='(#list.SHORT_NAME.equals(currencyName) || "recal".equals(flag))?true:false'/>
                                                        </div>
                                                        <div class="col-xs-2" style="padding: 0px;">
                                                          <s:property value="currencyName"/>)
                                                        </div>
                                                    </div>
                                                </div>
                                                <br class="clear"/>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                            <div class="boxcontent">
                                                <div class="table2">
                                                    <div class="tablerow">
                                                        <span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
                                                        <span style="color:green;"><s:actionmessage/></span>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="row" >
                                                            <div >

                                                                <table class="footable" id="bonusTbl" width="100%" >
                                                                    <thead>
                                                                    <tr>
                                                                        <th width="33%" style="text-align: center; vertical-align: middle;"> <b><s:text name="label.particulers" /></b></th>
                                                                        <th width="33%" style="text-align: center; vertical-align: middle;"><b> ${SHORT_NAME}</b></th>
                                                                        <th width="33%" class="urgent" style="text-align: center; vertical-align: middle;" ><b> ${SHORT_NAME} (Manual) </b></th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.premiumdet" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="premiumOC[%{#stat.count-1}]" id="premiumOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{premiumOC[#stat.count-1]==null ?'0.00':premiumOC[#stat.count-1]}"/>
                                                                        </td>
                                                                         <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualPremiumOC[%{#stat.count-1}]" id="manualPremiumOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);getClaimpaidVal('%{#stat.count-1}');"  onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{(manualPremiumOC==null ||manualPremiumOC[#stat.count-1]=='')?'0.00':manualPremiumOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimpaid" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="claimPaidOC[%{#stat.count-1}]" id="claimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);" onkeyup="allow2DigitDecValues(this);" readonly="true" maxlength="30"  value="%{claimPaidOC[#stat.count-1]==null ?'0.00':claimPaidOC[#stat.count-1]}"/>
                                                                        </td>
                                                                         <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualclaimPaidOC[%{#stat.count-1}]" id="manualclaimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);getClaimpaidVal('%{#stat.count-1}');"  onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{(manualclaimPaidOC==null ||manualclaimPaidOC[#stat.count-1]=='')?'0.00':manualclaimPaidOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimuotstanding" />
                                                                        </td>
                                                                        <td>
                                                                                <s:textfield name="claimOutStandingOC[%{#stat.count-1}]" id="claimOutStandingOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{claimOutStandingOC[#stat.count-1]==null?'0.00':claimOutStandingOC[#stat.count-1]}"/>
                                                                        </td>
                                                                         <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualclaimOutStandingOC[%{#stat.count-1}]" id="manualclaimOutStandingOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);getClaimpaidVal('%{#stat.count-1}');"  onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{(manualclaimOutStandingOC==null ||manualclaimOutStandingOC[#stat.count-1]=='')?'0.00':manualclaimOutStandingOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimratio" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="claimRatioOC[%{#stat.count-1}]" id="claimRatioOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{claimRatioOC[#stat.count-1]==null?'0.00':claimRatioOC[#stat.count-1]}"/>
                                                                        </td>
                                                                         <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualclaimRatioOC[%{#stat.count-1}]" id="manualclaimRatioOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{(manualclaimRatioOC==null ||manualclaimRatioOC[#stat.count-1]=='')?'0.00':manualclaimRatioOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                     <s:if test='"recal"==flag'>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.Bonus" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="bonusOC[%{#stat.count-1}]"  id="bonusOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{bonusOC[#stat.count-1]==null?'0.00':bonusOC[#stat.count-1]}"/>
                                                                        </td>
                                                                         <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualbonusOC[%{#stat.count-1}]" id="manualbonusOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{(manualbonusOC==null ||manualbonusOC[#stat.count-1]=='')?'0.00':manualbonusOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.bonuspaid" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="bonusPaidOCTillDate[%{#stat.count-1}]"  id="bonusPaidOCTillDate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{bonusPaidOCTillDate[#stat.count-1]==null?'0.00':bonusPaidOCTillDate[#stat.count-1]}"/>
                                                                        </td>
                                                                         <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualbonusPaidOCTillDate[%{#stat.count-1}]" id="manualbonusPaidOCTillDate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{(manualbonusPaidOCTillDate==null ||manualbonusPaidOCTillDate[#stat.count-1]=='')?'0.00':manualbonusPaidOCTillDate[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.bonusadj" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="bonusAdjOC[%{#stat.count-1}]" id="bonusAdjOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);disableForm(this.form,false,'');getRagneValue('%{#stat.count-1}');"   maxlength="30"  value="%{bonusAdjOC[#stat.count-1]==null?'0.00':bonusAdjOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                             <%--value='<s:property value="preCurrencylist[%{#stat.count-1}]"/>'--%>
                                                                        </td>
                                                                         <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualbonusAdjOC[%{#stat.count-1}]" id="manualbonusAdjOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{(manualbonusAdjOC==null ||manualbonusAdjOC[#stat.count-1]=='')?'0.00':manualbonusAdjOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
																	</s:if>
																	<s:else>
																	<s:hidden name="bonusOC[%{#stat.count-1}]"  id="bonusOC%{#stat.count-1}"/>
                                                                        <s:hidden name="manualbonusOC[%{#stat.count-1}]"  id="manualbonusOC%{#stat.count-1}"/>
                                                                        <s:hidden name="bonusPaidOCTillDate[%{#stat.count-1}]"  id="bonusPaidOCTillDate%{#stat.count-1}"/>
                                                                        <s:hidden name="manualbonusPaidOCTillDate[%{#stat.count-1}]"  id="manualbonusPaidOCTillDate%{#stat.count-1}"/>
                                                                        <s:hidden name="bonusAdjOC[%{#stat.count-1}]" id="bonusAdjOC%{#stat.count-1}"/>
                                                                        <s:hidden name="manualbonusAdjOC[%{#stat.count-1}]" id="manualbonusAdjOC%{#stat.count-1}"/>
																	
																	</s:else>
                                                                </table>

                                                            </div>
                                                        </div>
                                                        <br class="clear"/>

                                                    </div>
                                                </div>
                                            </div>
                                    </div>
                                    </div>
                                    </s:iterator>
                                        <s:if test='"Y".equals(gridShow)'>
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                    Converted Results in Currency - <s:property value="currencyName"/>
                                            </div>
                                            <div class="panel-body">
                                                <div class="boxcontent">
                                                    <div class="table2">
                                                        <div class="tablerow">
                                                            <span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
                                                            <span style="color:green;"><s:actionmessage/></span>
                                                        </div>
                                                        <div class="panel-body">

                                                            <div class="row" >
                                                                <div >

                                                                    <table class="footable" id="bonusTbl1" width="100%" >
                                                                        <thead>
                                                                        <tr>
                                                                            <th width="33%" style="text-align: center; vertical-align: middle;"> <b><s:text name="label.particulers" /></b></th>
                                                                            <th width="33%" style="text-align: center; vertical-align: middle;"><b><s:property value="currencyName"/></b></th>
                                                                            <th width="33%" style="text-align: center; vertical-align: middle;"><b><s:property value="currencyName"/> (Manual) </b></th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.premiumdet" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conPremiumOC" id="conPremiumOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{conPremiumOC==null?'0.00':conPremiumOC}"/>
                                                                            </td>
                                                                             <td >
                                                                            	<s:textfield name="manualconPremiumOC" id="manualconPremiumOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconPremiumOC==null?'0.00':manualconPremiumOC}"/>
                                                                        	</td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.claimpaid" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conClaimPaidOC" id="conClaimPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"  readonly="true" maxlength="30" value="%{conClaimPaidOC==null?'0.00':conClaimPaidOC}"/>
                                                                            </td>
                                                                             <td >
                                                                            	<s:textfield name="manualconClaimPaidOC" id="manualconClaimPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconClaimPaidOC==null?'0.00':manualconClaimPaidOC}"/>
                                                                        	</td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.claimuotstanding" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conClaimOutStandingOC"  id="conClaimOutStandingOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"  readonly="true" maxlength="30" value="%{conClaimOutStandingOC==null?'0.00':conClaimOutStandingOC}"/>
                                                                            </td>
                                                                            <td >
                                                                            	<s:textfield name="manualconClaimOutStandingOC" id="manualconClaimOutStandingOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconClaimOutStandingOC==null?'0.00':manualconClaimOutStandingOC}"/>
                                                                        	</td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.claimratio" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conClaimRatioOC" id="conClaimRatioOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conClaimRatioOC==null?'0.00':conClaimRatioOC}"/>
                                                                            </td>
                                                                            <td >
                                                                            	<s:textfield name="manualconClaimRatioOC" id="manualconClaimRatioOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconClaimRatioOC==null?'0.00':manualconClaimRatioOC}"/>
                                                                        	</td>

                                                                        </tr>
                                                                        <s:if test='"recal"==flag'>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.Bonus" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conbonusOC" id="conbonusOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conbonusOC==null?'0.00':conbonusOC}"/>
                                                                            </td>
                                                                            <td >
                                                                            	<s:textfield name="manualconbonusOC" id="manualconbonusOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconbonusOC==null?'0.00':manualconbonusOC}"/>
                                                                        	</td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.bonuspaid" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conbonusPaidOC" id="conbonusPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conbonusPaidOC==null?'0.00':conbonusPaidOC}"/>
                                                                            </td>
                                                                            <td >
                                                                            	<s:textfield name="manualconbonusPaidOC" id="manualconbonusPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconbonusPaidOC==null?'0.00':manualconbonusPaidOC}"/>
                                                                        	</td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.bonusadj" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conbonusAdjOC" id="conbonusAdjOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conbonusAdjOC==null?'0.00':conbonusAdjOC}"/>
                                                                            </td>
                                                                            <td >
                                                                            	<s:textfield name="manualconbonusAdjOC" id="manualconbonusAdjOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconbonusAdjOC==null?'0.00':manualconbonusAdjOC}"/>
                                                                        	</td>

                                                                        </tr>
                                                                        </s:if>
																			<s:else>
                                                                        <s:hidden name="conbonusOC" id="conbonusOC"/>
                                                                        <s:hidden name="manualconbonusOC" id="manualconbonusOC"/>
                                                                        <s:hidden name="conbonusPaidOC" id="conbonusPaidOC"/>
                                                                        <s:hidden name="manualconbonusPaidOC" id="manualconbonusPaidOC"/>
                                                                        <s:hidden name="conbonusAdjOC" id="conbonusAdjOC"/>
                                                                        <s:hidden name="manualconbonusAdjOC" id="manualconbonusAdjOC"/>
                                                                        </s:else>
                                                                    </table>

                                                                </div>
                                                            </div>
                                                            <br class="clear"/>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        </s:if>
                                    </s:if>
                                    <s:else>
                                        <div class="panel panel-primary" >
                                            <div class="panel-body">
                                                No Data Available
                                            </div>
                                        </div>
                                    </s:else>
                                </div>

                                <div class="boxcontent" align="center">
                                    <input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
                                    <s:if test="bonusCommission.size()>0">
                                    <s:if test='"recal"==flag'>
	                                    <input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="getPrint()"/>
	                                    <s:if test='"Y".equals(type)'>
                                    		<input type="button" value="Submit" id="butnRemove"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />
                                    	</s:if>
	                                    </s:if>
	                                    <s:if test='"recal"!=flag'>
                                     	<input type="button" id="print" value="Calculate" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');fnRecalculate();return false;"/>
                                     	
                                     </s:if>
                                    
                                    <s:if test='"Y".equals(gridShow)'>
                                    <button type="button" class="btn btn-success btn-sm" id="view" data-toggle="modal" data-target="#myModal" onclick="getViewDetails('data')">View</button>
                                    </s:if>
                                    <s:else>
                                    <button type="button" class="btn btn-success btn-sm"   id="view" data-toggle="modal" data-target="#myModal" onclick="getViewDetails('')">View</button>
                                    </s:else>
                                    </s:if>
                                </div>
                               
                                <div id="myModal" class="modal fade" role="dialog">
                                    <div class="modal-dialog modal-lg">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Bonus</h4>
                                            </div>
                                            <div class="modal-body">
                                            <div id="bonusView">
                                            <!-- Modal -->

                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <s:hidden name="slideScenario" id="slideScenario"/>
                                <s:hidden name="departmentId"/>
                                <s:hidden name="pageFor" id="pageFor"/>
                                <s:hidden name="contNo" id="contNo"/>
                                 <s:hidden name="layerno" id="layerno"/>
                                <s:hidden name="transaction" id="transaction"/>
                                <s:hidden name="transactionNo" id="transactionNo"/>
                                <s:hidden name="currencyName" id="currencyName"/>
                                <s:hidden name="gridShow" id="gridShow"/>
                                <s:hidden name="bonusExchangeRate" id="bonusExchangeRate"/>
								<s:hidden name="exchRate" id="exchRate"/>
								<s:hidden name="subProfit_center" id="subProfit_center"/>
								<s:hidden name="type" id="type"></s:hidden>
								<s:hidden name="currencyId" id="currencyId"></s:hidden>
								 <s:hidden name="premiumQuotaShare" id="premiumQuotaShare"/>
								  <s:hidden name="md_premium" id="md_premium"/>
								   <s:hidden name="adjustment_premium" id="adjustment_premium"/>
								   


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
function rateRange(val,id){
    if(parseFloat(val)>10){
        alert("Exchange Rate Should Be less than 10");
        document.getElementById('exchRatePrem'+ id ).value = '0.00';
    }

}

function getRagneValue(val){
    var value= document.getElementById('exchRatePrem'+ val).value;
    if(value>0){
    document.bonusPremiumpopup.action = "${pageContext.request.contextPath}/bonusviewPopupFaculPremium.do?mode=bonusRage";
    document.bonusPremiumpopup.submit();
    }
}
function getClaimpaidVal(id){
	var premium=document.getElementById("manualPremiumOC"+id).value;
	var unearned=0;
	var claim=document.getElementById("manualclaimPaidOC"+id).value;
	var out=document.getElementById("manualclaimOutStandingOC"+id).value;
	var val=0;
	if(""==premium){
	premium='0';
	}else{
	premium =premium.replace(new RegExp(',', 'g'),'')
	}if(""==unearned){
	unearned='0';
	}else{
	unearned =unearned.replace(new RegExp(',', 'g'),'')
	}if(""==claim){
	claim='0';
	}else{
	claim =claim.replace(new RegExp(',', 'g'),'')
	}if(""==out){
	out='0';
	}else{
	out =out.replace(new RegExp(',', 'g'),'')
	}
	var claimtotal=(parseFloat(claim)+ parseFloat(out));
	var preTotal = (parseFloat(premium)- parseFloat(unearned));
	if(parseFloat(claimtotal)>0&&parseFloat(preTotal)>0){
	 val = (claimtotal/preTotal)*100
	}
	document.getElementById("manualclaimRatioOC"+id).value=Comma(val.toFixed(2));
	}
function fnRecalculate(){
    	document.bonusPremiumpopup.action = "${pageContext.request.contextPath}/bonusviewPopupFaculPremium.do?mode=bonusRage&flag=recal";
    	document.bonusPremiumpopup.submit();
    }
function fnSubmitScale(){
    document.bonusPremiumpopup.action = "${pageContext.request.contextPath}/insertBonusFaculPremium.do?mode=insert";
    document.bonusPremiumpopup.submit();
}
    var tableToExcel = (function() {
        var uri = 'data:application/vnd.ms-excel;base64,'
            , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
            , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
            , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
        return function(table, name) {
            if (!table.nodeType) table = document.getElementById(table)
            var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
            window.location.href = uri + base64(format(template, ctx))
        }
    })()

    function getViewDetails(val){
        var contNo = document.bonusPremiumpopup.contNo.value;
        var transactionNo = document.bonusPremiumpopup.transactionNo.value;
        var departmentId = document.bonusPremiumpopup.departmentId.value;
        var currencyName = document.bonusPremiumpopup.currencyName.value;
        var transaction = document.bonusPremiumpopup.transaction.value;
         var layerno = document.bonusPremiumpopup.layerno.value;
        var bonusExchangeRate = document.bonusPremiumpopup.bonusExchangeRate.value;
        if('data'==val){
        var conPremiumOC = document.bonusPremiumpopup.conPremiumOC.value;
        var conClaimPaidOC = document.bonusPremiumpopup.conClaimPaidOC.value;
        var conClaimOutStandingOC = document.bonusPremiumpopup.conClaimOutStandingOC.value;
        var conClaimRatioOC = document.bonusPremiumpopup.conClaimRatioOC.value;
        var conbonusScaleCommOC = document.bonusPremiumpopup.conbonusOC.value;
        var conCommPaidOC = document.bonusPremiumpopup.conbonusPaidOC.value;
        var conbonusScaleAdjOC = document.bonusPremiumpopup.conbonusAdjOC.value;
        }
        else{
        var conPremiumOC = '0';
        var conClaimPaidOC ='0';
        var conClaimOutStandingOC = '0';
        var conClaimRatioOC = '0';
        var conbonusScaleCommOC = '0';
        var conCommPaidOC = '0';
        var conbonusScaleAdjOC = '0';
        }

        var URL='<%=request.getContextPath()%>/getBonusViewFaculPremium.do?dropDown=bonusView&mode=bonusView&contNo='+contNo+'&transactionNo='+transactionNo+'&layerno='+layerno+'&departmentId='+departmentId+'&currencyName='+currencyName+'&transaction='+transaction+'&bonusExchangeRate='+bonusExchangeRate+'&conPremiumOC='+conPremiumOC+'&conClaimPaidOC='+conClaimPaidOC+'&conClaimOutStandingOC='+conClaimOutStandingOC+'&conClaimRatioOC='+conClaimRatioOC+'&conbonusOC='+conbonusScaleCommOC+'&conbonusPaidOC='+conCommPaidOC+'&conbonusAdjOC='+conbonusScaleAdjOC;
        postRequest(URL,'bonusView');
    }
    
     function getPrint() {
		document.getElementById("close").style.display='none';
		document.getElementById("print").style.display='none';
		document.getElementById("view").style.display='none';
		var type='<s:property value="type"/>';
		if('Y'==type){
		document.getElementById("butnRemove").style.display='none';
		}
		window.print();
		document.getElementById("close").style.display='inline';
		document.getElementById("print").style.display='inline';
		document.getElementById("view").style.display='inline';
		if("Y"==type){
		document.getElementById("butnRemove").style.display='inline';
		}
	}
	<s:if test='"recal"==flag'>
	btnClick();
	</s:if>
	
	function btnClick()
    {
    $('td').css('background-color', 'White');
    }
</script>

</body>
</html>
