<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="org.apache.struts2.ServletActionContext"%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

	<title>Test Page</title>


	<!-- CSS  -->


	<link rel="stylesheet" type="text/css" href="css/select2.css"/>
	<link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<style type="text/css">
		.demoDiv {
			min-height: 115px;
		}
	</style>
	<script type="text/javascript">
        //function worker() {
        //postRequest("${pageContext.request.contextPath}/TimeOutHome.action?","");
        //}
       
 if (window.performance) {
  console.info("window.performance work's fine on this browser");
}

function preloadFun(){
  if (performance.navigation.type == 1) {
  alert("This page is reloaded");
    console.info( "This page is reloaded" );
  }else if (performance.navigation.type == 2) {
  alert("back forword");
    console.info( "This page is reloaded" );
  } else if (performance.navigation.type == 3) {
  alert("front forword");
    console.info( "This page is not reloaded");
  }
  }
  
	</script>

</head>
<body onunload="preloadFunc();">
<s:form id="" name="dash" method="post" action="" theme="simple">
	<div class="table0" style="padding: 5px 0px;">
		<div class="tablerow">
			<div class="pullLeft">
				<img alt="GRIPS" src="<%=request.getContextPath()%>/images/<s:property value='#session.HEADER_LOGO'/>" width="230" height="71"/>
			</div>
			<div class="pullRight">
				<font color="#AAAAAA" size=6  face="Courier New" style="font-weight: bolder;">
					<b style="text-transform: uppercase;"><s:text name="application.name" /> </b>
				</font> <s:property value="version"/>
			</div>
			<br class="clear"/>
		</div>
		<s:if test='"".equals(menuStatus) ||null==menuStatus'>
			<div class="tablerow" style="width: 50%">
				<div class="col s4">
					<div class="control-group">
						<label for="country" class="control-label">Navigate to:</label>
						<div class="controls">
							<s:select  list="%{#session.menuList}" name="menuName"  cssClass="select1 input-default" listKey="menukey" listValue="menu_list"  headerKey="~~~~welcomeHome.action~~~~1" headerValue="" cssStyle="width: 85%;" />
							<input type="button"  size="1"  value="Go" onclick="goMenu()" class="pullRight;btn btn-sm btn-success" style="width: 50px;height: 30px" />
						</div>
					</div>
				</div>
			</div>
		</s:if>
		<div class="tablerow">
			<div class="pullRight">
					<%-- <div id="hms" class="cont">00:30:00</div>
                    <input type="submit" id="btnSubmit" name="btnSubmit" value="Execute Test"/> --%>
				<s:a href="#" onclick="destroyPopUps();dockNavigation('', '', '', '', '', '', '', 'welcomeHome.action');" cssClass="loginMenu"><s:text name="label.home"/></s:a> |
				<span id="countdown" class="timer"></span> |
				<s:a  href="#" onclick="worker();worker();timer('extend');" cssClass="loginMenu">Extend</s:a>  |
				<s:a  href="#" onclick="destroyPopUps();gologout();" cssClass="loginMenu"><s:text name="label.logout"/></s:a> |
				<%--<s:a href="#" onclick="callManual();" cssClass="loginMenu">User Manual</s:a>|--%>
				Welcome, <s:property value="%{#session.UserName}" />
			</div>
			<br class="clear"/>
		</div>
	</div>
	<s:hidden name="mode" id="menuMode1"/>
	<s:hidden name="proposalNo" id="menuproposalNo1"/>
	<s:hidden name="proposal_no" id="menuproposal_no1"/>
	<s:hidden name="lastAccessedTime" id="lastAccessedTime"/>
</s:form>
<!--  Scripts-->
<script type="text/javascript">
    $('.select1').select2({ });
</script>
<script type="text/javascript">
 if(window.opener == undefined){
     window.location.href = "${pageContext.request.contextPath}/Loginhomepage.do";  
      }
var mtbChildWin = new Array();
var mtbWinCound = 0;
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }
    getExpiryDate('<s:property value="expiryDate"/>');
    function getExpiryDate(date){
        if(''!='<s:property value="expiryCount"/>' && null!='<s:property value="expiryCount"/>'&& 15>=parseInt(<s:property value="expiryCount"/>)){
            confirm("Your license is expiring on "+date+" .  Kindly renew your license before the expiry date to continue using the system");
        }
    }
    worker();
    timer('new');
    function goMenu(){
        document.dash.action='${pageContext.request.contextPath}/menuNavigationMenu.action';
        document.dash.submit();
    }

    function gologout(){
        var mode=document.getElementById("menuMode1").value;
        var proposal1=document.getElementById("menuproposalNo1").value;
        if(proposal1==null || proposal1=='' ){
            var proposal=document.getElementById("menuproposal_no1").value;
        }else{
            var proposal=document.getElementById("menuproposalNo1").value;
        }
        document.dash.proposalNo.value=proposal;
        if(mode=='endorsment'){
            answer=confirm("The changes have not been captured in the system.  Do you wish to exit without saving?  If you wish to update the record, kindly click on cancel and choose “Yes” under the Endorsement finalised field.");
            if(answer){
                document.dash.action='${pageContext.request.contextPath}/Loginout.action?menumodeStatus='+mode;
                document.dash.submit();
            }
            else{
                return false;
            }
        }
        else{
            document.dash.action='${pageContext.request.contextPath}/Loginout.action';
            document.dash.submit();
        }
    }

    function sessionTimeOut(){
        document.dash.action='${pageContext.request.contextPath}/sessionTimeOutHome.action';
        document.dash.submit();
    }
function networkChange(){
 if(!navigator.onLine){
	  alert("A network change has been detected.   Kindly relogin.");
	  window.location.href = "${pageContext.request.contextPath}/Loginout.do";
	}
 }
 
function timer(id) { 
networkChange()
        var val =  document.getElementById('countdown').innerHTML;
        var time =  <%=session.getLastAccessedTime()%>;
        time=new Date(time);
        var ti = new Date();
        var hourDiff= ti.getTime()-time.getTime();
        var mydate=new Date(hourDiff);
        var sec = mydate.getUTCSeconds();
        var min = mydate.getUTCMinutes();
        var minsec = (min*60)+sec;
        if(seconds==1800 ){
            seconds = seconds-sec;
        }
        if("extend"==id && val=="Completed"){
            var countdownTimer = setInterval('timer("new")', 1000);
            seconds=1800;
        }
        if("extend"==id){
            seconds=1800;
        }
        var days        = Math.floor(seconds/24/60/60);
        var hoursLeft   = Math.floor((seconds) - (days*86400));
        var hours       = Math.floor(hoursLeft/3600);
        var minutesLeft = Math.floor((hoursLeft) - (hours*3600));
        var minutes     = Math.floor(minutesLeft/60);
        var remainingSeconds = seconds % 60;
        if (remainingSeconds < 10) {
            remainingSeconds = "0" + remainingSeconds;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (hours < 10) {
            hours = "0" + hours;
        }
        document.getElementById('countdown').innerHTML = hours + ":" + minutes + ":" + remainingSeconds;
        if (seconds == 0) {
            clearInterval(countdownTimer);
            document.getElementById('countdown').innerHTML = "Completed";
        } else {
            seconds--;
        }
    }
    var countdownTimer = setInterval('timer("new")', 1000);

function callManual() {
	var URL = "${pageContext.request.contextPath}/useManualpdfJournal.action";
	var windowName = "userManual";
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
</script>

</body>
</html>
