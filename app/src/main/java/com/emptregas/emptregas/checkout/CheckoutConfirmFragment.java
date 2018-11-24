package com.emptregas.emptregas.checkout;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.emptregas.emptregas.OrderDetailsActivity;
import com.emptregas.emptregas.R;
import com.emptregas.emptregas.TokenManager;
import com.emptregas.emptregas.interfaces.ApiService;
import com.emptregas.emptregas.model.OrderFireball;
import com.emptregas.emptregas.model.Tareas;
import com.emptregas.emptregas.network.RetrofitBuilder;
import com.emptregas.emptregas.responses.OrderResponseOk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutConfirmFragment extends Fragment {
    private static final String TAG = "createOrder";
    private TextView dirRec,dirEnt,precio,distancia, crearOrden,nomRec,nomEnt,telEnt,telRec,notRec,notEnt;
    private String met_pay;
    private String lugar_pago,adressRec,adressEnt,uid;
private SharedPreferences preferences;
    ApiService service;
    TokenManager tokenManager;
    Call<OrderResponseOk> call;

    public CheckoutConfirmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* service = RetrofitBuilder.createService(ApiService.class);
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE));*/
        service = RetrofitBuilder.createService(ApiService.class);
    }

    private void cargarBorradorOrder() {
        preferences = getActivity().getSharedPreferences("borradorOrder", Context.MODE_PRIVATE);
        nomRec.setText(preferences.getString("nomRec","null"));
        dirRec.setText(preferences.getString("dirRec","null"));
        telRec.setText(preferences.getString("telRec","null"));
        notRec.setText(preferences.getString("notRec","null"));
        nomEnt.setText(preferences.getString("nomEnt","null"));
        dirEnt.setText(preferences.getString("dirEnt","null"));
        telEnt.setText(preferences.getString("telEnt","null"));
        notEnt.setText(preferences.getString("notEnt","null"));
        precio.setText(Integer.toString(preferences.getInt("precio",0)));
        distancia.setText(preferences.getString("distancia","null"));
      //  precio.setText(preferences.getString("precio","null"));
        met_pay = preferences.getString("met_pay","null");
        lugar_pago = preferences.getString("lugar_pago","null");
        adressRec = preferences.getString("adressRec","error");
        adressEnt = preferences.getString("adressEnt","error");
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final CheckoutActivity activity = (CheckoutActivity)getActivity();

        View view = inflater.inflate(R.layout.fragment_checkout_confirm, container, false);


        CheckoutAddressFragment fragment = (CheckoutAddressFragment)getActivity().getSupportFragmentManager().findFragmentByTag("address");

        nomRec = (TextView) view.findViewById(R.id.txtNomRec);
        dirRec = (TextView)view.findViewById(R.id.txtDirRec);
        telRec = (TextView)view.findViewById(R.id.txtTelRec);
        notRec = (TextView)view.findViewById(R.id.txtNotRec);

        nomEnt = (TextView)view.findViewById(R.id.txtNomEnt);
        dirEnt = (TextView)view.findViewById(R.id.txtDirEnt);
        telEnt = (TextView)view.findViewById(R.id.txtTelEnt);
        notEnt = (TextView)view.findViewById(R.id.txtNotEnt);
        precio = (TextView)view.findViewById(R.id.txtPrecio);
        distancia = (TextView)view.findViewById(R.id.txtDistancia);
        crearOrden = (TextView)view.findViewById(R.id.txtCrearOrden);




       // nomRec.setText(fragment.getNomRec());
    /*    dirRec.setText(activity.getDirRec());
        dirEnt.setText(activity.getDirEnt());
        precio.setText("$ "+Integer.toString(activity.getPrecio()));
        distancia.setText(activity.getDistancia());*/
        cargarBorradorOrder();



        crearOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                call = service.createOrder(
                        nomRec.getText().toString(),
                        dirRec.getText().toString(),
                        telRec.getText().toString(),
                        notRec.getText().toString(),
                        nomEnt.getText().toString(),
                        dirEnt.getText().toString(),
                        telEnt.getText().toString(),
                        notEnt.getText().toString(),
                        met_pay,
                        lugar_pago,
                        distancia.getText().toString(),
                        precio.getText().toString(),
                        uid,
                        adressRec,
                        adressEnt
                );
                call.enqueue(new Callback<OrderResponseOk>() {
                    @Override
                    public void onResponse(Call<OrderResponseOk> call, Response<OrderResponseOk> response) {

                        Log.w(TAG, "onResponse: " + response + adressRec + adressEnt);

                        if (response.isSuccessful()) {

                            Log.w(TAG, "onResponse: " + response.message() );
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = database.getReference("pedidos");

                            OrderFireball orderFireball = new OrderFireball();
                            orderFireball.setFk_soc(uid);
                            orderFireball.setPrice(precio.getText().toString());
                            orderFireball.setFk_statut(1);
                            orderFireball.setMet_pay(met_pay);
                            orderFireball.setLug_pay(lugar_pago);

                            //   Order orderResponce = (Order) response.body();
                            //       orderFireball.setRowid(orderResponce.getRef());
                            orderFireball.setTotal_ht(precio.getText().toString());
                            Tareas tareas = new Tareas();
                            tareas.setNom_rec(nomRec.getText().toString());
                            tareas.setDirec_rec(dirRec.getText().toString());
                            tareas.setTel_rec(telRec.getText().toString());
                            tareas.setNom_ent(nomEnt.getText().toString());
                            tareas.setDirec_ent(dirEnt.getText().toString());
                            tareas.setTel_ent(telEnt.getText().toString());
                            tareas.setRec_status(6);
                            tareas.setEnt_status(6);
                            orderFireball.setTareas(tareas);
                            orderFireball.setRowid(response.body().getRef());

                            databaseReference.child(uid).child(response.body().getRef()).setValue(orderFireball);
                            databaseReference.child(response.body().getRef()).setValue(orderFireball);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

final String rowID = response.body().getRef();

                            //creo el Texo y el Titulo
                            builder.setMessage("Orden Creada Exitosamente");

                            // Add the buttons
                            builder.setNegativeButton("Mis Ordenes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            builder.setPositiveButton("Ver Detalles", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    preferences.edit().clear();
                                    getActivity().getSupportFragmentManager().getBackStackEntryCount();
                                    Intent intent = new Intent(activity, OrderDetailsActivity.class);
                                    intent.putExtra("rowID",rowID);
                                    activity.startActivity(intent);
                                  //  activity.changeFragment("Mis Ordenes");
                                    activity.finish();

                                }
                            });



                            builder.create().show();

                        } else {
                       //     handleErrors(response.errorBody());
                            Toast.makeText(getContext(),"Error", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<OrderResponseOk> call, Throwable t) {
                        Log.w(TAG, "onFailure: " + t.getMessage());
                    }
                });


            }
        });

        return view;
    }


}
