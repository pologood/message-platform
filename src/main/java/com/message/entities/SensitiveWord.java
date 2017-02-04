package com.message.entities;

import java.io.Serializable;

/**
 * Created by  hpj
 * @desc 短信敏感字
 */
public class SensitiveWord implements Serializable {

    private Long id;                //id
    private String word;            //敏感字


    private String currentPage;         //当前第几页
    private String pageSize;            //每页多少条

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
