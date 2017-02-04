package com.message.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Zhenwei on 10/20/15.
 */
public class ConsumptionRecord implements Serializable {

    private static final long serialVersionUID = -4317489960369222270L;

    private Long id;            //id
    private Long userId;        //用户主键id
    private BigDecimal cost;    //发送扣款
    private BigDecimal refund;  //失败退款
    private String batchId;     //批次号(批量发送时才有)
    private String traceId;     //交易流水号
    private Date createDate;    //创建日期
    private Long gatewayId;     //发送网关id

    private String userName;    //用户名
    private Date startDate;     //筛选开始时间
    private Date endDate;       //筛选结束时间
    private BigDecimal price;
    private String description; //网关描述


    private String currentPage;         //当前第几页
    private String pageSize;            //每页多少条



    public Long getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }

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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getRefund() {
        return refund;
    }

    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
