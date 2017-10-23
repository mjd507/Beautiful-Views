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

    Bitmap mBitmap;
    Shader mBitmapShader;

    Bitmap mBitmap2;
    Shader mBitmapShader2;

    Shader mComposeShader;
    Shader mComposeShader2;

    public DrawShaderView(Context context) {
        super(context);
        init(context);
    }

    public DrawShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.batman);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, 400, 400, true);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        mBitmap2 = Bitmap.createScaledBitmap(mBitmap2, 400, 400, true);
        mBitmapShader2 = new BitmapShader(mBitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mComposeShader = new ComposeShader(mBitmapShader, mBitmapShader2, PorterDuff.Mode.SRC_OVER);

        mComposeShader2 = new ComposeShader(mBitmapShader, mBitmapShader2, PorterDuff.Mode.DST_OUT);
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

        //绘制 BitmapShader
        canvas.save();
        canvas.translate(600, 600); //bitmap 的左上角
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(200, 200, 200, mPaint);
        canvas.restore();

        setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);//关闭硬件加速

        canvas.save();
        canvas.translate(100, 1100);
        mPaint.setShader(mComposeShader);
        canvas.drawCircle(200, 200, 200, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(600, 1100);
        mPaint.setShader(mComposeShader2);
        canvas.drawCircle(200, 200, 200, mPaint);
        canvas.restore();
    }
}
