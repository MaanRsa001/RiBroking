package com.maan.common.util;
/**
 * @author Raja.K
 *
 * Common Login Template
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import sun.misc.BASE64Encoder;

public final class PasswordService {

    final Logger logger = LogUtil.getLogger(PasswordService.class);
    private static PasswordService instance;

    public PasswordService() {
    }

    public synchronized String encrypt(String plaintext) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA"); //step 2
        } catch (NoSuchAlgorithmException e) {
            logger.error("SHA Algorithm Instance Exception => ", e);
        }

        try {
            md.update(plaintext.getBytes("UTF-8")); //step 3
        } catch (UnsupportedEncodingException e) {
            logger.error("Encription Exception => ", e);
        }

        byte raw[] = md.digest(); //step 4
        String hash = (new BASE64Encoder()).encode(raw); //step 5
        return hash; //step 6
    }

    public static synchronized PasswordService getInstance() { //step 1
        if (instance == null) {
            instance = new PasswordService();
        }
        return instance;
    }
}
