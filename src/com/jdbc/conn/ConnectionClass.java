package com.jdbc.conn;
/*
 * 功能:数据库连接和数据操作类
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ConnectionClass {
    private Connection connection=null;
    private Statement statement=null;
    private String DBDriver;
    private String user;
    private String password;
    private String url;

    public ConnectionClass() {

        try {
            DBDriver="com.mysql.jdbc.Driver";
            Class.forName(DBDriver);//加载驱动,指定驱动路径
            user="root";
            password="root";
            url="jdbc:mysql://127.0.0.1:3306/guessgame?useUnicode=true&characterEncoding=utf-8";//lab数据库
            connection=DriverManager.getConnection(url, user, password);
            System.out.println("连接数据库成功!");
            statement= connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    public Statement getStatement() {
        return statement;
    }
    public void closeConnection(ResultSet rs) {//关闭数据库连接,如果写在构造方法中,则每次实例化一个连接类,conn和stmt都已关闭,之后的sql语句无法执行
        try {
            connection.close();
            statement.close();
            if(rs!=null) {
                rs.close();
            }
            System.out.println("数据库关闭连接");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

