package test.jereh.com.videodemo.ViewDemo2;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.jereh.com.videodemo.R;

/**
 * 原创作者：
 * 谷哥的小弟
 *
 * 博客地址：
 * http://blog.csdn.net/lfdfhl
 */
public class CollapseView extends LinearLayout {
    private long duration = 350;
    private Context mContext;
    private TextView mNumberTextView;
    private TextView mTitleTextView;
    private RelativeLayout mContentRelativeLayout;
    private RelativeLayout mTitleRelativeLayout;
    private ImageView mArrowImageView;
    int parentWidthMeasureSpec;
    int parentHeightMeasureSpec;
    public CollapseView(Context context) {
        this(context, null);
    }

    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        LayoutInflater.from(mContext).inflate(R.layout.collapse_layout, this);
        initView();
    }


    private void initView() {
        mNumberTextView=(TextView)findViewById(R.id.numberTextView);
        mTitleTextView =(TextView)findViewById(R.id.titleTextView);
        mTitleRelativeLayout= (RelativeLayout) findViewById(R.id.titleRelativeLayout);
        mContentRelativeLayout=(RelativeLayout)findViewById(R.id.contentRelativeLayout);
        mArrowImageView =(ImageView)findViewById(R.id.arrowImageView);
        mTitleRelativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateArrow();
            }
        });

        collapse(mContentRelativeLayout);
    }


    public void setNumber(String number){
        if(!TextUtils.isEmpty(number)){
            mNumberTextView.setText(number);
        }
    }

    public void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            mTitleTextView.setText(title);
        }
    }
    //请注意，该方法的参数是一个布局文件的ID。所以，要显示和隐藏的东西只要写在一个布局文件中就行。这样就灵活多了，可以根据实际需求实现不同的布局就行。
    // 比如在这个例子中，我在一个布局文件中就只放了一个ImageView，然后将这个布局文件的ID传递给该方法就行。
    public void setContent(int resID){
        View view=LayoutInflater.from(mContext).inflate(resID,null);
        RelativeLayout.LayoutParams layoutParams=
                new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        mContentRelativeLayout.addView(view);
    }


    public void rotateArrow() {
        int degree = 0;
        if (mArrowImageView.getTag() == null || mArrowImageView.getTag().equals(true)) {
            mArrowImageView.setTag(false);
            degree = -180;
            expand(mContentRelativeLayout);
        } else {
            degree = 0;
            mArrowImageView.setTag(true);
            collapse(mContentRelativeLayout);
        }
        //属性动画(ViewPropertyAnimator)让箭头旋转
        mArrowImageView.animate().setDuration(duration).rotation(degree);
    }
    //View的measure阶段时我们知道这些控件的宽和高是由系统测量的
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthMeasureSpec=widthMeasureSpec;
        parentHeightMeasureSpec=heightMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    // 展开
    //我们在这里根本不知道这个View(比如此处的content)有多高多宽，我们当然也不知道它要占多大的空间！！
    //在这就按照最直接粗暴的方式来——遇到问题，解决问题！找出该View的宽和高！
    private void expand(final View view) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        //但是这个控件的visibility原本是GONE的，
        // 系统在measure阶段根本不会去测量它的宽和高，所以现在需要我们自己去手动测量。
        view.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        view.setVisibility(View.VISIBLE);
//        动画的interpolatedTime在一定时间内(duration)从0变化到1
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    view.getLayoutParams().height =measuredHeight;
                }else{
                    view.getLayoutParams().height =(int) (measuredHeight * interpolatedTime);
                }
                //表示了content的高从0到measuredHeight的逐次变化，在这个变化的过程中不断调用requestLayout()
                view.requestLayout();
            }


            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    // 折叠
    private void collapse(final View view) {
        final int measuredHeight = view.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = measuredHeight - (int) (measuredHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }
}