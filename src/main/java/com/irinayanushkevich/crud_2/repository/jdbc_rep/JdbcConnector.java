package com.irinayanushkevich.crud_2.repository.jdbc_rep;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcConnector {
    public static JdbcConnector jdbcConnector = null;
    private Connection connection;

    private JdbcConnector() {
        Properties properties = new Properties();
        try {
            String propDir = "src/main/resources/liquibase.properties";
            properties.load(new FileInputStream(propDir));
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static JdbcConnector getInstance() {
        if (jdbcConnector == null) {
            jdbcConnector = new JdbcConnector();
        }
        return jdbcConnector;
    }

    public static Connection getConnection() {
        return getInstance().connection;
    }

    public static PreparedStatement getPreparedStatement(String SqlQuery) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(
                    SqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static PreparedStatement getPreparedStatementWithKeys(String SqlQuery) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(SqlQuery, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }


}
