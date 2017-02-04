package com.message.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by hpj on 2015-12-02.
 */
public class DownloadUtils {

    private static Logger logger = LoggerFactory.getLogger(DownloadUtils.class);

    /**
     * 生成zip文件
     *
     * @param srcFile
     * @param zipFile
     */
    public static void createZipFiles(List<File> srcFile, File zipFile) {
        byte[] buf = new byte[1024];
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < srcFile.size(); i++) {
                File file = srcFile.get(i);
                FileInputStream in = new FileInputStream(file);
                out.putNextEntry(new ZipEntry(file.getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
        } catch (IOException e) {
            logger.error("zipFiles方法发生异常" + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建文件名字中的时间段，格式为(20151101-20151201)
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String createDatePeriodName(Date startDate, Date endDate) {
        String startTime;
        String endTime;
        if (startDate == null) {
            Date date = DateUtils.addMonth(new Date(), -1);
            startTime = DateUtils.format(date, DateUtils.FORMAT_INT_DATE);
        } else {
            startTime = DateUtils.format(startDate, DateUtils.FORMAT_INT_DATE);
        }
        if (endDate == null) {
            endTime = DateUtils.format(new Date(), DateUtils.FORMAT_INT_DATE);
        } else {
            endTime = DateUtils.format(endDate, DateUtils.FORMAT_INT_DATE);
        }
        return StringUtils.join("(", startTime, "-", endTime, ")");
    }

    /**
     * 下载zip包
     *
     * @param fileNameList
     * @param fileName
     */
    public static void createZIP(List<String> fileNameList, String fileName) {
        File zipFile = new File(fileName);
        List<File> srcFile = new ArrayList<File>();
        for (String s : fileNameList) {
            srcFile.add(new File(s));
        }
        //生成临时zip文件
        createZipFiles(srcFile, zipFile);
    }
}
