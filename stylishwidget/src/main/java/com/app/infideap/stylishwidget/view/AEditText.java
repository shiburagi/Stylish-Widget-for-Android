package com.app.infideap.stylishwidget.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.app.infideap.stylishwidget.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by Zariman on 13/4/2016.
 */
public class AEditText extends AppCompatEditText {
    private Calendar calendar;
    private boolean is24Hours;



    private PickerDialog datePickerDialog = new PickerDialog() {
        private DatePickerDialog datePicker;
        @Override
        public void createDialog(final boolean isDateTime) {
            datePicker = new DatePickerDialog(
                    getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);
                            if (isDateTime)
                                timePickerDialog.showDialog();
                            else
                                displayDate();
                        }
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
        }

        @Override
        public void showDialog() {
            datePicker.show();
        }
    };

    private PickerDialog timePickerDialog = new PickerDialog() {
        private TimePickerDialog timePicker;
        @Override
        public void createDialog(final boolean isDateTime) {
            timePicker = new TimePickerDialog(
                    getContext(),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            calendar.set(Calendar.MINUTE, minute);
                            if (isDateTime)
                                displayDateTime();
                            else
                                displayTime();
                        }
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    is24Hours
            );
        }

        @Override
        public void showDialog() {
            timePicker.show();
        }
    };

    public AEditText(Context context) {
        super(context);
        setCustomTypeface(context, null);

    }

    public AEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomTypeface(context, attrs);


    }

    public AEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomTypeface(context, attrs);

    }

    @SuppressLint({"PrivateResource", "CustomViewStyleable"})
    private void setCustomTypeface(Context context, AttributeSet attrs) {
        is24Hours = android.text.format.DateFormat.is24HourFormat(getContext());
        if (isInEditMode())
            return;
        TypedArray a = context.obtainStyledAttributes(attrs, android.support.v7.appcompat.R.styleable.TextAppearance);
        int style = a.getInt(android.support.v7.appcompat.R.styleable.TextAppearance_android_textStyle, Typeface.NORMAL);

        setTextStyle(style);
        a.recycle();

//        a = context.obtainStyledAttributes(attrs, android.support.v7.appcompat.R.styleable.AppCompatTextView);
//
//        int inputType = a.getInt(android.support.v7.appcompat.R.styleable.Inpu, Typeface.NORMAL);
//
//        setInputType(inputType);
//        a.recycle();

    }

    public void setTextStyle(int style) {
        Stylish.getInstance().setTextStyle(this, style);

    }

    public void setSupportTextAppearance(int resId) {
        if (Build.VERSION.SDK_INT >= 23)
            this.setTextAppearance(resId);
        else
            this.setTextAppearance(getContext(), resId);
    }


    @Override
    public void setRawInputType(int type) {
        Log.e(this.getClass().getSimpleName(), "Type : "+type);
        switch (type) {
            case InputType.TYPE_DATETIME_VARIATION_TIME|InputType.TYPE_CLASS_DATETIME:
                setupTimePicker();
                break;
            case InputType.TYPE_DATETIME_VARIATION_DATE|InputType.TYPE_CLASS_DATETIME:
                setupDatePicker();
                break;
            case InputType.TYPE_CLASS_DATETIME:
                setupDateTimePicker();
                break;
            default:
                calendar = null;
                super.setRawInputType(type);
        }
    }

    private void setupDateTimePicker() {
        calendar = Calendar.getInstance();
        disableInput();
        datePickerDialog.createDialog(false);
        timePickerDialog.createDialog(false);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setError(null);
                datePickerDialog.showDialog();
                hideKeyboard(view);
            }
        });
        this.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) performClick();
            }
        });
    }


    private void setupDatePicker() {
        calendar = Calendar.getInstance();
        disableInput();
        datePickerDialog.createDialog(false);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setError(null);
                datePickerDialog.showDialog();
                hideKeyboard(view);
            }
        });
        this.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) performClick();
            }
        });
    }


    private void setupTimePicker() {
        calendar = Calendar.getInstance();
        disableInput();
        timePickerDialog.createDialog(false);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setError(null);
                timePickerDialog.showDialog();
                hideKeyboard(view);
            }
        });
        this.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) performClick();
            }
        });
    }


    public void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }




    private void disableInput() {
    }


    public Calendar getCalendar() {
        return calendar;
    }



    public abstract class PickerDialog{

        private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        private DateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        @SuppressLint("SetTextI18n")
        void displayDateTime() {
            setText(dateFormat.format(calendar.getTime()) + " " + timeFormat.format(calendar.getTime()));
        }

        void displayDate() {
            setText(dateFormat.format(calendar.getTime()));
        }


        void displayTime() {
            setText(timeFormat.format(calendar.getTime()));
        }
        public void setDateFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        public void setTimeFormat(DateFormat timeFormat) {
            this.timeFormat = timeFormat;
        }

        public abstract void createDialog(boolean isDateTime);
        public abstract void showDialog();
    }
}
