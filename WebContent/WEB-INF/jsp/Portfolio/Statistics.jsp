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
    <script src="tableToExcel.js"></script>
    <script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
	
    <style>
        .button {background-color: #4CAF50; /* Green */}

        .button2 {background-color: #008CBA;} /* Blue */
        .button3 {background-color: #f44336;} /* Red */
        .button4 {background-color: #e7e7e7; color: black;} /* Gray */
        .button5 {background-color: #555555;} /* Black */
    </style>
    <script type="text/javascript">
	 $(function() {
	    $( "#inceptionDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#expiryDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#accountDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#periodFrom" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#periodTo" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });		

	  </script>
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
 	min-width: 100px;
 	max-width: 150px;
 	width: 120px;
 	white-space: normal;
 	height: 50px;
 }
 </style>
</head>
<body>
	<%-- <div id="loading" style="width:100%;" >
	 <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif" />
</div>--%>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">
                    <div class="boxcontent">
                        <s:form id="statistics" name="statistics" theme="simple" action="" method="post" >
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                               <font face="Arial" ><b>Stats & Calc</b></font>
                                <s:if test='"popup"!=mode'>
                                 <span class="pullRight">
                                 <a  class="btn btn-sm btn-success" onclick="getViewContractDetails('<s:property value="%{#session.mfrid}" />','<s:property value="proposal_No" />','<s:property value="amendId" />');" >View Contract Details</a>
							     <s:hidden name="amendId"/>
								</span>
								</s:if>
                            </div>
						<s:if test='"renewal"!=mode'>
                            <div class="panel-body">
                                <div class="boxcontent">
                                    <div class="panel panel-primary">
                                        <div class="panel-body">
                                            <div class="boxcontent">
                                             <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="Proposal No" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="proposal_No"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.contractno" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="contractNo"/>
                                                    </div>
                                                </div>
                                                <s:if test='"3"==productId ||"5"==productId'>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        Layer No
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="layerNo"/>
                                                    </div>
                                                </div>
                                                </s:if>
                                               
                                                <%-- <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="Ceding Company" />
                                                    </div>
                                                    <div class="tbox">
                                                        ALL
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="Broker" />
                                                    </div>
                                                    <div class="tbox">
                                                        ALL
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="Underwriting Year" />
                                                    </div>
                                                    <div class="tbox">
                                                        ALL
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        Class
                                                    </div>
                                                    <div class="tbox">
                                                        ALL
                                                    </div>
                                                </div>
                                                 <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="Transaction Date From" />
                                                    </div>
                                                    <div class="tbox">
                                                        01/01/2001
                                                    </div>
                                                </div>
                                                 <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="Transaction Date To" />
                                                    </div>
                                                    <div class="tbox">
                                                        31/12/2100
                                                    </div>
                                                </div>--%>
                                            </div>
                                        </div>
                                        <br class="clear"/>
                                    </div>
                                </div>
                                <div class="panel panel-primary">
		                            <div class="panel-heading">
		                           <font face="Arial" ><b> UW Statistics</b></font>
		                            </div>
		                            <div class="panel-body" >
									<div class="boxcontent">
									<div class="textfield">
											<div class="text">
												Underwriting Year From
											</div>
											<div class="tbox">
												<s:select name="uwFrom" id="uwFrom" list="uwYearList" listKey="YEAR" listValue="YEAR" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  />												
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												Underwriting Year To
											</div>
											<div class="tbox">
												<s:select name="uwTo" id="uwTo" list="uwYearList" listKey="YEAR" listValue="YEAR" headerKey="" cssClass="inputBoxS" headerValue="---Select---" />												
											</div>
										</div>
		                            	<div class="textfield">
											<div class="text">
												Financial Period From
											</div>
											<div class="tbox">
												<div class="inputAppend">
												
												<s:textfield name="periodFrom" id="periodFrom"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  />
												</div>
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												Financial Period To
											</div>
											<div class="tbox">
												<div class="inputAppend">
												
												<s:textfield name="periodTo" id="periodTo"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  />
												</div>
											</div>
										</div>
									<%-- onchange="getAjax(this.value,'reclass');" 	<div class="textfield">
											<div class="text">
												Class
											</div>
											<div class="tbox" id="reclass">
												<s:select list="renewalDepartIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="redepartId" id="redepartId" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />												
											</div>
										</div> --%>
										</div>
										</div>
										 <div class="boxcontent">
		                            <div class="panel-body" align="center">
		                            <a  class="btn btn-sm btn-success" onclick="funGenerate();" >Generate</a>
		                           
		                            </div>
		                            </div>
		                        </div>
                                <div class="panel panel-primary">
		                            <div class="panel-heading">
		                           <font face="Arial" ><b> Settlement Statistics</b></font>
		                            </div>
		
		                            <div class="panel-body" align="center">
		                            <a  class="btn btn-sm btn-info" onclick="funDownload();" >Click here to Download</a>
		                            </div>
		                        </div>
		                        <s:if test='"2"==productId'>
		                         <div class="panel panel-primary">
		                            <div class="panel-heading">
		                            <h4 class = "panel-title">
							            <a data-toggle = "collapse" data-parent = "#accordion" aria-expanded="true" href = "#collapseOne">
							               <i class="glyphicon glyphicon-plus" id="detailsPlus" style="cursor: pointer;" onclick="detailsClick('slide')">&nbsp;<font face="Arial" ><b>Sliding Scale Commission</b></font></i>
											<i class="glyphicon glyphicon-minus" id="detailsMinus" style="cursor: pointer; display: none;" onclick="detailsClick(false)">&nbsp;<font face="Arial" ><b>Sliding Scale Commission</b></font></i> 
							            </a>
							         </h4>
		                               
		                            </div>
		 							<div id="detailsData" style="display: none;" class = "panel-collapse collapse in">
		                            <div class="panel-body" >
		                            <s:if test='""==slideScenario || null==slideScenario'>
		                            	<div class="boxcontent">
											Sliding Scale Commission is not applicable for any class of this contract
											<s:hidden name="slideAsOnDate" id="inceptionDate"/>
										</div>
		                            </s:if>
										<s:else>
		                            <div class="boxcontent">
		                            	<div class="textfield">
											<div class="text">
												Calculate As On Date
											</div>
											<div class="tbox">
												<div class="inputAppend">
												
												<s:textfield name="slideAsOnDate" id="inceptionDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  />
												</div>
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												Calculate in Currency
											</div>
											<div class="tbox">
												<s:select name="slideCurrency" id="slideCurrency" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  />												
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												Class
											</div>
											<div class="tbox">
											<s:if test='"one".equals(slideScenario)'>
												<s:textfield name="slidedepartId" id="slidedepartId" cssClass="inputBox" readonly="true"  />		
											</s:if>
											<s:else>
												<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="slidedepartId" id="slidedepartId" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
											</s:else>
											</div>
										</div>
										</div>
										 <div class="boxcontent">
			                                <br class="clear"/>
			                                 <div class="boxcontent" align="center">
			                                    	<a  class="btn btn-sm btn-success" onclick="SlideCommission('slide');" >Go</a>
			                                </div>
			                            </div>
										</s:else>
		                            </div>
		                            </div>
		                        </div>
		                        
		                         <div class="panel panel-primary">
		                            <div class="panel-heading">
		                             <h4 class = "panel-title">
							            <a data-toggle = "collapse" data-parent = "#accordion" aria-expanded="true" href = "#collapseOne">
							               <i class="glyphicon glyphicon-plus" id="profitPlus" style="cursor: pointer;" onclick="detailsClick('profit')">&nbsp;<font face="Arial" ><b>Profit Commission</b></font></i>
											<i class="glyphicon glyphicon-minus" id="profitMinus" style="cursor: pointer; display: none;" onclick="detailsClick(false)">&nbsp;<font face="Arial" ><b>Profit Commission</b></font></i>   
							            </a>
							         </h4>
		                               
		                            </div>
									<div id="profitData" style="display: none;" class = "panel-collapse collapse in">
		                            <div class="panel-body" >
		                            <s:if test='""==premiumScenario || null==premiumScenario'>
		                            <div class="boxcontent">
											Profit Commission is not applicable for any class of this contract
											<s:hidden name="profitAsOnDate" id="expiryDate"/>
										</div>
		                            </s:if>
			                         <s:else>
		                            	<div class="boxcontent">
		                            	<div class="textfield">
											<div class="text">
												Calculate As On Date
											</div>
											<div class="tbox">
												<div class="inputAppend">
												<s:textfield name="profitAsOnDate" id="expiryDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)" placeholder='DD/MM/YYYY' />
												</div>
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												Calculate in Currency
											</div>
											<div class="tbox">
												<s:select name="profitCurrency" id="profitCurrency" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  />												
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												Class
											</div>
											<div class="tbox">
											<s:if test='"one".equals(premiumScenario)'>
												<s:textfield name="premiumdepartId" id="premiumdepartId" cssClass="inputBox" readonly="true"  />		
											</s:if>
											<s:else>
												<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="premiumdepartId" id="premiumdepartId" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
											</s:else>
											</div>
										</div>
										</div>
										 <div class="boxcontent">
			                                <br class="clear"/>
			                                 <div class="boxcontent" align="center">
			                                 <a  class="btn btn-sm btn-success" onclick="ProfitCommission('profit');" >Go</a>
			                                </div>
			                            </div>
			                            
										</s:else>
		                            </div>
		                            </div>
		                        </div>
		                         <div class="panel panel-primary">
		                            <div class="panel-heading">
		                             <h4 class = "panel-title">
							            <a data-toggle = "collapse" data-parent = "#accordion" aria-expanded="true" href = "#collapseOne">
							               <i class="glyphicon glyphicon-plus" id="lossPlus" style="cursor: pointer;" onclick="detailsClick('loss')">&nbsp;<font face="Arial" ><b>Loss Participation / Loss Corridor </b></font></i>
											<i class="glyphicon glyphicon-minus" id="lossMinus" style="cursor: pointer; display: none;" onclick="detailsClick(false)">&nbsp;<font face="Arial" ><b>Loss Participation / Loss Corridor</b></font></i>   
							            </a>
							         </h4>
		                               
		                            </div>
		
		                           <div id="lossData" style="display: none;" class = "panel-collapse collapse in">
		                            <div class="panel-body" >
		                             <s:if test='""==lossScenario || null==lossScenario'>
		                             	<div class="boxcontent">
											Loss Participation / Corridor is not applicable for any class of this contract
											<s:hidden name="lossAsOnDate" id="accountDate"/>
										</div>
		                              </s:if>
			                            <s:else>
		                            	<div class="boxcontent">
		                            	<div class="textfield">
											<div class="text">
												Calculate As On Date
											</div>
											<div class="tbox">
												<div class="inputAppend">
												<s:textfield name="lossAsOnDate" id="accountDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  />
												</div>
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												Calculate in Currency
											</div>
											<div class="tbox">
												<s:select name="lossCurrency" id="lossCurrency" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  />												
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												Class
											</div>
											<div class="tbox">
											<s:if test='"one".equals(lossScenario)'>
												<s:textfield name="lossdepartId" id="lossdepartId" cssClass="inputBox" readonly="true"  />		
											</s:if>
											<s:else>
												<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="lossdepartId" id="lossdepartId" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
											</s:else>
											</div>
										</div>
										</div>
										 <div class="boxcontent">
			                                <br class="clear"/>
			                                 <div class="boxcontent" align="center">
			                                  <a  class="btn btn-sm btn-success" onclick="LossCommission('loss');" >Go</a>
			                                </div>
			                            </div>
			                            
										</s:else>
		                            </div>
		                            </div>
		                        </div>
		                        </s:if>
		                        <s:if test='"1"==productId||"3"==productId ||"5"==productId'>
		                         <div class="panel panel-primary">
		                            <div class="panel-heading">
		                             <h4 class = "panel-title">
							            <a data-toggle = "collapse" data-parent = "#accordion" aria-expanded="true" href = "#collapseOne">
							               <i class="glyphicon glyphicon-plus" id="bonusPlus" style="cursor: pointer;" onclick="detailsClick('bonus')">&nbsp;<font face="Arial" ><b>No Claim Bonus / Low Claim Bonus</b></font> </i>
											<i class="glyphicon glyphicon-minus" id="bonusMinus" style="cursor: pointer; display: none;" onclick="detailsClick(false)"><font face="Arial" ><b>&nbsp;No Claim Bonus / Low Claim Bonus </b></font></i>  
							            </a>
							         </h4>
		                               
		                            </div>
		                             <div id="bonusData" style="display: none;" class = "panel-collapse collapse in">
		                           <div class="panel-body" >
		                            <s:if test='""==bonusStatus||null==bonusStatus'>
	                                <div class="boxcontent">
	                               	 Bonus is not applicable for this contract
	                               	 <s:hidden name="bonusAsOnDate" id="accountDate"/>
	                                </div>
                                </s:if>
                                <s:else>
		                            	<div class="boxcontent">
		                            	<div class="textfield">
											<div class="text">
												Calculate As On Date
											</div>
											<div class="tbox">
												<div class="inputAppend">
												<s:textfield name="bonusAsOnDate" id="accountDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  />
												</div>
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												Calculate in Currency
											</div>
											<div class="tbox">
												<s:select name="bonusCurrency" id="bonusCurrency" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  />												
											</div>
										</div>
										
										</div>
										 <div class="boxcontent">
			                                <br class="clear"/>
			                                 <div class="boxcontent" align="center">
			                                    	<a  class="btn btn-sm btn-success" onclick="BonusPopup();" >Go</a>
			                                </div>
			                            </div>
			                        </s:else>
		                            </div>
		                            </div>
		                        </div>
		                        </s:if>
		                        
	                             <div class="boxcontent">
	                                <br class="clear"/>
	                                 <div class="boxcontent" align="center">
	                                 <s:if test='"popup"!=mode'>
	                                    	<a  class="btn btn-sm btn-danger" onclick="funBack();" >Back</a>
	                                </s:if>
	                                <s:else>
	                                	<a  class="btn btn-sm btn-danger" onclick="destroyPopUps();window.close()" >Close</a>
	                                </s:else>
	                                </div>
	                            </div>
                        </div>
                        <s:hidden name="contractNo" id="contractNo"/>
                        <s:hidden name="productId" id="productId"/>
                        <s:hidden name="slideScenario" id="slideScenario"/>
                        <s:hidden name="lossScenario" id="lossScenario"/>
                        <s:hidden name="premiumScenario" id="premiumScenario"/>
                        <s:hidden name="proposal_No" id="proposal_No"/>
                        <s:hidden name="departmentId" id="departmentId"/>
                        <s:hidden name="layerNo" id="layerNo"/>
                        <s:hidden name="inception_Date" id="inception_Date"/>
                        <s:hidden name="typeId" id="typeId"/>
                        </s:if>
                        <s:else>
                        <div class="panel-body">
                                <div class="boxcontent">
                                    <div class="panel panel-primary">
                                        <div class="panel-body">
                                            <div class="boxcontent">
                                         <div id="testTable" class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;">  
										<table class="table table-bordered" width="100%" >
									<thead>
									
									<s:iterator value="columnHeaderlist" var="list" status="stat">
									<th class="tableColWidth"> <s:property value="#list.RANGE"/> </th>
									</s:iterator>
									<th class="tableColWidth">  </th>
										<tr>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="premiumList" var="list" status="stat">
										<tr>
										
											<td>
						 					<s:property value="#list.ITEM"/>
						 					</td>
											<td>
						 					<s:property value="#list.CONTRACT_NO"/> 
						 					</td>
											<td>
											<s:property value="#list.UW_YEAR"/>
											
											</td>
											<s:if test='#list.CNT>=1'>
											<td align="right">
											<s:property value="getText('number.format',{M1})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=2'>
											<td align="right">
											<s:property value="getText('number.format',{M2})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=3'>
											<td align="right">
											<s:property value="getText('number.format',{M3})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=4'>
											<td align="right">
											<s:property value="getText('number.format',{M4})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=5'>
											<td align="right">
											<s:property value="getText('number.format',{M5})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=6'>
											<td align="right">
											<s:property value="getText('number.format',{M6})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=7'>
											<td align="right">
											<s:property value="getText('number.format',{M7})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=8'>
											<td align="right">
											<s:property value="getText('number.format',{M8})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=9'>
											<td align="right">
											<s:property value="getText('number.format',{M9})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=10'>
											<td align="right">
											<s:property value="getText('number.format',{M10})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=11'>
											<td align="right">
											<s:property value="getText('number.format',{M11})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=12'>
											<td align="right">
											<s:property value="getText('number.format',{M12})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=13'>
											<td align="right">
											<s:property value="getText('number.format',{M13})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=14'>
											<td align="right">
											<s:property value="getText('number.format',{M14})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=15'>
											<td align="right">
											<s:property value="getText('number.format',{M15})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=16'>
											<td align="right">
											<s:property value="getText('number.format',{M16})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=17'>
											<td align="right">
											<s:property value="getText('number.format',{M17})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=18'>
											<td align="right">
											<s:property value="getText('number.format',{M18})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=19'>
											<td align="right">
											<s:property value="getText('number.format',{M19})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=20'>
											<td align="right">
											<s:property value="getText('number.format',{M20})"/>
											</td>
											</s:if>
											<td align="right">
											<s:property value="getText('number.format',{TOTAL})"/>
											</td>
											<td align="center">
											<s:if test='0==(#stat.count-1)'>
											 <a  class="btn btn-sm btn-info" onclick="getReportsDownload('40','PREMIUM');" >Download</a>
											</s:if>
											</td>
										</tr>
										</s:iterator>
										
										
									</tbody>
								</table>
											<table class="table table-bordered" width="100%" >
									<thead>
									
									<s:iterator value="columnHeaderlist" var="list" status="stat">
									<th class="tableColWidth"> <s:property value="#list.RANGE"/> </th>
									</s:iterator>
									<th class="tableColWidth">  </th>
										<tr>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="acqcostList" var="list" status="stat">
										<tr>
										
											<td>
						 					<s:property value="#list.ITEM"/>
						 					</td>
											<td>
						 					<s:property value="#list.CONTRACT_NO"/> 
						 					</td>
											<td>
											<s:property value="#list.UW_YEAR"/>
											
											</td>
											<s:if test='#list.CNT>=1'>
											<td align="right">
											<s:property value="getText('number.format',{M1})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=2'>
											<td align="right">
											<s:property value="getText('number.format',{M2})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=3'>
											<td align="right">
											<s:property value="getText('number.format',{M3})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=4'>
											<td align="right">
											<s:property value="getText('number.format',{M4})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=5'>
											<td align="right">
											<s:property value="getText('number.format',{M5})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=6'>
											<td align="right">
											<s:property value="getText('number.format',{M6})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=7'>
											<td align="right">
											<s:property value="getText('number.format',{M7})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=8'>
											<td align="right">
											<s:property value="getText('number.format',{M8})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=9'>
											<td align="right">
											<s:property value="getText('number.format',{M9})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=10'>
											<td align="right">
											<s:property value="getText('number.format',{M10})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=11'>
											<td align="right">
											<s:property value="getText('number.format',{M11})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=12'>
											<td align="right">
											<s:property value="getText('number.format',{M12})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=13'>
											<td align="right">
											<s:property value="getText('number.format',{M13})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=14'>
											<td align="right">
											<s:property value="getText('number.format',{M14})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=15'>
											<td align="right">
											<s:property value="getText('number.format',{M15})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=16'>
											<td align="right">
											<s:property value="getText('number.format',{M16})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=17'>
											<td align="right">
											<s:property value="getText('number.format',{M17})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=18'>
											<td align="right">
											<s:property value="getText('number.format',{M18})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=19'>
											<td align="right">
											<s:property value="getText('number.format',{M19})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=20'>
											<td align="right">
											<s:property value="getText('number.format',{M20})"/>
											</td>
											</s:if>
											<td align="right">
											<s:property value="getText('number.format',{TOTAL})"/>
											</td>
											<td align="center">
											<s:if test='0==(#stat.count-1)'>
											 <a  class="btn btn-sm btn-info" onclick="getReportsDownload('41','ACQCOST');" >Download</a>
											</s:if>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<table class="table table-bordered" width="100%" >
									<thead>
									
									<s:iterator value="columnHeaderlist" var="list" status="stat">
									<th class="tableColWidth"> <s:property value="#list.RANGE"/> </th>
									</s:iterator>
									<th class="tableColWidth">  </th>
										<tr>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="claimList" var="list" status="stat">
										<tr>
										
											<td>
						 					<s:property value="#list.ITEM"/>
						 					</td>
											<td>
						 					<s:property value="#list.CONTRACT_NO"/> 
						 					</td>
											<td>
											<s:property value="#list.UW_YEAR"/>
											
											</td>
											<s:if test='#list.CNT>=1'>
											<td align="right">
											<s:property value="getText('number.format',{M1})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=2'>
											<td align="right">
											<s:property value="getText('number.format',{M2})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=3'>
											<td align="right">
											<s:property value="getText('number.format',{M3})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=4'>
											<td align="right">
											<s:property value="getText('number.format',{M4})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=5'>
											<td align="right">
											<s:property value="getText('number.format',{M5})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=6'>
											<td align="right">
											<s:property value="getText('number.format',{M6})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=7'>
											<td align="right">
											<s:property value="getText('number.format',{M7})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=8'>
											<td align="right">
											<s:property value="getText('number.format',{M8})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=9'>
											<td align="right">
											<s:property value="getText('number.format',{M9})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=10'>
											<td align="right">
											<s:property value="getText('number.format',{M10})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=11'>
											<td align="right">
											<s:property value="getText('number.format',{M11})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=12'>
											<td align="right">
											<s:property value="getText('number.format',{M12})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=13'>
											<td align="right">
											<s:property value="getText('number.format',{M13})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=14'>
											<td align="right">
											<s:property value="getText('number.format',{M14})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=15'>
											<td align="right">
											<s:property value="getText('number.format',{M15})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=16'>
											<td align="right">
											<s:property value="getText('number.format',{M16})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=17'>
											<td align="right">
											<s:property value="getText('number.format',{M17})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=18'>
											<td align="right">
											<s:property value="getText('number.format',{M18})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=19'>
											<td align="right">
											<s:property value="getText('number.format',{M19})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=20'>
											<td align="right">
											<s:property value="getText('number.format',{M20})"/>
											</td>
											</s:if>
											<td align="right">
											<s:property value="getText('number.format',{TOTAL})"/>
											</td>
											<td align="center">
											<s:if test='0==(#stat.count-1)'>
											 <a  class="btn btn-sm btn-info" onclick="getReportsDownload('42','CLAIM');" >Download</a>
											</s:if>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<table class="table table-bordered" width="100%" >
									<thead>
									
									<s:iterator value="columnHeaderlist" var="list" status="stat">
									<th class="tableColWidth"> <s:property value="#list.RANGE"/> </th>
									</s:iterator>
									<th class="tableColWidth">  </th>
										<tr>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="claimoutStandList" var="list" status="stat">
										<tr>
										
											<td>
						 					<s:property value="#list.ITEM"/>
						 					</td>
											<td>
						 					<s:property value="#list.CONTRACT_NO"/> 
						 					</td>
											<td>
											<s:property value="#list.UW_YEAR"/>
											
											</td>
											<s:if test='#list.CNT>=1'>
											<td align="right">
											<s:property value="getText('number.format',{M1})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=2'>
											<td align="right">
											<s:property value="getText('number.format',{M2})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=3'>
											<td align="right">
											<s:property value="getText('number.format',{M3})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=4'>
											<td align="right">
											<s:property value="getText('number.format',{M4})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=5'>
											<td align="right">
											<s:property value="getText('number.format',{M5})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=6'>
											<td align="right">
											<s:property value="getText('number.format',{M6})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=7'>
											<td align="right">
											<s:property value="getText('number.format',{M7})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=8'>
											<td align="right">
											<s:property value="getText('number.format',{M8})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=9'>
											<td align="right">
											<s:property value="getText('number.format',{M9})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=10'>
											<td align="right">
											<s:property value="getText('number.format',{M10})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=11'>
											<td align="right">
											<s:property value="getText('number.format',{M11})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=12'>
											<td align="right">
											<s:property value="getText('number.format',{M12})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=13'>
											<td align="right">
											<s:property value="getText('number.format',{M13})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=14'>
											<td align="right">
											<s:property value="getText('number.format',{M14})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=15'>
											<td align="right">
											<s:property value="getText('number.format',{M15})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=16'>
											<td align="right">
											<s:property value="getText('number.format',{M16})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=17'>
											<td align="right">
											<s:property value="getText('number.format',{M17})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=18'>
											<td align="right">
											<s:property value="getText('number.format',{M18})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=19'>
											<td align="right">
											<s:property value="getText('number.format',{M19})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=20'>
											<td align="right">
											<s:property value="getText('number.format',{M20})"/>
											</td>
											</s:if>
											<td align="right">
											<s:property value="getText('number.format',{TOTAL})"/>
											</td>
											<td align="center">
											<s:if test='0==(#stat.count-1)'>
											 <a  class="btn btn-sm btn-info" onclick="getReportsDownload('43','CLAIMOUTSTANDING');" >Download</a>
											</s:if>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<table class="table table-bordered" width="100%" >
									<thead>
									
									<s:iterator value="columnHeaderlist" var="list" status="stat">
									<th class="tableColWidth"> <s:property value="#list.RANGE"/> </th>
									</s:iterator>
									<th class="tableColWidth">  </th>
										<tr>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="treatyResultList" var="list" status="stat">
										<tr>
										
											<td>
						 					<s:property value="#list.ITEM"/>
						 					</td>
											<td>
						 					<s:property value="#list.CONTRACT_NO"/> 
						 					</td>
											<td>
											<s:property value="#list.UW_YEAR"/>
											
											</td>
											<s:if test='#list.CNT>=1'>
											<td align="right">
											<s:property value="getText('number.format',{M1})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=2'>
											<td align="right">
											<s:property value="getText('number.format',{M2})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=3'>
											<td align="right">
											<s:property value="getText('number.format',{M3})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=4'>
											<td align="right">
											<s:property value="getText('number.format',{M4})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=5'>
											<td align="right">
											<s:property value="getText('number.format',{M5})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=6'>
											<td align="right">
											<s:property value="getText('number.format',{M6})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=7'>
											<td align="right">
											<s:property value="getText('number.format',{M7})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=8'>
											<td align="right">
											<s:property value="getText('number.format',{M8})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=9'>
											<td align="right">
											<s:property value="getText('number.format',{M9})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=10'>
											<td align="right">
											<s:property value="getText('number.format',{M10})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=11'>
											<td align="right">
											<s:property value="getText('number.format',{M11})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=12'>
											<td align="right">
											<s:property value="getText('number.format',{M12})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=13'>
											<td align="right">
											<s:property value="getText('number.format',{M13})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=14'>
											<td align="right">
											<s:property value="getText('number.format',{M14})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=15'>
											<td align="right">
											<s:property value="getText('number.format',{M15})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=16'>
											<td align="right">
											<s:property value="getText('number.format',{M16})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=17'>
											<td align="right">
											<s:property value="getText('number.format',{M17})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=18'>
											<td align="right">
											<s:property value="getText('number.format',{M18})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=19'>
											<td align="right">
											<s:property value="getText('number.format',{M19})"/>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=20'>
											<td align="right">
											<s:property value="getText('number.format',{M20})"/>
											</td>
											</s:if>
											<td align="right">
											<s:property value="getText('number.format',{TOTAL})"/>
											</td>
											<td align="center">
											<s:if test='0==(#stat.count-1)'>
											 <a  class="btn btn-sm btn-info" onclick="tableToExcel('testTable', 'UW Statistics')" >Download</a>
											</s:if>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<table class="table table-bordered" width="100%"  >
									<thead>
									
									<s:iterator value="columnHeaderlist" var="list" status="stat">
									<th class="tableColWidth"> <s:property value="#list.RANGE"/> </th>
									</s:iterator>
									<th class="tableColWidth">  </th>
										<tr>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="claimRatioList" var="list" status="stat">
										<tr>
										
											<td>
						 					<s:property value="#list.ITEM"/>
						 					</td>
											<td>
						 					<s:property value="#list.CONTRACT_NO"/> 
						 					</td>
											<td>
											<s:property value="#list.UW_YEAR"/>
											
											</td>
											<s:if test='#list.CNT>=1'>
											<td align="right">
											<s:if test="#list.M1==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M1})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=2'>
											<td align="right">
											<s:if test="#list.M2==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M2})"/>
											</s:else>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=3'>
											<td align="right">
											<s:if test="#list.M3==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M3})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=4'>
											<td align="right">
											<s:if test="#list.M4==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M4})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=5'>
											<td align="right">
											<s:if test="#list.M5==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M5})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=6'>
											<td align="right">
											<s:if test="#list.M6==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="#list.M6"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=7'>
											<td align="right">
											<s:if test="#list.M7==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M7})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=8'>
											<td align="right">
											<s:if test="#list.M8==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M8})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=9'>
											<td align="right">
											<s:if test="#list.M9==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M9})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=10'>
											<td align="right">
											<s:if test="#list.M10==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M10})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=11'>
											<td align="right">
											<s:if test="#list.M11==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M11})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=12'>
											<td align="right">
											<s:if test="#list.M12==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M12})"/>
											</s:else>
											</td>
											</s:if>
											
											<s:if test='#list.CNT>=13'>
											<td align="right">
											<s:if test="#list.M13==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M13})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=14'>
											<td align="right">
											<s:if test="#list.M14==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M14})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=15'>
											<td align="right">
											<s:if test="#list.M15==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M15})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=16'>
											<td align="right">
											<s:if test="#list.M16==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M16})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=17'>
											<td align="right">
											<s:if test="#list.M17==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M17})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=18'>
											<td align="right">
											<s:if test="#list.M18==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M18})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=19'>
											<td align="right">
											<s:if test="#list.M19==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M19})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=20'>
											<td align="right">
											<s:if test="#list.M20==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M20})"/>
											</s:else>
											</td>
											</s:if>
											<td align="right">
											<s:if test="#list.TOTAL==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{TOTAL})"/>
											</s:else>
											<td align="center">
											<s:if test='0==(#stat.count-1)'>
											 <a  class="btn btn-sm btn-info" onclick="tableToExcel('testTable', 'UW Statistics')" >Download</a>
											</s:if>
											</td>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<table class="table table-bordered" width="100%" >
									<thead>
									
									<s:iterator value="columnHeaderlist" var="list" status="stat">
									<th class="tableColWidth"> <s:property value="#list.RANGE"/> </th>
									</s:iterator>
									<th class="tableColWidth">  </th>
										<tr>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="combinedRatioList" var="list" status="stat">
										<tr>
										
											<td>
						 					<s:property value="#list.ITEM"/>
						 					</td>
											<td>
						 					<s:property value="#list.CONTRACT_NO"/> 
						 					</td>
											<td>
											<s:property value="#list.UW_YEAR"/>
											
											</td>
											<s:if test='#list.CNT>=1'>
											<td align="right">
											<s:if test="#list.M1==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M1})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=2'>
											<td align="right">
											<s:if test="#list.M2==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M2})"/>
											</s:else>
											
											</td>
											</s:if>
											<s:if test='#list.CNT>=3'>
											<td align="right">
											<s:if test="#list.M3==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M3})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=4'>
											<td align="right">
											<s:if test="#list.M4==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M4})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=5'>
											<td align="right">
											<s:if test="#list.M5==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M5})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=6'>
											<td align="right">
											<s:if test="#list.M6==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="#list.M6"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=7'>
											<td align="right">
											<s:if test="#list.M7==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M7})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=8'>
											<td align="right">
											<s:if test="#list.M8==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M8})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=9'>
											<td align="right">
											<s:if test="#list.M9==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M9})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=10'>
											<td align="right">
											<s:if test="#list.M10==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M10})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=11'>
											<td align="right">
											<s:if test="#list.M11==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M11})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=12'>
											<td align="right">
											<s:if test="#list.M12==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M12})"/>
											</s:else>
											</td>
											</s:if>
											
											<s:if test='#list.CNT>=13'>
											<td align="right">
											<s:if test="#list.M13==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M13})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=14'>
											<td align="right">
											<s:if test="#list.M14==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M14})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=15'>
											<td align="right">
											<s:if test="#list.M15==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M15})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=16'>
											<td align="right">
											<s:if test="#list.M16==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M16})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=17'>
											<td align="right">
											<s:if test="#list.M17==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M17})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=18'>
											<td align="right">
											<s:if test="#list.M18==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M18})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=19'>
											<td align="right">
											<s:if test="#list.M19==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M19})"/>
											</s:else>
											</td>
											</s:if>
											<s:if test='#list.CNT>=20'>
											<td align="right">
											<s:if test="#list.M20==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{M20})"/>
											</s:else>
											</td>
											</s:if>
											<td align="right">
											<s:if test="#list.TOTAL==99999999">
											∞
											</s:if>
											<s:else>
											<s:property value="getText('number.format',{TOTAL})"/>
											</s:else>
											<td align="center">
											<s:if test='0==(#stat.count-1)'>
											 <a  class="btn btn-sm btn-info" onclick="tableToExcel('testTable', 'UW Statistics')" >Download</a>
											</s:if>
											</td>
										</tr>
										</s:iterator>
									</tbody>
								</table>			
												<br class="clear"/>
												</div>
                                            </div>
                                            
                                        </div>
                                        <div class="boxcontent">
	                                <br class="clear"/>
	                                 <div class="boxcontent" align="center">
	                                 <a  class="btn btn-sm btn-info" onclick="tableToExcel('testTable', 'UW Statistics')" >Export to Excel</a>
	                                    	<a  class="btn btn-sm btn-danger" onclick="reBack();" >Back</a>
	                                
	                                </div>
	                            </div>
                                     </div>
                                 </div>
                            </div>
                         <s:hidden name="contractNo" id="contractNo"/>
                         <s:hidden name="proposal_No" id="proposal_No"/>
                          <s:hidden name="inception_Date" id="inception_Date"/>
                           <s:hidden name="uwFrom" id="uwFrom"/>
                            <s:hidden name="uwTo" id="uwTo"/>
                             <s:hidden name="periodFrom" id="periodFrom"/>
                              <s:hidden name="periodTo" id="periodTo"/>
                              <s:hidden name="productId" id="productId"/>
                              <s:hidden name="typeId" id="typeId"/>
                              <s:hidden name="type" id="type"/>
                              
                        </s:else>
                        </s:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
var mtbChildWin = new Array();
var mtbWinCound = 0;
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }
function funBack(){
destroyPopUps();
document.statistics.action='${pageContext.request.contextPath}/InitPortfolio.do'; 
document.statistics.submit();
}
function reBack(){
document.statistics.action='${pageContext.request.contextPath}/statisticsDownStatistics.do'; 
document.statistics.submit();
}
function funDownload(){
document.getElementById('typeId').value='26';
document.statistics.action='${pageContext.request.contextPath}/getRlistReports.do?start_date=01/01/2001&end_date=31/12/2100&mode=ContractDown&reportType=xml&docType=ALL&uwYear=-1&cedingId=-1&brokerId=-1&showAllFields=N'; 
document.statistics.submit();
}
function funGenerate(){
document.statistics.action='${pageContext.request.contextPath}/renewalDetailsStatistics.do'; 
document.statistics.submit();
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
function SlideCommission(mode){
    var contrctNo = document.getElementById("contractNo").value;
    var transaction = document.getElementById("inceptionDate").value;
    var currency = document.getElementById("slideCurrency").value;
    var slideScenario = document.getElementById("slideScenario").value;
	var statementDate = document.getElementById("inceptionDate").value;
	var proposal_No = document.getElementById("proposal_No").value;
	var deptId= document.getElementById("slidedepartId").value;
		var status="";
	if("ALL"==deptId){
	var departmentId =  document.getElementById("departmentId").value;
	}
	else{
	var departmentId =deptId
	}
	if(transaction ==null || transaction ==''){
        alert("Please Select Calculate As On Date ");
	}else{
	var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
     if(transaction.match(dateformat)){
		var splitVal = transaction.split('/');
		var status="";
		var dd = parseInt(splitVal[0]);
              var mm  = parseInt(splitVal[1]);
              var yy = parseInt(splitVal[2]);
              var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];
              if (mm==1 || mm>2)
              {
                  if (dd>ListofDays[mm-1])
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail';
                  }
              }
              if (mm==2)
              {
                  var lyear = false;
                  if ( (!(yy % 4) && yy % 100) || !(yy % 400))
                  {
                      lyear = true;
                  }
                  if ((lyear==false) && (dd>=29))
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail'; 
                  }
                  if ((lyear==true) && (dd>29))
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail';  
                  }
              }
          }
          else
          {
		   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
             status='fail';  
          }
	}
	if(status!='fail'){
	if(splitVal[0] .length<2){
	 	splitVal[0]="0"+splitVal[0];
		 }if(splitVal[1].length<2){
		 splitVal[1]="0"+splitVal[1];
		 }
		 transaction = splitVal[0] + "/" + splitVal[1] + "/" + splitVal[2];
		 document.getElementById("inceptionDate").value = transaction;
		 }
	if(currency == null || currency ==''){
        alert("Please Select Calculate in Currency");
	}
	if(departmentId == null || departmentId ==''){
        alert("Please Select  Class");
	}
	
 var todayDate = new Date();
 var todayMonth = todayDate.getMonth() + 1;
 var todayDay = todayDate.getDate();
 var todayYear = todayDate.getFullYear();
 var todayDateText = todayDay + "/" + todayMonth + "/" + todayYear;
 
 var inputToDate = Date.parse(transaction);
 var todayToDate = Date.parse(todayDateText);
 
 if (inputToDate > todayToDate) {
	alert("Calculate As On Date Should be Less than or Equal to System Date");
	}
	else{
	if(transaction !=null && transaction !='' && currency != null && currency !='' && departmentId != null && departmentId !='' &&status!='fail'){
    var URL ='${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode='+mode+'&contNo='+contrctNo+'&proposal_No='+proposal_No+'&transaction='+transaction+'&currencyId='+currency+'&slideScenario='+slideScenario+'&statementDate='+statementDate+'&departmentId='+departmentId;
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
    mtbChildWin[mtbWinCound] = strOpen;
	mtbWinCound++;
    strOpen.focus();
    return false;
    }
    }
}

function LossCommission(mode){
   var contrctNo = document.getElementById("contractNo").value;
    var transaction = document.getElementById("accountDate").value;
    var currency = document.getElementById("lossCurrency").value;
    var slideScenario = document.getElementById("lossScenario").value;
	var statementDate = document.getElementById("expiryDate").value;
	var proposal_No = document.getElementById("proposal_No").value;
	var deptId= document.getElementById("lossdepartId").value;
	var status="";
	if("ALL"==deptId){
	var departmentId =  document.getElementById("departmentId").value;
	}
	else{
	var departmentId =deptId
	}
	if(transaction ==null || transaction ==''){
        alert("Please Select Calculate As On Date ");
	}else{
	var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
     if(transaction.match(dateformat)){
		var splitVal = transaction.split('/');
		var status="";
		var dd = parseInt(splitVal[0]);
              var mm  = parseInt(splitVal[1]);
              var yy = parseInt(splitVal[2]);
              var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];
              if (mm==1 || mm>2)
              {
                  if (dd>ListofDays[mm-1])
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail';
                  }
              }
              if (mm==2)
              {
                  var lyear = false;
                  if ( (!(yy % 4) && yy % 100) || !(yy % 400))
                  {
                      lyear = true;
                  }
                  if ((lyear==false) && (dd>=29))
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail'; 
                  }
                  if ((lyear==true) && (dd>29))
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail';  
                  }
              }
          }
          else
          {
		   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
             status='fail';  
          }
	}
	if(status!='fail'){
	if(splitVal[0] .length<2){
	 	splitVal[0]="0"+splitVal[0];
		 }if(splitVal[1].length<2){
		 splitVal[1]="0"+splitVal[1];
		 }
		 transaction = splitVal[0] + "/" + splitVal[1] + "/" + splitVal[2];
		 document.getElementById("accountDate").value = transaction;
		 }
	if(currency == null || currency ==''){
        alert("Please Select Calculate in Currency");
	}
	if(departmentId == null || departmentId ==''){
        alert("Please Select  Class");
	}
	
 var todayDate = new Date();
 var todayMonth = todayDate.getMonth() + 1;
 var todayDay = todayDate.getDate();
 var todayYear = todayDate.getFullYear();
 var todayDateText = todayDay + "/" + todayMonth + "/" + todayYear;
 var inputToDate = Date.parse(transaction);
 var todayToDate = Date.parse(todayDateText);
 if (inputToDate > todayToDate) {
	alert("Calculate As On Date Should be Less than or Equal to System Date");
	}
	else{
	if(transaction !=null && transaction !='' && currency != null && currency !='' && departmentId != null && departmentId !='' &&status!='fail'){
	 var URL ='${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode='+mode+'&contNo='+contrctNo+'&proposal_No='+proposal_No+'&transaction='+transaction+'&currencyId='+currency+'&slideScenario='+slideScenario+'&statementDate='+statementDate+'&departmentId='+departmentId;
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
    mtbChildWin[mtbWinCound] = strOpen;
	mtbWinCound++;
    strOpen.focus();
    return false;
 }
}
}


