package com.mingrisoft.sockword;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class BaseApplication extends Application {

    //创建一个Map的集合用来把activity加到这个map里面
    private static Map<String, Activity> destroyMap = new HashMap<>();

    /**
     * 添加到销毁的列队
     * <p/>
     * 要销毁的activity
     */
    public static void addDestroyActiivty(Activity activity, String activityName) {
        destroyMap.put(activityName, activity);
    }

    /**
     * 销毁指定的activity
     */
    public static void destroyActivity(String activityName) {
        Set<String> keySet = destroyMap.keySet();
        for (String key : keySet) {
            destroyMap.get(key).finish();
        }
    }
}