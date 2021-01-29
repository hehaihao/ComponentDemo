package com.hhh.componentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //通过路由直接打开home组件的HomeActivity，
        ARouter.getInstance().build("/homepage/homeActivity").navigation();
        finish();
    }
}