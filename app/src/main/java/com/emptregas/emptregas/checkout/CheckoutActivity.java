package com.emptregas.emptregas.checkout;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emptregas.emptregas.R;
import com.emptregas.emptregas.fragment.OrdersFragment;
import com.emptregas.emptregas.interfaces.Comunicador;
import com.emptregas.emptregas.model.OrderForm;

public class CheckoutActivity extends AppCompatActivity {

    private final String FRAG_TAG_ADDRESS = "Instrucciones";
    private final String FRAG_TAG_ADDRESS_DOWN = "Instrucciones2";
    private final String FRAG_TAG_PAYMENT_MODE = "Metodo de Pago";
    private final String FRAG_TAG_CONFIRM = "Confirmar Orden";
    private final String FRAG_TAG_ORDERS = "Mis Ordenes";
    private Handler mHandler;
    String prueba;
    Bundle bundle;

    private TextView checkoutActionText, checkoutStageHeading1, checkoutStageHeading2, checkoutStageHeading3,checkoutStageHeading4;
    private int checkoutStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

       bundle =  getIntent().getExtras();

        initUi();

        loadFragment(FRAG_TAG_ADDRESS);

    }
    public void changeFragment(String nameFragment){
        loadFragment(nameFragment);
    }
    public String getDirRec(){
        String dirRec = bundle.getString("bundleOrigen");
        return dirRec;
    }
    public String getDirEnt(){
        String dirEnt = bundle.getString("bundleDestino");
        return dirEnt;
    }
    public String getDistancia(){
        String distancia = bundle.getString("bundleDistancia");
        return distancia;
    }public Integer getPrecio(){
        Integer precio = bundle.getInt("bundlePrecio");
        return precio;
    }
    public String getAdressRec(){
        String adressRec = bundle.getString("adressRec");
        return adressRec;
    }
    public String getAdressEnt(){
        String adressEnt = bundle.getString("adressEnt");
        return adressEnt;
    }



    private void initUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        checkoutStageHeading1 = findViewById(R.id.checkoutStageHeading1);
        checkoutStageHeading2 = findViewById(R.id.checkoutStageHeading2);
        checkoutStageHeading3 = findViewById(R.id.checkoutStageHeading3);
        checkoutStageHeading3 = findViewById(R.id.checkoutStageHeading4);

      /*  findViewById(R.id.checkoutAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (checkoutStage) {
                    case 1:
                        loadFragment(FRAG_TAG_PAYMENT_MODE);
                        break;
                    case 2:
                        loadFragment(FRAG_TAG_CONFIRM);
                        break;
                    case 3:
                        break;
                }
            }
        });*/
    }

    private void loadFragment(final String fragTag) {
        Fragment fragment = null;
        switch (fragTag) {
            case FRAG_TAG_ADDRESS:
                checkoutStage = 1;
                fragment = new CheckoutAddressFragment();
                break;
            case FRAG_TAG_PAYMENT_MODE:
                checkoutStage = 3;
                fragment = new CheckoutPaymentModeFragment();
                break;
            case FRAG_TAG_CONFIRM:
                checkoutStage = 5;
                fragment = new CheckoutConfirmFragment();
                break;
            case FRAG_TAG_ORDERS:
                checkoutStage = 4;
                fragment = new OrdersFragment();
                break;
            case FRAG_TAG_ADDRESS_DOWN:
                checkoutStage = 2;
                fragment = new CheckoutAddress2Fragment();
                break;
        }

        setupViews();

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
                fragmentTransaction.add(R.id.checkoutFrame, finalFragment, fragTag);
                fragmentTransaction.addToBackStack(fragTag);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mHandler == null)
            mHandler = new Handler();
        if (getSupportFragmentManager().findFragmentByTag(fragTag) == null)
            mHandler.post(mPendingRunnable);

    }

    private void setupViews() {
        getSupportActionBar().setTitle(checkoutStage == 1 ? FRAG_TAG_ADDRESS : checkoutStage == 2 ? FRAG_TAG_ADDRESS_DOWN :  checkoutStage == 3 ? FRAG_TAG_PAYMENT_MODE : checkoutStage==5 ?FRAG_TAG_CONFIRM : FRAG_TAG_ORDERS);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //creo el Texo y el Titulo
             builder.setMessage("Desea Salir de la  Solicitud");

            // Add the buttons
             builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
             });
             builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
             builder.create().show();

        }

        else {
                checkoutStage = getSupportFragmentManager().getBackStackEntryCount();
                setupViews();
        }
    }


}