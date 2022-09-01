package com.maan.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;

import com.maan.bean.AdjustmentBean;
import com.maan.bean.PortfolioBean;
import com.maan.bean.TreasuryBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.impl.admin.AdminDAOImpl;

public class AdjustmentDAO extends MyJdbcTemplate{
	private static final Logger LOGGER = LogUtil.getLogger(AdjustmentDAO.class);
	public List<AdjustmentBean> getAdjustmentList(AdjustmentBean bean, Map<String, String> receiveAdjustAmountMap) {
		Double a=0.0,b=0.0,c=0.0;
		LOGGER.info("getAdjustmentList || Enter");
		List<AdjustmentBean> adjustmentList = new ArrayList<AdjustmentBean>();
		try{
			String query = "";
			Object[] obj=null;
			if(bean.getTest().equals("ALL")){
			 obj = new Object[8];
			query=getQuery(DBConstants.ADJUSTMENT_LIST);
			if(bean.getAmountType().equals("1")){
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{( bean.getAmount())});
				query=query+" where pending_amount=? ORDER BY   CONTRACT_NO, TRANSACTION_NO";
			}
			else if(bean.getAmountType().equals("2")){
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{( bean.getAmount())});
				query=query+" where pending_amount<=? ORDER BY   CONTRACT_NO, TRANSACTION_NO";
			}
			else if(bean.getAmountType().equals("3")){
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{( bean.getAmount())});
				query=query+" where pending_amount>=? ORDER BY   CONTRACT_NO, TRANSACTION_NO";
			}
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getCurrencyId();
			obj[2]=bean.getBrokerId();
			if("63".equalsIgnoreCase(bean.getBrokerId()))
				obj[3]=bean.getCedingId();
			else        	
				obj[3]=bean.getBrokerId();
			obj[4]=bean.getBranchCode();
			obj[5]=bean.getCurrencyId();
			obj[6]=bean.getBrokerId();
			if("63".equalsIgnoreCase(bean.getBrokerId()))
				obj[7]=bean.getCedingId();
			else        	
				obj[7]=bean.getBrokerId();
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			}else{
				obj = new Object[4];
				query=getQuery(DBConstants.ADJUSTMENT_TRANSACTIONCP);
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getTransactionNo();
				obj[2]=bean.getBranchCode();
				obj[3]=bean.getTransactionNo();
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			}
			List list=this.mytemplate.queryForList(query,obj);
			for(int i=0; i<list.size() ; i++) {
				Map map=(Map)list.get(i);
				AdjustmentBean bean1=new AdjustmentBean();
				bean1.setTransactionNo(map.get("TRANSACTION_NO")==null?"":map.get("TRANSACTION_NO").toString());
				bean1.setTransactionDate(map.get("ADATE")==null?"":map.get("ADATE").toString()); 
				bean1.setContractNo(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
				bean1.setLayerNo(map.get("LAYER_NO")==null?"":map.get("LAYER_NO").toString());
				bean1.setBrokerName(map.get("broker_name")==null?"":map.get("broker_name").toString());
				bean1.setCedingName(map.get("CEDING_COMPANY_name")==null?"":map.get("CEDING_COMPANY_name").toString());
				bean1.setCurrency(map.get("currency_name")==null?"":map.get("currency_name").toString());
				bean1.setPendingAmount(map.get("pending_amount")==null?"":DropDownControllor.formatter(map.get("pending_amount").toString()));
				bean1.setAdjustAmount(map.get("pending_amount")==null?"":DropDownControllor.formatter(map.get("pending_amount").toString()));
				bean1.setType(map.get("BUSINESS_TYPE")==null?"":map.get("BUSINESS_TYPE").toString());
				bean1.setProductName(map.get("PRODUCT_NAME")==null?"":map.get("PRODUCT_NAME").toString());
				bean1.setCurrencyId(map.get("CURRENCY_ID")==null?"":map.get("CURRENCY_ID").toString());
				bean1.setSubClass(map.get("DEPT_ID")==null?"":map.get("DEPT_ID").toString());
				bean1.setProposlaNo(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
				bean1.setAdjustType(bean.getAdjustType());
				if(bean1.getType().equals("Premium")){
					a=a+Double.parseDouble(map.get("pending_amount")==null?"":map.get("pending_amount").toString());

				}else{
					b=b+Double.parseDouble(map.get("pending_amount")==null?"":map.get("pending_amount").toString());
				}
				if(receiveAdjustAmountMap.containsKey(bean1.getTransactionNo())) {
					String string=receiveAdjustAmountMap.get(bean1.getTransactionNo());
					String[] st=string.split("~");
					bean1.setAdjustType(st[1]);
					bean1.setCheck(st[2]);
					bean1.setAdjustAmount(DropDownControllor.formatter(st[0].replace(",", "")));
				}
				else if(!receiveAdjustAmountMap.containsKey(bean1.getTransactionNo()) && bean.getMode()!="list") {
					bean1.setCheck("false");
				}
				adjustmentList.add(bean1);
			}
			bean.setUnUtilizedAmt(Double.toString(a-b));
			LOGGER.info("getAdjustmentList || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return adjustmentList;
	}
	public void AddAdjustment(AdjustmentBean bean, String branchCode,Map<String, String> receiveAdjustAmountMap) {

		try {
			List<AdjustmentBean> payList = getAdjustmentList(bean,receiveAdjustAmountMap);
			
			Double a=0.0,b=0.0,c=0.0;
			String updateQry;
			updateQry=getQuery(DBConstants.PAYMENT_SELECT_GETNEXTSNO);
			LOGGER.info("Query=>"+updateQry);
			String serialNo =(String) this.mytemplate.queryForObject(updateQry,String.class);
			bean.setSerialNo(serialNo);
			LOGGER.info("Result=>"+serialNo);
			
			for(int i=0;i<payList.size();i++) {
				
				AdjustmentBean form= payList.get(i);
				if(receiveAdjustAmountMap.containsKey(form.getTransactionNo())) {	 
					Object args[]=new String[17];
					 args[0]=serialNo;	
					 args[1]=form.getContractNo();
					 args[2]=form.getLayerNo();
					 args[3]=form.getProductName();
					 args[4]=form.getTransactionNo();
					 args[5]=bean.getAdjustmentDate();
						if("PREMIUM".equalsIgnoreCase(form.getType())){
							String string=receiveAdjustAmountMap.get(form.getTransactionNo());
							String[] st=string.split("~");
							args[6]= st[0].replace(",", "");
							args[12] = st[1];
						 	args[7]="P";
						 	updateQry = getQuery(DBConstants.PAYMENT_UPDATE_RSKPREMALLODTLS);
						 	Object[] updateArgs = new Object[5];
						 	updateArgs[0] = st[0].replace(",", "");
						 	updateArgs[1] = bean.getLoginId();
						 	updateArgs[2] = bean.getBranchCode();
							updateArgs[3] = form.getContractNo();
							updateArgs[4] = form.getTransactionNo();
							LOGGER.info("Update Query=>"+updateQry);
							LOGGER.info("Arg[]=>"+StringUtils.join(updateArgs,","));
							//LOGGER.info("Args[0]=>"+receiveAdjustAmountMap.get(form.getTransactionNo())+"\nArgs[1]=>"+form.getContractNo()+"\nArgs[2]=>"+form.getTransactionNo());
						 	int res=this.mytemplate.update(updateQry,updateArgs);
						 	LOGGER.info("Udate Result=>"+res);
						 	updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PREMSETSTAUS);
						 	updateArgs = new Object[5];
							updateArgs[0] = "Allocated";
							updateArgs[1] = bean.getLoginId();
						 	updateArgs[2] = bean.getBranchCode();
							updateArgs[3] = form.getContractNo();
							updateArgs[4] = form.getTransactionNo();
							LOGGER.info("Update Query=>"+updateQry);
							LOGGER.info("Arg[]=>"+StringUtils.join(updateArgs,","));
							//LOGGER.info("Args[0]=>"+updateArgs[0]+"\nArgs[1]=>"+form.getContractNo()+"\nArgs[2]=>"+form.getTransactionNo());
						 	res=this.mytemplate.update(updateQry,updateArgs);
						 	LOGGER.info("Udate Result=>"+res);
						 	a=a+Double.parseDouble(st[0].replace(",", ""));
						}
						else if("CLAIM".equalsIgnoreCase(form.getType())) {
							String string=receiveAdjustAmountMap.get(form.getTransactionNo());
							String[] st=string.split("~");
							args[6] = st[0].replace(",", "");
							args[12] = st[1];
						 	args[7]="C";
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMTALLODTLS);
							Object[] updateArgs = new Object[5];
							updateArgs[0] = st[0].replace(",", "");
							updateArgs[1] = bean.getLoginId();
						 	updateArgs[2] = bean.getBranchCode();
							updateArgs[3] = form.getContractNo();
							updateArgs[4] = form.getTransactionNo();
							LOGGER.info("Update Query=>"+updateQry);
							LOGGER.info("Arg[]=>"+StringUtils.join(updateArgs,","));
							//LOGGER.info("Args[0]=>"+receiveAdjustAmountMap.get(form.getTransactionNo())+"\nArgs[1]=>"+form.getContractNo()+"\nArgs[2]=>"+form.getTransactionNo());
							int res=this.mytemplate.update(updateQry,updateArgs);
							LOGGER.info("Udate Result=>"+res);
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMSETSTAUS);
							updateArgs = new Object[5];
							updateArgs[0] = "Allocated";
							updateArgs[1] = bean.getLoginId();
						 	updateArgs[2] = bean.getBranchCode();
							updateArgs[3] = form.getContractNo();
							updateArgs[4] = form.getTransactionNo();
							LOGGER.info("Update Query=>"+updateQry);
							LOGGER.info("Arg[]=>"+StringUtils.join(updateArgs,","));
							//LOGGER.info("Args[0]=>"+updateArgs[0]+" Args[1]=>"+form.getContractNo()+"\nArgs[2]=>"+form.getTransactionNo());
							res=this.mytemplate.update(updateQry,updateArgs);
							LOGGER.info("Udate Result=>"+res);
							b = b + Double.parseDouble(st[0].replace(",", ""));
						}
					args[8]="Y";
					args[9]="0";
					args[10]="";
					args[11]=form.getCurrencyId();
					args[13]=bean.getLoginId();
					args[14]=bean.getBranchCode();
					args[15]=form.getSubClass();
					args[16]=form.getProposlaNo();
					updateQry=getQuery(DBConstants.ADJUSTMENT_INSERT);
					LOGGER.info("Query=>"+updateQry);
					LOGGER.info("Args==> " + StringUtils.join(args,","));
					int res=this.mytemplate.update(updateQry,args);
					LOGGER.info("Result=>"+res);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
	public List<AdjustmentBean> getAdjustmentPayRecList(AdjustmentBean bean, Map<String, String> receiveAdjustAmountMap) {
		LOGGER.info("getAdjustmentPayRecList || Enter");
		Double a=0.0,b=0.0,c=0.0;
		List<AdjustmentBean> adjustmentList = new ArrayList<AdjustmentBean>();
		try{
			String query = "";
			Object[] obj=null;
			if(bean.getTest().equals("ALL")){
				obj = new Object[4];
				//obj = new Object[5];
			query=getQuery(DBConstants.ADJUSTMENT_PAY_REC_LIST);
			if(bean.getAmountType().equals("1")){
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{( bean.getAmount())});
				query=query+"  and (Nvl (B.Amount, 0) - Nvl (B.Allocated_Till_Date,0))=? order  by B.Receipt_Sl_No";
			}
			else if(bean.getAmountType().equals("2")){
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{( bean.getAmount())});
				query=query+"  and (Nvl (B.Amount, 0) - Nvl (B.Allocated_Till_Date,0))<=? order  by B.Receipt_Sl_No";
			}
			else if(bean.getAmountType().equals("3")){
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{( bean.getAmount())});
				query=query+"  and (Nvl (B.Amount, 0) - Nvl (B.Allocated_Till_Date,0))>=? order  by B.Receipt_Sl_No";
			}
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getCurrencyId();
			obj[2]=bean.getBrokerId();
			if("63".equalsIgnoreCase(bean.getBrokerId()))
				obj[3]=bean.getCedingId();
			else        	
				obj[3]=bean.getBrokerId();
			//obj[4]=bean.getAmount();
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			}else{
				obj = new Object[2];
				query=getQuery(DBConstants.ADJUSTMENT_TRANSACTIONRP);
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getTransactionNo();
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			}
			List list=this.mytemplate.queryForList(query,obj);
			
			for(int i=0; i<list.size() ; i++) {
				Map map=(Map)list.get(i);
				AdjustmentBean bean1=new AdjustmentBean();
				bean1.setTransactionNo(map.get("TRANSACTION_NO")==null?"":map.get("TRANSACTION_NO").toString());
				bean1.setTransactionDate(map.get("ADATE")==null?"":map.get("ADATE").toString()); 
				bean1.setContractNo(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
				bean1.setLayerNo(map.get("LAYER_NO")==null?"":map.get("LAYER_NO").toString());
				bean1.setBrokerName(map.get("broker_name")==null?"":map.get("broker_name").toString());
				bean1.setCedingName(map.get("CEDING_COMPANY_name")==null?"":map.get("CEDING_COMPANY_name").toString());
				bean1.setCurrency(map.get("currency_name")==null?"":map.get("currency_name").toString());
				bean1.setPendingAmount(map.get("pending_amount")==null?"":DropDownControllor.formatter(map.get("pending_amount").toString()));
				bean1.setAdjustAmount(map.get("pending_amount")==null?"":DropDownControllor.formatter(map.get("pending_amount").toString()));
				bean1.setType(map.get("BUSINESS_TYPE")==null?"":map.get("BUSINESS_TYPE").toString());
				bean1.setProductName(map.get("PRODUCT_NAME")==null?"":map.get("PRODUCT_NAME").toString());
				bean1.setCurrencyId(map.get("CURRENCY_ID")==null?"":map.get("CURRENCY_ID").toString());
				if(bean1.getType().equals("Payment")){
					a=a+Double.parseDouble(map.get("pending_amount")==null?"":map.get("pending_amount").toString());

				}else{
					b=b+Double.parseDouble(map.get("pending_amount")==null?"":map.get("pending_amount").toString());
				}
				if(receiveAdjustAmountMap.containsKey(bean1.getTransactionNo())) {
					String string=receiveAdjustAmountMap.get(bean1.getTransactionNo());
					String[] st=string.split("~");
					bean1.setAdjustType(st[1]);
					bean1.setCheck(st[2]);
					bean1.setAdjustAmount(DropDownControllor.formatter(st[0].replace(",", "")));
				}
				else if(!receiveAdjustAmountMap.containsKey(bean1.getTransactionNo()) && bean.getMode()!="list") {
					bean1.setCheck("false");
				}
				bean1.setAdjustType(bean.getAdjustType());
				adjustmentList.add(bean1);
			}
			bean.setUnUtilizedAmt(Double.toString(a-b));
			LOGGER.info("getAdjustmentList || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return adjustmentList;
	}
	public void AddAdjustmentPayRec(AdjustmentBean bean, String branchCode,Map<String, String> receiveAdjustAmountMap) {
		try {
			List<AdjustmentBean> payList = getAdjustmentPayRecList(bean,receiveAdjustAmountMap);
			
			Double a=0.0,b=0.0,c=0.0;
			String updateQry;
			updateQry=getQuery(DBConstants.PAYMENT_SELECT_GETNEXTSNO);
			LOGGER.info("Query=>"+updateQry);
			String serialNo =(String) this.mytemplate.queryForObject(updateQry,String.class);
			bean.setSerialNo(serialNo);
			LOGGER.info("Result=>"+serialNo);
			
			for(int i=0;i<payList.size();i++) {
				
				AdjustmentBean form= payList.get(i);
				if(receiveAdjustAmountMap.containsKey(form.getTransactionNo())) {
					String string=receiveAdjustAmountMap.get(form.getTransactionNo());
					String[] st=string.split("~");
					Object[] updateArgs = new Object[5];
					updateArgs[0] = st[0].replace(",", "");
					updateArgs[1] = bean.getLoginId();
					updateArgs[2] = bean.getBranchCode();
					updateArgs[3] = form.getTransactionNo();
					updateArgs[4] = form.getCurrencyId();
					updateQry=getQuery(DBConstants.PAYMENT_UPDATE_ALLOTRANDTLS);
					LOGGER.info("Update Query=>"+updateQry);
					LOGGER.info("Args[0]=>"+updateArgs[0]+" Args[1]=>"+updateArgs[1]+"\nArgs[2]=>"+updateArgs[2]);
					int res=this.mytemplate.update(updateQry,updateArgs);
					LOGGER.info("Result=>"+res);
					
					Object args[]=new String[17];
					 args[0]=serialNo;	
					 args[1]=form.getContractNo();
					 args[2]=form.getLayerNo();
					 args[3]=form.getProductName();
					 args[4]="";
					 args[5]=bean.getAdjustmentDate();
					 args[6]= st[0].replace(",", "");
					 args[7]= form.getType().equals("Payment")?"PT":"RT";	
					 args[8]="Y";
					 args[9]="0";
					 args[10]=form.getTransactionNo();
					 args[11]=form.getCurrencyId();
					 args[12] = st[1];
					 args[13]=bean.getLoginId();
					args[14]=bean.getBranchCode();
					args[15]=form.getSubClass();
					args[16]=form.getProposlaNo();
					updateQry=getQuery(DBConstants.ADJUSTMENT_INSERT);
					LOGGER.info("Query=>"+updateQry);
					LOGGER.info("Args==> " + StringUtils.join(args,","));
					int res1=this.mytemplate.update(updateQry,args);
					LOGGER.info("Result=>"+res1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public Map<String, List<AdjustmentBean>> adjustmentView(AdjustmentBean bean) {
		LOGGER.info("PaymentDAOImpl allocateView() || Enter");
		Map<String,List<AdjustmentBean>> viewMap=new HashMap<String,List<AdjustmentBean>>();
		String selectQry=null;
		try{
			Double a=0.0,b=0.0,c=0.0;
			final List<AdjustmentBean> allocatedList = new ArrayList<AdjustmentBean>();
			Object args[] = new Object[1];
			args[0] = bean.getSerialNo();
			if("ReverseInsert".equalsIgnoreCase(bean.getMode()) || "R".equalsIgnoreCase(bean.getStatus())){
			 selectQry = getQuery(DBConstants.ADJUSTMENT_GETREVERSE_TRANSDTLS);
			}
			else{
				 selectQry = getQuery(DBConstants.ADJUSTMENT_GETALLOTRANSDTLS);
			}
			LOGGER.info("Query=>"+selectQry);
			LOGGER.info("Args==> " + StringUtils.join(args,","));
			List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
			LOGGER.info("Result Size=>"+list.size());
			if (list.size()>0) {
				AdjustmentBean tempBean;
				for (int i = 0; i < list.size(); i++) {
					tempBean = new AdjustmentBean();
					Map<String,Object> resMap = list.get(i);
					tempBean.setSerialNo(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
					tempBean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
					bean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
					tempBean.setTransactionNo(resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString());
					tempBean.setContractNo(resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString());
					tempBean.setProductName(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
					tempBean.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
					if("ReverseInsert".equalsIgnoreCase(bean.getMode()) || "R".equalsIgnoreCase(bean.getStatus())){
					tempBean.setPayamount(resMap.get("REVERSAL_AMOUNT")==null?"":DropDownControllor.formatter(resMap.get("REVERSAL_AMOUNT").toString()));
					}
					else{
						tempBean.setPayamount(resMap.get("PAID_AMOUNT")==null?"":DropDownControllor.formatter(resMap.get("PAID_AMOUNT").toString()));
					}
					tempBean.setCurrencyId(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
					tempBean.setReceiptNo(resMap.get("RECEIPT_NO")==null?"":resMap.get("RECEIPT_NO").toString());
					if("ReverseInsert".equalsIgnoreCase(bean.getMode()) || "R".equalsIgnoreCase(bean.getStatus())){
						if(tempBean.getType().equals("P")){
							a=a+Double.parseDouble(resMap.get("REVERSAL_AMOUNT")==null?"":resMap.get("REVERSAL_AMOUNT").toString());

						}else if(tempBean.getType().equals("C")){
							b=b+Double.parseDouble(resMap.get("REVERSAL_AMOUNT")==null?"":resMap.get("REVERSAL_AMOUNT").toString());
						}else if(tempBean.getType().equals("PT")){
							a=a+Double.parseDouble(resMap.get("REVERSAL_AMOUNT")==null?"":resMap.get("REVERSAL_AMOUNT").toString());
						}else if(tempBean.getType().equals("RT")){
							b=b+Double.parseDouble(resMap.get("REVERSAL_AMOUNT")==null?"":resMap.get("REVERSAL_AMOUNT").toString());
						}
					}
					else{
					if(tempBean.getType().equals("P")){
						a=a+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());

					}else if(tempBean.getType().equals("C")){
						b=b+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
					}else if(tempBean.getType().equals("PT")){
						a=a+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());

					}else if(tempBean.getType().equals("RT")){
						b=b+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
					}
					}
					//tempBean.setStatus(("R".equals(resMap.get("ADJUSTMENT_TYPE")==null?"":resMap.get("ADJUSTMENT_TYPE").toString())?"Round Off":"Write Off"));
					tempBean.setAdjustType(resMap.get("ADJUSTMENT_TYPE")==null?"":resMap.get("ADJUSTMENT_TYPE").toString());
					selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
					Object[] selectArgs = new Object[2];
					selectArgs[0] = bean.getBranchCode();
					selectArgs[1] = tempBean.getCurrencyId();
					LOGGER.info("Query=>"+selectQry);
					LOGGER.info("Args==> " + StringUtils.join(selectArgs,","));
					tempBean.setCurrencyName(this.mytemplate.queryForObject(selectQry,selectArgs,String.class).toString());
					LOGGER.info("Result=>"+tempBean.getCurrencyName());
						allocatedList.add(tempBean);
				}
			}
			viewMap.put("AllocatedList", allocatedList);
			bean.setUnUtilizedAmt(DropDownControllor.formatter(Double.toString(a-b)));
		}
		catch(Exception e) {
			LOGGER.debug("Exception @ { " + e + " } ");
		}
		return viewMap;
	}
	public List<AdjustmentBean> getAdjustmentDoneList(AdjustmentBean bean) {
		LOGGER.info("PaymentDAOImpl getAdjustmentDoneList() || Enter");
		List<AdjustmentBean> adjustmentList = new ArrayList<AdjustmentBean>();
		try{
			Object args[] = new Object[1];
			args[0] = bean.getBranchCode();
			/*args[1] = bean.getBranchCode();
			args[2] = bean.getBranchCode();
			args[3] = bean.getBranchCode();
			args[4] = bean.getBranchCode();
			args[5] = bean.getBranchCode();
			args[6] = bean.getBranchCode();
			args[7] = bean.getBranchCode();
			args[8] = bean.getBranchCode();
			args[9] = bean.getBranchCode();
			args[10] = bean.getBranchCode();
			args[11] = bean.getBranchCode();*/
			String query="";
			query = getQuery(DBConstants.ADJUSTMENT_GETALLOTRANSDTLS_LIST);
			if(bean.getSearchBy()!=null){
				if("1".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where SNO like '%"+bean.getSearchValue()+"%'";
				}else if("2".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where UPPER(CEDINGCOMPANY) like UPPER('%"+bean.getSearchValue()+"%')";
				}else if("3".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where UPPER(BROKERNAME) like UPPER('%"+bean.getSearchValue()+"%')";
				}
				else if("4".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where UPPER(TYPE) like UPPER('%"+bean.getSearchValue()+"%')";
				}
			}
			query +="  order by sno desc";
			LOGGER.info("Query=>"+query);
			LOGGER.info("Args==> " + StringUtils.join(args,","));
			List<Map<String,Object>> list = this.mytemplate.queryForList(query,args);
			LOGGER.info("Result Size=>"+list.size());
			if (list.size()>0) {
				AdjustmentBean tempBean;
				for (int i = 0; i < list.size(); i++) {
					tempBean = new AdjustmentBean();
					Map<String,Object> resMap = list.get(i);
					tempBean.setSerialNo(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
					tempBean.setBrokerName(resMap.get("BROKERNAME")==null?"":resMap.get("BROKERNAME").toString());
					tempBean.setCedingName(resMap.get("CEDINGCOMPANY")==null?"":resMap.get("CEDINGCOMPANY").toString());
					tempBean.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
					tempBean.setCurrencyName(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
					tempBean.setAdjustType(resMap.get("ADJUSTMENT_TYPE")==null?"":resMap.get("ADJUSTMENT_TYPE").toString());
					tempBean.setPayamount(resMap.get("PAIDAMOUNT")==null?"":DropDownControllor.formatter(resMap.get("PAIDAMOUNT").toString()));
					tempBean.setStatus(resMap.get("status")==null?"":resMap.get("status").toString());
					adjustmentList.add(tempBean);
				}
			}
		}
		catch(Exception e) {
			LOGGER.debug("Exception @ { " + e + " } ");
		}
		return adjustmentList;
	}
	public String GetMaxDate(AdjustmentBean bean) {
		String result="";
		try{
			String query=getQuery(DBConstants.ADJUSTMENT_LIST_MAXDATE);
			LOGGER.info("Select Query ==> " + query);
			LOGGER.info("Args[0]==> " +bean.getTransDate());
			LOGGER.info("Args[0]==> " +StringUtils.join(bean.getTransDate(),","));
			
			result=(String)this.mytemplate.queryForObject(query,new Object[]{StringUtils.join(bean.getTransDate(),",")},String.class);
			System.out.println(result);
		}catch(Exception e){
			LOGGER.debug("Exception @ {" + e + "}");	
		}

		return result;
	}
	public String insertReverse(AdjustmentBean bean) {
		LOGGER.info("PaymentDAOImpl reverseInsert() || Enter");
		List<TreasuryBean> list1=new ArrayList<TreasuryBean>();
		String updateQry=null;
		String curencyId="";
			try{
				Double a=0.0,b=0.0,c=0.0;
				final List<AdjustmentBean> allocatedList = new ArrayList<AdjustmentBean>();
				Object args[] = new Object[1];
				args[0] = bean.getSerialNo();
				String selectQry = getQuery(DBConstants.ADJUSTMENT_GETALLOTRANSDTLS);
				LOGGER.info("Query=>"+selectQry);
				LOGGER.info("Args==> " + StringUtils.join(args,","));
				List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
				LOGGER.info("Result Size=>"+list.size());
				if (list.size()>0) {
					AdjustmentBean tempBean;
					for (int i = 0; i < list.size(); i++) {
						tempBean = new AdjustmentBean();
						Map<String,Object> resMap = list.get(i);
						//resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString();
						curencyId=resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString();
						if("Y".equalsIgnoreCase(resMap.get("STATUS")==null?"":resMap.get("STATUS").toString())){
							TreasuryBean pay=new  TreasuryBean();
							pay.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
							bean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
							selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
							Object[] selectArgs = new Object[2];
							selectArgs[0] = bean.getBranchCode();
							selectArgs[1] = resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString();
							LOGGER.info("Query=>"+selectQry);
							LOGGER.info("Args[0]=>"+selectArgs[0]);
							LOGGER.info("Args[1]=>"+selectArgs[1]);
							String result=this.mytemplate.queryForObject(selectQry,selectArgs,String.class).toString();
							LOGGER.info("Result=>"+result);
							bean.setCurrency(result);
							pay.setCurrency(result);
							pay.setTransactionno(resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString());
							pay.setContractno(resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString());
							pay.setProductname(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
							pay.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
							pay.setReceiptcheck(resMap.get("RECEIPT_NO")==null?"":resMap.get("RECEIPT_NO").toString());
							/*if(pay.getTransactionType()==null){
								if(pay.getReceiptcheck().equalsIgnoreCase("")){
									updateQry = getQuery(DBConstants.PAYMENT_UPDATE_ALLOCATEDDTLS2);	
								}
								else{
								updateQry = getQuery(DBConstants.PAYMENT_UPDATE_ALLOCATEDDTLS1);
								}
							}*/
							if(!pay.getReceiptcheck().equalsIgnoreCase("")){
								updateQry = getQuery(DBConstants.PAYMENT_UPDATE_ALLOCATEDDTLS1);	
							}
							else{
								updateQry = getQuery(DBConstants.PAYMENT_UPDATE_ALLOCATEDDTLS);
							}
							Object[]  updateArgs = new Object[8];
							
							updateArgs[0] = "R";
							updateArgs[1] = bean.getReverseDate();
							updateArgs[2] = resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString();
							updateArgs[3] = "0";
							updateArgs[4] = bean.getLoginId();
							updateArgs[5] = bean.getBranchCode();
								if(pay.getReceiptcheck().equalsIgnoreCase("")){
									updateArgs[6] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
								}
								else{
									updateArgs[6] = resMap.get("RECEIPT_NO")==null?"":resMap.get("RECEIPT_NO").toString();
								}
							/*if(pay.getTransactionType()!=null){
								updateArgs[6] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
							}
							else{
								if(pay.getReceiptcheck().equalsIgnoreCase("")){
									updateArgs[6] =resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
								}
								else{
									updateArgs[6] = resMap.get("RECEIPT_NO")==null?"":resMap.get("RECEIPT_NO").toString();
								}
							}*/
							updateArgs[7] = resMap.get("SNO")==null?"":resMap.get("SNO").toString();
							LOGGER.info("Query=>"+updateQry);
							int k=0;
							for(Object obj:updateArgs){
								LOGGER.info("Arg["+k+++"]==>"+obj);
							}
							int res=this.mytemplate.update(updateQry, updateArgs);
							LOGGER.info("Result=>"+res);
							if("P".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString()))
							{ 
								updateQry = getQuery(DBConstants.PAYMENT_UPDATE_RSKPREMDTLS1);
								updateArgs = new Object[6];
								updateArgs[0]="Pending";
								updateArgs[1] = resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString();
								updateArgs[2] = bean.getLoginId();
								updateArgs[3] = bean.getBranchCode();
								updateArgs[4] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
								updateArgs[5] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
								LOGGER.info("Query=>"+updateQry);
								k=0;
								for(Object obj:updateArgs){
									LOGGER.info("Arg["+k+++"]==>"+obj);
								}
								res=this.mytemplate.update(updateQry, updateArgs);
								LOGGER.info("Result=>"+res);
								selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRSKPREMDTLS);
								selectArgs = new Object[2];
								selectArgs[0] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
								selectArgs[1] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
								LOGGER.info("Query=>"+selectQry);
								k=0;
								for(Object obj:selectArgs){
									LOGGER.info("Arg["+k+++"]==>"+obj);
								}
								pay.setNetdue(this.mytemplate.queryForInt(selectQry, selectArgs)+"");
								LOGGER.info("Result=>"+pay.getNetdue());
								a=a+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
							}else if("C".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString()))
							{
								updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMTDTLS1);
								updateArgs = new Object[6];
								updateArgs[0]="Pending";
								updateArgs[1] = resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString();
								updateArgs[2] = bean.getLoginId();
								updateArgs[3] = bean.getBranchCode();
								updateArgs[4] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
								updateArgs[5] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
								LOGGER.info("Query=>"+updateQry);
								k=0;
								for(Object obj:updateArgs){
									LOGGER.info("Arg["+k+++"]==>"+obj);
								}
								res=this.mytemplate.update(updateQry, updateArgs);
								LOGGER.info("Result=>"+res);
								selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETCLAIMPYMTDTLS);
								selectArgs = new Object[2];
								selectArgs[0] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
								selectArgs[1] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
								LOGGER.info("Query=>"+selectQry);
								k=0;
								for(Object obj:selectArgs){
									LOGGER.info("Arg["+k+++"]==>"+obj);
								}
								pay.setPayamount(this.mytemplate.queryForInt(selectQry, selectArgs)+"");
								LOGGER.info("Result=>"+pay.getPayamount());
								b=b+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
							}
							else if("RT".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString()) || "PT".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString()))
							{
								/*if("RT".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString()))
								{
									//c=a-b;
									a=a+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());

								}
								else if("PT".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString()))
								{
									//c=b-a;
									a=a+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());

								}*/
								updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PYMTRETDTLS);
								updateArgs = new Object[5];
								///updateArgs[0]="Pending";
								updateArgs[0] = resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString();
								updateArgs[1] = bean.getLoginId();
								updateArgs[2] = bean.getBranchCode();
								updateArgs[3] =resMap.get("RECEIPT_NO")==null?"":resMap.get("RECEIPT_NO").toString();
								updateArgs[4] = curencyId;
								
								LOGGER.info("Query=>"+updateQry);
								 k=0;
								for(Object obj:updateArgs){
									LOGGER.info("Arg["+k+++"]==>"+obj);
								}
								int res1=this.mytemplate.update(updateQry, updateArgs);
								LOGGER.info("Result=>"+res1);
								selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTRETDTLS);
								selectArgs = new Object[2];
								selectArgs[0] =resMap.get("RECEIPT_NO")==null?"":resMap.get("RECEIPT_NO").toString();
								selectArgs[1] = curencyId;
								LOGGER.info("Query=>"+selectQry);
								k=0;
								for(Object obj:selectArgs){
									LOGGER.info("Arg["+k+++"]==>"+obj);
								}
							}
							//list.add(pay);
						}

					}
					
					
				}
			}
			catch(Exception e) {
				LOGGER.debug("Exception @ { " + e + " } ");
			}
				
		return null;
	}
	public String openPerionDate(AdjustmentBean bean) {
		
		try{
			Object args[] = new Object[2];
			args[0] ="Y";
			args[1] =bean.getBranchCode();
			String selectQry = getQuery(DBConstants.ADJUSMENT_OPEN_PERION_DATE);
			List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
			if (list.size()>0) {
				//AdjustmentBean tempBean;
				for (int i = 0; i < list.size(); i++) {
					//tempBean = new AdjustmentBean();
					Map<String,Object> resMap = list.get(i);
					bean.setOpStartDate(resMap.get("START_DATE")==null?"":resMap.get("START_DATE").toString());
					bean.setOpEndDate(resMap.get("END_DATE")==null?"":resMap.get("END_DATE").toString());
				}
			}
		}
		catch(Exception e) {
			LOGGER.debug("Exception @ { " + e + " } ");
		}
		return null;
	}
	public String allocatedDate(AdjustmentBean bean) {
		try{
			Object args[] = new Object[1];
			args[0] =bean.getSerialNo();
			String selectQry = getQuery(DBConstants.ADJUSMENT_ALLOCATION_PERION_DATE);
			List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
			if (list.size()>0) {
				//AdjustmentBean tempBean;
				for (int i = 0; i < list.size(); i++) {
					//tempBean = new AdjustmentBean();
					Map<String,Object> resMap = list.get(i);
					bean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
				}
			}
		}
		catch(Exception e) {
			LOGGER.debug("Exception @ { " + e + " } ");
		}
		return null;
	}
}
	


