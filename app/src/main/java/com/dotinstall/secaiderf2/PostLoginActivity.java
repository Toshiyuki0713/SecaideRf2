package com.dotinstall.secaiderf2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.api.LineApiClientBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostLoginActivity extends AppCompatActivity {

    private LineApiClient lineApiClient;
    TextView profileText;

    //private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user;
        user = new User();

        SharedPreferences lineData = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        String level = lineData.getString("user_id", "No Data");


        user.lineUserId = level;




        //private ListView mListView;
        /*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main); */
            //mListView = (ListView) findViewById(R.id.userdata);





            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://travossbot.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            GitHubService service = retrofit.create(GitHubService.class);

            Call<BiposiWalk> call = service.getBiposiWalk(user);
            call.enqueue(new Callback<BiposiWalk>() {
                @Override
                public void onResponse(Call<BiposiWalk> call, Response<BiposiWalk> response) {

                    ((TextView) findViewById(R.id.textView3)).setText("受け取った文字列"+ response.body());

                }

                @Override
                public void onFailure(Call<BiposiWalk> call, Throwable t) {
                }
            });



        LineApiClientBuilder apiClientBuilder = new LineApiClientBuilder(getApplicationContext(), Constants.CHANNEL_ID);
        lineApiClient = apiClientBuilder.build();


        Intent intent = getIntent();
        LineProfile intentProfile = intent.getParcelableExtra("line_profile");
        //LineCredential intentCredential = intent.getParcelableExtra("line_credential");



        profileText = (TextView) findViewById(R.id.profileName);
        profileText.setText(intentProfile.getDisplayName() + "さん");



        }
    }



//}
