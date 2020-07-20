package com.luffy.generalutilslib.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.luffy.generalutilslib.BuildConfig;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lvlufei on 2019/3/25
 *
 * @desc 版本升级-辅助工具
 */
public class VersionUpgradeUtils {

    private VersionUpgradeUtils() {
    }

    public static VersionUpgradeUtils getInstance() {
        return VersionUpgradeUtilsHelper.mVersionUpgradeUtils;
    }

    private static class VersionUpgradeUtilsHelper {
        private static final VersionUpgradeUtils mVersionUpgradeUtils = new VersionUpgradeUtils();
    }

    /**
     * 外部升级（应用宝市场更新）
     *
     * @param mContext 上下文
     * @param url      链接地址
     */
    public void externalUpgrade(Context mContext, String url) {
        if (SystemUtils.getInstance().isInstall(mContext, "com.tencent.android.qqdownloader")) {
            SystemUtils.getInstance().openAppDetail(mContext, AppUtils.getInstance().getPackName(mContext), "com.tencent.android.qqdownloader");
        } else {
            IntentUtils.getInstance().openBrowser(mContext, url);
        }
    }

    /**
     * 外部升级（下载apk）
     *
     * @param mContext 是否有效
     * @param uri      下载地址
     */
    public void internalUpgrade(final Context mContext, final String uri) {
        final ProgressDialog mProgressDialog;    //进度条对话框
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage("正在下载更新");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = downloadApk(uri, mProgressDialog);
                    sleep(2000);
                    installApk(mContext, file);
                    mProgressDialog.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 下载apk文件
     *
     * @param uri             下载地址
     * @param mProgressDialog 进度条弹框
     * @return 文件
     * @throws Exception 异常
     */
    public File downloadApk(String uri, ProgressDialog mProgressDialog) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            mProgressDialog.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), time + "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                mProgressDialog.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    /**
     * 安装Apk
     *
     * @param mContext 上下文
     * @param file     文件
     */
    public void installApk(Context mContext, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileprovider", file); //与manifest中定义的provider中的authorities保持一致
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }
}
