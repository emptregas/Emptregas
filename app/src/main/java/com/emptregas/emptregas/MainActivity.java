package com.emptregas.emptregas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.emptregas.emptregas.fragment.DetailsFragment;
import com.emptregas.emptregas.fragment.FavoriteFragment;
import com.emptregas.emptregas.fragment.HomeFragment;
import com.emptregas.emptregas.fragment.MyReviewsFragment;
import com.emptregas.emptregas.fragment.OrderDetailFragment;
import com.emptregas.emptregas.fragment.OrdersFragment;
import com.emptregas.emptregas.fragment.SupportFragment;
import com.emptregas.emptregas.adapter.DrawerListAdapter;
import com.emptregas.emptregas.model.NavItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private NavigationView navigationView;
    private Toolbar toolbar;
    private LinearLayout toolbarLayout;

    private Handler mHandler;
    private final String FRAG_TAG_HOME = "Home";
    private final String FRAG_TAG_FAVORITE = "Contactos";
    private final String FRAG_TAG_REVIEWS = "Mis Calificaciones";
    private final String FRAG_TAG_SUPPORT = "Contactenos";
    private final String FRAG_TAG_ORDERS = "Mis Ordenes";
    private final String FRAG_TAG_DETAILS = "Mis detalles";
    private final String FRAG_TAG_ORDER= "Detalles";
    private LinearLayout content_logo;
    private String fragTagCurrent = null;
    private int REQUEST_CODE_LOCATION = 99;
    private TextView userName,emailUser;

    TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     //   content_logo = (LinearLayout) findViewById(R.id.content_logo);
        ImageView background = findViewById(R.id.background);
      //  ImageView headerIcon = findViewById(R.id.headerIcon);
      //  Glide.with(this).load(R.drawable.chef_logo).into(headerIcon);
        Glide.with(this).load(R.drawable.background).into(background);
        userName = (TextView)findViewById(R.id.nameUser) ;
        emailUser = (TextView) findViewById(R.id.emailUser);

        userName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        emailUser.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        mNavItems.add(new NavItem("Home", "Inicio", R.drawable.ic_store_white_24dp));
        mNavItems.add(new NavItem("Ordenes", "Ver mis Ordenes", R.drawable.list));

     //   mNavItems.add(new NavItem("Agenda", "Costactos, Direcciones", R.drawable.ic_person_pin_white_24dp));
        //   mNavItems.add(new NavItem("Favoritos", "Lugares Favoritos", R.drawable.ic_favorite_white_24dp));
    //    mNavItems.add(new NavItem("Calificaciones", "Ver mis Calificaciones", R.drawable.ic_local_activity_white_24dp));
        mNavItems.add(new NavItem("Soporte", "Hable con Nosotros", R.drawable.ic_chat_white_24dp));
        mNavItems.add(new NavItem("Cerrar Sesión", "Salir de tu Cuenta", R.drawable.logout));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        mDrawerList = (ListView) findViewById(R.id.navList);
     //   toolbarLayout = findViewById(R.id.locationContainer);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragTagCurrent = null;
                switch (position) {
                    case 0:
                        fragTagCurrent = FRAG_TAG_HOME;
                        break;
                    case 1:
                        fragTagCurrent = FRAG_TAG_ORDERS;
                        break;
                    case 4:
                        fragTagCurrent = FRAG_TAG_DETAILS;
                        break;
                    case 6:
                        fragTagCurrent = FRAG_TAG_FAVORITE;
                        break;
                    case 5:
                        fragTagCurrent = FRAG_TAG_REVIEWS;
                        break;
                    case 2:
                        whatsapp();
                        break;
                    case 3:
                        tokenManager.deleteToken();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, SplashActivity.class));
                        finish();

                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
                if (fragTagCurrent != null) {
                    loadFragment(fragTagCurrent);
                    fragTagCurrent = null;
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

     /*   toolbarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, LocationActivity.class), REQUEST_CODE_LOCATION);
            }
        });*/

        loadFragment(FRAG_TAG_HOME);

    }

    private void loadFragment(final String fragTag) {
        Fragment fragment = null;
//        toolbarLayout.setVisibility(View.GONE);
        switch (fragTag) {
            case FRAG_TAG_HOME:
              //  content_logo.setVisibility(View.VISIBLE);
                fragment = new HomeFragment();
                break;
            case FRAG_TAG_FAVORITE:
             //   content_logo.setVisibility(View.GONE);
                fragment = new FavoriteFragment();
                break;
            case FRAG_TAG_REVIEWS:
             //   content_logo.setVisibility(View.GONE);
                fragment = new MyReviewsFragment();
                break;
            case FRAG_TAG_DETAILS:
            //    content_logo.setVisibility(View.GONE);
                fragment = new DetailsFragment();
                break;
            case FRAG_TAG_ORDERS:
             //   content_logo.setVisibility(View.GONE);
                fragment = new OrdersFragment();
                break;
          /*  case FRAG_TAG_SUPPORT:
                content_logo.setVisibility(View.GONE);

              //  fragment = new SupportFragment();
                break;*/
            case FRAG_TAG_ORDER:
            //    content_logo.setVisibility(View.GONE);
                fragment = new OrderDetailFragment();
                break;
        }
        getSupportActionBar().setTitle(fragTag);
        getSupportActionBar().setDisplayShowTitleEnabled(!fragTag.equals(FRAG_TAG_HOME));
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
                fragmentTransaction.replace(R.id.mainFrame, finalFragment, fragTag);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mHandler == null)
            mHandler = new Handler();
        if (getSupportFragmentManager().findFragmentByTag(fragTag) == null)
            mHandler.post(mPendingRunnable);

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().findFragmentByTag(FRAG_TAG_HOME) == null)
            loadFragment(FRAG_TAG_HOME);
        else
            super.onBackPressed();
    }
    @SuppressLint("NewApi")
    public void whatsapp() {
        String formattedNumber = "573195622289";
        try{
            Intent sendIntent =new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT,"");
            sendIntent.putExtra("jid", formattedNumber +"@s.whatsapp.net");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        }
        catch(Exception e)
        {
            Log.d("ErrorWhatsapp",""+e.toString());
            Toast.makeText(getApplication(),"Error/n"+ e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

}
