package org.toddthomas.models;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * In memory data store to keep track of actions and their times reported.
 */
public class ActionTimeDataStore {

    private final static ConcurrentMap<String, List<Integer>> dataStore = new ConcurrentHashMap<>();

    /**
     * Adds an action, if it does not already exist, and associates the time.
     * If the action does exist, it appends the time to the list of current values.
     * @param action to be recorded
     * @param time associated with the action
     */
    public void addEntry(String action, Integer time) {
        if (null == action || action.isEmpty() || null == time) return;

        List<Integer> values;

        if (!dataStore.containsKey(action)) {
            values = new ArrayList<>();
        } else {
            values = dataStore.get(action);
        }
        values.add(time);
        dataStore.put(action, values);
    }

    /**
     * Returns a thread safe copy of the map
     * @return synchronizedMap of the datastore
     */
    public Map<String, List<Integer>> getValues() {
        return Collections.synchronizedMap(dataStore);
    }

    protected void clearEntries() {
        dataStore.clear();
    }
}
