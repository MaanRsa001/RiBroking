<%-- 
    Author     : Raja.K
    Document   : Common Login Template
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="application.name"/></title>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">javascript:window.history.forward(1);</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css"/>       				
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>		    
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
	<script type="text/javascript">
		function reSetValue(){
	  	document.getElementById("userId").value ="";
	  	document.getElementById("userId").focus();
	}
	function reSetValue2(){
		document.getElementById("newpwd").value ="";
	  	document.getElementById("newpwd").focus();
	}
		 function callSubmit(val){
           	if(val=='back'){
           		document.changepwd.action="${pageContext.request.contextPath}/Loginpage.do";
           		document.getElementById("status").value="";
           	}
           	else if(val=='submit'){
           		document.changepwd.action="${pageContext.request.contextPath}/LoginpwdChange.do";
           	}
           	else if(val=='changePwd'){
           		document.changepwd.action="${pageContext.request.contextPath}/Loginsubmit.do";
           	}
              document.changepwd.submit();
     	}
     	
     	function passwordStrength(password){
			var desc = new Array();
			desc[0] = "Very Weak";
			desc[1] = "Weak";
			desc[2] = "Better";
			desc[3] = "Medium";
			desc[4] = "Strong";
			desc[5] = "Strongest";
			var score   = 0;
			if (password.length > 6) score++;
			if ( ( password.match(/[a-z]/) ) && ( password.match(/[A-Z]/)) ) score++;
			if ( password.match(/\d+/)) score++;
			if ( password.match(/.[@,#,$,%]/))score++;
			if ( (password.length > 10) && (password.match(/.[@,#,$,%]/)) && (password.match(/[a-z]/) ) && (password.match(/[A-Z]/)) && (password.match(/\d+/)))score++;
			document.getElementById("passwordDescription").innerHTML = desc[score];
			document.getElementById("passwordStrength").className = "strength" + score;
		}
	</script>
</head>

<body>
<div class="panel panel-primary">
		<div class="panel-heading">
			<s:text name="application.name"/>
		</div>
	<div class="panel-body" >
	<div class="logonBg">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:text name="pwd.change"/>
					</div>
					<div class="panel-body" >
						<br/>
						<div style="color: red;">
							<s:actionerror/>
						</div>
						<s:form id="changepwd" name="changepwd" method="post" action="" theme="simple">							
							<div class="form-group">
								<div class="tableWidth" style="color: #000000;">
									<s:if test='"success"==status'>
										<div class="row">
											<div class="col-xs-12" align="center">
												<h5 class="label label-success"> <s:text name="pwd.change.success"/> </h5>
											</div>
										</div>
									</s:if>
									<s:elseif test='"changePwd"==status'>
										<div class="textfield">
											<div class="text">
												&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="user.name" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield name="loginId" id="userId" cssClass="inputBox" placeholder="Login Name"  maxlength="60" cssStyle="border: 1px solid #c2c2c2;" />
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												<s:text name="pass.word" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:password name="pwd" id="pwd" cssClass="inputBox" placeholder="Password" maxlength="20" cssStyle="border: 1px solid #c2c2c2;"/>
											</div>
										</div>
									</s:elseif>
									<s:else>
									<div class="textfield">
											<div class="text">
												<s:text name="user.name" /> 
											</div>
											<div class="tbox">
												: <b><s:property value="loginId"/></b>
													<s:hidden name="loginId"/>
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												<s:text name="new.pwd" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:password name="newpwd" id="newpwd" cssClass="inputBox" maxlength="20" onkeyup="passwordStrength(this.value)" cssStyle="border: 1px solid #c2c2c2;"/>
											</div>
										</div>
										
										<div class="row">
											<div class="col-xs-6" style="color: red;">
												(<s:property value="pwdMsg"/>)												
											</div>
										</div>
										
										<div class="row">
											<div class="col-xs-6" align="center">
												<div id="passwordDescription">Password not entered</div> <div id="passwordStrength" class="strength0"></div>												
											</div>
										</div>
										<div class="textfield">
											<div class="text">
												<s:text name="confirm.pwd" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:password name="repwd" id="repwd" cssClass="inputBox" maxlength="20" cssStyle="border: 1px solid #c2c2c2;" />
											</div>
										</div>
										
									</s:else>
									<s:hidden name="status" id="status"/>
									<br class="clear" />
									
								</div>
								<br class="clear"/>
							</div>
							
							
						</s:form>				
					</div>
				</div>
			</div>			
		</div>
		<br class="clear" />
	</div>
	<div class="row">
		<s:if test='"success"==status'>
			<div class="col-xs-12" align="center">
				<s:hidden name="pwdMsg"/>
                <input  type="button" name="Submit1" style="cursor:pointer;" class="btn btn-sm btn-danger" value="Back" onclick="callSubmit('back')"/>
            </div>
		</s:if>
		<s:else>
			<div class="boxcontent" align="center">
					<input  type="button" name="Submit1" style="cursor:pointer;" class="btn btn-sm btn-danger" value=" Cancel " onclick="callSubmit('back')"/>&nbsp;&nbsp;&nbsp;
				<s:if test='"changePwd"==status'>
					<input type="button"  name="Submit2" style="cursor:pointer;" value=" Reset " class="btn btn-sm btn-warning" onclick="reSetValue();" />&nbsp;&nbsp;&nbsp;
	                <input type="button" name="Submit1" style="cursor:pointer;" class="btn btn-sm btn-success" value="Submit" onclick="callSubmit('changePwd')"/>	                              			
	            </s:if>
	            <s:else>
					<input type="button" name="Submit2" style="cursor:pointer;" value=" Reset " class="btn btn-sm btn-warning" onclick="reSetValue2();"/> &nbsp;&nbsp;&nbsp;
	              	<input type="button" name="Submit1" style="cursor:pointer;" class="btn btn-sm btn-success" value="Submit" onclick="callSubmit('submit')"/>	                              			
	          </s:else>
				<s:hidden name="pwdMsg"/>
			</div>
		</s:else>
	</div>
	<br class="clear"/>	
</div>

</div>

<%-- 
<table width="900" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td height="5"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
      <tr>
        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			<s:form id="changepwd" name="changepwd" method="post" action="" theme="simple">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
              	<td bgcolor="#FFFFFF"><h1><s:text name="application.name"/></h1></td>
              </tr>
              <tr><td>&nbsp;</td> </tr>
              <tr><td>&nbsp;</td> </tr>
              <tr>
                <td bgcolor="#FFFFFF">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
                		<tr>
							<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
                		</tr>
               			<tr>
	                      <td class="heading"><s:text name="pwd.change"/></td>
	                    </tr>
                		<s:if test='"success"==status'>
                			<tr>
                				<td>
		                			<table width="400px" border="0" align="center" cellpadding="0" cellspacing="0" style="border: 1px solid #808080; background-color: #F0F0F0;">                			
			                			<tr><td height="100px;">&nbsp;</td></tr>
						                <tr>
						                   <td align="center" style="padding-right:20px;"> <s:text name="pwd.change.success"/> </td>
						                </tr>
						                <tr><td height="100px;">&nbsp;</td></tr>
					                </table>
			                	</td>
			               </tr>
                		</s:if>
                		<s:elseif test='"changePwd"==status'>
		                    <tr><td style="padding:10px; background:#F8F8F8">&nbsp;</td></tr>
		                    <tr>	                                                 
		                      <td  bgcolor="#FFFFFF">
		                      	<table width="" border="0" align="center" cellpadding="0" cellspacing="0" style="border: 1px solid #808080; background-color: #F0F0F0;">
		                      	  <tr><td colspan="2">&nbsp;</td></tr>
		                      	  <tr><td colspan="2" class="headingtext"><s:text name="pwd.change" /></td></tr>
			                      <tr><td colspan="2">&nbsp;</td></tr>
		                          <tr align="center">
		                          		
		                          		<td width="200px" align="right" style="padding-right: 10px;"><s:text name="user.name" /> <font color="red">*</font> : </td>
	                            		
	                            		<td width="300px" align="left" style="padding-left: 10px;"><s:textfield name="loginId" id="userId" cssClass="input"/></td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr align="center">
		                          		
		                          		<td width="200px" align="right" style="padding-right: 10px;"><s:text name="pass.word" /> <font color="red">*</font> : </td>
	                            		
	                            		<td width="300px" align="left" style="padding-left: 10px;"><s:password name="pwd" id="pwd" cssClass="input" maxlength="20"/></td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr><td>&nbsp;</td></tr>
			                     </table>
			                   </td>
			                </tr>
                		</s:elseif>
                		<s:else>
		                    <tr><td style="padding:10px; background:#F8F8F8">&nbsp;</td></tr>
		                    <tr>	                                                 
		                      <td  bgcolor="#FFFFFF">
		                      	<table width="" border="0" align="center" cellpadding="0" cellspacing="0" style="border: 1px solid #808080; background-color: #F0F0F0;">
		                      	  <tr><td>&nbsp;</td></tr>
		                      	  <tr><td>&nbsp;</td></tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr align="center">
		                          		<td width="200px" align="right" style="padding-right: 10px;"><s:text name="user.name" /> : </td>
	                            		<td width="200px" align="left" style="padding-left: 10px;"><s:property value="loginId"/></td><s:hidden name="loginId"/>
	                            		<td width="350px">&nbsp;</td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr align="center">
		                          		<td width="200px" align="right" style="padding-right: 10px;"><s:text name="new.pwd" /> <font color="red">*</font> : </td>
	                            		<td width="200px" align="left" style="padding-left: 10px;"><s:password name="newpwd" id="newpwd" cssClass="input" maxlength="20" onkeyup="passwordStrength(this.value)"/></td>
			                      		<td width="350px">(<s:property value="pwdMsg"/>)</td>
			                      </tr>
								  <tr align="center">
		                          		<td > &nbsp;</td>
	                            		<td colspan="2" align="left" style="padding-left: 10px;"><div id="passwordDescription">Password not entered</div><div id="passwordStrength" class="strength0"></div></td>
			                      		
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr align="center">
		                          		<td width="200px" align="right" style="padding-right: 10px;"><s:text name="confirm.pwd" /> <font color="red">*</font> : </td>
	                            		<td width="200px" align="left" style="padding-left: 10px;"><s:password name="repwd" id="repwd" cssClass="input" maxlength="20"/></td>
	                            		<td width="350px">&nbsp;</td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr><td>&nbsp;</td></tr>
			                     </table>
			                   </td>
			                </tr>
			             </s:else>
	                     <s:hidden name="status" id="status"/>
		              </table>
		            </td>
                   </tr>
                   <tr>
	                  <td>
                   		<table align="center">
	                   		<s:if test='"success"==status'>
	                   			<tr>
	                            	<td align="center">
	                            		<s:hidden name="pwdMsg"/>
	                            		<s:submit name="Submit1" cssStyle="cursor:pointer;" cssClass="btn" value="Back" onclick="callSubmit('back')"/>	                              		
	                            	</td>
	                       	 	</tr>
	                   		</s:if>
	                   		<s:else>
	                   			<tr>
	                            	<td align="center">
	                            		<s:hidden name="pwdMsg"/>
	                            			<input  type="button" name="Submit1" style="cursor:pointer;" class="btn" value=" Cancel " onclick="callSubmit('back')"/>&nbsp;&nbsp;&nbsp;	                            			
	                              			<s:reset name="Submit2" cssStyle="cursor:pointer;" value=" Reset " cssClass="btn"/>&nbsp;&nbsp;&nbsp;
	                              		<s:if test='"changePwd"==status'>
	                              			<input type="button" name="Submit1" style="cursor:pointer;" class="btn" value="Submit" onclick="callSubmit('changePwd')"/>	                              			
	                              		</s:if>
	                              		<s:else>
	                              			<input type="button" name="Submit1" style="cursor:pointer;" class="btn" value="Submit" onclick="callSubmit('submit')"/>	                              			
	                            		</s:else>
	                            	</td>
	                       	 	</tr>
	                       	 </s:else>
                   		</table>
	                   	</td>
                    </tr>
        		 </table>
    		</s:form>
    	</td>
    	</tr>
    </table>
    </td></tr>
    </table>--%>
</body>
</html>