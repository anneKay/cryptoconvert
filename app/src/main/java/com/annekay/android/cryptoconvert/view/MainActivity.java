package com.annekay.android.cryptoconvert.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.annekay.android.cryptoconvert.R;
import com.annekay.android.cryptoconvert.adapter.CustomPagerAdapter;

public class MainActivity extends AppCompatActivity {
    //public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private Toolbar mTopToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            position = extras.getInt("recent_position");
        }


        ViewPager viewPager = findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}
