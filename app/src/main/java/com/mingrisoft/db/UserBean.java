package com.mingrisoft.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "tb_user")
public class UserBean implements Serializable {

    //唯一标识 自增
    @DatabaseField(generatedId = true, unique = true)
    private int id;

    @DatabaseField(columnName = "username")
    private String username;//用户名

    @DatabaseField(columnName = "password")
    private String password;//密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
