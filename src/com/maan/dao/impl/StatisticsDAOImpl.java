package com.maan.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.bean.FaculPremiumBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.LogUtil;
import com.maan.dao.StatisticsDAO;

public class StatisticsDAOImpl extends MyJdbcTemplate implements StatisticsDAO {
	String query="";
	Logger LOGGER = LogUtil.getLogger(StatisticsDAOImpl.class);

	public void SlideScenario(FaculPremiumBean bean) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		
		String slide  = "";
		String combine ="";
		String premium ="";
		String preCombine="";
		String loss ="";
		String lossCombine="";
		String proposalNo = "";
		String bonus="";
		
		try{
			Object args[] = null;
			if("2".equalsIgnoreCase(bean.getProductId())){
                args=new Object[2];
                args[0] = bean.getProposal_No();
                args[1] = bean.getDepartmentId();
                LOGGER.info("Query=>"+query);
    			LOGGER.info("Args=>"+StringUtils.join(args, ","));
				query = getQuery("GET_SLIDE_COMM_VALUE");
				result = this.mytemplate.queryForList(query,args);
				for(int i=0;i<result.size();i++){
					Map<String,Object> map = result.get(i);
					 slide  = map.get("RSK_SLADSCALE_COMM")==null?"": map.get("RSK_SLADSCALE_COMM").toString();
					 combine  =map.get("RSK_SLIDE_COMBIN_SUB_CLASS")==null?"": map.get("RSK_SLIDE_COMBIN_SUB_CLASS").toString();
					 premium = map.get("RSK_PROFIT_COMM")==null?"":map.get("RSK_PROFIT_COMM").toString();
					 preCombine =map.get("RSK_COMBIN_SUB_CLASS")==null?"":map.get("RSK_COMBIN_SUB_CLASS").toString();
					 loss =map.get("RSK_LOSS_PART_CARRIDOR")==null?"": map.get("RSK_LOSS_PART_CARRIDOR").toString();
					 lossCombine =map.get("RSK_LOSS_COMBIN_SUB_CLASS")==null?"":map.get("RSK_LOSS_COMBIN_SUB_CLASS").toString();
					 
					if(slide.equalsIgnoreCase("Y")){
                        if(combine.equalsIgnoreCase("1")){
                           bean.setSlideScenario("one");
                           bean.setSlidedepartId("ALL");
                        }
                        else if(combine.equalsIgnoreCase("2")){
                            bean.setSlideScenario("two");
                        }
					}
					if(premium.equalsIgnoreCase("1")){
						if(preCombine.equalsIgnoreCase("1")){
							bean.setPremiumScenario("one");
							bean.setPremiumdepartId("ALL");
						}
						else if(preCombine.equalsIgnoreCase("2")){
                            bean.setPremiumScenario("two");
                        }
					}
					if(loss.equalsIgnoreCase("Y")){
						if(lossCombine.equalsIgnoreCase("1")){
							bean.setLossScenario("one");
							bean.setLossdepartId("ALL");
						}
						else if(lossCombine.equalsIgnoreCase("2")){
                            bean.setLossScenario("two");
                        }
					}
				}
				if(combine.equalsIgnoreCase("2") || preCombine.equalsIgnoreCase("2")|| lossCombine.equalsIgnoreCase("2")) {
                    args=new Object[2];
                    args[0] = proposalNo;
                    args[1] = bean.getDepartmentId();
					query = getQuery("GET_SLIDE_COMM_VALUE2");
					LOGGER.info("Query=>"+query);
					LOGGER.info("Args=>"+StringUtils.join(args, ","));
					result = this.mytemplate.queryForList(query, args);
					for (int i = 0; i < result.size(); i++) {
                        Map<String,Object> map =result.get(i);
						 slide  = map.get("RSK_SLADSCALE_COMM")==null?"":map.get("RSK_SLADSCALE_COMM").toString();
						 premium = map.get("RSK_PROFIT_COMM")==null?"":map.get("RSK_PROFIT_COMM").toString();
						 loss = map.get("RSK_LOSS_PART_CARRIDOR")==null?"":map.get("RSK_LOSS_PART_CARRIDOR").toString();
						if(slide.equalsIgnoreCase("Y") ){
                            bean.setSlideScenario("three");
						}
						if(premium.equalsIgnoreCase("1")){
							bean.setSlideScenario("three");
						}
						if(loss.equalsIgnoreCase("Y") ){
                            bean.setSlideScenario("three");
						}
					}
				}
			}
			else if("1".equalsIgnoreCase(bean.getProductId()) ||"3".equalsIgnoreCase(bean.getProductId())){
				String query=getQuery("GET_BONUS_ID");
				Object arg[]= new Object[1];
				arg[0] =bean.getProposal_No();
				if("3".equalsIgnoreCase(bean.getProductId())){
					query+=" and P.RSK_LAYER_NO="+bean.getLayerNo();
				}
				bean.setBonusStatus(this.mytemplate.queryForObject(query, arg,String.class));
			}
			getCurrencyName(bean);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private void getCurrencyName(FaculPremiumBean bean) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
	try{
		String query=getQuery("GET_CURRENCY_ID");
		Object args[]=new Object[2];
		args[0] = bean.getProposal_No();
		args[1] = bean.getContractNo();
		result = this.mytemplate.queryForList(query,args);
		for(int i=0;i<result.size();i++){
		Map<String,Object> map = result.get(i);
		bean.setAmendId(map.get("RSK_ENDORSEMENT_NO")==null?"":map.get("RSK_ENDORSEMENT_NO").toString());
		if("2".equalsIgnoreCase(bean.getProductId())){
			bean.setSlideCurrency(map.get("RSK_ORIGINAL_CURR")==null?"":map.get("RSK_ORIGINAL_CURR").toString());
			bean.setProfitCurrency(map.get("RSK_ORIGINAL_CURR")==null?"":map.get("RSK_ORIGINAL_CURR").toString());
			bean.setLossCurrency(map.get("RSK_ORIGINAL_CURR")==null?"":map.get("RSK_ORIGINAL_CURR").toString());
		}
		else if("1".equalsIgnoreCase(bean.getProductId()) || "3".equalsIgnoreCase(bean.getProductId())){
			bean.setBonusCurrency(map.get("RSK_ORIGINAL_CURR")==null?"":map.get("RSK_ORIGINAL_CURR").toString());
		}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	}

	public List<Map<String, Object>> getColumnHeaderlist(FaculPremiumBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> ColumnHeaderlis=new ArrayList<Map<String,Object>>();
		String query ="";
		try{
			Map<String,Object>map= new HashMap<String, Object>();
			map.put("RANGE", "Item");
			ColumnHeaderlis.add(map);
			map=new HashMap<String,Object>();
			map.put("RANGE", "Contract No");
			ColumnHeaderlis.add(map);
			map=new HashMap<String,Object>();
			map.put("RANGE", "UWY");
			ColumnHeaderlis.add(map);
			query=getQuery("GET_COLUMN_HEADER");
			Object args[]=new Object[5];
			args[0] = bean.getPeriodFrom();
			args[1] = bean.getPeriodFrom();
			args[2] = bean.getPeriodFrom();
			args[3] = bean.getPeriodFrom();
			args[4] = bean.getPeriodTo();
			LOGGER.info("Query==>"+query);
			LOGGER.info("Args==>"+StringUtils.join(args,","));
			list=this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object>map1=list.get(i);
					ColumnHeaderlis.add(map1);
				}
			}
			map=new HashMap<String,Object>();
			map.put("RANGE", "Total");
			ColumnHeaderlis.add(map);
		}catch(Exception e){
			//logger.debug("Exception @ {" + e + "}");	
		}

		return ColumnHeaderlis;
	}

