package com.hci.shakey;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_RECEIVER_FOREGROUND;

public class WechatActivity extends AppCompatActivity {

    boolean shaking = true;
    SensorManager sensorManager;
    WechatActivity.ShakeMotionListener shakeMotionListener;
    private Context mContext = null;
    public View view;
    public String init1,init2,init3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        shaking = false;
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        shakeMotionListener = new WechatActivity.ShakeMotionListener();
        mContext = this;
        view = findViewById(R.id.textView3);
    }

    private class ShakeMotionListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (shaking) return;
            float x = Math.abs(event.values[0]);
            float y = Math.abs(event.values[1]);
            float z = Math.abs(event.values[2]);
            if (x>19 || y>19 || z>19) {
                shaking = true;
                vibrate(500);
                Intent intent = new Intent(WechatActivity.this, ShakeyFloatActivity.class);
                intent.putExtra("Environment", "WechatActivity");
                startActivityForResult(intent,GlobalIdentifiers.Wechat_reci);
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    @Override
    protected void onResume() {
        //注册监听加速度传感器
        sensorManager.registerListener(shakeMotionListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
        super.onResume();
    }

    @Override
    protected void onPause() {
        //资源释放
        sensorManager.unregisterListener(shakeMotionListener);
        super.onPause();
    }

    private void vibrate(long milliseconds) {
        Vibrator vibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == GlobalIdentifiers.Wechat_reci && shaking == true) {
                init1 = data.getStringExtra("init1");
                init2 = data.getStringExtra("init2");
                init3 = data.getStringExtra("init3");
                Log.v(init1,"I'm in onResult");
                showPopupWindow(view);
            }
            //todo
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
                shaking = false;
                toWeChatScanDirect();
                popupWindow.dismiss();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "button_init2 is pressed",Toast.LENGTH_SHORT).show();
                //todo
                shaking = false;
                toWeChatDirect();
                popupWindow.dismiss();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "button_init3 is pressed",Toast.LENGTH_SHORT).show();
                //todo
                shaking = false;
                toWeChatResult();
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view);
    }
    //直接打开微信扫码界面
    public void toWeChatScanDirect() {
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
    }
    //直接打开微信界面
    public void  toWeChatDirect() {
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
    }
    //直接打开虚假界面
    public void  toWeChatResult() {
        Intent intent = new Intent();
        intent.setClass(this, WeChatResultActivity.class);
        startActivity(intent);
        this.finish();
    }

}
