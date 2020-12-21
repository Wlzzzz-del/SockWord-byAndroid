package com.mingrisoft.sockword;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assetsbasedata.AssetsDatabaseManager;
import com.mingrisoft.greendao.entity.greendao.DaoMaster;
import com.mingrisoft.greendao.entity.greendao.DaoSession;
import com.mingrisoft.greendao.entity.greendao.WisdomEntity;
import com.mingrisoft.greendao.entity.greendao.WisdomEntityDao;
//import com.mingrisoft.sockword.base.AssetsDatabaseManager;
import com.mingrisoft.sockword.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class StudyFragment extends Fragment {

    private TextView difficultyTv,         //学习的难度
            wisdomEnglish,              //名人名句的英语意思
            wisdomChina,                //名人名句的汉语意思
            alreadyStudyText,           //已经学习题数
            alreadyMasteredText,        //已经掌握题数
            wrongText;              //答错题数
    private SharedPreferences sharedPreferences;        //定义轻量级数据库
    //数据库
//    private SQLiteDatabase db;

    private DaoMaster mDaoMaster;    // 数据库管理者

    private DaoSession mDaoSession;         // 与数据库进行会话

    private WisdomEntityDao questionDao;         // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.study_fragment_layhout, null);                 //绑定布局文件
        sharedPreferences = getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);          //初始化数据库
        difficultyTv = (TextView) view.findViewById(R.id.difficulty_text);          //学习难度绑定id
        wisdomEnglish = (TextView) view.findViewById(R.id.wisdom_english);              //名人名句的英语绑定id
        wisdomChina = (TextView) view.findViewById(R.id.wisdom_china);                  //名人名句的汉语绑定id
        alreadyMasteredText = (TextView) view.findViewById(R.id.already_mastered);          //已经掌握题数绑定id
        alreadyStudyText = (TextView) view.findViewById(R.id.already_study);             //已经学习题数绑定id
        wrongText = (TextView) view.findViewById(R.id.wrong_text);                     //倒错错数绑定id
        // 初始化，只需要调用一次
        AssetsDatabaseManager.initManager(getActivity());
        // 获取管理对象，因为数据库需要通过管理对象才能够获取
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        // 通过管理对象获取数据库
        SQLiteDatabase db1 = mg.getDatabase("wisdom.db");
        // 对数据库进行操作
        mDaoMaster = new DaoMaster(db1);          //初始化管理者
        mDaoSession = mDaoMaster.newSession();      //初始化会话对象
        questionDao = mDaoSession.getWisdomEntityDao();    //获取数据


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        difficultyTv.setText(sharedPreferences.getString("difficulty", "四级") + "英语");   //默认设置难度为四级
        List<WisdomEntity> datas =  questionDao.queryBuilder().list();          //货物数据 集合
        Random random = new Random();                                           //产生随机数
        int i = random.nextInt(10);                                                 //随机10以内的一个随机数
        wisdomEnglish.setText(datas.get(i).getEnglish());                       //从数据库里面获取到这条数据的的英语
        wisdomChina.setText(datas.get(i).getChina());                           //从数据库里面获取到这条数据的汉语
        setText();                                              //设置文字
    }

    private void setText() {
        alreadyMasteredText.setText(sharedPreferences.getInt("alreadyMastered",0)+"");      //设置已经复习的题数（数据库读取）
        alreadyStudyText.setText(sharedPreferences.getInt("alreadyStudy",0)+"");             //设置已经学习的题数（数据库读取）
        wrongText.setText(sharedPreferences.getInt("wrong",0)+"");                           //设置错题题数（数据库读取）
    }
}
