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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Koredake extends AppCompatActivity {

    private String message;
    private ListView KoredakeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koredake);

        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        TextView textView = (TextView) findViewById(R.id.profileName);
        textView.setText(message);


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


                ArrayAdapter<String> adapter = new ArrayAdapter<>(Koredake.this, R.layout.biposi_list, R.id.biposiRow_text);
                Log.e("ArrayAdapter", adapter.toString());

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


    }

    public void main_view (View view) {
        Intent intent = new Intent(this, Biposi.class);
        String str = message.toString();
        intent.putExtra("message", str);
        startActivity(intent);
    }
}
