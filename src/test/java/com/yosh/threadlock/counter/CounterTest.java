package com.yosh.threadlock.counter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CounterTest {

  @Test
  void raceCondition() throws InterruptedException {

    Counter counter = new Counter();
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

    Assertions.assertNotEquals(3_000_000L, counter.getValue());
    System.out.println("Excpected: 3.000.000, Actual: " + counter.getValue());
  }

  @Test
  void notRaceCondition() throws InterruptedException {
    SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
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

    Assertions.assertEquals(3_000_000L, synchronizedCounter.getValue());
    System.out.println("Excpected: 3.000.000, Actual: " + synchronizedCounter.getValue());
  }
}
