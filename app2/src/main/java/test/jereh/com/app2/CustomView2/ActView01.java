package test.jereh.com.app2.CustomView2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import test.jereh.com.app2.R;

/**
 * 自定义饼图+基础图形绘制
 */
public class ActView01 extends AppCompatActivity {
//    @BindView(R.id.bt1) Button bt1;
    private Button bt1;
    private Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cstom_view2);
        bt1=(Button) findViewById(R.id.bt1);
        bt2=(Button) findViewById(R.id.bt2);
        initView();
    }

    private void initView() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActView01.this,ActView02.class);
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActView01.this,CustomViewGroupActivity.class);
                startActivity(intent);
            }
        });
    }
}