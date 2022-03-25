package com.yosh.threadlock.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
  private Long value = 0L;

  private Lock lock = new ReentrantLock();

  public void increment() {
    try {
      lock.lock();
      value++;
    } finally {
      lock.unlock();
    }
  }

  public Long getValue() {
    return value;
  }
}
