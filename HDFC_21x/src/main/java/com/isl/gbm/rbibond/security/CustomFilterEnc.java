package com.isl.gbm.rbibond.security;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.JSONUtil;
import com.isl.gbm.user.management.util.UserAccountUtils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CustomFilterEnc implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        if(((HttpServletRequest) request).getMethod().equalsIgnoreCase("POST") || ((HttpServletRequest) request).getMethod().equalsIgnoreCase("PUT") || ((HttpServletRequest) request).getMethod().equalsIgnoreCase("DELETE")){
            this.runEncValidation(request, response, chain);
        }else{
            chain.doFilter(request, response);
        }

    }
    @Override
    public void destroy() {
        // Cleanup logic if needed
    }


    public void runEncValidation(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            // SKIP specific APIs (Image/File API)
            String uri = httpRequest.getRequestURI();
            String contentType = httpRequest.getContentType();
            if (uri.contains("/fts-hcp-integrated-service")) {
                chain.doFilter(request, response);
                return;
            }
            //  Skip encryption/decryption for multipart/form-data (file uploads)
            if (contentType != null && (contentType.toLowerCase().startsWith("multipart/") || contentType.toLowerCase().contains("multipart/") )) {
                httpResponse.setHeader("X-Multi", "true");
                chain.doFilter(request, response);
                return;
            }
            // Wrap the request to read and cache the body
            CustomHttpServletRequestWrapper wrappedRequest = new CustomHttpServletRequestWrapper(httpRequest);

            // Decrypt the cached body
            String decryptedPayload = UserAccountUtils.decodeAesPassword(wrappedRequest.getBody());

            // Wrap the request again with the decrypted body
            CustomHttpServletRequestWrapper decryptedRequest = new CustomHttpServletRequestWrapper(
                    new HttpServletRequestWrapper(httpRequest) {
                        @Override
                        public ServletInputStream getInputStream() throws IOException {
                            return new ServletInputStream() {
                                private final ByteArrayInputStream byteArrayInputStream =
                                        new ByteArrayInputStream(decryptedPayload.getBytes(StandardCharsets.UTF_8));

                                @Override
                                public boolean isFinished() {
                                    return byteArrayInputStream.available() == 0;
                                }

                                @Override
                                public boolean isReady() {
                                    return true;
                                }

                                @Override
                                public void setReadListener(ReadListener readListener) {
                                    // no-op
                                }

                                @Override
                                public int read() throws IOException {
                                    return byteArrayInputStream.read();
                                }
                            };
                        }

                        @Override
                        public BufferedReader getReader() throws IOException {
                            return new BufferedReader(new InputStreamReader(getInputStream(), StandardCharsets.UTF_8));
                        }
                    }
            );

            // Wrap the response to capture the output
            CustomHttpServletResponseWrapper wrappedResponse = new CustomHttpServletResponseWrapper(httpResponse);

            // Continue the filter chain with wrapped request and response
            chain.doFilter(decryptedRequest, wrappedResponse);

            // Encrypt the response payload
            String responseContent = wrappedResponse.getCaptureAsString();
            String encryptedResponse = this.convertEncFormat(responseContent);

            // Write the encrypted response back to the original response
            httpResponse.setContentLength(encryptedResponse.length());
            httpResponse.getWriter().write(encryptedResponse);
        } catch (Exception e) {
            throw new ServletException("Error processing encryption/decryption", e);
        }


    }

    public String convertEncFormat(String plainResponse){
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDataDTO responseDataDTO =null;
        if(plainResponse != null && plainResponse.trim().startsWith("[")){
            List<Object> list = null;
            try {
                list = objectMapper.readValue(plainResponse, new TypeReference<List<Object>>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("SUCCESS");
            responseDataDTO.setResult(list);
        }else {
            responseDataDTO = (ResponseDataDTO) JSONUtil.convertJsonToJava(plainResponse, ResponseDataDTO.class);
        }
        String responseDataDTOString = JSONUtil.convertJavaToJson(responseDataDTO);
        String result = UserAccountUtils.encodeAesPassword(responseDataDTOString);
        return JSONUtil.convertJavaToJson(result);
    }

}
