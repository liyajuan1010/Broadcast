package com.example.dell.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStatic,btnDynamic,btnBatteryChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStatic=(Button)findViewById(R.id.btn_static);
        btnDynamic=(Button)findViewById(R.id.btn_dynamic);
        btnBatteryChange=(Button)findViewById(R.id.btn_batteryChange);

        btnDynamic.setOnClickListener(this);
        btnStatic.setOnClickListener(this);
        btnBatteryChange.setOnClickListener(this);

        _checkPermission();
    }

    private void _checkPermission() {
        String[] permissions = new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission
                .READ_PHONE_STATE};
        List<String> unPermissions = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager
                    .PERMISSION_GRANTED) {
                unPermissions.add(permissions[i]);
            }
        }
        if (!unPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, unPermissions.toArray(new
                    String[unPermissions.size()]), 1);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_static:
                //也可以写成"_static()",再写"_static()"方法，为避免和static重名，在方法名前加“_”
                startActivity(new Intent(this,StaticActivity.class));
                break;
            case R.id.btn_dynamic:
                startActivity(new Intent(this,DynamicActivity.class));
                break;
            case R.id.btn_batteryChange:
                startActivity(new Intent(this,BatteryChangeActivity.class));
                break;
        }
    }
}
