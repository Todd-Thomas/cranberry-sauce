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

    @Test
    public void testAddAction() throws JsonProcessingException {
        actionAdder.addAction(JUMP_100);
        verify(actionTimeDataStore).addEntry("jump", 100);
    }
}
