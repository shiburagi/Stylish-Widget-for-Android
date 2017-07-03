package com.app.infideap.stylishwidget.view;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zariman on 30/4/2016.
 */
public class Stylish {

    static final Map<String, Typeface> TYPEFACE = new HashMap<>();
    private static final String TAG = Stylish.class.getSimpleName();
    static String FONT_REGULAR = "";
    static String FONT_BOLD = "";
    static String FONT_ITALIC = "";
    static String FONT_BOLD_ITALIC = "";

    private static final Stylish instance;

    static {
        instance = new Stylish();
    }

    private float fontScale = 1.0f;

    private Stylish() {
    }

    public void set(String regular, String bold, String italic) {
        FONT_REGULAR = regular;
        FONT_BOLD = bold;
        FONT_ITALIC = italic;
    }

    public void set(String regular, String bold, String italic, String boldItalic) {
        FONT_REGULAR = regular;
        FONT_BOLD = bold;
        FONT_ITALIC = italic;
        FONT_BOLD_ITALIC = boldItalic;
    }

    public void setFontRegular(String fontRegular) {
        FONT_REGULAR = fontRegular;
    }

    public void setFontBold(String fontBold) {
        FONT_BOLD = fontBold;
    }

    public void setFontItalic(String fontItalic) {
        FONT_ITALIC = fontItalic;
    }

    public void setFontBoldItalic(String fontBoldItalic) {
        FONT_BOLD_ITALIC = fontBoldItalic;
    }

    public static Stylish getInstance() {
        return instance;
    }

    Typeface getTypeface(Context context, String font, int style) {

        Typeface typeface;
        if (!Stylish.TYPEFACE.containsKey(font)) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(),
                        font);
                Stylish.TYPEFACE.put(font, typeface);

            } catch (Exception e) {
                typeface = Typeface.defaultFromStyle(style);
            }


        } else
            typeface = Stylish.TYPEFACE.get(font);

        return typeface;
    }

    public Typeface reqular(Context context) {
        return getTypeface(context, FONT_REGULAR, Typeface.NORMAL);
    }

    public Typeface bold(Context context) {
        return getTypeface(context, FONT_BOLD, Typeface.BOLD);
    }


    public Typeface italic(Context context) {
        return getTypeface(context, FONT_ITALIC, Typeface.ITALIC);
    }

    public Typeface boldItalic(Context context) {
        return getTypeface(context, FONT_BOLD_ITALIC, Typeface.BOLD_ITALIC);
    }

    boolean isExist(Context context, String font) {
        try {
            return Arrays.asList(context.getResources().getAssets().list("")).contains(font);
        } catch (IOException e) {
            return false;
        }
    }

    public float getFontScale() {
        return fontScale;
    }

    public void setFontScale(float fontScale) {
        this.fontScale = fontScale;
    }


    public Typeface getRegular() {
        return TYPEFACE.get(FONT_REGULAR);
    }

    public Typeface getBold() {
        return TYPEFACE.get(FONT_BOLD);
    }

    public Typeface getItalic() {
        return TYPEFACE.get(FONT_ITALIC);
    }

    public Typeface getBoldItalic() {
        return TYPEFACE.get(FONT_BOLD_ITALIC);
    }

    public void setTextStyle(TextView textView, int style) {
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
            case Typeface.BOLD_ITALIC:
                font = Stylish.FONT_BOLD_ITALIC;
                break;
            default:
                font = Stylish.FONT_REGULAR;
        }

        try {

            textView.setTypeface(Stylish.getInstance().getTypeface(textView.getContext(), font, style));
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
