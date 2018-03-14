package com.dotinstall.secaiderf2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.media.audiofx.AudioEffect.SUCCESS;
import static okhttp3.internal.http2.ErrorCode.CANCEL;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> dateList2 = new ArrayList<>();

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        ImageView imageView = findViewById(R.id.top);
        imageView.setImageResource(R.drawable.title);

        final TextView loginButton = (TextView) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try {
                    // App to App Login
                    Intent LoginIntent = LineLoginApi.getLoginIntent(v.getContext(), Constants.CHANNEL_ID);
                    startActivityForResult(LoginIntent, REQUEST_CODE);

                } catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE) {
            Log.e("ERROR", "Unsupported Request");
            return;
        }

        LineLoginResult result = LineLoginApi.getLoginResultFromIntent(data);

        switch (result.getResponseCode()) {

            case SUCCESS:
                // Login successful

                SharedPreferences lineData = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = lineData.edit();
                editor.putString("user_id", result.getLineProfile().getUserId());
                editor.apply();
                Log.e("SharedOK", editor.toString());



                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://travossbot.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Koredakeinterface koreservice = retrofit.create(Koredakeinterface.class);


                Call<List<KoredakeTaiso>> call = koreservice.getKoredakeTaiso(lineData.getString("user_id", "No Data"));
                Log.e("service", koreservice.toString());
                Log.e("lineData", lineData.toString());

                call.enqueue(new Callback<List<KoredakeTaiso>>() {
                    @Override
                    public void onResponse(Call<List<KoredakeTaiso>> call, Response<List<KoredakeTaiso>> response) {
                        Log.e("onResponse", response.toString());
                        Log.e("onResponse", call.toString());

                        List<KoredakeTaiso> koredake = response.body();
                        Log.e("response", koredake.toString());

                        ArrayList<String> dateList = new ArrayList<>();
                        //ArrayList<String> dateList2 = new ArrayList<>();
                        //KoredakeFormated koreF = new KoredakeFormated();


                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'年'MM'月'dd'日'", Locale.ENGLISH);


                        Date date = new Date();


                        for (KoredakeTaiso k : koredake) {

                            try {
                                date = sdf.parse(k.getExerciseDate().toString());
                                System.out.println(date.toString());
                                Log.e("Parseできてる？？", date.toString());


                                String str = new SimpleDateFormat("yyyy'年'MM'月'dd'日'").format(date);
                                //Log.e("変更できてるー？？", str.toString());


                                dateList.add(str);
                                //Log.e("KoredakeDateListできてる？？", dateList.toString());

                            } catch (ParseException e) {
                                e.printStackTrace();
                                //Log.e("ParseExceptionKoredake", e.toString());
                            }
                        }

                        Collections.sort(dateList, Collections.reverseOrder());
                        Log.e("Collections koredake", dateList.toString());


                        for(String s : dateList) {

                            try{
                                date = sdf2.parse(s);
                                System.out.println(date.toString());
                                Log.e("Parseその２", date.toString());

                                String str2 = new SimpleDateFormat("yyyy'年'M'月'd'日'").format(date);
                                //Log.e("変更その２", str2.toString());

                                dateList2.add(str2);
                                Log.e("KoredakeDateList2？？", dateList2.toString());


                            } catch (ParseException e) {
                                e.printStackTrace();
                                Log.e("ParseExceptionKoredake2", e.toString());
                            }
                        }

                        //koreF.setKoreFmt(dateList2);
                        //Log.e("KoredakeF？？", koreF.toString());
                    }


                    @Override
                    public void onFailure(Call<List<KoredakeTaiso>> call, Throwable t) {
                        Log.e("onFailure", call.toString());
                        Log.e("onFailure", t.toString());
                    }
                });



                Intent transitionIntent = new Intent(this, PostLoginActivity.class);
                //transitionIntent.putExtra("display_name", result.getLineProfile().getDisplayName());
                transitionIntent.putExtra("line_profile", result.getLineProfile());
                //transitionIntent.putExtra("line_credential", result.getLineCredential());
                //transitionIntent.putExtra("user_id", result.getLineProfile().getUserId());
                startActivity(transitionIntent);
                break;

            case CANCEL:
                // Login canceled by user

                Log.e("ERROR", "LINE Login Canceled by user!!");
                break;

            default:
                Log.e("ERROR", "Login FAILED!");
                Log.e("ERROR", result.getErrorData().toString());
        }
    }




}
