package org.toddthomas;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.toddthomas.processors.ActionAdder;
import org.toddthomas.processors.StatsRetriever;

public class TestMultiThreadAccess extends BaseTest {

    @Test
    public void testMultipleThreads() throws InterruptedException {
        Runnable rJ = new AddTask("jump");
        Runnable rS = new AddTask("skip");
        Runnable rR = new AddTask("run");
        Runnable rC = new AddTask("crawl");
        Runnable rL = new AddTask("leap");
        Runnable rW = new AddTask("walk");
        Runnable rH = new AddTask("hop");

        Runnable g1 = new GetTask("1");
        Runnable g2 = new GetTask("2");
        Runnable g3 = new GetTask("3");

        Thread threadRJ = new Thread(rJ);
        threadRJ.start();
        Thread threadG1 = new Thread(g1);
        threadG1.start();
        Thread threadRR = new Thread(rR);
        threadRR.start();
        Thread threadRS = new Thread(rS);
        threadRS.start();
        Thread threadG2 = new Thread(g2);
        threadG2.start();
        Thread threadRC = new Thread(rC);
        threadRC.start();
        Thread threadRL = new Thread(rL);
        threadRL.start();
        Thread threadRW = new Thread(rW);
        threadRW.start();
        Thread threadRH = new Thread(rH);
        threadRH.start();
        Thread threadG3 = new Thread(g3);
        threadG3.start();

        threadRJ.join();
        threadG1.join();
        threadRR.join();
        threadRS.join();
        threadG2.join();
        threadRC.join();
        threadRL.join();
        threadRW.join();
        threadRH.join();
        threadG3.join();
    }

    public static class AddTask implements Runnable {
        final private ActionAdder actionAdder;
        final private String action;

        public AddTask(String action) {
            this.action = action;
            actionAdder = new ActionAdder();
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i <= 15; i++) {
                    int time = 10 + (i * 12);
                    String json = "{\"action\":\"" + action + "\",\"time\":" + time + "}";
                    actionAdder.addAction(json);
                    Thread.sleep(25);
                }
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public static class GetTask implements Runnable {
        final private StatsRetriever statsRetriever;
        private final String threadNumber;

        public GetTask(String threadNumber) {
            this.threadNumber = threadNumber;
            statsRetriever = new StatsRetriever();
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i <= 15; i++) {
                    String results = statsRetriever.getStats();
                    System.out.println(threadNumber + "::RESULT:" + results);
                    Thread.sleep(20);
                }
            } catch (JsonProcessingException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
