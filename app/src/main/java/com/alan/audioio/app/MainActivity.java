package com.alan.audioio.app;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import com.alan.audioio.R;
import com.alan.audioio.app.ui.TestPlayWavActivity;
import com.alan.audioio.audio.common.APPContext;
import com.alan.audioio.utils.ALog;
import com.alan.audioio.utils.RuntimePermissionsManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RuntimePermissionsManager runtimePermissionsManager = new RuntimePermissionsManager(this,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        runtimePermissionsManager.setListener(new RuntimePermissionsManager.Listener() {
            @Override
            public void onPermissionsGranted(boolean isAllPermissionsGranted) {
                ALog.d("onPermissionsGranted()-->> isAllPermissionsGranted = " + isAllPermissionsGranted);
            }

            @Override
            public void onAskedTooManyTimes() {
                ALog.d("onAskedTooManyTimes()-->>");
            }
        });
        runtimePermissionsManager.makeRequest();

        APPContext.getInstance().setContext(this);
        findViewById(R.id.btnTestSoundPool).setOnClickListener(this);
        findViewById(R.id.btnTestAudioRecord).setOnClickListener(this);
        findViewById(R.id.btnTestAudioPlayer).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTestSoundPool) {
            TestSoundPoolActivity.launchMe(this);
        } else if (view.getId() == R.id.btnTestAudioRecord) {
            TestRecordToWavActivity.launchMe(this);
        } else if (view.getId() == R.id.btnTestAudioPlayer) {
            TestPlayWavActivity.launchMe(this);
        }
    }

}
