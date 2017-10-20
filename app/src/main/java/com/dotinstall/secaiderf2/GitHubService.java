package com.dotinstall.secaiderf2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gosho on 2017/10/20.
 */

public interface GitHubService {
    //APIを取得する場所はここで合ってますか？
    // MainActivityで宣言しているので。以下が正しいです。
    @GET("/api/userwalkdata/")
    Call<BiposiWalk> getBiposiWalk(
            //ここら辺がサンプルによって結構違うので分かりにくいです
            @Query("username") String username);

}
