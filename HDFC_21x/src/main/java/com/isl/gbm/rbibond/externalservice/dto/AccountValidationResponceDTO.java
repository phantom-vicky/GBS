package com.isl.gbm.rbibond.externalservice.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
public class AccountValidationResponceDTO implements Serializable {
        @JsonProperty("status")
        private Status status;
        @JsonProperty("maintenanceType")
        private String maintenanceType;
        @JsonProperty("configVersionId")
        private String configVersionId;
        @JsonProperty("responseString")
        private ResponseString responseString;
        // Getters and Setters
        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getMaintenanceType() {
            return maintenanceType;
        }

        public void setMaintenanceType(String maintenanceType) {
            this.maintenanceType = maintenanceType;
        }

        public String getConfigVersionId() {
            return configVersionId;
        }

        public void setConfigVersionId(String configVersionId) {
            this.configVersionId = configVersionId;
        }

        public ResponseString getResponseString() {
            return responseString;
        }

        public void setResponseString(ResponseString responseString) {
            this.responseString = responseString;
        }

    @Override
    public String toString() {
        return "AccountValidationResponceDTO{" +
                "status=" + status +
                ", maintenanceType='" + maintenanceType + '\'' +
                ", configVersionId='" + configVersionId + '\'' +
                ", responseString=" + responseString +
                '}';
    }

    public static class Status implements Serializable {
            @JsonProperty("isOverriden")
            private boolean isOverriden;
            @JsonProperty("replyCode")
            private int replyCode;
            @JsonProperty("replyText")
            private String replyText;
            @JsonProperty("memo")
            private String memo;
            @JsonProperty("externalReferenceNo")
            private String externalReferenceNo;
            @JsonProperty("internalReferenceNumber")
            private String internalReferenceNumber;
            @JsonProperty("postingDate")
            private PostingDate postingDate;
            @JsonProperty("errorCode")
            private String errorCode;
            @JsonProperty("extendedReply")
            private ExtendedReply extendedReply;
            @JsonProperty("validationErrors")
            private String validationErrors;
            @JsonProperty("userReferenceNumber")
            private String userReferenceNumber;

            // Getters and Setters

            public boolean isOverriden() {
                return isOverriden;
            }

            public void setOverriden(boolean overriden) {
                isOverriden = overriden;
            }

            public int getReplyCode() {
                return replyCode;
            }

            public void setReplyCode(int replyCode) {
                this.replyCode = replyCode;
            }

            public String getReplyText() {
                return replyText;
            }

            public void setReplyText(String replyText) {
                this.replyText = replyText;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getExternalReferenceNo() {
                return externalReferenceNo;
            }

            public void setExternalReferenceNo(String externalReferenceNo) {
                this.externalReferenceNo = externalReferenceNo;
            }

            public String getInternalReferenceNumber() {
                return internalReferenceNumber;
            }

            public void setInternalReferenceNumber(String internalReferenceNumber) {
                this.internalReferenceNumber = internalReferenceNumber;
            }

            public PostingDate getPostingDate() {
                return postingDate;
            }

            public void setPostingDate(PostingDate postingDate) {
                this.postingDate = postingDate;
            }

            public String getErrorCode() {
                return errorCode;
            }

            public void setErrorCode(String errorCode) {
                this.errorCode = errorCode;
            }

            public ExtendedReply getExtendedReply() {
                return extendedReply;
            }

            public void setExtendedReply(ExtendedReply extendedReply) {
                this.extendedReply = extendedReply;
            }

            public String getValidationErrors() {
                return validationErrors;
            }

            public void setValidationErrors(String validationErrors) {
                this.validationErrors = validationErrors;
            }

            public String getUserReferenceNumber() {
                return userReferenceNumber;
            }

            public void setUserReferenceNumber(String userReferenceNumber) {
                this.userReferenceNumber = userReferenceNumber;
            }

        @Override
        public String toString() {
            return "Status{" +
                    "isOverriden=" + isOverriden +
                    ", replyCode=" + replyCode +
                    ", replyText='" + replyText + '\'' +
                    ", memo='" + memo + '\'' +
                    ", externalReferenceNo='" + externalReferenceNo + '\'' +
                    ", internalReferenceNumber='" + internalReferenceNumber + '\'' +
                    ", postingDate=" + postingDate +
                    ", errorCode='" + errorCode + '\'' +
                    ", extendedReply=" + extendedReply +
                    ", validationErrors='" + validationErrors + '\'' +
                    ", userReferenceNumber='" + userReferenceNumber + '\'' +
                    '}';
        }

        public static class PostingDate {
                @JsonProperty("dateString")
                private String dateString;

                // Getters and Setters

                public String getDateString() {
                    return dateString;
                }

                public void setDateString(String dateString) {
                    this.dateString = dateString;
                }

            @Override
            public String toString() {
                return "PostingDate{" +
                        "dateString='" + dateString + '\'' +
                        '}';
            }
        }

            public static class ExtendedReply {
                @JsonProperty("messages")
                private String messages;

                // Getters and Setters
                public String getMessages() {
                    return messages;
                }
                public void setMessages(String messages) {
                    this.messages = messages;
                }

                @Override
                public String toString() {
                    return "ExtendedReply{" +
                            "messages='" + messages + '\'' +
                            '}';
                }
            }
        }

        public static class ResponseString {
            @JsonProperty("customerDetailsDTO")
            private List<CustomerDetailsDTO> customerDetailsDTO;

            // Getters and Setters
            public List<CustomerDetailsDTO> getCustomerDetailsDTO() {
                return customerDetailsDTO;
            }
            public void setCustomerDetailsDTO(List<CustomerDetailsDTO> customerDetailsDTO) {
                this.customerDetailsDTO = customerDetailsDTO;
            }

            @Override
            public String toString() {
                return "ResponseString{" +
                        "customerDetailsDTO=" + customerDetailsDTO +
                        '}';
            }
        }

        public static class CustomerDetailsDTO implements Serializable {
            @JsonProperty("customerFullName")
            private String customerFullName;
            @JsonProperty("txtPermadrAdd1")
            private String txtPermadrAdd1;
            @JsonProperty("rmPhoneNo")
            private String rmPhoneNo;
            @JsonProperty("codKeyType")
            private String codKeyType;
            @JsonProperty("codBlockReason")
            private String codBlockReason;
            @JsonProperty("namPermadrState")
            private String namPermadrState;
            @JsonProperty("refOfficePhoneArea")
            private String refOfficePhoneArea;
            @JsonProperty("refResPhoneArea")
            private String refResPhoneArea;
            @JsonProperty("rmEmailID")
            private String rmEmailID;
            @JsonProperty("codMisCompCode1")
            private String codMisCompCode1;
            @JsonProperty("memoSevrity")
            private String memoSevrity;
            @JsonProperty("codRM")
            private String codRM;
            @JsonProperty("prefCustFlag")
            private String prefCustFlag;
            @JsonProperty("codCustNatlId")
            private String codCustNatlId;
            @JsonProperty("rmBranchName")
            private String rmBranchName;
            @JsonProperty("txtContryUltRisk")
            private String txtContryUltRisk;
            @JsonProperty("accountMemoDetailsDTO")
            private List<AccountMemoDetailsDTO> accountMemoDetailsDTO; // Ensure this is a List
            @JsonProperty("namCustadrState")
            private String namCustadrState;
            @JsonProperty("namCustadrCntryDescription")
            private String namCustadrCntryDescription;
            @JsonProperty("flgCustMemo")
            private String flgCustMemo;
            @JsonProperty("keyPartnerCode")
            private String keyPartnerCode;
            @JsonProperty("customerAccountDetailsDTO")
            private List<CustomerAccountDetailsDTO> customerAccountDetailsDTO;
            @JsonProperty("mastCodGroupId")
            private String mastCodGroupId;
            @JsonProperty("codCustMarstat")
            private int codCustMarstat;
            @JsonProperty("refHoldTelex")
            private String refHoldTelex;
            @JsonProperty("codBusinessCat")
            private int codBusinessCat;
            @JsonProperty("customerFirstName")
            private String customerFirstName;
            @JsonProperty("customerMemoDetailsDTO")
            private List<CustomerMemoDetailsDTO> customerMemoDetailsDTO;
            @JsonProperty("refCustPhoneOff")
            private String refCustPhoneOff;
            @JsonProperty("flgDocComplete")
            private String flgDocComplete;
            @JsonProperty("datIncorporated")
            private String datIncorporated;
            @JsonProperty("codBusinessRegno")
            private String codBusinessRegno;
            @JsonProperty("genderDescription")
            private String genderDescription;
            @JsonProperty("namCustadrCntry")
            private String namCustadrCntry;
            @JsonProperty("kycStatus")
            private String kycStatus;
            @JsonProperty("codCustIntroducerId")
            private int codCustIntroducerId;
            @JsonProperty("refHoldPhone")
            private String refHoldPhone;
            @JsonProperty("txtEthnicOrigin")
            private String txtEthnicOrigin;
            @JsonProperty("refOfficePhoneExtn")
            private String refOfficePhoneExtn;
            @JsonProperty("codGroup")
            private String codGroup;
            @JsonProperty("txtCustDesgn")
            private String txtCustDesgn;
            @JsonProperty("codTds")
            private int codTds;
            @JsonProperty("customerAMLDetailsDTO")
            private List<CustomerAMLDetailsDTO> customerAMLDetailsDTO;
            @JsonProperty("txtCustSex")
            private String txtCustSex;
            @JsonProperty("customerId")
            private String customerId;
            @JsonProperty("datVerified")
            private String datVerified;
            @JsonProperty("memoNo")
            private String memoNo;
            @JsonProperty("customerLastName")
            private String customerLastName;
            @JsonProperty("txtCustadrAdd2")
            private String txtCustadrAdd2;
            @JsonProperty("maritalStatusDescription")
            private String maritalStatusDescription;
            @JsonProperty("txtCustadrAdd1")
            private String txtCustadrAdd1;
            @JsonProperty("custHomeBranchCode")
            private String custHomeBranchCode;
            @JsonProperty("txtCustadrAdd3")
            private String txtCustadrAdd3;
            @JsonProperty("refPhoneMobile")
            private String refPhoneMobile;
            @JsonProperty("rmCode")
            private String rmCode;
            @JsonProperty("namMotherMaiden")
            private String namMotherMaiden;
            @JsonProperty("namHoldadrCntry")
            private String namHoldadrCntry;
            @JsonProperty("refResPhoneCountry")
            private String refResPhoneCountry;
            @JsonProperty("namPermadrCntryDescription")
            private String namPermadrCntryDescription;
            @JsonProperty("refCustFax")
            private String refCustFax;
            @JsonProperty("txtCustPrefix")
            private String txtCustPrefix;
            @JsonProperty("refCustItNum")
            private String refCustItNum;
            @JsonProperty("txtCustNATNLTY")
            private String txtCustNATNLTY;
            @JsonProperty("namPermadrCntry")
            private String namPermadrCntry;
            @JsonProperty("namDirector2")
            private String namDirector2;
            @JsonProperty("namHoldadrCity")
            private String namHoldadrCity;
            @JsonProperty("txtHoldadrAdd3")
            private String txtHoldadrAdd3;
            @JsonProperty("refHoldFax")
            private String refHoldFax;
            @JsonProperty("datBirthCust")
            private String datBirthCust;
            @JsonProperty("txtHoldadrAdd1")
            private String txtHoldadrAdd1;
            @JsonProperty("memoText")
            private String memoText;
            @JsonProperty("refCustEmail")
            private String refCustEmail;
            @JsonProperty("txtHoldadrAdd2")
            private String txtHoldadrAdd2;
            @JsonProperty("ethnicCode")
            private String ethnicCode;
            @JsonProperty("flgKio")
            private String flgKio;
            @JsonProperty("customerFATCADtlsDTO")
            private List<CustomerFATCADtlsDTO> customerFATCADtlsDTO;
            @JsonProperty("namCntryIncorp")
            private String namCntryIncorp;
            @JsonProperty("namCustadrCity")
            private String namCustadrCity;
            @JsonProperty("codCustStatus")
            private int codCustStatus;
            @JsonProperty("panStatus")
            private String panStatus;
            @JsonProperty("customerTypeDescription")
            private String customerTypeDescription;
            @JsonProperty("customerBand")
            private String customerBand;
            @JsonProperty("addressChangeFlag")
            private String addressChangeFlag;
            @JsonProperty("namHoldadrState")
            private String namHoldadrState;
            @JsonProperty("flgStaff")
            private String flgStaff;
            @JsonProperty("txtCustResidence")
            private String txtCustResidence;
            @JsonProperty("rmBranchCode")
            private int rmBranchCode;
            @JsonProperty("oldCustomerID")
            private String oldCustomerID;
            @JsonProperty("txtPermadrZip")
            private String txtPermadrZip;
            @JsonProperty("refCustTelex")
            private String refCustTelex;
            @JsonProperty("customerShortName")
            private String customerShortName;
            @JsonProperty("codIinId")
            private int codIinId;
            @JsonProperty("flgVerifiedStat")
            private String flgVerifiedStat;
            @JsonProperty("flgCustTyp")
            private String flgCustTyp;
            @JsonProperty("txtKycDocIdentifier")
            private String txtKycDocIdentifier;
            @JsonProperty("codUtlComp")
            private String codUtlComp;
            @JsonProperty("txtBusinessTyp")
            private String txtBusinessTyp;
            @JsonProperty("txtHoldadrZip")
            private String txtHoldadrZip;
            @JsonProperty("datAdhrLnkdAcctUpd")
            private String datAdhrLnkdAcctUpd;
            @JsonProperty("rmName")
            private String rmName;
            @JsonProperty("customerMiddleName")
            private String customerMiddleName;
            @JsonProperty("codMisCustCode1")
            private String codMisCustCode1;
            @JsonProperty("txtContryInitRisk")
            private String txtContryInitRisk;
            @JsonProperty("codEmployeeId")
            private String codEmployeeId;
            @JsonProperty("codGroupId")
            private String codGroupId;
            @JsonProperty("codAadhaarNo")
            private String codAadhaarNo;
            @JsonProperty("flgMinor")
            private String flgMinor;
            @JsonProperty("codStmtDispatchMode")
            private String codStmtDispatchMode;
            @JsonProperty("cifType")
            private String cifType;
            @JsonProperty("refOfficePhoneCountry")
            private String refOfficePhoneCountry;
            @JsonProperty("datAadhaarUpdatedOn")
            private String datAadhaarUpdatedOn;
            @JsonProperty("flgBlocked")
            private String flgBlocked;
            @JsonProperty("refCustPhone")
            private String refCustPhone;
            @JsonProperty("txtDesignationValue")
            private String txtDesignationValue;
            @JsonProperty("txtCustadrZip")
            private String txtCustadrZip;
            @JsonProperty("codDesignation")
            private int codDesignation;
            @JsonProperty("refResPhoneExtn")
            private String refResPhoneExtn;
            @JsonProperty("ethnicCodeDesc")
            private String ethnicCodeDesc;
            @JsonProperty("refHoldEmail")
            private String refHoldEmail;
            @JsonProperty("datLastMnt")
            private String datLastMnt;
            @JsonProperty("namPermadrCity")
            private String namPermadrCity;
            @JsonProperty("codLiabNo")
            private String codLiabNo;
            @JsonProperty("codAadhaarLinkAcct")
            private String codAadhaarLinkAcct;
            @JsonProperty("txtPermadrAdd2")
            private String txtPermadrAdd2;
            @JsonProperty("txtPermadrAdd3")
            private String txtPermadrAdd3;
            @JsonProperty("refHoldPhoneOff")
            private String refHoldPhoneOff;
            @JsonProperty("codTypKyc")
            private String codTypKyc;
            @JsonProperty("flgIcTyp")
            private String flgIcTyp;

            // Getters and Setters


            public String getCustomerFullName() {
                return customerFullName;
            }

            public void setCustomerFullName(String customerFullName) {
                this.customerFullName = customerFullName;
            }

            public String getTxtPermadrAdd1() {
                return txtPermadrAdd1;
            }

            public void setTxtPermadrAdd1(String txtPermadrAdd1) {
                this.txtPermadrAdd1 = txtPermadrAdd1;
            }

            public String getRmPhoneNo() {
                return rmPhoneNo;
            }

            public void setRmPhoneNo(String rmPhoneNo) {
                this.rmPhoneNo = rmPhoneNo;
            }

            public String getCodKeyType() {
                return codKeyType;
            }

            public void setCodKeyType(String codKeyType) {
                this.codKeyType = codKeyType;
            }

            public String getCodBlockReason() {
                return codBlockReason;
            }

            public void setCodBlockReason(String codBlockReason) {
                this.codBlockReason = codBlockReason;
            }

            public String getNamPermadrState() {
                return namPermadrState;
            }

            public void setNamPermadrState(String namPermadrState) {
                this.namPermadrState = namPermadrState;
            }

            public String getRefOfficePhoneArea() {
                return refOfficePhoneArea;
            }

            public void setRefOfficePhoneArea(String refOfficePhoneArea) {
                this.refOfficePhoneArea = refOfficePhoneArea;
            }

            public String getRefResPhoneArea() {
                return refResPhoneArea;
            }

