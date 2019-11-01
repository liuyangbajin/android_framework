package com.bj.blockcanary;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.internal.BlockInfo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class AppBlockCanaryContext extends BlockCanaryContext {

    /**
     * 获取应用版本信息
     */
    public String provideQualifier() {

        return "unknown";
    }

    /**
     * 获取用户uid
     */
    public String provideUid() {
        return "uid";
    }

    /**
     * 设置网络类型
     */
    public String provideNetworkType() {

        return "4G";
    }

    /**
     * 设置监控时长, -1为一直监控
     */
    public int provideMonitorDuration() {
        return -1;
    }

    /**
     * 配置阈值（以毫秒为单位），拦截超过该阈值的信息
     */
    public int provideBlockThreshold() {

        return 800;
    }

    /**
     * Thread stack dump interval, use when block happens, BlockCanary will dump on main thread
     * stack according to current sample cycle.
     * <p>
     * Because the implementation mechanism of Looper, real dump interval would be longer than
     * the period specified here (especially when cpu is busier).
     * </p>
     *
     * @return dump interval (in millis)
     */
    public int provideDumpInterval() {
        return provideBlockThreshold();
    }

    /**
     * 设置log保存地址
     */
    public String providePath() {

        return Environment.getExternalStorageDirectory()+ "/blockcanary/";
    }

    /**
     * 设置是否在桌面展示.
     */
    public boolean displayNotification() {
        return false;
    }

    /**
     * 将文件打包成zip
     */
    public boolean zip(File[] src, File dest) {
        return false;
    }

    /**
     * Implement in your project, bundled log files.
     *
     * @param zippedFile zipped file
     */
    public void upload(File zippedFile) {
        throw new UnsupportedOperationException();
    }

    /**
     * Packages that developer concern, by default it uses process name,
     * put high priority one in pre-order.
     *
     * @return null if simply concern only package with process name.
     */
    public List<String> concernPackages() {
        return null;
    }


    /**
     * 提供白名单，白名单中的条目将不会显示在用户界面列表中
     */
    public List<String> provideWhiteList() {
        LinkedList<String> whiteList = new LinkedList<>();
        whiteList.add("org.chromium");
        return whiteList;
    }

    /**
     * Whether to delete files whose stack is in white list, used with white-list.
     *
     * @return true if delete, false it not.
     */
    public boolean deleteFilesInWhiteList() {
        return true;
    }

    /**
     * 自定义拦截器, 可以提供自己的操作
     */
    public void onBlock(Context context, BlockInfo blockInfo) {

        Log.i("lybj", blockInfo.toString());
    }
}
