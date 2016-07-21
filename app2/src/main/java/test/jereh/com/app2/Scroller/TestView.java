package test.jereh.com.app2.Scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/3.
 */
public class TestView extends TextView {
    public static final String TAG = TestView.class.getSimpleName();

    private Scroller mScroller;

    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller= new Scroller(context);
    }

    /**
     *平滑地滑动到指定的位置，坐标系为当前View的视图坐标
     * @param destX 指定位置的X坐标
     * @param destY 指定位置的Y坐标
     */
    public void smoothScrollTo(int destX,int destY,int time){
        int scrollX = getScrollX(); //获取到画布上的矩形框已经滑动的水平距离
        int scrollY = getScrollY(); //获取到画布上的矩形框已经滑动的垂直距离

        int deltaX = destX - scrollX; //需要滑动的水平距离
        int deltaY = destY -scrollY; //需要滑动的垂直距离

        mScroller.startScroll(mScroller.getCurrX(),mScroller.getCurrY(),deltaX,deltaY,time);

        invalidate();

        // mScroller.fling(0,0,-5000,-5000,-200,0,-200,0);
//        代码中的startScroll()方法可以使用fling()方法代替，效果由平滑变成快速滑动
//        startX  滚动起始点X坐标
//        startY  滚动起始点Y坐标
//        velocityX    当滑动屏幕时X方向初速度，以每秒像素数计算
//        velocityY    当滑动屏幕时Y方向初速度，以每秒像素数计算
//        minX    X 方向的最小值， scroller 不会 滚过此点。
//        maxX    X 方向的最大值， scroller 不会 滚过此点。
//        minY    Y 方向的最小值， scroller 不会 滚过此点。
//        maxY    Y 方向的最大值， scroller 不会 滚过此点。
//        参考系均为视图坐标

    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}
