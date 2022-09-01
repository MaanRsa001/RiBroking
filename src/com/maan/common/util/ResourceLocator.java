package com.maan.common.util;
/**
 * @author Raja.K
 *
 * Common Login Template
 */
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class ResourceLocator {
  private static ResourceLocator instance=new ResourceLocator();
	
  private ResourceLocator(){
  }
  
  public static ResourceLocator getInstance(){
	  return instance;
  }
  
  public  ResourceBundle getDBBundle() {
	
	  ResourceBundle dbBundle=null;
	  try{
		  dbBundle=ResourceBundle.getBundle("app_queries");
	  }catch(MissingResourceException e){
		  
	  }
	  return dbBundle;
  }
	
	

}
