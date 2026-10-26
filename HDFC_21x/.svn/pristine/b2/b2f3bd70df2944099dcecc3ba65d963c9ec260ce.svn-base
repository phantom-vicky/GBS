package com.isl.gbm.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class ULTRAGBMCheckSumConverter {
    private final static Logger log = LoggerFactory.getLogger(ULTRAGBMCheckSumConverter.class);

    public static void main(String[] args) {

        try {
            String guardianPanNo = "PAN003";
            String guardianOprAcctNo = "ICIC00304";
            String ssaAcctNum = "101SSA000000000066";
            String inetRefNum = "SSAINET000401";
            String tranDate = "12-JAN-2020";
            String tranAmount = "32643";
            String cbsTranId = "CBS45565";
            String creditAcctNum = "0110193567000027";
            String fromDate = "20-Jun-2020";
            String toDate = "23-Jun-2020";
            String message = ssaAcctNum + "|" + fromDate + "|" + toDate;
            String ChecksumKey = "AXISCHECKSUM123";
            System.out.println("HmacSHA256=" + HmacSHA256(message, ChecksumKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String HmacSHA256(String message, String secret) {
        MessageDigest md = null;
        try {

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte raw[] = sha256_HMAC.doFinal(message.getBytes());

            StringBuffer ls_sb = new StringBuffer();
            for (int i = 0; i < raw.length; i++)
                ls_sb.append(char2hex(raw[i]));
            return ls_sb.toString(); //step 6
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            return null;
        }
    }

    public static String char2hex(byte x) {
        char arr[] = {
                '0', '1', '2', '3',
                '4', '5', '6', '7',
                '8', '9', 'A', 'B',
                'C', 'D', 'E', 'F'
        };

        char c[] = {arr[(x & 0xF0) >> 4], arr[x & 0x0F]};
        return (new String(c));
    }
}
