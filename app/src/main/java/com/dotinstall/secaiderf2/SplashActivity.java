package com.dotinstall.secaiderf2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by gosho on 2017/11/26.
 */
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private static long WAIT_MILLIS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        ImageView imageView = (ImageView) findViewById(R.id.View);
        imageView.setImageResource(R.drawable.splash);
    }

    @Override
    public void onResume() {
        super.onResume();

        // 一定時間後にトップ画面に移行する

        Timer t = new Timer();
        final Window window = this.getWindow();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                // トップ画面を立ち上げ、速やかに終了する
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, WAIT_MILLIS);
    }
}
