package com.moze.db;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;


import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-09
 * Time: 19:47
 */
public class DruidDB {
    private static Connection conn;
    private static DataSource ds;
    static {
        Properties pro = new Properties();
        try {
            pro.load(DruidDB.class.getClassLoader().getResourceAsStream("druid.ini"));
            ds=DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public static Connection getConnection(){
        try {
            conn=ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String [] args){
        DruidDB.getConnection();
    }
}
