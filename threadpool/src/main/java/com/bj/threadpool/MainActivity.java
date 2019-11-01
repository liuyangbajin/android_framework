package com.bj.threadpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testFiexdThreadPool();
//        testSingleThreadExecutor();
//        testCachedThreadPool();
        testScheduledThreadPool();
    }

    /**
     * 创建一个定长的线程池，可控制线程最大的并发数，超出的部分任务，会在队列中等待
     * */
    private void testFiexdThreadPool() {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        for (int n = 0; n < 10; n++){

            final int finalN = n;
            threadPool.execute(new Runnable() {

                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName()+":"+ finalN);
                }
            });
        }
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */
    private void testSingleThreadExecutor(){

        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int n = 0; n < 10; n++){

            final int finalN = n;
            threadPool.execute(new Runnable() {

                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName()+":"+ finalN);
                }
            });
        }
    }

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * */
    private void testCachedThreadPool(){

        ExecutorService threadPool = Executors.newCachedThreadPool();

        for (int n = 0; n < 10; n++){

            final int finalN = n;
            threadPool.execute(new Runnable() {

                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName()+":"+ finalN);
                }
            });
        }
    }

    /**
     * 创建一个可定期或者延时执行任务的定长线程池，支持定时及周期性任务执行。
     * */
    private void testScheduledThreadPool(){

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);

        for (int n = 0; n < 10; n++){

            final int finalN = n;
            threadPool.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName()+":"+ finalN);
                }
            }, 3, 2, TimeUnit.SECONDS);
        }
    }
}
