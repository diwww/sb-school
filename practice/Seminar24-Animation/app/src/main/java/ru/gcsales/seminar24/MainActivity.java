package ru.gcsales.seminar24;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButtonBaseXml;
    private Button mButtonBaseCode;
    private Button mButtonObjectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Base animation via XML
        mButtonBaseXml = findViewById(R.id.button_base_xml);
        mButtonBaseXml.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.base_animation));
        });

        // Base animation via code
        mButtonBaseCode = findViewById(R.id.button_base_code);
        Animation animation = new ScaleAnimation(0.1f, 2.0f, 0.1f, 2.0f, mButtonBaseCode.getPivotX(), mButtonBaseCode.getPivotY());
        animation.setDuration(3000);
        animation.setInterpolator(new BounceInterpolator());
        mButtonBaseCode.setOnClickListener(v -> v.startAnimation(animation));

        // Object animator
        mButtonObjectAnimator = findViewById(R.id.button_object_animator);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mButtonObjectAnimator, View.X, 0f, 200f);
        animation.setDuration(1000);
        mButtonObjectAnimator.setOnClickListener(v -> objectAnimator.start());
    }
}
