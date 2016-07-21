package test.jereh.com.app2.CustomView1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/19.
 */
public class CustomView2 extends View {
    private Paint mPaint;
    public CustomView2(Context context) {
        super(context);
    }

    public CustomView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);//防止边缘的锯齿
    }

    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //-------->绘制白色矩形
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, 800, 800, mPaint);
        mPaint.reset();

        //-------->绘制直线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(450, 30, 570, 170, mPaint);
        mPaint.reset();

        //-------->绘制带边框的矩形
        mPaint.setStrokeWidth(10);
        mPaint.setARGB(150, 90, 255, 0);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF1=new RectF(30, 60, 350, 350);
        canvas.drawRect(rectF1, mPaint);
        mPaint.reset();

        //-------->绘制实心圆
         mPaint.setStrokeWidth(20);
         mPaint.setARGB(150,90,255,0);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF=new RectF(30,60,350,350);
        canvas.drawRect(rectF,mPaint);

        //-------->绘制椭圆
        mPaint.setColor(Color.YELLOW);
        RectF rectF2=new RectF(200, 430, 600, 600);
        canvas.drawOval(rectF2, mPaint);
        mPaint.reset();

        //-------->绘制文字
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(60);
        mPaint.setUnderlineText(true);
        canvas.drawText("Hello Android", 150, 720, mPaint);
        mPaint.reset();
        //canvas.translate相当于移动了坐标的原点，移动了坐标系。
        canvas.drawColor(Color.GREEN);
        Paint paint=new Paint();
        paint.setTextSize(70);
        paint.setColor(Color.BLUE);
        canvas.drawText("蓝色字体为Translate前所画", 20, 80, paint);
        canvas.translate(100,300);
        paint.setColor(Color.BLACK);
        canvas.drawText("黑色字体为Translate后所画", 20, 80, paint);
    }


}
