package app.krunal3kapadiya.floatingbutton

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

/**
 * @author Krunal Kapadiya (krunal3kapadiya)
 *
 */

class FloatingButton : FrameLayout {
    private var floatingActionButton: FloatingActionButton? = null
    private val iconArrayList = ArrayList<Int>()
    private val buttonList = ArrayList<View>()
    private val clickListenerArray = ArrayList<OnClickListener>()
    private var isExpanded = false
    var pos: Int = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setWillNotDraw(true)
        clipChildren = false
        clipToPadding = false
        //add main button
        addInitialButton(context)
        floatingActionButton!!.setOnClickListener(OnClickListener {
            //adding scaling animation
//            addAnimation(floatingActionButton!!)
            floatingActionButton!!.setImageDrawable(
                    if (isExpanded) ContextCompat.getDrawable(context, R.mipmap.ic_more_hor)
                    else ContextCompat.getDrawable(context, R.mipmap.ic_clear))
            if (isExpanded) {
                // if FAB is open
                if (buttonList.isEmpty())
                    return@OnClickListener
                for (i in buttonList.indices) {
                    addRemoveAnimation(buttonList[i])
                    removeView(buttonList[i])
                }
                buttonList.clear()
            } else {
                // if FAB is close, next create new buttons
                val centerX = floatingActionButton!!.x
                val centerY = floatingActionButton!!.y
                val buttonsCount = iconArrayList.size
                val angleStep = 180f / buttonsCount

                for (i in iconArrayList.indices) {
                    val floatingActionButton = FloatingActionButton(context)
                    val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

                    floatingActionButton.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat
                                    .getColor(context, R.color.colorPrimaryLight))
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(context, iconArrayList[i]))
                    floatingActionButton.layoutParams = layoutParams

                    addView(floatingActionButton)
                    floatingActionButton.setOnClickListener(clickListenerArray[i])

                    var alphaAnimation: ObjectAnimator = ObjectAnimator.ofFloat(floatingActionButton,
                            "alpha",
                            0.3f)

                    buttonList.add(floatingActionButton)
                }
                offsetAndScaleButtons(centerX, centerY, angleStep, 200f, 1f)
            }
            isExpanded = !isExpanded
        })
    }

    private fun addRemoveAnimation(view: View) {
        val buttonAnimator = AnimatorSet()
        val buttonAnimatorX = ValueAnimator.ofFloat(floatingActionButton!!.x + floatingActionButton!!.size,
                view.x)
        buttonAnimatorX.addUpdateListener { animation ->
            view.x = animation.animatedValue as Float - view.layoutParams.width / 2
            view.requestLayout()
        }
        buttonAnimatorX.duration = 1900

        val buttonAnimatorY = ValueAnimator.ofFloat(floatingActionButton!!.y + floatingActionButton!!.size,
                view.y)
        buttonAnimatorY.addUpdateListener { animation ->
            view.y = animation.animatedValue as Float
            view.requestLayout()
        }
        buttonAnimatorY.duration = 1900


        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f)
        scaleDownX.duration = 1500
        scaleDownY.duration = 1500

        buttonAnimator.play(buttonAnimatorX).with(buttonAnimatorY).with(scaleDownX).with(scaleDownY)/*.with(buttonSizeAnimator)*/
        buttonAnimator.start()
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
    private fun offsetAndScaleButtons(centerX: Float, centerY: Float, angleStep: Float, offset: Float, scale: Float) {
        var i = 0
        val cnt = iconArrayList.size
        var constant = (90 - 90.toDouble().pow((1 / (iconArrayList.size + 1)).toDouble()))
        while (i < cnt) {
            val angle = angleStep * i - constant
            val x = cos(Math.toRadians(angle)).toFloat() * offset
            val y = sin(Math.toRadians(angle)).toFloat() * offset
            val button = buttonList[i]
            button.x = centerX + x
            button.y = centerY + y
            button.scaleX = 1.0f * scale
            button.scaleY = 1.0f * scale

            val buttonAnimator = AnimatorSet()
            val buttonAnimatorX = ValueAnimator.ofFloat(floatingActionButton!!.x + floatingActionButton!!.size,
                    button.x)
            buttonAnimatorX.addUpdateListener { animation ->
                button.x = animation.animatedValue as Float - button.layoutParams.width / 2
                button.requestLayout()
            }
            buttonAnimatorX.duration = 1900

            /**
             * ValueAnimator to update y position of a button
             */
            val buttonAnimatorY = ValueAnimator.ofFloat(floatingActionButton!!.y + floatingActionButton!!.size,
                    button.y)
            buttonAnimatorY.addUpdateListener { animation ->
                button.y = animation.animatedValue as Float
                button.requestLayout()
            }
            buttonAnimatorY.duration = 1900

            val scaleDownX = ObjectAnimator.ofFloat(button, "scaleX", 0.1f, 1f)
            val scaleDownY = ObjectAnimator.ofFloat(button, "scaleY", 0.1f, 1f)
            scaleDownX.duration = 1500
            scaleDownY.duration = 1500

//            val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)
//            val interpolator = BounceInterpolatorAnim(0.1, 10.0)
//            animation.interpolator = interpolator
//            button.startAnimation(animation)

            buttonAnimator.play(buttonAnimatorX).with(buttonAnimatorY).with(scaleDownX).with(scaleDownY)/*.with(buttonSizeAnimator)*/
            buttonAnimator.start()
            i++
        }
    }


    private fun addAnimation(view: View) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)
        val interpolator = BounceInterpolatorAnim(0.1, 10.0)
        animation.interpolator = interpolator
        view.startAnimation(animation)
    }


    private fun addInitialButton(context: Context) {
        floatingActionButton = FloatingActionButton(context)
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        layoutParams.bottomMargin = 100
        floatingActionButton!!.layoutParams = layoutParams
        floatingActionButton!!.rippleColor = ContextCompat.getColor(context, R.color.colorPrimaryRipple)
        addView(floatingActionButton)
    }

    fun addViewIcon(icon: Int, onClickListener: OnClickListener) {
        iconArrayList.add(icon)
        clickListenerArray.add(onClickListener)
    }

    fun addViewIcon(icon: Int) {
        iconArrayList.add(icon)
        clickListenerArray.add(OnClickListener {
            Toast.makeText(context, "Set Action For Button", Toast.LENGTH_LONG).show()
        })
    }

    fun setImageDrawable(imageDrawable: Drawable) {
        floatingActionButton!!.setImageDrawable(imageDrawable)
    }

    override fun setBackgroundTintList(colorStateList: ColorStateList?) {
        floatingActionButton!!.backgroundTintList = colorStateList
    }
}
