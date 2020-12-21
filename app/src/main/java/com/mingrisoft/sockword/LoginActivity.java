package com.mingrisoft.sockword;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mingrisoft.db.UserBean;
import com.mingrisoft.db.UserDao;


/*
* 登录界面
* */
public class LoginActivity extends AppCompatActivity {
    //控件声明
    EditText usernameEt;
    EditText passEt;
    //数据库表管理类声明
    private UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//设置布局
        initData();
    }

    public void initData() {
        //初始化控件
        usernameEt = (EditText) findViewById(R.id.usernameEt);
        passEt = (EditText) findViewById(R.id.passEt);
        //初始化数据库表管理类
        userDao = new UserDao(this);
    }

    //跳转到注册界面
    public void goReg(View view){
        Intent intent = new Intent(this, RegActivity.class);//显式意图
        startActivity(intent);//跳转到注册页面
    }
    //跳转到主界面
    //通过onclick事件跳转
    public void goMain(View view){
        //获取用户名 、 密码
        String username = usernameEt.getText().toString();
        String password = passEt.getText().toString();
        //数据检查
        if (username.trim().equals("")||
                password.trim().equals("")
        ) {
            Toast.makeText(this,"输入内容不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        //创建用户对象
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);

        //登录
        if (userDao.login(userBean)) {
            //登录成功
            Intent intent = new Intent(this, HomeActivity.class);//创建显式意图对象
            startActivity(intent);//跳转到主界面
            finish();
        } else {
            Toast.makeText(this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
        }




    }
}
