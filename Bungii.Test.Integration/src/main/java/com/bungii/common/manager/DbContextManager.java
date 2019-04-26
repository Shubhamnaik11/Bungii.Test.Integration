package com.bungii.common.manager;

import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbContextManager {
    private static LogUtility logger = new LogUtility(DbContextManager.class);
    private static String MYSQL_URL = PropertyUtility.getJdbcConfigProperties("jdbc.mysqlserver.url");
    private static String MYSQL_MGMT_URL = PropertyUtility.getJdbcConfigProperties("jdbc.mysqlserver.mgmt.url");

    private static String MYSQL_USER = PropertyUtility.getJdbcConfigProperties("mysql.user");
    private static String MYSQL_PASSWORD = PropertyUtility.getJdbcConfigProperties("mysql.password");

    private static String MSSQL_URL = PropertyUtility.getJdbcConfigProperties("jdbc.sqlserver.url");
    private static String MSSQL_USER = PropertyUtility.getJdbcConfigProperties("sqlserver.user");
    private static String MSSQL_PASSWORD = PropertyUtility.getJdbcConfigProperties("sqlserver.password");


    public static String getDataFromMsSqlServer(String queryString) {
        String result = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(MSSQL_URL, MSSQL_USER, MSSQL_PASSWORD);
            logger.detail("Connected to ms sql server");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                result = rs.getString(1);
                logger.detail("MS SQL SERVER DATA" + result);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(" error in Connecting to ms sql server");
        }
        return result;
    }

    public static String getDataFromMySqlServer(String queryString) {
        String result = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
            logger.detail("Connected to my sql server");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                result = rs.getString(1);
                logger.detail("MY SQL SERVER DATA" + result);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }
    public static String getDataFromMySqlMgmtServer(String queryString) {
        String result = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MYSQL_MGMT_URL, MYSQL_USER, MYSQL_PASSWORD);
            logger.detail("Connected to my sql server");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                result = rs.getString(1);
                logger.detail("MY SQL SERVER DATA" + result);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }
}
