package com.hci.shakey;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

public class WechatActivity extends AppCompatActivity {

    boolean shaking = true;
    SensorManager sensorManager;
    WechatActivity.ShakeMotionListener shakeMotionListener;

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
                Intent intent = new Intent(WechatActivity.this, ShakeyFloatActivity.class);
                intent.putExtra("Environment", "WechatActivity");
                startActivityForResult(intent,GlobalIdentifiers.Wechat_reci);
                shaking = false;
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
}