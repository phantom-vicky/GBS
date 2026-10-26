package com.isl.gbm.rbibond.util;

import com.jcraft.jsch.ChannelSftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.List;

@Component
public class SftpUploader {

    private static final Logger LOGGER = LoggerFactory.getLogger(SftpUploader.class);
    private final Sftp sftp; // Assuming this is injected or initialized elsewhere

    public SftpUploader(Sftp sftp) {
        this.sftp = sftp;
    }

    public void uploadFileToSFTP(String remoteDir, String fileName, List<String> data) {
        ChannelSftp channelSftp = null;
        File tempFile = null;

        try {
            // Create SFTP channel
            channelSftp = sftp.createSftp();
            LOGGER.info("SFTP channel established.");
            LOGGER.info("SFTP channel established. Remote directory: {}", remoteDir);

            // Create temporary file
            tempFile = File.createTempFile(fileName, ".txt");
            if (!tempFile.canWrite()) {
                LOGGER.error("No write permission for temp file: {}", tempFile.getAbsolutePath());
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                for (String line : data) {
                    writer.write(line);
                    writer.newLine();
                }
                LOGGER.info("Data written to temp file: {}", tempFile.getAbsolutePath());
            }

            // Check remote directory existence (optional but recommended)
            try {
                channelSftp.cd(remoteDir);
            } catch (Exception e) {
                LOGGER.error("Remote directory does not exist: {}", remoteDir, e);
                return;
            }

            // Upload the file
            String remoteFilePath = remoteDir + "/" + fileName + ".txt";
            channelSftp.put(tempFile.getAbsolutePath(), remoteFilePath);
            LOGGER.info("File uploaded to SFTP at: {}", remoteFilePath);

        } catch (Exception e) {
            LOGGER.error("Failed to upload file to SFTP", e);
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
                LOGGER.info("SFTP channel disconnected.");
            }

            if (tempFile != null && tempFile.exists()) {
                try {
                    Files.delete(tempFile.toPath());
                    LOGGER.info("Temporary file deleted: {}", tempFile.getAbsolutePath());
                } catch (Exception ex) {
                    LOGGER.warn("Failed to delete temp file: {}", tempFile.getAbsolutePath(), ex);
                }
            }
        }
    }
}
