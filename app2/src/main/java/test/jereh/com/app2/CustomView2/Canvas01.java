package test.jereh.com.app2.CustomView2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/26.
 */
public class Canvas01 extends View{
    private Paint mPaint=new Paint();

    public Canvas01(Context context) {
        super(context);
    }

    public Canvas01(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL); //设置画笔模式为填充
        mPaint.setStrokeWidth(20f); //设置画笔宽度为10px
    }
    //关于坐标原点默认在左上角，水平向右为x轴增大方向，竖直向下为y轴增大方向。
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(300,300,mPaint);
        canvas.drawPoints(new float[]{   //绘制一组点，坐标位置由float数组指定
                500,500,
                500,600,
                500,700,
        },mPaint);
        //绘制线
        canvas.drawLine(100,400,800,1200,mPaint); // 在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
                100,200,200,200,
                100,300,200,300
        },mPaint);
        //绘制矩形------Rect是int(整形)的，而RectF是float(单精度浮点型)
        // 第一种
//        canvas.drawRect(600,600,800,700,mPaint);
//        Rect rect=new Rect(600,600,800,700);
//        canvas.drawRect(rect,mPaint);
        // 第三种
//        RectF rectF=new RectF(600,600,800,700);
//        canvas.drawRect(rectF,mPaint);
//        RectF rectF=new RectF(400,400,900,700);
//        canvas.drawRoundRect(rectF,30,30,mPaint);
//        canvas.drawRoundRect(600,600,900,700,30,30,mPaint);第二种方法在API21的时候才添加上
        // 第一种----椭圆
//        RectF rectF1 = new RectF(200,200,800,400);
//        canvas.drawOval(rectF1,mPaint);
        // 第二种
//        canvas.drawOval(200,200,800,400,mPaint);
        //绘制圆
//        canvas.drawCircle(400,400,200,mPaint);

       //绘制圆弧
        RectF rectF=new RectF(100,100,800,400);
        mPaint.setColor(Color.GRAY);//绘制背景
        canvas.drawRect(rectF,mPaint);//绘制带背景的矩形
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF,0,90,false,mPaint);//绘制圆弧
        //-------------------------------------
        RectF rectF1=new RectF(100,600,800,900);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF1,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF1,0,90,true,mPaint);

        //描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200,200,100,mPaint);
        //填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200,500,100,mPaint);
        //描边+填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200,800,100,mPaint);
    }
}
