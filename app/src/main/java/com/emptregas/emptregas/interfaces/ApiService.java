package com.emptregas.emptregas.interfaces;


import com.emptregas.emptregas.entities.AccessToken;
import com.emptregas.emptregas.entities.AccessToken2;
import com.emptregas.emptregas.responses.OrderResponse;
import com.emptregas.emptregas.responses.OrderResponseOk;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("register")
    @FormUrlEncoded
    Call<AccessToken> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("uid") String uid);

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("username") String username, @Field("password") String password);

    @POST("social_auth")
    @FormUrlEncoded
    Call<AccessToken> socialAuth(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("provider") String provider,
                                 @Field("provider_user_id") String providerUserId);

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);



    @GET("orders")
    Call<OrderResponse> orders();


    @POST("order/create")
    @FormUrlEncoded
    Call<OrderResponseOk> createOrder(
            @Field("nomRec") String nomRec,
            @Field("dirRec") String dirRec,
            @Field("telRec") String telRec,
            @Field("notRec") String notRec,
            @Field("nomEnt") String nomEnt,
            @Field("dirEnt") String dirEnt,
            @Field("telEnt") String telEnt,
            @Field("notEnt") String notEnt,
            @Field("met_pay") String met_pay,
            @Field("lugar_pay") String lugar_pago,
            @Field("distancia") String distancia,
            @Field("precio") String precio,
            @Field("uid") String uid,
            @Field("adress_rec") String adressRec,
            @Field("adress_ent") String adressEnt
            );

    @POST("order/create")
    @FormUrlEncoded
    Call<AccessToken2> createOrder2(
            @Field("nomRec") String nomRec,
            @Field("dirRec") String dirRec,
            @Field("telRec") String telRec,
            @Field("nomEnt") String nomEnt,
            @Field("dirEnt") String dirEnt,
            @Field("telEnt") String telEnt);
}
