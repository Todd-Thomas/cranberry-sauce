package org.toddthomas;

public class BaseTest {
  private static final String ACTION = "action";
  private static final String TIME = "time";

  protected static final String JUMP = "jump";
  protected static final String CRAWL = "crawl";
  protected static final String RUN = "run";

  protected static final Integer _1 = 1;
  protected static final Integer _20 = 20;
  protected static final Integer _30 = 30;
  protected static final Integer _43 = 43;
  protected static final Integer _100 = 100;
  protected static final Integer _123 = 123;
  protected static final Integer _200 = 200;
  protected static final Integer _321 = 321;

  protected static final String JUMP_100 =
      "{\"" + ACTION + "\":\"" + JUMP + "\", \"" + TIME + "\":" + _100 + "}";
  protected static final String JUMP_200 =
      "{\"" + ACTION + "\":\"" + JUMP + "\", \"" + TIME + "\":" + _200 + "}";
  protected static final String JUMP_321 =
      "{\"" + ACTION + "\":\"" + JUMP + "\", \"" + TIME + "\":" + _321 + "}";
  protected static final String CRAWL_1 =
      "{\"" + ACTION + "\":\"" + CRAWL + "\", \"" + TIME + "\":" + _1 + "}";
  protected static final String CRAWL_123 =
      "{\"" + ACTION + "\":\"" + CRAWL + "\", \"" + TIME + "\":" + _123 + "}";
  protected static final String RUN_20 =
      "{\"" + ACTION + "\":\"" + RUN + "\", \"" + TIME + "\":" + _20 + "}";
  protected static final String RUN_30 =
      "{\"" + ACTION + "\":\"" + RUN + "\", \"" + TIME + "\":" + _30 + "}";
  protected static final String RUN_43 =
      "{\"" + ACTION + "\":\"" + RUN + "\", \"" + TIME + "\":" + _43 + "}";
}