            public void setRefResPhoneArea(String refResPhoneArea) {
                this.refResPhoneArea = refResPhoneArea;
            }

            public String getRmEmailID() {
                return rmEmailID;
            }

            public void setRmEmailID(String rmEmailID) {
                this.rmEmailID = rmEmailID;
            }

            public String getCodMisCompCode1() {
                return codMisCompCode1;
            }

            public void setCodMisCompCode1(String codMisCompCode1) {
                this.codMisCompCode1 = codMisCompCode1;
            }

            public String getMemoSevrity() {
                return memoSevrity;
            }

            public void setMemoSevrity(String memoSevrity) {
                this.memoSevrity = memoSevrity;
            }

            public String getCodRM() {
                return codRM;
            }

            public void setCodRM(String codRM) {
                this.codRM = codRM;
            }

            public String getPrefCustFlag() {
                return prefCustFlag;
            }

            public void setPrefCustFlag(String prefCustFlag) {
                this.prefCustFlag = prefCustFlag;
            }

            public String getCodCustNatlId() {
                return codCustNatlId;
            }

            public void setCodCustNatlId(String codCustNatlId) {
                this.codCustNatlId = codCustNatlId;
            }

            public String getRmBranchName() {
                return rmBranchName;
            }

            public void setRmBranchName(String rmBranchName) {
                this.rmBranchName = rmBranchName;
            }

            public String getTxtContryUltRisk() {
                return txtContryUltRisk;
            }

            public void setTxtContryUltRisk(String txtContryUltRisk) {
                this.txtContryUltRisk = txtContryUltRisk;
            }

            public List<AccountMemoDetailsDTO> getAccountMemoDetailsDTO() {
                return accountMemoDetailsDTO;
            }

            public void setAccountMemoDetailsDTO(List<AccountMemoDetailsDTO> accountMemoDetailsDTO) {
                this.accountMemoDetailsDTO = accountMemoDetailsDTO;
            }

            public String getNamCustadrState() {
                return namCustadrState;
            }

            public void setNamCustadrState(String namCustadrState) {
                this.namCustadrState = namCustadrState;
            }

            public String getNamCustadrCntryDescription() {
                return namCustadrCntryDescription;
            }

            public void setNamCustadrCntryDescription(String namCustadrCntryDescription) {
                this.namCustadrCntryDescription = namCustadrCntryDescription;
            }

            public String getFlgCustMemo() {
                return flgCustMemo;
            }

            public void setFlgCustMemo(String flgCustMemo) {
                this.flgCustMemo = flgCustMemo;
            }

            public String getKeyPartnerCode() {
                return keyPartnerCode;
            }

            public void setKeyPartnerCode(String keyPartnerCode) {
                this.keyPartnerCode = keyPartnerCode;
            }

            public List<CustomerAccountDetailsDTO> getCustomerAccountDetailsDTO() {
                return customerAccountDetailsDTO;
            }

            public void setCustomerAccountDetailsDTO(List<CustomerAccountDetailsDTO> customerAccountDetailsDTO) {
                this.customerAccountDetailsDTO = customerAccountDetailsDTO;
            }

            public String getMastCodGroupId() {
                return mastCodGroupId;
            }

            public void setMastCodGroupId(String mastCodGroupId) {
                this.mastCodGroupId = mastCodGroupId;
            }

            public int getCodCustMarstat() {
                return codCustMarstat;
            }

            public void setCodCustMarstat(int codCustMarstat) {
                this.codCustMarstat = codCustMarstat;
            }

            public String getRefHoldTelex() {
                return refHoldTelex;
            }

            public void setRefHoldTelex(String refHoldTelex) {
                this.refHoldTelex = refHoldTelex;
            }

            public int getCodBusinessCat() {
                return codBusinessCat;
            }

            public void setCodBusinessCat(int codBusinessCat) {
                this.codBusinessCat = codBusinessCat;
            }

            public String getCustomerFirstName() {
                return customerFirstName;
            }

            public void setCustomerFirstName(String customerFirstName) {
                this.customerFirstName = customerFirstName;
            }

            public List<CustomerMemoDetailsDTO> getCustomerMemoDetailsDTO() {
                return customerMemoDetailsDTO;
            }

            public void setCustomerMemoDetailsDTO(List<CustomerMemoDetailsDTO> customerMemoDetailsDTO) {
                this.customerMemoDetailsDTO = customerMemoDetailsDTO;
            }

            public String getRefCustPhoneOff() {
                return refCustPhoneOff;
            }

            public void setRefCustPhoneOff(String refCustPhoneOff) {
                this.refCustPhoneOff = refCustPhoneOff;
            }

            public String getFlgDocComplete() {
                return flgDocComplete;
            }

            public void setFlgDocComplete(String flgDocComplete) {
                this.flgDocComplete = flgDocComplete;
            }

            public String getDatIncorporated() {
                return datIncorporated;
            }

            public void setDatIncorporated(String datIncorporated) {
                this.datIncorporated = datIncorporated;
            }

            public String getCodBusinessRegno() {
                return codBusinessRegno;
            }

            public void setCodBusinessRegno(String codBusinessRegno) {
                this.codBusinessRegno = codBusinessRegno;
            }

            public String getGenderDescription() {
                return genderDescription;
            }

            public void setGenderDescription(String genderDescription) {
                this.genderDescription = genderDescription;
            }

            public String getNamCustadrCntry() {
                return namCustadrCntry;
            }

            public void setNamCustadrCntry(String namCustadrCntry) {
                this.namCustadrCntry = namCustadrCntry;
            }

            public String getKycStatus() {
                return kycStatus;
            }

            public void setKycStatus(String kycStatus) {
                this.kycStatus = kycStatus;
            }

            public int getCodCustIntroducerId() {
                return codCustIntroducerId;
            }

            public void setCodCustIntroducerId(int codCustIntroducerId) {
                this.codCustIntroducerId = codCustIntroducerId;
            }

            public String getRefHoldPhone() {
                return refHoldPhone;
            }

            public void setRefHoldPhone(String refHoldPhone) {
                this.refHoldPhone = refHoldPhone;
            }

            public String getTxtEthnicOrigin() {
                return txtEthnicOrigin;
            }

            public void setTxtEthnicOrigin(String txtEthnicOrigin) {
                this.txtEthnicOrigin = txtEthnicOrigin;
            }

            public String getRefOfficePhoneExtn() {
                return refOfficePhoneExtn;
            }

            public void setRefOfficePhoneExtn(String refOfficePhoneExtn) {
                this.refOfficePhoneExtn = refOfficePhoneExtn;
            }

            public String getCodGroup() {
                return codGroup;
            }

            public void setCodGroup(String codGroup) {
                this.codGroup = codGroup;
            }

            public String getTxtCustDesgn() {
                return txtCustDesgn;
            }

            public void setTxtCustDesgn(String txtCustDesgn) {
                this.txtCustDesgn = txtCustDesgn;
            }

            public int getCodTds() {
                return codTds;
            }

            public void setCodTds(int codTds) {
                this.codTds = codTds;
            }

            public List<CustomerAMLDetailsDTO> getCustomerAMLDetailsDTO() {
                return customerAMLDetailsDTO;
            }

            public void setCustomerAMLDetailsDTO(List<CustomerAMLDetailsDTO> customerAMLDetailsDTO) {
                this.customerAMLDetailsDTO = customerAMLDetailsDTO;
            }

            public String getTxtCustSex() {
                return txtCustSex;
            }

