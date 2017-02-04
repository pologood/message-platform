package com.message.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Zhenwei on 9/24/15.
 */
public class SmsReceivingMessage implements Serializable {

    private Long id;        //主键id
    private String spCode;  //长号码(运营商号码)
    private String mobile;  //用户手机号码
    private String content; //短信内容
    private Long gatewayId; //网关名称
    private String gatewayName; //网关名称
    private Date receivingTime; //用户回复时间
    private Date createDate;    //创建时间

    private String userName;    //用户名
    private Date startDate;     //筛选开始时间
    private Date endDate;       //筛选结束时间



    //-------setter getter ---------------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGatewayId() {
        return gatewayId;
    }
    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }
    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

