package com.example.dell.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG="MyBroadcastReceiver";
    private Interaction interaction;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"动态广播测试");
        String str=intent.getStringExtra("key");
        interaction.setText("来自于广播中的数据");
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }
    public void setInteractionListener(Interaction interaction){
        this.interaction=interaction;
    }
}
