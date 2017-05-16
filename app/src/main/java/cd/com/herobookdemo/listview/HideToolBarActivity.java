package cd.com.herobookdemo.listview;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cd.com.herobookdemo.R;

/**
 * Created by chenDan on 2017/5/16.
 * <p>
 * Description:隐藏toolBar
 */

public class HideToolBarActivity extends Activity {
    ListView mListView;
    private int mScaledTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    int direction;
    boolean show = true;//是否隐藏
    Button btn;
    List<String> mlist;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidetoolbar);
        mContext = HideToolBarActivity.this;
        mListView = (ListView) findViewById(R.id.listview);
        btn = (Button) findViewById(R.id.btn_toolbar);
        View head = new View(this);
        head.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        mListView.addHeaderView(head);
        mScaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mlist = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mlist.add("列表" + i);
        }
        MyAdapter adapter = new MyAdapter();
        mListView.setAdapter(adapter);
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrentY = motionEvent.getY();
                        if (mCurrentY - mFirstY > mScaledTouchSlop) {
                            direction = 0;
                        } else if (mFirstY - mCurrentY > mScaledTouchSlop) {
                            direction = 1;
                        }
                        if (direction == 1) {
                            if (show) {
                                toolBarAnimation(0);//hide
                                show = !show;
                            }
                        } else if (direction == 0) {
                            if (!show) {
                                toolBarAnimation(1);//show
                                show = !show;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return false;
            }
        });

    }

    ObjectAnimator mAnimation;

    public void toolBarAnimation(int flag) {
        if (mAnimation != null && mAnimation.isRunning()) {
            mAnimation.cancel();
        }
        if (flag == 1) {
            mAnimation = ObjectAnimator.ofFloat(btn, "translationY", btn.getTranslationY(), 0);
        } else {
            mAnimation = ObjectAnimator.ofFloat(btn, "translationY", btn.getTranslationY(), -btn.getHeight());
        }
        mAnimation.start();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ItemHold itemHold;
            if (view == null) {
                itemHold = new ItemHold();
                view = View.inflate(mContext, R.layout.item, null);
                view.setTag(itemHold);
            } else {
                itemHold = (ItemHold) view.getTag();
            }
            //赋值
            itemHold.text.setText(mlist.get(i));
            return view;
        }

        class ItemHold {
            TextView text;
        }
    }
}
