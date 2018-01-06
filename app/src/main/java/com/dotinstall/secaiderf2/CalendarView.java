package com.dotinstall.secaiderf2;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gosho on 2017/12/21.
 */

public class CalendarView extends AppCompatActivity {

    // 管理しているカレンダー情報
    Calendar mCalendar;


    /*
    // インスタンス生成
    public static CalendarView newInstance(String title, int year, int month, int date)
    {
        CalendarView calendarView = new CalendarView();
        // 引数を設定
        Bundle args = new Bundle();
        args.putString("Title", title);
        args.putInt("Year", year);
        args.putInt("Month", month);
        args.putInt("Date", date);
        calendarView.setArguments(args);

        return calendarView;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_koredake);

        //初期表示する年月日およびダイアログに表示するタイトルの取得
        Bundle args = getArguments();
        int year = args.getInt("Year");
        int month = args.getInt("Month");
        int date = args.getInt("Date");
        String title = args.getString("Title");

        // 初期設定されている日付をメンバ変数に設定
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, month - 1, date);

    }
    */



}
