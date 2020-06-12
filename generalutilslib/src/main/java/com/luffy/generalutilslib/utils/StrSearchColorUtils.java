package com.luffy.generalutilslib.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by diaoyonglong on 2020/6/5
 *
 * @desc 指定字段变色
 */

public class StrSearchColorUtils {

    /**
     * @param color
     * @param text
     * @param keyword
     * @return
     */
    public static SpannableString matcherSearchTitle(int color, String text, String keyword) {
        String string = text.toLowerCase();
        String key = keyword.toLowerCase();
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);
        SpannableString ss = new SpannableString(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }
}
