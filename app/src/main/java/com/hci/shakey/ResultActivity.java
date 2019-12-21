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

public class ResultActivity extends AppCompatActivity{
    boolean shaking = true;
    SensorManager sensorManager;
    private Context mContext = null;
    public View view;
    public String init1,init2,init3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = this.getIntent();
        ImageView imv = (ImageView)findViewById(R.id.empty);
        imv.setImageResource(intent.getIntExtra("src",R.drawable.wechat));

        shaking = false;
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mContext = this;
    }
}