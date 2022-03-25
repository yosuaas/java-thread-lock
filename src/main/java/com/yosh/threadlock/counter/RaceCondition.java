package com.yosh.threadlock.counter;

public class RaceCondition {

  private static Counter counter = new Counter();

  private static SynchronizedCounter synchronizedCounter = new SynchronizedCounter();

  public static void sequential() {
    for (int i = 0; i < 3_000_000; i++) {
      counter.increment();
    }

    System.out.println(counter.getValue());
  }

  public static void raceCondition() throws InterruptedException {
    Runnable runnable =
        () -> {
          for (int i = 0; i < 1_000_000; i++) {
            counter.increment();
          }
        };

    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    Thread thread3 = new Thread(runnable);

    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();

    System.out.println(counter.getValue());
  }

  public static void notRaceCondition() throws InterruptedException {
    Runnable runnable =
        () -> {
          for (int i = 0; i < 1_000_000; i++) {
            synchronizedCounter.increment();
          }
        };

    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    Thread thread3 = new Thread(runnable);

    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();

    System.out.println(synchronizedCounter.getValue());
  }

  public static void main(String[] args) {
    try {
      notRaceCondition();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
