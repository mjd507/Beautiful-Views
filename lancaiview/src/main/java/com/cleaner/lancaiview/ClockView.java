package com.cleaner.lancaiview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static java.lang.Math.PI;

/**
 * 描述: 懒财网客户端 时钟转动效果
 * Created by mjd on 2017/10/22.
 */

public class ClockView extends View {
    private final String TAG = this.getClass().getSimpleName();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath;
    /**
     * 组成圆形的每个小圆点的半径
     */
    private final float POINT_RADIUS = 5;
    /**
     * PathDashPathEffect 第二个参数
     * 这里表示两个圆点的弧形距离 (起点到起点，这里也可说是圆心到圆心)
     */
    private float advance;
    /**
     * 时钟的半径
     */
    float mClockRadius = 300;
    /**
     * 圆点路径效果
     */
    private PathEffect mPathEffect;
    /**
     * 悬浮在圆形上走动的圆所在的矩形坐标
     * 比起大圆形，四个顶点分别缩小了圆点的半径的距离
     * 确保悬浮走动的圆点可以完全覆盖下面的小圆点
     */
    private RectF mRectF = new RectF(
            0 - POINT_RADIUS,
            0 - POINT_RADIUS,
            mClockRadius * 2 - POINT_RADIUS,
            mClockRadius * 2 - POINT_RADIUS);


    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initPathEffect();
    }

    /**
     * 初始化 PathEffect
     */
    private void initPathEffect() {
        mPath = new Path();
        mPath.addCircle(POINT_RADIUS, POINT_RADIUS, POINT_RADIUS, Path.Direction.CW);
        //两个圆点的距离 一秒钟秒针扫过 6° 夹角
        advance = this.calcArcLen(6, mClockRadius);
        Log.i(TAG, "advance * 60 = " + advance * 60 + ",  2πr=" + 2 * PI * mClockRadius);
        mPathEffect = new PathDashPathEffect(mPath, advance, 5, PathDashPathEffect.Style.ROTATE);
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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#EF5245"));
        mPaint.setStyle(Paint.Style.STROKE);
        //1. 画出圆点时钟
        mPaint.setPathEffect(mPathEffect);
        canvas.drawCircle(mClockRadius, mClockRadius, mClockRadius, mPaint);

        //2. 画出圆形上悬浮走动的圆点
        mPaint.reset();
        mPaint.setColor(Color.parseColor("#0000ff"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(advance / 2);
        Log.i(TAG, "StrokeWidth: " + advance / 2);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(mRectF, (float) -1.5, 3, false, mPaint);
    }
}
