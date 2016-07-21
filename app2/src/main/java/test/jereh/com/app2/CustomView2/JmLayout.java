package test.jereh.com.app2.CustomView2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.jereh.com.app2.R;

/**
 * Created by Administrator on 2016/6/26.自定义组合控件
 */
public class JmLayout extends RelativeLayout {
    private View view;
    private TextView mTvKind;

    public JmLayout(Context context) {
        super(context);
        initView();
    }
//在自定义控件里面对自定义属性进行相关的操作（可选）
    public JmLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

        // 得到自定义输入的值
        String kind = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "kind");
        mTvKind.setText(kind);
    }
    private void initView(){
        //  View.inflate  View的填充器
        //  最后一个参数表示把谁当做父容器，这里我们当MySettingView当做父容器
        //  当然，如果这里写null就什么都没有显示了!!!!!!!!!!!!
//        view = View.inflate(getContext(),R.layout.item_my_style_view, JmLayout.this);
        view= LayoutInflater.from(getContext()).inflate(R.layout.item_my_style_view, JmLayout.this);
        mTvKind = (TextView) view.findViewById(R.id.mTvKind);

    }


}
