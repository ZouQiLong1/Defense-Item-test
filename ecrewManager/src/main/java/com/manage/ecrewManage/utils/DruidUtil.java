package com.manage.ecrewManage.utils;/*
 *@author  邹琪龙
 *@ version 1.0
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtil {
//    德鲁伊工具类
    private static DataSource dataSource = null;
    static {
        Properties properties = new Properties();
        try {
            properties.load(DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){
        Connection connection = null;
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                connection.close();
            }
            if (resultSet != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }














}


