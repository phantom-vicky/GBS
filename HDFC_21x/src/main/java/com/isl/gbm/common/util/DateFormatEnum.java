package com.isl.gbm.common.util;

public enum DateFormatEnum {

    DATE_FORMAT_DD_MMM_YY("DD-MMM-YY"),
    DATE_FORMAT_DD_MM_YYYY("DDMMYYYY"),
    DATE_FORMAT_DD_$MM_$YYYY("dd/MM/yyyy"),
    DATE_FORMAT_DD_MMM_YYYY("dd-MMM-yyyy"),
    DATE_FORMAT_YYYYMMDD("YYYYMMdd"),
    DATE_FORMAT_YYMMDD("YYMMDD"),
    DATE_FORMAT_YYYYMM("YYYYMM"),
    DATE_FORMAT_DDMMYYYY("ddMMyyyy");


    DateFormatEnum(String format) {
        this.format = format;
    }

    private final String format;

    public String getFormat() {
        return format;
    }


}