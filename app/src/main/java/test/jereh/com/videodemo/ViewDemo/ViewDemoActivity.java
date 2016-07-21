package test.jereh.com.videodemo.ViewDemo;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import test.jereh.com.videodemo.R;

public class ViewDemoActivity extends AppCompatActivity {
    private Context mContext;
    GestureDetector mGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_demo);
        /**
         * 自定义View基础知识   Configuration
         * Configuration:用来描述设备的配置信息
         *比如用户的配置信息：locale和scaling等
         *比如设备的相关信息：输入模式，屏幕大小，屏幕方向
         */
        Configuration configuration=getResources().getConfiguration();
        //获取国家码
        int countryCode=configuration.mcc;
        //获取网络码
        int networkCode=configuration.mnc;
        //判断横竖屏
        if(configuration.orientation==Configuration.ORIENTATION_PORTRAIT){

        }else {

        }
        /**
         * ViewConfiguration
         * 提供一些自定义控件用到的标准常量，比如UI超时（），尺寸大小,滑动距离，敏感度等
         */
        //实例获取
        ViewConfiguration viewConfiguration=ViewConfiguration.get(mContext);//随便找的一个mContext
         //常用对象的方法
        //1.touchSlop-------系统判断是否滑动
        int touchSlop=viewConfiguration.getScaledTouchSlop();
        //获取Fling速度的最小值和最大值
        int minimumVelocity=viewConfiguration.getScaledMinimumFlingVelocity();
        int MaximumVelocity=viewConfiguration.getScaledMaximumFlingVelocity();
        //判断是否有物理按键
        boolean isHavePermaneMeaukey=viewConfiguration.hasPermanentMenuKey();

        //常用的静态方法
        //双击间隔时间，在该时间内是双击还是单机-----是简单的UI超时
        int doubleTapTimeout=ViewConfiguration.getDoubleTapTimeout();
        //按住状态转变为长按状态需要的时间
        int longPressTimeout=ViewConfiguration.getLongPressTimeout();
        //重复按键时间
        int time=ViewConfiguration.getKeyRepeatTimeout();

        /**
         * GestureDetector主要是简化Touch操作
         */
        mContext=this;
        init();
    }

    private void init(){
        mGestureDetector=new GestureDetector(mContext,new GestureLisenerImpl());
    }
    private class GestureLisenerImpl implements GestureDetector.OnGestureListener{
        //触摸屏幕是均会调用该方法
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }
        //轻轻的击一下屏幕
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }
        //手指滑动调用
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }
        //长按屏幕调用
        @Override
        public void onLongPress(MotionEvent e) {

        }
        //手指在屏幕上拖动时调用
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

    }
     //把Touch事件交给它------这里是Actitvty的Touch事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    /**
     * VelocityTracker用于跟踪触摸屏事件（如Flinging及其他手势事件等）的速率；
     * 手指滑动的速度
     */
    private void Tracker(){
       //测试令建了一个类
    }
    /**
     * Scroller滑动
     * scrollTo()和scrollBy()的关系:scrollBy（）对scrollTo()进行了封装
     * scroll的本质：scroll滑动的只是滑动的View的内容！！！！！！！好好理解这句话
     * scrollTo()和scrollBy()的坐标说明
     * 例：mTextView.scrollTo(0,25);---------负方向移动  重绘  set
     */

    /**
     * ViewDragHelper用于简化View的拖拽的相关操作
     * 侧滑等
     * 拖拽是否越界，拖拽一般干什么事
     */

    /**
     * View.MeasureSpec
     * 1.封装了父布局传递给子View的布局要求
     * 2.可以表示一个宽或一个高
     * 3.是由size和mode组成
     */

    public void testMeasure(){
        //获取mode
//        int specMode= View.MeasureSpec.getMode(measureSpec);
        //获取size
//        int specSize= View.MeasureSpec.getSize(measureSpec);
        //生成
//        int measureSpec= View.MeasureSpec.makeMeasureSpec(size,mode);

        //大小是像素    mode:
//        View.MeasureSpec.EXACTLY 父view已经检测到子view的精确大小，
// 在这个模式下子view的大小就是刚才的size
//        View.MeasureSpec.AT_MOST  表示父容器还没有检测到子view的大小，
// 但是指定了大小，子view最大不能超过这个size
//        View.MeasureSpec.UNSPECIFIED  父容器不对子view的大小限制，一般不用
//        父view测量子View  --------measureChildWithMargins()     getChildMeasureSpec()
//        measureChildWithMargins()-----源码：获取子View的宽和高 设置

        //ViewGroup 中有体现：子view的MeasureSpec是由父View的MeasureSpec和自己本身的布局参数决定的
         //是先获取父布局的mode和size    已经被使用的大小    可用的最大空间
        //switch()判断的是父容器的mode!!!!!!!一种是具体的值 一种是machParent  一种是warparent

        //View源码在onMeasure调用setMeasuredDimension（）
        //1.在onMeasure调用setMeasuredDimension（）设置view的宽和高
        //2.在setMeasuredDimension（）调----------getDefaultSize()获取view的宽和高
        //在getDefaultSize（）方法中又会调用到getSuggestedMinimumWidth()或getSuggestedMinimumHeight()获取到view的宽和高的最小值
        //按调用的倒序解析源码
        //getSuggestedMinimumWidth()源码考虑的背景是否是空------getDefaultSize（）------setMeasuredDimension（）

//        View源码中的layout（）用于确定自己View在它父view中的位置
        //ViewGroup源码中的onLayout()用于确定子view的位置
//        layout(int l, int t, int r, int b)传进来的参数代表了子view相对于父view的左上右下的一个坐标值，里面的源码有很多现在用不到
// boolean changed = isLayoutModeOptical(mParent) ?setOpticalFrame(l, t, r, b) : setFrame(l, t, r, b);————确实了子view在父view中的位置
//        setFrame（）传进来的四个值和原来的四个值做对比，只要有任意一个不等，就是true！!
//        如果变化会调用onLayout()————————在layout()中调用onLayout()用于确定子view的大小和位置  （viewGroup有子view）
//        在ViewGoup中onLayout（）是抽象的，也就是其子view都会重写这个方法
//          viewgoup首先执行了 layout（）确定了自己在它父view中的位置
//        然后又执行了onLayout()确定了每一个子view的位置，每个子view再调用Layout()确定自己在ViewGroup中的位置
//        Layout()确定自己本身在ViewGroup中的位置       ViewGoup的onLayout()确定子View的位置

        //总结：1.获取View的测量大小measuredWidth和measuredHeight的时机————————在onLayout（）获取
//        2.getMeasuredWidth()和getWidth()的区别-----时机不同：getMeasuredWidth在Measure结束调用，getWidth在layout结束调用
//        返回的计算方式不同：getMeasuredWidth是由setMeasureD..决定的，getWidth是由控件的右坐标-坐标决定的，Right-Left
//        3.view.getLeft/Right()   view.getTop/Bottom（）坐标问题
//        4.合理地自定义ViewGoup
//        一般继承系统已经有的布局比较好

    }


}
