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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gosho on 2017/10/23.
 */

public class Biposi extends AppCompatActivity {

    private String message;
    private ListView BiposiView;
    private BiposiBack biposiBack;
    //private ArrayList<String> DateList;
    private List<DateList> DateList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biposi_activity);

        biposiBack = (BiposiBack) this.findViewById(R.id.back2);

        ImageView imageSecList = findViewById(R.id.secListView);
        imageSecList.setImageResource(R.drawable.sec_list2);

        ImageView imageBi = findViewById(R.id.biIcon);
        imageBi.setImageResource(R.drawable.bi);

        ImageView imageSan = findViewById(R.id.sanIcon);
        imageSan.setImageResource(R.drawable.san);

        ImageView imageH = findViewById(R.id.hIcon);
        imageH.setImageResource(R.drawable.h);

        ImageView imageKore = findViewById(R.id.korenara);
        imageKore.setImageResource(R.drawable.koredeki);

        ImageView imagePush = findViewById(R.id.bipoHaya);
        imagePush.setImageResource(R.drawable.bipohaya);

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

                List<BiposiWalk> biposiWalk = response.body();
                Log.e("listBiposi", biposiWalk.toString());

                ArrayAdapter<String> adapter = new ArrayAdapter<>(Biposi.this, R.layout.biposi_list, R.id.biposiRow_text);
                Log.e("ArrayAdapter", adapter.toString());

                ArrayList<String> dateList = new ArrayList<>();

                //Collections.sort(biposiWalk, new BiposiComparator());

                for (BiposiWalk b : biposiWalk) {

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z", Locale.ENGLISH);
                        //Log.e("SDF", sdf.toString());

                        //Date date = sdf.parse(b.getStartDate().toString());
                        Date date = sdf.parse(b.getStartDate());
                        System.out.println(date.toString());
                        Log.e("Parseできてる？？", date.toString());


                        sdf.applyPattern("yyyy'年'MM'月'dd'日'");
                        System.out.println(sdf.format(date));
                        Log.e("applyPatternできてる？？", date.toString());


                        /*
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
                        System.out.println(sdf2.format(date));
                        Log.e("Changeできてる？？", date.toString());
                        Log.e("sdf2", sdf2.toString());
                        */



                        /*
                        String str = new SimpleDateFormat("yyyy'年'MM'月'dd'日'").format(date);
                        System.out.println("String型 = " + str);
                        Log.e("変更できてるー？？", str.toString());
                        */

                        dateList.add(sdf + " " + b.getSteps());
                        Log.e("BiposiDateListできてる？？", dateList.toString());


                        /*
                        StringBuilder builder = new StringBuilder();
                        builder.append(str + " " + b.getSteps() + "歩");
                        Log.e("StringBuildできてる？？", builder.toString());
                        adapter.add(builder.toString());
                        Log.e("Adapterできてる？？", adapter.toString());
                        //System.out.println(Arrays.toString(biposiWalk));
                        //Log.e("getSteps", b.toString());
                        */



                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.e("ParseExceptionBiposi44", e.toString());
                    }
                }

                Collections.sort(dateList, Collections.reverseOrder());
                Log.e("Collections biposi", dateList.toString());

                for (String s : dateList) {
                    String string = s;
                    Log.e("string", string.toString());
                    adapter.add(string);
                    Log.e("Adapterできてるーーー？？", adapter.toString());
                }

                BiposiView.setAdapter(adapter);


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
        //String str = message.toString();
        //intent.putExtra("message", str);
        startActivity(intent);
    }

    public void koredake_view(View view) {
        Intent intent = new Intent(this, Koredake.class);
        //String str = message.toString();
        //intent.putExtra("message", str);
        startActivity(intent);
    }
}
