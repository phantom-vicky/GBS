package com.isl.gbm.user.management.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.gbm.user.management.util.ProcName;
import com.isl.gbm.common.util.LogMaskUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {


    private final LogMaskUtil logMaskUtil;

    private final ObjectMapper objectMapper;

    // Debug log to verify aspect is loaded
    {
        log.info("LoggingAspect initialized and ready to intercept method calls");
    }

    // --- Controller Logging ---
// --- Controller Logging ---
    @Around("execution(* com.isl.gbm..controller..*(..))")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getCurrentHttpRequest();
        String uri = (request != null) ? request.getRequestURI() : "N/A";
        String httpMethod = (request != null) ? request.getMethod() : "N/A";

        String username = getCurrentUsername();
        String userSessionId = getUserSessionId();
        String branchCode = getBranchCode();
        String menuName = extractMenuName(joinPoint.getArgs());
        String correlationId = getCorrelationId();

        MDC.put("username", username);

        String method = joinPoint.getSignature().toShortString();
        long startTime = System.currentTimeMillis();

        // Masked request args
        String maskedInput = logMaskUtil.mask(toJson(joinPoint.getArgs()));

        // REQUEST
        log.info("[Controller Request] user={}, branch={}, uri={}, method={}, httpMethod={}, input={}",
                username, branchCode, uri, method, httpMethod, maskedInput);

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;

            // Masked output
            String maskedOutput = logMaskUtil.mask(toJson(result));

            if (result == null) {
                // EMPTY RESPONSE
                log.warn("[Controller Empty Response] user={}, branch={},  uri={}, method={}, httpMethod={}, executionTime={}ms, correlationId={}",
                        username, branchCode,  uri, method, httpMethod, executionTime, correlationId);
            } else if (isErrorResponse(result)) {
                // ERROR RESPONSE (like 4xx/5xx wrapper object)
                log.error("[Controller Error Response] method={}, args={}, output={}, correlationId={}",
                        method, maskedInput, maskedOutput, correlationId);

            } /*else {
                // SUCCESS RESPONSE
                log.info("[Controller Response] user={}, branch={},  uri={}, method={}, httpMethod={}, executionTime={}ms, output={}, correlationId={}",
                        username, branchCode,  uri, method, httpMethod, executionTime, maskedOutput, correlationId);
            }*/

            MDC.remove("username");
            return result;

        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            Throwable rootCause = getRootCause(e);
            String errorType = rootCause.getClass().getSimpleName();
            String errorMessage = rootCause.getMessage();

            // Masked args & error message
            String maskedInputError = logMaskUtil.mask(toJson(joinPoint.getArgs()));
            String maskedErrorMessage = logMaskUtil.mask(errorMessage);

            // ERROR RESPONSE (exception thrown)
            log.error("[Controller Exception] user={}, branch={}, uri={},  method={}, args={}, errorType={}, error={}, executionTime={}ms, correlationId={}",
                    username, branchCode,  uri, method, maskedInputError, errorType, maskedErrorMessage, executionTime, correlationId);

            if (rootCause instanceof java.sql.SQLException) {
                java.sql.SQLException sqlEx = (java.sql.SQLException) rootCause;
                log.error("[Controller SQL Exception] method={}, errorCode={}, sqlState={}, errorMessage={}, correlationId={}",
                        method, sqlEx.getErrorCode(), sqlEx.getSQLState(), logMaskUtil.mask(sqlEx.getMessage()), correlationId);
            } else if (rootCause instanceof org.springframework.dao.DataAccessException) {
                log.error("[Controller DataAccess Exception] method={}, exceptionType={}, errorMessage={}, correlationId={}",
                        method, rootCause.getClass().getSimpleName(), logMaskUtil.mask(rootCause.getMessage()), correlationId);
            }

            MDC.remove("username");
            throw e;
        }
    }



    @Around("@annotation(requiresPermissions)")
    public Object logMenuAccess(ProceedingJoinPoint joinPoint, RequiresPermissions requiresPermissions) throws Throwable {
        String[] permissions = requiresPermissions.value();
        if (permissions.length > 0) {
            // Take the first permission (you can adjust logic if multiple apply)
            String fullPermission = permissions[0];
            String[] parts = fullPermission.split(":");

            String menuCode = "";
            if (parts.length >= 3) {
                // Get middle part and last part
                menuCode = parts[1] + ":" + parts[2];
            }

            log.info("MenuCode: {}", menuCode);
        }

        return joinPoint.proceed();
    }


    // --- CBS Integration Controller Logging ---
    @Around("execution(* com.isl.gbm.cbs.integration.controller..*(..))")
    public Object logCbsIntegrationController(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getCurrentHttpRequest();
        String uri = (request != null) ? request.getRequestURI() : "N/A";
        String httpMethod = (request != null) ? request.getMethod() : "N/A";

        String username = getCurrentUsername();
        String userSessionId = getUserSessionId();
        String branchCode = getBranchCode();
        String menuName = extractMenuName(joinPoint.getArgs());
        String correlationId = getCorrelationId();

        MDC.put("username", username);

        String method = joinPoint.getSignature().toShortString();
        long startTime = System.currentTimeMillis();

        // REQUEST
        log.info("[CBS Integration Controller Request] user={}, branch={},  uri={}, method={}, httpMethod={}, input={}",
                username, branchCode,  uri, method, httpMethod, toJson(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;

            if (result == null) {
                // EMPTY RESPONSE
                log.warn("[CBS Integration Controller Empty Response] user={}, branch={},  uri={}, method={}, httpMethod={}, executionTime={}ms, correlationId={}",
                        username, branchCode, uri, method, httpMethod, executionTime, correlationId);
            } else if (isErrorResponse(result)) {
                // ERROR RESPONSE (like 4xx/5xx wrapper object)
                log.error("[CBS Integration Controller Error Response] method={}, args={}, output={}, correlationId={}",
                        method, toJson(joinPoint.getArgs()), toJson(result), correlationId);

            } else {
                // SUCCESS RESPONSE
                log.info("[CBS Integration Controller Response] user={}, branch={},  uri={}, method={}, httpMethod={}, executionTime={}ms, output={}, correlationId={}",
                        username, branchCode,  uri, method, httpMethod, executionTime, toJson(result), correlationId);
            }

            MDC.remove("username");
            return result;

        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            String username1 = getCurrentUsername();
            String userSessionId1 = getUserSessionId();
            String branchCode1 = getBranchCode();
            String menuName1 = extractMenuName(joinPoint.getArgs());
            String correlationId1 = getCorrelationId();
            String method1 = joinPoint.getSignature().toShortString();

            MDC.put("username", username);
            Throwable rootCause = getRootCause(e);
            String errorType = rootCause.getClass().getSimpleName();
            String errorMessage = rootCause.getMessage();

            // ERROR RESPONSE (exception thrown)
            log.error("[CBS Integration Controller Exception] user={}, branch={},  uri={},  method={}, args={}, errorType={}, error={}, executionTime={}ms, correlationId={}",
                    username1, branchCode1,  uri,     method1, toJson(joinPoint.getArgs()), errorType, errorMessage, executionTime, correlationId);

            if (rootCause instanceof java.sql.SQLException) {
                java.sql.SQLException sqlEx = (java.sql.SQLException) rootCause;
                log.error("[CBS Integration Controller SQL Exception] method={}, errorCode={}, sqlState={}, errorMessage={}, correlationId={}",
                        method, sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage(), correlationId);
            } else if (rootCause instanceof org.springframework.dao.DataAccessException) {
                log.error("[CBS Integration Controller DataAccess Exception] method={}, exceptionType={}, errorMessage={}, correlationId={}",
                        method, rootCause.getClass().getSimpleName(), rootCause.getMessage(), correlationId);
            }

            MDC.remove("username");
            throw e;
        }
    }

    // --- CBS Integration DAO Logging ---
    @Around("execution(* com.isl.gbm.cbs.integration.dao..*(..))")
    public Object logCbsIntegrationDao(ProceedingJoinPoint joinPoint) throws Throwable {
        String username = getCurrentUsername();
        String branchCode = getBranchCode();
        String menuName = extractMenuName(joinPoint.getArgs());
        String procName = extractProcName(joinPoint);
        String correlationId = getCorrelationId();
        long start = System.currentTimeMillis();

        MDC.put("username", username);
        String method = joinPoint.getSignature().toShortString();

        log.info("[CBS Integration DAO Request] user={}, branch={}, proc={}, method={}, input={}",
                username, branchCode,  procName, method, toJson(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            if (result == null) {
                log.warn("[CBS Integration DAO Empty Response] user={}, branch={},  proc={}, method={}, executionTime={}ms, correlationId={}",
                        username, branchCode,  procName, method, executionTime, correlationId);
            } else if (isErrorResponse(result)) {
                String exceptionMessage = "";
                if (result instanceof com.isl.gbm.common.dto.ResponseDataDTO) {
                    com.isl.gbm.common.dto.ResponseDataDTO response = (com.isl.gbm.common.dto.ResponseDataDTO) result;
                    exceptionMessage = response.getMessage();
                }

                log.error("[CBS Integration DAO Error Response] user={}, branch={},  proc={}, method={},  exceptionMessage={},",
                        username, branchCode, procName, method,  exceptionMessage);
            } else {
                log.info("[CBS Integration DAO Response] user={}, branch={}, proc={}, method={}, executionTime={}ms, output={}",
                        username, branchCode,  procName, method, executionTime, toJson(result));
            }

            MDC.remove("username");
            return result;
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - start;
            Throwable rootCause = getRootCause(e);

            log.error("[CBS Integration DAO Error Response] user={}, branch={}, proc={}, method={}, executionTime={}ms, errorType={}, message={}, correlationId={}",
                    username, branchCode,  procName, method, executionTime, rootCause.getClass().getSimpleName(), rootCause.getMessage(), correlationId, e);

            if (rootCause instanceof SQLException) {
                SQLException sqlEx = (SQLException) rootCause;
                log.error("[CBS Integration DAO SQL Exception] proc={}, method={}, errorCode={}, sqlState={}, errorMessage={}, correlationId={}",
                        procName, method, sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage(), correlationId);
            }

            MDC.remove("username");
            throw e;
        }
    }

    // --- CBS Integration Service Logging ---
    @Around("execution(* com.isl.gbm.cbs.integration.service..*(..))")
    public Object logCbsIntegrationService(ProceedingJoinPoint joinPoint) throws Throwable {
        String username = getCurrentUsername();
        String branchCode = getBranchCode();
        String menuName = extractMenuName(joinPoint.getArgs());
        String correlationId = getCorrelationId();
        long start = System.currentTimeMillis();

        MDC.put("username", username);
        String method = joinPoint.getSignature().toShortString();

        log.info("[CBS Integration Service Request] user={}, branch={}, method={}, input={}",
                username, branchCode, menuName, method, toJson(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            if (result == null) {
                log.warn("[CBS Integration Service Empty Response] user={}, branch={}, menu={}, method={}, executionTime={}ms, correlationId={}",
                        username, branchCode, menuName, method, executionTime, correlationId);
            } else if (isErrorResponse(result)) {
                String exceptionMessage = "";
                if (result instanceof com.isl.gbm.common.dto.ResponseDataDTO) {
                    com.isl.gbm.common.dto.ResponseDataDTO response = (com.isl.gbm.common.dto.ResponseDataDTO) result;
                    exceptionMessage = response.getMessage();
                }

                log.error("[CBS Integration Service Error Response] user={}, branch={}, method={}, exceptionMessage={}, correlationId={}",
                        username, branchCode, method, exceptionMessage, correlationId);
            } else {
                log.info("[CBS Integration Service Response] user={}, branch={}, menu={}, method={}, executionTime={}ms, output={}",
                        username, branchCode, menuName, method, executionTime, toJson(result));
            }

            MDC.remove("username");
            return result;
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - start;
            Throwable rootCause = getRootCause(e);

            log.error("[CBS Integration Service Exception] user={}, branch={}, menu={}, method={}, executionTime={}ms, errorType={}, message={}, correlationId={}",
                    username, branchCode, menuName, method, executionTime, rootCause.getClass().getSimpleName(), rootCause.getMessage(), correlationId, e);

            MDC.remove("username");
            throw e;
        }
    }

    // --- CBS Integration Manager Logging ---
    @Around("execution(* com.isl.gbm.cbs.integration.manager..*(..))")
    public Object logCbsIntegrationManager(ProceedingJoinPoint joinPoint) throws Throwable {
        String username = getCurrentUsername();
        String branchCode = getBranchCode();
        String menuName = extractMenuName(joinPoint.getArgs());
        String correlationId = getCorrelationId();
        long start = System.currentTimeMillis();

        MDC.put("username", username);
        String method = joinPoint.getSignature().toShortString();

        log.info("[CBS Integration Manager Request] user={}, branch={}, menu={}, method={}, input={}",
                username, branchCode, menuName, method, toJson(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            if (result == null) {
                log.warn("[CBS Integration Manager Empty Response] user={}, branch={}, menu={}, method={}, executionTime={}ms, correlationId={}",
                        username, branchCode, menuName, method, executionTime, correlationId);
            } else if (isErrorResponse(result)) {
                String exceptionMessage = "";
                if (result instanceof com.isl.gbm.common.dto.ResponseDataDTO) {
                    com.isl.gbm.common.dto.ResponseDataDTO response = (com.isl.gbm.common.dto.ResponseDataDTO) result;
                    exceptionMessage = response.getMessage();
                }

                log.error("[CBS Integration Manager Error Response] user={}, branch={}, method={}, exceptionMessage={}, correlationId={}",
                        username, branchCode, method, exceptionMessage, correlationId);
            } else {
                log.info("[CBS Integration Manager Response] user={}, branch={}, menu={}, method={}, executionTime={}ms, output={}",
                        username, branchCode, menuName, method, executionTime, toJson(result));
            }

            MDC.remove("username");
            return result;
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - start;
            Throwable rootCause = getRootCause(e);

            log.error("[CBS Integration Manager Exception] user={}, branch={}, menu={}, method={}, executionTime={}ms, errorType={}, message={}, correlationId={}",
                    username, branchCode, menuName, method, executionTime, rootCause.getClass().getSimpleName(), rootCause.getMessage(), correlationId, e);

            MDC.remove("username");
            throw e;
        }
    }

    // --- DAO Logging ---
    @Around("execution(* com.isl.gbm..dao..*(..))")
    public Object logDao(ProceedingJoinPoint joinPoint) throws Throwable {
        String username = getCurrentUsername();
        String branchCode = getBranchCode();
        String menuName = extractMenuName(joinPoint.getArgs());
        String procName = extractProcName(joinPoint);
        String correlationId = getCorrelationId();
        long start = System.currentTimeMillis();

        MDC.put("username", username);
        String method = joinPoint.getSignature().toShortString();

        // Masked request input
        String maskedInput = logMaskUtil.mask(toJson(joinPoint.getArgs()));

        log.info("[DAO Request] user={}, branch={}, proc={}, method={}, input={}",
                username, branchCode,  procName, method, maskedInput);

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            if (result == null) {
                log.warn("[DAO Empty Response] user={}, branch={}, proc={}, method={}, executionTime={}ms, correlationId={}",
                        username, branchCode,  procName, method, executionTime, correlationId);

            } else if (isErrorResponse(result)) {
                String exceptionMessage = "";
                if (result instanceof com.isl.gbm.common.dto.ResponseDataDTO) {
                    com.isl.gbm.common.dto.ResponseDataDTO response = (com.isl.gbm.common.dto.ResponseDataDTO) result;
                    exceptionMessage = response.getMessage();
                }

                log.error("[DAO Error Response] user={}, branch={}, proc={}, method={}, exceptionMessage={}",
                        username, branchCode, procName, method, logMaskUtil.mask(exceptionMessage));

            } else {
                // Masked result output
                long responseLogStart = System.currentTimeMillis();
                String maskedOutput = logMaskUtil.mask(toJson(result));
                log.info("[DAO Response] user={}, branch={},  proc={}, method={}, executionTime={}ms, output={}",
                        username, branchCode,  procName, method, executionTime, maskedOutput);
                long responseLogTime = System.currentTimeMillis() - responseLogStart;
                log.info("Log Printing Time={}ms", responseLogTime);
            }

            MDC.remove("username");
            return result;

        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - start;
            Throwable rootCause = getRootCause(e);

            log.error("[DAO Error Response] user={}, branch={},  proc={}, method={}, executionTime={}ms, errorType={}, message={}, correlationId={}",
                    username, branchCode,  procName, method, executionTime,
                    rootCause.getClass().getSimpleName(),
                    logMaskUtil.mask(rootCause.getMessage()),
                    correlationId, e);

            if (rootCause instanceof SQLException) {
                SQLException sqlEx = (SQLException) rootCause;
                log.error("[DAO SQL Exception] proc={}, method={}, errorCode={}, sqlState={}, errorMessage={}, correlationId={}",
                        procName, method, sqlEx.getErrorCode(), sqlEx.getSQLState(),
                        logMaskUtil.mask(sqlEx.getMessage()), correlationId);
            }

            MDC.remove("username");
            throw e;
        }
    }

    private String getCurrentUsername() {
        try {
            Object principal = SecurityUtils.getSubject().getPrincipal();
            return principal != null ? principal.toString() : "anonymous";
        } catch (Exception e) {
            return "unknown";
        }
    }

    private String getCorrelationId() {
        return MDC.get("correlationId") != null ? MDC.get("correlationId") : "N/A";
    }

    private String getUserSessionId() {
        return MDC.get("userSessionId") != null ? MDC.get("userSessionId") : "N/A";
    }

    private String getBranchCode() {
        return MDC.get("branchCode") != null ? MDC.get("branchCode") : "N/A";
    }


    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }

    // Example: extract menuName from DTO (if it implements HasMenuName)
    private String extractMenuName(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof HasMenuName) {
                return ((HasMenuName) arg).getMenuName();
            }
        }
        return "N/A";
    }

    private String extractProcName(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        ProcName annotation = method.getAnnotation(ProcName.class);
        if (annotation != null) {
            Class<? extends Enum<?>> enumClass = annotation.enumClass();
            String enumConstant = annotation.enumConstant();
            try {
                Enum<?> constant = Enum.valueOf((Class) enumClass, enumConstant);
                if (constant instanceof HasProcName) {  // optional interface
                    return ((HasProcName) constant).getProcName();
                }
                return constant.name();  // fallback
            } catch (Exception e) {
                return "N/A";
            }
        }
        return "N/A";
    }

    public interface HasProcName {
        String getProcName();
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return String.valueOf(obj);
        }
    }

    private String getStackTrace(Exception e) {
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * Get the root cause of an exception
     */
    private Throwable getRootCause(Throwable throwable) {
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    /**
     * Check if the response object represents an error response
     */
    private boolean isErrorResponse(Object result) {
        try {
            if (result == null) {
                return true; // null result is considered error
            }

            // Check ResponseEntity (Controller)
            if (result instanceof org.springframework.http.ResponseEntity) {
                org.springframework.http.ResponseEntity<?> responseEntity = (org.springframework.http.ResponseEntity<?>) result;
                org.springframework.http.HttpStatus status = (org.springframework.http.HttpStatus) responseEntity.getStatusCode();
                return status.is4xxClientError() || status.is5xxServerError();
            }

            // Check ResponseDataDTO (DAO or Controller)
            if (result instanceof com.isl.gbm.common.dto.ResponseDataDTO) {
                com.isl.gbm.common.dto.ResponseDataDTO responseData = (com.isl.gbm.common.dto.ResponseDataDTO) result;

                // Any non-empty message indicates an error
//                if (responseData.getMessage() != null && !responseData.getMessage().trim().isEmpty()) {
//                    return true;
//                }



                // Optional: check error object
                if (responseData.getError() != null) {
                    return true;
                }

                // Optional: check status code
                String status = responseData.getStatusCode();
                if (status != null) {
                    int statusCode;
                    try {
                        statusCode = Integer.parseInt(status);
                        if (statusCode >= 400) {
                            return true;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }

                return false;
            }



            // Fallback: check JSON output for error patterns
            String resultJson = toJson(result);

            System.out.println(resultJson +"=====resultJson");
            if (resultJson != null) {
                return resultJson.contains("\"statusCode\":\"UNAUTHORIZED\"") ||
                        resultJson.contains("\"statusCode\":\"FORBIDDEN\"") ||
                        resultJson.contains("\"statusCode\":\"NOT_FOUND\"") ||
                        resultJson.contains("\"statusCode\":\"BAD_REQUEST\"") ||
                        resultJson.contains("\"statusCode\":\"INTERNAL_SERVER_ERROR\"") ||
                        resultJson.contains("\"statusCode\":\"UNPROCESSABLE_ENTITY\"") ||
                        resultJson.contains("\"statusCodeValue\":401") ||
                        resultJson.contains("\"statusCodeValue\":403") ||
                        resultJson.contains("\"statusCodeValue\":404") ||
                        resultJson.contains("\"statusCodeValue\":400") ||
                        resultJson.contains("\"statusCodeValue\":500") ||
                        resultJson.contains("\"statusCodeValue\":422");
            }

            return false;

        } catch (Exception e) {
            // If detection fails, assume error
            return true;
        }
    }

    public interface HasMenuName {
        String getMenuName();
    }
}

