package com.maan.service;

import java.text.DecimalFormat;
import java.util.List;

import com.maan.bean.FaculPremiumBean;
import com.maan.bean.PortfolioBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.common.util.DropDownControllor;
import com.maan.dao.PortfolioDAO;
import com.maan.dao.impl.PortfolioDAOImpl;

public class PortfolioService 
{
	final PortfolioDAO  portfolioDAO=(PortfolioDAO) new PortfolioDAOImpl();
    private FaculPremiumService faculPremiumService = new FaculPremiumService();
    private RiskDetailsService riskService = new RiskDetailsService();
    private ProportionalPremiumService proportionalPremiumService = new ProportionalPremiumService();
    public List<PortfolioBean> getPendingList(final PortfolioBean beanObj, Object MenuRights)
    {
        return portfolioDAO.getPendingList(beanObj,MenuRights);

    }
    public List<PortfolioBean> getAutoPendingList(final PortfolioBean beanObj,String countryId)
    {
        List<PortfolioBean> list = portfolioDAO.getAutoPendingList(beanObj);
        for (PortfolioBean bean : list) {
            if (beanObj.getProductId().equals("1")) {

            } else {

            }
        }
        return list;

    }

    public static String makeDoubleString(String numeric, int i) {
        try {
            Double n = Double.parseDouble(numeric.trim().replaceAll(",",""));
            StringBuilder sb = new StringBuilder();
            int c = i;
            while (c-->0) {
                sb.append("0");
            }
            DecimalFormat df = new DecimalFormat("##0." + sb.toString());
            return df.format(n);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public List<PortfolioBean> getContractsList(final PortfolioBean beanObj, Object MenuRights)
	{
		return portfolioDAO.getContractsList(beanObj,MenuRights);
		
	}
	public List<PortfolioBean> getRejectedList(final PortfolioBean beanObj) 
	{
		return portfolioDAO.getRejectedList(beanObj);
		
	}
	public List<PortfolioBean> getHistoryList(final PortfolioBean beanObj) 
	{
		return portfolioDAO.getHistoryList(beanObj);
		
	}

    public boolean validateOpenPeriod(PortfolioBean bean) {
        return portfolioDAO.validateOpenPeriod(bean);
    }

    public void procAuto(PortfolioBean bean, String type, String countryId) {
        portfolioDAO.procAuto(bean,type,countryId);
    }
	public List<PortfolioBean> getConfirmedList(PortfolioBean bean, Object menuRights) {
		return portfolioDAO.getConfirmedList( bean, menuRights);
	}
}
