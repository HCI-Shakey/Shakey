package com.hci.shakey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.Uri;
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

import java.util.HashMap;
import java.util.List;

import com.hci.shakey.fragments.VoiceAssistantFragment;
import com.hci.shakey.support.LocalDataBase;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_RECEIVER_FOREGROUND;

public class ShakeyFloatActivity extends AppCompatActivity implements View.OnClickListener, VoiceAssistantFragment.OnFragmentInteractionListener, VoiceAssistantFragment.CallBackValue {

    private String str = "init";
    View view;
//    public HashMap<String,String> hashMap = new HashMap<>();
    public static String env;
    public String init1,init2,init3;
    private Context mContext = null;
    SensorManager sensorManager;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    VoiceAssistantFragment vaf;

    private Timer mTimer; // 按钮被点击，声音命令中有输入的时候，计时器会被取消
    private Timer mTimer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(GlobalIdentifiers.Shakey_float == true) {
            finish();
        }
        Log.i("mHelper012",""+LocalDataBase.mHelper);
        GlobalIdentifiers.Shakey_float = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakey_float);

        fragmentManager = getSupportFragmentManager();
        /*FragmentManager要管理fragment（添加，替换以及其他的执行动作）
19         *的一系列的事务变化，需要通过fragmentTransaction来操作执行
20          */
        fragmentTransaction = fragmentManager.beginTransaction();
        vaf = new VoiceAssistantFragment();
        fragmentTransaction.add(R.id.voice_assistant,vaf);
        fragmentTransaction.commit();

        Intent intent_reci = getIntent();

        env = intent_reci.getStringExtra("Environment");
        List<String> inits = LocalDataBase.getRecommends(env);
        init1 = inits.get(0);
        init2 = inits.get(1);
        init3 = inits.get(2);

        view =(TextView) findViewById(R.id.textViewfloat);