	public List<Map<String, Object>> getRenewalStatisticsList( FaculPremiumBean bean,String type) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String query ="";
		Object args[]=null;
		query=getQuery("GET_RENEWAL_STATISTICS_LIST");
	
		args=new Object[9];
		args[0] = bean.getPeriodFrom();
		args[1] = bean.getPeriodTo();
		args[2] = bean.getUwFrom();
		args[3] = bean.getUwTo();
		args[4] = bean.getContractNo();
		args[5] = bean.getBranchCode();
		args[6] = type;
		args[7] = bean.getUwFrom();
		args[8] = bean.getUwTo();
		LOGGER.info("Query==>"+query);
		LOGGER.info("Args==>"+StringUtils.join(args,","));
		list=this.mytemplate.queryForList(query,args);
		return list;
	}

	public List<Map<String, Object>> getRenewalCalStatisticsList( FaculPremiumBean bean, String type) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String query ="";
		Object args[]=null;
		if("treaty".equals(type)){
		query=getQuery("GET_TREATY_RESULT_UW");
		}else if("calimratio".equals(type)){
			query=getQuery("GET_CLAIM_RATIO_UW");
		}else if("combined".equals(type)){
			query=getQuery("GET_COMINED_RATIO_UW");
		}
	
		args=new Object[32];
		args[0] = bean.getPeriodFrom();
		args[1] = bean.getPeriodTo();
		args[2] = bean.getUwFrom();
		args[3] = bean.getUwTo();
		args[4] = bean.getContractNo();
		args[5] = bean.getBranchCode();
		args[6] = bean.getUwFrom();
		args[7] = bean.getUwTo();
		
		args[8] = bean.getPeriodFrom();
		args[9] = bean.getPeriodTo();
		args[10] = bean.getUwFrom();
		args[11] = bean.getUwTo();
		args[12] = bean.getContractNo();
		args[13] = bean.getBranchCode();
		args[14] = bean.getUwFrom();
		args[15] = bean.getUwTo();
		
		args[16] = bean.getPeriodFrom();
		args[17] = bean.getPeriodTo();
		args[18] = bean.getUwFrom();
		args[19] = bean.getUwTo();
		args[20] = bean.getContractNo();
		args[21] = bean.getBranchCode();
		args[22] = bean.getUwFrom();
		args[23] = bean.getUwTo();
		
		args[24] = bean.getPeriodFrom();
		args[25] = bean.getPeriodTo();
		args[26] = bean.getUwFrom();
		args[27] = bean.getUwTo();
		args[28] = bean.getContractNo();
		args[29] = bean.getBranchCode();
		args[30] = bean.getUwFrom();
		args[31] = bean.getUwTo();
		list=this.mytemplate.queryForList(query,args);
		LOGGER.info("Query==>"+query);
		LOGGER.info("Args==>"+StringUtils.join(args,","));
		return list;
	}
}
