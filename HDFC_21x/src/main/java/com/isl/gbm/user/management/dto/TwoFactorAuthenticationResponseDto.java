/*
 *
 */
package com.isl.gbm.user.management.dto;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.Date;

/**
 * The Class ActiveUserDto.
 */
public class TwoFactorAuthenticationResponseDto {


        public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {

        public Header header;
        public String status_code;
        public String status_msg;
        public String response_datetime;
        public Data data;

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public String getStatus_code() {
            return status_code;
        }

        public void setStatus_code(String status_code) {
            this.status_code = status_code;
        }

        public String getStatus_msg() {
            return status_msg;
        }

        public void setStatus_msg(String status_msg) {
            this.status_msg = status_msg;
        }

        public String getResponse_datetime() {
            return response_datetime;
        }

        public void setResponse_datetime(String response_datetime) {
            this.response_datetime = response_datetime;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public class Data {
            public String auth_flag;

            public String getAuth_flag() {
                return auth_flag;
            }

            public void setAuth_flag(String auth_flag) {
                this.auth_flag = auth_flag;
            }
        }

        public class Header {
            public String msg_code;
            public String source;
            public String channel;
            public String txn_ref_number;
            public String txn_datetime;
            public String ip;
            public String device_id;

            public String getMsg_code() {
                return msg_code;
            }

            public void setMsg_code(String msg_code) {
                this.msg_code = msg_code;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getTxn_ref_number() {
                return txn_ref_number;
            }

            public void setTxn_ref_number(String txn_ref_number) {
                this.txn_ref_number = txn_ref_number;
            }

            public String getTxn_datetime() {
                return txn_datetime;
            }

            public void setTxn_datetime(String txn_datetime) {
                this.txn_datetime = txn_datetime;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }
        }
    }




}
