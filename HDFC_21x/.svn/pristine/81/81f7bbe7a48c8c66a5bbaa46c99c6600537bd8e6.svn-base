package com.isl.gbm.common.util;

import org.apache.commons.lang3.StringUtils;

public class FileValidationUtils {

    public static boolean checkFileSize(long size){
        if(size > 2097152)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public static String getFileTypeByUsingMagicNumbers(String magicNumber){

        if(magicNumber.toUpperCase().startsWith(FileTypeEnum.XLS_DOC.getMagicNumber())){
            return FileTypeEnum.XLS_DOC.getFileTypeName();
        }else if(magicNumber.toUpperCase().startsWith(FileTypeEnum.XLSX_DOC.getMagicNumber())){
            return FileTypeEnum.XLSX_DOC.getFileTypeName();
        }else if(magicNumber.toUpperCase().startsWith(FileTypeEnum.GIF.getMagicNumber())){
            return FileTypeEnum.GIF.getFileTypeName();
        }else if(magicNumber.toUpperCase().startsWith(FileTypeEnum.TIFF.getMagicNumber())){
            return FileTypeEnum.TIFF.getFileTypeName();
        }else if(magicNumber.toUpperCase().startsWith(FileTypeEnum.JPEG.getMagicNumber())){
            return FileTypeEnum.JPEG.getFileTypeName();
        }else if(magicNumber.toUpperCase().startsWith(FileTypeEnum.BMP.getMagicNumber())){
            return FileTypeEnum.BMP.getFileTypeName();
        }else if(magicNumber.toUpperCase().startsWith(FileTypeEnum.PDF.getMagicNumber())){
            return FileTypeEnum.PDF.getFileTypeName();
        }else if(magicNumber.toUpperCase().startsWith(FileTypeEnum.JPG.getMagicNumber())){
            return FileTypeEnum.JPG.getFileTypeName();
        }else {
            return "";
        }
    }

    public static boolean checkDoubleExtension(String fileName){
        int count = StringUtils.countMatches(fileName,'.');
        if(count > 1)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public static String convertBytesToHexaDecimal(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            String st = String.format("%02X", b);
            stringBuilder.append(st);
        }
        return stringBuilder.toString();
    }
}
