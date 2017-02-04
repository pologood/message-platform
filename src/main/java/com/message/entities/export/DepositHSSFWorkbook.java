package com.message.entities.export;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by hpj on 2015-11-30.
 */
public class DepositHSSFWorkbook extends BaseHSSFWorkbook {

    private static String[] title = {"充值时间", "用户名", "充值金额", "充值类型", "充值网关"};

    public DepositHSSFWorkbook() {
        super(title);
    }

    @Override
    public void paddingCell(List list) {
        HSSFWorkbook workbook = getHssfWorkbook();
        /*HSSFSheet sheet = workbook.getSheet(Constant.COMMON_SHEET);
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, 4500);
        }
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            DepositAccount depositAccount = (DepositAccount)list.get(i);
            row.createCell(0).setCellValue(null == depositAccount.getCreateDate()? "--" : DateUtils.format(depositAccount.getCreateDate()));
            row.createCell(1).setCellValue(null == depositAccount.getUserName()? "--" : depositAccount.getUserName());
            row.createCell(2).setCellValue(null == depositAccount.getAmount()? "--" : depositAccount.getAmount().toString());
            row.createCell(3).setCellValue(null == depositAccount.getDepositType()? "--" : getDepositName(depositAccount.getDepositType()));
            row.createCell(4).setCellValue(null == depositAccount.getGatewayName()? "-- ": depositAccount.getGatewayName());
        }*/
    }


}
