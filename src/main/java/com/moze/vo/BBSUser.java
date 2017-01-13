package com.moze.vo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-09
 * Time: 20:02
 */
public class BBSUser {
    private int id;
    private String path;
    private String username;
    private String password;
    private int pagenum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public BBSUser() {
    }

    public BBSUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
