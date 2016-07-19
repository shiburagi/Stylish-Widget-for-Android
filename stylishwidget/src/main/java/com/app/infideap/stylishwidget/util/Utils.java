package com.app.infideap.stylishwidget.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Shiburagi on 14/07/2016.
 */
public class Utils {
    public static float convertPixelsToDp(float px){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static float convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}
