package com.yosh.threadlock.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServ {

  public static void single() throws InterruptedException {
    // Single Thread
    // ExecutorService executorService = Executors.newSingleThreadExecutor();

    // Fixed Thread
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    // Cached Thread
    // ExecutorService executorService = Executors.newCachedThreadPool();

    for (int i = 0; i < 1000; i++) {
      executorService.execute(
          () -> {
            try {
              Thread.sleep(1_000);

              System.out.println("Thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          });
    }

    executorService.awaitTermination(1, TimeUnit.HOURS);
  }

  public static void main(String[] args) throws InterruptedException {
    single();
  }
}
