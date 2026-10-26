package com.isl.gbm.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;

@Component
public class DatabaseConnectionFactory {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionFactory.class);

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${db.connection.retry}")
    private  String tryReconnect;

    @Value("${db.connection.delaytime}")
    private  String delayInTime;


    public Connection getConnection() throws SQLException {
        Connection connection = null;
        if (driverClassName.isEmpty() || url.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Database values not properly configured.");
        }
        try {
            Class.forName(driverClassName);
            connection= DriverManager.getConnection(url, userName, password);
            logger.info("JDBC Connection Open");
            return connection;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class not found exception: " + e.getMessage(), e);
        } catch (SQLException e) {
            if(connection == null){
                try {
                    return getReConnect();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new SQLException("SQL Exception: " + e.getMessage(), e);

        }
    }
    public Connection getReConnect() throws SQLException, InterruptedException {
        int count = Integer.parseInt(tryReconnect);
        int delayTime = Integer.parseInt(delayInTime);
        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(delayTime);
                return getConnection();
            } catch (InterruptedException e) {
                throw new InterruptedException("Exception inside getReConnect: " + e.getMessage());
            }
        }
        throw new SQLException("Failed to reconnect after " + count + " attempts.");
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

    public void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (IOException | SQLException e) {
                    logger.error("Exception while closing a resource: {}", e.getMessage(), e);
                } catch (Exception e) {
                    // Handle unexpected exceptions, or log them as needed
                    logger.error("Unexpected exception while closing a resource: {}", e.getMessage(), e);
                }
            }
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.info("JDBC Connection Closed");
            } catch (SQLException e) {
                logger.error("Failed to close the JDBC connection: {}", e.getMessage());
            }
        }
    }
    private void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Failed to close the statement: {}", e.getMessage());
            }
        }
    }
    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Failed to close the result set: {}", e.getMessage());
            }
        }
    }
}
