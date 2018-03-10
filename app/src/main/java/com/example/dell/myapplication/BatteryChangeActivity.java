package com.example.dell.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//电量拦截
//可通过改变虚拟机的电量进行测试结果
public class BatteryChangeActivity extends AppCompatActivity {
    private BatteryChangeReceiver batteryChangeReceiver;
    private TextView tvShowMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_change);
        tvShowMsg=(TextView)findViewById(R.id.tv_show_msg);
        batteryChangeReceiver=new BatteryChangeReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryChangeReceiver,intentFilter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryChangeReceiver);
    }

    //不加static，由于是动态注册，不需要静态的
    class BatteryChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int level=intent.getIntExtra("level",0);//这个key值不能改变，是系统定义好的
            int scale=intent.getIntExtra("scale",100);
            int current=level*100/scale;
            tvShowMsg.setText("当前电量："+current+"%");//因为current前加了""，所以会自动将其转换为字符串
        }
    }
}
