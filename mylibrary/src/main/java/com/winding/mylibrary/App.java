package com.winding.mylibrary;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by 刘少帅 on 2017/10/24
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化可打印日志
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
