package com.message.util;

/**
 * Created by hpj on 2015-11-17.
 */
public class UUIDFactory {

    /**
     * 产生批次号
     * @param isPersonal   true  内容不同的， false 内容相同的
     * @return  同一批次内容相同 c+UUID(31), 同一批次内容不同 p+UUID(31)
     */
    public static String createBatchId(boolean isPersonal) {
        String result = isPersonal? "p"+UUIDUtil.getRandomString(31) : "c"+UUIDUtil.getRandomString(31) ;
        return result;
    }

    /**
     * 生成流水号
     * 第三方限制,益达不能超过20位,玄武需要32位
     * 网关类型(如：易达0、玄武1、建周2、电信3、移动4、亿美5、联通6、宇宙之迅7、个推8)
     */
    public static String createTraceId(int gatewayNature) {
        if (gatewayNature == 0) {
            //益达不能超过20位
            return UUIDUtil.getRandomString(20);
        }
        return UUIDUtil.getRandomString(32);
    }





}
