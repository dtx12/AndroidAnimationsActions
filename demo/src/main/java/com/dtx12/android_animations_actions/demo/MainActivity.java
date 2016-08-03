package com.dtx12.android_animations_actions.demo;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dtx12.android_animations_actions.actions.Interpolations;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dtx12.android_animations_actions.actions.Actions.color;
import static com.dtx12.android_animations_actions.actions.Actions.delay;
import static com.dtx12.android_animations_actions.actions.Actions.fadeIn;
import static com.dtx12.android_animations_actions.actions.Actions.fadeOut;
import static com.dtx12.android_animations_actions.actions.Actions.forever;
import static com.dtx12.android_animations_actions.actions.Actions.moveBy;
import static com.dtx12.android_animations_actions.actions.Actions.moveTo;
import static com.dtx12.android_animations_actions.actions.Actions.parallel;
import static com.dtx12.android_animations_actions.actions.Actions.play;
import static com.dtx12.android_animations_actions.actions.Actions.rotateBy;
import static com.dtx12.android_animations_actions.actions.Actions.run;
import static com.dtx12.android_animations_actions.actions.Actions.scaleTo;
import static com.dtx12.android_animations_actions.actions.Actions.sequence;
import static com.dtx12.android_animations_actions.actions.Actions.sizeBy;
import static com.dtx12.android_animations_actions.actions.Actions.sizeTo;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.firstAnimContainer)
    protected LinearLayout firstAnimContainer;
    @Bind(R.id.secondAnimContainer)
    protected FrameLayout secondAnimContainer;
    @Bind(R.id.thirdAnimContainer)
    protected FrameLayout thirdAnimContainer;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void reset() {
        firstAnimContainer.removeAllViews();
        secondAnimContainer.removeAllViews();
        thirdAnimContainer.removeAllViews();
    }

    @OnClick(R.id.playSecondAnim)
    protected void playSecondAnim() {
        reset();
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
                    fadeIn(.5f), parallel(moveTo(newPos.x, newPos.y, 1, Interpolations.ExponentialEaseOut)), run(new Runnable() {
                        @Override
                        public void run() {
                            play(sequence(color(Color.BLUE, Color.GREEN, .1f), forever(sequence(color(Color.GREEN, Color.RED, 1), color(Color.GREEN, Color.RED, 1)))), view);
                        }
                    }),
                    parallel(rotateBy(720, 2, Interpolations.BackEaseOut), sequence(scaleTo(.5f, .5f, 1, Interpolations.BackEaseOut),
                            scaleTo(1, 1, 1, Interpolations.ElasticEaseOut))), sequence(delay(delay), parallel(fadeOut(.5f, Interpolations.ExponentialEaseOut),
                            scaleTo(0, 1, .5f, Interpolations.ExponentialEaseOut)))), view);
            delay += 1f;
            secondAnimContainer.addView(view);
        }
    }

    @OnClick(R.id.playFirstAnim)
    protected void playFirstAnimation() {
        reset();

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

    @OnClick(R.id.playThirdAnim)
    protected void playThirdAnimation() {
        reset();

        int size = getResources().getDimensionPixelSize(R.dimen.circle_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.circle_margin);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        params.leftMargin = margin;
        params.rightMargin = margin;
        params.gravity = Gravity.CENTER;
        ImageView view = new ImageView(this);
        view.setLayoutParams(params);
        view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle));
        thirdAnimContainer.addView(view);

        float targetX = thirdAnimContainer.getWidth() / 2f;
        float targetY = thirdAnimContainer.getHeight() / 2f - size / 2;

        play(sequence(color(-1, Color.GREEN), moveTo(targetX, targetY)), view);

        play(forever(sequence(sizeTo(size * 2, size * 2, 1f), (sizeBy(size, size, 1f)))), view);
    }
}