function ProfitCommission(mode){
   var contrctNo = document.getElementById("contractNo").value;
    var transaction = document.getElementById("expiryDate").value;
    var currency = document.getElementById("profitCurrency").value;
    var slideScenario = document.getElementById("premiumScenario").value;
	var statementDate = document.getElementById("expiryDate").value;
	var proposal_No = document.getElementById("proposal_No").value;
	var deptId= document.getElementById("premiumdepartId").value;
	var status="";
	if("ALL"==deptId){
	var departmentId =  document.getElementById("departmentId").value;
	}
	else{
	var departmentId =deptId
	}
	if(transaction ==null || transaction ==''){
        alert("Please Select Calculate As On Date ");
	}else{
	var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
     if(transaction.match(dateformat)){
		var splitVal = transaction.split('/');
		var status="";
		var dd = parseInt(splitVal[0]);
              var mm  = parseInt(splitVal[1]);
              var yy = parseInt(splitVal[2]);
              var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];
              if (mm==1 || mm>2)
              {
                  if (dd>ListofDays[mm-1])
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail';
                  }
              }
              if (mm==2)
              {
                  var lyear = false;
                  if ( (!(yy % 4) && yy % 100) || !(yy % 400))
                  {
                      lyear = true;
                  }
                  if ((lyear==false) && (dd>=29))
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail'; 
                  }
                  if ((lyear==true) && (dd>29))
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail';  
                  }
              }
          }
          else
          {
		   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
             status='fail';  
          }
	}
	if(status!='fail'){
	if(splitVal[0] .length<2){
	 	splitVal[0]="0"+splitVal[0];
		 }if(splitVal[1].length<2){
		 splitVal[1]="0"+splitVal[1];
		 }
		 transaction = splitVal[0] + "/" + splitVal[1] + "/" + splitVal[2];
		 document.getElementById("expiryDate").value = transaction;
		 }
	if(currency == null || currency ==''){
        alert("Please Select Calculate in Currency");
	}
	if(departmentId == null || departmentId ==''){
        alert("Please Select  Class");
	}
	
 var todayDate = new Date();
 var todayMonth = todayDate.getMonth() + 1;
 var todayDay = todayDate.getDate();
 var todayYear = todayDate.getFullYear();
 var todayDateText = todayDay + "/" + todayMonth + "/" + todayYear;
 
 var inputToDate = Date.parse(transaction);
 var todayToDate = Date.parse(todayDateText);
 
 if (inputToDate > todayToDate) {
	alert("Calculate As On Date Should be Less than or Equal to System Date");
	}
	else{
	if(transaction !=null && transaction !='' && currency != null && currency !='' && departmentId != null && departmentId !='' && status!='fail'){
	 var URL ='${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode='+mode+'&contNo='+contrctNo+'&proposal_No='+proposal_No+'&transaction='+transaction+'&currencyId='+currency+'&slideScenario='+slideScenario+'&statementDate='+statementDate+'&departmentId='+departmentId;
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
    mtbChildWin[mtbWinCound] = strOpen;
	mtbWinCound++;
    strOpen.focus();
    return false;
 }
}

}

