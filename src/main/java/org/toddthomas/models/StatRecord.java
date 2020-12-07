package org.toddthomas.models;

public class StatRecord {

  private String action;
  private int avg;

  private StatRecord() {
  }

  public StatRecord(String action, int avg) {
    this.action = action;
    this.avg = avg;
  }

  public String getAction() {
    return action;
  }

  public int getAvg() {
    return avg;
  }
}
