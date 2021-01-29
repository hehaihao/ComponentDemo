package com.hhh.common;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {
    @BindView(R2.id.tv_butterKnife_R2_test)
    public TextView tvR2Test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.tv_butterKnife_R2_test)
    public void onClick(View view){
    }
}