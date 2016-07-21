package test.jereh.com.videodemo.ViewDemo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import test.jereh.com.videodemo.R;

/**
 * Created by Administrator on 2016/5/29.
 */
public class AttrView extends View {

    public AttrView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义的相应的属性  实际上调用的getTheme().obtainStyledAttributes(set, attrs, 0, 0)
        //1.自己写获取方式
//        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.AttrView);
//        boolean test1=array.getBoolean(R.styleable.AttrView_test1,false);
//        int test2=array.getInt(R.styleable.AttrView_test2,0);
//        int test3=array.getInt(R.styleable.AttrView_test3,0);
        //2.defStyleRes 获取
//        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.AttrView,0,R.style.style_attrView1);
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.AttrView,R.attr.attrViewStyleRef,0);

        boolean test1=array.getBoolean(R.styleable.AttrView_test1,false);
        int test2=array.getInt(R.styleable.AttrView_test2,0);
        int test3=array.getDimensionPixelSize(R.styleable.AttrView_test3,0);

        Log.d("shitou","test1----------"+test1);
        Log.d("shitou","test2----------"+test2);
        Log.d("shitou","test3----------"+test3);

        array.recycle();
    }
}
