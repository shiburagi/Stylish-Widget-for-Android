package com.app.infideap.stylishwidget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.infideap.stylishwidget.Log;
import com.app.infideap.stylishwidget.R;
import com.app.infideap.stylishwidget.util.Utils;

import java.util.Locale;


/**
 * Created by Zariman on 13/4/2016.
 */
public class AMeter extends LinearLayout {
    private static final String TAG = AMeter.class.getSimpleName();
    private float value;
    private int meterColor;
    private View view;
    private float startAngle = 130;
    private float sweepAngle = 280;
    private float maxValue;
    private View needle1View;
    private View needle2View;
    private int textStyle;

    private float textSize;
    private int gapBottom = (int) Utils.convertDpToPixel(30);
    private int minimumSize = (int) Utils.convertDpToPixel(250);
    private boolean showText;


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public AMeter(Context context) {
        super(context);
        setCustomAttr(context, null);

    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public AMeter(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomAttr(context, attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public AMeter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomAttr(context, attrs);

    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void setCustomAttr(Context context, AttributeSet attrs) {

        initView();


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.meterAttr);

        setMeterColor(a.getColor(R.styleable.meterAttr_sw_meterColor, Color.WHITE));
        a.recycle();

        a = getContext().obtainStyledAttributes(attrs, R.styleable.textAttr);

        setTextSize(a.getDimension(R.styleable.textAttr_sw_textSize,
                getResources().getDimension(R.dimen.meterTextSize)));

        int textStyle;
        try {
            textStyle = a.getInt(R.styleable.textAttr_sw_textStyle, Typeface.BOLD);
        } catch (Exception e) {
            textStyle = Typeface.BOLD;
        }

        setTextStyle(textStyle);

        a.recycle();


        a = getContext().obtainStyledAttributes(attrs, R.styleable.constraintAttr);
        setShowText(a.getBoolean(R.styleable.constraintAttr_sw_showText, true));
        setValue(a.getFloat(R.styleable.constraintAttr_sw_value, 0f));
        setMaxValue(a.getFloat(R.styleable.constraintAttr_sw_maxValue, 1f));

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getLayoutParams().width != getLayoutParams().height) {

            if (getLayoutParams().width != ViewGroup.LayoutParams.MATCH_PARENT)
                if (widthMeasureSpec < minimumSize)
                    getLayoutParams().width = minimumSize;
            if (getLayoutParams().height != ViewGroup.LayoutParams.MATCH_PARENT)
                if (heightMeasureSpec < minimumSize)
                    getLayoutParams().height = minimumSize;

//            if (getLayoutParams().height > getLayoutParams().width) {
//                getLayoutParams().width = getLayoutParams().height;
//            } else if (getLayoutParams().height < getLayoutParams().width)
//                getLayoutParams().height = getLayoutParams().width;

            if (getHeight() < getWidth())
                getLayoutParams().height = getWidth();
        }

    }

    private void initView() {

        setWillNotDraw(false);
        setGravity(Gravity.CENTER);
        setPadding(0, 0, 0, 0);
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_needle, this, false);
//        view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        addView(view);

        needle1View = view.findViewById(R.id.view_needle_1);
        needle2View = view.findViewById(R.id.view_needle_2);


    }

    Paint paint = new Paint();


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onDraw(Canvas canvas) {


        requestLayout();


        super.onDraw(canvas);

        paint.setColor(Utils.adjustAlpha(meterColor, 70));

        float width = (float) getWidth() - super.getPaddingRight() - super.getPaddingLeft();
        float height = (float) getHeight() - super.getPaddingTop() - super.getPaddingBottom();
        float radius;

        radius = width / 2;

        Path path = new Path();

        path.addCircle(width / 2,
                height / 2, radius,
                Path.Direction.CW);
        float strokeSize = Utils.convertDpToPixel(10);
        paint.setStrokeWidth(strokeSize);
        paint.setStyle(Paint.Style.FILL);

        float center_x, center_y;
        paint.setStyle(Paint.Style.STROKE);
        radius -= (strokeSize / 2);

        center_x = width / 2 + super.getPaddingLeft();
        center_y = height / 2 + super.getPaddingTop();

        final RectF oval = new RectF();
        oval.set(center_x - radius,
                center_y - radius,
                center_x + radius,
                center_y + radius);
        canvas.drawArc(oval, startAngle, sweepAngle, false, paint);


        if (showText) {
            paint.setStyle(Paint.Style.FILL);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(meterColor);
            paint.setTextSize(textSize);

            canvas.drawText(String.format(Locale.getDefault(), "%.0f", value * 100 / maxValue),
                    center_x,
                    center_y + radius + gapBottom / 2, paint);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void rotateNeedle() {

        if (view != null) {
            float progress = value * sweepAngle / maxValue;
            progress = progress > sweepAngle ? sweepAngle : progress;
            view.setRotation(-90 + (startAngle - 90) + progress);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void setValue(float value) {
        this.value = value;
        if (value > maxValue) {
            maxValue = value;
        }

        invalidate();
        rotateNeedle();
    }

    public void setMeterColor(int meterColor) {
        this.meterColor = meterColor;

        needle1View.getBackground().setColorFilter(meterColor, PorterDuff.Mode.SRC_ATOP);
        needle2View.getBackground().setColorFilter(meterColor, PorterDuff.Mode.SRC_ATOP);

        invalidate();
//        requestLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
        invalidate();
        rotateNeedle();
    }

    public float getValue() {
        return value;
    }

    public int getMeterColor() {
        return meterColor;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setTextStyle(int textStyle) {
        this.textStyle = textStyle;
        invalidate();
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        invalidate();
    }

    @Override
    public void setPadding(@Px int left, @Px int top, @Px int right, @Px int bottom) {
        super.setPadding(left + gapBottom, top + gapBottom, right + gapBottom, bottom + gapBottom);
    }

    @Override
    public int getPaddingBottom() {
        return super.getPaddingBottom() - gapBottom;
    }

    @Override
    public int getPaddingTop() {
        return super.getPaddingTop() - gapBottom;
    }

    @Override
    public int getPaddingLeft() {
        return super.getPaddingLeft() - gapBottom;
    }

    @Override
    public int getPaddingRight() {
        return super.getPaddingRight() - gapBottom;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
//        int gapBottom = (int) Utils.convertDpToPixel(5);
//        super.setPadding(
//                getPaddingLeft() + gapBottom,
//                getPaddingTop() + gapBottom,
//                getPaddingRight() + gapBottom,
//                getPaddingBottom() + gapBottom
//        );
//        this.gapBottom = gapBottom;
    }

    public boolean isShowText() {
        return showText;
    }
}