            public void setTxtCustSex(String txtCustSex) {
                this.txtCustSex = txtCustSex;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getDatVerified() {
                return datVerified;
            }

            public void setDatVerified(String datVerified) {
                this.datVerified = datVerified;
            }

            public String getMemoNo() {
                return memoNo;
            }

            public void setMemoNo(String memoNo) {
                this.memoNo = memoNo;
            }

            public String getCustomerLastName() {
                return customerLastName;
            }

            public void setCustomerLastName(String customerLastName) {
                this.customerLastName = customerLastName;
            }

            public String getTxtCustadrAdd2() {
                return txtCustadrAdd2;
            }

            public void setTxtCustadrAdd2(String txtCustadrAdd2) {
                this.txtCustadrAdd2 = txtCustadrAdd2;
            }

            public String getMaritalStatusDescription() {
                return maritalStatusDescription;
            }

            public void setMaritalStatusDescription(String maritalStatusDescription) {
                this.maritalStatusDescription = maritalStatusDescription;
            }

            public String getTxtCustadrAdd1() {
                return txtCustadrAdd1;
            }

            public void setTxtCustadrAdd1(String txtCustadrAdd1) {
                this.txtCustadrAdd1 = txtCustadrAdd1;
            }

            public String getCustHomeBranchCode() {
                return custHomeBranchCode;
            }

            public void setCustHomeBranchCode(String custHomeBranchCode) {
                this.custHomeBranchCode = custHomeBranchCode;
            }

            public String getTxtCustadrAdd3() {
                return txtCustadrAdd3;
            }

            public void setTxtCustadrAdd3(String txtCustadrAdd3) {
                this.txtCustadrAdd3 = txtCustadrAdd3;
            }

            public String getRefPhoneMobile() {
                return refPhoneMobile;
            }

            public void setRefPhoneMobile(String refPhoneMobile) {
                this.refPhoneMobile = refPhoneMobile;
            }

            public String getRmCode() {
                return rmCode;
            }

            public void setRmCode(String rmCode) {
                this.rmCode = rmCode;
            }

            public String getNamMotherMaiden() {
                return namMotherMaiden;
            }

            public void setNamMotherMaiden(String namMotherMaiden) {
                this.namMotherMaiden = namMotherMaiden;
            }

            public String getNamHoldadrCntry() {
                return namHoldadrCntry;
            }

            public void setNamHoldadrCntry(String namHoldadrCntry) {
                this.namHoldadrCntry = namHoldadrCntry;
            }

            public String getRefResPhoneCountry() {
                return refResPhoneCountry;
            }

            public void setRefResPhoneCountry(String refResPhoneCountry) {
                this.refResPhoneCountry = refResPhoneCountry;
            }

            public String getNamPermadrCntryDescription() {
                return namPermadrCntryDescription;
            }

            public void setNamPermadrCntryDescription(String namPermadrCntryDescription) {
                this.namPermadrCntryDescription = namPermadrCntryDescription;
            }

            public String getRefCustFax() {
                return refCustFax;
            }

            public void setRefCustFax(String refCustFax) {
                this.refCustFax = refCustFax;
            }

            public String getTxtCustPrefix() {
                return txtCustPrefix;
            }

            public void setTxtCustPrefix(String txtCustPrefix) {
                this.txtCustPrefix = txtCustPrefix;
            }

            public String getRefCustItNum() {
                return refCustItNum;
            }

            public void setRefCustItNum(String refCustItNum) {
                this.refCustItNum = refCustItNum;
            }

            public String getTxtCustNATNLTY() {
                return txtCustNATNLTY;
            }

            public void setTxtCustNATNLTY(String txtCustNATNLTY) {
                this.txtCustNATNLTY = txtCustNATNLTY;
            }

            public String getNamPermadrCntry() {
                return namPermadrCntry;
            }

            public void setNamPermadrCntry(String namPermadrCntry) {
                this.namPermadrCntry = namPermadrCntry;
            }

            public String getNamDirector2() {
                return namDirector2;
            }

            public void setNamDirector2(String namDirector2) {
                this.namDirector2 = namDirector2;
            }

            public String getNamHoldadrCity() {
                return namHoldadrCity;
            }

            public void setNamHoldadrCity(String namHoldadrCity) {
                this.namHoldadrCity = namHoldadrCity;
            }

            public String getTxtHoldadrAdd3() {
                return txtHoldadrAdd3;
            }

            public void setTxtHoldadrAdd3(String txtHoldadrAdd3) {
                this.txtHoldadrAdd3 = txtHoldadrAdd3;
            }

            public String getRefHoldFax() {
                return refHoldFax;
            }

            public void setRefHoldFax(String refHoldFax) {
                this.refHoldFax = refHoldFax;
            }

            public String getDatBirthCust() {
                return datBirthCust;
            }

            public void setDatBirthCust(String datBirthCust) {
                this.datBirthCust = datBirthCust;
            }

            public String getTxtHoldadrAdd1() {
                return txtHoldadrAdd1;
            }

            public void setTxtHoldadrAdd1(String txtHoldadrAdd1) {
                this.txtHoldadrAdd1 = txtHoldadrAdd1;
            }

            public String getMemoText() {
                return memoText;
            }

            public void setMemoText(String memoText) {
                this.memoText = memoText;
            }

            public String getRefCustEmail() {
                return refCustEmail;
            }

            public void setRefCustEmail(String refCustEmail) {
                this.refCustEmail = refCustEmail;
            }

            public String getTxtHoldadrAdd2() {
                return txtHoldadrAdd2;
            }

            public void setTxtHoldadrAdd2(String txtHoldadrAdd2) {
                this.txtHoldadrAdd2 = txtHoldadrAdd2;
            }

            public String getEthnicCode() {
                return ethnicCode;
            }

            public void setEthnicCode(String ethnicCode) {
                this.ethnicCode = ethnicCode;
            }

            public String getFlgKio() {
                return flgKio;
            }

            public void setFlgKio(String flgKio) {
                this.flgKio = flgKio;
            }

            public List<CustomerFATCADtlsDTO> getCustomerFATCADtlsDTO() {
                return customerFATCADtlsDTO;
            }

            public void setCustomerFATCADtlsDTO(List<CustomerFATCADtlsDTO> customerFATCADtlsDTO) {
                this.customerFATCADtlsDTO = customerFATCADtlsDTO;
            }

            public String getNamCntryIncorp() {
                return namCntryIncorp;
            }

            public void setNamCntryIncorp(String namCntryIncorp) {
                this.namCntryIncorp = namCntryIncorp;
            }

            public String getNamCustadrCity() {
                return namCustadrCity;
            }

            public void setNamCustadrCity(String namCustadrCity) {
                this.namCustadrCity = namCustadrCity;
            }

            public int getCodCustStatus() {
                return codCustStatus;
            }

            public void setCodCustStatus(int codCustStatus) {
                this.codCustStatus = codCustStatus;
            }

            public String getPanStatus() {
                return panStatus;
            }

            public void setPanStatus(String panStatus) {
                this.panStatus = panStatus;
            }

            public String getCustomerTypeDescription() {
                return customerTypeDescription;
            }

            public void setCustomerTypeDescription(String customerTypeDescription) {
                this.customerTypeDescription = customerTypeDescription;
            }

            public String getCustomerBand() {
                return customerBand;
            }

            public void setCustomerBand(String customerBand) {
                this.customerBand = customerBand;
            }

            public String getAddressChangeFlag() {
                return addressChangeFlag;
            }

            public void setAddressChangeFlag(String addressChangeFlag) {
                this.addressChangeFlag = addressChangeFlag;
            }

            public String getNamHoldadrState() {
                return namHoldadrState;
            }

            public void setNamHoldadrState(String namHoldadrState) {
                this.namHoldadrState = namHoldadrState;
            }

            public String getFlgStaff() {
                return flgStaff;
            }

            public void setFlgStaff(String flgStaff) {
                this.flgStaff = flgStaff;
            }

            public String getTxtCustResidence() {
                return txtCustResidence;
            }

            public void setTxtCustResidence(String txtCustResidence) {
                this.txtCustResidence = txtCustResidence;
            }

            public int getRmBranchCode() {
                return rmBranchCode;
            }

            public void setRmBranchCode(int rmBranchCode) {
                this.rmBranchCode = rmBranchCode;
            }

            public String getOldCustomerID() {
                return oldCustomerID;
            }

            public void setOldCustomerID(String oldCustomerID) {
                this.oldCustomerID = oldCustomerID;
            }

            public String getTxtPermadrZip() {
                return txtPermadrZip;
            }

            public void setTxtPermadrZip(String txtPermadrZip) {
                this.txtPermadrZip = txtPermadrZip;
            }

            public String getRefCustTelex() {
                return refCustTelex;
            }

            public void setRefCustTelex(String refCustTelex) {
                this.refCustTelex = refCustTelex;
            }

            public String getCustomerShortName() {
                return customerShortName;
            }

            public void setCustomerShortName(String customerShortName) {
                this.customerShortName = customerShortName;
            }

            public int getCodIinId() {
                return codIinId;
            }

            public void setCodIinId(int codIinId) {
                this.codIinId = codIinId;
            }

            public String getFlgVerifiedStat() {
                return flgVerifiedStat;
            }

            public void setFlgVerifiedStat(String flgVerifiedStat) {
                this.flgVerifiedStat = flgVerifiedStat;
            }

            public String getFlgCustTyp() {
                return flgCustTyp;
            }

            public void setFlgCustTyp(String flgCustTyp) {
                this.flgCustTyp = flgCustTyp;
            }

            public String getTxtKycDocIdentifier() {
                return txtKycDocIdentifier;
            }

            public void setTxtKycDocIdentifier(String txtKycDocIdentifier) {
                this.txtKycDocIdentifier = txtKycDocIdentifier;
            }

            public String getCodUtlComp() {
                return codUtlComp;
            }

            public void setCodUtlComp(String codUtlComp) {
                this.codUtlComp = codUtlComp;
            }

            public String getTxtBusinessTyp() {
                return txtBusinessTyp;
            }

            public void setTxtBusinessTyp(String txtBusinessTyp) {
                this.txtBusinessTyp = txtBusinessTyp;
            }

            public String getTxtHoldadrZip() {
                return txtHoldadrZip;
            }

            public void setTxtHoldadrZip(String txtHoldadrZip) {
                this.txtHoldadrZip = txtHoldadrZip;
            }

            public String getDatAdhrLnkdAcctUpd() {
                return datAdhrLnkdAcctUpd;
            }

            public void setDatAdhrLnkdAcctUpd(String datAdhrLnkdAcctUpd) {
                this.datAdhrLnkdAcctUpd = datAdhrLnkdAcctUpd;
            }

            public String getRmName() {
                return rmName;
            }

            public void setRmName(String rmName) {
                this.rmName = rmName;
            }

            public String getCustomerMiddleName() {
                return customerMiddleName;
            }

            public void setCustomerMiddleName(String customerMiddleName) {
                this.customerMiddleName = customerMiddleName;
            }

            public String getCodMisCustCode1() {
                return codMisCustCode1;
            }

            public void setCodMisCustCode1(String codMisCustCode1) {
                this.codMisCustCode1 = codMisCustCode1;
            }

            public String getTxtContryInitRisk() {
                return txtContryInitRisk;
            }

            public void setTxtContryInitRisk(String txtContryInitRisk) {
                this.txtContryInitRisk = txtContryInitRisk;
            }

            public String getCodEmployeeId() {
                return codEmployeeId;
            }

            public void setCodEmployeeId(String codEmployeeId) {
                this.codEmployeeId = codEmployeeId;
            }

            public String getCodGroupId() {
                return codGroupId;
            }

            public void setCodGroupId(String codGroupId) {
                this.codGroupId = codGroupId;
            }

            public String getCodAadhaarNo() {
                return codAadhaarNo;
            }

            public void setCodAadhaarNo(String codAadhaarNo) {
                this.codAadhaarNo = codAadhaarNo;
            }

            public String getFlgMinor() {
                return flgMinor;
            }

            public void setFlgMinor(String flgMinor) {
                this.flgMinor = flgMinor;
            }

            public String getCodStmtDispatchMode() {
                return codStmtDispatchMode;
            }

            public void setCodStmtDispatchMode(String codStmtDispatchMode) {
                this.codStmtDispatchMode = codStmtDispatchMode;
            }

            public String getCifType() {
                return cifType;
            }

            public void setCifType(String cifType) {
                this.cifType = cifType;
            }

            public String getRefOfficePhoneCountry() {
                return refOfficePhoneCountry;
            }

            public void setRefOfficePhoneCountry(String refOfficePhoneCountry) {
                this.refOfficePhoneCountry = refOfficePhoneCountry;
            }

            public String getDatAadhaarUpdatedOn() {
                return datAadhaarUpdatedOn;
            }

            public void setDatAadhaarUpdatedOn(String datAadhaarUpdatedOn) {
                this.datAadhaarUpdatedOn = datAadhaarUpdatedOn;
            }

            public String getFlgBlocked() {
                return flgBlocked;
            }

            public void setFlgBlocked(String flgBlocked) {
                this.flgBlocked = flgBlocked;
            }

            public String getRefCustPhone() {
                return refCustPhone;
            }

            public void setRefCustPhone(String refCustPhone) {
                this.refCustPhone = refCustPhone;
            }

            public String getTxtDesignationValue() {
                return txtDesignationValue;
            }

            public void setTxtDesignationValue(String txtDesignationValue) {
                this.txtDesignationValue = txtDesignationValue;
            }

            public String getTxtCustadrZip() {
                return txtCustadrZip;
            }

            public void setTxtCustadrZip(String txtCustadrZip) {
                this.txtCustadrZip = txtCustadrZip;
            }

            public int getCodDesignation() {
                return codDesignation;
            }

            public void setCodDesignation(int codDesignation) {
                this.codDesignation = codDesignation;
            }

            public String getRefResPhoneExtn() {
                return refResPhoneExtn;
            }

            public void setRefResPhoneExtn(String refResPhoneExtn) {
                this.refResPhoneExtn = refResPhoneExtn;
            }

            public String getEthnicCodeDesc() {
                return ethnicCodeDesc;
            }

            public void setEthnicCodeDesc(String ethnicCodeDesc) {
                this.ethnicCodeDesc = ethnicCodeDesc;
            }

            public String getRefHoldEmail() {
                return refHoldEmail;
            }

            public void setRefHoldEmail(String refHoldEmail) {
                this.refHoldEmail = refHoldEmail;
            }

            public String getDatLastMnt() {
                return datLastMnt;
            }

            public void setDatLastMnt(String datLastMnt) {
                this.datLastMnt = datLastMnt;
            }

            public String getNamPermadrCity() {
                return namPermadrCity;
            }

            public void setNamPermadrCity(String namPermadrCity) {
                this.namPermadrCity = namPermadrCity;
            }

            public String getCodLiabNo() {
                return codLiabNo;
            }

            public void setCodLiabNo(String codLiabNo) {
                this.codLiabNo = codLiabNo;
            }

            public String getCodAadhaarLinkAcct() {
                return codAadhaarLinkAcct;
            }

            public void setCodAadhaarLinkAcct(String codAadhaarLinkAcct) {
                this.codAadhaarLinkAcct = codAadhaarLinkAcct;
            }

            public String getTxtPermadrAdd2() {
                return txtPermadrAdd2;
            }

            public void setTxtPermadrAdd2(String txtPermadrAdd2) {
                this.txtPermadrAdd2 = txtPermadrAdd2;
            }

            public String getTxtPermadrAdd3() {
                return txtPermadrAdd3;
            }

            public void setTxtPermadrAdd3(String txtPermadrAdd3) {
                this.txtPermadrAdd3 = txtPermadrAdd3;
            }

            public String getRefHoldPhoneOff() {
                return refHoldPhoneOff;
            }

            public void setRefHoldPhoneOff(String refHoldPhoneOff) {
                this.refHoldPhoneOff = refHoldPhoneOff;
            }

            public String getCodTypKyc() {
                return codTypKyc;
            }

            public void setCodTypKyc(String codTypKyc) {
                this.codTypKyc = codTypKyc;
            }

            public String getFlgIcTyp() {
                return flgIcTyp;
            }

            public void setFlgIcTyp(String flgIcTyp) {
                this.flgIcTyp = flgIcTyp;
            }

            @Override
            public String toString() {
                return "CustomerDetailsDTO{" +
                        "customerFullName='" + customerFullName + '\'' +
                        ", txtPermadrAdd1='" + txtPermadrAdd1 + '\'' +
                        ", rmPhoneNo='" + rmPhoneNo + '\'' +
                        ", codKeyType='" + codKeyType + '\'' +
                        ", codBlockReason='" + codBlockReason + '\'' +
                        ", namPermadrState='" + namPermadrState + '\'' +
                        ", refOfficePhoneArea='" + refOfficePhoneArea + '\'' +
                        ", refResPhoneArea='" + refResPhoneArea + '\'' +
                        ", rmEmailID='" + rmEmailID + '\'' +
                        ", codMisCompCode1='" + codMisCompCode1 + '\'' +
                        ", memoSevrity='" + memoSevrity + '\'' +
                        ", codRM='" + codRM + '\'' +
                        ", prefCustFlag='" + prefCustFlag + '\'' +
                        ", codCustNatlId='" + codCustNatlId + '\'' +
                        ", rmBranchName='" + rmBranchName + '\'' +
                        ", txtContryUltRisk='" + txtContryUltRisk + '\'' +
                        ", accountMemoDetailsDTO=" + accountMemoDetailsDTO +
                        ", namCustadrState='" + namCustadrState + '\'' +
                        ", namCustadrCntryDescription='" + namCustadrCntryDescription + '\'' +
                        ", flgCustMemo='" + flgCustMemo + '\'' +
                        ", keyPartnerCode='" + keyPartnerCode + '\'' +
                        ", customerAccountDetailsDTO=" + customerAccountDetailsDTO +
                        ", mastCodGroupId='" + mastCodGroupId + '\'' +
                        ", codCustMarstat=" + codCustMarstat +
                        ", refHoldTelex='" + refHoldTelex + '\'' +
                        ", codBusinessCat=" + codBusinessCat +
                        ", customerFirstName='" + customerFirstName + '\'' +
                        ", customerMemoDetailsDTO=" + customerMemoDetailsDTO +
                        ", refCustPhoneOff='" + refCustPhoneOff + '\'' +
                        ", flgDocComplete='" + flgDocComplete + '\'' +
                        ", datIncorporated='" + datIncorporated + '\'' +
                        ", codBusinessRegno='" + codBusinessRegno + '\'' +
                        ", genderDescription='" + genderDescription + '\'' +
                        ", namCustadrCntry='" + namCustadrCntry + '\'' +
                        ", kycStatus='" + kycStatus + '\'' +
                        ", codCustIntroducerId=" + codCustIntroducerId +
                        ", refHoldPhone='" + refHoldPhone + '\'' +
                        ", txtEthnicOrigin='" + txtEthnicOrigin + '\'' +
                        ", refOfficePhoneExtn='" + refOfficePhoneExtn + '\'' +
                        ", codGroup='" + codGroup + '\'' +
                        ", txtCustDesgn='" + txtCustDesgn + '\'' +
                        ", codTds=" + codTds +
                        ", customerAMLDetailsDTO=" + customerAMLDetailsDTO +
                        ", txtCustSex='" + txtCustSex + '\'' +
                        ", customerId='" + customerId + '\'' +
                        ", datVerified='" + datVerified + '\'' +
                        ", memoNo='" + memoNo + '\'' +
                        ", customerLastName='" + customerLastName + '\'' +
                        ", txtCustadrAdd2='" + txtCustadrAdd2 + '\'' +
                        ", maritalStatusDescription='" + maritalStatusDescription + '\'' +
                        ", txtCustadrAdd1='" + txtCustadrAdd1 + '\'' +
                        ", custHomeBranchCode='" + custHomeBranchCode + '\'' +
                        ", txtCustadrAdd3='" + txtCustadrAdd3 + '\'' +
                        ", refPhoneMobile='" + refPhoneMobile + '\'' +
                        ", rmCode='" + rmCode + '\'' +
                        ", namMotherMaiden='" + namMotherMaiden + '\'' +
                        ", namHoldadrCntry='" + namHoldadrCntry + '\'' +
                        ", refResPhoneCountry='" + refResPhoneCountry + '\'' +
                        ", namPermadrCntryDescription='" + namPermadrCntryDescription + '\'' +
                        ", refCustFax='" + refCustFax + '\'' +
                        ", txtCustPrefix='" + txtCustPrefix + '\'' +
                        ", refCustItNum='" + refCustItNum + '\'' +
                        ", txtCustNATNLTY='" + txtCustNATNLTY + '\'' +
                        ", namPermadrCntry='" + namPermadrCntry + '\'' +
                        ", namDirector2='" + namDirector2 + '\'' +
                        ", namHoldadrCity='" + namHoldadrCity + '\'' +
                        ", txtHoldadrAdd3='" + txtHoldadrAdd3 + '\'' +
                        ", refHoldFax='" + refHoldFax + '\'' +
                        ", datBirthCust='" + datBirthCust + '\'' +
                        ", txtHoldadrAdd1='" + txtHoldadrAdd1 + '\'' +
                        ", memoText='" + memoText + '\'' +
                        ", refCustEmail='" + refCustEmail + '\'' +
                        ", txtHoldadrAdd2='" + txtHoldadrAdd2 + '\'' +
                        ", ethnicCode='" + ethnicCode + '\'' +
                        ", flgKio='" + flgKio + '\'' +
                        ", customerFATCADtlsDTO=" + customerFATCADtlsDTO +
                        ", namCntryIncorp='" + namCntryIncorp + '\'' +
                        ", namCustadrCity='" + namCustadrCity + '\'' +
                        ", codCustStatus=" + codCustStatus +
                        ", panStatus='" + panStatus + '\'' +
                        ", customerTypeDescription='" + customerTypeDescription + '\'' +
                        ", customerBand='" + customerBand + '\'' +
                        ", addressChangeFlag='" + addressChangeFlag + '\'' +
                        ", namHoldadrState='" + namHoldadrState + '\'' +
                        ", flgStaff='" + flgStaff + '\'' +
                        ", txtCustResidence='" + txtCustResidence + '\'' +
                        ", rmBranchCode=" + rmBranchCode +
                        ", oldCustomerID='" + oldCustomerID + '\'' +
                        ", txtPermadrZip='" + txtPermadrZip + '\'' +
                        ", refCustTelex='" + refCustTelex + '\'' +
                        ", customerShortName='" + customerShortName + '\'' +
                        ", codIinId=" + codIinId +
                        ", flgVerifiedStat='" + flgVerifiedStat + '\'' +
                        ", flgCustTyp='" + flgCustTyp + '\'' +
                        ", txtKycDocIdentifier='" + txtKycDocIdentifier + '\'' +
                        ", codUtlComp='" + codUtlComp + '\'' +
                        ", txtBusinessTyp='" + txtBusinessTyp + '\'' +
                        ", txtHoldadrZip='" + txtHoldadrZip + '\'' +
                        ", datAdhrLnkdAcctUpd='" + datAdhrLnkdAcctUpd + '\'' +
                        ", rmName='" + rmName + '\'' +
                        ", customerMiddleName='" + customerMiddleName + '\'' +
                        ", codMisCustCode1='" + codMisCustCode1 + '\'' +
                        ", txtContryInitRisk='" + txtContryInitRisk + '\'' +
                        ", codEmployeeId='" + codEmployeeId + '\'' +
                        ", codGroupId='" + codGroupId + '\'' +
                        ", codAadhaarNo='" + codAadhaarNo + '\'' +
                        ", flgMinor='" + flgMinor + '\'' +
                        ", codStmtDispatchMode='" + codStmtDispatchMode + '\'' +
                        ", cifType='" + cifType + '\'' +
                        ", refOfficePhoneCountry='" + refOfficePhoneCountry + '\'' +
                        ", datAadhaarUpdatedOn='" + datAadhaarUpdatedOn + '\'' +
                        ", flgBlocked='" + flgBlocked + '\'' +
                        ", refCustPhone='" + refCustPhone + '\'' +
                        ", txtDesignationValue='" + txtDesignationValue + '\'' +
                        ", txtCustadrZip='" + txtCustadrZip + '\'' +
                        ", codDesignation=" + codDesignation +
                        ", refResPhoneExtn='" + refResPhoneExtn + '\'' +
                        ", ethnicCodeDesc='" + ethnicCodeDesc + '\'' +
                        ", refHoldEmail='" + refHoldEmail + '\'' +
                        ", datLastMnt='" + datLastMnt + '\'' +
                        ", namPermadrCity='" + namPermadrCity + '\'' +
                        ", codLiabNo='" + codLiabNo + '\'' +
                        ", codAadhaarLinkAcct='" + codAadhaarLinkAcct + '\'' +
                        ", txtPermadrAdd2='" + txtPermadrAdd2 + '\'' +
                        ", txtPermadrAdd3='" + txtPermadrAdd3 + '\'' +
                        ", refHoldPhoneOff='" + refHoldPhoneOff + '\'' +
                        ", codTypKyc='" + codTypKyc + '\'' +
                        ", flgIcTyp='" + flgIcTyp + '\'' +
                        '}';
            }
        }

    public static class AccountMemoDetailsDTO implements Serializable {
        @JsonProperty("txtCustMemo")
        private String txtCustMemo;
        @JsonProperty("flgSeverity")
        private String flgSeverity;
        @JsonProperty("datMemoStart")
        private String datMemoStart;
        @JsonProperty("datMemoEnd")
        private String datMemoEnd;
        @JsonProperty("codReason")
        private int codReason;

        // Getters and setters

        public String getTxtCustMemo() {
            return txtCustMemo;
        }

        public void setTxtCustMemo(String txtCustMemo) {
            this.txtCustMemo = txtCustMemo;
        }

        public String getFlgSeverity() {
            return flgSeverity;
        }

        public void setFlgSeverity(String flgSeverity) {
            this.flgSeverity = flgSeverity;
        }

        public String getDatMemoStart() {
            return datMemoStart;
        }

        public void setDatMemoStart(String datMemoStart) {
            this.datMemoStart = datMemoStart;
        }

        public String getDatMemoEnd() {
            return datMemoEnd;
        }

        public void setDatMemoEnd(String datMemoEnd) {
            this.datMemoEnd = datMemoEnd;
        }

        public int getCodReason() {
            return codReason;
        }

        public void setCodReason(int codReason) {
            this.codReason = codReason;
        }

        @Override
        public String toString() {
            return "AccountMemoDetailsDTO{" +
                    "txtCustMemo='" + txtCustMemo + '\'' +
                    ", flgSeverity='" + flgSeverity + '\'' +
                    ", datMemoStart='" + datMemoStart + '\'' +
                    ", datMemoEnd='" + datMemoEnd + '\'' +
                    ", codReason=" + codReason +
                    '}';
        }
    }

