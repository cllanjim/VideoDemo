package test.jereh.com.videodemo.ViewDemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import test.jereh.com.videodemo.R;

public class ViewDemo_1 extends AppCompatActivity {
    ViewDemo_1 vip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_demo_1);
        findViewById(R.id.vip);
    }
}
