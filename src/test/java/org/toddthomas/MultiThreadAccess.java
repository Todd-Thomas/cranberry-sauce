package org.toddthomas;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.toddthomas.processors.ActionAdder;
import org.toddthomas.processors.StatsRetriever;

/**
 * This test class creates multiple threads to add actions and a few tasks to get stats.
 * Its purpose is to show that the underlying is thread safe when bombarded with many different
 * threads accessing the core logic.
 */
public class MultiThreadAccess {

  @Test
  public void testMultipleThreads() throws InterruptedException {
    final Runnable rJ = new AddTask("jump");
    final Runnable rS = new AddTask("skip");
    final Runnable rR = new AddTask("run");
    final Runnable rC = new AddTask("crawl");
    final Runnable rL = new AddTask("leap");
    final Runnable rW = new AddTask("walk");
    final Runnable rH = new AddTask("hop");

    final Runnable g1 = new GetTask("1");
    final Runnable g2 = new GetTask("2");
    final Runnable g3 = new GetTask("3");

    Thread threadRj = new Thread(rJ);
    threadRj.start();
    Thread threadG1 = new Thread(g1);
    threadG1.start();
//    Thread threadRr = new Thread(rR);
//    threadRr.start();
//    Thread threadRs = new Thread(rS);
//    threadRs.start();
//    Thread threadG2 = new Thread(g2);
//    threadG2.start();
//    Thread threadRc = new Thread(rC);
//    threadRc.start();
//    Thread threadRl = new Thread(rL);
//    threadRl.start();
//    Thread threadRw = new Thread(rW);
//    threadRw.start();
//    Thread threadRh = new Thread(rH);
//    threadRh.start();
//    Thread threadG3 = new Thread(g3);
//    threadG3.start();

    threadRj.join();
    threadG1.join();
//    threadRr.join();
//    threadRs.join();
//    threadG2.join();
//    threadRc.join();
//    threadRl.join();
//    threadRw.join();
//    threadRh.join();
//    threadG3.join();
  }

  public static class AddTask implements Runnable {
    private final ActionAdder actionAdder;
    private final String action;

    public AddTask(String action) {
      this.action = action;
      actionAdder = new ActionAdder();
    }

    @Override
    public void run() {
      try {
        for (int i = 0; i <= 2; i++) {
          int time = 10 + (i * 12);
          String json = "{\"action\":\"" + action + "\",\"time\":" + time + "}";
          System.out.println(json);
          actionAdder.addAction(json);
          Thread.sleep(25);
        }
      } catch (InterruptedException | JsonProcessingException e) {
        e.printStackTrace();
      }
    }
  }

  public static class GetTask implements Runnable {
    private final StatsRetriever statsRetriever;
    private final String threadNumber;

    public GetTask(String threadNumber) {
      this.threadNumber = threadNumber;
      statsRetriever = new StatsRetriever();
    }

    @Override
    public void run() {
      try {
        for (int i = 0; i <= 5; i++) {
          String results = statsRetriever.getStats();
          System.out.println(threadNumber + "::RESULT:" + results);
          Thread.sleep(5);
        }
      } catch (JsonProcessingException | InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
