package com.dotinstall.secaiderf2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by gosho on 2017/11/26.
 */

public class Top extends AppCompatActivity {

    private BackOnCanvas backCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top);

        backCanvas = (BackOnCanvas) this.findViewById(R.id.back);

        /*
        ImageView imageView = findViewById(R.id.top);
        imageView.setImageResource(R.drawable.secaide_03);
        */

        /*
        TestView testView = new TestView(this);
        setContentView(testView); */


    }

    /*
    public void calendar_activity(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);

    }
    */

    /*
    class TestView extends View {
        Paint paint;

        public TestView(Context context) {
            super(context);
            paint = new Paint();
        }


        @Override
        protected void onDraw(Canvas canvas) {

            // 三角形を書く
            paint.setStrokeWidth(10);
            paint.setColor(Color.BLUE);
            Path path = new Path();
            path.moveTo(0, 0);
            path.lineTo(1440, 0);
            path.lineTo(0, 2000);
            path.lineTo(0, 0);
            canvas.drawPath(path, paint);


            paint.setStrokeWidth(10);
            paint.setColor(Color.CYAN);
            Path path2 = new Path();
            path2.moveTo(1440, 0);
            path2.lineTo(1540, 0);
            path2.lineTo(0, 2100);
            path2.lineTo(0, 2000);
            canvas.drawPath(path2, paint);



        }

    } */

    public void calendar_view(View view) {
        Intent intent = new Intent(this, Calendar.class);
        //String str = profileText.getText().toString();
        //intent.putExtra("message", str);
        startActivity(intent);
        Log.e("start", intent.toString());
    }


}



