package com.dtx12.android_animations_actions.demo;

import android.animation.Animator;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dtx12.android_animations_actions.actions.Interpolations;

import java.util.Random;

import static com.dtx12.android_animations_actions.actions.Actions.*;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.firstAnimContainer)
    protected LinearLayout firstAnimContainer;
    @Bind(R.id.secondAnimContainer)
    protected FrameLayout secondAnimContainer;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.playSecondAnim)
    protected void playSecondAnim() {
        firstAnimContainer.removeAllViews();
        secondAnimContainer.removeAllViews();
        Point center = new Point(secondAnimContainer.getMeasuredWidth() / 2, secondAnimContainer.getMeasuredHeight() / 2);
        int size = getResources().getDimensionPixelSize(R.dimen.circle_size);
        float delay = 0;
        for (int i = 0; i < 6; i++) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
            final ImageView view = new ImageView(this);
            view.setLayoutParams(params);
            view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.square));
            Point newPos = new Point();
            switch (i) {
                case 0:
                    newPos.set(0, 0);
                    break;

                case 1:
                    newPos.set(0, center.y);
                    break;

                case 2:
                    newPos.set(0, center.y * 2 - size);
                    break;

                case 3:
                    newPos.set(center.x * 2 - size, 0);
                    break;

                case 4:
                    newPos.set(center.x * 2 - size, center.y);
                    break;

                case 5:
                    newPos.set(center.x * 2 - size, center.y * 2 - size);
                    break;
            }
            play(sequence(parallel(fadeOut(), color(-1, Color.BLUE)), moveTo(center.x, center.y),
                    fadeIn(.5f), moveTo(newPos.x, newPos.y, 1, Interpolations.ExponentialEaseOut),
                    parallel(rotateBy(720, 2, Interpolations.BackEaseOut), sequence(scaleTo(.5f, .5f, 1, Interpolations.BackEaseOut),
                            scaleTo(1, 1, 1, Interpolations.ElasticEaseOut))), sequence(delay(delay), parallel(fadeOut(.5f, Interpolations.ExponentialEaseOut),
                            scaleTo(0, 1, .5f, Interpolations.ExponentialEaseOut)))), view);
            delay += .5f;
            secondAnimContainer.addView(view);
        }
    }

    @OnClick(R.id.playFirstAnim)
    protected void playFirstAnimation() {
        firstAnimContainer.removeAllViews();
        secondAnimContainer.removeAllViews();
        for (int i = 0; i < 6; i++) {
            int size = getResources().getDimensionPixelSize(R.dimen.circle_size);
            int margin = getResources().getDimensionPixelSize(R.dimen.circle_margin);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.leftMargin = margin;
            params.rightMargin = margin;
            params.gravity = Gravity.CENTER;
            ImageView view = new ImageView(this);
            view.setLayoutParams(params);
            view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle));
            float delay = random.nextFloat();
            float targetY = random.nextInt((int) (firstAnimContainer.getBottom() / 4f));
            play(sequence(fadeOut(), scaleTo(1.5f, 1.5f), fadeIn(2), delay(delay),
                    parallel(moveBy(0, targetY, 2, Interpolations.ElasticEaseOut), sequence(color(-1, Color.GREEN, 1), color(Color.GREEN, Color.RED, 1)),
                            scaleTo(1, 1, 2, Interpolations.ElasticEaseOut))), view);
            firstAnimContainer.addView(view);
        }
    }
}
