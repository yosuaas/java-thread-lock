package com.yosh.threadlock.deadlock;

public class Balance {

  private Long value;

  public Balance(Long value) {
    this.value = value;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }

  public static void transfer(Balance from, Balance to, Long amount) throws InterruptedException {
    synchronized (from) {
      Thread.sleep(1_000);
      synchronized (to) {
        Thread.sleep(1_000);

        from.setValue(from.getValue() - amount);
        to.setValue(to.getValue() + amount);
      }
    }
  }

  public static void transferSafe(Balance from, Balance to, Long amount)
      throws InterruptedException {
    synchronized (from) {
      Thread.sleep(1_000);
      from.setValue(from.getValue() - amount);
    }

    synchronized (to) {
      Thread.sleep(1_000);
      to.setValue(to.getValue() + amount);
    }
  }
}
