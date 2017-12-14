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

    private BackOnCanvas3 backCanvas3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        backCanvas3 = (BackOnCanvas3) this.findViewById(R.id.back3);

        /*
        ImageView imageView = findViewById(R.id.calendar);
        imageView.setImageResource(R.drawable.korecalendar); */

    }

    public void login_activity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
