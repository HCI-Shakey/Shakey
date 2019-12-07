package com.hci.shakey;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class OSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpButtons();
    }

    private void setUpButtons() {
        Button buttonShakey = findViewById(R.id.button_shakey);
        Button buttonWechat = findViewById(R.id.button_wechat);
        Button buttonMusic = findViewById(R.id.button_music);
        Button buttonAlipay = findViewById(R.id.button_alipay);
        Button buttonDidi = findViewById(R.id.button_didi);
        Button buttonMap = findViewById(R.id.button_map);

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
    }
}
