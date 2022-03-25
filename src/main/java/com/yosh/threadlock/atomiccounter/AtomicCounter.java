package com.yosh.threadlock.atomiccounter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter {
  private final AtomicLong value = new AtomicLong(0L);

  public void increment() {
    value.incrementAndGet();
  }

  public Long getValue() {
    return value.get();
  }
}
