package com.moze.service;

import com.moze.dao.IUserDAO;
import com.moze.dao.UserDAOImpl;
import com.moze.vo.BBSUser;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-12
 * Time: 11:27
 */
public class UserServiceImpl implements IUserService {
    private IUserDAO dao=new UserDAOImpl();
    @Override
    public BBSUser login(BBSUser user){
        return dao.login(user);
    }
    private String savePath;
    private Map<String,String> types=new HashMap<String, String>();
    public UserServiceImpl(){
        types.put("image/jpeg",".jpg");
        types.put("image/gif", ".gif");
        types.put("image/x-ms-bmp", ".bmp");
        types.put("image/png", ".png");
    }


    @Override
    public BBSUser uploadPid(String tpath, FileItemIterator it) {
        BBSUser user=new BBSUser();
        try {
            while (it.hasNext()){
                FileItemStream fis=it.next();
                String name=fis.getFieldName();
                InputStream os=fis.openStream();
                if((!fis.isFormField())&&(fis.getName().length()>0)){
                    String type=fis.getContentType();
                    savePath=""+System.getProperty("file.separator")+"uoload";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean addUser(BBSUser user) {
        return dao.addUser(user);
    }

    @Override
    public byte[] queryPicByid(int id) {
        return dao.queryPicByid(id);
    }
}
