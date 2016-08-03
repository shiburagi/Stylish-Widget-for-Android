package com.app.infideap.stylishwidget.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.app.infideap.stylishwidget.R;

/**
 * Created by Shiburagi on 01/08/2016.
 */
public class ACircularProgressBar extends LinearLayout {
    public ACircularProgressBar(Context context) {
        super(context);
        init(context,null);
    }

    public ACircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ACircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ACircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_circular_progressbar, this);
    }
}
