package com.dotinstall.secaiderf2;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gosho on 2018/02/18.
 */

public class CalendarList {

    private int textViewId = 0;					//テキストビューＩＤ
    private TextView textObject = null;			//テキストオブジェクト
    private int dayNum = 0;						//設定日付
    private boolean isNowDay = false;			//当日フラグ
    private boolean isSelected = false;			//選択フラグ

    private String days;


    /**
     * コンストラクタ
     */
    public CalendarList(int controlId){this.setTextViewId(controlId);}

    /**
     * @return textViewId
     */
    public int getTextViewId() {
        return textViewId;
    }
    /**
     * @param textViewId セットする textViewId
     */
    public void setTextViewId(int textViewId) {
        this.textViewId = textViewId;
    }
    /**
     * textObject 取得
     * @return textObject
     */
    public TextView getTextObject() {
        return textObject;
    }
    /**
     * @param textObject 設定 textObject
     */
    public void setTextObject(TextView textObject) {
        this.textObject = textObject;
    }

    /**
     * dayNum 取得
     * @return dayNum
     */
    public int getDayNum() {
        return dayNum;
    }
    /**
     * @param dayNum 設定 dayNum
     */
    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }



    ArrayList<String> calList = new ArrayList<String>();

    public ArrayList<String> getCalList() {
        return calList;
    }

    public void setCalList(ArrayList<String> calList) {
        this.calList = calList;
    }


    public String getDays() { return days; }

    public void setDays(String days) {
        this.days = days;
    }
}
