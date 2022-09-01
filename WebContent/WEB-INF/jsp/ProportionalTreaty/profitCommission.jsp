<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
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
                        <s:form id="profitPremiumpopup" name="profitPremiumpopup" theme="simple" action="" method="post" >
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Profit Commission Calculation and Adjustment
                                 <%--<s:if test='""!=transactionNo && "recal"!=flag &&"Calculation"!=flag'>
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
                                                <%-- <div class="textfield">
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
                                                            <s:textfield name="exchRatePrem[%{#stat.count-1}]" id= "exchRatePrem%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;height: 18px;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow8DigitDecValues(this);disableForm(this.form,false,'');getRagneValue('%{#stat.count-1}');"  onchange="rateRange(this.value,'%{#stat.count-1}');"  maxlength="30"  disabled='(#list.SHORT_NAME.equals(currencyName) || "Calculation".equals(flag))?true:false'/>
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
                                                                        <th width="33%" style="text-align: center; vertical-align: middle;"><b> ${SHORT_NAME}</b> (Manual)</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.premiumincludedet" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="portfolioPremium[%{#stat.count-1}]" id="portfolioPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{portfolioPremium[#stat.count-1]==null ?'0.00':portfolioPremium[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualportfolioPremium[%{#stat.count-1}]" id="manualportfolioPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"  maxlength="30" value="%{manualportfolioPremium==null || manualportfolioPremium[#stat.count-1]==null ?'0.00':manualportfolioPremium[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
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
                                                                            <s:textfield name="manualunearnpremiumOC[%{#stat.count-1}]" id="manualunearnpremiumOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{(manualunearnpremiumOC==null ||manualunearnpremiumOC[#stat.count-1]=='')?'0.00':manualunearnpremiumOC[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.accCost"/>
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="accCostBrokerage[%{#stat.count-1}]" id="accCostBrokerage%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30"  value="%{accCostBrokerage[#stat.count-1]==null ?'0.00':accCostBrokerage[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualaccCostBrokerage[%{#stat.count-1}]" id="manualaccCostBrokerage%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualaccCostBrokerage==null || manualaccCostBrokerage[#stat.count-1]==null ?'0.00':manualaccCostBrokerage[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimpaid" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="proClaimPaidOC[%{#stat.count-1}]" id="proClaimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30"  value="%{proClaimPaidOC[#stat.count-1]==null ?'0.00':proClaimPaidOC[#stat.count-1]}" />
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualproClaimPaidOC[%{#stat.count-1}]" id="manualproClaimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualproClaimPaidOC==null || manualproClaimPaidOC[#stat.count-1]==null ?'0.00':manualproClaimPaidOC[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimuotstanding" />
                                                                        </td>
                                                                        <td>
                                                                                <s:textfield name="proClaimOutStandingOC[%{#stat.count-1}]" id="proClaimOutStandingOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{proClaimOutStandingOC[#stat.count-1]==null?'0.00':proClaimOutStandingOC[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualproClaimOutStandingOC[%{#stat.count-1}]" id="manualproClaimOutStandingOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualproClaimOutStandingOC==null ||manualproClaimOutStandingOC[#stat.count-1]==null ?'0.00':manualproClaimOutStandingOC[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.manExp" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="managExp[%{#stat.count-1}]" id="managExp%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);this.value=Comma(this.value);"   maxlength="30" readonly="true" value="%{managExp[#stat.count-1]==null?'0.00':managExp[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualmanagExp[%{#stat.count-1}]" id="manualmanagExp%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualmanagExp==null ||manualmanagExp[#stat.count-1]==null ?'0.00':manualmanagExp[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.proloss" />
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="profitLoss[%{#stat.count-1}]"  id="profitLoss%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this); this.value=Comma(this.value);" onchange="disableForm(this.form,false,'');getTreatyAdjVal('%{#stat.count-1}');"  maxlength="30" disabled='%{(flag=="Calculation") ?true:false}' value="%{profitLoss==null?'0.00':profitLoss[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualprofitLoss[%{#stat.count-1}]" id="manualprofitLoss%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualprofitLoss==null ||manualprofitLoss[#stat.count-1]==null ?'0.00':manualprofitLoss[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false' />
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.otr.adj" />
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="otherAdj[%{#stat.count-1}]"  id="otherAdj%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" onchange="disableForm(this.form,false,'');getTreatyAdjVal('%{#stat.count-1}');" disabled='%{(flag=="Calculation" ) ?true:false}' value="%{otherAdj==null?'0.00':otherAdj[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualotherAdj[%{#stat.count-1}]" id="manualotherAdj%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualotherAdj==null || manualotherAdj[#stat.count-1]==null ?'0.00':manualotherAdj[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.treatyadj" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="treatyAdj[%{#stat.count-1}]" id="treatyAdj%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30"  readonly="true" value="%{treatyAdj[#stat.count-1]==null?'0.00':treatyAdj[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualtreatyAdj[%{#stat.count-1}]" id="manualtreatyAdj%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualtreatyAdj==null ||manualtreatyAdj[#stat.count-1]==null ?'0.00':manualtreatyAdj[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
																	<s:if test='"PR"==ProfitCommType'>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.prorat" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="profitRatio[%{#stat.count-1}]" id="profitRatio%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;"   maxlength="30"  readonly="true" value="%{profitRatio[#stat.count-1]==null?'0.00':profitRatio[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualprofitRatio[%{#stat.count-1}]" id="manualprofitRatio%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualprofitRatio==null ||manualprofitRatio[#stat.count-1]==null ?'0.00':manualprofitRatio[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    </s:if>
                                                                     <s:else>
                                                                    <s:hidden name="profitRatio[%{#stat.count-1}]" id="profitRatio%{#stat.count-1}" value="0"/>
                                                                    </s:else>
                                                                    <s:if test='"LR"==ProfitCommType'>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.lossratio" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="lossRatio[%{#stat.count-1}]" id="lossRatio%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;"    maxlength="30"  readonly="true" value="%{lossRatio[#stat.count-1]==null?'0.00':lossRatio[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manuallossRatio[%{#stat.count-1}]" id="manuallossRatio%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manuallossRatio==null ||manuallossRatio[#stat.count-1]==null ?'0.00':manuallossRatio[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    </s:if>
                                                                    <s:else>
                                                                    <s:hidden name="lossRatio[%{#stat.count-1}]" id="lossRatio%{#stat.count-1}" value="0"/>
                                                                    </s:else>
                                                                     <s:if test=' "Calculation"==flag '>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.profitcomm" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="profitCommission[%{#stat.count-1}]" id="profitCommission%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{profitCommission[#stat.count-1]==null?'0.00':profitCommission[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualprofitCommission[%{#stat.count-1}]" id="manualprofitCommission%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualprofitCommission==null || manualprofitCommission[#stat.count-1]==null ?'0.00':manualprofitCommission[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.supprocomm" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="superProfitComm[%{#stat.count-1}]" id="superProfitComm%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{superProfitComm[#stat.count-1]==null?'0.00':superProfitComm[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualsuperProfitComm[%{#stat.count-1}]" id="manualsuperProfitComm%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualsuperProfitComm==null ||manualsuperProfitComm[#stat.count-1]==null ?'0.00':manualsuperProfitComm[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.paydate" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="payedTillDate[%{#stat.count-1}]" id="payedTillDate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{payedTillDate[#stat.count-1]==null?'0.00':payedTillDate[#stat.count-1]}"/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualpayedTillDate[%{#stat.count-1}]" id="manualpayedTillDate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"   maxlength="30" value="%{manualpayedTillDate==null ||manualpayedTillDate[#stat.count-1]==null ?'0.00':manualpayedTillDate[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.profit.adj" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="profitCommAdj[%{#stat.count-1}]" id="profitCommAdj%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30"  value="%{profitCommAdj[#stat.count-1]==null?'0.00':profitCommAdj[#stat.count-1]}" disabled='%{(flag==Calculation ) ?true:false}'/>
                                                                        </td>
                                                                        <td style="background-color:#C6E2FF;">
                                                                            <s:textfield name="manualprofitCommAdj[%{#stat.count-1}]" id="manualprofitCommAdj%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);middleMinusRestriction(this);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualprofitCommAdj==null ||manualprofitCommAdj[#stat.count-1]==null ?'0.00':manualprofitCommAdj[#stat.count-1]}" disabled='"Calculation".equals(flag)?true:false'/>
                                                                        </td>

                                                                    </tr>
                                                                    </s:if>
                                                                    <s:else>
                                                                    <s:hidden name="profitCommission[%{#stat.count-1}]" id="profitCommission%{#stat.count-1}"/>
                                                                    <s:hidden name="superProfitComm[%{#stat.count-1}]" id="superProfitComm%{#stat.count-1}"/>
                                                                    <s:hidden name="payedTillDate[%{#stat.count-1}]" id="payedTillDate%{#stat.count-1}" />
                                                                    <s:hidden name="profitCommAdj[%{#stat.count-1}]" id="profitCommAdj%{#stat.count-1}"/>
                                                                     <s:hidden name="manualprofitCommission[%{#stat.count-1}]" id="manualprofitCommission%{#stat.count-1}"/>
                                                                    <s:hidden name="manualsuperProfitComm[%{#stat.count-1}]" id="manualsuperProfitComm%{#stat.count-1}"/>
                                                                    <s:hidden name="manualpayedTillDate[%{#stat.count-1}]" id="manualpayedTillDate%{#stat.count-1}" />
                                                                    <s:hidden name="manualprofitCommAdj[%{#stat.count-1}]" id="manualprofitCommAdj%{#stat.count-1}"/>
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

                                                                    <table class="footable" id="bonusTbl1" width="100%" >
                                                                        <thead>
                                                                        <tr>
                                                                            <th width="33%" style="text-align: center; vertical-align: middle;"> <b><s:text name="label.particulers" /></b></th>
                                                                            <th width="33%" style="text-align: center; vertical-align: middle;"><b><s:property value="currencyName"/></b></th>
                                                                            <th width="33%" style="text-align: center; vertical-align: middle;"><b><s:property value="currencyName"/> (Manual)</b></th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <tr>
                                                                        <td>
                                                                            <s:text name="label.premiumincludedet" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conPortfolioPremium" id="conPortfolioPremium" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{conPortfolioPremium==null ?'0.00':conPortfolioPremium}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconPortfolioPremium" id="manualconPortfolioPremium" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconPortfolioPremium==null ?'0.00':manualconPortfolioPremium}"/>
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
                                                                            <s:text name="label.accCost"/>
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conAccCostBrokerage" id="conAccCostBrokerage" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30"  value="%{conAccCostBrokerage==null ?'0.00':conAccCostBrokerage}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconAccCostBrokerage" id="manualconAccCostBrokerage" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconAccCostBrokerage==null ?'0.00':manualconAccCostBrokerage}"/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimpaid" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conProClaimPaidOC" id="conProClaimPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30"  value="%{conProClaimPaidOC==null ?'0.00':conProClaimPaidOC}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconProClaimPaidOC" id="manualconProClaimPaidOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconProClaimPaidOC==null ?'0.00':manualconProClaimPaidOC}"/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.claimuotstanding" />
                                                                        </td>
                                                                        <td>
                                                                                <s:textfield name="conProClaimOutStandingOC" id="conProClaimOutStandingOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{conProClaimOutStandingOC==null?'0.00':conProClaimOutStandingOC}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconProClaimOutStandingOC" id="manualconProClaimOutStandingOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconProClaimOutStandingOC==null ?'0.00':manualconProClaimOutStandingOC}"/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.manExp" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conManagExp" id="conManagExp" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{conManagExp==null?'0.00':conManagExp}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconManagExp" id="manualconManagExp" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconManagExp==null ?'0.00':manualconManagExp}"/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.proloss" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conProfitLoss"  id="conProfitLoss" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{conProfitLoss==null?'0.00':conProfitLoss}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconProfitLoss" id="manualconProfitLoss" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconProfitLoss==null ?'0.00':manualconProfitLoss}"/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.otr.adj" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conOtherAdj"  id="conOtherAdj" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{conOtherAdj==null?'0.00':conOtherAdj}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconOtherAdj" id="manualconOtherAdj" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconOtherAdj==null ?'0.00':manualconOtherAdj}"/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.treatyadj" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conTreatyAdj" id="conTreatyAdj" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);getRagneValue('%{#stat.count-1}');"   maxlength="30" readonly="true" value="%{conTreatyAdj==null?'0.00':conTreatyAdj}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconTreatyAdj" id="manualconTreatyAdj" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconTreatyAdj==null ?'0.00':manualconTreatyAdj}"/>
                                                                        </td>

                                                                    </tr>
																	<s:if test='"PR"==ProfitCommType'>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.prorat" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conProfitRatio" id="conProfitRatio" cssClass="inputBox" cssStyle="text-align: right;" onblur="getRagneValue('%{#stat.count-1}');"  readonly="true" maxlength="30"  value="%{conProfitRatio==null?'0.00':conProfitRatio}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconProfitRatio" id="manualconProfitRatio" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconProfitRatio==null ?'0.00':manualconProfitRatio}"/>
                                                                        </td>

                                                                    </tr>
                                                                    </s:if>
                                                                    <s:else>
                                                                    <s:hidden name="conProfitRatio" id="conProfitRatio" value="0"/>
                                                                    </s:else>
                                                                    <s:if test='"LR"==ProfitCommType'>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.lossratio" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conLossRatio" id="conLossRatio" cssClass="inputBox" cssStyle="text-align: right;" onblur="getRagneValue('%{#stat.count-1}');" readonly="true"  maxlength="30"  value="%{conLossRatio==null?'0.00':conLossRatio}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconLossRatio" id="manualconLossRatio" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconLossRatio==null ?'0.00':manualconLossRatio}"/>
                                                                        </td>

                                                                    </tr>
                                                                    </s:if>
                                                                     <s:else>
                                                                    <s:hidden name="conLossRatio" id="conLossRatio" value="0"/>
                                                                    </s:else>
                                                                     <s:if test=' "Calculation"==flag '>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.profitcomm" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conProfitCommission" id="conProfitCommission" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);" readonly="true"  maxlength="30"  value="%{conProfitCommission==null?'0.00':conProfitCommission}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconProfitCommission" id="manualconProfitCommission" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconProfitCommission==null ?'0.00':manualconProfitCommission}"/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.supprocomm" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conSuperProfitComm" id="conSuperProfitComm" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30"  value="%{conSuperProfitComm==null?'0.00':conSuperProfitComm}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconSuperProfitComm" id="manualconSuperProfitComm" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconSuperProfitComm==null ?'0.00':manualconSuperProfitComm}"/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.paydate" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conPayedTillDate" id="conPayedTillDate" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);getRagneValue('%{#stat.count-1}');" readonly="true"   maxlength="30"  value="%{conPayedTillDate==null?'0.00':conPayedTillDate}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconPayedTillDate" id="manualconPayedTillDate" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconPayedTillDate==null ?'0.00':manualconPayedTillDate}"/>
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <s:text name="label.profit.adj" />
                                                                        </td>
                                                                        <td>
                                                                            <s:textfield name="conProfitCommAdj" id="conProfitCommAdj" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);getRagneValue('%{#stat.count-1}');"   maxlength="30"  value="%{conProfitCommAdj==null?'0.00':conProfitCommAdj}"/>
                                                                        </td>
                                                                         <td >
                                                                            <s:textfield name="manualconProfitCommAdj" id="manualconProfitCommAdj" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"  readonly="true" maxlength="30" value="%{manualconProfitCommAdj==null ?'0.00':manualconProfitCommAdj}"/>
                                                                        </td>
                                                                    </tr>
                                                                    </s:if>
                                                                     <s:else>
                                                                    <s:hidden name="conProfitCommission" id="conProfitCommission"/>
                                                                    <s:hidden name="conSuperProfitComm" id="conSuperProfitComm"/>
                                                                    <s:hidden name="conPayedTillDate" id="conPayedTillDate"/>
                                                                    <s:hidden name="conProfitCommAdj" id="conProfitCommAdj"/>
                                                                     <s:hidden name="manualconProfitCommission" id="manualconProfitCommission"/>
                                                                    <s:hidden name="manualconSuperProfitComm" id="manualconSuperProfitComm"/>
                                                                    <s:hidden name="manualconPayedTillDate" id="manualconPayedTillDate"/>
                                                                    <s:hidden name="manualconProfitCommAdj" id="manualconProfitCommAdj"/>
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
                                     <s:if test=' "Calculation"==flag '>
                                    <input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="getPrint()"/>
                                    </s:if>
                                    <%-- <s:if test=' "Calculation"==flag && "Y".equals(gridShow)'><input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />
                                    </s:if> <s:if test='""==transactionNo || "recal"==flag ||"Calculation"==flag '>--%>
                                    <s:if test='"Y".equals(gridShow)'>
                                    <button type="button" class="btn btn-success btn-sm" id="view" data-toggle="modal" data-target="#myModal" onclick="getViewDetails('data','<s:property value="proposal_No"/>')">View</button>
                                    </s:if>
                                    <s:else>
                                    <button type="button" class="btn btn-success btn-sm" id="view" data-toggle="modal" data-target="#myModal" onclick="getViewDetails('','')">View</button>
                                    </s:else>
                                    <%--</s:if>--%>
                                    <s:if test=' "Calculation"!=flag '>
                                    <input type="button" id="calc" value="Calc. Profit Comm."  onclick="getCalculationValue();" class="btn btn-sm btn-info"/>
									</s:if>
									</s:if>	
                                </div>
                                <div id="myModal" class="modal fade" role="dialog">
                                    <div class="modal-dialog modal-lg">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Profit Commission</h4>
                                            </div>
                                            <div class="modal-body">
                                            <div id="profitView">
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
                                <s:hidden name="ProfitCommType" id="ProfitCommType"/>
                                <s:hidden name="managementExp" id="managementExp"/>
								<s:hidden name="stepUp" id="stepUp"/>
								<s:hidden name="profitComPer" id="profitComPer"/>
								<s:hidden name="superProfitCom" id="superProfitCom"/>
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

function getCalculationValue(){
var inputs=document.getElementsByTagName('input');
for(i=0;i<inputs.length;i++){
    inputs[i].disabled=false;
}  
	document.profitPremiumpopup.action = "${pageContext.request.contextPath}/calculationProfitCommProportionPremium.do?mode=profitRage&flag=Calculation";
    document.profitPremiumpopup.submit();
}
function getTreatyAdjVal(id){
var portfolioPremium = document.getElementById("portfolioPremium"+id).value;
var accCostBrokerage = document.getElementById("accCostBrokerage"+id).value;
var managExp = document.getElementById("managExp"+id).value;
var proClaimPaidOC = document.getElementById("proClaimPaidOC"+id).value;
var proClaimOutStandingOC = document.getElementById("proClaimOutStandingOC"+id).value;
var profitLoss = document.getElementById("profitLoss"+id).value;
var otherAdj = document.getElementById("otherAdj"+id).value;
portfolioPremium = portfolioPremium.replace(new RegExp(',', 'g'),'')
accCostBrokerage = accCostBrokerage.replace(new RegExp(',','g'),'')
managExp = managExp.replace(new RegExp(',','g'),'')
proClaimPaidOC = proClaimPaidOC.replace(new RegExp(',','g'),'');
proClaimOutStandingOC = proClaimOutStandingOC.replace(new RegExp(',','g'),'');
profitLoss = profitLoss.replace(new RegExp(',','g'),'');
otherAdj = otherAdj.replace(new RegExp(',','g'),'');
var total = parseFloat(portfolioPremium)-parseFloat(accCostBrokerage)-parseFloat(managExp)-parseFloat(proClaimPaidOC)-parseFloat(proClaimOutStandingOC)+parseFloat(profitLoss)+parseFloat(otherAdj);
document.getElementById("treatyAdj"+id).value = Comma(total.toFixed(2));
}

function getRagneValue(val){
 //   var value= document.getElementById('exchRatePrem'+ val).value;
 //   if(value>0){
 //   document.profitPremiumpopup.action = "${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode=profitRage";
 //   document.profitPremiumpopup.submit();
 //  }
}

<%--function fnSubmitScale(){
    document.profitPremiumpopup.action = "${pageContext.request.contextPath}/insertSlideProportionPremium.do?mode=profitInsert&flag=Calculation";
    document.profitPremiumpopup.submit();
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

    function getViewDetails(val,pro){
	     var conPortfolioPremium= '0';
		 var conAccCostBrokerage= '0';
		 var conProClaimPaidOC= '0';
		 var conProClaimOutStandingOC= '0';
		 var conManagExp= '0';
		 var conProfitLoss= '0';
		 var conOtherAdj= '0';
		 var conTreatyAdj= '0';
		 var conProfitRatio= '0';
		 var conLossRatio= '0';
		 var conProfitCommission= '0';
		 var conSuperProfitComm= '0';
		 var conPayedTillDate= '0';
		 var conProfitCommAdj= '0';
         var slideScenario = document.profitPremiumpopup.slideScenario.value;
         var contNo = document.profitPremiumpopup.contNo.value;
         var transactionNo = document.profitPremiumpopup.transactionNo.value;
         var departmentId = document.profitPremiumpopup.departmentId.value;
         var currencyName = document.profitPremiumpopup.currencyName.value;
         var transaction = document.profitPremiumpopup.transaction.value;
         var slideExchangeRate = document.profitPremiumpopup.slideExchangeRate.value;
         var ProfitCommType=document.getElementById("ProfitCommType").value;
         var statementDate = document.profitPremiumpopup.statementDate.value;
         var subProfit_center = document.profitPremiumpopup.subProfit_center.value;
         if('data'==val){
	        var conPortfolioPremium = document.profitPremiumpopup.conPortfolioPremium.value;
	        var conAccCostBrokerage = document.profitPremiumpopup.conAccCostBrokerage.value;
	        var conProClaimPaidOC = document.profitPremiumpopup.conProClaimPaidOC.value;
	        var conProClaimOutStandingOC = document.profitPremiumpopup.conProClaimOutStandingOC.value;
	        var conManagExp = document.profitPremiumpopup.conManagExp.value;
	        var conProfitLoss = document.profitPremiumpopup.conProfitLoss.value;
	        var conOtherAdj = document.profitPremiumpopup.conOtherAdj.value;
	        var conTreatyAdj = document.profitPremiumpopup.conTreatyAdj.value;
	        var conProfitRatio = document.profitPremiumpopup.conProfitRatio.value;
	        var conLossRatio = document.profitPremiumpopup.conLossRatio.value;
	        var conProfitCommission = document.profitPremiumpopup.conProfitCommission.value;
	        var conSuperProfitComm = document.profitPremiumpopup.conSuperProfitComm.value;
	        var conPayedTillDate = document.profitPremiumpopup.conPayedTillDate.value;
	        var conProfitCommAdj = document.profitPremiumpopup.conProfitCommAdj.value;
        }
       

        var URL='<%=request.getContextPath()%>/getSlideViewProportionPremium.do?dropDown=profitView&mode=profitView&statementDate='+statementDate+'&ProfitCommType='+ProfitCommType+'&Proposal_No='+pro+'&slideScenario='+slideScenario+'&contNo='+contNo+'&transactionNo='+transactionNo+'&departmentId='+departmentId+'&currencyName='+currencyName+'&transaction='+transaction+'&slideExchangeRate='+slideExchangeRate+'&conPortfolioPremium='+conPortfolioPremium+'&conAccCostBrokerage='+conAccCostBrokerage+'&conProClaimPaidOC='+conProClaimPaidOC+'&conProClaimOutStandingOC='+conProClaimOutStandingOC+'&conManagExp='+conManagExp+'&conProfitLoss='+conProfitLoss+'&conOtherAdj='+conOtherAdj+'&conTreatyAdj='+conTreatyAdj+'&conProfitRatio='+conProfitRatio+'&conLossRatio='+conLossRatio+'&conProfitCommission='+conProfitCommission+'&conSuperProfitComm='+conSuperProfitComm+'&conPayedTillDate='+conPayedTillDate+'&conProfitCommAdj='+conOtherAdj+'&subProfit_center='+subProfit_center;
        postRequest(URL,'profitView');
    }
    
    <%--function fnRecalculate(){
    	document.profitPremiumpopup.action = "${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode=profit&flag=recal";
    	document.profitPremiumpopup.submit();
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
	
	<s:if test='"Calculation"==flag'>
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
