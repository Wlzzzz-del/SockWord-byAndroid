package com.mingrisoft.sockword;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class SetFragment extends Fragment implements View.OnClickListener {

    private SharedPreferences sharedPreferences;            //定义一个轻量级数据库
    private SwitchButton switchButton;                      //开关按钮
    private Spinner spinnerDifficulty;                      //定义选择难度的下拉框
    private Spinner spinnerAllNum;                          //定义解锁题目的下拉框
    private Spinner spinnerNewNum;                          //定义新题目的下拉框
    private Spinner spinnerReviewNum;                       //定义复习题的下拉框
    private ArrayAdapter<String> adapterDifficulty, adapterAllNum, adapterNewNum, adapterReviewNUm;        //定义下拉框的适配器
    String[] difficulty = new String[]{"小学", "初中", "高中", "四级", "六级"};                  //选择难度下拉框里面的选项nr
    String[] allNum = new String[]{"2道", "4道", "6道", "8道"};                               //解锁题目下拉框的选项内容
    String[] newNum = new String[]{"10", "30", "50", "100"};                                  //新题目下拉框的选项内容
    String[] revicwNum = new String[]{"10", "30", "50", "100"};                               //复习题目下拉框的选项内容
    SharedPreferences.Editor editor = null;                 //定义数据库的编辑器

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_fragment_layhout, null);           //绑定布局文件
        init(view);
        return view;
    }

    /**
     * 初始化控件
     */
    private void init(View view) {
        sharedPreferences = getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);         //初始化适配器
        editor = sharedPreferences.edit();                                        //初始化编辑器
        switchButton = (SwitchButton) view.findViewById(R.id.switch_btn);           //开关按钮绑定id
        switchButton.setOnClickListener(this);                                  //开关按钮设置监听事件
        spinnerDifficulty = (Spinner) view.findViewById(R.id.spinner_difficulty);       //选择难度下拉框绑定id
        spinnerAllNum = (Spinner) view.findViewById(R.id.spinner_all_number);        //解锁题目下拉框绑定id
        spinnerNewNum = (Spinner) view.findViewById(R.id.spinner_new_number);            //新题目下拉框绑定id
        spinnerReviewNum = (Spinner) view.findViewById(R.id.spinner_revise_number);          //复习题下拉框绑定id
        adapterDifficulty = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_selectable_list_item, difficulty);              //初始化选择难度下拉框的适配器
        spinnerDifficulty.setAdapter(adapterDifficulty);                            //给选择难度下拉框设置适配器
        setSpinnerItemSelectedByValue(spinnerDifficulty, sharedPreferences.getString("difficulty", "四级"));      //定义选择难度下拉框的默认选项
        this.spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {             //设置选择难度的下拉框的监听事件
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();                         //获取到选择的内容
                editor.putString("difficulty", msg);                                                //写到数据库里面
                editor.commit();                                                                    //保存
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * 同上面的选择难度的选项框的原理一样
         * */
        adapterAllNum = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, allNum);
        spinnerAllNum.setAdapter(adapterAllNum);
        setSpinnerItemSelectedByValue(spinnerAllNum, sharedPreferences.getInt("allNum", 2) + "道");
        this.spinnerAllNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                int i = Integer.parseInt(msg.substring(0, 1));
                editor.putInt("allNum", i);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /**
         * 同上面的选择难度的选项框的原理一样
         * */
        adapterNewNum = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, newNum);
        spinnerNewNum.setAdapter(adapterNewNum);
        setSpinnerItemSelectedByValue(spinnerNewNum, sharedPreferences.getString("newNum", "10"));
        this.spinnerNewNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                editor.putString("newNum", msg);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * 同上面的选择难度的选项框的原理一样
         * */
        adapterReviewNUm = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, revicwNum);
        spinnerReviewNum.setAdapter(adapterReviewNUm);
        setSpinnerItemSelectedByValue(spinnerReviewNum, sharedPreferences.getString("reviewNum", "10"));
        this.spinnerReviewNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                editor.putString("reviewNum", msg);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        /**
         * 从数据库获取开关按钮的状态
         * */
        if (sharedPreferences.getBoolean("btnTf", false)) {
            switchButton.openSwitch();
        } else {
            switchButton.closeSwitch();
        }
    }

    /**
     * 点击的监听事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_btn:       //点击开关按钮
                if (switchButton.isSwitchOpen()) { //如果开关按钮为打开状态
                    switchButton.closeSwitch();        //则关闭按钮
                    editor.putBoolean("btnTf", false);       //写入数据库开关按钮状态

                } else {                    //否则为关闭状态
                    switchButton.openSwitch();      //打开开关按钮
                    editor.putBoolean("btnTf", true);    //写入数据库状态
                }
                editor.commit();            //进行保存
                break;
        }
    }

    /**
     * 设置下拉框默认选项的方法
     */
    public void setSpinnerItemSelectedByValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter(); //得到SpinnerAdapter对象
        int k = apsAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);// 默认选中项
            }
        }
    }
}
