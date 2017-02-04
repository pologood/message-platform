package com.message.entities.export;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hpj on 2015-12-01.
 */
public class DownloadInfo implements Serializable {

    private String fileName;

    private Date startDate;

    private Date endDate;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
