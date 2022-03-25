package com.yosh.threadlock.futuretest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

  public static void future() throws InterruptedException, ExecutionException {
    Callable<String> callable =
        () -> {
          Thread.sleep(5000);
          return "Hi";
        };

    ExecutorService service = Executors.newFixedThreadPool(10);

    Future<String> future = service.submit(callable);
    Future<String> future2 = service.submit(callable);
    Future<String> future3 = service.submit(callable);

    String response = future.get();
    String response2 = future2.get();
    String response3 = future3.get();

    System.out.println(response);
    System.out.println(response2);
    System.out.println(response3);
  }

  public static void futureAll() throws InterruptedException, ExecutionException {
    Callable<String> callable =
        () -> {
          Thread.sleep(5000);
          return "Hi";
        };

    List<Callable<String>> callables = new ArrayList<>();
    callables.add(callable);
    callables.add(callable);
    callables.add(callable);

    ExecutorService service = Executors.newFixedThreadPool(10);

    List<Future<String>> futures = service.invokeAll(callables);

    // String futuresAny = service.invokeAny(callables);

    futures.forEach(
        future -> {
          System.out.println(future);
        });
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    future();
  }
}
