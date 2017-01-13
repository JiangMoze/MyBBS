package com.moze.dao;

import com.moze.db.DruidDB;
import com.moze.vo.Article;
import com.moze.vo.BBSUser;
import com.moze.vo.PageBean;
import com.moze.vo.ReArticle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-09
 * Time: 20:13
 */
public class ArticleDAOImpl implements IArticleDAO {
    private Connection conn;

    public ArticleDAOImpl() {
        conn = DruidDB.getConnection();
    }

    public static void main(String[] args) {

    }

    @Override
    public PageBean queryAll(int curPage, int useid) {
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<Article> list = new ArrayList<Article>();
        PageBean pb = new PageBean();
        try {
            String sql = "call q4(?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, curPage);
            cs.setInt(2, useid);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.registerOutParameter(5, Types.INTEGER);
            boolean flag = cs.execute();
            while (flag) {
                rs = cs.getResultSet();
                pb.setCurPage(curPage);
                pb.setMaxPage(cs.getInt(3));
                pb.setMaxRowCount(cs.getInt(4));
                pb.setRowsPerPage(cs.getInt(5));
                while (rs.next()) {
                    Article a = new Article();
                    BBSUser user = new BBSUser();
                    a.setId(rs.getInt("id"));
                    a.setTitle(rs.getString("title"));
                    a.setContent(rs.getString("content"));
                    a.setDatetime(rs.getString("datetime"));
                    user.setId(rs.getInt("userid"));
                    a.setUser(user);
                    list.add(a);
                }
                pb.setData(list);
                flag = cs.getMoreResults();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return pb;
    }

    @Override
    public boolean addArticle(Article a) {
        PreparedStatement pstmt = null;
        boolean flag = false;
        try {
            String sql = "insert into article(rootid,title,content,datetime,userid) values (?,?,?,now(),?)";
            pstmt = conn.prepareStatement(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public boolean delZArticle(int id) {
        PreparedStatement pstmt = null;
        boolean flag = false;
        try {
            String sql = "delete from article where id=? or  rootid =?;";
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public boolean delCArticle(int id) {
        PreparedStatement pstmt = null;
        boolean flag = false;

        try {
            String sql = "delete from article where id=?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            flag = pstmt.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return flag;

    }

    @Override
    public ReArticle queryReplay(int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ReArticle re = new ReArticle();

        try {
            String sql = "select p.title,t.* " +
                    " from " +
                    "(select a.id,a.title,a.content,a.userid,a.rootid from article a " +
                    "inner join (bbsuser u) on(a.userid=u.id) " +
                    "where  a.rootid=? ) t,article p " +
                    " where p.id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, id);
            rs = pstmt.executeQuery();
            List<Article> list1 = new ArrayList<Article>();
            while (rs.next()) {
                re.setTitle(rs.getString("p.title"));
                Article a = new Article();
                a.setId(rs.getInt("t.id"));
                a.setContent(rs.getString("t.content"));
                a.setTitle(rs.getString("t.rootid"));
                BBSUser user = new BBSUser();
                user.setId(rs.getInt("t.userid"));
                a.setUser(user);
                list1.add(a);

            }
            re.setList(list1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return re;

    }
}
