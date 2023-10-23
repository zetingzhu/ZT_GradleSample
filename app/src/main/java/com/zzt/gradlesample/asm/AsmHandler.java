package com.zzt.gradlesample.asm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zeting
 * @date: 2023/10/23
 */
public class AsmHandler {

    private static AsmHandler instance;

    private ExecutorService executors;

    private AsmHandler() {
        executors = Executors.newFixedThreadPool(3);
    }

    public static AsmHandler instance() {
        if (instance == null) {
            instance = new AsmHandler();
        }
        return instance;
    }

    public static void handleNewThread(Runnable runnable) {
        instance().executors.execute(runnable);
    }

}