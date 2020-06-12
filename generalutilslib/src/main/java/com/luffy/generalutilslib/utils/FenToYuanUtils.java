package com.luffy.generalutilslib.utils;

import java.math.BigDecimal;

/**
 * Created by diaoyonglong on 2020/6/5
 *
 * @desc 分转元
 */

public class FenToYuanUtils {
    /**
     * 转换为bigDecimal在toString
     *
     * @return
     */
    public static String changeFentoYuan(int price) {
        return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
    }
}
