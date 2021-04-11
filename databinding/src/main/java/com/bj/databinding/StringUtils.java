package com.bj.databinding;


import android.view.View;

public class StringUtils{

    private OnMsgListener onMsgListener;

    interface OnMsgListener{

        void onMsg(View textView, String msg);
    }

    public OnMsgListener getOnMsgListener() {
        return onMsgListener;
    }

    public void setOnMsgListener(OnMsgListener onMsgListener) {
        this.onMsgListener = onMsgListener;
    }

    /**
     * 触发自定义事件
     * */
    public void showMsg(){

        if(onMsgListener != null){

        }
    }
}
