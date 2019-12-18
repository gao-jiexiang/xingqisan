package com.example.xiaozhu.danyuan06_1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class HelloActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mHelloBtn;
    private ImageView mImageView;
    private TextView mHelloTime;
    private String[] arr = {"倒计时：3","倒计时：2","倒计时：1"};
    private int i = 0;
    private Timer timer;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                if(i<arr.length){
                    mHelloTime.setText(arr[i++]);
                }else {
                    timer.cancel();
                    Intent intent = new Intent(HelloActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        initView();
    }

    private void initView() {
        mHelloBtn = (Button) findViewById(R.id.hello_btn);
        mHelloBtn.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.image_view);
        mHelloTime = (TextView) findViewById(R.id.hello_time);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hello_animation);
        mImageView.setAnimation(animation);
        handler.sendEmptyMessage(1);
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        timer.schedule(timerTask,1000,1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.hello_btn:
                timer.cancel();
                Intent intent = new Intent(HelloActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
