package com.dtx12.android_animations_actions.demo

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dtx12.android_animations_actions.actions.Actions
import com.dtx12.android_animations_actions.actions.Actions.color
import com.dtx12.android_animations_actions.actions.Actions.delay
import com.dtx12.android_animations_actions.actions.Actions.fadeIn
import com.dtx12.android_animations_actions.actions.Actions.fadeOut
import com.dtx12.android_animations_actions.actions.Actions.forever
import com.dtx12.android_animations_actions.actions.Actions.moveBy
import com.dtx12.android_animations_actions.actions.Actions.moveTo
import com.dtx12.android_animations_actions.actions.Actions.parallel
import com.dtx12.android_animations_actions.actions.Actions.play
import com.dtx12.android_animations_actions.actions.Actions.rotateBy
import com.dtx12.android_animations_actions.actions.Actions.scaleTo
import com.dtx12.android_animations_actions.actions.Actions.sizeTo
import com.dtx12.android_animations_actions.actions.Interpolations
import com.dtx12.android_animations_actions.demo.databinding.ActivityMainBinding
import java.util.*

class MainActivity() : AppCompatActivity() {

    private val random = Random()

    private val binder by lazy {
        ActivityMainBinding.inflate(
            LayoutInflater.from(this),
            null,
            false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binder) {

            setContentView(root)

            play(parallel(fadeIn(), sizeTo(0f, 0f, 0f)), binder.circle)

            playFirstAnim.setOnClickListener {
                playFirstAnimation()
            }

            playSecondAnim.setOnClickListener {
                playSecondAnim()
            }

            playThirdAnim.setOnClickListener {
                playThirdAnimation()
            }
        }
    }

    private fun reset() = with(binder) {
        firstAnimContainer.removeAllViews()
        secondAnimContainer.removeAllViews()
        // thirdAnimContainer.removeAllViews();
    }

    protected fun playFirstAnimation() = with(binder) {
        reset()
        for (i in 0..5) {
            val size = resources.getDimensionPixelSize(R.dimen.circle_size)
            val margin = resources.getDimensionPixelSize(R.dimen.circle_margin)
            val params = LinearLayout.LayoutParams(size, size)
            params.leftMargin = margin
            params.rightMargin = margin
            params.gravity = Gravity.CENTER
            val view = ImageView(this@MainActivity)
            view.layoutParams = params
            view.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.circle))
            val delay = random.nextFloat()
            val targetY = random.nextInt((firstAnimContainer.bottom / 4f).toInt()).toFloat()
            play(
                Actions.sequence(
                    fadeOut(),
                    scaleTo(1.5f, 1.5f),
                    fadeIn(2f),
                    delay(delay),
                    parallel(
                        moveBy(0f, targetY, 2f, Interpolations.ElasticEaseOut),
                        Actions.sequence(
                            color(-1, Color.GREEN, 1f),
                            color(Color.GREEN, Color.RED, 1f)
                        ),
                        scaleTo(1f, 1f, 2f, Interpolations.ElasticEaseOut)
                    )
                ), view
            )
            firstAnimContainer.addView(view)
        }
    }

    protected fun playSecondAnim() = with(binder) {
        reset()
        val center =
            Point(secondAnimContainer.measuredWidth / 2, secondAnimContainer.measuredHeight / 2)
        val size = resources.getDimensionPixelSize(R.dimen.circle_size)
        var delay = 0f
        for (i in 0..5) {
            val params = FrameLayout.LayoutParams(size, size)
            val view = ImageView(this@MainActivity)
            view.layoutParams = params
            view.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.square))
            val newPos = Point()
            when (i) {
                0 -> newPos[0] = 0
                1 -> newPos[0] = center.y
                2 -> newPos[0] = center.y * 2 - size
                3 -> newPos[center.x * 2 - size] = 0
                4 -> newPos[center.x * 2 - size] = center.y
                5 -> newPos[center.x * 2 - size] = center.y * 2 - size
            }
            play(
                Actions.sequence(
                    parallel(fadeOut(), color(-1, Color.BLUE)),
                    moveTo(center.x.toFloat(), center.y.toFloat()),
                    fadeIn(.5f),
                    parallel(
                        moveTo(
                            newPos.x.toFloat(),
                            newPos.y.toFloat(),
                            1f,
                            Interpolations.ExponentialEaseOut
                        )
                    ),
                    Actions.run(
                        Runnable {
                            play(
                                Actions.sequence(
                                    color(Color.BLUE, Color.GREEN, .1f), forever(
                                        Actions.sequence(
                                            color(Color.GREEN, Color.RED, 1f),
                                            color(
                                                Color.GREEN, Color.RED, 1f
                                            )
                                        )
                                    )
                                ), view
                            )
                        }),
                    parallel(
                        rotateBy(720f, 2f, Interpolations.BackEaseOut), Actions.sequence(
                            scaleTo(.5f, .5f, 1f, Interpolations.BackEaseOut),
                            scaleTo(1f, 1f, 1f, Interpolations.ElasticEaseOut)
                        )
                    ),
                    Actions.sequence(
                        delay(delay), parallel(
                            fadeOut(.5f, Interpolations.ExponentialEaseOut),
                            scaleTo(0f, 1f, .5f, Interpolations.ExponentialEaseOut)
                        )
                    )
                ), view
            )
            delay += 1f
            secondAnimContainer.addView(view)
        }
    }

    protected fun playThirdAnimation() = with(binder) {
        //reset();
        val duration = 5f
        val size = Resources.getSystem().displayMetrics.heightPixels
        play(
            forever(
                duration + 0.5f,
                Actions.sequence(
                    Actions.run(object : Runnable {
                        var time = Calendar.getInstance().timeInMillis
                        override fun run() {
                            val t = Calendar.getInstance().timeInMillis
                            Log.v(this@MainActivity::class.java.simpleName, "dt=" + (t - time))
                            time = t
                        }
                    }),
                    parallel(fadeIn(), sizeTo(0f, 0f, 0f)),
                    parallel(
                        fadeOut(duration),
                        sizeTo(size.toFloat(), size.toFloat(), duration)
                    )
                )
            ), circle
        )
    }

    companion object {

        fun dpToPx(dp: Int): Float {
            val scale = density
            return (dp * scale + 0.5f)
        }

        val density: Float
            get() = Resources.getSystem().displayMetrics.density
    }
}