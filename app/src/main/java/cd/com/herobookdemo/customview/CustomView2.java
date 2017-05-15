package cd.com.herobookdemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ChenDan on 2017/5/7.
 * description:自定义View之继承现有控件，闪动textview
 */

public class CustomView2 extends TextView {

    Paint mPaint;
    //初始化视图的宽度为0
    int mViewWidth = 0;
    LinearGradient mLinearGradient;
    Matrix mGradentMatri;
    int mTranslate=0;



    public CustomView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public void initPaint() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //mGradentMatri在onSizeChanged方法中已经实例化了，
        // 所以不为空的话，说明画笔已经初始化了，下面就是设置移动
        if (mGradentMatri!=null){
            //移动视图宽度五分之一之和
            mTranslate+=mViewWidth/5;
            //这里不理解
            if (mTranslate>mViewWidth*2){
                mTranslate=-mViewWidth;
            }
            //设置视图移动距离
            mGradentMatri.setTranslate(mTranslate,0);
            //设置矩阵
            mLinearGradient.setLocalMatrix(mGradentMatri);
            //每100秒闪动一次
            postInvalidateDelayed(100);
        }

    }

    /**
     * 在onSizeChanged方法中做一些初始化操作
     * 疑问：渐变并没有改变控件的大小，为什么要在这个方法中做初始化呢？
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //如果视图宽度为0
        if (mViewWidth == 0) {
            //则获取到测量的宽度赋值给这个变量
            mViewWidth = getMeasuredWidth();
            //如果获取到测量出来的宽度大于0
            if (mViewWidth > 0) {
                //获取绘制当前绘制Textview的paint对象
                mPaint = getPaint();
                //创建一个线性渲染器
                /**
                 * 参数解释
                 * float x0: 渐变起始点x坐标
                 float y0:渐变起始点y坐标
                 float x1:渐变结束点x坐标
                 float y1:渐变结束点y坐标
                 int[] colors:颜色 的int 数组
                 float[] positions: 相对位置的颜色数组,可为null,  若为null,颜色沿渐变线均匀分布
                 Shader.TileMode 的几种模式详细看http://blog.csdn.net/u012702547/article/details/50821044

                 */
                mLinearGradient = new LinearGradient(0, 0, mViewWidth,
                        0, new int[]{Color.BLUE, Color.GREEN, Color.RED},null,
                Shader.TileMode.CLAMP);
                //给原始的画笔设置渐变器
                mPaint.setShader(mLinearGradient);
                //创建矩阵
                mGradentMatri=new Matrix();

            }
        }
    }
}
