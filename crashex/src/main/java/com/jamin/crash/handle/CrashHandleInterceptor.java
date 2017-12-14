package com.jamin.crash.handle;

/**
 * Created by Jamin on 2017/11/4.
 * Handle Crash or not
 */

public interface CrashHandleInterceptor {

  /**
   * @return true :caught exception
   * false uncaught
   */
  boolean handleException(Thread thread, Throwable throwable);
}
