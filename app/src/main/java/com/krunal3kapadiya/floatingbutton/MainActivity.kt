package com.krunal3kapadiya.floatingbutton

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import app.krunal3kapadiya.floatingbutton.FloatingButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this@MainActivity

        val floatingButtonAnimation = findViewById<FloatingButton>(R.id.floating_button_animation)

        floatingButtonAnimation.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_more_hor)!!)
        floatingButtonAnimation.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@MainActivity, R.color.colorAccent))

        floatingButtonAnimation.addViewIcon(R.mipmap.ic_airport)
        floatingButtonAnimation.addViewIcon(R.mipmap.ic_bus)
        floatingButtonAnimation.addViewIcon(R.mipmap.ic_boat)
    }
}
