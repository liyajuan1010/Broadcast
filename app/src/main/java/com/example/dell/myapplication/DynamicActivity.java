package com.example.dell.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DynamicActivity extends AppCompatActivity implements View.OnClickListener,Interaction {
    private Button btnRegister,btnSend;
    private TextView tvShowMsg;
    private MyBroadcastReceiver myDynamicReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        btnRegister=(Button)findViewById(R.id.btn_register);
        btnSend=(Button)findViewById(R.id.btn_send);
        tvShowMsg=(TextView)findViewById(R.id.tv_show_msg);

        btnSend.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_send:
                send();
                break;
        }
    }
    //new--JavaClass--MyBroadcastReceiver(继承BroadcastReceiver)

    private void send() {
        Intent intent=new Intent("com.example.dell.myapplication.dynamicactivity");
        /*
        *  Intent intent=new Intent();
           intent.setAction("com.example.dell.myapplication.dynamicactivity");
        */
        intent.putExtra("key","From Activity");
        sendBroadcast(intent);
    }
    private void register() {
        myDynamicReceiver= new MyBroadcastReceiver();
        myDynamicReceiver.setInteractionListener(this);
        IntentFilter intentfilter=new IntentFilter("com.example.dell.myapplication.dynamicactivity");
        /*
         IntentFilter intentFilter=new IntentFilter();
         intentFilter.addAction("com.example.dell.myapplication.dynamicactivity");
        * */
        registerReceiver(myDynamicReceiver,intentfilter);//注册
    }

    //解注册
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myDynamicReceiver);//解注册
    }

    @Override
    public void setText(String str) {
        tvShowMsg.setText(str);
    }
    //new--JavaClass--Interaction(第二个改为Interface)
}
