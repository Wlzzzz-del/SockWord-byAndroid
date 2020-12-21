package com.mingrisoft.sockword;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.text.InputFilter;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
/**
 * 设置屏幕状态的监听
 * */
public class ScreenListener {
    private Context context;        //联系上下文
    private ScreenBroadcastReceiver mScreenReceiver;    //定义一个广播
    private ScreenStateListener mScreenStateListener;       //定义个内部接口
    /**
     * 初始化
     * */
    public ScreenListener(Context context) {
        this.context = context;
        mScreenReceiver =new ScreenBroadcastReceiver();//初始化广播
    }


    /**
     * 自定义接口
     * */
    public interface ScreenStateListener{

        void onScreenOn();
        void onScreenOff();
        void onUserPresent();

    }

    /**
     * 获取screen的状态
     * */
    private void getScreenState() {
        PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE); //初始化powerManager
        if (manager.isScreenOn()){
            if (mScreenStateListener != null){
                mScreenStateListener.onScreenOn();
            }
        }else {
            if (mScreenStateListener != null){
                mScreenStateListener.onScreenOff();
            }
        }
    }

    /**
     * 写一个内部的广播
     * */
    private class ScreenBroadcastReceiver extends BroadcastReceiver{

        private String action = null;
        @Override
        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)){        //开屏操作
                    mScreenStateListener.onScreenOn();
            }else if (Intent.ACTION_SCREEN_OFF.equals(action)){     //锁屏操作
                    mScreenStateListener.onScreenOff();
            }else if (Intent.ACTION_USER_PRESENT.equals(action)) {      //解锁操作
                    mScreenStateListener.onUserPresent();
            }

        }
    }

    /**
     * 开始监听广播状态
     * */

    public void begin(ScreenStateListener listener){
        mScreenStateListener = listener;
        registerListener();
        getScreenState();
    }


    /**
     * 启动广播接收器
     * */
    private void registerListener() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        context.registerReceiver(mScreenReceiver, filter);

    }



    /**
     * 解除广播
     * */
  public void unregisterListener(){
      context.unregisterReceiver(mScreenReceiver); //注销广播

  }


}
