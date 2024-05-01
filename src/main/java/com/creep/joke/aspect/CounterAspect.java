package com.creep.joke.aspect;

import com.creep.joke.counter.Counter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/** The type Counter aspect. */
@Aspect
@Setter
@Getter
@Slf4j
@Component
public class CounterAspect {
  private final Counter counter = new Counter();

  /** Incrementation of counter. */
  @Before("PointCuts.allMethodsFromController()")
  public void incrementBefore() {
    log.info("Current number of service calls: {}.", counter.incrementAndGet());
  }
}
