package org.toddthomas.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.toddthomas.models.ActionTimeDataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatsRetriever {

    private static final String AVG = "avg";

    private ActionTimeDataStore actionTimeDataStore;

    protected StatsRetriever(ActionTimeDataStore actionTimeDataStore) {
        this.actionTimeDataStore = actionTimeDataStore;
    }

    public StatsRetriever() {
        this.actionTimeDataStore = new ActionTimeDataStore();
    }


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
