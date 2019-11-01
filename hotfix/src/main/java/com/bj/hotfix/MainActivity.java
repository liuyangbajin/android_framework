package com.bj.hotfix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 自己动手撸一个热修复
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn:

                MyLogic myLogic = new MyLogic();
                Toast.makeText(MainActivity.this, myLogic.toMsg(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
