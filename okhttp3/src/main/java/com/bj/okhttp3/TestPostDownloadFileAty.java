package com.bj.okhttp3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bj.okhttp3.utils.ApiUtils;
import com.bj.okhttp3.utils.PermissionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载文件
 * */
public class TestPostDownloadFileAty extends Activity implements View.OnClickListener {

    ProgressBar pro;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                pro.setProgress(msg.arg1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_download_file);

        pro = findViewById(R.id.pro);
        findViewById(R.id.btn_download).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_download:

                PermissionUtils.getPermissionRead(this, new PermissionUtils.OnPermissionListener(){

                    @Override
                    public void onPermissionGranted() {

                        doDownload();
                    }

                    @Override
                    public void onPermissionDenied() {
                    }
                });
                break;
        }
    }

    /**
     * 下载文件
     * */
    private void doDownload(){

        // 接口地址
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Request rb = new Request.Builder()
                .get()
                .url(ApiUtils.TestPostDownload)
                .build();

        Call call = client.newCall(rb);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("lybj", "接口调用失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                writeFile(response);
            }
        });
    }

    /**
     * 下载文件
     * */
    private void writeFile(Response response) {

        InputStream is = null;
        FileOutputStream fos = null;
        is = response.body().byteStream();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path, "hehe.apk");
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            // 获取下载的文件的大小
            long fileSize = response.body().contentLength();
            long sum = 0;
            int porSize = 0;
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes);
                sum += len;
                porSize = (int) ((sum * 1.0f / fileSize) * 100);
                Message message = handler.obtainMessage(1);
                message.arg1 = porSize;
                handler.sendMessage(message);
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("myTag", "下载成功");
    }
}
