package com.emptregas.emptregas;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emptregas.emptregas.model.OrderFireball;
import com.emptregas.emptregas.model.Tareas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderDetailsActivity extends AppCompatActivity {
    private static final String TAG = "OrderDetail";

    private TextView order_id,order_price,dirRec,dirEnt,telRec,nomRec,notRec,telEnt,nomEnt,notEnt;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView state1;
    private ImageView state2;
    private ImageView state3;
    private ImageView state4;
    private ImageView state5;
    private TextView met_pay;
    private TextView lug_pay;
    private Toolbar toolbar;

    String orderID="";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference Order = database.getReference("pedidos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_order_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Detalles de la Orden");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        order_id = (TextView) findViewById(R.id.orderId);
        order_price = (TextView) findViewById(R.id.price);
        dirRec = (TextView) findViewById(R.id.txtDirRec);
        dirEnt = (TextView) findViewById(R.id.txtDirEnt);
        nomRec = (TextView) findViewById(R.id.txtNomRec);
        telRec = (TextView) findViewById(R.id.txtTelRec);
        notRec = (TextView) findViewById(R.id.txtNotRec);
        telEnt = (TextView) findViewById(R.id.txtTelEnt);
        nomEnt = (TextView) findViewById(R.id.txtNomEnt);
        notEnt = (TextView) findViewById(R.id.txtNotEnt);
        state1 = (ImageView) findViewById(R.id.state1);
        state2 = (ImageView) findViewById(R.id.state2);
        state3 = (ImageView) findViewById(R.id.state3);
        state4 = (ImageView) findViewById(R.id.state4);
        state5 = (ImageView) findViewById(R.id.state5);
        met_pay = (TextView) findViewById(R.id.metPago);
        lug_pay = (TextView) findViewById(R.id.lugPago);
        //Get OrderID desde el intent
        if(getIntent() !=null)
            orderID = getIntent().getStringExtra("rowID");
        order_id.setText(orderID);

        if (orderID!=""){
            getDetailOrder(orderID);

        }

    }

    private void getDetailOrder(String orderID) {

        Log.w(TAG, "orderID: " + orderID );
        Order.child(uid).child(orderID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                OrderFireball order = dataSnapshot.getValue(OrderFireball.class);

         //       order_price.setText(String.valueOf(order.getPrice()));
                dirRec.setText(order.getTareas().getDirec_rec());
                dirEnt.setText(order.getTareas().getDirec_ent());
                nomRec.setText(order.getTareas().getNom_rec());
                telRec.setText(order.getTareas().getTel_rec());
             //   notRec.setText(order.getTareas().g());
                nomEnt.setText(order.getTareas().getNom_ent());
                telEnt.setText(order.getTareas().getTel_ent());
                order_price.setText("$ "+order.getPrice());

                if (order.getTareas().getRec_status()==6){
                    state1.setBackgroundResource(R.drawable.round_circle_accent);
                    state2.setBackgroundResource(R.drawable.round_circle_gray);
                    state3.setBackgroundResource(R.drawable.round_circle_gray);
                    state4.setBackgroundResource(R.drawable.round_circle_gray);
                    state5.setBackgroundResource(R.drawable.round_circle_gray);
                }
                if (order.getTareas().getRec_status()==7){
                    state1.setBackgroundResource(R.drawable.round_circle_primary);
                    state2.setBackgroundResource(R.drawable.round_circle_gray);
                    state3.setBackgroundResource(R.drawable.round_circle_gray);
                    state4.setBackgroundResource(R.drawable.round_circle_gray);
                    state5.setBackgroundResource(R.drawable.round_circle_gray);
                }
                if (order.getTareas().getRec_status()==1){
                    state1.setBackgroundResource(R.drawable.round_circle_primary);
                    state2.setBackgroundResource(R.drawable.round_circle_accent);
                    state3.setBackgroundResource(R.drawable.round_circle_gray);
                    state4.setBackgroundResource(R.drawable.round_circle_gray);
                    state5.setBackgroundResource(R.drawable.round_circle_gray);
                }
                if (order.getTareas().getRec_status()==4){
                    state1.setBackgroundResource(R.drawable.round_circle_primary);
                    state2.setBackgroundResource(R.drawable.round_circle_primary);
                    state3.setBackgroundResource(R.drawable.round_circle_accent);
                    state4.setBackgroundResource(R.drawable.round_circle_gray);
                    state5.setBackgroundResource(R.drawable.round_circle_gray);
                }
                if (order.getTareas().getRec_status()==2){
                    state1.setBackgroundResource(R.drawable.round_circle_primary);
                    state2.setBackgroundResource(R.drawable.round_circle_primary);
                    state3.setBackgroundResource(R.drawable.round_circle_primary);
                    state4.setBackgroundResource(R.drawable.round_circle_gray);
                    state5.setBackgroundResource(R.drawable.round_circle_gray);
                }
                if (order.getTareas().getEnt_status()==1){
                    state1.setBackgroundResource(R.drawable.round_circle_primary);
                    state2.setBackgroundResource(R.drawable.round_circle_primary);
                    state3.setBackgroundResource(R.drawable.round_circle_primary);
                    state4.setBackgroundResource(R.drawable.round_circle_accent);
                    state5.setBackgroundResource(R.drawable.round_circle_gray);

                }
                if (order.getTareas().getEnt_status()==4){
                    state1.setBackgroundResource(R.drawable.round_circle_primary);
                    state2.setBackgroundResource(R.drawable.round_circle_primary);
                    state3.setBackgroundResource(R.drawable.round_circle_primary);
                    state4.setBackgroundResource(R.drawable.round_circle_primary);
                    state5.setBackgroundResource(R.drawable.round_circle_accent);
                }
                if (order.getTareas().getEnt_status()==2){
                    state1.setBackgroundResource(R.drawable.round_circle_primary);
                    state2.setBackgroundResource(R.drawable.round_circle_primary);
                    state3.setBackgroundResource(R.drawable.round_circle_primary);
                    state4.setBackgroundResource(R.drawable.round_circle_primary);
                    state5.setBackgroundResource(R.drawable.round_circle_primary);
                }
                met_pay.setText(order.getMet_pay());
                lug_pay.setText(order.getLug_pay());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplication(),"error"+databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}

