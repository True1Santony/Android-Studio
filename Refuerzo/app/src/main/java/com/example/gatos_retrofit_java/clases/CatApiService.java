package com.example.gatos_retrofit_java.clases;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CatApiService {
    @GET("cats?min_weight=3")
    Call<List<Cat>> getCats(@Header("X-Api-Key") String apiKey, @Query("min_weight") int minWeight);


}
