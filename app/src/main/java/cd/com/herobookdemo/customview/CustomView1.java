package cd.com.herobookdemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import cd.com.herobookdemo.R;

/**
 * Created by ChenDan on 2017/5/7.
 * description:自定义View之继承自现有控件
 *
 */

public class CustomView1 extends TextView {

    Paint mPaint1;
    Paint mPaint2;

    public CustomView1(Context context) {
        super(context);
        initPaint();
    }

    public CustomView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    public void initPaint() {
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint1.setColor(getResources().getColor(R.color.colorAccent));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //外矩形
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        //内矩形
        canvas.drawRect(10, 10, getMeasuredWidth() - 20, getMeasuredHeight() - 20, mPaint2);
        //保存画布状态
        canvas.save();
        //绘制文字前平移30像素，即文字绘制的位置距离View左边30像素
        canvas.translate(30, 0);
        //以上是调用父类方法之前的操作，对于textview来说是绘制文本之前
        super.onDraw(canvas);
        //在调用了系统的绘制之后，取出之前保存的状态，在原来保存的基础上进行操作，重新设置的属性
        // 不会影响到保存之前的状态
        canvas.restore();


    }


}
