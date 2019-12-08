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
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //添加返回按钮
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setUpButtons();
    }

    private void setUpButtons() {
        Button buttoninit0 = findViewById(R.id.button_init0);
        Button buttoninit1 = findViewById(R.id.button_init1);
        Button buttoninit2 = findViewById(R.id.button_init2);
        Button buttoninit3 = findViewById(R.id.button_init3);
        Button buttoninit4 = findViewById(R.id.button_init4);
        Button buttoninit5 = findViewById(R.id.button_init5);
        Button buttoninit6 = findViewById(R.id.button_init6);
        /*prepare for use
        buttonShakey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, ShakeySettingsActivity.class);
                startActivity(intent);
            }
        });
        buttonWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, WechatActivity.class);
                startActivity(intent);
            }
        });
        buttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
        buttonAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, AlipayActivity.class);
                startActivity(intent);
            }
        });
        buttonDidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, DidiActivity.class);
                startActivity(intent);
            }
        });
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        buttonLockScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, LockScreenActivity.class);
                startActivity(intent);
            }
        });*/
    }
    @Override
    protected void onPause() {
        //资源释放
        super.onPause();
    }
}
