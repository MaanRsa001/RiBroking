<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%> 

<!DOCTYPE HTML>
<html>
<head>
	<style>		
	</style>	
</head>  
<body>
<s:set name="userRights" value='%{MenuRights == ""?"":MenuRights}' />
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">				
				<div class="boxcontent">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="heading.contractDetails" />
							<s:if test="claim_No != '' && claim_No != null">
							<span class="pullRight">								
								<s:text name="Claim.ClaimNo" />								
								:&nbsp;<s:property value="claim_No"/>								
							</span>
							</s:if>
						</div>
						<div class="panel-body">
							<div class="boxcontent">											
								<div class="textfield" >
									<div class="text txtB">
										<s:text name="Claim.PolicyContractNo" />
									</div>
									<div class="tbox">
										<s:property value="policy_Contract_No"/>
									</div>
								</div>
								<div class="textfield" align="right">
									<input class="btn btn-xs btn-primary" type="button" value="View Contract Details" onclick="getViewContractDetails('<s:property value="%{#session.mfrid}" />','<s:property value="proposal_No" />','<s:property value="amendId" />')" />
									<s:hidden name="amendId"/>
								</div>
								<s:if test='"5".equals(#session.mfrid)'>
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
										<s:if test='"5".equals(#session.mfrid)'>
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
															<s:if test='"4".equals(#session.mfrid)'>
															<s:text name="claim.treatyLimitOSOC" /> 
															</s:if>
															<s:if test='"5".equals(#session.mfrid)'>
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
															<s:if test='"4".equals(#session.mfrid)'> 
															<s:text name="claim.treatyLimitOSDC" /> <s:property value="shortname" />
															</s:if>
															<s:if test='"5".equals(#session.mfrid)'>
															<s:text name="claim.coverLimitOSDC" /><s:property value="shortname" />(Main Class)
															</s:if>
														</div>
														<div class="tbox">
															<s:property value="sumInsOSDC"/>
															<s:hidden name="sumInsOSDC" />
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
<script type="text/javascript">
function detailsClick(val) {
	if(val){
  		document.getElementById('detailsData').style.display='block';		
		document.getElementById('detailsMinus').style.display='block';
		document.getElementById('detailsPlus').style.display='none';
		document.getElementById('claimOverflow').style.height='45vh';						    
    }else{
    	document.getElementById('detailsData').style.display='none';
		document.getElementById('detailsMinus').style.display='none';
		document.getElementById('detailsPlus').style.display='block';
		document.getElementById('claimOverflow').style.height='59vh';		    
    }
}
</script>
</body>
</html>
