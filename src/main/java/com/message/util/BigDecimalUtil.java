package com.message.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by hpj on 2015-11-12.
 */
public class BigDecimalUtil {

    /**
     * bigDecimal乘法，默认取小数点后两位
     * @return
     */
    public static BigDecimal multiply(BigDecimal mul1, BigDecimal mul2) {
        try {
            return mul1.multiply(mul2).setScale(2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.valueOf(0);
        }
    }

}
