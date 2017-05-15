package cd.com.herobookdemo.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
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
