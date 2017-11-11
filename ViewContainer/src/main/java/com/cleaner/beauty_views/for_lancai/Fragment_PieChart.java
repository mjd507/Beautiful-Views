package com.cleaner.beauty_views.for_lancai;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleaner.beauty_views.R;

/**
 * 描述:
 * Created by mjd on 2017/11/11.
 */

public class Fragment_PieChart extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pie_chart_view, container, false);
        return view;
    }

}
