package test.jereh.com.videodemo.ViewDemo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * 自定义View学习，谷哥的小弟所讲
 */
import test.jereh.com.videodemo.R;

public class ViewDemoActivity_1 extends AppCompatActivity {
    //自定义View的常见实现方式：
    private CollapseView vip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapse_layout_items);
        vip=(CollapseView)findViewById(R.id.vip);
        vip.setNumber(String.valueOf(1));
        vip.setTitle("测试组合view");
    }
}
