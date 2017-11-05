package com.dotinstall.secaiderf2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gosho on 2017/10/21.
 */

public class User extends AppCompatActivity {
    SharedPreferences lineData = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
    String Level = lineData.getString("user_id", "No Data");
    }
