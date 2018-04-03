package com.dotinstall.secaiderf2;

/**
 * Created by gosho on 2018/03/31.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by gosho on 2018/03/30.
 */

public class CustomImageView extends RelativeLayout {
    private Context mContext;

    //コンストラクタは3つ用意しました。 API21からは4つのコンストラクタが使えるようです
    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.custom_image_view, this, true);
    }

    public void setInfo(String date, String koredakeIvent, String biposiIvent) {

        ((TextView) findViewById(R.id.date)).setText(date);
        ((TextView) findViewById(R.id.koredakeIvent)).setText(koredakeIvent);
        ((TextView) findViewById(R.id.koredakeIvent)).setTextColor(Color.BLUE);
        ((TextView) findViewById(R.id.biposiIvent)).setText(biposiIvent);
        ((TextView) findViewById(R.id.biposiIvent)).setTextColor(Color.RED);

        invalidate();
    }

    public void setInfoDate(String date) {

        ((TextView) findViewById(R.id.date)).setText(date);

        invalidate();
    }
}
