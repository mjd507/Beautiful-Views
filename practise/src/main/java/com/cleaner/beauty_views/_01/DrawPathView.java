package com.cleaner.beauty_views._01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 描述:
 * Created by mjd on 2017/10/17.
 */

public class DrawPathView extends View {
    Paint mPaint = new Paint();
    Path mPath = new Path();
    RectF mRectF1 = new RectF(100, 100, 300, 300);
    RectF mRectF2 = new RectF(300, 100, 500, 300);

    public DrawPathView(Context context) {
        super(context);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.arcTo(mRectF1, -225, 225);
        mPath.arcTo(mRectF2, -180, 225);
        mPath.lineTo(300, 440);
        canvas.drawPath(mPath, mPaint);
    }
}
