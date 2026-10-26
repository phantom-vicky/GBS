package com.isl.gbm.rbibond.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

@Component
public class NumberToWordConverter {



    public String convertAmountToWordsString(String totalAmountString) {
        if (totalAmountString == null || totalAmountString.isEmpty()) {
            throw new IllegalArgumentException("Total amount string is null or empty.");
        }
        StringTokenizer token = new StringTokenizer(totalAmountString, ".");
        long rupees = Long.parseLong(token.nextToken());
        int paise = 0;

        if (token.hasMoreTokens()) {
            paise = Integer.parseInt(token.nextToken());
        }

        String amtRupee = StringUtils.capitalize(numberToWord(rupees));
        String paiseRupee = numberToWord(paise);

        return amtRupee + " Rupees and " + paiseRupee + " Paise only";
    }

    private static final String[] unitsArray = {
            "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tensArray = {
            "Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private String numberToWord(long number) {

        String words = "";

        if (number == 0) {
            return "Zero";
        }

        if (number < 0) {
            String numberStr = "" + number;
            numberStr = numberStr.substring(1);
            return "minus " + numberToWord(Integer.parseInt(numberStr));
        }
        if ((number / 10000000) > 0) {
            words += numberToWord(number / 10000000) + " Crore ";
            number %= 10000000;
        }
        if ((number / 100000) > 0) {
            words += numberToWord(number / 100000) + " Lakh ";
            number %= 100000;
        }
        if ((number / 1000) > 0) {
            words += numberToWord(number / 1000) + " Thousand ";
            number %= 1000;
        }
        if ((number / 100) > 0) {
            words += numberToWord(number / 100) + " Hundred ";
            number %= 100;
        }

        if (number > 0) {
            if (number < 20) {
                words += unitsArray[(int) number];
            } else {
                words += tensArray[(int) (number / 10)];
                if ((number % 10) > 0) {
                    words += " " + unitsArray[(int) (number % 10)];
                }
            }
        }
        return words.trim();
    }

    public String formatStringNumberWithCommas(String numberStr) {
        double number = Double.parseDouble(numberStr);
        Locale indianLocale = new Locale("en", "IN"); // Create an Indian locale
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(indianLocale);
        return numberFormat.format(number);
    }

    public String formatLongNumberWithCommas(long number) {
        Locale indianLocale = new Locale("en", "IN"); // Create an Indian locale
        NumberFormat numberFormat = NumberFormat.getInstance(indianLocale);
        return numberFormat.format(number);
    }


}

