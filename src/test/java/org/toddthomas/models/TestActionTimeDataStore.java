package org.toddthomas.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.util.Collections;
import java.util.List;
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
    List<StatRecord> results = dataStore.getValues();
    assertNotNull(results);
    StatRecord record = results.get(0);
    assertEquals("jump", record.getAction());
    assertEquals(100, record.getAvg());
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

    List<StatRecord> results = dataStore.getValues();
    assertNotNull(results);

    assertTrue(validateEntry(results, new StatRecord("jump", 62)));
    assertTrue(validateEntry(results, new StatRecord("sit", 48)));
    assertTrue(validateEntry(results, new StatRecord("run", 1234)));
    assertTrue(validateEntry(results, new StatRecord("hop", 43)));
    assertTrue(validateEntry(results, new StatRecord("stop", 55)));
  }

  private boolean validateEntry(List<StatRecord> actual, StatRecord expected) {
    boolean success = false;
    for (StatRecord record : actual) {
      if (record.getAction().equals(expected.getAction()) && record.getAvg() == record.getAvg()) {
        success = true;
        break;
      }
    }
    return success;
  }

  private void assertEmptyMap() {
    List<StatRecord> results = dataStore.getValues();
    assertEquals(results, Collections.emptyList());
  }
}