    public static class CustomerFATCADtlsDTO implements Serializable {
        @JsonProperty("txtLglCase")
        private String txtLglCase;
        @JsonProperty("ctrYrsJob")
        private int ctrYrsJob;
        @JsonProperty("refCustEmpPhoneCountry")
        private String refCustEmpPhoneCountry;
        @JsonProperty("addrTaxReporting1")
        private String addrTaxReporting1;
        @JsonProperty("addrTaxReporting2")
        private String addrTaxReporting2;
        @JsonProperty("addrTaxReporting3")
        private String addrTaxReporting3;
        @JsonProperty("txtMedIns")
        private String txtMedIns;
        @JsonProperty("namEmpadrCity")
        private String namEmpadrCity;
        @JsonProperty("refCustEmpEmail")
        private String refCustEmpEmail;
        @JsonProperty("refCustEmpFax")
        private String refCustEmpFax;
        @JsonProperty("amtValHouse")
        private int amtValHouse;
        @JsonProperty("txtAddr3SpoEmp")
        private String txtAddr3SpoEmp;
        @JsonProperty("refCustEmpFaxArea")
        private String refCustEmpFaxArea;
        @JsonProperty("addrProof")
        private String addrProof;
        @JsonProperty("addrTaxReporting4")
        private String addrTaxReporting4;
        @JsonProperty("addrTaxReporting5")
        private String addrTaxReporting5;
        @JsonProperty("namCustEmp")
        private String namCustEmp;
        @JsonProperty("txtDesgnPrevjob")
        private String txtDesgnPrevjob;
        @JsonProperty("txtEmpadrAdd2")
        private String txtEmpadrAdd2;
        @JsonProperty("txtEmpadrAdd3")
        private String txtEmpadrAdd3;
        @JsonProperty("txtEmpadrAdd1")
        private String txtEmpadrAdd1;
        @JsonProperty("namCityBirth")
        private String namCityBirth;
        @JsonProperty("datPsptIssue")
        private String datPsptIssue;
        @JsonProperty("datExpiryVisa")
        private String datExpiryVisa;
        @JsonProperty("codCntryBirth")
        private String codCntryBirth;
        @JsonProperty("addrTyp")
        private int addrTyp;
        @JsonProperty("ctrAgeRetire")
        private int ctrAgeRetire;
        @JsonProperty("typAddrTax5")
        private String typAddrTax5;
        @JsonProperty("addrProofDocNo")
        private String addrProofDocNo;
        @JsonProperty("typAddrTax3")
        private String typAddrTax3;
        @JsonProperty("namEmpadrCntry")
        private String namEmpadrCntry;
        @JsonProperty("typAddrTax4")
        private String typAddrTax4;
        @JsonProperty("txtSpoDesgn")
        private String txtSpoDesgn;
        @JsonProperty("idDocExpDat")
        private String idDocExpDat;
        @JsonProperty("typAddrTax1")
        private String typAddrTax1;
        @JsonProperty("typAddrTax2")
        private String typAddrTax2;
        @JsonProperty("txtMortAssts")
        private String txtMortAssts;
        @JsonProperty("datFromPrevjob")
        private String datFromPrevjob;
        @JsonProperty("txtEmpDesignation")
        private String txtEmpDesignation;
        @JsonProperty("txtTenrOccuptn")
        private int txtTenrOccuptn;
        @JsonProperty("txtTypVisa")
        private String txtTypVisa;
        @JsonProperty("codTaxCntry5")
        private String codTaxCntry5;
        @JsonProperty("idDocDesc")
        private String idDocDesc;
        @JsonProperty("refSpoEmpPhone")
        private String refSpoEmpPhone;
        @JsonProperty("datPsptExpiry")
        private String datPsptExpiry;
        @JsonProperty("namCustMaiden")
        private String namCustMaiden;
        @JsonProperty("relatedPersonType")
        private String relatedPersonType;
        @JsonProperty("amtRentExp")
        private int amtRentExp;
        @JsonProperty("txtEmpadrZip")
        private String txtEmpadrZip;
        @JsonProperty("txtRsnLeftJob")
        private String txtRsnLeftJob;
        @JsonProperty("flgLglCase")
        private String flgLglCase;
        @JsonProperty("codTaxCntry1")
        private String codTaxCntry1;
        @JsonProperty("codCcBrn")
        private int codCcBrn;
        @JsonProperty("codTaxCntry2")
        private String codTaxCntry2;
        @JsonProperty("codTaxCntry3")
        private String codTaxCntry3;
        @JsonProperty("codTaxCntry4")
        private String codTaxCntry4;
        @JsonProperty("namEmpadrState")
        private String namEmpadrState;
        @JsonProperty("namEmp")
        private String namEmp;
        @JsonProperty("datToPrevjob")
        private String datToPrevjob;
        @JsonProperty("namCustSpouse")
        private String namCustSpouse;
        @JsonProperty("amtCustIncome")
        private int amtCustIncome;
        @JsonProperty("txtDesignation")
        private String txtDesignation;
        @JsonProperty("txtCrRating")
        private String txtCrRating;
        @JsonProperty("namCrCard1")
        private String namCrCard1;
        @JsonProperty("namCrCard2")
        private String namCrCard2;
        @JsonProperty("namCrCard3")
        private String namCrCard3;
        @JsonProperty("namPrevEmp")
        private String namPrevEmp;
        @JsonProperty("amtInsuExp")
        private int amtInsuExp;
        @JsonProperty("datAnnexure")
        private String datAnnexure;
        @JsonProperty("txtOccuptnCat")
        private int txtOccuptnCat;
        @JsonProperty("datDobSpouse")
        private String datDobSpouse;
        @JsonProperty("namSpoEmployer")
        private String namSpoEmployer;
        @JsonProperty("dinNo")
        private String dinNo;
        @JsonProperty("txtAddr1SpoEmp")
        private String txtAddr1SpoEmp;
        @JsonProperty("amtOthExpense")
        private int amtOthExpense;
        @JsonProperty("datMarriage")
        private String datMarriage;
        @JsonProperty("flgTaxCntryCitizen4")
        private String flgTaxCntryCitizen4;
        @JsonProperty("flgTaxCntryCitizen5")
        private String flgTaxCntryCitizen5;
        @JsonProperty("flgTaxCntryCitizen2")
        private String flgTaxCntryCitizen2;
        @JsonProperty("flgTaxCntryCitizen3")
        private String flgTaxCntryCitizen3;
        @JsonProperty("amtOthIncome")
        private int amtOthIncome;
        @JsonProperty("idDocTyp")
        private String idDocTyp;
        @JsonProperty("refCustEmpPhoneExtn")
        private String refCustEmpPhoneExtn;
        @JsonProperty("datProcess")
        private String datProcess;
        @JsonProperty("flgTaxCntryCitizen1")
        private String flgTaxCntryCitizen1;
        @JsonProperty("idDocNum")
        private String idDocNum;
        @JsonProperty("datCrRating")
        private String datCrRating;
        @JsonProperty("refCustEmpMobile")
        private String refCustEmpMobile;
        @JsonProperty("txtPoliceRecrd")
        private String txtPoliceRecrd;
        @JsonProperty("txtAddrProofDesc")
        private String txtAddrProofDesc;
        @JsonProperty("namCustFather")
        private String namCustFather;
        @JsonProperty("txtCustOccuptn")
        private String txtCustOccuptn;
        @JsonProperty("txtAddr2SpoEmp")
        private String txtAddr2SpoEmp;
        @JsonProperty("namSpouseCust")
        private String namSpouseCust;
        @JsonProperty("refCustEmpFaxCountry")
        private String refCustEmpFaxCountry;
        @JsonProperty("txtRemarks")
        private String txtRemarks;
        @JsonProperty("refCrCard1")
        private String refCrCard1;
        @JsonProperty("txtValMort")
        private String txtValMort;
        @JsonProperty("flgTaxCntryRes3")
        private String flgTaxCntryRes3;
        @JsonProperty("refVisa")
        private String refVisa;
        @JsonProperty("refCustEmpPhoneArea")
        private String refCustEmpPhoneArea;
        @JsonProperty("flgTaxCntryRes2")
        private String flgTaxCntryRes2;
        @JsonProperty("flgTaxCntryRes5")
        private String flgTaxCntryRes5;
        @JsonProperty("flgTaxCntryRes4")
        private String flgTaxCntryRes4;
        @JsonProperty("tinNo2")
        private String tinNo2;
        @JsonProperty("tinNo3")
        private String tinNo3;
        @JsonProperty("tinNo4")
        private String tinNo4;
        @JsonProperty("tinNo5")
        private String tinNo5;
        @JsonProperty("refCustEmpTelex")
        private String refCustEmpTelex;
        @JsonProperty("typTinNo3")
        private String typTinNo3;
        @JsonProperty("typTinNo4")
        private String typTinNo4;
        @JsonProperty("typTinNo5")
        private String typTinNo5;
        @JsonProperty("tinNo1")
        private String tinNo1;
        @JsonProperty("ctrDurtnStay")
        private int ctrDurtnStay;
        @JsonProperty("txtMedHist")
        private String txtMedHist;
        @JsonProperty("flgTaxCntryRes1")
        private String flgTaxCntryRes1;
        @JsonProperty("typTinNo1")
        private String typTinNo1;
        @JsonProperty("typTinNo2")
        private String typTinNo2;
        @JsonProperty("codResStat")
        private String codResStat;
        @JsonProperty("codEmpId")
        private String codEmpId;
        @JsonProperty("amtLastSal")
        private int amtLastSal;
        @JsonProperty("refCustPspt")
        private String refCustPspt;
        @JsonProperty("refCustEmpPhone")
        private String refCustEmpPhone;
        @JsonProperty("refCrCard3")
        private String refCrCard3;
        @JsonProperty("refCrCard2")
        private String refCrCard2;
        @JsonProperty("ctrUpdatSrlno")
        private int ctrUpdatSrlno;
        @JsonProperty("codNatlIdSpouse")
        private String codNatlIdSpouse;
        @JsonProperty("codVisaTyp")
        private String codVisaTyp;
        @JsonProperty("txtOccpnSpouse")
        private int txtOccpnSpouse;

        // Getters and Setters


        public String getTxtLglCase() {
            return txtLglCase;
        }

        public void setTxtLglCase(String txtLglCase) {
            this.txtLglCase = txtLglCase;
        }

        public int getCtrYrsJob() {
            return ctrYrsJob;
        }

        public void setCtrYrsJob(int ctrYrsJob) {
            this.ctrYrsJob = ctrYrsJob;
        }

        public String getRefCustEmpPhoneCountry() {
            return refCustEmpPhoneCountry;
        }

        public void setRefCustEmpPhoneCountry(String refCustEmpPhoneCountry) {
            this.refCustEmpPhoneCountry = refCustEmpPhoneCountry;
        }

        public String getAddrTaxReporting1() {
            return addrTaxReporting1;
        }

        public void setAddrTaxReporting1(String addrTaxReporting1) {
            this.addrTaxReporting1 = addrTaxReporting1;
        }

        public String getAddrTaxReporting2() {
            return addrTaxReporting2;
        }

        public void setAddrTaxReporting2(String addrTaxReporting2) {
            this.addrTaxReporting2 = addrTaxReporting2;
        }

        public String getAddrTaxReporting3() {
            return addrTaxReporting3;
        }

        public void setAddrTaxReporting3(String addrTaxReporting3) {
            this.addrTaxReporting3 = addrTaxReporting3;
        }

        public String getTxtMedIns() {
            return txtMedIns;
        }

        public void setTxtMedIns(String txtMedIns) {
            this.txtMedIns = txtMedIns;
        }

        public String getNamEmpadrCity() {
            return namEmpadrCity;
        }

        public void setNamEmpadrCity(String namEmpadrCity) {
            this.namEmpadrCity = namEmpadrCity;
        }

        public String getRefCustEmpEmail() {
            return refCustEmpEmail;
        }

        public void setRefCustEmpEmail(String refCustEmpEmail) {
            this.refCustEmpEmail = refCustEmpEmail;
        }

        public String getRefCustEmpFax() {
            return refCustEmpFax;
        }

        public void setRefCustEmpFax(String refCustEmpFax) {
            this.refCustEmpFax = refCustEmpFax;
        }

        public int getAmtValHouse() {
            return amtValHouse;
        }

        public void setAmtValHouse(int amtValHouse) {
            this.amtValHouse = amtValHouse;
        }

        public String getTxtAddr3SpoEmp() {
            return txtAddr3SpoEmp;
        }

        public void setTxtAddr3SpoEmp(String txtAddr3SpoEmp) {
            this.txtAddr3SpoEmp = txtAddr3SpoEmp;
        }

        public String getRefCustEmpFaxArea() {
            return refCustEmpFaxArea;
        }

        public void setRefCustEmpFaxArea(String refCustEmpFaxArea) {
            this.refCustEmpFaxArea = refCustEmpFaxArea;
        }

        public String getAddrProof() {
            return addrProof;
        }

        public void setAddrProof(String addrProof) {
            this.addrProof = addrProof;
        }

        public String getAddrTaxReporting4() {
            return addrTaxReporting4;
        }

        public void setAddrTaxReporting4(String addrTaxReporting4) {
            this.addrTaxReporting4 = addrTaxReporting4;
        }

        public String getAddrTaxReporting5() {
            return addrTaxReporting5;
        }

        public void setAddrTaxReporting5(String addrTaxReporting5) {
            this.addrTaxReporting5 = addrTaxReporting5;
        }

        public String getNamCustEmp() {
            return namCustEmp;
        }

        public void setNamCustEmp(String namCustEmp) {
            this.namCustEmp = namCustEmp;
        }

        public String getTxtDesgnPrevjob() {
            return txtDesgnPrevjob;
        }

        public void setTxtDesgnPrevjob(String txtDesgnPrevjob) {
            this.txtDesgnPrevjob = txtDesgnPrevjob;
        }

        public String getTxtEmpadrAdd2() {
            return txtEmpadrAdd2;
        }

        public void setTxtEmpadrAdd2(String txtEmpadrAdd2) {
            this.txtEmpadrAdd2 = txtEmpadrAdd2;
        }

        public String getTxtEmpadrAdd3() {
            return txtEmpadrAdd3;
        }

        public void setTxtEmpadrAdd3(String txtEmpadrAdd3) {
            this.txtEmpadrAdd3 = txtEmpadrAdd3;
        }

        public String getTxtEmpadrAdd1() {
            return txtEmpadrAdd1;
        }

        public void setTxtEmpadrAdd1(String txtEmpadrAdd1) {
            this.txtEmpadrAdd1 = txtEmpadrAdd1;
        }

        public String getNamCityBirth() {
            return namCityBirth;
        }

        public void setNamCityBirth(String namCityBirth) {
            this.namCityBirth = namCityBirth;
        }

        public String getDatPsptIssue() {
            return datPsptIssue;
        }

        public void setDatPsptIssue(String datPsptIssue) {
            this.datPsptIssue = datPsptIssue;
        }

        public String getDatExpiryVisa() {
            return datExpiryVisa;
        }

        public void setDatExpiryVisa(String datExpiryVisa) {
            this.datExpiryVisa = datExpiryVisa;
        }

        public String getCodCntryBirth() {
            return codCntryBirth;
        }

        public void setCodCntryBirth(String codCntryBirth) {
            this.codCntryBirth = codCntryBirth;
        }

        public int getAddrTyp() {
            return addrTyp;
        }

        public void setAddrTyp(int addrTyp) {
            this.addrTyp = addrTyp;
        }

        public int getCtrAgeRetire() {
            return ctrAgeRetire;
        }

        public void setCtrAgeRetire(int ctrAgeRetire) {
            this.ctrAgeRetire = ctrAgeRetire;
        }

        public String getTypAddrTax5() {
            return typAddrTax5;
        }

        public void setTypAddrTax5(String typAddrTax5) {
            this.typAddrTax5 = typAddrTax5;
        }

        public String getAddrProofDocNo() {
            return addrProofDocNo;
        }

        public void setAddrProofDocNo(String addrProofDocNo) {
            this.addrProofDocNo = addrProofDocNo;
        }

        public String getTypAddrTax3() {
            return typAddrTax3;
        }

        public void setTypAddrTax3(String typAddrTax3) {
            this.typAddrTax3 = typAddrTax3;
        }

        public String getNamEmpadrCntry() {
            return namEmpadrCntry;
        }

        public void setNamEmpadrCntry(String namEmpadrCntry) {
            this.namEmpadrCntry = namEmpadrCntry;
        }

        public String getTypAddrTax4() {
            return typAddrTax4;
        }

        public void setTypAddrTax4(String typAddrTax4) {
            this.typAddrTax4 = typAddrTax4;
        }

        public String getTxtSpoDesgn() {
            return txtSpoDesgn;
        }

        public void setTxtSpoDesgn(String txtSpoDesgn) {
            this.txtSpoDesgn = txtSpoDesgn;
        }

        public String getIdDocExpDat() {
            return idDocExpDat;
        }

        public void setIdDocExpDat(String idDocExpDat) {
            this.idDocExpDat = idDocExpDat;
        }

