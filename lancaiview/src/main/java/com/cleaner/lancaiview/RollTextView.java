package com.cleaner.lancaiview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

/**
 * 描述: 懒财网客户端 资产滚动的文字效果
 * Created by mjd on 2017/11/11.
 */

public class RollTextView extends View {
    private final String TAG = this.getClass().getSimpleName();
    private static final boolean isDebug = true;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] centerPoint = new float[2];
    private int mIntegerSize;
    private int mDecimalSize;
    private String mIntegerValue;
    private String mDecimalValue;

    private final int MSG_REFRESH_TEXT = 0;


    @SuppressLint("HandlerLeak")
    private class MyHandler extends android.os.Handler {
        private WeakReference<View> weakRef;

        MyHandler(View view) {
            weakRef = new WeakReference<>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakRef.get() == null) {
                //View 被回收掉了
                mHandler.removeMessages(MSG_REFRESH_TEXT);
                return;
            }
            if (msg.what == MSG_REFRESH_TEXT) {
                if (mInitText == 0) {
                    mHandler.removeMessages(MSG_REFRESH_TEXT);
                    return;
                }
                mInitText += mAddNumber * 100;
                if (isDebug) {
                    Log.e(TAG, mInitText + "");
                }
                calcIntegerAndDecimal(mInitText);
                mHandler.sendEmptyMessageDelayed(MSG_REFRESH_TEXT, 100);
            }
        }
    }

    private MyHandler mHandler = new MyHandler(this);

    public RollTextView(Context context) {
        this(context, null);
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.getAttrs(context, attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.RollTextView);
        mIntegerSize = typedArray.getDimensionPixelOffset(R.styleable.RollTextView_integer_textSize, 90);
        mDecimalSize = typedArray.getDimensionPixelOffset(R.styleable.RollTextView_decimal_textSize, 54);
        typedArray.recycle();
    }

    private double mInitText = 0; //初始数字
    private double mAddNumber; // 每毫秒增长的值

    public void setInitNum(double text) {
        mInitText = text;
        //年化 6.6% ==>
        // 10000 元  360 * 24 * 60 * 60 秒  收益 660
        // 10000 元  1s收益 660/31104000
        mAddNumber = text / 10000 * 660 / 31104000 / 1000;
        if (isDebug) {
            Log.e(TAG, mAddNumber + "----");
        }
        mHandler.sendEmptyMessageDelayed(MSG_REFRESH_TEXT, 500);
    }

    private void calcIntegerAndDecimal(double num) {
        String numStr = String.valueOf(num);
        String[] numArr = numStr.split("\\.");
        mIntegerValue = numArr[0];
        String decimal;
        if (numArr[0].length() < 5) { // < 10000，保留八位小数
            decimal = new DecimalFormat("#.00000000").format(num);
        } else {
            decimal = new DecimalFormat("#.000000").format(num);
        }
        mDecimalValue = decimal;

        //每三位整数以逗号分隔
        mIntegerValue = new DecimalFormat(",###").format(Integer.valueOf(mIntegerValue));
        mDecimalValue = decimal.split("\\.")[1];
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defaultSize = mIntegerSize * 2 + getPaddingTop() + getPaddingBottom();
        int measuredWidth = getSize(mIntegerSize * 13, widthMeasureSpec);
        int measuredHeight = getSize(defaultSize, heightMeasureSpec);
        if (isDebug) {
            Log.i(TAG, "measuredWidth:" + measuredWidth + ", measuredHeight:" + measuredHeight);
        }
        centerPoint[0] = measuredWidth / 2f;
        centerPoint[1] = measuredHeight / 2f;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int getSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:// 想要多大就多大
            case MeasureSpec.AT_MOST: //Wrap_Content
                result = size;
                break;
            case MeasureSpec.EXACTLY: //Match_Content
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mInitText == 0) return;
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(mIntegerSize);

        float fontSpacing = mPaint.getFontSpacing();
        mPaint.setTextSize(mIntegerSize);
        float integerStrWidth = mPaint.measureText(mIntegerValue);
        float dotWidth = mPaint.measureText(".");
        mPaint.setTextSize(mDecimalSize);
        float decimalWidth = mPaint.measureText(mDecimalValue);
        float numWidth = integerStrWidth + dotWidth + decimalWidth;
        //绘制整数
        mPaint.setTextSize(mIntegerSize);
        canvas.drawText(mIntegerValue, centerPoint[0] - numWidth / 2, fontSpacing, mPaint);

        //绘制点
        mPaint.setTextSize(mIntegerSize);
        canvas.drawText(".", centerPoint[0] - numWidth / 2 + integerStrWidth, fontSpacing, mPaint);

        //绘制小数
        mPaint.setTextSize(mDecimalSize);
        canvas.drawText(mDecimalValue, centerPoint[0] - numWidth / 2 + integerStrWidth + dotWidth, fontSpacing, mPaint);
    }

    public void stopRoll() {
        mHandler.removeMessages(MSG_REFRESH_TEXT);
    }

    public void startRoll() {
        stopRoll();
        mHandler.sendEmptyMessage(MSG_REFRESH_TEXT);
    }

}
