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
 * Created by mjd on 2017/10/20.
 */

public class DrawHistogramView extends View {
    Paint mPaint1 = new Paint(); //画线
    Paint mPaint2 = new Paint(); //画矩形
    Path mPath1 = new Path(); //画线
    Path mPath2 = new Path(); //画矩形
    int LINE_WIDTH = 1000;
    int LINE_HEIGHT = 600;
    int per_height = 15; //每个百分比的高度
    int per_width = 90;
    int per_distance = 30;
    int startX = 100;
    int startY = 100;
    //32 28 18 16 3 3 1
    RectF mRect1 = new RectF(startX + per_distance * 1 + per_width * 0, LINE_HEIGHT - 1 * per_height, startX + per_distance * 1 + per_width * 1, LINE_HEIGHT);
    RectF mRect2 = new RectF(startX + per_distance * 2 + per_width * 1, LINE_HEIGHT - 3 * per_height, startX + per_distance * 2 + per_width * 2, LINE_HEIGHT);
    RectF mRect3 = new RectF(startX + per_distance * 3 + per_width * 2, LINE_HEIGHT - 3 * per_height, startX + per_distance * 3 + per_width * 3, LINE_HEIGHT);
    RectF mRect4 = new RectF(startX + per_distance * 4 + per_width * 3, LINE_HEIGHT - 16 * per_height, startX + per_distance * 4 + per_width * 4, LINE_HEIGHT);
    RectF mRect5 = new RectF(startX + per_distance * 5 + per_width * 4, LINE_HEIGHT - 18 * per_height, startX + per_distance * 5 + per_width * 5, LINE_HEIGHT);
    RectF mRect6 = new RectF(startX + per_distance * 6 + per_width * 5, LINE_HEIGHT - 28 * per_height, startX + per_distance * 6 + per_width * 6, LINE_HEIGHT);
    RectF mRect7 = new RectF(startX + per_distance * 7 + per_width * 6, LINE_HEIGHT - 32 * per_height, startX + per_distance * 7 + per_width * 7, LINE_HEIGHT);

    public DrawHistogramView(Context context) {
        super(context);
    }

    public DrawHistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawHistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#3F51B5"));
        //画线
        mPaint1.setColor(Color.WHITE);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(5);
        mPath1.moveTo(startX, startY);
        mPath1.lineTo(startX, LINE_HEIGHT);
        mPath1.lineTo(LINE_WIDTH, LINE_HEIGHT);
        canvas.drawPath(mPath1, mPaint1);
        //画矩形
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setColor(Color.GREEN);
        mPaint1.setStrokeWidth(0);
        mPaint1.setColor(Color.WHITE);
        mPaint1.setTextSize(24);
        mPath2.addRect(mRect1, Path.Direction.CW);
        mPath2.addRect(mRect2, Path.Direction.CW);
        mPath2.addRect(mRect3, Path.Direction.CW);
        mPath2.addRect(mRect4, Path.Direction.CW);
        mPath2.addRect(mRect5, Path.Direction.CW);
        mPath2.addRect(mRect6, Path.Direction.CW);
        mPath2.addRect(mRect7, Path.Direction.CW);
        canvas.drawPath(mPath2, mPaint2);
        //画文字
        canvas.drawText("Froyo", 110 + 1 * per_distance + 0 * per_width, LINE_HEIGHT + 30, mPaint1);
        canvas.drawText("GB", 130 + 2 * per_distance + 1 * per_width, LINE_HEIGHT + 30, mPaint1);
        canvas.drawText("IC S", 120 + 3 * per_distance + 2 * per_width, LINE_HEIGHT + 30, mPaint1);
        canvas.drawText("JB", 130 + 4 * per_distance + 3 * per_width, LINE_HEIGHT + 30, mPaint1);
        canvas.drawText("KK", 130 + 5 * per_distance + 4 * per_width, LINE_HEIGHT + 30, mPaint1);
        canvas.drawText("L", 130 + 6 * per_distance + 5 * per_width, LINE_HEIGHT + 30, mPaint1);
        canvas.drawText("M", 130 + 7 * per_distance + 6 * per_width, LINE_HEIGHT + 30, mPaint1);

        mPaint1.setTextSize(40);
        canvas.drawText("直方图", 480, LINE_HEIGHT + 100, mPaint1);

    }
}