//        switch (env){
//            case "WechatActivity":
////                List<String> inits = LocalDataBase.getRecommends(env);
////                init1 = inits.get(0);
////                init2 = inits.get(1);
////                init3 = inits.get(2);
//                init1 = "wechat";
//                init2 = "wechat";
//                init3 = "wechat";
//                break;
//            case "AlipayActivity":
////                List<String> inits = LocalDataBase.getRecommends(env);
////                init1 = inits.get(0);
////                init2 = inits.get(1);
////                init3 = inits.get(2);
//                init1 = "alipaychat";
//                init2 = "alipaychat";
//                init3 = "alipaychat";
//                break;
//            case "DidiActivity":
////                List<String> inits = LocalDataBase.getRecommends(env);
////                init1 = inits.get(0);
////                init2 = inits.get(1);
////                init3 = inits.get(2);
//                init1 = "didichat";
//                init2 = "didichat";
//                init3 = "didihat";
//                break;
//            case "MapActivity":
////                List<String> inits = LocalDataBase.getRecommends(env);
////                init1 = inits.get(0);
////                init2 = inits.get(1);
////                init3 = inits.get(2);
//                init1 = "mapchat";
//                init2 = "mapchat";
//                init3 = "mapchat";
//                break;
//            case "MusicActivity":
////                List<String> inits = LocalDataBase.getRecommends(env);
////                init1 = inits.get(0);
////                init2 = inits.get(1);
////                init3 = inits.get(2);
//                init1 = "musicchat";
//                init2 = "musicchat";
//                init3 = "musicchat";
//                break;
//            default:
//                break;
//        }
        Button button1 = (Button) findViewById(R.id.button_i1);
        ((Button)button1).setText(init1);
        Button button2 = (Button) findViewById(R.id.button_i2);
        ((Button)button2).setText(init2);
        Button button3 = (Button) findViewById(R.id.button_i3);
        ((Button)button3).setText(init3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        // 开启计时任务
        TimerTask timerTask1 = new TimerTask(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this, "1s后延时任务执行了", Toast.LENGTH_SHORT).show();
                        // TODO: 执行默认动作
                    }
                });
            }
        };

        // 计时器等待1200ms, 执行默认动作
        mTimer = new Timer();
        mTimer.schedule(timerTask1,1000);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_i1 :
                //mTimer.cancel();
                switch (env) {
                    case "WechatActivity":
                        solveWeChat(init1);
                        break;
                    case "AlipayActivity":
                        solveAlipay(init1);
                        break;
                    case "DidiActivity":
                        solveDidi(init1);
                        break;
                    case "MapActivity":
                        solveGaoDe(init1);
                        break;
                    case "MusicActivity":
                        solveMusic(init1);
                        break;
                    default:
                        break;
                }
                GlobalIdentifiers.Shakey_float = false;
                finish();
                break;
            case R.id.button_i2:
                //mTimer.cancel();
                switch (env) {
                    case "WechatActivity":
                        solveWeChat(init2);
                        break;
                    case "AlipayActivity":
                        solveAlipay(init2);
                        break;
                    case "DidiActivity":
                        solveDidi(init2);
                        break;
                    case "MapActivity":
                        solveGaoDe(init2);
                        break;
                    case "MusicActivity":
                        solveMusic(init2);
                        break;
                    default:
                        break;
                }
                GlobalIdentifiers.Shakey_float = false;
                finish();
                break;
            case R.id.button_i3:
                //mTimer.cancel();
                switch (env) {
                    case "WechatActivity":
                        solveWeChat(init3);
                        break;
                    case "AlipayActivity":
                        solveAlipay(init3);
                        break;
                    case "DidiActivity":
                        solveDidi(init3);
                        break;
                    case "MapActivity":
                        solveGaoDe(init3);
                        break;
                    case "MusicActivity":
                        solveMusic(init3);
                        break;
                    default:
                        break;
                }
                GlobalIdentifiers.Shakey_float = false;
                finish();
                break;
            default:
                break;
        }
    }

    public void solveWeChat(String ope) {
        if(ope.contentEquals("扫一扫")) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
                intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
                intent.setFlags(FLAG_RECEIVER_FOREGROUND | FLAG_ACTIVITY_CLEAR_TOP);//335544320
                intent.setAction("android.intent.action.VIEW");
                startActivity(intent);
                this.finish();
            } catch (Exception e) {
            }
        } else  if(ope.contentEquals("返回上一界面")) {
            try {
                Intent intent = new Intent();
                ComponentName cmp=new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(cmp);
                startActivity(intent);
                this.finish();
            } catch (Exception e) {
            }
        } else if(ope.contentEquals("添加好友")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.wc_add);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void solveAlipay(String ope) {
        if(ope.contentEquals("扫一扫")) {
            try {
                //利用Intent打开支付宝
                //支付宝跳过开启动画打开扫码和付款码的urlscheme分别是：
                //alipayqr://platformapi/startapp?saId=10000007
                //alipayqr://platformapi/startapp?saId=20000056
                Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } catch (Exception e) {
                //若无法正常跳转，在此进行错误处理
            }
        } else  if(ope.contentEquals("出示付款码")) {
            try {
                //利用Intent打开支付宝
                //支付宝跳过开启动画打开扫码和付款码的urlscheme分别是：
                //alipayqr://platformapi/startapp?saId=10000007
                //alipayqr://platformapi/startapp?saId=20000056
                Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=20000056");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } catch (Exception e) {
                //若无法正常跳转，在此进行错误处理
            }
        } else if(ope.contentEquals("待开发")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.ali_fkm);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void solveDidi(String ope) {
        if(ope.contentEquals("打车")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.dd_dc);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else  if(ope.contentEquals("钱包")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.dd_qb);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else if(ope.contentEquals("待开发")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.dd_dc);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void solveGaoDe(String ope) {
        if(ope.contentEquals("导航上班")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.gd_sb);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else  if(ope.contentEquals("导航回家")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.gd_hj);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else if(ope.contentEquals("待开发")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.gd_home);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void solveMusic(String ope) {
        if(ope.contentEquals("暂停/播放")) {
            boolean isPauseMusic = false;
            AudioManager audioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
            if(audioManager.isMusicActive()) {
                audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                isPauseMusic = true;
                return;
            }
            if(isPauseMusic) {
                audioManager.abandonAudioFocus(null);
                isPauseMusic = false;
            }
        } else  if(ope.contentEquals("听歌识曲")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.wyy_tgsq);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else if(ope.contentEquals("获取推荐")) {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.wyy_tj);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void sendMessageValue(String message) {
        if (message.contentEquals("hasSoundAction")) {
            // 取消计时器
            mTimer.cancel();
        } else if (message.contentEquals("beginListen")) {
            vaf.startListening();
        }
    }
}