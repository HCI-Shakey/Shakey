package com.hci.shakey;

import androidx.appcompat.app.AppCompatActivity;

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

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_RECEIVER_FOREGROUND;

public class ShakeyFloatActivity extends AppCompatActivity implements View.OnClickListener {

    private String str = "init";
    View view;
//    public HashMap<String,String> hashMap = new HashMap<>();
    public static String env;
    public String init1,init2,init3;
    private Context mContext = null;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(GlobalIdentifiers.Shakey_float == true) {
            finish();
        }
        GlobalIdentifiers.Shakey_float = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakey_float);
        Intent intent_reci = getIntent();
        env = intent_reci.getStringExtra("Environment");
        view =(TextView) findViewById(R.id.textViewfloat);
        switch (env){
            case "WechatActivity":
//                List<String> inits = LocalDataBase.getRecommends(env);
//                init1 = inits.get(0);
//                init2 = inits.get(1);
//                init3 = inits.get(2);
                init1 = "wechat";
                init2 = "wechat";
                init3 = "wechat";
                break;
            case "AlipayActivity":
//                List<String> inits = LocalDataBase.getRecommends(env);
//                init1 = inits.get(0);
//                init2 = inits.get(1);
//                init3 = inits.get(2);
                init1 = "alipaychat";
                init2 = "alipaychat";
                init3 = "alipaychat";
                break;
            case "DidiActivity":
//                List<String> inits = LocalDataBase.getRecommends(env);
//                init1 = inits.get(0);
//                init2 = inits.get(1);
//                init3 = inits.get(2);
                init1 = "didichat";
                init2 = "didichat";
                init3 = "didihat";
                break;
            case "MapActivity":
//                List<String> inits = LocalDataBase.getRecommends(env);
//                init1 = inits.get(0);
//                init2 = inits.get(1);
//                init3 = inits.get(2);
                init1 = "mapchat";
                init2 = "mapchat";
                init3 = "mapchat";
                break;
            case "MusicActivity":
//                List<String> inits = LocalDataBase.getRecommends(env);
//                init1 = inits.get(0);
//                init2 = inits.get(1);
//                init3 = inits.get(2);
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
        ((Button)button2).setText(init2);
        Button button3 = (Button) findViewById(R.id.button_i3);
        ((Button)button3).setText(init3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_i1 :
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
        if(ope == "扫一扫") {
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
        } else  if(ope == "返回上一界面") {
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
        } else if(ope == "添加好友") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.wc_add);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void solveAlipay(String ope) {
        if(ope == "扫一扫") {
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
        } else  if(ope == "出示付款码") {
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
        } else if(ope == "待开发") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.ali_fkm);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void solveDidi(String ope) {
        if(ope == "打车") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.dd_dc);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else  if(ope == "钱包") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.dd_qb);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else if(ope == "待开发") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.dd_dc);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void solveGaoDe(String ope) {
        if(ope == "导航上班") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.gd_sb);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else  if(ope == "导航回家") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.gd_hj);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else if(ope == "待开发") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.gd_home);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void solveMusic(String ope) {
        if(ope == "暂停/播放") {
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
        } else  if(ope == "听歌识曲") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.wyy_tgsq);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        } else if(ope == "获取推荐") {
            Intent intent = new Intent();
            intent.putExtra("src",R.drawable.wyy_tj);
            intent.setClass(this, ResultActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}