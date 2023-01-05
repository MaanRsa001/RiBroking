package com.maan.dao.ApiCaller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.maan.common.LogUtil;
import com.maan.common.util.ResourceLocator;
import com.opensymphony.xwork2.ActionContext;



public class ApiConfig {
	HttpServletRequest request = ServletActionContext.getRequest();
	Map<String, Object> session = ActionContext.getContext().getSession();
	final static Logger logger = LogUtil.getLogger(ApiConfig.class);
	protected static  String customerApi_link=null;
	protected static  String customerApi_username=null;
	protected static  String customerApi_password=null;
	protected static  String customerApi_auth=null;	
	protected static  String vehicle_link=null;
	protected static  String vehicle_username=null;
	protected static  String vehicle_password=null;
	protected static  String vehicle_auth=null;
	private static Map<String,String> tableRef=new HashMap<String,String>();
	private static ResourceBundle bundle = ResourceLocator.getWebservice();
	public String getValueFromWebservice(String key) {
		String string = tableRef.get(key);
			if(string==null || string.length()==0) {
				string=bundle.getString(key);
				tableRef.put(key, string);
			}  
		return string;
	}
	public String callAPI(String Link,String authorization,String request){
		try{
			logger.info("Request JSON===>" + request);
			logger.info("Link===>" + Link);
			StringEntity entity = new StringEntity(request,"UTF-8");
			CloseableHttpClient httpclient = HttpClients.createDefault();			 
			HttpPost httpPost = new HttpPost(Link); 
			httpPost.setHeader("Content-type", "application/json; charset=utf-8"); 
			if(StringUtils.isNotBlank(authorization)) {
				authorization = ("Bearer "+authorization);
				httpPost.setHeader(HttpHeaders.AUTHORIZATION,authorization);
			}
			httpPost.setEntity(entity);	
			CloseableHttpResponse response = httpclient.execute(httpPost); 
			if(response.getStatusLine().getStatusCode()<=400){
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer responseAsString = new StringBuffer();
				String line = "";				
				while ((line = rd.readLine()) != null) {
					responseAsString.append(line);					
				}					
				 logger.info("Response JSON===>" + responseAsString.toString());				 
				return responseAsString.toString();
			}

		}catch (Exception e) {
			logger.info("Exception @ API Configuration===>" +e);
			e.printStackTrace();
		}
		return null;
	}

	protected String callAPI(String reqFrom, String url,
			String auth ,String request){
		try{
			String authorization = "login".equalsIgnoreCase(reqFrom)?"":("Bearer "+auth);
			logger.info(reqFrom+" Api Url: " + url);
			System.out.println(reqFrom+" Api Url: " + url);
			logger.info(reqFrom+" Authorization Header: "+auth);
			System.out.println(reqFrom+" Authorization Header: "+authorization);
			logger.info(reqFrom+" Json Request: " + request);
			System.out.println(reqFrom+" Json Request: " + request);
			StringEntity entity = new StringEntity(request,"UTF-8");
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-type", "application/json; charset=utf-8");
			if(StringUtils.isNotBlank(auth)) {
			httpPost.setHeader(HttpHeaders.AUTHORIZATION,authorization);
			}
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			//if(response.getStatusLine().getStatusCode()<=400){
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer responseAsString = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					responseAsString.append(line);
				}
				 logger.info(reqFrom+" Json Response: "+responseAsString.toString());
				 System.out.println(reqFrom+" Json Response: "+responseAsString.toString());
				 
				return responseAsString.toString();
				
			//}

		}catch (Exception e) {
			logger.info(reqFrom+" Exception @ ApiConfig.callAPI(): " +e);
			e.printStackTrace();
		}
		return null;
	}
	public String MyPropertyLoad() {
		String result="";
	         
	        InputStream is = null;
	        Properties prop = null;
	        try {
	            prop = new Properties();
	            is = new FileInputStream(new File("ApplicationConstants.properties"));
	            prop.load(is);
	            result=prop.getProperty("db.host");
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        return result;
	    }
	protected String callAPIPost(String Link,String authorization,String request){
		try{
			System.out.println("request:"+request);
			System.out.println("Link===>" + Link);
			System.out.println("authorization===>" + authorization);
			StringEntity entity = new StringEntity(request,"UTF-8");
			CloseableHttpClient httpclient = HttpClients.createDefault();			 
			HttpPost httpPost = new HttpPost(Link); 
			httpPost.setHeader("Content-type", "application/json; charset=utf-8"); 
			httpPost.setHeader("Authorization", authorization); 
			httpPost.setEntity(entity);	
			CloseableHttpResponse response = httpclient.execute(httpPost); 
			if(response.getStatusLine().getStatusCode()<=400 || response.getStatusLine().getStatusCode()==403 ){
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				StringBuffer responseAsString = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					responseAsString.append(line);
				}
				System.out.println(responseAsString.toString());
				return responseAsString.toString();
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	protected String callAPIGet(String Link,String authorization){
		try{
			System.out.println("request:"+Link);
			 
			CloseableHttpClient httpclient = HttpClients.createDefault();			 
			HttpGet httpPost = new HttpGet(Link); 
			httpPost.setHeader("Content-type", "application/json; charset=utf-8"); 
			httpPost.setHeader("Authorization", authorization); 
			 
			CloseableHttpResponse response = httpclient.execute(httpPost); 
			if(response.getStatusLine().getStatusCode()<=400){
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				StringBuffer responseAsString = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					responseAsString.append(line);
				}
				return responseAsString.toString();
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	protected synchronized String getActualLink(String content,Map<String,Object> hmap) {
		if(StringUtils.isNotBlank(content)){
			for (Entry<String, Object> entry : hmap.entrySet()) {	
				if("driverdetails".equalsIgnoreCase(entry.getKey()))
					content = content.replaceAll("<"+entry.getKey()+">", String.valueOf(entry.getValue()==null?"":entry.getValue()));
				else
					content = content.replaceAll("<"+entry.getKey()+">", String.valueOf(entry.getValue()==null?"":entry.getValue()).replaceAll("\"", " \\\\\\\\\""));
		    }
		}
		return content;
	}
	
	public void setTokenValues(JSONObject json) {
		String token = "";
		json = (JSONObject) json.get("DefaultValue");
		token = json.get("Token") == null ? "" : json.get("Token").toString();
		session.put("TOKEN_SPRING", token);
	}
}