        public String getTypAddrTax1() {
            return typAddrTax1;
        }

        public void setTypAddrTax1(String typAddrTax1) {
            this.typAddrTax1 = typAddrTax1;
        }

        public String getTypAddrTax2() {
            return typAddrTax2;
        }

        public void setTypAddrTax2(String typAddrTax2) {
            this.typAddrTax2 = typAddrTax2;
        }

        public String getTxtMortAssts() {
            return txtMortAssts;
        }

        public void setTxtMortAssts(String txtMortAssts) {
            this.txtMortAssts = txtMortAssts;
        }

        public String getDatFromPrevjob() {
            return datFromPrevjob;
        }

        public void setDatFromPrevjob(String datFromPrevjob) {
            this.datFromPrevjob = datFromPrevjob;
        }

        public String getTxtEmpDesignation() {
            return txtEmpDesignation;
        }

        public void setTxtEmpDesignation(String txtEmpDesignation) {
            this.txtEmpDesignation = txtEmpDesignation;
        }

        public int getTxtTenrOccuptn() {
            return txtTenrOccuptn;
        }

        public void setTxtTenrOccuptn(int txtTenrOccuptn) {
            this.txtTenrOccuptn = txtTenrOccuptn;
        }

        public String getTxtTypVisa() {
            return txtTypVisa;
        }

        public void setTxtTypVisa(String txtTypVisa) {
            this.txtTypVisa = txtTypVisa;
        }

        public String getCodTaxCntry5() {
            return codTaxCntry5;
        }

        public void setCodTaxCntry5(String codTaxCntry5) {
            this.codTaxCntry5 = codTaxCntry5;
        }

        public String getIdDocDesc() {
            return idDocDesc;
        }

        public void setIdDocDesc(String idDocDesc) {
            this.idDocDesc = idDocDesc;
        }

        public String getRefSpoEmpPhone() {
            return refSpoEmpPhone;
        }

        public void setRefSpoEmpPhone(String refSpoEmpPhone) {
            this.refSpoEmpPhone = refSpoEmpPhone;
        }

        public String getDatPsptExpiry() {
            return datPsptExpiry;
        }

        public void setDatPsptExpiry(String datPsptExpiry) {
            this.datPsptExpiry = datPsptExpiry;
        }

        public String getNamCustMaiden() {
            return namCustMaiden;
        }

        public void setNamCustMaiden(String namCustMaiden) {
            this.namCustMaiden = namCustMaiden;
        }

        public String getRelatedPersonType() {
            return relatedPersonType;
        }

        public void setRelatedPersonType(String relatedPersonType) {
            this.relatedPersonType = relatedPersonType;
        }

        public int getAmtRentExp() {
            return amtRentExp;
        }

        public void setAmtRentExp(int amtRentExp) {
            this.amtRentExp = amtRentExp;
        }

        public String getTxtEmpadrZip() {
            return txtEmpadrZip;
        }

        public void setTxtEmpadrZip(String txtEmpadrZip) {
            this.txtEmpadrZip = txtEmpadrZip;
        }

        public String getTxtRsnLeftJob() {
            return txtRsnLeftJob;
        }

        public void setTxtRsnLeftJob(String txtRsnLeftJob) {
            this.txtRsnLeftJob = txtRsnLeftJob;
        }

        public String getFlgLglCase() {
            return flgLglCase;
        }

        public void setFlgLglCase(String flgLglCase) {
            this.flgLglCase = flgLglCase;
        }

        public String getCodTaxCntry1() {
            return codTaxCntry1;
        }

        public void setCodTaxCntry1(String codTaxCntry1) {
            this.codTaxCntry1 = codTaxCntry1;
        }

        public int getCodCcBrn() {
            return codCcBrn;
        }

        public void setCodCcBrn(int codCcBrn) {
            this.codCcBrn = codCcBrn;
        }

        public String getCodTaxCntry2() {
            return codTaxCntry2;
        }

        public void setCodTaxCntry2(String codTaxCntry2) {
            this.codTaxCntry2 = codTaxCntry2;
        }

        public String getCodTaxCntry3() {
            return codTaxCntry3;
        }

        public void setCodTaxCntry3(String codTaxCntry3) {
            this.codTaxCntry3 = codTaxCntry3;
        }

        public String getCodTaxCntry4() {
            return codTaxCntry4;
        }

        public void setCodTaxCntry4(String codTaxCntry4) {
            this.codTaxCntry4 = codTaxCntry4;
        }

        public String getNamEmpadrState() {
            return namEmpadrState;
        }

        public void setNamEmpadrState(String namEmpadrState) {
            this.namEmpadrState = namEmpadrState;
        }

        public String getNamEmp() {
            return namEmp;
        }

        public void setNamEmp(String namEmp) {
            this.namEmp = namEmp;
        }

        public String getDatToPrevjob() {
            return datToPrevjob;
        }

        public void setDatToPrevjob(String datToPrevjob) {
            this.datToPrevjob = datToPrevjob;
        }

        public String getNamCustSpouse() {
            return namCustSpouse;
        }

        public void setNamCustSpouse(String namCustSpouse) {
            this.namCustSpouse = namCustSpouse;
        }

        public int getAmtCustIncome() {
            return amtCustIncome;
        }

        public void setAmtCustIncome(int amtCustIncome) {
            this.amtCustIncome = amtCustIncome;
        }

        public String getTxtDesignation() {
            return txtDesignation;
        }

        public void setTxtDesignation(String txtDesignation) {
            this.txtDesignation = txtDesignation;
        }

        public String getTxtCrRating() {
            return txtCrRating;
        }

        public void setTxtCrRating(String txtCrRating) {
            this.txtCrRating = txtCrRating;
        }

        public String getNamCrCard1() {
            return namCrCard1;
        }

        public void setNamCrCard1(String namCrCard1) {
            this.namCrCard1 = namCrCard1;
        }

        public String getNamCrCard2() {
            return namCrCard2;
        }

        public void setNamCrCard2(String namCrCard2) {
            this.namCrCard2 = namCrCard2;
        }

        public String getNamCrCard3() {
            return namCrCard3;
        }

        public void setNamCrCard3(String namCrCard3) {
            this.namCrCard3 = namCrCard3;
        }

        public String getNamPrevEmp() {
            return namPrevEmp;
        }

        public void setNamPrevEmp(String namPrevEmp) {
            this.namPrevEmp = namPrevEmp;
        }

        public int getAmtInsuExp() {
            return amtInsuExp;
        }

        public void setAmtInsuExp(int amtInsuExp) {
            this.amtInsuExp = amtInsuExp;
        }

        public String getDatAnnexure() {
            return datAnnexure;
        }

        public void setDatAnnexure(String datAnnexure) {
            this.datAnnexure = datAnnexure;
        }

        public int getTxtOccuptnCat() {
            return txtOccuptnCat;
        }

        public void setTxtOccuptnCat(int txtOccuptnCat) {
            this.txtOccuptnCat = txtOccuptnCat;
        }

        public String getDatDobSpouse() {
            return datDobSpouse;
        }

        public void setDatDobSpouse(String datDobSpouse) {
            this.datDobSpouse = datDobSpouse;
        }

        public String getNamSpoEmployer() {
            return namSpoEmployer;
        }

        public void setNamSpoEmployer(String namSpoEmployer) {
            this.namSpoEmployer = namSpoEmployer;
        }

        public String getDinNo() {
            return dinNo;
        }

        public void setDinNo(String dinNo) {
            this.dinNo = dinNo;
        }

        public String getTxtAddr1SpoEmp() {
            return txtAddr1SpoEmp;
        }

        public void setTxtAddr1SpoEmp(String txtAddr1SpoEmp) {
            this.txtAddr1SpoEmp = txtAddr1SpoEmp;
        }

        public int getAmtOthExpense() {
            return amtOthExpense;
        }

        public void setAmtOthExpense(int amtOthExpense) {
            this.amtOthExpense = amtOthExpense;
        }

        public String getDatMarriage() {
            return datMarriage;
        }

        public void setDatMarriage(String datMarriage) {
            this.datMarriage = datMarriage;
        }

        public String getFlgTaxCntryCitizen4() {
            return flgTaxCntryCitizen4;
        }

        public void setFlgTaxCntryCitizen4(String flgTaxCntryCitizen4) {
            this.flgTaxCntryCitizen4 = flgTaxCntryCitizen4;
        }

        public String getFlgTaxCntryCitizen5() {
            return flgTaxCntryCitizen5;
        }

        public void setFlgTaxCntryCitizen5(String flgTaxCntryCitizen5) {
            this.flgTaxCntryCitizen5 = flgTaxCntryCitizen5;
        }

        public String getFlgTaxCntryCitizen2() {
            return flgTaxCntryCitizen2;
        }

        public void setFlgTaxCntryCitizen2(String flgTaxCntryCitizen2) {
            this.flgTaxCntryCitizen2 = flgTaxCntryCitizen2;
        }

        public String getFlgTaxCntryCitizen3() {
            return flgTaxCntryCitizen3;
        }

        public void setFlgTaxCntryCitizen3(String flgTaxCntryCitizen3) {
            this.flgTaxCntryCitizen3 = flgTaxCntryCitizen3;
        }

        public int getAmtOthIncome() {
            return amtOthIncome;
        }

        public void setAmtOthIncome(int amtOthIncome) {
            this.amtOthIncome = amtOthIncome;
        }

        public String getIdDocTyp() {
            return idDocTyp;
        }

        public void setIdDocTyp(String idDocTyp) {
            this.idDocTyp = idDocTyp;
        }

        public String getRefCustEmpPhoneExtn() {
            return refCustEmpPhoneExtn;
        }

        public void setRefCustEmpPhoneExtn(String refCustEmpPhoneExtn) {
            this.refCustEmpPhoneExtn = refCustEmpPhoneExtn;
        }

        public String getDatProcess() {
            return datProcess;
        }

        public void setDatProcess(String datProcess) {
            this.datProcess = datProcess;
        }

        public String getFlgTaxCntryCitizen1() {
            return flgTaxCntryCitizen1;
        }

        public void setFlgTaxCntryCitizen1(String flgTaxCntryCitizen1) {
            this.flgTaxCntryCitizen1 = flgTaxCntryCitizen1;
        }

        public String getIdDocNum() {
            return idDocNum;
        }

        public void setIdDocNum(String idDocNum) {
            this.idDocNum = idDocNum;
        }

        public String getDatCrRating() {
            return datCrRating;
        }

        public void setDatCrRating(String datCrRating) {
            this.datCrRating = datCrRating;
        }

        public String getRefCustEmpMobile() {
            return refCustEmpMobile;
        }

        public void setRefCustEmpMobile(String refCustEmpMobile) {
            this.refCustEmpMobile = refCustEmpMobile;
        }

        public String getTxtPoliceRecrd() {
            return txtPoliceRecrd;
        }

        public void setTxtPoliceRecrd(String txtPoliceRecrd) {
            this.txtPoliceRecrd = txtPoliceRecrd;
        }

        public String getTxtAddrProofDesc() {
            return txtAddrProofDesc;
        }

        public void setTxtAddrProofDesc(String txtAddrProofDesc) {
            this.txtAddrProofDesc = txtAddrProofDesc;
        }

        public String getNamCustFather() {
            return namCustFather;
        }

        public void setNamCustFather(String namCustFather) {
            this.namCustFather = namCustFather;
        }

        public String getTxtCustOccuptn() {
            return txtCustOccuptn;
        }

        public void setTxtCustOccuptn(String txtCustOccuptn) {
            this.txtCustOccuptn = txtCustOccuptn;
        }

        public String getTxtAddr2SpoEmp() {
            return txtAddr2SpoEmp;
        }

        public void setTxtAddr2SpoEmp(String txtAddr2SpoEmp) {
            this.txtAddr2SpoEmp = txtAddr2SpoEmp;
        }

        public String getNamSpouseCust() {
            return namSpouseCust;
        }

        public void setNamSpouseCust(String namSpouseCust) {
            this.namSpouseCust = namSpouseCust;
        }

        public String getRefCustEmpFaxCountry() {
            return refCustEmpFaxCountry;
        }

        public void setRefCustEmpFaxCountry(String refCustEmpFaxCountry) {
            this.refCustEmpFaxCountry = refCustEmpFaxCountry;
        }

        public String getTxtRemarks() {
            return txtRemarks;
        }

        public void setTxtRemarks(String txtRemarks) {
            this.txtRemarks = txtRemarks;
        }

        public String getRefCrCard1() {
            return refCrCard1;
        }

        public void setRefCrCard1(String refCrCard1) {
            this.refCrCard1 = refCrCard1;
        }

        public String getTxtValMort() {
            return txtValMort;
        }

        public void setTxtValMort(String txtValMort) {
            this.txtValMort = txtValMort;
        }

        public String getFlgTaxCntryRes3() {
            return flgTaxCntryRes3;
        }

        public void setFlgTaxCntryRes3(String flgTaxCntryRes3) {
            this.flgTaxCntryRes3 = flgTaxCntryRes3;
        }

        public String getRefVisa() {
            return refVisa;
        }

        public void setRefVisa(String refVisa) {
            this.refVisa = refVisa;
        }

        public String getRefCustEmpPhoneArea() {
            return refCustEmpPhoneArea;
        }

        public void setRefCustEmpPhoneArea(String refCustEmpPhoneArea) {
            this.refCustEmpPhoneArea = refCustEmpPhoneArea;
        }

        public String getFlgTaxCntryRes2() {
            return flgTaxCntryRes2;
        }

        public void setFlgTaxCntryRes2(String flgTaxCntryRes2) {
            this.flgTaxCntryRes2 = flgTaxCntryRes2;
        }

        public String getFlgTaxCntryRes5() {
            return flgTaxCntryRes5;
        }

        public void setFlgTaxCntryRes5(String flgTaxCntryRes5) {
            this.flgTaxCntryRes5 = flgTaxCntryRes5;
        }

        public String getFlgTaxCntryRes4() {
            return flgTaxCntryRes4;
        }

        public void setFlgTaxCntryRes4(String flgTaxCntryRes4) {
            this.flgTaxCntryRes4 = flgTaxCntryRes4;
        }

        public String getTinNo2() {
            return tinNo2;
        }

        public void setTinNo2(String tinNo2) {
            this.tinNo2 = tinNo2;
        }

        public String getTinNo3() {
            return tinNo3;
        }

        public void setTinNo3(String tinNo3) {
            this.tinNo3 = tinNo3;
        }

        public String getTinNo4() {
            return tinNo4;
        }

        public void setTinNo4(String tinNo4) {
            this.tinNo4 = tinNo4;
        }

        public String getTinNo5() {
            return tinNo5;
        }

        public void setTinNo5(String tinNo5) {
            this.tinNo5 = tinNo5;
        }

        public String getRefCustEmpTelex() {
            return refCustEmpTelex;
        }

        public void setRefCustEmpTelex(String refCustEmpTelex) {
            this.refCustEmpTelex = refCustEmpTelex;
        }

        public String getTypTinNo3() {
            return typTinNo3;
        }

        public void setTypTinNo3(String typTinNo3) {
            this.typTinNo3 = typTinNo3;
        }

        public String getTypTinNo4() {
            return typTinNo4;
        }

        public void setTypTinNo4(String typTinNo4) {
            this.typTinNo4 = typTinNo4;
        }

        public String getTypTinNo5() {
            return typTinNo5;
        }

        public void setTypTinNo5(String typTinNo5) {
            this.typTinNo5 = typTinNo5;
        }

        public String getTinNo1() {
            return tinNo1;
        }

        public void setTinNo1(String tinNo1) {
            this.tinNo1 = tinNo1;
        }

        public int getCtrDurtnStay() {
            return ctrDurtnStay;
        }

        public void setCtrDurtnStay(int ctrDurtnStay) {
            this.ctrDurtnStay = ctrDurtnStay;
        }

        public String getTxtMedHist() {
            return txtMedHist;
        }

        public void setTxtMedHist(String txtMedHist) {
            this.txtMedHist = txtMedHist;
        }

        public String getFlgTaxCntryRes1() {
            return flgTaxCntryRes1;
        }

        public void setFlgTaxCntryRes1(String flgTaxCntryRes1) {
            this.flgTaxCntryRes1 = flgTaxCntryRes1;
        }

        public String getTypTinNo1() {
            return typTinNo1;
        }

        public void setTypTinNo1(String typTinNo1) {
            this.typTinNo1 = typTinNo1;
        }

        public String getTypTinNo2() {
            return typTinNo2;
        }

        public void setTypTinNo2(String typTinNo2) {
            this.typTinNo2 = typTinNo2;
        }

        public String getCodResStat() {
            return codResStat;
        }

        public void setCodResStat(String codResStat) {
            this.codResStat = codResStat;
        }

        public String getCodEmpId() {
            return codEmpId;
        }

        public void setCodEmpId(String codEmpId) {
            this.codEmpId = codEmpId;
        }

        public int getAmtLastSal() {
            return amtLastSal;
        }

        public void setAmtLastSal(int amtLastSal) {
            this.amtLastSal = amtLastSal;
        }

        public String getRefCustPspt() {
            return refCustPspt;
        }

