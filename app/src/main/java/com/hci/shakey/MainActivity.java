package com.hci.shakey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.iflytek.cloud.SpeechUtility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建语音配置对象
        mscInit(null);
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
