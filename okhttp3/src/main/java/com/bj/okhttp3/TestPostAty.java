package com.bj.okhttp3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bj.okhttp3.utils.ApiUtils;
import com.bj.okhttp3.utils.MediaTypeUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * post
 * */
public class TestPostAty extends Activity {

    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_test_post);

        tv_msg = findViewById(R.id.tv_msg);

        doPost("xiaoming", "123", "打篮球");
    }

    private void doPost(String username, String pass, String hobby) {

        // 创建实例
        OkHttpClient okhttp = new OkHttpClient.Builder()
                .build();

        // 创建表单及数据
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", pass);
        map.put("hobby", hobby);
        String jsonStr = new Gson().toJson(map);

        // 指定编码为utf-8
        RequestBody formBody = RequestBody.create(MediaTypeUtils.JSON_UTF_8.value, jsonStr);

        // 创建请求实例
        Request request = new Request.Builder()
                .url(ApiUtils.TestPost)
                .post(formBody)
                .build();

        Call call = okhttp.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("lybj", "接口调用失败");
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
