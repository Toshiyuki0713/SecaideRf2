package com.dotinstall.secaiderf2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gosho on 2017/12/10.
 */

public class BiposiBack extends View {
    Paint paint;

    public BiposiBack(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // 三角形を書く
        paint.setStrokeWidth(10);
        paint.setARGB(255, 229, 233, 169);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(1440, 0);
        path.lineTo(0, 2000);
        path.lineTo(0, 0);
        canvas.drawPath(path, paint);


        paint.setStrokeWidth(10);
        paint.setARGB(255, 149, 194, 143);
        Path path2 = new Path();
        path2.moveTo(1440, 0);
        path2.lineTo(1540, 0);
        path2.lineTo(0, 2100);
        path2.lineTo(0, 2000);
        canvas.drawPath(path2, paint);
    }
}
