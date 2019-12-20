package com.hci.shakey;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hci.shakey.support.LocalDataBase;

public class OSActivity extends AppCompatActivity {

    boolean shaking;
    SensorManager sensorManager;
    ShakeMotionListener shakeMotionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);
        setUpButtons();
        Log.i("mHelper2","" + LocalDataBase.mHelper);

        shaking = false;
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        shakeMotionListener = new ShakeMotionListener();
    }

    private void setUpButtons() {
        Button buttonShakey = findViewById(R.id.button_shakey);
        Button buttonWechat = findViewById(R.id.button_wechat);
        Button buttonMusic = findViewById(R.id.button_music);
        Button buttonAlipay = findViewById(R.id.button_alipay);
        Button buttonDidi = findViewById(R.id.button_didi);
        Button buttonMap = findViewById(R.id.button_map);
        Button buttonLockScreen = findViewById(R.id.button_lock_screen);
        Button buttonCall = findViewById(R.id.button_call);
        Button buttonLight = findViewById(R.id.button_light);

        buttonShakey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, ShakeySettingsActivity.class);
                startActivity(intent);
            }
        });
        buttonWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, WechatActivity.class);
                startActivity(intent);
            }
        });
        buttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
        buttonAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, AlipayActivity.class);
                startActivity(intent);
            }
        });
        buttonDidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, DidiActivity.class);
                startActivity(intent);
            }
        });
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        buttonLockScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, LockScreenActivity.class);
                startActivity(intent);
            }
        });
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, DialActivity.class);
                startActivity(intent);
            }
        });
        buttonLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OSActivity.this, FlashLightActivity.class);
                startActivity(intent);
            }
        });
    }

    private class ShakeMotionListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (shaking) return;
            float x = Math.abs(event.values[0]);
            float y = Math.abs(event.values[1]);
            float z = Math.abs(event.values[2]);
            if (x>60 && y<40) {
                shaking = true;
                vibrate(500);
                Intent intent = new Intent(OSActivity.this, ShakeyFloatActivity.class);
                intent.putExtra("Environment", "OSActivity");
                startActivityForResult(intent, GlobalIdentifiers.CALL_SHAKEY);
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
        if (requestCode == GlobalIdentifiers.CALL_SHAKEY) { shaking = false; }
    }
}
