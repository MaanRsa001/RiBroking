<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="JavaScript" type="text/javascript">
	
	</script >
	<STYLE type="text/css">
	  @media print {
	    @page {
	        margin-top: 0; 
	        margin-bottom: 0; 
	    } 
	    body {
	        padding-top: 72px; 
	        padding-bottom: 72px ; 
	    } 
	}
	
	table, th, td {
	  border: 1px solid black;
	  border-collapse: collapse;
	}
	th, td {
	  padding: 5px;
	  text-align: center;    
	}
	</STYLE>	
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
		<!-- Include Twitter Bootstrap and jQuery: -->
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	 
	<!-- Include the plugin's CSS and JS: -->
	<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
	<link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>

	
	<style type="text/css">
	.text {
		width: 40%;
	}
	.tbox {
		width: 60%;
	}
	.inputBox {
		width: 95%;
	}	
	</style>		
</head>
<body>
<s:form name="facultativeView" id="facultativeView" action="FaculitivesDispatchAction" method="post" theme="simple">
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<div class="table2">
						<div class="tablerow">
							<div class="boxcontent">
							<br/>
							<div class="row" align="right">
								<img alt="ugandare" src="<%=request.getContextPath()%>/images/ugandare_logo.JPG">&emsp;&emsp;
							</div>
								<div class="panel-heading" align="center">
									 <s:text name="Claim Authorisation Form" />
								</div>
								<div class="panel-body">
									<div class="row">
										<s:if test="multiPaymentNoList.size()>0">
											<table>
												<thead>
													<tr>
														<th style="text-align: center">
															COMPANY NAME
														</th>
														<th style="text-align: center">
															INSURED NAME
														</th>
														<th style="text-align: center">
															CONTRACT NO
														</th>
														<th style="text-align: center">
															REFERENCE
														</th>
														<th style="text-align: center">
															CURRENCY
														</th>
														<th style="text-align: center">
															CLASS
														</th>
														<th style="text-align: center">
															LOSS DETAILS
														</th>
														<th style="text-align: center">
															DATE OF LOSS
														</th>
														<th style="text-align: center">
															LOSS ESTIMATE OC
														</th>
														<th style="text-align: center">
															SHARE SIGNED %
														</th>
														<th style="text-align: center">
															LOSS ESTIMATE OS OC
														</th>
														<th style="text-align: center">
															PAYMENT TYPE
														</th>
														<th style="text-align: center">
															CLAIM NO
														</th>
														<th style="text-align: center">
															CLAIM PAYMENT NO
														</th>
													</tr>
												</thead>
												<tbody>
												<s:iterator  value="multiPaymentNoList" status="stat" var="claim" >
													<tr align="right">
														<td align="center"><s:property value="%{#claim.COMPANY_NAME}" /></td>
														<td align="center"><s:property value="%{#claim.INSURED_NAME}" /></td>
														<td align="center" ><s:property value="%{#claim.CONTRACT_NO}" /></td>
														<td align="center"><s:property value="%{#claim.REFERENCE}" /></td>
														<td align="center"><s:property value="%{#claim.CURRENCY}" /></td>
														<td align="center"><s:property value="%{#claim.CLASS}" /></td>
														<td align="center"><s:property value="%{#claim.LOSS_DETAILS}" /></td>
														<td align="center"><s:property value="%{#claim.DATE_OF_LOSS}" /></td>
														<td style="text-align: right"><s:property value="getText('number.format',{#claim.LOSS_ESTIMATE_OC})" default="0" /></td>
														<td style="text-align: right"><s:property value="getText('number.format',{#claim.SHARE_SIGNED})" default="0" /></td>
														<td style="text-align: right"><s:property value="getText('number.format',{#claim.LOSS_ESTIMATE_OS_OC})" default="0" /></td>
														<td align="center"><s:property value="%{#claim.PAYMENT_TYPE}" /></td>
														<td align="center"><s:property value="%{#claim.CLAIM_NO}" /></td>
														<td align="center"><s:property value="%{#claim.CLAIM_PAYMENT_NO}" /></td>
													</tr>
												</s:iterator>
												<tr align="right">
													<td align="center" colspan="8"><b>Total</b></td>
													<td style="text-align: right"><b><s:property value="loss_Estimate_Orig_Curr" default="0" /></b></td>
													<td style="text-align: right"></td>
													<td style="text-align: right"><b><s:property value="loss_Estimate_Our_share_Orig_Curr" default="0" /></b></td>
													<td align="center" colspan="3"></td>
												</tr>
												</tbody>
											</table>
											<br/>
											<br/>
											<div class="boxcontent" align="center" id="buttonDivId">
												<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="getPrint()"/>
												<input type="button"  value="Close" class="btn btn-sm btn-danger" onclick="window.close()"/>
											</div>
										</s:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
<script>
function getPrint() {
	document.getElementById('buttonDivId').style.display='none';
	window.print();
	document.getElementById('buttonDivId').style.display='block';
}
</script>
</body>
</html>
