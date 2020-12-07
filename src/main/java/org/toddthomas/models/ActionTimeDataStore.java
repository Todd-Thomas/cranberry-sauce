package org.toddthomas.models;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ActionTimeDataStore {

    private final static ConcurrentMap<String, List<Integer>> dataStore = new ConcurrentHashMap<>();

    // TODO -TTH- This needs to be thread safe
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

    public Map<String, List<Integer>> getValues() {
        return Collections.synchronizedMap(dataStore);
    }

    protected void clearEntries() {
        dataStore.clear();
    }
}