        public void setRefCustPspt(String refCustPspt) {
            this.refCustPspt = refCustPspt;
        }

        public String getRefCustEmpPhone() {
            return refCustEmpPhone;
        }

        public void setRefCustEmpPhone(String refCustEmpPhone) {
            this.refCustEmpPhone = refCustEmpPhone;
        }

        public String getRefCrCard3() {
            return refCrCard3;
        }

        public void setRefCrCard3(String refCrCard3) {
            this.refCrCard3 = refCrCard3;
        }

        public String getRefCrCard2() {
            return refCrCard2;
        }

        public void setRefCrCard2(String refCrCard2) {
            this.refCrCard2 = refCrCard2;
        }

        public int getCtrUpdatSrlno() {
            return ctrUpdatSrlno;
        }

        public void setCtrUpdatSrlno(int ctrUpdatSrlno) {
            this.ctrUpdatSrlno = ctrUpdatSrlno;
        }

        public String getCodNatlIdSpouse() {
            return codNatlIdSpouse;
        }

        public void setCodNatlIdSpouse(String codNatlIdSpouse) {
            this.codNatlIdSpouse = codNatlIdSpouse;
        }

        public String getCodVisaTyp() {
            return codVisaTyp;
        }

        public void setCodVisaTyp(String codVisaTyp) {
            this.codVisaTyp = codVisaTyp;
        }

        public int getTxtOccpnSpouse() {
            return txtOccpnSpouse;
        }

        public void setTxtOccpnSpouse(int txtOccpnSpouse) {
            this.txtOccpnSpouse = txtOccpnSpouse;
        }

        @Override
        public String toString() {
            return "CustomerFATCADtlsDTO{" +
                    "txtLglCase='" + txtLglCase + '\'' +
                    ", ctrYrsJob=" + ctrYrsJob +
                    ", refCustEmpPhoneCountry='" + refCustEmpPhoneCountry + '\'' +
                    ", addrTaxReporting1='" + addrTaxReporting1 + '\'' +
                    ", addrTaxReporting2='" + addrTaxReporting2 + '\'' +
                    ", addrTaxReporting3='" + addrTaxReporting3 + '\'' +
                    ", txtMedIns='" + txtMedIns + '\'' +
                    ", namEmpadrCity='" + namEmpadrCity + '\'' +
                    ", refCustEmpEmail='" + refCustEmpEmail + '\'' +
                    ", refCustEmpFax='" + refCustEmpFax + '\'' +
                    ", amtValHouse=" + amtValHouse +
                    ", txtAddr3SpoEmp='" + txtAddr3SpoEmp + '\'' +
                    ", refCustEmpFaxArea='" + refCustEmpFaxArea + '\'' +
                    ", addrProof='" + addrProof + '\'' +
                    ", addrTaxReporting4='" + addrTaxReporting4 + '\'' +
                    ", addrTaxReporting5='" + addrTaxReporting5 + '\'' +
                    ", namCustEmp='" + namCustEmp + '\'' +
                    ", txtDesgnPrevjob='" + txtDesgnPrevjob + '\'' +
                    ", txtEmpadrAdd2='" + txtEmpadrAdd2 + '\'' +
                    ", txtEmpadrAdd3='" + txtEmpadrAdd3 + '\'' +
                    ", txtEmpadrAdd1='" + txtEmpadrAdd1 + '\'' +
                    ", namCityBirth='" + namCityBirth + '\'' +
                    ", datPsptIssue='" + datPsptIssue + '\'' +
                    ", datExpiryVisa='" + datExpiryVisa + '\'' +
                    ", codCntryBirth='" + codCntryBirth + '\'' +
                    ", addrTyp=" + addrTyp +
                    ", ctrAgeRetire=" + ctrAgeRetire +
                    ", typAddrTax5='" + typAddrTax5 + '\'' +
                    ", addrProofDocNo='" + addrProofDocNo + '\'' +
                    ", typAddrTax3='" + typAddrTax3 + '\'' +
                    ", namEmpadrCntry='" + namEmpadrCntry + '\'' +
                    ", typAddrTax4='" + typAddrTax4 + '\'' +
                    ", txtSpoDesgn='" + txtSpoDesgn + '\'' +
                    ", idDocExpDat='" + idDocExpDat + '\'' +
                    ", typAddrTax1='" + typAddrTax1 + '\'' +
                    ", typAddrTax2='" + typAddrTax2 + '\'' +
                    ", txtMortAssts='" + txtMortAssts + '\'' +
                    ", datFromPrevjob='" + datFromPrevjob + '\'' +
                    ", txtEmpDesignation='" + txtEmpDesignation + '\'' +
                    ", txtTenrOccuptn=" + txtTenrOccuptn +
                    ", txtTypVisa='" + txtTypVisa + '\'' +
                    ", codTaxCntry5='" + codTaxCntry5 + '\'' +
                    ", idDocDesc='" + idDocDesc + '\'' +
                    ", refSpoEmpPhone='" + refSpoEmpPhone + '\'' +
                    ", datPsptExpiry='" + datPsptExpiry + '\'' +
                    ", namCustMaiden='" + namCustMaiden + '\'' +
                    ", relatedPersonType='" + relatedPersonType + '\'' +
                    ", amtRentExp=" + amtRentExp +
                    ", txtEmpadrZip='" + txtEmpadrZip + '\'' +
                    ", txtRsnLeftJob='" + txtRsnLeftJob + '\'' +
                    ", flgLglCase='" + flgLglCase + '\'' +
                    ", codTaxCntry1='" + codTaxCntry1 + '\'' +
                    ", codCcBrn=" + codCcBrn +
                    ", codTaxCntry2='" + codTaxCntry2 + '\'' +
                    ", codTaxCntry3='" + codTaxCntry3 + '\'' +
                    ", codTaxCntry4='" + codTaxCntry4 + '\'' +
                    ", namEmpadrState='" + namEmpadrState + '\'' +
                    ", namEmp='" + namEmp + '\'' +
                    ", datToPrevjob='" + datToPrevjob + '\'' +
                    ", namCustSpouse='" + namCustSpouse + '\'' +
                    ", amtCustIncome=" + amtCustIncome +
                    ", txtDesignation='" + txtDesignation + '\'' +
                    ", txtCrRating='" + txtCrRating + '\'' +
                    ", namCrCard1='" + namCrCard1 + '\'' +
                    ", namCrCard2='" + namCrCard2 + '\'' +
                    ", namCrCard3='" + namCrCard3 + '\'' +
                    ", namPrevEmp='" + namPrevEmp + '\'' +
                    ", amtInsuExp=" + amtInsuExp +
                    ", datAnnexure='" + datAnnexure + '\'' +
                    ", txtOccuptnCat=" + txtOccuptnCat +
                    ", datDobSpouse='" + datDobSpouse + '\'' +
                    ", namSpoEmployer='" + namSpoEmployer + '\'' +
                    ", dinNo='" + dinNo + '\'' +
                    ", txtAddr1SpoEmp='" + txtAddr1SpoEmp + '\'' +
                    ", amtOthExpense=" + amtOthExpense +
                    ", datMarriage='" + datMarriage + '\'' +
                    ", flgTaxCntryCitizen4='" + flgTaxCntryCitizen4 + '\'' +
                    ", flgTaxCntryCitizen5='" + flgTaxCntryCitizen5 + '\'' +
                    ", flgTaxCntryCitizen2='" + flgTaxCntryCitizen2 + '\'' +
                    ", flgTaxCntryCitizen3='" + flgTaxCntryCitizen3 + '\'' +
                    ", amtOthIncome=" + amtOthIncome +
                    ", idDocTyp='" + idDocTyp + '\'' +
                    ", refCustEmpPhoneExtn='" + refCustEmpPhoneExtn + '\'' +
                    ", datProcess='" + datProcess + '\'' +
                    ", flgTaxCntryCitizen1='" + flgTaxCntryCitizen1 + '\'' +
                    ", idDocNum='" + idDocNum + '\'' +
                    ", datCrRating='" + datCrRating + '\'' +
                    ", refCustEmpMobile='" + refCustEmpMobile + '\'' +
                    ", txtPoliceRecrd='" + txtPoliceRecrd + '\'' +
                    ", txtAddrProofDesc='" + txtAddrProofDesc + '\'' +
                    ", namCustFather='" + namCustFather + '\'' +
                    ", txtCustOccuptn='" + txtCustOccuptn + '\'' +
                    ", txtAddr2SpoEmp='" + txtAddr2SpoEmp + '\'' +
                    ", namSpouseCust='" + namSpouseCust + '\'' +
                    ", refCustEmpFaxCountry='" + refCustEmpFaxCountry + '\'' +
                    ", txtRemarks='" + txtRemarks + '\'' +
                    ", refCrCard1='" + refCrCard1 + '\'' +
                    ", txtValMort='" + txtValMort + '\'' +
                    ", flgTaxCntryRes3='" + flgTaxCntryRes3 + '\'' +
                    ", refVisa='" + refVisa + '\'' +
                    ", refCustEmpPhoneArea='" + refCustEmpPhoneArea + '\'' +
                    ", flgTaxCntryRes2='" + flgTaxCntryRes2 + '\'' +
                    ", flgTaxCntryRes5='" + flgTaxCntryRes5 + '\'' +
                    ", flgTaxCntryRes4='" + flgTaxCntryRes4 + '\'' +
                    ", tinNo2='" + tinNo2 + '\'' +
                    ", tinNo3='" + tinNo3 + '\'' +
                    ", tinNo4='" + tinNo4 + '\'' +
                    ", tinNo5='" + tinNo5 + '\'' +
                    ", refCustEmpTelex='" + refCustEmpTelex + '\'' +
                    ", typTinNo3='" + typTinNo3 + '\'' +
                    ", typTinNo4='" + typTinNo4 + '\'' +
                    ", typTinNo5='" + typTinNo5 + '\'' +
                    ", tinNo1='" + tinNo1 + '\'' +
                    ", ctrDurtnStay=" + ctrDurtnStay +
                    ", txtMedHist='" + txtMedHist + '\'' +
                    ", flgTaxCntryRes1='" + flgTaxCntryRes1 + '\'' +
                    ", typTinNo1='" + typTinNo1 + '\'' +
                    ", typTinNo2='" + typTinNo2 + '\'' +
                    ", codResStat='" + codResStat + '\'' +
                    ", codEmpId='" + codEmpId + '\'' +
                    ", amtLastSal=" + amtLastSal +
                    ", refCustPspt='" + refCustPspt + '\'' +
                    ", refCustEmpPhone='" + refCustEmpPhone + '\'' +
                    ", refCrCard3='" + refCrCard3 + '\'' +
                    ", refCrCard2='" + refCrCard2 + '\'' +
                    ", ctrUpdatSrlno=" + ctrUpdatSrlno +
                    ", codNatlIdSpouse='" + codNatlIdSpouse + '\'' +
                    ", codVisaTyp='" + codVisaTyp + '\'' +
                    ", txtOccpnSpouse=" + txtOccpnSpouse +
                    '}';
        }
    }
        public static class CustomerMemoDetailsDTO implements Serializable {

        @JsonProperty("txtCustMemo")
        private String txtCustMemo;
        @JsonProperty("flgSeverity")
        private String flgSeverity;
        @JsonProperty("datMemoStart")
        private String datMemoStart;
        @JsonProperty("datMemoEnd")
        private String datMemoEnd;
        @JsonProperty("codReason")
        private int codReason;

        // Getters and Setters


            public String getTxtCustMemo() {
                return txtCustMemo;
            }

            public void setTxtCustMemo(String txtCustMemo) {
                this.txtCustMemo = txtCustMemo;
            }

            public String getFlgSeverity() {
                return flgSeverity;
            }

            public void setFlgSeverity(String flgSeverity) {
                this.flgSeverity = flgSeverity;
            }

            public String getDatMemoStart() {
                return datMemoStart;
            }

            public void setDatMemoStart(String datMemoStart) {
                this.datMemoStart = datMemoStart;
            }

            public String getDatMemoEnd() {
                return datMemoEnd;
            }

            public void setDatMemoEnd(String datMemoEnd) {
                this.datMemoEnd = datMemoEnd;
            }

            public int getCodReason() {
                return codReason;
            }

            public void setCodReason(int codReason) {
                this.codReason = codReason;
            }

            @Override
            public String toString() {
                return "CustomerMemoDetailsDTO{" +
                        "txtCustMemo='" + txtCustMemo + '\'' +
                        ", flgSeverity='" + flgSeverity + '\'' +
                        ", datMemoStart='" + datMemoStart + '\'' +
                        ", datMemoEnd='" + datMemoEnd + '\'' +
                        ", codReason=" + codReason +
                        '}';
            }
        }

    public static class CustomerAMLDetailsDTO implements Serializable {

        @JsonProperty("selfEmpFrom")
        private String selfEmpFrom;

        @JsonProperty("amlNum9")
        private int amlNum9;

        @JsonProperty("amlNum8")
        private int amlNum8;

        @JsonProperty("amlNum7")
        private int amlNum7;

        @JsonProperty("amlNum6")
        private int amlNum6;

        @JsonProperty("natIndCode")
        private String natIndCode;

        @JsonProperty("incSrcDesc")
        private String incSrcDesc;

        @JsonProperty("iecCode")
        private String iecCode;

        @JsonProperty("datOfIncorp")
        private String datOfIncorp;

        @JsonProperty("ausInCa")
        private String ausInCa;

        @JsonProperty("amlNum1")
        private int amlNum1;

        @JsonProperty("typeDocument")
        private String typeDocument;

        @JsonProperty("annualTurnover")
        private int annualTurnover;

        @JsonProperty("amlNum5")
        private int amlNum5;

        @JsonProperty("amlNum4")
        private int amlNum4;

        @JsonProperty("amlNum3")
        private int amlNum3;

        @JsonProperty("amlNum2")
        private int amlNum2;

        @JsonProperty("namCcy")
        private String namCcy;

        @JsonProperty("codProfession")
        private String codProfession;

        @JsonProperty("typEmployer")
        private String typEmployer;

        @JsonProperty("landHoldingInAcres")
        private String landHoldingInAcres;

        @JsonProperty("amlDate10")
        private String amlDate10;

        @JsonProperty("dtlActivity")
        private String dtlActivity;

        @JsonProperty("txtOccupDesc")
        private String txtOccupDesc;

        @JsonProperty("natIndDesc")
        private String natIndDesc;

        @JsonProperty("natureOfBus")
        private String natureOfBus;

        @JsonProperty("txtResDesc")
        private String txtResDesc;

        @JsonProperty("docIssDate")
        private String docIssDate;

        @JsonProperty("designation")
        private String designation;

        @JsonProperty("amlCod10")
        private String amlCod10;

        @JsonProperty("codOccupation")
        private String codOccupation;

        @JsonProperty("subCatCode")
        private String subCatCode;

        @JsonProperty("riskProfile")
        private String riskProfile;

        @JsonProperty("txtEmployerDesc")
        private String txtEmployerDesc;

        @JsonProperty("txtProfessionDesc")
        private String txtProfessionDesc;

        @JsonProperty("datCashProfileExpiry")
        private String datCashProfileExpiry;

        @JsonProperty("docIssPlace")
        private String docIssPlace;

        @JsonProperty("grossIncome")
        private String grossIncome;

        @JsonProperty("amlCod6")
        private String amlCod6;

        @JsonProperty("amlCod7")
        private String amlCod7;

        @JsonProperty("amlCod4")
        private String amlCod4;

        @JsonProperty("amlCod5")
        private String amlCod5;

        @JsonProperty("amlCod2")
        private String amlCod2;

        @JsonProperty("ausDate")
        private String ausDate;

        @JsonProperty("amlCod3")
        private String amlCod3;

        @JsonProperty("grossIncDesc")
        private String grossIncDesc;

        @JsonProperty("amlCod1")
        private String amlCod1;

        @JsonProperty("typeOfBusiness")
        private String typeOfBusiness;

        @JsonProperty("txtCompanyDesc")
        private String txtCompanyDesc;

        @JsonProperty("amlNum10")
        private int amlNum10;

        @JsonProperty("subCatDesc")
        private String subCatDesc;

        @JsonProperty("txtDocumentDesc")
        private String txtDocumentDesc;

        @JsonProperty("amlCod8")
        private String amlCod8;

        @JsonProperty("amlCod9")
        private String amlCod9;

        @JsonProperty("typCompany")
        private String typCompany;

        @JsonProperty("amlDate1")
        private String amlDate1;

        @JsonProperty("amlDate2")
        private String amlDate2;

        @JsonProperty("amlDate3")
        private String amlDate3;

        @JsonProperty("amlDate4")
        private String amlDate4;

        @JsonProperty("datLastMnt")
        private String datLastMnt;

        @JsonProperty("amlDate5")
        private String amlDate5;

        @JsonProperty("amlDate6")
        private String amlDate6;

        @JsonProperty("amlDate7")
        private String amlDate7;

        @JsonProperty("documentId")
        private String documentId;

        @JsonProperty("amlDate8")
        private String amlDate8;

        @JsonProperty("incomeSource")
        private String incomeSource;

        @JsonProperty("txtBusDesc")
        private String txtBusDesc;

