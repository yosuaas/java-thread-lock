package com.yosh.threadlock.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

  public static void threadPool() throws InterruptedException {

    RejectedExecutionHandler handler =
        new RejectedExecutionHandler() {
          @Override
          public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("Task " + r + " rejected because out of capacity");
          }
        };

    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
    ThreadPoolExecutor threadpool =
        new ThreadPoolExecutor(2, 10, 1, TimeUnit.MINUTES, queue, handler);

    for (int i = 0; i < 30; i++) {
      threadpool.execute(
          () -> {
            try {
              Thread.sleep(1_000);

              System.out.println("Thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          });
    }
    Thread.sleep(10_000);
  }

  public static void main(String[] args) throws InterruptedException {
    threadPool();
  }
}
