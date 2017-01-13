package com.moze.dao;

import com.moze.db.DruidDB;
import com.moze.vo.BBSUser;

import java.io.FileInputStream;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-10
 * Time: 8:53
 */
public class UserDAOImpl implements IUserDAO {
    private Connection conn=null;

    public UserDAOImpl() {
        conn= DruidDB.getConnection();
    }

    @Override
    public BBSUser login(BBSUser user) {
        BBSUser user1=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        boolean flag=false;
        try {
            pstmt=conn.prepareStatement("SELECT  * FROM bbsuser WHERE username=? AND password=? ;");
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            rs=pstmt.executeQuery();
            if(rs.next()){
                user1=new BBSUser();
                user1.setId(rs.getInt("id"));
                user1.setUsername(rs.getString("username"));
                user1.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null){
                    rs.close();
                }
                if(pstmt!=null){
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return user1;
    }

    @Override
    public boolean addUser(BBSUser user) {
        PreparedStatement pstmt=null;
        boolean flag=false;
        try {
            pstmt=conn.prepareStatement("INSERT into bbsuer(username,password,pic,pagenum) VALUE (?,?,?,?);");
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            FileInputStream is=new FileInputStream(user.getPath());
            pstmt.setBinaryStream(3,is,is.available());
            pstmt.setInt(4,user.getPagenum());
            flag=pstmt.executeUpdate()>0?true:false;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

                try {
                    if(pstmt!=null){
                        pstmt.close();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
        return flag;
    }

    @Override
    public byte[] queryPicByid(int id) {
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        byte[] buffer=null;
        try {
            pstmt=conn.prepareStatement("SELECT * FROM bbsuser WHERE id=?");
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            if(rs.next()){
                Blob b=rs.getBlob("pic");
                buffer=b.getBytes(1,(int)(b.length()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null){
                    rs.close();
                }
                if(pstmt!=null){
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }
}
