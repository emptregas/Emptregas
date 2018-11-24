package com.emptregas.emptregas.checkout;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.emptregas.emptregas.R;


public class CheckoutAddressFragment extends Fragment {
    private static final String TAG = "address";
    private TextInputLayout lytNomRec,lytNomEnt,lytDirRec, lytDirEnt;
    private EditText edtNomRec, edtNomEnt,edtDirEnt,edtTelRec,edtTelEnt,edtNotEnt,edtNotRec;
private TextView btnNext, distancia,precio,edtDirRec;
    CheckoutActivity activity;


    public CheckoutAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout_address, container, false);

        activity = (CheckoutActivity)getActivity();

            edtNomRec = (EditText) view.findViewById(R.id.edtNomRec);
            edtDirRec = (TextView) view.findViewById(R.id.edtDirRec);
            edtTelRec = (EditText) view.findViewById(R.id.edtTelRec);
            edtNotRec = (EditText) view.findViewById(R.id.edtNotRec);

            // TextView
            distancia = (TextView) view.findViewById(R.id.txtDistancia);
            precio = (TextView) view.findViewById(R.id.txtPrecio);


            //Seteamos

            edtDirRec.setText(activity.getDirRec());
//            edtDirEnt.setText(activity.getDirEnt());
            distancia.setText(activity.getDistancia());
            precio.setText("$ "+Integer.toString(activity.getPrecio()));


            btnNext = (TextView) view.findViewById(R.id.confInstrucciones);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (edtNomRec.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(),"Por favor Ingrese el Nombre del Lugar o Contacto al Recoger",Toast.LENGTH_LONG).show();
                        edtNomRec.requestFocus();
                    }
            /*        if (edtNomEnt.getText().toString().isEmpty()) {
                        edtNomEnt.requestFocus();
                        Toast.makeText(getContext(),"Por favor Ingrese el Nombre del Lugar o Contacto al Entregar",Toast.LENGTH_LONG).show();

                    }*/

                    if (!edtNomRec.getText().toString().isEmpty()/* && !edtNomEnt.getText().toString().isEmpty()*/){
                        guardarBorradorOrder();
                        activity.changeFragment("Instrucciones2");
                    }
                }

            });
        return view;
    }
    private void guardarBorradorOrder(){
        SharedPreferences preferences = getActivity().getSharedPreferences("borradorOrder",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("nomRec",edtNomRec.getText().toString());
        editor.putString("telRec",edtTelRec.getText().toString());
        editor.putString("dirRec",edtDirRec.getText().toString());
        editor.putString("notRec",edtNotRec.getText().toString());
//        editor.putString("nomEnt",edtNomEnt.getText().toString());
 //       editor.putString("telEnt",edtTelEnt.getText().toString());
 //       editor.putString("dirEnt",edtDirEnt.getText().toString());
 //       editor.putString("notEnt",edtNotEnt.getText().toString());
        editor.putInt("precio",activity.getPrecio());
        editor.putString("distancia",distancia.getText().toString());
        editor.putString("adressRec",activity.getAdressRec());
        editor.putString("adressEnt",activity.getAdressEnt());

        editor.commit();

    }


}
