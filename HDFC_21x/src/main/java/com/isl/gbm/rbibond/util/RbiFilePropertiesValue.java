package com.isl.gbm.rbibond.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.nio.file.Paths;

@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sftp.client")
public class RbiFilePropertiesValue {

    private String host;

    private Integer port;

    private String protocol;

    private String username;

    private String password;

    private Integer sessionConnectTimeout;

    private Integer channelConnectedTimeout;

    private String sessionStrictHostKeyChecking;

    public String getSessionStrictHostKeyChecking() {
        return sessionStrictHostKeyChecking;
    }

    public void setSessionStrictHostKeyChecking(String sessionStrictHostKeyChecking) {
        this.sessionStrictHostKeyChecking = sessionStrictHostKeyChecking;
    }

    public Integer getSessionConnectTimeout() {
        return sessionConnectTimeout;
    }

    public void setSessionConnectTimeout(Integer sessionConnectTimeout) {
        this.sessionConnectTimeout = sessionConnectTimeout;
    }

    public Integer getChannelConnectedTimeout() {
        return channelConnectedTimeout;
    }

    public void setChannelConnectedTimeout(Integer channelConnectedTimeout) {
        this.channelConnectedTimeout = channelConnectedTimeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //@Value("${gbm.dirpath.gst.rbi.root}")
    private String rbiRootDirectory;


    //@Value("${gbm.dirpath.gst.gbm.root}")
    private String gbmRootDirectory;

    private String inDirectory;

    private String inDoneDirectory;

    private String ackDirectory;

    private String ackDirectoryDone;

    private String nckDirectory;

    private String nckDirectoryDone;

    private String dnDirectory;

    private String dnDoneDirectory;

    private String caseDirectory;

    private String caseDoneDirectory;

    private String caseAckDirectory;

    private String caseAckDoneDirectory;

    private String caseNckDirectory;

    private String caseNckDoneDirectory;

    private String nonFinResDirectory;

    private String nonFinResDoneDirectory;

    private String nonFinResAckDirectory;

    private String nonFinResAckDoneDirectory;

    private String nonFinResNckDirectory;

    private String nonFinResNckDoneDirectory;

    private String finResDirectory;

    private String finResDoneDirectory;

    private String finResAckDirectory;

    private String finResAckDoneDirectory;

    private String finResNckDirectory;

    private String finResNckDoneDirectory;

    private String moeDnDirectory;

    private String moeDnDoneDirectory;


    //GBM

    private String gbmAckDirectory;

    private String gbmAckDoneDirectory;

    private String inGbmDirectory;

    private String inGbmDoneDirectory;

    private String gbmNckDirectory;

    private String gbmNckDirectoryDone;

    private String gbmDnDirectory;

    private String gbmDnDirectoryDone;

    private String gbmCaseDirectory;

    private String gbmCaseDoneDirectory;

    private String gbmCaseAckDirectory;

    private String gbmCaseAckDoneDirectory;

    private String gbmCaseNckDirectory;

    private String gbmCaseNckDoneDirectory;

    private String gbmNonFinResDirectory;

    private String gbmNonFinResDoneDirectory;

    private String gbmNonFinResAckDirectory;

    private String gbmNonFinResAckDoneDirectory;

    private String gbmNonFinResNckDirectory;

    private String gbmNonFinResNckDoneDirectory;

    private String gbmFinResDirectory;

    private String gbmFinResDoneDirectory;

    private String gbmFinResAckDirectory;

    private String gbmFinResAckDoneDirectory;

    private String gbmFinResNckDirectory;

    private String gbmFinResNckDoneDirectory;

    private String gbmMoeDnDirectory;

    private String gbmMoeDnDoneDirectory;

   /* @PostConstruct
    public void initValues() {
        this.inDirectory = Paths.get(rbiRootDirectory).resolve("IN").toString();
        this.inDoneDirectory = Paths.get(rbiRootDirectory).resolve("IN").resolve("Done").toString();
        this.ackDirectory = Paths.get(rbiRootDirectory).resolve("ACK").toString();
        this.ackDirectoryDone = Paths.get(rbiRootDirectory).resolve("ACK").resolve("Done").toString();
        this.nckDirectory = Paths.get(rbiRootDirectory).resolve("NCK").toString();
        this.nckDirectoryDone = Paths.get(rbiRootDirectory).resolve("NCK").resolve("Done").toString();
        this.dnDirectory = Paths.get(rbiRootDirectory).resolve("DN").toString();
        this.dnDoneDirectory = Paths.get(rbiRootDirectory).resolve("DN").resolve("Done").toString();
        this.caseDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASE").toString();
        this.caseDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASE").resolve("Done").toString();
        this.caseAckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASEACK").toString();
        this.caseAckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASEACK").resolve("Done").toString();
        this.caseNckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASENCK").toString();
        this.caseNckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASENCK").resolve("Done").toString();
        this.nonFinResDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRES").toString();
        this.nonFinResDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRES").resolve("Done").toString();
        this.nonFinResAckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRESACK").toString();
        this.nonFinResAckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRESACK").resolve("Done").toString();
        this.nonFinResNckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRESNCK").toString();
        this.nonFinResNckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRESNCK").resolve("Done").toString();
        this.finResDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRES").toString();
        this.finResDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRES").resolve("Done").toString();
        this.finResAckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRESACK").toString();
        this.finResAckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRESACK").resolve("Done").toString();
        this.finResNckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRESNCK").toString();
        this.finResNckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRESNCK").resolve("Done").toString();
        this.moeDnDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("MOEDN").toString();
        this.moeDnDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("MOEDN").resolve("Done").toString();

        //GBM
        this.inGbmDirectory = Paths.get(gbmRootDirectory).resolve("IN").toString();
        this.inGbmDoneDirectory = Paths.get(gbmRootDirectory).resolve("IN").resolve("Done").toString();
        this.gbmAckDirectory = Paths.get(gbmRootDirectory).resolve("ACK").toString();
        this.gbmAckDoneDirectory = Paths.get(gbmRootDirectory).resolve("ACK").resolve("Done").toString();
        this.gbmNckDirectory = Paths.get(gbmRootDirectory).resolve("NCK").toString();
        this.gbmNckDirectoryDone = Paths.get(gbmRootDirectory).resolve("NCK").resolve("Done").toString();
        this.gbmDnDirectory = Paths.get(gbmRootDirectory).resolve("DN").toString();
        this.gbmDnDirectoryDone = Paths.get(gbmRootDirectory).resolve("DN").resolve("Done").toString();
        this.gbmCaseDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASE").toString();
        this.gbmCaseDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASE").resolve("Done").toString();
        this.gbmCaseAckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASEACK").toString();
        this.gbmCaseAckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASEACK").resolve("Done").toString();
        this.gbmCaseNckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASENCK").toString();
        this.gbmCaseNckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("CASENCK").resolve("Done").toString();
        this.gbmNonFinResDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRES").toString();
        this.gbmNonFinResDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRES").resolve("Done").toString();
        this.gbmNonFinResAckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRESACK").toString();
        this.gbmNonFinResAckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRESACK").resolve("Done").toString();
        this.gbmNonFinResNckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRESNCK").toString();
        this.gbmNonFinResNckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("NFINRESNCK").resolve("Done").toString();
        this.gbmFinResDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRES").toString();
        this.gbmFinResDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRES").resolve("Done").toString();
        this.gbmFinResAckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRESACK").toString();
        this.gbmFinResAckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRESACK").resolve("Done").toString();
        this.gbmFinResNckDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRESNCK").toString();
        this.gbmFinResNckDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("FINRESNCK").resolve("Done").toString();
        this.gbmMoeDnDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("MOEDN").toString();
        this.gbmMoeDnDoneDirectory = Paths.get(gbmRootDirectory).resolve("MOE").resolve("MOEDN").resolve("Done").toString();

    }*/

    //GBM

    public String getGbmRootDirectory() {
        return gbmRootDirectory;
    }
    public String getInGbmDirectory() {
        return inGbmDirectory;
    }
    public String getInGbmDoneDirectory() {return inGbmDoneDirectory; }
    public String getGbmAckDirectory() {
        return gbmAckDirectory;
    }
    public String getGbmNckDirectory() { return gbmNckDirectory; }
    public String getGbmNckDirectoryDone() { return gbmNckDirectoryDone; }
    public String getGbmDnDirectory() { return gbmDnDirectory; }
    public String getGbmDnDirectoryDone() { return gbmDnDirectoryDone; }

    public String getGbmCaseDirectory() {
        return gbmCaseDirectory;
    }

    public String getGbmCaseDoneDirectory() {
        return gbmCaseDoneDirectory;
    }

    public String getGbmCaseAckDirectory() {
        return gbmCaseAckDirectory;
    }

    public String getGbmCaseAckDoneDirectory() {
        return gbmCaseAckDoneDirectory;
    }

    public String getGbmCaseNckDirectory() {
        return gbmCaseNckDirectory;
    }

    public String getGbmCaseNckDoneDirectory() {
        return gbmCaseNckDoneDirectory;
    }

    public String getGbmNonFinResDirectory() {
        return gbmNonFinResDirectory;
    }

    public String getGbmNonFinResDoneDirectory() {
        return gbmNonFinResDoneDirectory;
    }

    public String getGbmNonFinResAckDirectory() {
        return gbmNonFinResAckDirectory;
    }

    public String getGbmNonFinResAckDoneDirectory() {
        return gbmNonFinResAckDoneDirectory;
    }

    public String getGbmNonFinResNckDirectory() {
        return gbmNonFinResNckDirectory;
    }

    public String getGbmNonFinResNckDoneDirectory() {
        return gbmNonFinResNckDoneDirectory;
    }

    public String getGbmFinResDirectory() {
        return gbmFinResDirectory;
    }

    public String getGbmFinResDoneDirectory() {
        return gbmFinResDoneDirectory;
    }

    public String getGbmFinResAckDirectory() {
        return gbmFinResAckDirectory;
    }

    public String getGbmFinResAckDoneDirectory() {
        return gbmFinResAckDoneDirectory;
    }

    public String getGbmFinResNckDirectory() {
        return gbmFinResNckDirectory;
    }

    public String getGbmFinResNckDoneDirectory() {
        return gbmFinResNckDoneDirectory;
    }

    public String getGbmMoeDnDirectory() {
        return gbmMoeDnDirectory;
    }

    public String getGbmMoeDnDoneDirectory() {
        return gbmMoeDnDoneDirectory;
    }

    //RBI

    public String getRbiRootDirectory() {
        return rbiRootDirectory;
    }

    public String getInDirectory() {
        return inDirectory;
    }

    public String getInDoneDirectory() {
        return inDoneDirectory;
    }

    public String getAckDirectory() {
        return ackDirectory;
    }

    public String getAckDirectoryDone() {
        return ackDirectoryDone;
    }

    public String getNckDirectory() {
        return nckDirectory;
    }

    public String getNckDirectoryDone() {
        return nckDirectoryDone;
    }

    public String getDnDirectory() {
        return dnDirectory;
    }

    public String getDnDoneDirectory() {
        return dnDoneDirectory;
    }

    public String getCaseDirectory() {
        return caseDirectory;
    }

    public String getCaseDoneDirectory() {
        return caseDoneDirectory;
    }

    public String getCaseAckDirectory() {
        return caseAckDirectory;
    }

    public String getCaseAckDoneDirectory() {
        return caseAckDoneDirectory;
    }

    public String getCaseNckDirectory() {
        return caseNckDirectory;
    }

    public String getCaseNckDoneDirectory() {
        return caseNckDoneDirectory;
    }

    public String getNonFinResDirectory() {
        return nonFinResDirectory;
    }

    public String getNonFinResDoneDirectory() {
        return nonFinResDoneDirectory;
    }

    public String getNonFinResAckDirectory() {
        return nonFinResAckDirectory;
    }

    public String getNonFinResAckDoneDirectory() {
        return nonFinResAckDoneDirectory;
    }

    public String getNonFinResNckDirectory() {
        return nonFinResNckDirectory;
    }

    public String getNonFinResNckDoneDirectory() {
        return nonFinResNckDoneDirectory;
    }

    public String getFinResDirectory() {
        return finResDirectory;
    }

    public String getFinResDoneDirectory() {
        return finResDoneDirectory;
    }

    public String getFinResAckDirectory() {
        return finResAckDirectory;
    }

    public String getFinResAckDoneDirectory() {
        return finResAckDoneDirectory;
    }

    public String getFinResNckDirectory() {
        return finResNckDirectory;
    }

    public String getFinResNckDoneDirectory() {
        return finResNckDoneDirectory;
    }

    public String getMoeDnDirectory() {
        return moeDnDirectory;
    }

    public String getMoeDnDoneDirectory() {
        return moeDnDoneDirectory;
    }

    public String getGbmAckDoneDirectory() {
        return gbmAckDoneDirectory;
    }

    public void setGbmAckDoneDirectory(String gbmAckDoneDirectory) {
        this.gbmAckDoneDirectory = gbmAckDoneDirectory;
    }
}
