package com.creep.joke.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryMapTest {

    private InMemoryMap inMemoryMap;

    @BeforeEach
    void setUp() {
        inMemoryMap = new InMemoryMap();
    }

    @Test
    void testPutAndGet() {
        assertNull(inMemoryMap.get("key1"));
        inMemoryMap.put("key1", "value1");
        assertEquals("value1", inMemoryMap.get("key1"));
    }

    @Test
    void testContainsKey() {
        inMemoryMap.put("key2", "value2");
        assertTrue(inMemoryMap.containsKey("key2"));
        assertFalse(inMemoryMap.containsKey("key3"));
    }

    @Test
    void testRemove() {
        inMemoryMap.put("key4", "value4");
        assertTrue(inMemoryMap.containsKey("key4"));
        inMemoryMap.remove("key4");
        assertFalse(inMemoryMap.containsKey("key4"));
    }

    @Test
    void testSize() {
        assertEquals(0, inMemoryMap.size());
        inMemoryMap.put("key5", "value5");
        assertEquals(1, inMemoryMap.size());
    }

    @Test
    void testCacheEviction() {
        for (int i = 0; i < 60; i++) {
            inMemoryMap.put("key" + i, "value" + i);
        }
        assertEquals(50, inMemoryMap.size());
        assertFalse(inMemoryMap.containsKey("key0")); // проверяем, что самый старый элемент был удален
    }
}
