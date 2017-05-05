package com.app.infideap.mystylishexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.infideap.stylishwidget.view.AMeter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeterFragment extends Fragment {

    public MeterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WidgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeterFragment newInstance() {
        return new MeterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.layout_meter, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AMeter meter = (AMeter) view.findViewById(R.id.meter);
        meter.setMaxValue(100f);
        progress(meter, 0);

        meter = (AMeter) view.findViewById(R.id.meter_notext);
        meter.setMaxValue(100);
        progress(meter, 0);

        AMeter lineMeter = (AMeter) view.findViewById(R.id.linemeter);
        lineMeter.setMaxValue(100);
        lineMeter.setStartValue(0);
        lineMeter.setValue(70);
        progress(lineMeter, 0);
    }

    private void progress(final AMeter meter, final int i) {
        if (i <= 100)
            meter.postDelayed(new Runnable() {
                @Override
                public void run() {
                    meter.setValue(i);
                    progress(meter, i + 1);
                }
            }, 100);
    }

}
