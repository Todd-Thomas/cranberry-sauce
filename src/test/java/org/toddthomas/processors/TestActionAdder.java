package org.toddthomas.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.toddthomas.models.ActionTimeDataStore;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class TestActionAdder {
    @InjectMocks
    private ActionAdder actionAdder;

    @Mock
    private ActionTimeDataStore actionTimeDataStore;

    private static final String JUMP_100 = "{\"action\":\"jump\", \"time\":100}";
    private static final String JUMP_200 = "{\"action\":\"jump\", \"time\":200}";
    private static final String JUMP_321 = "{\"action\":\"jump\", \"time\":321}";
    private static final String CRAWL_1 = "{\"action\":\"crawl\", \"time\":1}";
    private static final String CRAWL_123 = "{\"action\":\"crawl\", \"time\":123}";
    private static final String RUN_20 = "{\"action\":\"run\", \"time\":20}";
    private static final String RUN_30 = "{\"action\":\"run\", \"time\":30}";
    private static final String RUN_43 = "{\"action\":\"run\", \"time\":43}";

    @Test
    public void testAddAction() throws JsonProcessingException {
        actionAdder.addAction(JUMP_100);
        verify(actionTimeDataStore).addEntry("jump", 100);
    }

    @Test
    public void testAddMultipleActions() throws JsonProcessingException {
        actionAdder.addAction(RUN_43);
        actionAdder.addAction(CRAWL_123);
        actionAdder.addAction(JUMP_200);
        actionAdder.addAction(JUMP_321);
        actionAdder.addAction(RUN_20);
        actionAdder.addAction(CRAWL_1);
        actionAdder.addAction(RUN_30);
        verify(actionTimeDataStore).addEntry("run", 43);
        verify(actionTimeDataStore).addEntry("crawl", 123);
        verify(actionTimeDataStore).addEntry("jump", 200);
        verify(actionTimeDataStore).addEntry("jump", 321);
        verify(actionTimeDataStore).addEntry("run", 20);
        verify(actionTimeDataStore).addEntry("crawl", 1);
        verify(actionTimeDataStore).addEntry("run", 30);
    }
}
