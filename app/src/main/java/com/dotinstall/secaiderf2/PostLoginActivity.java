package com.dotinstall.secaiderf2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.api.LineApiClientBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.candidatesArea;
import static android.R.id.list;
import static android.R.id.list_container;
import static java.security.AccessController.getContext;

public class PostLoginActivity extends AppCompatActivity {

    private LineApiClient lineApiClient;
    TextView profileText;

    private ListView KoredakeView;

    //private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.koredake_activity);

        /*
        ImageView imageTouch3 = findViewById(R.id.koredakeActivity);
        imageTouch3.setImageResource(R.drawable.touch3); */

        /*
        ImageView imageBi = findViewById(R.id.biIcon);
        imageBi.setImageResource(R.drawable.bi);
        */



        User user;
        user = new User();

        SharedPreferences lineData = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        String level = lineData.getString("user_id", "No Data");
        Log.e("SharedUser", level);


        user.lineUserId = level;
        Log.e("StoreOk", user.lineUserId);

        KoredakeView = (ListView) findViewById(R.id.koredakeView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://travossbot.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Koredakeinterface koreservice = retrofit.create(Koredakeinterface.class);


        Call<List<KoredakeTaiso>> call = koreservice.getKoredakeTaiso(lineData.getString("user_id", "No Data"));
        Log.e("service", koreservice.toString());
        Log.e("lineData", lineData.toString());

        //Log.e("getBiposi", service.return);


        call.enqueue(new Callback<List<KoredakeTaiso>>() {
            @Override
            public void onResponse(Call<List<KoredakeTaiso>> call, Response<List<KoredakeTaiso>> response) {
                Log.e("onResponse", response.toString());
                Log.e("onResponse", call.toString());
                //new String()はStringを生成するものです
                //((TextView) findViewById(R.id.biposiView)).setText("受け取った文字列"+ response.body());

                List<KoredakeTaiso> koredake = response.body();
                Log.e("response", koredake.toString());


                ArrayAdapter<String> adapter = new ArrayAdapter<>(PostLoginActivity.this, R.layout.biposi_list, R.id.biposiRow_text);
                Log.e("ArrayAdapter", adapter.toString());

                Collections.reverse(koredake);
                Log.e("Collections koredake", koredake.toString());


                /*
                try {
                GsonBuilder gb = new GsonBuilder();
                gb.setDateFormat("yyyy-MM-dd HH:mm:ss Z");
                Gson gson = gb.create();
                return gson;


                Log.e("gson", gson.toString());
                } catch (ParseException e) {
                e.printStackTrace();
                Log.e("ParseException", e.toString());

                }

                for (KoredakeTaiso k : koredake) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(k.getExerciseDate());
                    adapter.add(builder.toString());
                    //System.out.println(Arrays.toString(biposiWalk));
                    Log.e("getKoredatke", k.toString());

                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年M月d日 HH時mm分ss秒");
                    //System.out.println(sdf2.format(b));
                    Log.e("date", sdf2.toString());

                }


                KoredakeView.setAdapter(adapter);
                */


                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
                    Date date = sdf.parse(koredake.toString());
                    System.out.println(date.toString());
                    Log.e("sdf", date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("ParseException", e.toString());

                }

                for (KoredakeTaiso k : koredake) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(k.getExerciseDate());
                    adapter.add(builder.toString());
                    //System.out.println(Arrays.toString(biposiWalk));
                    Log.e("getKoredatke", k.toString());

                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年M月d日 HH時mm分ss秒");
                    //System.out.println(sdf2.format(b));
                    Log.e("date", sdf2.toString());

                }


                KoredakeView.setAdapter(adapter);


            }


            @Override
            public void onFailure(Call<List<KoredakeTaiso>> call, Throwable t) {
                Log.e("onFailure", call.toString());
                Log.e("onFailure", t.toString());
            }
        });




        /*
        LineApiClientBuilder apiClientBuilder = new LineApiClientBuilder(getApplicationContext(), Constants.CHANNEL_ID);
        lineApiClient = apiClientBuilder.build();

        Intent intent = getIntent();
        LineProfile intentProfile = intent.getParcelableExtra("line_profile");
        //LineCredential intentCredential = intent.getParcelableExtra("line_credential");

        profileText = (TextView) findViewById(R.id.profileName);
        profileText.setText(intentProfile.getDisplayName() + "さん");
        */
    }

    public void health_data(View view) {
        Intent intent = new Intent(this, HealthCareData.class);
        String str = profileText.getText().toString();
        intent.putExtra("message", str);
        startActivity(intent);
    }

    public void main_view(View view) {
        Intent intent = new Intent(this, Biposi.class);
        //String str = profileText.getText().toString();
        //intent.putExtra("message", str);
        startActivity(intent);
        Log.e("start", intent.toString());
    }
}