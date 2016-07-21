package test.jereh.com.videodemo.ViewDemo;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MyLinearLayoutDemo extends LinearLayout{
    private ViewDragHelper mViewDragHelper;
    public MyLinearLayoutDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewDragHelper();
    }

    private void initViewDragHelper() {
        //初始化
        mViewDragHelper=ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override //处理水平方向的越界
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int fixedLeft;
                View parent= (View) child.getParent();
                int leftBound=parent.getPaddingLeft();
                int rightBount=parent.getWidth()-child.getWidth()-parent.getPaddingRight();
                if(left<leftBound){
                    fixedLeft=leftBound;
                }else if(left>rightBount){
                    fixedLeft=rightBount;
                }else{
                    fixedLeft=left;
                }
                return fixedLeft;
            }

            @Override  //处理垂直方向的越界
            public int clampViewPositionVertical(View child, int top, int dy) {
                int fixdeTop;
                View parent= (View) child.getParent();
                int topBound=getPaddingTop();
                int bottomBound=getHeight()-child.getHeight()-parent.getPaddingTop();
                if(top<topBound){
                    fixdeTop=topBound;
                }else if(top>topBound){
                    fixdeTop=bottomBound;
                }else{
                    fixdeTop=top;
                }
                return fixdeTop;
            }

            @Override  //监听拖动状态的改变
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                switch (state){
                    case ViewDragHelper.STATE_DRAGGING:
                        break;
                    case ViewDragHelper.STATE_IDLE:
                        break;
                    case ViewDragHelper.STATE_SETTLING:
                        break;
                }
            }

            @Override //捕获View
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }

            @Override //释放View
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
            }
        });
    }

    @Override //将事件拦截交给ViewDragHelper处理
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override  //将Touch事件交给ViewDragHelper处理
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}
