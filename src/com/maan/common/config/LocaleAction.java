/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maan.common.config;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class LocaleAction extends ActionSupport {

    private String url;
    private String nameSpace;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String execute() {
        HttpServletRequest request = ServletActionContext.getRequest();        
        return "SUCCESS";
    }
}
