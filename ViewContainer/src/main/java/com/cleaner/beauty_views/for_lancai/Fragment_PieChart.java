package com.cleaner.beauty_views.for_lancai;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.cleaner.beauty_views.R;
import com.cleaner.lancaiview.PieChartView;

/**
 * 描述:
 * Created by mjd on 2017/11/11.
 */

public class Fragment_PieChart extends Fragment {

    private PieChartView pie;
    private SeekBar sb1;
    private SeekBar sb2;
    private SeekBar sb3;
    private SeekBar sb4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pie_chart_view, container, false);
        pie = view.findViewById(R.id.pie);
        sb1 = view.findViewById(R.id.sb_1);
        sb2 = view.findViewById(R.id.sb_2);
        sb3 = view.findViewById(R.id.sb_3);
        sb4 = view.findViewById(R.id.sb_4);
        sb1.setOnSeekBarChangeListener(sb1Listener);
        sb2.setOnSeekBarChangeListener(sb2Listener);
        sb3.setOnSeekBarChangeListener(sb3Listener);
        sb4.setOnSeekBarChangeListener(sb4Listener);
        return view;
    }

    @NonNull
    private SeekBar.OnSeekBarChangeListener sb4Listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float pect = (float) (progress * 0.01);
            pie.setPart4Pect(pect);
            int leftPect = (int) ((1 - pect) * 100 / 3);
            sb1.setProgress(leftPect);
            sb2.setProgress(leftPect);
            sb3.setProgress(leftPect);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            sb1.setOnSeekBarChangeListener(null);
            sb2.setOnSeekBarChangeListener(null);
            sb3.setOnSeekBarChangeListener(null);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            sb1.setOnSeekBarChangeListener(sb1Listener);
            sb2.setOnSeekBarChangeListener(sb2Listener);
            sb3.setOnSeekBarChangeListener(sb3Listener);
        }
    };

    @NonNull
    private SeekBar.OnSeekBarChangeListener sb3Listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float pect = (float) (progress * 0.01);
            pie.setPart3Pect(pect);
            int leftPect = (int) ((1 - pect) * 100 / 3);
            sb1.setProgress(leftPect);
            sb2.setProgress(leftPect);
            sb4.setProgress(leftPect);
        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            sb1.setOnSeekBarChangeListener(null);
            sb2.setOnSeekBarChangeListener(null);
            sb4.setOnSeekBarChangeListener(null);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            sb1.setOnSeekBarChangeListener(sb1Listener);
            sb2.setOnSeekBarChangeListener(sb2Listener);
            sb4.setOnSeekBarChangeListener(sb4Listener);
        }
    };

    @NonNull
    private SeekBar.OnSeekBarChangeListener sb2Listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float pect = (float) (progress * 0.01);
            pie.setPart2Pect(pect);
            int leftPect = (int) ((1 - pect) * 100 / 3);
            sb1.setProgress(leftPect);
            sb3.setProgress(leftPect);
            sb4.setProgress(leftPect);
        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            sb1.setOnSeekBarChangeListener(null);
            sb3.setOnSeekBarChangeListener(null);
            sb4.setOnSeekBarChangeListener(null);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            sb1.setOnSeekBarChangeListener(sb1Listener);
            sb3.setOnSeekBarChangeListener(sb3Listener);
            sb4.setOnSeekBarChangeListener(sb4Listener);
        }
    };

    @NonNull
    private SeekBar.OnSeekBarChangeListener sb1Listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float pect = (float) (progress * 0.01);
            pie.setPart1Pect(pect);
            int leftPect = (int) ((1 - pect) * 100 / 3);
            sb2.setProgress(leftPect);
            sb3.setProgress(leftPect);
            sb4.setProgress(leftPect);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            sb2.setOnSeekBarChangeListener(null);
            sb3.setOnSeekBarChangeListener(null);
            sb4.setOnSeekBarChangeListener(null);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            sb2.setOnSeekBarChangeListener(sb2Listener);
            sb3.setOnSeekBarChangeListener(sb3Listener);
            sb4.setOnSeekBarChangeListener(sb4Listener);
        }
    };
}


