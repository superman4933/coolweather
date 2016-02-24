package com.superman.coolweather.util;

/**
 * Created by Administrator on 2016/2/24.
 */
public interface HttpCallbackListener  {
   void onFinish(String response);

   void onError(Exception e);
}
