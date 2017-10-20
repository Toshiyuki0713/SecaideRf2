package com.dotinstall.secaiderf2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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

        //エラーが出てしまいます
        Service = Retrofit.create(GitHubService.class);

        //ここから下がほとんどエラーの元みたいです。コピペして()内とかを変えただけなので文法ミスはないと思うのですが
        Call<BiposiWalk> call = service.getBiposiWalk(user);
        call.enqueue(new Callback<BiposiWalk>() {
            @Override
            public void onResponse(Response<BiposiWalk> response) {

                ((TextView) findViewById(R.id.textview)).setText(new String());

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
