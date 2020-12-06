package org.toddthomas.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.Pair;
import org.toddthomas.models.ActionTimeDataStore;

public class ActionAdder {

    private static final String ACTION = "action";
    private static final String TIME = "time";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final ActionTimeDataStore dataStore;

    // Needed for unit testing
    protected ActionAdder(ActionTimeDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public ActionAdder() {
        dataStore = new ActionTimeDataStore();
    }

    public void addAction(String input) throws JsonProcessingException {
        Pair<String, Integer> actionTime = extractValues(input);
        storeValues(actionTime.fst, actionTime.snd);
    }

    private Pair<String, Integer> extractValues(String input) throws JsonProcessingException {
        JsonNode actionNode = OBJECT_MAPPER.readTree(input);
        String action = actionNode.get(ACTION).asText();
        Integer time = actionNode.get(TIME).asInt();
        return new Pair<>(action, time);
    }

    private void storeValues(String action, Integer time) {
        dataStore.addEntry(action, time);
    }
}
