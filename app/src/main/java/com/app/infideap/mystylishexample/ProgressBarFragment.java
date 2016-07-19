package com.app.infideap.mystylishexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.infideap.stylishwidget.view.AProgressBar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgressBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressBarFragment extends Fragment {

    public ProgressBarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WidgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgressBarFragment newInstance() {
        return new ProgressBarFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.layout_progress_bar, container, false);

        initProgressBar(rootView);
        initReverseProgressBar(rootView);
        return rootView;
    }

    private void initProgressBar(View view) {
        AProgressBar multiProgressBar = (AProgressBar) view.findViewById(R.id.progressBar_multi);
        multiProgressBar.setProgressValues(
                30,
                50,
                90,
                70);

        multiProgressBar.setMaxValue(100);
        multiProgressBar.withAnimation(1000);

        final AProgressBar colorMultiProgressBar =
                (AProgressBar) view.findViewById(R.id.progressBar_multi_color);
        colorMultiProgressBar.setProgressValues(
                30,
                150,
                90,
                70);

        colorMultiProgressBar.setProgressColors(
                Color.parseColor("#039BE5"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FBC02D"),
                Color.parseColor("#f44336"));

        colorMultiProgressBar.setMaxValue(100);
        colorMultiProgressBar.withAnimation(1000);

        AProgressBar colorTextMultiProgressBar =
                (AProgressBar) view.findViewById(R.id.progressBar_multi_color_text);
        colorTextMultiProgressBar.setProgressValues(
                30,
                150,
                90,
                70);

        colorTextMultiProgressBar.setProgressColors(
                Color.parseColor("#039BE5"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FBC02D"),
                Color.parseColor("#f44336"));

        colorTextMultiProgressBar.setProgressTexts(
                "30%",
                "150%",
                "90%",
                "70%"
        );

        colorTextMultiProgressBar.setMaxValue(100);
        colorTextMultiProgressBar.withAnimation(1000);


        AProgressBar iconMultiProgressBar =
                (AProgressBar) view.findViewById(R.id.progressBar_multi_icon);
        iconMultiProgressBar.setProgressValues(
                30,
                150,
                90,
                70);

        iconMultiProgressBar.setProgressColors(
                Color.parseColor("#039BE5"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FBC02D"),
                Color.parseColor("#f44336"));

        iconMultiProgressBar.setProgressTexts(
                "30%",
                "150%",
                "90%",
                "70%"
        );
        iconMultiProgressBar.setProgressIcons(
            R.drawable.ic_directions_run_white_24dp,
            R.drawable.ic_directions_bike_white_24dp,
            R.drawable.ic_directions_boat_white_24dp,
            R.drawable.ic_directions_subway_white_24dp
        );

        iconMultiProgressBar.setMaxValue(100);
        iconMultiProgressBar.withAnimation(1000);
    }
    private void initReverseProgressBar(View view) {

        AProgressBar reverseMultiProgressBar = (AProgressBar) view.findViewById(R.id.progressBar_reverse_multi);
        reverseMultiProgressBar.setProgressValues(
                30,
                50,
                90,
                70
        );

        reverseMultiProgressBar.setMaxValue(100);
        reverseMultiProgressBar.withAnimation(1000);

        AProgressBar colorReverseMultiProgressBar =
                (AProgressBar) view.findViewById(R.id.progressBar_reverse_multi_color);
        colorReverseMultiProgressBar.setProgressValues(
                30,
                150,
                90,
                70
        );

        colorReverseMultiProgressBar.setProgressColors(
                Color.parseColor("#039BE5"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FBC02D"),
                Color.parseColor("#f44336")
        );

        colorReverseMultiProgressBar.setMaxValue(100);
        colorReverseMultiProgressBar.withAnimation(1000);

        AProgressBar colorTextReverseMultiProgressBar =
                (AProgressBar) view.findViewById(R.id.progressBar_reverse_multi_color_text);
        colorTextReverseMultiProgressBar.setProgressValues(
                30,
                150,
                90,
                70);

        colorTextReverseMultiProgressBar.setProgressColors(
                Color.parseColor("#039BE5"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FBC02D"),
                Color.parseColor("#f44336"));

        colorTextReverseMultiProgressBar.setProgressTexts(
                "30%",
                "150%",
                "90%",
                "70%"
        );

        colorTextReverseMultiProgressBar.setMaxValue(100);
        colorTextReverseMultiProgressBar.withAnimation(1000);

        AProgressBar iconReverseMultiProgressBar =
                (AProgressBar) view.findViewById(R.id.progressBar_reverse_multi_icon);
        iconReverseMultiProgressBar.setProgressValues(
                30,
                150,
                90,
                70);

        iconReverseMultiProgressBar.setProgressColors(
                Color.parseColor("#039BE5"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FBC02D"),
                Color.parseColor("#f44336"));

        iconReverseMultiProgressBar.setProgressTexts(
                "30%",
                "150%",
                "90%",
                "70%"
        );
        iconReverseMultiProgressBar.setProgressIcons(
                R.drawable.ic_directions_run_white_24dp,
                R.drawable.ic_directions_bike_white_24dp,
                R.drawable.ic_directions_boat_white_24dp,
                R.drawable.ic_directions_subway_white_24dp
        );

        iconReverseMultiProgressBar.setMaxValue(100);
        iconReverseMultiProgressBar.withAnimation(1000);
    }


}
