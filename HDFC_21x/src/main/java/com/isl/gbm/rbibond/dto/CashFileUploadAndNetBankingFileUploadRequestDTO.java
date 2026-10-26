package com.isl.gbm.rbibond.dto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CashFileUploadAndNetBankingFileUploadRequestDTO {

    private String schemeName;
    private String countOfRecord;
    private String totalAmt;
    private String subCode;
    private String fileMode;
    private String fileType;
    private String fileName1;
    private String fileName2;
    private String fileName3;
    private String fileName4;
    private String fileName5;
    private String fileName6;
    private String fileName7;
    private String fileName8;
    private String fileName9;
    private byte[] file1;
    private byte[] file2;
    private byte[] file3;
    private byte[] file4;
    private byte[] file5;
    private byte[] file6;
    private byte[] file7;
    private byte[] file8;
    private byte[] file9;
    private String liquidationDate;
    private List<RRBNBBBRequestDTO>  list1;
    private List<TBNBBBRequestDTO>  list2;
    private List<RRBNBRequestDTO>  list3;
    private List<TBNBRequestDTO>  list4;
    private List<TaxBondTextFileDTO>  list5;
    private List<RRBNBNOVOPAYRequestDTO>  list6;
    private List<TBNBBBRequestDTO>  list7;
    private List<RRBNBNOVOPAYRequestDTO>  list8;
    private List<TBNBBBRequestDTO>  list9;
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String verifiedBy;
    private String verifiedDate;
    private String verifiedStatus;
    private String rejectionRemarks;
    private String valueDate;
    private List<CashFileUploadAndNetBankingFileUploadRequestDTO> torchOut;
    private String count1;
    private String count2;
    private String count3;
    private String count4;
    private String count7;
    private String count6;
    private String count8;
    private String count9;
    private String amount1;
    private String amount2;
    private String amount3;
    private String amount4;
    private String amount7;
    private String amount6;
    private String amount8;
    private String amount9;

    public String getCount1() {
        return count1;
    }

    public void setCount1(String count1) {
        this.count1 = count1;
    }

    public String getCount2() {
        return count2;
    }

    public void setCount2(String count2) {
        this.count2 = count2;
    }

    public String getCount3() {
        return count3;
    }

    public void setCount3(String count3) {
        this.count3 = count3;
    }

    public String getCount4() {
        return count4;
    }

    public void setCount4(String count4) {
        this.count4 = count4;
    }

    public String getCount7() {
        return count7;
    }

    public void setCount7(String count7) {
        this.count7 = count7;
    }

    public String getCount6() {
        return count6;
    }

    public void setCount6(String count6) {
        this.count6 = count6;
    }

    public String getAmount1() {
        return amount1;
    }

    public void setAmount1(String amount1) {
        this.amount1 = amount1;
    }

    public String getAmount2() {
        return amount2;
    }

    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }

    public String getAmount3() {
        return amount3;
    }

    public void setAmount3(String amount3) {
        this.amount3 = amount3;
    }

    public String getAmount4() {
        return amount4;
    }

    public void setAmount4(String amount4) {
        this.amount4 = amount4;
    }

    public String getAmount7() {
        return amount7;
    }

    public void setAmount7(String amount7) {
        this.amount7 = amount7;
    }

    public String getAmount6() {
        return amount6;
    }

    public void setAmount6(String amount6) {
        this.amount6 = amount6;
    }

    public List<CashFileUploadAndNetBankingFileUploadRequestDTO> getTorchOut() {
        return torchOut;
    }

    public void setTorchOut(List<CashFileUploadAndNetBankingFileUploadRequestDTO> torchOut) {
        this.torchOut = torchOut;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getCountOfRecord() {
        return countOfRecord;
    }

    public void setCountOfRecord(String countOfRecord) {
        this.countOfRecord = countOfRecord;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public List<TaxBondTextFileDTO> getList5() {
        return list5;
    }

    public void setList5(List<TaxBondTextFileDTO> list5) {
        this.list5 = list5;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getRejectionRemarks() {
        return rejectionRemarks;
    }

    public void setRejectionRemarks(String rejectionRemarks) {
        this.rejectionRemarks = rejectionRemarks;
    }

    public String getFileName1() {
        return fileName1;
    }

    public void setFileName1(String fileName1) {
        this.fileName1 = fileName1;
    }

    public String getFileName2() {
        return fileName2;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }

    public String getFileName3() {
        return fileName3;
    }

    public void setFileName3(String fileName3) {
        this.fileName3 = fileName3;
    }

    public String getFileName4() {
        return fileName4;
    }

    public void setFileName4(String fileName4) {
        this.fileName4 = fileName4;
    }

    public String getFileName5() {
        return fileName5;
    }

    public void setFileName5(String fileName5) {
        this.fileName5 = fileName5;
    }

    public byte[] getFile5() {
        return file5;
    }

    public void setFile5(byte[] file5) {
        this.file5 = file5;
    }

    public byte[] getFile1() {
        return file1;
    }

    public void setFile1(byte[] file1) {
        this.file1 = file1;
    }

    public byte[] getFile2() {
        return file2;
    }

    public void setFile2(byte[] file2) {
        this.file2 = file2;
    }

    public byte[] getFile3() {
        return file3;
    }

    public void setFile3(byte[] file3) {
        this.file3 = file3;
    }

    public byte[] getFile4() {
        return file4;
    }

    public void setFile4(byte[] file4) {
        this.file4 = file4;
    }

    public List<RRBNBBBRequestDTO> getList1() {
        return list1;
    }

    public void setList1(List<RRBNBBBRequestDTO> list1) {
        this.list1 = list1;
    }

    public List<TBNBBBRequestDTO> getList2() {
        return list2;
    }

    public void setList2(List<TBNBBBRequestDTO> list2) {
        this.list2 = list2;
    }

    public List<RRBNBRequestDTO> getList3() {
        return list3;
    }

    public void setList3(List<RRBNBRequestDTO> list3) {
        this.list3 = list3;
    }

    public List<TBNBRequestDTO> getList4() {
        return list4;
    }

    public void setList4(List<TBNBRequestDTO> list4) {
        this.list4 = list4;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getFileMode() {
        return fileMode;
    }

    public void setFileMode(String fileMode) {
        this.fileMode = fileMode;
    }

    public String getLiquidationDate() {
        return liquidationDate;
    }

    public void setLiquidationDate(String liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    public String getFileName6() {
        return fileName6;
    }

    public void setFileName6(String fileName6) {
        this.fileName6 = fileName6;
    }

    public String getFileName7() {
        return fileName7;
    }

    public void setFileName7(String fileName7) {
        this.fileName7 = fileName7;
    }

    public byte[] getFile6() {
        return file6;
    }

    public void setFile6(byte[] file6) {
        this.file6 = file6;
    }

    public byte[] getFile7() {
        return file7;
    }

    public void setFile7(byte[] file7) {
        this.file7 = file7;
    }

    public List<RRBNBNOVOPAYRequestDTO> getList6() {
        return list6;
    }

    public void setList6(List<RRBNBNOVOPAYRequestDTO> list6) {
        this.list6 = list6;
    }

    public List<TBNBBBRequestDTO> getList7() {
        return list7;
    }

    public void setList7(List<TBNBBBRequestDTO> list7) {
        this.list7 = list7;
    }

    public String getFileName8() {
        return fileName8;
    }

    public void setFileName8(String fileName8) {
        this.fileName8 = fileName8;
    }

    public String getFileName9() {
        return fileName9;
    }

    public void setFileName9(String fileName9) {
        this.fileName9 = fileName9;
    }

    public byte[] getFile8() {
        return file8;
    }

    public void setFile8(byte[] file8) {
        this.file8 = file8;
    }

    public byte[] getFile9() {
        return file9;
    }

    public void setFile9(byte[] file9) {
        this.file9 = file9;
    }

    public List<RRBNBNOVOPAYRequestDTO> getList8() {
        return list8;
    }

    public void setList8(List<RRBNBNOVOPAYRequestDTO> list8) {
        this.list8 = list8;
    }

    public List<TBNBBBRequestDTO> getList9() {
        return list9;
    }

    public void setList9(List<TBNBBBRequestDTO> list9) {
        this.list9 = list9;
    }

    public String getCount8() {
        return count8;
    }

    public void setCount8(String count8) {
        this.count8 = count8;
    }

    public String getCount9() {
        return count9;
    }

    public void setCount9(String count9) {
        this.count9 = count9;
    }

    public String getAmount8() {
        return amount8;
    }

    public void setAmount8(String amount8) {
        this.amount8 = amount8;
    }

    public String getAmount9() {
        return amount9;
    }

    public void setAmount9(String amount9) {
        this.amount9 = amount9;
    }
}
