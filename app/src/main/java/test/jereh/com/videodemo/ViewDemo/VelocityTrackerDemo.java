package test.jereh.com.videodemo.ViewDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.VelocityTracker;

/**
 * Created by Administrator on 2016/5/19.
 */
public class VelocityTrackerDemo extends AppCompatActivity {
    VelocityTracker mVelocityTracker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.开始追踪 2.获取相应速度 3.停止追踪
        startVelocityTracker(null);
        getVelocity();
        stopVelocityTracker();
    }

    public void startVelocityTracker(MotionEvent event){
        if(mVelocityTracker==null){
            mVelocityTracker=VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }
    public int getVelocity() {
        //设置单位，1000表示1秒时间内运动的像素
        mVelocityTracker.computeCurrentVelocity(1000);
        //获取1秒内X方向所滑动的像素值
        int xVelocity= (int) mVelocityTracker.getXVelocity();
        return Math.abs(xVelocity);   //y方向和这个类似
    }
    private void stopVelocityTracker() {
        //当我们调用跟设备硬件相关的播放器，录音机，摄像头，要及时的释放
        if(mVelocityTracker!=null){
            mVelocityTracker.recycle();
            mVelocityTracker=null;
        }
    }

}
