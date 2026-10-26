package com.isl.gbm.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.*;

@Component
public class JdbcConnectionFactory {
    private static final Logger log= LoggerFactory.getLogger(JdbcConnectionFactory.class);

    @Autowired
    PropertiesValue propertiesValue;

    public Connection getConnection(){
        Connection connection=null;

        try {
            if (!StringUtils.isEmpty(propertiesValue.getDriverClass())&&
                    !StringUtils.isEmpty(propertiesValue.getUrl())&&
                    !StringUtils.isEmpty(propertiesValue.getUserName())&&
                    !StringUtils.isEmpty(propertiesValue.getPassword())) {
                Class.forName(propertiesValue.getDriverClass());
                connection = DriverManager.getConnection(propertiesValue.getUrl(), propertiesValue.getUserName(), propertiesValue.getPassword());
            }
        }catch (ClassNotFoundException cnfe){
            System.err.println(cnfe);
            log.error("ClassNotFoundException : ",cnfe);
        }catch (SQLException sqle){
            System.err.println(sqle);
            log.error("SQLException : ",sqle);
        }
        return connection;
    }


    public  void closeConnection(Connection connection){
        try{
            if(!StringUtils.isEmpty(connection)){
                connection.close();
            }
        }catch (SQLException sqle){
            System.err.println(sqle);
            log.error("SQLException : ",sqle);
        }
    }

    public  void closeConnection(Connection connection, Statement statement){
        try{
            if(!StringUtils.isEmpty(statement)){
                statement.close();
            }
            if(!StringUtils.isEmpty(connection)){
                connection.close();
            }
        }catch (SQLException sqle){
            System.err.println(sqle);
            log.error("SQLException : ",sqle);
        }
    }

    public  void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (!StringUtils.isEmpty(connection)) {
                connection.close();
            }
            if (!StringUtils.isEmpty(statement)) {
                statement.close();
            }
            if (!StringUtils.isEmpty(resultSet)) {
                resultSet.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle);
            log.error("SQLException : ", sqle);
        }
    }
}