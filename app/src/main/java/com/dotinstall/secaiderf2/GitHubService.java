package com.dotinstall.secaiderf2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gosho on 2017/10/20.
 */

public interface GitHubService {

    @GET("/api/userwalkdata/")
    Call<BiposiWalk> getBiposiWalk(@Query("user_id") User user);

    }
