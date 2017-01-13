package com.moze.service;

import com.moze.vo.BBSUser;
import org.apache.commons.fileupload.FileItemIterator;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-12
 * Time: 11:23
 */
public interface IUserService {
    public BBSUser login(BBSUser user);
    public BBSUser uploadPid(String tpath, FileItemIterator it);
    public boolean addUser(BBSUser user);
    public byte[] queryPicByid(int id);

}
