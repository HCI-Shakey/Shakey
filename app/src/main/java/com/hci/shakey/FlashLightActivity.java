package com.hci.shakey;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.*;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.hardware.camera2.CameraManager;

import static android.view.WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL;
import static android.view.WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;

public class FlashLightActivity extends AppCompatActivity {
    private Context mContext = null;
    public View view;
    private CameraManager mCameraManager;
    private Camera camera = null;
    private String mCameraId;
    private boolean isLight = false;
    public static final int CAMERA_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
        mCameraManager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] cameraList = mCameraManager.getCameraIdList();
        } catch (CameraAccessException e) {

        }

        final ImageView imv = (ImageView)findViewById(R.id.fl);
        imv.setImageResource(R.drawable.fl_close);

        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLight = !isLight;
                if(isLight) {
                    imv.setImageResource(R.drawable.fl_open);
                } else {
                    imv.setImageResource(R.drawable.fl_close);
                }
                try {
                    mCameraManager.setTorchMode("0", isLight);
                } catch (CameraAccessException e) {

                }
            }
        });
    }
}
