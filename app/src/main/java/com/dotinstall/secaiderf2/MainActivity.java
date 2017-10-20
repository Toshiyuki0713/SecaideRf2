package com.dotinstall.secaiderf2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ここら辺の使い方が合っているか分からないです
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://travossbot.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // エラーが出てしまいます
        // そもそも型 変数 = の文法に沿っていないです。Javaの文法も並行して学習してください。
        // ここでのServiceはGitHubServiceのことです。APIなのか、独自に作成されたクラスなのか、最初はAPIを覚えていないため判別するのが困難だとは思いいますが、随時調べ覚えていってください。
        // createはstaticメソッドではないので、Retrofit.createではなく、上で宣言しているretrofitを使う必要があります。
        GitHubService service = retrofit.create(GitHubService.class);

        //ここから下がほとんどエラーの元みたいです。コピペして()内とかを変えただけなので文法ミスはないと思うのですが
        // getBiposiWalk(user);としていますが、userは変数を意味してしまいます。
        Call<BiposiWalk> call = service.getBiposiWalk("user_name");
        call.enqueue(new Callback<BiposiWalk>() {
            @Override
            public void onResponse(Call<BiposiWalk> call, Response<BiposiWalk> response) {
                // new String()はStringを生成するものです
                ((TextView) findViewById(R.id.textview)).setText("受け取った文字列" + response.body());
            }

            @Override
            public void onFailure(Call<BiposiWalk> call, Throwable t) {

            }
        });
    }
}
