package cd.com.herobookdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by chenDan on 2017/5/9.
 * <p>
 * Description:自定义View之继承View实现全新的视图
 * 实现思路：视图由一个圆，一个文本，一个弧形组成，且他们的中点都在一处
 *
 */

public class CustomView4 extends View {

    private int mScreenWidth;
    private int mScreenHeight;
    int circleXY;
    private float mRadius;
    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;
    private String drawText = "笔绘丹心";
    private int mSweepValue = 120;

    public CustomView4(Context context) {
        super(context);
        screenWidth(context);
        init();
    }

    public CustomView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        screenWidth(context);
        init();
    }

    public CustomView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth(context);
        init();
    }

    public void screenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = windowManager.getDefaultDisplay().getWidth();
        mScreenHeight = windowManager.getDefaultDisplay().getHeight();

    }

    public void init() {
        //圆心
        circleXY = mScreenWidth / 2;
        //半径
        mRadius = (float) (mScreenWidth * 0.5 / 2);
        //绘制圆的笔
        mCirclePaint = new Paint();
        mCirclePaint.setStrokeWidth(50);
        mCirclePaint.setColor(Color.GREEN);
        //绘制弧形的笔
        mArcPaint = new Paint();
        mArcPaint.setStrokeWidth(50);
        //设置空心，因为是狐
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setColor(Color.GREEN);
        //绘制文本的笔
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setStrokeWidth(10);
        mTextPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制矩形，用于绘制弧形
        //参数解释
        //参数1：left：椭圆左边到屏幕左边的距离 参数2：top:椭圆上边到屏幕上边的距离
        //参数3：right:椭圆右边到屏幕左边的距离 参数4：bottom:椭圆下边到屏幕上边的距离
        //所以可得椭圆短半径为bottom-top/right-left,或者长半径为bottom-top/right-left
        //当短半径等于长半径时，椭圆为一个圆。
        RectF rectF = new RectF((float) (mScreenWidth * 0.1), (float) (mScreenWidth * 0.1),
                (float) (mScreenWidth * 0.9), (float) (mScreenWidth * 0.9));
        //绘制圆
        //参数解释，前两个参数共同决定圆心，参数1表示x,参数2表示y
        //参数3，表示圆半径，参数4表示绘制圆的笔
        canvas.drawCircle(circleXY, circleXY, mRadius, mCirclePaint);
        //绘制弧形
        //参数解释
        //参数1：外接矩形，弧形在矩形内绘制 参数2：绘制弧形的起始角度  参数3：绘制弧形的结束角度
        //参数4:是否使用圆形，就是如果为false，绘制出来的就是个狐，如果为true，绘制出来的就是个扇形
        //通俗的讲，主要区别就是旁边由圆心连接的两道杠的区别，false没有，true有
        canvas.drawArc(rectF, 0, mSweepValue, false, mArcPaint);
        //绘制文本
        //参数解释
        //参数1：绘制的文本
        //参数2和参数3为绘制的起始位置，参数4：绘制文本的画笔
        canvas.drawText(drawText, circleXY, circleXY, mTextPaint);

    }

    //设置弧形角度
    public void setSweepValue(int sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepValue = 120;
        }
        //设置了角度要刷新
        invalidate();
    }
}
