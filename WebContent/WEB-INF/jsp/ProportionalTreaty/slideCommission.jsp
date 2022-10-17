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
    <link rel="stylesheet" type="text/css" h -->ref="css/select2-bootstrap.css"/>
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
 
    tr.urgent td{ background-color :red; }
    tr.warning td{ background-color : yellow; }
    tr.okay td{ background-color : green; }
 
</style>
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
                        <s:form id="slidePremiumpopup" name="slidePremiumpopup" theme="simple" action="" method="post" >
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Sliding Scale Commission Calculation and Adjustment
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
                                               <%--  <div class="textfield">
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
                                                            Exchange Rate :( 1 ${SHORT_NAME}=
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
                                                                            <s:textfield name="premiumOC[%{#stat.count-1}]" id="premiumOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{premiumOC[#stat.count-1]==null ?'0.00':premiumOC[#stat.count-1]}"/>
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
                                                                            <s:textfield name="claimPaidOC[%{#stat.count-1}]" id="claimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30"  value="%{claimPaidOC[#stat.count-1]==null ?'0.00':claimPaidOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualclaimPaidOC[%{#stat.count-1}]" id="manualclaimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);getClaimpaidVal('%{#stat.count-1}');"   maxlength="30"  value="%{(manualclaimPaidOC==null||manualclaimPaidOC[#stat.count-1]=='')?'0.00':manualclaimPaidOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
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
                                                                                <s:textfield name="manualclaimOutStandingOC[%{#stat.count-1}]" id="manualclaimOutStandingOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);getClaimpaidVal('%{#stat.count-1}');"  maxlength="30" value="%{(manualclaimOutStandingOC==null||manualclaimOutStandingOC[#stat.count-1]=='')?'0.00':manualclaimOutStandingOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimratio" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="claimRatioOC[%{#stat.count-1}]" id="claimRatioOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{(claimRatioOC[#stat.count-1]==null||claimRatioOC[#stat.count-1]=='')?'0.00':claimRatioOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualclaimRatioOC[%{#stat.count-1}]" id="manualclaimRatioOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30"  value="%{(manualclaimRatioOC==null||manualclaimRatioOC[#stat.count-1]=='')?'0.00':manualclaimRatioOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <s:if test='"recal"==flag'>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.slideScale" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="slideScaleCommOC[%{#stat.count-1}]"  id="slideScaleCommOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{slideScaleCommOC[#stat.count-1]==null?'0.00':slideScaleCommOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualslideScaleCommOC[%{#stat.count-1}]"  id="manualslideScaleCommOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);disableForm(this.form,false,'');fnRecalculate();return false;"    maxlength="30"  value="%{manualslideScaleCommOC==null?'0.00':manualslideScaleCommOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.commpaid" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="commPaidOCTillDate[%{#stat.count-1}]"  id="commPaidOCTillDate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{commPaidOCTillDate[#stat.count-1]==null?'0.00':commPaidOCTillDate[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualcommPaidOCTillDate[%{#stat.count-1}]"  id="manualcommPaidOCTillDate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);disableForm(this.form,false,'');fnRecalculate();return false;"   maxlength="30"  value="%{manualcommPaidOCTillDate==null?'0.00':manualcommPaidOCTillDate[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.slidescaleadj" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="slideScaleAdjOC[%{#stat.count-1}]" id="slideScaleAdjOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="disableForm(this.form,false,'');this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30"  value="%{slideScaleAdjOC[#stat.count-1]==null?'0.00':slideScaleAdjOC[#stat.count-1]}"  disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualslideScaleAdjOC[%{#stat.count-1}]" id="manualslideScaleAdjOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="disableForm(this.form,false,'');middleMinusRestriction(this);this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30"  value="%{manualslideScaleAdjOC==null?'0.00':manualslideScaleAdjOC[#stat.count-1]}" disabled='"recal".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    </s:if>
                                                                        <s:else>
                                                                        <s:hidden name="slideScaleCommOC[%{#stat.count-1}]"  id="slideScaleCommOC%{#stat.count-1}"/>
                                                                        <s:hidden name="manualslideScaleCommOC[%{#stat.count-1}]"  id="manualslideScaleCommOC%{#stat.count-1}"/>
                                                                        <s:hidden name="commPaidOCTillDate[%{#stat.count-1}]"  id="commPaidOCTillDate%{#stat.count-1}"/>
                                                                        <s:hidden name="manualcommPaidOCTillDate[%{#stat.count-1}]"  id="manualcommPaidOCTillDate%{#stat.count-1}"/>
                                                                        <s:hidden name="slideScaleAdjOC[%{#stat.count-1}]" id="slideScaleAdjOC%{#stat.count-1}"/>
                                                                        <s:hidden name="manualslideScaleAdjOC[%{#stat.count-1}]" id="manualslideScaleAdjOC%{#stat.count-1}"/>
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
                                                                                <s:textfield name="conPremiumOC" id="conPremiumOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{conPremiumOC==null?'0.00':conPremiumOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconPremiumOC" id="manualconPremiumOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);checkDecimals15(this);"   readonly="true" maxlength="30" value="%{manualconPremiumOC==null?'0.00':manualconPremiumOC}"/>
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
	                                                                            <s:textfield name="manualconunearnpremiumOC" id="manualconunearnpremiumOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconunearnpremiumOC==null ?'0.00':manualconunearnpremiumOC}"/>
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
                                                                                <s:textfield name="manualconClaimPaidOC" id="manualconClaimPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);checkDecimals15(this);"  readonly="true" maxlength="30" value="%{manualconClaimPaidOC==null?'0.00':manualconClaimPaidOC}"/>
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
                                                                                <s:textfield name="manualconClaimOutStandingOC"  id="manualconClaimOutStandingOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);checkDecimals15(this);"  readonly="true" maxlength="30" value="%{manualconClaimOutStandingOC==null?'0.00':manualconClaimOutStandingOC}"/>
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
                                                                                <s:textfield name="manualconClaimRatioOC" id="manualconClaimRatioOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{manualconClaimRatioOC==null?'0.00':manualconClaimRatioOC}"/>
                                                                            </td>

                                                                        </tr>
                                                                        <s:if test='"recal"==flag'>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.slideScale" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conSlideScaleCommOC" id="conSlideScaleCommOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conSlideScaleCommOC==null?'0.00':conSlideScaleCommOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconSlideScaleCommOC" id="manualconSlideScaleCommOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{manualconSlideScaleCommOC==null?'0.00':manualconSlideScaleCommOC}"/>
                                                                            </td>
                                                                            

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.commpaid" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conCommPaidOC" id="conCommPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conCommPaidOC==null?'0.00':conCommPaidOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconCommPaidOC" id="manualconCommPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{manualconCommPaidOC==null?'0.00':manualconCommPaidOC}"/>
                                                                            </td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <s:text name="label.slidescaleadj" />
                                                                            </td>
                                                                            <td>
                                                                                <s:textfield name="conSlideScaleAdjOC" id="conSlideScaleAdjOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{conSlideScaleAdjOC==null?'0.00':conSlideScaleAdjOC}"/>
                                                                            </td>
                                                                            <td >
                                                                                <s:textfield name="manualconSlideScaleAdjOC" id="manualconSlideScaleAdjOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);checkDecimals15(this);" readonly="true"  maxlength="30" value="%{manualconSlideScaleAdjOC==null?'0.00':manualconSlideScaleAdjOC}"/>
                                                                            </td>

                                                                        </tr>
                                                                        </s:if>
                                                                        <s:else>
                                                                        <s:hidden name="conSlideScaleCommOC" id="conSlideScaleCommOC"/>
                                                                        <s:hidden name="manualconSlideScaleCommOC" id="manualconSlideScaleCommOC"/>
                                                                        <s:hidden name="conCommPaidOC" id="conCommPaidOC"/>
                                                                        <s:hidden name="manualconCommPaidOC" id="manualconCommPaidOC"/>
                                                                        <s:hidden name="conSlideScaleAdjOC" id="conSlideScaleAdjOC"/>
                                                                        <s:hidden name="manualconSlideScaleAdjOC" id="manualconSlideScaleAdjOC"/>
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

                                <div class="boxcontent" align="center">
                                    	<input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
                                     <s:if test="slideCommission.size()>0">
                                     <s:if test='"recal"==flag'>
                                     	<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="getPrint()"/>
                                     </s:if>
                                     <s:if test='"recal"!=flag'>
                                     	<input type="button" id="print" value="Calculate" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');fnRecalculate();return false;"/>
                                     </s:if>
                                    <s:if test='"Y".equals(gridShow)'>
                                    	<button type="button" class="btn btn-success btn-sm" id="view" data-toggle="modal" data-target="#myModal" onclick="getViewDetails('data')">View</button>
                                    </s:if>
                                    <s:else>
                                    	<button type="button" class="btn btn-success btn-sm" id="view" data-toggle="modal" data-target="#myModal" onclick="getViewDetails('')">View</button>
                                    </s:else>
                                    </s:if>
                                </div>
                                <div id="myModal" class="modal fade" role="dialog">
                                    <div class="modal-dialog modal-lg">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Slide Scale Commission</h4>
                                            </div>
                                            <div class="modal-body">
                                            <div id="slideView">
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
var inputs=document.getElementsByTagName('input');
for(i=0;i<inputs.length;i++){
    inputs[i].disabled=false;
}  
    if(parseFloat(val)>10){
        alert("Exchange Rate Should Be less than 10");
        document.getElementById('exchRatePrem'+ id ).value = '0.00';
    }

}

function getRagneValue(val){
    var value= document.getElementById('exchRatePrem'+ val).value;
    if(value>0){
    document.slidePremiumpopup.action = "${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode=slideRage";
    document.slidePremiumpopup.submit();
    }
}

/*function fnSubmitScale(){
    document.slidePremiumpopup.action = "${pageContext.request.contextPath}/insertSlideProportionPremium.do?mode=slideSubmit";
    document.slidePremiumpopup.submit();
}*/
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
     var slideScenario = document.slidePremiumpopup.slideScenario.value;
     var contNo = document.slidePremiumpopup.contNo.value;
     var transactionNo = document.slidePremiumpopup.transactionNo.value;
     var departmentId = document.slidePremiumpopup.departmentId.value;
     var currencyName = document.slidePremiumpopup.currencyName.value;
     var transaction = document.slidePremiumpopup.transaction.value;
     var slideExchangeRate = document.slidePremiumpopup.slideExchangeRate.value;
     var statementDate= document.slidePremiumpopup.statementDate.value;
     var subProfit_center = document.slidePremiumpopup.subProfit_center.value;
     if('data'==val){
     var conPremiumOC = document.slidePremiumpopup.conPremiumOC.value;
     var conClaimPaidOC = document.slidePremiumpopup.conClaimPaidOC.value;
     var conClaimOutStandingOC = document.slidePremiumpopup.conClaimOutStandingOC.value;
     var conClaimRatioOC = document.slidePremiumpopup.conClaimRatioOC.value;
     var conSlideScaleCommOC = document.slidePremiumpopup.conSlideScaleCommOC.value;
     var conCommPaidOC = document.slidePremiumpopup.conCommPaidOC.value;
     var conSlideScaleAdjOC = document.slidePremiumpopup.conSlideScaleAdjOC.value;
     }
     else{
     var conPremiumOC = '0';
     var conClaimPaidOC ='0';
     var conClaimOutStandingOC = '0';
     var conClaimRatioOC = '0';
     var conSlideScaleCommOC = '0';
     var conCommPaidOC = '0';
     var conSlideScaleAdjOC = '0';
     }

     var URL='<%=request.getContextPath()%>/getSlideViewProportionPremium.do?dropDown=slideView&mode=slideView&statementDate='+statementDate+'&slideScenario='+slideScenario+'&contNo='+contNo+'&transactionNo='+transactionNo+'&departmentId='+departmentId+'&currencyName='+currencyName+'&transaction='+transaction+'&slideExchangeRate='+slideExchangeRate+'&conPremiumOC='+conPremiumOC+'&conClaimPaidOC='+conClaimPaidOC+'&conClaimOutStandingOC='+conClaimOutStandingOC+'&conClaimRatioOC='+conClaimRatioOC+'&conSlideScaleCommOC='+conSlideScaleCommOC+'&conCommPaidOC='+conCommPaidOC+'&conSlideScaleAdjOC='+conSlideScaleAdjOC+'&subProfit_center='+subProfit_center;
     postRequest(URL,'slideView');
 }
  function fnRecalculate(){
    	document.slidePremiumpopup.action = "${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode=slideRage&flag=recal";
    	document.slidePremiumpopup.submit();
    }
    
    function getPrint() {
		document.getElementById("close").style.display='none';
		document.getElementById("print").style.display='none';
		document.getElementById("view").style.display='none';
		window.print();
		document.getElementById("close").style.display='inline';
		document.getElementById("print").style.display='inline';
		document.getElementById("view").style.display='inline';
	}
	
	function getClaimpaidVal(id){
	var premium=document.getElementById("manualPremiumOC"+id).value;
	var unearned=document.getElementById("manualunearnpremiumOC"+id).value;
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
function allow2DigitDecValues(obj)
{
	var validno="1234567890.-,";
	var temp=obj.value.replace(new RegExp(',', 'g'),'');
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(index==-1)
	{
	if(val.length>1)
		temp=temp.substring(0,index+19);
	}else
	{
		if(val.length>2)
			temp=temp.substring(0,index+3);		
	}
	finalValue=temp;
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			finalValue=finalValue.replace(temp.charAt(i),"");
		}
	}
	var val =parseFloat(finalValue).toFixed(2);
	obj.value=Comma(val);
return false;
}
function allow8DigitDecValues(obj)
{
	
	var validno="1234567890.-,";
	var temp=obj.value.replace(new RegExp(',', 'g'),'');;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(index==-1)
	{
	if(val.length>1)
		temp=temp.substring(0,index+19);
	}else
	{
		if(val.length>8)
			temp=temp.substring(0,index+9);		
	}
	finalValue=temp;
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			finalValue=finalValue.replace(temp.charAt(i),"");
		}
		
	}
	var val =parseFloat(finalValue).toFixed(8);
	obj.value=Comma(val);
return false;
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
