package com.dtx12.android_animations_actions.demo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.dtx12.android_animations_actions.library.Interpolations;

import java.util.Random;

import static com.dtx12.android_animations_actions.library.Actions.*;


public class MainActivity extends AppCompatActivity {

    private LinearLayout container;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (LinearLayout) findViewById(R.id.container);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateCircles();
            }
        });
    }

    private void animateCircles() {
        container.removeAllViews();
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
            float targetY = random.nextInt((int) (container.getBottom() / 4f));
            play(sequence(fadeOut(), scaleTo(1.5f, 1.5f), fadeIn(2), delay(delay),
                    parallel(moveBy(0, targetY, 2, Interpolations.ElasticEaseOut), sequence(color(-1, Color.GREEN, 1), color(Color.GREEN, Color.RED, 1)),
                            scaleTo(1, 1, 2, Interpolations.ElasticEaseOut))), view);
            container.addView(view);
        }
    }
}
