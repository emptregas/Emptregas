package com.emptregas.emptregas.entities;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RestGoogleApi {
    private static RestGoogleApi self;
    private String API_BASE_URL = "https://maps.googleapis.com/";
    private Retrofit retrofit;

    private RestGoogleApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(JacksonConverterFactory.create());
        retrofit = builder.client(httpClient).build();
    }

    public static RestGoogleApi getInstance() {
        if (self == null) {
            synchronized(RestGoogleApi.class) {
                if (self == null) {
                    self = new RestGoogleApi();
                }
            }
        }
        return self;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}

