package com.app.infideap.stylishwidget.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.infideap.stylishwidget.R;
import com.app.infideap.stylishwidget.util.Utils;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Shiburagi on 13/07/2016.
 */
public class AProgressBar extends LinearLayout {
    private static final String TAG = AProgressBar.class.getSimpleName();
    private static final Object TEXTVIEW_TAG = "TEXTVIEW";
    private int id = 1;
    private int radius;
    private int padding;
    private boolean isReverse;
    private float max;
    private List<LinearLayout> progressLayouts;

    private List<Progress> progress;

    private int gravity;
    private int textStyle;
    private float textSize;
    private int textAppearance;
    private ArrayList<Progress> sortProgress;
    private int iconPadding;

    public AProgressBar(Context context) {
        super(context);
        init(context, null);

    }

    public AProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
//        if (isInEditMode())
//            return;

//        setGravity(Gravity.RIGHT);

        progress = new ArrayList<>();
        progressLayouts = new ArrayList<>();
        sortProgress = new ArrayList<>();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.progressBar);
        setMaxValue(a.getFloat(R.styleable.progressBar_sw_maxValue, 0f));
        setProgressValue(a.getFloat(R.styleable.progressBar_sw_progressValue, 0f));
        setProgressText(a.getString(R.styleable.progressBar_sw_progressText));
        setProgressTextStyle(a.getInt(R.styleable.progressBar_sw_progressTextStyle, 0));
        setProgressTextAppearance(a.getInt(R.styleable.progressBar_sw_progressTextAppearance, 0));
        setProgressTextSize(a.getDimension(R.styleable.progressBar_sw_progressTextSize,
                getResources().getDimension(R.dimen.progressTextSize)));
        setProgressIcon(a.getDrawable(R.styleable.progressBar_sw_progressIcon));
        setRadius(a.getDimensionPixelSize(R.styleable.progressBar_sw_radius, 0));
        setPadding(a.getDimensionPixelSize(R.styleable.progressBar_sw_progressPadding, 0));
        setProgressIconPadding(a.getDimensionPixelSize(R.styleable.progressBar_sw_progressIconPadding,0));

        setProgressColor(a.getColor(R.styleable.progressBar_sw_progressColor, Color.rgb(255, 255, 255)));
        setProgressBackground(a.getColor(R.styleable.progressBar_sw_progressBackground, Color.argb(0, 0, 0, 0)));

        refresh();

        if (!isInEditMode()) {
            if (a.getBoolean(R.styleable.progressBar_sw_withAnimation, false))
                withAnimation(a.getInteger(R.styleable.progressBar_sw_duration, 1000));
        }
        a.recycle();
    }

    private void setProgressTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setProgressBackground(int color) {
        createGradientDrawableWithCorner(this, color);
    }

    private void refresh() {
        if (progressLayouts != null) {
            if (progressLayouts.size() != sortProgress.size()) {
                addView(getContext());
            }
        } else
            addView(getContext());


        if (progressLayouts != null && progress != null) {
            float previousValue = 0;

            for (int i = 0; i < progressLayouts.size(); i++) {
                progressLayouts.get(i).setGravity(gravity);
                progressLayouts.get(i).setWeightSum(sortProgress.get(i).value);
                setProgress(progressLayouts.get(i), sortProgress.get(i), previousValue, i);

                previousValue = sortProgress.get(i).value;
            }
        }
    }

    public float getProgressValue() {
        return getProgressValue(0);
    }

    public float getProgressValue(int index) {
        if (index < 0 || index >= progress.size())
            return 0;

        return progress.get(index).value;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void addView(Context context) {

        if (progress == null)
            return;

        removeAllViews();

        setOrientation(HORIZONTAL);

        LinearLayout progressLayout = null;
        boolean isRightAlign = isRightAlign();

        if (progressLayouts.size() > 0)
            progressLayout = progressLayouts.get(progressLayouts.size() - 1);
        for (int i = progressLayouts.size(); i < progress.size(); i++) {
            progressLayouts.add(new LinearLayout(context));
            progressLayouts.get(i).setOrientation(HORIZONTAL);

            ATextView textView = new ATextView(getContext());
            textView.setId(i);
            int padding = (int) Utils.convertDpToPixel(16);
            textView.setPadding(padding, 0, padding, 0);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setId(100+i);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setLayoutParams(new LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
            linearLayout.addView(textView);
            if (isRightAlign)
                progressLayouts.get(i).addView(linearLayout);
            if (progressLayout != null) {
                progressLayouts.get(i).addView(progressLayout);
            }
            if (!isRightAlign)
                progressLayouts.get(i).addView(linearLayout);
            progressLayout = progressLayouts.get(i);
        }
        if (progressLayout != null)
            addView(progressLayout);

        progressLayouts = progressLayouts.subList(0, progress.size());
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }


    private void setRadius(int dimension) {
        this.radius = dimension;

        refresh();
    }


    private void setMax(float maxValue) {
        this.max = maxValue;

        setWeightSum(max);
    }

    public void setMaxValue(float maxValue) {
        if (progress.size() > 0)
            if (sortProgress.get(progress.size() - 1).value > maxValue)
                maxValue = sortProgress.get(progress.size() - 1).value;
        setMax(maxValue);
        refresh();
    }

    public void setProgressValue(float progressValue) {

        if (progressValue > max) {
            setMax(progressValue);
        }
        if (progress.size() == 0)
            this.progress.add(
                    new Progress(progressValue)
            );
        else {
            this.progress.get(0).value = progressValue;
            progress = progress.subList(0, 1);
        }
        sort();
        refresh();
    }


    private void setProgress(LinearLayout progressLayout, Progress progress, float previousValue, int i) {
        createGradientDrawableWithCorner(progressLayout, progress.color);

        LayoutParams params =
                new LayoutParams(0, RelativeLayout.LayoutParams.MATCH_PARENT, progress.value);

        progressLayout.setLayoutParams(params);

        progressLayout.setWeightSum(progress.value);

        ATextView textView = (ATextView) progressLayout.findViewById(i);
        LinearLayout layout = (LinearLayout) progressLayout.findViewById(100+i);
        if (textView != null) {
            textView.setText(progress.text);
            textView.setCompoundDrawablesWithIntrinsicBounds(
                    progress.drawable,
                    null,
                    null,
                    null
            );
            textView.setCompoundDrawablePadding(iconPadding);
            textView.setTextStyle(textStyle);
            textView.setTextSize(textSize);
            textView.setSupportTextAppearance(textAppearance);
            layout.setLayoutParams(new LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    progress.value - previousValue
            ));
        }

    }


    public void setProgressValues(float... progressValues) {

        progress.clear();
        for (int i = 0; i < progress.size(); i++) {
            progress.get(i).value = progressValues[i];
        }
        for (int i = progress.size(); i < progressValues.length; i++) {
            progress.add(new Progress(progressValues[i]));
        }

        progress = progress.subList(0, progressValues.length);


        if (progress.get(progress.size() - 1).value > max)
            setMax(progress.get(progress.size() - 1).value);

        sort();
        refresh();
    }


    public void addProgressValue(float progressValue) {
        progress.add(new Progress(progressValue));
        sort();
        refresh();
    }

    public void addProgressValue(float progressValue, int color) {
        progress.add(new Progress(progressValue, color));
        sort();
        refresh();
    }

    public boolean removeProgressValue(int index) {
        if (index < 0 || index >= progress.size())
            return false;
        progress.remove(index);
        sort();
        refresh();
        return true;
    }

    public void setProgressValue(int index, float progressValue) {
        if (index < 0 || index >= progress.size())
            return;
        progress.get(index).value = progressValue;
        sort();
        refresh();
    }


    public void setProgressColor(int progressColor) {
        if (progress.size() == 0)
            this.progress.add(
                    new Progress(progressColor)
            );
        else {
            this.progress.get(0).color = progressColor;
            progress = progress.subList(0, 1);
        }
        refresh();
    }

    public void setProgressColor(int index, int color) {
        if (index < 0 || index >= progress.size())
            return;
        progress.get(index).color = color;
        refresh();
    }


    public void setProgressColors(int... colors) {
        for (int i = 0; i < progress.size(); i++) {
            progress.get(i).color = colors[i];
        }
        for (int i = progress.size(); i < colors.length; i++) {
            progress.add(new Progress(colors[i]));
        }

        refresh();
    }

    public void setProgressValueWithColor(int index, int progressValue, int color) {
        if (index < 0 || index >= progress.size())
            return;
        progress.get(index).value = progressValue;
        progress.get(index).color = color;
        sort();
        refresh();
    }


    public void setProgressText(int resId) {
        setProgressText(getResources().getString(resId));
    }
    public void setProgressText(String text) {
        if (progress.size() == 0)
            this.progress.add(
                    new Progress(text)
            );
        else {
            this.progress.get(0).text = text;
            progress = progress.subList(0, 1);
        }
        refresh();
    }

    public void setProgressTexts(int... resIds) {
        for (int i = 0; i < progress.size(); i++) {
            progress.get(i).text = getResources().getString(resIds[i]);
        }
        for (int i = progress.size(); i < resIds.length; i++) {
            progress.add(new Progress(getResources().getString(resIds[i])));
        }

        refresh();
    }

    public void setProgressTexts(String... texts) {
        for (int i = 0; i < progress.size(); i++) {
            progress.get(i).text = texts[i];
        }
        for (int i = progress.size(); i < texts.length; i++) {
            progress.add(new Progress(texts[i]));
        }

        refresh();
    }

    public void setProgressText(int index, int resId) {
        setProgressText(index, getResources().getString(resId));
    }
    public void setProgressText(int index, String text) {
        if (index < 0 || index >= progress.size())
            return;
        progress.get(index).text = text;
        refresh();
    }

    public void setProgressValueAndText(int index, float progressValue, int resId) {
        setProgressValueAndText(index, progressValue, resId);
    }
    public void setProgressValueAndText(int index, float progressValue, String text) {
        if (index < 0 || index >= progress.size())
            return;
        progress.get(index).value = progressValue;
        progress.get(index).text = text;
        sort();
        refresh();
    }



    public void setProgressIcon(int resId) {
        setProgressIcon(getResources().getDrawable(resId));
    }

    public void setProgressIcon(Drawable drawable) {
        if (progress.size() == 0)
            this.progress.add(
                    new Progress(drawable)
            );
        else {
            this.progress.get(0).drawable = drawable;
            progress = progress.subList(0, 1);
        }
        refresh();
    }

    public void setProgressIcons(Drawable... drawables) {
        for (int i = 0; i < progress.size(); i++) {
            progress.get(i).drawable = drawables[i];
        }
        for (int i = progress.size(); i < drawables.length; i++) {
            progress.add(new Progress(drawables[i]));
        }

        refresh();
    }

    public void setProgressIcons(int... resIds) {
        for (int i = 0; i < progress.size(); i++) {
            progress.get(i).drawable = getResources().getDrawable(resIds[i]);
        }
        for (int i = progress.size(); i < resIds.length; i++) {
            progress.add(new Progress(getResources().getDrawable(resIds[i])));
        }

        refresh();
    }

    public void setProgressIcon(int index, int resId) {
        setProgressIcon(index, getResources().getDrawable(resId));
    }

    public void setProgressIcon(int index, Drawable drawable) {
        if (index < 0 || index >= progress.size())
            return;
        progress.get(index).drawable = drawable;
        refresh();
    }

    private void sort() {
        sortProgress.clear();
        sortProgress.addAll(this.progress);
        sort(sortProgress);
        if (sortProgress.size() > 0)
            if (sortProgress.get(sortProgress.size() - 1).value > max) {
                max = sortProgress.get(sortProgress.size() - 1).value;
                setWeightSum(max);
            }
    }

    private void sort(List<Progress> progress) {
        Collections.sort(progress, new Comparator<Progress>() {
            @Override
            public int compare(Progress lhs, Progress rhs) {
                return lhs.value < rhs.value ? -1 : (lhs.value < rhs.value) ? 1 : 0;
            }
        });
    }

    @Override
    public void setGravity(int gravity) {
        super.setGravity(gravity);

        this.gravity = gravity;
        refresh();
    }

    public void withAnimation(final long duration) {
        if (progressLayouts == null)
            return;


        post(new Runnable() {
                 @Override
                 public void run() {
                     ObjectAnimator[] animators = new ObjectAnimator[progressLayouts.size() * 2];
                     int i = 0;
                     for (LinearLayout layout : progressLayouts) {
                         int start;
                         if (isRightAlign())
                             start = layout.getWidth() / 2;
                         else
                             start = -layout.getWidth() / 2;
                         animators[i++] =
                                 ObjectAnimator.ofFloat(
                                         layout, "translationX",
                                         start, 0);
                         animators[i++] = ObjectAnimator.ofFloat(layout, "scaleX", 0, 1f);
                     }

                     if (animators.length == 0)
                         return;
                     final AnimatorSet set = new AnimatorSet();
                     set.playTogether(animators);
                     set.setDuration(duration).start();
                 }
             }
        );

    }

    private boolean isRightAlign() {
        return Gravity.isHorizontal(gravity) &&
                (gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.RIGHT;
    }

    private GradientDrawable createGradientDrawableWithCorner(LinearLayout layout, int color) {
        GradientDrawable backgroundDrawable = createGradientDrawable(color);
        int newRadius = radius;
        backgroundDrawable.setCornerRadii(new float[]{newRadius, newRadius, newRadius, newRadius, newRadius, newRadius, newRadius, newRadius});

//        backgroundDrawable.setCornerRadii(new float[]{0, 0, newRadius, newRadius, newRadius, newRadius, 0, 0});

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            layout.setBackground(backgroundDrawable);
        } else {
            layout.setBackgroundDrawable(backgroundDrawable);
        }
        return backgroundDrawable;
    }

    // Create an empty color rectangle gradient drawable
    protected GradientDrawable createGradientDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(color);

        return gradientDrawable;
    }

    public void setProgressTextStyle(int progressTextStyle) {
        this.textStyle = progressTextStyle;

        refresh();
    }

    public void setProgressTextAppearance(int progressTextAppearance) {
        this.textAppearance = progressTextAppearance;
    }

    public void setProgressIconPadding(int progressIconPadding) {
        this.iconPadding = progressIconPadding;
    }

    private class Progress {
        float value;
        int color;
        String text;
        Drawable drawable;

        public Progress(float progressValue) {
            this.value = progressValue;
            this.color = Color.rgb((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random()));
        }

        public Progress(int color) {
            this.color = color;
        }

        public Progress(float progressValue, int color) {
            this.value = progressValue;
            this.color = color;
        }

        public Progress(String text) {
            this.text = text;
        }

        public Progress(Drawable drawable) {
            this.drawable = drawable;
        }
    }

}
