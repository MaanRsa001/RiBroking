<%-- 
    Author     : Raja.K
    Document   : Common Login Template
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title><s:text name="application.name" /></title>
        <link rel='shortcut icon' type='image/x-icon' href='${pageContext.request.contextPath}/images/title-logo.png' />
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
         <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
        <script>javascript:window.history.forward(1); </script>
        <style>
            #image_style a{display:block;color:transparent;} 
            #image_style a:hover{background-position:left bottom;}
            body { display : none;} 
        </style>             
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
       	<script language="JavaScript">javascript:window.history.forward(1);</script>
        <script type="text/javascript">
             function callValidLogin(val){
             	document.form1.action="${pageContext.request.contextPath}/LoginCheck.do";
                document.form1.submit();
             }
            function callSubmit(val){
            	if(val=='login'){
                	document.form1.action="${pageContext.request.contextPath}/Loginsubmit.do";
                }
                document.form1.submit();
            }
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
        </script>
    </head>
    <body style="margin: 0px auto;background-color: #999999;" >
        <s:form name="form1" method="post" action="" theme="simple" >
            <s:hidden name="loginCount" id="loginCount" />
            <table width="834" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td bgcolor="#333333">
                        <table  bgcolor="#FFFFFF" width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr bgcolor="#00447A">
                                <td height="25" colspan="2">
                                    <div align="center">
                                        <p class="Logon"><br></p>
                                        <p class="Logon">&nbsp;</p>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="left">
                                <img src="${pageContext.request.contextPath}/images/TQR_Logo.png" border="0" width="230" height="71">
                                </td>
                                <td class="textbold" colspan="2" align="center">
                                    <h2><font color = "#004094" style="font-family: Arial; margin: 0px; font-weight:bold; font-size: 50px;"><s:text name="application.name" /></font></h2>
                                </td>
                            </tr>
                            <tr>
                                <td width="455" height="377" bgcolor="#00447A">
                                    &nbsp;
                                </td>
                                <td width="379" valign="bottom" background="${pageContext.request.contextPath}/images/log_bg.jpg">
                                    <table border="0" align="center" cellpadding="4" cellspacing="0" class="table-border-login" style="font-family: Arial;">
                                        <s:if test="hasActionErrors()">
                                            <tr> 
                                                <td colspan="2" style="color:red">
                                                    <s:actionerror/>
                                                </td>
                                            </tr>
                                        </s:if>
                                        <tr>
                                            <td height="20" colspan="2" align="center" style="font-size: 12px; color:#004094;">
                                                <s:text name="Registered User" />
                                            </td>
                                        </tr>

                                        <tr> 
                                            <td valign="middle" style="font-size: 12px; color:#004094;"><s:text name="User Name" /></td>
                                            <td valign="top"> 
                                                <s:textfield name="loginId" id="loginId" cssClass="form" size="25" cssStyle="width:90%;"/>
                                            </td>
                                        </tr>
                                        <tr> 
                                            <td valign="middle" style="font-size: 12px; color:#004094;"><s:text name="Password" /></td>
                                            <td valign="top"><s:password name="pwd" id="pwd" cssClass="form" size="25"  cssStyle="width:90%;"/></td>
                                        </tr>
                                        <tr> 
                                            <td height="9"></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                                <center>
                                                	 <input type="submit" name="submit" value="Login" onclick="callSubmit('login');" class="loginButton" >
                                                	<!--<s:submit value="Login" cssClass="btn" action="Loginsubmit" />
                                                	--><!-- 
                                                	<s:submit value="Login" action="submitLogin" cssClass="btn" />
                                                	-->                                                    
                                                </center>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                                <center>
                                                  	<a href="${pageContext.request.contextPath}/Loginoption.action?status=changePwd" onclick="changePass()" style="font-size: 15px;color: #0000FF; font-family: Arial; ">Change Password?</a>
                                                </center>
                                            </td>
                                        </tr>
                                         <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                                <center>
                                                   <a href="${pageContext.request.contextPath}/Loginoption.action?status=forgotPwd" onclick="forgotPass()" style="font-size: 15px;color: #0000FF; font-family: Arial;" >Forgot Password?</a>
                                                </center>
                                            </td>
                                        </tr>
                                       <%-- <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                                <center>
                                                   <a href="#" onclick="callManual();" style="font-size: 15px;color: #0000FF; font-family: Arial;" >User Manual</a>
                                                </center>
                                            </td>
                                        </tr>--%> 
                        			</table>
                    			</td>
               				 </tr>
           			 	</table>
           			</td>
           		</tr>
           </table>
        </s:form>
        <script>
            if (self == top) {
                var theBody = document.getElementsByTagName('body')[0]
                theBody.style.display = "block"
            } else {
                top.location = self.location
            } 
            if(window.opener == undefined){
           window.location.href = "${pageContext.request.contextPath}/Loginhomepage.do";  
            }
        </script>
    </body>
</html>