package org.toddthomas.processors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.toddthomas.BaseTest;
import org.toddthomas.models.ActionTimeDataStore;
import org.toddthomas.models.StatRecord;

@RunWith(MockitoJUnitRunner.class)
public class TestStatsRetriever extends BaseTest {
  @InjectMocks
  private StatsRetriever statsRetriever;

  @Mock
  private ActionTimeDataStore actionTimeDataStore;

  @Test
  public void testGetStats() throws JsonProcessingException {
    when(actionTimeDataStore.getValues()).thenReturn(generateList());
    String jsonString = statsRetriever.getStats();
    assertEquals("[{\"action\":\"skip\",\"avg\":4}]", jsonString);
  }

  private List<StatRecord> generateList() {
    List<StatRecord> statRecords = new ArrayList<>();
    StatRecord record = new StatRecord("skip", 4);
    statRecords.add(record);
    return statRecords;
  }

}
