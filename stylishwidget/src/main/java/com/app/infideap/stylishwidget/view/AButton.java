package com.app.infideap.stylishwidget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatButton;
import android.util.AttributeSet;

import com.app.infideap.stylishwidget.R;


/**
 * Created by Zariman on 13/4/2016.
 */
public class AButton extends AppCompatButton {
    public AButton(Context context) {
        super(context);
        setCustomTypeface(context, null);

    }

    public AButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomTypeface(context, attrs);

    }

    public AButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomTypeface(context, attrs);

    }


    private void setCustomTypeface(Context context, AttributeSet attrs) {
        if(isInEditMode())
            return;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextAppearance);
        int style = a.getInt(R.styleable.TextAppearance_android_textStyle, Typeface.BOLD);

        setTextStyle(style);
        a.recycle();

        post(new Runnable() {
            @Override
            public void run() {
                //setTextSize(getTextSize() * Stylish.getInstance().getFontScale());
            }
        });
    }

    public void setTextStyle(int style) {
        Stylish.getInstance().setTextStyle(this, style);
    }
}
