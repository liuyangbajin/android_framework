package com.bj.okhttp3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.zhouyou.http.interceptor.GzipRequestInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Get
 * */
public class TestGetAty extends Activity {

    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_test_get);

        tv_msg = findViewById(R.id.tv_msg);

        doGet();
    }

    /**
     * 获取网络数据
     * */
    private void doGet() {

        OkHttpClient mClient =new OkHttpClient.Builder() // 构建者模式，创建实例
                .connectTimeout(20, TimeUnit.SECONDS) // 设置连接超时时间
                .build();

        Request mRequest = new Request.Builder() // 构建者模式，创建请求信息
                .get()
                .url("https://www.baidu.com")
                .build();

        Call call = mClient.newCall(mRequest); // 将request转换成call

        call.enqueue(new Callback() { // 执行call

            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String strByNet = response.body().string();

                // 切换到主线程
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        tv_msg.setText(strByNet);
                    }
                });
            }
        });
    }
}
