package com.emptregas.emptregas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.emptregas.emptregas.adapter.NotificationsAdapter;
import com.emptregas.emptregas.checkout.CheckoutActivity;

/**
 * Created by a_man on 23-01-2018.
 */

public class NotificationsActivity extends AppCompatActivity {
    private RecyclerView cartRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        cartRecycler = findViewById(R.id.cartRecycler);
        setupCartRecycler();
        findViewById(R.id.checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationsActivity.this, CheckoutActivity.class));
            }
        });
    }

    private void setupCartRecycler() {
        cartRecycler.setLayoutManager(new LinearLayoutManager(this));
        cartRecycler.setAdapter(new NotificationsAdapter(this));
    }
}
