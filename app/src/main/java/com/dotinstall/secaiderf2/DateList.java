package com.dotinstall.secaiderf2;

import java.util.Comparator;

/**
 * Created by gosho on 2018/02/07.
 */

public class DateList {
    String date;
    String steps;
    public DateList( String d, String s ){
        date = d;
        steps = s;
    }
    public  String toString(){
        return date + ":" + steps;
    }
}
