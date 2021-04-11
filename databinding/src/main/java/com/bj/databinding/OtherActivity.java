package com.bj.databinding;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;


/**
 * @author: lybj
 * @date: 2019-12-29
 * @Description:
 */
public class OtherActivity extends Activity {

    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_other);
//        OtherActivityBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_other);

    }
}
