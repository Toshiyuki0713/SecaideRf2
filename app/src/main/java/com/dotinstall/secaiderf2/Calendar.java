package com.dotinstall.secaiderf2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by gosho on 2017/11/26.
 */

public class Calendar extends AppCompatActivity {

    private CalendarBack calendarBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        calendarBack = (CalendarBack) this.findViewById(R.id.back3);


        ImageView imageView = findViewById(R.id.sec_cal);
        imageView.setImageResource(R.drawable.sec_calendar);

        ImageView imageView2 = findViewById(R.id.koredeki_undo);
        imageView2.setImageResource(R.drawable.koredeki);

    }


    public void login_activity(View view) {
        Intent intent = new Intent(this, Koredake.class);
        startActivity(intent);
    }

    public void koredake_view (View view) {
        Intent intent = new Intent(this, Koredake.class);
        //String str = message.toString();
        //intent.putExtra("message", str);
        startActivity(intent);
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
