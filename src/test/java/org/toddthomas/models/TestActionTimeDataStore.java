package org.toddthomas.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.toddthomas.BaseTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestActionTimeDataStore extends BaseTest {

    private ActionTimeDataStore dataStore;

    @Before
    public void setUp() {
        dataStore = new ActionTimeDataStore();
    }

    @After
    public void tearDown() {
        dataStore = null;
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

    private void assertEmptyMap() {
        Map<String, List<Integer>> results = dataStore.getStats();
        assertEquals(results, Collections.emptyMap());
    }
}
