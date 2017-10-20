package com.dotinstall.secaiderf2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gosho on 2017/10/20.
 */

public interface GitHubService {
    //APIを取得する場所はここで合ってますか？
    @GET("https://travossbot.herokuapp.com/api/userwalkdata/")
    Call<BiposiWalk> getBiposiWalk(
            //ここら辺がサンプルによって結構違うので分かりにくいです
            @Query("key") String key,
            @Query("large_area") String area,
            @Query("format") String format);

}
