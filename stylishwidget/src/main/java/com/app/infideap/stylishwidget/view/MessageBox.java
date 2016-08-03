package com.app.infideap.stylishwidget.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.infideap.stylishwidget.R;

/**
 * Created by Zariman on 7/4/2016.
 */
public class MessageBox extends FrameLayout {
    private static final String TAG = MessageBox.class.getSimpleName();
    private ATextView textView;
    private ImageView imageView;
    private Button button;

    public MessageBox(Context context) {
        super(context);
        init(null);
    }

    public MessageBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MessageBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MessageBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);

    }

    public void init(AttributeSet attrs) {

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.messageBox);
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(getContext())
                .inflate(R.layout.layout_messagebox, null);

        int background;
        try {
            background = a.getColor(
                    R.styleable.messageBox_sw_boxBackground,
                    getResources().getColor(R.color.colorAccent)
            );
        } catch (Exception e) {
            background = getResources().getColor(R.color.colorAccent);
        }
        setBackgroundColor(background);
        String message;

        try {
            message = a.getString(R.styleable.messageBox_sw_message);
        } catch (Exception e) {
            message = "";
        }
        int textStyle;

        try {
            textStyle = a.getInt(R.styleable.messageBox_sw_textStyle, Typeface.NORMAL);
        } catch (Exception e) {
            textStyle = Typeface.NORMAL;
        }
        int padding, topPadding, bottomPadding, leftPadding, rightPadding;

        try {
            padding = a.getDimensionPixelSize(R.styleable.messageBox_sw_innerPadding, layout.getPaddingBottom());
        } catch (Exception e) {
            padding = layout.getPaddingBottom();
        }
        try {
            leftPadding = a.getDimensionPixelSize(R.styleable.messageBox_sw_innerLeftPadding, padding);
        } catch (Exception e) {
            leftPadding = padding;
        }
        try {
            rightPadding = a.getDimensionPixelSize(R.styleable.messageBox_sw_innerRightPadding, padding);
        } catch (Exception e) {
            rightPadding = padding;
        }
        try {
            topPadding = a.getDimensionPixelSize(R.styleable.messageBox_sw_innerTopPadding, padding);
        } catch (Exception e) {
            topPadding = padding;
        }
        try {
            bottomPadding = a.getDimensionPixelSize(R.styleable.messageBox_sw_innerBottomPadding, padding);
        } catch (Exception e) {
            bottomPadding = padding;
        }

        int drawablePadding;
        try {
            drawablePadding = a.getDimensionPixelSize(R.styleable.messageBox_sw_innerBottomPadding, padding);
        } catch (Exception e) {
            drawablePadding = padding;
        }

        Drawable drawable;
        try {
            drawable = a.getDrawable(R.styleable.messageBox_sw_drawable);
        } catch (Exception e) {
            drawable = null;
        }

        a.recycle();
        layout.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

        try {
            textView = (ATextView) layout.findViewById(R.id.textView_message);

            textView.setText(message);
            textView.setTextStyle(textStyle);
            if (drawable != null) {
                textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                textView.setCompoundDrawablePadding(drawablePadding);
            }
        } catch (Exception e) {

        }
        button = (Button) layout.findViewById(R.id.button_action);
        imageView = (ImageView) layout.findViewById(R.id.imageView_close);

        addView(layout);

        setCloseButton(new OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


    }


    public void setMessage(int resId) {
        textView.setText(resId);
    }

    public void setMessage(String message) {
        textView.setText(message);
    }

    public void setCloseButton(final OnClickListener listener) {

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                setVisibility(GONE);
            }
        });
        imageView.setVisibility(VISIBLE);
        button.setVisibility(GONE);
    }

    public void setActionButton(int text, OnClickListener listener) {
        setActionButton(getResources().getString(text), listener);
    }

    public void setActionButton(String text, OnClickListener listener) {

        button.setText(text);
        button.setOnClickListener(listener);
        imageView.setVisibility(GONE);
        button.setVisibility(VISIBLE);
    }


}


