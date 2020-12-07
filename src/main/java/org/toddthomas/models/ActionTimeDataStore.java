package org.toddthomas.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * In memory data store to keep track of actions and their times reported.
 */
public class ActionTimeDataStore {

  private static final HashMap<String, List<Integer>> dataStore = new HashMap<>();

  /**
   * Adds an action, if it does not already exist, and associates the time.
   * If the action does exist, it appends the time to the list of current values.
   *
   * @param action to be recorded
   * @param time   associated with the action
   */
  public void addEntry(String action, Integer time) {
    if (null == action || action.isEmpty() || null == time) {
      return;
    }

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
   * Returns a thread safe copy of the map.
   *
   * @return unmodifiableMap of the datastore
   */
  public synchronized List<StatRecord> getValues() {
    List<StatRecord> statsList = new ArrayList<>();

    for (String action : dataStore.keySet()) {
      List<Integer> values = dataStore.get(action);
      int count = values.size();
      System.out.println("Action: " + action + " count: " + count);
      int total = values.stream().reduce(0, Integer::sum);
      System.out.println("Action: " + action + " total: " + total);
      int avg = total / count;
      System.out.println("Action: " + action + " avg: " + avg);

      StatRecord record = new StatRecord(action, avg);
      statsList.add(record);
    }
    return statsList;
  }

  protected void clearEntries() {
    dataStore.clear();
  }
}
