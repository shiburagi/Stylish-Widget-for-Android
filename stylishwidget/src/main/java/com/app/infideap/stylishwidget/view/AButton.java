package com.app.infideap.stylishwidget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;


/**
 * Created by Zariman on 13/4/2016.
 */
public class AButton extends Button {
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
        TypedArray a = context.obtainStyledAttributes(attrs, android.support.v7.appcompat.R.styleable.TextAppearance);
        int style = a.getInt(android.support.v7.appcompat.R.styleable.TextAppearance_android_textStyle, Typeface.BOLD);

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
        String font;
        switch (style) {
            case Typeface.BOLD:
                font = Stylish.FONT_BOLD;
                break;
            case Typeface.ITALIC:
                font = Stylish.FONT_ITALIC;
                break;
            case Typeface.NORMAL:
                font = Stylish.FONT_REGULAR;
                break;
            default:
                font = Stylish.FONT_REGULAR;
        }

        try {

            setTypeface(Stylish.getInstance().getTypeface(getContext(), font, getTypeface()));
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
