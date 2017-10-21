package com.cleaner.beauty_views._02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cleaner.beauty_views.R;

/**
 * 描述: Shader 着色器
 * Created by mjd on 2017/10/21.
 */

public class DrawShaderView extends View {
    Paint mPaint = new Paint();
    Shader mLinearShader = new LinearGradient(100, 100, 500, 500, Color.RED, Color.YELLOW, Shader.TileMode.CLAMP);
    Shader mRadialShader = new RadialGradient(800, 300, 200, Color.RED, Color.YELLOW, Shader.TileMode.CLAMP);
    Shader mSweepShader = new SweepGradient(300, 800, Color.RED, Color.YELLOW);
    Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
    Shader mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
    Bitmap mBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
    Shader mBitmapShader2 = new BitmapShader(mBitmap2, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
    Shader mComposeShader = new ComposeShader(mBitmapShader, mBitmapShader2, PorterDuff.Mode.DST_OVER);

    public DrawShaderView(Context context) {
        super(context);
    }

    public DrawShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(mLinearShader);
        canvas.drawCircle(300, 300, 200, mPaint);

        mPaint.setShader(mRadialShader);
        canvas.drawCircle(800, 300, 200, mPaint);

        mPaint.setShader(mSweepShader);
        canvas.drawCircle(300, 800, 200, mPaint);

        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(800, 800, 200, mPaint);

        boolean isOpen = canvas.isHardwareAccelerated();
        if (isOpen) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        mPaint.setShader(mComposeShader);
        canvas.drawCircle(300, 1300, 200, mPaint);

        mPaint.setShader(mComposeShader);
        canvas.drawCircle(800, 1300, 200, mPaint);
    }
}
