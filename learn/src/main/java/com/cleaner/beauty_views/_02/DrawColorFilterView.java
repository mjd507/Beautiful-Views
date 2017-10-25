package com.cleaner.beauty_views._02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cleaner.beauty_views.R;

/**
 * 描述:
 * Created by mjd on 2017/10/21.
 */

public class DrawColorFilterView extends View {
    Bitmap mBitmap;
    Paint mPaint = new Paint();
    ColorFilter mLightFilter;
    ColorMatrix mColorMatrix;
    ColorFilter mMatrixFilter;
    {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        mLightFilter = new LightingColorFilter(0x00ffff, 0x000000);

        mColorMatrix = new ColorMatrix();
        mColorMatrix.setSaturation(0);
        mMatrixFilter = new ColorMatrixColorFilter(mColorMatrix);
    }
    public DrawColorFilterView(Context context) {
        super(context);
    }

    public DrawColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColorFilter(mLightFilter);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        mPaint.setColorFilter(mMatrixFilter);
        canvas.drawBitmap(mBitmap, mBitmap.getWidth() + 100, 0, mPaint);
    }
}
