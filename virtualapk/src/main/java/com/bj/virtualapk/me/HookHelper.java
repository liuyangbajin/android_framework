package com.bj.virtualapk.me;

import android.app.Instrumentation;
import android.content.Context;
import java.lang.reflect.Field;

public class HookHelper {

    public static String TARGET_INTENsT_NAME = "info";

    public static void hookInstrumentation(Context context) throws Exception {

        Class<?> contextImplClass = Class.forName("android.app.ContextImpl");
        Field mMainThreadField = getField(contextImplClass,"mMainThread"); // 1
        Object activityThread = mMainThreadField.get(context); // 2

        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Field mInstrumentationField = getField(activityThreadClass,"mInstrumentation"); // 3

        setField(activityThreadClass,activityThread,"mInstrumentation",new InstrumentationProxy((Instrumentation) mInstrumentationField.get(activityThread),
                context.getPackageManager()));
    }

    public static Field getField(Class clazz, String name) throws Exception{
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    public static void setField(Class clazz, Object target, String name, Object value) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        field.set(target, value);
    }
}