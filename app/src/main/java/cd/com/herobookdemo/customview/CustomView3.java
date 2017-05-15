package cd.com.herobookdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cd.com.herobookdemo.R;

/**
 * Created by chenDan on 2017/5/9.
 * <p>
 * Description:自定义View之组合控件
 */

public class CustomView3 extends RelativeLayout {


    private String mTitleText;
    private String mLeftText;
    private String mRightText;
    private int mTitleColor;
    private int mLeftColor;
    private int mRightColor;
    private float mTitleSize;
    private float mLeftSize;
    private float mRightSize;
    TextView mTvLeft;
    TextView mTvRight;
    TextView mTvTitle;
    LayoutParams mLeftParam;
    LayoutParams mRightParam;
    LayoutParams mTopParam;

    public CustomView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initView(context);


    }


    public CustomView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.topBar);
        mTitleText = typedArray.getString(R.styleable.topBar_titleText);
        mLeftText = typedArray.getString(R.styleable.topBar_leftText);
        mRightText = typedArray.getString(R.styleable.topBar_rightText);
        mTitleColor = typedArray.getColor(R.styleable.topBar_titleColor, 0);
        mLeftColor = typedArray.getColor(R.styleable.topBar_leftColor, 0);
        mRightColor = typedArray.getColor(R.styleable.topBar_rightColor, 0);
        mTitleSize = typedArray.getDimension(R.styleable.topBar_titleSize, 0);
        mLeftSize = typedArray.getDimension(R.styleable.topBar_leftSize, 0);
        mRightSize = typedArray.getDimension(R.styleable.topBar_rightSize, 0);
        //获取完属性后一定要记得调用recycle方法来回收哦~，避免重新调用出错
        typedArray.recycle();
    }

    public void initView(Context context) {
        //实例化标题三个控件
        mTvTitle = new TextView(context);
        mTvLeft = new TextView(context);
        mTvRight = new TextView(context);
        //把设置的属性添加给这三个控件
        mTvTitle.setText(mTitleText);
        mTvTitle.setTextColor(mTitleColor);
        mTvTitle.setTextSize(mTitleSize);
        mTvTitle.setGravity(Gravity.CENTER);
        mTvLeft.setText(mLeftText);
        mTvLeft.setGravity(Gravity.CENTER);
        mTvLeft.setTextColor(mLeftColor);
        mTvLeft.setTextSize(mLeftSize);
        mTvRight.setText(mRightText);
        mTvRight.setTextColor(mRightColor);
        mTvRight.setTextSize(mRightSize);
        mTvRight.setGravity(Gravity.CENTER);
        mLeftParam = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mTvLeft, mLeftParam);
        mRightParam = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mTvRight, mRightParam);
        mTopParam = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTopParam.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTvTitle, mTopParam);
        mTvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClick != null) {
                    mOnClick.onLeftClick();
                }
            }
        });
        mTvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClick != null) {
                    mOnClick.onRightClick();
                }
            }
        });
    }

    public onClick mOnClick;

    //定义接口
    public interface onClick {
        void onLeftClick();

        void onRightClick();
    }

    public void setOnClickListener(onClick onClick) {
        mOnClick = onClick;
    }
}
