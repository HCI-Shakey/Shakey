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

public class ShakeyFloatActivity extends AppCompatActivity {

    private String str = "init";
    View view;
    private Context mContext = null;
    public String init1,init2,init3;
    AppCompatActivity a1 = this;
    boolean mark = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakey_float);
        Intent intent_reci = getIntent();
        str = intent_reci.getStringExtra("Environment");
        view =(TextView) findViewById(R.id.textViewfloat);
        mContext = this;
        switch (str){
            case "WechatActivity":
                init1 = "wechat";
                init2 = "wechat";
                init3 = "wechat";
                break;
            case "AlipayActivity":
                init1 = "wechat";
                init2 = "wechat";
                init3 = "wechat";
                break;
            case "DidiActivity":
                init1 = "wechat";
                init2 = "wechat";
                init3 = "wechat";
                break;
            case "MapActivity":
                init1 = "wechat";
                init2 = "wechat";
                init3 = "wechat";
                break;
            case "MusicActivity":
                init1 = "wechat";
                init2 = "wechat";
                init3 = "wechat";
                break;
            default:
                break;
        }
    }

    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            // Activity失去焦点时不获取 update by bianmaoran on v2.3.1
            return;
        }
        if(mark == true) {
            a1.finish();
            return;
        }
        Log.v(init1,"I'm miaomiaomiao");
        mark = true;
        showPopupWindow(view);
    }
    private void showPopupWindow(View view) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_window, null);
        Button button = (Button) contentView.findViewById(R.id.button1);
        ((Button)button).setText(init1);
        Button button2 = (Button) contentView.findViewById(R.id.button2);
        Log.v(init2,"I'm here");
        ((Button)button2).setText(init2);
        Button button3 = (Button) contentView.findViewById(R.id.button3);
        ((Button)button3).setText(init3);
        TextView textView1 = (TextView) contentView.findViewById(R.id.textView);
        textView1.setText("to do");
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "button is pressed",Toast.LENGTH_SHORT).show();
                //todo
                mark = true;
                popupWindow.dismiss();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "button_init2 is pressed",Toast.LENGTH_SHORT).show();
                //todo
                mark = true;
                popupWindow.dismiss();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "button_init3 is pressed",Toast.LENGTH_SHORT).show();
                //todo
                mark = true;
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view);
    }
}