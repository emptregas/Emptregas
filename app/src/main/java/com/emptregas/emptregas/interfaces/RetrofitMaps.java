package com.emptregas.emptregas.interfaces;


import com.emptregas.emptregas.responses.DistanceMatrixResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

// http://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC&destinations=New+York+City,NY

public interface RetrofitMaps {
    @GET("maps/api/distancematrix/json")
    Call<DistanceMatrixResponse> getDistanceInfo(
            @QueryMap Map<String, String> parameters
    );
}