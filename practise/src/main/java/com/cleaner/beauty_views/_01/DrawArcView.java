package com.cleaner.beauty_views._01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 描述:
 * Created by mjd on 2017/10/17.
 */

public class DrawArcView extends View {
    Paint mPaint = new Paint();
    RectF mRectF = new RectF(200, 100, 800, 500);

    public DrawArcView(Context context) {
        super(context);
    }

    public DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(mRectF, -110, 100, true, mPaint);

        canvas.drawArc(mRectF, 20, 140, false, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawArc(mRectF, 180, 60, false, mPaint);
    }
}
