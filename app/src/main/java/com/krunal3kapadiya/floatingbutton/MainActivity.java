package com.krunal3kapadiya.floatingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

/**
 * Created by krunal on 9/18/2017.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context mContext = MainActivity.this;

        final FloatingButtonAnimation mFloatingButtonAnimation = findViewById(R.id.floating_button_animation);

        mFloatingButtonAnimation.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_more_hor));
        mFloatingButtonAnimation.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorAccent)));

        mFloatingButtonAnimation.addViewIcon(R.mipmap.ic_airport);
        mFloatingButtonAnimation.addViewIcon(R.mipmap.ic_bus);
        mFloatingButtonAnimation.addViewIcon(R.mipmap.ic_boat);
    }
}
