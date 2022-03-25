package com.yosh.threadlock;

public class ThreadLock {

  public static void testCreateThread() {
    Runnable runnable =
        () -> {
          try {
            Thread.sleep(2_000L);

            System.out.println("Run in thread " + Thread.currentThread().getName());
            System.out.println("Done 1");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        };
    Runnable runnable2 =
        () -> {
          try {
            Thread.sleep(4_000L);

            System.out.println("Run in thread " + Thread.currentThread().getName());
            System.out.println("Done 2");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        };

    Thread thread = new Thread(runnable);
    thread.start();

    Thread thread2 = new Thread(runnable2);
    thread2.start();

    try {
      thread.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    testCreateThread();
  }
}
