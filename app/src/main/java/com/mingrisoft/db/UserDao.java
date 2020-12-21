package com.mingrisoft.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;


/*
 *
 * 数据库管理类
 *
 * */

public class UserDao {

    private Context context;

    private Dao<UserBean, Integer> dao;

    /*
     * 构造dao对象
     * */
    public UserDao(Context context) {
        this.context = context;
        try {
            this.dao = DatabaseHelper.getHelper(context).getDao(UserBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 插入数据
     * */
    public boolean insert(UserBean bean){
        try {
            if (isExit(bean.getUsername())) {
                return false;
            }
            dao.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //调用登录

    public boolean login(UserBean bean){
        List<UserBean> list=null;
        try {
            list=dao.queryBuilder().where().eq("username",bean.getUsername())
                    .and().eq("password",bean.getPassword()).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list==null||list.size()==0) {
            return false;
        }
        return true;
    }

    //判断用户是否存在
    public boolean isExit(String username){
        List<UserBean> list=null;
        try {
            list=dao.queryBuilder().where().eq("username",username)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list==null||list.size()==0) {
            return false;
        }
        return true;
    }



}
