package com.yosh.threadlock.atomiccounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AtomicCounterTest {

  @Test
  void testAtomicCounter() throws InterruptedException {
    AtomicCounter counter = new AtomicCounter();

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

    Assertions.assertEquals(3_000_000L, counter.getValue());
  }
}
