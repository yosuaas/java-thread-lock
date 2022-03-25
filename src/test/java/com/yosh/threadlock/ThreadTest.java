package com.yosh.threadlock;

import org.junit.jupiter.api.Test;

public class ThreadTest {

  @Test
  void testThreadName() {
    String threadName = Thread.currentThread().getName();
    System.out.println(threadName);
  }

  @Test
  void testCreateThread() {
    Runnable runnable =
        () -> {
          try {
            Thread.sleep(5_000L);

            System.out.println("Run in thread " + Thread.currentThread().getName());
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        };

    Thread thread = new Thread(runnable);
    thread.setName("Yosh");
    thread.start();
  }
}
