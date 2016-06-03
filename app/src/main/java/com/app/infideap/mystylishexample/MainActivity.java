package com.app.infideap.mystylishexample;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.infideap.stylishwidget.view.MessageBox;
import com.app.infideap.stylishwidget.view.Stylish;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initial this before setContentView or declare in onCreate() of Custom Application
        String fontFolder = "rajdhani/";
        Stylish.getInstance().set(
                fontFolder.concat("Rajdhani-Regular.ttf"),
                fontFolder.concat("Rajdhani-Bold.ttf"),
                fontFolder.concat("Rajdhani-Light.ttf"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initMessageBox();
    }

    private void initMessageBox() {
        final MessageBox infoMessageBox = (MessageBox) findViewById(R.id.message_info);
        assert infoMessageBox != null;
        infoMessageBox.setCloseButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Close Button Click!").create();
                dialog.show();

                infoMessageBox.setVisibility(View.GONE);
            }
        });
        MessageBox warningMessageBox = (MessageBox) findViewById(R.id.message_warning);
        assert warningMessageBox != null;
        warningMessageBox.setActionButton(R.string.learnmore, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Warning Action Click!").create();
                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
