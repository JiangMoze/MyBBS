package com.moze.dao;

import com.moze.vo.Article;
import com.moze.vo.PageBean;
import com.moze.vo.ReArticle;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-09
 * Time: 20:08
 */
public interface IArticleDAO {
    public PageBean queryAll(int cutPage,int useid);
    public boolean addArticle(Article a);
    public boolean delZArticle(int id);
    public boolean delCArticle(int id);
    public ReArticle queryReplay(int id);

}
