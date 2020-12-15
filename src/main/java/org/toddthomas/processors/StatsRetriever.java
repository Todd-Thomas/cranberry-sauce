package org.toddthomas.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import org.toddthomas.models.ActionTimeDataStore;
import org.toddthomas.models.StatRecord;


/**
 * Retrieves the actions along with the average time per each action
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
     *
     * @return JSON formatted string that contains each action and its average duration
     * @throws JsonProcessingException when the ObjectMapper cannot write the value as a string
     */
    public String getStats() throws JsonProcessingException {
        List<StatRecord> statsList = new ArrayList<>();
        Map<String, List<Integer>> results = actionTimeDataStore.getValues();

        for (String action : results.keySet()) {
            List<Integer> values = results.get(action);
            int count = values.size();
            int total = values.stream().reduce(0, Integer::sum);
            int avg = total / count;

            StatRecord record = new StatRecord(action, avg);
            statsList.add(record);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(statsList);
    }

    static private class StatRecord {
        private String action;
        private int avg;

        private StatRecord() {
        }

        public StatRecord(String action, int avg) {
            this.action = action;
            this.avg = avg;
        }

        public String getAction() {
            return action;
        }

        public int getAvg() {
            return avg;
        }
    }
}
