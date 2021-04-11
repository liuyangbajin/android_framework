package com.bj.okhttp3;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bj.okhttp3.utils.ApiUtils;
import com.bj.okhttp3.utils.MediaTypeUtils;
import com.bj.okhttp3.utils.PermissionUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 上传文件
 * */
public class TestPostUploadFileAty extends Activity implements View.OnClickListener {

    TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_upload_file);

        tv_msg = findViewById(R.id.tv_msg);
        findViewById(R.id.btn_upload).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_upload:

                PermissionUtils.getPermissionRead(this, new PermissionUtils.OnPermissionListener(){

                    @Override
                    public void onPermissionGranted() {

                        File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile().toString(), "demo.txt");
                        if(file.exists()){

                            doUpload(file, "123", "附带的信息");
                        }else {
                            System.out.println("文件不存在");
                        }
                    }

                    @Override
                    public void onPermissionDenied() {
                    }
                });
                break;
        }
    }

    /**
     * 上传文件
     * */
    private void doUpload(File file, String userId, String msg){

        // 接口地址
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        RequestBody fileRequestBody1 = RequestBody.create(MediaTypeUtils.UPLOAD_FILE.value, file);

        // 可传多个
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userId", userId)
                .addFormDataPart("msg", msg)
                .addFormDataPart("file", "myFileName", fileRequestBody1)
                .build();

        Request rb = new Request.Builder()
                .url(ApiUtils.TestPostUpload)
                .post(body)
                .build();

        Call call = client.newCall(rb);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("lybj", "接口调用失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        tv_msg.setText(string);
                    }
                });
            }
        });
    }
}
