package com.example.nocturnal.placesearch;

import android.content.Intent;

import com.example.nocturnal.Detailes.PlaceDetailes;
import com.example.nocturnal.NearbyPlace.NearbyPlacess;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Nocturnal on 06-May-17.
 */

public interface NearbyPlaceApi{

    @GET
    Call<NearbyPlacess> getNearbyPlaceData(@Url String url);

    @GET
    Call<PlaceDetailes> getPlaceDetaileData(@Url String url);

}
