package com.cleaner.lancaiview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;

import static java.lang.Math.PI;

/**
 * 描述: 懒财网客户端 秒钟转动效果
 * Created by mjd on 2017/10/22.
 */

public class ClockView extends View {
    private final String TAG = this.getClass().getSimpleName();
    private static final boolean isDebug = false;
    private Paint mPaint = new Paint();
    private Path mPath = new Path();

    /**
     * View 的中心点
     */
    private float[] centerPoint = new float[2];

    /**
     * 组成圆形的每个小圆点的半径
     */
    private float POINT_RADIUS;
    /**
     * 小圆点的颜色
     */
    private int POINT_COLOR;
    /**
     * 悬浮圆点的颜色
     */
    private int OVER_CIRCLE_COLOR;
    /**
     * PathDashPathEffect 第二个参数
     * 这里表示两个圆点的弧形距离 (起点到起点，这里也可说是圆心到圆心)
     */
    private float advance;
    /**
     * 时钟的半径
     */
    float mClockRadius;
    /**
     * 圆点路径效果
     */
    private PathEffect mPathEffect;
    /**
     * 悬浮在圆形上走动的圆所在的矩形坐标
     */
    private RectF mRectF = new RectF();

    /**
     * 悬浮圆点的起始角度从 12 点开始
     */
    private float mAngle = -90.1f;

    private final int MSG_REFRESH_CLOCK = 0;
    private MyHandler mHandler = new MyHandler(this);

    @SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {
        WeakReference<View> mWeakReference;

        MyHandler(View view) {
            mWeakReference = new WeakReference<>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mWeakReference.get() == null) {
                //View 被回收掉了
                mHandler.removeMessages(MSG_REFRESH_CLOCK);
                return;
            }
            if (msg.what == MSG_REFRESH_CLOCK) {
                resetAngle(mAngle + 6);
                mHandler.sendEmptyMessageDelayed(MSG_REFRESH_CLOCK, 1000);
            }
        }
    }

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.getAttrs(context, attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.ClockView);
        mClockRadius = typedArray.getDimensionPixelOffset(R.styleable.ClockView_clock_radius, 300);
        POINT_RADIUS = typedArray.getDimensionPixelOffset(R.styleable.ClockView_point_circle_radius, 6);
        POINT_COLOR = typedArray.getColor(R.styleable.ClockView_point_circle_color, Color.parseColor("#EF5245"));
        OVER_CIRCLE_COLOR = typedArray.getColor(R.styleable.ClockView_over_circle_color, Color.parseColor("#EF5245"));
        typedArray.recycle();
        this.initPathEffect();
    }

    /**
     * 初始化 PathEffect
     */
    private void initPathEffect() {
        mPath.addCircle(POINT_RADIUS, POINT_RADIUS, POINT_RADIUS, Path.Direction.CW);
        //两个圆点的距离 一秒钟秒针扫过 6° 夹角
        advance = this.calcPerSecondArcLen(mClockRadius);
        if (isDebug) {
            Log.i(TAG, "advance * 60 = " + advance * 60 + ",  2πr=" + 2 * PI * mClockRadius);
        }
        mPathEffect = new PathDashPathEffect(mPath, advance, 5, PathDashPathEffect.Style.ROTATE);
    }

    /**
     * 每秒钟经过的弧长
     */
    private float calcPerSecondArcLen(float radius) {
        return calcArcLen(6, radius);
    }

    /**
     * 计算弧长
     * C=2πr
     * 360° ==> 2πr
     * n°  ==> n*πr/180
     */
    private float calcArcLen(float deg, float radius) {
        return (float) (deg * PI * radius * 1.0 / 180);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //默认的宽高为大圆所在的矩形宽高 + 两端悬浮圆形的半径
        int defaultSize = (int) (mClockRadius * 2 + calcPerSecondArcLen(mClockRadius) / 1.2 + getPaddingTop() + getPaddingBottom());
        int measuredWidth = getSize(defaultSize, widthMeasureSpec);
        int measuredHeight = getSize(defaultSize, heightMeasureSpec);
        if (isDebug) {
            Log.i(TAG, "measuredWidth:" + measuredWidth + ", measuredHeight:" + measuredHeight);
        }
        centerPoint[0] = measuredWidth / 2f;
        centerPoint[1] = measuredHeight / 2f;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int getSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:// 想要多大就多大
            case MeasureSpec.AT_MOST: //Wrap_Content
                result = size;
                break;
            case MeasureSpec.EXACTLY: //Match_Content
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setColor(POINT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        //1. 画出圆点时钟
        mPaint.setPathEffect(mPathEffect);
        canvas.drawCircle(centerPoint[0], centerPoint[1], mClockRadius, mPaint);
        //2. 画出圆形上悬浮走动的圆点
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(OVER_CIRCLE_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(advance / 1.2f);
        if (isDebug) {
            Log.i(TAG, "StrokeWidth: " + advance / 1.2);
        }
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        /*
          悬浮在圆形上走动的圆所在的矩形坐标
          比起大圆形，四个顶点分别缩小了圆点的半径的距离
          确保悬浮走动的圆点可以完全覆盖下面的小圆点
         */
        mRectF.left = centerPoint[0] - mClockRadius + POINT_RADIUS;
        mRectF.top = centerPoint[1] - mClockRadius + POINT_RADIUS;
        mRectF.right = centerPoint[0] + mClockRadius - POINT_RADIUS;
        mRectF.bottom = centerPoint[1] + mClockRadius - POINT_RADIUS;
        /*
          mAngle 当前的悬浮圆点的起始角度
          sweepAngle 这里仅仅是一个很小的范围，用来画一条很短的线
          通过上面 setStrokeCap(ROUND);  来让画笔画出一个圆来
         */
        canvas.drawArc(mRectF, mAngle, 0.2f, false, mPaint);

    }

    /**
     * 设置悬浮走动的圆点的起始角度
     */
    private void resetAngle(float angle) {
        mAngle = angle;
        invalidate();
    }

    /*
      ============= 对外提供的方法 ============
     */

    /**
     * 停止时钟转动
     */
    public void stopRun() {
        mHandler.removeMessages(MSG_REFRESH_CLOCK);
    }

    /**
     * 重新开始转动
     */
    public void startRun() {
        stopRun();
        mHandler.sendEmptyMessage(MSG_REFRESH_CLOCK);
    }

}
