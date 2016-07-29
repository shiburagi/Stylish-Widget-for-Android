package com.app.infideap.mystylishexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.infideap.stylishwidget.view.MessageBox;
import com.app.infideap.stylishwidget.view.MessageBoxDialog;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageBoxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageBoxFragment extends Fragment {

    public MessageBoxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WidgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageBoxFragment newInstance() {
        return new MessageBoxFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.layout_message_box, container, false);

        initMessageBox(rootView);

        return rootView;
    }

    private void initMessageBox(View view) {
        final MessageBox infoMessageBox = (MessageBox) view.findViewById(R.id.message_info);
        assert infoMessageBox != null;
        infoMessageBox.setCloseButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setMessage("Close Button Click!").create();
                dialog.show();

                infoMessageBox.setVisibility(View.GONE);
            }
        });
        MessageBox warningMessageBox = (MessageBox) view.findViewById(R.id.message_warning);
        assert warningMessageBox != null;
        warningMessageBox.setActionButton(R.string.learnmore, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setMessage("Warning Action Click!").create();
                dialog.show();
            }
        });

        Button button = (Button) view.findViewById(R.id.button_bottomsheetmessagebox);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageBoxDialog.Builder builder = new
                        MessageBoxDialog.Builder(getContext())
                        .setMessage(R.string.bottomsheetmessagebox);
                builder.create().show();
            }
        });

    }

}
