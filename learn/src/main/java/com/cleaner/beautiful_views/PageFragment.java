package com.cleaner.beautiful_views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描述:
 * Created by mjd on 2017/10/17.
 */

public class PageFragment extends Fragment {

    int layoutId;
    String title;

    public static PageFragment newInstance(int layoutId, String title) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("layoutId", layoutId);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            layoutId = args.getInt("layoutId");
            title = args.getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutId, container, false);
    }

}
