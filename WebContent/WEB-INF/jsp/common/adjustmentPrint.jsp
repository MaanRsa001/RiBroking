<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">       				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
		<style type="text/css">
	 @media print
			{    
			    .no-print, .no-print *
			    {
			        display: none !important;
			    }
			}
		.footable > tbody > tr > td {
	  padding: 1px;
	}
	@page 
        {
            size: legal;   /* auto is the current printer page size */
            margin:0cm;
        }
     @media print { html, body { height: 99%; } }   
}
	
	
	</style>
	</head>

	<body onload="divdis();">
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
				<s:form id="PaymentForm" name="PaymentForm" theme="simple" action="" method="post">
					<div class="table1"
						style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
					<%-- 	<div class="tablerow" align="center">
							<s:if test='"PT".equals(allocType)'>
								<s:text name="heading.payment" />
							</s:if>
							<s:else>
								<s:text name="heading.Receipt" />
							</s:else>
						</div>
						<s:set name="brokerid" value="%{brokerid==null?'':brokerid}" />
						<div class="tablerow">
							<span style="color: red;"><s:actionerror /> </span>
						</div>--%>
						<s:if test='"print".equals(mode)'>
                      <div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Adjustment Details" />
									</div>									
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedList" pagesize="" requestURI="" class="footable" uid="row" id="record2" >
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
												<display:column style="text-align:left;" title="Status" property="status" />
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
                            	<input type="button" value="Back"	id="back" class="btn btn-sm btn-danger" onclick="back5(this.form);" />
                            	<input type="button" value="Print" id="print" class="btn btn-sm btn-primary"	onClick="printpage()"/>	
                            	<s:hidden name="serialNo" />
                            </div>
                      </s:if>
					</div>
				</s:form>
			</div>
		</div>
		<script type="text/javascript">
            var form = $('form[name="PaymentForm"]');
	function back5() {
		//document.PaymentForm.serial_no.value="";
		document.PaymentForm.action="${pageContext.request.contextPath}/adjustmentViewAdjustment.action?mode=view";
		document.PaymentForm.submit();	
	}
	function printpage()
		{
			document.getElementById("back").style.display='none';
			document.getElementById("print").style.display='none';
			window.print();
			document.getElementById("back").style.display='inline';
			document.getElementById("print").style.display='inline';
		}
</script>
	</body>
</html>
