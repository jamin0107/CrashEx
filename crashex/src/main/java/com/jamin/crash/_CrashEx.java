package com.jamin.crash;

import com.jamin.crash.facade.CrashFacadeInterceptor;
import com.jamin.crash.handle.CrashHandleInterceptor;

/**
 * Created by Jamin on 2017/11/4.
 */

class _CrashEx {

  static final String TAG = "CrashExSDK";

  private volatile static _CrashEx INSTANCE;

  static _CrashEx getInstance() {
    if (INSTANCE == null) {
      synchronized (_CrashEx.class) {
        if (INSTANCE == null) {
          INSTANCE = new _CrashEx();
        }
      }
    }
    return INSTANCE;
  }

  private _CrashEx() {

  }

  void init() {
    CrashExHandler.getInstance().register();
  }

  void setCrashHandleInterceptor(CrashHandleInterceptor crashInterceptor) {
    if (!CrashExHandler.getInstance().isInited()) {
      throw new IllegalStateException("CrashExSDK need call init() before call this method.");
    }
    CrashExHandler.getInstance().setCrashHandleInterceptor(crashInterceptor);
  }

  void setCrashFacadeInterceptor(CrashFacadeInterceptor crashFacadeInterceptor) {
    if (!CrashExHandler.getInstance().isInited()) {
      throw new IllegalStateException("CrashExSDK need call init() before call this method.");
    }
    CrashExHandler.getInstance().setCrashFacadeInterceptor(crashFacadeInterceptor);
  }
}
