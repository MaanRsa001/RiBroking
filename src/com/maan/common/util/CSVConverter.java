package com.maan.common.util;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class CSVConverter
{

    private static String OUTPUT_DATE_FORMAT;
    private static String CVS_SEPERATOR_CHAR;
    private static String NEW_LINE_CHARACTER = "\r\n";

    public CSVConverter()
    {
    }

    public static void csvConvertion(String excelFileName, String csvFileName, String dateFormat, String seperatorChar)
    {
        try
        {
            OUTPUT_DATE_FORMAT = dateFormat;
            CVS_SEPERATOR_CHAR = seperatorChar;
            if("xlsx".equals(excelFileName.substring(excelFileName.lastIndexOf('.') + 1, excelFileName.length())))
            {
                excelXToCSV(excelFileName, csvFileName);
            } else
            if("xls".equals(excelFileName.substring(excelFileName.lastIndexOf('.') + 1, excelFileName.length())))
            {
                excelToCSV(excelFileName, csvFileName);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void excelToCSV(String excelFileName, String csvFileName)
    {
        try
        {
            checkValidFile(excelFileName);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelFileName)));
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            int count = 0;
            int firstCount = 0;
            String csvData;
            for(; rowIter.hasNext(); writeCSV(csvFileName, (new StringBuilder(String.valueOf(csvData.substring(0, csvData.length() - 1)))).append(NEW_LINE_CHARACTER).toString()))
            {
                csvData = "";
                HSSFRow myRow = (HSSFRow)rowIter.next();
                if(++count == 1)
                {
                    firstCount = myRow.getLastCellNum();
                }
                for(int i = 0; i < firstCount; i++)
                {
                    csvData = String.valueOf(csvData)+getCellData(myRow.getCell(i))+"~";
                }
           }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void excelToCSV(String excelFileName, String csvFileName, String dateFormat, String seperatorChar)
    {
        try
        {
            checkValidFile(excelFileName);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelFileName)));
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            int count = 0;
            int firstCount = 0;
            String csvData;
            for(; rowIter.hasNext(); writeCSV(csvFileName, (new StringBuilder(String.valueOf(csvData.substring(0, csvData.length() - 1)))).append(NEW_LINE_CHARACTER).toString()))
            {
                csvData = "";
                HSSFRow myRow = (HSSFRow)rowIter.next();
                if(++count == 1)
                {
                    firstCount = myRow.getLastCellNum();
                }
                for(int i = 0; i < firstCount; i++)
                {
                    csvData = String.valueOf(csvData)+getCellData(myRow.getCell(i))+"~";
                }
           }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void excelXToCSV(String excelFileName, String csvFileName)
        throws InvocationTargetException
    {
        try
        {
            checkValidFile(excelFileName);
            File file = new File(excelFileName);
            OPCPackage opcPackage = OPCPackage.open(file.getAbsolutePath());
            XSSFWorkbook myWorkBook = new XSSFWorkbook(opcPackage);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            int count = 0;
            int firstCount = 0;
            int rows = mySheet.getPhysicalNumberOfRows();
            for(int eachRow = 0; eachRow < rows; eachRow++)
            {
                String csvData = "";
                XSSFRow myRow = mySheet.getRow(eachRow);
                if(++count == 1)
                {
                    firstCount = myRow.getLastCellNum();
                }
                for(int i = 0; i < firstCount; i++)
                {
                    csvData = String.valueOf(csvData)+getXLSXCellData(myRow.getCell(i));
                }

                writeCSV(csvFileName, (String.valueOf(csvData.substring(0, csvData.length() - 1)))+NEW_LINE_CHARACTER);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String getXLSXCellData(XSSFCell myCell)
        throws Exception
    {
        String cellData = "";
        if(myCell == null)
        {
            cellData = String.valueOf(cellData)+CVS_SEPERATOR_CHAR;
        } else
        {
            switch(myCell.getCellType())
            {
            case 1: // '\001'
            	cellData = String.valueOf(cellData)+myCell.getRichStringCellValue()+CVS_SEPERATOR_CHAR;
                break;
            case 4: // '\004'
                cellData = String.valueOf(cellData)+myCell.getBooleanCellValue()+CVS_SEPERATOR_CHAR;
                break;

            case 0: // '\0'
                cellData = String.valueOf(cellData)+getXLSXNumericValue(myCell)+CVS_SEPERATOR_CHAR;
                break;

            case 2: // '\002'
                cellData = String.valueOf(cellData)+getXLSXFormulaValue(myCell)+CVS_SEPERATOR_CHAR;
                // fall through

            case 3: // '\003'
            default:
                cellData = String.valueOf(cellData)+CVS_SEPERATOR_CHAR;
                break;
            }
        }
        return cellData;
    }

    private static String getCellData(HSSFCell myCell)
        throws Exception
    {
        String cellData = "";
        if(myCell == null)
        {
            cellData = String.valueOf(cellData)+CVS_SEPERATOR_CHAR;
        } else
        {
            switch(myCell.getCellType())
            {
            case 1: // '\001'
            	cellData = String.valueOf(cellData)+myCell.getRichStringCellValue()+CVS_SEPERATOR_CHAR;
                break;
            case 4: // '\004'
                cellData = String.valueOf(cellData)+myCell.getBooleanCellValue()+CVS_SEPERATOR_CHAR;
                break;

            case 0: // '\0'
                cellData = String.valueOf(cellData)+getNumericValue(myCell);
                break;

            case 2: // '\002'
                cellData = String.valueOf(cellData)+getFormulaValue(myCell);
                // fall through

            case 3: // '\003'
            default:
                cellData = String.valueOf(cellData)+CVS_SEPERATOR_CHAR;
                break;
            }
        }
        return cellData;
    }

    private static String getXLSXNumericValue(XSSFCell myCell)
        throws Exception
    {
        String cellData = "";
        if(HSSFDateUtil.isCellDateFormatted(myCell))
        {
            java.util.Date obj = myCell.getDateCellValue();
           // cellData = String.valueOf(cellData)+new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(obj)+CVS_SEPERATOR_CHAR;
            cellData = String.valueOf(cellData)+new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(obj);
        } else
        {
           // cellData = String.valueOf(cellData)+myCell.getNumericCellValue()+CVS_SEPERATOR_CHAR;
            cellData = String.valueOf(cellData)+myCell.getNumericCellValue();

        }
        return cellData;
    }

    private static String getNumericValue(HSSFCell myCell)
        throws Exception
    {
        String cellData = "";
        if(HSSFDateUtil.isCellDateFormatted(myCell))
        {
            java.util.Date obj = myCell.getDateCellValue();
            cellData = String.valueOf(cellData)+new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(obj)+CVS_SEPERATOR_CHAR;
        } else
        {
            DataFormatter formatter = new DataFormatter();
            cellData = String.valueOf(cellData)+formatter.formatCellValue(myCell)+CVS_SEPERATOR_CHAR;
        }
        return cellData;
    }

    private static String getXLSXFormulaValue(XSSFCell myCell)
        throws Exception
    {
        String cellData = "";
        if(myCell.getCachedFormulaResultType() == 1 || myCell.getCellType() == 4)
        {
            cellData = String.valueOf(cellData)+myCell.getRichStringCellValue()+CVS_SEPERATOR_CHAR;
        } else
        if(myCell.getCachedFormulaResultType() == 0)
        {
            cellData = String.valueOf(cellData)+getXLSXNumericValue(myCell)+CVS_SEPERATOR_CHAR;
        }
        return cellData;
    }

    private static String getFormulaValue(HSSFCell myCell)
        throws Exception
    {
        String cellData = "";
        if(myCell.getCachedFormulaResultType() == 1 || myCell.getCellType() == 4)
        {
            cellData = String.valueOf(cellData)+myCell.getRichStringCellValue()+CVS_SEPERATOR_CHAR;
        } else
        if(myCell.getCachedFormulaResultType() == 0)
        {
            cellData = String.valueOf(cellData)+getNumericValue(myCell)+CVS_SEPERATOR_CHAR;
        }
        return cellData;
    }

    private static void writeCSV(String csvFileName, String csvData)
        throws Exception
    {
        FileOutputStream writer = new FileOutputStream(csvFileName, true);
        writer.write(csvData.getBytes());
        writer.close();
    }

    private static void checkValidFile(String fileName)
    {
        boolean valid = true;
        try
        {
            File f = new File(fileName);
            if(!f.exists() || f.isDirectory())
            {
                valid = false;
            }
        }
        catch(Exception e)
        {
            valid = false;
        }
        if(!valid)
        {
            System.out.println("File doesn't exist: "+fileName);
            //System.exit(0);
        }
    }

}