        @JsonProperty("amlDate9")
        private String amlDate9;

        @JsonProperty("typResidence")
        private String typResidence;

        @JsonProperty("eximValue")
        private String eximValue;

        // Getters and Setters


        public String getSelfEmpFrom() {
            return selfEmpFrom;
        }

        public void setSelfEmpFrom(String selfEmpFrom) {
            this.selfEmpFrom = selfEmpFrom;
        }

        public int getAmlNum9() {
            return amlNum9;
        }

        public void setAmlNum9(int amlNum9) {
            this.amlNum9 = amlNum9;
        }

        public int getAmlNum8() {
            return amlNum8;
        }

        public void setAmlNum8(int amlNum8) {
            this.amlNum8 = amlNum8;
        }

        public int getAmlNum7() {
            return amlNum7;
        }

        public void setAmlNum7(int amlNum7) {
            this.amlNum7 = amlNum7;
        }

        public int getAmlNum6() {
            return amlNum6;
        }

        public void setAmlNum6(int amlNum6) {
            this.amlNum6 = amlNum6;
        }

        public String getNatIndCode() {
            return natIndCode;
        }

        public void setNatIndCode(String natIndCode) {
            this.natIndCode = natIndCode;
        }

        public String getIncSrcDesc() {
            return incSrcDesc;
        }

        public void setIncSrcDesc(String incSrcDesc) {
            this.incSrcDesc = incSrcDesc;
        }

        public String getIecCode() {
            return iecCode;
        }

        public void setIecCode(String iecCode) {
            this.iecCode = iecCode;
        }

        public String getDatOfIncorp() {
            return datOfIncorp;
        }

        public void setDatOfIncorp(String datOfIncorp) {
            this.datOfIncorp = datOfIncorp;
        }

        public String getAusInCa() {
            return ausInCa;
        }

        public void setAusInCa(String ausInCa) {
            this.ausInCa = ausInCa;
        }

        public int getAmlNum1() {
            return amlNum1;
        }

        public void setAmlNum1(int amlNum1) {
            this.amlNum1 = amlNum1;
        }

        public String getTypeDocument() {
            return typeDocument;
        }

        public void setTypeDocument(String typeDocument) {
            this.typeDocument = typeDocument;
        }

        public int getAnnualTurnover() {
            return annualTurnover;
        }

        public void setAnnualTurnover(int annualTurnover) {
            this.annualTurnover = annualTurnover;
        }

        public int getAmlNum5() {
            return amlNum5;
        }

        public void setAmlNum5(int amlNum5) {
            this.amlNum5 = amlNum5;
        }

        public int getAmlNum4() {
            return amlNum4;
        }

        public void setAmlNum4(int amlNum4) {
            this.amlNum4 = amlNum4;
        }

        public int getAmlNum3() {
            return amlNum3;
        }

        public void setAmlNum3(int amlNum3) {
            this.amlNum3 = amlNum3;
        }

        public int getAmlNum2() {
            return amlNum2;
        }

        public void setAmlNum2(int amlNum2) {
            this.amlNum2 = amlNum2;
        }

        public String getNamCcy() {
            return namCcy;
        }

        public void setNamCcy(String namCcy) {
            this.namCcy = namCcy;
        }

        public String getCodProfession() {
            return codProfession;
        }

        public void setCodProfession(String codProfession) {
            this.codProfession = codProfession;
        }

        public String getTypEmployer() {
            return typEmployer;
        }

        public void setTypEmployer(String typEmployer) {
            this.typEmployer = typEmployer;
        }

        public String getLandHoldingInAcres() {
            return landHoldingInAcres;
        }

        public void setLandHoldingInAcres(String landHoldingInAcres) {
            this.landHoldingInAcres = landHoldingInAcres;
        }

        public String getAmlDate10() {
            return amlDate10;
        }

        public void setAmlDate10(String amlDate10) {
            this.amlDate10 = amlDate10;
        }

        public String getDtlActivity() {
            return dtlActivity;
        }

        public void setDtlActivity(String dtlActivity) {
            this.dtlActivity = dtlActivity;
        }

        public String getTxtOccupDesc() {
            return txtOccupDesc;
        }

        public void setTxtOccupDesc(String txtOccupDesc) {
            this.txtOccupDesc = txtOccupDesc;
        }

        public String getNatIndDesc() {
            return natIndDesc;
        }

        public void setNatIndDesc(String natIndDesc) {
            this.natIndDesc = natIndDesc;
        }

        public String getNatureOfBus() {
            return natureOfBus;
        }

        public void setNatureOfBus(String natureOfBus) {
            this.natureOfBus = natureOfBus;
        }

        public String getTxtResDesc() {
            return txtResDesc;
        }

        public void setTxtResDesc(String txtResDesc) {
            this.txtResDesc = txtResDesc;
        }

        public String getDocIssDate() {
            return docIssDate;
        }

        public void setDocIssDate(String docIssDate) {
            this.docIssDate = docIssDate;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getAmlCod10() {
            return amlCod10;
        }

        public void setAmlCod10(String amlCod10) {
            this.amlCod10 = amlCod10;
        }

        public String getCodOccupation() {
            return codOccupation;
        }

        public void setCodOccupation(String codOccupation) {
            this.codOccupation = codOccupation;
        }

        public String getSubCatCode() {
            return subCatCode;
        }

        public void setSubCatCode(String subCatCode) {
            this.subCatCode = subCatCode;
        }

        public String getRiskProfile() {
            return riskProfile;
        }

        public void setRiskProfile(String riskProfile) {
            this.riskProfile = riskProfile;
        }

        public String getTxtEmployerDesc() {
            return txtEmployerDesc;
        }

        public void setTxtEmployerDesc(String txtEmployerDesc) {
            this.txtEmployerDesc = txtEmployerDesc;
        }

        public String getTxtProfessionDesc() {
            return txtProfessionDesc;
        }

        public void setTxtProfessionDesc(String txtProfessionDesc) {
            this.txtProfessionDesc = txtProfessionDesc;
        }

        public String getDatCashProfileExpiry() {
            return datCashProfileExpiry;
        }

        public void setDatCashProfileExpiry(String datCashProfileExpiry) {
            this.datCashProfileExpiry = datCashProfileExpiry;
        }

        public String getDocIssPlace() {
            return docIssPlace;
        }

        public void setDocIssPlace(String docIssPlace) {
            this.docIssPlace = docIssPlace;
        }

        public String getGrossIncome() {
            return grossIncome;
        }

        public void setGrossIncome(String grossIncome) {
            this.grossIncome = grossIncome;
        }

        public String getAmlCod6() {
            return amlCod6;
        }

        public void setAmlCod6(String amlCod6) {
            this.amlCod6 = amlCod6;
        }

        public String getAmlCod7() {
            return amlCod7;
        }

        public void setAmlCod7(String amlCod7) {
            this.amlCod7 = amlCod7;
        }

        public String getAmlCod4() {
            return amlCod4;
        }

        public void setAmlCod4(String amlCod4) {
            this.amlCod4 = amlCod4;
        }

        public String getAmlCod5() {
            return amlCod5;
        }

        public void setAmlCod5(String amlCod5) {
            this.amlCod5 = amlCod5;
        }

        public String getAmlCod2() {
            return amlCod2;
        }

        public void setAmlCod2(String amlCod2) {
            this.amlCod2 = amlCod2;
        }

        public String getAusDate() {
            return ausDate;
        }

        public void setAusDate(String ausDate) {
            this.ausDate = ausDate;
        }

        public String getAmlCod3() {
            return amlCod3;
        }

        public void setAmlCod3(String amlCod3) {
            this.amlCod3 = amlCod3;
        }

        public String getGrossIncDesc() {
            return grossIncDesc;
        }

        public void setGrossIncDesc(String grossIncDesc) {
            this.grossIncDesc = grossIncDesc;
        }

        public String getAmlCod1() {
            return amlCod1;
        }

        public void setAmlCod1(String amlCod1) {
            this.amlCod1 = amlCod1;
        }

        public String getTypeOfBusiness() {
            return typeOfBusiness;
        }

        public void setTypeOfBusiness(String typeOfBusiness) {
            this.typeOfBusiness = typeOfBusiness;
        }

        public String getTxtCompanyDesc() {
            return txtCompanyDesc;
        }

        public void setTxtCompanyDesc(String txtCompanyDesc) {
            this.txtCompanyDesc = txtCompanyDesc;
        }

        public int getAmlNum10() {
            return amlNum10;
        }

        public void setAmlNum10(int amlNum10) {
            this.amlNum10 = amlNum10;
        }

        public String getSubCatDesc() {
            return subCatDesc;
        }

        public void setSubCatDesc(String subCatDesc) {
            this.subCatDesc = subCatDesc;
        }

        public String getTxtDocumentDesc() {
            return txtDocumentDesc;
        }

        public void setTxtDocumentDesc(String txtDocumentDesc) {
            this.txtDocumentDesc = txtDocumentDesc;
        }

        public String getAmlCod8() {
            return amlCod8;
        }

        public void setAmlCod8(String amlCod8) {
            this.amlCod8 = amlCod8;
        }

        public String getAmlCod9() {
            return amlCod9;
        }

        public void setAmlCod9(String amlCod9) {
            this.amlCod9 = amlCod9;
        }

        public String getTypCompany() {
            return typCompany;
        }

        public void setTypCompany(String typCompany) {
            this.typCompany = typCompany;
        }

        public String getAmlDate1() {
            return amlDate1;
        }

        public void setAmlDate1(String amlDate1) {
            this.amlDate1 = amlDate1;
        }

        public String getAmlDate2() {
            return amlDate2;
        }

        public void setAmlDate2(String amlDate2) {
            this.amlDate2 = amlDate2;
        }

        public String getAmlDate3() {
            return amlDate3;
        }

        public void setAmlDate3(String amlDate3) {
            this.amlDate3 = amlDate3;
        }

        public String getAmlDate4() {
            return amlDate4;
        }

        public void setAmlDate4(String amlDate4) {
            this.amlDate4 = amlDate4;
        }

        public String getDatLastMnt() {
            return datLastMnt;
        }

        public void setDatLastMnt(String datLastMnt) {
            this.datLastMnt = datLastMnt;
        }

        public String getAmlDate5() {
            return amlDate5;
        }

        public void setAmlDate5(String amlDate5) {
            this.amlDate5 = amlDate5;
        }

        public String getAmlDate6() {
            return amlDate6;
        }

        public void setAmlDate6(String amlDate6) {
            this.amlDate6 = amlDate6;
        }

        public String getAmlDate7() {
            return amlDate7;
        }

        public void setAmlDate7(String amlDate7) {
            this.amlDate7 = amlDate7;
        }

        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public String getAmlDate8() {
            return amlDate8;
        }

        public void setAmlDate8(String amlDate8) {
            this.amlDate8 = amlDate8;
        }

        public String getIncomeSource() {
            return incomeSource;
        }

        public void setIncomeSource(String incomeSource) {
            this.incomeSource = incomeSource;
        }

        public String getTxtBusDesc() {
            return txtBusDesc;
        }

        public void setTxtBusDesc(String txtBusDesc) {
            this.txtBusDesc = txtBusDesc;
        }

        public String getAmlDate9() {
            return amlDate9;
        }

        public void setAmlDate9(String amlDate9) {
            this.amlDate9 = amlDate9;
        }

        public String getTypResidence() {
            return typResidence;
        }

        public void setTypResidence(String typResidence) {
            this.typResidence = typResidence;
        }

        public String getEximValue() {
            return eximValue;
        }

        public void setEximValue(String eximValue) {
            this.eximValue = eximValue;
        }

        @Override
        public String toString() {
            return "CustomerAMLDetailsDTO{" +
                    "selfEmpFrom='" + selfEmpFrom + '\'' +
                    ", amlNum9=" + amlNum9 +
                    ", amlNum8=" + amlNum8 +
                    ", amlNum7=" + amlNum7 +
                    ", amlNum6=" + amlNum6 +
                    ", natIndCode='" + natIndCode + '\'' +
                    ", incSrcDesc='" + incSrcDesc + '\'' +
                    ", iecCode='" + iecCode + '\'' +
                    ", datOfIncorp='" + datOfIncorp + '\'' +
                    ", ausInCa='" + ausInCa + '\'' +
                    ", amlNum1=" + amlNum1 +
                    ", typeDocument='" + typeDocument + '\'' +
                    ", annualTurnover=" + annualTurnover +
                    ", amlNum5=" + amlNum5 +
                    ", amlNum4=" + amlNum4 +
                    ", amlNum3=" + amlNum3 +
                    ", amlNum2=" + amlNum2 +
                    ", namCcy='" + namCcy + '\'' +
                    ", codProfession='" + codProfession + '\'' +
                    ", typEmployer='" + typEmployer + '\'' +
                    ", landHoldingInAcres='" + landHoldingInAcres + '\'' +
                    ", amlDate10='" + amlDate10 + '\'' +
                    ", dtlActivity='" + dtlActivity + '\'' +
                    ", txtOccupDesc='" + txtOccupDesc + '\'' +
                    ", natIndDesc='" + natIndDesc + '\'' +
                    ", natureOfBus='" + natureOfBus + '\'' +
                    ", txtResDesc='" + txtResDesc + '\'' +
                    ", docIssDate='" + docIssDate + '\'' +
                    ", designation='" + designation + '\'' +
                    ", amlCod10='" + amlCod10 + '\'' +
                    ", codOccupation='" + codOccupation + '\'' +
                    ", subCatCode='" + subCatCode + '\'' +
                    ", riskProfile='" + riskProfile + '\'' +
                    ", txtEmployerDesc='" + txtEmployerDesc + '\'' +
                    ", txtProfessionDesc='" + txtProfessionDesc + '\'' +
                    ", datCashProfileExpiry='" + datCashProfileExpiry + '\'' +
                    ", docIssPlace='" + docIssPlace + '\'' +
                    ", grossIncome='" + grossIncome + '\'' +
                    ", amlCod6='" + amlCod6 + '\'' +
                    ", amlCod7='" + amlCod7 + '\'' +
                    ", amlCod4='" + amlCod4 + '\'' +
                    ", amlCod5='" + amlCod5 + '\'' +
                    ", amlCod2='" + amlCod2 + '\'' +
                    ", ausDate='" + ausDate + '\'' +
                    ", amlCod3='" + amlCod3 + '\'' +
                    ", grossIncDesc='" + grossIncDesc + '\'' +
                    ", amlCod1='" + amlCod1 + '\'' +
                    ", typeOfBusiness='" + typeOfBusiness + '\'' +
                    ", txtCompanyDesc='" + txtCompanyDesc + '\'' +
                    ", amlNum10=" + amlNum10 +
                    ", subCatDesc='" + subCatDesc + '\'' +
                    ", txtDocumentDesc='" + txtDocumentDesc + '\'' +
                    ", amlCod8='" + amlCod8 + '\'' +
                    ", amlCod9='" + amlCod9 + '\'' +
                    ", typCompany='" + typCompany + '\'' +
                    ", amlDate1='" + amlDate1 + '\'' +
                    ", amlDate2='" + amlDate2 + '\'' +
                    ", amlDate3='" + amlDate3 + '\'' +
                    ", amlDate4='" + amlDate4 + '\'' +
                    ", datLastMnt='" + datLastMnt + '\'' +
                    ", amlDate5='" + amlDate5 + '\'' +
                    ", amlDate6='" + amlDate6 + '\'' +
                    ", amlDate7='" + amlDate7 + '\'' +
                    ", documentId='" + documentId + '\'' +
                    ", amlDate8='" + amlDate8 + '\'' +
                    ", incomeSource='" + incomeSource + '\'' +
                    ", txtBusDesc='" + txtBusDesc + '\'' +
                    ", amlDate9='" + amlDate9 + '\'' +
                    ", typResidence='" + typResidence + '\'' +
                    ", eximValue='" + eximValue + '\'' +
                    '}';
        }
    }
        public static class CustomerAccountDetailsDTO implements Serializable{
            @JsonProperty("prodFrqChqThresholdLim")
            private Integer prodFrqChqThresholdLim;

            @JsonProperty("flagATM")
            private String flagATM;

            @JsonProperty("flgPos")
            private String flgPos;

            @JsonProperty("flagSWPOut")
            private String flagSWPOut;

            @JsonProperty("productcodProdType")
            private String productcodProdType;

            @JsonProperty("ctrThreshholdDayLimit")
            private Integer ctrThreshholdDayLimit;

            @JsonProperty("productName")
            private String productName;

            @JsonProperty("flgNomineeNameDisplay")
            private String flgNomineeNameDisplay;

            @JsonProperty("nomineeAge")
            private Integer nomineeAge;

            @JsonProperty("amtAqb4")
            private Double amtAqb4;

            @JsonProperty("flgTbs")
            private String flgTbs;

            @JsonProperty("amtAqb2")
            private Double amtAqb2;

            @JsonProperty("amtAqb3")
            private Double amtAqb3;

            @JsonProperty("accountOperatingInstructions")
            private String accountOperatingInstructions;

            @JsonProperty("amtAqb1")
            private Double amtAqb1;

            @JsonProperty("branchPinCode")
            private String branchPinCode;

