package com.yosh.threadlock.deadlock;

public class DeadLock {

  public static void main(String[] args) throws InterruptedException {
    Balance balance1 = new Balance(1_000_000L);
    Balance balance2 = new Balance(1_000_000L);

    Thread t1 =
        new Thread(
            () -> {
              try {
                Balance.transfer(balance1, balance2, 500_000L);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              try {
                Balance.transfer(balance2, balance1, 500_000L);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println(balance1.getValue());
    System.out.println(balance2.getValue());
  }
}
