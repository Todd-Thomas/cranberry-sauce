package org.toddthomas.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.toddthomas.models.ActionTimeDataStore;
import org.toddthomas.models.StatRecord;

/**
 * Retrieves the actions along with the average time per each action.
 */
public class StatsRetriever {

  private final ActionTimeDataStore actionTimeDataStore;

  protected StatsRetriever(ActionTimeDataStore actionTimeDataStore) {
    this.actionTimeDataStore = actionTimeDataStore;
  }

  public StatsRetriever() {
    this.actionTimeDataStore = new ActionTimeDataStore();
  }

  /**
   * Gets the current stats as a JSON string.
   *
   * @return JSON formatted string that contains each action and its average duration
   * @throws JsonProcessingException when the ObjectMapper cannot write the value as a string
   */
  public String getStats() throws JsonProcessingException {
    List<StatRecord> statsList = actionTimeDataStore.getValues();
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(statsList);
  }

}
