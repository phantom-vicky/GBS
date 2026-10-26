package com.isl.gbm.common.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

@Component
public class AESUtils {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    private static String aesKey;

    private static String aesIv;

    public AESUtils(@Value("${gbm.ultra.aes.key}") String aes_key,@Value("${gbm.ultra.aes.iv}") String aes_iv){
        aesKey = aes_key;
        aesIv = aes_iv;
    }

    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt)
    {
        try
        {
            SecretKey key = new SecretKeySpec(
                    Base64.decodeBase64(aesKey), "AES");
            AlgorithmParameterSpec iv = new IvParameterSpec(
                    Base64.decodeBase64(aesIv));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return new String((Base64.encodeBase64String(cipher.doFinal(
                    strToEncrypt.getBytes("UTF-8")))));
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException();
        }
    }

    public static String decrypt(String strToDecrypt)
    {
        try
        {

            SecretKey key = new SecretKeySpec(
                    Base64.decodeBase64(aesKey), "AES");
            AlgorithmParameterSpec iv = new IvParameterSpec(
                    Base64.decodeBase64(aesIv));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)), StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException();
        }
    }


}
