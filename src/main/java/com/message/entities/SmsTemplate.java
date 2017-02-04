package com.message.entities;

import java.io.Serializable;

/**
 * Created by hpj on 2015-11-27.
 */
public class SmsTemplate implements Serializable {

    private Long    id;
    private Long    userId;             //用户id
    private String  code;               //模板编码
    private String  aliyunCode;         //对应的阿里云编码
    private String  template;           //模板内容


    //-------------非持久化属性-----------------
    private String userName;       //所属用户
    private String simTemplate;    //简单模板内容 页面展示
    private String currentPage;         //当前第几页
    private String pageSize;            //每页多少条


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAliyunCode() {
        return aliyunCode;
    }

    public void setAliyunCode(String aliyunCode) {
        this.aliyunCode = aliyunCode;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSimTemplate() {
        return simTemplate;
    }

    public void setSimTemplate(String simTemplate) {
        this.simTemplate = simTemplate;
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
