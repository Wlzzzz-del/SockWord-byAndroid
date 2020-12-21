package com.mingrisoft.sockword;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mingrisoft.db.UserBean;
import com.mingrisoft.db.UserDao;


/*
*
* 注册界面
* */
public class RegActivity extends AppCompatActivity {

    //控件声明
    EditText usernameEt;
    EditText passEt;
    EditText pass1Et;

    //数据库表管理类声明
    private UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);//设置布局
        initData();
    }


    public void initData() {
        //初始化控件
        usernameEt = (EditText) findViewById(R.id.usernameEt);
        passEt = (EditText) findViewById(R.id.passEt);
        pass1Et = (EditText) findViewById(R.id.pass1Et);
        //初始化数据库表管理类
        userDao = new UserDao(this);
    }
    //通过onclick事件触发传递值
    public void submit(View view){
        //获取用户名 、 密码 、确认密码
        String username = usernameEt.getText().toString();
        String password = passEt.getText().toString();
        String password1 = pass1Et.getText().toString();
        //数据检查，trim()去空格
        if (username.trim().equals("")||
                password.trim().equals("")||
                password1.trim().equals("")
        ) {
            Toast.makeText(this,"输入内容不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(password1)) {
            Toast.makeText(this,"两次输入的密码不一致！",Toast.LENGTH_SHORT).show();
            return;
        }

        //创建用户对象
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        //插入用户
        if (!userDao.insert(userBean)) {
            Toast.makeText(this,"用户名已存在！",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this,"注册成功，请登录！",Toast.LENGTH_SHORT).show();
        finish();
    }
}
