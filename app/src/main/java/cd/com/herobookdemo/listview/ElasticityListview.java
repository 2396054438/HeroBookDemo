package cd.com.herobookdemo.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by chenDan on 2017/5/15.
 * <p>
 * Description:
 */

public class ElasticityListview extends ListView {
    //值越大，弹性越大
    int mMaxOverScrollY = 50;

    public ElasticityListview(Context context) {
        super(context);
        initView(context);
    }

    public ElasticityListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ElasticityListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    //为了适配，根据屏幕分辨率来获取
    public void initView(Context context) {
        DisplayMetrics displayMetrics =
                context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        mMaxOverScrollY = (int) (mMaxOverScrollY * density);
    }

    //重写这个方法来改变mMaxOverScrollY，从而达到弹性的效果
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY, int maxOverScrollX,
                                   int maxOverScrollY, boolean isTouchEvent) {

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                scrollRangeY, maxOverScrollX, mMaxOverScrollY, isTouchEvent);
    }
}
