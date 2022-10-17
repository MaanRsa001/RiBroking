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
    <script src="tableToExcel.js"></script>
    <style>
        .button {background-color: #4CAF50; /* Green */}

        .button2 {background-color: #008CBA;} /* Blue */
        .button3 {background-color: #f44336;} /* Red */
        .button4 {background-color: #e7e7e7; color: black;} /* Gray */
        .button5 {background-color: #555555;} /* Black */
    </style>
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">
                    <div class="boxcontent">
                        <s:form id="lossPremiumpopup" name="lossPremiumpopup" theme="simple" action="" method="post" >
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Loss Participation / Corridor Calculation and Adjustment
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
                                                   <%--<div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.contractno" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="contNo"/>
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
                                                        <s:property value="subProfit_center"/>
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
                                                        <s:text name="label.treatyName" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="treatyName_type"/>
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
                                                </div>--%>
                                                <br class="clear"/>
                                                 
                                            </div>
                                        </div>
                                        <br class="clear"/>
                                    </div>
                                </div>
                                <div class="boxcontent">
                                    <s:if test="slideCommission.size()>0">
                                    <s:iterator value="slideCommission" var="list" status="stat">
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
                                                            <s:textfield name="exchRatePrem[%{#stat.count-1}]" id= "exchRatePrem%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;height: 18px;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow8DigitDecValues(this);disableForm(this.form,false,'');getRagneValue('%{#stat.count-1}');"   maxlength="30" onchange="rateRange(this.value,'%{#stat.count-1}');" disabled='(#list.SHORT_NAME.equals(currencyName)||"recal".equals(flag))?true:false'/>
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

                                                                <table class="table table-bordered" id="bonusTbl" width="100%" >
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
                                                                            <s:textfield name="lossPremiumOC[%{#stat.count-1}]" id="lossPremiumOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{lossPremiumOC[#stat.count-1]==null ?'0.00':lossPremiumOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualPremiumOC[%{#stat.count-1}]" id="manualPremiumOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);getClaimpaidVal('%{#stat.count-1}');"   maxlength="30" value="%{(manualPremiumOC==null ||manualPremiumOC[#stat.count-1]=='')?'0.00':manualPremiumOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                   <tr>
                                                                        <td>
                                                                            <s:text name="label.unearnedpremiumdet" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="unearnpremiumOC[%{#stat.count-1}]" id="unearnpremiumOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{unearnpremiumOC[#stat.count-1]==null ?'0.00':unearnpremiumOC[#stat.count-1]}"/>
                                                                        </td>
																		<td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualunearnpremiumOC[%{#stat.count-1}]" id="manualunearnpremiumOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);getClaimpaidVal('%{#stat.count-1}');"   maxlength="30" value="%{(manualunearnpremiumOC==null ||manualunearnpremiumOC[#stat.count-1]=='')?'0.00':manualunearnpremiumOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimpaid" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="lossClaimPaidOC[%{#stat.count-1}]" id="lossClaimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30"  value="%{lossClaimPaidOC[#stat.count-1]==null ?'0.00':lossClaimPaidOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manuallossClaimPaidOC[%{#stat.count-1}]" id="manuallossClaimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);getClaimpaidVal('%{#stat.count-1}');"   maxlength="30" value="%{(manuallossClaimPaidOC==null ||manuallossClaimPaidOC[#stat.count-1]=='')?'0.00':manuallossClaimPaidOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimuotstanding" />
                                                                        </td>
                                                                        <td>
                                                                                <s:textfield name="lossClaimOutStandingOC[%{#stat.count-1}]" id="lossClaimOutStandingOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{lossClaimOutStandingOC[#stat.count-1]==null?'0.00':lossClaimOutStandingOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manuallossClaimOutStandingOC[%{#stat.count-1}]" id="manuallossClaimOutStandingOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);getClaimpaidVal('%{#stat.count-1}');"   maxlength="30" value="%{(manuallossClaimOutStandingOC==null ||manuallossClaimOutStandingOC[#stat.count-1]=='')?'0.00':manuallossClaimOutStandingOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimratio" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="lossClaimRatioOC[%{#stat.count-1}]" id="lossClaimRatioOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{lossClaimRatioOC[#stat.count-1]==null?'0.00':lossClaimRatioOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manuallossClaimRatioOC[%{#stat.count-1}]" id="manuallossClaimRatioOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{(manuallossClaimRatioOC==null ||manuallossClaimRatioOC[#stat.count-1]=='')?'0.00':manuallossClaimRatioOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                     <tr>
                                                                        <td>
                                                                            <s:text name="label.claimpaidratio" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="claimPaidRatioOC[%{#stat.count-1}]" id="claimPaidRatioOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{claimPaidRatioOC[#stat.count-1]==null?'0.00':claimPaidRatioOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manuallossclaimPaidRatioOC[%{#stat.count-1}]" id="manuallossclaimPaidRatioOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{(manuallossclaimPaidRatioOC==null ||manuallossclaimPaidRatioOC[#stat.count-1]=='')?'0.00':manuallossclaimPaidRatioOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <s:if test='"recal"==flag'>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.lossPor" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="lossPartOC[%{#stat.count-1}]"  id="lossPartOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{lossPartOC[#stat.count-1]==null?'0.00':lossPartOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manuallossPartOC[%{#stat.count-1}]" id="manuallossPartOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{(manuallossPartOC==null ||manuallossPartOC[#stat.count-1]=='')?'0.00':manuallossPartOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.lossPartRef" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="lossPartRefTillDate[%{#stat.count-1}]"  id="lossPartRefTillDate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{lossPartRefTillDate[#stat.count-1]==null?'0.00':lossPartRefTillDate[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manuallossPartRefTillDate[%{#stat.count-1}]" id="manuallossPartRefTillDate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{(manuallossPartRefTillDate==null ||manuallossPartRefTillDate[#stat.count-1]=='')?'0.00':manuallossPartRefTillDate[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.lossPartRefadj" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="lossPartRefAdjOC[%{#stat.count-1}]" id="lossPartRefAdjOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);disableForm(this.form,false,'');getRagneValue('%{#stat.count-1}');"   maxlength="30"  value="%{lossPartRefAdjOC[#stat.count-1]==null?'0.00':lossPartRefAdjOC[#stat.count-1]}"/>
                                                                             <%--value='<s:property value="preCurrencylist[%{#stat.count-1}]"/>'--%>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manuallossPartRefAdjOC[%{#stat.count-1}]" id="manuallossPartRefAdjOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{(manuallossPartRefAdjOC==null ||manuallossPartRefAdjOC[#stat.count-1]=='')?'0.00':manuallossPartRefAdjOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    </s:if>
                                                                     <s:else>
                                                                        <s:hidden name="lossPartOC[%{#stat.count-1}]"  id="lossPartOC%{#stat.count-1}"/>
                                                                        <s:hidden name="manuallossPartOC[%{#stat.count-1}]"  id="manuallossPartOC%{#stat.count-1}"/>
                                                                        <s:hidden name="lossPartRefTillDate[%{#stat.count-1}]"  id="lossPartRefTillDate%{#stat.count-1}"/>
                                                                        <s:hidden name="manuallossPartRefTillDate[%{#stat.count-1}]"  id="manuallossPartRefTillDate%{#stat.count-1}"/>
                                                                        <s:hidden name="lossPartRefAdjOC[%{#stat.count-1}]" id="lossPartRefAdjOC%{#stat.count-1}"/>
                                                                        <s:hidden name="manuallossPartRefAdjOC[%{#stat.count-1}]" id="manuallossPartRefAdjOC%{#stat.count-1}"/>
                                                                        </s:else>
																	</tbody>
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

                                                                    <table class="table table-bordered" id="bonusTbl1" width="100%" >
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
                                                                                <s:textfield name="conlossPremiumOC" id="conlossPremiumOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{conlossPremiumOC==null?'0.00':conlossPremiumOC}"/>
                                                                            </td>
																			<td >
                                                                                <s:textfield name="manualconPremiumOC" id="manualconPremiumOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconPremiumOC==null?'0.00':manualconPremiumOC}"/>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
	                                                                        <td>
	                                                                            <s:text name="label.unearnedpremiumdet" />
	                                                                        </td>
	                                                                        <td>
	                                                                            <s:textfield name="conunearnpremiumOC" id="conunearnpremiumOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{conunearnpremiumOC==null ?'0.00':conunearnpremiumOC}"/>
	                                                                        </td>
																			<td >
	                                                                            <s:textfield name="manualconunearnpremiumOC" id="manualconunearnpremiumOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconunearnpremiumOC==null ?'0.00':manualconunearnpremiumOC}"/>
	                                                                        </td>
	
	                                                                    </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.claimpaid" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conlossClaimPaidOC" id="conlossClaimPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"  readonly="true" maxlength="30" value="%{conlossClaimPaidOC==null?'0.00':conlossClaimPaidOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconlossClaimPaidOC" id="manualconlossClaimPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconlossClaimPaidOC==null?'0.00':manualconlossClaimPaidOC}"/>
                                                                            </td>
		
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.claimuotstanding" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conlossClaimOutStandingOC"  id="conlossClaimOutStandingOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"  readonly="true" maxlength="30" value="%{conlossClaimOutStandingOC==null?'0.00':conlossClaimOutStandingOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconlossClaimOutStandingOC" id="manualconlossClaimOutStandingOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconlossClaimOutStandingOC==null?'0.00':manualconlossClaimOutStandingOC}"/>
                                                                            </td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.claimratio" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conlossClaimRatioOC" id="conlossClaimRatioOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conlossClaimRatioOC==null?'0.00':conlossClaimRatioOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconlossClaimRatioOC" id="manualconlossClaimRatioOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconlossClaimRatioOC==null?'0.00':manualconlossClaimRatioOC}"/>
                                                                            </td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.claimpaidratio" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conClaimPaidRatioOC" id="conClaimPaidRatioOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conClaimPaidRatioOC==null?'0.00':conClaimPaidRatioOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconClaimPaidRatioOC" id="manualconClaimPaidRatioOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconClaimPaidRatioOC==null?'0.00':manualconClaimPaidRatioOC}"/>
                                                                            </td>

                                                                        </tr>
                                                                        <s:if test='"recal"==flag'>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.lossPor" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conlossOC" id="conlossOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conlossOC==null?'0.00':conlossOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconlossOC" id="manualconlossOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconlossOC==null?'0.00':manualconlossOC}"/>
                                                                            </td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.lossPartRef" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conlossTillOC" id="conlossTillOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conlossTillOC==null?'0.00':conlossTillOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconlossTillOC" id="manualconlossTillOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconlossTillOC==null?'0.00':manualconlossTillOC}"/>
                                                                            </td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.lossPartRefadj" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conlossAdjOC" id="conlossAdjOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conlossAdjOC==null?'0.00':conlossAdjOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconlossAdjOC" id="manualconlossAdjOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconlossAdjOC==null?'0.00':manualconlossAdjOC}"/>
                                                                            </td>

                                                                        </tr>
                                                                        </s:if>
                                                                         <s:else>
                                                                        <s:hidden name="conlossOC" id="conlossOC"/>
                                                                        <s:hidden name="manualconlossOC" id="manualconlossOC"/>
                                                                        <s:hidden name="conlossTillOC" id="conlossTillOC"/>
                                                                        <s:hidden name="manualconlossTillOC" id="manualconlossTillOC"/>
                                                                        <s:hidden name="conlossAdjOC" id="conlossAdjOC"/>
                                                                        <s:hidden name="manualconlossAdjOC" id="manualconlossAdjOC"/>
                                                                        </s:else>
																		</tbody>
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
                                <s:if test='"recal"==flag'>
								<div class="boxcontent">
                                    <div class="panel panel-primary">
                                        <div class="panel-body">
                                            <table class="table table-bordered" id="bonusTbl1" width="100%" >
                                              <tbody>
                                              <tr>
                                                  <td>
                                                      <s:text name="Note: Adjustment to Outstanding Losses " /><s:property value="currencyName"/>
                                                  </td>
                                                  <td><br>
                                                      <s:textfield name="adjToOutLoss" id="adjToOutLoss" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{adjToOutLoss==null?'0.00':adjToOutLoss}"/>
                                                  <br></td>
                                                  <td><br>
                                                      <s:textfield name="manualadjoutOC" id="manualadjoutOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualadjoutOC==null?'0.00':manualadjoutOC}"/>
                                                  <br></td>

                                              </tr>
                                              </tbody>
                                           </table>
                                        </div>
                                        <br class="clear"/>
                                    </div>
                                </div>
                               </s:if>
                                <div class="boxcontent" align="center">
                                    <input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
                                     <s:if test="slideCommission.size()>0">
	                                   <%-- <input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />--%> 
	                                   <s:if test='"recal"==flag'>
	                                    <input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="getPrint()"/>
	                                    </s:if>
	                                    <s:if test='"recal"!=flag'>
                                     	<input type="button" id="print" value="Calculate" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');fnRecalculate();return false;"/>
                                     </s:if>
	                                    <%--<s:if test='""==transactionNo || "recal"==flag'>--%>
		                                    <s:if test='"Y".equals(gridShow)'>
		                                   	 	<button type="button" id="view" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal" onclick="getViewDetails('data')">View</button>
		                                    </s:if>
		                                    <s:else>
		                                    	<button type="button" id="view" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal" onclick="getViewDetails('')">View</button>
		                                    </s:else>
	                                    <%--</s:if>--%>
                                    </s:if>
                                </div>
                                <div id="myModal" class="modal fade" role="dialog">
                                    <div class="modal-dialog modal-lg">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Loss Participation</h4>
                                            </div>
                                            <div class="modal-body">
                                            <div id="lossView">
                                            <!-- Modal -->

                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <s:hidden name="preCurrencylist" id="preCurrencylist" />
                                <s:hidden name="slideScenario" id="slideScenario"/>
                                <s:hidden name="departmentId"/>
                                <s:hidden name="pageFor" id="pageFor"/>
                                <s:hidden name="contNo" id="contNo"/>
                                <s:hidden name="transaction" id="transaction"/>
                                <s:hidden name="transactionNo" id="transactionNo"/>
                                <s:hidden name="currencyName" id="currencyName"/>
                                <s:hidden name="gridShow" id="gridShow"/>
                                 <s:hidden name="proposal_No" id="proposal_No"/>
                                <s:hidden name="slideExchangeRate" id="slideExchangeRate"/>
								<s:hidden name="statementDate" id="statementDate"/>
								<s:hidden name="exchRate" id="exchRate"/>
								<s:hidden name="subProfit_center" id="subProfit_center"/>
								<s:hidden name="currencyId" id="currencyId"/>
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
    document.lossPremiumpopup.action = "${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode=lossRage";
    document.lossPremiumpopup.submit();
    }
}
function fnRecalculate(){
    	document.lossPremiumpopup.action = "${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode=lossRage&flag=recal";
    	document.lossPremiumpopup.submit();
    }
    function getClaimpaidVal(id){
	var premium=document.getElementById("manualPremiumOC"+id).value;
	var unearned=document.getElementById("manualunearnpremiumOC"+id).value;
	var claim=document.getElementById("manuallossClaimPaidOC"+id).value;
	var out=document.getElementById("manuallossClaimOutStandingOC"+id).value;
	var val=0;
	var val1=0;
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
	var claimtotal1=parseFloat(claim);
	var preTotal = (parseFloat(premium)- parseFloat(unearned));
	if(parseFloat(claimtotal)>0&&parseFloat(preTotal)>0){
	 val = (claimtotal/preTotal)*100
	}
	if(parseFloat(claimtotal1)>0&&parseFloat(preTotal)>0){
	 val1 = (claimtotal1/preTotal)*100
	}
	document.getElementById("manuallossClaimRatioOC"+id).value=Comma(val.toFixed(2));
	document.getElementById("manuallossclaimPaidRatioOC"+id).value=Comma(val1.toFixed(2));
	}
<%--function fnSubmitScale(){
    document.lossPremiumpopup.action = "${pageContext.request.contextPath}/insertSlideProportionPremium.do?mode=lossSubmit";
    document.lossPremiumpopup.submit();
}--%>
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
        var slideScenario = document.lossPremiumpopup.slideScenario.value;
        var contNo = document.lossPremiumpopup.contNo.value;
        var transactionNo = document.lossPremiumpopup.transactionNo.value;
        var departmentId = document.lossPremiumpopup.departmentId.value;
        var currencyName = document.lossPremiumpopup.currencyName.value;
        var transaction = document.lossPremiumpopup.transaction.value;
        var slideExchangeRate = document.lossPremiumpopup.slideExchangeRate.value;
        var statementDate= document.lossPremiumpopup.statementDate.value;
        var subProfit_center = document.lossPremiumpopup.subProfit_center.value;
        if('data'==val){
        var conlossPremiumOC = document.lossPremiumpopup.conlossPremiumOC.value;
        var conlossClaimPaidOC = document.lossPremiumpopup.conlossClaimPaidOC.value;
        var conlossClaimOutStandingOC = document.lossPremiumpopup.conlossClaimOutStandingOC.value;
        var conlossClaimRatioOC = document.lossPremiumpopup.conlossClaimRatioOC.value;
        var conlossOC = document.lossPremiumpopup.conlossOC.value;
        var conlossTillOC = document.lossPremiumpopup.conlossTillOC.value;
        var conlossAdjOC = document.lossPremiumpopup.conlossAdjOC.value;
        var conClaimPaidRatioOC = document.lossPremiumpopup.conClaimPaidRatioOC.value;
        
        }
        else{
        var conPremiumOC = '0';
        var conClaimPaidOC ='0';
        var conClaimOutStandingOC = '0';
        var conClaimRatioOC = '0';
        var conlossOC = '0';
        var conlossTillOC = '0';
        var conlossAdjOC = '0';
         var conClaimPaidRatioOC='0';
        }

        var URL='<%=request.getContextPath()%>/getSlideViewProportionPremium.do?dropDown=lossView&mode=lossView&statementDate='+statementDate+'&slideScenario='+slideScenario+'&contNo='+contNo+'&transactionNo='+transactionNo+'&departmentId='+departmentId+'&currencyName='+currencyName+'&transaction='+transaction+'&slideExchangeRate='+slideExchangeRate+'&conlossPremiumOC='+conlossPremiumOC+'&conlossClaimPaidOC='+conlossClaimPaidOC+'&conlossClaimOutStandingOC='+conlossClaimOutStandingOC+'&conlossClaimRatioOC='+conlossClaimRatioOC+'&conlossOC='+conlossOC+'&conlossTillOC='+conlossTillOC+'&conlossAdjOC='+conlossAdjOC+'&conClaimPaidRatioOC='+conClaimPaidRatioOC+'&subProfit_center='+subProfit_center;
        postRequest(URL,'lossView');
    }
   <%--  function fnRecalculate(){
    	document.lossPremiumpopup.action = "${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode=loss&flag=recal";
    	document.lossPremiumpopup.submit();
    }--%>
    function getPrint() {
		document.getElementById("close").style.display='none';
		document.getElementById("print").style.display='none';
		document.getElementById("view").style.display='none';
		window.print();
		document.getElementById("close").style.display='inline';
		document.getElementById("print").style.display='inline';
		document.getElementById("view").style.display='inline';
	}
	<s:if test='"recal"==flag'>
	btnClick();
	</s:if>
	
	function btnClick()
    {
          $('td').css('background-color', 'White');
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
