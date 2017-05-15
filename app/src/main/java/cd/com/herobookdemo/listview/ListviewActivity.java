package cd.com.herobookdemo.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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
 * Created by chenDan on 2017/5/13.
 * <p>
 * Description:listview相关用法
 * <p>
 * 关于更新数据需要注意的两点
 * <p>
 * 1.之前我们都知道更新适配器的时候需要保证数据源即list一致
 * 2，除了list一致，控件，即listview也要一致
 * 3，一般控件和集合都是在onCreate方法，如果没有更新成功，可以检查有没有因为什么原因导致
 * onCreate重新创建了
 */

public class ListviewActivity extends Activity {
    ListView mListview;
    List<String> mlist;
    Context mContext;
    Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mContext = ListviewActivity.this;
        mListview = (ListView) findViewById(R.id.listview);
        mListview.setEmptyView(findViewById(R.id.tv_empty));
        mBtn = (Button) findViewById(R.id.btn_add);
        mlist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mlist.add("不明数据");
        }
        final MyAdapter adapter = new MyAdapter();
        mListview.setAdapter(adapter);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlist.add("new");
                adapter.notifyDataSetChanged();
                mListview.setSelection(mlist.size() - 1);
            }
        });
        mListview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //点击下去的一瞬间触发
                        Log.d("ListviewActivity", "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //手机移动
                        Log.d("ListviewActivity", "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        //手指离开
                        Log.d("ListviewActivity", "ACTION_UP");
                        break;
                }


                return false;
            }
        });
        mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int state) {
                Log.d("ListviewActivity", "onScrollStateChanged");
                switch (state) {
                    case SCROLL_STATE_FLING:
                        //惯性滑动时候
                        break;
                    case SCROLL_STATE_IDLE:
                        //滑动停止时
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        //正在滚动时
                        break;
                }
            }

            /**
             *
             * @param absListView
             * @param firstVisiableItem
             * @param visiableItemCount
             * @param totleItemCount
             * 当手指没有滑动的时候，这个方法只会回调两次，否则回调三次
             */
            @Override
            public void onScroll(AbsListView absListView,
                                 int firstVisiableItem, int visiableItemCount, int totleItemCount) {
                if (firstVisiableItem + visiableItemCount == totleItemCount && totleItemCount > 0) {
                    //滑动到最后一个
                }
                int lastVisiblePosition = mListview.getLastVisiblePosition();
                if (firstVisiableItem > lastVisiblePosition) {
                    //上滑
                }else if (lastVisiblePosition>firstVisiableItem){
                    //下滑
                }
                lastVisiblePosition=firstVisiableItem;
                Log.d("ListviewActivity", "====");
                //在滚动过程中，一直调用
            }
        });
        


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
                view = View.inflate(mContext, R.layout.item_listview, null);
                itemHold.mTvTitle = (TextView) view.findViewById(R.id.tv_title);
                view.setTag(itemHold);
            } else {
                itemHold = (ItemHold) view.getTag();
            }
            itemHold.mTvTitle.setText(mlist.get(i));
            return view;
        }

        /**
         * 使用ViewHold可提升百分之50的性能
         */
        class ItemHold {
            TextView mTvTitle;
        }
    }
}
