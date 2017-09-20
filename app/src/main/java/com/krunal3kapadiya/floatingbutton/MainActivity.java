package com.krunal3kapadiya.floatingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by krunal on 9/18/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingButtonAnimation mFloatingButtonAnimation;
    private Context mContext;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        mFloatingButtonAnimation = (FloatingButtonAnimation) findViewById(R.id.floating_button_animation);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        mFloatingButtonAnimation.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_airport));

        mFloatingButtonAnimation.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorAccent)));
        mFloatingButtonAnimation.addView(R.mipmap.ic_airport);
        mFloatingButtonAnimation.addView(R.mipmap.ic_bus);
        mFloatingButtonAnimation.addView(R.mipmap.ic_boat);

        mFloatingButtonAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getParent();

                FloatingActionButton floatingActionButton = new FloatingActionButton(mContext);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.colorAccent)));
                floatingActionButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_airport));
                mRelativeLayout.addView(floatingActionButton);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
