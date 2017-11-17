package com.dotinstall.secaiderf2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HealthCareData extends AppCompatActivity {

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care_data);

        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        TextView textView = (TextView) findViewById(R.id.profileName);
        textView.setText(message);
    }

    public void koredake_view (View view) {
        Intent intent = new Intent(this, Koredake.class);
        String str = message.toString();
        intent.putExtra("message", str);
        startActivity(intent);
    }

    public void main_view (View view) {
        Intent intent = new Intent(this, Biposi.class);
        String str = message.toString();
        intent.putExtra("message", str);
        startActivity(intent);
    }

}
