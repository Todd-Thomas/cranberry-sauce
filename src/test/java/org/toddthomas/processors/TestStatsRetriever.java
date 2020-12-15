package org.toddthomas.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.toddthomas.BaseTest;
import org.toddthomas.models.ActionTimeDataStore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestStatsRetriever extends BaseTest {
    @InjectMocks
    private StatsRetriever statsRetriever;

    @Mock
    private ActionTimeDataStore actionTimeDataStore;

    @Test
    public void testGetStats() throws JsonProcessingException {
        when(actionTimeDataStore.getValues()).thenReturn(generateMap());
        String jsonString = statsRetriever.getStats();
        assertEquals("[{\"action\":\"skip\",\"avg\":4}]", jsonString);
    }

    private Map<String, List<Integer>> generateMap() {
        Map<String, List<Integer>> mockValue = new HashMap<>();
        List<Integer> skipList = Arrays.asList(4,8,2,9,1);
        mockValue.put("skip", skipList);
        return mockValue;
    }

}
