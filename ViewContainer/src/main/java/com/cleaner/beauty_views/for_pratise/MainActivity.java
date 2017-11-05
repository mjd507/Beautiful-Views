package com.cleaner.beauty_views.for_pratise;

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

    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.circle_view, "画圆"));
        pageModels.add(new PageModel(R.layout.arc_view, "画椭圆"));
        pageModels.add(new PageModel(R.layout.path_view, "画黑桃"));
        pageModels.add(new PageModel(R.layout.histogram_view, "直方图"));
        pageModels.add(new PageModel(R.layout.pie_view, "饼图"));
        pageModels.add(new PageModel(R.layout.shder_view, "渐变图"));
        pageModels.add(new PageModel(R.layout.color_filter_view, "滤镜"));
        pageModels.add(new PageModel(R.layout.xfer_mode_view, "混合模式"));
        pageModels.add(new PageModel(R.layout.path_effect_view, "线条"));
        pageModels.add(new PageModel(R.layout.mask_view, "遮罩"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_practise);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.layoutId, pageModel.title);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pageModels.get(position).title;
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }



}
