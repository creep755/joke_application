package com.creep.joke.aspect;

import org.aspectj.lang.annotation.Pointcut;

/** The type Point cuts. */
public class PointCuts {
  /** All methods from controller. */
  @Pointcut(value = "execution(* com.creep.joke.controller.*.*(..))")
  public void allMethodsFromController() {}

  /** All methods. */
  @Pointcut(value = "execution(* com.creep.joke.service.*.*(..))")
  public void allMethods() {}
}
