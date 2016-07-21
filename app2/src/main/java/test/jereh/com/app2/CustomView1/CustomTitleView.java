package test.jereh.com.app2.CustomView1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import test.jereh.com.app2.R;

/**
 * 为验证码
 */
public class CustomTitleView extends TextView {

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;
    public CustomTitleView(Context context) {
        this(context,null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
//    默认的布局文件调用的是两个参数的构造方法，
//    所以记得让所有的构造调用我们的三个参数的构造，我们在三个参数的构造中获得自定义属性。
    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView,defStyleAttr,0);
        int n=array.getIndexCount();
        for(int i=0;i<n;i++){
            int attr=array.getIndex(i);
            switch (attr){
                case R.styleable.CustomTitleView_titleText:
                    mTitleText=array.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColors:
                    mTitleTextColor=array.getColor(attr, Color.BLACK);  // 默认颜色设置为黑色
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize=array.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,
                                    getResources().getDisplayMetrics()));
                    break;
            }
        }
        array.recycle();
        /**
         * 获得绘制文本的宽和高
         */
        mPaint=new Paint();
        mPaint.setTextSize(mTitleTextSize);
//        mPaint.setColor(mTitleTextColor);
        mBound=new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);

        this.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                mTitleText = randomText();
                postInvalidate();
            }

        });
    }
    //生成随机数
    private String randomText(){
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4){
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set){
            sb.append("" + i);
        }
        return sb.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;
        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (specMode){
            case MeasureSpec.EXACTLY:// 明确指定了
                width = getPaddingLeft() + getPaddingRight() + specSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                width = getPaddingLeft() + getPaddingRight() + mBound.width();
                break;
        }
        /**
         * 设置高度
         */
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode){
            case MeasureSpec.EXACTLY:// 明确指定了
                height = getPaddingTop() + getPaddingBottom() + specSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                height = getPaddingTop() + getPaddingBottom() + mBound.height();
                break;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);
    }
}
