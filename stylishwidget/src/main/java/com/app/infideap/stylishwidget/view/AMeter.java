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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

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
    private int gapBottom = (int) Utils.convertDpToPixel(50);
    private int minimumSize = (int) Utils.convertDpToPixel(250);
    private boolean showText;
    private int numberOfLine = 1;
    private float startValue = 0;
    private boolean showNeedle;
    private float lineStrokeSize;
    private float lineWidth;
    private String unit;


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
        setShowNeedle(a.getBoolean(R.styleable.meterAttr_sw_showNeedle, true));
        setLineWidth(a.getFloat(R.styleable.meterAttr_sw_lineWidth, 1f));
        setLineStrokeSize(a.getDimension(R.styleable.meterAttr_sw_lineStrokeSize, Utils.convertDpToPixel(10)));
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
        setStartValue(a.getFloat(R.styleable.constraintAttr_sw_startValue, 0f));
        setMaxValue(a.getFloat(R.styleable.constraintAttr_sw_maxValue, 1f));
        setNumberOfLine(a.getInt(R.styleable.constraintAttr_sw_numberOfLine, 1));
        setUnit(a.getString(R.styleable.constraintAttr_sw_unit));

        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getLayoutParams().width != getLayoutParams().height
                || getLayoutParams().width == LayoutParams.WRAP_CONTENT) {

            if (getLayoutParams().width != LayoutParams.MATCH_PARENT)
                if (getLayoutParams().width < minimumSize)
                    getLayoutParams().width = minimumSize;
            if (getLayoutParams().height != LayoutParams.MATCH_PARENT)
                if (getLayoutParams().height < minimumSize)
                    getLayoutParams().height = minimumSize;


            if (getHeight() < getWidth())
                getLayoutParams().height = getWidth();


            setLayoutParams(getLayoutParams());


            super.onMeasure(
                    widthMeasureSpec,
                    widthMeasureSpec
            );


        } else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void initView() {

        setWillNotDraw(false);
        setGravity(Gravity.CENTER);
        setPadding(
                super.getPaddingLeft(),
                super.getPaddingTop(),
                super.getPaddingRight(),
                super.getPaddingBottom());
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_needle, this, false);

        addView(view);

        needle1View = view.findViewById(R.id.view_needle_1);
        needle2View = view.findViewById(R.id.view_needle_2);


    }

    Paint paint = new Paint();


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int alphaColor = Utils.adjustAlpha(meterColor, 70);
        paint.setColor(alphaColor);

        float width = (float) getWidth() - super.getPaddingRight() - super.getPaddingLeft();
        float height = (float) getHeight() - super.getPaddingTop() - super.getPaddingBottom();
        float radius;

        radius = width / 2;

        Path path = new Path();

        path.addCircle(width / 2,
                height / 2, radius,
                Path.Direction.CW);
        float strokeSize = lineStrokeSize;
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

        int numberOfLine = this.numberOfLine;
        float perBit = sweepAngle / (numberOfLine * 2 - 1);
        float lineWidth = numberOfLine <= 1 ? 1 : this.lineWidth;
        float bit = perBit * lineWidth;

        float weight = numberOfLine <= 1 ? 1 : 0.5f * lineWidth;

        float totalBit = bit * numberOfLine;
        float totalSpaceBit = sweepAngle - totalBit;
        float space = numberOfLine <= 1 ? 0 : totalSpaceBit / (numberOfLine - 1);

        float progress = value * numberOfLine / maxValue;
        float startProgress = startValue * numberOfLine / maxValue;

        paint.setColor(alphaColor);
        for (int i = 0; i < numberOfLine; i++) {
//            canvas.drawArc(oval, startAngle + bit * i, bit, false, paint);
            canvas.drawArc(oval, startAngle + bit * i + space * i, bit, false, paint);
        }

        if (startProgress < progress) {
            paint.setColor(meterColor);
            int loopStart = (int) startProgress;
            float diff = startProgress - (loopStart);

            if (diff > 0) {
                if (diff < weight) {
                    float balance = progress - startProgress;
                    float start = startAngle + startProgress * (bit + space);
                    float angle = balance < weight ?
                            bit * balance : bit * (weight - diff);
                    canvas.drawArc(oval, start, angle, false, paint);
                }

                startProgress = loopStart + 1;
            }
            for (int i = (int) startProgress; i < progress; i++) {
                float balance = progress - i;
//                canvas.drawArc(oval, startAngle + bit * i, balance < 1 ? bit * balance : bit, false, paint);
                canvas.drawArc(oval, startAngle + bit * i + space * i, balance < weight ? bit * balance : bit, false, paint);
            }
        }
        if (showText) {

            paint.setStyle(Paint.Style.FILL);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(meterColor);
            paint.setTextSize(textSize);
            String text = String.format(Locale.getDefault(), "%.0f", value * 100 / maxValue);
            if (unit.length() <= 1)
                text = text.concat(unit);

            if (showNeedle) {
                canvas.drawText(text,
                        center_x,
                        center_y + radius + gapBottom / 2, paint);

                if (unit.length() > 1) {

                    paint.setTextSize(textSize / 2);
                    canvas.drawText(unit,
                            center_x,
                            center_y + radius + gapBottom / 2 + textSize / 2, paint);
                }
            } else {

                canvas.drawText(
                        text,
                        center_x,
                        center_y + textSize / 2, paint);
                if (unit.length() > 1) {
                    paint.setTextSize(textSize / 2);
                    canvas.drawText(
                            unit,
                            center_x,
                            center_y + textSize, paint);
                }
            }
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
        invalidate();
    }

    public boolean isShowText() {
        return showText;
    }

    public void setNumberOfLine(int numberOfLine) {
        this.numberOfLine = numberOfLine;
        invalidate();
    }

    public int getNumberOfLine() {
        return numberOfLine;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void setStartValue(float startValue) {
        this.startValue = startValue;
        invalidate();
    }

    public float getStartValue() {
        return startValue;
    }

    public void setShowNeedle(boolean showNeedle) {
        this.showNeedle = showNeedle;
        view.setVisibility(showNeedle ? VISIBLE : GONE);
    }

    public boolean isShowNeedle() {
        return showNeedle;
    }

    public void setLineStrokeSize(float lineStrokeSize) {
        this.lineStrokeSize = lineStrokeSize;
        needle1View.setLayoutParams(new LayoutParams((int) lineStrokeSize, (int) Utils.convertDpToPixel(10)));
        invalidate();
    }

    public float getLineStrokeSize() {
        return lineStrokeSize;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? "" : unit.trim();
        invalidate();
    }

    public String getUnit() {
        return unit;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        invalidate();
    }

    public float getLineWidth() {
        return lineWidth;
    }
}
