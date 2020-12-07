package org.toddthomas.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.toddthomas.BaseTest;

public class TestActionTimeDataStore extends BaseTest {

  private ActionTimeDataStore dataStore;

  @Before
  public void setUp() {
    dataStore = new ActionTimeDataStore();
  }

  @After
  public void tearDown() {
    dataStore.clearEntries();
  }

  @Test
  public void testAddEntriesNullParams() {
    dataStore.addEntry(null, null);
    assertEmptyMap();
  }

  @Test
  public void testAddEntriesActionNull() {
    dataStore.addEntry(null, _1);
    assertEmptyMap();
  }

  @Test
  public void testAddEntriesTimeNull() {
    dataStore.addEntry(JUMP, null);
    assertEmptyMap();
  }

  @Test
  public void testAddEntriesActionEmpty() {
    dataStore.addEntry("", _20);
    assertEmptyMap();
  }

  @Test
  public void testAddOneEntry() {
    dataStore.addEntry("jump", 100);
    Map<String, List<Integer>> results = dataStore.getValues();
    assertNotNull(results);
    assertTrue(results.containsKey("jump"));
    List<Integer> times = results.get("jump");
    assertNotNull(times);
    assertEquals(1, times.size());
    assertEquals(new Integer(100), times.get(0));
  }

  @Test
  public void testAddMultipleEntriesSerialize() {
    dataStore.addEntry("jump", 100);
    dataStore.addEntry("sit", 23);
    dataStore.addEntry("hop", 43);
    dataStore.addEntry("sit", 45);
    dataStore.addEntry("run", 1234);
    dataStore.addEntry("sit", 76);
    dataStore.addEntry("jump", 25);
    dataStore.addEntry("stop", 55);

    Map<String, List<Integer>> results = dataStore.getValues();
    assertNotNull(results);

    validateEntry(results, "jump", new int[] {100, 25});
    validateEntry(results, "sit", new int[] {23, 76, 45});
    validateEntry(results, "run", new int[] {1234});
    validateEntry(results, "hop", new int[] {43});
    validateEntry(results, "stop", new int[] {55});
  }

  private void validateEntry(Map<String, List<Integer>> actual, String key, int[] expectedValues) {
    assertTrue(actual.containsKey(key));
    List<Integer> values = actual.get(key);

    for (int expectedValue : expectedValues) {
      assertTrue(values.contains(expectedValue));
    }
  }

  private void assertEmptyMap() {
    Map<String, List<Integer>> results = dataStore.getValues();
    assertEquals(results, Collections.emptyMap());
  }
}
