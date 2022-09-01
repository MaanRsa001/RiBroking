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
	        font-size: 12px;
	    }
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
<s:form name="claimView" id="claimView" action="" method="post" theme="simple">
<div id="loading" style="width:100%;display:none"  >
 	<img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif" />
 </div>
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
										<div class="textfield33" id="paymentDivId">
											<div class="text">
												<s:text name="Claim Payment Numbers:" />
											</div>
											<div class="tbox">
												<s:select list="paymentNoList" cssClass="inputBoxS" name="paymentNo" id="paymentNo" multiple="true" listKey="CLAIM_PAYMENT_NO" listValue="CLAIM_PAYMENT_NO" headerKey="0" headerValue="--SELECT--"  ></s:select>
												&nbsp;&nbsp;
												<input type="button" value="Search" onclick="viewAuthInfo()" class="btn btn-sm btn-info">
											</div>
											</div>
									</div>
										<br/>
									<div class="row">
										<div id="authDivId"></div>
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
<s:hidden name="proposal_No" id="proposal_No" />
<s:hidden name="contarctno" id="contarctno" />
<s:hidden name="layerNo" id="layerNo" />
<s:hidden name="currecny" id="currecny" />
<s:hidden name="branchCode" id="branchCode" />
<input type="hidden" name="myObjecttype"  id ="paymentNoId"/>  

</s:form>
<script>

function viewAuthInfo(){
	var paymentNo = document.getElementById('paymentNoId').value;
	var proposalNo = document.getElementById('proposal_No').value;
	var contractNo =document.getElementById('contarctno').value;
	var layerNo =document.getElementById('layerNo').value;
	var currency =document.getElementById('currecny').value;
	var branchCode =document.getElementById('branchCode').value;
	var URL = '${pageContext.request.contextPath}/viewAuthAjaxClaim?proposal_No='+proposalNo+'&contarctno='+contractNo+'&layerNo='+layerNo+'&currecny='+currency+'&paymentNo='+paymentNo;
	//alert(URL);
	var id = 'authDivId';
	postRequestID(URL , id);
}


function postRequestID(strUrl, id) {
	$.ajax( {
		url : strUrl,
		error : function() {
			$('#' + id).html('<p>An error has occurred in jquery Ajax</p>');
		},
		success : function(data) {
			$('#' + id).html(data);
		},
		beforeSend : function() {
			$('#loading').show();
			$('.ajaxLoader').show();
		},
		complete : function() {
			$('#loading').hide();
			$('.ajaxLoader').hide();
		},
		type : 'POST'
	});
}

function getPrint() {
	document.getElementById('paymentDivId').style.display='none';
	document.getElementById('buttonDivId').style.display='none';
	document.getElementById('statBtn').style.display='none';
	window.print();
	document.getElementById('paymentDivId').style.display='block';	
	document.getElementById('buttonDivId').style.display='block';
	document.getElementById('statBtn').style.display='block';
}
    $('#paymentNo').change(function(){
      //brand new array each time
    var foo = [];
      //fills the array
    foo = $("option:selected", this).map(function(){ return $(this).val() }).get();
      //fills input with vals, as strings of course
    $("input[name='myObjecttype']").val(foo);
      //logs an array, if needed
    console.log($("input[name='myObjecttype']").val().split(','));
});  

	   $(document).ready(function() {
        $('#paymentNo').multiselect({
            onDropdownShow: function(event) {
            }
        });
    });
    
    
    

function ViewClaimAuthDetail(){
	var paymentNo = document.getElementById('paymentNoId').value;
	var proposalNo = document.getElementById('proposal_No').value;
	var contractNo =document.getElementById('contarctno').value;
	var layerNo =document.getElementById('layerNo').value;
	var currency =document.getElementById('currecny').value;
	var branchCode =document.getElementById('branchCode').value;
	var URL = '${pageContext.request.contextPath}/viewAuthAjaxClaim?mode=detail&proposal_No='+proposalNo+'&contarctno='+contractNo+'&layerNo='+layerNo+'&currecny='+currency+'&paymentNo='+paymentNo;
		var windowName = "Claim Detail";
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

function funDownload(){
	document.claimView.action='${pageContext.request.contextPath}/getRlistReports.do?start_date=01/01/2001&end_date=31/12/2100&mode=ContractDown&reportType=xml&docType=ALL&uwYear=-1&cedingId=-1&brokerId=-1&showAllFields=N&typeId=26'; 
	document.claimView.submit();
}

	function changeCheckBox(val){
		if(val==1){
			document.getElementById('no').checked = false;
			document.getElementById('partial').checked = false;
		}else if(val ==2){
			document.getElementById('yes').checked = false;
			document.getElementById('partial').checked = false;
		}else{
			document.getElementById('yes').checked = false;
			document.getElementById('no').checked = false;
		}
	}
</script>
</body>
</html>