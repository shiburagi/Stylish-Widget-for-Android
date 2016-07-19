package com.app.infideap.stylishwidget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.infideap.stylishwidget.Log;

import java.util.ArrayList;

/**
 * Created by Shiburagi on 23/06/2016.
 */
public class ARadioGroup extends RadioGroup {
    private static final String TAG = ARadioGroup.class.getSimpleName();
    private ArrayList<RadioButton> radioButtons;
    private OnCheckedChangeListener listener;
    private RadioButton selected;
    private int selectedId;
    private boolean trigger;

    public ARadioGroup(Context context) {
        super(context);
        init(context, null);
    }

    public ARadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        radioButtons = new ArrayList<>();

        search(context, this);

        Log.d(TAG, "Size : " + radioButtons.size() + ", " + getChildCount());
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        Log.d(TAG, "addView");
        search(getContext(), child);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(TAG, "onLayout() : " + selected);
        trigger = false;
        if (selected != null) {
            selected.setChecked(true);
//            radioButtons.get(selectedId).setChecked(true);
        }
        trigger = true;
    }

    private void search(Context context, View view) {
        if (view instanceof RadioButton) {
            final RadioButton radioButton = (RadioButton) view;
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        clearCheck(radioButton);
                        selected = radioButton;
                        if (trigger)
                            listener.onCheckedChanged(ARadioGroup.this, buttonView.getId());
                    }
                }
            });
            radioButtons.add(radioButton);
        } else if (view instanceof ViewGroup) {
            search(context, (ViewGroup) view);
        }
    }

    private void search(Context context, ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            search(context, view);
        }
    }

    private void clearCheck(RadioButton radioButton) {
        for (RadioButton _radioButton : radioButtons) {
            if (radioButton != _radioButton)
                _radioButton.setChecked(false);
        }
    }


    @Override
    public void clearCheck() {
        for (RadioButton radioButton : radioButtons) {
            radioButton.setChecked(false);
        }
    }

    @Override
    public void setOnCheckedChangeListener(final OnCheckedChangeListener listener) {
        this.listener = listener;
        super.setOnCheckedChangeListener(listener);
    }

    public RadioButton getSelected() {
        return selected;
    }
}
