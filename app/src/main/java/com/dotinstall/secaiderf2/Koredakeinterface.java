package com.dotinstall.secaiderf2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gosho on 2017/11/10.
 */

public interface Koredakeinterface {
    @GET("/api/excercisedata/")
    Call<List<KoredakeTaiso>> getKoredakeTaiso(@Query("user") String userId);
}
