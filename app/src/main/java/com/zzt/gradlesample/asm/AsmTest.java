package com.zzt.gradlesample.asm;

import android.util.Log;

/**
 * @author: zeting
 * @date: 2023/10/23
 */
public class AsmTest {
    public static final String TAG = "asmTest";

    private static class InnerClass{
        private  static final AsmTest INSTANCE = new AsmTest();
    }
    private AsmTest() {}
    public static AsmTest getInstance() {
        return InnerClass.INSTANCE;
    }

    private Thread mThread;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.v(TAG, "runnable run on " + Thread.currentThread().getName());
        }
    };

    public void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.v(TAG, "runnable run test");
            }
        }, "test").start();
    }

    public void test2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.v(TAG, "runnable run test2");
            }
        };
        new Thread(runnable).start();
    }

    public void test3() {
        Thread thread = new Thread(runnable);
        thread.setName("test3");
        thread.start();
    }

    public void test4() {
        mThread = new Thread(runnable, "test4");
        mThread.start();
    }

    public void test5() {
        new Thread(runnable).start();
    }

    public void test6(Runnable runnable) {
        new Thread(runnable).start();
    }

    public void test7(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("test7");
        thread.start();
    }

    public void test8() {
        new Thread("test8").start();
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable);
    }
}