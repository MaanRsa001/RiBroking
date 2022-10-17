<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%> 
<!DOCTYPE html>
<html>
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">       				
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>		
	<script language="JavaScript" type="text/javascript">
		function printpage()
		{
			document.getElementById("back").style.display='none';
			document.getElementById("print").style.display='none';
			window.print();
			document.getElementById("back").style.display='inline';
			document.getElementById("print").style.display='inline';
		}
		function funBack(){
	try{
		document.journal.action="${pageContext.request.contextPath}/getjournalViewJournal";
		document.journal.submit();
		}catch(err){alert(err)}
	}
	</script>
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
            margin:1cm;
        }
     @media print { html, body { height: 99%; } }   
}
	
	
	</style>
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
				<div class="tablerow">
					<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
					<div class="tablerow">
						<div style="padding:10px; background:#F8F8F8">
							<s:form id="journal" name="journal" method="post" action="" theme="simple">
							<div class="panel panel-primary">
									<div class="panel-heading">
									<s:if test='"1".equals(journalID)'>
									<s:text name="label.pemiumjournal"></s:text>
									</s:if>
									<s:elseif test='"2".equals(journalID)'>
									<s:text name="label.claimjournal"></s:text>
									</s:elseif>
									<s:elseif test='"3".equals(journalID)'>
									<s:text name="label.treasuryjournal"></s:text>
									</s:elseif>
									<s:elseif test='"4".equals(journalID)'>
									<s:text name="label.pipelinejournal"></s:text>
									</s:elseif>
									<s:elseif test='"5".equals(journalID)'>
									<s:text name="label.outlossjournal"></s:text>
									</s:elseif>
									<s:elseif test='"6".equals(journalID)'>
									<s:text name="label.pipelineuprjournal"></s:text>
									</s:elseif>
									<s:elseif test='"7".equals(journalID)'>
									<s:text name="label.bookeduprjournal"></s:text>
									</s:elseif>
									</div>
									<div class="panel-body">
										<div class="tablerow" style="background-color: #fff;">									
									<div class="boxcontent">
									<s:iterator value="journalViewList" var="journalView" status="status">
									<div style="padding-top: 20px">
										<table width="100%" style="margin: 0 auto;" class="table table-bordered">
											<tr>
											<td>
												<s:label key="label.transactionDate"/>
												</td>
											<td>
												<s:property value='spcCurrencyList.get(#status.count-1).get("STARTDATE")'/>
											</td>
											<td>
												<s:label key="label.uwy"/>
											</td>
											<td>
											<s:property value='spcCurrencyList.get(#status.count-1).get("UWY")'/>
											</td>
											<td>
												<s:label key="label.currency"/>
											</td>
											<td>
											<s:property value='spcCurrencyList.get(#status.count-1).get("CURRENCY")'/>
											</td>
											<td>
												<s:label key="label.spc"/>
											</td>
											<td>
											<s:property value='spcCurrencyList.get(#status.count-1).get("SPC")'/>
											</td>
											<td>
												<s:label key="label.reference"/>
											</td>
											<td>
											<s:property value='spcCurrencyList.get(#status.count-1).get("REFERENCE")'/>
											</td>
											</tr>
											</table><br/>
											<table width="100%" style="margin: 0 auto;" class="footable display" >
											<thead>						
													<tr>
														<th width="50%"> <s:text name="Ledger" /> </th>
														<th width="12.5%"> <s:text name="Debit (OC)" /> </th>
														<th width="12.5%"> <s:text name="Credit (OC)" /> </th>
														<th width="12.5%"> <s:text name="Debit " />(<s:property value="shortname"/>) </th>
														<th width="12.5%"> <s:text name="Credit " />(<s:property value="shortname"/>) </th>
													</tr>			
												</thead>
												<tbody>
													<s:iterator value="#journalView" var="record">
														<tr>
															<td>${LEDGER}</td>
															<td align="right"><s:property value="getText('number.format',{DEBITOC})"/></td>
															<td align="right"><s:property value="getText('number.format',{CREDITOC})"/></td>
															<td align="right"><s:property value="getText('number.format',{DEBITUGX})"/></td>
															<td align="right"><s:property value="getText('number.format',{CREDITUGX})"/></td>
														</tr>
													</s:iterator>
													<tr>
														<td><s:label key="label.narration"/></td> 
														<td colspan="4"><s:property value='spcCurrencyList.get(#status.count-1).get("NARRATION")'/></td>
													</tr>
												</tbody>
																						
											</table>
									</div>
										</s:iterator>
									</div>
								</div>
								<br/><br/>
								<div class="tablerow" align="center">
									<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="funBack();" />&nbsp;&nbsp;
									<input type="button" value="Print" id="print" class="btn btn-sm btn-primary" onclick="printpage()" />&nbsp;&nbsp;&nbsp;
								</div>
									</div>
							</div>
							<s:hidden name="journalID"></s:hidden>
							<s:hidden name="openperiod"></s:hidden>
							</s:form>
						</div>
					</div>
				</div>
		</div>
	
</div>		
</body>
</html>
