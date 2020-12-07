package org.toddthomas.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionTimeDataStore {

    private final static Map<String, List<Integer>> dataStore = new HashMap<>();

    // TODO -TTH- This needs to be thread safe
    public void addEntry(String action, Integer time) {
        List<Integer> value;

        if (!dataStore.containsKey(action)) {
            value = Collections.singletonList(time);
        } else {
            value = dataStore.get(action);
            value.add(time);
        }
        dataStore.put(action, value);
    }
}
