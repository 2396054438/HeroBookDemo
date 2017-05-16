package cd.com.herobookdemo.listview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cd.com.herobookdemo.R;

/**
 * Created by chenDan on 2017/5/16.
 * <p>
 * Description:
 */

public class ChatActivity extends Activity {
    ListView mListView;
    List<ChatBean> mChatBeanList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //
        mListView = (ListView) findViewById(R.id.list_chat);
        ChatBean chatin = new ChatBean();
        chatin.setImg(R.mipmap.img1);
        chatin.setText("hellow");
        chatin.setType(1);

        ChatBean chatout = new ChatBean();
        chatout.setImg(R.mipmap.img2);
        chatout.setText("nice to meet you");
        chatin.setType(2);

        ChatBean chatin2 = new ChatBean();
        chatin2.setImg(R.mipmap.img1);
        chatin2.setText("how are you");
        chatin.setType(1);

        ChatBean chatout2 = new ChatBean();
        chatout2.setImg(R.mipmap.img2);
        chatout2.setText("i am fine ,thanks");
        chatin.setType(2);

        mChatBeanList.add(chatin);
        mChatBeanList.add(chatout);
        mChatBeanList.add(chatin2);
        mChatBeanList.add(chatout2);
        ChatInAdapter chatInAdapter=new ChatInAdapter();
        mListView.setAdapter(chatInAdapter);

    }

    class ChatInAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mChatBeanList.size();
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
        public int getItemViewType(int position) {
            int type = mChatBeanList.get(position).getType();
            return type;
        }

        /**
         * 两种type类型
         *
         * @return
         */
        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ChatInHold chatHold = null;
            if (view == null) {
                int itemViewType = getItemViewType(i);
                if (itemViewType == 1) {
                    chatHold = new ChatInHold();
                    view = View.inflate(ChatActivity.this, R.layout.item_in, null);
                    chatHold.text = (TextView) view.findViewById(R.id.text_in);
                } else if (itemViewType == 2) {
                    chatHold = new ChatInHold();
                    view = View.inflate(ChatActivity.this, R.layout.item_out, null);
                    chatHold.text = (TextView) view.findViewById(R.id.text_out);
                }
                view.setTag(chatHold);
            } else {
                chatHold = (ChatInHold) view.getTag();
            }
            //赋值
            ChatBean chatBean = mChatBeanList.get(i);
            String text = chatBean.getText();
            chatHold.text.setText(text);
            return view;
        }

        class ChatInHold {
            TextView text;
        }


    }
}
