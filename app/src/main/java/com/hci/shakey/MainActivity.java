package com.hci.shakey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.hci.shakey.support.LocalDataBase;
import com.iflytek.cloud.SpeechUtility;

import com.hci.shakey.fragments.VoiceAssistantFragment;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements VoiceAssistantFragment.OnFragmentInteractionListener,VoiceAssistantFragment.CallBackValue{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    VoiceAssistantFragment vaf;

    private Timer mTimer; // 按钮被点击，声音命令中有输入的时候，计时器会被取消

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalDataBase.initialize(this);
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

//        // 开启计时任务
//        TimerTask timerTask = new TimerTask(){
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //Toast.makeText(MainActivity.this, "1s后延时任务执行了", Toast.LENGTH_SHORT).show();
//                        // TODO: 执行默认动作
//                    }
//                });
//            }
//        };
//        // 计时器等待一秒钟
//        mTimer.schedule(timerTask,1000);

        LocalDataBase.addAction("微信","扫码");
        LocalDataBase.addAction("微信", "发起群聊");
        LocalDataBase.addAction("微信", "添加好友");

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

    @Override
    public void sendMessageValue(String message) {
        if (message.contentEquals("hasSoundAction")) {
            // 取消计时器
        }
    }
}
