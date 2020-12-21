package com.mingrisoft.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


/*
*
* 数据库管理类
* */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String TABLE_NAME = "sqlite-sock.db";

    private static final int DB_VERSION = 1;

    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
    }

    private static DatabaseHelper instance;

    /*
    * 创建单例实例
    * */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    /*
    *
    * 创建数据库表
    * */
    @Override
    public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    *
    * 更新数据库时 直接删除旧的表
    * */
    @Override
    public void onUpgrade(SQLiteDatabase arg0, ConnectionSource connectionSource, int arg2, int arg3) {
        try {
            TableUtils.dropTable(connectionSource, UserBean.class, true);
            onCreate(arg0, connectionSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
