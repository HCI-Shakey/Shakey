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
import android.view.View;
import android.widget.Button;

public class LockScreenActivity extends AppCompatActivity {

    boolean shaking;
    SensorManager sensorManager;
    LockScreenActivity.ShakeMotionListener shakeMotionListener;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        shaking = false;
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        shakeMotionListener = new LockScreenActivity.ShakeMotionListener();
        button = findViewById(R.id.button_notice);
        button.setVisibility(View.GONE);
        button.setText("null");
        Button buttonShowCall = findViewById(R.id.button_show_call);
        Button buttonShowWechat = findViewById(R.id.button_show_wechat);
        buttonShowCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setText("您有一个未接来电");
                button.setVisibility(View.VISIBLE);
                //TODO 给后台传消息
            }
        });
        buttonShowWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setText("您有一条新的微信消息");
                button.setVisibility(View.VISIBLE);
                //TODO 给后台传消息
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
                Intent intent = new Intent(LockScreenActivity.this, ShakeyFloatActivity.class);
                intent.putExtra("Environment", "LockScreenActivity");
                if (button.getText().equals("您有一个未接来电")) {
                    intent.putExtra("Notice", "您有一个未接来电");
                } else if (button.getText().equals("您有一条新的微信消息")) {
                    intent.putExtra("Notice", "您有一条新的微信消息");
                }
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
