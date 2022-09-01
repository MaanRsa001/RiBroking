package com.maan.dao;

import java.util.List;

import com.maan.bean.PortfolioBean;

public interface PortfolioDAO 
{
	List<PortfolioBean> getPendingList(final PortfolioBean beanObj);
	List<PortfolioBean> getAutoPendingList(final PortfolioBean beanObj);
	List<PortfolioBean> getContractsList(final PortfolioBean beanObj, Object menuRights);
	List<PortfolioBean> getRejectedList(final PortfolioBean beanObj);
	List<PortfolioBean> getHistoryList(PortfolioBean beanObj);

    boolean validateOpenPeriod(PortfolioBean bean);

    void procAuto(PortfolioBean bean, String type, String countryId);
}
