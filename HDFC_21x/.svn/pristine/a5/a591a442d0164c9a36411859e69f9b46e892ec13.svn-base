package com.isl.gbm.rbibond.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component(value = "GstSftp")
public class Sftp {

    private static final Logger log = LoggerFactory.getLogger(Sftp.class);

    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";

    @Autowired
    RbiFilePropertiesValue rbiFilePropertiesValue;
    @Value("${profile.type}")
    private String stubProcess;
    @Value("${private.key.path.hdfc}")
    private String key;


    public ChannelSftp createSftp() throws Exception {
        ChannelSftp channel;
        try {
            JSch jsch = new JSch();
           // log.info("Try to connect sftp[" + rbiFilePropertiesValue.getUsername() + "@" + rbiFilePropertiesValue.getHost() + "], use password[" + rbiFilePropertiesValue.getPassword() + "]");
            // Add the private key
            if ("UAT".equalsIgnoreCase(stubProcess) || "PROD".equalsIgnoreCase(stubProcess)) {
                jsch.addIdentity(key);
            }
            Session session = createSession(jsch, rbiFilePropertiesValue.getHost(), rbiFilePropertiesValue.getUsername(), rbiFilePropertiesValue.getPort());
            session.setPassword(rbiFilePropertiesValue.getPassword());
            session.connect(rbiFilePropertiesValue.getSessionConnectTimeout());
            log.info("Session connected to {}.", rbiFilePropertiesValue.getHost());
            channel = (ChannelSftp) session.openChannel(rbiFilePropertiesValue.getProtocol());
            channel.connect(rbiFilePropertiesValue.getChannelConnectedTimeout());
            log.info("Channel created to {}.", rbiFilePropertiesValue.getHost());
        } catch (JSchException e) {
            log.error("JSchException occurred: {}", e.getMessage(), e);
            throw new Exception("Failed to establish SFTP connection", e);
        } catch (Exception e) {
            log.error("An unexpected error occurred: {}", e.getMessage(), e);
            throw new Exception("An unexpected error occurred while creating SFTP channel", e);
        }
        log.info("Scheduler connect at {}.", LocalDateTime.now());
        return channel;
    }


    public Session createSession(JSch jsch, String host, String username, Integer port) throws Exception {
        Session session = null;

        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }

        if (session == null) {
            throw new Exception(host + " session is null");
        }

        session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, rbiFilePropertiesValue.getSessionStrictHostKeyChecking());
        return session;
    }

    public void disconnect(ChannelSftp sftp) {
        try {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                } else if (sftp.isClosed()) {
                    log.info("sftp is closed already");
                }
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }



}
