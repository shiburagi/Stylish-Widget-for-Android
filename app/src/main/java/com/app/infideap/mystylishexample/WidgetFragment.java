package com.app.infideap.mystylishexample;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.infideap.stylishwidget.util.TextViewUtils;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WidgetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WidgetFragment extends Fragment {

    private TextView incrementTextView;
    private TextView decrementTextView;
    private Thread incrementThread;
    private Thread decrementTread;

    public WidgetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WidgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WidgetFragment newInstance() {
        return new WidgetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_widget, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        incrementTextView = (TextView) view.findViewById(R.id.textView_increment);
        decrementTextView = (TextView) view.findViewById(R.id.textView_decrement);

        startCounter();

    }

    private void startCounter() {

        if (incrementThread != null) {
            incrementThread.interrupt();
        }
        if (decrementTread != null) {
            decrementTread.interrupt();
        }

        TextViewUtils utils = TextViewUtils.getInstance();
        //Increment
        incrementThread = utils.printIncrement(incrementTextView, "$%,d", 132, 500);
        //Decrement
        decrementTread = utils.printIncrement(decrementTextView, "%,dpt", 132, 0, 500);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && incrementTextView != null && decrementTextView != null) {
            startCounter();
        }
    }
}
