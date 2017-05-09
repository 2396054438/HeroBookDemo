package cd.com.herobookdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by chenDan on 2017/5/9.
 * <p>
 * Description:模拟音频条形图
 */

public class MusicBarView extends View {


    private Paint mPaint;
    public int mOffset = 10;//偏移量
    public int mRectWidth ;//矩形宽度
    public int mRectHeight ;//矩形高度
    private int mRectCount = 10;//矩形数量
    private float currentHeight;//视图高度
    private int mWidth;//视图宽度
    private LinearGradient mLinearGradient;//线性渐变

    public MusicBarView(Context context) {
        super(context);
        init(context);
    }

    public MusicBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MusicBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //获取屏幕宽度
    public int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        return width;
    }

    public void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("MusicBarView", "onDraw");
        for (int i=0;i<mRectCount;i++){
            currentHeight = (float)(Math.random()*mRectHeight);
            canvas.drawRect((float)(mWidth*0.4/2+mRectWidth*i+mOffset),currentHeight,(float)(mWidth*0.4/2+mRectWidth*(i+1)),mRectHeight,mPaint);
            postInvalidateDelayed(300);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("MusicBarView", "onSizeChanged");

       // mPaint = new Paint();
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int)(mWidth*0.6/mRectCount);
        mLinearGradient = new LinearGradient(0,0,mRectWidth,mRectHeight, Color.YELLOW,Color.BLUE, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

}
