package com.app.infideap.stylishwidget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.infideap.stylishwidget.R;

/**
 * Created by Shiburagi on 26/09/2016.
 */

public class IndicatorTabLayout extends TabLayout {

    public IndicatorTabLayout(Context context) {
        super(context);
    }

    public IndicatorTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndicatorTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void addOnTabSelectedListener(@NonNull final OnTabSelectedListener listener) {
        super.addOnTabSelectedListener(listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    public void addTab(@NonNull Tab tab, int position, boolean setSelected) {
        IndicatorTab indicatorTab = new IndicatorTab(getContext(), tab, this);
        super.addTab(indicatorTab.tab, position, setSelected);
    }

    public void setIndicatorTextColor(int color) {
        for (int i = 0; i < getTabCount(); i++) {
            parse(getTabAt(i)).countTextView.setTextColor(color);
        }
    }

    public IndicatorTab getIndicatorTabAt(int index) {
        return parse(getTabAt(index));

    }

    private IndicatorTab parse(Tab tab) {
        return IndicatorTab.from(tab);
    }


    public static class IndicatorTab {

        private Context context;
        private Tab tab;
        private TextView titleTextView;
        private ImageView countImageView;
        private View countLayout;
        private TextView countTextView;


        @RequiresApi(api = Build.VERSION_CODES.FROYO)
        private IndicatorTab(Context context, Tab tab, TabLayout tabLayout) {
            this.tab = tab;
            this.context = context;

            View customView = View.inflate(context, R.layout.tablayout_tab_view, null);
            init(customView);

            countLayout.setVisibility(View.GONE);
            countImageView.setColorFilter(
                    ContextCompat.getColor(context, R.color.colorWhite));

            titleTextView.setTextColor(tabLayout.getTabTextColors());

            titleTextView.setText(tab.getText());
            tab.setCustomView(customView);
        }

        private void init(View customView) {
            titleTextView = (TextView) customView.findViewById(R.id.textView_title);
            countTextView = (TextView) customView.findViewById(R.id.textView_count);
            countImageView = (ImageView) customView.findViewById(R.id.imageView_count);
            countLayout = customView.findViewById(R.id.layout_count);
        }

        private IndicatorTab() {

        }

        public IndicatorTab setText(CharSequence text) {
            tab.setText(text);
            setText();
            return this;
        }

        public IndicatorTab setText(int text) {
            tab.setText(text);
            setText();
            return this;
        }

        private void setText() {
            titleTextView.setText(tab.getText());
        }

        public IndicatorTab setIndicatorText(CharSequence text) {
            countLayout.setVisibility(VISIBLE);
            countTextView.setText(text);
            return this;
        }

        public IndicatorTab setIndicatorVisible(boolean visible) {
            countLayout.setVisibility(visible ? VISIBLE : GONE);
            return this;
        }

        public IndicatorTab setIcon(Drawable icon) {
            tab.setIcon(icon);

            return this;
        }

        public IndicatorTab setIcon(int icon) {
            tab.setIcon(icon);
            return this;
        }

        public void select() {
            tab.select();
        }


        public int getPosition() {
            return tab.getPosition();
        }

        public Tab getTab() {
            return tab;
        }

        public static IndicatorTab from(Tab tab) {
            IndicatorTab indicatorTab = new IndicatorTab();
            indicatorTab.tab = tab;
            if (tab.getCustomView() != null)
                indicatorTab.init(tab.getCustomView());

            return indicatorTab;
        }
    }
}
