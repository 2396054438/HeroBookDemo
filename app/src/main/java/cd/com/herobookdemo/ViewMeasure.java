package cd.com.herobookdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ChenDan on 2017/5/6.
 * View测量类
 */

public class ViewMeasure extends View {
    public ViewMeasure(Context context) {
        super(context);
    }

    public ViewMeasure(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewMeasure(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureWidth(widthMeasureSpec),MeasureHeight(heightMeasureSpec))  ;
    }

    /**
     * 测量宽
     *
     * @param measureSpac
     * @return
     */
    public int MeasureWidth(int measureSpac) {
        //测量结果
        int result = 0;
        //  获取测量模式
        int specMode = MeasureSpec.getMode(measureSpac);
        //获取测量大小
        int specSize = MeasureSpec.getSize(measureSpac);
        Log.d("ViewMeasure", "specMode:" + specMode+"specSize:"+specSize);
        //如果模式为EXACTLY，则返回获取到的size
        if (specMode == MeasureSpec.EXACTLY) {
            return specSize;
        } else {
            //
            result = 50;
            //如果为AT_MOST模式，则取最小值，即包裹容器
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specMode);

            }
        }

        return result;
    }

    /**
     * 测量高
     */
    public int MeasureHeight(int measureSpec) {

        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode==MeasureSpec.EXACTLY){
            return size;
        }else {
            //默认值
            result=200;
            if (mode==MeasureSpec.AT_MOST){
                result=Math.min(result,mode);
            }
        }


        return result;
    }


}
