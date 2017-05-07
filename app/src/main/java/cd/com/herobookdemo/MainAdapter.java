package cd.com.herobookdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDan on 2017/5/6.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemHolder> {
    Context mContext;
    List<String> mlist;
    onItemClick mOnItemClick;

    public interface onItemClick {
        void onItemClick(View view, int pos);

        void onItemLongClick(View view, int pos);
    }


    public void setOnItemClick(onItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    public MainAdapter(Context context, List<String> list) {
        mContext = context;
        if (mlist == null) {
            mlist = new ArrayList<>();
        }
        this.mlist = list;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        holder.tvItem.setText(mlist.get(position));
        if (mOnItemClick != null) {
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClick.onItemClick(holder.mLinearLayout, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView tvItem;
        LinearLayout mLinearLayout;

        public ItemHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            mLinearLayout= (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }
}
