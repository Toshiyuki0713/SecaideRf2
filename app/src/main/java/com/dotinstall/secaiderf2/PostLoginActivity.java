package com.dotinstall.secaiderf2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.api.LineApiClientBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

    private ListView BiposiView;

    //private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user;
        user = new User();

        SharedPreferences lineData = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        String level = lineData.getString("user_id", "No Data");
        Log.e("SharedUser", level);


        user.lineUserId = level;
        Log.e("StoreOk", user.lineUserId);

        BiposiView = (ListView) findViewById(R.id.biposiView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://travossbot.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);


        Call<List<BiposiWalk>> call = service.getBiposiWalk(lineData.getString("user_id", "No Data"));
        Log.e("service", service.toString());
        Log.e("lineData", lineData.toString());

        //Log.e("getBiposi", service.return);


        call.enqueue(new Callback<List<BiposiWalk>>() {
            @Override
            public void onResponse(Call<List<BiposiWalk>> call, Response<List<BiposiWalk>> response) {
                Log.e("onResponse", response.toString());
                Log.e("onResponse", call.toString());
                //new String()はStringを生成するものです
                //((TextView) findViewById(R.id.biposiView)).setText("受け取った文字列"+ response.body());

                List<BiposiWalk> biposiWalkList = response.body();
                Log.e("response", biposiWalkList.toString());


                //BiposiView biposiView = new BiposiView();


                ArrayAdapter<String> adapter = new ArrayAdapter<>(PostLoginActivity.this, R.layout.biposi_list, R.id.biposiRow_text);
                Log.e("ArrayAdapter", adapter.toString());


                for (BiposiWalk b : biposiWalkList) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(b.getStartDate() + b.getSteps() + "歩");
                    adapter.add(builder.toString());
                    System.out.println((biposiWalkList));
                    Log.e("startDate", b.toString());

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss");
                        Date date = sdf.parse(b.getStartDate());
                        //System.out.println(date);
                        SimpleDateFormat str = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z");
                        System.out.println("String型 = " + str);
                        Log.e("sdf", date.toString());
                        Log.e("str", date.toString());

                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.e("ParseException", e.toString());

                    }

                    /*
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年M月d日 HH時mm分ss秒 Z");
                    //System.out.println(sdf2.format(b.getStartDate()));
                    Log.e("date", sdf2.toString());

                    try {
                        String strDate = "2017/03/02 01:23:45";
                        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        Date date = sdFormat.parse(strDate);
                        System.out.println("Date型 = " + date);

                        String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
                        System.out.println("String型 = " + str);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    */

                }


                BiposiView.setAdapter(adapter);

            }


            @Override
            public void onFailure(Call<List<BiposiWalk>> call, Throwable t) {
                Log.e("onFailure", call.toString());
                Log.e("onFailure", t.toString());
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

    public void health_data(View view) {
        Intent intent = new Intent(this, HealthCareData.class);
        String str = profileText.getText().toString();
        intent.putExtra("message", str);
        startActivity(intent);
    }

    public void koredake_view(View view) {
        Intent intent = new Intent(this, Koredake.class);
        String str = profileText.getText().toString();
        intent.putExtra("message", str);
        startActivity(intent);
        Log.e("start", intent.toString());
    }
}