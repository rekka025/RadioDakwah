package com.reka.radiodakwahislami.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.reka.radiodakwahislami.MainActivity;
import com.reka.radiodakwahislami.R;


public class WelcomeActivity extends AppCompatActivity {
    private TextView tv;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv = (TextView) findViewById(R.id.spTv);
        imageView = (ImageView) findViewById(R.id.spRadio);
        Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.splash);
        tv.startAnimation(myAnim);
        imageView.startAnimation(myAnim);
        final Intent intent = new Intent(this, MainActivity.class);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                startActivity(intent);
                finish();
                }
            }
        };
                timer.start();

    }

}
