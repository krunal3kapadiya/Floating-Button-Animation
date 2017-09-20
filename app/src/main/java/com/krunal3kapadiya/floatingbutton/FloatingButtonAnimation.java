package com.krunal3kapadiya.floatingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by krunal on 9/18/2017.
 */

public class FloatingButtonAnimation extends FloatingActionButton implements View.OnClickListener {
    private Context mContext;

    public FloatingButtonAnimation(Context context) {
        super(context);
    }

    private ArrayList<Integer> mIconArrayList;

    public FloatingButtonAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.FloatingButtonAnimation, 0, 0);
        mIconArrayList = new ArrayList<>();
        setOnClickListener(this);
        a.recycle();

    }

    public FloatingButtonAnimation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addView(int icon) {
        mIconArrayList.add(icon);
    }

    @Override
    public void onClick(View view) {


//        LayoutInflater inflater = LayoutInflater.from(mContext).inflate(floatingActionButton.getRootView().getId(), findViewById(android.R.id.content), false);
    }
}
