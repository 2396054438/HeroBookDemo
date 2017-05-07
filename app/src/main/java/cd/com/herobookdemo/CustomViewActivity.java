package cd.com.herobookdemo;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by ChenDan on 2017/5/7.
 * description:自定义View   Activty
 * 第一个是继承自已有控件
 * 第二个是组合控件
 * 第三个是继承自Viwe实现全新的控件
 */

public class CustomViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
    }
}
