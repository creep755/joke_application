package com.creep.joke.counter;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

/** The type Counter. */
@Component
public class Counter {

  private static AtomicInteger requestCount = new AtomicInteger(0);

  /**
   * Increment and get int.
   *
   * @return the int
   */
  public synchronized int incrementAndGet() {
    return requestCount.incrementAndGet();
  }
}
