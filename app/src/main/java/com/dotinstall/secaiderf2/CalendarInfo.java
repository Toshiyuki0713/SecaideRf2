package com.dotinstall.secaiderf2;

/**
 * Created by gosho on 2017/12/31.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

/**
 * カレンダーフィールド作成クラス
 */
public class CalendarInfo {
    private int year; //対象の西暦年
    private int month; //対象の月
    private int startDay; //先頭曜日
    private int lastDate; //月末日付
    public int[][] calendarMatrix = new int[5][7];	//カレンダー情報テーブル

    CalendarList cal = new CalendarList(0);
    ArrayList<String> koreFmt = new ArrayList<String>();
    //ArrayList<CalendarList> cal = new ArrayList<>();




    /**
     　* カレンダー表オブジェクトを作成します。
     　* @param year 西暦年(..., 2005, 2006, 2007, ...)
     　* @param month 月(1, 2, 3, ..., 10, 11, 12)
     */
    public CalendarInfo(int year, int month) {
        this.year = year;
        this.month = month;
        this.createFields();
    }
    /**
     * カレンダーフィールド作成
     */
    private void createFields() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        //dayList
        ArrayList<String> dayList = new ArrayList<>();

        // 月の初めの曜日を求めます。
        calendar.set(year, month - 1, 1); // 引数: 1月: 0, 2月: 1, ...
        this.startDay = calendar.get(Calendar.DAY_OF_WEEK);//曜日を取得
        //Log.e("calendar月初", calendar.toString());
        Log.e("startDay","月初の曜日は「" + startDay + "」");

        // 月末の日付を求めます。
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        this.lastDate = calendar.get(Calendar.DATE);//日を取得
        //Log.e("calendar月末", calendar.toString());
        Log.e("lastDate","月末は「" + lastDate + "」");
        int dayCount = 1;
        boolean isStart = false;
        boolean isEnd = false;

        //日付を格納
        dayList.add(year + "年" + month + "月" + dayCount + "日");
        //Log.e("days？？", "daysは「" + dayList + "」");

        cal.setDays(year + "年" + month + "月" + dayCount + "日");
        Log.e("setDays", cal.toString());


        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 7 ; j++) {
                //初期値セット
                this.calendarMatrix[i][j] = 0;

                //先頭曜日確認
                // startDay: 日曜日 = 1, 月曜日 = 2, ...
                if(isStart == false && (this.startDay -1 ) == j) {
                    //日にちセット開始
                    isStart = true;
                }

                //System.out.println(year + "年" + month + "日");
                //Log.e("System.out？？", "System.outは「" + year + "年" + month + "月" + dayCount + "日" + "」");

                if(isStart) {
                    //終了日まで行ったか
                    if(!isEnd) {
                        this.calendarMatrix[i][j] = dayCount;
                    }

                    //カウント＋１
                    dayCount++;
                    //Log.e("dayCount++", "dayCount++は「" + dayCount + "」");

                    dayList.add(year + "年" + month + "月" + dayCount + "日");
                    //dayList.add(year + month + dayCount);
                    //Log.e("days？？", "daysは「" + (year + "年" + month + "月" + dayCount + "日" + "」"));
                    //Log.e("dayList", "dayListは「" + dayList + "」");


                    //cal.setDays(year + "年" + month + "月" + dayCount + "日");
                    //Log.e("setDays", cal.toString());




                    //dayList.add(dayCount);
                    //Log.e("dayCountできてる？？", "daiCountは「" + dayCount + "」");


                    //終了確認
                    if(dayCount > this.lastDate) {
                        isEnd = true;
                    }
                }
            }
        }
        cal.setCalList(dayList);
        Log.e("CalList？？", cal.toString());
    }
}

