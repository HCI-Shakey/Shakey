package com.hci.shakey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.hci.shakey.support.LocalDataBase;
import com.iflytek.cloud.SpeechUtility;

import com.hci.shakey.fragments.VoiceAssistantFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalDataBase.initialize(this);
        Log.i("mHelper1","" + LocalDataBase.mHelper);

        // 创建语音配置对象
        mscInit(null);

        LocalDataBase.addAction("WechatActivity","扫一扫");
        LocalDataBase.addAction("WechatActivity", "返回上一界面");
        LocalDataBase.addAction("WechatActivity", "添加好友");

        LocalDataBase.addAction("MusicActivity","暂停/播放");
        LocalDataBase.addAction("MusicActivity", "听歌识曲");
        LocalDataBase.addAction("MusicActivity", "获取推荐");

        LocalDataBase.addAction("MapActivity","导航上班");
        LocalDataBase.addAction("MapActivity", "导航回家");
        LocalDataBase.addAction("MapActivity", "待开发");

        LocalDataBase.addAction("DidiActivity","打车");
        LocalDataBase.addAction("DidiActivity", "钱包");
        LocalDataBase.addAction("DidiActivity", "待开发");

        LocalDataBase.addAction("AlipayActivity","扫一扫");
        LocalDataBase.addAction("AlipayActivity", "出示付款码");
        LocalDataBase.addAction("AlipayActivity", "待开发");

        LocalDataBase.addAction("LockScreenActivity","微信扫一扫");
        LocalDataBase.addAction("LockScreenActivity", "拍照");
        LocalDataBase.addAction("LockScreenActivity", "音乐");
        LocalDataBase.addAction("LockScreenActivity", "打开手电筒");
        LocalDataBase.addAction("LockScreenActivity", "打开支付宝");
        LocalDataBase.addAction("LockScreenActivity", "滴滴打车");

        LocalDataBase.addAction("OSActivity","微信扫一扫");
        LocalDataBase.addAction("OSActivity", "拍照");
        LocalDataBase.addAction("OSActivity", "音乐");
        LocalDataBase.addAction("OSActivity", "打开手电筒");
        LocalDataBase.addAction("OSActivity", "打开支付宝");
        LocalDataBase.addAction("OSActivity", "滴滴打车");


        Button buttonEnter = findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OSActivity.class);
                startActivity(intent);
            }
        });

    }

    private void mscInit (String serverUrl){
        StringBuffer bf = new StringBuffer();
        bf.append("appid="+getString(R.string.app_id));
        bf.append(",");
        if (!TextUtils.isEmpty(serverUrl)) {
            bf.append("server_url="+serverUrl);
            bf.append(",");
        }
        SpeechUtility.createUtility(this.getApplicationContext(), bf.toString());
    }

}
