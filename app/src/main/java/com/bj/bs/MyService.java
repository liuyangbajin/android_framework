package com.bj.bs;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {

        return new MyBinder();
    }

    class MyBinder extends com.bj.bs.MyAidl.Stub {

        @Override
        public int add(int a, int b) throws RemoteException {

            return a + b;
        }
    }
}
