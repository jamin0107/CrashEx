package com.jamin.crash.facade;

import java.lang.reflect.Field;

/**
 * Created by wangjieming on 06/12/2017.
 * Facade Crash Message.
 */

public class CrashFacade {

  /**
   * append ex Message behind detailMessage.
   */
  public static Throwable facadeThrowable(Throwable throwable, String exMessage) {
    try {
      Field field = getDeclaredField(throwable, "detailMessage");
      if (field == null) {
        return throwable;
      }
      field.setAccessible(true);
      String originDetailMessage = (String) field.get(throwable);
      String newDetailMessage = originDetailMessage + exMessage;
      field.set(throwable, newDetailMessage);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return throwable;
  }


  /**
   * find detailMessage field
   */
  private static Field getDeclaredField(Object object, String fieldName) {
    Field field;
    Class<?> clazz = object.getClass();

    for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
      try {
        field = clazz.getDeclaredField(fieldName);
        return field;
      } catch (Exception ignore) {

      }
    }
    return null;
  }
}
