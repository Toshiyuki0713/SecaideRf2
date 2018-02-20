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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

public class Koredake extends AppCompatActivity {

    private String message;
    private ListView KoredakeView;
    private KoreHealthBack koreHealthBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.koredake_activity);

        koreHealthBack = (KoreHealthBack) this.findViewById(R.id.back);

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

        ImageView imagePush = findViewById(R.id.push);
        imagePush.setImageResource(R.drawable.touch3);


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

                List<KoredakeTaiso> koredake = response.body();
                Log.e("response", koredake.toString());


                ArrayAdapter<String> adapter = new ArrayAdapter<>(Koredake.this, R.layout.biposi_list, R.id.biposiRow_text);
                //Log.e("ArrayAdapter", adapter.toString());

                ArrayList<String> dateList = new ArrayList<>();
                ArrayList<String> dateList2 = new ArrayList<>();
                KoredakeFormated koreF = new KoredakeFormated();


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'年'MM'月'dd'日'", Locale.ENGLISH);


                Date date = new Date();


                for (KoredakeTaiso k : koredake) {

                try {
                    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    //Log.e("SDF", sdf.toString());

                    //Date date = sdf.parse(k.getExerciseDate().toString());
                    date = sdf.parse(k.getExerciseDate().toString());
                    System.out.println(date.toString());
                    Log.e("Parseできてる？？", date.toString());

                    /*
                    Arrays.sort(koredake, new Comparator<KoredakeTaiso>() {
                        public int compare(KoredakeTaiso date1, KoredakeTaiso date2) {
                            return date1.date - date2.getExerciseDate();
                        }
                    });
                    */

                    String str = new SimpleDateFormat("yyyy'年'MM'月'dd'日'").format(date);
                    System.out.println("String型 = " + str);
                    Log.e("変更できてるー？？", str.toString());


                    dateList.add(str);
                    Log.e("KoredakeDateListできてる？？", dateList.toString());

                    /*
                    StringBuilder builder = new StringBuilder();
                    builder.append(str);
                    adapter.add(builder.toString());
                    */

                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("ParseExceptionKoredake", e.toString());
                }
                }

                Collections.sort(dateList, Collections.reverseOrder());
                Log.e("Collections koredake", dateList.toString());


                for(String s : dateList) {

                    try{
                    date = sdf2.parse(s);
                    System.out.println(date.toString());
                    Log.e("Parseその２", date.toString());

                    String str2 = new SimpleDateFormat("yyyy'年'MM'月'd'日'").format(date);
                    Log.e("変更その２", str2.toString());

                    //koreF.setKoreFmt(str2);
                    //Log.e("KoredakeF？？", koreF.toString());

                    dateList2.add(str2);

                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.e("ParseExceptionKoredake2", e.toString());
                    }
                }

                koreF.setKoreFmt(dateList2);
                Log.e("KoredakeF？？", koreF.toString());



                for (String s : dateList2) {
                    String string = s;
                    Log.e("string", string);
                    adapter.add(string);
                    //Log.e("Adapterできてるーーー？？", adapter.toString());
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
        //String str = message.toString();
        //intent.putExtra("message", str);
        startActivity(intent);
    }

    public void health_data (View view) {
        Intent intent = new Intent(this, HealthCareData.class);
        //String str = message.toString();
        //intent.putExtra("message", str);
        startActivity(intent);
    }
}
