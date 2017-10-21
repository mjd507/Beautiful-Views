package com.cleaner.beauty_views._02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 描述:
 * Created by mjd on 2017/10/21.
 */

public class DrawStrokeView extends View {
    Paint mPaint = new Paint();
    Path mPath = new Path();

    PathEffect mDashPathEffect = new DashPathEffect(new float[]{50, 30}, 0);

    public DrawStrokeView(Context context) {
        super(context);
    }

    public DrawStrokeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawStrokeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(40);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100, 100, 500, 100, mPaint);

        mPath.moveTo(200, 200);
        mPath.lineTo(400, 200);
        mPath.rLineTo(-160, 120);
        canvas.translate(100, 100);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(mPath, mPaint);

        mPaint.setStrokeWidth(10);
        mPaint.setPathEffect(mDashPathEffect);
        canvas.drawCircle(600, 900, 200, mPaint);
    }
}
