package com.bj.bs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent in = new Intent(this, MyService.class);
        bindService(in, new MyConn(), BIND_AUTO_CREATE);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    int result = myAidl.add(4,5);
                    Log.i("lybj", result+"");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        Looper.getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() {

            @Override
            public boolean queueIdle() {

                // 耗时操作
                return false;
            }
        });
    }

    com.bj.bs.MyAidl myAidl;
    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            myAidl = com.bj.bs.MyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
