package com.moze.dao;

import com.moze.vo.BBSUser;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-09
 * Time: 20:11
 */
public interface IUserDAO {
    public BBSUser login(BBSUser user);
    public boolean addUser(BBSUser user);
    public byte[] queryPicByid(int id);
}
