package com.isl.gbm.rbibond.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JDBCTemplateUtil {

    private static final Logger log = LoggerFactory.getLogger(JDBCTemplateUtil.class);

    private final JdbcTemplate jdbcTemplate;

    public JDBCTemplateUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Map<String, Object> executeStoredProc(String procedureName, Map<String, Object> inParams,
                                                 SqlParameter[] sqlParameters, SqlOutParameter[] sqlOutParameters,
                                                 Map<String, RowMapper<?>> rowMappers) {
        log.info("Inside executeStoredProc JDBCTemplateUtil ..");
        try {

            // Log the input parameters
            if (inParams != null && !inParams.isEmpty()) {
                log.info("Procedure : "+procedureName);
                for (Map.Entry<String, Object> entry : inParams.entrySet()) {
                    log.info("In Parameter Name: " + entry.getKey() + ", In Parameter Value: " + entry.getValue());
                }
            } else {
                log.info("No input parameters provided.");
            }

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(procedureName)
                    .declareParameters(sqlParameters)
                    .declareParameters(sqlOutParameters);

            for (Map.Entry<String, RowMapper<?>> entry : rowMappers.entrySet()) {
                jdbcCall.returningResultSet(entry.getKey(), entry.getValue());
            }

            return jdbcCall.execute(inParams);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurred while executing stored procedure: " + procedureName, e);
           // throw e; // rethrowing the exception after logging
            throw new RuntimeException("Error executing stored procedure", e);
        } catch (Exception e) {
            log.error("Exception occurred while executing stored procedure: " + procedureName, e);
            //throw e; // rethrowing the exception after logging
            throw new RuntimeException("Error executing stored procedure", e);
        }
        finally {
        log.info("Outside executeStoredProc JDBCTemplateUtil..");
    }
    }

    public Map<String, Object> executePackageStoredProc(String packageName, String procedureName, Map<String, Object> inParams,
                                                        SqlParameter[] sqlParameters, SqlOutParameter[] sqlOutParameters,
                                                        Map<String, RowMapper<?>> rowMappers) {
        log.info("Inside executePackageStoredProc JDBCTemplateUtil..");
        try {

            // Log the input parameters
            if (inParams != null && !inParams.isEmpty()) {
                log.info("Package Procedure : "+packageName+"."+procedureName);
                for (Map.Entry<String, Object> entry : inParams.entrySet()) {
                    log.info("In Parameter Name: " + entry.getKey() + ", In Parameter Value: " + entry.getValue());
                }
            } else {
                log.info("No input parameters provided.");
            }

            jdbcTemplate.setFetchSize(100);
            jdbcTemplate.setQueryTimeout(1200);// 20mins

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(packageName)
                    .withProcedureName(procedureName)
                    .declareParameters(sqlParameters)
                    .declareParameters(sqlOutParameters);

            for (Map.Entry<String, RowMapper<?>> entry : rowMappers.entrySet()) {
                jdbcCall.returningResultSet(entry.getKey(), entry.getValue());
            }
            return jdbcCall.execute(inParams);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurred while executing package stored procedure: " + packageName + "." + procedureName, e);
            //throw e; // rethrowing the exception after logging
            throw new RuntimeException("Error executing stored procedure", e);
        } catch (Exception e) {
            log.error("Exception occurred while executing package stored procedure: " + packageName + "." + procedureName, e);
           // throw e; // rethrowing the exception after logging
            throw new RuntimeException("Error executing stored procedure", e);
        } finally {
            log.info("Outside executePackageStoredProc JDBCTemplateUtil..");
        }
    }
}
