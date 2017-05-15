package cd.com.herobookdemo.customview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import cd.com.herobookdemo.R;

/**
 * Created by ChenDan on 2017/5/7.
 * description:自定义View   Activty
 * 第一个是继承自已有控件
 * 第二个是组合控件
 * 第三个是继承自Viwe实现全新的控件
 */

public class CustomViewActivity extends Activity {
    CustomView3 mCustomView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        mCustomView3 = (CustomView3) findViewById(R.id.custom3);
        mCustomView3.setOnClickListener(new CustomView3.onClick() {
            @Override
            public void onLeftClick() {
                Toast.makeText(CustomViewActivity.this, "onLeftClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(CustomViewActivity.this, "onRightClick", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
