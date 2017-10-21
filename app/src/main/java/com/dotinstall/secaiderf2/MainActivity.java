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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://travossbot.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<BiposiWalk> call = service.getBiposiWalk(user_name);


        call.enqueue(new Callback<BiposiWalk>() {
            @Override
            public void onResponse(Call<BiposiWalk> call, Response<BiposiWalk> response) {

                ((TextView) findViewById(R.id.textview)).setText("受け取った文字列" + response.body());

            }

            @Override
            public void onFailure(Call<BiposiWalk> call, Throwable t) {
            }
        });
    }
}
