package com.example.inputpointfti.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientService {
    private static final String PUBLIC_API_BASE_URL = "https://api.publicapis.org/";
    private static Retrofit publicApiClient;

    private ApiClientService() {}

    public static Retrofit getPublicApiClient() {
        if (publicApiClient == null) {
            publicApiClient = new Retrofit.Builder()
                .baseUrl(PUBLIC_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }

        return publicApiClient;
    }
}
