package com.app.infideap.stylishwidget.view;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Created by Zariman on 7/4/2016.
 */
public class MessageBoxDialog {



    public static class Builder {
        private final Context context;
        private String message;
        private String text;
        private View.OnClickListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(int message) {
            return setMessage(context.getResources().getString(message));
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setActionButton(int text, View.OnClickListener listener) {
            return setActionButton(context.getResources().getString(text), listener);
        }

        public Builder setActionButton(String text, View.OnClickListener listener) {
            this.text = text;
            this.listener = listener;

            return this;
        }

        public Builder setCloseButton(View.OnClickListener listener) {
            this.text = null;
            this.listener = listener;

            return this;
        }

        public BottomSheetDialog create() {
            final MessageBox messageBox = new MessageBox(context);

            messageBox.setMessage(message);

            final BottomSheetDialog dialog = new BottomSheetDialog(context);
            dialog.setContentView(messageBox);

            if (listener==null){
                listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.hide();
                    }
                };
            }
            if (text == null)
                messageBox.setCloseButton(listener);
            else
                messageBox.setActionButton(text, listener);


            return dialog;
        }
    }
}


