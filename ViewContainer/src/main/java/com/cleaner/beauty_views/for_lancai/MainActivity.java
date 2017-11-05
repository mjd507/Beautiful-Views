package com.cleaner.beauty_views.for_lancai;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.cleaner.beauty_views.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager mViewPager;

    private List<PageModel> mPageModels = new ArrayList<>();
    private void initFragment() {
        mPageModels.add(new PageModel(new Fragment_Clock(),"秒钟转动"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lancai);

        initFragment();
        mViewPager = findViewById(R.id.view_pager);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mPageModels.get(position).mFragment;
            }

            @Override
            public int getCount() {
                return mPageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mPageModels.get(position).mTitle;
            }
        };
        mViewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    class PageModel{
        Fragment mFragment;
        String mTitle;
        PageModel(Fragment fragment,String title){
            mFragment = fragment;
            mTitle = title;
        }
    }
}
