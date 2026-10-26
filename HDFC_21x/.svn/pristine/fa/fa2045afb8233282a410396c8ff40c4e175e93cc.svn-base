package com.isl.gbm.rbibond.externalservice.utility;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;
import java.util.logging.*;

public class ZipUtils {
    private static final Logger logger = Logger.getLogger(ZipUtils.class.getName());

    public static void unzipFile(String zipFilePath, String destDir) throws IOException {
        Path zipPath = Paths.get(zipFilePath);
        Path destinationPath = Paths.get(destDir);

        // Ensure destination directory exists
        if (Files.notExists(destinationPath)) {
            Files.createDirectories(destinationPath);
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path entryPath = destinationPath.resolve(entry.getName());

                // If the entry is a directory, create it, otherwise extract the file
                if (entry.isDirectory()) {
                    Files.createDirectories(entryPath);
                    logger.info("Created directory: " + entryPath);
                } else {
                    // Ensure the parent directories exist for files
                    if (Files.notExists(entryPath.getParent())) {
                        Files.createDirectories(entryPath.getParent());
                    }

                    // Extract the file content
                    try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(entryPath))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipInputStream.read(buffer)) > 0) {
                            bos.write(buffer, 0, bytesRead);
                        }
                    }
                    logger.info("Extracted file: " + entryPath);
                }

                // Close the current zip entry
                zipInputStream.closeEntry();
            }
        } catch (IOException e) {
            logger.severe("Error extracting zip file: " + e.getMessage());
            throw e;  // Re-throw the exception after logging
        }
    }

    public static List<String> unzipFiles(String zipFilePath, String destDir) throws IOException {
        Path zipPath = Paths.get(zipFilePath);
        Path destinationDir = Paths.get(destDir);

        // Ensure destination directory exists
        if (Files.notExists(destinationDir)) {
            Files.createDirectories(destinationDir);
        }

        // Extract the folder name from the zip file name (without extension)
        String folderName = zipPath.getFileName().toString().replaceAll("\\.ZIP$", "");
        Path folderDestination = destinationDir.resolve(folderName);

        // Create the subdirectory for the extracted files
        if (Files.notExists(folderDestination)) {
            Files.createDirectories(folderDestination);
        }

        // List to hold the file names
        List<String> fileNames = new ArrayList<>();

        // Unzip the file into the folder
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                // Add file name to the list
                fileNames.add(entry.getName());

                Path entryPath = folderDestination.resolve(entry.getName());

                // If the entry is a directory, create it, otherwise extract the file
                if (entry.isDirectory()) {
                    Files.createDirectories(entryPath);
                    System.out.println("Created directory: " + entryPath);
                } else {
                    // Ensure the parent directories exist for files
                    if (Files.notExists(entryPath.getParent())) {
                        Files.createDirectories(entryPath.getParent());
                    }

                    // Extract the file content
                    try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(entryPath))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipInputStream.read(buffer)) > 0) {
                            bos.write(buffer, 0, bytesRead);
                        }
                    }
                    System.out.println("Extracted file: " + entryPath);
                }

                // Close the current zip entry
                zipInputStream.closeEntry();
            }
        } catch (IOException e) {
            System.err.println("Error extracting zip file: " + e.getMessage());
            throw e;  // Re-throw the exception after logging
        }

        // Return the list of file names
        return fileNames;
    }

}
