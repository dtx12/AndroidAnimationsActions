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

            Actions.play(Actions.parallel(Actions.fadeIn(), Actions.sizeTo(0f, 0f, 0f)), binder.circle)

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

    protected fun playFirstAnimation() {
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
            val targetY = random.nextInt((binder.firstAnimContainer.bottom / 4f).toInt()).toFloat()
            Actions.play(
                Actions.sequence(
                    Actions.fadeOut(),
                    Actions.scaleTo(1.5f, 1.5f),
                    Actions.fadeIn(2f),
                    Actions.delay(delay),
                    Actions.parallel(
                        Actions.moveBy(0f, targetY, 2f, Interpolations.ElasticEaseOut),
                        Actions.sequence(
                            Actions.color(-1, Color.GREEN, 1f),
                            Actions.color(Color.GREEN, Color.RED, 1f)
                        ),
                        Actions.scaleTo(1f, 1f, 2f, Interpolations.ElasticEaseOut)
                    )
                ), view
            )
            binder.firstAnimContainer.addView(view)
        }
    }

    protected fun playSecondAnim() {
        reset()
        val center = Point(binder.secondAnimContainer.measuredWidth / 2, binder.secondAnimContainer.measuredHeight / 2)
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
            Actions.play(
                Actions.sequence(
                    Actions.parallel(Actions.fadeOut(), Actions.color(-1, Color.BLUE)),
                    Actions.moveTo(center.x.toFloat(), center.y.toFloat()),
                    Actions.fadeIn(.5f),
                    Actions.parallel(
                        Actions.moveTo(
                            newPos.x.toFloat(),
                            newPos.y.toFloat(),
                            1f,
                            Interpolations.ExponentialEaseOut
                        )
                    ),
                    Actions.run(
                        Runnable {
                            Actions.play(
                                Actions.sequence(
                                    Actions.color(Color.BLUE, Color.GREEN, 1f), Actions.forever(
                                        Actions.sequence(
                                            Actions.color(Color.GREEN, Color.RED, 1f),
                                            Actions.color(
                                                Color.GREEN, Color.RED, 1f
                                            )
                                        )
                                    )
                                ), view
                            )
                        }),
                    Actions.parallel(
                        Actions.rotateBy(720f, 2f, Interpolations.BackEaseOut), Actions.sequence(
                            Actions.scaleTo(.5f, .5f, 1f, Interpolations.BackEaseOut),
                            Actions.scaleTo(1f, 1f, 1f, Interpolations.ElasticEaseOut)
                        )
                    ),
                    Actions.sequence(
                        Actions.delay(delay), Actions.parallel(
                            Actions.fadeOut(.5f, Interpolations.ExponentialEaseOut),
                            Actions.scaleTo(0f, 1f, .5f, Interpolations.ExponentialEaseOut)
                        )
                    )
                ), view
            )
            delay += 1f
            binder.secondAnimContainer.addView(view)
        }
    }

    protected fun playThirdAnimation() {
        //reset();
        val duration = 5f
        val size = Resources.getSystem().displayMetrics.heightPixels
        Actions.play(
            Actions.forever(
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
                    Actions.parallel(Actions.fadeIn(), Actions.sizeTo(0f, 0f, 0f)),
                    Actions.parallel(
                        Actions.fadeOut(duration),
                        Actions.sizeTo(size.toFloat(), size.toFloat(), duration)
                    )
                )
            ), binder.circle
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