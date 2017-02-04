package com.message.util;

/**
 * Created by hpj on 2015-09-16.
 */
public class PageUtils {

    /**
     * 获取正确的页码
     *
     * @param num
     * @return
     */
    public static Integer getCorrectCurrentPage(String num) {
        try {
            return Integer.valueOf(num);
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * 获取正确的条数
     *
     * @param num
     * @return
     */
    public static Integer getCorrectCurrentPageSize(String num) {
        try {
            return Integer.valueOf(num);
        } catch (Exception e) {
            return 20;
        }
    }

}
