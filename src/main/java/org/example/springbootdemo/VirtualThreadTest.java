package org.example.springbootdemo;

import org.springframework.lang.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadTest {
    static int taskNum = 3000;

    public static void main(String[] args) {
        runVirtualThread();
        runPlatformThread(Runtime.getRuntime().availableProcessors());
        runPlatformThread(200);
        runPlatformThread(500);
    }

    private static void runVirtualThread() {
        long begin = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskNum; i++) {
                executorService.submit(() -> {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        System.out.printf("virtual thread run %d tasks, consumed %d ms, avg %.2f ms\n"
                , taskNum, System.currentTimeMillis() - begin, 1.0 * (System.currentTimeMillis() - begin) / taskNum);
    }

    private static void runPlatformThread(@NonNull Integer threadNum) {
        long begin = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newFixedThreadPool(threadNum)) {
            for (int i = 0; i < taskNum; i++) {
                executorService.submit(() -> {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        System.out.printf("%d platform threads run %d tasks, consumed %d ms, avg %.2f ms\n",
                threadNum,
                taskNum,
                System.currentTimeMillis() - begin,
                1.0 * (System.currentTimeMillis() - begin) / taskNum);
    }
}
