package org.toddthomas.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * In memory data store to keep track of actions and their times reported.
 */
public class ActionTimeDataStore {

  private static final HashMap<String, List<Integer>> dataStore =
      new HashMap<>();

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

    // Tricky Part: the data store itself needs to be synchronized so that only one thread
    // can call addEntry at a time.
    synchronized (dataStore) {
      if (!dataStore.containsKey(action)) {
        values = new ArrayList<>();
      } else {
        values = dataStore.get(action);
      }
      values.add(time);
      dataStore.put(action, values);
    }
  }

  /**
   * Returns a thread safe copy of the map.
   *
   * @return unmodifiableMap of the datastore
   */
  public List<StatRecord> getValues() {
    List<StatRecord> statsList = new ArrayList<>();

    // Tricky Part: the data store itself needs to be synchronized so that only one thread
    // can call getValues at a time. It is very important that this thread maintain a lock
    // on this block of code because it needs to perform operations on the List to compute
    // the average.
    // I have purposely implemented the logic this way to show that even when other threads
    // are calling addEntry, the values within this instance of the dataStore are not effect.
    // Before synchronizing this block, when I was in debug mode, I could easily see that the
    // values variable was being changed from the calls to addEntry.
    synchronized (dataStore) {
      for (String action : dataStore.keySet()) {
        List<Integer> values = dataStore.get(action);
        int count = values.size();
        int total = values.stream().reduce(0, Integer::sum);
        int avg = total / count;
        StatRecord record = new StatRecord(action, avg);
        statsList.add(record);
      }
    }
    return statsList;
  }

  protected void clearEntries() {
    dataStore.clear();
  }
}
