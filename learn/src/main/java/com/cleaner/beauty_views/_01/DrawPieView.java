package com.cleaner.beauty_views._01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 描述:
 * Created by mjd on 2017/10/21.
 */

public class DrawPieView extends View {
    int per_distance = 2;
    int sweep1 = 8, sweep2 = 8, sweep3 = 60, sweep4 = 100, sweep5 = 120, sweep6 = 50;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaint2 = new Paint();
    RectF mRectF = new RectF(200, 200, 800, 800);
    RectF mRectF2 = new RectF(180, 180, 780, 780);

    Path mPath = new Path();

    public DrawPieView(Context context) {
        super(context);
    }

    public DrawPieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawPieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#3F51B5"));
        mPaint2.setColor(Color.WHITE);
        mPaint2.setTextSize(28);

        //画扇形
        mPaint.setColor(Color.parseColor("#5E2B7E"));
        canvas.drawArc(mRectF, 0, sweep1, true, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(mRectF, per_distance + sweep1, sweep2, true, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(mRectF, per_distance * 2 + sweep1 + sweep2, sweep3, true, mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawArc(mRectF, per_distance * 3 + sweep1 + sweep2 + sweep3, sweep4, true, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(mRectF2, per_distance * 4 + sweep1 + sweep2 + sweep3 + sweep4, sweep5, true, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(mRectF, per_distance * 5 + sweep1 + sweep2 + sweep3 + sweep4 + sweep5, sweep6, true, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.WHITE);
        mPath.moveTo(330, 220);
        mPath.lineTo(300, 200);
        mPath.lineTo(100, 200);
        canvas.drawPath(mPath, mPaint);
        canvas.drawText("Lollipop", 100, 180, mPaint2);
    }
}
