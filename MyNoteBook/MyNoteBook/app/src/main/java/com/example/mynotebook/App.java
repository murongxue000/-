package com.example.mynotebook;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    public static App app;//单例化Application
    public static App getApp() {
        if (app == null) {
            synchronized (App.class) { //线程安全
                if (app == null) {
                    app = new App();
                }
            }
        }
        return app;
    }

    //上下文
    public static Context context;
    public static Context getContext() {
        return context;}

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

}
