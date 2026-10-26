package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FtsFetchCiDataResponse {

    private String Status;
    private String RejectReason;
    private LstData LstData;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRejectReason() {
        return RejectReason;
    }

    public void setRejectReason(String rejectReason) {
        RejectReason = rejectReason;
    }

    public FtsFetchCiDataResponse.LstData getLstData() {
        return LstData;
    }

    public void setLstData(FtsFetchCiDataResponse.LstData lstData) {
        LstData = lstData;
    }

    public static class LstData {
        private String Branch_Code;
        private String Branch_Name;
        private String WBO_Location_Code;
        private String WBO_Name;
        private String CPU_Name;
        private String Classification;
        private String InHouseAutomationUnitClassification;
        private String ClassificationInternal;
        private String Barcode;
        private String Cust_ID;
        private String Account_Number;
        private String Customer_Name;
        private String Schedule_No;
        private String Scheduling_Date;
        private String Doc_Type;
        private String Date_Of_Scan_At_Ops;
        private String LGLC_Code;
        private String Latest_Stage;
        private String Latest_Stage_Datetime;
        private String Doc_Expected;
        private String Hold_Reason;
        private String Branch_Remarks;
        private String WBO_Remarks;
        private String CPU_Remarks;
        private String Date_Of_1st_FTNR_Hold;
        private String Time_Of_1st_FTNR_Hold;
        private String Date_Of_Last_FTNR_Hold;
        private String Time_Of_Last_FTNR_Hold;
        private String Date_Of_1st_Imaging_Hold;
        private String Time_Of_1st_Imaging_Hold;
        private String Date_Of_Last_Imaging_Hold;
        private String Time_Of_Last_Imaging_Hold;
        private String File_Path;
        private String File_Name;
        private String Channel;
        private List<IndexingDetail> LstIndexingDetails;

        public String getBranch_Code() {
            return Branch_Code;
        }

        public void setBranch_Code(String branch_Code) {
            Branch_Code = branch_Code;
        }

        public String getBranch_Name() {
            return Branch_Name;
        }

        public void setBranch_Name(String branch_Name) {
            Branch_Name = branch_Name;
        }

        public String getWBO_Location_Code() {
            return WBO_Location_Code;
        }

        public void setWBO_Location_Code(String WBO_Location_Code) {
            this.WBO_Location_Code = WBO_Location_Code;
        }

        public String getWBO_Name() {
            return WBO_Name;
        }

        public void setWBO_Name(String WBO_Name) {
            this.WBO_Name = WBO_Name;
        }

        public String getCPU_Name() {
            return CPU_Name;
        }

        public void setCPU_Name(String CPU_Name) {
            this.CPU_Name = CPU_Name;
        }

        public String getClassification() {
            return Classification;
        }

        public void setClassification(String classification) {
            Classification = classification;
        }

        public String getInHouseAutomationUnitClassification() {
            return InHouseAutomationUnitClassification;
        }

        public void setInHouseAutomationUnitClassification(String inHouseAutomationUnitClassification) {
            InHouseAutomationUnitClassification = inHouseAutomationUnitClassification;
        }

        public String getClassificationInternal() {
            return ClassificationInternal;
        }

        public void setClassificationInternal(String classificationInternal) {
            ClassificationInternal = classificationInternal;
        }

        public String getBarcode() {
            return Barcode;
        }

        public void setBarcode(String barcode) {
            Barcode = barcode;
        }

        public String getCust_ID() {
            return Cust_ID;
        }

        public void setCust_ID(String cust_ID) {
            Cust_ID = cust_ID;
        }

        public String getAccount_Number() {
            return Account_Number;
        }

        public void setAccount_Number(String account_Number) {
            Account_Number = account_Number;
        }

        public String getCustomer_Name() {
            return Customer_Name;
        }

        public void setCustomer_Name(String customer_Name) {
            Customer_Name = customer_Name;
        }

        public String getSchedule_No() {
            return Schedule_No;
        }

        public void setSchedule_No(String schedule_No) {
            Schedule_No = schedule_No;
        }

        public String getScheduling_Date() {
            return Scheduling_Date;
        }

        public void setScheduling_Date(String scheduling_Date) {
            Scheduling_Date = scheduling_Date;
        }

        public String getDoc_Type() {
            return Doc_Type;
        }

        public void setDoc_Type(String doc_Type) {
            Doc_Type = doc_Type;
        }

        public String getDate_Of_Scan_At_Ops() {
            return Date_Of_Scan_At_Ops;
        }

        public void setDate_Of_Scan_At_Ops(String date_Of_Scan_At_Ops) {
            Date_Of_Scan_At_Ops = date_Of_Scan_At_Ops;
        }

        public String getLGLC_Code() {
            return LGLC_Code;
        }

        public void setLGLC_Code(String LGLC_Code) {
            this.LGLC_Code = LGLC_Code;
        }

        public String getLatest_Stage() {
            return Latest_Stage;
        }

        public void setLatest_Stage(String latest_Stage) {
            Latest_Stage = latest_Stage;
        }

        public String getLatest_Stage_Datetime() {
            return Latest_Stage_Datetime;
        }

        public void setLatest_Stage_Datetime(String latest_Stage_Datetime) {
            Latest_Stage_Datetime = latest_Stage_Datetime;
        }

        public String getDoc_Expected() {
            return Doc_Expected;
        }

        public void setDoc_Expected(String doc_Expected) {
            Doc_Expected = doc_Expected;
        }

        public String getHold_Reason() {
            return Hold_Reason;
        }

        public void setHold_Reason(String hold_Reason) {
            Hold_Reason = hold_Reason;
        }

        public String getBranch_Remarks() {
            return Branch_Remarks;
        }

        public void setBranch_Remarks(String branch_Remarks) {
            Branch_Remarks = branch_Remarks;
        }

        public String getWBO_Remarks() {
            return WBO_Remarks;
        }

        public void setWBO_Remarks(String WBO_Remarks) {
            this.WBO_Remarks = WBO_Remarks;
        }

        public String getCPU_Remarks() {
            return CPU_Remarks;
        }

        public void setCPU_Remarks(String CPU_Remarks) {
            this.CPU_Remarks = CPU_Remarks;
        }

        public String getDate_Of_1st_FTNR_Hold() {
            return Date_Of_1st_FTNR_Hold;
        }

        public void setDate_Of_1st_FTNR_Hold(String date_Of_1st_FTNR_Hold) {
            Date_Of_1st_FTNR_Hold = date_Of_1st_FTNR_Hold;
        }

        public String getTime_Of_1st_FTNR_Hold() {
            return Time_Of_1st_FTNR_Hold;
        }

        public void setTime_Of_1st_FTNR_Hold(String time_Of_1st_FTNR_Hold) {
            Time_Of_1st_FTNR_Hold = time_Of_1st_FTNR_Hold;
        }

        public String getDate_Of_Last_FTNR_Hold() {
            return Date_Of_Last_FTNR_Hold;
        }

        public void setDate_Of_Last_FTNR_Hold(String date_Of_Last_FTNR_Hold) {
            Date_Of_Last_FTNR_Hold = date_Of_Last_FTNR_Hold;
        }

        public String getTime_Of_Last_FTNR_Hold() {
            return Time_Of_Last_FTNR_Hold;
        }

        public void setTime_Of_Last_FTNR_Hold(String time_Of_Last_FTNR_Hold) {
            Time_Of_Last_FTNR_Hold = time_Of_Last_FTNR_Hold;
        }

        public String getDate_Of_1st_Imaging_Hold() {
            return Date_Of_1st_Imaging_Hold;
        }

        public void setDate_Of_1st_Imaging_Hold(String date_Of_1st_Imaging_Hold) {
            Date_Of_1st_Imaging_Hold = date_Of_1st_Imaging_Hold;
        }

        public String getTime_Of_1st_Imaging_Hold() {
            return Time_Of_1st_Imaging_Hold;
        }

        public void setTime_Of_1st_Imaging_Hold(String time_Of_1st_Imaging_Hold) {
            Time_Of_1st_Imaging_Hold = time_Of_1st_Imaging_Hold;
        }

        public String getDate_Of_Last_Imaging_Hold() {
            return Date_Of_Last_Imaging_Hold;
        }

        public void setDate_Of_Last_Imaging_Hold(String date_Of_Last_Imaging_Hold) {
            Date_Of_Last_Imaging_Hold = date_Of_Last_Imaging_Hold;
        }

        public String getTime_Of_Last_Imaging_Hold() {
            return Time_Of_Last_Imaging_Hold;
        }

        public void setTime_Of_Last_Imaging_Hold(String time_Of_Last_Imaging_Hold) {
            Time_Of_Last_Imaging_Hold = time_Of_Last_Imaging_Hold;
        }

        public String getFile_Path() {
            return File_Path;
        }

        public void setFile_Path(String file_Path) {
            File_Path = file_Path;
        }

        public String getFile_Name() {
            return File_Name;
        }

        public void setFile_Name(String file_Name) {
            File_Name = file_Name;
        }

        public String getChannel() {
            return Channel;
        }

        public void setChannel(String channel) {
            Channel = channel;
        }

        public List<IndexingDetail> getLstIndexingDetails() {
            return LstIndexingDetails;
        }

        public void setLstIndexingDetails(List<IndexingDetail> lstIndexingDetails) {
            LstIndexingDetails = lstIndexingDetails;
        }

        public static class IndexingDetail {
            private String Indexing_Value;
            private String Indexing_Name;
            private String Image_Path;
            private String Image_Name;

            public String getIndexing_Value() {
                return Indexing_Value;
            }

            public void setIndexing_Value(String indexing_Value) {
                Indexing_Value = indexing_Value;
            }

            public String getIndexing_Name() {
                return Indexing_Name;
            }

            public void setIndexing_Name(String indexing_Name) {
                Indexing_Name = indexing_Name;
            }

            public String getImage_Path() {
                return Image_Path;
            }

            public void setImage_Path(String image_Path) {
                Image_Path = image_Path;
            }

            public String getImage_Name() {
                return Image_Name;
            }

            public void setImage_Name(String image_Name) {
                Image_Name = image_Name;
            }
        }
    }
}
