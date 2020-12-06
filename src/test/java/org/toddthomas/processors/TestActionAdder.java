package org.toddthomas.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.toddthomas.BaseTest;
import org.toddthomas.models.ActionTimeDataStore;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class TestActionAdder extends BaseTest {
    @InjectMocks
    private ActionAdder actionAdder;

    @Mock
    private ActionTimeDataStore actionTimeDataStore;

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
