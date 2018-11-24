package com.emptregas.emptregas.responses;

import com.emptregas.emptregas.model.Order;

import java.util.List;

/**
 * Created by mardr on 11/07/2018.
 */

public class OrderResponse {


    List<Order> data;
    /*public ArrayList<Order> orders;*/

   public  List<Order> getData() {
        return data;
    }

   /* public void setData(List<Order> data) {
        this.data = data;
    }*/

}
