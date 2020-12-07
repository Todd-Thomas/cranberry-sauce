package org.toddthomas.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.toddthomas.models.ActionTimeDataStore;

import java.util.AbstractMap;

/**
 * Adds an action and its time to the underlying datastore
 */
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

    /**
     * Expects a JSON string to parse and insert into the data store
     * @param input JSON formatted string. Example: {"action":"hop", "time":123}
     * @throws JsonProcessingException when the string is not a properly formatted
     */
    public void addAction(String input) throws JsonProcessingException {
        AbstractMap.SimpleEntry<String, Integer> actionTime = extractValues(input);
        storeValues(actionTime.getKey(), actionTime.getValue());
    }

    private AbstractMap.SimpleEntry<String, Integer> extractValues(String input) throws JsonProcessingException {
        JsonNode actionNode = OBJECT_MAPPER.readTree(input);
        String action = actionNode.get(ACTION).asText();
        Integer time = actionNode.get(TIME).asInt();
        return new AbstractMap.SimpleEntry<>(action, time);
    }

    private void storeValues(String action, Integer time) {
        dataStore.addEntry(action, time);
    }
}
