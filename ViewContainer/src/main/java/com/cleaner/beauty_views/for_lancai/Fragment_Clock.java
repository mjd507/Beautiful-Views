package com.cleaner.beauty_views.for_lancai;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cleaner.beauty_views.R;
import com.cleaner.lancaiview.ClockView;
import com.cleaner.lancaiview.RollTextView;

/**
 * 描述:
 * Created by mjd on 2017/11/5.
 */

public class Fragment_Clock extends Fragment implements View.OnClickListener {

    private View mView;
    private Button mBtnStart;
    private Button mBtnStop;
    private ClockView mClockView;
    private RollTextView mRollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_clock_view, container, false);
        mRollView = mView.findViewById(R.id.roll_tv);
        mClockView = mView.findViewById(R.id.mClockView);
        mBtnStart = mView.findViewById(R.id.start);
        mBtnStop = mView.findViewById(R.id.stop);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mRollView.setInitNum(15000);
        mClockView.startRun();
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                mClockView.startRun();
                mRollView.startRoll();
                break;
            case R.id.stop:
                mClockView.stopRun();
                mRollView.stopRoll();
                break;
        }
    }
}
