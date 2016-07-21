package test.jereh.com.app2.CustomView1;

import android.app.Activity;
import android.os.Bundle;

import test.jereh.com.app2.R;

public class CustomTitleViewActitvty extends Activity {
    private CustomTitleView vip;
    private CustomView2 custom2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_title_view);
        vip= (CustomTitleView) super.findViewById(R.id.vip);

    }
}
