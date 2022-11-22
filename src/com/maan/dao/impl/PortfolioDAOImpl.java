package com.maan.dao.impl;

import com.maan.bean.PortfolioBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.PortfolioDAO;
import com.maan.service.PortfolioService;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PortfolioDAOImpl extends MyJdbcTemplate implements PortfolioDAO {
    final org.slf4j.Logger logger = LogUtil.getLogger(PortfolioDAOImpl.class);

    public List<PortfolioBean> getPendingList(final PortfolioBean beanObj,Object MenuRights) {
        List<PortfolioBean> finalList = new ArrayList<PortfolioBean>();
        try {
            String query = "";
            Object[] obj = new Object[4];
            obj[0] = beanObj.getProductId();
            obj[1] = beanObj.getBranchCode();
            obj[2] = beanObj.getBranchCode();
            obj[3] = beanObj.getProductId();
            query = getQuery(DBConstants.PORTFOLIO_SELECT_NTU);
            if (beanObj.getFlag() != null && "N".equalsIgnoreCase(beanObj.getFlag())) {
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_FLAGN);
            } else if("RP".equalsIgnoreCase(beanObj.getFlag())){
                query += "a.CONTRACT_STATUS IS NULL OR a.CONTRACT_STATUS IN ('P','N')" ;
            }else if(!"RP".equalsIgnoreCase(beanObj.getFlag())){
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_FLAGNULL);
            }
            if ("1".equals(beanObj.getProductId())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{beanObj.getBranchCode(), beanObj.getBranchCode(), beanObj.getDeptId(), beanObj.getBranchCode()});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_NTUDEPT);
            } else {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{beanObj.getBranchCode(), beanObj.getBranchCode(), beanObj.getBranchCode()});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_NTUEND);
            }
            /*if(!"superuser".equalsIgnoreCase(beanObj.getUserType())){
                obj=new DropDownControllor().getIncObjectArray(obj,new Object[]{beanObj.getLoginId()});
				query+=" "+getQuery(DBConstants.PORTFOLIO_SELECT_LOGINID);
			}*/
           /* if ("CCN".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_COMNAME);
            }
            if ("BRN".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_BRONAME);
            }
            if ("UWY".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
            }*/
            if(StringUtils.isNotBlank(beanObj.getSearchType()) && null !=beanObj.getSearchType()){
            	if(StringUtils.isNotBlank(beanObj.getProposalNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getProposalNoSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_PERNO);
            	}
            	if(StringUtils.isNotBlank(beanObj.getContractNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getContractNoSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONNO);
            	}
        		if(StringUtils.isNotBlank(beanObj.getCompanyNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getCompanyNameSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_COMNAME);
            	}
        		if(StringUtils.isNotBlank(beanObj.getBrokerNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getBrokerNameSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_BRONAME);
            	}
        		if(StringUtils.isNotBlank(beanObj.getDepartmentNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getDepartmentNameSearch() + "%")});
            		 query += " AND UPPER(B.TMAS_DEPARTMENT_NAME) LIKE UPPER(?)";
            	}
        		if(StringUtils.isNotBlank(beanObj.getBouquetNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getBouquetNoSearch() + "%")});
            		 query += " AND UPPER(A.Bouquet_No) LIKE UPPER(?)";
            	}
        		if(StringUtils.isNotBlank(beanObj.getSubclassSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSubclassSearch() + "%")});
            		 query += " AND UPPER((select RTRIM(XMLAGG(XMLELEMENT(E,TMAS_SPFC_NAME,',')).EXTRACT('//text()'),',')  from TMAS_SPFC_MASTER SPFC where SPFC.TMAS_SPFC_ID in(select * from table(SPLIT_TEXT_FN(replace(E.RSK_SPFCID,' ', '')))) AND  SPFC.TMAS_PRODUCT_ID = E.RSK_PRODUCTID AND SPFC.BRANCH_CODE = E.BRANCH_CODE)) LIKE UPPER(?)";
            	}
            	if("1".equalsIgnoreCase(beanObj.getProductId())){
        		if(StringUtils.isNotBlank(beanObj.getInsuredNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getInsuredNameSearch() + "%")});
            		 query += " AND UPPER(E.RSK_INSURED_NAME) LIKE UPPER(?)";
            	}
            	if(StringUtils.isNotBlank(beanObj.getUwYearSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getUwYearSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
            	}if(StringUtils.isNotBlank(beanObj.getUnderwriterSearch())){
            		String qry ="SELECT UWR_CODE FROM UNDERWRITTER_MASTER WHERE BRANCH_CODE="+beanObj.getBranchCode()+" and UPPER(UNDERWRITTER) like UPPER('%" + beanObj.getUnderwriterSearch()+ "%') and UWR_STATUS='Y'";
            		String res =this.mytemplate.queryForObject(qry,String.class);
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + res + "%")});
            		 query += " AND UPPER(E.RSK_UNDERWRITTER) LIKE UPPER(?) ";
            	}
            	}else{
            		if(StringUtils.isNotBlank(beanObj.getUwYearSearch1())){
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getUwYearSearch1() + "%")});
                        query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
                	}if(StringUtils.isNotBlank(beanObj.getUnderwriterSearch1())){
                		String qry ="SELECT UWR_CODE FROM UNDERWRITTER_MASTER WHERE BRANCH_CODE="+beanObj.getBranchCode()+" and UPPER(UNDERWRITTER) like UPPER('%" + beanObj.getUnderwriterSearch1()+ "%') and UWR_STATUS='Y'";
                		String res =this.mytemplate.queryForObject(qry,String.class);
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + res + "%")});
                		 query += " AND UPPER(E.RSK_UNDERWRITTER) LIKE UPPER(?) ";
                	}
            	}if(StringUtils.isNotBlank(beanObj.getOfferNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getOfferNoSearch() + "%")});
            		query += " AND A.OFFER_NO LIKE ? ";
            		
            	}
            	
            	beanObj.setType("Yes");
            }else{
            	beanObj.setProposalNoSearch("");
            	beanObj.setContractNoSearch("");
            	beanObj.setCompanyNameSearch("");
            	beanObj.setBrokerNameSearch("");
            	beanObj.setDepartmentNameSearch("");
            	beanObj.setInsuredNameSearch("");
            	beanObj.setUwYearSearch("");
            	beanObj.setUnderwriterSearch("");
            	beanObj.setUwYearSearch1("");
            	beanObj.setUnderwriterSearch1("");
            	beanObj.setOfferNoSearch("");
            }
            if ("RP".equalsIgnoreCase(beanObj.getFlag())) {
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_RENEWALPENDING);
            } else {
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_PENDING);
            }
            if(StringUtils.isNotBlank(beanObj.getAttachedUW()) && !"ALL".equalsIgnoreCase(beanObj.getAttachedUW())) {
        		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{(beanObj.getAttachedUW())});
        		 query += " AND UPPER(E.RSK_UNDERWRITTER) IN (select * from table(SPLIT_TEXT_FN(?)))";
        	}
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_ORDERBYPRONO);
            logger.info("Query=>" + query);
            logger.info("Obj[]=>" + StringUtils.join(obj, ","));
            List<Map<String, Object>> list = this.mytemplate.queryForList(query, obj);
            //if(list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> tempMap = list.get(i);
                PortfolioBean tempBean = new PortfolioBean();
                tempBean.setProposalNo(tempMap.get("PROPOSAL_NO") == null ? "" : tempMap.get("PROPOSAL_NO").toString());
                tempBean.setOfferNo(tempMap.get("OFFER_NO") == null ? "" : tempMap.get("OFFER_NO").toString());
                tempBean.setBouquetNo(tempMap.get("Bouquet_No") == null ? "" : tempMap.get("Bouquet_No").toString());
                tempBean.setAmendId(tempMap.get("AMEND_ID") == null ? "" : tempMap.get("AMEND_ID").toString());
                tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME") == null ? "" : tempMap.get("COMPANY_NAME").toString());
                tempBean.setDepartment_Name(tempMap.get("TMAS_DEPARTMENT_NAME") == null ? "" : tempMap.get("TMAS_DEPARTMENT_NAME").toString());
                tempBean.setSubClass(tempMap.get("TMAS_SPFC_NAME") == null ? "" : tempMap.get("TMAS_SPFC_NAME").toString());
                tempBean.setDepartmentId(tempMap.get("TMAS_DEPARTMENT_ID") == null ? "" : tempMap.get("TMAS_DEPARTMENT_ID").toString());
                tempBean.setInception_Date(tempMap.get("INCEPTION_DATE") == null ? "" : tempMap.get("INCEPTION_DATE").toString());
                tempBean.setExpiry_Date(tempMap.get("EXPIRY_DATE") == null ? "" : tempMap.get("EXPIRY_DATE").toString());
                tempBean.setInsuredName(tempMap.get("RSK_INSURED_NAME") == null ? "" : tempMap.get("RSK_INSURED_NAME").toString());
                tempBean.setQuote_Gendrated_date(tempMap.get("ACCOUNT_DATE") == null ? "" : tempMap.get("ACCOUNT_DATE").toString());
                tempBean.setCedding_company_id(tempMap.get("CEDING_COMPANY_ID") == null ? "" : tempMap.get("CEDING_COMPANY_ID").toString());
                tempBean.setLayerNo(tempMap.get("LAYER_NO") == null ? "" : tempMap.get("LAYER_NO").toString());
                if (beanObj.getFlag() != null && "N".equalsIgnoreCase(beanObj.getFlag())) {
                    tempBean.setFlag("N");
                } else if ("RP".equalsIgnoreCase(beanObj.getFlag())) {
                    tempBean.setFlag("RP");
                    tempBean.setTitle("RP");
                   
                } else {
                    tempBean.setFlag("P");
                }
				/*if(tempMap.get("BASE_LAYER")!=null)
					tempBean.setBaseLayer(tempMap.get("BASE_LAYER")==null?"":tempMap.get("BASE_LAYER").toString());
				else
					tempBean.setBaseLayer("");*/
                if (tempMap.get("BASE_LAYER") != null) {
                    tempBean.setBaseLayer(tempMap.get("BASE_LAYER").toString());
                    tempBean.setContractno1(tempMap.get("CONTRACT_NO") == null ? "" : tempMap.get("CONTRACT_NO").toString());
                    tempBean.setLay1("layer");
                } else {
                    tempBean.setBaseLayer("");
                }
                tempBean.setUwYear(tempMap.get("UW_YEAR") == null ? "" : tempMap.get("UW_YEAR").toString());
                tempBean.setUwMonth(tempMap.get("UW_MONTH") == null ? "" : tempMap.get("UW_MONTH").toString());
                tempBean.setUnderwritter(tempMap.get("UNDERWRITTER") == null ? "" : tempMap.get("UNDERWRITTER").toString());
                tempBean.setBrokerName(tempMap.get("BROKER_NAME") == null ? "" : tempMap.get("BROKER_NAME").toString());
                tempBean.setOld_Contract(tempMap.get("OLD_CONTRACTNO") == null ? "" : "0".equals(tempMap.get("OLD_CONTRACTNO")) == true ? "" : tempMap.get("OLD_CONTRACTNO").toString());
                tempBean.setButtonSelectionList(getPendingButtonList(tempBean,MenuRights));
                finalList.add(tempBean);
            }
        } catch (Exception e) {
            logger.debug("Exception @ {" + e + "}");
        }
        return finalList;
    }

    private String emptyIfNull(Object str) {
        return (str == null || str.toString().isEmpty()) ? "" : str.toString().trim();
    }

    public List<PortfolioBean> getAutoPendingList(PortfolioBean beanObj) {
        String query;
        int mode = parseNumber(beanObj.getProductId()).intValue();
        if (mode == 1) {
            query = getQuery(DBConstants.PORTFOLIO_SELECT_AUTOPENDING1);
        } else if (mode == 3) {
            query = getQuery("portfolio.select.autoPendingNPT");
        } else {
            return new ArrayList<PortfolioBean>();
        }
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("pid", beanObj.getProductId());
        param.put("bc", beanObj.getBranchCode());
        param.put("insDate", beanObj.getDueDate());
        logger.info("query : {}", query);
        logger.info("params : {}", param);
        List<PortfolioBean> portfolioBeans = new ArrayList<PortfolioBean>();
        List<Map<String, Object>> list = this.getSimpleJdbcTemplate().queryForList(query, param);
        for (Map<String, Object> tempMap : list) {
            PortfolioBean tempBean = new PortfolioBean();
            if(!"N".equalsIgnoreCase(tempMap.get("EDIT_MODE").toString())){
            	portfolioBeans = new ArrayList<PortfolioBean>();
            	beanObj.setMode("error");
            	break;
            }else{
            tempBean.setProposalNo(emptyIfNull(tempMap.get("RSK_PROPOSAL_NUMBER")));
            tempBean.setContractno1(emptyIfNull(tempMap.get("RSK_CONTRACT_NO")));
            tempBean.setContractNo(emptyIfNull(tempMap.get("RSK_CONTRACT_NO")));
            tempBean.setAmendId(emptyIfNull(tempMap.get("RSK_ENDORSEMENT_NO")));
            tempBean.setCeding_Company_Name(emptyIfNull(tempMap.get("COMPANY")));
            tempBean.setCedding_company_id(emptyIfNull(tempMap.get("CEDING_ID")));
            tempBean.setBrokerName(emptyIfNull(tempMap.get("BROKER")));
            tempBean.setBrokerId(emptyIfNull(tempMap.get("BROKER_ID")));
            tempBean.setDepartmentId(emptyIfNull(tempMap.get("TMAS_DEPARTMENT_ID")));
            tempBean.setDepartment_Name(emptyIfNull(tempMap.get("TMAS_DEPARTMENT_NAME")));
            tempBean.setPremiumClass(emptyIfNull(tempMap.get("RSK_DEPTID")));
            tempBean.setPremiumSubClass(emptyIfNull(tempMap.get("RSK_SPFCID")));
            tempBean.setSubClass(emptyIfNull(tempMap.get("TMAS_SPFC_NAME")));
            tempBean.setCurrencyShort(emptyIfNull(tempMap.get("SHORT_NAME")));
            tempBean.setCurrencyId(emptyIfNull(tempMap.get("RSK_ORIGINAL_CURR")));
            tempBean.setInception_Date(emptyIfNull(tempMap.get("INSDATE")));
            tempBean.setExpiry_Date(emptyIfNull(tempMap.get("EXPDATE")));
            tempBean.setInsuredName(emptyIfNull(tempMap.get("RSK_INSURED_NAME")));
            tempBean.setExchangeRate(emptyIfNull(tempMap.get("RSK_EXCHANGE_RATE")));
            tempBean.setShareSign(PortfolioService.makeDoubleString(emptyIfNull(tempMap.get("SHARE_SIGNED")), 4));
            tempBean.setInstallmentDate(emptyIfNull(tempMap.get("INSTALLMENT_DATE")));
            tempBean.setInstallmentNo(emptyIfNull(tempMap.get("INSTALLMENT_NO")));
            tempBean.setPremiumOC(emptyIfNull(tempMap.get("MND_PREMIUM_OC")));
            tempBean.setCommission(emptyIfNull(tempMap.get("RSK_COMM")));
            tempBean.setBrokerage(emptyIfNull(tempMap.get("RSK_BROKERAGE")));
            tempBean.setTax(emptyIfNull(tempMap.get("RSK_TAX")));
            tempBean.setOtherCost(emptyIfNull(tempMap.get("RSK_OTHER_COST")).trim());
            tempBean.setTreatyName(emptyIfNull(tempMap.get("TREATY")));
            tempBean.setLayerNo(emptyIfNull(tempMap.get("LAYER_NO")));
            tempBean.setAcceptenceDate(emptyIfNull(tempMap.get("ACDATE")));
//            tempBean.setTransactionNo(emptyIfNull(tempMap.get("TRANSACTION_NO")));
            if (beanObj.getFlag() != null && "N".equalsIgnoreCase(beanObj.getFlag())) {
                tempBean.setFlag("N");
            } else if ("RP".equalsIgnoreCase(beanObj.getFlag())) {
                tempBean.setFlag("RP");
            } else {
                tempBean.setFlag("P");
            }
                /*if(tempMap.get("BASE_LAYER")!=null)
					tempBean.setBaseLayer(emptyIfNull(tempMap.get("BASE_LAYER")));
				else
					tempBean.setBaseLayer("");*/
            if (tempMap.get("BASE_LAYER") != null) {
                tempBean.setBaseLayer(tempMap.get("BASE_LAYER").toString());
//                tempBean.setContractno1(emptyIfNull(tempMap.get("CONTRACT_NO")));
                tempBean.setLay1("layer");
            } else {
                tempBean.setBaseLayer("");
            }
            tempBean.setUwYear(emptyIfNull(tempMap.get("UW_YEAR")));
            tempBean.setUwMonth(emptyIfNull(tempMap.get("UW_MONTH")));
            tempBean.setUnderwritter(emptyIfNull(tempMap.get("UNDERWRITTER")));
            tempBean.setOld_Contract(tempMap.get("OLD_CONTRACTNO") == null ? "" : "0".equals(tempMap.get("OLD_CONTRACTNO")) ? "" : tempMap.get("OLD_CONTRACTNO").toString());
            portfolioBeans.add(tempBean);
            }
        }
        return portfolioBeans;
    }

    public List<PortfolioBean> getContractsList(final PortfolioBean beanObj , Object MenuRights) {
        List<PortfolioBean> finalList = new ArrayList<PortfolioBean>();
        try {
            String query = "";
            Object[] obj = new Object[0];
            obj = new Object[8];
            obj[0] = beanObj.getProductId();
            obj[1] = beanObj.getProductId();
            obj[2] = beanObj.getBranchCode();
            obj[3] = beanObj.getBranchCode();
            obj[4] = beanObj.getBranchCode();
            obj[5] = beanObj.getBranchCode();
            obj[6] = beanObj.getProductId();
            obj[7] = beanObj.getBranchCode();
            query = getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLIST1);
            if (!"1".equals(beanObj.getProductId()))
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLISTLAYERNO1);
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLIST2);
            if (!"1".equals(beanObj.getProductId()))
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLISTLAYERNO2);
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLIST3);
            if (!"1".equals(beanObj.getProductId()))
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLISTLAYERNO3);
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLIST4);
            if ("1".equals(beanObj.getProductId())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{beanObj.getDeptId()});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLISTDEPTID);
            }
            if ("2".equals(beanObj.getProductId())) {
            
            }
			/*if(!"superuser".equalsIgnoreCase(beanObj.getUserType())){
				obj=new DropDownControllor().getIncObjectArray(obj,new Object[]{beanObj.getLoginId()});
				query+=" "+getQuery(DBConstants.PORTFOLIO_SELECT_LOGINID);
			}*/
            /*if ("CNO".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONNO);
            }
            if ("CCN".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_COMNAME);
            }
            if ("BRN".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_BRONAME);
            }
            if ("UWY".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
            }
            if ("DM".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " AND UPPER(B.TMAS_DEPARTMENT_NAME) LIKE UPPER(?)";
            }*/
            if ("RD".equalsIgnoreCase(beanObj.getFlag())) {
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_RENEWALDUE);
            }
            if ("NC".equalsIgnoreCase(beanObj.getFlag())) {
                //obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{"NC"});
                //query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CLAIMYN);
            }
            if ("EC".equalsIgnoreCase(beanObj.getFlag())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{"EC"});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CLAIMYN);
            }
            if(StringUtils.isNotBlank(beanObj.getSearchType()) && null !=beanObj.getSearchType()){
            	if(StringUtils.isNotBlank(beanObj.getProposalNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getProposalNoSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_PERNO);
            	}
            	if(StringUtils.isNotBlank(beanObj.getContractNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getContractNoSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONNO);
            	}
            	if("3".equalsIgnoreCase(beanObj.getProductId()) || "5".equalsIgnoreCase(beanObj.getProductId())){
	            	if(StringUtils.isNotBlank(beanObj.getCompanyNameSearch1())){
	            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getCompanyNameSearch1() + "%")});
	                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_COMNAME);
	            	}
	            	if(StringUtils.isNotBlank(beanObj.getBrokerNameSearch1())){
	            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getBrokerNameSearch1() + "%")});
	                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_BRONAME);
	            	}
	            	if(StringUtils.isNotBlank(beanObj.getDepartmentNameSearch1())){
	            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getDepartmentNameSearch1() + "%")});
	            		 query += " AND UPPER(B.TMAS_DEPARTMENT_NAME) LIKE UPPER(?)";
	            	}
            	}else{
            		if(StringUtils.isNotBlank(beanObj.getCompanyNameSearch())){
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getCompanyNameSearch() + "%")});
                        query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_COMNAME);
                	}
            		if(StringUtils.isNotBlank(beanObj.getBrokerNameSearch())){
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getBrokerNameSearch() + "%")});
                        query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_BRONAME);
                	}
            		if(StringUtils.isNotBlank(beanObj.getDepartmentNameSearch())){
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getDepartmentNameSearch() + "%")});
                		 query += " AND UPPER(B.TMAS_DEPARTMENT_NAME) LIKE UPPER(?)";
                	}
            	}
            	if("1".equalsIgnoreCase(beanObj.getProductId()) ){
            	if(StringUtils.isNotBlank(beanObj.getInsuredNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getInsuredNameSearch() + "%")});
            		 query += " AND UPPER(D.RSK_INSURED_NAME) LIKE UPPER(?)";
            	}
            	}
            	if("1".equalsIgnoreCase(beanObj.getProductId()) || "3".equalsIgnoreCase(beanObj.getProductId()) || "5".equalsIgnoreCase(beanObj.getProductId())){
            	if(StringUtils.isNotBlank(beanObj.getUwYearSearch2())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getUwYearSearch2() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
            	}if(StringUtils.isNotBlank(beanObj.getUnderwriterSearch())){
            		String qry ="SELECT UWR_CODE FROM UNDERWRITTER_MASTER WHERE BRANCH_CODE="+beanObj.getBranchCode()+" and UPPER(UNDERWRITTER) like UPPER('%" + beanObj.getUnderwriterSearch()+ "%') and UWR_STATUS='Y'";
            		String res =this.mytemplate.queryForObject(qry,String.class);
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + res + "%")});
            		 query += " AND UPPER(D.RSK_UNDERWRITTER) LIKE UPPER(?) ";
            	}
            	}else{
            		if(StringUtils.isNotBlank(beanObj.getUwYearSearch3())){
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getUwYearSearch3() + "%")});
                        query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
                	}if(StringUtils.isNotBlank(beanObj.getUnderwriterSearch1())){
                		String qry ="SELECT UWR_CODE FROM UNDERWRITTER_MASTER WHERE BRANCH_CODE="+beanObj.getBranchCode()+" and UPPER(UNDERWRITTER) like UPPER('%" + beanObj.getUnderwriterSearch1()+ "%') and UWR_STATUS='Y'";
                		String res =this.mytemplate.queryForObject(qry,String.class);
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + res + "%")});
                		 query += " AND UPPER(D.RSK_UNDERWRITTER) LIKE UPPER(?) ";
                	}
            	}
            	
            	beanObj.setType("Yes");
            }else{
            	beanObj.setProposalNoSearch("");
            	beanObj.setContractNoSearch("");
            	beanObj.setCompanyNameSearch1("");
            	beanObj.setBrokerNameSearch1("");
            	beanObj.setDepartmentNameSearch1("");
            	beanObj.setCompanyNameSearch("");
            	beanObj.setBrokerNameSearch("");
            	beanObj.setDepartmentNameSearch("");
            	beanObj.setInsuredNameSearch("");
            	beanObj.setUwYearSearch2("");
            	beanObj.setUnderwriterSearch("");
            	beanObj.setUwYearSearch3("");
            	beanObj.setUnderwriterSearch1("");
            }
            if(StringUtils.isNotBlank(beanObj.getAttachedUW()) && !"ALL".equalsIgnoreCase(beanObj.getAttachedUW())) {
        		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{(beanObj.getAttachedUW())});
        		 query += " AND UPPER(D.RSK_UNDERWRITTER) IN (select * from table(SPLIT_TEXT_FN(?)))";
        	}
            /*if ("2".equals(beanObj.getProductId())) {
            	 query += " " + getQuery("ORDER_BY_PROPOSAL");
            }else{*/
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_ORDERBYCONNO);
            //}
            if(StringUtils.isBlank(beanObj.getType()) ){
                query ="select * from ( "+query+" )where  rownum<=100";
            }
            logger.info("Query=>" + query);
            logger.info("Obj[]=>" + StringUtils.join(obj, ","));

            List<Map<String, Object>> list = this.mytemplate.queryForList(query, obj);
            //if(list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> tempMap = list.get(i);
                PortfolioBean tempBean = new PortfolioBean();

                tempBean.setProposalNo(tempMap.get("CONTRACT_NO") == null ? "" : tempMap.get("CONTRACT_NO").toString());
                tempBean.setProposalId(tempMap.get("PROPOSAL_NO") == null ? "" : tempMap.get("PROPOSAL_NO").toString());
                tempBean.setAmendId(tempMap.get("AMEND_ID") == null ? "" : tempMap.get("AMEND_ID").toString());
                tempBean.setProposalStatus(tempMap.get("PROPOSAL_STATUS")==null?"":tempMap.get("PROPOSAL_STATUS").toString());
                tempBean.setContractStatus(tempMap.get("CONTRACT_STATUS")==null?"":tempMap.get("CONTRACT_STATUS").toString());
                tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME") == null ? "" : tempMap.get("COMPANY_NAME").toString());
                tempBean.setDepartment_Name(tempMap.get("TMAS_DEPARTMENT_NAME") == null ? "" : tempMap.get("TMAS_DEPARTMENT_NAME").toString());
                tempBean.setDepartmentId(tempMap.get("TMAS_DEPARTMENT_ID") == null ? "" : tempMap.get("TMAS_DEPARTMENT_ID").toString());
                tempBean.setInception_Date(tempMap.get("INCEPTION_DATE") == null ? "" : tempMap.get("INCEPTION_DATE").toString());
                tempBean.setExpiry_Date(tempMap.get("EXPIRY_DATE") == null ? "" : tempMap.get("EXPIRY_DATE").toString());
                tempBean.setInsuredName(tempMap.get("RSK_INSURED_NAME") == null ? "" : tempMap.get("RSK_INSURED_NAME").toString());
                tempBean.setQuote_Gendrated_date(tempMap.get("ACCOUNT_DATE") == null ? "" : tempMap.get("ACCOUNT_DATE").toString());
                tempBean.setCedding_company_id(tempMap.get("CEDING_COMPANY_ID") == null ? "" : tempMap.get("CEDING_COMPANY_ID").toString());
                tempBean.setLayerNo(tempMap.get("LAYER_NO") == null ? "" : tempMap.get("LAYER_NO").toString());
                tempBean.setOldStatus(tempMap.get("OLD_STATUS") == null ? "" : tempMap.get("OLD_STATUS").toString());
                tempBean.setAcceptenceDate(tempMap.get("ACCOUNT_DATE") == null ? "" : tempMap.get("ACCOUNT_DATE").toString());
                tempBean.setProductId(tempMap.get("PRODUCT_ID") == null ? "" : tempMap.get("PRODUCT_ID").toString());
                if (tempMap.get("BASE_LAYER") != null)
                    tempBean.setBaseLayer(tempMap.get("BASE_LAYER") == null ? "" : tempMap.get("BASE_LAYER").toString());
                tempBean.setOld_Contract(tempMap.get("OLD_CONTRACTNO") == null ? "" : "0".equals(tempMap.get("OLD_CONTRACTNO")) == true ? "" : tempMap.get("OLD_CONTRACTNO").toString());
                tempBean.setRenewal_Status(tempMap.get("RENEWAL_STATUS") == null ? "" : tempMap.get("RENEWAL_STATUS").toString());
                if (tempMap.get("RenwalDate") != null) {
                    final float RenwalPreiod = Float.parseFloat(tempMap.get("RenwalDate") == null ? "" : tempMap.get("RenwalDate").toString());
                    if (RenwalPreiod <= 60) {
                        tempBean.setRenewalPeriod("1");
                    } else {
                        tempBean.setRenewalPeriod("0");
                    }
                }
                tempBean.setUwYear(tempMap.get("UW_YEAR") == null ? "" : tempMap.get("UW_YEAR").toString());
                tempBean.setUwMonth(tempMap.get("UW_MONTH") == null ? "" : tempMap.get("UW_MONTH").toString());
                tempBean.setUnderwritter(tempMap.get("UNDERWRITTER") == null ? "" : tempMap.get("UNDERWRITTER").toString());
                tempBean.setBrokerName(tempMap.get("BROKER_NAME") == null ? "" : tempMap.get("BROKER_NAME").toString());
                tempBean.setEditMode(tempMap.get("EDIT_MODE") == null ? "" : tempMap.get("EDIT_MODE").toString());
                if ("C".equalsIgnoreCase(beanObj.getFlag())) {
                    tempBean.setFlag("C");
                } else if ("RD".equalsIgnoreCase(beanObj.getFlag())) {
                    tempBean.setFlag("RD");
                }
                if("P".equalsIgnoreCase(tempBean.getContractStatus().trim()) && "A".equalsIgnoreCase(tempBean.getProposalStatus().trim()) ){
                	//tempBean.setDisableStatus("Y");
                	tempBean.setDisableStatus("N");
                }
                else if(!"N".equalsIgnoreCase(tempBean.getEditMode())&&!"".equalsIgnoreCase(tempBean.getEditMode())){
                	//tempBean.setDisableStatus("Y");
                	tempBean.setDisableStatus("N");
                }
                else{
                	tempBean.setDisableStatus("N");
                }
                if("0".equalsIgnoreCase(tempBean.getRenewal_Status().trim())){
                	tempBean.setRenewaldisable("Y");
                }
                else{
                	tempBean.setRenewaldisable("N");
                }
                
                
                tempBean.setCeaseStatus(tempMap.get("CEASE_STATUS") == null ? "" : tempMap.get("CEASE_STATUS").toString());
                tempBean.setClaimCount(this.mytemplate.queryForInt("SELECT COUNT(*) FROM TTRN_CLAIM_DETAILS WHERE CONTRACT_NO =? AND LAYER_NO=?", new Object[]{tempBean.getProposalNo(),tempBean.getLayerNo()}));
                tempBean.setPremiumcount(this.mytemplate.queryForInt("SELECT COUNT(*) FROM  rsk_premium_details WHERE CONTRACT_NO =? AND LAYER_NO=?", new Object[]{tempBean.getProposalNo(),tempBean.getLayerNo()}));
                tempBean.setCombinedClassCount(this.mytemplate.queryForObject("SELECT DECODE(COUNT(*),0,'N','Y') FROM TMAS_DEPARTMENT_MASTER WHERE BRANCH_CODE =? AND TMAS_PRODUCT_ID=? AND  TMAS_STATUS='Y' AND CORE_COMPANY_CODE IS NOT NULL AND TMAS_DEPARTMENT_ID IN(?) ",  new Object[]{beanObj.getBranchCode(),beanObj.getProductId(),tempBean.getDepartmentId()},String.class));
                if("Y".equalsIgnoreCase(tempBean.getCeaseStatus())){
                	if(tempBean.getPremiumcount()==0 ){
                		tempBean.setPremiumStatus("Y");
                	}
                	else{
                		tempBean.setPremiumStatus("N");
                	}
                	if(tempBean.getClaimCount()==0 ){
                		tempBean.setClaimStatus("Y");
                	}
                	else{
                		tempBean.setClaimStatus("N");
                	}
                }
                String qry =getQuery("GET_GNPI_COUNT");
                Object args[]=new Object[3];
                args[0]=tempBean.getProposalId();
                args[1]=tempBean.getProposalNo();
                args[2]="GNPI";
                String res =this.mytemplate.queryForObject(qry, args,String.class);
                tempBean.setGnpiFlag(res);
                tempBean.setButtonSelectionList(getButtonList(tempBean,MenuRights));
                finalList.add(tempBean);
                
                /*Map<String,Object> map=new HashMap<String,Object>();
                List<Map<String,Object>> addList= new ArrayList<Map<String,Object>>();
                List<List<Map<String,Object>>> buttonList = new ArrayList<List<Map<String,Object>>>();
                map.put("E","Edit");
                addList.add(map);
                buttonList.add(addList);*/
               
            }
            
        } catch (Exception e) {
            logger.debug("Exception @ {" + e + "}");
        }
        return finalList;
    }
    private List<Map<String, Object>> getPendingButtonList(PortfolioBean tempBean, Object menuRights) {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			
			if( menuRights.toString().contains("EN") ){//&&"N".equalsIgnoreCase(tempBean.getEditMode())&&!"".equalsIgnoreCase(tempBean.getEditMode())){
				map.put("TYPE","E");
				map.put("DETAIL_NAME","Edit");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("V")){
				map.put("TYPE","V");
				map.put("DETAIL_NAME","View");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("V")){
				map.put("TYPE","RI");
				map.put("DETAIL_NAME","Reinsurer");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("V")){
				map.put("TYPE","PL");
				map.put("DETAIL_NAME","Placement");
				list.add(map);
				map=new HashMap<String,Object>();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
    private List<Map<String, Object>> getButtonList(PortfolioBean tempBean, Object menuRights) {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			if( menuRights.toString().contains("EN") ){//&&"N".equalsIgnoreCase(tempBean.getEditMode())&&!"".equalsIgnoreCase(tempBean.getEditMode())){
				map.put("TYPE","E");
				map.put("DETAIL_NAME","Edit");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("V")){
				map.put("TYPE","V");
				map.put("DETAIL_NAME","View");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("V")){
				map.put("TYPE","PL");
				map.put("DETAIL_NAME","Placing");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if(!"0".equalsIgnoreCase(tempBean.getAmendId())){
				map=new HashMap<String,Object>();
				map.put("TYPE","H");
				map.put("DETAIL_NAME","History");
				list.add(map);
				map=new HashMap<String,Object>();
				}
			if( menuRights.toString().contains("P") &&!"Y".equalsIgnoreCase(tempBean.getDisableStatus()) && !"Y".equalsIgnoreCase(tempBean.getPremiumStatus()) &&!"4".equalsIgnoreCase(tempBean.getProductId())){
				map.put("TYPE","P");
				map.put("DETAIL_NAME","Premium");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("C") &&!"Y".equalsIgnoreCase(tempBean.getDisableStatus())&& !"Y".equalsIgnoreCase(tempBean.getClaimStatus())){
				map.put("TYPE","C");
				map.put("DETAIL_NAME","Claim");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if("5".equalsIgnoreCase(tempBean.getProductId())){
				map.put("TYPE","IE");
				map.put("DETAIL_NAME","IE Module");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("ST")){
				map.put("TYPE","S");
				map.put("DETAIL_NAME","Stats & Calc.");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("CS") &&!"Y".equalsIgnoreCase(tempBean.getDisableStatus()) &&!"Y".equalsIgnoreCase(tempBean.getCombinedClassCount()) && "2".equalsIgnoreCase(tempBean.getProductId()) &&StringUtils.isBlank(tempBean.getBaseLayer())){
				if( "1".equalsIgnoreCase(tempBean.getRenewal_Status())){
				map.put("TYPE","CS");
				map.put("DETAIL_NAME","Classes");
				list.add(map);
				map=new HashMap<String,Object>();
				}
			}
			if(("3".equalsIgnoreCase(tempBean.getProductId())||"5".equalsIgnoreCase(tempBean.getProductId()))&&"0".equalsIgnoreCase(tempBean.getGnpiFlag())&&StringUtils.isBlank(tempBean.getBaseLayer()) &&!"Y".equalsIgnoreCase(tempBean.getDisableStatus()) &&"1".equalsIgnoreCase(tempBean.getRenewal_Status())){
				map.put("TYPE","L");
				map.put("DETAIL_NAME","Duplicate Layer");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			
			if( menuRights.toString().contains("R")&&"N".equalsIgnoreCase(tempBean.getDisableStatus()) &&"N".equalsIgnoreCase(tempBean.getRenewaldisable())&&"1".equalsIgnoreCase(tempBean.getRenewal_Status())&&"1".equalsIgnoreCase(tempBean.getRenewalPeriod())){
				/*if(StringUtils.isNotBlank(tempBean.getBaseLayer())&&"0".equalsIgnoreCase(tempBean.getOldStatus())){
				map.put("TYPE","R");
				map.put("DETAIL_NAME","Renewal");
				list.add(map);
				map=new HashMap<String,Object>();
				}*/
				if(StringUtils.isBlank(tempBean.getBaseLayer())){
					map.put("TYPE","R");
					map.put("DETAIL_NAME","Renewal");
					list.add(map);
					map=new HashMap<String,Object>();
				}
			}
			if( menuRights.toString().contains("CP")&&StringUtils.isBlank(tempBean.getBaseLayer())){
				map.put("TYPE","CP");
				map.put("DETAIL_NAME","Copy");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			map.put("TYPE","D");
			map.put("DETAIL_NAME","Document");
			list.add(map);
			map=new HashMap<String,Object>();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public List<PortfolioBean> getRejectedList(final PortfolioBean beanObj) {
        List<PortfolioBean> finalList = new ArrayList<PortfolioBean>();
        try {
            String query = "";
            Object[] obj = new Object[8];
            obj[0] = beanObj.getProductId();
            obj[1] = beanObj.getBranchCode();
            obj[2] = beanObj.getProductId();
            obj[3] = beanObj.getBranchCode();
            obj[4] = beanObj.getBranchCode();
            obj[5] = beanObj.getProductId();
            obj[6] = beanObj.getBranchCode();
            obj[7] = beanObj.getBranchCode();
            query = getQuery(DBConstants.PORTFOLIO_SELECT_REJECTED);
            if ("1".equals(beanObj.getProductId())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{beanObj.getDeptId()});
                query += getQuery(DBConstants.PORTFOLIO_SELECT_REJECTEDDEPT);
            }

			/*if(!"superuser".equalsIgnoreCase(beanObj.getUserType())){
				obj=new DropDownControllor().getIncObjectArray(obj,new Object[]{beanObj.getLoginId()});
				query+=" "+getQuery(DBConstants.PORTFOLIO_SELECT_LOGINID);
			}*/
          /*  if ("PNO".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_PERNO);
            }
            if ("CCN".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_COMNAME);
            }
            if ("BRN".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_BRONAME);
            }
            if ("UWY".equalsIgnoreCase(beanObj.getSearchType())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSearchValue() + "%")});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
            }*/
            if(StringUtils.isNotBlank(beanObj.getSearchType()) && null !=beanObj.getSearchType()){
            	if(StringUtils.isNotBlank(beanObj.getProposalNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getProposalNoSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_PERNO);
            	}
            	if(StringUtils.isNotBlank(beanObj.getContractNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getContractNoSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONNO);
            	}
        		if(StringUtils.isNotBlank(beanObj.getCompanyNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getCompanyNameSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_COMNAME);
            	}
        		if(StringUtils.isNotBlank(beanObj.getBrokerNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getBrokerNameSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_BRONAME);
            	}
        		if(StringUtils.isNotBlank(beanObj.getDepartmentNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getDepartmentNameSearch() + "%")});
            		 query += " AND UPPER(B.TMAS_DEPARTMENT_NAME) LIKE UPPER(?)";
            	}
            	if("1".equalsIgnoreCase(beanObj.getProductId())){
        		if(StringUtils.isNotBlank(beanObj.getInsuredNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getInsuredNameSearch() + "%")});
            		 query += " AND UPPER(D.RSK_INSURED_NAME) LIKE UPPER(?)";
            	}
            	if(StringUtils.isNotBlank(beanObj.getUwYearSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getUwYearSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
            	}if(StringUtils.isNotBlank(beanObj.getUnderwriterSearch())){
            		String qry ="SELECT UWR_CODE FROM UNDERWRITTER_MASTER WHERE BRANCH_CODE="+beanObj.getBranchCode()+" and UPPER(UNDERWRITTER) like UPPER('%" + beanObj.getUnderwriterSearch()+ "%') and UWR_STATUS='Y'";
            		String res =this.mytemplate.queryForObject(qry,String.class);
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + res + "%")});
            		 query += " AND UPPER(D.RSK_UNDERWRITTER) LIKE UPPER(?) ";
            	}
            	}else{
            		if(StringUtils.isNotBlank(beanObj.getUwYearSearch1())){
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getUwYearSearch1() + "%")});
                        query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
                	}if(StringUtils.isNotBlank(beanObj.getUnderwriterSearch1())){
                		String qry ="SELECT UWR_CODE FROM UNDERWRITTER_MASTER WHERE BRANCH_CODE="+beanObj.getBranchCode()+" and UPPER(UNDERWRITTER) like UPPER('%" + beanObj.getUnderwriterSearch1()+ "%') and UWR_STATUS='Y'";
                		String res =this.mytemplate.queryForObject(qry,String.class);
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + res + "%")});
                		 query += " AND UPPER(D.RSK_UNDERWRITTER) LIKE UPPER(?) ";
                	}
            	}
            	
            	beanObj.setType("Yes");
            }
            else{
            	beanObj.setProposalNoSearch("");
            	beanObj.setContractNoSearch("");
            	beanObj.setCompanyNameSearch1("");
            	beanObj.setBrokerNameSearch1("");
            	beanObj.setDepartmentNameSearch1("");
            	beanObj.setCompanyNameSearch("");
            	beanObj.setBrokerNameSearch("");
            	beanObj.setDepartmentNameSearch("");
            	beanObj.setInsuredNameSearch("");
            	beanObj.setUwYearSearch2("");
            	beanObj.setUnderwriterSearch("");
            	beanObj.setUwYearSearch3("");
            	beanObj.setUnderwriterSearch1("");
            }
            if(StringUtils.isNotBlank(beanObj.getAttachedUW()) && !"ALL".equalsIgnoreCase(beanObj.getAttachedUW())) {
        		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{(beanObj.getAttachedUW())});
        		 query += " AND UPPER(D.RSK_UNDERWRITTER) IN (select * from table(SPLIT_TEXT_FN(?)))";
        	}
            
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_ORDERBYPRONO);
            beanObj.setMode("true");
            logger.info("Query=>" + query);
            logger.info("Obj[]=>" + StringUtils.join(obj, ","));
            List<Map<String, Object>> list = this.mytemplate.queryForList(query, obj);
            //if(list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> tempMap = list.get(i);
                PortfolioBean tempBean = new PortfolioBean();
                tempBean.setProposalNo(tempMap.get("PROPOSAL_NO") == null ? "" : tempMap.get("PROPOSAL_NO").toString());
                tempBean.setAmendId(tempMap.get("AMEND_ID") == null ? "" : tempMap.get("AMEND_ID").toString());
                tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME") == null ? "" : tempMap.get("COMPANY_NAME").toString());
                tempBean.setDepartment_Name(tempMap.get("TMAS_DEPARTMENT_NAME") == null ? "" : tempMap.get("TMAS_DEPARTMENT_NAME").toString());
                tempBean.setInception_Date(tempMap.get("INCEPTION_DATE") == null ? "" : tempMap.get("INCEPTION_DATE").toString());
                tempBean.setExpiry_Date(tempMap.get("EXPIRY_DATE") == null ? "" : tempMap.get("EXPIRY_DATE").toString());
                tempBean.setInsuredName(tempMap.get("RSK_INSURED_NAME") == null ? "" : tempMap.get("RSK_INSURED_NAME").toString());
                tempBean.setQuote_Gendrated_date(tempMap.get("ACCOUNT_DATE") == null ? "" : tempMap.get("ACCOUNT_DATE").toString());
                tempBean.setCedding_company_id(tempMap.get("CEDING_COMPANY_ID") == null ? "" : tempMap.get("CEDING_COMPANY_ID").toString());
                tempBean.setFlag("R");
                if (tempMap.get("BASE_LAYER") != null) {
                    tempBean.setBaseLayer(tempMap.get("BASE_LAYER") == null ? "" : tempMap.get("BASE_LAYER").toString());
                } else {
                    tempBean.setBaseLayer("");
                    tempBean.setUwYear(tempMap.get("UW_YEAR") == null ? "" : tempMap.get("UW_YEAR").toString());
                    tempBean.setUwMonth(tempMap.get("UW_MONTH") == null ? "" : tempMap.get("UW_MONTH").toString());
                    tempBean.setUnderwritter(tempMap.get("UNDERWRITTER") == null ? "" : tempMap.get("UNDERWRITTER").toString());
                    tempBean.setBrokerName(tempMap.get("BROKER_NAME") == null ? "" : tempMap.get("BROKER_NAME").toString());
                }
                finalList.add(tempBean);
            }

			/* list=this.mytemplate.query(query,obj, new RowMapper(){
		    	public Object mapRow(ResultSet rset, final int arg)throws SQLException 
		    	{
			    	 final PortfolioBean bean=new PortfolioBean();
			    	 bean.setProposalNo(rset.getString("PROPOSAL_NO"));
			    	 bean.setAmendId(rset.getString("AMEND_ID"));
			    	 bean.setCeding_Company_Name(rset.getString("COMPANY_NAME"));
			    	 bean.setDepartment_Name(rset.getString("TMAS_DEPARTMENT_NAME"));
			    	 bean.setInception_Date(rset.getString("INCEPTION_DATE"));
			    	 bean.setExpiry_Date(rset.getString("EXPIRY_DATE"));
			    	 bean.setQuote_Gendrated_date(rset.getString("ACCOUNT_DATE"));
			    	 bean.setCedding_company_id(rset.getString("CEDING_COMPANY_ID"));
			    	 bean.setFlag("R");
			    	 if(rset.getString("BASE_LAYER")!=null)
			    	 {
			    		 bean.setBaseLayer(rset.getString("BASE_LAYER"));
			    	 }
				     else
				     {
					     bean.setBaseLayer("");	 
				    	 bean.setUwYear(rset.getString("UW_YEAR"));
				    	 bean.setUwMonth(rset.getString("UW_MONTH"));
				    	 bean.setUnderwritter(rset.getString("UNDERWRITTER"));
				    	 bean.setBrokerName(rset.getString("BROKER_NAME"));
				     }
			    	 return bean;
		    	}
		    });*/

        } catch (Exception e) {
            logger.debug("Exception @ {" + e + "}");
        }
        return finalList;
    }

    public List<PortfolioBean> getHistoryList(final PortfolioBean beanObj) {
        List<PortfolioBean> finalList = new ArrayList<PortfolioBean>();
        try {
            String query = "";
            Object[] obj = new Object[0];
			/*if("1".equals(beanObj.getProductId()))
			{*/
            obj = new Object[7];
            obj[0] = beanObj.getProductId();
            obj[1] = beanObj.getProductId();
            obj[2] = beanObj.getBranchCode();
            obj[3] = beanObj.getBranchCode();
            obj[4] = beanObj.getBranchCode();
            obj[5] = beanObj.getBranchCode();
            obj[6] = beanObj.getBranchCode();
            query = getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLIST1);
            if (!"1".equals(beanObj.getProductId()))
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLISTLAYERNO1);
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLIST2);
            if (!"1".equals(beanObj.getProductId()))
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLISTLAYERNO2);
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLIST3HISTORY);
            if ("1".equals(beanObj.getProductId())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{beanObj.getDeptId()});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONTRACTLISTDEPTID);
            }
			/*if(!"superuser".equalsIgnoreCase(beanObj.getUserType())){
				obj=new DropDownControllor().getIncObjectArray(obj,new Object[]{beanObj.getLoginId()});
				query+=" "+getQuery(DBConstants.PORTFOLIO_SELECT_LOGINID);
			}*/
            if ("RD".equalsIgnoreCase(beanObj.getFlag())) {
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_RENEWALDUE);
            }
            if ("NC".equalsIgnoreCase(beanObj.getFlag())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{"NC"});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CLAIMYN);
            }
            if ("EC".equalsIgnoreCase(beanObj.getFlag())) {
                obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{"EC"});
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CLAIMYN);
            }
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_PERNO);
            
            obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{beanObj.getProposalNo()});
            /*if ("2".equals(beanObj.getProductId())) {
           	 query += " " + getQuery("ORDER_BY_PROPOSAL");
           }else{*/
           query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_ORDERBYCONNO);
           //}
            logger.info("Query=>" + query);
            logger.info("Obj[]=>" + StringUtils.join(obj, ","));
            List<Map<String, Object>> list = this.mytemplate.queryForList(query, obj);
            //if(list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> tempMap = list.get(i);
                PortfolioBean tempBean = new PortfolioBean();
                tempBean.setProposalNo(tempMap.get("CONTRACT_NO") == null ? "" : tempMap.get("CONTRACT_NO").toString());
                tempBean.setProposalId(tempMap.get("PROPOSAL_NO") == null ? "" : tempMap.get("PROPOSAL_NO").toString());
                tempBean.setAmendId(tempMap.get("AMEND_ID") == null ? "" : tempMap.get("AMEND_ID").toString());
                tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME") == null ? "" : tempMap.get("COMPANY_NAME").toString());
                tempBean.setDepartment_Name(tempMap.get("TMAS_DEPARTMENT_NAME") == null ? "" : tempMap.get("TMAS_DEPARTMENT_NAME").toString());
                tempBean.setInception_Date(tempMap.get("INCEPTION_DATE") == null ? "" : tempMap.get("INCEPTION_DATE").toString());
                tempBean.setExpiry_Date(tempMap.get("EXPIRY_DATE") == null ? "" : tempMap.get("EXPIRY_DATE").toString());
                tempBean.setInsuredName(tempMap.get("RSK_INSURED_NAME") == null ? "" : tempMap.get("RSK_INSURED_NAME").toString());
                tempBean.setQuote_Gendrated_date(tempMap.get("ACCOUNT_DATE") == null ? "" : tempMap.get("ACCOUNT_DATE").toString());
                tempBean.setCedding_company_id(tempMap.get("CEDING_COMPANY_ID") == null ? "" : tempMap.get("CEDING_COMPANY_ID").toString());
                tempBean.setLayerNo(tempMap.get("LAYER_NO") == null ? "" : tempMap.get("LAYER_NO").toString());
                if("0".equalsIgnoreCase(tempBean.getAmendId())){
                	tempBean.setEndorsmentTypeName("Original");
                }
                else{
                tempBean.setEndorsmentTypeName(tempMap.get("RS_ENDORSEMENT_TYPE") == null ? "" : tempMap.get("RS_ENDORSEMENT_TYPE").toString());
                }
                if (tempMap.get("BASE_LAYER") != null)
                    tempBean.setBaseLayer(tempMap.get("BASE_LAYER") == null ? "" : tempMap.get("BASE_LAYER").toString());
                tempBean.setOld_Contract(tempMap.get("OLD_CONTRACTNO") == null ? "" : "0".equals(tempMap.get("OLD_CONTRACTNO")) == true ? "" : tempMap.get("OLD_CONTRACTNO").toString());
                tempBean.setRenewal_Status(tempMap.get("Renewal_Status") == null ? "" : tempMap.get("Renewal_Status").toString());
                if (tempMap.get("RenwalDate") != null) {
                    final float RenwalPreiod = Float.parseFloat(tempMap.get("RenwalDate") == null ? "" : tempMap.get("RenwalDate").toString());
                    if (RenwalPreiod <= 365) {
                        tempBean.setRenewalPeriod("1");
                    } else {
                        tempBean.setRenewalPeriod("0");
                    }
                }
                tempBean.setUwYear(tempMap.get("UW_YEAR") == null ? "" : tempMap.get("UW_YEAR").toString());
                tempBean.setUwMonth(tempMap.get("UW_MONTH") == null ? "" : tempMap.get("UW_MONTH").toString());
                tempBean.setUnderwritter(tempMap.get("UNDERWRITTER") == null ? "" : tempMap.get("UNDERWRITTER").toString());
                tempBean.setBrokerName(tempMap.get("BROKER_NAME") == null ? "" : tempMap.get("BROKER_NAME").toString());
                if ("C".equalsIgnoreCase(beanObj.getFlag())) {
                    tempBean.setFlag("C");
                } else if ("RD".equalsIgnoreCase(beanObj.getFlag())) {
                    tempBean.setFlag("RD");
                }
                tempBean.setDisplay("History");
                finalList.add(tempBean);
            }

			/*list=this.mytemplate.query(query,obj, new RowMapper(){
		    public Object mapRow(ResultSet rset, final int arg)throws SQLException 
		    {
			    	 final PortfolioBean bean=new PortfolioBean();
			    	 bean.setProposalNo(rset.getString("CONTRACT_NO"));
			    	 bean.setProposalId(rset.getString("PROPOSAL_NO"));
			    	 bean.setAmendId(rset.getString("AMEND_ID"));
			    	 bean.setCeding_Company_Name(rset.getString("COMPANY_NAME"));
			    	 bean.setDepartment_Name(rset.getString("TMAS_DEPARTMENT_NAME"));
			    	 bean.setInception_Date(rset.getString("INCEPTION_DATE"));
			    	 bean.setExpiry_Date(rset.getString("EXPIRY_DATE"));
			    	 bean.setQuote_Gendrated_date(rset.getString("ACCOUNT_DATE"));
			    	 bean.setCedding_company_id(rset.getString("CEDING_COMPANY_ID"));
			    	 bean.setLayerNo(rset.getString("LAYER_NO"));
			    	 if(rset.getString("BASE_LAYER")!=null)
			    	 bean.setBaseLayer(rset.getString("BASE_LAYER"));
			    	 bean.setOld_Contract(rset.getString("OLD_CONTRACTNO")==null?"":"0".equals(rset.getString("OLD_CONTRACTNO"))==true?"":rset.getString("OLD_CONTRACTNO"));
			    	 bean.setRenewal_Status(rset.getString("Renewal_Status"));
			    	 if(rset.getString("RenwalDate")!=null)
			    	 {
			    	  final float RenwalPreiod=Float.parseFloat(rset.getString("RenwalDate"));
			    	  if(RenwalPreiod <=60)
			    	  {
			    	    bean.setRenewalPeriod("1");
			    	  }
			    	  else
			    	  {
			    	   bean.setRenewalPeriod("0");
			    	  }
			    	}
			    	bean.setUwYear(rset.getString("UW_YEAR"));
			    	bean.setUwMonth(rset.getString("UW_MONTH"));
			    	bean.setUnderwritter(rset.getString("UNDERWRITTER"));
			    	bean.setBrokerName(rset.getString("BROKER_NAME"));
			    	if("C".equalsIgnoreCase(beanObj.getFlag()))
					{
			    		 bean.setFlag("C");			    		
					}else if("RD".equalsIgnoreCase(beanObj.getFlag()))
			    	{
						 bean.setFlag("RD");
			    	}
		    	 return bean;
		    	}
		    });*/
        } catch (Exception e) {
            logger.debug("Exception @ {" + e + "}");
        }
        return finalList;
    }
    public boolean validateOpenPeriod(PortfolioBean bean) {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("d", bean.getTransactionDate());
        args.put("bc", bean.getBranchCode());
        boolean isValid = getSimpleJdbcTemplate().queryForInt(getQuery("GET_VALID_OPEN_PERIOD_DATE_BY_BRANCH"), args) > 0;
        if (!isValid) {
            List<Map<String, Object>> list = getSimpleJdbcTemplate().queryForList(getQuery("GET_OPEN_PERIOD_DATE_BY_BRANCH"), args);
            for (Map<String, Object> i : list) {
                bean.setOpenPeriodStartDate(i.get("start_date").toString());
                bean.setOpenPeriodEndDate(i.get("end_date").toString());
                if (list.size() > 1) break;
            }
        }
        return isValid;
    }

    @SuppressWarnings("unchecked")
    public void procAuto(PortfolioBean bean, String menuId, String countryId) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DropDownControllor dropDownController=new DropDownControllor();
        DecimalFormat df = new DecimalFormat("#.00");
        String transactionNo = "";
        try {
			if (parseNumber(bean.getProductId()) > 0) {
			    if (bean.getPortfolioList() != null)
			        for (PortfolioBean b : bean.getPortfolioList()) {
			            if(StringUtils.isNotBlank(b.getPremiumClass()) && "3".equalsIgnoreCase(bean.getProductId())){
			            	try{
				         	   String qry = getQuery("GET_DEPT_CODE_LIMIT");
				         	   b.setPremiumClass(this.mytemplate.queryForObject(qry,new Object[]{b.getContractNo(),StringUtils.isEmpty(b.getLayerNo())? "0" : b.getLayerNo(),bean.getProductId()},String.class));
				            }catch(Exception e){
					         	   e.printStackTrace();
					          }
			            }
			            b.setExchangeRate(dropDownController.GetExchangeRate(b.getCurrencyId(), b.getTransactionDate(),countryId,bean.getBranchCode()));
			            Map<String, Object> map = new HashMap<String, Object>();
			            map.put("receipt", "");
			            map.put("contractNo", b.getContractNo());
			           // if("06".equalsIgnoreCase(bean.getBranchCode())){
			            	 map.put("transactionNo", new DropDownControllor().getSequence("Premium",bean.getProductId(),b.getDepartmentId(), bean.getBranchCode(),"",b.getTransactionDate()));
			    		//}else
			          //  map.put("transactionNo", dropDownController.getPolicyNo("3", "0", bean.getBranchCode()));
			            transactionNo =  map.get("transactionNo").toString();
			            map.put("transactionDate", b.getTransactionDate());
			            map.put("installmentDate", b.getInstallmentDate());
			            map.put("installmentNo", b.getInstallmentNo());
			            map.put("currencyId", b.getCurrencyId());
			            map.put("exRate", parseNumber(b.getExchangeRate()));
			            map.put("premQS", parseNumber(b.getPremiumOC()));
			            map.put("commission", parseNumber(b.getCommission()));
			            map.put("commissionOC", parseNumber(b.getCommissionOC()==null?"":b.getCommissionOC()));
			            map.put("brokerage", parseNumber(b.getBrokerage()));
			            map.put("brokerageOC", parseNumber(b.getBrokerageOC()));
			            map.put("tax", parseNumber(b.getTax()));
			            map.put("taxOC", parseNumber(b.getTaxOC()));
			            map.put("entryDate", b.getReceiveDate());
			            map.put("status", "Y");
			            map.put("enteringMode", "2");
			            map.put("settlementStatus", bean.getFaculPremiumBean().getSettlement_status());
			            map.put("otherCostOC", parseNumber(b.getOtherCostOC()));
			            map.put("premQSDC", getDC(map.get("premQS").toString(), b.getExchangeRate()));
			            map.put("commissionDC", b.getCommissionOC()==null?"":getDC(map.get("commissionOC").toString(), b.getExchangeRate()));
			            map.put("brokerageDC", getDC(map.get("brokerageOC").toString(), b.getExchangeRate()));
			            map.put("taxDC", getDC(map.get("taxOC").toString(), b.getExchangeRate()));
			            map.put("otherCostDC", getDC(map.get("otherCostOC").toString(), b.getExchangeRate()));
			            map.put("cedantRef", b.getReference());
			            map.put("remark", b.getRemarks());
			            map.put("whtOC", parseNumber(b.getFaculPremiumBean().getWithHoldingTaxOC()));
			            map.put("whtDC", getDC(b.getFaculPremiumBean().getWithHoldingTaxOC(), b.getExchangeRate()));
			            map.put("totalCrOC", parseNumber(b.getPremiumOC()));
			            map.put("totalCrDC", getDC(map.get("totalCrOC").toString(), b.getExchangeRate()));
			            map.put("totalDrOC", getTotalDebit(map));
			            map.put("totalDrDC", getDC(map.get("totalDrOC").toString(), b.getExchangeRate()));
			            map.put("netDueOC", df.format(parseNumber(map.get("totalCrOC").toString()) - parseNumber(map.get("totalDrOC").toString())));
			            map.put("netDueDC", getDC((map.get("netDueOC").toString()), b.getExchangeRate()));
			            b.setNetDueOC(map.get("netDueOC").toString());
			            map.put("layerNo", StringUtils.isEmpty(b.getLayerNo())? "0" : b.getLayerNo());
			            map.put("loginId", bean.getLoginId()==null?"":bean.getLoginId());
			            map.put("branchCode", bean.getBranchCode()==null?"":bean.getBranchCode());
			            map.put("subClass", b.getDepartmentId()==null?"":b.getDepartmentId());
			            if("1".equalsIgnoreCase(bean.getProductId())){
			            	map.put("premiumClass", b.getPremiumClass()==null?"":b.getPremiumClass());
			            	map.put("premiumsubClass", b.getPremiumSubClass()==null?"":b.getPremiumSubClass());
			            }
			            else{
			            	map.put("premiumClass", b.getPremiumClass()==null?"":b.getPremiumClass());
			            	map.put("premiumsubClass", "D");
			            }
			            map.put("taxDetectedOC", parseNumber(b.getFaculPremiumBean().getTaxDedectSource()));
			            map.put("taxDetectedDC", getDC(b.getFaculPremiumBean().getTaxDedectSource(), b.getExchangeRate()));
			            map.put("servicetaxOC", parseNumber(b.getFaculPremiumBean().getServiceTax()));
			            map.put("servicetaxDC", getDC(b.getFaculPremiumBean().getServiceTax(), b.getExchangeRate()));
			            map.put("bonusOC", parseNumber(b.getFaculPremiumBean().getBonus()));
			            map.put("bonusDC", getDC(b.getFaculPremiumBean().getBonus(), b.getExchangeRate()));
			            map.put("receivedDate", b.getTransactionDate());
			            map.put("proposalNo", b.getProposalNo());
			            map.put("productId", bean.getProductId());
			            maps.add(map);
			            logger.info(map.toString());
			        }
			    	 if("1".equalsIgnoreCase(bean.getProductId())){
					    	getSimpleJdbcTemplate().batchUpdate(getQuery("premium.insert.facpremium.auto"), maps.toArray(new Map[maps.size()]));
					    	getSimpleJdbcTemplate().batchUpdate(getQuery("premium.insert.facpremium.auto.retro"), maps.toArray(new Map[maps.size()]));
					    }else if("3".equalsIgnoreCase(bean.getProductId())){
					    	getSimpleJdbcTemplate().batchUpdate(getQuery("premium.insert.nonproppremium.auto"), maps.toArray(new Map[maps.size()]));
					    	getSimpleJdbcTemplate().batchUpdate(getQuery("premium.insert.nonproppremium.auto.retro"), maps.toArray(new Map[maps.size()]));
					    }
				    getSimpleJdbcTemplate().batchUpdate(getQuery("UPDATE_MND_INSTALLMENT_AUTO"), maps.toArray(new Map[maps.size()]));
				   // UpdateMndInstallment(transactionNo,bean);
			
			} else {

			}
		} catch (Exception e) {
			bean.setTransactionError("error");
			for(int i=maps.size()-1;i>=0;i--){
				Map<String,Object> limap=maps.get(i);
				String query="{CALL sequence_reset(?,?,?)}";
				Object args[]=new Object[3];
				args[0]=limap.get("transactionNo");
				args[1]=limap.get("productId");
				args[2]=limap.get("branchCode");
				this.mytemplate.update(query,args);
			e.printStackTrace();
			}
    }
}
    /*private void UpdateMndInstallment(String transactionNo, PortfolioBean bean) {
		try{
			if (parseNumber(bean.getProductId()) > 0) {
			    if (bean.getPortfolioList() != null)
			        for (PortfolioBean b : bean.getPortfolioList()) {
						String query=getQuery("UPDATE_MND_INSTALLMENT_AUTO");
						Object args[] = new Object[4];
						args[0] = transactionNo;
						args[1] = b.getContractNo();
						args[2] = b.getLayerNo()==null?"0":b.getLayerNo();
						args[3] = b.getInstallmentNo();
						this.mytemplate.update(query,args);
			        }
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/

	private Double parseNumber(String dirtyNumber) {
        if (dirtyNumber == null || dirtyNumber.isEmpty()) return 0d;
        return Double.parseDouble(dirtyNumber.replaceAll(",", "").trim());
    }

    private Double getTotalDebit(Map<String, Object> map) {
        List<String> debits = new ArrayList<String>();
        debits.add("commissionOC");
        debits.add("brokerageOC");
        debits.add("taxOC");
        debits.add("otherCostOC");
        debits.add("whtOC");
        Double total = 0d;
        for (String key : debits) {
            total += parseNumber(map.get(key).toString());
        }
        return total;
    }

    private Double getDC(String oc, String exchange) {
    	  DecimalFormat df = new DecimalFormat("#.00");
        if (oc == null || exchange == null)
            return 0d;
        Double ocd = parseNumber(oc.replaceAll(",", "")), exchanged = parseNumber(exchange.replaceAll(",", ""));
        return Double.parseDouble(df.format(ocd / exchanged));
    }

    private Double getOc(String prem, String percent) {
        if (prem == null || percent == null) return 0d;
        Double premd = parseNumber(prem.replaceAll(",", "").trim()),
                percentd = parseNumber(percent.replaceAll(",", "").trim());
        return premd * (percentd / 100);
    }

	@Override
	public List<PortfolioBean> getConfirmedList(PortfolioBean beanObj, Object MenuRights) {
		List<PortfolioBean> finalList = new ArrayList<PortfolioBean>();
        try {
            String query = "";
            Object[] obj = new Object[7];
            obj[0] = beanObj.getProductId();
            obj[1] = beanObj.getBranchCode();
            obj[2] = beanObj.getBranchCode();
            obj[3] = beanObj.getProductId();
            obj[4] = beanObj.getBranchCode();
            obj[5] = beanObj.getBranchCode();
            obj[6] = beanObj.getBranchCode();
            query = getQuery("GET_NEW_CONTRACT_LIST");
           
            
            if(StringUtils.isNotBlank(beanObj.getSearchType()) && null !=beanObj.getSearchType()){
            	if(StringUtils.isNotBlank(beanObj.getProposalNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getProposalNoSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_PERNO);
            	}
            	if(StringUtils.isNotBlank(beanObj.getContractNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getContractNoSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_CONNO);
            	}
        		if(StringUtils.isNotBlank(beanObj.getCompanyNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getCompanyNameSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_COMNAME);
            	}
        		if(StringUtils.isNotBlank(beanObj.getBrokerNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getBrokerNameSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_BRONAME);
            	}
        		if(StringUtils.isNotBlank(beanObj.getDepartmentNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getDepartmentNameSearch() + "%")});
            		 query += " AND UPPER(B.TMAS_DEPARTMENT_NAME) LIKE UPPER(?)";
            	}
        		if(StringUtils.isNotBlank(beanObj.getBouquetNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getBouquetNoSearch() + "%")});
            		 query += " AND UPPER(A.Bouquet_No) LIKE UPPER(?)";
            	}
        		if(StringUtils.isNotBlank(beanObj.getSubclassSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getSubclassSearch() + "%")});
            		 query += " AND UPPER((select RTRIM(XMLAGG(XMLELEMENT(E,TMAS_SPFC_NAME,',')).EXTRACT('//text()'),',')  from TMAS_SPFC_MASTER SPFC where SPFC.TMAS_SPFC_ID in(select * from table(SPLIT_TEXT_FN(replace(E.RSK_SPFCID,' ', '')))) AND  SPFC.TMAS_PRODUCT_ID = E.RSK_PRODUCTID AND SPFC.BRANCH_CODE = E.BRANCH_CODE)) LIKE UPPER(?)";
            	}
            	if("1".equalsIgnoreCase(beanObj.getProductId())){
        		if(StringUtils.isNotBlank(beanObj.getInsuredNameSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getInsuredNameSearch() + "%")});
            		 query += " AND UPPER(E.RSK_INSURED_NAME) LIKE UPPER(?)";
            	}
            	if(StringUtils.isNotBlank(beanObj.getUwYearSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getUwYearSearch() + "%")});
                    query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
            	}if(StringUtils.isNotBlank(beanObj.getUnderwriterSearch())){
            		String qry ="SELECT UWR_CODE FROM UNDERWRITTER_MASTER WHERE BRANCH_CODE="+beanObj.getBranchCode()+" and UPPER(UNDERWRITTER) like UPPER('%" + beanObj.getUnderwriterSearch()+ "%') and UWR_STATUS='Y'";
            		String res =this.mytemplate.queryForObject(qry,String.class);
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + res + "%")});
            		 query += " AND UPPER(E.RSK_UNDERWRITTER) LIKE UPPER(?) ";
            	}
            	}else{
            		if(StringUtils.isNotBlank(beanObj.getUwYearSearch1())){
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getUwYearSearch1() + "%")});
                        query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_UYEAR);
                	}if(StringUtils.isNotBlank(beanObj.getUnderwriterSearch1())){
                		String qry ="SELECT UWR_CODE FROM UNDERWRITTER_MASTER WHERE BRANCH_CODE="+beanObj.getBranchCode()+" and UPPER(UNDERWRITTER) like UPPER('%" + beanObj.getUnderwriterSearch1()+ "%') and UWR_STATUS='Y'";
                		String res =this.mytemplate.queryForObject(qry,String.class);
                		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + res + "%")});
                		 query += " AND UPPER(E.RSK_UNDERWRITTER) LIKE UPPER(?) ";
                	}
            	}if(StringUtils.isNotBlank(beanObj.getOfferNoSearch())){
            		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + beanObj.getOfferNoSearch() + "%")});
            		query += " AND A.OFFER_NO LIKE ? ";
            		
            	}
            	
            	beanObj.setType("Yes");
            }else{
            	beanObj.setProposalNoSearch("");
            	beanObj.setContractNoSearch("");
            	beanObj.setCompanyNameSearch("");
            	beanObj.setBrokerNameSearch("");
            	beanObj.setDepartmentNameSearch("");
            	beanObj.setInsuredNameSearch("");
            	beanObj.setUwYearSearch("");
            	beanObj.setUnderwriterSearch("");
            	beanObj.setUwYearSearch1("");
            	beanObj.setUnderwriterSearch1("");
            	beanObj.setOfferNoSearch("");
            }
            if ("RP".equalsIgnoreCase(beanObj.getFlag())) {
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_RENEWALPENDING);
            } else {
                query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_PENDING);
            }
            if(StringUtils.isNotBlank(beanObj.getAttachedUW()) && !"ALL".equalsIgnoreCase(beanObj.getAttachedUW())) {
        		obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{(beanObj.getAttachedUW())});
        		 query += " AND UPPER(E.RSK_UNDERWRITTER) IN (select * from table(SPLIT_TEXT_FN(?)))";
        	}
            query += " " + getQuery(DBConstants.PORTFOLIO_SELECT_ORDERBYPRONO);
            logger.info("Query=>" + query);
            logger.info("Obj[]=>" + StringUtils.join(obj, ","));
            List<Map<String, Object>> list = this.mytemplate.queryForList(query, obj);
            //if(list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> tempMap = list.get(i);
                PortfolioBean tempBean = new PortfolioBean();
                tempBean.setContractNo(tempMap.get("CONTRACT_NO") == null ? "" : tempMap.get("CONTRACT_NO").toString());
                tempBean.setProposalNo(tempMap.get("PROPOSAL_NO") == null ? "" : tempMap.get("PROPOSAL_NO").toString());
                tempBean.setOfferNo(tempMap.get("OFFER_NO") == null ? "" : tempMap.get("OFFER_NO").toString());
                tempBean.setBouquetNo(tempMap.get("Bouquet_No") == null ? "" : tempMap.get("Bouquet_No").toString());
                tempBean.setAmendId(tempMap.get("AMEND_ID") == null ? "" : tempMap.get("AMEND_ID").toString());
                tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME") == null ? "" : tempMap.get("COMPANY_NAME").toString());
                tempBean.setDepartment_Name(tempMap.get("TMAS_DEPARTMENT_NAME") == null ? "" : tempMap.get("TMAS_DEPARTMENT_NAME").toString());
                tempBean.setSubClass(tempMap.get("TMAS_SPFC_NAME") == null ? "" : tempMap.get("TMAS_SPFC_NAME").toString());
                tempBean.setDepartmentId(tempMap.get("TMAS_DEPARTMENT_ID") == null ? "" : tempMap.get("TMAS_DEPARTMENT_ID").toString());
                tempBean.setInception_Date(tempMap.get("INCEPTION_DATE") == null ? "" : tempMap.get("INCEPTION_DATE").toString());
                tempBean.setExpiry_Date(tempMap.get("EXPIRY_DATE") == null ? "" : tempMap.get("EXPIRY_DATE").toString());
                tempBean.setInsuredName(tempMap.get("RSK_INSURED_NAME") == null ? "" : tempMap.get("RSK_INSURED_NAME").toString());
                tempBean.setQuote_Gendrated_date(tempMap.get("ACCOUNT_DATE") == null ? "" : tempMap.get("ACCOUNT_DATE").toString());
                tempBean.setCedding_company_id(tempMap.get("CEDING_COMPANY_ID") == null ? "" : tempMap.get("CEDING_COMPANY_ID").toString());
                tempBean.setLayerNo(tempMap.get("LAYER_NO") == null ? "" : tempMap.get("LAYER_NO").toString());
                tempBean.setSectionNo(tempMap.get("SECTION_NO") == null ? "" : tempMap.get("SECTION_NO").toString());
                if (beanObj.getFlag() != null && "N".equalsIgnoreCase(beanObj.getFlag())) {
                    tempBean.setFlag("N");
                } else if ("RP".equalsIgnoreCase(beanObj.getFlag())) {
                    tempBean.setFlag("RP");
                    tempBean.setTitle("RP");
                   
                } else {
                    tempBean.setFlag("P");
                }
				/*if(tempMap.get("BASE_LAYER")!=null)
					tempBean.setBaseLayer(tempMap.get("BASE_LAYER")==null?"":tempMap.get("BASE_LAYER").toString());
				else
					tempBean.setBaseLayer("");*/
                if (tempMap.get("BASE_LAYER") != null) {
                    tempBean.setBaseLayer(tempMap.get("BASE_LAYER").toString());
                    tempBean.setContractno1(tempMap.get("CONTRACT_NO") == null ? "" : tempMap.get("CONTRACT_NO").toString());
                    tempBean.setLay1("layer");
                } else {
                    tempBean.setBaseLayer("");
                }
                tempBean.setUwYear(tempMap.get("UW_YEAR") == null ? "" : tempMap.get("UW_YEAR").toString());
                tempBean.setUwMonth(tempMap.get("UW_MONTH") == null ? "" : tempMap.get("UW_MONTH").toString());
                tempBean.setUnderwritter(tempMap.get("UNDERWRITTER") == null ? "" : tempMap.get("UNDERWRITTER").toString());
                tempBean.setBrokerName(tempMap.get("BROKER_NAME") == null ? "" : tempMap.get("BROKER_NAME").toString());
                tempBean.setOld_Contract(tempMap.get("OLD_CONTRACTNO") == null ? "" : "0".equals(tempMap.get("OLD_CONTRACTNO")) == true ? "" : tempMap.get("OLD_CONTRACTNO").toString());
                tempBean.setButtonSelectionList(getConfimedButtonList(tempBean,MenuRights));
                finalList.add(tempBean);
            }
        } catch (Exception e) {
            logger.debug("Exception @ {" + e + "}");
        }
        return finalList;
	}

	private List<Map<String, Object>> getConfimedButtonList(PortfolioBean tempBean, Object menuRights) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			
			
			if( menuRights.toString().contains("V")){
				map.put("TYPE","V");
				map.put("DETAIL_NAME","View");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("V")){
				map.put("TYPE","PL");
				map.put("DETAIL_NAME","Pl. Summar");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("V")){
				map.put("TYPE","C");
				map.put("DETAIL_NAME","Contract");
				list.add(map);
				map=new HashMap<String,Object>();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
