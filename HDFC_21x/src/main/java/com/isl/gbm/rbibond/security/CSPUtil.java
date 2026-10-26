package com.isl.gbm.rbibond.security;

import java.security.SecureRandom;
import java.util.Base64;

public class CSPUtil {

    // Generates a secure Base64-encoded nonce (e.g., for CSP headers)
    public static String generateNonce() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] nonceBytes = new byte[16]; // 128-bit nonce
        secureRandom.nextBytes(nonceBytes);
        return Base64.getEncoder().encodeToString(nonceBytes);
    }
}
