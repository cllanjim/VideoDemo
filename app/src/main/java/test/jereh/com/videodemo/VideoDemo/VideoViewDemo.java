package test.jereh.com.videodemo.VideoDemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/14.
 */
public class VideoViewDemo extends VideoView implements GestureDetector.OnGestureListener{
    private List<Uri> mUriList;
    private int current=1;
    GestureDetector detector;
    public VideoViewDemo(Context context) {
        super(context);
        mUriList=new ArrayList<Uri>();
        init();
    }

    public List<Uri> getUriList() {
        return mUriList;
    }

    public void setUriList(List<Uri> uriList) {
        mUriList = uriList;
    }

    private void init() {
        detector = new GestureDetector(getContext(),this);
    }


    public VideoViewDemo(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VideoViewDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VideoViewDemo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return  detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    // e1手势起点的移动事件,e2当前手势点的移动事件,每秒x轴方向移动的像素
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float minMove = 120;  //最小滑动距离
        float minVelocity = 0; //最小滑动速度
        float beginX = e1.getX();
        float endX = e2.getX();
        if(beginX-endX>minMove&&Math.abs(velocityX)>minVelocity){   //左滑
            Toast.makeText(getContext(),velocityX+"左滑",Toast.LENGTH_SHORT).show();
            if (current>0) {
                Uri uri=mUriList.get(current-1);
                current-=1;
                setVideoURI(uri);
                start();
            }else{
                Toast.makeText(getContext(),velocityX+"第一个",Toast.LENGTH_SHORT).show();
            }
        }else if(endX-beginX>minMove&&Math.abs(velocityX)>minVelocity){   //右滑
            Toast.makeText(getContext(),velocityX+"右滑",Toast.LENGTH_SHORT).show();
            if (current<mUriList.size()-1) {
                Uri uri=mUriList.get(current+1);
                current+=1;
                Log.d("jereh","-----------------------------------"+current);
                setVideoURI(uri);
                start();
            }else{
                Toast.makeText(getContext(),velocityX+"最后一个",Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}
