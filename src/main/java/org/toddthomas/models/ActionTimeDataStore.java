package org.toddthomas.models;

import java.util.*;

public class ActionTimeDataStore {

    private final static Map<String, List<Integer>> dataStore = new HashMap<>();

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
        return Collections.unmodifiableMap(dataStore);
    }

    protected void clearEntries() {
        dataStore.clear();
    }
}
