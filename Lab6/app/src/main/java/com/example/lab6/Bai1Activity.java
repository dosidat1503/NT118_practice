package com.example.lab6;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bai1Activity extends AppCompatActivity {
    private Button btnFadeInXml, btnFadeOutXml,
            btnBlinkXml, btnZoomInXml, btnZoomOutXml,  btnRotateXml, btnMoveXml, btnSlideUpXml,
            btnBounceXml, btnCombineXml;
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai1);

        findViewsByIds();
        initVariables();

        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);

        final Animation anim_fade_in = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_fade_in);
        btnFadeInXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_fade_in);
            }
        });

        final Animation anim_fade_out = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_fade_out);
        btnFadeOutXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_fade_out);
            }
        });

        final Animation anim_blink = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_blink);
        btnBlinkXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_blink);
            }
        });

        final Animation anim_zoom_in = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_zoom_in);
        btnZoomInXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_zoom_in);
            }
        });

        final Animation anim_zoom_out = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_zoom_out);
        btnZoomOutXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_zoom_out);
            }
        });

        final Animation anim_rotate = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_rotate);
        btnRotateXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_rotate);
            }
        });

        final Animation anim_move = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_move);
        btnMoveXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_move);
            }
        });

        final Animation anim_slide_up = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_slide_up);
        btnSlideUpXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_slide_up);
            }
        });

        final Animation anim_bounce = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_bounce);
        btnBounceXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_bounce);
            }
        });

        final Animation anim_combine = AnimationUtils.loadAnimation(Bai1Activity.this, R.anim.anim_combine);
        btnCombineXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim_combine);
            }
        });
    }

    private void handleClickAnimationXml(Button btn, int animId)
    {
        final Animation animation = AnimationUtils.loadAnimation(Bai1Activity.this, animId);
        animation.setAnimationListener(animationListener);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }

    private void findViewsByIds() {
        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);
        btnFadeInXml = (Button) findViewById(R.id.btn_fade_in_xml);
        btnFadeOutXml = (Button) findViewById(R.id.btn_fade_out_xml);
        btnBlinkXml = (Button) findViewById(R.id.btn_blink_xml);
        btnZoomInXml = (Button) findViewById(R.id.btn_zoom_in_xml);
        btnZoomOutXml = (Button) findViewById(R.id.btn_zoom_out_xml);
        btnRotateXml = (Button) findViewById(R.id.btn_rotate_xml);
        btnMoveXml = (Button) findViewById(R.id.btn_move_xml);
        btnSlideUpXml = (Button) findViewById(R.id.btn_slide_up_xml);
        btnBounceXml = (Button) findViewById(R.id.btn_bounce_xml);
        btnCombineXml = (Button) findViewById(R.id.btn_combine_xml);
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
