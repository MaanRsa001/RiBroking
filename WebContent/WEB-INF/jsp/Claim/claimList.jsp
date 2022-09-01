<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%> 

<!DOCTYPE HTML>
<html>
<head>
	<style>
		#tooltip{
			position:absolute;
			border:1px solid #333;
			background:#f7f5d1;
			padding:2px 5px;
			color:#333;
			display:none;
		}  
	</style>	
</head>  
<body>
<s:set name="userRights" value='%{MenuRights == ""?"":MenuRights}' />
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="claim" name="claim" action="" theme="simple">
					<div class="table1">
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Heading.ClaimDetails" />
										<s:if test='#session.MenuRights.indexOf("CN")!=-1 && !"Y".equals(ceaseStatus)'>			
										<span class="pullRight">
											<input type="button" value="New Claim" class="btn btn-sm btn-warning" onClick="NewCliam()" />											
										</span>
										</s:if>
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="heading.contractDetails" />
												</div>
												<div class="panel-body">
												<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.PolicyContractNo" /> 
														</div>
														<div class="tbox">
															<s:property value="policy_Contract_No"/>
														</div>
													</div>
													<s:if test='"3".equals(#session.mfrid)'>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.layer" />
														</div>
														<div class="tbox">
															<s:property value="layerNo"/>
														</div>
													</div>
													</s:if>
													<div class="textfield" >
														<div class="text txtB">
														<s:if test='"3".equals(#session.mfrid)'>
															<s:text name="Claim.Subclass" />
														</s:if>
														<s:else>
														<s:text name="Claim.class" />
														</s:else>
														</div>
														<div class="tbox">
															<s:property value="departmentName"/>
														</div>
													</div>
													<div class="textfield"  >
														<div class="text txtB">
															<s:text name="Claim.SignedShare" />
														</div>
														<div class="tbox">
															<s:property value="signed_Share"/>
														</div>
													</div>
													<div class="textfield">
														<div class="text txtB">
															<s:text name="Claim.From" />
														</div>
														<div class="tbox">
															<s:property value="from"/>
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.To" />
														</div>
														<div class="tbox">
															<s:property value="to"/>
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:if test='"1".equals(#session.mfrid)'>
															<s:text name="Facultative.SumInsOurShare" />
															</s:if>
															<s:if test='"2".equals(#session.mfrid)'>
															<s:text name="claim.treatyLimitOSOC" /> 
															</s:if>
															<s:if test='"3".equals(#session.mfrid)'>
															<s:text name="claim.coverLimitOSOC" />(Main Class)
															</s:if>
														</div>
														<div class="tbox">
															<s:property value="sumInsOSOC"/>
															<s:hidden name="sumInsOSOC" />
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:if test='"1".equals(#session.mfrid)'>
															<s:text name="Facultative.SumInsOurShareDC" /><s:property value="shortname" />
															</s:if>
															<s:if test='"2".equals(#session.mfrid)'> 
															<s:text name="claim.treatyLimitOSDC" /> <s:property value="shortname" />
															</s:if>
															<s:if test='"3".equals(#session.mfrid)'>
															<s:text name="claim.coverLimitOSDC" /><s:property value="shortname" />(Main Class)
															</s:if>
														</div>
														<div class="tbox">
															<s:property value="sumInsOSDC"/>
															<s:hidden name="sumInsOSDC" />
														</div>
													</div>
											<%-- 	<s:else>
												
												
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.PolicyContractNo" /> 
														</div>
														<div class="tbox">
															<s:property value="policy_Contract_No"/>
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.Subclass" />
														</div>
														<div class="tbox">
															<s:property value="departmentName"/>
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Ceding.CompanyName" />
														</div>
														<div class="tbox">
															<s:property value="ceding_company_Name"/>
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.BrokerName" />
														</div>
														<div class="tbox">
															<s:property value="broker_Name"/>
														</div>
													</div>
													<s:if test='"1".equals(#session.mfrid)'>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.OriginalInsured" />
														</div>
														<div class="tbox">
															<s:property value="insuredName"/>
														</div>
													</div>
													</s:if>
													<div class="textfield">
														<div class="text txtB">
															<s:text name="Claim.From" />
														</div>
														<div class="tbox">
															<s:property value="from"/>
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.To" />
														</div>
														<div class="tbox">
															<s:property value="to"/>
														</div>
													</div>
													<s:if test='!"1".equals(#session.mfrid)'>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.TreatyName" />
														</div>
														<div class="tbox">
															<s:property value="treaty_Name"/>
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.NatureofCoverage" />
														</div>
														<div class="tbox">
															<s:property value="nature_of_Coverage"/>
														</div>
													</div>
													</s:if>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="Claim.Retention%" />
														</div>
														<div class="tbox">
															<s:property value="retention"/>
														</div>
													</div>
													<div class="textfield"  >
														<div class="text txtB">
															<s:text name="Claim.SignedShare" />
														</div>
														<div class="tbox">
															<s:property value="signed_Share"/>
														</div>
													</div>	
													<div class="textfield" >
														<div class="text txtB">
															<s:if test='"1".equals(#session.mfrid)'>
															<s:text name="Facultative.SumInsOurShare" />
															</s:if>
															<s:if test='"2".equals(#session.mfrid)'>
															<s:text name="claim.treatyLimitOSOC" /> 
															</s:if>
															<s:if test='"3".equals(#session.mfrid)'>
															<s:text name="claim.coverLimitOSOC" /> 
															</s:if>
														</div>
														<div class="tbox">
															<s:property value="sumInsOSOC"/>
															<s:hidden name="sumInsOSOC" />
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:if test='"1".equals(#session.mfrid)'>
															<s:text name="Facultative.SumInsOurShareDC" /><s:property value="shortname" />
															</s:if>
															<s:if test='"2".equals(#session.mfrid)'> 
															<s:text name="claim.treatyLimitOSDC" /> <s:property value="shortname" />
															</s:if>
															<s:if test='"3".equals(#session.mfrid)'>
															<s:text name="claim.coverLimitOSDC" /><s:property value="shortname" />
															</s:if>
														</div>
														<div class="tbox">
															<s:property value="sumInsOSDC"/>
															<s:hidden name="sumInsOSDC" />
														</div>
													</div>
													<s:if test='"2".equals(#session.mfrid)'>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="claim.cashcallLimit-OurShare" />
														</div>
														<div class="tbox">
															<s:property value="cashLossOSOC"/>
															<s:hidden name="cashLossOSOC" />
														</div>
													</div>
													<div class="textfield" >
														<div class="text txtB">
															<s:text name="claim.cashcallLimit-OurShare-USD" /> <s:property value="shortname" />
														</div>
														<div class="tbox">
															<s:property value="cashLossOSDC"/>
															<s:hidden name="cashLossOSDC" />
														</div>
													</div>
													</s:if>	
													</s:else>	--%>											
													<br class="clear" />
												</div>
											</div>											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">			
									<div class="panel-body">
										<display:table name="CliamList" pagesize="10" requestURI="" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<s:set name="myrow" value="#attr.record"/>										
											<display:column sortable="true" style="text-align:center;" title="Claim No" property="claim_No" />
											<display:column sortable="true" style="text-align:center;" title="Date Of Loss" property="date_of_Loss" />
											<display:column sortable="true" style="text-align:center;" title="Claim Registered Date" property="created_Date" />
											<display:column sortable="true" style="text-align:center;" title="Status Of Claim" property="status_of_claim" />
											<s:if test='#session.MenuRights.indexOf("CN")!=-1 '>
											<display:column sortable="true" style="text-align:center;" title=" Edit">
											<s:if test=' !"Y".equals(ceaseStatus)'>
												<a title="Edit" style="cursor: pointer;" onClick="EditMode('${record.policy_Contract_No}','${record.claim_No}')">Edit</a>
											</s:if>
											</display:column>
											</s:if>
											<s:if test='#session.MenuRights.indexOf("CV")!=-1'>
											<display:column sortable="true" style="text-align:center;" title=" View ">
												<a title="View" style="cursor: pointer;" onclick="ViewMode('${record.policy_Contract_No}','${record.claim_No }')">View</a>
											</display:column>
											</s:if>
										<%--	<display:column sortable="true" title="Delete">
									         <s:if test='"Y".equalsIgnoreCase(#myrow.deleteStatus)' >
											 	<center ><a title="Delete" style="cursor: pointer;" onClick="deletedata('${record.policy_Contract_No}','${record.claim_No }')">Delete</a></center>
									       	 </s:if>
									        </display:column> --%>
											<display:column sortable="false" style="text-align:center;" title="Document">
												<a href="#" class="" title="Document" onclick="getDocList('<s:property value="proposal_No"/>','${record.claim_No}','<s:property value="ceding_company_Name"/>','<s:property value="broker_Name"/>','claim');">
													<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Document Details" width="12" height="17">
												</a>
											</display:column> 
										</display:table>										
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">
							<div class="boxcontent" align="center">
								 <s:if test='"CD".equals(claimMasterMode)'>
									<input type="button" value="Back" class="btn btn-sm btn-danger" onClick="destroyPopUps();BackMethodclaim()" /> &nbsp;&nbsp;&nbsp;
								</s:if>
								<s:elseif test='"CP".equals(claimMasterMode)'>
									<input type="button" value="Back" class="btn btn-sm btn-danger" onClick="destroyPopUps();BackMethod1()" /> &nbsp;&nbsp;&nbsp;
								</s:elseif>
								<s:else>
									<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="BackFunction()"/>	
								</s:else>							
							</div>
						</div>
						<s:hidden name="proposalno" id="proposalno"/>
						<s:hidden name="proposal_No" id="proposal_No"/>
						<s:hidden name="tranNo" id="tranNo"/>
						<s:hidden name="moduleType" value="CL"/>
						<s:hidden name="companyName" id="companyName" value="%{ceding_company_Name}"/>
						<s:hidden name="brokerName" id="brokerName" value="%{broker_Name}"/>
						<s:hidden name="claim_No" id="claim_No" value="%{claim_No%}"/>
						<s:hidden name="contarctno" id="contarctno" value="%{policy_Contract_No}" />
						<s:hidden name="layerNo" id="layerNo"/>
						<s:hidden name="BusinessMode" id="BusinessMode"/>
						<s:hidden name="menuId" id="menuId"/>
						<s:hidden name="flag" id="flag"/>
						<s:hidden name="type" id="type"/>	
						<s:hidden name="claimMasterMode" id="claimMasterMode"/>		
						<s:hidden name="claimDisplay" id="claimDisplay"/>	
						<s:hidden name="multiuserError" id="multiuserError"/>	
					</div>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function ViewMode(policy_Contract_No,claim_No) {
		document.getElementById("contarctno").value=policy_Contract_No;
		document.getElementById("claim_No").value=claim_No; 
		document.claim.action="${pageContext.request.contextPath}/claimViewClaim.do?mode=Amode";
		document.claim.submit();
	}	
	function EditMode(policy_Contract_No,claim_No) {
		document.getElementById("contarctno").value=policy_Contract_No;
		document.getElementById("claim_No").value=claim_No; 
		document.claim.action="${pageContext.request.contextPath}/claimInitClaim.do?edit=edit";
		document.claim.submit();
	}	 
	function NewCliam() {	
		document.getElementById("BusinessMode").value="NewCliam";
		document.claim.action="${pageContext.request.contextPath}/claimInitClaim.do";
		document.claim.submit();
	}	 
	function BackFunction() {
		//var flag=document.getElementById("flag").value;
		//var menuId=document.getElementById("menuId").value;
  		document.location='${pageContext.request.contextPath}/InitPortfolio.action';

	}	
	function getDocList(proposalId,tranNo,compName,brokeeName,type) {		
		document.getElementById("proposalno").value=proposalId;
		document.getElementById("tranNo").value=tranNo;
		document.getElementById("type").value=type;		
		document.claim.action="${pageContext.request.contextPath}/documentUpload.action";
	    document.claim.submit();
	} 
	function deletedata(contNo,claimno){
		document.getElementById("contarctno").value=contNo;
		document.getElementById("claim_No").value=claimno;
		document.claim.action="${pageContext.request.contextPath}/deleteclaimDetailsClaim.action";
		document.claim.submit();  
	}
function BackMethod1() {	
		document.claim.action="<%=request.getContextPath()%>/claimPaymentListClaim.do?flag=claim";
		document.claim.submit();
	}
	function BackMethodclaim(){
	 document.claim.action="<%=request.getContextPath()%>/claimMasterListClaim.do?flag=claim";
	 document.claim.submit();
	 }
	 editModeStatus();
function editModeStatus(){
	var val =document.getElementById("multiuserError").value;
	if('error'==val){
	document.getElementById("multiuserError").value ='';
	alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
	}
}
</script>	
</body>
</html>
