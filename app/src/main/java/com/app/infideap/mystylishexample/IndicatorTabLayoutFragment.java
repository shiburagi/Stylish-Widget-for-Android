package com.app.infideap.mystylishexample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.infideap.stylishwidget.view.IndicatorTabLayout;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IndicatorTabLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndicatorTabLayoutFragment extends Fragment {

    public IndicatorTabLayoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WidgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndicatorTabLayoutFragment newInstance() {
        return new IndicatorTabLayoutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.layout_indicator_tablayout, container, false);

        initTabLayout(rootView);

        return rootView;
    }

    private void initTabLayout(View view) {
        final IndicatorTabLayout tabLayout = (IndicatorTabLayout) view.findViewById(R.id.tabLayout_indicator);

        TabLayout.Tab[] tabs = {
                tabLayout.newTab().setText(R.string.calls).setIcon(R.drawable.ic_clear_white_24dp),
                tabLayout.newTab().setText("asdasdsd asdas asdasads sdasds"),
                tabLayout.newTab().setText(R.string.contacts),
        };

        for (TabLayout.Tab tab : tabs) {
            tabLayout.addTab(tab);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        tabLayout.getIndicatorTabAt(1).setIndicatorText("200");
    }

}
