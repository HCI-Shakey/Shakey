package com.hci.shakey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.iflytek.cloud.SpeechUtility;

import com.hci.shakey.fragments.VoiceAssistantFragment;

public class MainActivity extends AppCompatActivity implements VoiceAssistantFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    VoiceAssistantFragment vaf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建语音配置对象
        mscInit(null);

        fragmentManager = getSupportFragmentManager();
        /*FragmentManager要管理fragment（添加，替换以及其他的执行动作）
19         *的一系列的事务变化，需要通过fragmentTransaction来操作执行
20          */
        fragmentTransaction = fragmentManager.beginTransaction();
        vaf = new VoiceAssistantFragment();
        fragmentTransaction.add(R.id.voice_assistant,vaf);
        fragmentTransaction.commit();
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
