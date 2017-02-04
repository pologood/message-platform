package com.message.entities;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by hpj
 */
public class BaseMessage implements Serializable {

    private static final long serialVersionUID = 2894015754877424575L;

    private Long    id;                 //主键id
    private String  content;            //消息内容
    private String  sign;               //传入签名（一般为自定义）
    private String  sendType;           //发送类型（marketing; verify;）
    private String  batchId;            //批处理id
    private Date    executeTime;        //发送时间 (如果定时发送)
    private Long    userId;             //发送信息用户id



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}
