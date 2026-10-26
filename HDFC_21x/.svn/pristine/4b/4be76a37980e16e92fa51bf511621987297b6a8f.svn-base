package com.isl.gbm.rbibond.externalservice.connections;

import com.isl.gbm.rbibond.externalservice.customexception.ApiRequestException;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import oracle.jdbc.driver.OracleConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class ConnectionFactoryHcp {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactoryHcp.class);
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 2000; // 2 seconds
    private static final int MAX_TOTAL_WAIT_TIME_MS = 5000; // 5 seconds

    private final PropertiesValues propertiesValue;
    private final HikariDataSource dataSource;
    private HikariPool hikariPool;

    @Autowired
    public ConnectionFactoryHcp(PropertiesValues propertiesValue, HikariDataSource dataSource) {
        this.propertiesValue = propertiesValue;
        this.dataSource = dataSource;
        setPool();
    }
/*
    public Connection getConnectionOld() throws SQLException {
        try (Connection hikariCon = dataSource.getConnection()) {
            if (hikariCon.isWrapperFor(OracleConnection.class)) {
                logConnectionPoolInformation();
                return hikariCon.unwrap(OracleConnection.class);
            }
        } catch (SQLException sqle) {
            logger.error("Exception while getting a connection: {}", sqle.getMessage());
            throw sqle; // Propagate the exception for higher-level error handling
        }
        return null; // Or throw a custom exception if necessary
    }

 */

    public Connection getConnection() throws SQLException {
        int attempts = 0;
        long startTime = System.currentTimeMillis();
        SQLException lastException = null;

        while (attempts < MAX_RETRIES) {
            attempts++;
            try (Connection hikariCon = dataSource.getConnection()) {
                if (hikariCon.isWrapperFor(OracleConnection.class)) {
                    logConnectionPoolInformation();
                    logger.info("Connection established successfully on attempt {}", attempts);
                    return hikariCon.unwrap(OracleConnection.class);
                }
            } catch (SQLException sqle) {
                lastException = sqle;
                logger.error("Attempt {} - Failed to obtain a database connection: {}", attempts, sqle.getMessage());

                if (System.currentTimeMillis() - startTime > MAX_TOTAL_WAIT_TIME_MS) {
                    logger.error("Exceeded maximum total wait time of {}ms", MAX_TOTAL_WAIT_TIME_MS);
                    break;
                }

                if (attempts < MAX_RETRIES) {
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt(); // Restore the interrupt status
                        logger.error("Retry process was interrupted", ie);
                        throw new ApiRequestException("Retry process was interrupted", ie);
                    }
                }
            }
        }

        logger.error("All attempts to establish a database connection failed.");
        throw new ApiRequestException("Unable to establish a database connection after " + MAX_RETRIES + " attempts", lastException);
    }

    public void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    logger.error("Exception while closing a resource: {}", e.getMessage());
                }
            }
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Failed to close the connection: {}", e.getMessage());
            }
        }
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Failed to close the statement: {}", e.getMessage());
            }
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Failed to close the result set: {}", e.getMessage());
            }
        }
    }

    public void closeResources(Connection connection, Statement statement) {
        closeStatement(statement);
        closeConnection(connection);
    }

    public void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }

    private void setPool() {
        try {
            java.lang.reflect.Field field = dataSource.getClass().getDeclaredField("pool");
            field.setAccessible(true);
            this.hikariPool = (HikariPool) field.get(dataSource);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Exception while accessing HikariPool: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void logConnectionPoolInformation() {
        // Add appropriate guards to log connection pool information only in non-production environments
        HikariDataSourcePoolMetadata poolMetadata = new HikariDataSourcePoolMetadata(dataSource);
        logger.info("Active Connections: {}", poolMetadata.getActive());
        logger.info("Maximum Pool Size: {}", dataSource.getMaximumPoolSize());
        logger.info("Pool Maximum: {}", poolMetadata.getMax());
        logger.info("Pool Minimum: {}", poolMetadata.getMin());
    }
}
