package com.emptregas.emptregas.adapter;


import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emptregas.emptregas.R;
import com.emptregas.emptregas.model.OrderFireball;
import com.emptregas.emptregas.model.StatusOrder;

import java.util.List;

public class OrdersAdapter2
        extends RecyclerView.Adapter<OrdersAdapter2.ViewHolderOrder>
        implements View.OnClickListener{

    List<OrderFireball> orders;
    private View.OnClickListener Listener;
    TextView price;
    public OrdersAdapter2(List<OrderFireball> listOrders, FragmentActivity activity) {
        this.orders = listOrders;
    }

    @Override
    public ViewHolderOrder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_list, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
       view.setOnClickListener(this);
        return new ViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderOrder holder, final int position) {
        holder.rowid.setText(String.valueOf(orders.get(position).getRowid()));
        holder.price.setText("$ "+String.valueOf(orders.get(position).getPrice()));
        StatusOrder statusOrder = new StatusOrder(orders.get(position).getFk_statut());
        holder.orderStatus.setText(statusOrder.getNameOrderStatus());
        holder.origen.setText(orders.get(position).getTareas().getDirec_rec());
        holder.destino.setText(orders.get(position).getTareas().getDirec_ent());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.Listener =listener;
    }

    @Override
    public void onClick(View view) {
        if (Listener!=null){
            Listener.onClick(view);
        }
    }


    public class ViewHolderOrder extends RecyclerView.ViewHolder {
        public TextView rowid;
        public TextView price;
        public TextView origen;
        public TextView destino;
        public TextView orderStatus;
        public LinearLayout container;
        public ImageView state1;
        public ImageView state2;
        public ImageView state3;
        public ImageView state4;
        public ImageView state5;

        public ViewHolderOrder(final View itemView) {
            super(itemView);
            rowid = (TextView) itemView.findViewById(R.id.rowid);
            price = (TextView) itemView.findViewById(R.id.price);
            origen = (TextView) itemView.findViewById(R.id.origen);
            destino = (TextView) itemView.findViewById(R.id.destino);
            orderStatus = (TextView) itemView.findViewById(R.id.orderStatus);
            state1 = (ImageView) itemView.findViewById(R.id.state1);
            state2 = (ImageView) itemView.findViewById(R.id.state2);
            state3 = (ImageView) itemView.findViewById(R.id.state3);
            state4 = (ImageView) itemView.findViewById(R.id.state4);
            state5 = (ImageView) itemView.findViewById(R.id.state5);
        }
        }
    }


