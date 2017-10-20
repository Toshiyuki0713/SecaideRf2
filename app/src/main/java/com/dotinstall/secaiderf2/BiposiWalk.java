package com.dotinstall.secaiderf2;

/**
 * Created by gosho on 2017/10/20.
 */

//サンプルを真似ただけなのですが、リンク先のJSONをちゃんとPOJO変換してコピペした方がいいでしょうか？
public class BiposiWalk {
    private String steps;
    private String distance;
    private String start_date;
    private String end_date;
    private String created_at;
    private String user;

    public String getSteps(){
        return steps;
    }

    public String getDistance(){
        return distance;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUser() {
        return user;
    }
}
