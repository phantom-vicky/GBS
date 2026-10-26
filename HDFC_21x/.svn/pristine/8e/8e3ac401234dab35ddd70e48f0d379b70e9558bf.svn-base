package com.isl.gbm.common.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LogMaskUtil {

    private final List<String> sensitiveFields;
    private final Map<String, Pattern> fieldPatterns = new HashMap<>();

    public LogMaskUtil(@Value("${pii.fields}") String fields) {
        this.sensitiveFields = Arrays.asList(fields.split(","));
        for (String field : sensitiveFields) {
            // matches: "fieldName":"value"
            String regex = "(\"" + field.trim() + "\"\\s*:\\s*\")(.*?)(\")";
            fieldPatterns.put(field, Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
    }

    public String mask(String logMessage) {
        if (logMessage == null) return null;

        String maskedMessage = logMessage;
        for (Map.Entry<String, Pattern> entry : fieldPatterns.entrySet()) {
            String field = entry.getKey();
            Matcher matcher = entry.getValue().matcher(maskedMessage);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                String fieldValue = matcher.group(2); // group(2) = actual value
                String maskedValue = fieldValue;
                // PAN masking only if field is "pan" and length is 10
                if (field.toLowerCase().contains("pan") && fieldValue != null && fieldValue.length() == 10) {
                    maskedValue = fieldValue.substring(0, 2) + "****" + fieldValue.substring(6);
                }else if (null!=fieldValue&&fieldValue.length() == 10 && fieldValue.matches("(?i)[A-Z]{5}[0-9]{4}[A-Z]")) {
                    maskedValue = fieldValue.substring(0, 2) + "****" + fieldValue.substring(6);
                }else if (fieldValue != null) {
                    // For other fields, mask completely (optional)
                    maskedValue = "";
                }
                // Keep JSON structure: "field":"maskedValue"
                String replacement = matcher.group(1) + maskedValue + matcher.group(3);
                matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
            }
            matcher.appendTail(sb);
            maskedMessage = sb.toString();
        }
        // 2️⃣ Mask PAN patterns directly, even if no field name matches
        Pattern panPattern = Pattern.compile("(:\\s*\")([A-Z]{5}[0-9]{4}[A-Z])(\")");
        Matcher panMatcher = panPattern.matcher(maskedMessage);
        StringBuffer sb2 = new StringBuffer();
        while (panMatcher.find()) {
            String value = panMatcher.group(2);
            String masked = value.substring(0, 2) + "****" + value.substring(6);
            panMatcher.appendReplacement(sb2, Matcher.quoteReplacement(panMatcher.group(1) + masked + panMatcher.group(3)));
        }
        panMatcher.appendTail(sb2);
        maskedMessage = sb2.toString();
        Pattern panArrayPattern = Pattern.compile(
                "(\"panDet\"\\s*:\\s*\\[\\s*)(\".*?\")\\s*\\]"
        );
        Matcher panArrayMatcher = panArrayPattern.matcher(maskedMessage);
        StringBuffer sb3 = new StringBuffer();

        while (panArrayMatcher.find()) {
            String panListStr = panArrayMatcher.group(2); // e.g., "\"ABHPE2158H\""
            // remove starting and ending quotes
            panListStr = panListStr.substring(1, panListStr.length() - 1);

            String[] pans = panListStr.split("\",\""); // split multiple PANs

            for (int i = 0; i < pans.length; i++) {
                String pan = pans[i].trim();
                if (pan.matches("(?i)[A-Z]{5}[0-9]{4}[A-Z]")) {
                    pans[i] = pan.substring(0, 2) + "****" + pan.substring(6);
                }
            }

            String maskedPanList = "\"" + String.join("\",\"", pans) + "\"";

            panArrayMatcher.appendReplacement(sb3,
                    Matcher.quoteReplacement(panArrayMatcher.group(1) + maskedPanList + "]"));
        }
        panArrayMatcher.appendTail(sb3);
        maskedMessage = sb3.toString();
        return maskedMessage;
    }
}

