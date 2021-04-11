package com.bj.virtualapk;

import android.app.Application;
import android.content.Context;

import com.bj.virtualapk.me.HookHelper;
import com.didi.virtualapk.PluginManager;

public class MyApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {

        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();


//        try {
//            HookHelper.hookInstrumentation(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        getStubActivity
//        }
    }
}
