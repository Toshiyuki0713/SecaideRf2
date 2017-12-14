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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.list;

/**
 * Created by gosho on 2017/10/23.
 */

public class Biposi extends AppCompatActivity {

    private String message;
    private ListView BiposiView;
    private BackOnCanvas2 backCanvas2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biposi_activity);

        backCanvas2 = (BackOnCanvas2) this.findViewById(R.id.back2);


        /*
        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        TextView textView = (TextView) findViewById(R.id.profileName);
        textView.setText(message);


        ImageView imagebipo = findViewById(R.id.biposiActivity);
        imagebipo.setImageResource(R.drawable.biposi_back);

        */

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


                List<BiposiWalk> biposiWalk = response.body();
                Log.e("response", biposiWalk.toString());

                ArrayAdapter<String> adapter = new ArrayAdapter<>(Biposi.this, R.layout.biposi_list, R.id.biposiRow_text);
                Log.e("ArrayAdapter", adapter.toString());

                
                //Collections.sort(biposiWalk, new BiposiComparator());

                for (BiposiWalk obj : biposiWalk) {
                    System.out.println(obj.getStartDate());
                }




                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
                    Date date = sdf.parse(biposiWalk.toString());
                    System.out.println(date.toString());
                    Log.e("sdf", date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("ParseException", e.toString());

                }

                for (BiposiWalk b : biposiWalk) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(b.getStartDate() + b.getSteps() + "歩");
                    adapter.add(builder.toString());
                    //System.out.println(Arrays.toString(biposiWalk));
                    Log.e("getSteps", b.toString());

                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年M月d日 HH時mm分ss秒");
                    //System.out.println(sdf2.format(b));
                    Log.e("date", sdf2.toString());

                }


                BiposiView.setAdapter(adapter);

                Collections.reverse(biposiWalk);
                Log.e("Collections biposi", biposiWalk.toString());

            }


            @Override
            public void onFailure(Call<List<BiposiWalk>> call, Throwable t) {
                Log.e("onFailure", call.toString());
                Log.e("onFailure", t.toString());
            }
        });

    }

    public void health_data(View view) {
        Intent intent = new Intent(this, HealthCareData.class);
        String str = message.toString();
        intent.putExtra("message", str);
        startActivity(intent);
    }

    public void koredake_view(View view) {
        Intent intent = new Intent(this, Koredake.class);
        //String str = message.toString();
        //intent.putExtra("message", str);
        startActivity(intent);
    }
}
