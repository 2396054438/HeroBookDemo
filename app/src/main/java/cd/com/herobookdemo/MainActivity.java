package cd.com.herobookdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Context mContext;
    List<String> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        addDate();
        MainAdapter adapter = new MainAdapter(mContext, mlist);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new MainAdapter.onItemClick() {
            @Override
            public void onItemClick(View view, int pos) {
                switch (pos){
                    case 0:
                        openActivity(ViewMeasureActivity.class);
                    break;
                    case 1:
                        openActivity(CustomViewActivity.class);
                    break;
                    case 2:
                        openActivity(MusicRecfActivity.class);
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int pos) {

            }
        });
    }

    private void addDate() {
        mlist = new ArrayList<>();
        mlist.add("View的测量");
        mlist.add("自定义View之对现有控件进行扩展");
        mlist.add("音频条形图");

    }

    public void openActivity(Class clas) {
        Intent intent = new Intent(mContext,clas);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
