package com.emptregas.emptregas.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;
import com.emptregas.emptregas.model.Order;

import java.util.List;

/**
 * Created by mardr on 29/06/2018.
 */

public class AccessToken2 {

    @Json(name = "token_type")
    String tokenType;
    @Json(name = "expires_in")
    int expiresIn;
    @Json(name = "access_token")
    String accessToken;
    @Json(name = "refresh_token")
    String refreshToken;
   @SerializedName( "data")
    private List<Order> data;

    public List<Order> getData() {
        return data;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
