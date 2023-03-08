package com.manage.ecrewManage.dao.impl;/*
 *@author  邹琪龙
 *@ version 1.0
 */


import com.manage.ecrewManage.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

@SuppressWarnings("all")
public class BasicDao {
       static QueryRunner queryRunner = new QueryRunner();
        //查询多行多列
        public static  <T> List<T> selectMultiply(String sql, Class<T> clazz, Object... parameters){
            Connection connection = null;
            try {
                connection = DruidUtil.getConnection();
                return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                DruidUtil.close(connection,null,null);
            }
        }
        //查询单行
        public static <T> T selectSingle(String sql, Class<T> clazz, Object...parameters){
            Connection connection = null;
            try {
                connection = (Connection) DruidUtil.getConnection();
                return queryRunner.query((Connection) connection,sql,new BeanHandler<T>(clazz),parameters);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                DruidUtil.close((Connection) connection,null,null);
            }
        }
        //查询单个对象
        public static Object selectSalary(String sql,Object...parameters){
            Connection connection = null;
            try {
                connection = (Connection) DruidUtil.getConnection();
                return queryRunner.query((Connection) connection,sql,new ScalarHandler(),parameters);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                DruidUtil.close((Connection) connection,null,null);
            }
        }
        //执行dml语句
        public static int update(String sql,Object...parameters){
            Connection connection = null;
            try {
                connection = (Connection) DruidUtil.getConnection();
                int update = queryRunner.update((Connection) connection,sql, parameters);
                return update;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                DruidUtil.close((Connection) connection,null,null);
            }

        }
    }
