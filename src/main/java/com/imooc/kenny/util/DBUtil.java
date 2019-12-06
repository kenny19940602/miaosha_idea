package com.imooc.kenny.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * ClassName: DBUtil
 * Function:  TODO
 * Date:      2019/11/29 14:52
 * author     Kenny
 * version    V1.0
 */
public class DBUtil {
    private static Properties props;

    static {
        try {
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("application.properties");
            props = new Properties();
            props.load(in);
            in.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws Exception{
        String url = props.getProperty("spring.datasource.url");
        String username = props.getProperty("spring.datasource.username");
        String password = props.getProperty("spring.datasource.password");
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        return DriverManager.getConnection(url,username, password);
    }

    public static void main(String[] args) throws Exception {
        Connection connection = getConn();
        System.out.println(connection);
    }
}
