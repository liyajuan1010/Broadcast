package com.example.dell.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StaticActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSend;
    private static TextView tvShowMsg;
    private static final String TAG ="StartActivity" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
        btnSend=(Button)findViewById(R.id.btn_send);
        tvShowMsg=(TextView)findViewById(R.id.btn_show_msg);

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send:
                send();
                break;
        }
    }

    private void send() {
        Intent intent=new Intent("com.example.dell.myapplication.StaticActivity");//隐式使用
        /*
           或将上句写成
           Intent intent=new Intent();
           intent.setAction("com.example.dell.myapplication.StaticActivity");
        */
        intent.putExtra("key","静态注册测试");
        sendBroadcast(intent);
    }

    //在Manifest中静态注册
    public static class MyBroadcastReceiver extends BroadcastReceiver {//此时其内部的，如TextView必须写成static
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG,"接收广播");
            String str=intent.getStringExtra("key");
            tvShowMsg.setText(str);
        }
    }
}
