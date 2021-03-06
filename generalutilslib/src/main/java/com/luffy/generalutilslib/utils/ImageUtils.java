package com.luffy.generalutilslib.utils;

import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 图片-辅助工具
 */
public class ImageUtils {

    private ImageUtils() {
    }

    public static ImageUtils getInstance() {
        return ImageUtilsHelper.mImageUtils;
    }

    private static class ImageUtilsHelper {
        private static final ImageUtils mImageUtils = new ImageUtils();
    }

    /**
     * 根据InputStream获取图片实际的宽度和高度
     *
     * @param imageStream 输入流
     * @return 图片大小
     */
    public ImageSize getImageSize(InputStream imageStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(imageStream, null, options);
        return new ImageSize(options.outWidth, options.outHeight);
    }

    /**
     * 图片大小
     */
    public static class ImageSize {
        int width;
        int height;

        public ImageSize() {
        }

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "ImageSize{" +
                    "width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    /**
     * 计算图片大小
     *
     * @param srcSize    原图片大小
     * @param targetSize 目标图片大小
     * @return 图片大小
     */
    public int calculateInSampleSize(ImageSize srcSize, ImageSize targetSize) {
        // 源图片的宽度
        int width = srcSize.width;
        int height = srcSize.height;
        int inSampleSize = 1;

        int reqWidth = targetSize.width;
        int reqHeight = targetSize.height;

        if (width > reqWidth && height > reqHeight) {
            // 计算出实际宽度和目标宽度的比率
            int widthRatio = Math.round((float) width / (float) reqWidth);
            int heightRatio = Math.round((float) height / (float) reqHeight);
            inSampleSize = Math.max(widthRatio, heightRatio);
        }
        return inSampleSize;
    }

    /**
     * 根据ImageView获适当的压缩的宽和高
     *
     * @param view View
     * @return 图片大小
     */
    public ImageSize getImageViewSize(View view) {
        ImageSize imageSize = new ImageSize();
        imageSize.width = getExpectWidth(view);
        imageSize.height = getExpectHeight(view);
        return imageSize;
    }

    /**
     * 根据view获得期望的高度
     *
     * @param view View
     * @return 期望高度
     */
    private int getExpectHeight(View view) {
        int height = 0;
        if (view == null) return 0;
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        //如果是WRAP_CONTENT，此时图片还没加载，getWidth根本无效
        if (params != null && params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
            height = view.getWidth(); // 获得实际的宽度
        }
        if (height <= 0 && params != null) {
            height = params.height; // 获得布局文件中的声明的宽度
        }
        if (height <= 0) {
            height = getImageViewFieldValue(view, "mMaxHeight");// 获得设置的最大的宽度
        }
        //如果宽度还是没有获取到，憋大招，使用屏幕的宽度
        if (height <= 0) {
            DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
            height = displayMetrics.heightPixels;
        }
        return height;
    }

    /**
     * 根据view获得期望的宽度
     *
     * @param view View
     * @return 期望宽度
     */
    private int getExpectWidth(View view) {
        int width = 0;
        if (view == null) return 0;
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        //如果是WRAP_CONTENT，此时图片还没加载，getWidth根本无效
        if (params != null && params.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
            width = view.getWidth(); // 获得实际的宽度
        }
        if (width <= 0 && params != null) {
            width = params.width; // 获得布局文件中的声明的宽度
        }
        if (width <= 0) {
            width = getImageViewFieldValue(view, "mMaxWidth");// 获得设置的最大的宽度
        }
        //如果宽度还是没有获取到，憋大招，使用屏幕的宽度
        if (width <= 0) {
            DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
            width = displayMetrics.widthPixels;
        }
        return width;
    }

    /**
     * 通过反射获取imageview的某个属性值
     *
     * @param object    Object
     * @param fieldName 文件名称
     * @return 属性值
     */
    private int getImageViewFieldValue(Object object, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = field.getInt(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