function BonusPopup(){
    var contrctNo = document.getElementById("contractNo").value;
    var transaction = document.getElementById("accountDate").value;
    var currency = document.getElementById("bonusCurrency").value;
    var proposal_No = document.getElementById("proposal_No").value;
	var departmentId =  document.getElementById("departmentId").value;
    var layerNo = document.getElementById("layerNo").value;
    var status="";
    if(transaction ==null || transaction ==''){
        alert("Please Select Transaction Date ");
	}else{
	var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
     if(transaction.match(dateformat)){
		var splitVal = transaction.split('/');
		var status="";
		var dd = parseInt(splitVal[0]);
              var mm  = parseInt(splitVal[1]);
              var yy = parseInt(splitVal[2]);
              var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];
              if (mm==1 || mm>2)
              {
                  if (dd>ListofDays[mm-1])
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail';
                  }
              }
              if (mm==2)
              {
                  var lyear = false;
                  if ( (!(yy % 4) && yy % 100) || !(yy % 400))
                  {
                      lyear = true;
                  }
                  if ((lyear==false) && (dd>=29))
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail'; 
                  }
                  if ((lyear==true) && (dd>29))
                  {
				   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
                    status='fail';  
                  }
              }
          }
          else
          {
		   alert("Invalid date.Please Enter Date DD/MM/YYYY format");
             status='fail';  
          }
	}
	if(currency == null || currency ==''){
        alert("Please Select Currency");
	}
	if(status!='fail'){
	if(splitVal[0] .length<2){
	 	splitVal[0]="0"+splitVal[0];
		 }if(splitVal[1].length<2){
		 splitVal[1]="0"+splitVal[1];
		 }
		 transaction = splitVal[0] + "/" + splitVal[1] + "/" + splitVal[2];
		 document.getElementById("accountDate").value = transaction;
		 }
 var todayDate = new Date();
 var todayMonth = todayDate.getMonth() + 1;
 var todayDay = todayDate.getDate();
 var todayYear = todayDate.getFullYear();
 if(todayMonth<10){
 todayMonth="0"+todayMonth;
 }
 if(todayDay <10){
 todayDay="0"+todayDay;
 }
 var todayDateText = todayDay + "/" + todayMonth + "/" + todayYear;
 
 var inputToDate = Date.parse(transaction);
 var todayToDate = Date.parse(todayDateText);
 
 if (inputToDate > todayToDate) {
	alert("Calculate As On Date Should be Less than or Equal to System Date");
	}
	else{
	if(transaction !=null && transaction !='' && currency != null && currency !='' &&status!='fail'){
    var URL ='${pageContext.request.contextPath}/bonusviewPopupFaculPremium.do?mode=bonus&contNo='+contrctNo+'&transaction='+transaction+'&currencyId='+currency+'&departmentId='+departmentId+'&proposal_No='+proposal_No+'&layerno='+layerNo;
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
    mtbChildWin[mtbWinCound] = strOpen;
	mtbWinCound++;
    strOpen.focus();
    return false;
}
}
}
getTodayDate();
function getTodayDate(){
 var todayDate = new Date();
 var todayMonth = todayDate.getMonth() + 1;
 var todayDay = todayDate.getDate();
 var todayYear = todayDate.getFullYear();
 if(todayMonth<10){
 todayMonth="0"+todayMonth;
 }
 if(todayDay <10){
 todayDay="0"+todayDay;
 }
 var todayDateText = todayDay + "/" + todayMonth + "/" + todayYear;
 var pid=document.getElementById("productId").value
 if("1"==pid||"3"==pid ||"5"==pid){
	  var accountDate =document.getElementById("accountDate").value;
	  if(''==accountDate){
	 	document.getElementById("accountDate").value=todayDateText;
	 }
 }
 else if("2"==pid){
	var date =document.getElementById("inceptionDate").value;
	var expiryDate = document.getElementById("expiryDate").value;
	 if(''==date){
	 document.getElementById("inceptionDate").value=todayDateText;
	 }  
	 if(''==expiryDate){
	 document.getElementById("expiryDate").value=todayDateText;
	 }
	  var accountDate =document.getElementById("accountDate").value;
	  if(''==accountDate){
	 	document.getElementById("accountDate").value=todayDateText;
	 }
  }
 }
 
 
