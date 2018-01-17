package org.xavier.domain.po;

import org.xavier.domain.protobufObj.ProtoBuf;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息：<br/>
 *
 * @author ProtoBuf
 * @version 1.0
 * @date 2018.01.16
 * @since Jdk 1.8
 */
public class User {
    private String uName;
    private ArrayList<Article> articles;

    public User() {
    }

    public User(ProtoBuf.User user) {
        uName = user.getUName();
        articles = protoBufToPO(user.getArticlesList());
    }

    public ArrayList<Article> protoBufToPO(List<ProtoBuf.Article> articles) {
        ArrayList<Article> result = new ArrayList();
        Article item;
        for (ProtoBuf.Article temp : articles) {
            item = new Article(temp);
            result.add(item);
        }
        return result;
    }


    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
