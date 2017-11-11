package com.cleaner.beauty_views.for_lancai;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cleaner.beauty_views.R;
import com.cleaner.lancaiview.RollTextView;

/**
 * 描述:
 * Created by mjd on 2017/11/11.
 */

public class Fragment_RollText extends Fragment implements View.OnClickListener {

    private View mView;
    private RollTextView mRollTv;
    private EditText mEt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_roll_text_view, container, false);
        mRollTv = mView.findViewById(R.id.roll_tv);
        mEt = mView.findViewById(R.id.ed_money);
        Button mBtn = mView.findViewById(R.id.btn_ok);
        Button mStop = mView.findViewById(R.id.btn_stop);
        Button mStart = mView.findViewById(R.id.btn_start);
        mBtn.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mStart.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok) {
            String str = mEt.getText().toString().trim();
            mRollTv.setInitNum(Double.valueOf(str));
        } else if (v.getId() == R.id.btn_stop) {
            mRollTv.stopRoll();
        }else if (v.getId() == R.id.btn_start) {
            mRollTv.startRoll();
        }
    }
}
