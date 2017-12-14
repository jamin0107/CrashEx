package com.jamin.crash.demo;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import java.util.List;

/**
 * Created by Jamin on 13/12/2017.
 */

public class CrashFacadeHelper {

  /**
   * Generate extend ErrorMessage.
   *
   * @return example:<<ProcessName-GoogleApiHandler-12479>>
   */
  public static String getExCrashMessage() {
    StringBuilder stringBuilder = new StringBuilder("\n<<");
    String processName = getCurProcessName(MainApplication.INSTANCE);
    String processDetail =
        processName + "-" + Thread.currentThread().getName() + "-" + Thread.currentThread().getId();
    if (!TextUtils.isEmpty(processDetail)) {
      stringBuilder.append(processDetail);
    }

    stringBuilder.append(">>");
    return stringBuilder.toString();
  }

  /**
   * get current process name
   */
  private static String getCurProcessName(Context ctx) {
    if (ctx == null) {
      return null;
    }
    int pid = android.os.Process.myPid();
    ActivityManager mActivityManager =
        (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
    if (mActivityManager != null) {
      List<ActivityManager.RunningAppProcessInfo> appProcesses =
          mActivityManager.getRunningAppProcesses();
      if (appProcesses != null && appProcesses.size() > 0) {
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
          if (appProcess.pid == pid) {
            return appProcess.processName;
          }
        }
      }
    }
    return null;
  }
}
