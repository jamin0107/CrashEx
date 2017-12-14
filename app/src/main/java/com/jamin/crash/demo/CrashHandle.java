package com.jamin.crash.demo;

import com.jamin.crash.facade.CrashFacade;

/**
 * Created by wangjieming on 13/12/2017.
 */

public class CrashHandle {

  /**
   * catch some uncaught exception
   */
  public static boolean crashInterceptor(Thread thread, Throwable throwable) {

    if (throwable == null || thread.getId() == 1) {
      //Don't intercept the Exception of Main Thread.
      return false;
    }

    String classpath = null;
    if (throwable.getStackTrace() != null && throwable.getStackTrace().length > 0) {
      classpath = throwable.getStackTrace()[0].toString();
    }

    //intercept GMS  "Results have already been set" Exception.
    if (classpath != null
        && throwable.getMessage().contains("Results have already been set")
        && classpath.contains("com.google.android.gms")) {
      CrashHelper.logNonFatalException(CrashFacade.facadeThrowable(throwable, "<ExMessage>"));
      return true;
    }

    //intercept NPE
    if (throwable instanceof NullPointerException) {
      CrashHelper.logNonFatalException(
          CrashFacade.facadeThrowable(throwable, CrashFacadeHelper.getExCrashMessage()));
      return true;
    }

    return false;
  }
}
