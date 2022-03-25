package com.yosh.threadlock.deadlock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadLockTest {

  @Test
  void deadLockSafe() throws InterruptedException {
    Balance balance1 = new Balance(1_000_000L);
    Balance balance2 = new Balance(1_000_000L);

    Thread t1 =
        new Thread(
            () -> {
              try {
                Balance.transferSafe(balance1, balance2, 500_000L);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              try {
                Balance.transferSafe(balance2, balance1, 500_000L);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    Assertions.assertEquals(1_000_000L, balance1.getValue());
    Assertions.assertEquals(1_000_000L, balance2.getValue());
  }
}
