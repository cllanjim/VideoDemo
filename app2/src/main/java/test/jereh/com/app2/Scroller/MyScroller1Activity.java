package test.jereh.com.app2.Scroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import test.jereh.com.app2.R;

/**
 * Scroller基本理论
 */
public class MyScroller1Activity extends AppCompatActivity {
    Button mButton;
    TestView mTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scroller1);
//        mTestView = (TestView)findViewById(R.id.testView);
        mButton = (Button)findViewById(R.id.button);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTestView.smoothScrollTo(-100,-400,5000);
//            }
//        });
    }




}
