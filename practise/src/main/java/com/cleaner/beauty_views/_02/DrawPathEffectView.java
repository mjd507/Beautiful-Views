package com.cleaner.beauty_views._02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 描述:
 * Created by mjd on 2017/10/21.
 */

public class DrawPathEffectView extends View {
    Paint mPaint = new Paint();
    Path mPath = new Path();

    PathEffect mPathEffect1 = new CornerPathEffect(50);
    PathEffect mPathEffect2 = new DiscretePathEffect(20, 5);
    //「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制
    // 第二个参数 phase 是虚线的偏移量
    PathEffect mPathEffect3 = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
    Path mPath2 = new Path();
    PathEffect mPathEffect4;

    PathEffect mPathEffect5 = new SumPathEffect(mPathEffect2, mPathEffect3);
    PathEffect mPathEffect6 = new ComposePathEffect(mPathEffect2, mPathEffect3);


    public DrawPathEffectView(Context context) {
        this(context,null);
    }

    public DrawPathEffectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawPathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath2.addCircle(50,50,10, Path.Direction.CW);

        mPathEffect4 = new PathDashPathEffect(mPath2, 40, 0, PathDashPathEffect.Style.TRANSLATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPath.moveTo(50, 120);
        mPath.rLineTo(60, 100);
        mPath.rLineTo(70, -180);
        mPath.rLineTo(70, 150);
        mPath.rLineTo(80, -180);
        mPath.rLineTo(100, 150);

        canvas.save();
        canvas.translate(0, 0);
        mPaint.setPathEffect(mPathEffect1);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 0);
        mPaint.setPathEffect(mPathEffect2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 500);
        mPaint.setPathEffect(mPathEffect3);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 500);
        mPaint.setPathEffect(mPathEffect4);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 1000);
        mPaint.setPathEffect(mPathEffect5);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 1000);
        mPaint.setPathEffect(mPathEffect6);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }
}
