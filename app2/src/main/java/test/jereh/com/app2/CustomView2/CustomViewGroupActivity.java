package test.jereh.com.app2.CustomView2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import test.jereh.com.app2.R;

public class CustomViewGroupActivity extends AppCompatActivity {
    private Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);
        bt1=(Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CustomViewGroupActivity.this,ActView02.class);
                startActivity(intent);
            }
        });
    }
}
