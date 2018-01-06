package com.dotinstall.secaiderf2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class HealthCareData extends AppCompatActivity {

    //private String message;
    private KoreHealthBack koreHealthBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthcaredata);

        koreHealthBack = (KoreHealthBack) this.findViewById(R.id.back);

        ImageView imageBi = findViewById(R.id.biIcon);
        imageBi.setImageResource(R.drawable.bi);

        ImageView imageSan = findViewById(R.id.sanIcon);
        imageSan.setImageResource(R.drawable.san);

        ImageView imageH = findViewById(R.id.hIcon);
        imageH.setImageResource(R.drawable.h);

        ImageView imageSecList = findViewById(R.id.secListView);
        imageSecList.setImageResource(R.drawable.sec_list2);

        /*
        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        TextView textView = (TextView) findViewById(R.id.profileName);
        textView.setText(message); */
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

}
