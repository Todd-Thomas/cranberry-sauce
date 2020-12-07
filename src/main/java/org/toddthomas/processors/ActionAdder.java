package org.toddthomas.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.toddthomas.models.ActionTimeDataStore;

import java.util.AbstractMap;

public class ActionAdder {

    private static final String ACTION = "action";
    private static final String TIME = "time";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final ActionTimeDataStore dataStore;

    protected ActionAdder(ActionTimeDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public ActionAdder() {
        this.dataStore = new ActionTimeDataStore();
    }

    public void addAction(String input) throws JsonProcessingException {
        AbstractMap.SimpleEntry<String, Integer> actionTime = extractValues(input);
        storeValues(actionTime.getKey(), actionTime.getValue());
    }

    private AbstractMap.SimpleEntry<String, Integer> extractValues(String input) throws JsonProcessingException {
        JsonNode actionNode = OBJECT_MAPPER.readTree(input);
        System.out.println(actionNode);
        String action = actionNode.get(ACTION).asText();
        Integer time = actionNode.get(TIME).asInt();
        return new AbstractMap.SimpleEntry<>(action, time);
    }

    private void storeValues(String action, Integer time) {
        dataStore.addEntry(action, time);
    }
}
