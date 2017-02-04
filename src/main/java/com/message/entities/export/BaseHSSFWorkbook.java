package com.message.entities.export;

import com.message.util.DownloadUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by hpj on 2015-11-30.
 */
public abstract class BaseHSSFWorkbook implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(BaseHSSFWorkbook.class);

    private HSSFWorkbook hssfWorkbook;

    public BaseHSSFWorkbook(String[] title) {
        //创建一个导出的excel
        this.hssfWorkbook = createHSSFWorkbook(title);
    }

    /**
     * 创建一个下载的excel
     *
     * @param title
     */
    private HSSFWorkbook createHSSFWorkbook(String[] title) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置头部
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        return wb;
    }

    protected abstract void paddingCell(List list);

    public HSSFWorkbook getHssfWorkbook() {
        return hssfWorkbook;
    }

    public void setHssfWorkbook(HSSFWorkbook hssfWorkbook) {
        this.hssfWorkbook = hssfWorkbook;
    }

    /**
     * 充值记录下载
     *
     * @param response
     * @param info
     * @param workbook
     * @throws IOException
     */
    public void downloadExcel(HttpServletResponse response, DownloadInfo info, HSSFWorkbook workbook) {
        try (OutputStream outputStream = response.getOutputStream()) {
            String datePeriod = DownloadUtils.createDatePeriodName(info.getStartDate(), info.getEndDate());
            String fileName = StringUtils.join(info.getFileName(), datePeriod, ".xls");
            String realName = new String(fileName.getBytes("gb2312"), "iso8859-1");
            response.setHeader("Content-disposition", StringUtils.join("attachment;filename=", realName));
            response.setContentType("application/x-msdownload;charset=UTF-8");
            workbook.write(outputStream);
            outputStream.flush();
            workbook.close();
        } catch (Exception e) {
            logger.error("downloadExcel方法发生异常" + e.getMessage());
        }

    }

    public static void downloadZIP(HttpServletResponse response, List<String> srcFile, String tempPath, String fileName) {
        //下载临时文件
        File zipFile = null;
        try {
            zipFile = new File(StringUtils.join(tempPath, fileName));
            InputStream fis = new BufferedInputStream(new FileInputStream(zipFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            String realName = new String(fileName.getBytes("gb2312"), "iso8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + realName);
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            logger.error("downloadZIP方法出错" + e.getMessage());
        } finally {
            //下载完毕后删除临时文件
            for (int i = 0; i < srcFile.size(); i++) {
                new File(srcFile.get(i)).delete();
            }
            if (zipFile.exists()) {
                zipFile.delete();
            }
        }
    }

    /**
     * 生成临时excel到本地
     *
     * @param hssfWorkbook
     * @return
     * @throws IOException
     */
    public void createExcelToLocal(HSSFWorkbook hssfWorkbook, String fileName) {
        try (FileOutputStream output = new FileOutputStream(new File(fileName))) {
            hssfWorkbook.write(output);
            output.flush();
        } catch (Exception e) {
            logger.error("createExcelToLocal方法抛错" + e.getMessage());
        }
    }

}
