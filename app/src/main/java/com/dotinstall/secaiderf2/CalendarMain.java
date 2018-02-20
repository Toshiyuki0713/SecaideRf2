package com.dotinstall.secaiderf2;

import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import java.util.Calendar;
import android.icu.util.TimeZone;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class CalendarMain extends AppCompatActivity implements View.OnClickListener {

    private TextView nextMonthButton = null;				//次の月へボタン
    private TextView previousMonthButton = null;			//前の月へボタン
    private TextView headerMonthText = null;			//年月表示テキストビュー

    private int currentYear = 0;						//現在表示中の年
    private int currentMonth = 0;						//現在表示中の月

    private int nowYear = 0;							//現在の年
    private int nowMonth = 0;							//現在の月
    private int nowDay = 0;								//現在の日


    private int nextMonth = 0;
    private int previousMonth = 0;

    //private int moveMonth = 0;
    //private int backMonth = 0;

    //日表示テキスト情報リスト
    private ArrayList<DayTextViewInfo> dayTextList = new ArrayList<DayTextViewInfo>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

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

        Calendar cal1 = Calendar.getInstance();            //(1)オブジェクトの生成

        this.currentYear = cal1.get(Calendar.YEAR);        //(2)現在の年を取得
        Log.e("currentYear","現在年は「" + currentYear + "」");

        this.currentMonth = cal1.get(Calendar.MONTH) + 1;  //(3)現在の月を取得
        Log.e("currentMonth","現在月は「" + currentMonth + "」");

        this.nowYear = this.currentYear;
        this.nowMonth = this.currentMonth;
        this.nowDay = cal1.get(Calendar.DATE);                //(4)現在の日を取得
        Log.e("nowDay","現在日は「" + nowDay + "」");

        this.nextMonth = currentMonth + 1;
        this.previousMonth = currentMonth -1;


        int id = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                TextView tv = (TextView) findViewById(this.dayTextList.get(id).getTextViewId());
                tv.setOnClickListener(this);

                //tv.setBackgroundResource(R.drawable.text_day_line);
                if (j == 0) {
                    //日曜日
                    tv.setTextColor(Color.RED);
                }
                if (j == 6) {
                    //土曜日
                    tv.setTextColor(Color.BLUE);
                }
                this.dayTextList.get(id).setTextObject(tv);
                id++;
            }
        }

        this.SetCalendar(0);
    }





    /**
     * カレンダー再描画
     */
    private void SetCalendar(int offset) {
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

        //カレンダーテーブル作成
        CalendarInfo cl = new CalendarInfo(currentYear, currentMonth);

        int row = 0;
        int col = 0;
        for(int i = 0 ; i < this.dayTextList.size(); i++) {
            DayTextViewInfo tg = this.dayTextList.get(i);

            if(cl.calendarMatrix[row][col] != 0) {
                // 日付表示
                tg.setDayNum(cl.calendarMatrix[row][col]);
                //Log.e("setDayNum", cl.toString());
                tg.getTextObject().setText(tg.getDispString());
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

        this.headerMonthText.setText(String.valueOf(this.currentYear) + "年"
                + String.valueOf(this.currentMonth) + "月"
                + String.valueOf(this.nowDay) + "日");
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

}
