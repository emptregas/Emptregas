package com.emptregas.emptregas;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emptregas.emptregas.fragment.AuthFragment;
import com.emptregas.emptregas.interactor.AuthMainInteractor;
import com.emptregas.emptregas.location.LocationActivity;
import com.emptregas.emptregas.model.TokenManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    private TextView splashMessage;
    private ImageView splashLogo;
    private CardView cardView;
    private FrameLayout frameLayout,frameLayout2;
    private FirebaseAuth firebaseAuth;

    private Handler mHandler;
    private final String FRAG_TAG_SIGN_IN_UP = "SIGN_IN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        firebaseAuth = FirebaseAuth.getInstance();

        splashLogo = findViewById(R.id.splashLogo);
        splashMessage = findViewById(R.id.slogan);
        cardView = findViewById(R.id.cardView);
        frameLayout = findViewById(R.id.frameLayout);
        //  frameLayout2 = findViewById(R.id.splashFrame);

        ImageView background = findViewById(R.id.background);

        //   Glide.with(this).load(R.drawable.logo_splash3).into(splashLogo);
//        Glide.with(this).load(R.drawable.splash_background).into(background);

        setupAuthLayout();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                validarSecion();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frameLayout.setVisibility(View.VISIBLE);
                    }
                },600);


            }
        }, 2500);
    }

    private void setupAuthLayout() {
        mHandler = new Handler();
        loadFragment(FRAG_TAG_SIGN_IN_UP);
    }

    private void loadFragment(final String fragTag) {
        Fragment fragment = null;
        switch (fragTag) {
            case FRAG_TAG_SIGN_IN_UP:
                fragment = AuthFragment.newInstance(new AuthMainInteractor() {
                    @Override
                    public void moveToMain() {
                        startActivity(new Intent(SplashActivity.this, LocationActivity.class));
                        finish();
                    }
                });
                break;
        }
        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        final Fragment finalFragment = fragment;
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frameLayout, finalFragment, fragTag);
                fragmentTransaction.commit();
            }
        };

        mHandler.post(mPendingRunnable);

    }

    private void endSplash() {

        int[] location = new int[2];
        splashLogo.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        Log.d("y 1",  String.valueOf(y));
        int y1 = y;
        y = y-112;
        Log.d("y 1",  String.valueOf(y));

        splashLogo.animate().translationY(-1*y).setDuration(600).start();
        //   frameLayout.animate().translationY(y1-292).setDuration(600).start();

        AlphaAnimation animation1 = new AlphaAnimation(1.0f, 0.0f);
        animation1.setDuration(600);
        animation1.setFillAfter(true);
        AlphaAnimation animation2 = new AlphaAnimation(0.0f, 1.0f);
        animation2.setDuration(600);
        animation2.setFillAfter(true);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ViewGroup.LayoutParams params = frameLayout.getLayoutParams();
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                frameLayout.setLayoutParams(params);
                //    splashMessage.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //    splashMessage.startAnimation(animation1);
        //  frameLayout.setVisibility(View.VISIBLE);
        //   frameLayout.startAnimation(animation2);
    }

    public  void validarSecion(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else{
            endSplash();

        }

    }
}
