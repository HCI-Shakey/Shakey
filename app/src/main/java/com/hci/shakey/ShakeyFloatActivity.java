package com.hci.shakey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class ShakeyFloatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakey_float);
        Intent intent_reci = getIntent();
        String str = intent_reci.getStringExtra("Environment");
        switch (str){
            case "WechatActivity":
                Intent intent_get = new Intent();
                intent_get.putExtra("init1", "wechat1");
                intent_get.putExtra("init2", "wechat2");
                intent_get.putExtra("init3", "wechat3");
                setResult(GlobalIdentifiers.Wechat_reci, intent_get);
                break;
            case "AlipayActivity":
                Intent intent_Alipay = new Intent();
                intent_Alipay.putExtra("init1", "Alipay1");
                intent_Alipay.putExtra("init2", "Alipay2");
                intent_Alipay.putExtra("init3", "Alipay3");
                setResult(GlobalIdentifiers.Alipay_reci, intent_Alipay);
                break;
            case "DidiActivity":
                Intent intent_Didi = new Intent();
                intent_Didi.putExtra("init1", "Didi1");
                intent_Didi.putExtra("init2", "Didi2");
                intent_Didi.putExtra("init3", "Didi3");
                setResult(GlobalIdentifiers.Alipay_reci, intent_Didi);
                break;
            case "MapActivity":
                Intent intent_Map = new Intent();
                intent_Map.putExtra("init1", "Map1");
                intent_Map.putExtra("init2", "Map2");
                intent_Map.putExtra("init3", "Map3");
                setResult(GlobalIdentifiers.Alipay_reci, intent_Map);
                break;
            case "MusicActivity":
                Intent intent_Music = new Intent();
                intent_Music.putExtra("init1", "Music1");
                intent_Music.putExtra("init2", "Music2");
                intent_Music.putExtra("init3", "Music3");
                setResult(GlobalIdentifiers.Alipay_reci, intent_Music);
                break;
            default:
                break;
        }
        finish();
    }

    /*@Override
    public void onBackPressed() {
        //        super.onBackPressed();//不能够有该行代码，否则返回崩溃
        Intent intent = new Intent();
        intent.putExtra("init1", "wechat11");
        intent.putExtra("init2", "wechat22");
        setResult(3, intent);
        finish();
    }*/
}