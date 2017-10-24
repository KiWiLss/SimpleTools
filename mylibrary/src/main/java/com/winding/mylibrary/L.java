package com.winding.mylibrary;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;

/**
 * 日志相关工具类
 */
public class L {
    public static String TAG = "MMM";
    public static boolean isLog=true;

    public static void setIsLog(boolean isShow){
        isLog=isShow;
    }

    public static void setTag(String tag){
        TAG=tag;
    }

    public static void d(Object o){
        if (isLog) {
            Logger.t(TAG).d(o.toString());
        }
    }
    public static void i(Object o){
        if (isLog) {
            Logger.t(TAG).i(o.toString());
        }
    }

    public static void e(Object o){
        if (isLog) {
            Logger.t(TAG).e(o.toString());
        }
    }
    public static void w(Object o){
        if (isLog) {
            Logger.t(TAG).w(o.toString());
        }
    }
    public static void json(String o){
        if (isLog) {
            Logger.t(TAG).json(o);
        }
    }
    public static void xml(String o){
        if (isLog) {
            Logger.t(TAG).xml(o);
        }
    }

    /**object --> json type
     * @param o
     */
    public static void ii(Object o){
        if (isLog) {
            Logger.t(TAG).i(JSON.toJSONString(o));
        }
    }
    public static void ee(Object o){
        if (isLog) {
            Logger.t(TAG).e(JSON.toJSONString(o));
        }
    }

}