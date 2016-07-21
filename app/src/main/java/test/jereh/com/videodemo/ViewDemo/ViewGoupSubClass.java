package test.jereh.com.videodemo.ViewDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ViewGoupSubClass extends ViewGroup {
    public ViewGoupSubClass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount=getChildCount();//获取子view的个数
        if(childCount>0){
            View child=getChildAt(0);//设1个孩纸
            //测子view大小   传进来父view的MeasureSpec
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount=getChildCount();
        if(childCount>0){
            View child=getChildAt(0);
            //获取测量后的宽和高
            int measuredWidth=child.getMeasuredWidth();
            int measuredHeight=child.getMeasuredHeight();
            child.layout(0,0,measuredWidth,measuredHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
