package com.example.dell.myapplication;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

//电话拦截
//new---JavaClass(选继承BroadcastReceiver）
//在manifest中静态注册<receiver，还有用户权限的许可<user-permission
//电话和短信属于危险权限，需要获得用户的许可
public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //获取电话管理服务
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Service.TELEPHONY_SERVICE);

        switch (tm.getCallState()) {
            case TelephonyManager.CALL_STATE_RINGING://响铃
                String incomingNumber = intent
                        .getStringExtra("incoming_number");
                Toast.makeText(context, "" + incomingNumber, Toast.LENGTH_LONG).show();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK://接听电话
                Toast.makeText(context, "接听电话", Toast.LENGTH_LONG).show();
                break;

            case TelephonyManager.CALL_STATE_IDLE://挂断电话
                Toast.makeText(context, "挂断电话", Toast.LENGTH_LONG).show();
                break;

        }
    }
}