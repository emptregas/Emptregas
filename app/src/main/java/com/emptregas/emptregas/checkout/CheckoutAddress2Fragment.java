package com.emptregas.emptregas.checkout;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emptregas.emptregas.R;


public class CheckoutAddress2Fragment extends Fragment {
    private static final String TAG = "address";
    private TextInputLayout lytNomRec;
    private EditText edtNomRec, edtNomEnt,edtDirRec,edtTelRec,edtTelEnt,edtNotEnt;
private TextView btnNext, distancia,precio,edtDirEnt;
    CheckoutActivity activity;


    public CheckoutAddress2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout_address_down, container, false);

        activity = (CheckoutActivity)getActivity();

            edtNomEnt = (EditText) view.findViewById(R.id.edtNomEnt);
            edtDirEnt = (TextView) view.findViewById(R.id.edtDirEnt);
            edtTelEnt = (EditText) view.findViewById(R.id.edtTelEnt);
            edtNotEnt = (EditText) view.findViewById(R.id.edtNotEnt);


            // TextView
            distancia = (TextView) view.findViewById(R.id.txtDistancia);
            precio = (TextView) view.findViewById(R.id.txtPrecio);
            edtDirEnt.setText(activity.getDirEnt());
            distancia.setText(activity.getDistancia());
            precio.setText("$ "+Integer.toString(activity.getPrecio()));


            btnNext = (TextView) view.findViewById(R.id.confInstrucciones);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (edtNomEnt.getText().toString().isEmpty()) {
                        edtNomEnt.requestFocus();
                        Toast.makeText(getContext(),"Por favor Ingrese el Nombre del Lugar o Contacto al Entregar",Toast.LENGTH_LONG).show();

                    }

                    if (/*!edtNomRec.getText().toString().isEmpty() && */!edtNomEnt.getText().toString().isEmpty()){
                        guardarBorradorOrder();
                        activity.changeFragment("Metodo de Pago");
                    }
                }

            });
        return view;
    }
    private void guardarBorradorOrder(){
        SharedPreferences preferences = getActivity().getSharedPreferences("borradorOrder",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("nomEnt",edtNomEnt.getText().toString());
        editor.putString("telEnt",edtTelEnt.getText().toString());
        editor.putString("dirEnt",edtDirEnt.getText().toString());
        editor.putString("notEnt",edtNotEnt.getText().toString());
        editor.putInt("precio",activity.getPrecio());
        editor.putString("distancia",distancia.getText().toString());
        editor.putString("adressRec",activity.getAdressRec());
        editor.putString("adressEnt",activity.getAdressEnt());

        editor.commit();

    }


}
