package com.maan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import com.maan.bean.ClaimBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.ClaimDAO;
import com.maan.dao.ApiCaller.ApiForClaim;

public class ClaimDAOImpl extends MyJdbcTemplate implements ClaimDAO {

	private static final Logger LOGGER = LogUtil.getLogger(ClaimDAOImpl.class);
	ApiForClaim apiclaim = new ApiForClaim();

	@SuppressWarnings("unchecked")
	public boolean contractDetails(final ClaimBean beanObj, final int mode) {
		LOGGER.info("CliamBusinessImpl contractDetails || Enter mode=>" + mode);
		String query = "";
		Object[] args = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		boolean dataFlag = false;
		try {
			if (mode == 1) {
				list = apiclaim.getContractDetails(beanObj);
				if (!CollectionUtils.isEmpty(list)) {
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> contractDetails = (Map<String, Object>) list.get(i);
						
						beanObj.setPolicy_Contract_No(contractDetails.get("PolicyContractNo")==null?"":contractDetails.get("PolicyContractNo").toString());
						beanObj.setAmendId(contractDetails.get("AmendId")==null?"":contractDetails.get("AmendId").toString());
						beanObj.setCeding_company_Name(contractDetails.get("CedingcompanyName")==null?"":contractDetails.get("CedingcompanyName").toString());
						beanObj.setCeding_Company_Code(contractDetails.get("CedingCompanyCode")==null?"":contractDetails.get("CedingCompanyCode").toString());
						beanObj.setProposal_No(contractDetails.get("ProposalNo")==null?"":contractDetails.get("ProposalNo").toString());
						if(StringUtils.isBlank(beanObj.getDepartmentId()) || "0".equalsIgnoreCase(beanObj.getDepartmentId()) ){
						beanObj.setDepartmentId(contractDetails.get("DepartmentId")==null?"":contractDetails.get("DepartmentId").toString());
						beanObj.setDepartmentClass(contractDetails.get("DepartmentClass")==null?"":contractDetails.get("DepartmentClass").toString());
						}
						beanObj.setUwYear(contractDetails.get("UwYear")==null?"":contractDetails.get("UwYear").toString());
						
						if(StringUtils.isBlank(beanObj.getCurrecny())){
						beanObj.setCurrecny(contractDetails.get("Currecny")==null?"":contractDetails.get("Currecny").toString());
						}
						//beanObj.setShareSigned(contractDetails.get("SHARE_SIGNED"))
						if("1".equalsIgnoreCase(beanObj.getProductId())){
							beanObj.setSigned_Share(contractDetails.get("SignedShare")==null?"":contractDetails.get("SignedShare").toString());
							beanObj.setLimit_Our_share_USD(contractDetails.get("LimitOurshareUSD")==null?"":contractDetails.get("LimitOurshareUSD").toString());
							beanObj.setSumInsOSOC(contractDetails.get("SumInsOSOC")==null?"":contractDetails.get("SumInsOSOC").toString());
							beanObj.setSumInsOSDC(contractDetails.get("SumInsOSDC")==null?"":contractDetails.get("SumInsOSDC").toString());
							beanObj.setDepartmentName(contractDetails.get("DepartmentName")==null?"":contractDetails.get("DepartmentName").toString());

						}else
						{
							beanObj.setSigned_Share(contractDetails.get("SignedShare")==null?"":contractDetails.get("SignedShare").toString());
							beanObj.setLimit_Orig_Curr(contractDetails.get("LimitOrigCurr")==null?"":contractDetails.get("LimitOrigCurr").toString());
							beanObj.setLimit_Our_share_USD(contractDetails.get("LimitOurshareUSD")==null?"":contractDetails.get("LimitOurshareUSD").toString());
							beanObj.setSumInsOSOC(contractDetails.get("SumInsOSOC")==null?"":contractDetails.get("SumInsOSOC").toString());
							beanObj.setSumInsOSDC(contractDetails.get("SumInsOSDC")==null?"":contractDetails.get("SumInsOSDC").toString());
							beanObj.setDepartmentName(contractDetails.get("DepartmentName")==null?"":contractDetails.get("DepartmentName").toString());
						}
						beanObj.setSubProfitCenter(contractDetails.get("SubProfitCenter")==null?"":contractDetails.get("SubProfitCenter").toString());
						beanObj.setRetention(contractDetails.get("Retention")==null?"":contractDetails.get("Retention").toString());
						beanObj.setFrom(contractDetails.get("From")==null?"":contractDetails.get("From").toString());
						beanObj.setTo(contractDetails.get("To")==null?"":contractDetails.get("To").toString());
						beanObj.setTreaty_Name(contractDetails.get("TreatyName")==null?"":contractDetails.get("TreatyName").toString());
						beanObj.setBroker_code(contractDetails.get("Brokercode")==null?"":contractDetails.get("Brokercode").toString());
						beanObj.setBroker_Name(contractDetails.get("BrokerName")==null?"":contractDetails.get("BrokerName").toString());
						beanObj.setClaimdepartId(contractDetails.get("ClaimdepartId")==null?"":contractDetails.get("ClaimdepartId").toString());
						beanObj.setConsubProfitId(contractDetails.get("ConsubProfitId")==null?"":contractDetails.get("ConsubProfitId").toString());
						if(StringUtils.isBlank(beanObj.getInsuredName())){
						beanObj.setInsuredName(contractDetails.get("InsuredName")==null?"":contractDetails.get("InsuredName").toString());
						}
						beanObj.setProposalType(contractDetails.get("ProposalType")==null?"":contractDetails.get("ProposalType").toString());
						beanObj.setBasis(contractDetails.get("Basis")==null?"":contractDetails.get("Basis").toString());
						
						if("3".equalsIgnoreCase(beanObj.getProductId()))
						{
							beanObj.setNature_of_Coverage(contractDetails.get("NatureofCoverage")==null?"":contractDetails.get("NatureofCoverage").toString());
							beanObj.setReinstatementPremium(contractDetails.get("ReinstatementPremium")==null?"":contractDetails.get("ReinstatementPremium").toString());
							/*if(!"Y".equalsIgnoreCase(beanObj.getReinstatementPremium())){
								beanObj.setReinstType("NA");
								beanObj.setReinstPremiumOCOS("0");
							}*/
						}
						if("2".equalsIgnoreCase(beanObj.getProductId()))
						{
							beanObj.setNature_of_Coverage(contractDetails.get("NatureofCoverage")==null?"":contractDetails.get("NatureofCoverage").toString());
							beanObj.setCashLossOSOC(contractDetails.get("CashLossOSOC")==null?"0":contractDetails.get("CashLossOSOC").toString());
							beanObj.setCashLossOSDC(contractDetails.get("CashLossOSDC")==null?"0":contractDetails.get("CashLossOSDC").toString());
						}
					}
				}
			} else if (mode == 4) {
			list = apiclaim.getContractDetailsmodefour(beanObj);
			if (!CollectionUtils.isEmpty(list)) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> ContractDetailsmodefour = (Map<String, Object>) list.get(i);
					beanObj.setStatus_of_claim(ContractDetailsmodefour.get("Statusofclaim") == null ? "": ContractDetailsmodefour.get("Statusofclaim").toString());
					beanObj.setClaimNo(ContractDetailsmodefour.get("ClaimNo") == null ? "": ContractDetailsmodefour.get("ClaimNo").toString());
					beanObj.setDepartmentClass(ContractDetailsmodefour.get("DepartmentClass") == null ? "": ContractDetailsmodefour.get("DepartmentClass").toString());
					beanObj.setDate_of_Loss(ContractDetailsmodefour.get("DateofLoss") == null ? "": ContractDetailsmodefour.get("DateofLoss").toString());
					beanObj.setReport_Date(ContractDetailsmodefour.get("ReportDate") == null ? "": ContractDetailsmodefour.get("ReportDate").toString());
					beanObj.setLoss_Details(ContractDetailsmodefour.get("LossDetails") == null ? "": ContractDetailsmodefour.get("LossDetails").toString());
					beanObj.setCause_of_Loss(ContractDetailsmodefour.get("CauseofLoss") == null ? "": ContractDetailsmodefour.get("CauseofLoss").toString());
					beanObj.setLocation(ContractDetailsmodefour.get("Location") == null ? "": ContractDetailsmodefour.get("Location").toString());
					beanObj.setLoss_Estimate_Orig_Curr(ContractDetailsmodefour.get("LossEstimateOrigCurr") == null ? "": ContractDetailsmodefour.get("LossEstimateOrigCurr").toString());
					beanObj.setLoss_Estimate_Our_share_Orig_Curr(ContractDetailsmodefour.get("LossEstimateOurshareOrigCurr") == null ? "": ContractDetailsmodefour.get("LossEstimateOurshareOrigCurr").toString());
					beanObj.setExc_Rate(ContractDetailsmodefour.get("ExcRate") == null ? "": ContractDetailsmodefour.get("ExcRate").toString());
					beanObj.setLoss_Estimate_Our_Share_USD(ContractDetailsmodefour.get("LossEstimateOurShareUSD") == null ? "": ContractDetailsmodefour.get("LossEstimateOurShareUSD").toString());
					beanObj.setRi_Recovery(ContractDetailsmodefour.get("RiRecovery") == null ? "": ContractDetailsmodefour.get("RiRecovery").toString());
					beanObj.setRi_Recovery_Amount_USD(ContractDetailsmodefour.get("RiRecoveryAmountUSD") == null ? "": ContractDetailsmodefour.get("RiRecoveryAmountUSD").toString());
					beanObj.setRecovery_from(ContractDetailsmodefour.get("Recoveryfrom") == null ? "": ContractDetailsmodefour.get("Recoveryfrom").toString());
					beanObj.setCreated_by(ContractDetailsmodefour.get("Createdby") == null ? "": ContractDetailsmodefour.get("Createdby").toString());
					beanObj.setCreated_Date(ContractDetailsmodefour.get("CreatedDate") == null ? "": ContractDetailsmodefour.get("CreatedDate").toString());
					beanObj.setModified_by(ContractDetailsmodefour.get("Modifiedby") == null ? "": ContractDetailsmodefour.get("Modifiedby").toString());
					beanObj.setModified_Date(ContractDetailsmodefour.get("ModifiedDate") == null ? "": ContractDetailsmodefour.get("ModifiedDate").toString());
					beanObj.setUpdated_by(ContractDetailsmodefour.get("Updatedby") == null ? "": ContractDetailsmodefour.get("Updatedby").toString());
					beanObj.setUpdated_Date(ContractDetailsmodefour.get("UpdatedDate") == null ? "": ContractDetailsmodefour.get("UpdatedDate").toString());
					beanObj.setCurrecny(ContractDetailsmodefour.get("Currency") == null ? "": ContractDetailsmodefour.get("Currency").toString());
					beanObj.setCurrencyName(ContractDetailsmodefour.get("CurrencyName") == null ? "": ContractDetailsmodefour.get("CurrencyName").toString());
					beanObj.setAdvice_uw_email(ContractDetailsmodefour.get("Adviceuwemail") == null ? "": ContractDetailsmodefour.get("Adviceuwemail").toString());
					beanObj.setAdvice_Mangement(ContractDetailsmodefour.get("AdviceMangement") == null ? "": ContractDetailsmodefour.get("AdviceMangement").toString());
					beanObj.setRisk_Code(ContractDetailsmodefour.get("RiskCode") == null ? "": ContractDetailsmodefour.get("RiskCode").toString());
					beanObj.setAccumulation_Code(ContractDetailsmodefour.get("AccumulationCode") == null ? "": ContractDetailsmodefour.get("AccumulationCode").toString());
					beanObj.setEvent_Code(ContractDetailsmodefour.get("EventCode") == null ? "": ContractDetailsmodefour.get("EventCode").toString());
					beanObj.setInsuredName(ContractDetailsmodefour.get("InsuredName") == null ? "": ContractDetailsmodefour.get("InsuredName").toString());
					beanObj.setRecordFees(ContractDetailsmodefour.get("RecordFees") == null ? "": ContractDetailsmodefour.get("RecordFees").toString());
					beanObj.setSurveyorAdjesterPerOC(ContractDetailsmodefour.get("SurveyorAdjesterPerOC") == null ? "": ContractDetailsmodefour.get("SurveyorAdjesterPerOC").toString());
					beanObj.setSurveyorAdjesterOurShareOC(ContractDetailsmodefour.get("SurveyorAdjesterOurShareOC") == null ? "": ContractDetailsmodefour.get("SurveyorAdjesterOurShareOC").toString());
					beanObj.setOtherProfessionalPerOc(ContractDetailsmodefour.get("OtherProfessionalPerOc") == null ? "": ContractDetailsmodefour.get("OtherProfessionalPerOc").toString());
					beanObj.setAdvice_management_email(ContractDetailsmodefour.get("Advicemanagementemail") == null ? "": ContractDetailsmodefour.get("Advicemanagementemail").toString());
					beanObj.setProfessionalOurShareOc(ContractDetailsmodefour.get("ProfessionalOurShareOc") == null ? "": ContractDetailsmodefour.get("ProfessionalOurShareOc").toString());
					beanObj.setIbnrPerOc(ContractDetailsmodefour.get("IbnrPerOc") == null ? "": ContractDetailsmodefour.get("IbnrPerOc").toString());
					beanObj.setIbnrOurShareOc(ContractDetailsmodefour.get("IbnrOurShareOc") == null ? "": ContractDetailsmodefour.get("IbnrOurShareOc").toString());
					beanObj.setCedentClaimNo(ContractDetailsmodefour.get("CedentClaimNo") == null ? "": ContractDetailsmodefour.get("CedentClaimNo").toString());
					beanObj.setReservePositionDate(ContractDetailsmodefour.get("ReservePositionDate") == null ? "": ContractDetailsmodefour.get("ReservePositionDate").toString());
					beanObj.setSubProfitId(ContractDetailsmodefour.get("SubProfitId") == null ? "": ContractDetailsmodefour.get("SubProfitId").toString());
					beanObj.setReopen_Date(ContractDetailsmodefour.get("ReopenDate") == null ? "": ContractDetailsmodefour.get("ReopenDate").toString());
					beanObj.setReputed_Date(ContractDetailsmodefour.get("ReputedDate") == null ? "": ContractDetailsmodefour.get("ReputedDate").toString());
					beanObj.setAccumulation_Code(ContractDetailsmodefour.get("AccumulationCode") == null ? "": ContractDetailsmodefour.get("AccumulationCode").toString());
					beanObj.setLayerNo(ContractDetailsmodefour.get("LayerNo") == null ? "": ContractDetailsmodefour.get("LayerNo").toString());
					beanObj.setGrossLossFGU(ContractDetailsmodefour.get("GrossLossFGU") == null ? "": ContractDetailsmodefour.get("GrossLossFGU").toString());
					beanObj.setRecordIbnr(ContractDetailsmodefour.get("RecordIbnr") == null ? "": ContractDetailsmodefour.get("RecordIbnr").toString());
					beanObj.setClaimdepartId(ContractDetailsmodefour.get("ClaimdepartId") == null ? "": ContractDetailsmodefour.get("ClaimdepartId").toString());
					beanObj.setRemarks(ContractDetailsmodefour.get("Remarks") == null ? "": ContractDetailsmodefour.get("Remarks").toString());
				}
			}
			} else if (mode == 5) {
				list = apiclaim.getContractDetailsmodefive(beanObj);
				if (!CollectionUtils.isEmpty(list)) {
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> ContractDetailsmodefive = (Map<String, Object>) list.get(i);
						beanObj.setPaid_Amount_Orig_curr(ContractDetailsmodefive.get("PaidAmountOrigcurr") == null ? "": ContractDetailsmodefive.get("PaidAmountOrigcurr").toString());
						beanObj.setPayment_Request_No(ContractDetailsmodefive.get("PaymentRequestNo") == null ? "": ContractDetailsmodefive.get("PaymentRequestNo").toString());
						beanObj.setLoss_Estimate_Revised_Orig_Curr(ContractDetailsmodefive.get("LossEstimateRevisedOrigCurr") == null ? "": ContractDetailsmodefive.get("LossEstimateRevisedOrigCurr").toString());
						beanObj.setClaim_Note_recommendations(ContractDetailsmodefive.get("ClaimNoterecommendations") == null ? "": ContractDetailsmodefive.get("ClaimNoterecommendations").toString());
						beanObj.setPayment_Reference(ContractDetailsmodefive.get("PaymentReference") == null ? "": ContractDetailsmodefive.get("PaymentReference").toString());
						beanObj.setAdvice_Treasury(ContractDetailsmodefive.get("AdviceTreasury") == null ? "": ContractDetailsmodefive.get("AdviceTreasury").toString());
						beanObj.setDate(ContractDetailsmodefive.get("Date") == null ? "": ContractDetailsmodefive.get("Date").toString());
						beanObj.setPaid_Amount_USD(ContractDetailsmodefive.get("PaidAmountUSD") == null ? "": ContractDetailsmodefive.get("PaidAmountUSD").toString());
						beanObj.setLoss_Estimate_Revised_USD(ContractDetailsmodefive.get("LossEstimateRevisedUSD") == null ? "": ContractDetailsmodefive.get("LossEstimateRevisedUSD").toString());
					}
				}
			} else if (mode == 6) {
				query = getQuery(DBConstants.CLAIM_SELECT_GETCLAIMUPDATELIST);
				LOGGER.info("Select Query====>" + query);
				LOGGER.info("Arg[0]====>" + beanObj.getClaim_No());
				LOGGER.info("Arg[1]====>" + beanObj.getPolicy_Contract_No());
				this.mytemplate.query(query, new Object[] { beanObj.getClaim_No(), beanObj.getPolicy_Contract_No() },
						new RowMapper() {
							public Object mapRow(ResultSet updateList, int rowNum) throws SQLException {
								beanObj.setLoss_Estimate_Revised_Orig_Curr(
										updateList.getString("LOSS_ESTIMATE_REVISED_OC"));
								beanObj.setLoss_Estimate_Revised_USD(updateList.getString("LOSS_ESTIMATE_REVISED_DC"));
								beanObj.setUpdate_Reference(updateList.getString("UPDATE_REFERENCE"));
								beanObj.setCliam_update_Date(updateList.getString("INCEPTION_DT"));
								return null;
							}
						});
			}

			else if (mode == 7) {
				query = getQuery(DBConstants.CLAIM_SELECT_GETCLAIMREVIEWQUERY);
				LOGGER.info("Select Query=>" + query);
				LOGGER.info("Arg[0]" + beanObj.getClaim_No());
				LOGGER.info("Arg[1]" + beanObj.getPolicy_Contract_No());

				this.mytemplate.query(query, new Object[] { beanObj.getClaim_No(), beanObj.getPolicy_Contract_No() },
						new RowMapper() {
							public Object mapRow(ResultSet claimReview, int rowNum) throws SQLException {
								beanObj.setReview_Date(claimReview.getString("REVIEW_DT"));
								beanObj.setReview_Done_By(claimReview.getString("REVIEW_DONE_BY"));
								return null;
							}
						});
			} else if (mode == 10) {

				list = apiclaim.getcontractDetailsModeTen(beanObj);
				if (!CollectionUtils.isEmpty(list)) {
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> ContractDetailsmodeTen = (Map<String, Object>) list.get(i);
						beanObj.setSumOfPaidAmountOC(ContractDetailsmodeTen.get("SumOfPaidAmountOC") == null ? "": ContractDetailsmodeTen.get("SumOfPaidAmountOC").toString());
						beanObj.setRevSumOfPaidAmt(ContractDetailsmodeTen.get("RevSumOfPaidAmt") == null ? "": ContractDetailsmodeTen.get("RevSumOfPaidAmt").toString());
						beanObj.setLoss_Estimate_Revised_Orig_Curr(ContractDetailsmodeTen.get("LossEstimateRevisedOrigCurr") == null ? "": ContractDetailsmodeTen.get("LossEstimateRevisedOrigCurr").toString());

					}
				}

			}
			LOGGER.info("CliamBusinessImpl contractDetails || Exit");
		} catch (Exception exe) {
			LOGGER.debug("Exception " + exe);
			exe.printStackTrace();
		}
		return dataFlag;
	}

	@SuppressWarnings("unchecked")
	public List cliamTableList(final ClaimBean beanObj, final int mode) {

		LOGGER.info("CliamBusinessImpl cliamTableList || Enter");
		List cliamlists = new ArrayList();
		String layerNo = "";
		String query = "";
		try {
			/*
			 * if(StringUtils.isNotBlank(beanObj.getClaim_No())) {
			 * if("3".equalsIgnoreCase(beanObj.getProductId())){
			 * query=getQuery(DBConstants.CLAIM_SELECT_SUMPAIDAMT1);
			 * LOGGER.info("Select Query====>"+query);
			 * LOGGER.info("Arg[0]====>"+beanObj.getClaim_No());
			 * LOGGER.info("Arg[1]====>"+beanObj.getPolicy_Contract_No());
			 * beanObj.setSumOfPaidAmountOC(DropDownControllor.formatter((String)this.
			 * mytemplate.queryForObject(query,new
			 * Object[]{beanObj.getClaim_No(),beanObj.getPolicy_Contract_No()},String.class)
			 * )); }else{ query=getQuery(DBConstants.CLAIM_SELECT_SUMPAIDAMT);
			 * LOGGER.info("Select Query====>"+query);
			 * LOGGER.info("Arg[0]====>"+beanObj.getClaim_No());
			 * LOGGER.info("Arg[1]====>"+beanObj.getPolicy_Contract_No());
			 * beanObj.setSumOfPaidAmountOC(DropDownControllor.formatter((String)this.
			 * mytemplate.queryForObject(query,new
			 * Object[]{beanObj.getClaim_No(),beanObj.getPolicy_Contract_No()},String.class)
			 * )); } }
			 */
			if (mode == 1) {

				List<Map<String, Object>> list = apiclaim.getClaimList(beanObj);
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> tempMap = (Map<String, Object>) list.get(i);
					ClaimBean cliam = new ClaimBean();
					cliam.setClaim_No(tempMap.get("ClaimNo") == null ? "" : tempMap.get("ClaimNo").toString());
					cliam.setDate_of_Loss(tempMap.get("DateOfLoss") == null ? "" : tempMap.get("DateOfLoss").toString());
					cliam.setCreated_Date(tempMap.get("CreatedDate") == null ? "" : tempMap.get("CreatedDate").toString());
					cliam.setStatus_of_claim(tempMap.get("StatusOfClaim") == null ? "" : tempMap.get("StatusOfClaim").toString());
					cliam.setPolicy_Contract_No(tempMap.get("PolicyContractNo") == null ? "" : tempMap.get("PolicyContractNo").toString());
					cliam.setEditMode(tempMap.get("EditMode") == null ? "" : tempMap.get("EditMode").toString());
					cliam.setDeleteStatus(tempMap.get("DeleteStatus") == null ? "" : tempMap.get("DeleteStatus").toString());
					cliamlists.add(cliam);
				}

				/*
				 * if(StringUtils.isNotBlank(beanObj.getProposal_No()) ){
				 * beanObj.setCeaseStatus((String)this.mytemplate.
				 * queryForObject("select CEASE_STATUS from position_master pm where PROPOSAL_NO=? and  pm.amend_id=(Select Max(Amend_id) From Position_master p where p.PROPOSAL_NO=pm.PROPOSAL_NO )"
				 * , new Object[]{beanObj.getProposal_No()}, String.class)); }
				 */
			}
			if (mode == 2 || mode == 7) {
				String args[] = null;

				if (mode == 2) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					list = apiclaim.getClaimListmodetwo(beanObj.getBranchCode(), beanObj.getClaim_No(),
							beanObj.getPolicy_Contract_No());
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> tempMap = (Map<String, Object>) list.get(i);
						ClaimBean cliam = new ClaimBean();
						cliam.setLoss_Estimate_Revised_Orig_Curr(tempMap.get("LossEstimateRevisedOrigCurr") == null ? "": tempMap.get("LossEstimateRevisedOrigCurr").toString());
						cliam.setLoss_Estimate_Revised_USD(tempMap.get("setLossEstimateRevisedUSD") == null ? "": tempMap.get("setLossEstimateRevisedUSD").toString());
						cliam.setUpdate_Reference(tempMap.get("UpdateReference") == null ? "": tempMap.get("UpdateReference").toString());
						cliam.setCliam_update_Date(tempMap.get("CliamupdateDate") == null ? "": tempMap.get("CliamupdateDate").toString());
						cliam.setSNo(tempMap.get("SNo") == null ? "" : tempMap.get("SNo").toString());
						cliam.setPolicy_Contract_No(tempMap.get("PolicyContractNo") == null ? "": tempMap.get("PolicyContractNo").toString());
						cliam.setClaim_No(tempMap.get("ClaimNo") == null ? "" : tempMap.get("ClaimNo").toString());

						cliamlists.add(cliam);
						/*
						 * bean.setLoss_Estimate_Revised_USD(tempMap.get("LOSS_ESTIMATE_REVISED_DC")==
						 * null?"":DropDownControllor.formatter(tempMap.get("LOSS_ESTIMATE_REVISED_DC").
						 * toString()));
						 * bean.setUpdate_Reference(tempMap.get("UPDATE_REFERENCE")==null?"":tempMap.get
						 * ("UPDATE_REFERENCE").toString());
						 * bean.setCliam_update_Date(tempMap.get("INCEPTION_DT")==null?"":tempMap.get(
						 * "INCEPTION_DT").toString());
						 * bean.setSNo(tempMap.get("SL_NO")==null?"":tempMap.get("SL_NO").toString());
						 * bean.setPolicy_Contract_No(tempMap.get("CONTRACT_NO")==null?"":tempMap.get(
						 * "CONTRACT_NO").toString());
						 * bean.setClaim_No(tempMap.get("CLAIM_NO")==null?"":tempMap.get("CLAIM_NO").
						 * toString());
						 */
					}
				}

				else if (mode == 7) {

					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					list = apiclaim.getClaimListmodeseven(beanObj);
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> sevenmap = (Map<String, Object>) list.get(i);
						ClaimBean cliam = new ClaimBean();
						cliam.setCurrencyName(sevenmap.get("CurrencyName") == null ? "" : sevenmap.get("CurrencyName").toString());
						cliam.setTotalORpaidAmount(sevenmap.get("TotalORpaidAmount") == null ? "": sevenmap.get("TotalORpaidAmount").toString());
						cliam.setTotalSApaidAmount(sevenmap.get("TotalSApaidAmount") == null ? "": sevenmap.get("TotalSApaidAmount").toString());
						cliam.setTotalOPpaidAmount(sevenmap.get("TotalOPpaidAmount") == null ? "": sevenmap.get("TotalOPpaidAmount").toString());
						cliam.setTotalTORpaidAmount(sevenmap.get("TotalTORpaidAmount") == null ? "": sevenmap.get("TotalTORpaidAmount").toString());
						
						if (mode == 7) {
							cliam.setLoss_Estimate_Revised_Orig_Curr(sevenmap.get("LossEstimateRevisedOrigCurr") == null ? "": sevenmap.get("LossEstimateRevisedOrigCurr").toString());
							cliam.setLoss_Estimate_Revised_USD(sevenmap.get("LossEstimateRevisedUSD") == null ? "": sevenmap.get("LossEstimateRevisedUSD").toString());
							cliam.setUpdate_Reference(sevenmap.get("UpdateReference") == null ? "": sevenmap.get("UpdateReference").toString());
							cliam.setCliam_update_Date(sevenmap.get("CliamupdateDate") == null ? "": sevenmap.get("CliamupdateDate").toString());
							cliam.setSNo(sevenmap.get("SNo") == null ? "" : sevenmap.get("SNo").toString());
							cliam.setPolicy_Contract_No(sevenmap.get("PolicyContractNo") == null ? "": sevenmap.get("PolicyContractNo").toString());
							cliam.setLero2a(sevenmap.get("Lero2a") == null ? "" : sevenmap.get("Lero2a").toString());
							cliam.setLero2b(sevenmap.get("Lero2b") == null ? "" : sevenmap.get("Lero2b").toString());
							cliam.setLero2c(sevenmap.get("Lero2c") == null ? "" : sevenmap.get("Lero2c").toString());
							cliam.setSaf3a(sevenmap.get("Saf3a") == null ? "" : sevenmap.get("Saf3a").toString());
							cliam.setSaf3c(sevenmap.get("Saf3c") == null ? "" : sevenmap.get("Saf3c").toString());
							cliam.setOfos4a(sevenmap.get("Ofos4a") == null ? "" : sevenmap.get("Ofos4a").toString());
							cliam.setOfos4b(sevenmap.get("Ofos4b") == null ? "" : sevenmap.get("Ofos4b").toString());
							cliam.setOfos4c(sevenmap.get("Ofos4c") == null ? "" : sevenmap.get("Ofos4c").toString());
							cliam.setTotala(sevenmap.get("Totala") == null ? "" : sevenmap.get("Totala").toString());
							cliam.setTotalb(sevenmap.get("Totalb") == null ? "" : sevenmap.get("Totalb").toString());
							cliam.setTotalc(sevenmap.get("Totalc") == null ? "" : sevenmap.get("Totalc").toString());
							cliam.setReInspremiumOS(sevenmap.get("ReInspremiumOS") == null ? "": sevenmap.get("ReInspremiumOS").toString());
							cliam.setCibnr100Oc(sevenmap.get("Cibnr100Oc") == null ? "" : sevenmap.get("Cibnr100Oc").toString());
							cliam.setClaim_No(sevenmap.get("ClaimNo") == null ? "" : sevenmap.get("ClaimNo").toString());
						}
						cliamlists.add(cliam);

					}

				}
			}

			/*
			 * args=new String[4]; args[0]=beanObj.getPolicy_Contract_No();
			 * args[1]=beanObj.getClaim_No(); args[2]=beanObj.getPolicy_Contract_No();
			 * args[3]=beanObj.getClaim_No();
			 * query=getQuery(DBConstants.CLIME_SELECT_GETCLAIMRESERVELIST); }
			 * LOGGER.info("Select Query=>"+query); int i=0;
			 * LOGGER.info("Arg =>"+StringUtils.join(args, ",")); double a=0.0; double
			 * b=0.0; double c=0.0; double d=0.0; List
			 * list=this.mytemplate.queryForList(query,args); if(list!=null &&
			 * list.size()>0){ for (int j = 0; j < list.size(); j++) { Map<String,Object>
			 * tempMap = (Map<String, Object>) list.get(j); ClaimBean bean=new ClaimBean();
			 * bean.setLoss_Estimate_Revised_Orig_Curr(tempMap.get(
			 * "LOSS_ESTIMATE_REVISED_OC")==null?"":DropDownControllor.formatter(tempMap.get
			 * ("LOSS_ESTIMATE_REVISED_OC").toString()));
			 * bean.setLoss_Estimate_Revised_USD(tempMap.get("LOSS_ESTIMATE_REVISED_DC")==
			 * null?"":DropDownControllor.formatter(tempMap.get("LOSS_ESTIMATE_REVISED_DC").
			 * toString()));
			 * bean.setUpdate_Reference(tempMap.get("UPDATE_REFERENCE")==null?"":tempMap.get
			 * ("UPDATE_REFERENCE").toString());
			 * bean.setCliam_update_Date(tempMap.get("INCEPTION_DT")==null?"":tempMap.get(
			 * "INCEPTION_DT").toString());
			 * bean.setSNo(tempMap.get("SL_NO")==null?"":tempMap.get("SL_NO").toString());
			 * bean.setPolicy_Contract_No(tempMap.get("CONTRACT_NO")==null?"":tempMap.get(
			 * "CONTRACT_NO").toString());
			 * bean.setClaim_No(tempMap.get("CLAIM_NO")==null?"":tempMap.get("CLAIM_NO").
			 * toString()); if(mode==7) {
			 * bean.setLero2a(tempMap.get("LOSS_ESTIMATE_REVISED_OC_2A")==null?"":
			 * DropDownControllor.formatter(tempMap.get("LOSS_ESTIMATE_REVISED_OC_2A").
			 * toString()));
			 * bean.setLero2b(tempMap.get("PAID_CLAIM_OS_OC_2B")==null?"":DropDownControllor
			 * .formatter(tempMap.get("PAID_CLAIM_OS_OC_2B").toString()));
			 * bean.setLero2c(tempMap.get("OC_OS_AMOUNT_2C")==null?"":DropDownControllor.
			 * formatter(tempMap.get("OC_OS_AMOUNT_2C").toString()));
			 * bean.setSaf3a(tempMap.get("SAF_OS_OC_3A")==null?"":DropDownControllor.
			 * formatter(tempMap.get("SAF_OS_OC_3A").toString()));
			 * bean.setSaf3b(tempMap.get("SAF_OS_OC_3B")==null?"":DropDownControllor.
			 * formatter(tempMap.get("SAF_OS_OC_3B").toString()));
			 * bean.setSaf3c(tempMap.get("SURVEYOR_OS_AMT_3C")==null?"":DropDownControllor.
			 * formatter(tempMap.get("SURVEYOR_OS_AMT_3C").toString()));
			 * bean.setOfos4a(tempMap.get("OTH_FEE_OS_OC_4A")==null?"":DropDownControllor.
			 * formatter(tempMap.get("OTH_FEE_OS_OC_4A").toString()));
			 * bean.setOfos4b(tempMap.get("OTH_FEE_OS_OC_4B")==null?"":DropDownControllor.
			 * formatter(tempMap.get("OTH_FEE_OS_OC_4B").toString()));
			 * bean.setOfos4c(tempMap.get("OTHER_PROFESS_OS_AMT_4C")==null?"":
			 * DropDownControllor.formatter(tempMap.get("OTHER_PROFESS_OS_AMT_4C").toString(
			 * )));
			 * bean.setTotala(tempMap.get("TOTAL_A")==null?"":DropDownControllor.formatter(
			 * Double.toString((Double.parseDouble(tempMap.get("TOTAL_A").toString())))));
			 * bean.setTotalb(tempMap.get("TOTAL_B")==null?"":DropDownControllor.formatter(
			 * Double.toString((Double.parseDouble(tempMap.get("TOTAL_B").toString())))));
			 * bean.setTotalc(tempMap.get("TOTAL_C")==null?"":DropDownControllor.formatter(
			 * Double.toString((Double.parseDouble(tempMap.get("TOTAL_C").toString())))));
			 * bean.setReInspremiumOS(tempMap.get("REINSPREMIUM_OURSHARE_OC")==null?"":
			 * DropDownControllor.formatter(tempMap.get("REINSPREMIUM_OURSHARE_OC").toString
			 * ()));
			 * bean.setCibnr100Oc(tempMap.get("C_IBNR_OS_OC")==null?"":DropDownControllor.
			 * formatter(tempMap.get("C_IBNR_OS_OC").toString()));
			 * 
			 * a=a+Double.parseDouble(tempMap.get("PAID_CLAIM_OS_OC_2B")==null?"":tempMap.
			 * get("PAID_CLAIM_OS_OC_2B").toString());
			 * b=b+Double.parseDouble(tempMap.get("SAF_OS_OC_3B")==null?"":tempMap.get(
			 * "SAF_OS_OC_3B").toString());
			 * c=c+Double.parseDouble(tempMap.get("OTH_FEE_OS_OC_4B")==null?"":tempMap.get(
			 * "OTH_FEE_OS_OC_4B").toString());
			 * d=d+Double.parseDouble(tempMap.get("TOTAL_B")==null?"":Double.toString(Double
			 * .parseDouble(tempMap.get("TOTAL_B").toString()))); } cliamlists.add(bean);
			 * 
			 * } beanObj.setTotalORpaidAmount(DropDownControllor.formatter(Double.toString(
			 * Double.parseDouble(String.valueOf(a)))));
			 * beanObj.setTotalSApaidAmount(DropDownControllor.formatter(Double.toString(
			 * Double.parseDouble(String.valueOf(b)))));
			 * beanObj.setTotalOPpaidAmount(DropDownControllor.formatter(Double.toString(
			 * Double.parseDouble(String.valueOf(c)))));
			 * beanObj.setTotalTORpaidAmount(DropDownControllor.formatter(Double.toString(
			 * Double.parseDouble(String.valueOf(d)))));
			 * 
			 * }
			 * 
			 * query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY_NAME);
			 * beanObj.setCurrencyName((String)this.mytemplate.queryForObject(query,new
			 * Object[]{beanObj.getBranchCode()},String.class));
			 * 
			 * 
			 * }
			 */
			if (mode == 3) {
				List<Map<String, Object>> list1 = apiclaim.getClaimListmodethree(beanObj);
				for (int i = 0; i < list1.size(); i++) {
					Map<String, Object> relist = (Map<String, Object>) list1.get(i);
					ClaimBean cliam = new ClaimBean();
					cliam.setClaimNo(relist.get("ClaimNo") == null ? "" : relist.get("ClaimNo").toString());
					cliam.setDate_of_Loss(relist.get("DateofLoss") == null ? "" : relist.get("DateofLoss").toString());
					cliam.setLoss_Estimate_Our_share_Orig_Curr(relist.get("LossEstimateOurshareOrigCurr") == null ? "": relist.get("LossEstimateOurshareOrigCurr").toString());
					cliam.setCurrecny(relist.get("Currecny") == null ? "" : relist.get("Currecny").toString());
					cliam.setDate(relist.get("Date") == null ? "" : relist.get("Date").toString());
					cliamlists.add(cliam);
				}
				
			}
			if (mode == 4) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				list = apiclaim.getClaimListmodefour(beanObj.getBranchCode(), beanObj.getClaim_No(),
						beanObj.getPolicy_Contract_No(), beanObj.getClaimPaymentNo());
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> result = list.get(i);
					ClaimBean bean = new ClaimBean();
					bean.setPayment_Request_No(result.get("PaymentRequestNo") == null ? "" : result.get("PaymentRequestNo").toString());
					bean.setPaid_Amount_Orig_curr(result.get("PaidAmountOrigCurr") == null ? "": result.get("PaidAmountOrigCurr").toString());
					bean.setLoss_Estimate_Revised_Orig_Curr(result.get("LossEstimateRevisedOrigCurr") == null ? "": result.get("LossEstimateRevisedOrigCurr").toString());
					bean.setDate(result.get("Date") == null ? "" : result.get("Date").toString());
					bean.setClaimPaymentNo(result.get("ClaimPaymentNo") == null ? "" : result.get("ClaimPaymentNo").toString());
					bean.setSNo(result.get("SNo") == null ? "" : result.get("SNo").toString());
					bean.setSettlementStatus(result.get("SettlementStatus") == null ? "" : result.get("SettlementStatus").toString());
					bean.setTransactionType(result.get("TransactionType") == null ? "" : result.get("TransactionType").toString());
					bean.setTransactionNumber(result.get("TransactionNumber") == null ? "" : result.get("TransactionNumber").toString());
					cliamlists.add(bean);
				}
			}

			/*
			 * bean.setPayment_Request_No(result.get("PAYMENT_REQUEST_NO").toString());
			 * bean.setPaid_Amount_Orig_curr(DropDownControllor.formatter(result.get(
			 * "PAID_AMOUNT_OC").toString()));
			 * bean.setLoss_Estimate_Revised_Orig_Curr(DropDownControllor.formatter(result.
			 * get("LOSS_ESTIMATE_REVISED_OC").toString()));
			 * bean.setDate(result.get("INCEPTION_DT").toString());
			 * bean.setClaimPaymentNo(result.get("CLAIM_PAYMENT_NO").toString());
			 * bean.setSNo(result.get("RESERVE_ID").toString());
			 * bean.setSettlementStatus(result.get("SETTLEMENT_STATUS").toString());
			 * bean.setTransactionType(result.get("TRANS_TYPE").toString());
			 * bean.setTransactionNumber(result.get("TRANSACTION_NO")==null?"":result.get(
			 * "TRANSACTION_NO").toString());
			 * 
			 * 
			 * int count = this.mytemplate.
			 * queryForInt("select count(*) allocatedYN from TTRN_ALLOCATED_TRANSACTION where CONTRACT_NO =? and TRANSACTION_NO=? and LAYER_NO=?  and STATUS='Y'"
			 * ,new
			 * Object[]{beanObj.getContarctno(),bean.getClaimPaymentNo(),beanObj.getLayerNo(
			 * )}); if(count==0){ bean.setAllocatedYN("Y"); }else if(count>=1){
			 * bean.setAllocatedYN("N"); }
			 * bean.setStatus_of_claim((String)this.mytemplate.queryForObject(getQuery(
			 * "GET_STATUS_OF_CLAIM"),new
			 * Object[]{beanObj.getContarctno(),beanObj.getClaim_No(),beanObj.getLayerNo()},
			 * String.class)); if(StringUtils.isNotBlank(bean.getDate())){ if(new
			 * DropDownControllor().Validatethree(beanObj.getBranchCode(),
			 * bean.getDate())==0 ){ bean.setTransOpenperiodStatus("N"); }else
			 * if(!result.get("RESERVE_ID").toString().equalsIgnoreCase(maxno)){
			 * bean.setTransOpenperiodStatus("N"); } else {
			 * bean.setTransOpenperiodStatus("Y"); } }
			 */

			if (mode == 5) {
				query = getQuery(DBConstants.CLAIM_SELECT_GETCLAIMPAYMENTLIST);
				LOGGER.info("Select Query====>" + query);
				LOGGER.info("Arg[0]====>" + beanObj.getClaim_No());
				LOGGER.info("Arg[1]====>" + beanObj.getPolicy_Contract_No());
				cliamlists = this.mytemplate.query(query,
						new String[] { beanObj.getClaim_No(), beanObj.getPolicy_Contract_No() }, new RowMapper() {
							public Object mapRow(final ResultSet result, final int arg1) throws SQLException {
								final ClaimBean beanObj = new ClaimBean();
								beanObj.setPayment_Request_No(result.getString("PAYMENT_REQUEST_NO"));
								beanObj.setPaid_Amount_Orig_curr(result.getString("PAID_AMOUNT_OC"));
								beanObj.setLoss_Estimate_Revised_Orig_Curr(
										result.getString("LOSS_ESTIMATE_REVISED_OC"));
								beanObj.setDate(result.getString("INCEPTION_DATE"));
								beanObj.setClaimPaymentNo(result.getString("CLAIM_PAYMENT_NO"));
								beanObj.setSNo(result.getString("RESERVE_ID"));
								return beanObj;
							}
						});
			}
			if (mode == 6) {
				query = getQuery(DBConstants.CLAIM_SELECT_GETCLAIMREVIEWLIST);
				LOGGER.info("Select Query====>" + query);
				LOGGER.info("Arg[0]====>" + beanObj.getPolicy_Contract_No());
				LOGGER.info("Arg[1]====>" + beanObj.getClaim_No());
				cliamlists = this.mytemplate.query(query,
						new String[] { beanObj.getPolicy_Contract_No(), beanObj.getClaim_No() }, new RowMapper() {
							public Object mapRow(final ResultSet result, final int arg1) throws SQLException {
								final ClaimBean beanObj = new ClaimBean();
								beanObj.setSNo(result.getString("SNO"));
								beanObj.setReview_Date(result.getString("REVIEW_DATE"));
								beanObj.setReview_Done_By(result.getString("REVIEW_DONE_BY"));
								beanObj.setRemarks(result.getString("REMARKS"));
								return beanObj;
							}
						});

			}
			LOGGER.info("CliamBusinessImpl cliamTableList || Exit Size=>" + cliamlists.size());
		} catch (Exception exe) {
			LOGGER.debug("Exception " + exe);
			exe.printStackTrace();
		}
		return cliamlists;
	}

	public boolean insertCliamDetails(final ClaimBean beanObj, final int mode) {

		LOGGER.info("CliamBusinessImpl insertCliamDetails || Enter");
		boolean insertFlag = false;
		String query = "";
		Object[] args = null;
		try {
			if (mode == 2) {
				insertFlag=apiclaim.insertCliamDetails(beanObj);
			}
			if (mode == 3) {
				insertFlag=apiclaim.insertCliamDetailsmode3(beanObj);
			}
			if (mode == 8) {
				insertFlag=apiclaim.insertCliamDetailsmode8(beanObj);
			}
			if (mode == 9) {
				args = CliamDetailsAruguments(beanObj, mode);
				query = getQuery(DBConstants.CLAIM_UPDATE_TTRNCLAIMDETAILSRDANDRDB);
				LOGGER.info("Update Query====>" + query);
				int i = 0;
				for (Object o : args) {
					LOGGER.info("Arg[" + i + "]====>" + o);
					i++;
				}
				this.mytemplate.update(query, args);
				args = CliamDetailsAruguments(beanObj, 10);
				query = getQuery(DBConstants.CLAIM_SELECT_MAXSNO);
				LOGGER.info("Select Query====>" + query);
				LOGGER.info("Arg[0]====>" + beanObj.getPolicy_Contract_No());
				LOGGER.info("Arg[1]====>" + beanObj.getClaim_No());
				args[0] = (String) this.mytemplate.queryForObject(query,
						new Object[] { beanObj.getPolicy_Contract_No(), beanObj.getClaim_No() }, String.class);
				query = getQuery(DBConstants.CLAIM_INSERT_TTRNCLAIMREVIEW);
				LOGGER.info("Insert Query====>" + query);
				i = 0;
				for (Object o : args) {
					LOGGER.info("Arg[" + i + "]====>" + o);
					i++;
				}
				this.mytemplate.update(query, args);
			}
			if (mode == 12) {
				
				insertFlag=apiclaim.insertCliamDetailsmode12(beanObj);
			}
			LOGGER.info("CliamBusinessImpl insertCliamDetails || Exit");
		} catch (Exception exe) {
			LOGGER.debug("Exception " + exe);
		}
		return insertFlag;
	}

	public static Object[] CliamDetailsAruguments(final ClaimBean beanObj, final int mode) {
		LOGGER.info("CliamBusinessImpl CliamDetailsAruguments || Enter mode=>" + mode);
		Object[] arg = null;
		if (mode == 2) {
			arg = new Object[56];
			arg[0] = beanObj.getStatus_of_claim();
			arg[1] = beanObj.getDate_of_Loss();
			arg[2] = beanObj.getReport_Date();
			arg[3] = beanObj.getLoss_Details();
			arg[4] = beanObj.getCause_of_Loss();
			arg[5] = beanObj.getCurrecny();
			arg[6] = beanObj.getLoss_Estimate_Orig_Curr();
			arg[7] = beanObj.getLoss_Estimate_Our_share_Orig_Curr();
			arg[8] = beanObj.getExc_Rate();
			arg[9] = DropDownControllor.GetDesginationCountry(beanObj.getLoss_Estimate_Our_share_Orig_Curr(),
					beanObj.getExc_Rate());
			arg[10] = beanObj.getAdvice_UW() == null ? "" : beanObj.getAdvice_UW();
			arg[11] = beanObj.getAdvice_Mangement() == null ? "" : beanObj.getAdvice_Mangement();
			arg[12] = beanObj.getRi_Recovery();
			arg[13] = "0";
			arg[14] = beanObj.getRecovery_from();
			arg[15] = beanObj.getCreated_by();
			arg[16] = beanObj.getCreated_Date();
			arg[17] = beanObj.getLocation();
			arg[18] = beanObj.getRemarks();
			arg[19] = beanObj.getAdvice_uw_email() == null ? "" : beanObj.getAdvice_uw_email();
			arg[20] = beanObj.getAdvice_management_email() == null ? "" : beanObj.getAdvice_management_email();
			arg[21] = beanObj.getRisk_Code() == null ? "" : beanObj.getRisk_Code();
			arg[22] = beanObj.getAccumulation_Code() == null ? "" : beanObj.getAccumulation_Code();
			arg[23] = beanObj.getEvent_Code() == null ? "" : beanObj.getEvent_Code();

			arg[25] = beanObj.getDepartmentId();
			arg[24] = beanObj.getInsuredName();

			// BRANCH_CODE, LOGIN_ID, RECORD_FEES_CRE_RESERVE, SAF_100_OC,
			// SAF_100_DC,OTH_FEE_100_OC, OTH_FEE_100_DC, C_IBNR_100_OC,
			// C_IBNR_100_DC,RECORD_IBNR,CEDENT_CLAIM_NO,SAF_OS_DC,OTH_FEE_OS_DC,C_IBNR_OS_DC,Loss_Estimate_DC)
			// VALUES
			// (?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,SYSDATE,'Y',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
			arg[26] = beanObj.getBranchCode();
			arg[27] = beanObj.getLoginId();
			arg[28] = beanObj.getRecordFees() == null ? "" : beanObj.getRecordFees();
			arg[29] = beanObj.getSurveyorAdjesterPerOC() == null ? "0" : beanObj.getSurveyorAdjesterPerOC();
			arg[30] = StringUtils.isEmpty(beanObj.getSurveyorAdjesterPerOC())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getSurveyorAdjesterPerOC(),
									beanObj.getExc_Rate());
			arg[31] = beanObj.getOtherProfessionalPerOc() == null ? "0" : beanObj.getOtherProfessionalPerOc();
			arg[32] = StringUtils.isEmpty(beanObj.getOtherProfessionalPerOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getOtherProfessionalPerOc(),
									beanObj.getExc_Rate());
			arg[33] = beanObj.getIbnrPerOc() == null ? "0" : beanObj.getIbnrPerOc();
			arg[34] = StringUtils.isEmpty(beanObj.getIbnrPerOc()) || StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
					: DropDownControllor.GetDesginationCountry(beanObj.getIbnrPerOc(), beanObj.getExc_Rate());
			arg[35] = beanObj.getRecordIbnr();
			arg[36] = beanObj.getCedentClaimNo();
			arg[37] = beanObj.getSurveyorAdjesterOurShareOC() == null ? "0" : beanObj.getSurveyorAdjesterOurShareOC();
			arg[38] = beanObj.getProfessionalOurShareOc() == null ? "0" : beanObj.getProfessionalOurShareOc();
			arg[39] = beanObj.getIbnrOurShareOc() == null ? "0" : beanObj.getIbnrOurShareOc();
			arg[40] = StringUtils.isEmpty(beanObj.getSurveyorAdjesterOurShareOC())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getSurveyorAdjesterOurShareOC(),
									beanObj.getExc_Rate());
			arg[41] = StringUtils.isEmpty(beanObj.getProfessionalOurShareOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getProfessionalOurShareOc(),
									beanObj.getExc_Rate());
			arg[42] = StringUtils.isEmpty(beanObj.getIbnrOurShareOc()) || StringUtils.isEmpty(beanObj.getExc_Rate())
					? "0"
					: DropDownControllor.GetDesginationCountry(beanObj.getIbnrOurShareOc(), beanObj.getExc_Rate());

			arg[43] = StringUtils.isEmpty(beanObj.getLoss_Estimate_Orig_Curr())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getLoss_Estimate_Orig_Curr(),
									beanObj.getExc_Rate());
			arg[44] = beanObj.getReopen_Date() == null ? "" : beanObj.getReopen_Date();
			arg[45] = beanObj.getGrossLossFGU() == null ? "" : beanObj.getGrossLossFGU();
			if ("2".equalsIgnoreCase(beanObj.getProductId())) {
				arg[46] = beanObj.getClaimdepartId();
			} else if ("1".equalsIgnoreCase(beanObj.getProductId())) {
				arg[46] = beanObj.getDepartmentId();
			} else if ("3".equalsIgnoreCase(beanObj.getProductId())) {
				arg[46] = beanObj.getDepartmentClass();
			}
			arg[47] = beanObj.getSubProfitId() == null ? "D" : beanObj.getSubProfitId();
			arg[48] = beanObj.getReservePositionDate();
			arg[49] = beanObj.getDepartmentClass();
			arg[50] = beanObj.getProposal_No();
			arg[51] = beanObj.getProductId();
			arg[52] = beanObj.getReopen_Date() == null ? "" : beanObj.getReopen_Date();
			arg[53] = beanObj.getPolicy_Contract_No().trim();
			arg[54] = beanObj.getClaim_No();
			arg[55] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();

			// obj[8] = StringUtils.isEmpty(beanObj.getAcquisition_Cost())||
			// StringUtils.isEmpty(beanObj.getExchRate()) ? "0":
			// getDesginationCountry(beanObj.getAcquisition_Cost(),beanObj.getExchRate());
		} else if (mode == 3) {
			arg = new Object[30];
			// arg[1]="0";
			arg[1] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();
			arg[2] = beanObj.getPayment_Request_No();
			// if("3".equalsIgnoreCase(beanObj.getProductId())){
			// arg[3]=beanObj.getNetclaimAmountOCOS();
			// arg[4]=DropDownControllor.GetDesginationCountry(beanObj.getNetclaimAmountOCOS(),beanObj.getExc_Rate());
			// }else{
			arg[3] = beanObj.getPaid_Amount_Orig_curr();
			arg[4] = DropDownControllor.GetDesginationCountry(beanObj.getPaid_Amount_Orig_curr(),
					beanObj.getExc_Rate());
			// }
			arg[5] = beanObj.getLoss_Estimate_Revised_Orig_Curr();
			arg[6] = DropDownControllor.GetDesginationCountry(beanObj.getLoss_Estimate_Revised_Orig_Curr(),
					beanObj.getExc_Rate());
			arg[7] = beanObj.getClaim_Note_recommendations() == null ? "" : beanObj.getClaim_Note_recommendations();
			arg[8] = beanObj.getPayment_Reference();
			if (StringUtils.isNotBlank(beanObj.getAdvice_Treasury())) {
				arg[9] = beanObj.getAdvice_Treasury();
			} else {
				arg[9] = "";
			}
			arg[10] = beanObj.getDate();
			arg[11] = beanObj.getClaim_No();
			arg[12] = beanObj.getPolicy_Contract_No();
			arg[13] = beanObj.getClaimPaymentNo();
			arg[14] = beanObj.getRemarks();
			arg[15] = beanObj.getAdviceTreasuryEmail() == null ? "" : beanObj.getAdviceTreasuryEmail();
			arg[16] = "1";
			if ("3".equalsIgnoreCase(beanObj.getProductId())) {
				arg[17] = beanObj.getReinstType();
				arg[18] = beanObj.getReinstPremiumOCOS();
				arg[19] = DropDownControllor.GetDesginationCountry(beanObj.getReinstPremiumOCOS(),
						beanObj.getExc_Rate());
				// arg[20]=beanObj.getPaid_Amount_Orig_curr();
				// arg[21]=DropDownControllor.GetDesginationCountry(beanObj.getPaid_Amount_Orig_curr(),beanObj.getExc_Rate());
				// arg[22]=beanObj.getInsuredName();
			} else {
				arg[17] = "";
				arg[18] = "";
				arg[19] = "";
				// arg[20]="";
				// arg[21]="";
				// arg[22]="";
			}
			arg[20] = beanObj.getPaid_claim_os();
			arg[21] = DropDownControllor.GetDesginationCountry(beanObj.getPaid_claim_os(), beanObj.getExc_Rate());
			arg[22] = beanObj.getSurveyor_fee_os();
			arg[23] = DropDownControllor.GetDesginationCountry(beanObj.getSurveyor_fee_os(), beanObj.getExc_Rate());
			arg[24] = beanObj.getOther_prof_fee_os();
			arg[25] = DropDownControllor.GetDesginationCountry(beanObj.getOther_prof_fee_os(), beanObj.getExc_Rate());
			arg[26] = beanObj.getBranchCode();
			arg[27] = beanObj.getLoginId();
			arg[28] = beanObj.getExc_Rate();
			arg[29] = beanObj.getPaymentType();
		} else if (mode == 7) {
			arg = new Object[31];
			arg[1] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();
			arg[2] = beanObj.getLoss_Estimate_Our_share_Orig_Curr();
			arg[3] = DropDownControllor.GetDesginationCountry(beanObj.getLoss_Estimate_Our_share_Orig_Curr(),
					beanObj.getExc_Rate());
			arg[4] = "Inserted at the time of Claim Registration";
			arg[5] = beanObj.getCreated_Date();
			arg[6] = beanObj.getClaim_No();
			arg[7] = beanObj.getPolicy_Contract_No();
			arg[8] = "Inserted at the time of Claim Registration";
			// INSERT INTO TTRN_CLAIM_UPDATION
			// (SL_NO,LAYER_NO,LOSS_ESTIMATE_REVISED_OC,LOSS_ESTIMATE_REVISED_DC,UPDATE_REFERENCE,INCEPTION_DATE,CLAIM_NO,CONTRACT_NO,STATUS,REMARKS,
			// BRANCH_CODE, LOGIN_ID, SAF_100_OC, SAF_100_DC, OTH_FEE_100_OC,
			// OTH_FEE_100_DC, C_IBNR_100_OC, C_IBNR_100_DC, SAF_OS_DC, OTH_FEE_OS_DC,
			// C_IBNR_OS_DC, LOSS_ESTIMATE_100_OC, LOSS_ESTIMATE_100_DC)
			// VALUES(?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),?,?,'Y',?
			// ,?,?,?,?,?,?,?,?,?,?,?,?,?)
			arg[9] = beanObj.getBranchCode();
			arg[10] = beanObj.getLoginId();
			arg[11] = beanObj.getSurveyorAdjesterPerOC() == null ? "0" : beanObj.getSurveyorAdjesterPerOC();
			arg[12] = StringUtils.isEmpty(beanObj.getSurveyorAdjesterPerOC())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getSurveyorAdjesterPerOC(),
									beanObj.getExc_Rate());
			arg[13] = beanObj.getOtherProfessionalPerOc() == null ? "0" : beanObj.getOtherProfessionalPerOc();
			arg[14] = StringUtils.isEmpty(beanObj.getOtherProfessionalPerOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getOtherProfessionalPerOc(),
									beanObj.getExc_Rate());
			arg[15] = beanObj.getIbnrPerOc() == null ? "0" : beanObj.getIbnrPerOc();
			arg[16] = StringUtils.isEmpty(beanObj.getIbnrPerOc()) || StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
					: DropDownControllor.GetDesginationCountry(beanObj.getIbnrPerOc(), beanObj.getExc_Rate());
			arg[17] = beanObj.getSurveyorAdjesterOurShareOC() == null ? "0" : beanObj.getSurveyorAdjesterOurShareOC();
			arg[18] = beanObj.getProfessionalOurShareOc() == null ? "0" : beanObj.getProfessionalOurShareOc();
			arg[19] = beanObj.getIbnrOurShareOc() == null ? "0" : beanObj.getIbnrOurShareOc();
			arg[20] = StringUtils.isEmpty(beanObj.getSurveyorAdjesterOurShareOC())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getSurveyorAdjesterOurShareOC(),
									beanObj.getExc_Rate());
			arg[21] = StringUtils.isEmpty(beanObj.getProfessionalOurShareOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getProfessionalOurShareOc(),
									beanObj.getExc_Rate());
			arg[22] = StringUtils.isEmpty(beanObj.getIbnrOurShareOc()) || StringUtils.isEmpty(beanObj.getExc_Rate())
					? "0"
					: DropDownControllor.GetDesginationCountry(beanObj.getIbnrOurShareOc(), beanObj.getExc_Rate());

			arg[23] = beanObj.getLoss_Estimate_Orig_Curr();
			arg[24] = StringUtils.isEmpty(beanObj.getLoss_Estimate_Orig_Curr())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getLoss_Estimate_Orig_Curr(),
									beanObj.getExc_Rate());
			arg[25] = beanObj.getRecordFees();
			arg[26] = beanObj.getRecordIbnr();
			arg[27] = beanObj.getExc_Rate();
			if (StringUtils.isBlank(beanObj.getTotalReserveOSOC())) {
				double total = Double
						.parseDouble(StringUtils.isBlank(beanObj.getSurveyorAdjesterOurShareOC()) ? "0"
								: beanObj.getSurveyorAdjesterOurShareOC().replaceAll(",", ""))
						+ Double.parseDouble(StringUtils.isBlank(beanObj.getProfessionalOurShareOc()) ? "0"
								: beanObj.getProfessionalOurShareOc().replaceAll(",", ""))
						+ Double.parseDouble(StringUtils.isBlank(beanObj.getLoss_Estimate_Our_share_Orig_Curr()) ? "0"
								: beanObj.getLoss_Estimate_Our_share_Orig_Curr().replaceAll(",", ""));
				beanObj.setTotalReserveOSOC(Double.toString(total));
			}
			arg[28] = beanObj.getTotalReserveOSOC();
			arg[29] = StringUtils.isEmpty(beanObj.getTotalReserveOSOC()) || StringUtils.isEmpty(beanObj.getExc_Rate())
					? "0"
					: DropDownControllor.GetDesginationCountry(beanObj.getTotalReserveOSOC(), beanObj.getExc_Rate());
			arg[30] = beanObj.getReservePositionDate();
		} else if (mode == 8) {
			arg = new Object[31];
			arg[1] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();
			arg[2] = beanObj.getUpdate_Rivised_original_Cur();
			arg[3] = DropDownControllor.GetDesginationCountry(beanObj.getUpdate_Rivised_original_Cur(),
					beanObj.getExc_Rate());
			arg[4] = beanObj.getUpdate_Reference();
			arg[5] = beanObj.getCliam_update_Date();
			arg[6] = beanObj.getClaim_No();
			arg[7] = beanObj.getPolicy_Contract_No();
			arg[8] = beanObj.getRemarks();
			arg[9] = beanObj.getBranchCode();
			arg[10] = beanObj.getLoginId();
			arg[11] = beanObj.getUpdatesurveyorAdjesterPerOC() == null ? "0" : beanObj.getUpdatesurveyorAdjesterPerOC();
			arg[12] = StringUtils.isEmpty(beanObj.getUpdatesurveyorAdjesterPerOC())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdatesurveyorAdjesterPerOC(),
									beanObj.getExc_Rate());
			arg[13] = beanObj.getUpdateotherProfessionalPerOc() == null ? "0"
					: beanObj.getUpdateotherProfessionalPerOc();
			arg[14] = StringUtils.isEmpty(beanObj.getUpdateotherProfessionalPerOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdateotherProfessionalPerOc(),
									beanObj.getExc_Rate());
			arg[15] = beanObj.getUpdateibnrPerOc() == null ? "0" : beanObj.getUpdateibnrPerOc();
			arg[16] = StringUtils.isEmpty(beanObj.getUpdateibnrPerOc()) || StringUtils.isEmpty(beanObj.getExc_Rate())
					? "0"
					: DropDownControllor.GetDesginationCountry(beanObj.getUpdateibnrPerOc(), beanObj.getExc_Rate());
			arg[17] = beanObj.getUpdatesurveyorAdjesterOurShareOC() == null ? "0"
					: beanObj.getUpdatesurveyorAdjesterOurShareOC();
			arg[18] = beanObj.getUpdateprofessionalOurShareOc() == null ? "0"
					: beanObj.getUpdateprofessionalOurShareOc();
			arg[19] = beanObj.getUpdateibnrOurShareOc() == null ? "0" : beanObj.getUpdateibnrOurShareOc();
			arg[20] = StringUtils.isEmpty(beanObj.getUpdatesurveyorAdjesterOurShareOC())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdatesurveyorAdjesterOurShareOC(),
									beanObj.getExc_Rate());
			arg[21] = StringUtils.isEmpty(beanObj.getUpdateprofessionalOurShareOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdateprofessionalOurShareOc(),
									beanObj.getExc_Rate());
			arg[22] = StringUtils.isEmpty(beanObj.getUpdateibnrOurShareOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdateibnrOurShareOc(),
									beanObj.getExc_Rate());
			arg[23] = beanObj.getLoss_Estimate_Orig_Curr();
			arg[24] = StringUtils.isEmpty(beanObj.getLoss_Estimate_Orig_Curr())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getLoss_Estimate_Orig_Curr(),
									beanObj.getExc_Rate());
			arg[25] = beanObj.getUpdaterecordFees();
			arg[26] = beanObj.getUpdaterecordIbnr();
			arg[27] = beanObj.getExc_Rate();
			arg[28] = beanObj.getTotalReserveOSOC();
			arg[29] = StringUtils.isEmpty(beanObj.getTotalReserveOSOC()) || StringUtils.isEmpty(beanObj.getExc_Rate())
					? "0"
					: DropDownControllor.GetDesginationCountry(beanObj.getTotalReserveOSOC(), beanObj.getExc_Rate());
			arg[30] = beanObj.getReservePositionDate();
		}

		else if (mode == 9) {
			arg = new Object[4];
			arg[0] = beanObj.getReview_Date();
			arg[1] = beanObj.getReview_Done_By();
			arg[2] = beanObj.getPolicy_Contract_No();
			arg[3] = beanObj.getClaim_No();
		} else if (mode == 10) {
			arg = new Object[10];
			arg[0] = "0";
			arg[1] = beanObj.getPolicy_Contract_No();
			arg[2] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();
			arg[3] = beanObj.getClaim_No();
			arg[4] = beanObj.getReview_Date();
			arg[5] = beanObj.getReview_Done_By();
			arg[6] = beanObj.getFrom();
			arg[7] = beanObj.getTo();
			arg[8] = "Y";
			arg[9] = beanObj.getRemarks();
		} else if (mode == 12) {
			/*
			 * arg=new Object[9]; arg[1]="0"; arg[2]="0";
			 * arg[3]=DropDownControllor.GetDesginationCountry(beanObj.
			 * getUpdate_Rivised_original_Cur(),beanObj.getExc_Rate());
			 * arg[4]=beanObj.getUpdate_Reference(); arg[5]=beanObj.getDate();
			 * arg[6]=beanObj.getClaim_No(); arg[7]=beanObj.getPolicy_Contract_No();
			 * arg[8]=beanObj.getRemarks();
			 */

			arg = new Object[31];
			arg[1] = "0";
			arg[2] = "0";
			arg[3] = DropDownControllor.GetDesginationCountry("0", beanObj.getExc_Rate());
			arg[4] = beanObj.getUpdate_Reference() == null ? "" : beanObj.getUpdate_Reference();
			arg[5] = beanObj.getCliam_update_Date();
			arg[6] = beanObj.getClaim_No();
			arg[7] = beanObj.getPolicy_Contract_No();
			arg[8] = beanObj.getRemarks();
			arg[9] = beanObj.getBranchCode();
			arg[10] = beanObj.getLoginId();
			arg[11] = beanObj.getUpdatesurveyorAdjesterPerOC() == null ? "0" : beanObj.getUpdatesurveyorAdjesterPerOC();
			arg[12] = StringUtils.isEmpty(beanObj.getUpdatesurveyorAdjesterPerOC())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdatesurveyorAdjesterPerOC(),
									beanObj.getExc_Rate());
			arg[13] = beanObj.getUpdateotherProfessionalPerOc() == null ? "0"
					: beanObj.getUpdateotherProfessionalPerOc();
			arg[14] = StringUtils.isEmpty(beanObj.getUpdateotherProfessionalPerOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdateotherProfessionalPerOc(),
									beanObj.getExc_Rate());
			arg[15] = beanObj.getUpdateibnrPerOc() == null ? "0" : beanObj.getUpdateibnrPerOc();
			arg[16] = StringUtils.isEmpty(beanObj.getUpdateibnrPerOc()) || StringUtils.isEmpty(beanObj.getExc_Rate())
					? "0"
					: DropDownControllor.GetDesginationCountry(beanObj.getUpdateibnrPerOc(), beanObj.getExc_Rate());
			arg[17] = beanObj.getUpdatesurveyorAdjesterOurShareOC() == null ? "0"
					: beanObj.getUpdatesurveyorAdjesterOurShareOC();
			arg[18] = beanObj.getUpdateprofessionalOurShareOc() == null ? "0"
					: beanObj.getUpdateprofessionalOurShareOc();
			arg[19] = beanObj.getUpdateibnrOurShareOc() == null ? "0" : beanObj.getUpdateibnrOurShareOc();
			arg[20] = StringUtils.isEmpty(beanObj.getUpdatesurveyorAdjesterOurShareOC())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdatesurveyorAdjesterOurShareOC(),
									beanObj.getExc_Rate());
			arg[21] = StringUtils.isEmpty(beanObj.getUpdateprofessionalOurShareOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdateprofessionalOurShareOc(),
									beanObj.getExc_Rate());
			arg[22] = StringUtils.isEmpty(beanObj.getUpdateibnrOurShareOc())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getUpdateibnrOurShareOc(),
									beanObj.getExc_Rate());
			arg[23] = beanObj.getLoss_Estimate_Orig_Curr();
			arg[24] = StringUtils.isEmpty(beanObj.getLoss_Estimate_Orig_Curr())
					|| StringUtils.isEmpty(beanObj.getExc_Rate()) ? "0"
							: DropDownControllor.GetDesginationCountry(beanObj.getLoss_Estimate_Orig_Curr(),
									beanObj.getExc_Rate());
			arg[25] = beanObj.getUpdaterecordFees() == null ? "No" : beanObj.getUpdaterecordFees();
			arg[26] = beanObj.getUpdaterecordIbnr() == null ? "No" : beanObj.getUpdaterecordIbnr();
			arg[27] = beanObj.getExc_Rate();
			arg[28] = beanObj.getTotalReserveOSOC() == null ? "0" : beanObj.getTotalReserveOSOC();
			arg[29] = StringUtils.isEmpty(beanObj.getTotalReserveOSOC()) || StringUtils.isEmpty(beanObj.getExc_Rate())
					? "0"
					: DropDownControllor.GetDesginationCountry(beanObj.getTotalReserveOSOC(), beanObj.getExc_Rate());
			arg[30] = beanObj.getReservePositionDate();
		}
		for (int i = 0; i < arg.length; i++) {
			LOGGER.info("args[" + i + "]" + arg[i]);
		}
		final Object[] copiedArray = new Object[arg.length];
		System.arraycopy(arg, 0, copiedArray, 0, arg.length);
		LOGGER.info("CliamBusinessImpl CliamDetailsAruguments || Exit");
		return copiedArray;
	}

	private boolean Mode_Validation(final String paymentRequestNo, final String claimNo) {
		LOGGER.info("CliamBusinessImpl Mode_Validation || Enter");
		boolean modes = false;
		String query = getQuery(DBConstants.CLAIM_SELECT_PAYMENTREQNO);
		LOGGER.info("Select Query====>" + query);
		LOGGER.info("Arg[0]====>" + claimNo);
		LOGGER.info("Arg[1]====>" + paymentRequestNo);
		final int result = this.mytemplate.queryForInt(query, new Object[] { claimNo, paymentRequestNo });
		if (result == 0) {
			modes = true;
		}
		LOGGER.info("Result==>" + modes);
		LOGGER.info("CliamBusinessImpl Mode_Validation || Exit");
		return modes;
	}

	private Object GetSl_no(final String claimNo, final String policyContractNo, final String TableName) {
		LOGGER.info("CliamBusinessImpl GetSl_no || Enter");
		String part1, part2;
		part1 = getQuery(DBConstants.CLAIM_SELECT_MAXSNODTB1);
		part2 = getQuery(DBConstants.CLAIM_SELECT_MAXSNODTB2);
		String query = part1 + " " + TableName + " " + part2;
		LOGGER.info("Select Query====>" + query);
		LOGGER.info("Arg[0]====>" + claimNo);
		LOGGER.info("Arg[1]====>" + policyContractNo);
		String result = (String) this.mytemplate.queryForObject(query, new Object[] { claimNo, policyContractNo },
				String.class);
		LOGGER.info("Select Result=>" + result);
		LOGGER.info("CliamBusinessImpl GetSl_no || Exit");
		return result;
	}

	public double businessValidaion(final ClaimBean formObj, final int mode) {
		LOGGER.info("CliamBusinessImpl businessValidaion || Enter");
		double amount = 0.0;
		String query = "";
		if (mode == 1) {
			query = getQuery(DBConstants.CLAIM_SELECT_LOSSESTIMATEOSOC);
			LOGGER.info("Select Query====>" + query);
			LOGGER.info("Arg[0]====>" + formObj.getClaim_No());
			LOGGER.info("Arg[1]====>" + formObj.getPolicy_Contract_No());
			amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
					new Object[] { formObj.getClaim_No(), formObj.getPolicy_Contract_No() }, String.class));
		}
		if (mode == 2) {
			query = getQuery(DBConstants.CLAIM_SELECT_SUMPAIDAMTOC);
			LOGGER.info("Select Query====>" + query);
			LOGGER.info("Arg[0]====>" + formObj.getClaim_No());
			LOGGER.info("Arg[1]====>" + formObj.getPolicy_Contract_No());
			amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
					new Object[] { formObj.getClaim_No(), formObj.getPolicy_Contract_No() }, String.class));
		}

		if (mode == 3) {
			query = getQuery(DBConstants.CLIAM_SELECT_TOTALAMTPAIDTILLDATE);
			LOGGER.info("Select query====>" + query);
			LOGGER.info("Arg[0]====>" + formObj.getClaim_No());
			LOGGER.info("Arg[1]====>" + formObj.getPolicy_Contract_No());
			amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
					new Object[] { formObj.getClaim_No(), formObj.getPolicy_Contract_No() }, String.class));
		}
		if (mode == 4) {
			query = getQuery(DBConstants.CLAIM_SELECT_LOSSESTREV);
			LOGGER.info("Select Query====>" + query);
			LOGGER.info("arg[0]=>" + formObj.getPolicy_Contract_No() + " args[1]=>" + formObj.getClaim_No());
			amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
					new Object[] { formObj.getPolicy_Contract_No(), formObj.getClaim_No() }, String.class));
			LOGGER.info("LossEstimate Result=>" + amount);
		}
		if (mode == 5) {
			query = getQuery(DBConstants.CLAIM_SELECT_REVSUMPAIDAMT);
			LOGGER.info("Select Query==>" + query);
			LOGGER.info("arg[0]=>" + formObj.getClaim_No() + " args[1]=>" + formObj.getClaim_No());
			amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
					new Object[] { formObj.getClaim_No(), formObj.getPolicy_Contract_No() }, String.class));
			LOGGER.info("Select RevPaidAmount OC=>" + amount);
		} else if (mode == 7) {
			try {

				if ("edit".equalsIgnoreCase(formObj.getPaymentFlag())) {
					query = getQuery("CLAIM_LOSS_ESTIMATE_PAID_DIFFERENCE_EDIT");
					amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
							new Object[] { formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getClaimPaymentNo(), formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getPolicy_Contract_No(), formObj.getClaim_No() },
							String.class));

				} else {
					query = getQuery(DBConstants.CLAIM_LOSS_ESTIMATE_PAID_DIFFERENCE);
					amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
							new Object[] { formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getPolicy_Contract_No(), formObj.getClaim_No() },
							String.class));
				}
				LOGGER.info("Select Query==>" + query);
				LOGGER.info("arg[0]=>" + formObj.getPolicy_Contract_No() + " args[1]=>" + formObj.getClaim_No());
			} catch (Exception e) {
				LOGGER.debug("Exception in CLAIM_LOSS_ESTIMATE_PAID_DIFFERENCE " + e);
			}
			LOGGER.info("Select CLAIM_LOSS_ESTIMATE_PAID_DIFFERENCE=>" + amount);
		} else if (mode == 8) {
			try {
				if ("edit".equalsIgnoreCase(formObj.getPaymentFlag())) {
					query = getQuery("CLAIM_SAF_OS_SUM_DIFFERENCE_EDIT");
					amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
							new Object[] { formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getClaimPaymentNo(), formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getPolicy_Contract_No(), formObj.getClaim_No() },
							String.class));

				} else {
					query = getQuery(DBConstants.CLAIM_SAF_OS_SUM_DIFFERENCE);
					LOGGER.info("Select Query==>" + query);
					LOGGER.info("arg[0]=>" + formObj.getPolicy_Contract_No() + " args[1]=>" + formObj.getClaim_No());
					amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
							new Object[] { formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getPolicy_Contract_No(), formObj.getClaim_No() },
							String.class));
				}
			} catch (Exception e) {
				LOGGER.debug("Exception in CLAIM_SAF_OS_SUM_DIFFERENCE " + e);
			}
			LOGGER.info("Select CLAIM_SAF_OS_SUM_DIFFERENCE=>" + amount);
		} else if (mode == 9) {
			try {
				if ("edit".equalsIgnoreCase(formObj.getPaymentFlag())) {
					query = getQuery("CLAIM_OTHER_FEE_OS_SUM_EDIT");
					amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
							new Object[] { formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getClaimPaymentNo(), formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getPolicy_Contract_No(), formObj.getClaim_No() },
							String.class));

				} else {
					query = getQuery(DBConstants.CLAIM_OTHER_FEE_OS_SUM_DIFFERENCE);
					LOGGER.info("Select Query==>" + query);
					LOGGER.info("arg[0]=>" + formObj.getPolicy_Contract_No() + " args[1]=>" + formObj.getClaim_No());
					amount = Double.parseDouble((String) this.mytemplate.queryForObject(query,
							new Object[] { formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getPolicy_Contract_No(), formObj.getClaim_No(),
									formObj.getPolicy_Contract_No(), formObj.getClaim_No() },
							String.class));
				}
			} catch (Exception e) {
				LOGGER.debug("Exception in CLAIM_OTHER_FEE_OS_SUM_DIFFERENCE " + e);
			}
			LOGGER.info("Select CLAIM_OTHER_FEE_OS_SUM_DIFFERENCE=>" + amount);
		}
		LOGGER.info("Select Result=>" + amount);
		LOGGER.info("CliamBusinessImpl businessValidaion || Exit");
		return amount;
	}

	private String getExcRateForCliam(final String claimNo, final String policyContractNo) {
		LOGGER.info("CliamBusinessImpl getExcRateForCliam || Enter");
		String excRate = "";
		String query = getQuery(DBConstants.CLAIM_SELECT_EXCRATE);
		LOGGER.info("Select query====>" + query);
		LOGGER.info("Arg[0]====>" + claimNo);
		LOGGER.info("Arg[1]====>" + policyContractNo);
		excRate = (String) this.mytemplate.queryForObject(query, new Object[] { claimNo, policyContractNo },
				String.class);
		LOGGER.info("CliamBusinessImpl getExcRateForCliam || Exit excRate=>" + excRate);
		return excRate;
	}

	public String getClaimStatus(ClaimBean formObj) {

		LOGGER.info("CliamBusinessImpl getClaimStatus || Enter");
		String status = "";
		try {
			String query = getQuery(DBConstants.CLAIM_SELECT_CLAIMSTATUS);
			LOGGER.info("Select query=>" + query);
			LOGGER.info("Arg[0]==>" + formObj.getPolicy_Contract_No());
			LOGGER.info("Arg[1]==>" + formObj.getClaim_No());
			status = (String) this.mytemplate.queryForObject(query,
					new Object[] { formObj.getPolicy_Contract_No(), formObj.getClaim_No() }, String.class);
			LOGGER.info("CliamBusinessImpl getClaimStatus || Exit excRate=>" + status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public String getClaimDates(ClaimBean formObj, int mode) {
		LOGGER.info("CliamBusinessImpl getClaimDates() || Enter");
		String date = "";
		try {
			if (mode == 1) {
				String query = getQuery(DBConstants.CLAIM_SELECT_GETINSDATE);
				LOGGER.info("Select Query =>" + query);
				LOGGER.info("Args[0]&Args[1] =>" + formObj.getClaim_No());
				date = (String) this.mytemplate.queryForObject(query,
						new Object[] { formObj.getClaim_No(), formObj.getClaim_No() }, String.class);
			} else if (mode == 2) {
				String query = getQuery(DBConstants.CLAIM_SELECT_GETLOSSDATE);
				LOGGER.info("Select Query =>" + query);
				LOGGER.info("Args[0]=>" + formObj.getClaim_No());
				date = (String) this.mytemplate.queryForObject(query, new Object[] { formObj.getClaim_No() },
						String.class);
			} else if (mode == 3) {
				String query = getQuery(DBConstants.CLAIM_SELECT_GETCONTINSDATE);
				LOGGER.info("Select Query =>" + query);
				LOGGER.info("Args[0]&Args[1]=>" + formObj.getPolicy_Contract_No());
				date = (String) this.mytemplate.queryForObject(query,
						new Object[] { formObj.getPolicy_Contract_No(), formObj.getPolicy_Contract_No() },
						String.class);
			} else if (mode == 4) {
				String query = getQuery(DBConstants.CLAIM_SELECT_GETLASTPAYMENTDT);
				LOGGER.info("Select Query =>" + query);
				LOGGER.info("Args[0]=?" + formObj.getClaim_No() + "Args[1]=>" + formObj.getPolicy_Contract_No());
				List list = this.mytemplate.queryForList(query,
						new Object[] { formObj.getClaim_No(), formObj.getPolicy_Contract_No() });
				if (list == null && list.size() > 0)
					date = ((Map) list.get(0)).get("PAY_DT").toString();
			} else if (mode == 5) {
				String query = getQuery("claim.lost.reserve.updateDate");
				LOGGER.info("Select Query ==> " + query);
				LOGGER.info("Args[0]==> " + formObj.getPolicy_Contract_No());
				LOGGER.info("Args[1]==> " + formObj.getClaim_No());
				LOGGER.info("Args[2]==> " + formObj.getBranchCode());

				date = (String) this.mytemplate.queryForObject(query, new Object[] { formObj.getPolicy_Contract_No(),
						formObj.getClaim_No(), formObj.getBranchCode() }, String.class);
			}
		} catch (Exception e) {
			LOGGER.debug("Exception " + e);
		}
		LOGGER.info("CliamBusinessImpl getClaimDates() || Exit Department Date=>" + date);
		return date;
	}

	public String getShortname(ClaimBean bean) {
		String result = "";
		result = apiclaim.getShortName(bean.getBranchCode());

		return result;
	}

	public String getCurrencyID(String claimNo, String contractNo, String branchCode) {
		String Currencyid = "";
		Currencyid = (String) this.mytemplate.queryForObject(getQuery("GET_CURRENCY"),
				new Object[] { claimNo, contractNo, branchCode }, String.class);
		return Currencyid;
	}

	public List<Map<String, Object>> allocationList(ClaimBean bean) {
		// LOGGER.info("CliamBusinessImpl getClaimDates() || Enter");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = apiclaim.allocationList(bean);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> allocationList = (Map<String, Object>) list.get(i);
			bean.setTotalAmount(allocationList.get("TotalAmount") == null ? "" : allocationList.get("TotalAmount").toString());
			bean.setPaid_Amount_Orig_curr(allocationList.get("PaidAmountOrigcurr") == null ? "": allocationList.get("PaidAmountOrigcurr").toString());
		}
		return list;
	}

	/*
	 * Double a=0.0; try{ String query=getQuery(DBConstants.CLAIM_ALLOCATION_LIST);
	 * LOGGER.info("Select Query =>"+query);
	 * LOGGER.info("Args[0]&Args[1] =>"+bean.getContractNo());
	 * //date=this.mytemplate.queryForList(query,new
	 * Object[]{bean.getClaim_No(),bean.getTransactionNumber(),bean.getClaim_No(),
	 * bean.getTransactionNumber()}); date=this.mytemplate.queryForList(query,new
	 * Object[]{bean.getContractNo(),bean.getTransactionNumber(),bean.getContractNo(
	 * ),bean.getTransactionNumber()}); if (date.size()>0) { for (int i = 0; i <
	 * date.size(); i++) { Map<String,Object> resMap = date.get(i);
	 * a=a+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get(
	 * "PAID_AMOUNT").toString()); } } if(a>0){
	 * bean.setTotalAmount(DropDownControllor.formatter(Double.toString(a))); }
	 * else{ bean.setTotalAmount(""); } }catch(Exception e){
	 * LOGGER.debug("Exception "+e); }
	 * LOGGER.info("CliamBusinessImpl getClaimDates() || Exit Department Date=>"
	 * +date); return null; }
	 */

	public List allocList(final ClaimBean bean) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = apiclaim.allocList(bean);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> allocList = (Map<String, Object>) list.get(i);
				bean.setDate(allocList.get("Date") == null ? "" : allocList.get("Date").toString());
				bean.setSNo(allocList.get("SNo") == null ? "" : allocList.get("SNo").toString());
				bean.setTransactionType(allocList.get("TransactionType") == null ? "" : allocList.get("TransactionType").toString());
				// bean.setTransactionNumber(allocList.get("TransactionNumber")==null?"":allocList.get("TransactionNumber").toString());
				bean.setPaid_Amount_Orig_curr(allocList.get("PaidAmountOrigcurr") == null ? "": allocList.get("PaidAmountOrigcurr").toString());
				bean.setStatus_of_claim(allocList.get("Statusofclaim") == null ? "" : allocList.get("Statusofclaim").toString());
				bean.setProductName(allocList.get("ProductName") == null ? "" : allocList.get("ProductName").toString());
				bean.setType(allocList.get("Type") == null ? "" : allocList.get("Type").toString());
				bean.setSign(allocList.get("Sign") == null ? "" : allocList.get("Sign").toString());
				bean.setReceiptNo(allocList.get("ReceiptNo") == null ? "" : allocList.get("ReceiptNo").toString());
			}
		return list;
	}

	/*
	 * String query=""; List allocists=new ArrayList();
	 * 
	 * try{ query=getQuery(DBConstants.CLAIM_ALLOCATION_LIST);
	 * LOGGER.info("Select Query====>"+query); Object arg[]=new Object[4];
	 * arg[0]=bean.getContractNo(); arg[1]=bean.getTransactionNumber();
	 * arg[2]=bean.getContractNo(); arg[3]=bean.getTransactionNumber();
	 * allocists=this.mytemplate.query(query,arg,new RowMapper() { public Object
	 * mapRow(final ResultSet result,final int arg1)throws SQLException { final
	 * ClaimBean bean=new ClaimBean();
	 * bean.setDate(result.getString("INCEPTION_DATE"));
	 * bean.setSNo(result.getString("SNO"));
	 * bean.setTransactionType(result.getString("TRANS_TYPE"));
	 * //bean.setTransactionNumber(result.getString("TRANSACTION_NO"));
	 * bean.setPaid_Amount_Orig_curr(result.getString("PAID_AMOUNT"));
	 * bean.setStatus_of_claim(result.getString("STATUS"));
	 * bean.setProductName(result.getString("PRODUCT_NAME"));
	 * bean.setType(result.getString("TYPE"));
	 * bean.setSign(result.getString("sign"));
	 * if(bean.getSign().equalsIgnoreCase("-1")){ bean.setSign("-"); } else
	 * if(bean.getSign().equalsIgnoreCase("1")){ bean.setSign("+"); } else{
	 * bean.setSign(""); } bean.setReceiptNo(result.getString("RECEIPT_NO")); return
	 * bean; //return null; } }); }catch(Exception e){ LOGGER.debug("Exception "+e);
	 * } LOGGER.info("CliamBusinessImpl getClaimDates() || Exit Department Date=>");
	 * return allocists; } public String getUnderwritter(ClaimBean bean) { String
	 * status="";
	 * 
	 * try { String query =
	 * "select RSK_UNDERWRITTER from TTRN_RISK_DETAILS TRD where RSK_CONTRACT_NO=? and RSK_LAYER_NO=nvl(?,0) and RSK_DEPTID=? and RSK_ENDORSEMENT_NO=(select max(RSK_ENDORSEMENT_NO) from TTRN_RISK_DETAILS where RSK_CONTRACT_NO=TRD.RSK_CONTRACT_NO and RSK_LAYER_NO=TRD.RSK_LAYER_NO and RSK_DEPTID=TRD.RSK_DEPTID)"
	 * ; status= (String)this.mytemplate.queryForObject(query,new
	 * Object[]{bean.getContarctno(),bean.getLayerNo(),bean.getDepartmentId()},
	 * String.class); System.out.println(status); } catch(Exception e) {
	 * LOGGER.debug("Exception @ { " + e + " } "); e.printStackTrace(); }
	 */

	public List<ClaimBean> claimlist(ClaimBean bean) {
		List<Map<String, Object>> allocists = new ArrayList<Map<String, Object>>();
		List<ClaimBean> cliamlists = new ArrayList<ClaimBean>();
		String query = "";
		try {
			if (bean.getFlag().equalsIgnoreCase("claim")) {
				query = getQuery(DBConstants.PARTIAL_CLAIM_SELECT_GETCLAIMMASTERLIST);
			} else {
				query = getQuery(DBConstants.CLAIM_SELECT_CLAIMTABLELIST_CLAIMMASTER);
			}
			if ("S".equalsIgnoreCase(bean.getSearchType())) {
				if (StringUtils.isNotBlank(bean.getCompanyNameSearch())) {
					query += " AND UPPER(CUSTOMER_NAME) LIKE UPPER('%" + bean.getCompanyNameSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getBrokerNameSearch())) {
					query += " AND UPPER(BROKER_NAME) LIKE UPPER('%" + bean.getBrokerNameSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getContractNoSearch())) {
					query += " AND CONTRACT_NO LIKE ('%" + bean.getContractNoSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getClaimNoSearch())) {
					query += " AND CLAIM_NO LIKE ('%" + bean.getClaimNoSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getDateOfLossSearch())) {
					query += " AND DATE_OF_LOSS LIKE ('%" + bean.getDateOfLossSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getClaimStatusSearch())) {
					query += " AND UPPER(STATUS_OF_CLAIM) LIKE UPPER('%" + bean.getClaimStatusSearch() + "%')";
				}
			} else {
				bean.setCompanyNameSearch("");
				bean.setBrokerNameSearch("");
				bean.setDateOfLossSearch("");
				bean.setClaimStatusSearch("");
				bean.setContractNoSearch("");
				bean.setClaimNoSearch("");
			}
			// query="SELECT CLAIM_NO,TO_CHAR(DATE_OF_LOSS,'DD/MM/YYYY') AS DATE_OF_LOSS
			// ,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') AS
			// CREATED_DATE,LAYER_NO,STATUS_OF_CLAIM,CONTRACT_NO,(TOTAL_AMT_PAID_TILL_DATE-LOSS_ESTIMATE_OS_OC)
			// AS EDITVIEW FROM TTRN_CLAIM_DETAILS ORDER BY CLAIM_NO DESC";
			LOGGER.info("Select Query====>" + query);
			Object arg[] = new Object[1];
			arg[0] = bean.getBranchCode();
			allocists = this.mytemplate.queryForList(query, arg);
			for (int i = 0; i < allocists.size(); i++) {
				Map<String, Object> tempMap = (Map<String, Object>) allocists.get(i);
				ClaimBean tempBean = new ClaimBean();
				tempBean.setClaim_No(tempMap.get("CLAIM_NO") == null ? "" : tempMap.get("CLAIM_NO").toString());
				tempBean.setDate_of_Loss(
						tempMap.get("DATE_OF_LOSS") == null ? "" : tempMap.get("DATE_OF_LOSS").toString());
				tempBean.setCreated_Date(
						tempMap.get("CREATED_DATE") == null ? "" : tempMap.get("CREATED_DATE").toString());
				tempBean.setStatus_of_claim(
						tempMap.get("STATUS_OF_CLAIM") == null ? "" : tempMap.get("STATUS_OF_CLAIM").toString());
				tempBean.setPolicy_Contract_No(
						tempMap.get("CONTRACT_NO") == null ? "" : tempMap.get("CONTRACT_NO").toString());
				tempBean.setEditMode(tempMap.get("editview") == null ? "" : tempMap.get("editview").toString());
				tempBean.setLayerNo(tempMap.get("layer_no") == null ? "" : tempMap.get("layer_no").toString());
				tempBean.setProposal_No(
						tempMap.get("Proposal_no") == null ? "" : tempMap.get("Proposal_no").toString());
				tempBean.setCeding_company_Name(
						tempMap.get("Customer_name") == null ? "" : tempMap.get("Customer_name").toString());
				tempBean.setBroker_Name(
						tempMap.get("Broker_name") == null ? "" : tempMap.get("Broker_name").toString());
				tempBean.setInception_Date(
						tempMap.get("INCEPTION_DATE") == null ? "" : tempMap.get("INCEPTION_DATE").toString());
				tempBean.setExpiryDate(tempMap.get("Expiry_date") == null ? "" : tempMap.get("Expiry_date").toString());
				tempBean.setProductId(tempMap.get("Product_id") == null ? "" : tempMap.get("Product_id").toString());
				tempBean.setProductName(
						tempMap.get("TMAS_PRODUCT_NAME") == null ? "" : tempMap.get("TMAS_PRODUCT_NAME").toString());
				tempBean.setDepartmentId(tempMap.get("DEPT_ID") == null ? "" : tempMap.get("DEPT_ID").toString());
				int count = new DropDownControllor().Validatethree(bean.getBranchCode(), tempBean.getCreated_Date());
				int claimpaymentcount = new DropDownControllor().getCliampaymnetCount(tempBean.getClaim_No(),
						tempBean.getPolicy_Contract_No());

				if (count != 0 && claimpaymentcount == 0) {
					tempBean.setDeleteStatus("Y");
				}
				cliamlists.add(tempBean);
			}

		} catch (Exception e) {
			LOGGER.debug("Exception " + e);
		}
		LOGGER.info("CliamBusinessImpl getClaimDates() || Exit Department Date=>");
		return cliamlists;
	}

	public List<Map<String, Object>> productIdList(ClaimBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		LOGGER.info("Enter ino product List");
		try {
			String query = getQuery("GET_PRODUC_ID_LIST");
			Object[] args = new String[1];
			args[0] = bean.getBranchCode();

			LOGGER.info("Select Query ==> " + query);
			list = this.mytemplate.queryForList(query, args);

		} catch (Exception e) {
			LOGGER.debug("Exception " + e);
		}
		LOGGER.info("Exit ino product List");
		return list;
	}

	public List<ClaimBean> contractidetifierlist(ClaimBean bean) {
		String query = "";
		List<Map<String, Object>> allocists = new ArrayList<Map<String, Object>>();
		List<ClaimBean> finalList = new ArrayList<ClaimBean>();
		LOGGER.info("Enter into PremiumList");
		try {
			query = getQuery(DBConstants.CONTRACT_IDENTIFIER_LIST);
			LOGGER.info("Select Query====>" + query);
			Object arg[] = new Object[8];
			arg[0] = bean.getProductId();
			arg[1] = bean.getProductId();
			arg[2] = bean.getBranchCode();
			arg[3] = bean.getBranchCode();
			arg[4] = bean.getBranchCode();
			arg[5] = bean.getBranchCode();
			arg[6] = bean.getProductId();
			arg[7] = bean.getBranchCode();
			if (!"N".equalsIgnoreCase(bean.getCeding_Company_Code())
					&& !"".equalsIgnoreCase(bean.getCeding_Company_Code())) {
				query += "AND D.RSK_CEDINGID = " + bean.getCeding_Company_Code() + "";
			}
			if (!"N".equalsIgnoreCase(bean.getBroker_code()) && !"".equalsIgnoreCase(bean.getBroker_code())) {
				query += " AND D.RSK_BROKERID =" + bean.getBroker_code() + "";
			}
			if (!"N".equalsIgnoreCase(bean.getUnderwritingYear()) && !"".equalsIgnoreCase(bean.getUnderwritingYear())) {
				query += "AND D.RSK_UWYEAR =" + bean.getUnderwritingYear() + "";
			}
			if (!"N".equalsIgnoreCase(bean.getDeptId()) && !"".equalsIgnoreCase(bean.getDeptId())) {
				query += "AND D.RSK_DEPTID=" + bean.getDeptId() + "";
			}
			query += "ORDER BY A.CONTRACT_NO DESC";
			allocists = this.mytemplate.queryForList(query, arg);
			for (int i = 0; i < allocists.size(); i++) {
				Map<String, Object> tempMap = (Map<String, Object>) allocists.get(i);
				ClaimBean tempBean = new ClaimBean();
				tempBean.setProposal_No(
						tempMap.get("PROPOSAL_NO") == null ? "" : tempMap.get("PROPOSAL_NO").toString());
				tempBean.setContractNo(tempMap.get("CONTRACT_NO") == null ? "" : tempMap.get("CONTRACT_NO").toString());
				tempBean.setCeding_company_Name(
						tempMap.get("COMPANY_NAME") == null ? "" : tempMap.get("COMPANY_NAME").toString());
				tempBean.setBroker_Name(
						tempMap.get("BROKER_NAME") == null ? "" : tempMap.get("BROKER_NAME").toString());
				tempBean.setLayerNo(tempMap.get("LAYER_NO") == null ? "" : tempMap.get("LAYER_NO").toString());
				tempBean.setTransactionNumber(
						tempMap.get("TRANSACTION_NO") == null ? "" : tempMap.get("TRANSACTION_NO").toString());
				tempBean.setProductId(bean.getProductId());
				tempBean.setDeptId(tempMap.get("TMAS_DEPARTMENT_NAME") == null ? ""
						: tempMap.get("TMAS_DEPARTMENT_NAME").toString());
				tempBean.setExpiryDate(tempMap.get("EXPIRY_DATE") == null ? "" : tempMap.get("EXPIRY_DATE").toString());
				tempBean.setInception_Date(
						tempMap.get("INCEPTION_DATE") == null ? "" : tempMap.get("INCEPTION_DATE").toString());
				tempBean.setTransactionDate(
						tempMap.get("TRANSACTION_DATE") == null ? "" : tempMap.get("TRANSACTION_DATE").toString());
				tempBean.setUnderwritingYear(tempMap.get("UW_YEAR") == null ? "" : tempMap.get("UW_YEAR").toString());
				tempBean.setUnderwriter(
						tempMap.get("UNDERWRITTER") == null ? "" : tempMap.get("UNDERWRITTER").toString());
				tempBean.setOld_Contract(
						tempMap.get("OLD_CONTRACTNO") == null ? "" : tempMap.get("OLD_CONTRACTNO").toString());
				tempBean.setClaim_No(tempMap.get("CLAIM_NO") == null ? "" : tempMap.get("CLAIM_NO").toString());
				tempBean.setDepartmentId(tempMap.get("DEPT_ID") == null ? "" : tempMap.get("DEPT_ID").toString());
				tempBean.setClaimCount(tempMap.get("COUNT") == null ? "" : tempMap.get("COUNT").toString());

				finalList.add(tempBean);
			}

		} catch (Exception e) {
			LOGGER.debug("Exception " + e);
		}
		LOGGER.info("CliamBusinessImpl getClaimDates() || Exit Department Date=>");
		return finalList;
	}

	public List<ClaimBean> claimpaymentlist(ClaimBean bean) {
		String query = "";
		List<Map<String, Object>> allocists = new ArrayList<Map<String, Object>>();
		List<ClaimBean> finalList = new ArrayList<ClaimBean>();
		try {
			if (bean.getFlag().equalsIgnoreCase("claim")) {
				query = getQuery(DBConstants.PARTIAL_CLAIM_SELECT_GETCLAIMPAYMENTMASTERLIST);
			} else {
				query = getQuery(DBConstants.CLAIM_SELECT_GETCLAIMPAYMENTMASTERLIST);
			}

			if ("S".equalsIgnoreCase(bean.getSearchType())) {
				if (StringUtils.isNotBlank(bean.getCompanyNameSearch())) {
					query += " AND UPPER(CUSTOMER_NAME) LIKE UPPER('%" + bean.getCompanyNameSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getBrokerNameSearch())) {
					query += " AND UPPER(BROKER_NAME) LIKE UPPER('%" + bean.getBrokerNameSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getContractNoSearch())) {
					query += " AND CONTRACT_NO LIKE ('%" + bean.getContractNoSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getClaimNoSearch())) {
					query += " AND CLAIM_NO LIKE ('%" + bean.getClaimNoSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getPaymentNoSearch())) {
					query += " AND CLAIM_PAYMENT_NO LIKE ('%" + bean.getPaymentNoSearch() + "%')";
				}
				if (StringUtils.isNotBlank(bean.getPaymentDateSearch())) {
					query += " AND INCEPTION_DT LIKE ('%" + bean.getPaymentDateSearch() + "%')";
				}
			} else {
				bean.setCompanyNameSearch("");
				bean.setBrokerNameSearch("");
				bean.setPaymentNoSearch("");
				bean.setPaymentDateSearch("");
				bean.setContractNoSearch("");
				bean.setClaimNoSearch("");
			}
			Object arg[] = new Object[1];
			arg[0] = bean.getBranchCode();
			// LOGGER.info("Select Query====>"+query);
			allocists = this.mytemplate.queryForList(query, arg);
			for (int i = 0; i < allocists.size(); i++) {
				Map<String, Object> tempMap = (Map<String, Object>) allocists.get(i);
				ClaimBean tempBean = new ClaimBean();
				tempBean.setClaim_No(tempMap.get("CLAIM_NO") == null ? "" : tempMap.get("CLAIM_NO").toString());
				tempBean.setPolicy_Contract_No(
						tempMap.get("CONTRACT_NO") == null ? "" : tempMap.get("CONTRACT_NO").toString());
				tempBean.setLayerNo(tempMap.get("layer_no") == null ? "" : tempMap.get("layer_no").toString());
				tempBean.setProposal_No(
						tempMap.get("Proposal_no") == null ? "" : tempMap.get("Proposal_no").toString());
				tempBean.setCeding_company_Name(
						tempMap.get("Customer_name") == null ? "" : tempMap.get("Customer_name").toString());
				tempBean.setBroker_Name(
						tempMap.get("Broker_name") == null ? "" : tempMap.get("Broker_name").toString());
				tempBean.setInception_Date(
						tempMap.get("INCEPTION_DATE") == null ? "" : tempMap.get("INCEPTION_DATE").toString());
				tempBean.setExpiryDate(tempMap.get("Expiry_date") == null ? "" : tempMap.get("Expiry_date").toString());
				tempBean.setProductId(tempMap.get("Product_id") == null ? "" : tempMap.get("Product_id").toString());
				tempBean.setProductName(
						tempMap.get("TMAS_PRODUCT_NAME") == null ? "" : tempMap.get("TMAS_PRODUCT_NAME").toString());
				tempBean.setPayment_Request_No(
						tempMap.get("PAYMENT_REQUEST_NO") == null ? "" : tempMap.get("PAYMENT_REQUEST_NO").toString());
				tempBean.setPaid_Amount_Orig_curr(DropDownControllor.formatter(
						tempMap.get("PAID_AMOUNT_OC") == null ? "" : tempMap.get("PAID_AMOUNT_OC").toString()));
				tempBean.setLoss_Estimate_Revised_Orig_Curr(
						DropDownControllor.formatter(tempMap.get("LOSS_ESTIMATE_REVISED_OC") == null ? ""
								: tempMap.get("LOSS_ESTIMATE_REVISED_OC").toString()));
				tempBean.setDate(tempMap.get("INCEPTION_DT") == null ? "" : tempMap.get("INCEPTION_DT").toString());
				tempBean.setClaimPaymentNo(
						tempMap.get("CLAIM_PAYMENT_NO") == null ? "" : tempMap.get("CLAIM_PAYMENT_NO").toString());
				tempBean.setSNo(tempMap.get("RESERVE_ID") == null ? "" : tempMap.get("RESERVE_ID").toString());
				tempBean.setSettlementStatus(
						tempMap.get("SETTLEMENT_STATUS") == null ? "" : tempMap.get("SETTLEMENT_STATUS").toString());
				tempBean.setTransactionType(
						tempMap.get("TRANS_TYPE") == null ? "" : tempMap.get("TRANS_TYPE").toString());
				tempBean.setTransactionNumber(
						tempMap.get("RECEIPT_NO") == null ? "" : tempMap.get("RECEIPT_NO").toString());
				tempBean.setDepartmentId(tempMap.get("DEPT_ID") == null ? "" : tempMap.get("DEPT_ID").toString());
				String maxno = "";
				try {
					int count = this.mytemplate.queryForInt(
							"select count(*) allocatedYN from TTRN_ALLOCATED_TRANSACTION where CONTRACT_NO =? and TRANSACTION_NO=? and LAYER_NO=?  and STATUS='Y'",
							new Object[] { tempBean.getPolicy_Contract_No(), tempBean.getClaim_No(),
									tempBean.getLayerNo() });
					if (count == 0) {
						bean.setAllocatedYN("N");
					} else if (count >= 1) {
						bean.setAllocatedYN("Y");
					}
					tempBean.setStatus_of_claim(
							(String) this.mytemplate.queryForObject(getQuery("GET_STATUS_OF_CLAIM"), new Object[] {
									tempBean.getPolicy_Contract_No(), tempBean.getClaim_No(), tempBean.getLayerNo() },
									String.class));
					String que = "SELECT MAX(SL_NO) FROM TTRN_CLAIM_UPDATION WHERE  CLAIM_NO=? AND  CONTRACT_NO=?";
					maxno = this.mytemplate.queryForObject(que,
							new Object[] { tempBean.getClaim_No(), tempBean.getPolicy_Contract_No() }, String.class);
				} catch (Exception e) {
					LOGGER.debug("Exception " + e);
				}
				if (StringUtils.isNotBlank(tempBean.getDate())) {
					if (new DropDownControllor().Validatethree(bean.getBranchCode(), tempBean.getDate()) == 0) {
						tempBean.setTransOpenperiodStatus("N");
					} else if (!tempMap.get("RESERVE_ID").toString().equalsIgnoreCase(maxno)) {
						tempBean.setTransOpenperiodStatus("N");
					} else {
						tempBean.setTransOpenperiodStatus("Y");
					}
				}
				finalList.add(tempBean);
			}
		} catch (Exception e) {
			LOGGER.debug("Exception " + e);
		}
		return finalList;
	}

	public String cedentNumberCheck(ClaimBean bean) {
		String result = "0";
		String query = "";
		Object args[] = null;
		try {
			if (StringUtils.isBlank(bean.getClaim_No())) {
				query = getQuery("CEDENT_BASED_MAX_CLAIM_NUMBER");
				args = new Object[2];
				args[0] = bean.getCedentClaimNo();
				args[1] = bean.getBranchCode();
				result = (String) this.mytemplate.queryForObject(query, args, String.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("Exception " + e);
		}
		return result;
	}

	public int claimpaymentCount(ClaimBean bean) {
		int count = 0;
		try {
			String query = getQuery("GET_CLAIM_COUNT");
			Object args[] = new Object[2];
			args[0] = bean.getPolicy_Contract_No();
			args[1] = bean.getClaim_No();
			count = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("Exception " + e);
		}
		return count;
	}

	public int insertAggregate(final ClaimBean beanObj) {
		int status = 0;
		// insert for aggregate/accumulation details
		String query = "";
		Object[] args = null;
		int spresult = 0;
		try {
			query = getQuery("INSERT_ACCUMULATION_DATA");
			LOGGER.info("aggregate/accumulation details insert Name==>" + query);
			args = new String[13];
			args[0] = beanObj.getClaim_No();
			args[1] = beanObj.getContarctno();
			args[2] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();
			args[3] = beanObj.getDepartmentId();
			args[4] = beanObj.getRisk_Code();
			args[5] = beanObj.getAccumulation_Code();
			args[6] = beanObj.getEvent_Code();
			args[7] = beanObj.getClaim_No();
			args[8] = beanObj.getContarctno();
			args[9] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();
			args[10] = beanObj.getDepartmentId();
			args[11] = beanObj.getBranchCode();
			args[12] = beanObj.getLoginId();

			LOGGER.info("Args==>" + StringUtils.join(args, ","));
			spresult = this.mytemplate.update(query, args);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("aggregate/accumulation details insert Result==>" + spresult);
		return status;
	}

	public List<Map<String, Object>> XlPremiumList(ClaimBean bean, String countryId) {
		String query = "";
		Object[] args = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> claimBookedPremium = new ArrayList<String>();
		List<String> claimExcRate = new ArrayList<String>();
		List<String> claimPremOC = new ArrayList<String>();
		List<String> claimPremDC = new ArrayList<String>();
		List<String> fielddisable = new ArrayList<String>();
		String rate = "";
		String transactionDate = "";
		String currency = "";
		try {
			query = getQuery("XL_COST_LIST");

			args = new Object[4];
			args[0] = bean.getProductId();
			args[1] = bean.getBranchCode();

			args[2] = bean.getPolicy_Contract_No();
			args[3] = bean.getLayerNo();
			list = this.mytemplate.queryForList(query, args);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					claimPremOC.add(map.get("PREMIUM_OC") == null ? ""
							: DropDownControllor.formatter(map.get("PREMIUM_OC").toString()));
					claimPremDC.add(map.get("PREMIUM_DC") == null ? ""
							: DropDownControllor.formatter(map.get("PREMIUM_DC").toString()));
					transactionDate = map.get("TRANSACTION_MONTH_YEAR") == null ? ""
							: map.get("TRANSACTION_MONTH_YEAR").toString();
					currency = map.get("CURRENCY_ID") == null ? "" : map.get("CURRENCY_ID").toString();
					if (currency.equalsIgnoreCase(bean.getCurrecny())) {
						fielddisable.add("Y");
					} else {
						fielddisable.add("N");
					}
					String ExchRate = new DropDownControllor().GetExchangeRate(currency, transactionDate, countryId,
							bean.getBranchCode());
					// String ClaimExchageRate= new
					// DropDownControllor().GetExchangeRate(bean.getCurrecny(),bean.getDate(),countryId,bean.getBranchCode());//for
					// ReInstatement Premium Automatic calculation V6 document changes
					String ClaimExchageRate = new DropDownControllor().GetExchangeRate(bean.getCurrecny(),
							transactionDate, countryId, bean.getBranchCode());
					rate = Double.toString(Double.parseDouble(ClaimExchageRate) / Double.parseDouble(ExchRate));
					claimExcRate.add(DropDownControllor.formattereight(rate));
					claimBookedPremium.add(DropDownControllor.formatter(Double.toString(
							Double.parseDouble(claimPremOC.get(i).replaceAll(",", "")) * Double.parseDouble(rate))));
				}
			}
			bean.setClaimBookedPremium(claimBookedPremium);
			bean.setClaimExcRate(claimExcRate);
			bean.setClaimPremOC(claimPremOC);
			bean.setClaimPremDC(claimPremDC);
			bean.setBaseExchRate(claimExcRate);
			bean.setFielddisableXlPrm(fielddisable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> ClaimPaidList(ClaimBean bean, String countryId) {
		String query = "";
		Object[] args = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> reinsNumList = new ArrayList<Map<String, Object>>();
		List<String> claimBookedPremium = new ArrayList<String>();
		List<String> claimExcRate = new ArrayList<String>();
		List<String> claimPremOC = new ArrayList<String>();
		List<String> claimPremDC = new ArrayList<String>();
		List<String> reinsNum = new ArrayList<String>();
		List<String> claimNoList = new ArrayList<String>();
		List<String> claimPaymentNoList = new ArrayList<String>();
		List<String> departmentNameList = new ArrayList<String>();
		List<String> inceptionDateList = new ArrayList<String>();
		List<String> currencyNameList = new ArrayList<String>();
		List<String> rdsCurrencyNameList = new ArrayList<String>();
		List<String> fielddisable = new ArrayList<String>();

		String rate = "";
		String transactionDate = "";
		String currency = "";
		try {
			query = getQuery("XL_CLAIM_DETAILS");
			args = new Object[7];
			args[0] = bean.getProductId();
			args[1] = bean.getPolicy_Contract_No();
			args[2] = bean.getLayerNo();
			args[3] = bean.getBranchCode();
			args[4] = bean.getDate();
			args[5] = bean.getDate();
			args[6] = StringUtils.isBlank(bean.getClaimPaymentNo()) ? "0" : bean.getClaimPaymentNo();
			list = this.mytemplate.queryForList(query, args);
			Map<String, Object> hmap = new HashMap<String, Object>();
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					hmap = new HashMap<String, Object>();
					hmap.put("NUMBER", Integer.toString(i + 1));
					reinsNumList.add(hmap);
					// Map<Str>
					// reinsNum.add(Integer.toString(i+1));
					reinsNum.add(Integer.toString(i + 1));
					claimNoList.add(map.get("CLAIM_NO") == null ? "" : map.get("CLAIM_NO").toString());
					claimPaymentNoList
							.add(map.get("CLAIM_PAYMENT_NO") == null ? "" : map.get("CLAIM_PAYMENT_NO").toString());
					departmentNameList.add(
							map.get("TMAS_DEPARTMENT_NAME") == null ? "" : map.get("TMAS_DEPARTMENT_NAME").toString());
					inceptionDateList
							.add(map.get("INCEPTION_DATE") == null ? "" : map.get("INCEPTION_DATE").toString());
					currencyNameList.add(map.get("CURRENCY_NAME") == null ? "" : map.get("CURRENCY_NAME").toString());
					rdsCurrencyNameList
							.add(map.get("RDS_CURRENCY_NAME") == null ? "" : map.get("RDS_CURRENCY_NAME").toString());
					String currencyId = map.get("RSK_ORIGINAL_CURR") == null ? ""
							: map.get("RSK_ORIGINAL_CURR").toString();
					claimPremOC.add(map.get("PAID_EXCHANGE_RATE_OC") == null ? ""
							: DropDownControllor.formatter(map.get("PAID_EXCHANGE_RATE_OC").toString()));
					claimPremDC.add(map.get("PAID_EXCHANGE_RATE_DC") == null ? ""
							: DropDownControllor.formatter(map.get("PAID_EXCHANGE_RATE_DC").toString()));
					transactionDate = map.get("INCEPTION_DATE") == null ? "" : map.get("INCEPTION_DATE").toString();
					currency = map.get("CURRENCY") == null ? "" : map.get("CURRENCY").toString();

					String ExchRate = new DropDownControllor().GetExchangeRate(currency, transactionDate, countryId,
							bean.getBranchCode());
					// String ClaimExchageRate=
					// map.get("EXCHANGE_RATE")==null?"":map.get("EXCHANGE_RATE").toString();
					String ClaimExchageRate = new DropDownControllor().GetExchangeRate(currencyId,
							inceptionDateList.get(i), countryId, bean.getBranchCode());
					rate = Double.toString(Double.parseDouble(ClaimExchageRate) / Double.parseDouble(ExchRate));
					claimExcRate.add(DropDownControllor.formattereight(rate));
					bean.setRdsCurrency(
							map.get("RDS_CURRENCY_NAME") == null ? "" : map.get("RDS_CURRENCY_NAME").toString());
					bean.setRdsCurrencyId(
							map.get("RSK_ORIGINAL_CURR") == null ? "" : map.get("RSK_ORIGINAL_CURR").toString());
					claimBookedPremium.add(DropDownControllor.formatter(Double.toString(
							Double.parseDouble(claimPremOC.get(i).replaceAll(",", "")) * Double.parseDouble(rate))));
					if (currencyNameList.get(i).equalsIgnoreCase(bean.getRdsCurrency())) {
						fielddisable.add("Y");
					} else {
						fielddisable.add("N");
					}
				}
			}

			String ExchRate = new DropDownControllor().GetExchangeRate(bean.getCurrecny(), bean.getDate(), countryId,
					bean.getBranchCode());
			if (StringUtils.isBlank(bean.getRdsCurrencyId())) {
				String qry = getQuery("GET_RDS_CURRENCY_ID");
				args = new Object[5];
				args[0] = bean.getPolicy_Contract_No();
				args[1] = bean.getLayerNo();
				args[2] = bean.getProductId();
				args[3] = bean.getBranchCode();
				args[4] = bean.getProposal_No();
				bean.setRdsCurrencyId(this.mytemplate.queryForObject(qry, args, String.class));
			}
			String claimrate = new DropDownControllor().GetExchangeRate(bean.getRdsCurrencyId(), bean.getDate(),
					countryId, bean.getBranchCode());
			claimrate = Double.toString(Double.parseDouble(claimrate) / Double.parseDouble(ExchRate));
			String total_oc = DropDownControllor
					.formatter(Double.toString(Double.parseDouble(bean.getPaid_claim_os().replaceAll(",", ""))
							+ Double.parseDouble(bean.getSurveyor_fee_os().replaceAll(",", ""))
							+ Double.parseDouble(bean.getOther_prof_fee_os().replaceAll(",", ""))));
			String total_dc = DropDownControllor.formatter(DropDownControllor
					.GetDesginationCountry(total_oc.replaceAll(",", ""), ExchRate.replaceAll(",", "")));
			if (StringUtils.isBlank(bean.getRdsCurrency())) {
				String qry = getQuery("RDS_CURRENCY_NAME");
				Object arg[] = new Object[5];
				arg[0] = bean.getProposal_No();
				arg[1] = bean.getPolicy_Contract_No();
				arg[2] = bean.getLayerNo();
				arg[3] = bean.getProductId();
				arg[4] = bean.getBranchCode();
				bean.setRdsCurrency(this.mytemplate.queryForObject(qry, arg, String.class));
			}
			String Query = getQuery("premium.select.currency.short");
			Object argu[] = new Object[2];
			argu[0] = bean.getCurrecny();
			argu[1] = bean.getBranchCode();
			bean.setCurrencyName(this.mytemplate.queryForObject(Query, argu, String.class));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("CLAIM_NO", bean.getClaim_No());
			map.put("INCEPTION_DATE", bean.getDate());
			map.put("CURRENCY_NAME", bean.getCurrencyName());
			map.put("PAID_EXCHANGE_RATE_OC", total_oc);
			map.put("PAID_EXCHANGE_RATE_DC", total_dc);
			map.put("CLAIM_PAYMENT_NO", "This Trxn");
			map.put("RDS_CURRENCY_NAME", bean.getRdsCurrency());
			map.put("TMAS_DEPARTMENT_NAME", bean.getDepartmentName());
			claimNoList.add(bean.getClaim_No());
			claimPaymentNoList.add("This Trxn");
			departmentNameList.add(bean.getDepartmentName());
			inceptionDateList.add(bean.getDate());
			currencyNameList.add(bean.getCurrencyName());
			rdsCurrencyNameList.add(bean.getRdsCurrency());
			list.add(map);
			claimPremOC.add(total_oc);
			claimPremDC.add(total_dc);

			if (bean.getCurrencyName().equalsIgnoreCase(bean.getRdsCurrency())) {
				fielddisable.add("Y");
			} else {
				fielddisable.add("N");
			}
			claimExcRate.add(DropDownControllor.formattereight(claimrate));
			reinsNum.add(Integer.toString(list.size()));
			claimBookedPremium
					.add(DropDownControllor.formatter(Double.toString(Double.parseDouble(total_oc.replaceAll(",", ""))
							* Double.parseDouble(claimrate.replaceAll(",", "")))));
			hmap = new HashMap<String, Object>();
			hmap.put("NUMBER", Integer.toString(list.size()));
			reinsNumList.add(hmap);

			bean.setClaimPaidPremium(claimBookedPremium);
			bean.setClaimEcxhangeRate(claimExcRate);
			bean.setClaimPaidOC(claimPremOC);
			bean.setClaimPaidDC(claimPremDC);
			bean.setReinsNum(reinsNum);

			bean.setClaimBaseExchRate(claimExcRate);
			bean.setPaidSno(reinsNum);
			bean.setClaimNoList(claimNoList);
			bean.setClaimPaymentNoList(claimPaymentNoList);
			bean.setRdsCurrencyNameList(rdsCurrencyNameList);
			bean.setCurrencyNameList(currencyNameList);
			bean.setDepartmentNameList(departmentNameList);
			bean.setInceptionDateList(inceptionDateList);
			bean.setReinsNumList(reinsNumList);
			bean.setFielddisableClaimsPaid(fielddisable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getReInstatementTypeList(ClaimBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String query = "";
		Object args[] = null;
		String type = "";
		double preSum = 0;
		double sum = 0;
		int paidDivCover = 0;
		int prePaidDivCover = 0;
		String coverNo = "0";
		int presize = 0;
		int j = 1;

		List<String> reinsNum = new ArrayList<String>();
		List<String> paidSno = new ArrayList<String>();
		List<String> claimNoList = new ArrayList<String>();
		List<String> claimPaymentNoList = new ArrayList<String>();
		List<String> departmentNameList = new ArrayList<String>();
		List<String> inceptionDateList = new ArrayList<String>();
		List<String> currencyNameList = new ArrayList<String>();
		List<String> rdsCurrencyNameList = new ArrayList<String>();
		List<String> claimPaidOC = new ArrayList<String>();
		List<String> claimPaidDC = new ArrayList<String>();
		List<String> claimEcxhangeRate = new ArrayList<String>();
		List<String> claimPaidPremium = new ArrayList<String>();
		List<String> paymentCoverNum = new ArrayList<String>();
		List<String> paymentCoverPlus = new ArrayList<String>();
		for (int i = 0; i < bean.getReinsNum().size(); i++) {

			reinsNum.add("0");
			claimNoList.add("0");
			claimPaymentNoList.add("0");
			departmentNameList.add("0");
			inceptionDateList.add("0");
			currencyNameList.add("0");
			rdsCurrencyNameList.add("0");
			claimPaidOC.add("0");
			claimPaidDC.add("0");
			claimEcxhangeRate.add("0");
			claimPaidPremium.add("0");
			paidSno.add("0");
		}

		try {
			query = getQuery("GET_RIP_TYPE");
			args = new Object[5];
			args[0] = bean.getPolicy_Contract_No();
			args[1] = bean.getLayerNo();
			args[2] = bean.getBranchCode();
			args[3] = bean.getProductId();
			args[4] = bean.getDepartmentId();
			type = this.mytemplate.queryForObject(query, args, String.class);
			for (int i = 0; i < bean.getReinsNum().size(); i++) {
				int val = Integer.parseInt(bean.getPaidSno().get(i)) - 1;
				reinsNum.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1, Integer.toString(i + 1));
				claimNoList.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1, bean.getClaimNoList().get(val));
				claimPaymentNoList.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1,
						bean.getClaimPaymentNoList().get(val).replaceAll(",", ""));
				if ("This Trxn".equalsIgnoreCase(bean.getClaimPaymentNoList().get(val))) {
					bean.setCurrencyName(bean.getCurrencyNameList().get(val));
				}
				departmentNameList.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1,
						bean.getDepartmentNameList().get(val));
				inceptionDateList.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1,
						bean.getInceptionDateList().get(val));
				currencyNameList.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1,
						bean.getCurrencyNameList().get(val));
				rdsCurrencyNameList.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1,
						bean.getRdsCurrencyNameList().get(val));
				claimPaidOC.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1, bean.getClaimPaidOC().get(val));
				claimPaidDC.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1, bean.getClaimPaidDC().get(val));
				claimEcxhangeRate.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1,
						bean.getClaimEcxhangeRate().get(val));
				claimPaidPremium.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1,
						bean.getClaimPaidPremium().get(val));
				paidSno.set(Integer.parseInt(bean.getReinsNum().get(i)) - 1, bean.getPaidSno().get(i));
			}
			bean.setReinsNum(reinsNum);
			bean.setClaimNoList(claimNoList);
			bean.setDepartmentNameList(departmentNameList);
			bean.setInceptionDateList(inceptionDateList);
			bean.setCurrencyNameList(currencyNameList);
			bean.setRdsCurrencyNameList(rdsCurrencyNameList);
			bean.setClaimPaidOC(claimPaidOC);
			bean.setClaimPaidDC(claimPaidDC);
			bean.setClaimEcxhangeRate(claimEcxhangeRate);
			bean.setClaimPaidPremium(claimPaidPremium);
			bean.setClaimPaymentNoList(claimPaymentNoList);
			bean.setPaidSno(paidSno);
			reinsNum = new ArrayList<String>();
			claimNoList = new ArrayList<String>();
			claimPaymentNoList = new ArrayList<String>();
			departmentNameList = new ArrayList<String>();
			inceptionDateList = new ArrayList<String>();
			currencyNameList = new ArrayList<String>();
			rdsCurrencyNameList = new ArrayList<String>();
			claimPaidOC = new ArrayList<String>();
			claimPaidDC = new ArrayList<String>();
			claimEcxhangeRate = new ArrayList<String>();
			claimPaidPremium = new ArrayList<String>();
			paidSno = new ArrayList<String>();
			if ("S".equalsIgnoreCase(type)) {
				query = getQuery("COVER_SUM_INSURED_VAL");
				args = new Object[7];
				args[0] = bean.getProposal_No();
				args[1] = bean.getLayerNo();
				args[2] = bean.getBranchCode();
				args[3] = bean.getProposal_No();
				args[4] = bean.getLayerNo();
				args[5] = bean.getPolicy_Contract_No();
				args[6] = bean.getBranchCode();
				String val = this.mytemplate.queryForObject(query, args, String.class);
				// df.format(Double.parseDouble(val));
				val = DropDownControllor.formatter(val).replaceAll(",", "");
				for (int i = 0; i < bean.getReinsNum().size(); i++) {
					preSum = sum;
					String preSumVal = DropDownControllor.formatter(Double.toString(sum)).replaceAll(",", "");
					sum += Double.parseDouble(bean.getClaimPaidPremium().get(i).replaceAll(",", ""));
					String sumVal = DropDownControllor.formatter(Double.toString(sum)).replaceAll(",", "");

					if (Double.parseDouble(val) >= Double.parseDouble(sumVal)) {
						reinsNum.add(bean.getReinsNum().get(i));
						claimNoList.add(bean.getClaimNoList().get(i));
						claimPaymentNoList.add(bean.getClaimPaymentNoList().get(i));
						departmentNameList.add(bean.getDepartmentNameList().get(i));
						inceptionDateList.add(bean.getInceptionDateList().get(i));
						currencyNameList.add(bean.getCurrencyNameList().get(i));
						rdsCurrencyNameList.add(bean.getRdsCurrencyNameList().get(i));
						claimPaidOC.add(bean.getClaimPaidOC().get(i));
						claimPaidDC.add(bean.getClaimPaidDC().get(i));
						claimEcxhangeRate.add(bean.getClaimEcxhangeRate().get(i));
						claimPaidPremium.add(bean.getClaimPaidPremium().get(i));
						paymentCoverNum.add(coverNo);
						paymentCoverPlus.add(Integer.toString(Integer.parseInt(coverNo) + 1));
						paidSno.add(bean.getPaidSno().get(i));

					} else {
						paidDivCover = (int) (Double.parseDouble(sumVal) / Double.parseDouble(val));
						prePaidDivCover = (int) (Double.parseDouble(preSumVal) / Double.parseDouble(val));
						if (paidDivCover != prePaidDivCover) {
							double step1 = Double.parseDouble(val) * (prePaidDivCover + 1);
							double step2 = Double.parseDouble(sumVal) - step1;
							double step3 = Double.parseDouble(bean.getClaimPaidPremium().get(i).replaceAll(",", ""))
									- step2;
							claimPaidPremium.add(DropDownControllor.formatter(Double.toString(step3)));
							claimPaidPremium.add(DropDownControllor.formatter(Double.toString(step2)));
							double step4 = step3
									/ Double.parseDouble(bean.getClaimPaidPremium().get(i).replaceAll(",", ""));
							double paidDc = Double.parseDouble(bean.getClaimPaidDC().get(i).replaceAll(",", ""))
									* step4;
							claimPaidDC.add(DropDownControllor.formatter(Double.toString(paidDc)));
							double step6 = step2
									/ Double.parseDouble(bean.getClaimPaidPremium().get(i).replaceAll(",", ""));
							double paidDc1 = Double.parseDouble(bean.getClaimPaidDC().get(i).replaceAll(",", ""))
									* step6;
							claimPaidDC.add(DropDownControllor.formatter(Double.toString(paidDc1)));

							double paidDc11 = Double.parseDouble(bean.getClaimPaidOC().get(i).replaceAll(",", ""))
									* step4;
							claimPaidOC.add(DropDownControllor.formatter(Double.toString(paidDc11)));
							double step61 = step2
									/ Double.parseDouble(bean.getClaimPaidPremium().get(i).replaceAll(",", ""));
							double paidDc21 = Double.parseDouble(bean.getClaimPaidOC().get(i).replaceAll(",", ""))
									* step61;
							claimPaidOC.add(DropDownControllor.formatter(Double.toString(paidDc21)));

							coverNo = Integer.toString(Integer.parseInt(coverNo) + 1);
							paidSno.add(bean.getPaidSno().get(i));
							paidSno.add(bean.getPaidSno().get(i));

							for (int k = 0; k < 2; k++) {
								reinsNum.add(bean.getReinsNum().get(i));
								claimNoList.add(bean.getClaimNoList().get(i));
								claimPaymentNoList.add(bean.getClaimPaymentNoList().get(i));
								departmentNameList.add(bean.getDepartmentNameList().get(i));
								inceptionDateList.add(bean.getInceptionDateList().get(i));
								currencyNameList.add(bean.getCurrencyNameList().get(i));
								rdsCurrencyNameList.add(bean.getRdsCurrencyNameList().get(i));
								// claimPaidOC.add(bean.getClaimPaidOC().get(i));
								claimEcxhangeRate.add(bean.getClaimEcxhangeRate().get(i));
								if (0 == k) {
									paymentCoverNum.add(Integer.toString(Integer.parseInt(coverNo) - 1));
									paymentCoverPlus.add(coverNo);
								} else {
									paymentCoverNum.add(coverNo);
									paymentCoverPlus.add(Integer.toString(Integer.parseInt(coverNo) + 1));
								}
							}
						} else {
							reinsNum.add(bean.getReinsNum().get(i));
							claimNoList.add(bean.getClaimNoList().get(i));
							claimPaymentNoList.add(bean.getClaimPaymentNoList().get(i));
							departmentNameList.add(bean.getDepartmentNameList().get(i));
							inceptionDateList.add(bean.getInceptionDateList().get(i));
							currencyNameList.add(bean.getCurrencyNameList().get(i));
							rdsCurrencyNameList.add(bean.getRdsCurrencyNameList().get(i));
							claimPaidOC.add(bean.getClaimPaidOC().get(i));
							claimPaidDC.add(bean.getClaimPaidDC().get(i));
							claimEcxhangeRate.add(bean.getClaimEcxhangeRate().get(i));
							claimPaidPremium.add(bean.getClaimPaidPremium().get(i));
							paymentCoverNum.add(coverNo);
							paymentCoverPlus.add(Integer.toString(Integer.parseInt(coverNo) + 1));
							paidSno.add(bean.getPaidSno().get(i));
						}
					}
					// bean.setReinsNum(reinsNum);
				}
			}
			for (int i = 0; i < reinsNum.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("0", "");
				list.add(map);
			}
			bean.setReinsNum(reinsNum);
			bean.setClaimNoList(claimNoList);
			bean.setDepartmentNameList(departmentNameList);
			bean.setInceptionDateList(inceptionDateList);
			bean.setCurrencyNameList(currencyNameList);
			bean.setRdsCurrencyNameList(rdsCurrencyNameList);
			bean.setClaimPaidOC(claimPaidOC);
			bean.setClaimPaidDC(claimPaidDC);
			bean.setClaimEcxhangeRate(claimEcxhangeRate);
			bean.setClaimPaidPremium(claimPaidPremium);
			bean.setPaymentCoverNum(paymentCoverNum);
			bean.setPaymentCoverPlus(paymentCoverPlus);
			bean.setClaimPaymentNoList(claimPaymentNoList);
			bean.setPaidSno(paidSno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getReInsValue(ClaimBean bean) {
		String query = "";
		Object args[] = null;
		double result = 0;
		String finalVal = "";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < bean.getClaimPaymentNoList().size(); i++) {
				if ("This Trxn".equalsIgnoreCase(bean.getClaimPaymentNoList().get(i))) {
					query = getQuery("GET_RIP_VALUES");
					args = new Object[5];
					args[0] = bean.getProposal_No();
					args[1] = bean.getPolicy_Contract_No();
					args[2] = bean.getLayerNo();
					args[3] = bean.getBranchCode();
					args[4] = bean.getPaymentCoverPlus().get(i);
					list = this.mytemplate.queryForList(query, args);
					if (list.size() > 0) {
						for (int j = 0; j < list.size(); j++) {
							Map<String, Object> map = list.get(j);
							String type = map.get("REINST_TYPE") == null ? "0"
									: map.get("REINST_TYPE").toString().replaceAll(",", "");
							// String totalNoCol =
							// map.get("COUNT")==null?"0":map.get("COUNT").toString().replaceAll(",", "");
							String totalNoCol = StringUtils.isBlank(bean.getTotalBookedPremium()) ? "0"
									: bean.getTotalBookedPremium().replaceAll(",", "");
							String amtPer = map.get("AMOUNT_PERCENT") == null ? "0"
									: map.get("AMOUNT_PERCENT").toString().replaceAll(",", "");
							String minAmt = map.get("MIN_AMOUNT_PERCENT") == null ? "0"
									: map.get("MIN_AMOUNT_PERCENT").toString().replaceAll(",", "");
							String time = map.get("MIN_TIME_PERCENT") == null ? "0"
									: map.get("MIN_TIME_PERCENT").toString().replaceAll(",", "");
							double AmountPer = Double.parseDouble(amtPer) / 100;
							String val = getSumInsuredVal(bean);
							String total = Double
									.toString(Double.parseDouble(bean.getClaimPaidOC().get(i).replaceAll(",", ""))
											/ Double.parseDouble(val.replaceAll(",", "")));
							// total=DropDownControllor.formatter(total);

							if ("PRA".equalsIgnoreCase(type)) {
								minAmt = Double.toString(Double.parseDouble(minAmt) / 100);
								double amount = maxValue(Double.parseDouble(minAmt), Double.parseDouble(total));
								double ans = Double.parseDouble(StringUtils.isBlank(bean.getTotalBookedPremium()) ? "0"
										: bean.getTotalBookedPremium().replaceAll(",", "")) * amount;
								ans = ans * AmountPer;
								result += ans;
							} else if ("PRT".equalsIgnoreCase(type)) {
								double dateCom = getDateDetails(bean);
								double minTime = Double.parseDouble(time) / 100;
								double amount = maxValue(dateCom, minTime);
								double ans = Double.parseDouble(totalNoCol) * Double.parseDouble(total) * amount;
								ans = ans * AmountPer;
								result += ans;
							} else if ("PRAT".equalsIgnoreCase(type)) {
								minAmt = Double.toString(Double.parseDouble(minAmt) / 100);
								double amount = maxValue(Double.parseDouble(minAmt), Double.parseDouble(total));
								// double ans = Double.parseDouble(totalNoCol)*amount ;
								double ans = 0;
								// ans = ans*AmountPer;
								// result +=ans;
								double dateCom = getDateDetails(bean);
								double minTime = Double.parseDouble(time) / 100;
								double amount1 = maxValue(dateCom, minTime);
								double ans1 = Double.parseDouble(totalNoCol) * amount1 * amount;
								ans = ans1 * AmountPer;
								result += ans;
							} else if ("FREE".equalsIgnoreCase(type)) {
								result += AmountPer;
							}
						}
					}

				}
			}
			finalVal = DropDownControllor.formatter(Double.toString(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalVal;
	}

	private double getDateDetails(ClaimBean bean) {
		String query = "";
		Object[] args = null;
		String inceptionDate = "";
		double ans = 0;
		String ExpDate = "";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			query = getQuery("GET_DATE_OF_LOSS");
			args = new Object[4];
			args[0] = bean.getClaim_No();
			args[1] = bean.getPolicy_Contract_No();
			args[2] = bean.getLayerNo();
			args[3] = bean.getBranchCode();
			String dateOfLoss = this.mytemplate.queryForObject(query, args, String.class);
			query = getQuery("GET_RDS_DATE");
			args = new Object[4];
			args[0] = bean.getProposal_No();
			args[1] = bean.getPolicy_Contract_No();
			args[2] = bean.getLayerNo();
			args[3] = bean.getBranchCode();
			list = this.mytemplate.queryForList(query, args);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					inceptionDate = map.get("RSK_INCEPTION_DATE") == null ? ""
							: map.get("RSK_INCEPTION_DATE").toString();
					ExpDate = map.get("RSK_EXPIRY_DATE") == null ? "" : map.get("RSK_EXPIRY_DATE").toString();
				}
			}
			String format = "dd/MM/yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			java.util.Date incep = sdf.parse(inceptionDate);
			java.util.Date exp = sdf.parse(ExpDate);
			java.util.Date lossDate = sdf.parse(dateOfLoss);
			double diffInDays = (double) ((exp.getTime() - incep.getTime()) / (1000 * 60 * 60 * 24));
			diffInDays = diffInDays + 1;
			double diff = (double) ((exp.getTime() - lossDate.getTime()) / (1000 * 60 * 60 * 24));

			ans = diff / diffInDays;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	private double maxValue(double minAmt, double rdsAmt) {
		try {
			if (minAmt < rdsAmt) {
				minAmt = rdsAmt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return minAmt;
	}

	private String getSumInsuredVal(ClaimBean bean) {
		String val = "", query = "";
		Object args[] = null;
		try {
			query = getQuery("COVER_SUM_INSURED_VAL");
			args = new Object[7];
			args[0] = bean.getProposal_No();
			args[1] = bean.getLayerNo();
			args[2] = bean.getBranchCode();
			args[3] = bean.getProposal_No();
			args[4] = bean.getLayerNo();
			args[5] = bean.getPolicy_Contract_No();
			args[6] = bean.getBranchCode();
			val = this.mytemplate.queryForObject(query, args, String.class);
			// val = DropDownControllor.formatter(val);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	public String getProposalNo(ClaimBean bean) {
		String query = "";
		String proposal_No = "";
		Object[] obj = null;
		if (StringUtils.isNotBlank(bean.getProductId()) && "1".equals(bean.getProductId())) {
			query = getQuery("GET_FAC_PROPOSAL_NO");
			obj = new Object[1];
			obj[0] = bean.getContarctno();
			// proposal_No=this.mytemplate.queryForObject(query, obj , String.class);
		} else if ("2".equals(bean.getProductId())) {
			query = getQuery("GET_PRO_PROPOSAL_NO");
			obj = new Object[2];
			obj[0] = bean.getContarctno();
			obj[1] = bean.getDepartmentId();
			// proposal_No=this.mytemplate.queryForObject(query, obj , String.class);
		} else if ("3".equals(bean.getProductId())) {
			query = getQuery("GET_XOL_PROPOSAL_NO");
			obj = new Object[2];
			obj[0] = bean.getContarctno();
			obj[1] = bean.getLayerNo();
			// proposal_No=this.mytemplate.queryForObject(query, obj , String.class);
		}
		List<Map<String, Object>> list = this.mytemplate.queryForList(query, obj);
		if (list != null && list.size() > 0) {
			Map<String, Object> map = list.get(0);
			proposal_No = map.get("PROPOSAL_NO") == null ? "" : map.get("PROPOSAL_NO").toString();
			if (!"2".equalsIgnoreCase(bean.getProductId())) {
				bean.setDepartmentId(map.get("DEPT_ID") == null ? "" : map.get("DEPT_ID").toString());
			}
		}

		return proposal_No;
	}

	public void claimPaymentEdit(ClaimBean bean) {
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			String query = getQuery("GET_CLAIM_PAYMENT_DATA");
			Object args[] = new Object[4];
			args[0] = bean.getClaim_No();
			args[1] = bean.getContarctno();
			args[2] = StringUtils.isBlank(bean.getLayerNo())?"0":bean.getLayerNo();
			args[3] = bean.getClaimPaymentNo();
			list = this.mytemplate.queryForList(query, args);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					bean.setDate(map.get("INCEPTION_DATE") == null ? "" : map.get("INCEPTION_DATE").toString());
					bean.setPayment_Request_No(
							map.get("PAYMENT_REQUEST_NO") == null ? "" : map.get("PAYMENT_REQUEST_NO").toString());
					bean.setPayment_Reference(
							map.get("PAYMENT_REFERENCE") == null ? "" : map.get("PAYMENT_REFERENCE").toString());
					bean.setPaid_claim_os(map.get("PAID_CLAIM_OS_OC") == null ? ""
							: DropDownControllor.formatter(map.get("PAID_CLAIM_OS_OC").toString()));
					bean.setSurveyor_fee_os(map.get("SAF_OS_OC") == null ? ""
							: DropDownControllor.formatter(map.get("SAF_OS_OC").toString()));
					bean.setOther_prof_fee_os(map.get("OTH_FEE_OS_OC") == null ? ""
							: DropDownControllor.formatter(map.get("OTH_FEE_OS_OC").toString()));
					bean.setPaid_Amount_Orig_curr(map.get("PAID_AMOUNT_OC") == null ? ""
							: DropDownControllor.formatter(map.get("PAID_AMOUNT_OC").toString()));
					bean.setRemarks(map.get("REMARKS") == null ? "" : map.get("REMARKS").toString());
					bean.setClaimPaymentNo(
							map.get("CLAIM_PAYMENT_NO") == null ? "" : map.get("CLAIM_PAYMENT_NO").toString());
					if ("paymentView".equalsIgnoreCase(bean.getDropDown())) {
						bean.setReinstType(map.get("REINSTATEMENT_TYPE_NAME") == null ? ""
								: map.get("REINSTATEMENT_TYPE_NAME").toString());
					} else {
						bean.setReinstType(
								map.get("REINSTATEMENT_TYPE") == null ? "" : map.get("REINSTATEMENT_TYPE").toString());

					}
					bean.setReinstPremiumOCOS(map.get("REINSPREMIUM_OURSHARE_OC") == null ? ""
							: map.get("REINSPREMIUM_OURSHARE_OC").toString());
					bean.setPaymentType(map.get("PAYMENT_TYPE") == null ? "" : map.get("PAYMENT_TYPE").toString());

					// bean.setDate(map.get("")==null?"":map.get("").toString());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getContractNO(ClaimBean bean) {

		String query = "";
		String ContractNo = "";
		Object[] obj = null;
		try {
			query = getQuery("GET_CONTRACT_NUMBER");
			if (StringUtils.isNotBlank(bean.getContarctno())) {
				query += " and CONTRACT_NO = " + bean.getContarctno();
			}
			obj = new Object[1];
			obj[0] = bean.getClaim_No();
			List<Map<String, Object>> list = this.mytemplate.queryForList(query, obj);
			if (list != null && list.size() > 0) {
				Map<String, Object> map = list.get(0);
				ContractNo = map.get("CONTRACT_NO") == null ? "" : map.get("CONTRACT_NO").toString();
				if (!"2".equalsIgnoreCase(bean.getProductId())) {
					bean.setDepartmentId(map.get("CLAIM_CLASS") == null ? "" : map.get("CLAIM_CLASS").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ContractNo;

	}

	public int claimMasterValidation(ClaimBean bean, String type) {
		int count = 0;
		String query = "";
		Object args[] = null;
		try {
			if ("claim".equalsIgnoreCase(type)) {
				args = new Object[1];
				args[0] = bean.getClaim_No();
				query = "SELECT COUNT(*) FROM TTRN_CLAIM_DETAILS WHERE CLAIM_NO=?";
				count = this.mytemplate.queryForInt(query, args);
			} else if ("contract".equalsIgnoreCase(type)) {
				args = new Object[1];
				// args[0]= bean.getClaim_No();
				args[0] = bean.getContarctno();
				query = "SELECT COUNT(*) FROM POSITION_MASTER WHERE CONTRACT_NO =? ";

				count = this.mytemplate.queryForInt(query, args);
			} else if ("class".equalsIgnoreCase(type)) {
				args = new Object[2];
				args[1] = bean.getDepartmentId();
				args[0] = bean.getContarctno();
				query = "SELECT COUNT(*) FROM POSITION_MASTER WHERE CONTRACT_NO=? AND DEPT_ID=?";
				count = this.mytemplate.queryForInt(query, args);
			} else if ("layer".equalsIgnoreCase(type)) {
				args = new Object[2];
				args[1] = bean.getLayerNo();
				args[0] = bean.getContarctno();
				query = "SELECT COUNT(*) FROM POSITION_MASTER WHERE CONTRACT_NO=? AND LAYER_NO=?";
				count = this.mytemplate.queryForInt(query, args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Map<String, Object>> claimNoList(ClaimBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			String query = getQuery("GET_CEDENT_NO_LIST");
			Object args[] = new Object[4];
			args[0] = bean.getCedentClaimNo();
			args[1] = bean.getDate_of_Loss();
			args[2] = bean.getCeding_Company_Code();
			args[3] = bean.getBranchCode();
			if (StringUtils.isNotBlank(bean.getClaim_No())) {
				query += " AND C.CLAIM_NO !=" + bean.getClaim_No();
			}
			list = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getPaymentNoList(ClaimBean bean) {
		LOGGER.info("Entered into getPaymentNoList");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Object[] args = null;
		try {
			String query = getQuery("GET_PAYMENT_NO_LIST");
			args = new Object[] { bean.getBranchCode(), bean.getContarctno(), bean.getLayerNo(), bean.getProposal_No(),
					bean.getCurrecny() };
			LOGGER.info("Select Query==>" + query);
			LOGGER.info("Args==>" + StringUtils.join(args, ","));
			list = this.mytemplate.queryForList(query, args);
			LOGGER.info("Size==>" + list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getClaimAuthView(ClaimBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> gridList = new ArrayList<Map<String, Object>>();
		LOGGER.info("Entered into getPaymentNoList");
		double totalEstimateOc = 0;
		double lossEstimateOsOc = 0;
		String query = "";
		Object[] args = null;
		String paymentNo = "";
		try {
			if (bean.getPaymentNo().contains(",")) {
				query = getQuery("GET_CLAIM_AUTH_MULTIPLE");
				paymentNo = bean.getPaymentNo();
				String arg[] = paymentNo.split(",");
				paymentNo = convertToArg(arg);
				query += "AND TCP.CLAIM_PAYMENT_NO IN (" + paymentNo
						+ ") ) GROUP BY CLAIM_NO, COMPANY_NAME, BROKER_NAME, INSURED_NAME, DATE_OF_LOSS, LOSS_DETAILS, CONTRACT_NO, RSK_INSURED_NAME, OSLR, CURRENCY";
				args = new Object[] { bean.getProposal_No(), bean.getContarctno(), bean.getLayerNo(),
						bean.getCurrecny(), bean.getBranchCode() };
			} else {
				query = getQuery("GET_CLAIM_AUTH_SINGLE");
				paymentNo = bean.getPaymentNo();
				args = new Object[] { bean.getProposal_No(), bean.getContarctno(), bean.getLayerNo(),
						bean.getCurrecny(), bean.getBranchCode(), paymentNo };
			}
			LOGGER.info("queryFrammer  " + queryFrammer(query, args));
			list = this.mytemplate.queryForList(query, args);

			if (bean.getPaymentNo().contains(",")) {
				query = getQuery("GET_CLAIM_AUTH_MULTIPLE_GRID");
				query += " AND TCP.CLAIM_PAYMENT_NO IN (" + paymentNo + ")";
				args = new Object[] { bean.getProposal_No(), bean.getContarctno(), bean.getLayerNo(),
						bean.getCurrecny(), bean.getBranchCode() };
				LOGGER.info(queryFrammer(query, args));
				gridList = this.mytemplate.queryForList(query, args);

				if (gridList.size() > 0) {
					for (int i = 0; i < gridList.size(); i++) {
						totalEstimateOc += Double.valueOf(gridList.get(i).get("LOSS_ESTIMATE_OC").toString());
						lossEstimateOsOc += Double.valueOf(gridList.get(i).get("LOSS_ESTIMATE_OS_OC").toString());
					}
				}
//				bean.setLoss_Estimate_Orig_Curr(String.valueOf(totalEstimateOc));
//				bean.setLoss_Estimate_Our_share_Orig_Curr(String.valueOf(lossEstimateOsOc));
				bean.setLoss_Estimate_Orig_Curr(DropDownControllor.formatter(String.valueOf(totalEstimateOc)));
				bean.setLoss_Estimate_Our_share_Orig_Curr(
						DropDownControllor.formatter(String.valueOf(lossEstimateOsOc)));

				bean.setMultiPaymentNoList(gridList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String convertToArg(String[] arg) {
		String value = "";
		for (int i = 0; i < arg.length; i++) {
			if (i == arg.length - 1) {
				value += "'" + arg[i] + "'";
			} else {
				value += "'" + arg[i] + "',";
			}
		}
		return value;
	}

	public String queryFrammer(String query, Object[] args) {
		try {
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query = query.substring(0, query.indexOf("?")) + "'" + args[i] + "'"
							+ query.substring(query.indexOf("?") + 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	@Override
	public String getDuplicateCedentClaim(ClaimBean bean) {
		List<Map<String, Object>> list = null;
		String claimno = "";
		try {
			String query = getQuery("GET_DUPLICATE_CEDENT_NO_LIST");
			Object args[] = new Object[3];
			args[0] = bean.getBranchCode();
			args[1] = bean.getProposal_No();
			args[2] = bean.getCedentClaimNo();
			if (StringUtils.isNotBlank(bean.getClaim_No())) {
				query += " AND C.CLAIM_NO !=" + bean.getClaim_No();
			}
			list = this.mytemplate.queryForList(query, args);
			if (list != null && list.size() > 0) {
				claimno = list.get(0).get("CLAIM_NO") == null ? "" : list.get(0).get("CLAIM_NO").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return claimno;
	}

	@Override
	public String getUnderwritter(ClaimBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
