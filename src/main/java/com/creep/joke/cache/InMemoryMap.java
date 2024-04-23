package com.creep.joke.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/** The type In memory map. */
@Component
public class InMemoryMap {
  private final Map<String, Object> cache;
  private static final int CAPACITY = 50;

  /** Instantiates a new In memory map. */
  public InMemoryMap() {
    cache =
        new LinkedHashMap<>() {
          @Override
          protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            return cache.size() > CAPACITY;
          }
        };
  }

  /**
   * Put.
   *
   * @param key the key
   * @param value the value
   */
  public void put(String key, Object value) {
    cache.put(key, value);
  }

  /**
   * Get object.
   *
   * @param key the key
   * @return the object
   */
  public Object get(String key) {
    return cache.get(key);
  }

  /**
   * Contains key boolean.
   *
   * @param key the key
   * @return the boolean
   */
  public boolean containsKey(String key) {
    return cache.containsKey(key);
  }

  /**
   * Remove.
   *
   * @param key the key
   */
  public void remove(String key) {
    cache.remove(key);
  }

  /**
   * Size int.
   *
   * @return the int
   */
  public int size() {
    return cache.size();
  }
}
