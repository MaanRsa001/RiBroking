<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script language="JavaScript">
				function stopRKey(evt) { 
				  var evt = (evt) ? evt : ((event) ? event : null); 
				  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
				} 
				document.onkeypress = stopRKey; 
		</script>
	</head>

	<body>
		<div class="table0" style="width: 90%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
					<div class="tablerow">
						<div style="padding:10px; background:#F8F8F8">
							<s:form id="retroprocess" name="retroprocess" method="post" action="" theme="simple">
							<div class="table2">								
								<div class="tablerow">
									<div class="heading">
									</div>
								</div>
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">	
								<s:if test='"success".equals(mode)'>
								<div class="boxcontent" align="center">
								Retro Processing Successful<br><br>
								<input type="button"  value="Back" class="btn btn-sm btn-danger" onclick="getRetroBack();" />
								</div>
								</s:if>
								<s:else>
									<div class="boxcontent">
										<table width="75%" style="margin: 0 auto;">
										<tr>
												<td height="5" align="center"><span style="color:blue"><s:text name="label.period"  /> </span><br/></td>
											</tr>
											<tr>
												<td>
													<s:select list="#{'31/03':'31 March','30/06':'30 June','30/09':'30 September','31/12':'31 December'}" name="retro_period_Date" cssClass="inputBoxS" cssStyle="width: 50%; float:left;" headerKey=""  headerValue="---Select---"/>
													<s:select list="yearList"  name="retro_period_Year" listKey="YEAR" listValue="YEAR" cssClass="inputBoxS" cssStyle="width: 50%; float:left;" headerKey=""  headerValue="---Select---"/>
												</td>
											</tr>
											<tr>
												<td height="5"></td>
											</tr>
											<tr>
												<td align="center">
												<input type="button"  value="Full Download" class="btn btn-sm btn-info" onclick="getRetroDownload('full');" />&nbsp;&nbsp;&nbsp;
													<input type="button"  value="Group Download" class="btn btn-sm btn-info" onclick="getRetroDownload('shot');" />&nbsp;&nbsp;&nbsp;
													<input type="button"  value="Submit" class="btn btn-sm btn-success" onclick="getRetroSumbit()" />&nbsp;&nbsp;&nbsp;
												</td>
											</tr>										
										</table>
									</div>
									</s:else>								
								</div>
							</div>
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
	function getRetroSumbit(){
	try{
		document.retroprocess.action="${pageContext.request.contextPath}/retroInsertRetro";
		document.retroprocess.submit();
		}catch(err){alert(err)}
	}
	function getRetroDownload(val)
	{
		//form.downloadType.value='xls';
	    document.retroprocess.action="${pageContext.request.contextPath}/retroDownloadRetro.action?type="+val;
		document.retroprocess.submit();	
	}
	function getRetroBack(){
	try{
		document.retroprocess.action="${pageContext.request.contextPath}/retroprocessRetro";
		document.retroprocess.submit();
		}catch(err){alert(err)}
	}
		</script>
	</body>
</html>
