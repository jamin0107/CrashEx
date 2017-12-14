package com.jamin.crash.demo;

/**
 * Created by Jamin on 13/12/2017.
 */

public class CrashHelper {


  /**
   * report non-fatal exception , just like Crashlytics
   */
  public static void logNonFatalException(Throwable throwable) {
    //Crashlytics.logException(throwable);
    throwable.printStackTrace();
  }




}
