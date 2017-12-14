package com.jamin.crash.demo;

import android.app.Application;
import com.jamin.crash.CrashExSDK;
import com.jamin.crash.facade.CrashFacadeInterceptor;
import com.jamin.crash.handle.CrashHandleInterceptor;

/**
 * Created by Jamin on 13/12/2017.
 */

public class MainApplication extends Application {

  public static MainApplication INSTANCE;

  @Override public void onCreate() {
    super.onCreate();
    INSTANCE = this;
    CrashExSDK.init();
    CrashExSDK.setCrashFacadeInterceptor(new CrashFacadeInterceptor() {
      @Override public String facadeException() {
        return CrashFacadeHelper.getExCrashMessage();
      }
    });

    CrashExSDK.setCrashHandleInterceptor(new CrashHandleInterceptor() {
      @Override public boolean handleException(Thread thread, Throwable throwable) {
        return CrashHandle.crashInterceptor(thread, throwable);
      }
    });
  }
}
