package com.bj.hotfix;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 获取我们补丁的路径
        String dexPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/my_dex.jar";

        // 加载补丁
        try {
//            inject(dexPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载补丁
     * */
    private void inject(String dexPath) throws Exception{

        // ================= 1.获取classes的dexElements ===================

        // 反射获取 BaseDexClassLoader
        Class<?> mBaseDexClassLoader = Class.forName("dalvik.system.BaseDexClassLoader");

        // 反射获取 BaseDexClassLoader 中的 pathList
        Field pathListField = mBaseDexClassLoader.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(getClassLoader());

        // 反射获取 pathList 中的 dexElements
        Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        Object dexElements = dexElementsField.get(pathList); // pathList为dexClassLoader中的内部类



        // ================= 2.获取我们的补丁中的dexElements ===================

        String dexopt = getDir("dexopt", 0).getAbsolutePath();
        DexClassLoader mDexClassLoader = new DexClassLoader(dexPath, dexopt, dexopt, getClassLoader());

        // 反射获取加载我们补丁后 dexClassLoader 中的 pathList
        Field myPathListField = mBaseDexClassLoader.getDeclaredField("pathList");
        myPathListField.setAccessible(true);
        Object myPathList = myPathListField.get(mDexClassLoader);

        // 反射获取 加载我们补丁后，pathList 中的 dexElements
        Field myDexElementsField = myPathList.getClass().getDeclaredField("dexElements");
        myDexElementsField.setAccessible(true);
        Object myDexElements = myDexElementsField.get(myPathList);



        // ================= 3.合并数组 ===================
        Object newDexElements = mergeArray(myDexElements, dexElements);



        // ================= 4.将合并后的数组赋值给我们的app的classLoader ===================
        dexElementsField.set(pathList, newDexElements);
    }

    /**
     * 通过反射合并两个数组
     */
    private Object mergeArray(Object firstArr, Object secondArr) {
        int firstLength = Array.getLength(firstArr);
        int secondLength = Array.getLength(secondArr);
        int length = firstLength + secondLength;

        Class<?> componentType = firstArr.getClass().getComponentType();
        Object newArr = Array.newInstance(componentType, length);
        for (int i = 0; i < length; i++) {
            if (i < firstLength) {
                Array.set(newArr, i, Array.get(firstArr, i));
            } else {
                Array.set(newArr, i, Array.get(secondArr, i - firstLength));
            }
        }
        return newArr;
    }
}
