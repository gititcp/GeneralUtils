package com.luffy.generalutilslib.utils.notch.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import java.lang.reflect.Method;

@TargetApi(Build.VERSION_CODES.O)
public class MiNotchScreen implements INotchScreen {

    @Override
    public boolean hasNotch(Context context) {
        try {
            Method getInt = Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, int.class);
            int notch = (int) getInt.invoke(null, "ro.miui.notch", 0);
            return notch == 1;
        } catch (Throwable ignore) {
        }
        return false;
    }

    @Override
    public void getNotchWidthHeight(Context context, NotchSizeCallback callback) {
        int[] notchWidthHeight = {getNotchWidth(context), getNotchHeight(context)};
        callback.onResult(notchWidthHeight);
    }

    private static int getNotchHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    private static int getNotchWidth(Context context) {
        int resourceId = context.getResources().getIdentifier("notch_width", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
