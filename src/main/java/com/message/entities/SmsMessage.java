package com.message.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hpj on 2015-08-20.
 */
public class SmsMessage extends BaseMessage {

    private String      mobile;                 //发送手机号,多个手机号请用“,” 英文逗号隔开
    private BigDecimal  price;                  //价格
    private String      templateCode;           //模板编码 若使用模板，则短信内容填写 {参数1：参数值1，参数2：参数值2}
    private String      templateParam;          //模板参数
    private String      traceId;                //第三方批次号(网关或消息平台)
    private String      comment;                //描述
    private String      customMsg;              //预留字段 第三方流水号
    private String      status;                 //发送状态
    private int         sendCounter = 0;        // sms 发送计数器  默认：0
    private Date        createDate;             //创建时间
    private boolean     callBack = false;       //是否已经回调给对接平台


    //-------------非持久化属性-----------------
    private String startDate;             //筛选开始时间
    private String endDate;               //筛选结束时间
    private String currentPage;         //当前第几页
    private String pageSize;            //每页多少条
    private String simContent;          //短信简单内容 页面展示
    private String SimComment;          //短信反馈简单展示



    //-------------setter getter-----------------

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomMsg() {
        return customMsg;
    }

    public void setCustomMsg(String customMsg) {
        this.customMsg = customMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSendCounter() {
        return sendCounter;
    }

    public void setSendCounter(int sendCounter) {
        this.sendCounter = sendCounter;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isCallBack() {
        return callBack;
    }

    public void setCallBack(boolean callBack) {
        this.callBack = callBack;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getSimContent() {
        return simContent;
    }

    public void setSimContent(String simContent) {
        this.simContent = simContent;
    }

    public String getSimComment() {
        return SimComment;
    }

    public void setSimComment(String simComment) {
        SimComment = simComment;
    }
}
