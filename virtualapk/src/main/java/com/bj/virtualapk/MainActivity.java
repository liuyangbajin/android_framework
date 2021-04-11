package com.bj.virtualapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.didi.virtualapk.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pluginPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/Test.apk");
        File plugin = new File(pluginPath);
        try {
            PluginManager.getInstance(MainActivity.this).loadPlugin(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.tv01).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClassName("com.bj.virtualapk_plugin", "com.bj.virtualapk_plugin.MainActivity");
                startActivity(intent);
            }
        });
    }
}
