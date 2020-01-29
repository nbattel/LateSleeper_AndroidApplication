package com.twelve.splashscreenanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Variables
    Animation topAnimation, bottomAnimation, rotationAnimation;
    ImageView clock, arm;
    TextView applicationName;
    Handler animationDelay = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removing the status bar at the top of the app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Animations
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        rotationAnimation = AnimationUtils.loadAnimation(this, R.anim.rotation_animation);

        clock = findViewById(R.id.clockNoArmImageView);
        arm = findViewById(R.id.armImageView);
        applicationName = findViewById(R.id.applicationName);

        clock.setAnimation(topAnimation);

        animationDelay.postDelayed(new Runnable() {
            @Override
            public void run() {
                arm.setAnimation(topAnimation);
                arm.setAnimation(rotationAnimation);
            }
        }, 3000);

        applicationName.setAnimation(topAnimation);
    }
}
