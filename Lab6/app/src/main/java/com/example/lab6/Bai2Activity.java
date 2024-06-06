package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.data.ApnSetting;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;

public class Bai2Activity extends AppCompatActivity {
    private Button  btnFadeInCode,  btnFadeOutCode,
            btnBlinkCode, btnZoomInCode,
            btnZoomOutCode, btnRotateCode,  btnMoveCode, btnSlideUpCode,
            btnBounceCode, btnCombineCode;
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai1);

        findViewsByIds();
        initVariables();

        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, initRotateAnimation());
        handleClickAnimationCode(btnMoveCode, initMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode, initSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode, initBounceAnimation());
        handleClickAnimationCode(btnCombineCode, initCombineAnimation());
        ivUitLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(Bai2Activity.this, Bai3Activity.class);
                startActivity(iNewActivity);
                overridePendingTransition(R.anim.anim_move, R.anim.anim_fade_out);
            }
        });
    }
    private Animation initFadeInAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initFadeOutAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initBlinkAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(300);
        animation.setRepeatMode(AlphaAnimation.REVERSE);
        animation.setRepeatCount(3);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initZoomInAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1f, 3f, 1f, 3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initZoomOutAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initRotateAnimation() {
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(600);
        animation.setRepeatMode(RotateAnimation.RESTART);
        animation.setRepeatCount(2);
        animation.setInterpolator(new CycleInterpolator(0.25f));
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initMoveAnimation() {
        TranslateAnimation animation = new TranslateAnimation(0f, 1000f, 0f, 0f);
        animation.setDuration(800);
        animation.setFillAfter(true);
        animation.setInterpolator(new LinearInterpolator());
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initSlideUpAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 1f, 0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initBounceAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f, 1f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setInterpolator(new BounceInterpolator());
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initCombineAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setInterpolator(new LinearInterpolator());

        ScaleAnimation animation1 = new ScaleAnimation(1f, 3f, 1f, 3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation1.setDuration(4000);

        animationSet.addAnimation(animation1);

        RotateAnimation animation2 = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation2.setRepeatMode(RotateAnimation.RESTART);
        animation2.setRepeatCount(2);
        animation2.setDuration(500);

        animationSet.addAnimation(animation2);

        animationSet.setAnimationListener(animationListener);
        return animationSet;
    }

    private void handleClickAnimationCode(Button btn, final Animation animation) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }

    private void findViewsByIds() {
        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);
        btnFadeInCode = (Button) findViewById(R.id.btn_fade_in_code);
        btnFadeOutCode = (Button) findViewById(R.id.btn_fade_out_code);
        btnBlinkCode = (Button) findViewById(R.id.btn_blink_code);
        btnZoomInCode = (Button) findViewById(R.id.btn_zoom_in_code);
        btnZoomOutCode = (Button) findViewById(R.id.btn_zoom_out_code);
        btnRotateCode = (Button) findViewById(R.id.btn_rotate_code);
        btnMoveCode = (Button) findViewById(R.id.btn_move_code);
        btnSlideUpCode = (Button) findViewById(R.id.btn_slide_up_code);
        btnBounceCode = (Button) findViewById(R.id.btn_bounce_code);
        btnCombineCode = (Button) findViewById(R.id.btn_combine_code);
    }

    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }
}