function detailsClick(val) { 
var pid=document.getElementById("productId").value
if("2"==pid){
 if("slide"==val){
  		document.getElementById('detailsData').style.display='block';
  		document.getElementById('detailsMinus').style.display='block';
  		document.getElementById('lossMinus').style.display='none';
		document.getElementById('profitMinus').style.display='none';	
  		document.getElementById('profitData').style.display='none';
  		document.getElementById('lossData').style.display='none';		
		document.getElementById('detailsPlus').style.display='none';
		document.getElementById('profitPlus').style.display='block';
		document.getElementById('lossPlus').style.display='block';
    }else if("loss"==val){
  		document.getElementById('detailsData').style.display='none';
  		document.getElementById('profitData').style.display='none';
  		document.getElementById('lossData').style.display='block';
  		document.getElementById('lossMinus').style.display='block';	
  		document.getElementById('detailsMinus').style.display='none';
		document.getElementById('profitMinus').style.display='none';	
		document.getElementById('detailsPlus').style.display='block';
		document.getElementById('profitPlus').style.display='block';
		document.getElementById('lossPlus').style.display='none';
    }else if("profit"==val){
  		document.getElementById('detailsData').style.display='none';
  		document.getElementById('profitData').style.display='block';
  		document.getElementById('lossData').style.display='none';
  		document.getElementById('profitMinus').style.display='block';	
  		document.getElementById('detailsMinus').style.display='none';
  		document.getElementById('lossMinus').style.display='none';
		document.getElementById('detailsPlus').style.display='block';
		document.getElementById('profitPlus').style.display='none';
		document.getElementById('lossPlus').style.display='block';
		}
		else{
    	document.getElementById('detailsData').style.display='none';
		document.getElementById('detailsMinus').style.display='none';
		document.getElementById('lossMinus').style.display='none';
		document.getElementById('profitMinus').style.display='none';	
		document.getElementById('profitData').style.display='none';
  		document.getElementById('lossData').style.display='none';		
		document.getElementById('detailsPlus').style.display='block';
		document.getElementById('profitPlus').style.display='block';
		document.getElementById('lossPlus').style.display='block';
    }
    }
  else if("1"==pid||"3"==pid || "5"==pid){
    if("bonus"==val){
		document.getElementById('bonusMinus').style.display='block';
		document.getElementById('bonusPlus').style.display='none';
		document.getElementById('bonusData').style.display='block';
    }else{
		document.getElementById('bonusMinus').style.display='none';
		document.getElementById('bonusPlus').style.display='block';
		document.getElementById('bonusData').style.display='none';
    }
    }
}
function getViewContractDetails(prodId,proposalNo,amendId) {
		var URL ='';
		if(prodId=='1'){
			URL ='${pageContext.request.contextPath}/ViewMethodFacultative?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		else if(prodId=='2') {
			URL ='${pageContext.request.contextPath}/ViewMethodRiskDetails?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		else if((prodId=='3')||(prodId=='5')) {
			URL ='${pageContext.request.contextPath}/ViewMethodXol?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		else {
			URL ='${pageContext.request.contextPath}/ViewMethodRetro?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		
		var windowName = "Contract Details";
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
			mtbChildWin[mtbWinCound] = strOpen;
			mtbWinCound++;
			strOpen.focus();
			return false;
	}
	function getAjax(value,id)
{
var uwFrom=document.getElementById("uwFrom").value;
var uwTo=document.getElementById("uwTo").value;
var contractNo = document.getElementById("contractNo").value;
if(uwFrom!=null && uwFrom!='' && uwTo!=null && uwTo!=''){
	var URL='${pageContext.request.contextPath}/ajaxValueStatistics.action?uwFrom='+uwFrom+'&uwTo='+uwTo+'&contractNo='+contractNo+'&dropDown=reclass';
	postRequest(URL,'reclass');
	}
}
 function getReportsDownload(val,val1)
{
	document.getElementById("productId").value='6';
	document.getElementById('typeId').value=val;
	document.getElementById('type').value=val1;
	document.statistics.action="${pageContext.request.contextPath}/getRlistReports?reportType=Excel";
	document.statistics.submit();
}
</script>

</body>
</html>
