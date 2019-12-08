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

public class MapActivity extends AppCompatActivity {

    boolean shaking = true;
    SensorManager sensorManager;
    MapActivity.ShakeMotionListener shakeMotionListener;
    private Context mContext = null;
    public View view;
    public String init1,init2,init3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
        shakeMotionListener = new MapActivity.ShakeMotionListener();
        mContext = this;
        view = findViewById(R.id.textViewmap);
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
                Intent intent = new Intent(MapActivity.this, ShakeyFloatActivity.class);
                intent.putExtra("Environment", "MapActivity");
                startActivityForResult(intent,GlobalIdentifiers.Map_reci);
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
        if(resultCode == GlobalIdentifiers.Map_reci && shaking == true) {
            init1 = data.getStringExtra("init1");
            init2 = data.getStringExtra("init2");
            init3 = data.getStringExtra("init3");
            Log.v(init1,"I'm in onResult");
            showPopupWindow(view);
        }
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
                toResult();
                popupWindow.dismiss();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "button_init2 is pressed",Toast.LENGTH_SHORT).show();
                //todo
                shaking = false;
                popupWindow.dismiss();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "button_init3 is pressed",Toast.LENGTH_SHORT).show();
                //todo
                shaking = false;
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view);
    }
    //打开虚假界面
    public void  toResult() {
        Intent intent = new Intent();
        intent.putExtra("src",R.drawable.gd_home);
        intent.setClass(this, ResultActivity.class);
        startActivity(intent);
        this.finish();
    }

}