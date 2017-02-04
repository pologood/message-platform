package com.message.entities.export;

import java.util.List;

/**
 * Created by hpj on 2015-12-01.
 */
public class ConsumptionHSSFWorkbook extends BaseHSSFWorkbook {

    private static String[] title = {"操作时间", "用户名", "消费金额", "发送短信价格", "短信通道"};

    public ConsumptionHSSFWorkbook() {
        super(title);
    }

    @Override
    public void paddingCell(List list) {
        /*HSSFWorkbook workbook = getHssfWorkbook();
        HSSFSheet sheet = workbook.getSheet(Constant.COMMON_SHEET);
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, 4500);
        }
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            ConsumptionRecord record = (ConsumptionRecord)list.get(i);
            row.createCell(0).setCellValue(null == record.getCreateDate()? "--" :DateUtils.format(record.getCreateDate()));
            row.createCell(1).setCellValue(null == record.getUserName()? "--": record.getUserName());
            BigDecimal costVal = new BigDecimal(0);
            if (null != record.getCost() && null != record.getRefund()){
                costVal = record.getCost().subtract(record.getRefund());
            }

            row.createCell(2).setCellValue(null == costVal ? "--" :costVal.toString());
            row.createCell(3).setCellValue(null == record.getPrice()? "--" : record.getPrice().toString());
            row.createCell(4).setCellValue(null == record.getDescription()? "--" :record.getDescription());
        }*/
    }

}
