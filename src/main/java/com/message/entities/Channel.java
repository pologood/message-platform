package com.message.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hpj
 */
public class Channel implements Serializable {

    private static final long serialVersionUID = -7936098230230668351L;

    private Long    id;                 //主键id
    private String  channelName;        //通道名称
    private String  description;        //通道描述
    private String  type;               //通道类型
    private boolean enable;             //通道是否可用
    private Date    createDate;         //创建时间

    //-非数据库字段
    private boolean selected;           //是否被选中
    private long    sendingCount;       //发送数量
    private String currentPage;         //当前第几页
    private String pageSize;            //每页多少条


    /**-----------setter getter---------------------------**/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public long getSendingCount() {
        return sendingCount;
    }

    public void setSendingCount(long sendingCount) {
        this.sendingCount = sendingCount;
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