            @JsonProperty("amtAmb2")
            private Double amtAmb2;

            @JsonProperty("amtAmb3")
            private Double amtAmb3;

            @JsonProperty("amtAmb1")
            private Double amtAmb1;

            @JsonProperty("flgChqGrntyFac")
            private String flgChqGrntyFac;

            @JsonProperty("flgAtmFac")
            private Integer flgAtmFac;

            @JsonProperty("ctrChqThreshholdLimit")
            private Integer ctrChqThreshholdLimit;

            @JsonProperty("balBook")
            private BigDecimal balBook;

            @JsonProperty("flgSiFac")
            private String flgSiFac;

            @JsonProperty("namGuardian")
            private String namGuardian;

            @JsonProperty("branchCountry")
            private String branchCountry;

            @JsonProperty("branchCode")
            private Integer branchCode;

            @JsonProperty("flgOdFac")
            private String flgOdFac;

            @JsonProperty("customerShortName")
            private String customerShortName;

            @JsonProperty("branchCity")
            private String branchCity;

            @JsonProperty("prodChqThresholdDayLim")
            private Integer prodChqThresholdDayLim;

            @JsonProperty("codTypProd")
            private String codTypProd;

            @JsonProperty("codProdType")
            private Integer codProdType;

            @JsonProperty("prodCtrChqThresholdLim")
            private Integer prodCtrChqThresholdLim;

            @JsonProperty("branchState")
            private String branchState;

            @JsonProperty("codRel")
            private Integer codRel;

            @JsonProperty("micrCode")
            private String micrCode;

            @JsonProperty("activationDate")
            private String activationDate;

            @JsonProperty("txtAcctMemo")
            private String txtAcctMemo;

            @JsonProperty("frqChqThreshholdLimit")
            private Integer frqChqThreshholdLimit;

            @JsonProperty("prodTypeDesc")
            private String prodTypeDesc;

            @JsonProperty("accountTitle")
            private String accountTitle;

            @JsonProperty("txtMsgAdv")
            private String txtMsgAdv;

            @JsonProperty("flgAtm")
            private Integer flgAtm;

            @JsonProperty("flgNet")
            private String flgNet;

            @JsonProperty("accountStatusDescription")
            private String accountStatusDescription;

            @JsonProperty("availableBalance")
            private BigDecimal availableBalance;

            @JsonProperty("codAcctNoExt")
            private String codAcctNoExt;

            @JsonProperty("jointHolders")
            private List<JointHolderDTO> jointHolders;

            // Getters and Setters

            public Integer getProdFrqChqThresholdLim() {
                return prodFrqChqThresholdLim;
            }

            public void setProdFrqChqThresholdLim(Integer prodFrqChqThresholdLim) {
                this.prodFrqChqThresholdLim = prodFrqChqThresholdLim;
            }

            public String getFlagATM() {
                return flagATM;
            }

            public void setFlagATM(String flagATM) {
                this.flagATM = flagATM;
            }

            public String getFlgPos() {
                return flgPos;
            }

            public void setFlgPos(String flgPos) {
                this.flgPos = flgPos;
            }

            public String getFlagSWPOut() {
                return flagSWPOut;
            }

            public void setFlagSWPOut(String flagSWPOut) {
                this.flagSWPOut = flagSWPOut;
            }

            public String getProductcodProdType() {
                return productcodProdType;
            }

            public void setProductcodProdType(String productcodProdType) {
                this.productcodProdType = productcodProdType;
            }

            public Integer getCtrThreshholdDayLimit() {
                return ctrThreshholdDayLimit;
            }

            public void setCtrThreshholdDayLimit(Integer ctrThreshholdDayLimit) {
                this.ctrThreshholdDayLimit = ctrThreshholdDayLimit;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getFlgNomineeNameDisplay() {
                return flgNomineeNameDisplay;
            }

            public void setFlgNomineeNameDisplay(String flgNomineeNameDisplay) {
                this.flgNomineeNameDisplay = flgNomineeNameDisplay;
            }

            public Integer getNomineeAge() {
                return nomineeAge;
            }

            public void setNomineeAge(Integer nomineeAge) {
                this.nomineeAge = nomineeAge;
            }

            public Double getAmtAqb4() {
                return amtAqb4;
            }

            public void setAmtAqb4(Double amtAqb4) {
                this.amtAqb4 = amtAqb4;
            }

            public String getFlgTbs() {
                return flgTbs;
            }

            public void setFlgTbs(String flgTbs) {
                this.flgTbs = flgTbs;
            }

            public Double getAmtAqb2() {
                return amtAqb2;
            }

            public void setAmtAqb2(Double amtAqb2) {
                this.amtAqb2 = amtAqb2;
            }

            public Double getAmtAqb3() {
                return amtAqb3;
            }

            public void setAmtAqb3(Double amtAqb3) {
                this.amtAqb3 = amtAqb3;
            }

            public String getAccountOperatingInstructions() {
                return accountOperatingInstructions;
            }

            public void setAccountOperatingInstructions(String accountOperatingInstructions) {
                this.accountOperatingInstructions = accountOperatingInstructions;
            }

            public Double getAmtAqb1() {
                return amtAqb1;
            }

            public void setAmtAqb1(Double amtAqb1) {
                this.amtAqb1 = amtAqb1;
            }

            public String getBranchPinCode() {
                return branchPinCode;
            }

            public void setBranchPinCode(String branchPinCode) {
                this.branchPinCode = branchPinCode;
            }

            public Double getAmtAmb2() {
                return amtAmb2;
            }

            public void setAmtAmb2(Double amtAmb2) {
                this.amtAmb2 = amtAmb2;
            }

            public Double getAmtAmb3() {
                return amtAmb3;
            }

            public void setAmtAmb3(Double amtAmb3) {
                this.amtAmb3 = amtAmb3;
            }

            public Double getAmtAmb1() {
                return amtAmb1;
            }

            public void setAmtAmb1(Double amtAmb1) {
                this.amtAmb1 = amtAmb1;
            }

            public String getFlgChqGrntyFac() {
                return flgChqGrntyFac;
            }

            public void setFlgChqGrntyFac(String flgChqGrntyFac) {
                this.flgChqGrntyFac = flgChqGrntyFac;
            }

            public Integer getFlgAtmFac() {
                return flgAtmFac;
            }

            public void setFlgAtmFac(Integer flgAtmFac) {
                this.flgAtmFac = flgAtmFac;
            }

            public Integer getCtrChqThreshholdLimit() {
                return ctrChqThreshholdLimit;
            }

            public void setCtrChqThreshholdLimit(Integer ctrChqThreshholdLimit) {
                this.ctrChqThreshholdLimit = ctrChqThreshholdLimit;
            }

            public BigDecimal getBalBook() {
                return balBook;
            }

            public void setBalBook(BigDecimal balBook) {
                this.balBook = balBook;
            }

            public String getFlgSiFac() {
                return flgSiFac;
            }

            public void setFlgSiFac(String flgSiFac) {
                this.flgSiFac = flgSiFac;
            }

            public String getNamGuardian() {
                return namGuardian;
            }

            public void setNamGuardian(String namGuardian) {
                this.namGuardian = namGuardian;
            }

            public String getBranchCountry() {
                return branchCountry;
            }

            public void setBranchCountry(String branchCountry) {
                this.branchCountry = branchCountry;
            }

            public Integer getBranchCode() {
                return branchCode;
            }

            public void setBranchCode(Integer branchCode) {
                this.branchCode = branchCode;
            }

            public String getFlgOdFac() {
                return flgOdFac;
            }

            public void setFlgOdFac(String flgOdFac) {
                this.flgOdFac = flgOdFac;
            }

            public String getCustomerShortName() {
                return customerShortName;
            }

            public void setCustomerShortName(String customerShortName) {
                this.customerShortName = customerShortName;
            }

            public String getBranchCity() {
                return branchCity;
            }

            public void setBranchCity(String branchCity) {
                this.branchCity = branchCity;
            }

            public Integer getProdChqThresholdDayLim() {
                return prodChqThresholdDayLim;
            }

            public void setProdChqThresholdDayLim(Integer prodChqThresholdDayLim) {
                this.prodChqThresholdDayLim = prodChqThresholdDayLim;
            }

            public String getCodTypProd() {
                return codTypProd;
            }

            public void setCodTypProd(String codTypProd) {
                this.codTypProd = codTypProd;
            }

            public Integer getCodProdType() {
                return codProdType;
            }

            public void setCodProdType(Integer codProdType) {
                this.codProdType = codProdType;
            }

            public Integer getProdCtrChqThresholdLim() {
                return prodCtrChqThresholdLim;
            }

            public void setProdCtrChqThresholdLim(Integer prodCtrChqThresholdLim) {
                this.prodCtrChqThresholdLim = prodCtrChqThresholdLim;
            }

            public String getBranchState() {
                return branchState;
            }

            public void setBranchState(String branchState) {
                this.branchState = branchState;
            }

            public Integer getCodRel() {
                return codRel;
            }

            public void setCodRel(Integer codRel) {
                this.codRel = codRel;
            }

            public String getMicrCode() {
                return micrCode;
            }

            public void setMicrCode(String micrCode) {
                this.micrCode = micrCode;
            }

            public String getActivationDate() {
                return activationDate;
            }

            public void setActivationDate(String activationDate) {
                this.activationDate = activationDate;
            }

            public String getTxtAcctMemo() {
                return txtAcctMemo;
            }

            public void setTxtAcctMemo(String txtAcctMemo) {
                this.txtAcctMemo = txtAcctMemo;
            }

            public Integer getFrqChqThreshholdLimit() {
                return frqChqThreshholdLimit;
            }

            public void setFrqChqThreshholdLimit(Integer frqChqThreshholdLimit) {
                this.frqChqThreshholdLimit = frqChqThreshholdLimit;
            }

            public String getProdTypeDesc() {
                return prodTypeDesc;
            }

            public void setProdTypeDesc(String prodTypeDesc) {
                this.prodTypeDesc = prodTypeDesc;
            }

            public String getAccountTitle() {
                return accountTitle;
            }

            public void setAccountTitle(String accountTitle) {
                this.accountTitle = accountTitle;
            }

            public String getTxtMsgAdv() {
                return txtMsgAdv;
            }

            public void setTxtMsgAdv(String txtMsgAdv) {
                this.txtMsgAdv = txtMsgAdv;
            }

            public Integer getFlgAtm() {
                return flgAtm;
            }

            public void setFlgAtm(Integer flgAtm) {
                this.flgAtm = flgAtm;
            }

            public String getFlgNet() {
                return flgNet;
            }

            public void setFlgNet(String flgNet) {
                this.flgNet = flgNet;
            }

            public String getAccountStatusDescription() {
                return accountStatusDescription;
            }

            public void setAccountStatusDescription(String accountStatusDescription) {
                this.accountStatusDescription = accountStatusDescription;
            }

            public BigDecimal getAvailableBalance() {
                return availableBalance;
            }

            public void setAvailableBalance(BigDecimal availableBalance) {
                this.availableBalance = availableBalance;
            }

            public String getCodAcctNoExt() {
                return codAcctNoExt;
            }

            public void setCodAcctNoExt(String codAcctNoExt) {
                this.codAcctNoExt = codAcctNoExt;
            }

            public List<JointHolderDTO> getJointHolders() {
                return jointHolders;
            }

            public void setJointHolders(List<JointHolderDTO> jointHolders) {
                this.jointHolders = jointHolders;
            }

            @Override
            public String toString() {
                return "CustomerAccountDetailsDTO{" +
                        "prodFrqChqThresholdLim=" + prodFrqChqThresholdLim +
                        ", flagATM='" + flagATM + '\'' +
                        ", flgPos='" + flgPos + '\'' +
                        ", flagSWPOut='" + flagSWPOut + '\'' +
                        ", productcodProdType='" + productcodProdType + '\'' +
                        ", ctrThreshholdDayLimit=" + ctrThreshholdDayLimit +
                        ", productName='" + productName + '\'' +
                        ", flgNomineeNameDisplay='" + flgNomineeNameDisplay + '\'' +
                        ", nomineeAge=" + nomineeAge +
                        ", amtAqb4=" + amtAqb4 +
                        ", flgTbs='" + flgTbs + '\'' +
                        ", amtAqb2=" + amtAqb2 +
                        ", amtAqb3=" + amtAqb3 +
                        ", accountOperatingInstructions='" + accountOperatingInstructions + '\'' +
                        ", amtAqb1=" + amtAqb1 +
                        ", branchPinCode='" + branchPinCode + '\'' +
                        ", amtAmb2=" + amtAmb2 +
                        ", amtAmb3=" + amtAmb3 +
                        ", amtAmb1=" + amtAmb1 +
                        ", flgChqGrntyFac='" + flgChqGrntyFac + '\'' +
                        ", flgAtmFac=" + flgAtmFac +
                        ", ctrChqThreshholdLimit=" + ctrChqThreshholdLimit +
                        ", balBook=" + balBook +
                        ", flgSiFac='" + flgSiFac + '\'' +
                        ", namGuardian='" + namGuardian + '\'' +
                        ", branchCountry='" + branchCountry + '\'' +
                        ", branchCode=" + branchCode +
                        ", flgOdFac='" + flgOdFac + '\'' +
                        ", customerShortName='" + customerShortName + '\'' +
                        ", branchCity='" + branchCity + '\'' +
                        ", prodChqThresholdDayLim=" + prodChqThresholdDayLim +
                        ", codTypProd='" + codTypProd + '\'' +
                        ", codProdType=" + codProdType +
                        ", prodCtrChqThresholdLim=" + prodCtrChqThresholdLim +
                        ", branchState='" + branchState + '\'' +
                        ", codRel=" + codRel +
                        ", micrCode='" + micrCode + '\'' +
                        ", activationDate='" + activationDate + '\'' +
                        ", txtAcctMemo='" + txtAcctMemo + '\'' +
                        ", frqChqThreshholdLimit=" + frqChqThreshholdLimit +
                        ", prodTypeDesc='" + prodTypeDesc + '\'' +
                        ", accountTitle='" + accountTitle + '\'' +
                        ", txtMsgAdv='" + txtMsgAdv + '\'' +
                        ", flgAtm=" + flgAtm +
                        ", flgNet='" + flgNet + '\'' +
                        ", accountStatusDescription='" + accountStatusDescription + '\'' +
                        ", availableBalance=" + availableBalance +
                        ", codAcctNoExt='" + codAcctNoExt + '\'' +
                        ", jointHolders=" + jointHolders +
                        '}';
            }
        }

    // Inner class for JointHolderDTO
    public static class JointHolderDTO implements Serializable {

        @JsonProperty("customerFullName")
        private String customerFullName;

        @JsonProperty("customerPANInoperativeStatus")
        private String customerPANInoperativeStatus;

        @JsonProperty("customerEmail")
        private String customerEmail;

        @JsonProperty("customerResTelNo")
        private String customerResTelNo;

        @JsonProperty("customerId")
        private String customerId;

        @JsonProperty("ctrSrlNo")
        private String ctrSrlNo;

        @JsonProperty("customerRelationship")
        private String customerRelationship;

        @JsonProperty("customerMobile")
        private String customerMobile;

        @JsonProperty("customerPAN")
        private String customerPAN;

        // Getters and Setters


        public String getCustomerFullName() {
            return customerFullName;
        }

        public void setCustomerFullName(String customerFullName) {
            this.customerFullName = customerFullName;
        }

        public String getCustomerPANInoperativeStatus() {
            return customerPANInoperativeStatus;
        }

        public void setCustomerPANInoperativeStatus(String customerPANInoperativeStatus) {
            this.customerPANInoperativeStatus = customerPANInoperativeStatus;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getCustomerResTelNo() {
            return customerResTelNo;
        }

        public void setCustomerResTelNo(String customerResTelNo) {
            this.customerResTelNo = customerResTelNo;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCtrSrlNo() {
            return ctrSrlNo;
        }

        public void setCtrSrlNo(String ctrSrlNo) {
            this.ctrSrlNo = ctrSrlNo;
        }

        public String getCustomerRelationship() {
            return customerRelationship;
        }

        public void setCustomerRelationship(String customerRelationship) {
            this.customerRelationship = customerRelationship;
        }

        public String getCustomerMobile() {
            return customerMobile;
        }

        public void setCustomerMobile(String customerMobile) {
            this.customerMobile = customerMobile;
        }

        public String getCustomerPAN() {
            return customerPAN;
        }

        public void setCustomerPAN(String customerPAN) {
            this.customerPAN = customerPAN;
        }

        @Override
        public String toString() {
            return "JointHolderDTO{" +
                    "customerFullName='" + customerFullName + '\'' +
                    ", customerPANInoperativeStatus='" + customerPANInoperativeStatus + '\'' +
                    ", customerEmail='" + customerEmail + '\'' +
                    ", customerResTelNo='" + customerResTelNo + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", ctrSrlNo='" + ctrSrlNo + '\'' +
                    ", customerRelationship='" + customerRelationship + '\'' +
                    ", customerMobile='" + customerMobile + '\'' +
                    ", customerPAN='" + customerPAN + '\'' +
                    '}';
        }
    }


}
    
