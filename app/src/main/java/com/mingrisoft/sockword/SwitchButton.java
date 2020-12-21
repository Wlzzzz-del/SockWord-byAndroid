package com.mingrisoft.sockword;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mingrisoft.sockword.R;


/**
 * <p/>
 * 开关  自定义button
 */
public class SwitchButton extends FrameLayout {
    private ImageView openImage;    //打开按钮的图片图片
    private ImageView closeImage;   //关闭按钮的图片

    /**
     * 联系上下文
     * */
    public SwitchButton(Context context) {
        this(context, null);
    }

    /**
     * 构造方法
     * */
    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        //context通过调用obtainStyledAttributes方法来获取一个TypeArray，然后由该TypeArray来对属性进行设置
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);

        //画出开关为打开的状态
        Drawable openDrawable = typedArray.getDrawable(R.styleable.SwitchButton_switchOpenImage);

        //画出开关为关闭的状态
        Drawable closeDrawable = typedArray.getDrawable(R.styleable.SwitchButton_switchCloseImage);
        int switchStatus = typedArray.getInt(R.styleable.SwitchButton_switchStatus, 0);
        //调用结束后务必调用recycle()方法，否则这次的设定会对下次的使用造成影响
        typedArray.recycle();

        LayoutInflater.from(context).inflate(R.layout.switch_button, this);//绑定布局文件
        openImage = (ImageView) findViewById(R.id.iv_switch_open);      //绑定id
        closeImage = (ImageView) findViewById(R.id.iv_switch_close);       //绑定id

        if (openDrawable != null) {        //如果是打开状态
            openImage.setImageDrawable(openDrawable);   //设置显示图片
        }
        if (closeDrawable != null) {        //如果是关闭状态
            closeImage.setImageDrawable(closeDrawable);    //设置关闭图片
        }
        if (switchStatus == 1) {    //判断开关的状态
            closeSwitch();          //执行关闭的方法
        }

    }

    /**
     * 判断开关的状态
     */
    public boolean isSwitchOpen() {
        return openImage.getVisibility() == View.VISIBLE;
    }

    /**
     * 打开开关
     */
    public void openSwitch() {
        openImage.setVisibility(View.VISIBLE);      //显示打开图片
        closeImage.setVisibility(View.INVISIBLE);   //隐藏关闭图片
    }

    /**
     * 关闭开关
     */
    public void closeSwitch() {
        openImage.setVisibility(View.INVISIBLE);        //隐藏打开图片
        closeImage.setVisibility(View.VISIBLE);     //显示关闭图片
    }


}
