package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.twelve.latesleeper.R;

public class MainActivity extends AppCompatActivity {

    // Delay time
    private static int DELAY_TIME = 5000;

    // Animation
    Animation topAnimation, bottomAnimation;

    // Views
    ImageView imageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer welcomeVoice = MediaPlayer.create(MainActivity.this, R.raw.welcomevoice);
        welcomeVoice.start();

        // Applying animations to image view and text
        // Animations
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        // Hooks
        imageLogo = findViewById(R.id.imageView);

        imageLogo.setAnimation(topAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent  = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, DELAY_TIME);
    }
}
