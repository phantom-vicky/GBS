package com.isl.gbm.common.util;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import oracle.jdbc.driver.OracleConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by abhay.kumar-v on 2/1/2018.
 */
@Component
public class ConnectionPoolFactory {
    private static final Logger log = LoggerFactory.getLogger(ConnectionPoolFactory.class);
    @Autowired
    private PropertiesValue propertiesValue = new PropertiesValue();

    @Autowired
    HikariDataSource dataSource;
    private HikariPool hikariPool;

    public Connection getConnection() {
        Connection connection = null;

        try (Connection hikariCon = dataSource.getConnection()) {
            if (hikariCon.isWrapperFor(OracleConnection.class)) {
                connection = hikariCon.unwrap(OracleConnection.class);

            }

        }
        catch (SQLException sqle) {

            log.error(CommonConstants.EXCEPTION_MESSAGE, sqle);
        }

        log.info("Active Conn====" +dataSource.getMaximumPoolSize());
        log.info("Active Conn====" +new HikariDataSourcePoolMetadata(dataSource).getActive());
        log.info("pool Max====" +new HikariDataSourcePoolMetadata(dataSource).getMax());
        log.info("pool Min====" +new HikariDataSourcePoolMetadata(dataSource).getMin());
        return connection;


    }
    @Autowired
    public void getPool() {
        try {
            java.lang.reflect.Field field = dataSource.getClass().getDeclaredField("pool");
            field.setAccessible(true);
            this.hikariPool = (HikariPool) field.get(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection(Connection connection) {
        ((oracle.jdbc.internal.OracleConnection)connection).removeAllDescriptor();
       /* try {
            if (!StringUtils.isEmpty(connection)) {
                connection.close();
            }
        } catch (SQLException sqle) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, sqle);
        }*/
    }

    public void closeConnection(Connection connection, Statement statement) {
        ((oracle.jdbc.internal.OracleConnection)connection).removeAllDescriptor();
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
           /* if (!StringUtils.isEmpty(connection)) {
                connection.close();
            }*/

        } catch (SQLException sqle) {
            log.error("SQLException : ", sqle);
        }
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        ((oracle.jdbc.internal.OracleConnection)connection).removeAllDescriptor();
        try {
            if (!StringUtils.isEmpty(resultSet)) {
                resultSet.close();
            }
            if (!StringUtils.isEmpty(statement)) {
                statement.close();
            }
           /* if (!StringUtils.isEmpty(connection)) {
                connection.close();
            }*/
        } catch (SQLException sqle) {
            log.error("SQLException : ", sqle);
        }
    }
}
