package com.jamin.crash;

import android.text.TextUtils;
import android.util.Log;
import com.jamin.crash.facade.CrashFacade;
import com.jamin.crash.facade.CrashFacadeInterceptor;
import com.jamin.crash.handle.CrashHandleInterceptor;

/**
 * Created by Jamin on 2017/11/4.
 */

class CrashExHandler implements Thread.UncaughtExceptionHandler {

  private static CrashExHandler crashExHandler;
  private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
  private boolean inited;
  private CrashHandleInterceptor crashInterceptor;
  private CrashFacadeInterceptor crashFacadeInterceptor;

  static synchronized CrashExHandler getInstance() {
    CrashExHandler myCrashHandler;
    synchronized (CrashExHandler.class) {
      if (crashExHandler == null) {
        crashExHandler = new CrashExHandler();
      }
      myCrashHandler = crashExHandler;
    }
    return myCrashHandler;
  }

  /**
   * register at application init.
   */
  void register() {
    if (!inited) {
      inited = true;
      try {
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
      } catch (Exception e) {
        Log.d(_CrashEx.TAG, "Exception e = " + e.getMessage());
        inited = false;
        e.printStackTrace();
      }
    }
  }

  @Override public void uncaughtException(Thread thread, Throwable throwable) {
    //intercept exception.
    if (crashInterceptor != null && crashInterceptor.handleException(thread, throwable)) {
      Log.d(_CrashEx.TAG, "crashInterceptor.handleException");
      return;
    }

    Throwable facadeThrowable = throwable;
    if (crashFacadeInterceptor != null) {
      String exMessage = crashFacadeInterceptor.facadeException();
      if (!TextUtils.isEmpty(exMessage)) {
        facadeThrowable = CrashFacade.facadeThrowable(throwable, exMessage);
      }
    }
    uncaughtExceptionHandler.uncaughtException(thread, facadeThrowable);
  }

  boolean isInited() {
    return inited;
  }

  void setCrashHandleInterceptor(CrashHandleInterceptor crashHandleInterceptor) {
    this.crashInterceptor = crashHandleInterceptor;
  }

  void setCrashFacadeInterceptor(CrashFacadeInterceptor crashFacadeInterceptor) {
    this.crashFacadeInterceptor = crashFacadeInterceptor;
  }
}
