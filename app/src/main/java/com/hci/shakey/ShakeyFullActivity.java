package com.hci.shakey;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class ShakeyFullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakey_full);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button buttoninit0 = findViewById(R.id.button_init0);
        Button buttoninit1 = findViewById(R.id.button_init1);
        Button buttoninit2 = findViewById(R.id.button_init2);
        Button buttoninit3 = findViewById(R.id.button_init3);
        Button buttoninit4 = findViewById(R.id.button_init4);
        buttoninit0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShakeyFullActivity.this, WechatActivity.class);
                startActivity(intent);
            }
        });
        buttoninit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShakeyFullActivity.this, AlipayActivity.class);
                startActivity(intent);
            }
        });
        buttoninit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShakeyFullActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
        buttoninit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShakeyFullActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        buttoninit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShakeyFullActivity.this, DidiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpButtons() {
        Button buttoninit0 = findViewById(R.id.button_init0);
        Button buttoninit1 = findViewById(R.id.button_init1);
        Button buttoninit2 = findViewById(R.id.button_init2);
        Button buttoninit3 = findViewById(R.id.button_init3);
        Button buttoninit4 = findViewById(R.id.button_init4);
    }

    @Override
    protected void onPause() {
        //资源释放
        super.onPause();
    }
}
