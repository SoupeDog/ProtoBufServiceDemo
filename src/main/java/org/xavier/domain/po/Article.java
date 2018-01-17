package org.xavier.domain.po;

import org.xavier.domain.protobufObj.ProtoBuf;

/**
 * 描述信息：<br/>
 *
 * @author ProtoBuf
 * @version 1.0
 * @date 2018.01.16
 * @since Jdk 1.8
 */
public class Article {
    private String title;
    private Boolean sellWell;

    public Article() {
    }

    public Article(ProtoBuf.Article article) {
        title = article.getTitle();
        sellWell = article.getSellWell();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSellWell() {
        return sellWell;
    }

    public void setSellWell(Boolean sellWell) {
        this.sellWell = sellWell;
    }
}
