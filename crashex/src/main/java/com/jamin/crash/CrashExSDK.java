package com.jamin.crash;

import com.jamin.crash.facade.CrashFacadeInterceptor;
import com.jamin.crash.handle.CrashHandleInterceptor;

/**
 * Created by wangjieming on 13/12/2017.
 */

public class CrashExSDK {


  public static void init() {
    _CrashEx.getInstance().init();
  }

  public static void setCrashFacadeInterceptor(CrashFacadeInterceptor crashFacadeInterceptor) {
    _CrashEx.getInstance().setCrashFacadeInterceptor(crashFacadeInterceptor);
  }

  public static void setCrashHandleInterceptor(CrashHandleInterceptor crashHandleInterceptor) {
    _CrashEx.getInstance().setCrashHandleInterceptor(crashHandleInterceptor);
  }
}
