package com.maan.common.login;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


public class FileEncryption {
	final static HttpServletRequest request1 = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	static ServletContext context = request1.getSession().getServletContext();
	private static String SAMPLE_FILE=context.getRealPath("/documents/SEC/enc.txt");
	private static String ENC_FILE=context.getRealPath("/documents/SEC/enc.des");
	private static String DEC_FILE=context.getRealPath("/documents/SEC/dec.txt");
	public static void main(String[] args) throws Exception {
		   String key = "ReInsuranceIArom";
	        File inputFile = new File(SAMPLE_FILE);
	        File encryptedFile = new File(ENC_FILE);
	        File decryptedFile = new File(DEC_FILE);
	        try {
	        	if(inputFile.exists()){
				CryptoUtils.encrypt(key, inputFile, encryptedFile);
	        	}if(encryptedFile.exists()){
	            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
	        	}
	        } catch (CryptoException ex) {
	            System.out.println(ex.getMessage());
	            ex.printStackTrace();
	        }
		
	}

}