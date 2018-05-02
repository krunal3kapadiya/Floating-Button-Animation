package com.krunal3kapadiya.floatingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krunal on 9/18/2017.
 */

public class FloatingButtonAnimation extends FrameLayout {

    private Context mContext;
    private FloatingActionButton mFloatingActionButton;
    private ArrayList<Integer> mIconArrayList = new ArrayList<>();
    private List<View> mButtonList = new ArrayList<>();

    private boolean isExpanded = false;

    public FloatingButtonAnimation(Context context) {
        super(context);
    }

    public FloatingButtonAnimation(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(true);
        setClipChildren(false);
        setClipToPadding(false);

        mContext = context;

        //add main button
        addInitialButton(context);

        mFloatingActionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnimation(mFloatingActionButton);
                mFloatingActionButton.setImageDrawable(isExpanded ? ContextCompat.getDrawable(mContext, R.mipmap.ic_more_hor) : ContextCompat.getDrawable(mContext, R.mipmap.ic_clear));
                if (isExpanded) {
                    if (mButtonList.isEmpty())
                        return;
                    for (int i = 0; i < mButtonList.size(); i++) {
//                        addAnimation(mButtonList.get(i));

                        removeView(mButtonList.get(i));
                    }
                    mButtonList.clear();
                } else {

                    final float centerX = mFloatingActionButton.getX();
                    final float centerY = mFloatingActionButton.getY();
                    final int buttonsCount = mIconArrayList.size();
                    final float angleStep = 180f / buttonsCount;

                    for (int i = 0; i < mIconArrayList.size(); i++) {
                        final FloatingActionButton floatingActionButton = new FloatingActionButton(mContext);
                        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.colorPrimaryLight)));
                        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(mContext, mIconArrayList.get(i)));
                        floatingActionButton.setLayoutParams(layoutParams);

                        addView(floatingActionButton);

                        mButtonList.add(floatingActionButton);
                    }
                    offsetAndScaleButtons(centerX, centerY, angleStep, 200, 1f);
                }
                isExpanded = !isExpanded;
            }
        });
    }

    /**
     * scale the button
     *
     * @param centerX
     * @param centerY
     * @param angleStep
     * @param offset
     * @param scale
     */
    private void offsetAndScaleButtons(float centerX, float centerY, float angleStep, float offset, float scale) {
        for (int i = 0, cnt = mIconArrayList.size(); i < cnt; i++) {
            final float angle = angleStep * i - 160;
            final float x = (float) Math.cos(Math.toRadians(angle)) * offset;
            final float y = (float) Math.sin(Math.toRadians(angle)) * offset;

            final View button = mButtonList.get(i);
            button.setX(centerX + x);
            button.setY(centerY + y);
            button.setScaleX(1.0f * scale);
            button.setScaleY(1.0f * scale);
        }
    }


    private void addAnimation(View view) {
        final Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.bounce);
        BounceInterpolatorAnim interpolator = new BounceInterpolatorAnim(0.1, 10);
        animation.setInterpolator(interpolator);
        view.startAnimation(animation);
    }


    private void addInitialButton(Context context) {
        mFloatingActionButton = new FloatingActionButton(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        layoutParams.bottomMargin = 100;
        mFloatingActionButton.setLayoutParams(layoutParams);
        mFloatingActionButton.setRippleColor(ContextCompat.getColor(context, R.color.colorPrimaryRipple));
        addView(mFloatingActionButton);
    }

    public void addViewIcon(int icon) {
        mIconArrayList.add(icon);
    }

    public void setImageDrawable(Drawable imageDrawable) {
        mFloatingActionButton.setImageDrawable(imageDrawable);
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        mFloatingActionButton.setBackgroundTintList(colorStateList);
    }
}
