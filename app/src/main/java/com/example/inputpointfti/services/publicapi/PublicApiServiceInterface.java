package com.example.inputpointfti.services.publicapi;

import com.example.inputpointfti.models.publicapi.PublicApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PublicApiServiceInterface {
    @GET("entries")
    Call<PublicApiResponse> getAllEntries();

    @GET("random")
    Call<PublicApiResponse> getEntryRandomly();
}
