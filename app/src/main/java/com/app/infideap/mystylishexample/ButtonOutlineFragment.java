package com.app.infideap.mystylishexample;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ButtonOutlineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonOutlineFragment extends Fragment {

    public ButtonOutlineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WidgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ButtonOutlineFragment newInstance() {
        return new ButtonOutlineFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_button_outline, container, false);
    }

}
