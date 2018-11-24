package com.emptregas.emptregas.checkout;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.emptregas.emptregas.R;

public class CheckoutPaymentModeFragment extends Fragment {
    private RadioButton rddatafono, rdefectivo, rdcreditCard,alRecoger,alEntregar;
    private View creditDetail, debitDetail, efectivoDetail;
    private  TextView distancia,precio , btnNext;
    private String met_pay;
  //  private LinearLayout ,creditDetail;

    public CheckoutPaymentModeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkour_payment_mode, container, false);

        final CheckoutActivity activity = (CheckoutActivity)getActivity();

        //Cast Radio Buttons

        rdefectivo = view.findViewById(R.id.efectivo);
        rddatafono = view.findViewById(R.id.datafono);
        rdcreditCard = view.findViewById(R.id.creditCard);
        alRecoger = view.findViewById(R.id.alRecoger);
        alEntregar = view.findViewById(R.id.alEntregar);

        efectivoDetail = view.findViewById(R.id.efectivoDetailContainer);

        creditDetail = view.findViewById(R.id.creditDetailContainer);

        //
        distancia = (TextView) view.findViewById(R.id.txtDistancia);
        precio = (TextView) view.findViewById(R.id.txtPrecio);
        btnNext =(TextView) view.findViewById(R.id.confMetPay);


        distancia.setText(activity.getDistancia());
        precio.setText("$"+Integer.toString(activity.getPrecio()));


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarMetodoPago();
             /*   CheckoutConfirmFragment checkoutConfirmFragment = new CheckoutConfirmFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.checkoutFrame, checkoutConfirmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
             activity.changeFragment("Confirmar Orden");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        met_pay = "Efectivo";
       rddatafono.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rdcreditCard.setChecked(false);
                    rdefectivo.setChecked(false);
                    met_pay = "Efectivo";
                }
            }
        });
        rdcreditCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rddatafono.setChecked(false);
                    rdefectivo.setChecked(false);
                    met_pay = "Tarjeta de Credito";
                }
                creditDetail.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });
        rdefectivo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rdcreditCard.setChecked(false);
                    rddatafono.setChecked(false);
                    met_pay = "Datafono";
                }
                efectivoDetail.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });
    }

    public void guardarMetodoPago(){
        SharedPreferences preferences = getActivity().getSharedPreferences("borradorOrder", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

         editor.putString("met_pay",met_pay);

        if (alRecoger.isChecked()){
            editor.putString("lugar_pago","Al Recoger");
        }else{
            editor.putString("lugar_pago","Al Entregar");
        }
        editor.commit();
    }
}
