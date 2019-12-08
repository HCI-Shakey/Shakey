package com.hci.shakey;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

public class DialActivity extends AppCompatActivity{
    boolean shaking = true;
    private Context mContext = null;
    public View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        shaking = false;
        mContext = this;
        view = findViewById(R.id.textView3);
    }
}
