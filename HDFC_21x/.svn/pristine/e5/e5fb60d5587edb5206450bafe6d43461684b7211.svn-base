package com.isl.gbm.rbibond.externalservice.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface FileDownloadServiceHCP {

    public ResponseDataDTO downloadFile(String imageName,String filePath);
    public boolean deleteFileByName(String imageName);
    public ResponseEntity<byte[]> fetchFile(String folderName, String fileName);
    public ResponseEntity<Object> getImageAsBase64(String folderName, String fileName);
    public ResponseEntity<Object> getAllImagesAsBase64(String folderName);
    public ResponseEntity<Object> getAllImagesAsBase64(String folderName, Map<String, String> map);
}
