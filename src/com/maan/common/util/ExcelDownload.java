package com.maan.common.util;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.slf4j.Logger;

import com.maan.bean.ReportsBean;
import com.maan.common.util.LogUtil;

public class ExcelDownload {	
	public static Logger logger = LogUtil.getLogger(ExcelDownload.class);
	public static String writeExcel(Map<String, Object> headerInfo, List<Map<String, Object>> columnInfo, List<Map<String, Object>> recordsList, ByteArrayOutputStream bos, String typeId) throws Exception
	{
		
			try{
			  System.setProperty("java.awt.headless", "true");
			  HSSFWorkbook wb = new HSSFWorkbook();
			  HSSFSheet sheet = wb.createSheet("Sheet1");
			  sheet.getPrintSetup().setLandscape(true);
			  sheet.getPrintSetup().setScale((short)95);			  
			  HSSFDataFormat format = wb.createDataFormat();
			  HSSFFont font = wb.createFont();
			  HSSFFont font1 = wb.createFont();
			  font.setFontName("Arial");
			  font.setFontHeightInPoints((short)9);
			  font.setBoldweight((short)10);
			  font1.setFontName("Arial");
			  font1.setFontHeightInPoints((short)8);
			  font1.setBoldweight((short)10);
			  HSSFRow row = sheet.createRow((short)0);
			  HSSFRow row1 = sheet.createRow((short)1);
			  HSSFRow row2 = sheet.createRow((short)2);
			  HSSFRow row3 = sheet.createRow((short)3);
			  HSSFRow row4 = sheet.createRow((short)4);
			  HSSFRow row5 = sheet.createRow((short)5);
			  HSSFRow row6 = sheet.createRow((short)6);
			  HSSFRow row7 = sheet.createRow((short)7);
			  HSSFRow row9 = sheet.createRow((short)9);
			  HSSFRow totalrow,totalrow1;
			  HSSFCellStyle style = wb.createCellStyle();
			  HSSFCellStyle style1 = wb.createCellStyle();
			  HSSFCellStyle style2 = wb.createCellStyle();
			  HSSFCellStyle style3 = wb.createCellStyle();
			  HSSFCellStyle style4 = wb.createCellStyle();
			  HSSFCellStyle style5 = wb.createCellStyle();
			  HSSFCellStyle style6 = wb.createCellStyle();
			  HSSFCellStyle style7 = wb.createCellStyle();
			  HSSFCellStyle style8 = wb.createCellStyle();
			  style.setFont(font); 
			  style7.setFont(font);
			  style7.setWrapText( true );
			  style7.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			  style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			  style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
			  style1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			  style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			  style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			  style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			  style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			  style1.setRightBorderColor(HSSFColor.BLACK.index);
			  style1.setLeftBorderColor(HSSFColor.BLACK.index);
			  style1.setTopBorderColor(HSSFColor.BLACK.index);
			  style1.setBottomBorderColor(HSSFColor.BLACK.index);
			  font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			  style1.setFont(font);
			  style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			  style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			  style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			  style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			  style2.setDataFormat(format.getFormat("dd/mm/yyyy"));
			  style2.setWrapText( true );
			  style2.setFont(font1);
			  style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			  style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
			  style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			  style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
			  style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			  style3.setWrapText( true );
			  style3.setFont(font1);
			  style4.setFont(font1);
			  style4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		      style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		      style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			  style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
			  style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			  style4.setDataFormat(format.getFormat("#,##0.00"));
			  style4.setWrapText( true );
			  style5.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		      style5.setBorderRight(HSSFCellStyle.BORDER_THIN);
		      style5.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			  style5.setBorderTop(HSSFCellStyle.BORDER_THIN);
			  style5.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			  style5.setWrapText( true );
			  style5.setFont(font1);
			  style8.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		      style8.setBorderRight(HSSFCellStyle.BORDER_THIN);
		      style8.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			  style8.setBorderTop(HSSFCellStyle.BORDER_THIN);
			  style8.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			  style8.setWrapText( true );
			  style8.setFont(font);
			  style6.setBorderRight(HSSFCellStyle.BORDER_THIN);
			  style6.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			  style6.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	  style6.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			  style6.setBorderTop(HSSFCellStyle.BORDER_THIN);
			  style6.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			  style6.setRightBorderColor(HSSFColor.BLACK.index);
			  style6.setLeftBorderColor(HSSFColor.BLACK.index);
			  style6.setTopBorderColor(HSSFColor.BLACK.index);
			  style6.setBottomBorderColor(HSSFColor.BLACK.index);
			  style6.setDataFormat(format.getFormat("#,##0.00"));
			  style6.setAlignment(HSSFCellStyle.ALIGN_RIGHT);	
			  style1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			  font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		  	  style6.setFont(font);
			  style1.setFont(font);
			  style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
			  List<Map<String, Object>> companyInfo=(List)headerInfo.get("CompanyInfo");
			  ReportsBean bean=(ReportsBean)headerInfo.get("SelectedInfo");
			  HSSFCell cell1,cell2,cell3,cell4,cell5,totalcell,totalcell1, datecell1, datecell2,datecell3,datecell4,datecell5,datecell6,datecell7,datecell8,cell6;
			  if(companyInfo!=null &&companyInfo.size()>0){
				  Map<String, Object> companyMap = (Map<String, Object>)companyInfo.get(0);
				  cell1 = row.createCell((short)3);
				  cell1.setCellValue(new HSSFRichTextString(companyMap.get("COMPANY_NAME")==null?"":companyMap.get("COMPANY_NAME").toString()));
				  cell1.setCellStyle(style);
				  sheet.autoSizeColumn((short)3);
				  cell2 = row1.createCell((short)3);
				  cell2.setCellValue(new HSSFRichTextString(((companyMap.get("POBOX")==null?"":(companyMap.get("POBOX").toString()+", "))+(companyMap.get("ADDRESS1")==null?"":(companyMap.get("ADDRESS1").toString()+" ")+(companyMap.get("ADDRESS2")==null?"":(companyMap.get("ADDRESS2").toString()))))));
				  cell2.setCellStyle(style);
				  sheet.autoSizeColumn((short)3);
				  cell3 = row2.createCell((short)3);
				  cell3.setCellValue(new HSSFRichTextString(companyMap.get("CITY")==null?"":(companyMap.get("CITY").toString()+"  ")+(companyMap.get("COUNTRY")==null?"":(companyMap.get("COUNTRY").toString()))));
				  cell3.setCellStyle(style);
				  sheet.autoSizeColumn((short)3);
				  cell4 = row3.createCell((short)3);
				  cell4.setCellValue(new HSSFRichTextString(companyMap.get("EMAIL")==null?"":"Email: "+(companyMap.get("EMAIL").toString()+"  ")+(companyMap.get("TELEPHONE")==null?"":"Tel: "+(companyMap.get("TELEPHONE").toString()+" - ")+(companyMap.get("FAX")==null?"":"Fax: "+(companyMap.get("FAX").toString())))));
				  cell4.setCellStyle(style);
				  sheet.autoSizeColumn((short)3);
				  cell5 = row4.createCell((short)3);
				  cell5.setCellValue(new HSSFRichTextString((companyMap.get("WEB_SITE")==null?"":(companyMap.get("WEB_SITE").toString()))));
				  cell5.setCellStyle(style);
				  sheet.autoSizeColumn((short)3);
			  }
			  cell6 = row5.createCell((short)3);
			  cell6.setCellValue(new HSSFRichTextString(bean.getReportName()));
			  cell6.setCellStyle(style7); 
			  sheet.addMergedRegion(new Region(0,(short)3,0,(short)6));
			  sheet.addMergedRegion(new Region(1,(short)3,1,(short)6));
			  sheet.addMergedRegion(new Region(2,(short)3,2,(short)6));
			  sheet.addMergedRegion(new Region(3,(short)3,3,(short)6));
		      sheet.addMergedRegion(new Region(4,(short)3,4,(short)6));
			  sheet.addMergedRegion(new Region(5,(short)3,5,(short)6));
			  HSSFCell cell[][] = new HSSFCell[recordsList.size()+11][columnInfo.size()]; 
	 		  for(int colNo=0;colNo<columnInfo.size();colNo++)
	 		  {
			        try{
			   				cell[0][colNo] = row9.createCell((short)colNo);
			   				Map<String, Object> columnMap=(Map<String, Object>)columnInfo.get(colNo);
							cell[0][colNo].setCellValue(new HSSFRichTextString((String)columnMap.get("EXCEL_HEADER_NAME")));
	                        cell[0][colNo].setCellStyle(style1);
	                        sheet.autoSizeColumn((short)colNo);
	                 }
	               catch(Exception eee){
							System.out.println("Error"+eee);
	                       eee.printStackTrace();
					}				
			   }
	 		  if(!(typeId.equalsIgnoreCase("12") ||  typeId.equalsIgnoreCase("11")|| typeId.equalsIgnoreCase("27")|| typeId.equalsIgnoreCase("28")||typeId.equalsIgnoreCase("29") ||typeId.equalsIgnoreCase("30") ||(typeId.equalsIgnoreCase("37") ||typeId.equalsIgnoreCase("38") ||typeId.equalsIgnoreCase("39") ||typeId.equalsIgnoreCase("40") ||typeId.equalsIgnoreCase("41") ||typeId.equalsIgnoreCase("42") ||typeId.equalsIgnoreCase("43") || typeId.equalsIgnoreCase("44"))  || typeId.equalsIgnoreCase("45")|| "46".equals(bean.getTypeId()) ||"47".equals(bean.getTypeId()) ||"48".equals(bean.getTypeId()) ||"49".equals(bean.getTypeId()) ||"50".equals(bean.getTypeId()) ||"51".equals(bean.getTypeId()) ||"52".equals(bean.getTypeId()) ||"53".equals(bean.getTypeId())))
	 		  {
	 		   datecell1 = row6.createCell((short)1);
	 		   datecell1.setCellValue(new HSSFRichTextString("Product :"));
			   datecell1.setCellStyle(style8); 
			   sheet.autoSizeColumn((short)1);
		       datecell2 = row6.createCell((short)2);
			   datecell2.setCellValue(new HSSFRichTextString(""+bean.getProductName()));
			   datecell2.setCellStyle(style5);
			   sheet.autoSizeColumn((short)2);
			   datecell3 = row6.createCell((short)3);
	 		   datecell3.setCellValue(new HSSFRichTextString("Ceding Name :"));
			   datecell3.setCellStyle(style8);
			   sheet.autoSizeColumn((short)3);
		       datecell4 = row6.createCell((short)4);
			   datecell4.setCellValue(new HSSFRichTextString(""+bean.getCedingCoName()));
			   datecell4.setCellStyle(style5);
			   sheet.autoSizeColumn((short)4);
			   datecell5 = row6.createCell((short)7);
			   datecell5.setCellValue(new HSSFRichTextString(bean.getStartDateLabelName()+" :"));
			   datecell5.setCellStyle(style8);
			   sheet.autoSizeColumn((short)7);
	 		   datecell6 = row6.createCell((short)8);
	 		   datecell6.setCellValue(new HSSFRichTextString(""+bean.getStart_date()));
	 		   datecell6.setCellStyle(style2);
	 		   sheet.autoSizeColumn((short)8);
	 		   datecell7 = row6.createCell((short)9);
			   datecell7.setCellValue(new HSSFRichTextString("UW Year :"));
			   datecell7.setCellStyle(style8);   
			   sheet.autoSizeColumn((short)9);
			   datecell8 = row6.createCell((short)10);
			   datecell8.setCellValue(new HSSFRichTextString("-1".equalsIgnoreCase(bean.getUwYear())?"All":bean.getUwYear()));
			   datecell8.setCellStyle(style5);
			   sheet.autoSizeColumn((short)10);
	           
	 		   datecell1 = row7.createCell((short)1);
			   datecell1.setCellValue(new HSSFRichTextString("LoginId :"));
			   datecell1.setCellStyle(style8);
			   sheet.autoSizeColumn((short)1);
		       datecell2 = row7.createCell((short)2);
			   datecell2.setCellValue(new HSSFRichTextString(""+bean.getLoginName()));
			   datecell2.setCellStyle(style5);
			   sheet.autoSizeColumn((short)2);
	 		   datecell3 = row7.createCell((short)3);
	 		   datecell3.setCellValue(new HSSFRichTextString("Broker Name :"));
	 		   datecell3.setCellStyle(style8);
	 		   sheet.autoSizeColumn((short)3);
	 	       datecell4 = row7.createCell((short)4);
	 		   datecell4.setCellValue(new HSSFRichTextString(bean.getBrokerName()));
	 		   datecell4.setCellStyle(style5);
	 		   sheet.autoSizeColumn((short)5);
	 	       datecell5 = row7.createCell((short)7);
	 		   datecell5.setCellValue(new HSSFRichTextString(bean.getEndDateLabelName()+" :"));
	 		   datecell5.setCellStyle(style8);
	 		   sheet.autoSizeColumn((short)7);
	 		   datecell6 = row7.createCell((short)8);
	 		   datecell6.setCellValue(new HSSFRichTextString(""+bean.getEnd_date()));
	 		   datecell6.setCellStyle(style2); 
	 		   sheet.autoSizeColumn((short)8);
	 		   datecell7 = row7.createCell((short)9);
			   datecell7.setCellValue(new HSSFRichTextString("Generated Date :"));
			   datecell7.setCellStyle(style8);
			   sheet.autoSizeColumn((short)9);
			   datecell8 = row7.createCell((short)10);
			   datecell8.setCellValue(new HSSFRichTextString(bean.getSysDate()));
			   datecell8.setCellStyle(style2);
			   sheet.autoSizeColumn((short)10);
			   //sheet.addMergedRegion(new Region(6,(short)4,6,(short)6));
			   //sheet.addMergedRegion(new Region(7,(short)4,7,(short)6));
	 		  }else if( typeId.equalsIgnoreCase("11") ||typeId.equalsIgnoreCase("37") ||typeId.equalsIgnoreCase("38") ||typeId.equalsIgnoreCase("39") ||typeId.equalsIgnoreCase("45")){
	 			 datecell1 = row6.createCell((short)1);
	 			 if(typeId.equalsIgnoreCase("11")){
		 		   datecell1.setCellValue(new HSSFRichTextString("Debtors Ageing Report Date as on :"));
	 			 }else  if(typeId.equalsIgnoreCase("37")){
		 		   datecell1.setCellValue(new HSSFRichTextString("Trial Balance as on :"));
	 			 }else  if(typeId.equalsIgnoreCase("38")){
		 		   datecell1.setCellValue(new HSSFRichTextString("Portfolio Out Pending as on :"));
	 			 }else  if(typeId.equalsIgnoreCase("39")){
		 		   datecell1.setCellValue(new HSSFRichTextString("Pending Statements as on :"));
	 			 }else  if(typeId.equalsIgnoreCase("45")){
		 		   datecell1.setCellValue(new HSSFRichTextString("Claims Outstanding as on :"));
	 			 }
				   datecell1.setCellStyle(style8); 
				   sheet.autoSizeColumn((short)1);
			       datecell2 = row6.createCell((short)2);
				   datecell2.setCellValue(new HSSFRichTextString(""+bean.getStart_date()));
				   datecell2.setCellStyle(style5);
				   sheet.autoSizeColumn((short)2);
	 		  }else if( typeId.equalsIgnoreCase("40") ||typeId.equalsIgnoreCase("41") ||typeId.equalsIgnoreCase("42") ||typeId.equalsIgnoreCase("43")){
		 			 datecell1 = row6.createCell((short)1);
			 		   datecell1.setCellValue(new HSSFRichTextString("Underwriting Year From :"));
					   datecell1.setCellStyle(style8); 
					   sheet.autoSizeColumn((short)1);
				       datecell2 = row6.createCell((short)2);
					   datecell2.setCellValue(new HSSFRichTextString(""+bean.getUwFrom()));
					   datecell2.setCellStyle(style5);
					   sheet.autoSizeColumn((short)2);
					   
					   datecell3 = row6.createCell((short)7);
					   datecell3.setCellValue(new HSSFRichTextString("Underwriting Year To :"));
					   datecell3.setCellStyle(style8); 
					   sheet.autoSizeColumn((short)1);
				       datecell4 = row6.createCell((short)8);
					   datecell4.setCellValue(new HSSFRichTextString(""+bean.getUwTo()));
					   datecell4.setCellStyle(style5);
					   sheet.autoSizeColumn((short)2);
					   
					   datecell5 = row7.createCell((short)1);
					   datecell5.setCellValue(new HSSFRichTextString("Financial Period From :"));
					   datecell5.setCellStyle(style8); 
					   sheet.autoSizeColumn((short)1);
				       datecell6 = row7.createCell((short)2);
					   datecell6.setCellValue(new HSSFRichTextString(""+bean.getPeriodFrom()));
					   datecell6.setCellStyle(style5);
					   sheet.autoSizeColumn((short)2);
					   
					   datecell7 = row7.createCell((short)7);
					   datecell7.setCellValue(new HSSFRichTextString("Financial Period To :"));
					   datecell7.setCellStyle(style8); 
					   sheet.autoSizeColumn((short)1);
				       datecell8 = row7.createCell((short)8);
					   datecell8.setCellValue(new HSSFRichTextString(""+bean.getPeriodTo()));
					   datecell8.setCellStyle(style5);
					   sheet.autoSizeColumn((short)2);
		 		  }
	 		 else if( typeId.equalsIgnoreCase("44") ){
	 			 datecell1 = row6.createCell((short)1);
		 		   datecell1.setCellValue(new HSSFRichTextString("Transaction Start Date :"));
				   datecell1.setCellStyle(style8); 
				   sheet.autoSizeColumn((short)1);
			       datecell2 = row6.createCell((short)2);
				   datecell2.setCellValue(new HSSFRichTextString(""+bean.getStart_date()));
				   datecell2.setCellStyle(style5);
				   sheet.autoSizeColumn((short)2);
				   
				   datecell3 = row6.createCell((short)7);
				   datecell3.setCellValue(new HSSFRichTextString("Transaction End date :"));
				   datecell3.setCellStyle(style8); 
				   sheet.autoSizeColumn((short)1);
			       datecell4 = row6.createCell((short)8);
				   datecell4.setCellValue(new HSSFRichTextString(""+bean.getEnd_date()));
				   datecell4.setCellStyle(style5);
				   sheet.autoSizeColumn((short)2);
				   
				   datecell5 = row7.createCell((short)1);
				   datecell5.setCellValue(new HSSFRichTextString("Contract No :"));
				   datecell5.setCellStyle(style8); 
				   sheet.autoSizeColumn((short)1);
			       datecell6 = row7.createCell((short)2);
				   datecell6.setCellValue(new HSSFRichTextString(""+bean.getContractNo()));
				   datecell6.setCellStyle(style5);
				   sheet.autoSizeColumn((short)2);
				   
				   datecell7 = row7.createCell((short)7);
				   datecell7.setCellValue(new HSSFRichTextString("Layer No :"));
				   datecell7.setCellStyle(style8); 
				   sheet.autoSizeColumn((short)1);
			       datecell8 = row7.createCell((short)8);
				   datecell8.setCellValue(new HSSFRichTextString(""+bean.getLayerNo()));
				   datecell8.setCellStyle(style5);
				   sheet.autoSizeColumn((short)2);
	 		  }
			   Map<String,Double> subTotal=new HashMap();
	 		   if(recordsList!=null && recordsList.size()>0)
	 			{
	 			    int rowNo=10; 
	 				for(int i=0; i<recordsList.size(); i++,rowNo++)
	 				{
	 					int colNo=0;
	 					row = sheet.createRow(rowNo);
	 					Map<String, Object> map=(Map<String, Object>)recordsList.get(i);
	 					Set keySet=map.keySet();
	 					Iterator itr=keySet.iterator();
	 					row5 = sheet.createRow((short)rowNo);
	 					while(itr.hasNext() && colNo<columnInfo.size())
	 					{	
	 						//String key="", value="";
	 						String value="";
	 						cell[rowNo][colNo]= row5.createCell((short)colNo);
	 					//	key=(String)itr.next();
	 						Map columnMap=(Map)columnInfo.get(colNo);
	 	     				if("NUMBER".equalsIgnoreCase(columnMap.get("DATA_TYPE").toString())){
	 	     					cell[rowNo][colNo].setCellValue(Double.parseDouble((map.get(columnMap.get("FIELD_NAME"))==null?"0":map.get(columnMap.get("FIELD_NAME"))).toString()));
	 	     					cell[rowNo][colNo].setCellStyle(style3);
	 	     					sheet.autoSizeColumn((short)colNo);
	 	     				}else if("DATE".equalsIgnoreCase(columnMap.get("DATA_TYPE").toString())){
	 	     					value=(map.get(columnMap.get("FIELD_NAME"))==null?"":map.get(columnMap.get("FIELD_NAME"))).toString();
	 	     					cell[rowNo][colNo].setCellValue(map.get(columnMap.get("FIELD_NAME"))==null?"":map.get(columnMap.get("FIELD_NAME"))+"");
	 	     					cell[rowNo][colNo].setCellStyle(style2);
	 	     					sheet.autoSizeColumn((short)colNo);
	 	     				}else if("VARCHAR".equalsIgnoreCase(columnMap.get("DATA_TYPE").toString())){
	 	     					value=(map.get(columnMap.get("FIELD_NAME"))==null?"":map.get(columnMap.get("FIELD_NAME"))).toString();
	 	     					cell[rowNo][colNo].setCellValue(new HSSFRichTextString(value));
	 	     					cell[rowNo][colNo].setCellStyle(style5);
	 	     					sheet.autoSizeColumn((short)colNo);
	 	 	     			}else if("DECIMAL".equalsIgnoreCase(columnMap.get("DATA_TYPE").toString()))
	 	     				{
	 	 	     				double val=Double.parseDouble((map.get(columnMap.get("FIELD_NAME"))==null?"0":map.get(columnMap.get("FIELD_NAME"))).toString().replaceAll(",","").replaceAll("#", "0"));
	 	     					cell[rowNo][colNo].setCellValue(val);
	 	     					cell[rowNo][colNo].setCellStyle(style4);
	 	     					sheet.autoSizeColumn((short)colNo);
	 	     					/*if(!"3".equalsIgnoreCase(typeId) && !"4".equalsIgnoreCase(typeId)){
	 	     					if("Y".equalsIgnoreCase((String)columnMap.get("SUMATIONYN")))
	 	     					{
	 	     						if(i==0)
	 	     						{
	 	     							subTotal.put("SubTot"+colNo,val);
	 	     						}else
	 	     						{
	 	     							double subTot=Double.parseDouble(subTotal.get("SubTot"+colNo).toString());
	 	     							subTotal.remove("SubTot"+colNo);
	 	     							subTotal.put("SubTot"+colNo,(subTot+val));
	 	     						}
	 	     					}
	 	     					}*/
	 	     				}
	 	     				colNo++;
	 					}
	 				}
	 				totalrow = sheet.createRow((short)rowNo);
	 				totalrow1 = sheet.createRow((short)8);
					for(int colNo=0;colNo<columnInfo.size(); colNo++)
					{
						Map columnMap=(Map)columnInfo.get(colNo);
						/*if(!"3".equalsIgnoreCase(typeId) && !"4".equalsIgnoreCase(typeId)){
						if("Y".equalsIgnoreCase((String)columnMap.get("SUMATIONYN")))
	  					{
							totalcell = totalrow.createCell((short)colNo);
							totalcell.setCellValue(subTotal.get("SubTot"+colNo));
							//totalcell.setCellFormula(columnMap.get("FORMULA")==null?"":(columnMap.get("FORMULA").toString()+(rowNo)+")"));
							totalcell.setCellStyle(style6);
							sheet.autoSizeColumn((short)colNo);
							totalcell1 = totalrow1.createCell((short)colNo);
							totalcell1.setCellFormula(columnMap.get("FORMULA")==null?"":(columnMap.get("FORMULA").toString()+(rowNo)+")"));
							totalcell1.setCellStyle(style6);
							sheet.autoSizeColumn((short)colNo);
	  					}
						}*/
					}
	 			}    
			wb.write(bos);
			bos.close();
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	return "";
	}
}