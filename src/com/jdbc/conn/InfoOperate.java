package com.jdbc.conn;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.PreparedStatement;
/*
 * 功能:用户信息类和操作信息类
 */
public class InfoOperate {

    private ConnectionClass connectionclass = null;
    private Connection conn = null;
    private Statement stmt = null;

    public InfoOperate(ConnectionClass connclass) {
        conn = connclass.getConnection();//取得连接类中的statement对象
        stmt = connclass.getStatement();//取得连接类中的sql语句
        //这样,当实例化一个连接类 其中的connection已连接 statement已连接,即可使用
    }

    //查询用户ID是否存在
    public boolean selectID(String ID) {
        try {
            String sql = "SELECT userID FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String str = rs.getString("userID");
                if (ID.equals(str)) {
                    return true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    //查询密码是否正确
    public boolean selectPassword(String userID, String password) {
        try {
            String sql = "SELECT userID,password FROM user";
            ResultSet rs = stmt.executeQuery(sql);//返回结果集
            while (rs.next()) {
                String pswd = rs.getString("password");
                String id = rs.getString("userID");
                if (userID.equals(id) & password.equals(pswd)) {
                    return true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    //添加用户方法
    public void insertUser(String userID, String password) {
        try {
            String sql = "insert into user values ('" + userID + "','" + password + "',0)";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println("注册成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //获取用户数量
    public int getUserNum(){
        int num = 0;
        try {
            String sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                num++;
            }
        }catch (Exception e){

        }
        return num;
    }

    //获取所有用户名字的list
    public String[] getUserRank() {
        int n = getUserNum();
        String[] user = new String[n];
        try {
            String sql = ("SELECT * FROM user order by point desc");
            ResultSet rs = stmt.executeQuery(sql);
            for (int i = 0; rs.next(); i++) {
                user[i] = "第"+(i+1)+"名-"+rs.getString("userID")+",积分:"+rs.getInt("point");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return user;
    }

    //获得某位用户的排名
    public int getRank(String userID) {
        int n = getUserNum();
        String[] user = new String[n];
        int i = 0;
        try {
            String sql = ("SELECT * FROM user order by point desc");
            ResultSet rs = stmt.executeQuery(sql);
            for (; rs.next(); i++) {
                if (rs.getString("userID").equals(userID))
                    break;
            }
            i++;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return i;
    }

    //获得某位用户积分
    public int getPoint(String userID) {
        int point = 0;
        try {
            String sql = ("SELECT * FROM user");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                if (rs.getString("userID").equals(userID)){
                    point = rs.getInt("point");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return point;
    }

    //更新数据
    public void upPoint(String userID,int point){
        try{
            String sql = "update user set point ="+point+" where userID = '"+userID+"'";
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
        }
    }

    //普通场赢了加一积分
    public void addOne(String userID){
        try {
            String sql = "select * from user where userID = '"+userID+"'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int point = rs.getInt("point");
            point++;
            upPoint(userID,point);
        }catch (Exception e){
        }
    }
    //积分场参加减一积分
    public void subOne(String userID){
        try {
            String sql = "select * from user where userID = '"+userID+"'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int point = rs.getInt("point");
            point--;
            upPoint(userID,point);
        }catch (Exception e){
        }
    }
    //积分场赢了加五积分
    public void addThree(String userID){
        try {
            String sql = "select * from user where userID = '"+userID+"'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int point = rs.getInt("point");
            point += 3;
            upPoint(userID,point);
        }catch (Exception e){
        }
    }
}
