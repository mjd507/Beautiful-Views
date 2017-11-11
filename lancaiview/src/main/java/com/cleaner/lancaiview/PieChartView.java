package com.cleaner.lancaiview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 描述: 懒财网客户端 懒定期资产配置饼状图
 * Created by mjd on 2017/11/11.
 */

public class PieChartView extends View {
    private final String TAG = this.getClass().getSimpleName();
    private static final boolean isDebug = false;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF mRectf = new RectF();
    //大圆半径
    private int mCircleRadius;
    //圆环宽度
    private int mCircleWidth;
    //扇形半径 ==> 大圆半径 - 线条宽度/2
    private float mArcRadius;
    private int mPart1Color;
    private int mPart2Color;
    private int mPart3Color;
    private int mPart4Color;
    private float[] centerPoint = new float[2];

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.getAttrs(context, attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.PieChartView);
        mCircleRadius = typedArray.getDimensionPixelOffset(R.styleable.PieChartView_circle_radius, 300);
        mCircleWidth = typedArray.getDimensionPixelOffset(R.styleable.PieChartView_circle_width, 30);
        mArcRadius = mCircleRadius - mCircleWidth / 2;
        mPart1Color = typedArray.getColor(R.styleable.PieChartView_part1_color, Color.parseColor("#3789E2"));
        mPart2Color = typedArray.getColor(R.styleable.PieChartView_part2_color, Color.parseColor("#6CC4F6"));
        mPart3Color = typedArray.getColor(R.styleable.PieChartView_part3_color, Color.parseColor("#E29363"));
        mPart4Color = typedArray.getColor(R.styleable.PieChartView_part4_color, Color.parseColor("#EA5043"));
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defaultSize = mCircleRadius * 2 + getPaddingTop() + getPaddingBottom();
        int measuredWidth = getSize(defaultSize, widthMeasureSpec);
        int measuredHeight = getSize(defaultSize, heightMeasureSpec);
        if (isDebug) {
            Log.i(TAG, "measuredWidth:" + measuredWidth + ", measuredHeight:" + measuredHeight);
        }
        centerPoint[0] = measuredWidth / 2f;
        centerPoint[1] = measuredHeight / 2f;
        setMeasuredDimension(measuredWidth, measuredHeight);
        mRectf.left = centerPoint[0] - mArcRadius;
        mRectf.top = centerPoint[1] - mArcRadius;
        mRectf.right = centerPoint[0] + mArcRadius;
        mRectf.bottom = centerPoint[1] + mArcRadius;
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

    private float mPart1Progress = 160;
    private float mPart2Progress = 120;
    private float mPart3Progress = 80;
    private float mPart4Progress = 0;

    public void setPart1Progress(float progress) {
        mPart1Progress = progress;
    }

    public void setPart2Progress(float progress) {
        mPart2Progress = progress;
    }

    public void setPart3Progress(float progress) {
        mPart3Progress = progress;
    }

    public void setPart5Progress(float progress) {
        mPart4Progress = progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth);

        if (mPart1Progress != 0) {
            mPaint.setColor(mPart1Color);
            canvas.drawArc(mRectf, -90, mPart1Progress, true, mPaint);
        }

        if (mPart2Progress != 0) {
            mPaint.setColor(mPart2Color);
            canvas.drawArc(mRectf, -90 + mPart1Progress, mPart2Progress, true, mPaint);
        }
        if (mPart3Progress != 0) {
            mPaint.setColor(mPart3Color);
            canvas.drawArc(mRectf, -90 + mPart1Progress + mPart2Progress, mPart3Progress, true, mPaint);
        }
        if (mPart4Progress != 0) {
            mPaint.setColor(mPart4Color);
            canvas.drawArc(mRectf, -90 + mPart1Progress + mPart2Progress + mPart3Progress, mPart4Progress, true, mPaint);
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(centerPoint[0], centerPoint[1], mCircleRadius - mCircleWidth, mPaint);
    }
}
