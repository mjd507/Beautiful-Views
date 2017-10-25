package com.cleaner.beauty_views._02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cleaner.beauty_views.R;

/**
 * 描述:
 * Created by mjd on 2017/10/25.
 */

public class DrawMaskView extends View {
    Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.what_the_fuck);
    Paint mPaint = new Paint();
    MaskFilter mMaskFilter1 = new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL);
    MaskFilter mMaskFilter2 = new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID);
    MaskFilter mMaskFilter3 = new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER);
    MaskFilter mMaskFilter4 = new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER);

    public DrawMaskView(Context context) {
        super(context);
    }

    public DrawMaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawMaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);//关闭硬件加速

        canvas.save();
        canvas.translate(0, 0);
        mPaint.setMaskFilter(mMaskFilter1);
        canvas.drawBitmap(mBitmap, 100, 100, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 0);
        mPaint.setMaskFilter(mMaskFilter2);
        canvas.drawBitmap(mBitmap, 100, 100, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 500);
        mPaint.setMaskFilter(mMaskFilter3);
        canvas.drawBitmap(mBitmap, 100, 100, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 500);
        mPaint.setMaskFilter(mMaskFilter4);
        canvas.drawBitmap(mBitmap, 100, 100, mPaint);
        canvas.restore();
    }
}
