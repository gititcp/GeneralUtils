package com.luffy.generalutilslib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lvlufei on 2018/7/18
 *
 * @desc 正则表达式-辅助工具
 */
public class RegexUtils {
    private RegexUtils() {
    }

    public static RegexUtils getInstance() {
        return RegexUtilsHelper.mRegexUtils;
    }

    private static class RegexUtilsHelper {
        private static final RegexUtils mRegexUtils = new RegexUtils();
    }

    public static class Regex {
        /**
         * 中文-正则
         */
        public static final String CHINESE_REGEX = "[\u4e00-\u9fa5]+";

        /**
         * 身份证-正则
         */
        public static final String IDCARD_REGEX = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

        /**
         * 手机号-正则
         */
        public static final String PHONE_REGEX = "^[1][0-9]+\\d{9}";

        /**
         * 密码-正则
         */
        public static final String PASSWORD_REGEX = "^[0-9A-Za-z]{6,20}$";

        /**
         * 企业税号-正则
         */
        public static final String TAX_NO_REGEX = "^[0-9A-Za-z]{15,20}$";

        /**
         * 验证码-正则
         */
        public static final String VERIFICATIONCODE_REGEX = "[0-9]{6}";

        /**
         * 邮箱-正则
         */
        public static final String EMAIL_REGEX = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

        /**
         * 网址-正则
         */
        public static final String URL_REGEX = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";

        /**
         * 电话号码-正则
         */
        public static final String TELEPHONE_REGEX = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";

    }

    /**
     * 验证
     *
     * @param content 上下文
     * @return 是否匹配
     */
    public boolean isMatcher(String content, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }

    /**
     * 邮箱-验证
     *
     * @param content 上下文
     * @return 是否为邮箱
     */
    public boolean isEmail(String content) {
        if (!ValidUtils.getInstance().isValid(content)) {
            return true;
        }
        Pattern pattern = Pattern.compile(Regex.EMAIL_REGEX);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }
}
