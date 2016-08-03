package com.app.infideap.stylishwidget.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
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

    public void setFontRegular(String fontRegular) {
        FONT_REGULAR = fontRegular;
    }

    public void setFontBold(String fontBold) {
        FONT_BOLD = fontBold;
    }

    public void setFontItalic(String fontItalic) {
        FONT_ITALIC = fontItalic;
    }

    public static Stylish getInstance() {
        return instance;
    }

    Typeface getTypeface(Context context, String font, Typeface typeface) {

        if (!Stylish.TYPEFACE.containsKey(font)) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(),
                        font);

                Stylish.TYPEFACE.put(font, typeface);
            } catch (Exception e) {
                Log.e(TAG,
                        String.format(
                                Locale.getDefault(),
                                "Unable to load '%s'. " +
                                        "Please make sure the path of the font is correct.",
                                font
                        )
                );
            }
        } else
            typeface = Stylish.TYPEFACE.get(font);

        return typeface;
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
}
