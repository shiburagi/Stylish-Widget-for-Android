package com.app.infideap.mystylishexample;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.app.infideap.stylishwidget.view.Stylish;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initial this before setContentView or declare in onCreate() of Custom Application
        String fontFolder = "Exo_2/Exo2-";
        Stylish.getInstance().set(
                fontFolder.concat("Regular.ttf"),
                fontFolder.concat("Bold.ttf"),
                fontFolder.concat("Italic.ttf"),
                fontFolder.concat("BoldItalic.ttf"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabLayout();
        initViewPager();
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        final Fragment[] fragments = new Fragment[]{
                MeterFragment.newInstance(),
                WidgetFragment.newInstance(),
                ButtonPlainFragment.newInstance(),
                ButtonFillFragment.newInstance(),
                ButtonOutlineFragment.newInstance(),
                MessageBoxFragment.newInstance(),
                ProgressBarFragment.newInstance(),
        };
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position<fragments.length)
                    return fragments[position];
                else
                    return new Fragment();
            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        assert tabLayout != null;

        TabLayout.Tab[] tabs = {
                tabLayout.newTab().setText(R.string.meter),
                tabLayout.newTab().setText(R.string.widget),
                tabLayout.newTab().setText(R.string.plainbutton),
                tabLayout.newTab().setText(R.string.fillbutton),
                tabLayout.newTab().setText(R.string.outlinebutton),
                tabLayout.newTab().setText(R.string.messagebox),
                tabLayout.newTab().setText(R.string.progressbar),
        };

        for (TabLayout.Tab tab : tabs)
            tabLayout.addTab(tab);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
