<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.jqueryui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dataTables/css/dataTables.tableTools.css">
		<link href="${pageContext.request.contextPath}/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.jqueryui.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.responsive.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/dataTables.tableTools.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/jquery.dataTables.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dataTables/js/numeric-comma.js"></script>		
		--><script language="JavaScript">javascript:window.history.forward(1);</script>
		<script language="JavaScript">
				function stopRKey(evt) { 
				  var evt = (evt) ? evt : ((event) ? event : null); 
				  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
				} 
				document.onkeypress = stopRKey; 
		</script>
		<!--<script type="text/javascript">
  	jQuery(function ($) {
  		try {
			var data = $('.display').dataTable( {
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				responsive: true,
				"dom": 'T<"clear">lfrtip',
				"columnDefs": [ { "type": "numeric-comma", targets: 3 } ],
		        "tableTools": {
		            "sSwfPath": "${pageContext.request.contextPath}/dataTables/swf/copy_csv_xls_pdf.swf",
		            "aButtons": [ 
					"copy",
					"print", 
					{
						"sExtends": "collection",
						"sButtonText": "Export",
						//"mColumns": "visible",
						"aButtons": [{
							"sExtends": "csv",
							"sButtonText": "CSV",
							"sFileName": "report.csv",
							"mColumns": "visible"
							}, 
							{
							"sExtends": "xls",
							"sButtonText": "Excel",
							"sFileName": "report.xls",
							"mColumns": "visible"
							},
							{
							"sExtends": "pdf",
							"sButtonText": "PDF",
							"sPdfOrientation": "landscape",
							"sFileName": "report.pdf",
							"mColumns": "visible"
						}]
					}
					]
		        },
		        /*"footerCallback": function ( row, data, start, end, display ) {
				    var api = this.api(), data;
				    // Remove the formatting to get integer data for summation
				    var intVal = function ( i ) {
				        return typeof i === 'string' ?
				            i.replace(/[\$,]/g, '')*1 :
				            typeof i === 'number' ?
				                i : 0;
				    };
				    // Total over all pages
				    total = api
				        .column( 4 )
				        .data()
				        .reduce( function (a, b) {
				            return intVal(a) + intVal(b);
				        } );
				    // Total over this page
				    pageTotal = api
				        .column( 4, { page: 'current'} )
				        .data()
				        .reduce( function (a, b) {
				            return intVal(a) + intVal(b);
				        }, 0 );
				    // Update footer
				    $( api.column( 4 ).footer() ).html(
				       pageTotal
				    );
				}*/
				"footerCallback": function (nRow, aasData, iStart, iEnd, aiDisplay ) {                   
				    $($(nRow).children()).remove();                            
				    for(var i=0;i<aasData[0].length;i++){                       
				        var a= document.createElement('th');                   
				        $(a).html('');
				        $(a).css('text-align','right');                                         
				        $(nRow).append(a);                                     
				    }                                                          
				    var columnas=[4, 5, 6];     //the columns you wish to add             
				    for(var j in columnas) {                                   
				        var columnaActual= columnas[j];                        
				        var total=0;                                           
				        for(var i=iStart;i<iEnd;i++){                           
				            total=total+parseInt(aasData[aiDisplay[i]][columnaActual]);
				        }                                                              
				        $($(nRow).children().get(columnaActual)).html(total);          
				    }                                                          
				}
			});
		} catch(err){}
	} );
  	</script>
		--><SCRIPT type="text/javascript">
		//function printpage()
	//{
			//document.journal.action="${pageContext.request.contextPath}/getjournalPrintJournal.action";
			//document.journal.submit();
		//}
		function printpage(val,val1) {
	var URL = "${pageContext.request.contextPath}/getjournalPrintJournal.action?journalID="+val+"&openperiod="+val1;
	var windowName = "QuotatiionPrint";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 900;
	var h =	500;
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
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}
		</SCRIPT>
	</head>

	<body>
	<div class="table0" style="width: 90%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
					<div class="tablerow">
						<div style="padding:10px; background:#F8F8F8">
							<s:form id="journal" name="journal" method="post" action="" theme="simple">
							<div class="table2">								
								<div class="boxcontent">
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
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
								
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">									
									<div class="boxcontent">
										<s:iterator value="journalViewList" var="journalView" status="status">
										<br/><br/>
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
											</table>
											<br/><br/>
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
														<td ><s:label key="label.narration"/></td> 
														<td colspan="4"><s:property value='spcCurrencyList.get(#status.count-1).get("NARRATION")'/></td>
													</tr>
												</tbody>
																						
											</table>
										</s:iterator>
										<br/><br/>
									</div>
								</div>
								<div class="tablerow" align="center">
									<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="funBack();" />&nbsp;&nbsp;
									<input type="button" value="Print" id="print" class="btn btn-sm btn-primary" onclick="printpage('<s:property value="journalID"/>','<s:property value="openperiod"/>')" />&nbsp;&nbsp;&nbsp;
								</div>
							</div>
							<s:hidden name="journalID"></s:hidden>
							<s:hidden name="openperiod"></s:hidden>
							
						</div>
						</div>
						</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
		</div>
		
		<script type="text/javascript">
			function funsubmit() {
				document.getElementById("uploadProgress").style.display = "block";
			}
			
			function menuSelection(){
			  
				var URL='${pageContext.request.contextPath}/getMenuSelectionAdmin.do';  
		
				var windowName = "Selection";
				var width  = screen.availWidth;
				var height = screen.availHeight;
				var w = 720;
				var h = 550;
				var features =
					'width='          + w +
					',height='		  + h +
					',left='		  + ((width - w - 0) * .4)  +
					',top='			  + ((height - h - 0) * .4) +
					',directories=no' +
					',location=no'	  +
					',menubar=no'	  +
					',scrollbars=yes' +
					',status=yes'	  +
					',toolbar=no'	  +
					',resizable=false';
				var strOpen = window.open (URL, windowName, features);
				strOpen.focus();
			}
	function funBack(){
	try{
		document.journal.action="${pageContext.request.contextPath}/journalHomeJournal";
		document.journal.submit();
		}catch(err){alert(err)}
	}
		</script>
	</body>
</html>
