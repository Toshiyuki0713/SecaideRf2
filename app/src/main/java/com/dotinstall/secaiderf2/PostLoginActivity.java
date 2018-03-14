package com.dotinstall.secaiderf2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.linecorp.linesdk.api.LineApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class PostLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private CalendarBack backCanvas3;
    private LineApiClient lineApiClient;

    private TextView nextMonthButton = null;				//次の月へボタン
    private TextView previousMonthButton = null;			//前の月へボタン
    private TextView headerMonthText = null;			//年月表示テキストビュー

    private int currentYear = 0;						//現在表示中の年
    private int currentMonth = 0;						//現在表示中の月

    private int currentDayOfWeek = 0;

    private int nowYear = 0;							//現在の年
    private int nowMonth = 0;							//現在の月
    private int nowDay = 0;								//現在の日


    private int nextMonth = 0;
    private int previousMonth = 0;

    private int gessyo;

    //日表示テキスト情報リスト
    private ArrayList<DayTextViewInfo> dayTextList = new ArrayList<DayTextViewInfo>();
    private ArrayList<CalendarList> calList = new ArrayList<CalendarList>();

    ArrayList<String> dateList2 = new ArrayList<>();
    ArrayList<String> eq = new ArrayList<>();

    ArrayList<String> calen = new ArrayList<>();

    KoredakeFormated koreF = new KoredakeFormated();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        backCanvas3 = (CalendarBack) this.findViewById(R.id.back3);
        Log.e("backCanvas3", backCanvas3.toString());


        ImageView imageView = findViewById(R.id.sec_cal);
        imageView.setImageResource(R.drawable.sec_calendar);

        ImageView imageView2 = findViewById(R.id.koredeki_undo);
        imageView2.setImageResource(R.drawable.koredeki);

        ImageView imageBi = findViewById(R.id.biIcon);
        imageBi.setImageResource(R.drawable.bi);

        ImageView imageSan = findViewById(R.id.sanIcon);
        imageSan.setImageResource(R.drawable.san);

        ImageView imageH = findViewById(R.id.hIcon);
        imageH.setImageResource(R.drawable.h);


        // 現在日時の取得
        Date now = new Date(System.currentTimeMillis());
        // 日時のフォーマットオブジェクト作成
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年M月d日");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT")); //タイムゾーン変更？
        Log.e("formatter", formatter.toString());

        // フォーマット
        String nowText = formatter.format(now);
        // 表示
        TextView textView = (TextView) findViewById(R.id.todayTextView);
        textView.setText(nowText);



        User user;
        user = new User();

        SharedPreferences lineData = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        String level = lineData.getString("user_id", "No Data");
        Log.e("SharedUser", level);


        user.lineUserId = level;
        Log.e("StoreOk", user.lineUserId);


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




        initializeControl();
    }

    /**
     * 各コントロール初期化
     */

    private void initializeControl() {

        this.nextMonthButton = (TextView) findViewById(R.id.moveMonthTextView);
        this.nextMonthButton.setOnClickListener(this);
        this.previousMonthButton = (TextView) findViewById(R.id.backMonthTextView);
        this.previousMonthButton.setOnClickListener(this);

        this.headerMonthText = (TextView)findViewById(R.id.todayTextView);

        DayTextViewInfo info = null;
        CalendarList list = null;


        info = new DayTextViewInfo(R.id.one_su_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.one_mo_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.one_tu_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.one_we_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.one_th_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.one_fr_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.one_sa_text);
        this.dayTextList.add(info);

        info = new DayTextViewInfo(R.id.two_su_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.two_mo_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.two_tu_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.two_we_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.two_th_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.two_fr_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.two_sa_text);
        this.dayTextList.add(info);

        info = new DayTextViewInfo(R.id.three_su_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.three_mo_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.three_tu_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.three_we_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.three_th_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.three_fr_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.three_sa_text);
        this.dayTextList.add(info);

        info = new DayTextViewInfo(R.id.four_su_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.four_mo_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.four_tu_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.four_we_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.four_th_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.four_fr_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.four_sa_text);
        this.dayTextList.add(info);

        info = new DayTextViewInfo(R.id.five_su_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.five_mo_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.five_tu_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.five_we_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.five_th_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.five_fr_text);
        this.dayTextList.add(info);
        info = new DayTextViewInfo(R.id.five_sa_text);
        this.dayTextList.add(info);


        java.util.Calendar cal1 = java.util.Calendar.getInstance();            //(1)オブジェクトの生成

        this.currentYear = cal1.get(java.util.Calendar.YEAR);        //(2)現在の年を取得
        Log.e("currentYear","現在年は「" + currentYear + "」");

        this.currentMonth = cal1.get(java.util.Calendar.MONTH) + 1;  //(3)現在の月を取得
        Log.e("currentMonth","現在月は「" + currentMonth + "」");

        this.nowYear = this.currentYear;
        this.nowMonth = this.currentMonth;
        this.nowDay = cal1.get(java.util.Calendar.DATE);                //(4)現在の日を取得
        Log.e("nowDay","現在日は「" + nowDay + "」");

        this.nextMonth = currentMonth + 1;
        this.previousMonth = currentMonth -1;


        int id = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                TextView tv = (TextView) findViewById(this.dayTextList.get(id).getTextViewId());
                //Log.e("tv","tv「" + tv + "」");
                tv.setOnClickListener(this);

                //tv.setBackgroundResource(R.drawable.text_day_line);
                /*
                if (j == 0) {
                    //日曜日
                    tv.setTextColor(Color.RED);
                }
                if (j == 6) {
                    //土曜日
                    tv.setTextColor(Color.BLUE);
                }
                */
                this.dayTextList.get(id).setTextObject(tv);
                Log.e("dayTextList","dayTextList「" + dayTextList + "」");
                id++;


            }
        }
        this.SetCalendar(0);
    }



    /**
     * カレンダー再描画
     */
    private void SetCalendar(int offset) {

        //ArrayList<String> calen = new ArrayList<>();

        this.currentMonth += offset;
        this.nextMonth += offset;
        this.previousMonth += offset;

        if(currentMonth > 12){
            this.currentYear += 1;
            this.currentMonth = 1;
        }
        else if(currentMonth == 0){
            this.currentMonth = 12;
            this.currentYear -= 1;
        }

        if(nextMonth > 12){
            this.nextMonth = 1;
        }
        else if(nextMonth == 0){
            this.nextMonth = 12;
        }

        if(previousMonth > 12){
            this.previousMonth = 1;
        }
        else if(previousMonth == 0){
            this.previousMonth = 12;
        }


        //テキスト表示情報初期化
        for(int i = 0 ; i < this.dayTextList.size(); i++) {
            DayTextViewInfo tg = this.dayTextList.get(i);
            if(tg.isNowDay() || tg.isSelected() ) {
                //tg.getTextObject().setBackgroundResource(R.drawable.text_day_line);
            }

            tg.setNowDay(false);
            tg.setDayNum(0);
            tg.setSelected(false);
            tg.getTextObject().setText(tg.getDispString());
            //Log.e("tg", tg.toString());

        }


        /*
        try{

            Thread.sleep(10000); //3000ミリ秒Sleepする

        }catch(InterruptedException e){}
        */


        //カレンダーテーブル作成
        CalendarInfo cl = new CalendarInfo(currentYear, currentMonth);

        int row = 0;
        int col = 0;
        for(int i = 0 ; i < this.dayTextList.size(); i++) {
            DayTextViewInfo tg = this.dayTextList.get(i);


            if(cl.calendarMatrix[row][col] != 0) {

                java.util.Calendar cal2 = java.util.Calendar.getInstance();

                cal2.set(currentYear, currentMonth - 1, 1); // 引数: 1月: 0, 2月: 1, ...
                this.gessyo = cal2.get(java.util.Calendar.DAY_OF_WEEK);//曜日を取得
                //Log.e("gessyo","gessyo is「" + gessyo + "」");


                int d = i - gessyo +2;

                String str = currentYear + "年" + currentMonth + "月" + d + "日";


                calen.add(str);
                //Log.e("calen","calenは「" + calen + "」");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'd'日'", Locale.ENGLISH);

                Date date = new Date();

                try {
                    date = sdf.parse(str);
                    System.out.println(date.toString());
                    //Log.e("Parse-------!!!", date.toString());

                    String string = new SimpleDateFormat("d").format(date);
                    //Log.e("変更できてる-------!!!", string.toString());

                    // 日付表示
                    tg.setDayNum(cl.calendarMatrix[row][col]);
                    //tg.getTextObject().setText(tg.getDispString());
                    tg.getTextObject().setText(string + "\n");

                    //tg.getTextObject().append("・");

                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("ParseException!!!", e.toString());
                }




                //(ユーザーの日付配列).indexOf(セルに表示する日付)

                    dateList2.indexOf(str);
                    Log.e("indexOf","indexOfは「" + dateList2.indexOf(str) + "」");
                    Log.e("str","その時のstrは「" + str + "」");
                    Log.e("KoreKore","KoreKoreは「" + dateList2 + "」");




                if(this.nowYear == this.currentYear
                        && this.nowMonth == this.currentMonth
                        && cl.calendarMatrix[row][col] == nowDay) {

                    // 当日日付表示
                    //this.dayTextList.get(i).setNowDay(true);
                    //tg.getTextObject().setBackgroundResource(R.drawable.text_now_line);
                }
            }

            col += 1;
            if(col == 7){
                row += 1;
                col = 0;
            }
        }

        //年月表示

        //this.headerMonthText.setText(String.valueOf(this.currentYear) + "年" + String.valueOf(this.currentMonth) + "月" );
        this.nextMonthButton.setText(String.valueOf(this.nextMonth) + "月" );
        Log.e("nextMonth","次月は「" + nextMonth + "」");
        this.previousMonthButton.setText(String.valueOf(this.previousMonth) + "月" );
        Log.e("nextMonth","前月は「" + previousMonth + "」");


    }

    public void onClick(View v) {
        // TODO 自動生成されたメソッド・スタブ
        if(v.getId() == R.id.moveMonthTextView) {
            this.SetCalendar(+1);

            //this.nextMonthButton.setText(String.valueOf(this.nextMonth ++) + "月");
            //Log.e("nextMonth","加算した次の月は「" + nextMonth + "」");
            /*
            this.nextMonthButton.setText(String.valueOf(this.previousMonth ++) + "月");
            Log.e("nextMonth","加算した前の月は「" + previousMonth + "」");
            */
        }
        else if(v.getId() == R.id.backMonthTextView) {
            this.SetCalendar(-1);
            /*
            this.nextMonthButton.setText(String.valueOf(this.nextMonth --) + "月");
            Log.e("nextMonth","減算した次の月は「" + nextMonth + "」");
            this.nextMonthButton.setText(String.valueOf(this.previousMonth --) + "月");
            Log.e("nextMonth","減算した前の月は「" + previousMonth + "」");
            */
        }
        else
        {
            for(int i = 0 ; i < this.dayTextList.size(); i++) {
                if(this.dayTextList.get(i).getTextViewId() == v.getId()) {
                    //this.dayTextList.get(i).getTextObject().setBackgroundResource(R.drawable.text_selected_line);
                    this.dayTextList.get(i).setSelected(true);
                }
                else {
                    if(this.dayTextList.get(i).isNowDay() == true) {
                        //this.dayTextList.get(i).getTextObject().setBackgroundResource(R.drawable.text_now_line);
                        this.dayTextList.get(i).setSelected(false);
                    }
                    else if(this.dayTextList.get(i).isSelected()) {
                        //this.dayTextList.get(i).getTextObject().setBackgroundResource(R.drawable.text_day_line);
                        this.dayTextList.get(i).setSelected(false);
                    }
                }
            }
        }

    }

    public void koredake_view(View view) {
        Intent intent = new Intent(this, Koredake.class);
        //String str = profileText.getText().toString();
        //intent.putExtra("message", str);
        startActivity(intent);
        //Log.e("start", intent.toString());
    }

    public void main_view (View view) {
        Intent intent = new Intent(this, Biposi.class);
        //String str = message.toString();
        //intent.putExtra("message", str);
        startActivity(intent);
    }

    public void health_data(View view) {
        Intent intent = new Intent(this, HealthCareData.class);
        //String str = profileText.getText().toString();
        //intent.putExtra("message", str);
        startActivity(intent);
    }
}