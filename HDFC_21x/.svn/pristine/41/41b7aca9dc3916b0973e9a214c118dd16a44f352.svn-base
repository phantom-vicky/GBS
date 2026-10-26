package com.isl.gbm.rbibond.externalservice.service.Impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.externalservice.customexception.ApiRequestException;
import com.isl.gbm.rbibond.externalservice.dto.*;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.rbibond.externalservice.utility.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jakarta.xml.soap.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExternalServiceImpl implements ExternalService {

    private final RestTemplate restTemplate;
    private final Gson gson;


    @Value("${external.service.accountValidation.url}")
    private String externalServiceUrl;

    // Inject RestTemplate (can be done via constructor or @Autowired)
    @Autowired
    public ExternalServiceImpl(RestTemplate restTemplate, Gson gson) {
        this.restTemplate = restTemplate;
        this.gson = gson;
    }
    private static final Logger logger = LoggerFactory.getLogger(ExternalServiceImpl.class);

    private static final int CONNECTION_TIMEOUT = 60000; // 60 seconds
    private static final int READ_TIMEOUT = 60000; // 60 seconds
    private static final String ACCOUNT_VALIDATION_ERROR = "No valid customers found for the combination of inputs provided";
    private static final String panReturnCode="1";
    private static final String defaultValueStatus="N";
    private static final String SUCCESS_SCENARIO = "Success scenario case for PAN validation";
    private static final String FAILURE_SCENARIO = "Failure scenario case for PAN validation";

    private static AtomicInteger sequence = new AtomicInteger(1);

    @Value("${pan_val_service_url}")
    private String serviceURL;
    @Value("${pan_nsdl_user_name}")
    private String nsdlUsername;
    @Value("${pan_val_consumer_name}")
    private String consumerName;
    @Value("${pan_val_cost_no}")
    private String costNo;
    @Value("${pan_val_filler}")
    private String filler;

    @Override
    public ResponseDataDTO panValidation(PanDetailsDTO panDetailsDTO) {
        logger.info("Pan Validation Request: PAN={}, Name={}, DOB={}",maskPan(panDetailsDTO.getPan()), panDetailsDTO.getName(), panDetailsDTO.getDob());
        //Inputs validation for the NULL case! from the mandatory params.
        if (panDetailsDTO.getName() == null || panDetailsDTO.getDob() == null) {
            return checkForNullPanOrDobReturnResponse(panDetailsDTO);
        }
        boolean isValid =PanInputValidations.validatePanValidationRequest(panDetailsDTO);
        if (isValid) {
            logger.info("Inputs validation are successful.");
        } else {
            logger.error("Inputs validation failed. Please check the input parameters.");
            throw new ApiRequestException("Inputs validation failed. Please check the mandatory parameters for pan validation.");
        }
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String outputXML;
        PanValResponseDTO panResponse = new PanValResponseDTO();

        int responseCode = 0;
        try {
            String panRequest = createSOAPRequest(panDetailsDTO);
            logger.info("Request sent to URL: {}", serviceURL);
            HttpURLConnection conn = createConnection(serviceURL);
            sendRequest(conn, panRequest);

            responseCode = conn.getResponseCode();
            outputXML = readResponse(conn, responseCode);
            XmlFormatter.prettyFormat(outputXML);
            panResponse = handleSoapResponse(outputXML);
            if (panResponse.getReturnCode().equalsIgnoreCase(STATUS_CODE)) {
                responseDataDTO.setMessage(SUCCESS_MESSAGE);
            } else {
                responseDataDTO.setMessage(panResponse.getReturnMessage());
            }
        } catch (SocketTimeoutException e) {
            logger.error("SocketTimeoutException error: {}", e.getMessage());
            responseDataDTO.setMessage("Error in establishing connection");
        } catch (IOException e) {
            logger.error("IOException error: {}", e.getMessage());
            responseDataDTO.setMessage("Error in establishing connection");
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage());
            responseDataDTO.setMessage("An unexpected error occurred");
        }
        responseDataDTO.setResult(panResponse);
        responseDataDTO.setStatusCode(String.valueOf(responseCode));
        return responseDataDTO;
    }
    private static String maskPan(String pan) {
        if (pan == null || pan.length() != 10) {
            return pan;
        }
        // Keep first 2 and last 3 visible
        return pan.substring(0, 2) + "xxxx" + pan.substring(6);
    }
    public static final String FAILURE_STATUS = "N";
    public static final String FAILURE_STATUS_FAIL = "Failure";
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static final String STATUS_CODE = "1";
    public static final String STATUS_DESC = "Success";

    private ResponseDataDTO checkForNullPanOrDobReturnResponse(PanDetailsDTO panDetailsDTO) {
        // Check if either name or dob is null
        if (panDetailsDTO.getName() == null || panDetailsDTO.getDob() == null) {
            // Logging the scenario where name or dob is null
            //logger.info("PAN: {} - Name or DOB is null. Returning hardcoded response.", panDetailsDTO.getPan());

            // Creating a response object
            ResponseDataDTO response = new ResponseDataDTO();
            PanValResponseDTO panValResponse = new PanValResponseDTO();
            // Set static data using constants for the response
            panValResponse.setPan(panDetailsDTO.getPan());  // Include the PAN value from input
            panValResponse.setPanStatus(FAILURE_STATUS);    // Failure status
            panValResponse.setDob(FAILURE_STATUS);           // Failure status for DOB
            panValResponse.setName(FAILURE_STATUS);          // Failure status for Name
            panValResponse.setReturnMessage(STATUS_DESC);
            panValResponse.setReturnCode(STATUS_CODE);
            panValResponse.setReturnDescription(FAILURE_STATUS_FAIL);
            // Set the response
            response.setResult(panValResponse);
            response.setMessage(SUCCESS_MESSAGE); // Static success message
            return response;
            // Return the response with hardcoded details
        }
        // If PAN and DOB are not null, return null (or handle accordingly)
        return null;
    }

    private HttpURLConnection createConnection(String serviceURL) throws IOException {
        URL endpoint = new URL(serviceURL);
        HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
        conn.setConnectTimeout(CONNECTION_TIMEOUT);
        conn.setReadTimeout(READ_TIMEOUT);
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        return conn;
    }

    private void sendRequest(HttpURLConnection conn, String panRequest) throws IOException {
        try (OutputStream os = conn.getOutputStream()) {
            os.write(panRequest.getBytes());
            os.flush();
        }
    }

    private String readResponse(HttpURLConnection conn, int responseCode) throws IOException {
        if (responseCode != HttpURLConnection.HTTP_OK) {
            logger.error("Failed with HTTP error code: {}", responseCode);
            throw new IOException("Server returned non-OK status: " + responseCode);
        }
        StringBuilder outputXML = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String response;
            while ((response = br.readLine()) != null) {
                outputXML.append(response);
            }
        }
        String maskedXml = maskPanInXml(outputXML.toString());

        logger.info("Response received: {}", maskedXml);
        return outputXML.toString();
    }
    private static String maskPanInXml(String xml) {
        if (xml == null || xml.isEmpty()) {
            return xml;
        }

        Pattern panPattern = Pattern.compile("\\b[A-Z]{5}[0-9]{4}[A-Z]\\b");
        Matcher matcher = panPattern.matcher(xml);
        StringBuffer maskedXml = new StringBuffer();

        while (matcher.find()) {
            String pan = matcher.group();
            String maskedPan = maskPan(pan);
            matcher.appendReplacement(maskedXml, maskedPan);
        }
        matcher.appendTail(maskedXml);

        return maskedXml.toString();
    }
    private String createSOAPRequest(PanDetailsDTO panDetailsDTO) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        return String.format("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pan=\"panval.isd.xsd.hdfcbank.com\">" +
                        "<soapenv:Header/>" +
                        "<soapenv:Body>" +
                        "<pan:PANValServiceRequest>" +
                        "<Consumer_Name>%s</Consumer_Name>" +
                        "<NSDL_User_Name>%s</NSDL_User_Name>" +
                        "<PAN_Details>" +
                        "<PAN>%s</PAN>" +
                        "<Name>%s</Name>" +
                        "<DOB>%s</DOB>" +
                        "</PAN_Details>" +
                        "<Req_Date_Time>%s</Req_Date_Time>" +
                        "<Req_No>%s</Req_No>" +
                        "<Cost_Center_No>%s</Cost_Center_No>" +
                        "<Filler1>%s</Filler1>" +
                        "</pan:PANValServiceRequest>" +
                        "</soapenv:Body>" +
                        "</soapenv:Envelope>",
                consumerName, nsdlUsername, panDetailsDTO.getPan(), panDetailsDTO.getName(), panDetailsDTO.getDob(),
                now.format(formatter), generateTransactionId(nsdlUsername, consumerName), costNo, filler);
    }

    private PanValResponseDTO handleSoapResponse(String xmlResponse) {
        PanValResponseDTO panValResponseDTO = new PanValResponseDTO();
        try {
            // Step 1: Convert XML response string to InputStream
            InputStream inputStream = new ByteArrayInputStream(xmlResponse.getBytes());

            // Step 2: Create SOAPMessage from InputStream
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage(null, inputStream);

            // Step 3: Extract SOAPBody from SOAPMessage
            SOAPBody soapBody = soapMessage.getSOAPBody();

            // Step 4: Get the PANValServiceResponse element from the SOAPBody
            NodeList responseNodeList = soapBody.getElementsByTagName("a:PANValServiceResponse");

            if (responseNodeList.getLength() > 0) {
                // The first node in the list should be the desired response
                Node responseNode = responseNodeList.item(0);
                Element responseElement = null;
                if (responseNode instanceof Element) {
                    responseElement = (Element) responseNode;
                }
                populateResponseDTO(panValResponseDTO, responseElement);
                logger.info("Pan Validation Retrigger Response: " +
                                "PAN={}, NameStatus={}, PANStatus={}, DOBStatus={}, " +
                                "PANStatusDesc={}, PANNameDesc={}, FatherName={}, PANDobDesc={}, " +
                                "ReturnMsg={}, ReturnCode={}, ReturnDesc={}, SeedingStatus={}, SeedingStatusDesc={}",
                        maskPan(panValResponseDTO.getPan()),
                        panValResponseDTO.getName(),
                        panValResponseDTO.getPanStatus(),
                        panValResponseDTO.getDob(),
                        panValResponseDTO.getPanStatusDescription(),
                        panValResponseDTO.getPanNameDescription(),
                        panValResponseDTO.getFatherName(),
                        panValResponseDTO.getPanDobDescription(),
                        panValResponseDTO.getReturnMessage(),
                        panValResponseDTO.getReturnCode(),
                        panValResponseDTO.getReturnDescription(),
                        panValResponseDTO.getSeedingStatus(),
                        panValResponseDTO.getSeedingStatusDescription()
                );
            } else {
                throw new RuntimeException("No PANValServiceResponse found in SOAP body");
            }
        } catch (Exception e) {
            logger.info("SOAP processing error occurred while handling response: {}", e.getMessage(), e);
            panValResponseDTO.setName("N");
            panValResponseDTO.setPanStatus("N");
            panValResponseDTO.setDob("N");
            panValResponseDTO.setReturnCode("0");
            return panValResponseDTO;
        }
        return panValResponseDTO;
    }
    private void populateResponseDTO(PanValResponseDTO panValResponseDTO, Element responseElement) {
        panValResponseDTO.setReturnCode(getElementValue(responseElement, SOAPResponseKeys.RETURN_CODE));
        panValResponseDTO.setReturnDescription(getElementValue(responseElement, SOAPResponseKeys.RETURN_DESCRIPTION));
        panValResponseDTO.setResponseTime(getElementValue(responseElement, SOAPResponseKeys.RESPONSE_TIME));
        panValResponseDTO.setReqNo(getElementValue(responseElement, SOAPResponseKeys.REQ_NO));
        /*
        * commenting for set default values;
        panValResponseDTO.setName(getElementValue(responseElement, SOAPResponseKeys.NAME));
        panValResponseDTO.setPanStatus(getElementValue(responseElement, SOAPResponseKeys.PAN_STATUS));
        panValResponseDTO.setDob(getElementValue(responseElement, SOAPResponseKeys.DOB));
         */
        panValResponseDTO.setName(getElementValueWithDefault(responseElement, SOAPResponseKeys.NAME, defaultValueStatus));
        panValResponseDTO.setPanStatus(getElementValueWithDefault(responseElement, SOAPResponseKeys.PAN_STATUS, defaultValueStatus));
        panValResponseDTO.setDob(getElementValueWithDefault(responseElement, SOAPResponseKeys.DOB, defaultValueStatus));
        panValResponseDTO.setFatherName(getElementValue(responseElement, SOAPResponseKeys.FATHER_NAME));
        panValResponseDTO.setPan(getElementValue(responseElement, SOAPResponseKeys.PAN));
        panValResponseDTO.setSeedingStatus(getElementValue(responseElement, SOAPResponseKeys.SEEDING_STATUS));
        if(panReturnCode.equalsIgnoreCase(panValResponseDTO.getReturnCode())){
            logger.info(SUCCESS_SCENARIO);
            panValResponseDTO.setPanDobDescription(panValResponseDTO.getDob().equalsIgnoreCase("Y") ? "Matched with Database" : "Not matched with Database");
            panValResponseDTO.setSeedingStatusDescription(getSeedingStatus(panValResponseDTO.getSeedingStatus())); // Populate description here
            panValResponseDTO.setPanStatusDescription(PanStatus.getMessage((panValResponseDTO.getPanStatus()))); // Populate description here
        }else {
            logger.info(FAILURE_SCENARIO);
        }
        String returnResponse = getElementValue(responseElement, SOAPResponseKeys.RETURN_CODE);
        int returnCode = -1;
        try {
            // Parse the returnResponse string into an integer
            returnCode = Integer.parseInt(Objects.requireNonNull(returnResponse));
            panValResponseDTO.setReturnMessage(ErrorHandler.getMessageByCode(returnCode));
        } catch (NumberFormatException e) {
            logger.error("Invalid Return Code format: {}", returnResponse);
        }
    }


    // Helper method to extract text content of a specific element by tag name
    private static String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }

    // Helper method to extract text content of a specific element by tag name and return a default value if null or empty
    private static String getElementValueWithDefault(Element parentElement, String tagName, String defaultValue) {
        String value = getElementValue(parentElement, tagName);
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }


    private static String generateTransactionId(String nsdlUserName, String consumerName) {
        //LocalDate currentDate = LocalDate.now();
        LocalDateTime currentDate = LocalDateTime.now();
        //String julianDate = currentDate.format(DateTimeFormatter.ofPattern("yyDDD"));
        String julianDate = currentDate.format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
        long timeComponent = System.currentTimeMillis() % 1000000;
        String uniqueTimeComponent = String.format("%06d", timeComponent);
        String sequentialNumber = String.format("%06d", sequence.getAndIncrement());
        logger.info("Running Sequence: {}:{}_{}_{}",nsdlUserName,consumerName,julianDate,sequentialNumber);
        return String.format("%s:%s_%s_%s", nsdlUserName, consumerName, julianDate, sequentialNumber);
    }

    public static String getSeedingStatus(String flag) {
        if (flag == null) {
            return "Unknown flag"; // Handle null input gracefully
        }
        switch (flag) {
            case "Y":
                return "Operative PAN";
            case "R":
                return "Inoperative PAN";
            case "NA":
                return "For all non-Individual PANs";
            default:
                return "Unknown flag";
        }
    }


    @Override
    public ResponseDataDTO accountValidationFTS(AccountValidationRequestDTO requestDTO) {
        ResponseDataDTO responseData = new ResponseDataDTO();

        // Validate request
        validateRequest(requestDTO, responseData);
        if (responseData.getStatusCode() != null) {
            return responseData;
        }
        //constructing request for account validation.
        constructFATCARequest(requestDTO);
        String externalReferenceNo =requestDTO.getSessionContext().getExternalReferenceNo();
        String jsonRequestBody = gson.toJson(requestDTO);
        logger.info("Sending account validation request to external service. Request body: {}, External Reference Number: {}", jsonRequestBody, externalReferenceNo);
        HttpEntity<AccountValidationRequestDTO> entity = new HttpEntity<>(requestDTO, createHttpHeaders());
            ResponseEntity<String> responseEntity;
            try {
                responseEntity = makeExternalServiceCall(entity);
            } catch (Exception e) {
                logger.error("Service call failed {}:", e.getMessage());
                return createFailureResponse(responseData);
            }
            return processResponse(responseEntity, responseData, requestDTO);
    }


    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
    private void validateRequest(AccountValidationRequestDTO requestDTO, ResponseDataDTO responseData) {
        if (requestDTO == null || requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO() == null ||
                requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().getAccountNumber() == null ||
                requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().getAccountNumber().isEmpty()) {
            logger.error("Invalid input: requestDTO or accountNumber is null/empty");
            responseData.setMessage("Invalid request: Account details are missing");
            responseData.setStatusCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    private ResponseEntity<String> makeExternalServiceCall(HttpEntity<AccountValidationRequestDTO> entity) {
        logger.info("Request URI: {}", externalServiceUrl);
        return restTemplate.exchange(externalServiceUrl, HttpMethod.POST, entity, String.class);
    }

    private ResponseDataDTO processResponse(ResponseEntity<String> responseEntity, ResponseDataDTO responseData, AccountValidationRequestDTO requestDTO) {
        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            try {
                logger.info("Response entity: {}", responseEntity);
                logger.info("Response body: {}", responseEntity.getBody());
                AccountValidationResponceDTO externalResponse = gson.fromJson(responseEntity.getBody(), AccountValidationResponceDTO.class);
                handleResponse(externalResponse, responseData, requestDTO);
            } catch (JsonSyntaxException e) {
                logger.error("Failed to parse response from external service: {}", e.getMessage(), e);
                responseData.setMessage("Error parsing external service response");
                responseData.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            }
        } else {
            logger.warn("External service returned non-successful status: {} or empty response", responseEntity.getStatusCode());
            responseData.setMessage("External service error: Unable to validate account");
            responseData.setStatusCode(String.valueOf(responseEntity.getStatusCodeValue()));
        }
        return responseData;
    }

    private void handleResponse(AccountValidationResponceDTO externalResponse, ResponseDataDTO responseData, AccountValidationRequestDTO requestDTO) {
        String replyText = externalResponse.getStatus().getReplyText();
        if ("0".equalsIgnoreCase(replyText)) {
            responseData.setMessage("Account validation successful");
            responseData.setStatusCode(String.valueOf(HttpStatus.OK.value()));
            responseData.setResult(externalResponse);
            logger.info("Account validation successful for account: {}, External Reference Number: {}",
                    requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().getAccountNumber(),
                    requestDTO.getSessionContext().getExternalReferenceNo());
        } else if (ACCOUNT_VALIDATION_ERROR.equalsIgnoreCase(replyText)) {
            responseData.setMessage("Account validation failed");
            responseData.setStatusCode(String.valueOf(HttpStatus.OK.value()));
            responseData.setResult(externalResponse);
        } else {
            responseData.setMessage("Invalid Response");
            responseData.setStatusCode(String.valueOf(HttpStatus.OK.value()));
            responseData.setResult(externalResponse);
            logger.info("Account validation response for account: {} Status: {}",
                    requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().getAccountNumber(), replyText);
        }
    }

    private ResponseDataDTO createFailureResponse(ResponseDataDTO responseData) {
        responseData.setMessage("Failed to validate account due to service error");
        responseData.setStatusCode(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
        return responseData;
    }

    public static final String isAMLDetailsRequired = "Y";
    public static final String isFATCADetailsRequired = "Y";
    public static final String isRelationshipRequired = "Y";
    public static final String isDemogDetailsRequired = "Y";
    public static final String isAcctountDetailsRequired = "Y";
    public static final String isJointHolderDetailsRequired = "Y";
    public static final String isCustomerMemoDetailsRequired = "Y";
    public static final String isAccountMemoDetailsRequired = "Y";
    public static final String channel = "GBS";
    public static final String userId = "DevUser01";
    public static final String bankCode = "08";
    public static final String transactionBranch = "089999";

    private void constructFATCARequest(AccountValidationRequestDTO requestDTO){

        requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().setIsAMLDetailsRequired(isAMLDetailsRequired);
        requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().setIsFATCADetailsRequired(isFATCADetailsRequired);
        requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().setIsRelationshipRequired(isRelationshipRequired);
        requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().setIsDemogDetailsRequired(isDemogDetailsRequired);
        requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().setIsAcctountDetailsRequired(isAcctountDetailsRequired);
        requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().setIsJointHolderDetailsRequired(isJointHolderDetailsRequired);
        requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().setIsCustomerMemoDetailsRequired(isCustomerMemoDetailsRequired);
        requestDTO.getFetchCustomerAccountAmlFatcaDetailsRequestDTO().setIsAccountMemoDetailsRequired(isAccountMemoDetailsRequired);

        String externalReferenceNo=RequestUtils.generateTransactionReference();//should be unique on every transaction.
        requestDTO.getSessionContext().setUserId(userId);
        requestDTO.getSessionContext().setChannel(channel);
        requestDTO.getSessionContext().setTransactionBranch(transactionBranch);
        requestDTO.getSessionContext().setBankCode(bankCode);
        requestDTO.getSessionContext().setExternalReferenceNo(externalReferenceNo);

    }
}