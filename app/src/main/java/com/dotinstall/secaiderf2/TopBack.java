package com.dotinstall.secaiderf2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by gosho on 2017/12/05.
 */

/*
public class BackOnCanvas extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top);

        //TextView textView = (TextView) findViewById(R.id.top);
    }
    */

    public class TopBack extends View {

        Paint paint;

        public TopBack(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint = new Paint();
        }


        @Override
        protected void onDraw(Canvas canvas) {

            // 三角形を書く
            paint.setStrokeWidth(10);
            paint.setARGB(255, 102, 139, 192);
            Path path = new Path();
            path.moveTo(0, 0);
            path.lineTo(1440, 0);
            path.lineTo(0, 2000);
            path.lineTo(0, 0);
            canvas.drawPath(path, paint);


            paint.setStrokeWidth(10);
            paint.setARGB(255, 101, 173, 219);
            Path path2 = new Path();
            path2.moveTo(1440, 0);
            path2.lineTo(1540, 0);
            path2.lineTo(0, 2100);
            path2.lineTo(0, 2000);
            canvas.drawPath(path2, paint);
        }

    }
//}

