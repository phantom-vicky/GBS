package com.isl.gbm.rbibond.externalservice.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DirectoryCleaner {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryCleaner.class);

    @Value("${file.download.directory.hcp}")
    private String baseDirectory;

    @Value("${scheduled.job.cron.hcp.directory.cleaner}")
    private String cronExpression;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String SAFE_BASE_DIRECTORY = "/GiftCity";

    @Scheduled(cron = "${scheduled.job.cron.hcp.directory.cleaner}")
    public void clearDirectory() {
        if (baseDirectory == null || baseDirectory.trim().isEmpty()) {
            String errorMessage = "Base directory is not configured.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        // Sanitize the file name to prevent directory traversal attacks
       // String sanitizedPathName = sanitizeServerPath(baseDirectory);

        Path directoryPath = Paths.get(baseDirectory);
        LocalDate currentDate = LocalDate.now();
        String currentDateFolderName = currentDate.format(DATE_FORMATTER);

        // Ensure base directory exists
        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
            try {
                // Construct the path for today's folder (e.g., "2025-01-21")
                Path dateFolderPath = directoryPath.resolve(currentDateFolderName);

                // Check if today's folder exists
                if (Files.exists(dateFolderPath) && Files.isDirectory(dateFolderPath)) {
                    // Delete all files and subdirectories within today's folder
                    Files.walkFileTree(dateFolderPath, new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            // Delete each file
                            Files.delete(file);
                            logger.info("Deleted file: {}", file.toString());
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                            // Delete each directory after its contents are deleted
                            Files.delete(dir);
                            logger.info("Deleted directory: {}", dir.toString());
                            return FileVisitResult.CONTINUE;
                        }
                    });
                    logger.info("All files and subdirectories in {} have been deleted.", dateFolderPath);
                } else {
                    logger.warn("Today's folder ({}) does not exist or is not a directory.", dateFolderPath);
                }
            } catch (IOException e) {
                logger.error("Error while clearing directory: {}", baseDirectory, e);
            }
        } else {
            logger.warn("The specified base directory does not exist or is not a directory: {}", baseDirectory);
        }
    }

    /**
     * Sanitizes the file name to prevent security risks like directory traversal.
     *
     * @param inputPath The file name to sanitize
     * @return A sanitized file name
     */
    public static String sanitizeServerPath(String inputPath) throws IllegalArgumentException {
        // Ensure the path does not contain directory traversal or special characters
        if (inputPath.contains("..") || inputPath.contains("\\") || inputPath.contains(":")) {
            logger.error("Potential directory traversal detected in path: {}", inputPath);
            throw new IllegalArgumentException("Invalid path.");
        }

        // Convert the path to a Path object for further validation
        Path path = Paths.get(inputPath);

        // Normalize the path to remove redundant elements (e.g., "./", "/foo/..")
        Path normalizedPath = path.normalize();

        // Ensure that the path starts within the designated safe base directory
        Path baseDirPath = Paths.get(SAFE_BASE_DIRECTORY);
        if (!normalizedPath.startsWith(baseDirPath)) {
            logger.error("Path {} tries to access outside of the allowed base directory.", inputPath);
            throw new IllegalArgumentException("Path is outside of the allowed base directory.");
        }

        return normalizedPath.toString();
    }

}

