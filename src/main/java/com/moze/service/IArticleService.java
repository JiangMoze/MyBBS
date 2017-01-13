package com.moze.service;

import com.moze.vo.Article;
import com.moze.vo.PageBean;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-12
 * Time: 11:23
 */
public interface IArticleService {
    public PageBean queryAll(int curPAge,int userid);
    public boolean addArticle(Article a);
    public boolean delZArticle(int id);
    public boolean delCArticle(int id);
    public String queryReplay(int id);
}
