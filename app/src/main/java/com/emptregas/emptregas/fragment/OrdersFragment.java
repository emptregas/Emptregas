package com.emptregas.emptregas.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emptregas.emptregas.OrderDetailsActivity;
import com.emptregas.emptregas.R;
import com.emptregas.emptregas.adapter.OrdersAdapter2;
import com.emptregas.emptregas.interfaces.ApiService;
import com.emptregas.emptregas.model.Order;
import com.emptregas.emptregas.model.OrderFireball;
import com.emptregas.emptregas.model.TokenManager;
import com.emptregas.emptregas.network.RetrofitBuilder;
import com.emptregas.emptregas.responses.OrderResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class OrdersFragment extends Fragment {
    private static final String TAG = "OrdersFragment";
    private RecyclerView recyclerOrders;
    private List<OrderFireball> listaOrders;
/*    RecyclerView.Adapter adapter;
    TextView rowid;
    ApiService service;
    TokenManager tokenManager ;*/
    OrdersAdapter2 adapter;

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs", MODE_PRIVATE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        recyclerOrders = view.findViewById(R.id.recyclerOrders);
        listaOrders = new ArrayList<>();
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerOrders.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerOrders.setLayoutManager(mLayoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        adapter = new OrdersAdapter2(listaOrders,getActivity());
        recyclerOrders.setAdapter(adapter);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = database.getReference("pedidos").child(uid).orderByValue();

                query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaOrders.removeAll(listaOrders);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren())
                     {
                         OrderFireball order = snapshot.getValue(OrderFireball.class);
                         listaOrders.add(order);
                }
                adapter.notifyDataSetChanged();
                if (listaOrders==null || listaOrders.size()==0){
                    Toast.makeText(getContext(),"No has creado ningun pedidio", Toast.LENGTH_SHORT).show();
                }



               adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), OrderDetailsActivity.class);
                        TextView textView = (TextView) view.findViewById(R.id.rowid);
                        intent.putExtra("rowID",textView.getText().toString());
                        getActivity().startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


  /*      service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        Call<OrderResponse> orderResponseCall = service.orders();
        orderResponseCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, final Response<OrderResponse> response) {
                Log.w(TAG, "onResponse: " + response );

                if(response.isSuccessful()){

                    OrdersAdapter2 adapter = new OrdersAdapter2(response.body().getData(),getActivity());

                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String rowID = String.format("%07d",Integer.parseInt(response.body().getData().get(recyclerOrders.getChildAdapterPosition(view)).getRef()));
                            Log.w(TAG, "rowID: "+rowID );
                            Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                            intent.putExtra("rowID",rowID);
                            startActivity(intent);
                        }
                    });
                    recyclerOrders.setAdapter(adapter);
                }else {


                    Toast.makeText(getActivity(),"Error!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });*/

    }

    private void setupOrdersRecycler() {
        recyclerOrders.setLayoutManager(new LinearLayoutManager(getContext()));






       // recyclerOrders.setAdapter(new OrdersAdapter2(getContext()));
    }
}
