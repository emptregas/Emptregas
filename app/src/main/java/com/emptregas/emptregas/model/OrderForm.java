package com.emptregas.emptregas.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderForm implements Parcelable {
    private  String distancia;
    private String origen;
    private String Destino ;
    private  int precio;

    public OrderForm() {

    }

 /*   public OrderForm(String origen, String destino, String distancia, int precio) {
        this.distancia = distancia;
        this.origen = origen;
        Destino = destino;
        this.precio = precio;
    }*/

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return Destino;
    }

    public String getDistancia() {
        return distancia;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public OrderForm(Parcel in) {
        origen = in.readString();
        Destino = in.readString();
        distancia = in.readString();
        precio = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(origen);
        dest.writeString(Destino);
        dest.writeString(distancia);
        dest.writeInt(precio);
    }

    @SuppressWarnings("unused")
    public static final Creator<OrderForm> CREATOR = new Creator<OrderForm>() {
        @Override
        public OrderForm createFromParcel(Parcel in) {
            return new OrderForm(in);
        }

        @Override
        public OrderForm[] newArray(int size) {
            return new OrderForm[size];
        }
    };
}