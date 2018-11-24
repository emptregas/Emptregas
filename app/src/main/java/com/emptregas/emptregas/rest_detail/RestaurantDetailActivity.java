package com.emptregas.emptregas.rest_detail;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.emptregas.emptregas.R;
import com.emptregas.emptregas.model.Restaurant;
import com.emptregas.emptregas.adapter.ViewPagerStateAdapter;

public class RestaurantDetailActivity extends AppCompatActivity {
    private static String EXTRA_REST_NAME = "restaurant_name";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        initUi();
        setupViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rest_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupViewPager() {
        ViewPagerStateAdapter adapter = new ViewPagerStateAdapter(getSupportFragmentManager());
        adapter.addFrag(new CuisineFragment(), "Cuisine");
        adapter.addFrag(new ReviewFragment(), "Review");
        adapter.addFrag(new InfoFragment(), "Info");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initUi() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        appBarLayout = findViewById(R.id.appBarLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);

        title = getIntent().getStringExtra(EXTRA_REST_NAME);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    //userDetailsSummaryContainer.setVisibility(View.INVISIBLE);
                    collapsingToolbarLayout.setTitle(title);
                    isShow = true;
                } else if (isShow) {
                    //userDetailsSummaryContainer.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    public static Intent newIntent(Context context, Restaurant restaurant) {
        Intent intent = new Intent(context, RestaurantDetailActivity.class);
        intent.putExtra(EXTRA_REST_NAME, restaurant.getName());
        return intent;
    }
}
