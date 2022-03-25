package com.yosh.threadlock.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {

  public static void blockingQueue() throws InterruptedException {
    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    ExecutorService service = Executors.newFixedThreadPool(10);

    service.execute(
        () -> {
          try {
            String data = queue.take();
            System.out.println("Receive data " + data);
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        });

    for (int i = 0; i < 100; i++) {
      service.execute(
          () -> {
            try {
              queue.put("Data");
              System.out.println("Success put data");
            } catch (Exception e) {
              e.printStackTrace();
            }
          });
    }

    service.awaitTermination(1, TimeUnit.HOURS);
  }
}
