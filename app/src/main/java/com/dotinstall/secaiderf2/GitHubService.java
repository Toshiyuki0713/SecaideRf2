package com.dotinstall.secaiderf2;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gosho on 2017/10/20.
 */

public interface GitHubService {

    @GET("/api/userwalkdata/")
    Call<List<BiposiWalk>> getBiposiWalk(@Query("user") String userId);
}
