package com.bj.recordvideo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.bj.recordvideo.media.AvcEncoder;
import com.bj.recordvideo.media.BitmapProvider;
import com.bj.recordvideo.media.Processable;

import java.io.File;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends Activity implements Processable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_merge_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityPermissionsDispatcher.hasPermissionWithCheck(MainActivity.this);
            }
        });
    }

    private void createVideo() {
        String picPath = Environment.getExternalStorageDirectory()+"/arvideo";
        String videoPath = Environment.getExternalStorageDirectory()+"/aaaaa/"+ System.currentTimeMillis() + ".mp4";
        File[] files = new File(picPath).listFiles(pathname -> pathname.getName().endsWith(".png")
                || pathname.getName().endsWith(".PNG")
                || pathname.getName().endsWith(".JPG")
                || pathname.getName().endsWith(".jpg")
        );
        Log.e("lybj","开始执行");
        new Thread(){
            @Override
            public void run() {
                super.run();
                new AvcEncoder(new BitmapProvider(files), 16, new File(videoPath), 0, MainActivity.this).start();
            }
        }.start();
    }

    @Override
    public void onProcess(int process) {
        Log.e("lybj", "进度："+process);
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void hasPermission() {
        createVideo();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
