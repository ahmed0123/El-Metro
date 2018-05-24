package com.example.hendawy.metro.activites;

import android.app.SearchManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hendawy.metro.FilterManager;
import com.example.hendawy.metro.R;
import com.example.hendawy.metro.adapter.TabsPagerAdapter;
import com.example.hendawy.metro.fragments.AllLineFragment;
import com.example.hendawy.metro.fragments.LineOne;
import com.example.hendawy.metro.fragments.LineThree;
import com.example.hendawy.metro.fragments.LineTwo;

public class StationActivity extends AppCompatActivity  {

    private static StationActivity instance;
    private FilterManager filterManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        instance = this;
        filterManager = new FilterManager();

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void setupViewPager(ViewPager viewPager) {
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllLineFragment(), "All Stations");
        adapter.addFragment(new LineOne(), "Line 1");
        adapter.addFragment(new LineTwo(), "Line 2");
        adapter.addFragment(new LineThree(), "Line 3");

        viewPager.setAdapter(adapter);
    }
    

}
