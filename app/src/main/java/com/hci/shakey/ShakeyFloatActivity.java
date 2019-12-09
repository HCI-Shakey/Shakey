package com.hci.shakey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class ShakeyFloatActivity extends AppCompatActivity implements View.OnClickListener {

    private String str = "init";
    View view;
    public String init1,init2,init3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(GlobalIdentifiers.Shakey_float == true) {
            finish();
        }
        GlobalIdentifiers.Shakey_float = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakey_float);
        Intent intent_reci = getIntent();
        str = intent_reci.getStringExtra("Environment");
        view =(TextView) findViewById(R.id.textViewfloat);
        switch (str){
            case "WechatActivity":
                init1 = "wechat";
                init2 = "wechat";
                init3 = "wechat";
                break;
            case "AlipayActivity":
                init1 = "alipaychat";
                init2 = "alipaychat";
                init3 = "alipaychat";
                break;
            case "DidiActivity":
                init1 = "didichat";
                init2 = "didichat";
                init3 = "didihat";
                break;
            case "MapActivity":
                init1 = "mapchat";
                init2 = "mapchat";
                init3 = "mapchat";
                break;
            case "MusicActivity":
                init1 = "musicchat";
                init2 = "musicchat";
                init3 = "musicchat";
                break;
            default:
                break;
        }
        Button button1 = (Button) findViewById(R.id.button_i1);
        ((Button)button1).setText(init1);
        Button button2 = (Button) findViewById(R.id.button_i2);
        Log.v(init2,"I'm here");
        ((Button)button2).setText(init2);
        Button button3 = (Button) findViewById(R.id.button_i3);
        ((Button)button3).setText(init3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_i1 :
                //todo:
                GlobalIdentifiers.Shakey_float = false;
                finish();
                break;
            case R.id.button_i2:
                //todo:
                GlobalIdentifiers.Shakey_float = false;
                finish();
                break;
            case R.id.button_i3:
                //todo:
                GlobalIdentifiers.Shakey_float = false;
                finish();
                break;
            default:
                break;
        }
    }
}