package com.cleaner.beauty_views._02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cleaner.beauty_views.R;

/**
 * 描述:
 * Created by mjd on 2017/10/21.
 */

public class DrawXfermodeView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
    Bitmap mBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
    Xfermode mXfermode1 = new PorterDuffXfermode(PorterDuff.Mode.SRC);
    Xfermode mXfermode2 = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    Xfermode mXfermode3 = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    public DrawXfermodeView(Context context) {
        super(context);
    }

    public DrawXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setXfermode(mXfermode1);
        canvas.drawBitmap(mBitmap2, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.drawBitmap(mBitmap, mBitmap.getWidth() + 100, 0, mPaint);
        mPaint.setXfermode(mXfermode2);
        canvas.drawBitmap(mBitmap2, mBitmap.getWidth() + 100, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.drawBitmap(mBitmap, 0, mBitmap.getHeight() + 20, mPaint);
        mPaint.setXfermode(mXfermode3);
        canvas.drawBitmap(mBitmap2, 0, mBitmap.getHeight() + 20, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(saved);
    }
}
